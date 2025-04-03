/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.sistemarestaurantepersistencia;

import itson.sistemarestaurantedominio.Ingrediente;
import itson.sistemarestaurantedominio.dtos.NuevoIngredienteDTO;
import itson.sistemarestaurantedominio.dtos.IngredienteRegistradoDTO;
import java.util.List;

/**
 *
 * @author saula
 */
public interface IIngredientesDAO {
    public abstract Ingrediente registrar(NuevoIngredienteDTO nuevoIngredienteDTO);
    public abstract List<IngredienteRegistradoDTO> obtenerInventarioIngredientes();
}
