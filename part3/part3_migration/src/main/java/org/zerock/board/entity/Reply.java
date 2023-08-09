package org.zerock.board.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reply {
    private long rno;
    private Board board;
    private String title;
    private String content;
    private Member member;
}
