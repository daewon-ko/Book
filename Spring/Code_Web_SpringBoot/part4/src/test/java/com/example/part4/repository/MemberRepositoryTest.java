package com.example.part4.repository;

import com.example.part4.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository repository;

    @Test
    @DisplayName("회원들을 임의로 Insert하는 테스트코드")
    public void insertMembers() {
        IntStream.range(1,100).forEach(
                i -> {
                    Member member = Member.builder()
                            .email("r" + i + "@naver.com")
                            .pw("11111")
                            .nickname("reviewr" + i)
                            .build();
                    repository.save(member);
                }
        );
    }

}
