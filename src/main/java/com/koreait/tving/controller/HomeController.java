package com.koreait.tving.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.security.PermitAll;


@Log4j2
@Controller
public class HomeController {

    @GetMapping("/")
    public String init(){
        log.info(" ------------ home -------------");
        log.info(" ------------ 티빙 메인 화면 ------------");
        return "redirect:/home";
    }

    @GetMapping("/home")
    public void home(){

    }

    @GetMapping("/login1")
    public String login(){
        return "/login";
    }

    @GetMapping("/main")
    public void main_get(){}

}
