package com.sample.web.controller;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.hazelcast.core.Hazelcast;
import com.sample.web.model.Globalvariables;
import com.sample.web.util.WebUtils;

@Controller
public class HomeController {

	
	@Autowired
	RestTemplate restTemplate;
    
    @RequestMapping(method = RequestMethod.GET, value = "/users/extra")
    @ResponseBody
    public Map<String, Object> getExtraInfo(Authentication auth) {
        OAuth2AuthenticationDetails oauthDetails = (OAuth2AuthenticationDetails) auth.getDetails();

        Map<String, Object> details = (Map<String, Object>) oauthDetails.getDecodedDetails();
        details.put("Bearer", oauthDetails.getTokenValue());
        return details;
    }
    
    @RequestMapping(value = "/admin/home", method = RequestMethod.GET)
	public ModelAndView home(Authentication auth,HttpServletRequest request) {
    	System.out.println(auth.getName());
    	
		HttpSession httpSession =request.getSession();
		
		 OAuth2AuthenticationDetails oauthDetails = (OAuth2AuthenticationDetails) auth.getDetails();
		 
		 String token=oauthDetails.getTokenValue();
		System.out.println("session-id="+httpSession.getId());
		httpSession.setAttribute("TEST_SESSION", "Testing");
		
		Globalvariables globalvariables=new Globalvariables();
		globalvariables.setPersonName("tesing");
		
		
		Map<String,Globalvariables> map=Hazelcast.getHazelcastInstanceByName("hzCache").getMap("globalVariable");
		
		map.put(token, globalvariables);
		
//		Hazelcast.getHazelcastInstanceByName("_hzInstance_1_qa_HazelcastDistributedSession").getDistributedObjects();
		
		Cookie cookies[]=WebUtils.getRequest().getCookies();
		String setCookies="";
		if(cookies!=null) {
			for (Cookie cookie : cookies) {
				setCookies+=cookie.getName()+"="+cookie.getValue();
			}
		}
		System.out.println("Cookie_val-R1="+setCookies);
		
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	headers.add("Cookie", setCookies);
    	
    	
    	String userCode = "?userCode="+auth.getName();
    	
    	HttpEntity<String> entity = new HttpEntity<String>(userCode,headers);
    	String response=restTemplate.exchange(
    	         "http://192.168.2.45:8089/spring-test/spring-resource-2/test/test1?userCode=NEW PERSON 1", HttpMethod.GET, entity, String.class).getBody();
    	System.out.println(response);
    	/*String response2=restTemplate.exchange(
   	         "http://192.168.2.45:8089/spring-test/spring-resource-2/test/test2", HttpMethod.GET, entity, String.class).getBody();
    	System.out.println(response2);
    	String response3=restTemplate.exchange(
      	         "http://192.168.2.45:8089/spring-test/spring-resource-2/test/test3", HttpMethod.GET, entity, String.class).getBody();
    	System.out.println(response3);*/
    	ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/dummyhome");
		return modelAndView;
		
	}
    @RequestMapping(value = "/admin/dummyoption", method = RequestMethod.GET)
	public ModelAndView dummyoption() {
    	ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/dummyoption");
		return modelAndView;
		
	}
}
