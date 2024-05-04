package toby.spring.spring.domain.connectionmaker;

import lombok.Getter;

import java.sql.Connection;
import java.sql.SQLException;

@Getter
public class CountingConnectionMaker implements ConnectionMaker{
    private int counter;
    private ConnectionMaker connectionMaker;

    public CountingConnectionMaker(final ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    @Override
    public Connection makeConnection() throws ClassNotFoundException, SQLException {
        counter++;
        return connectionMaker.makeConnection();
    }

}
