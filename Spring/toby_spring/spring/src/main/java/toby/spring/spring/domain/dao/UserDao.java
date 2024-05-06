package toby.spring.spring.domain.dao;

import toby.spring.spring.domain.strategy.StatementStrategy;
import toby.spring.spring.domain.user.User;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {
    private JdbcContext jdbcContext;
    private DataSource dataSource;

    public void setDataSource(final DataSource dataSource) {
        jdbcContext = new JdbcContext();
        jdbcContext.setDataSource(dataSource);
        this.dataSource = dataSource;
    }

//    public void setJdbcContext(final JdbcContext jdbcContext) {
//        this.jdbcContext = jdbcContext;
//    }

    public void add(final User user) throws ClassNotFoundException, SQLException {
        jdbcContext.workWithStatementStrategy(new StatementStrategy() {
            @Override
            public PreparedStatement makePreparedStatement(final Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement("insert into users(id, name, password) values (?, ?, ?)");
                ps.setString(1, user.getId());
                ps.setString(2, user.getName());
                ps.setString(3, user.getName());

                return ps;
            }
        });
    }

    public User get(String id) throws ClassNotFoundException, SQLException {

        Connection c = dataSource.getConnection();

        PreparedStatement ps = c.prepareStatement(
                "select * from users where id = ?"
        );
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();

        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        ps.close();
        c.close();

        return user;
    }


    public int getCount() throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = dataSource.getConnection();
            ps = c.prepareStatement("select count(*) from users");
            rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            throw e;
        } finally {

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {

                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {

                }
            }
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {

                }
            }
        }
    }


    public void deleteAll(final String query) throws SQLException {
        executeSql("delete from users");
    }

    public void add(final String query) {

    }

    public void executeSql(final String query) throws SQLException {

        jdbcContext.workWithStatementStrategy(new StatementStrategy() {
            @Override
            public PreparedStatement makePreparedStatement(final Connection connection) throws SQLException {
                return connection.prepareStatement(query);
            }
        });
    }


}
