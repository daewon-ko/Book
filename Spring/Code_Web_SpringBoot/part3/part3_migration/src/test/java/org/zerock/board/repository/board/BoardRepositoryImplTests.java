package org.zerock.board.repository.board;

import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("Member 테이블에 존재하는 이메일을 기반으로 Board를 생성하는 테스트 코드")
    void save() {
        IntStream.range(1, 100).forEach(
                i -> {
                    Member member = Member.builder().email("user" + i+"@@@.com").build();
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
