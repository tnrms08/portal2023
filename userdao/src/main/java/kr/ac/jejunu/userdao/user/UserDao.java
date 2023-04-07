package kr.ac.jejunu.userdao.user;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {
    private final DataSource dataSource;

    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public User findById(Long id) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null; //오류 발생
        User user = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement
                    ("select id, name, password from userinfo where id = ?");
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
//            resultSet.next();
//            user = new User();
//            user.setId(resultSet.getLong("id"));
//            user.setName(resultSet.getString("name"));
//            user.setPassword(resultSet.getString("password"));
            if(resultSet.next()){
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
            }

        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
       
        return user;
    }

    public void insert(User user) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            //쿼리 만들고
            preparedStatement = connection.prepareStatement
                    ("insert into userinfo (name, password) values ( ?, ? )"
                            , Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            //쿼리 실행하고
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            user.setId(resultSet.getLong(1));
        } finally {
            //자원해지
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            //쿼리 만들고
            preparedStatement = connection.prepareStatement
                    ("update userinfo set name = ?, password = ? where id = ?");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setLong(3,user.getId());
            //쿼리 실행하고
          preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            //자원해지
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void delete(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            //쿼리 만들고
            preparedStatement = connection.prepareStatement
                    ("delete from userinfo where id =?");
            preparedStatement.setLong(1,id);
            //쿼리 실행하고
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            //자원해지
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
