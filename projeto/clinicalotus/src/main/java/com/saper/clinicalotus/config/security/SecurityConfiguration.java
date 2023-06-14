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
                .requestMatchers( "/**").hasAnyRole( "ADMIN")
                .requestMatchers("/paciente/**").hasAnyRole("RECEPCIONISTA")
                .requestMatchers("/consulta/**").hasAnyRole("RECEPCIONISTA")
                .requestMatchers(HttpMethod.GET, "/paciente/**").hasAnyRole("MEDICO")
                .requestMatchers(HttpMethod.POST, "/funcionario/**", "/endereco/**").hasAnyRole("MEDICO", "RECEPCIONISTA")
                .requestMatchers(HttpMethod.PUT, "/funcionario/**", "/endereco/**").hasAnyRole("MEDICO", "RECEPCIONISTA")
                .requestMatchers(HttpMethod.POST, "/funcionario/medico/**", "/horarioAtendimento/**").hasAnyRole("MEDICO")
                .requestMatchers(HttpMethod.GET, "/funcionario/medico/**",
                                                         "/medico/**",
                                                         "/funcionario/**",
                                                         "/categoria/**",
                                                         "/especialidade/**",
                                                         "/horarioAtendimento/**",
                                                         "/consulta/**",
                                                         "/cidade/**",
                                                         "/estado/**",
                                                         "/endereco/**",
                                                         "/my/**").hasAnyRole("MEDICO", "RECEPCIONISTA")
                .requestMatchers(HttpMethod.PUT, "/funcionario/medico/**", "/medico/**", "/horarioAtendimento/**").hasAnyRole("MEDICO")
                .requestMatchers(HttpMethod.POST, "/endereco/**").hasAnyRole("MEDICO", "RECEPCIONISTA")
                .requestMatchers(HttpMethod.GET, "/planoDeSaude/**").hasAnyRole("RECEPCIONISTA")
                .anyRequest().hasRole("ADMIN"));
        http.csrf((csrf) -> csrf.disable());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}