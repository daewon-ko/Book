package toby.spring.spring.domain.strategy;

import toby.spring.spring.domain.user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddStatement implements StatementStrategy{
    private User user;

    public AddStatement(final User user) {
        this.user = user;
    }

    @Override
    public PreparedStatement makePreparedStatement(final Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("insert into (id, name, password) values (?,?,?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());
        return ps;
    }
}
