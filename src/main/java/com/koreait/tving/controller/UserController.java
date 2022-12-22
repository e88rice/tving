package com.koreait.tving.controller;

import com.koreait.tving.dtos.UserDTO;
import com.koreait.tving.vos.WatchVO;
import com.koreait.tving.service.UserService;
import com.koreait.tving.vos.UserVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return "redirect:/login";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/info")
    public void info_get(){}

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/info")
    public String info_post(
            @AuthenticationPrincipal UserDTO userDTO,
            UserVO vo
            ){
        if(userService.update_user(vo)){
            userDTO.setTel(vo.getTel());
            userDTO.setEmail(vo.getEmail());
        }
        return "redirect:/main";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/mypage")
    public void mypage_get(){

    }

    @PreAuthorize("isAuthenticated()")
    @ResponseBody // 얘를 걸어서 뷰를 반환하는게 아닌 데이터를 반환하게끔 해줘야 함 에이잭스쓸거라
    @GetMapping("/mypage/watch")
    public List<WatchVO> all_watch_program_get(
            @AuthenticationPrincipal UserDetails userDetails
            ){
        log.info("ㄴㅇㄹ"+userDetails.getUsername());
        return userService.get_all_watch_program_list(userDetails.getUsername());
    }

}
