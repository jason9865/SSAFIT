package com.ssafit.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.ssafit.user.model.dto.response.UserResponse;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

@Component
public class JwtUtil {
	
	private static final String SALT = "SSAFIT";
	
	
	//토큰 생성 메서드
	//데이터가 하나만 인자로 들어온다라고 가정하고 / 테스트에 방해가 되는 유효기간 제거
	public String createToken(String key, String value) throws UnsupportedEncodingException {
		return Jwts.builder()
				.setHeaderParam("alg", "HS256")
				.setHeaderParam("typ", "JWT") //헤더완료
				.claim(key, value)
				.signWith(SignatureAlgorithm.HS256, SALT.getBytes("UTF-8")) //서명완료
				.compact();
	}
	
	// String으로 된 코드를 복호화
	public Jws<Claims> getClaims(String token) throws Exception {
		// 암호화 키로 복호화. 즉 암호화 키가 다르면 에러가 발생.
		try {
			return Jwts.parser().setSigningKey(SALT.getBytes("UTF-8")).parseClaimsJws(token);
		} catch (SignatureException e) {
			return null;
		}
	}
	
	// 토큰 검증 함수
	public boolean validateToken(Jws<Claims> claims) {
		return !claims.getBody()
					  .getExpiration()
					  .before(new Date());
	}
	
	// 토큰을 통해 PayLoad의 ID를 가져옴
	public String getKey(Jws<Claims> claims) {
		// ID 가져오기
		return claims.getBody().getId();
	}
	
	// 토큰을 통해 PayLoad의 데이터를 가져옴
	public Object getClaims(Jws<Claims> claims, String key) {
		// 데이터 가져오기
		return claims.getBody().get(key);
	}
	
}
