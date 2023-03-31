package kr.ac.jejunu.userdao;

import kr.ac.jejunu.userdao.user.DaoFactory;
import kr.ac.jejunu.userdao.user.User;
import kr.ac.jejunu.userdao.user.UserDao;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;

public class UserDaoTest {
    private static UserDao userDao;

    @BeforeAll    //이걸 반드시 하고 TestCase 수행
    public static void setup(){
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(DaoFactory.class);
        userDao = applicationContext.getBean("userDao",UserDao.class);
    }
    @Test
    public void get() throws SQLException, ClassNotFoundException {
        //DB에 작성된 내용으로 지정
        Long id = 1l;
        String name  = "angela";
        String password = "1234";

        User user = userDao.findById(id);
        assertThat(user.getId(),is(id));
        assertThat(user.getName(),is(name));
        assertThat(user.getPassword(),is(password));
    }
    @Test
    public void insert() throws SQLException, ClassNotFoundException {
        String name = "김미숙";
        String password = "1111";
        User user = new User();
        user.setName(name);
        user.setPassword(password);


//        ApplicationContext applicationContext =
//                new AnnotationConfigApplicationContext(DaoFactory.class);
//        UserDao userDao = applicationContext.getBean("userDao",UserDao.class);
//        DaoFactory daoFactory = new DaoFactory();
//        UserDao userDao = daoFactory.userDao();
        userDao.insert(user);
        assertThat(user.getId(), greaterThan(1l));

        User insertedUser = userDao.findById(user.getId());
        assertThat(insertedUser.getId(),  is(user.getId()));
        assertThat(insertedUser.getName(), is(name));
        assertThat(insertedUser.getPassword(), is(password));
    }

    //한라대
//    @Test
//    public void getForHalla() throws SQLException, ClassNotFoundException {
////        UserDao userDao = new HallaConnectionMaker();
//        ConnectionMaker connectionMaker = new HallaConnectionMaker();
//        UserDao userDao = new UserDao(connectionMaker);
//
//        //DB에 작성된 내용으로 지정
//        Long id = 1l;
//        String name  = "hulk";
//        String password = "1111";
//
//        User user = userDao.findById(id);
//        assertThat(user.getId(),is(id));
//        assertThat(user.getName(),is(name));
//        assertThat(user.getPassword(),is(password));
//    }
//
//    @Test
//    public void insertForHalla() throws SQLException, ClassNotFoundException {
//        String name = "김미숙";
//        String password = "1111";
//        User user = new User();
//        user.setName(name);
//        user.setPassword(password);
//
////        UserDao userDao = new HallaConnectionMaker();
//        ConnectionMaker connectionMaker = new HallaConnectionMaker();
//        UserDao userDao = new UserDao(connectionMaker);
//
//        userDao.insert(user);
//        assertThat(user.getId(), greaterThan(1l));
//
//        User insertedUser = userDao.findById(user.getId());
//        assertThat(insertedUser.getId(),  is(user.getId()));
//        assertThat(insertedUser.getName(), is(name));
//        assertThat(insertedUser.getPassword(), is(password));
//    }

}
