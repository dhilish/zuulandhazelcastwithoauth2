package co.gateway.zuul.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.client.RestTemplate;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler 
{

	@Autowired
	RestTemplate restTemplate;
	
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		HttpSession httpSession = request.getSession();
		DefaultOAuth2User auth =(DefaultOAuth2User) authentication.getPrincipal();
		String token = (String) auth.getAttributes().get("Bearer");
		httpSession.setAttribute("Authorization", "Bearer " + token);
		httpSession.setAttribute("Authentication", authentication);
		Cookie[] cookies = request.getCookies();
		String printCookies="";
		if(cookies!=null) {
			for (Cookie cookie : cookies) {
				printCookies+=cookie.getName()+"="+cookie.getValue();
				}
		}
		System.out.println("CookieVal-successHandler="+printCookies);
		response.sendRedirect("http://localhost:8089/spring-test/spring-resource/admin/home");
	}

}
