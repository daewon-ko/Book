package com.example.chapter3.member.repository;

import com.example.chapter3.member.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @DisplayName("")
    @Test
    void test() {
        //given
        for (int i = 1; i <= 100; i++) {
            Member entity = Member.builder()
                    .password(passwordEncoder.encode("1111"))
                    .name("USER" + i)
                    .email("user" + i + "@gmail.com")
                    .role(i <= 80 ? "USER" : "ADMIN")
                    .build();
            memberRepository.save(entity);

        }

        //when

        //then

    }


}