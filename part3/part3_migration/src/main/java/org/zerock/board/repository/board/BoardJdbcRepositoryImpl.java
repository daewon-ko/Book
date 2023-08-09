package org.zerock.board.repository.board;

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

// TODO : Board Repository 계층에서 다른 Domain인 Member와 MemberRepository를 의존하는 것이 과연 적절한가?
// 만약 이와 같이 작성한다면 테스트 코드 등을 작성할 떄 문제가 되는가?(단위 테스트)
import org.zerock.board.entity.Member;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class BoardJdbcRepositoryImpl implements BoardJDBCRepository {


    private final NamedParameterJdbcTemplate jdbcTemplate;


    public BoardJdbcRepositoryImpl(final DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Optional<Board> findById(final Long bno) {
        String sql = "select * from Board where bno =:bno";
        MapSqlParameterSource param = new MapSqlParameterSource().addValue("bno", bno); // 파라미터를 MapSqlParameterSource에 매핑
        return jdbcTemplate.query(sql, param, (rs, rowNum) -> new Board(
                rs.getLong("bno"),
                Member.createFromEmail(rs.getString("member_email")),
                rs.getString("title"),
                rs.getString("content"),
                rs.getTimestamp("regdate").toLocalDateTime(),
                rs.getTimestamp("moddate").toLocalDateTime(),
                rs.getBoolean("deleted")
        )).stream().findAny();
    }

    /**
     * Board 클래스에서 Member 클래스에 의존한다.
     * schema 또한 Board에서 Member_email(varchar 타입)이라는 컬럼을 통해
     * Member와 FK 관계를 설정하였다.
     *
     * Board 클래스의 findAll을
     * @return
     */
    @Override
    public List<Board> findAll() {
        String sql = "select * from Board";
        BeanPropertyRowMapper param = new BeanPropertyRowMapper();
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Board(
                rs.getLong("bno"),
                rs.getObject("member_email",Member.class),
                rs.getString("title"),
                rs.getString("content"),
                rs.getTimestamp("regdate").toLocalDateTime(),
                rs.getTimestamp("moddate").toLocalDateTime(),
                rs.getBoolean("deleted")
        ));
    }

    @Override
    public void modify(final Board board) {
        String sql = "update Board set title = :title, content = :content where Board.bno = :bno";
        SqlParameterSource params = new BeanPropertySqlParameterSource(board);
        jdbcTemplate.update(sql, params);
    }


    @Override
    public boolean save(Board board) {
        String sql = "insert into Board (member_email, title, content)" +
                " values (:memberEmail, :title, :content)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("memberEmail", board.getWriter().getEmail());
        param.addValue("title", board.getTitle());
        param.addValue("content", board.getContent());
//        SqlParameterSource params = new BeanPropertySqlParameterSource(board);
        jdbcTemplate.update(sql, param, keyHolder);
        return false;
    }

    @Override
    public List<Board> findPages(PageRequestDTO requestDTO, Pagination pagination) {

        int recordSize = requestDTO.getRecordSize();
        int offSet = pagination.getLimitStart();
        String sql = "select * from Board b INNER JOIN Member m on b.member_email = m.email " +
                " where b.deleted = false order by b.bno desc limit :offSet, :recordSize";
        SqlParameterSource param = new BeanPropertySqlParameterSource(requestDTO);
        return jdbcTemplate.query(sql, param, (rs, rowNum) -> new Board(
                rs.getLong("bno"),
                Member.createFromEmail(rs.getString("member_email")),
                rs.getString("title"),
                rs.getString("content"),
                rs.getTimestamp("regdate").toLocalDateTime(),
                rs.getTimestamp("moddate").toLocalDateTime(),
                rs.getBoolean("deleted")
        ));
    }

    @Override
    public int calculateTotalRecordCount(PageRequestDTO requestDTO) {
        String sql = "select count(*) from Board";
        SqlParameterSource param = new BeanPropertySqlParameterSource(requestDTO);
        return jdbcTemplate.queryForObject(sql, param, Integer.class);
    }

    @Override
    public void deleteById(final long bno) {
        String sql = "UPDATE Board Set deleted = true where bno =:bno";
        SqlParameterSource param = new MapSqlParameterSource().addValue("bno", bno);
        jdbcTemplate.update(sql, param);
    }

}
