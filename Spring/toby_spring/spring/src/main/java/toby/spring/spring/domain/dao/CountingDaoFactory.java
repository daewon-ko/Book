package toby.spring.spring.domain.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import toby.spring.spring.domain.connectionmaker.ConnectionMaker;
import toby.spring.spring.domain.connectionmaker.CountingConnectionMaker;
import toby.spring.spring.domain.connectionmaker.DConnectionMaker;

import java.util.concurrent.ConcurrentNavigableMap;

@Configuration
public class CountingDaoFactory {
    @Bean
    public UserDao userDao() {
        return new UserDao(connectionMaker());
    }

    @Bean
    public ConnectionMaker connectionMaker() {
        return new CountingConnectionMaker(realConnectionMaker());
    }
    @Bean
    public ConnectionMaker realConnectionMaker() {
        return new DConnectionMaker();
    }
}
