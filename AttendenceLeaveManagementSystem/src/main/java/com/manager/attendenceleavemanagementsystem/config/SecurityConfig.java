package com.manager.attendenceleavemanagementsystem.config;

import com.manager.attendenceleavemanagementsystem.filter.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf(csrf -> csrf.disable());

        httpSecurity
                .sessionManagement(
                        session ->
                                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        httpSecurity
                .authorizeHttpRequests(
                        auth ->
                                auth
                                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                                        .requestMatchers("/api/authentication/**").permitAll()
                                        .requestMatchers("/api/attendance/**").permitAll()

                                        // EMPLOYEE FORBIDDEN APIs
                                        .requestMatchers(HttpMethod.POST, "/api/employee").hasRole("ADMIN")
                                        .requestMatchers(HttpMethod.PUT, "/api/employee/active/**").hasRole("ADMIN")
                                        .requestMatchers(HttpMethod.PUT, "/api/leave/status/**").hasRole("ADMIN")
                                        .requestMatchers(HttpMethod.DELETE, "/api/leave/**").hasRole("ADMIN")

                                        // ADMIN CAN ACCESS EVERYTHING
//                                        .requestMatchers("/**").hasRole("ADMIN")
//                                        .requestMatchers("/**").hasRole("EMPLOYEE")

                                        // EMPLOYEE CAN ACCESS EVERYTHING ELSE
                                        .anyRequest().hasAnyRole("EMPLOYEE","ADMIN")



                );

        httpSecurity
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);


        return httpSecurity.build();

    }
}
