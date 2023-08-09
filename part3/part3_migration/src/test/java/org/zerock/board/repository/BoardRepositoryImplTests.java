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
        IntStream.range(1, 100).forEach(
                i -> {
                    Member member = Member.builder().email("user" + i + "@@@.com").build();
                    Board board = Board.builder()
                            .writer(member)
                            .title("Title" + i)
                            .content("하하하" + i)
                            .deleted(false)
                            .build();
                    boardJdbcRepository.save(board);
                    System.out.println(board);
                }
        );

    }
}
