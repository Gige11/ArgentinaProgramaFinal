package ConecBD;

import java.sql.*;

public class BDconexion {

    private static Connection conexion;

    public static Connection getConexion() throws SQLException{

        if (conexion == null || conexion.isClosed()){
             conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/concesionaria","root","");
        }
        return conexion;
    }

    public static void CloseConnection() throws SQLException{
        if (conexion != null && !conexion.isClosed()) {
            conexion.close();
        }
    }
}
