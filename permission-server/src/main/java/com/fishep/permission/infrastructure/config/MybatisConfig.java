package com.fishep.permission.infrastructure.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.fishep.permission.infrastructure.mapper")
public class MybatisConfig {
}
