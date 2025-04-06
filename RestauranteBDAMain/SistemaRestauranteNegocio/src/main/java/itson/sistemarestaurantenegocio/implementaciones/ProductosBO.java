/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.sistemarestaurantenegocio.implementaciones;

import itson.sistemarestaurantedominio.Producto;
import itson.sistemarestaurantedominio.TipoProducto;
import itson.sistemarestaurantedominio.dtos.NuevoProductoDTO;
import itson.sistemarestaurantenegocio.IProductosBO;
import itson.sistemarestaurantepersistencia.IProductosDAO;
import itson.sistemarestaurantepersistencia.implementaciones.ProductosDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author felix isaac sanchez q
 */
public class ProductosBO implements IProductosBO{
 private static final String DEFUALT_NOMBRE = "PRODUCTO SIN NOMBRE";
 private static final float DEFUALT_PRECIO = 0.0f; 
 //public static final TipoProducto DEFAULT_TIPO = null ;

 private ProductosDAO productosDAO;

    public ProductosBO(ProductosDAO productosDAO) {
        this.productosDAO = productosDAO;
    }
 
 public Producto registrarProducto(NuevoProductoDTO nuevoProductoDTO){
     if (esTextoPorDefecto(nuevoProductoDTO.getNombre(), DEFUALT_NOMBRE)) {
         throw new IllegalArgumentException("Error: El nombre del producto debe completarse correctamente");
     }
     
     if (nuevoProductoDTO.getPrecio() <= 0 || nuevoProductoDTO.getPrecio() == DEFUALT_PRECIO) {
         throw new IllegalArgumentException("Error: El precio debe ser un valor valido mayor a cero.");
     }
     if (nuevoProductoDTO.getTipoProducto() == null) {
         throw new IllegalArgumentException ("Error: El tipo de producto debe especificarse correctamente .");
     }
     
     if (!validarNombreProducto(nuevoProductoDTO.getNombre())) {
         throw new IllegalArgumentException("Error: El nombre del producto solo puede contener letras, n");
     }
     
     NuevoProductoDTO productoValido = new NuevoProductoDTO(
             nuevoProductoDTO.getNombre(), 
             nuevoProductoDTO.getPrecio(), 
             nuevoProductoDTO.getTipoProducto());
     
     return productosDAO.registrar(productoValido);
     
 }
 
 private boolean esTextoPorDefecto(String texto, String placeholder){
     return texto.isEmpty() || texto.equals(placeholder);
 }
 
 private boolean validarNombreProducto(String nombre){
     return nombre.matches("^[a-zA-Z0-9 ]+$");
 }
    
 public List<Producto> buscarProductos(String filtro){
     if (filtro == null || filtro.trim().isEmpty()) {
         return new ArrayList<>();
     }
     return productosDAO.buscarProductos(filtro.trim());
 
 }
 
}
