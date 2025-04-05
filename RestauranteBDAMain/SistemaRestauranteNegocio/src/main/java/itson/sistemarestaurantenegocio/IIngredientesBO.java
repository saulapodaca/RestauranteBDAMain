/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.sistemarestaurantenegocio;

import itson.sistemarestaurantedominio.Ingrediente;
import itson.sistemarestaurantedominio.dtos.NuevoIngredienteDTO;
import itson.sistemarestaurantenegocio.excepciones.IngredienteRegistradoException;
import itson.sistemarestaurantenegocio.excepciones.NombreInvalidoException;
import itson.sistemarestaurantenegocio.excepciones.StockInvalidoException;

/**
 *
 * @author saula
 */
public interface IIngredientesBO {
    public abstract Ingrediente registrar(NuevoIngredienteDTO nuevoIngredienteDTO) 
            throws NombreInvalidoException, StockInvalidoException, IngredienteRegistradoException;
    public abstract void validacionesIniciales(String nombre,String nombreDefault, String stock, String stockDefault) 
            throws NombreInvalidoException, StockInvalidoException;

}
