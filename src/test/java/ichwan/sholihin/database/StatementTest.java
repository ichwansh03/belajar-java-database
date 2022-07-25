package ichwan.sholihin.database;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StatementTest {

    @Test
    void testCreateStatement() throws SQLException{
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();
        statement.close();
        connection.close();
    }

    @Test
    void testExecuteUpdate() throws SQLException{
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();

        String sql = """
                INSERT INTO customer(id, name, email)
                VALUES ('abdul','Ichwan','ichwan@gmail.com')
                """;

        int update  = statement.executeUpdate(sql);
        System.out.println(update);

        statement.close();
        connection.close();
    }

    @Test
    void testExecuteDelete() throws SQLException{
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();

        String sql = """
                DELETE FROM customer
                """;

        int update  = statement.executeUpdate(sql);
        System.out.println(update);

        statement.close();
        connection.close();
    }

    @Test
    void testExecuteQuery() throws SQLException{
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();

        //ketika select dari table admin, jangan menggunakan string append (username ditambahin karakter namauser'; # maka anonymous akan masuk)
        String sql = """
                SELECT * FORM customer
                """;

        ResultSet resultSet = statement.executeQuery(sql);

        while(resultSet.next()){
            //pengambilan data dari resultset
            String id = resultSet.getString("id");
            String name = resultSet.getString("customer.name");
            String email = resultSet.getString("email");

            System.out.println(String.join(", ",id, name, email));
        }

        statement.close();
        connection.close();
    }

}
