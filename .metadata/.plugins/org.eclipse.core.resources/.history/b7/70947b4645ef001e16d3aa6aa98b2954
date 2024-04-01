//package com.microservice.EmpOfficeDetails.security;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//@EnableWebSecurity
//public class DemoSecurityConfig  {
//
//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager() {
//        UserDetails user = User.builder()
//                .username("user")
//                .password("{noop}test123")
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("{noop}test123")
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(user, admin);
//    }
//
//    @SuppressWarnings({ "deprecation", "removal" })
//	@Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeRequests(authorizeRequests ->
//                authorizeRequests
//                       // .requestMatchers("/swagger-ui/**").permitAll()
//              
//                        .requestMatchers(HttpMethod.GET, "/employees/{employeeId}").hasAnyRole("USER", "ADMIN")
//                        .requestMatchers(HttpMethod.GET, "/employees/personal/{employeeId}").hasAnyRole("USER", "ADMIN")
//                        .requestMatchers(HttpMethod.POST, "/employees").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.PUT, "/employees/{employeeId}").hasAnyRole("USER", "ADMIN")
//                        .requestMatchers(HttpMethod.DELETE, "/employees/{employeeId}").hasRole("ADMIN")
//                        .anyRequest().authenticated()
//        );
//
//        // use HTTP Basic authentication	
//        http.httpBasic();
//        http.csrf().disable(); 
//        return http.build();
//    }
//}
