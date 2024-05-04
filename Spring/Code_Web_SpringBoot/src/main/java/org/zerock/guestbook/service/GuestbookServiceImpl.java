package org.zerock.guestbook.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.zerock.guestbook.dto.GuestBookDTO;
import org.zerock.guestbook.dto.PageRequestDTO;
import org.zerock.guestbook.dto.PageResultDTO;
import org.zerock.guestbook.entity.GuestBook;
import org.zerock.guestbook.entity.QGuestBook;
import org.zerock.guestbook.repository.GuestbookRepository;

import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class GuestbookServiceImpl implements GuestbookService {

    private final GuestbookRepository repository;

    @Override
    public Long register(GuestBookDTO dto) {
        log.info("DTO-------");
        log.info(dto);

        GuestBook entity = dtoToEntity(dto);
        log.info(entity);
        repository.save(entity);

        return entity.getGno();
    }

    @Override
    public PageResultDTO<GuestBookDTO, GuestBook> getList(final PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("gno").descending());
        BooleanBuilder booleanBuilder = getSearch(requestDTO);
        Page<GuestBook> result = repository.findAll(booleanBuilder, pageable);
    //QueyDSL 사용
        Function<GuestBook, GuestBookDTO> fn = (entity -> entityToDto(entity));
        return new PageResultDTO<>(result, fn);
    }

    @Override
    public GuestBookDTO read(final Long gno) {

//        Optional<GuestBook> result = repository.findById(gno);
//        return result.isPresent() ? entityToDto(result.get()) : null ;
        GuestBook guestBook = repository.findById(gno)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 아이디가 없습니다."));
        return entityToDto(guestBook);
    }

    @Override
    public void remove(final long gno) {
        repository.deleteById(gno);
    }

    @Override
    public void modify(final GuestBookDTO dto) {
        Optional<GuestBook> result = repository.findById(dto.getGno());
        GuestBook entity = result.orElseThrow(() -> new IllegalArgumentException("해당하는 아이디를 찾을 수 없습니다."));
        entity.changeTitle(dto.getTitle());
        entity.changeContent(dto.getContent());
        repository.save(entity);
    }

    private BooleanBuilder getSearch(PageRequestDTO requestDTO) {
        //QueryDSL 처리
        String type = requestDTO.getType();
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        QGuestBook qGuestBook = QGuestBook.guestBook;

        String keyword = requestDTO.getKeyword();
        BooleanExpression expression = qGuestBook.gno.gt(0L);   // gno >0 조건만 생성

        booleanBuilder.and(expression);
        /*
        검색 조건이 없는 경우
         */
        if (type == null || type.trim().length() == 0) {
            return booleanBuilder;
        }

        // 검색 조건을 작성하기
        BooleanBuilder conditionBuilder = new BooleanBuilder();
        if (type.contains("t")) {
            conditionBuilder.or(qGuestBook.title.contains(keyword));
        }
        if (type.contains("c")) {
            conditionBuilder.or(qGuestBook.content.contains(keyword));
        }
        if (type.contains("w")) {
            conditionBuilder.or(qGuestBook.writer.contains(keyword));
        }
        // 모든 조건 통합하기
        booleanBuilder.and(conditionBuilder);
        return booleanBuilder;
    }

}
