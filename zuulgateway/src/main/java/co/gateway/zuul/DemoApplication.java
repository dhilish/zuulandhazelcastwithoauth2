package co.gateway.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import co.gateway.zuul.filter.ErrorFilter;
import co.gateway.zuul.filter.PreFilter;
import co.gateway.zuul.filter.RouteFilter;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class DemoApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		System.setProperty("http.proxyHost", "127.0.0.1");
 		System.setProperty("https.proxyHost", "127.0.0.1");
 		System.setProperty("http.proxyPort", "8888");
 		System.setProperty("https.proxyPort", "8888");
		SpringApplication.run(DemoApplication.class, args);
	}
	 
	
	@Bean
	public PreFilter preFilter() {
		return new PreFilter();
	}
	
	@Bean
	public ErrorFilter errorFilter() {
		return new ErrorFilter();
	}

	@Bean
	public RouteFilter routeFilter() {
		return new RouteFilter();
	}
}
