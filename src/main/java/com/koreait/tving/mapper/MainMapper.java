package com.koreait.tving.mapper;

import com.koreait.tving.vos.ContentsVO;
import com.koreait.tving.vos.ProgramVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MainMapper {
    // 메인 화면에서 실시간 뜨는 콘텐츠(프로그램)의 이름들을 가져옴
    List<String> get_recommended_programs();

    ProgramVO get_program(String name);

    List<ContentsVO> get_program_contents(String name);

    // 시청 버튼을 눌렀을 시 시청 내역에 추가(프로그램 이름)
    boolean insert_watched_program(@Param("userID") String id, @Param("name") String name, @Param("order") String order);

    // 검색어로 검색해서 해당 프로그램의 정보를 받아옴
    List<ProgramVO> search_program(String word);
    
}
