package co.gateway.zuul.initializer;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

import com.hazelcast.config.SecurityConfig;

import co.gateway.zuul.DemoApplication;

public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {

	public SecurityInitializer() {
		super(SecurityConfig.class, DemoApplication.class);
	}
}