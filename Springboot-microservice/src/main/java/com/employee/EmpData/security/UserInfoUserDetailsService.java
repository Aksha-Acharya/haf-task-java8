package com.employee.EmpData.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.employee.EmpData.entity.UserAccess;
import com.employee.EmpData.entity.repository.UserAccessRepository;

public class UserInfoUserDetailsService implements UserDetailsService {

	@Autowired
	UserAccessRepository userAccessRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<UserAccess> user = userAccessRepository.findByUsername(username);
		return user.map(UserInfoUserDetails::new).orElseThrow(()-> new UsernameNotFoundException("User not found!!"+username));
	}

}
