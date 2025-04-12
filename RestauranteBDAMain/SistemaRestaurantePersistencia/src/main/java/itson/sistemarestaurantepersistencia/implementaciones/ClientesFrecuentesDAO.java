/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.sistemarestaurantepersistencia.implementaciones;

import itson.sistemarestaurantedominio.ClienteFrecuente;
import itson.sistemarestaurantedominio.dtos.NuevoClienteFrecuenteDTO;
import itson.sistemarestaurantedominio.dtos.ReporteClienteFrecuenteDTO;
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

    /**
     * Obtiene un reporte de clientes frecuentes filtrados por nombre y/o número mínimo de visitas.
     * Este método ejecuta una consulta JPQL para obtener los datos de los clientes frecuentes, 
     * incluyendo su nombre, apellido, número de comandas realizadas, total acumulado, fecha de la última comanda 
     * y puntos de fidelidad. Los resultados se agrupan por cliente y se filtran según el nombre y el número mínimo de visitas.
     * 
     * @param nombreFiltro Filtro opcional para buscar clientes por su nombre completo. Si es {@code null}, 
     *                     no se aplicará filtro por nombre.
     * @param minimoVisitas Filtro opcional para obtener solo los clientes con un número mínimo de visitas. 
     *                      Si es {@code null}, no se aplicará este filtro.
     * @return Una lista de objetos {@link ReporteClienteFrecuenteDTO} con la información de los clientes frecuentes 
     *         que cumplen con los filtros aplicados.
     * @throws Exception Si ocurre algún error durante la ejecución de la consulta.
     * @see ReporteClienteFrecuenteDTO
     */
    @Override
    public List<ReporteClienteFrecuenteDTO> obtenerReporteClientesFrecuentes(String nombreFiltro, Integer minimoVisitas) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        try {
            // Corregir la consulta JPQL
            String jpql = "SELECT new itson.sistemarestaurantedominio.dtos.ReporteClienteFrecuenteDTO("
                    + "c.nombre, c.apellidoPaterno, c.apellidoMaterno, "
                    + "COUNT(co), SUM(co.totalAcumulado), MAX(co.fechaCreacion), c.puntosFidelidad) "
                    + "FROM ClienteFrecuente c "
                    + "LEFT JOIN c.comandas co "
                    + "WHERE (:nombreFiltro IS NULL OR CONCAT(c.nombre, ' ', c.apellidoPaterno, ' ', c.apellidoMaterno) LIKE CONCAT('%', :nombreFiltro, '%')) "
                    + "GROUP BY c.id "
                    + "HAVING (:minimoVisitas IS NULL OR COUNT(co) >= :minimoVisitas)";

            TypedQuery<ReporteClienteFrecuenteDTO> query = entityManager.createQuery(jpql, ReporteClienteFrecuenteDTO.class);
            query.setParameter("nombreFiltro", nombreFiltro);
            query.setParameter("minimoVisitas", minimoVisitas);

            return query.getResultList();
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }


}
