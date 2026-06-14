package com.stellaris.Chat_Rooms.security.filters;

import com.stellaris.Chat_Rooms.domain.enums.Role;
import com.stellaris.Chat_Rooms.persistence.entities.UserEntity;
import com.stellaris.Chat_Rooms.security.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {
    private final TokenService tokenService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

        System.out.println("URI: " + request.getRequestURI());
        System.out.println("AUTH: " + authorization);

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authorization.substring(7);

        if (tokenService.isValidToken(token) && SecurityContextHolder.getContext().getAuthentication() == null) {
            String username = tokenService.extractUsername(token);
            UserEntity userFound = (UserEntity) userDetailsService.loadUserByUsername(username);

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userFound, null, userFound.getAuthorities());
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authToken);

            filterChain.doFilter(request, response);
        }
    }
}
