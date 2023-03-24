package kr.ac.jejunu.userdao.user;

import java.sql.*;

//public abstract class UserDao {
public class UserDao {
    private final ConnectionMaker connectionMaker;

    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }


//    private final ConnectionMaker connectionMaker = new ConnectionMaker();

    public User findById(Long id) throws ClassNotFoundException, SQLException {
        Connection connection = connectionMaker.getConnection();    //Method로 리팩토링(Ctrl+Alt+M)
        //쿼리 만들고
        PreparedStatement preparedStatement = connection.prepareStatement
                ("select id, name, password from userinfo where id = ?");
        preparedStatement.setLong(1, id);
        //쿼리 실행하고
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        //결과를 사용자 정보에 매핑하고
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));
        //자원해지
        resultSet.close();
        preparedStatement.close();
        connection.close();
        //결과리턴
        return user;
    }

    public void insert(User user) throws ClassNotFoundException, SQLException {
        Connection connection = connectionMaker.getConnection();
        //쿼리 만들고
        PreparedStatement preparedStatement = connection.prepareStatement
                ("insert into userinfo (name, password) values ( ?, ? )"
                        , Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());
        //쿼리 실행하고
        preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        resultSet.next();
        user.setId(resultSet.getLong(1));
        //자원해지
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }

    //1. Module로 변경
    /* 이 코드가 있던 자리에 "Connection connection = getConnection();" 생성되고 Module로 생성됨
    private static Connection getConnection() throws ClassNotFoundException, SQLException {

        //데이터 어딨어? mysql
        //mysql 클래스 로딩
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Connection 맺고 return
        return DriverManager.getConnection
                ("jdbc:mysql://localhost/jeju", "root", "010810");
    }
     */

    // 2. 위의 Connection을 추상화 : 한라대가 들어올지 제주대가 들어지 모르기 때문
    // => 추상화 한 다음에 HallaUserDao, JejuUserDao를 만들어서 재정의
    // UserDao 안에서의 중복 제거를 위해 사용 (같은 클래스)
//    abstract public Connection getConnection() throws ClassNotFoundException, SQLException ;

    // 3. Connection을 추상화가 아닌 인터페이스로 지정하기
    // 다른 오브젝트도 사용하지만 뭐가 들어올지 모르는 경우 (다른 클래스까지 사용하는 추상화로 사용)
//    public Connection getConnection() throws ClassNotFoundException, SQLException {
//
//        //데이터 어딨어? mysql
//        //mysql 클래스 로딩
//        //Connection 맺고 return
//        return connectionMaker.getConnection();
//    }
//    private final ConnectionMaker connectionMaker=new JejuConnectionMaker();
//    private final ConnectionMaker connectionMaker;
}
