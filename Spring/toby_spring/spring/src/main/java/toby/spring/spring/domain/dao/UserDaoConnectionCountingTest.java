package toby.spring.spring.domain.dao;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import toby.spring.spring.domain.connectionmaker.CountingConnectionMaker;
import toby.spring.spring.domain.connectionmaker.DConnectionMaker;

public class UserDaoConnectionCountingTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CountingDaoFactory.class);
        UserDao dao = context.getBean("userDao", UserDao.class);
        /**
         * DAO 사용 코드
         */
        CountingConnectionMaker ccm= context.getBean("connectionMaker", CountingConnectionMaker.class);

        System.out.println("Connection counter : "+ ccm.getCounter());
    }
}
