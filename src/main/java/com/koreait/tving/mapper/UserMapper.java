package com.koreait.tving.mapper;

import com.koreait.tving.vos.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    boolean register(UserVO vo);

    // 로그인 시도 시 해당 ID에 해당하는 유저를 찾음
    UserVO find_user(
            @Param("id") String username); // 파람은 그냥 뒤에 변수를 전달 할 때 사용할 이름 같은거

    boolean update_user(String id, String email, String tel);

}
