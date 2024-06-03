package com.employee.EmpData.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;




@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	@Bean
	public UserDetailsService userDetailsService() {
        return new UserInfoUserDetailsService();
    }

	
	@SuppressWarnings("deprecation")
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		 http.authorizeRequests(authorizeRequests ->
			authorizeRequests.requestMatchers("/swagger-ui/**", "/v3/api-docs").permitAll()
			.requestMatchers(HttpMethod.POST, "/employees").permitAll()
				.requestMatchers(HttpMethod.GET, "/employees/{employeeId}").hasAnyRole("EMP", "ADMIN", "MANAGER")
					.requestMatchers(HttpMethod.GET, "/employees/personal/{employeeId}").hasAnyRole("MANAGER", "ADMIN")
					.requestMatchers(HttpMethod.PUT, "/employees/{employeeId}").hasAnyRole("EMP", "ADMIN")
					.requestMatchers(HttpMethod.DELETE, "/employees/{employeeId}").hasRole("ADMIN").anyRequest()
					.authenticated());

// use HTTP Basic authentication
			http.httpBasic(Customizer.withDefaults());

// disable Cross Site Request Forgery (CSRF)
// in general, not required for stateless REST APIs that use POST, PUT, DELETE and/or PATCH
			http.csrf(csrf -> csrf.disable());

			return http.build();
		}
	
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
}