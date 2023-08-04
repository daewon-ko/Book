package org.zerock.board.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.board.entity.Board;
import org.zerock.board.entity.Reply;

import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReplyRepositoryTest {
    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void insertReply() {
        IntStream.rangeClosed(1,300).forEach(i -> {
            long bno = (long) (Math.random() * 100) + 1;
            Board board = Board.builder().bno(bno).build();

            Reply reply = Reply.builder()
                    .text("REPLY...." + i)
                    .board(board)
                    .replyer("guest")
                    .build();

            replyRepository.save(reply);
        });
    }

    @Test
    public void testReply1() {
        Optional<Reply> result = replyRepository.findById(1L);
        Reply reply = result.orElseThrow(() -> new IllegalArgumentException("해당하는~~"));

        System.out.println("reply = " + reply);
        System.out.println("reply.getBoard() = " + reply.getBoard());
    }

}
