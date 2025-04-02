/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.sistemarestaurantepersistencia.implementaciones;

import itson.sistemarestaurantedominio.ClienteFrecuente;
import itson.sistemarestaurantedominio.dtos.NuevoClienteFrecuenteDTO;
import itson.sistemarestaurantepersistencia.IClientesFrecuentesDAO;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.EntityManager;

/**
 *
 * @author rauln
 */
public class ClientesFrecuentesDAO implements IClientesFrecuentesDAO{

    @Override
    public ClienteFrecuente registrar(NuevoClienteFrecuenteDTO nuevoClienteFrecuenteDTO) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager(false);
        entityManager.getTransaction().begin();
        ClienteFrecuente clienteFrecuente = new ClienteFrecuente();
        clienteFrecuente.setNombre(nuevoClienteFrecuenteDTO.getNombre());
        clienteFrecuente.setApellidoPaterno(nuevoClienteFrecuenteDTO.getApellidoPaterno());
        clienteFrecuente.setApellidoMaterno(nuevoClienteFrecuenteDTO.getApellidoMaterno());
        clienteFrecuente.setCorreo(nuevoClienteFrecuenteDTO.getCorreo());
        clienteFrecuente.setTelefono(nuevoClienteFrecuenteDTO.getNumeroTelefono());
        Calendar fechaRegistro = Calendar.getInstance();
        clienteFrecuente.setFechaRegistro(fechaRegistro);
        
        entityManager.persist(clienteFrecuente);
        entityManager.getTransaction().commit();
        return clienteFrecuente;
    }
    
    
}
