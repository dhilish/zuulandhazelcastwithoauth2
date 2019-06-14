/*--------------------------------------------------------------------------------------------------------------
AUTHOR                          : Sandeep P Pillai
DATE                            : 31/01/2018
PURPOSE/NOTES                   : Spring security configuration
IMPORTANT VARIABLES             :
ASSUMPTIONS/LIMITATIONS         :
GLOBAL VARIABLES USED           :
LIBRARY USED                    :  
CLASSES USED                    : 
----------------------------------------------------------------------------------------------------------------
REVISION HISTORY
----------------------------------------------------------------------------------------------------------------
Date                            By                                      Notes
----------------------------------------------------------------------------------------------------------------

----------------------------------------------------------------------------------------------------------------
PREFIX OF VARIABLES
GLOBAL                          : g_
CLASS                           : _
SUB/FUNCTION/PROPERTY/EVENT     : NIL
ARGUMENTS                       : a_ 
--------------------------------------------------------------------------------------------------------------*/

package com.baeldung.config;

import java.util.Arrays;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@ConditionalOnProperty(prefix = "mainapp", name = "distributed", havingValue = "false")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
    private BCryptPasswordEncoder passwordEncoder;
	   
	@Value("${mainapp.distributed}")
	private boolean redirectUrl;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		  .withUser("john").password(passwordEncoder.encode("123")).roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests().antMatchers("/").permitAll();

	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}

}