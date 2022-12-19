package com.koreait.tving.mapper;

import com.koreait.tving.vos.UserVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    boolean register(UserVO vo);

}
