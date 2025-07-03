package zona_fit.presentacion;

import zona_fit.datos.ClienteDAO;
import zona_fit.datos.IClienteDAO;
import zona_fit.dominio.Cliente;

import java.util.List;
import java.util.Scanner;

public class ZonaFitApp {
    public static void main(String[] args) {
        zonaFitApp();
    }

    public static void zonaFitApp(){
        boolean salir = false;
        Scanner consola = new Scanner(System.in);
        IClienteDAO clienteDAO = new ClienteDAO();
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

    private static boolean ejecutarOpcion(Scanner consola, int opcion, IClienteDAO clienteDAO) {
        boolean salir = false;
        switch (opcion){
            case 1 -> listarClientes(clienteDAO);
            case 2 -> buscarClientePorId(consola);
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

    private static void listarClientes(IClienteDAO clienteDAO){
        System.out.println("*** Listado Clientes ***");
        List<Cliente> clientes = clienteDAO.listarClientes();
        clientes.forEach(System.out::println);
    }

    private static void buscarClientePorId(Scanner consola){
        IClienteDAO clienteDAO = new ClienteDAO();
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

    private static void agregarCliente(Scanner consola, IClienteDAO clienteDAO){
        System.out.println("*** Agregar Cliente ***");
        try{
            System.out.print("Ingrese el nombre del cliente: ");
            String nombre = consola.nextLine();
            System.out.print("Ingrese el apellido del cliente: ");
            String apellido = consola.nextLine();
            System.out.print("Ingrese la membresia del cliente: ");
            int membresia = Integer.parseInt(consola.nextLine());
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

    private static void modificarCliente(IClienteDAO clienteDAO, Scanner consola){
        System.out.println("*** Modificar Cliente ***");
        try{
            System.out.print("Ingrese el ID del cliente a modificar: ");
            int id = Integer.parseInt(consola.nextLine());
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
            System.out.print("Ingrese el nuevo nombre del cliente: ");
            String nombre = consola.nextLine();
            System.out.print("Ingrese el nuevo apellido del cliente: ");
            String apellido = consola.nextLine();
            System.out.print("Ingrese la nueva membresia del cliente: ");
            int membresia = Integer.parseInt(consola.nextLine());
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

    private static void eliminarCliente(IClienteDAO clienteDAO, Scanner consola) {
        System.out.println("*** Eliminar Cliente ***");
        System.out.print("Ingrese el ID del cliente a eliminar: ");
        int id = Integer.parseInt(consola.nextLine());
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
        boolean eliminado = clienteDAO.eliminarCliente(clienteEncontrado);
        if (eliminado) {
            System.out.println("Cliente eliminado: " + clienteEncontrado);
        } else {
            System.out.println("Cliente no eliminado: " + clienteEncontrado);
        }

    }
}
