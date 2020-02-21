package com.code.aeon;

import com.code.aeon.security.jwt.JwtAuthEntryPoint;
import com.code.aeon.service.UserDetailsServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AeonApplication {

    public static void main(String[] args) {
        SpringApplication.run(AeonApplication.class, args);
    }

    @Bean
    public UserDetailsServiceImpl userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public JwtAuthEntryPoint unauthorizedHandler() {

        return new JwtAuthEntryPoint();
    }


}
