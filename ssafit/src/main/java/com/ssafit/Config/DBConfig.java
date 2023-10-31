package com.ssafit.Config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages= "com.ssafit.*.model.dao")
public class DBConfig {

}
