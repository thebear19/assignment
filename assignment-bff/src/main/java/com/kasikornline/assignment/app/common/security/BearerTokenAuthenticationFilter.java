package com.kasikornline.assignment.app.common.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BearerTokenAuthenticationFilter extends OncePerRequestFilter {
    static final int BEARER_TOKEN_BEGIN_INDEX = 7;
    static final String DUMMY_TOKEN = "token";
    static final String BEARER_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader != null && authorizationHeader.startsWith(BEARER_PREFIX)) {
            UserDetails userDetails = verifyToken(authorizationHeader.substring(BEARER_TOKEN_BEGIN_INDEX));
            UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(request, userDetails);

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        filterChain.doFilter(request, response);
    }

    UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request, UserDetails userDetails) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(),
                userDetails.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return authenticationToken;
    }

    UserDetails verifyToken(String token) {
        log.info("Checking token");
        if (!DUMMY_TOKEN.equals(token)) {
            throw new BadCredentialsException("Invalid token");
        }

        log.info("Built user details");
        return User.builder()
                .username("User_000018b0e1a211ef95a30242ac180002")
                .password("password")
                .authorities(List.of(new SimpleGrantedAuthority("USER")))
                .build();
    }


}
