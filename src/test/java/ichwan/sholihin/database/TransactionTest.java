package ichwan.sholihin.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionTest {

    //commit data langsung ke SQL, jika tidak dicommit maka data tidak terinsert
    @Test
    void testCommit() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        connection.setAutoCommit(false);

        String sql = "INSERT INTO comments(email, comment) VALUES (?, ?)";

        for (int i = 0; i < 100; i++) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"mantap@gmail.com");
            preparedStatement.setString(2,"halo");
            preparedStatement.executeUpdate();
        }

        connection.commit();
        connection.close();
    }

    //tarik kembali data yg sudah di commit
    @Test
    void testRollback() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        connection.setAutoCommit(false);

        String sql = "INSERT INTO comments(email, comment) VALUES (?, ?)";

        for (int i = 0; i < 100; i++) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"mantap@gmail.com");
            preparedStatement.setString(2,"halo");
            preparedStatement.executeUpdate();
        }

        connection.rollback();
        connection.close();
    }
}
