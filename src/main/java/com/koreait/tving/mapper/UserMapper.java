package com.koreait.tving.mapper;

import com.koreait.tving.vos.WatchVO;
import com.koreait.tving.vos.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    boolean register(UserVO vo);

    // 로그인 시도 시 해당 ID에 해당하는 유저를 찾음
    UserVO find_user(
            @Param("id") String userID); // 파람은 그냥 뒤에 변수를 전달 할 때 사용할 이름 같은거

    // 유저의 정보를 새롭게 전달하여 유저의 정보를 업데이트
    boolean update_user(UserVO vo);

    // 유저의 권한을 수정하는 부분
    boolean modify_membership(@Param("id")String userID, @Param("role")String role);

    // 해당 유저가 시청한 하나의 프로그램의 회차정보를 가져옴
    WatchVO get_watch_program_order(@Param("id")String userID, @Param("name")String programName);

    // 유저 아이디를 전달하여 유저의 시청내역을 가져옴
    List<WatchVO> get_all_watch_program_list(@Param("id") String userID);

}
