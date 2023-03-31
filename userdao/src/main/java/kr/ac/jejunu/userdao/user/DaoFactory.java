package kr.ac.jejunu.userdao.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 얘만 JejuConnectionMaker와 의존성을 가진다.
// Spring의 코어부분...??
@Configuration
public class DaoFactory {
    @Bean   //spring이 new 해준 애들은 Bean이라고 얘기한다.
    public UserDao userDao(){
        UserDao userDao= new UserDao(connectionMaker());
        return userDao;
    }

    @Bean
    public ConnectionMaker connectionMaker() {
        return new JejuConnectionMaker();
    }
}
