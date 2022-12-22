package com.koreait.tving.controller;

import com.koreait.tving.dtos.UserDTO;
import com.koreait.tving.mapper.MainMapper;
import com.koreait.tving.service.MainService;
import com.koreait.tving.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Log4j2
@Controller
public class MainController {

    @Autowired
    private MainMapper mainMapper;

    @Autowired
    private MainService mainService;

    @Autowired
    private UserService userservice;

    @PreAuthorize("isAuthenticated()") // 로그인했다면 올 수 있음
    @GetMapping("/main")
    public void main_get(Model model){
        log.info(" ---------- main_get --------- ");
        // 만약 로그인 하였다 -> 로그인 시 메인 화면으로 이동
        // 만약 로그인 하지 않았음 -> 로그인 안했을 시의 홈 화면으로 이동
        List<String> titles = mainMapper.get_recommended_programs();
        model.addAttribute("titles", titles);
    }


    //    프로그램 상세페이지로 이동
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/main/program/{name}")
    public String program_get(
            @AuthenticationPrincipal UserDTO userDTO,
            @PathVariable String name,
            Model model){
        // 시청내역
        model.addAttribute("order", userservice.get_watch_program_order(userDTO.getId(), name));
        // 정보
        model.addAttribute("program", mainService.get_program(name));
        // 회차 정보
        model.addAttribute("contents", mainService.get_program_contents(name));
        // 해당 페이지로 이동
        return "/main/program";
    }

    // 프로그램 상세 페이지에서 프로그램 시청 버튼 눌렀을 때
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/main/program/watch/{name}/{order}")
    public String program_watch_get(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable String name,
            @PathVariable String order){
        // 시청 내역에 해당 프로그램 추가하기
        log.info("시청하려는 유저: " + userDetails.getUsername());
        String userID = userDetails.getUsername();
        log.info("시청하려는 프로그램명: " + name);
        log.info("시청 오더: "+ order);
        mainService.insert_watched_program(userID, name, order); // userDTO로 로그인해서 그 정보가 남아있는데 걔 꺼내서 id를 넘겨줌
        // 시청하러 가야지만 현재는 마이페이지로
        return "redirect:/user/mypage";
    }

}
