package org.zerock.guestbook.dto;

import lombok.Data;

import java.util.List;
import java.util.stream.IntStream;


@Data
public class PageResultDTO<DTO , Pagination> {
    // DTO 리스트
    private List<DTO> dtoList;

    private Pagination pagination;
    private String type;
    private String keyword;


    public PageResultDTO(final List<DTO> dtoList, final Pagination pagination) {
        this.dtoList = dtoList;
        this.pagination = pagination;

    }

}
