package com.eazybytes.config;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.eazybytes.model.Customer;
import com.eazybytes.repository.CustomerRepository;

@Service
public class EazyBankuserDetailsService implements UserDetailsService{
	
	private static CustomerRepository customerRepository;
	
	public EazyBankuserDetailsService(CustomerRepository customerRepository) {
		EazyBankuserDetailsService.customerRepository = customerRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer customer = customerRepository.findByEmail(username).get();
		if(customer == null){
			throw new UsernameNotFoundException("user not found");
		}
		List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(customer.getRole()));
		return new User(customer.getEmail(), customer.getPwd(), authorities);
	}

}
