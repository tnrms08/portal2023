package kr.ac.jejunu.userdao.user;

// 얘만 JejuConnectionMaker와 의존성을 가진다.
// Spring의 코어부분...??
public class DaoFactory {
    public UserDao getUserDao(){
        UserDao userDao= new UserDao(connectionMaker());
        return userDao;
    }

    private ConnectionMaker connectionMaker() {
        return new JejuConnectionMaker();
    }
}
