package com.ssafit.user.controller;

import com.ssafit.user.model.dto.request.UserLoginRequest;
import com.ssafit.user.model.dto.request.UserModifyRequest;
import com.ssafit.user.model.dto.request.UserRegistRequest;
import com.ssafit.user.model.dto.response.UserResponse;
import com.ssafit.user.model.entity.User;
import com.ssafit.user.service.UserService;
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
import java.util.List;

@Controller
@RequestMapping("/user")
@Api(tags="사용자 관리 컨트롤러")
public class UserController {
	
	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/login")
	@ApiOperation(value="로그인을 합니다.", notes="bindingResult 추후 추가 예정")
	public ResponseEntity<?> login(@RequestBody UserLoginRequest loginRequest, HttpSession session) {
		UserResponse loginUser = userService.login(loginRequest);
		System.out.println(loginUser);
		if (loginUser != null) {
			session.setAttribute("loginUser",loginUser);
			return new ResponseEntity<UserResponse>(loginUser, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}
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
		return new ResponseEntity<List<UserResponse>>(userService.getList(),HttpStatus.OK);
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



}
