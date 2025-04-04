/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.sistemarestaurantepersistencia;

import itson.sistemarestaurantedominio.ClienteFrecuente;
import itson.sistemarestaurantedominio.dtos.NuevoClienteFrecuenteDTO;
import java.util.List;

/**
 *
 * @author rauln
 */
public interface IClientesFrecuentesDAO {
    public abstract ClienteFrecuente registrar(NuevoClienteFrecuenteDTO nuevoClienteFrecuenteDTO);
    public abstract boolean existeCorreo(String correo);
    public abstract boolean existeTelefono(String telefono);
    public abstract List<ClienteFrecuente> buscarClientes(String query);
    
    
}
