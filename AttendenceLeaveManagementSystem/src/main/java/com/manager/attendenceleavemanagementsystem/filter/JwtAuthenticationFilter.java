package com.manager.attendenceleavemanagementsystem.filter;

import com.manager.attendenceleavemanagementsystem.entity.Employee;
import com.manager.attendenceleavemanagementsystem.repository.EmployeeRepository;
import com.manager.attendenceleavemanagementsystem.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final EmployeeRepository employeeRepository;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, EmployeeRepository employeeRepository) {
        this.jwtUtil = jwtUtil;
        this.employeeRepository = employeeRepository;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authenticationHeader = request.getHeader("Authorization");




        if (authenticationHeader != null && authenticationHeader.startsWith("Bearer ")){
            String token = authenticationHeader.substring(7);
            String email = jwtUtil.extractEmail(token);

            Employee employee = employeeRepository
                    .findByEmpEmail(email)
                    .orElse(null);


            if (employee != null && SecurityContextHolder.getContext().getAuthentication() == null){

                String role = employee.getEmpAuthenticationRole().toUpperCase();
                System.out.println("role form db : " + role);
                List<GrantedAuthority> authorities = Collections.singletonList(
                        new SimpleGrantedAuthority("ROLE_"+ role)
                );
                System.out.println("authorities : " + authorities);

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                  employee,
                  null,
                  authorities
                );

                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        filterChain.doFilter(request, response);

    }
}
