package org.zerock.board.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.board.entity.Board;
import org.zerock.board.entity.Member;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void insertBoard() {

        IntStream.rangeClosed(1, 100).forEach(
                i -> {
                    Member member = Member.builder()
                            .email("user" + i + "@aaa.com")
                            .build();

                    Board board = Board.builder()
                            .title("title" + i)
                            .content("content" + i)
                            .writer(member)
                            .build();

                    boardRepository.save(board);
                }
        );
    }

    @Test
    @Transactional
    public void testRead1() {
        Optional<Board> result = boardRepository.findById(100L);
        Board board = result.get();

        System.out.println("board = " + board);
        System.out.println("board.getWriter() = " + board.getWriter());

    }

//    @Test
//    public void testReadWithWriter() {
//        Object result = boardRepository.getBoardWithWriter(100L);
//        Object result2 = boardRepository.getBoardWithWriter(90L);
//
//        Object[] arr = (Object[]) result;
//
//        Object[] arr2 = (Object[]) result2;
//        System.out.println(Arrays.toString(objects));
//
//        System.out.println("------------------");
//        System.out.println(Arrays.toString(arr));
//        System.out.println("------------------");
//        System.out.println(arr2.toString());
//    }

    @Test
    public void testGetBoardWithReply() {
        List<Object[]> result = boardRepository.getBoardWithReply(100L);
        for (Object[] objects : result) {
            System.out.println(Arrays.toString(objects));
        }
    }

    @Test
    public void testWithReplyCount() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
        Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageable);

        result.get().forEach(row -> {
            Object[] arr = row;
            System.out.println(Arrays.toString(arr));
        });
    }

    @Test
    public void testRead3() {
        Object result = boardRepository.getBoardByBno(100L);

        Object[] arr = (Object[]) result;

        System.out.println(Arrays.toString(arr));
    }

}
