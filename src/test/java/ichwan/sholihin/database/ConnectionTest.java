package ichwan.sholihin.database;

import com.mysql.cj.jdbc.Driver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {

    //registrasi terlebih dahulu sebelum konek ke database
    @BeforeAll
    static void beforeAll(){
        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
        } catch (SQLException exception){
            exception.printStackTrace();
        }
    }

    @Test
    void testConnectToDatabase(){
        String URL = "jdbc:mysql://localhost:3306/db_belajar_java";
        String username = "root";
        String password = "";

        //try with resource, menutup koneksi db secara otomatis
        try(Connection connection = DriverManager.getConnection(URL, username, password)) {
            System.out.println("sukses terhubung");
            //menutup koneksi untuk menghindari nilai maksimum koneksi
            //connection.close();
            //System.out.println("koneksi ditutup");
        } catch (SQLException e){
            Assertions.fail(e);
        }
    }
}
