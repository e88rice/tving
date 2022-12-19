package com.koreait.tving.service;

import com.koreait.tving.mapper.UserMapper;
import com.koreait.tving.vos.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public boolean register(UserVO vo){
        return userMapper.register(vo);
    }

}
