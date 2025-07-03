package zona_fit.datos;

import zona_fit.conexion.Conexion;
import zona_fit.dominio.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static zona_fit.conexion.Conexion.getConexion;

/**
 * Implementación concreta del patrón DAO para la entidad Cliente.
 * Maneja todas las operaciones de base de datos relacionadas con clientes.
 */
public class ClienteDAO implements IClienteDAO{
    
    /**
     * Obtiene todos los clientes de la base de datos ordenados por ID
     * @return Lista de clientes obtenidos de la base de datos
     */
    @Override
    public List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        String sql = "SELECT * FROM cliente ORDER BY id";
        try{
            // Preparar y ejecutar la consulta
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            // Procesar cada registro del ResultSet
            while (rs.next()){
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setMembresia(rs.getInt("membresia"));
                clientes.add(cliente);
            }
        }catch (Exception e){
            System.out.println("Error al listar clientes: " + e.getMessage());
        }finally {
            // Cerrar la conexión para liberar recursos
            try{
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerrar la conexion: " + e.getMessage());
            }
        }
        return clientes;
    }

    /**
     * Busca un cliente por ID y llena el objeto con los datos encontrados
     * @param cliente objeto que contiene el ID a buscar
     * @return true si encuentra el cliente, false en caso contrario
     */
    @Override
    public boolean buscarClientePorId(Cliente cliente) {
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        String sql = "SELECT * FROM cliente WHERE id = ?";
        try{
            // Preparar consulta con parámetro
            ps = con.prepareStatement(sql);
            ps.setInt(1, cliente.getId());
            rs  = ps.executeQuery();
            // Si encuentra el cliente, llenar sus datos
            if (rs.next()){
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setMembresia(rs.getInt("membresia"));
                return true;
            }
        }catch (Exception e){
            System.out.println("Error al buscar cliente por id: " + e.getMessage());
        }
        finally {
            try{
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerrar la conexion: " + e.getMessage());
            }
        }
        return false;
    }

    /**
     * Inserta un nuevo cliente en la base de datos
     * @param cliente objeto con los datos del nuevo cliente
     * @return true si se insertó correctamente, false en caso contrario
     */
    @Override
    public boolean agregarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "INSERT INTO cliente (nombre, apellido, membresia) VALUES (?, ?, ?)";
        try{
            // Preparar la inserción con los datos del cliente
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setInt(3, cliente.getMembresia());
            ps.execute();
            return true;

        }catch (Exception e){
            System.out.println("Error al agregar cliente: " + e.getMessage());
        }
        finally {
            try{
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerrar la conexion: " + e.getMessage());
            }
        }
        return false;
    }

    /**
     * Actualiza los datos de un cliente existente en la base de datos
     * @param cliente objeto con los nuevos datos del cliente
     * @return true si se modificó correctamente, false en caso contrario
     */
    @Override
    public boolean modificarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "UPDATE cliente SET nombre=?, apellido=?, membresia=? WHERE id = ?";
        try{
            // Preparar la actualización con los nuevos datos
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setInt(3, cliente.getMembresia());
            ps.setInt(4, cliente.getId());
            ps.execute();
            return true;
        }catch (Exception e){
            System.out.println("Error al modificar cliente: " + e.getMessage());
        }
        finally {
            try{
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerrar la conexion: " + e.getMessage());
            }
        }
        return false;
    }

    /**
     * Elimina un cliente de la base de datos
     * @param cliente objeto que contiene el ID del cliente a eliminar
     * @return true si se eliminó correctamente, false en caso contrario
     */
    @Override
    public boolean eliminarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "DELETE FROM cliente WHERE id = ?";
        try{
            // Preparar la eliminación por ID
            ps = con.prepareStatement(sql);
            ps.setInt(1, cliente.getId());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al eliminar cliente: " + e.getMessage());
        }
        finally {
            try{
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerrar la conexion: " + e.getMessage());
            }
        }
        return false;
    }

    public static void main(String[] args) {
        IClienteDAO clienteDAO = new ClienteDAO();

        //Prueba Modificar Cliente


        //Prueba Eliminar Cliente


//        //Prueba Listar Clientes
//        System.out.println("*** Listar Clientes ***");
//        List<Cliente> clientes = clienteDAO.listarClientes();
//        clientes.forEach(System.out::println);

    }
}
