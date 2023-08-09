package org.zerock.board.repository.board;

import org.zerock.board.common.Pagination;
import org.zerock.board.dto.PageRequestDTO;
import org.zerock.board.entity.Board;

import java.util.List;
import java.util.Optional;

public interface BoardJDBCRepository {
    Optional<Board> findById(Long gno);


    List<Board> findAll();

    void modify(Board board);

    boolean save(Board board);

    List<Board> findPages(PageRequestDTO requestDTO, Pagination pagination);

    int calculateTotalRecordCount(PageRequestDTO pageRequestDTO);

    void deleteById(long gno);

    int countRepliesByBno(long bno);

//    void delete(Long gno);

}
