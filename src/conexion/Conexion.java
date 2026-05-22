package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    public static String url ="jdbc:mysql://127.0.0.1:3306/puti_club_bcn";
    public static String driver = "com.mysql.cj.jdbc.Driver";
    public static String root = "NiggaDeluxe";
    public static String password = "TravelonPassw0rd";

    public static Connection getConnection() throws SQLException{
        try{
            Class.forName(driver);
        }catch (ClassNotFoundException e) {
            throw new SQLException("Driver no disponible");
        }
        return DriverManager.getConnection(url, root, password);

    }
}
