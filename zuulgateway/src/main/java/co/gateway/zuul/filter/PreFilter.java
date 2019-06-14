package co.gateway.zuul.filter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class PreFilter extends ZuulFilter {

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		
		HttpServletRequest request = ctx.getRequest();
		
		HttpSession httpSession = request.getSession();
		
		System.out.println(httpSession.getId());
		Cookie[] cookies = request.getCookies();
		String printCookies="";
		if(cookies!=null) {
			for (Cookie cookie : cookies) {
				printCookies+=cookie.getName()+"="+cookie.getValue();
				}
		}
		System.out.println("CookieVal="+printCookies);
		if(httpSession.getAttribute("Authorization").toString() != null) {
			ctx.addZuulRequestHeader("Authorization", httpSession.getAttribute("Authorization").toString());
		}
		System.out.println(
				"Request Method : " + request.getMethod() + " Request URL : " + request.getRequestURL().toString());

		return null;
	}

}