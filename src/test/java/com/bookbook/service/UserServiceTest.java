package com.bookbook.service;

import com.bookbook.dto.user.SignUpRequest;
import com.bookbook.exception.user.UserIdExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/*
 * @SpringBootTest 정리
 * - 애플리케이션을 테스트하기 위한 많은 기능을 제공한다.
 *   - Spring Boot Test, JUnit, AssertJ, Hamcrest, Mockito ...
 * - ApplicationContext를 쉽게 생성하고 조작할 수 있다.
 * - classes 속성을 통해 빈을 생성할 클래스들을 지정할 수 있고, 지정하지 않으면 애플리케이션 상에 정의된 모든 빈을 생성한다.
 * - @Import 어노테이션을 사용해서 별도로 TestConfiguration을 명시하고 가져다 쓸 수 있다.
 * - @Transactional 어노테이션과 같이 사용하면 rollback 된다. spring-boot-test는 그저 spring-test를 확장한 것이기 때문
 *   - 다만 RANDOM_PORT나 DEFINED_PORT로 테스트를 설정하면 실제 테스트 서버는 별도의 스레드에서 수행되기 때문에 rollback되지 않는다.
 *
 * 언제 사용하나?
 * - 일반적으로 통합테스트 시 사용한다.
 * - business~data layer 와 같이 계층에 걸쳐 테스트할 때 사용하기 좋다.
 * - 참고로 유닛테스트의 경우에는 @WebMvcTest, @DataJpaTest, @MyBatisTest 등을 사용한다
 *
 * 장점
 * - 애플리케이션의 컨텍스트를 전부 셋업하기에 편리하다
 *
 * 단점
 * - 기본적으로 모든 빈을 탐색하고 등록하기 때문에 특정 계층만 테스트할 목적으로 사용하기에는 무겁고 시간이 오래 걸린다.
 * */
@Sql("classpath:init.sql")
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    SignUpRequest signUpRequest;
    SignUpRequest signUpRequestWithoutIntroduce;

    @BeforeEach
    void setUpUser() {
        signUpRequest = new SignUpRequest(
                "wisdom",
                "pwpwpwpw123",
                "introduce"
        );

        signUpRequestWithoutIntroduce = new SignUpRequest(
                "jihye",
                "pasdlfkjaslkdf123"
        );
    }

    @DisplayName("선택 입력값인 introduce 값이 없어도 유저가 정상적으로 회원가입에 성공합니다")
    @Test
    void signUpWithoutIntroduce() {
        userService.signUp(signUpRequestWithoutIntroduce);
        assertThat(signUpRequest.getUserId()).isNotNull();
    }

    @DisplayName("선택 입력값인 introduce 값이 있는 상황에서 유저가 정상적으로 회원가입에 성공합니다")
    @Test
    void signUpWithIntroduce() {
        userService.signUp(signUpRequest);
        assertThat(signUpRequest.getUserId()).isNotNull();
    }

    @DisplayName("이미 가입된 아이디 입력으로 회원가입에 실패합니다")
    @Test
    void signUpWithDuplicatedId() {
        userService.signUp(signUpRequest);
        assertThatThrownBy(() -> userService.signUp(signUpRequest))
                .isInstanceOf(UserIdExistsException.class)
                .hasMessageContaining("이미 가입된 아이디입니다.")
                .hasMessageContaining(signUpRequest.getUserId())
        ;
    }
}