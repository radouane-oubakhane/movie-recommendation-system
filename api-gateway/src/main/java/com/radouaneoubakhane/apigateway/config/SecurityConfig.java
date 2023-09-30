package com.radouaneoubakhane.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

        @Bean
        public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
            http.csrf(ServerHttpSecurity.CsrfSpec::disable)
                    .authorizeExchange(authorizeExchangeSpec -> authorizeExchangeSpec
                            .pathMatchers("/eureka/**").permitAll()

                            .pathMatchers("/movie-service/**").hasRole("ADMIN")
                            .pathMatchers("/user-service/**").hasRole("ADMIN")
                            .pathMatchers("/catalog-service/**").hasRole("ADMIN")
                    )
                    .oauth2ResourceServer((oauth2) -> oauth2
                            .jwt(jwtConfigurer -> jwtConfigurer
                                    .jwtAuthenticationConverter(new KeycloakRoleConverter())
                            )
                    );
            return http.build();
        }

}











