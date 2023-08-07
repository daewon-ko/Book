package org.zerock.board.repository.reply;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class ReplyRepositoryImpl implements ReplyJdbcRepository{

    private final NamedParameterJdbcTemplate jdbcTemplate;


    public ReplyRepositoryImpl(final DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public int countReplies(final Long bno) {
        String sql = "SELECT COUNT(*) FROM Reply where Reply.guestBook_gno = : bno";
        SqlParameterSource param = new MapSqlParameterSource("bno", bno);
        Integer count = jdbcTemplate.queryForObject(sql, param, Integer.class);
        return count != null ? count : 0;
    }
}
