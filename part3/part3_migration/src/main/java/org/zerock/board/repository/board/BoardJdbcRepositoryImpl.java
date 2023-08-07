package org.zerock.board.repository.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.zerock.board.common.Pagination;
import org.zerock.board.dto.PageRequestDTO;
import org.zerock.board.entity.Board;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class BoardJdbcRepositoryImpl implements BoardJDBCRepository {


    @Autowired
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public BoardJdbcRepositoryImpl(final DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Optional<Board> findById(final Long gno) {
        String sql = "select * from GuestBook where gno =:gno";
        MapSqlParameterSource param = new MapSqlParameterSource().addValue("gno", gno); // 파라미터를 MapSqlParameterSource에 매핑
        return jdbcTemplate.query(sql, param, (rs, rowNum) -> new Board(
                rs.getLong("gno"),
                rs.getString("title"),
                rs.getString("writer"),
                rs.getString("content"),
                rs.getTimestamp("regdate").toLocalDateTime(),
                rs.getTimestamp("moddate").toLocalDateTime()
        )).stream().findAny();
    }

    @Override
    public List<Board> findAll() {
        String sql = "select * from GuestBook";
        BeanPropertyRowMapper param = new BeanPropertyRowMapper();
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Board(
                rs.getLong("gno"),
                rs.getString("title"),
                rs.getString("writer"),
                rs.getString("content"),
                rs.getTimestamp("regdate").toLocalDateTime(),
                rs.getTimestamp("moddate").toLocalDateTime()
        ));
    }

    @Override
    public void modify(final Board board) {
        String sql = "update GuestBook set title = :title, content = :content where GuestBook.gno = :gno";
        SqlParameterSource params = new BeanPropertySqlParameterSource(board);
        jdbcTemplate.update(sql, params);
    }


    @Override
    public boolean save(Board board) {
        String sql = "insert into GuestBook (title, writer, content)" +
                " values (:title, :writer, :content)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource params = new BeanPropertySqlParameterSource(board);
        jdbcTemplate.update(sql, params, keyHolder);
        return false;
    }

    @Override
    public List<Board> findPages(PageRequestDTO requestDTO, Pagination pagination) {
        int recordSize = requestDTO.getRecordSize();
        int offSet = pagination.getLimitStart();
        String sql = "select * from GuestBook where deleted = false order by gno desc limit :offSet, :recordSize";
        SqlParameterSource param = new BeanPropertySqlParameterSource(requestDTO);
        return jdbcTemplate.query(sql, param, (rs, rowNum) -> new Board(
                rs.getLong("gno"),
                rs.getString("title"),
                rs.getString("writer"),
                rs.getString("content"),
                rs.getTimestamp("regdate").toLocalDateTime(),
                rs.getTimestamp("moddate").toLocalDateTime()
        ));
    }

    @Override
    public int calculateTotalRecordCount(PageRequestDTO requestDTO) {
        String sql = "select count(*) from GuestBook";
        SqlParameterSource param = new BeanPropertySqlParameterSource(requestDTO);
        return jdbcTemplate.queryForObject(sql, param, Integer.class);
    }

    @Override
    public void deleteById(final long gno) {
        String sql = "UPDATE GuestBook Set deleted = true where gno =:gno";
        SqlParameterSource param = new MapSqlParameterSource().addValue("gno", gno);
        jdbcTemplate.update(sql, param);
    }

}
