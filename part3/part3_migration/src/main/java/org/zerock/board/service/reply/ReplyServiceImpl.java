package org.zerock.board.service.reply;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.board.dto.ReplyDTO;
import org.zerock.board.entity.Reply;
import org.zerock.board.repository.reply.ReplyJdbcRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService{

    @Autowired
    private final ReplyJdbcRepository repository;

    @Override
    public ReplyDTO findById(final Long rno) {
        Reply reply = repository.findById(rno);
        return entityToDTO(reply);
    }

    @Override
    public Long register(final ReplyDTO replyDTO) {
        Reply reply = dtoToEntity(replyDTO);
        return repository.register(reply);
    }

    @Override
    public List<ReplyDTO> getList(final Long bno) {
        return repository.getReplyList(bno)
                .stream().map(entity -> entityToDTO(entity))
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public Long modify(final ReplyDTO replyDTO) {
        Reply reply = dtoToEntity(replyDTO);
        return repository.modifyReply(reply);

    }

    @Override
    public void remove(final Long rno) {
        repository.deleteReply(rno);

    }
}
