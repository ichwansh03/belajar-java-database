package ichwan.sholihin.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchTest {

    @Test
    void testBatchPrepareStatement() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        String sql = "INSERT INTO comments(email, comment) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        for (int i = 0; i < 100; i++) {
            preparedStatement.clearParameters();
            preparedStatement.setString(1,"mantap@gmail.com");
            preparedStatement.setString(2,"halo");
            preparedStatement.addBatch();
        }

        preparedStatement.executeBatch();
        preparedStatement.close();
        connection.close();
    }

    //ini test jika script SQL sudah ditentukan
    @Test
    void testBatchStatement() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();

        String sql = "INSERT INTO comments(email, comment) VALUES ('ichwan@gmail.com', 'hi')";

        for (int i = 0; i < 100; i++) {
            statement.addBatch(sql);
        }

        statement.executeBatch();
        statement.close();
        connection.close();
    }
}
