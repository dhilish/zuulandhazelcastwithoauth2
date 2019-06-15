package com.sample.web.initializer;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

import com.hazelcast.config.SecurityConfig;
import com.sample.config.ResourceServerApplication;

public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {

	public SecurityInitializer() {
		super(SecurityConfig.class, ResourceServerApplication.class);
	}
}