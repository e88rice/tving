package com.koreait.tving.service;

import com.koreait.tving.mapper.MainMapper;
import com.koreait.tving.mapper.UserMapper;
import com.koreait.tving.vos.ContentsVO;
import com.koreait.tving.vos.ProgramVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainService {

    @Autowired
    private MainMapper mainMapper;

    public List<String> get_recommended_programs(){
        return mainMapper.get_recommended_programs();
    }

    //  프로그램 이름으로 하나의 프로그램 정보를 가져옴
    public ProgramVO get_program(String name){
        return mainMapper.get_program(name);
    }
    // 프로그램 이름으로 하나의 프로그램의 각 회차 정보를 가져옴
    public List<ContentsVO> get_program_contents(String name){
        return mainMapper.get_program_contents(name);
    }



    public boolean insert_watched_program(String id, String name, String order){
        return mainMapper.insert_watched_program(id, name, order);
    }

    public List<ProgramVO> search_program(String word){
        return mainMapper.search_program(word);
    }
}
