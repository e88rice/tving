package com.koreait.tving.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.security.PermitAll;


@Log4j2
@Controller
public class HomeController {

    @GetMapping("/")
    public String init(){
        log.info(" ------------ home -------------");
        log.info(" ------------ 티빙 로그인 안했을 시 메인 화면 : home ------------");
        return "redirect:/home";
    }

    @GetMapping("/home")
    public void home(){

    }

    @GetMapping("/login1") // 티빙, 카카오, 네이버 로그인창이 모여있는 곳
    public String login(){
        return "/login";
    }

    @PreAuthorize("isAuthenticated()") // 로그인했다면 올 수 있음
    @GetMapping("/main")
    public void main_get(){
        log.info(" ---------- main_get --------- ");
        // 만약 로그인 하였다 -> 로그인 시 메인 화면으로 이동
        // 만약 로그인 하지 않았음 -> 로그인 안했을 시의 홈 화면으로 이동
    }

}
