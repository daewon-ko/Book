package toby.spring.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import toby.spring.spring.domain.dao.JdbcContext;
import toby.spring.spring.domain.dao.UserDao;

import javax.sql.DataSource;

@Configuration
public class AppConfig {
//    @Bean
//    public JdbcContext jdbcContext() {
//        JdbcContext jdbcContext = new JdbcContext();
//        jdbcContext.setDataSource(dataSource());
//        return jdbcContext;
//    }

    @Bean
    public DataSource dataSource() {
        return new SimpleDriverDataSource();
    }

    @Bean4
    public UserDao userDao() {
        UserDao userDao = new UserDao();
        userDao.setDataSource(dataSource());
        return userDao;
    }

}
