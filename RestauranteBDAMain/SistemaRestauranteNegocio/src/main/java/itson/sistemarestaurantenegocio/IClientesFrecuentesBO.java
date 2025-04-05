/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.sistemarestaurantenegocio;

import itson.sistemarestaurantedominio.ClienteFrecuente;
import itson.sistemarestaurantedominio.dtos.NuevoClienteFrecuenteDTO;
import java.util.List;

/**
 *
 * @author rauln
 */
public interface IClientesFrecuentesBO {
    public abstract ClienteFrecuente registrarClienteFrecuente(NuevoClienteFrecuenteDTO nuevoClienteFrecuenteDTO);
    public abstract List<ClienteFrecuente> buscarClientes(String filtro);
}
