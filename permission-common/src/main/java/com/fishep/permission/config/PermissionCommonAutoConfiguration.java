package com.fishep.permission.config;

import com.fishep.permission.aspect.PermissionAspect;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
//@Import(PermissionAspect.class)
@ComponentScans(@ComponentScan("com.fishep.permission"))
public class PermissionCommonAutoConfiguration {
}
