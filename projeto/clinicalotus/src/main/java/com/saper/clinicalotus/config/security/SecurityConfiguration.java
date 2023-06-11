package com.saper.clinicalotus.config.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "/funcionario").permitAll()
                .requestMatchers(HttpMethod.GET, "/paciente/**").hasAnyRole("MEDICO", "RECEPCIONISTA", "ADMIN")
                .requestMatchers("/paciente/**").hasAnyRole("RECEPCIONISTA", "ADMIN")
                .requestMatchers(HttpMethod.GET, "/funcionario/medico").hasAnyRole("RECEPCIONISTA", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/**").hasAnyRole("ADMIN", "RECEPCIONISTA", "ADMIN")
                .anyRequest().hasRole("ADMIN");
        http.csrf().disable();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}