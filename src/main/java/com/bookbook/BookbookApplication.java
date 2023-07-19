package com.bookbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/*
 * @SpringBootApplication annotation이 스프링 프레임워크를 어떻게 구동시킬까?
 * - 자동 환경 설정 annotation이고, 크게 3가지 annotation을 합친 것이다.
 *   - @SpringBootConfiguration,@EnableAutoConfiguration,@ComponentScan
 * - 스프링부트의 (1)자동 환경 설정 + (2)스프링 Bean 읽기와 생성이 자동으로 설정된다.
 *
 * (1)스프링부트의 자동 환경 설정
 * - @SpringBootConfiguration
 *   - 스프링의 @Configuration을 대체하지만 차이점은 @SpringBootConfigur를ation의 경우 구성을 자동으로 찾을 수 있다는 것
 *
 * (2)스프링 Bean 읽기와 생성
 * - 1단계: @ComponentScan
 *    - @Component,@Configuration,@Repository,@Service,@Controller,@RestController annotation이 붙은 객체들을 읽어 Bean 으로 등록한다.
 * - 2단계: @EnableAutoConfiguration
 * 	  - spring.factories 내부에 여러 Configuration들이 존재하고, 조건에 따라 Bean 을 등록한다.
 *      - 조건: @ConditionalOnClass(클래스가 classpath 에 존재할 때), @ConditionalOnMissingBean(특정 Bean 이 사전에 정의되지 않았을 때), @ConditionalOnProperty(특정 property를 명시해주고 일치할 떄)
 * */
@SpringBootApplication
public class BookbookApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookbookApplication.class, args);
	}

}