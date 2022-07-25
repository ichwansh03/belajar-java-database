package ichwan.sholihin.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//untuk mengamankan data dari SQLInjection
public class PreparedStatementTest {

    @Test
    void testPrepareStatement() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();

        //testing inject SQL menggunakan karakter '; #
        String username = "admin'; #";
        String password = "salah";

        //JANGAN MENGGUNAKAN STRING APPEND!
        String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,username);
        preparedStatement.setString(2,password);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()){
            System.out.println("login berhasil");
        } else {
            System.out.println("login gagal");
        }

        preparedStatement.close();
        connection.close();
    }
}
