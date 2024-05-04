package org.zerock.board.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberDTO {
    private String email;
    private String password;
    private String name;
}
