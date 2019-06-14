package co.auth.identity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
public class IdentityApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
	 	System.setProperty("http.proxyHost", "127.0.0.1");
 		System.setProperty("https.proxyHost", "127.0.0.1");
 		System.setProperty("http.proxyPort", "8888");
 		System.setProperty("https.proxyPort", "8888");
		SpringApplication.run(IdentityApplication.class, args);
	}

}
