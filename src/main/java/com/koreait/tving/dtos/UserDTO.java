package com.koreait.tving.dtos;


import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDate;
import java.util.Collection;

@Getter
@Setter
@ToString
public class UserDTO extends User {

    private String id;
    private String password;
    private String name;
    private String nickName;
    private String email;
    private String tel;
    private LocalDate registerDate;
    private boolean isSocial;
    private String role;


    //  로그인 확인할 때 얘로 검사하기 위해서 User를 상속받고
    // User+UserDTO 생성자를 만들어
    public UserDTO(String username,
                   String password,
                   String name,
                   String nickName,
                   String email,
                   String tel,
                   LocalDate registerDate,
                   boolean isSocial,
                   String role,
                   Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id = username;
        this.password = password;
        this.name = name;
        this.nickName = nickName;
        this.email = email;
        this.tel = tel;
        this.registerDate = registerDate;
        this.isSocial = isSocial;
        this.role = role;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
