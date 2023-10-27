package com.ssafy.ws.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ssafy.ws.model.dto.Article;
import com.ssafy.ws.model.dto.Board;
import com.ssafy.ws.model.dto.User;
import com.ssafy.ws.model.service.ArticleService;
import com.ssafy.ws.model.service.BoardService;
import com.ssafy.ws.model.service.UserService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/login")
	public String loginPage() {
		return "/user/loginPage";
	}
	
	@PostMapping("/login")
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
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/signup")
	public String registPage() {
		return "/user/signupPage";
	}
	
	@PostMapping("/signup")
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

	@GetMapping("/delete/{userSeq}")
	public String deleteUser(@PathVariable("userSeq") int userSeq, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		userService .removeUser(userSeq);
		String referer = request.getHeader("Referer");
		redirectAttributes.addAttribute("referer",referer);

		return "redirect:/admin/user";
	}

	@GetMapping("/myPage/{userSeq}")
	public String myPage(@PathVariable("userSeq") int userSeq, Model model) {
		User myUser = userService.searchByUserSeq(userSeq);
		model.addAttribute("user",myUser);
		
		if (myUser.getUserRank() == 2)
			return "/admin/adminPage";
		return "/user/myPage";
	}
	
	@GetMapping("/myPage/{userSeq}/update")
	public String updatePage(@PathVariable("userSeq") int userSeq, Model model) {
		model.addAttribute("user",userService.searchByUserSeq(userSeq));
		return "/user/updatePage";
	}
	
	@PostMapping("/myPage/{userSeq}/update")
	public String update(@PathVariable("userSeq") int userSeq, @ModelAttribute User user, HttpServletRequest request, Model model) {
		if (!user.getUserPwd().equals(request.getParameter("confirmedPwd"))) {
			model.addAttribute("msg","비밀번호를 확인하세요.");
			return "/user/updatePage";
		}
		
		userService.modifyUser(user);
		return "redirect:/user/myPage/" + userSeq;
	}

}
