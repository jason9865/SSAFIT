package com.ssafy.ws.Config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages="com.ssafy.ws.model.dao")
public class DBConfig {

}
