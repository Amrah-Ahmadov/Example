package com.example.callcenterforloanproject.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    private final JWTManager jwtManager;
    private final MyUserDetailsService myUserDetailsService;

    public JwtRequestFilter(JWTManager jwtManager, MyUserDetailsService myUserDetailsService) {
        this.jwtManager = jwtManager;
        this.myUserDetailsService = myUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader("Authorization");
        String userName = null;
        String jwt = null;
        if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer")){
            jwt = requestTokenHeader.substring(7);
            try {
                userName = jwtManager.getUserNameFromToken(jwt);
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                System.out.println("JWT Token has expired");
            }
                } else {
                    logger.warn("JWT doesn't begin with Bearer");
                }
        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            System.out.println("Test");
            UserDetails userDetails = this.myUserDetailsService.loadUserByUsername(userName);

            if (jwtManager.validateToken(jwt)) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);
     }
}


