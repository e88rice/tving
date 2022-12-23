package com.koreait.tving.controller;

import com.koreait.tving.dtos.UserDTO;
import com.koreait.tving.vos.WatchVO;
import com.koreait.tving.service.UserService;
import com.koreait.tving.vos.UserVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;


    // 로그인 창
    @GetMapping("/login")
    public void user_get(){}
    
    // 로그인 시도 처리
    @PostMapping("/login")
    public String user_post( String username ){
        log.info(" ---------- login_post 로그인 시도 ---------- ");
        // 로그인이 성공했다면 무조건 메인화면
        return "redirect:/main";
    }
    
    // 회원가입 창 뷰
    @GetMapping("/register")
    public void register_get(){}
    
    // 실제 회원가입 처리
    @PostMapping("/register")
    public String register_post(UserVO vo){
        log.info(" --------- register_post ---------- ");
        log.info(vo);
        userService.register(vo);
        return "redirect:/user/login";
    }

    // 계정의 정보를 보는 곳
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/info")
    public void info_get(){}

    // 계정의 정보를 변경시키는 곳
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

    // 마이페이지
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/mypage")
    public void mypage_get(
            @AuthenticationPrincipal UserDTO userDTO,
            Model model
    ){
        String message = null;
        switch (userDTO.getRole()){
            case "USER":
                message = "현재 이용중인 이용권이 없습니다.";
            case "BASIC":
                message = "BASIC 이용권";
        }
        model.addAttribute("roleMessage", message);
    }

    // 에이잭스로 마이페이지에서 내가 시청한 내역을 가져오는 곳
    @PreAuthorize("isAuthenticated()")
    @ResponseBody // 얘를 걸어서 뷰를 반환하는게 아닌 데이터를 반환하게끔 해줘야 함 에이잭스쓸거라
    @GetMapping("/mypage/watch")
    public List<WatchVO> all_watch_program_get(
            @AuthenticationPrincipal UserDetails userDetails
            ){
        return userService.get_all_watch_program_list(userDetails.getUsername());
    }

    @GetMapping("/membership")
    public void membership_get(){}

    // 사용자가 이용권을 구매하게 한다
    @PostMapping("/membership")
    public String membership_post(
            @AuthenticationPrincipal UserDTO userDTO,
            @AuthenticationPrincipal UserDetails userDetails
    ){

        // 현재 이용권 정보를 체크

        // 이용권을 사는 로직

        // 권한을 업그레이드 시킴 user -> basic
        userService.modify_membership(userDTO.getId(), "BASIC");
        userDTO.setRole("BASIC");

        return "redirect:/main";
    }


}
