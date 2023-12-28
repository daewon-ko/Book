package toby.spring.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import toby.spring.spring.domain.connectionmaker.ConnectionMaker;
import toby.spring.spring.domain.connectionmaker.DConnectionMaker;
import toby.spring.spring.domain.dao.DaoFactory;
import toby.spring.spring.domain.user.User;
import toby.spring.spring.domain.dao.UserDao;

import java.sql.SQLException;

public class UserDaoTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao dao = context.getBean("userDao", UserDao.class);

        User user = new User();
        user.setId("whiteShip");
        user.setName("백기선");
        user.setPassword("married");

        dao.add(user);

        System.out.println(user.getId() + "등록 성공");

        User user2 = dao.get(user.getId());
        System.out.println(user2.getName());
        System.out.println(user2.getPassword());

        System.out.println(user2.getId()+"조회 성공");


    }
}
