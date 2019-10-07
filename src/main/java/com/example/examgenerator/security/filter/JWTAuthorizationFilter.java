package com.example.examgenerator.security.filter;

import com.example.examgenerator.persistence.model.ApplicationUser;
import com.example.examgenerator.security.service.CustomUserDetailsService;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.examgenerator.security.filter.Constants.*;


public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
    private final CustomUserDetailsService customUserDetailsService;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager,
                                  CustomUserDetailsService customUserDetailsService) {
        super(authenticationManager);
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(HEADER_STRING);
        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(request);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token == null) return null;// testando de novo se o token nao esta null
        String username = Jwts.parser().setSigningKey(SECRET)
                .parseClaimsJws(token.replace(TOKEN_PREFIX, "")) // removendo o 'Bearer'e mantendo apenas o token
                .getBody()
                .getSubject(); // pegando o username da class JWTAuthenticationFilter que foi add como Subject
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
        ApplicationUser applicationUser = customUserDetailsService.loadApplicationUserByUsername(username);
        return username != null ? new UsernamePasswordAuthenticationToken(applicationUser, null, userDetails.getAuthorities()) : null;
    }
}
