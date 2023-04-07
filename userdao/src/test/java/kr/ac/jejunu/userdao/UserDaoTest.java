package kr.ac.jejunu.userdao;

import kr.ac.jejunu.userdao.user.DaoFactory;
import kr.ac.jejunu.userdao.user.User;
import kr.ac.jejunu.userdao.user.UserDao;
import org.hamcrest.core.IsNull;
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

        userDao.insert(user);
        assertThat(user.getId(), greaterThan(1l));

        User insertedUser = userDao.findById(user.getId());
        assertThat(insertedUser.getId(),  is(user.getId()));
        assertThat(insertedUser.getName(), is(name));
        assertThat(insertedUser.getPassword(), is(password));
    }
    @Test
    public void update() throws SQLException, ClassNotFoundException {
        User user = insertedUser();

        String updatedName = "updatedName";
        String updatedPassword = "2222";

        user.setName(updatedName);
        user.setPassword(updatedPassword);
        userDao.update(user);

        User updatedUser = userDao.findById(user.getId());
        assertThat(updatedUser.getName(), is(updatedName));
        assertThat(updatedUser.getPassword(), is(updatedPassword));
    }

    //Refactor -> Extract/Introduce -> Method
    private static User insertedUser() throws ClassNotFoundException, SQLException {
        String name = "김미숙";
        String password = "1111";
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        userDao.insert(user);
        return user;
    }

    @Test
    public void delete() throws SQLException, ClassNotFoundException {
        User user = insertedUser();
        userDao.delete(user.getId());

        User deletedUser = userDao.findById(user.getId());

        assertThat(deletedUser, IsNull.nullValue());
    }
}
