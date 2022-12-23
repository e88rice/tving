package com.koreait.tving;

import com.koreait.tving.mapper.MainMapper;
import com.koreait.tving.mapper.UserMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Log4j2
@SpringBootTest
class TvingApplicationTests {

    @Autowired
    UserMapper userMapper;
    @Autowired
    MainMapper mainMapper;

    @Test
    void contextLoads() {
        log.info(mainMapper.search_program("동일"));
    }

    @Test
    void test2(){
        log.info(mainMapper.get_program("짱구는못말려"));
    }

    @Test
    void test(){
        log.info(mainMapper.get_program_contents("재벌집막내아들"));
    }

}
