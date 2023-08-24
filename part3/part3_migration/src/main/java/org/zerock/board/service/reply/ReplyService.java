package org.zerock.board.service.reply;

import org.zerock.board.dto.ReplyDTO;
import org.zerock.board.entity.Reply;

import java.util.List;

public interface ReplyService {

    ReplyDTO findById(Long rno);

    Long register(ReplyDTO replyDTO);

    List<ReplyDTO> getList(Long bno);

    Long modify(ReplyDTO replyDTO);

    void remove(Long rno);

    default Reply dtoToEntity(ReplyDTO replyDTO) {
        return Reply.builder()
                .rno(replyDTO.getRno())
                .bno(replyDTO.getBno())
                .title(replyDTO.getTitle())
                .content(replyDTO.getContent())
                .replyer(replyDTO.getReplyer())
                .build();
    }

    default ReplyDTO entityToDTO(Reply reply) {
        return ReplyDTO.builder()
                .rno(reply.getRno())
                .bno(reply.getBno())
                .content(reply.getContent())
                .title(reply.getTitle())
                .replyer(reply.getReplyer())
                .build();
    }
}
