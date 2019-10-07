package com.example.examgenerator.security.config;

import com.example.examgenerator.security.filter.JWTAuthenticationFilter;
import com.example.examgenerator.security.filter.JWTAuthorizationFilter;
import com.example.examgenerator.security.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter { // Classe responsavel por aplicar o filtro nos endpoints

    private final CustomUserDetailsService customUserDetailsService;
    @Autowired
    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception { // esse metodo serve apenas para validar o passwords q estao criptografados
        auth.userDetailsService(customUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
                .and().csrf().disable()// para desabilitar temporariamente o CRSF para nao pedir o token toda vez
                .authorizeRequests()
                .antMatchers("/*/professor/**").hasRole("PROFESSOR") // para proteger as URLs
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager(),customUserDetailsService));
    }
}
