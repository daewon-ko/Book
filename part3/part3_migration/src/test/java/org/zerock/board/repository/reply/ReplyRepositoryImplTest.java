package org.zerock.board.repository.reply;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.board.entity.Board;
import org.zerock.board.entity.Member;
import org.zerock.board.entity.Reply;

import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static java.lang.Math.random;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReplyRepositoryImplTest {
    @Autowired
    private ReplyJdbcRepository replyJdbcRepository;

    @Test
    @DisplayName("Reply에 임의의 Dummies 데이터를 넣는 테스트")
    public void insertDummyReplies() {

        IntStream.rangeClosed(1, 300).forEach(
                i -> {
                    long bno = (long) (Math.random() * 99) + 1;
                    Member member = Member.builder().email("user" + bno + "@@@.com").build();
                    Board board = Board.builder().bno(bno).deleted(false).build();
                    Reply reply = Reply.builder().
                            rno(i).
                            title("title" + i).
                            content("댓글" + i).
                            board(board).
                            member(member).
                            build();
                    replyJdbcRepository.saveReply(reply);

                }
        );


    }
}
