package zona_fit.conexion;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Clase utilitaria para gestionar la conexión a la base de datos MySQL.
 * Implementa el patrón Singleton implícito para conexiones de base de datos.
 */
public class Conexion {
    
    /**
 * Establece y retorna una conexión a la base de datos MySQL.
 * 
 * @return Connection objeto de conexión a la base de datos, null si falla
 */
    public static Connection getConexion(){
        Connection conexion = null;
        // Configuración de la base de datos
        String baseDatos = "zona_fit_db";
        String url = "jdbc:mysql://localhost:3306/" + baseDatos;
        String usuario = "*******";
        String password = "*******";
        
        try{
            // Cargar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establecer la conexión
            conexion = DriverManager.getConnection(url, usuario, password);
        }catch (Exception e){
            System.out.println("Error al conectar con la base de datos" + e.getMessage());
        }
        return conexion;
    }

    public static void main(String[] args) {
        Connection conexion = Conexion.getConexion();
        if (conexion != null){
            System.out.println("Conexion exitosa: " + conexion);
        }else {
            System.out.println("Error al conectarse");
        }
    }
}
