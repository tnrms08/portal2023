package kr.ac.jejunu.userdao.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// JejuUserDao -> JejuConnectionMaker
//public class JejuUserDao extends UserDao {
public class JejuConnectionMaker implements ConnectionMaker{
    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException{
        //데이터 어딨어? mysql
        //mysql 클래스 로딩
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Connection 맺고
        return DriverManager.getConnection
                ("jdbc:mysql://localhost/jeju", "root", "010810");
    }

}