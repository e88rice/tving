package com.koreait.tving.controller;

import com.koreait.tving.dtos.UserDTO;
import com.koreait.tving.mapper.MainMapper;
import com.koreait.tving.service.MainService;
import com.koreait.tving.service.UserService;
import com.koreait.tving.vos.ProgramVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yaml.snakeyaml.util.UriEncoder;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
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
        model.addAttribute("watchVO", userservice.get_watch_program_order(userDTO.getId(), name));
        log.info("퍄퍄 " + userservice.get_watch_program_order(userDTO.getId(), name));
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
            @AuthenticationPrincipal UserDTO userDTO,
            @PathVariable String name,
            @PathVariable String order){
        // 시청 내역에 해당 프로그램 추가하기
        String userID = userDTO.getId();
        log.info("시청하려는 유저: " + userID);
        log.info("시청하려는 프로그램명: " + name);
        log.info("시청 오더: "+ order);
        if (userDTO.getRole().equals("USER")){
            return "redirect:/user/membership";
        }
        mainService.insert_watched_program(userID, name, order); // userDTO로 로그인해서 그 정보가 남아있는데 걔 꺼내서 id를 넘겨줌
        // 시청하러 갑니다잉
        return "redirect:/watch/" + UriEncoder.encode(name) + "/" + order;
    }

    // 비디오 정보 넘겨주는곳
    @PreAuthorize("hasRole('BASIC')")
    @ResponseBody
    @GetMapping(value = "/watch/{name}/{order}", produces = "video/mp4") // produces = 헤더 값(미디어 타입)을 지정
    public Resource watchVideo(@PathVariable String name, @PathVariable String order) throws IOException {
        String filePath = "src/main/resources/static/videos/program/" + name + "/" + order + ".mp4";
        return new ByteArrayResource( // 이 친구가 영상을 byte로 정리해줘서 구간별로 이동가능
                FileCopyUtils.copyToByteArray(
                        Files.newInputStream(
                                Paths.get(filePath))));
    }


    @GetMapping("/main/search")
    public void search_program_View(){

    }

    // 검색 기능 (word: 검색어)
    @GetMapping("/search/{word}")
    public String search_program_get(
            @PathVariable String word, Model model){
        model.addAttribute("searchWord", word);
        List<ProgramVO> programVOS = mainService.search_program(word);
        List<String> movieList = new ArrayList<>();
        List<String> programList = new ArrayList<>();
        for (ProgramVO vo : programVOS){
            if (vo.getClassification().equals("영화")){
                movieList.add(vo.getName());
            }
            else{
                programList.add(vo.getName());
            }
        }

        model.addAttribute("movieList", movieList);
        model.addAttribute("programList", programList);

        return "redirect:/main/search";
    }


}
