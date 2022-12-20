package com.koreait.tving.vos;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserVO {

    private String id;
    private String password;
    private String name;
    private String nickName;
    private String email;
    private String tel;
    private LocalDate registerDate;
    private boolean isSocial;
    private String role;

}
