package org.zerock.guestbook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class PageRequestDTO {
    private int page;
    private int recordSize;
    private int pageSize;
    private String type;
    private String keyword;

    public PageRequestDTO() {
        this.page = 1;
        this.recordSize = 10;
        this.pageSize = 10;
    }

    public int getOffSet() {
        return (page - 1) * recordSize;
    }
    
}
