/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.sistemarestaurantepersistencia;

import itson.sistemarestaurantedominio.ProductoIngrediente;
import itson.sistemarestaurantedominio.dtos.NuevaRelacionProductoIngredienteDTO;
import itson.sistemarestaurantedominio.dtos.ProductoRegistradoDTO;
import itson.sistemarestaurantedominio.dtos.RelacionProductoIngredienteRegistradaDTO;
import itson.sistemarestaurantepersistencia.excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author saula
 */
public interface IProductosIngredientesDAO {

    public ProductoIngrediente registrar(NuevaRelacionProductoIngredienteDTO relacionProductoIngrediente)
            throws PersistenciaException;

    public void eliminarRelacion(RelacionProductoIngredienteRegistradaDTO relacionProductoIngrediente);

    public boolean existeRelacion(Long idProducto, Long idIngrediente);
    
    public List<ProductoIngrediente> obtenerListaIngredientesDeProducto(ProductoRegistradoDTO producto);

}
