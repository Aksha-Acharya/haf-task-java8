//package com.employee.EmpData.security;
//import javax.sql.DataSource;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.provisioning.JdbcUserDetailsManager;
//import org.springframework.security.provisioning.UserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//@Configuration
//@EnableWebSecurity
//public class DemoSecurityConfig  {
//
//
//	 @Bean
//	    public UserDetailsManager userDetailsManager(DataSource dataSource) {
//
//	        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//
//	        // define query to retrieve a user by username
//	        jdbcUserDetailsManager.setUsersByUsernameQuery(
//	                "select username, password from user_access where username=?");
//	      
//	        // define query to retrieve the authorities/roles by username
//	        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
//	                "select username, roles from user_access where username=?");
//
//	        return jdbcUserDetailsManager;
//	    }
//
//
//    @SuppressWarnings({ "deprecation", "removal" })
//	@Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeRequests(authorizeRequests ->
//                authorizeRequests
//                .requestMatchers("/swagger-ui/**","/v3/api-docs").permitAll() 
//                        .requestMatchers(HttpMethod.GET, "/employees/{employeeId}").hasAnyRole("EMP", "ADMIN","MANAGER")
//                        .requestMatchers(HttpMethod.GET, "/employees/personal/{employeeId}").hasAnyRole("MANAGER", "ADMIN")
//                        .requestMatchers(HttpMethod.POST, "/employees").hasAnyRole("EMP","ADMIN")
//                        .requestMatchers(HttpMethod.PUT, "/employees/{employeeId}").hasAnyRole("EMP","ADMIN")
//                        .requestMatchers(HttpMethod.DELETE, "/employees/{employeeId}").hasRole("ADMIN")
//                        .anyRequest().authenticated()
//        );
//
//        // use HTTP Basic authentication
//        http.httpBasic(Customizer.withDefaults());
//
//        // disable Cross Site Request Forgery (CSRF)
//        // in general, not required for stateless REST APIs that use POST, PUT, DELETE and/or PATCH
//        http.csrf(csrf -> csrf.disable());
//
//        return http.build();
//    }
////    @Bean
////    public InMemoryUserDetailsManager userDetailsManager() {
////        UserDetails employee = User.builder()
////                .username("employee")
////                .password("{noop}test123")
////                .roles("EMP")
////                .build();
//  //  UserDetails manager = User.builder()
////          .username("manager")
////          .password("{noop}test123")
////          .roles("MANAGER")
////          .build();
////
////        UserDetails admin = User.builder()
////                .username("admin")
////                .password("{noop}test123")
////                .roles("ADMIN")
////                .build();
////
////        return new InMemoryUserDetailsManager(employee, admin);
////    }
//}
