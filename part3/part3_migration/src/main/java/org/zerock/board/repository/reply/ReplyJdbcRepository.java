package org.zerock.board.repository.reply;

import org.zerock.board.entity.Reply;

public interface ReplyJdbcRepository {
    int countReplies(Long bno);

    void saveReply(Reply reply);
}
