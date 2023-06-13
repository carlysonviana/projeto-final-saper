package com.saper.clinicalotus.config.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic(withDefaults());
        http.authorizeHttpRequests( (authz)->authz
                .requestMatchers(HttpMethod.POST, "/funcionario").permitAll()
                .requestMatchers(HttpMethod.GET, "/paciente/**").hasAnyRole("MEDICO", "RECEPCIONISTA", "ADMIN")
                .requestMatchers("/paciente/**").hasAnyRole("RECEPCIONISTA", "ADMIN")
                .requestMatchers("/my/**").hasAnyRole("RECEPCIONISTA", "ADMIN","MEDICO")
                .requestMatchers(HttpMethod.GET, "/funcionario/medico").hasAnyRole("RECEPCIONISTA", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/**").hasAnyRole( "RECEPCIONISTA", "ADMIN")
                .requestMatchers( "/**").hasAnyRole( "ADMIN")
                .anyRequest().hasRole("ADMIN"));
        http.csrf((csrf) -> csrf.disable());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}