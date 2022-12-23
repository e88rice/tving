package com.koreait.tving.service;

import com.koreait.tving.vos.WatchVO;
import com.koreait.tving.mapper.UserMapper;
import com.koreait.tving.vos.UserVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 유저 회원가입
    public boolean register(UserVO vo){
        vo.setPassword(passwordEncoder.encode(vo.getPassword()));
        return userMapper.register(vo);
    }

    // 유저 정보 가져오기
    public UserVO find_user(String username){
        return userMapper.find_user(username);
    }

    // 유저 정보 수정
    public boolean update_user(UserVO vo){
        return userMapper.update_user(vo);
    }
    
    // 유저의 멤버쉽 정보를 수정
    public boolean modify_membership(@Param("id") String userID, @Param("role") String role){
        return userMapper.modify_membership(userID, role);
    }

    public WatchVO get_watch_program_order(@Param("id") String userID, @Param("name") String name){
        return userMapper.get_watch_program_order(userID, name);
    }

    public List<WatchVO> get_all_watch_program_list(String id){
        return userMapper.get_all_watch_program_list(id);
    }


}
