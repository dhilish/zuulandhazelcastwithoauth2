/*--------------------------------------------------------------------------------------------------------------
AUTHOR                          : Cimple Joseph
DATE                            : 22/02/2018
PURPOSE/NOTES                   : Custom authentication provider
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

package co.auth.identity.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	RestTemplate restTemplate =new RestTemplate();

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String code = authentication.getName();
		String password = (String) authentication.getCredentials();
		UsernamePasswordAuthenticationToken authToken = null;
		if(code!=null&&password!=null) {
			Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
			authToken = new UsernamePasswordAuthenticationToken(code, password, grantedAuthorities);
		} else {
				throw new UsernameNotFoundException("Username and Password not found.");
		}
		return authToken;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}

}
