/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.sistemarestaurantepersistencia.implementaciones;

import itson.encriptacion.Desencriptador;
import itson.encriptacion.Encriptador;
import itson.sistemarestaurantedominio.ClienteFrecuente;
import itson.sistemarestaurantedominio.dtos.NuevoClienteFrecuenteDTO;
import itson.sistemarestaurantepersistencia.IClientesFrecuentesDAO;
import java.util.ArrayList;
import java.util.Calendar;
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

            // Validar si el teléfono ya está registrado SOLO SI NO ES NULL
            if (nuevoClienteFrecuenteDTO.getNumeroTelefono() != null) {
                if (existeTelefono(nuevoClienteFrecuenteDTO.getNumeroTelefono())) {
                    throw new IllegalArgumentException("El número de teléfono ingresado ya está registrado.");
                }
            }

            // Crear entidad ClienteFrecuente
            ClienteFrecuente cliente = new ClienteFrecuente();
            cliente.setNombre(nuevoClienteFrecuenteDTO.getNombre());
            cliente.setApellidoPaterno(nuevoClienteFrecuenteDTO.getApellidoPaterno());
            cliente.setApellidoMaterno(nuevoClienteFrecuenteDTO.getApellidoMaterno());

            // Encriptar datos sensibles antes de guardarlos
            cliente.setCorreo(Encriptador.encriptar(nuevoClienteFrecuenteDTO.getCorreo()));
            cliente.setTelefono(Encriptador.encriptar(nuevoClienteFrecuenteDTO.getNumeroTelefono()));

            Calendar fechaRegistro = Calendar.getInstance();
            cliente.setFechaRegistro(fechaRegistro);

            // Guardar en la base de datos
            entityManager.persist(cliente);
            entityManager.getTransaction().commit();

            return cliente;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Error al registrar cliente frecuente", e);
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
            String correoEncriptado = Encriptador.encriptar(correo); // Encriptar antes de consultar
            TypedQuery<Long> query = entityManager.createQuery(
                    "SELECT COUNT(c) FROM ClienteFrecuente c WHERE c.correo = :correo", Long.class
            );
            query.setParameter("correo", correoEncriptado);
            return query.getSingleResult() > 0;
        } catch (NoResultException e) {
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public boolean existeTelefono(String telefono) {
        if (telefono == null) {
            return false;
        }

        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        try {
            String telefonoEncriptado = Encriptador.encriptar(telefono); // Encriptar para buscarlo
            TypedQuery<Long> query = entityManager.createQuery(
                    "SELECT COUNT(c) FROM ClienteFrecuente c WHERE c.telefono = :telefono", Long.class
            );
            query.setParameter("telefono", telefonoEncriptado);
            return query.getSingleResult() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<ClienteFrecuente> buscarClientes(String filtro) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        List<ClienteFrecuente> clientesFiltrados = new ArrayList<>();

        try {
            // Obtener todos los clientes
            List<ClienteFrecuente> clientes = entityManager
                    .createQuery("SELECT c FROM ClienteFrecuente c", ClienteFrecuente.class)
                    .getResultList();

            for (ClienteFrecuente cliente : clientes) {
                try {
                    String nombre = cliente.getNombre() != null ? cliente.getNombre().toLowerCase() : "";
                    String apellidoPaterno = cliente.getApellidoPaterno() != null ? cliente.getApellidoPaterno().toLowerCase() : "";
                    String apellidoMaterno = cliente.getApellidoMaterno() != null ? cliente.getApellidoMaterno().toLowerCase() : "";

                    String correo = cliente.getCorreo() != null ? Desencriptador.desencriptar(cliente.getCorreo()).toLowerCase() : "";
                    String telefono = cliente.getTelefono() != null ? Desencriptador.desencriptar(cliente.getTelefono()) : "";

                    String filtroLower = filtro.toLowerCase();

                    if (nombre.contains(filtroLower)
                            || apellidoPaterno.contains(filtroLower)
                            || apellidoMaterno.contains(filtroLower)
                            || correo.contains(filtroLower)
                            || telefono.contains(filtroLower)) {
                        clientesFiltrados.add(cliente);
                    }
                } catch (Exception e) {
                    // Si falla la desencriptación de algún cliente, simplemente lo ignoramos.
                    System.err.println("Error al desencriptar cliente: " + e.getMessage());
                }
            }

        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }

        return clientesFiltrados;
    }

}
