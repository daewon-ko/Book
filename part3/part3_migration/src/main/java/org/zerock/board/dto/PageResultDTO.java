package org.zerock.board.dto;

import lombok.Data;

import java.util.List;


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
