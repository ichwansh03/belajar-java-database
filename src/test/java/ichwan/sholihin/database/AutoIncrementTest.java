package ichwan.sholihin.database;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class AutoIncrementTest {

    //ketika insert, langsung dapetin hasil generated auto increment dari id
    @Test
    void testAutoIncrement() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        String sql = "INSERT INTO comments(email, comment) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        preparedStatement.setString(1,"mantap@gmail.com");
        preparedStatement.setString(2,"halo");

        preparedStatement.executeUpdate();

        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        if (resultSet.next()){
            System.out.println("id comment : "+resultSet.getInt(1));
        }

        preparedStatement.close();
        connection.close();
    }
}
