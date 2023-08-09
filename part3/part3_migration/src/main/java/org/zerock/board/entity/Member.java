package org.zerock.board.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    private String email;
    private String password;
    private String name;

    public static Member createFromEmail(String email) {
        Member member = new Member();
        member.email = email;
        return member;

    }

}
