package org.zerock.guestbook.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.zerock.guestbook.common.Pagination;
import org.zerock.guestbook.dto.GuestBookDTO;
import org.zerock.guestbook.dto.PageRequestDTO;
import org.zerock.guestbook.dto.PageResultDTO;
import org.zerock.guestbook.entity.GuestBook;
import org.zerock.guestbook.repository.JDBCRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class GuestbookServiceImpl implements GuestbookService {

    private final JDBCRepository repository;

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
    public GuestBookDTO read(final Long gno) {
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
        entity.changeContent(dto);
        entity.changeTitle(dto);
        log.info("GuestBook Content: {}, GuestBook Title: {}", entity.getContent(), entity.getTitle());
        repository.modify(entity);
    }


    @Override
    public PageResultDTO getList(final PageRequestDTO requestDTO) {
        /*
        하단의 3줄을 Page조건으로 변경할 필요. 
        어떤 로직인가? 
        PageRequest를 받아서 DB에 접근 후 해당하는 조건만큼 Select 해온다. 
        그 이후 fn조건을 이용하여 PageResultDto로 변환해준다. 
        
         */
//        Pageable pageable = requestDTO.getPageable(Sort.by("gno").descending());
//        BooleanBuilder booleanBuilder = getSearch(requestDTO);
//        Page<GuestBook> result = repository.findAll(booleanBuilder, pageable);
        //QueyDSL 사용

        int totalRecord = repository.calculateTotalRecordCount(requestDTO);

        Pagination pagination = new Pagination(totalRecord, requestDTO);

        List<GuestBookDTO> dtoList = repository.findPages(requestDTO, pagination).stream()
                .map(entity -> entityToDto(entity))
                .collect(Collectors.toUnmodifiableList());

        return new PageResultDTO<>(dtoList, pagination);
    }
}
