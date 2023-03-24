package kr.ac.jejunu.userdao.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface ConnectionMaker {
//    public ConnectionMaker() {
    // 3. Connection을 추상화가 아닌 인터페이스로 지정하기

    // 다른 오브젝트도 사용하지만 뭐가 들어올지 모르는 경우 (다른 클래스까지 사용하는 추상화로 사용)
//    public Connection getConnection() throws ClassNotFoundException, SQLException{
//
//        //데이터 어딨어? mysql
//        //mysql 클래스 로딩
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        //Connection 맺고 return
//        return DriverManager.getConnection
//                ("jdbc:mysql://localhost/jeju", "root", "010810");
//    }

    // 인터페이스로 만들기
    public Connection getConnection() throws ClassNotFoundException, SQLException;
}