package org.zerock.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.board.dto.BoardDTO;
import org.zerock.board.entity.Board;
import org.zerock.board.repository.BoardRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardServiceImpl implements BoardService{

    private final BoardRepository repository;

    @Override
    public Long register(final BoardDTO dTo) {
        log.info("dto: {}", dTo);

        Board board = dtoToEntity(dTo);

        repository.save(board);
        return board.getBno();
    }
}
