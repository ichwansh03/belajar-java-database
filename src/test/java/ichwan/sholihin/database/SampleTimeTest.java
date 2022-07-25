package ichwan.sholihin.database;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class SampleTimeTest {

    @Test
    void testDateTime() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        String sql = """
                INSERT INTO sample_time(sample_date, sample_time, sample_timestamp)
                VALUES (?, ?, ?)
                """;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setDate(1, new Date(System.currentTimeMillis()));
        preparedStatement.setTime(2,new Time(System.currentTimeMillis()));
        preparedStatement.setTimestamp(3,new Timestamp(System.currentTimeMillis()));

        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }
}
