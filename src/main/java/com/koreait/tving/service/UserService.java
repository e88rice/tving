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

    public boolean register(UserVO vo){
        vo.setPassword(passwordEncoder.encode(vo.getPassword()));
        return userMapper.register(vo);
    }

    public UserVO find_user(String username){
        return userMapper.find_user(username);
    }

    public boolean update_user(UserVO vo){
        return userMapper.update_user(vo);
    }

    public WatchVO get_watch_program_order(@Param("id") String userID, @Param("name") String name){
        return userMapper.get_watch_program_order(userID, name);
    }

    public List<WatchVO> get_all_watch_program_list(String id){
        return userMapper.get_all_watch_program_list(id);
    }


}
