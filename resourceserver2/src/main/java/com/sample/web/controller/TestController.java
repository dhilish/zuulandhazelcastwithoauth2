package com.sample.web.controller;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.sample.web.model.Globalvariables;
import com.sample.web.util.WebUtils;

@RestController
@RequestMapping("/test")
public class TestController {

private HazelcastInstance hazelcastInstance = null;
	
	
	@Autowired
	FindByIndexNameSessionRepository<? extends Session> sessions;
	
	@GetMapping(value ="/test1")
	public HttpEntity<String> test1(@RequestParam String userCode,HttpServletRequest request){

		HttpSession httpSession=request.getSession();
		Authentication authentication = (Authentication) httpSession.getAttribute("Authentication");

		String token = null;
		if(authentication!=null) {
			DefaultOAuth2User auth =(DefaultOAuth2User) authentication.getPrincipal();
			 token = (String) auth.getAttributes().get("Bearer");
		}
		
		Session session=sessions.findById(httpSession.getId());
		
		if(session!=null)
			System.out.println(session.getAttribute("TEST_SESSION").toString());
		
		Map<String,Globalvariables> map=Hazelcast.getHazelcastInstanceByName("hzCache").getMap("globalVariable");
		
		if(token!=null)
			System.out.println(map.get(token).getPersonName());
		
		
		
		if(authentication!=null)
		System.out.println("Auth_name from Session set in zuul custom_success handler: "+authentication.getName());

		System.out.println("TEST_SESSION setted in resourse-1 = "+httpSession.getAttribute("TEST_SESSION"));
		String val="testing1";
		System.out.println(val);
		HttpEntity<String> httpEntity=new HttpEntity<String>(val);
		return httpEntity;
	}
	@GetMapping(value ="/test2")
	public HttpEntity<String> test2(HttpServletRequest request){

		HttpSession httpSession=request.getSession(false);
		Authentication authentication = (Authentication) httpSession.getAttribute("Authentication");
		if(authentication!=null)
			System.out.println("Auth_name from Session set in zuul custom_success handler: "+authentication.getName());

		Cookie cookies[]=WebUtils.getRequest().getCookies();
		String setCookies="";
		if(cookies!=null) {
			for (Cookie cookie : cookies) {
				setCookies+=cookie.getName()+"="+cookie.getValue();
			}
		}
		System.out.println("Cookie_val-R2="+setCookies);

		System.out.println("TEST_SESSION setted in resourse-1 = "+httpSession.getAttribute("TEST_SESSION"));
		String val="testing2";
		System.out.println(val);
		HttpEntity<String> httpEntity=new HttpEntity<>(val);
		return httpEntity;
	}

	@GetMapping(value ="/test3")
	public HttpEntity<String> test3(){
		String val="testing3";
		System.out.println(val);
		HttpEntity<String> httpEntity=new HttpEntity<>(val);
		return httpEntity;
	}
}
