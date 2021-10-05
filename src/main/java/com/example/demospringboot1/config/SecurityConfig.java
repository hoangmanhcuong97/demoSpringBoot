package com.example.demospringboot1.config;

import com.example.demospringboot1.aspect.CustomerSuccessHandler;
import com.example.demospringboot1.service.userservice.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private IUserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService((UserDetailsService) userService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/homePage**","/admin**").access("hasRole('ADMIN')")
                .antMatchers("/homeProvince**","/user**").access("hasRole('USER')")
                .and()
                .formLogin().successHandler(new CustomerSuccessHandler())
                .and()
                .csrf().disable()
                .exceptionHandling().accessDeniedPage("/Error403")
                .and().logout();
    }
}
