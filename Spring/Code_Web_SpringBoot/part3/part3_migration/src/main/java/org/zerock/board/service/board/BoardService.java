package org.zerock.board.service.board;

import org.zerock.board.dto.BoardDTO;
import org.zerock.board.dto.PageRequestDTO;
import org.zerock.board.dto.PageResultDTO;
import org.zerock.board.entity.Board;
import org.zerock.board.entity.Member;

public interface BoardService {
    Long register(BoardDTO dto);

    /**
     *
     * @param dto
     * @return Board
     *
     */
    default Board dtoToEntity(BoardDTO dto) {
        Member member = Member.builder().email(dto.getWriterEmail()).build();
        Board entity = Board.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(member)
                .regDate(dto.getRegDate())
                .modDate(dto.getModDate())
                .deleted(dto.isDeleted())
                .build();
        return entity;
    }

    default BoardDTO entityToDto(Board entity) {
        int replyCount = getReplyCount(entity.getBno());
        return BoardDTO.builder()
                .bno(entity.getBno())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writerEmail(entity.getWriter().getEmail())
                .writerName(entity.getWriter().getName())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .replyCount(replyCount)
                .deleted(entity.isDeleted())
                .build();
    }

    BoardDTO read(Long gno);

    void modify(BoardDTO dto);

    PageResultDTO getList(PageRequestDTO pageRequestDTO);

    void remove(long gno);

    int getReplyCount(Long bno);

}
