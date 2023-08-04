package com.bookbook.mapper;

import com.bookbook.dto.user.UserInfo;
import com.bookbook.dto.user.SignUpRequest;
import com.bookbook.dto.user.UserProfileResponse;
import com.bookbook.dto.user.LoginUser;
import org.apache.ibatis.annotations.Mapper;

/*
* @Mapper 정리
* - MyBatis의 mappers를 위한 marker interface로 사용한다. -> 특정 매퍼 등록을 위한 어노테이션
* - 여러 개의 매퍼를 사용하기 위해선 @MapperScan 어노테이션을 사용하고, @Mapper 는 생략할 수도 있다.
*   - 매퍼 스캔은 basePackages 속성에 지정된 패키지 아래의 모든 인터페이스가 매퍼로 추가된다.
* */
@Mapper
public interface UserMapper {
    void insertUser(SignUpRequest user);

    boolean existsByUserId(String userId);

    UserInfo findByActiveUserId(String userId);

    UserInfo findById(Long Id);

    void deleteUser(Long id);

    UserProfileResponse findUserProfile(LoginUser loginUser);

    void updatePassword(LoginUser loginUser);

    void updateIntroduce(LoginUser loginUser);
}
