package com.example.examgenerator.security.filter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Constants {  // contem informações para utilizar entre as class de configuracao de filtros
    public static final String SECRET = "secre";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final long  EXPIRATION_TIME =  86400000L; // 1 day

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("debora"));//gerando um password, usei essa classe por ter um 'psvm'
    }
}
