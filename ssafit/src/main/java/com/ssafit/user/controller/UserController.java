package com.ssafit.user.controller;

import com.ssafit.user.model.dto.User;
import com.ssafit.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	public String login(Model model, User user,HttpSession session) {
		String view = "/user/loginPage";
		User loginUser = userService.login(user);
		if (loginUser != null) {
			session.setAttribute("loginUser",loginUser);
			view = "redirect:/";
		} else {
			model.addAttribute("msg"," 로그인 실패");
		}
		
		return view;
	}

//	@PostMapping("/login")
//	public ResponseEntity<?> login(User user,HttpSession session) {
//		User loginUser = userService.login(user);
//		if (loginUser != null) {
//			session.setAttribute("loginUser",loginUser);
//			return new ResponseEntity<User>(loginUser,HttpStatus.OK);
//		} else {
//			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
//		}
//
//	}
	
	@GetMapping("/logout")
	@ApiOperation(value="로그아웃", notes="로그아웃 기능 구현")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@PostMapping("/signup")
	@ApiOperation(value="회원가입", notes="bindingResult 조건 추후 추가 예정")
	public String regist(@ModelAttribute User user,HttpServletRequest request,Model model) {
		if (!user.getUserPwd().equals(request.getParameter("confirmedPwd"))) {
			model.addAttribute("msg","비밀번호를 확인하세요.");
			return "/user/signupPage";
		}
		userService.registUser(user);
		return "redirect:/";
	}

//	@PostMapping("/signup")
//	public ResponseEntity<Integer> signup(@ModelAttribute User user,HttpServletRequest request,Model model){
////		int result = userService.registUser(user);
//
//		// result가 0이면 등록X
//		// result가 1이면 등록O
//	}

//	@GetMapping("/delete/{userSeq}")
	@DeleteMapping("/delete/{userSeq}")
	@ApiOperation(value="유저 삭제", notes="관리자 계정만 접근 가능합니다.")
	public String deleteUser(@PathVariable("userSeq") int userSeq, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		userService .removeUser(userSeq);
		String referer = request.getHeader("Referer");
		redirectAttributes.addAttribute("referer",referer);

		return "redirect:/admin/user";
	}

	@GetMapping("/myPage/{userSeq}")
	@ApiOperation(value="마이 페이지", notes="로그인 유저만 접근 가능합니다.")
	public String myPage(@PathVariable("userSeq") int userSeq, Model model) {
		User myUser = userService.searchByUserSeq(userSeq);
		model.addAttribute("user",myUser);
		
		if (myUser.getUserRank() == 2)
			return "/admin/adminPage";
		return "/user/myPage";
	}
	
	@PostMapping("/myPage/{userSeq}/update")
	@ApiOperation(value="유저 정보 갱신", notes="로그인 유저만 접근 가능합니다.")
	public String update(@PathVariable("userSeq") int userSeq, @ModelAttribute User user, HttpServletRequest request, Model model) {
		if (!user.getUserPwd().equals(request.getParameter("confirmedPwd"))) {
			model.addAttribute("msg","비밀번호를 확인하세요.");
			return "/user/updatePage";
		}
		
		userService.modifyUser(user);
		return "redirect:/user/myPage/" + userSeq;
	}


	///////////////////
	// jsp를 위한 메서드
	@GetMapping("/login")
	public String loginPage() {
		return "/user/loginPage";
	}
	@GetMapping("/myPage/{userSeq}/update")
	public String updatePage(@PathVariable("userSeq") int userSeq, Model model) {
		model.addAttribute("user",userService.searchByUserSeq(userSeq));
		return "/user/updatePage";
	}

	@GetMapping("/signup")
	@ApiOperation(value="비디오 조회", notes="검색조건 추후 추가 예정")
	public String registPage() {
		return "/user/signupPage";
	}

}
