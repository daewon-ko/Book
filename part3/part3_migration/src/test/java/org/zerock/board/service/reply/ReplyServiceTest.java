package org.zerock.board.service.reply;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.board.dto.ReplyDTO;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReplyServiceTest {

    @Autowired
    private ReplyService service;

    @Test
    @Transactional
    void testGetList() {
        Long bno = 99L;

        List<ReplyDTO> replyDTOList = service.getList(bno);
        replyDTOList.forEach(replyDTO -> System.out.println(replyDTO));

    }

}
