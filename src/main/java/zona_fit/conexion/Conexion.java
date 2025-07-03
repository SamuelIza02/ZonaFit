package zona_fit.conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    public static Connection getConexion(){
        Connection conexion = null;
        String baseDatos = "zona_fit_db";
        String url = "jdbc:mysql://localhost:3306/" + baseDatos;
        String usuario = "root";
        String password = "admin";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url, usuario, password);
        }catch (Exception e){
            System.out.println("Error al conectar con la base de datos" + e.getMessage());
        }
        return conexion;
    }
}
