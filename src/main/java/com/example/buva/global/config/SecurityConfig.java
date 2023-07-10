package com.example.buva.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig implements WebSecurityConfigurer {

    @Override
    public void init(SecurityBuilder builder) throws Exception {

    }

    @Override
    public void configure(SecurityBuilder builder) throws Exception {

    }
}