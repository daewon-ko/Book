package org.zerock.board.repository.member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.board.entity.Member;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryImplTest {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("Member Dummie 테이블 만들기 위한 코드")
    public void insertMember() {
        IntStream.range(1,100).forEach(
                i -> {
                    Member member = new Member("user" + i+"@@@.com", "password" + i, "userName" + i);
                    memberRepository.save(member);
                }
        );
    }

}
