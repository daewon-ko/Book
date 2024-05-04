package org.zerock.guestbook.repository;

import org.zerock.guestbook.common.Pagination;
import org.zerock.guestbook.dto.PageRequestDTO;
import org.zerock.guestbook.entity.GuestBook;

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
