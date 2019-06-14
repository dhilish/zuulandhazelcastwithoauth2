package co.auth.identity.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class WebUtils {
	
	public static HttpServletRequest getRequest() {
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		HttpServletRequest servletRequest = null;
		if (null != requestAttributes) {
			servletRequest = ((ServletRequestAttributes) requestAttributes).getRequest();
		}
		return servletRequest;
	}
}
