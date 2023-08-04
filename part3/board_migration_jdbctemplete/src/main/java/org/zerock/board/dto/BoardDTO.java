package org.zerock.board.dto;

import com.sun.istack.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {
    private Long bno;
    private String title;
    private String content;
    private String writerEmail;
    private String writerName;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
    @Builder.Default
    private int replyCount =0; // 해당 게시글의 댓글 수
}


