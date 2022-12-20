package com.koreait.tving.controller;

import com.koreait.tving.service.UserService;
import com.koreait.tving.vos.UserVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Log4j2
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping("/login")
    public void user_get(){}

    @PostMapping("/login")
    public String user_post( String username ){
        log.info(" ---------- login_post 로그인 시도 ---------- ");
        // 로그인이 성공했다면 무조건 메인화면
        return "redirect:/main";
    }


    @GetMapping("/register")
    public void register_get(){}

    @PostMapping("/register")
    public String register_post(UserVO vo){
        log.info(" --------- register_post ---------- ");
        log.info(vo);
        userService.register(vo);
        return "redirect:/home";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/info")
    public void info_get(){}

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/info")
    public String info_post(String id, String email, String tel){
        log.info("아아아아:" + id, email, tel);
        userService.update_user(id, email, tel);
        return "redirect:/main";
    }

}
