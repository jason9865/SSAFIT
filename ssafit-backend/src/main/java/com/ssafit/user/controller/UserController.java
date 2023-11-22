package com.ssafit.user.controller;

import com.ssafit.user.model.dto.request.FindPwRequest;
import com.ssafit.user.model.dto.request.UserLoginRequest;
import com.ssafit.user.model.dto.request.UserModifyRequest;
import com.ssafit.user.model.dto.request.UserRegistRequest;
import com.ssafit.user.model.dto.response.UserResponse;
import com.ssafit.user.model.entity.Mail;
import com.ssafit.user.model.entity.User;
import com.ssafit.user.service.UserService;
import com.ssafit.util.JwtUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
@Api(tags="사용자 관리 컨트롤러")
@CrossOrigin("http://localhost:5173/")
public class UserController {
	
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/login")
	@ApiOperation(value="로그인을 합니다.", notes="bindingResult 추후 추가 예정")
	public ResponseEntity<Map<String, Object>> login(@RequestBody UserLoginRequest loginRequest, HttpSession session) {
		Map<String, Object> result = new HashMap<String, Object>();
		UserResponse loginUser = userService.login(loginRequest);
		System.out.println(loginUser);
		
		HttpStatus status = null;
		
		if (loginUser != null) {
			try {
				result.put("access-token", jwtUtil.createToken("seq", ""+loginUser.getUserSeq()));
				result.put("loginUser", loginUser);	
				result.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (UnsupportedEncodingException e) {
				result.put("message", FAIL);
				status = HttpStatus.NO_CONTENT;
			}
		} else {
			result.put("message", FAIL);
			status = HttpStatus.NO_CONTENT;
		}
		return new ResponseEntity<Map<String, Object>>(result, status);
	}
	
	@GetMapping("/logout")
	@ApiOperation(value="로그아웃", notes="로그아웃 기능 구현")
	public ResponseEntity<Void> logout(HttpSession session) {
		session.invalidate();
		return ResponseEntity.ok().build();
	}

	@GetMapping
	@ApiOperation(value="회원 리스트 가져오기")
	public ResponseEntity<List<UserResponse>> getUsers() {
		return new ResponseEntity<List<UserResponse>>(userService.getUserList(),HttpStatus.OK);
	}

	@GetMapping("/{userSeq}")
	@ApiOperation(value="단일 회원 가져오기")
	public ResponseEntity<UserResponse> getUser(@PathVariable int userSeq) {
		return new ResponseEntity<UserResponse>(userService.searchByUserSeq(userSeq),HttpStatus.OK);
	}
	
	@PostMapping("/signup")
	@ApiOperation(value="회원가입", notes="bindingResult 조건 추후 추가 예정")
	public ResponseEntity<Boolean> regist(@RequestBody UserRegistRequest userRegistRequest) {

		boolean isRegisted = userService.registUser(userRegistRequest);
		if(!isRegisted)
			return new ResponseEntity<Boolean>(isRegisted,HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Boolean>(isRegisted,HttpStatus.OK);
	}

	@PutMapping("/update/{userSeq}")
	@ApiOperation(value="유저 정보 갱신", notes="로그인 유저만 접근 가능합니다.")
	public ResponseEntity<Boolean> updateUser(@PathVariable int userSeq, @RequestBody UserModifyRequest userModifyRequest) {
		boolean isModified = userService.modifyUser(userModifyRequest,userSeq);
		if(!isModified)
			return new ResponseEntity<Boolean>(isModified,HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Boolean>(isModified,HttpStatus.OK);
	}

	@DeleteMapping("/delete/{userSeq}")
	@ApiOperation(value="유저 삭제", notes="관리자 계정만 접근 가능합니다.")
	public ResponseEntity<Boolean> removeUser(@PathVariable int userSeq) {
		boolean isRemoved = userService.removeUser(userSeq);
		if(!isRemoved)
			return new ResponseEntity<Boolean>(isRemoved,HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Boolean>(isRemoved,HttpStatus.OK);
	}
	
	@PostMapping("/sendMail")
	@ApiOperation(value="비밀번호 찾기", notes="userId와 userEmail 유효성 검증 이후 이메일로 임시비밀번호 발급")
	public ResponseEntity<String> sendMail(@RequestBody FindPwRequest FindPwRequest) {
		String message = "이메일로 임시비밀번호가 발급되었습니다.";
		System.out.println(FindPwRequest.getUserId());
		System.out.println(FindPwRequest.getEmail());
		
		String userId = FindPwRequest.getUserId();
		String email = FindPwRequest.getEmail();
		
		User user = userService.searchByUserId(userId);
		if(user == null) {	// 아이디 유효 검증
			message = "아이디를 확인해주세요.";
		} else {
			if(user.getEmail().equals(email)) {	// 이메일 유효 검증
				Mail mail = userService.createMailAndChangePwd(userId, email);
				userService.sendMail(mail);
			} else {
				message = "이메일을 확인해주세요.";
			}
		}
		
		
		return new ResponseEntity<String>(message,HttpStatus.OK);
	}


}
