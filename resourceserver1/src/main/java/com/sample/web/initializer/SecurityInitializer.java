package com.sample.web.initializer;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

import com.sample.config.HazSessionConfig;
import com.sample.config.SecurityConfig;

public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {

	public SecurityInitializer() {
		super(SecurityConfig.class, HazSessionConfig.class);
	}
}