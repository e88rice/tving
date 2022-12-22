package com.koreait.tving.vos;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class WatchVO {

    private String userID;
    private String programName;
    private String title;
    private int watchOrder;
    private LocalDateTime watchDate;

}
