package com.koreait.tving;

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

    @Test
    void contextLoads() {
        log.info(userMapper.update_user("testman1", "test@naver.com", "010-1111-2222"));
    }

}
