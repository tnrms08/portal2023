package kr.ac.jejunu.userdao;

import kr.ac.jejunu.user.User;
import kr.ac.jejunu.user.UserDao;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class UserDaoTest {
    @Test
    public void get() throws SQLException, ClassNotFoundException {
        UserDao userDao = new UserDao();

        //DB에 작성된 내용으로 지정
        Long id = 1l;
        String name  = "angela";
        String password = "1234";

        User user = userDao.findById(id);
        assertThat(user.getId(),is(id));
        assertThat(user.getName(),is(name));
        assertThat(user.getPassword(),is(password));
    }


}
