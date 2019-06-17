package co.gateway.zuul.initializer;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

import co.gateway.zuul.config.HazSessionConfig;
import co.gateway.zuul.config.SecurityConfig;

public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {

	public SecurityInitializer() {
		super(SecurityConfig.class, HazSessionConfig.class);
	}
}