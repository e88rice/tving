package com.koreait.tving.service;

import com.koreait.tving.dtos.UserDTO;
import com.koreait.tving.mapper.UserMapper;
import com.koreait.tving.vos.UserVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

@Log4j2
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;


    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info(" ------------- loadUserByUsername ----------------");
        log.info(username + "님이 로그인 시도하였습니다!");

        UserVO userVO = userMapper.find_user(username);

        if(userVO == null){
            throw new UsernameNotFoundException(username + ": 해당 유저는 존재하지 않습니다....");
        }

        return new UserDTO(
                    username,
                    userVO.getPassword(),
                    userVO.getName(),
                    userVO.getNickName(),
                    userVO.getEmail(),
                    userVO.getTel(),
                    userVO.getRegisterDate(),
                    userVO.isSocial(),
                    userVO.getRole(),
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_" + userVO.getRole()))
        );
    }
}
