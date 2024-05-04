package org.zerock.board.repository.member;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.zerock.board.dto.MemberDTO;
import org.zerock.board.entity.Member;

import javax.sql.DataSource;

@Repository
public class MemberRepositoryImpl implements MemberRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public MemberRepositoryImpl(final DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Member findByEmail(final String email) {
        String sql = "SELECT * FROM Member where Member.email = :email";
        SqlParameterSource param = new MapSqlParameterSource("email", email);
        return jdbcTemplate.queryForObject(sql, param, (rs, rowNum) -> new Member(
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("name")
        ));
    }

    @Override
    public void save(final Member member) {
        String sql = "INSERT INTO Member values (:email, :password, :name)";
        SqlParameterSource param = new BeanPropertySqlParameterSource(member);
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, param, keyHolder);
    }
}



