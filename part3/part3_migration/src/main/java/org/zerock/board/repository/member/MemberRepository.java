package org.zerock.board.repository.member;

import org.zerock.board.entity.Member;

public interface MemberRepository {
    Member findByEmail(String Email);
}
