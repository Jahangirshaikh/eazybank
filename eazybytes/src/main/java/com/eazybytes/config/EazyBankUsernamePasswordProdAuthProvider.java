package com.eazybytes.config;

import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
public class EazyBankUsernamePasswordProdAuthProvider implements AuthenticationProvider{
	
	private static EazyBankuserDetailsService eazyBankuserDetailsService;
	private static PasswordEncoder passwordEncoder;
	
	public EazyBankUsernamePasswordProdAuthProvider(EazyBankuserDetailsService eazyBankuserDetailsService,
			PasswordEncoder passwordEncoder) {
		EazyBankUsernamePasswordProdAuthProvider.eazyBankuserDetailsService = eazyBankuserDetailsService;
		EazyBankUsernamePasswordProdAuthProvider.passwordEncoder = passwordEncoder;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		UserDetails userDetails = eazyBankuserDetailsService.loadUserByUsername(username);
		if(passwordEncoder.matches(password, userDetails.getPassword())) {
			return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
		}
		else {
			throw new BadCredentialsException("Invalid Credentials.");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
