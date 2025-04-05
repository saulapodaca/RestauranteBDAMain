/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.sistemarestaurantepersistencia.implementaciones;

import itson.sistemarestaurantedominio.ClienteFrecuente;
import itson.sistemarestaurantedominio.dtos.NuevoClienteFrecuenteDTO;
import itson.sistemarestaurantepersistencia.IClientesFrecuentesDAO;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author rauln
 */
public class ClientesFrecuentesDAO implements IClientesFrecuentesDAO {

    @Override
    public ClienteFrecuente registrar(NuevoClienteFrecuenteDTO nuevoClienteFrecuenteDTO) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();

        try {
            entityManager.getTransaction().begin();

            // Validar si el correo ya está registrado SOLO SI NO ES NULL
            if (nuevoClienteFrecuenteDTO.getCorreo() != null) {
                if (existeCorreo(nuevoClienteFrecuenteDTO.getCorreo())) {
                    throw new IllegalArgumentException("El correo ingresado ya está registrado.");
                }
            }

            // Validar si el teléfono ya está registrado
            if (existeTelefono(nuevoClienteFrecuenteDTO.getNumeroTelefono())) {
                throw new IllegalArgumentException("El número de teléfono ingresado ya está registrado.");
            }

            // Crear entidad ClienteFrecuente
            ClienteFrecuente cliente = new ClienteFrecuente();
            cliente.setNombre(nuevoClienteFrecuenteDTO.getNombre());
            cliente.setApellidoPaterno(nuevoClienteFrecuenteDTO.getApellidoPaterno());
            cliente.setApellidoMaterno(nuevoClienteFrecuenteDTO.getApellidoMaterno());
            cliente.setCorreo(nuevoClienteFrecuenteDTO.getCorreo());
            cliente.setTelefono(nuevoClienteFrecuenteDTO.getNumeroTelefono());
            Calendar fechaRegistro = Calendar.getInstance();
            cliente.setFechaRegistro(fechaRegistro);

            // Guardar en la base de datos
            entityManager.persist(cliente);
            entityManager.getTransaction().commit();

            return cliente;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public boolean existeCorreo(String correo) {
        if (correo == null) {
            return false; // Si es NULL, no validamos unicidad
        }

        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        try {
            TypedQuery<Long> query = entityManager.createQuery(
                    "SELECT COUNT(c) FROM ClienteFrecuente c WHERE c.correo = :correo", Long.class
            );
            query.setParameter("correo", correo);
            return query.getSingleResult() > 0;
        } catch (NoResultException e) {
            return false;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public boolean existeTelefono(String telefono) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        try {
            TypedQuery<Long> query = entityManager.createQuery(
                    "SELECT COUNT(c) FROM ClienteFrecuente c WHERE c.telefono = :telefono", Long.class
            );
            query.setParameter("telefono", telefono);
            return query.getSingleResult() > 0;
        } catch (NoResultException e) {
            return false;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<ClienteFrecuente> buscarClientes(String filtro) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        List<ClienteFrecuente> clientes = new ArrayList<>();

        try {
            String jpql = "SELECT c FROM ClienteFrecuente c WHERE "
                    + "LOWER(c.nombre) LIKE LOWER(:filtro) OR "
                    + "LOWER(c.apellidoPaterno) LIKE LOWER(:filtro) OR "
                    + "LOWER(c.apellidoMaterno) LIKE LOWER(:filtro) OR "
                    + "LOWER(c.correo) LIKE LOWER(:filtro) OR "
                    + "c.telefono LIKE :filtro";

            clientes = entityManager.createQuery(jpql, ClienteFrecuente.class)
                    .setParameter("filtro", "%" + filtro + "%")
                    .getResultList();
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
        System.out.println(clientes);
        return clientes;
    }

}
