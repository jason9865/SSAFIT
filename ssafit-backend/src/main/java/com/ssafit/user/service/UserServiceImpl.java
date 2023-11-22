package com.ssafit.user.service;

import com.ssafit.user.model.dao.UserDao;
import com.ssafit.user.model.dto.request.UserLoginRequest;
import com.ssafit.user.model.dto.request.UserModifyRequest;
import com.ssafit.user.model.dto.request.UserRegistRequest;
import com.ssafit.user.model.dto.response.UserResponse;
import com.ssafit.user.model.entity.Mail;
import com.ssafit.user.model.entity.User;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserDao userDao;
	private final MailSender mailSender;
	
	@Value("${spring.mail.username}")
	private String from;

	@Override
	public List<UserResponse> getUserList() {
		return userDao.selectAll().stream().map(UserResponse::from).collect(toList());

	}

	@Override
	public UserResponse searchByUserSeq(int userSeq) {
		return UserResponse.from(userDao.selectByUserSeq(userSeq));
	}

	@Override
	public User searchByUserId(String id) {
		return userDao.selectByUserId(id);
	}

	@Override
	public User searchByName(String name) {
		return userDao.selectByName(name);
	}

	@Override
	@Transactional
	public boolean registUser(UserRegistRequest request) {
		User newUser = User.builder().userId(request.getUserId()).userPwd(request.getUserPwd())
				.userName(request.getUserName()).nickName(request.getNickName()).email(request.getEmail()).build();
		return userDao.insertUser(newUser) > 0;
	}

	@Override
	@Transactional
	public boolean modifyUser(UserModifyRequest request, int userSeq) {
		User updatedUser = User.builder().userSeq(userSeq).userPwd(request.getUserPwd()).userName(request.getUserName())
				.nickName(request.getNickName()).email(request.getEmail()).build();
		return userDao.updateUser(updatedUser) > 0;
	}

	@Override
	@Transactional
	public boolean removeUser(int userSeq) {
		return userDao.deleteUser(userSeq) > 0;
	}

	@Override
	public UserResponse login(UserLoginRequest loginRequest) {
		User temp = userDao.selectByUserId(loginRequest.getUserId());
		if (temp != null && temp.getUserPwd().equals(loginRequest.getUserPwd()))
			return UserResponse.from(temp);
		return null;
	}

	@Override
	public Mail createMailAndChangePwd(String userId, String email) {
		String tempPwd = getTempPwd();
		Mail mail  = new Mail();
		mail.setAddress(email);
		mail.setTitle("SSAFIT 임시비밀번호 발급");
		
		mail.setMessage("안녕하세요, SSAFIT 임시비밀번호 발급 메일입니다."
						+"회원님의 임시 비밀번호는"
						+ tempPwd
						+"입니다. 로그인 후 비밀번호를 변경해주세요.");
		
		updatePwd(userId, tempPwd);
		System.out.println("임시비밀번호" + " " + tempPwd);
		
		return mail;
	}

	@Override
	public String getTempPwd() {
		char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
				'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

		String str = "";

		// 문자 배열 길이의 값을 랜덤으로 10개를 뽑아 구문을 작성함
		int idx = 0;
		for (int i = 0; i < 10; i++) {
			idx = (int) (charSet.length * Math.random());
			str += charSet[idx];
		}
		
		return str;
	}

	@Override
	public void sendMail(Mail mail) {
		SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mail.getAddress());
        message.setSubject(mail.getTitle());
        message.setText(mail.getMessage());
        message.setFrom(from);
        message.setReplyTo(from);
        System.out.println(message);
        mailSender.send(message);
	}

	@Override
	public void updatePwd(String userId, String pwd) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("pwd", pwd);
		
		userDao.updatePwd(map);
	}

}
