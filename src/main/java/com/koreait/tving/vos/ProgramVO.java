package com.koreait.tving.vos;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ProgramVO {

    private String name;
    private int age;
    private String time;
    private String genre;
    private String distributor;
    private String introduce;
    private String classification;
    private List<String> creators;
    private List<String> casts;
}
