package toby.spring.spring.domain.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import toby.spring.spring.domain.connectionmaker.ConnectionMaker;
import toby.spring.spring.domain.connectionmaker.DConnectionMaker;

@Configuration
public class DaoFactory {
    @Bean
    public UserDao userDao() {
        return new UserDao(connectionMaker());
    }

    @Bean
    public ConnectionMaker connectionMaker() {
        return new DConnectionMaker();
    }
}
