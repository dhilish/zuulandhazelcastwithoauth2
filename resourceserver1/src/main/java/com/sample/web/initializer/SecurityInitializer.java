package com.sample.web.initializer;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

import com.sample.config.HazSessionConfig;
import com.sample.config.OAuth2ResourceServerConfigRemoteTokenService;

public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {

	public SecurityInitializer() {
		super(OAuth2ResourceServerConfigRemoteTokenService.class, HazSessionConfig.class);
	}
}