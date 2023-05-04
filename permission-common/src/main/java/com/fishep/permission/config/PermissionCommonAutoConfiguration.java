package com.fishep.permission.config;

import com.fishep.permission.aspect.PermissionAspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(PermissionAspect.class)
public class PermissionCommonAutoConfiguration {
}
