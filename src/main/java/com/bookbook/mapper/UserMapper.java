package com.bookbook.mapper;

import com.bookbook.dto.SignUpRequest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    Long insertUser(SignUpRequest user);
    boolean selectUserId(String userId);
}
