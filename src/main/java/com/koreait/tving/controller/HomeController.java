package com.koreait.tving.controller;

import com.koreait.tving.dtos.UserDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.security.PermitAll;


@Log4j2
@Controller
public class HomeController {

    @GetMapping("/")
    public String init(@AuthenticationPrincipal UserDetails userDetails){
        log.info(" ------------ home -------------");
        log.info(" ------------ 티빙 로그인 안했을 시 메인 화면 : home ------------");
        if(userDetails == null) {
            log.info(" 로그인 실패 홈으로 돌아갑니다 ");
            return "redirect:/home";
        }
        return "redirect:/main";
    }

    @GetMapping("/home")
    public void home(){

    }

    @GetMapping("/login1") // 티빙, 카카오, 네이버 로그인창이 모여있는 곳
    public String login(){
        return "/login";
    }


}
