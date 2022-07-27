package ichwan.sholihin.database;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.*;

public class MetaDataTest {

    //untuk mendapatkan informasi seputar nama/versi database atau nama table di db nya
    @Test
    void testMetaData() throws SQLException{
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        DatabaseMetaData metaData = connection.getMetaData();

        System.out.println(metaData.getDatabaseProductName());
        System.out.println(metaData.getDatabaseProductVersion());

        ResultSet resultSet = metaData.getTables("db_belajar_java",null,null,null);
        while (resultSet.next()){
            String tableName = resultSet.getString("TABLE_NAME");
            System.out.println(tableName);
        }

        connection.close();
    }

    @Test
    void testResultSetMetaData() throws SQLException{
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM customer");

        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        for (int i = 1; i <= resultSetMetaData.getColumnCount() ; i++) {
            System.out.println("nama : "+resultSetMetaData.getColumnName(i));
            //outputnya angka karena representasi dari java.sql.Types
            System.out.println("tipe : "+resultSetMetaData.getColumnType(i));
            System.out.println("tipe name : "+resultSetMetaData.getColumnTypeName(i));
        }

        resultSet.close();
        statement.close();
        connection.close();
    }
}
