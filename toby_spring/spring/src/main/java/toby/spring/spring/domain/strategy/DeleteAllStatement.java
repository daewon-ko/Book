package toby.spring.spring.domain.strategy;

import toby.spring.spring.domain.strategy.StatementStrategy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteAllStatement implements StatementStrategy {
    @Override
    public PreparedStatement makePreparedStatement(final Connection connection) throws SQLException {
        return connection.prepareStatement("delete from users");
    }
}
