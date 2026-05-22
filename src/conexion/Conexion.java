package conexion;

import java.sql.Connection;
import java.sql.SQLException;

public class Conexion {
    String url ="jdbc:mysql://127.0.0.1:3306/puti_club_bcn";
    String driver = "com.mysql.cj.jdbc.Driver";
    String root = "NiggaDeluxe";
    String password = "TravelonPassw0rd";

    public static Connection getConnection() throws SQLException{

    }
}
