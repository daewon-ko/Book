package org.zerock.board.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.board.dto.BoardDTO;
import org.zerock.board.dto.PageRequestDTO;
import org.zerock.board.dto.PageResultDTO;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BoardServiceImplTest {

    @Autowired
    private BoardService boardService;

    @Test
    public void testRegister() {
        BoardDTO.builder()
                .title("test")
                .content("test")
                .writerEmail("user55@aaa.com")
                .build();

    }


    @Test
    public void testList() {
        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        PageResultDTO<BoardDTO, Object[]> result = boardService.getList(pageRequestDTO);

        for (BoardDTO boardDTO : result.getDtoList()) {
            System.out.println("boardDTO = " + boardDTO);
        }

    }

    @Test
    @DisplayName("게시물 번호(bno)로 게시글을 조회하는 테스트코드")
    public void testGet() {

        Long bno = 100L;
        BoardDTO result = boardService.getBoardByBno(bno);

        assertThat(result.getBno()).isEqualTo(100L);
    }

    @Test
    @Transactional
    @DisplayName("특정 게시글 번호를 삭제하는 테스트 코드")
    public void deleteByBno() {

        //given
        Long bno = 4L;

        //when
        Long deletedBno = boardService.removeWithReplies(bno);

        assertThat(deletedBno).isEqualTo(4L);
    }

    @Test
    @DisplayName("제목과 내용을 변경하는 테스트 코드")
    @Transactional
    public void testModify() {
        BoardDTO result = BoardDTO.builder()
                .bno(5L)
                .title("제목 변경")
                .content("내용 변경")
                .build();

        System.out.println("result = " + result);

        assertThat(result.getContent()).isEqualTo("내용 변경");
    }
}
