package com.baeldung.web.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baeldung.web.util.WebUtils;

@RestController
@RequestMapping("/test")
public class TestController {

	@GetMapping(value ="/test1")
	public HttpEntity<String> test1(@RequestParam String userCode,HttpServletRequest request){
		
		HttpSession httpSession=WebUtils.getRequest().getSession();
		Authentication authentication = (Authentication) httpSession.getAttribute("Authentication");
		System.out.println("Auth_name from Session set in zuul custom_success handler: "+authentication.getName());
		
		System.out.println("TEST_SESSION = "+httpSession.getAttribute("TEST_SESSION"));
		String val="testing1";
		System.out.println(val);
		HttpEntity<String> httpEntity=new HttpEntity<String>(val);
		return httpEntity;
	}
	@GetMapping(value ="/test2")
	public HttpEntity<String> test2(){
	
		HttpSession httpSession=WebUtils.getRequest().getSession();
		
		Cookie cookies[]=WebUtils.getRequest().getCookies();
		String setCookies="";
		if(cookies!=null) {
			for (Cookie cookie : cookies) {
				setCookies+=cookie.getName()+"="+cookie.getValue();
			}
		}
		System.out.println("Cookie_val-R2="+setCookies);
		
		System.out.println("TEST_SESSION = "+httpSession.getAttribute("TEST_SESSION"));
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
