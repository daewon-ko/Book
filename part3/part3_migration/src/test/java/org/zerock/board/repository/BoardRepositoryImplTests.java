package org.zerock.board.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.board.entity.Board;
import org.zerock.board.entity.Member;
import org.zerock.board.repository.board.BoardJDBCRepository;

import java.util.stream.IntStream;

@SpringBootTest
class BoardRepositoryImplTests {
    @Autowired
    private BoardJDBCRepository boardJdbcRepository;

    @Test
    @Transactional
    void save() {
        IntStream.range(101, 200).forEach(
                i -> {
                    Member member = Member.builder().email("user" + i + "@@@.com").build();
                    Board build = Board.builder()
                            .title("Title" + i)
                            .writerEmail(member.getEmail())
                            .content("하하하" + i)
                            .build();
                    boardJdbcRepository.save(build);
                    System.out.println(build);
                }
        );

    }
}
