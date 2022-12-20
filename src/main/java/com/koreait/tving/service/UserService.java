package com.koreait.tving.service;

import com.koreait.tving.mapper.UserMapper;
import com.koreait.tving.vos.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    public boolean update_user(String id, String email, String tel){
        return userMapper.update_user(id, email, tel);
    }


}
