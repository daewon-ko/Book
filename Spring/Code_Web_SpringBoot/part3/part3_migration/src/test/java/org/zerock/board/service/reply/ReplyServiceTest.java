package org.zerock.board.service.reply;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.board.dto.ReplyDTO;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
class ReplyServiceTest {

    @Autowired
    private ReplyService service;

    @Test
    @DisplayName("댓글번호로 댓글을 조회하는 테스트코드")
    @Transactional
    void findById() {
        Long rno = 1L;
        ReplyDTO result = service.findById(rno);
        assertThat(result.getRno()).isEqualTo(rno);

    }

    @Test
    @Transactional
    void testGetList() {
        Long bno = 99L;

        List<ReplyDTO> replyDTOList = service.getList(bno);
        replyDTOList.forEach(replyDTO -> System.out.println(replyDTO));

    }

    @Test
    @DisplayName("댓글을 수정하는 테스트코드")
    void modifyReply() {
        ReplyDTO dto = ReplyDTO.builder().rno(99L)
                .title("안녕하세요.").content("히히").build();

        Long modifyRno = service.modify(dto);

        assertThat(modifyRno).isEqualTo(dto.getRno());

    }

    @Test
    @Transactional
    void deleteReply() {
        Long rno = 1L;
        service.remove(rno);

        assertThatThrownBy(() -> service.findById(rno)).isInstanceOf(EmptyResultDataAccessException.class);


    }
}
