package ru.tinkoff.notes.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import ru.tinkoff.notes.security.UserDetailsServiceImpl;

@Configuration
public class SecurityConfig {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((customizer) -> customizer.anyRequest().authenticated())
                .userDetailsService(userDetailsService)
                .httpBasic(Customizer.withDefaults());

        return httpSecurity.build();
    }
}
