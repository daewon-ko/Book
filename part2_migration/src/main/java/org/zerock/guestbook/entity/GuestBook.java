package org.zerock.guestbook.entity;


import lombok.*;
import org.zerock.guestbook.dto.GuestBookDTO;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GuestBook {

    private Long gno;
    private String title;
    private String writer;

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
