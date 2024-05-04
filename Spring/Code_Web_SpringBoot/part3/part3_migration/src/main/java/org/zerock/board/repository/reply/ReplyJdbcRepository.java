package org.zerock.board.repository.reply;

import org.zerock.board.entity.Reply;

import java.util.List;

public interface ReplyJdbcRepository {
    int countReplies(Long bno);

    Long register(Reply reply);

    List<Reply> getReplyList(Long bno);

    Long modifyReply(Reply reply);

    void deleteReply(Long rno);

    Reply findById(Long rno);
}
