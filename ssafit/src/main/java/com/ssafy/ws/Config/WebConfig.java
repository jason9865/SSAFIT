package com.ssafy.ws.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

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
	
	@Bean
	public ViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver bean = new InternalResourceViewResolver();
		bean.setViewClass(JstlView.class);
		bean.setPrefix("/WEB-INF/views");
		bean.setSuffix(".jsp");
		return bean;
	}

	// CORS에러를 해결하기 위해 컨트롤러에 각각 작성할수도 있지만 공통처리를 위해서는 여기에
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*"); // 전부 허용
		registry.addMapping("/**").allowedMethods("GET","POST"); // 메서드 가지고 허용
	}
	
}
