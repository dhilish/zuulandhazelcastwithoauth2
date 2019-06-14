package com.baeldung.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableResourceServer
//@EnableDiscoveryClient
@ConditionalOnProperty(prefix = "mainapp", name = "distributed", havingValue = "true")
public class OAuth2ResourceServerConfigRemoteTokenService extends ResourceServerConfigurerAdapter {


	@Value("${mainapp.distributed}")
	private boolean redirectUrl;
	
	 @Autowired
	    private CustomAccessTokenConverter customAccessTokenConverter;
	
    @Override
    public void configure(final HttpSecurity http) throws Exception {
	    	 http
	         .authorizeRequests()
	         .anyRequest().access("#oauth2.hasScope('corporateuser')")
	         .and()
	         .exceptionHandling()
	             .authenticationEntryPoint(authenticationEntryPoint());
    }

    @Primary
    @Bean
    public RemoteTokenServices tokenServices() {
        final RemoteTokenServices tokenService = new RemoteTokenServices();
        tokenService.setAccessTokenConverter(customAccessTokenConverter);
        tokenService.setCheckTokenEndpointUrl("http://localhost:8081/spring-security-identity-server/oauth/check_token");
        tokenService.setClientId("sampleERP_TempCustomer1");
        tokenService.setClientSecret("temp_SampleERPCustomerSecret");
        return tokenService;
    }    


    private AuthenticationEntryPoint authenticationEntryPoint() {
        return new AuthenticationEntryPoint() {
            @Override
            public void commence(HttpServletRequest aRequest, HttpServletResponse aResponse,
                   AuthenticationException aAuthException) throws IOException, ServletException {
                aResponse.sendRedirect("http://localhost:8089/login");
            }
        };
    }
    
    @Bean
   	public RestTemplate restTemplate() {
   	    return new RestTemplate();
   	}
    
}


