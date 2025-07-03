package zona_fit.dominio;

import java.util.Objects;

/**
 * Clase entidad que representa un cliente del gimnasio.
 * Contiene la información básica del cliente y sus membresías.
 */
public class Cliente {
    // Atributos de la entidad Cliente
    private int id;          // Identificador único del cliente
    private String nombre;   // Nombre del cliente
    private String apellido; // Apellido del cliente
    private int membresia;   // Tipo de membresía del cliente

    // Constructor por defecto
    public Cliente(){}

    /**
     * Constructor para búsquedas por ID
     * @param id identificador del cliente
     */
    public Cliente(int id){
        this.id = id;
    }

    /**
     * Constructor para crear nuevos clientes (sin ID)
     * @param nombre nombre del cliente
     * @param apellido apellido del cliente
     * @param membresia tipo de membresía
     */
    public Cliente(String nombre, String apellido, int membresia){
        this.nombre = nombre;
        this.apellido = apellido;
        this.membresia = membresia;
    }

    /**
     * Constructor completo para clientes existentes
     * @param id identificador del cliente
     * @param nombre nombre del cliente
     * @param apellido apellido del cliente
     * @param membresia tipo de membresía
     */
    public Cliente(int id, String nombre, String apellido, int membresia){
        this(nombre, apellido, membresia);
        this.id = id;
    }

    // Métodos getter y setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getMembresia() {
        return membresia;
    }

    public void setMembresia(int membresia) {
        this.membresia = membresia;
    }

    /**
     * Representación en cadena del objeto Cliente
     * @return String con la información del cliente
     */
    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", membresia=" + membresia +
                '}';
    }

    /**
     * Compara dos objetos Cliente por igualdad
     * @param o objeto a comparar
     * @return true si son iguales, false en caso contrario
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return id == cliente.id && membresia == cliente.membresia && Objects.equals(nombre, cliente.nombre) && Objects.equals(apellido, cliente.apellido);
    }

    /**
     * Genera el código hash del objeto Cliente
     * @return código hash basado en todos los atributos
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, apellido, membresia);
    }
}
