package org.zerock.board.repository.reply;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.zerock.board.dto.ReplyDTO;
import org.zerock.board.entity.Board;
import org.zerock.board.entity.Member;
import org.zerock.board.entity.Reply;

import javax.sql.DataSource;
import java.util.List;

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
    public Long register(final Reply reply) {
        String sql = "INSERT INTO Reply ( board_bno, title, content, replyer) " +
                " VALUES ( :boardNumber, :title, :content, :replyer)";
        MapSqlParameterSource param = new MapSqlParameterSource();
        KeyHolder keyHolder = new GeneratedKeyHolder();
        param.addValue("boardNumber", reply.getBno());
        param.addValue("title", reply.getTitle());
        param.addValue("content", reply.getContent());
        param.addValue("replyer", reply.getReplyer());
        return (long) jdbcTemplate.update(sql, param, keyHolder);

    }

    @Override
    public List<Reply> getReplyList(final Long bno) {
        Board board = Board.builder().bno(bno).deleted(false).build();

        String sql = "SELECT * FROM Reply r INNER JOIN Board b ON r.board_bno = b.bno";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Reply(
                rs.getLong("rno"),
                rs.getLong("board_bno"),
                rs.getString("content"),
                rs.getString("title"),
                rs.getString("replyer")
        ));
    }

    @Override
    public Long modifyReply(Reply reply) {
        String sql = "UPDATE Reply Set content =:content WHERE Reply.rno = :rno";
        SqlParameterSource param = new BeanPropertySqlParameterSource(reply);
        return (long)jdbcTemplate.update(sql, param);

    }

    @Override
    public void deleteReply(final Long rno) {
        String sql = "DELETE FROM Reply WHERE Reply.rno =:rno";
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("rno", rno);
        jdbcTemplate.update(sql, param);
    }

    @Override
    public Reply findById(final Long rno) {
        String sql = "SELECT * FROM Reply WHERE Reply.rno=:rno";
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("rno", rno);
        return jdbcTemplate.queryForObject(sql, param, (rs,rowNum)-> new Reply(
                rs.getLong("rno"),
                rs.getLong("board_bno"),
                rs.getString("title"),
                rs.getString("content"),
                rs.getString("replyer")
        ));
    }


}
