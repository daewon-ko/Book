package org.zerock.board.repository.reply;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.zerock.board.entity.Board;
import org.zerock.board.entity.Reply;

import javax.sql.DataSource;

@Repository
public class ReplyRepositoryImpl implements ReplyJdbcRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;


    public ReplyRepositoryImpl(final DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public int countReplies(final Long bno) {
        String sql = "SELECT COUNT(*) FROM Reply r INNER JOIN Board b on r.board_bno = b.bno";
        SqlParameterSource param = new MapSqlParameterSource("bno", bno);
        Integer count = jdbcTemplate.queryForObject(sql, param, Integer.class);
        return count != null ? count : 0;
    }

    @Override
    public void saveReply(final Reply reply) {
        String sql = "INSERT INTO Reply ( board_bno, title, content, replyer) " +
                " VALUES ( :boardNumber, :title, :content, :replyer)";
        MapSqlParameterSource param = new MapSqlParameterSource();
        KeyHolder keyHolder = new GeneratedKeyHolder();
        param.addValue("boardNumber", reply.getBoard().getBno());
        param.addValue("title", reply.getTitle());
        param.addValue("content", reply.getContent());
        param.addValue("replyer", reply.getMember().getEmail());
        jdbcTemplate.update(sql, param, keyHolder);
    }
}
