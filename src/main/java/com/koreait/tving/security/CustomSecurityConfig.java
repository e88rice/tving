package com.koreait.tving.security;


import com.koreait.tving.handler.Custom403Handler;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Log4j2
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CustomSecurityConfig {

    private final DataSource dataSource;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        log.info(" ----------------- filterChain -------------------");
//        httpSecurity.formLogin()
//                .successForwardUrl("/home");
        httpSecurity.formLogin()
                .loginPage("/user/login").successForwardUrl("/user/login");


//        403 에러 처리 부분
//        httpSecurity.exceptionHandling().accessDeniedHandler(accessDeniedHandler());
//        httpSecurity.exceptionHandling().accessDeniedPage("/home");
        httpSecurity.csrf().disable();
        httpSecurity.oauth2Login()
                .loginPage("/user/login");
        httpSecurity.logout().logoutSuccessUrl("/");


        return httpSecurity.build();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new Custom403Handler();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl repository = new JdbcTokenRepositoryImpl();
        repository.setDataSource(dataSource);
        return repository;
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        log.info(" ---------------- web security customizer configure ---------------- ");
        return web -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }




}
