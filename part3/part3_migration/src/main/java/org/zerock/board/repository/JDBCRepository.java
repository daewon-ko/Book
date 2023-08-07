package org.zerock.board.repository;

import org.zerock.board.common.Pagination;
import org.zerock.board.dto.PageRequestDTO;
import org.zerock.board.entity.GuestBook;

import java.util.List;
import java.util.Optional;

public interface JDBCRepository {
    Optional<GuestBook> findById(Long gno);


    List<GuestBook> findAll();

    void modify(GuestBook guestBook);

    boolean save(GuestBook guestBook);

    List<GuestBook> findPages(PageRequestDTO requestDTO, Pagination pagination);

    int calculateTotalRecordCount(PageRequestDTO pageRequestDTO);

    void deleteById(long gno);

//    void delete(Long gno);

}
