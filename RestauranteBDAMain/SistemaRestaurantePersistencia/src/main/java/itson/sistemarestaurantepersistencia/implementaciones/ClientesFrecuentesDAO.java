/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.sistemarestaurantepersistencia.implementaciones;

import itson.sistemarestaurantedominio.ClienteFrecuente;
import itson.sistemarestaurantedominio.dtos.NuevoClienteFrecuenteDTO;
import itson.sistemarestaurantepersistencia.IClientesFrecuentesDAO;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;


/**
 *
 * @author rauln
 */
public class ClientesFrecuentesDAO implements IClientesFrecuentesDAO {

    /**
     * Registra un nuevo cliente frecuente en la base de datos.
     *
     * @param nuevoClienteFrecuenteDTO El DTO que contiene la información del
     * cliente a registrar. Se asume que los datos han sido previamente
     * validados y encriptados.
     * @return El cliente registrado, con la información persistida en la base
     * de datos.
     * @throws RuntimeException Si ocurre algún error durante la transacción con
     * la base de datos.
     */
    @Override
    public ClienteFrecuente registrar(NuevoClienteFrecuenteDTO nuevoClienteFrecuenteDTO) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        try {
            // Convertir el DTO a entidad ClienteFrecuente
            ClienteFrecuente cliente = new ClienteFrecuente();
            cliente.setNombre(nuevoClienteFrecuenteDTO.getNombre());
            cliente.setApellidoPaterno(nuevoClienteFrecuenteDTO.getApellidoPaterno());
            cliente.setApellidoMaterno(nuevoClienteFrecuenteDTO.getApellidoMaterno());
            cliente.setCorreo(nuevoClienteFrecuenteDTO.getCorreo());
            cliente.setTelefono(nuevoClienteFrecuenteDTO.getNumeroTelefono());
            cliente.setFechaRegistro(Calendar.getInstance());

            // Iniciar y confirmar transacción para registrar el cliente
            entityManager.getTransaction().begin();
            entityManager.persist(cliente);
            entityManager.getTransaction().commit();

            return cliente;
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    /**
     * Busca clientes frecuentes en la base de datos utilizando un filtro de
     * búsqueda.
     *
     * @param filtro El texto a buscar en los campos nombre, apellido, correo o
     * teléfono del cliente. El filtro no distingue entre mayúsculas y
     * minúsculas.
     * @return Una lista de clientes que coinciden con el filtro proporcionado.
     * @throws RuntimeException Si ocurre un error al ejecutar la consulta.
     */
    @Override
    public List<ClienteFrecuente> buscarClientes(String filtro) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        try {
            // Crear la consulta JPQL para buscar clientes por nombre, apellido, correo o teléfono
            TypedQuery<ClienteFrecuente> query = entityManager.createQuery(
                    "SELECT c FROM ClienteFrecuente c "
                    + "WHERE LOWER(c.nombre) LIKE :filtro "
                    + "OR LOWER(c.apellidoPaterno) LIKE :filtro "
                    + "OR LOWER(c.apellidoMaterno) LIKE :filtro "
                    + "OR LOWER(c.correo) LIKE :filtro "
                    + "OR c.telefono LIKE :filtro",
                    ClienteFrecuente.class
            );
            query.setParameter("filtro", "%" + filtro.toLowerCase() + "%");

            // Retornar la lista de clientes que coinciden con el filtro
            return query.getResultList();
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }




    /**
     * Obtiene todos los clientes frecuentes registrados en la base de datos.
     *
     * @return Una lista con todos los clientes frecuentes registrados.
     * @throws RuntimeException Si ocurre un error al ejecutar la consulta.
     */
    @Override
    public List<ClienteFrecuente> obtenerTodosLosClientes() {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        try {
            // Ejecutar la consulta JPQL para obtener todos los clientes
            return entityManager
                    .createQuery("SELECT c FROM ClienteFrecuente c", ClienteFrecuente.class)
                    .getResultList();
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

}
