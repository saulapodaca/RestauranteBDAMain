/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.sistemarestaurantepersistencia;

import itson.sistemarestaurantedominio.Producto;
import itson.sistemarestaurantedominio.TipoProducto;
import itson.sistemarestaurantedominio.dtos.NuevoProductoDTO;
import itson.sistemarestaurantedominio.dtos.ProductoActualizadoDTO;
import itson.sistemarestaurantedominio.dtos.ProductoRegistradoDTO;
import java.util.List;

public interface IProductosDAO {

    public Producto registrar(NuevoProductoDTO nuevoProductoDTO);

    public List<ProductoRegistradoDTO> buscarProductos(String nombre, String tipoProducto);

    public boolean existenteProducto(String nombre, TipoProducto tipo);

    public Producto actualizarProducto(ProductoActualizadoDTO productoActualizadoDTO);

    public Producto obtenerProducto(Long id);

}
