package org.zerock.board.service.board;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.zerock.board.common.Pagination;
import org.zerock.board.dto.BoardDTO;
import org.zerock.board.dto.PageRequestDTO;
import org.zerock.board.dto.PageResultDTO;
import org.zerock.board.entity.Board;
import org.zerock.board.repository.board.BoardJDBCRepository;
import org.zerock.board.repository.reply.ReplyJdbcRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardJDBCRepository boardJDBCRepository;

    private final ReplyJdbcRepository replyJdbcRepository;

    @Override
    public Long register(BoardDTO dto) {
        log.info("DTO-------");
        log.info(dto);

        Board entity = dtoToEntity(dto);
        log.info(entity);
        boardJDBCRepository.save(entity);

        return entity.getBno();
    }


    @Override
    public BoardDTO read(final Long gno) {
        Board board = boardJDBCRepository.findById(gno)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 아이디가 없습니다."));
        return entityToDto(board);
    }

    @Override
    public void remove(final long gno) {
        boardJDBCRepository.deleteById(gno);
    }



    @Override
    public void modify(final BoardDTO dto) {
        Optional<Board> result = boardJDBCRepository.findById(dto.getGno());
        Board entity = result.orElseThrow(() -> new IllegalArgumentException("해당하는 아이디를 찾을 수 없습니다."));
        entity.changeContent(dto);
        entity.changeTitle(dto);
        log.info("GuestBook Content: {}, GuestBook Title: {}", entity.getContent(), entity.getTitle());
        boardJDBCRepository.modify(entity);
    }


    @Override
    public PageResultDTO getList(final PageRequestDTO requestDTO) {


        int totalRecord = boardJDBCRepository.calculateTotalRecordCount(requestDTO);

        Pagination pagination = new Pagination(totalRecord, requestDTO);

        List<BoardDTO> dtoList = boardJDBCRepository.findPages(requestDTO, pagination).stream()
                .map(entity -> entityToDto(entity))
                .collect(Collectors.toUnmodifiableList());

        return new PageResultDTO<>(dtoList, pagination);
    }


    @Override
    public int getReplyCount(final Long bno) {
        return replyJdbcRepository.countReplies(bno);
    }
}
