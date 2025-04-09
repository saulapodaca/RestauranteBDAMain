/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.sistemarestaurantepersistencia;

import itson.sistemarestaurantedominio.Ingrediente;
import itson.sistemarestaurantedominio.UnidadMedidaIngrediente;
import itson.sistemarestaurantedominio.dtos.NuevoIngredienteDTO;
import itson.sistemarestaurantedominio.dtos.IngredienteRegistradoDTO;
import itson.sistemarestaurantedominio.dtos.StockIngredienteActualizadoDTO;
import java.util.List;

/**
 *
 * @author saula
 */
public interface IIngredientesDAO {
    
    /**
     * Registra un ingrediente en el sistema
     * @param nuevoIngredienteDTO contiene la información del ingrediente que se registrará
     * @return el ingrediente con la información con la que se guardó
     */
    public abstract Ingrediente registrar(NuevoIngredienteDTO nuevoIngredienteDTO);
    
    /**
     * verifica si el ingrediente a registrar ya existe en el sistema
     * @param nombre del ingrediente
     * @param unidadMedida del ingrediente
     * @return true si el ingrediente existe en el sistema, false de lo contrario
     */
    public abstract boolean existeIngrediente(String nombre, UnidadMedidaIngrediente unidadMedida);
    
    /**
     * Busca los ingredientes registrados en el sistema por medio de su nombre y unidad de medida
     * @param nombre se refiere al nombre por el que se filtrará
     * @param unidadMedida a la unidad de medida que tendrán los ingredientes a buscar
     * @return una lista de los ingredientes registrados dto que cumplan con los criterios
     */
    public abstract List<IngredienteRegistradoDTO> buscarIngredientes(String nombre, String unidadMedida);
    
    public abstract Ingrediente actualizarStock(StockIngredienteActualizadoDTO ingrediente);

}
