package org.zerock.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.board.dto.BoardDTO;
import org.zerock.board.dto.PageRequestDTO;
import org.zerock.board.dto.PageResultDTO;
import org.zerock.board.entity.Board;
import org.zerock.board.entity.Member;
import org.zerock.board.repository.BoardRepository;
import org.zerock.board.repository.ReplyRepository;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardServiceImpl implements BoardService {

    private final BoardRepository repository;

    private final ReplyRepository replyRepository;

    @Override
    public Long register(final BoardDTO dTo) {
        log.info("dto: {}", dTo);

        Board board = dtoToEntity(dTo);
        log.info("Board Entity : {}", board);

        repository.save(board);
        return board.getBno();
    }

    @Override
    public PageResultDTO<BoardDTO, Object[]> getList(final PageRequestDTO pageRequestDTO) {


        log.info("pageRequestDTO: {}", pageRequestDTO);

        Function<Object[], BoardDTO> fn = (en -> entityToDTO((Board) en[0], (Member) en[1],
                (Long) en[2]));

        Page<Object[]> result = repository.getBoardWithReplyCount(
                pageRequestDTO.getPageable(Sort.by("bno").descending()
                ));

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public BoardDTO getBoardByBno(final Long bno) {
        Object result = repository.getBoardByBno(bno);

        Object[] arr = (Object[]) result;

        return entityToDTO((Board) arr[0], (Member) arr[1], (Long) arr[2]);
    }

    @Override
    @Transactional
    public Long removeWithReplies(final Long bno) {
        replyRepository.deleteByBno(bno);
        repository.deleteById(bno);

        return bno;
    }

    @Override
    @Transactional
    public void modify(final BoardDTO boardDTO) {
        Board board = repository.getReferenceById(boardDTO.getBno());

        if (board != null) {
            board.changeContent(boardDTO.getContent());
            board.changeTitle(board.getTitle());
        }
    }
}
