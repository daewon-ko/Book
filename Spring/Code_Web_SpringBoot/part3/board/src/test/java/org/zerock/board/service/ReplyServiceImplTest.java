package org.zerock.board.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.board.dto.ReplyDTO;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReplyServiceImplTest {
    @Autowired
    private  ReplyServiceImpl replyService;

    @Test
    @DisplayName("댓글 목록을 게시판 번호로 조회하는 테스트")
    public void testGetList() {
        Long bno = 85L;
        List<ReplyDTO> list = replyService.getList(bno);

        list.stream().forEach(i -> System.out.println(i));
    }

}
