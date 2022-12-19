package com.koreait.tving.controller;

import com.koreait.tving.service.UserService;
import com.koreait.tving.vos.UserVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping("/login")
    public void user(){

    }

    @GetMapping("/register")
    public void register_get(){}

    @PostMapping("/register")
    public String register_post(UserVO vo){
        log.info(" --------- register_post ---------- ");
        log.info(vo);
        userService.register(vo);
        return "redirect:/main";
    }

}
