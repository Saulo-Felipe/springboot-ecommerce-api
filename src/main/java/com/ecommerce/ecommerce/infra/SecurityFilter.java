package com.ecommerce.ecommerce.infra;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
        HttpServletRequest request, 
        HttpServletResponse response, 
        FilterChain filterChain
    ) throws ServletException, IOException {
        
        String token = this.getHeaderToken(request);

        if (token == null) throw new RuntimeException("Token inválido");

        filterChain.doFilter(request, response);
    }


    private String getHeaderToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }
    
}