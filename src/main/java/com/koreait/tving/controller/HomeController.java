package com.koreait.tving.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Log4j2
@Controller
public class HomeController {

    @GetMapping("/")
    public String home(){ return "/home"; }

    @GetMapping("/login1")
    public String login(){ return "/login"; }

    @GetMapping("/login-main")
    public String tvinglogin(){ return "/login-main"; }

    @GetMapping("/main")
    public String main(){ return "/main"; }


}
