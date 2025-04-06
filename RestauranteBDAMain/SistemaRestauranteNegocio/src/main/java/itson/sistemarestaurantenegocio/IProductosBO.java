/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.sistemarestaurantenegocio;

import itson.sistemarestaurantedominio.Producto;
import itson.sistemarestaurantedominio.dtos.NuevoProductoDTO;
import java.util.List;

/**
 *
 * @author Felix isaac sanchez Quintero
 */
public interface IProductosBO {
  Producto registrarProducto(NuevoProductoDTO nuevoProductoDTO);
  List<Producto> buscarProductos(String filtro);
}
