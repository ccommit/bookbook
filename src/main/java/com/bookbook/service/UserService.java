package com.bookbook.service;

import com.bookbook.dto.user.SignUpUserDTO;
import com.bookbook.exception.NicknameExistsException;
import com.bookbook.mapper.UserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public void signUp(SignUpUserDTO signUpUserDTO) {
        String nickname = signUpUserDTO.getNickname();

        if (checkUniqueNickname(nickname)) {
            throw new NicknameExistsException("There is an account with that nickname:" + nickname);
        }

        signUpUserDTO = SignUpUserDTO.createUserDTO(
                nickname,
                passwordEncoder.encode(signUpUserDTO.getPassword()),
                signUpUserDTO.getIntroduce()
        );

        userMapper.insertUser(signUpUserDTO);
    }

    private boolean checkUniqueNickname(String nickname) {
        return userMapper.selectUserNickname(nickname);
    }
}
