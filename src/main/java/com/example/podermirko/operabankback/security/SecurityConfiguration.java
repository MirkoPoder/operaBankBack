package com.example.podermirko.operabankback.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private Object AuthenticationSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/register", "/", "/login", "/createUser").permitAll() //nii saan kirjeldada, et kõigil on ligipääs esilehele
                .anyRequest().permitAll() //kui tahan, et parooli ei küsiks, siis panen .permitAll/.authenticated
                .and()
                .formLogin()
                .loginPage("/")
                .loginProcessingUrl("/login")

                .successForwardUrl("http://localhost:3000/accounts")
                .successHandler((org.springframework.security.web.authentication.AuthenticationSuccessHandler) AuthenticationSuccessHandler)
                .defaultSuccessUrl("http://localhost:3000/accounts")
                .failureUrl("http://localhost:3000/usernotfound")
                .permitAll()
                .and()
                .logout()
                .permitAll();
        http.csrf().disable();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

