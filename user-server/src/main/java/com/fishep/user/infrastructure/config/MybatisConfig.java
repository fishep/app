package com.fishep.user.infrastructure.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.fishep.user.infrastructure.mapper")
public class MybatisConfig {
}
