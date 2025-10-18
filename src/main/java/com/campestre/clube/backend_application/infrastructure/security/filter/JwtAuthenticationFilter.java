package com.campestre.clube.backend_application.infrastructure.security.filter;

import com.campestre.clube.backend_application.infrastructure.security.AccountAuthenticationService;
import com.campestre.clube.backend_application.infrastructure.security.JwtTokenManager;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

import static com.campestre.clube.backend_application.core.exceptions.ExceptionExtensions.*;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private final AccountAuthenticationService autenticacaoService;
    private final JwtTokenManager jwtTokenManager;

    public JwtAuthenticationFilter(
            AccountAuthenticationService autenticacaoService, JwtTokenManager jwtTokenManager
    ) {
        this.autenticacaoService = autenticacaoService;
        this.jwtTokenManager = jwtTokenManager;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain
    ) throws ServletException, IOException {
        String tokenHeader = request.getHeader("Authorization");
        if (tokenHeader == null || !tokenHeader.startsWith("Bearer ")) throw INVALID_TOKEN;

        String jwtToken = tokenHeader.substring(7);
        String username;

        try {
            username = jwtTokenManager.getUsernameFromToken(jwtToken);
        } catch (ExpiredJwtException e) {
            throw UNAUTHORIZED_EXPIRED_TOKEN;
        } catch (io.jsonwebtoken.security.SignatureException e) {
            throw UNAUTHORIZED_INVALID_TOKEN;
        } catch (Exception e) {
            LOGGER.error("[ERRO INTERNO] Falha ao processar token: {}", e.getMessage(), e);
            throw INTERNAL_ERROR_AUTHENTICATE_PROCESS;
        }

        try {
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                addUsernameInContext(request, username, jwtToken);
            }
        } catch (Exception e) {
            throw INTERNAL_ERROR_AUTHENTICATE_PROCESS;
        }

        filterChain.doFilter(request, response);
    }

    private void addUsernameInContext(HttpServletRequest request, String username, String jwtToken) {
        UserDetails userDetails = autenticacaoService.loadUserByUsername(username);

        if (jwtTokenManager.validateToken(jwtToken, userDetails.getUsername())) {
            String role = jwtTokenManager.getRoleFromToken(jwtToken);
            List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + role));

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userDetails, null, authorities);

            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            throw UNAUTHORIZED_INVALID_TOKEN;
        }
    }
}
