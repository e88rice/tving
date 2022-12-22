package com.koreait.tving.vos;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ContentsVO {
    private String name;
    private int order;
    private String title;
    private String introduce;
    private LocalDate airingDate;
    private int runningTime;

}
