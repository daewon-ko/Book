package toby.spring.spring.domain.dao;

import toby.spring.spring.domain.strategy.StatementStrategy;
import toby.spring.spring.domain.user.User;

import javax.sql.DataSource;
import java.sql.*;

public  class UserDao {
    private DataSource dataSource;

    public UserDao(DataSource dataSource) {
        // 생성자에서 ConnectionMaker를 초기화하는데, 구체클래스에 의존한다. 해결책은?
        this.dataSource = dataSource;
    }


    public void add(final User user) throws ClassNotFoundException, SQLException {
        StatementStrategy statementStrategy = new StatementStrategy() {
            @Override
            public PreparedStatement makePreparedStatement(final Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement("insert into users(id, name, password) values (?, ?, ?)");
                ps.setString(1, user.getId());
                ps.setString(2, user.getName());
                ps.setString(3, user.getName());

                return ps;
            }
        };

        jdbcContextWithStatementStrategy(statementStrategy);
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

    public void jdbcContextWithStatementStrategy(StatementStrategy statementStrategy) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = dataSource.getConnection();
            ps = statementStrategy.makePreparedStatement(c);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
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

    public void deleteAll() throws SQLException {
        StatementStrategy statementStrategy = new StatementStrategy() {
            @Override
            public PreparedStatement makePreparedStatement(final Connection connection) throws SQLException {
                return connection.prepareStatement("delete from users");
            }
        };
        jdbcContextWithStatementStrategy(statementStrategy);
    }


}
