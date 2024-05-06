package org.zerock.board.entity;


import lombok.*;
import org.zerock.board.dto.BoardDTO;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Board {

    private Long bno;
    private Member writer;
    private String title;
    private String content;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
    private boolean deleted;


    public void changeTitle(BoardDTO dto) {
        this.title = dto.getTitle();
    }

    public void changeContent(BoardDTO dto) {
        this.content = dto.getContent();
    }

//    public static BoardDTO toDTO(Board board) {
//        BoardDTO boardDTO = new BoardDTO();
//        boardDTO.setBno(board.getBno());
//        boardDTO.setTitle(board.getTitle());
//        boardDTO.setContent(board.getContent());
//        boardDTO.setModDate(board.getModDate());
//        boardDTO.setRegDate(board.getRegDate());
//        return boardDTO;
//    }
}
