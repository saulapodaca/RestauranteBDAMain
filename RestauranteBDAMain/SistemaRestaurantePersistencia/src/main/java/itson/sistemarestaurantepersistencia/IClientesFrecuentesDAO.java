/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.sistemarestaurantepersistencia;

import itson.sistemarestaurantedominio.ClienteFrecuente;
import itson.sistemarestaurantedominio.dtos.NuevoClienteFrecuenteDTO;
import itson.sistemarestaurantedominio.dtos.ReporteClienteFrecuenteDTO;
import java.util.List;

/**
 *
 * @author rauln
 */
public interface IClientesFrecuentesDAO {
    public abstract ClienteFrecuente registrar(NuevoClienteFrecuenteDTO nuevoClienteFrecuenteDTO);
    public abstract List<ClienteFrecuente> obtenerTodosLosClientes();
    public abstract List<ReporteClienteFrecuenteDTO> obtenerReporteClientesFrecuentes(String nombreFiltro, Integer minimoVisitas);
    
    
    
}
