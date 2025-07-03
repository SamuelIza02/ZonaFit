package zona_fit.presentacion;

import zona_fit.datos.ClienteDAO;
import zona_fit.datos.IClienteDAO;
import zona_fit.dominio.Cliente;

import java.util.List;
import java.util.Scanner;

/**
 * Clase principal de la aplicación ZonaFit.
 * Maneja la interfaz de usuario y la lógica de presentación.
 * Implementa un menú interactivo para gestionar clientes del gimnasio.
 */
public class ZonaFitApp {
    
    /**
     * Método principal que inicia la aplicación
     * @param args argumentos de línea de comandos
     */
    public static void main(String[] args) {
        zonaFitApp();
    }

    /**
     * Método principal de la aplicación que controla el flujo del programa.
     * Mantiene el menú activo hasta que el usuario decida salir.
     */
    public static void zonaFitApp(){
        boolean salir = false;
        Scanner consola = new Scanner(System.in);
        // Crear instancia del DAO para operaciones de base de datos
        IClienteDAO clienteDAO = new ClienteDAO();
        
        // Bucle principal del menú
        while (!salir){
            try {
                int opcion = mostrarMenu(consola);
                salir = ejecutarOpcion(consola, opcion, clienteDAO);
            } catch (Exception e) {
                System.out.println("Error al ejecutar las opciones: " + e.getMessage());
            }
            System.out.println();
        }
    }

    /**
     * Muestra el menú principal y captura la opción del usuario
     * @param consola objeto Scanner para leer entrada del usuario
     * @return número de opción seleccionada por el usuario
     */
    private static int mostrarMenu(Scanner consola) {
        System.out.println("""
                *** Zona Fit ***
                1. Listar Clientes
                2. Buscar Cliente
                3. Agregar Cliente
                4. Modificar Cliente
                5. Eliminar Cliente
                6. Salir
                Elige una opcion: \s""");
        return Integer.parseInt(consola.nextLine());
    }

    /**
     * Ejecuta la opción seleccionada por el usuario
     * @param consola objeto Scanner para entrada de datos
     * @param opcion número de opción a ejecutar
     * @param clienteDAO objeto DAO para operaciones de base de datos
     * @return true si el usuario quiere salir, false para continuar
     */
    private static boolean ejecutarOpcion(Scanner consola, int opcion, IClienteDAO clienteDAO) {
        boolean salir = false;
        switch (opcion){
            case 1 -> listarClientes(clienteDAO);
            case 2 -> buscarClientePorId(consola, clienteDAO);
            case 3 -> agregarCliente(consola, clienteDAO);
            case 4 -> modificarCliente(clienteDAO, consola);
            case 5 -> eliminarCliente(clienteDAO, consola);
            case 6 -> {
                System.out.println("Hasta luego");
                salir = true;
            }
            default -> System.out.println("Opcion no valida");
        }
        return salir;
    }

    /**
     * Lista todos los clientes registrados en el sistema
     * @param clienteDAO objeto DAO para acceder a los datos
     */
    private static void listarClientes(IClienteDAO clienteDAO){
        System.out.println("*** Listado Clientes ***");
        List<Cliente> clientes = clienteDAO.listarClientes();
        clientes.forEach(System.out::println);
    }

    /**
     * Busca y muestra un cliente específico por su ID
     * @param consola objeto Scanner para entrada de datos
     * @param clienteDAO objeto DAO para acceder a los datos
     */
    private static void buscarClientePorId(Scanner consola, IClienteDAO clienteDAO){
        System.out.println("*** Buscar Cliente Por ID ***");
        System.out.print("Ingrese el ID del cliente: ");
        int id = Integer.parseInt(consola.nextLine());
        Cliente clientePorId = new Cliente(id);
        boolean encontrado = clienteDAO.buscarClientePorId(clientePorId);
        if (encontrado) {
            System.out.println("Cliente encontrado: " + clientePorId);
        } else {
            System.out.println("Cliente no encontrado con ID: " + clientePorId.getId());
        }
    }

    /**
     * Agrega un nuevo cliente al sistema
     * Solicita al usuario los datos necesarios y valida la entrada
     * @param consola objeto Scanner para entrada de datos
     * @param clienteDAO objeto DAO para operaciones de base de datos
     */
    private static void agregarCliente(Scanner consola, IClienteDAO clienteDAO){
        System.out.println("*** Agregar Cliente ***");
        try{
            // Solicitar datos del nuevo cliente
            System.out.print("Ingrese el nombre del cliente: ");
            String nombre = consola.nextLine();
            System.out.print("Ingrese el apellido del cliente: ");
            String apellido = consola.nextLine();
            System.out.print("Ingrese la membresia del cliente: ");
            int membresia = Integer.parseInt(consola.nextLine());
            
            // Crear y agregar el nuevo cliente
            Cliente clienteNuevo = new Cliente(nombre, apellido, membresia);
            boolean agregado = clienteDAO.agregarCliente(clienteNuevo);
            if (agregado){
                System.out.println("Cliente agregado: " + clienteNuevo);
            }else{
                System.out.println("Cliente no agregado: " + clienteNuevo);
            }
        } catch (Exception e) {
            System.out.println("Error al agregar el cliente: " + e.getMessage());
        }

    }

    /**
     * Modifica los datos de un cliente existente
     * Primero verifica que el cliente exista antes de permitir la modificación
     * @param clienteDAO objeto DAO para operaciones de base de datos
     * @param consola objeto Scanner para entrada de datos
     */
    private static void modificarCliente(IClienteDAO clienteDAO, Scanner consola){
        System.out.println("*** Modificar Cliente ***");
        try{
            // Solicitar ID del cliente a modificar
            System.out.print("Ingrese el ID del cliente a modificar: ");
            int id = Integer.parseInt(consola.nextLine());
            
            // Verificar que el cliente existe
            List<Cliente> clientes = clienteDAO.listarClientes();
            Cliente clienteEncontrado = null;
            for (Cliente cliente : clientes) {
                if (cliente.getId() == id) {
                    clienteEncontrado = cliente;
                    break;
                }
            }
            if (clienteEncontrado == null) {
                System.out.println("Cliente con ID " + id + " no encontrado");
                return;
            }
            
            // Solicitar nuevos datos del cliente
            System.out.print("Ingrese el nuevo nombre del cliente: ");
            String nombre = consola.nextLine();
            System.out.print("Ingrese el nuevo apellido del cliente: ");
            String apellido = consola.nextLine();
            System.out.print("Ingrese la nueva membresia del cliente: ");
            int membresia = Integer.parseInt(consola.nextLine());
            
            // Actualizar el cliente
            Cliente modificarCliente = new Cliente(id,nombre, apellido, membresia);
            boolean modificado = clienteDAO.modificarCliente(modificarCliente);
            if (modificado){
                System.out.println("Cliente modificado: " + modificarCliente);
            }else{
                System.out.println("Cliente no modificado: " + modificarCliente);
            }
        } catch (Exception e) {
            System.out.println("Error al modificar el cliente: " + e.getMessage());
        }
    }

    /**
     * Elimina un cliente del sistema
     * Verifica que el cliente exista antes de proceder con la eliminación
     * @param clienteDAO objeto DAO para operaciones de base de datos
     * @param consola objeto Scanner para entrada de datos
     */
    private static void eliminarCliente(IClienteDAO clienteDAO, Scanner consola) {
        System.out.println("*** Eliminar Cliente ***");
        System.out.print("Ingrese el ID del cliente a eliminar: ");
        int id = Integer.parseInt(consola.nextLine());
        
        // Verificar que el cliente existe antes de eliminar
        List<Cliente> clientes = clienteDAO.listarClientes();
        Cliente clienteEncontrado = null;
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                clienteEncontrado = cliente;
                break;
            }
        }
        if (clienteEncontrado == null) {
            System.out.println("Cliente con ID " + id + " no encontrado");
            return;
        }
        
        // Proceder con la eliminación
        boolean eliminado = clienteDAO.eliminarCliente(clienteEncontrado);
        if (eliminado) {
            System.out.println("Cliente eliminado: " + clienteEncontrado);
        } else {
            System.out.println("Cliente no eliminado: " + clienteEncontrado);
        }

    }
}
