package com.bookbook.mapper;

import com.bookbook.dto.user.SignUpUserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    Long insertUser(SignUpUserDTO user);
    boolean selectUserNickname(String nickname);
}
