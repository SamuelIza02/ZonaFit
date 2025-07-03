package zona_fit.datos;

import zona_fit.dominio.Cliente;

import java.util.List;

/**
 * Interfaz DAO (Data Access Object) para la entidad Cliente.
 * Define las operaciones CRUD (Create, Read, Update, Delete) 
 * que se pueden realizar sobre los clientes en la base de datos.
 */
public interface IClienteDAO {
    
    /**
     * Obtiene todos los clientes de la base de datos
     * @return Lista de todos los clientes
     */
    List<Cliente> listarClientes();
    
    /**
     * Busca un cliente por su ID y llena sus datos
     * @param cliente objeto con el ID a buscar
     * @return true si encuentra el cliente, false en caso contrario
     */
    boolean buscarClientePorId(Cliente cliente);
    
    /**
     * Agrega un nuevo cliente a la base de datos
     * @param cliente objeto cliente a insertar
     * @return true si se insertó correctamente, false en caso contrario
     */
    boolean agregarCliente(Cliente cliente);
    
    /**
     * Modifica los datos de un cliente existente
     * @param cliente objeto con los nuevos datos del cliente
     * @return true si se modificó correctamente, false en caso contrario
     */
    boolean modificarCliente(Cliente cliente);
    
    /**
     * Elimina un cliente de la base de datos
     * @param cliente objeto cliente a eliminar
     * @return true si se eliminó correctamente, false en caso contrario
     */
    boolean eliminarCliente(Cliente cliente);
}
