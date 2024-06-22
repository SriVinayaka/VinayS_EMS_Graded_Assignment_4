package com.gl.employeeservice.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.gl.employeeservice.service.UserDetailsImplementation;

/**
 * Security configuration for the application
 */
@Configuration
public class SecurityConfig //extends WebSecurityConfiguration {
{

    @Bean
    public UserDetailsService myUserDetailsService() {
        return new UserDetailsImplementation();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService()).passwordEncoder(passwordEncoder());
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .requestMatchers("/h2-console/**").permitAll() // Allow access to H2 console for debugging (disable in production)
                .requestMatchers("/api/user", "/api/role").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/employees").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/employees").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/employees/*").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .httpBasic() // Use HTTP Basic authentication
                .and()
                .cors().and().csrf().disable(); // Disable CSRF for simplicity (consider enabling for production)
    }
}
