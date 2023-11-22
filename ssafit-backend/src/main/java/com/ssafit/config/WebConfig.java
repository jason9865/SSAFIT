package com.ssafit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.BeanNameViewResolver;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer{
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");

		registry.addResourceHandler("/swagger-ui/**");
	}
	
	@Bean
	public ViewResolver beanViewResolver() {
		BeanNameViewResolver bean = new BeanNameViewResolver();
		bean.setOrder(0);
		return bean;
	}


	// CORS에러를 해결하기 위해 컨트롤러에 각각 작성할수도 있지만 공통처리를 위해서는 여기에
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*"); // 전부 허용
		registry.addMapping("/**").allowedMethods("GET","POST"); // 메서드 가지고 허용
	}
	
	@Bean
	public MailSender getMailSender() {
		return new MailSender() {
			
			@Override
			public void send(SimpleMailMessage... simpleMessages) throws MailException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void send(SimpleMailMessage simpleMessage) throws MailException {
				// TODO Auto-generated method stub
				
			}
		};
	}
	
}
