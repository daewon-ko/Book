package org.zerock.board.entity;


import lombok.*;
import org.zerock.board.dto.GuestBookDTO;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GuestBook {

    private Long gno;
    private String title;
    private String email;

    private String content;
    private LocalDateTime regDate;
    private LocalDateTime modDate;


    public void changeTitle(GuestBookDTO dto) {
        this.title = dto.getTitle();
    }

    public void changeContent(GuestBookDTO dto) {
        this.content = dto.getContent();
    }
}
