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
<<<<<<< HEAD
/**
 *
 * @author Felix isaac sanchez Quintero
 */
public interface IProductosDAO  {
    Producto registrar(NuevoProductoDTO nuevoProductoDTO);
    List<Producto> buscarProductos(String filtro);
    boolean existenteProducto(String nombre, TipoProducto tipoProducto);
    List<Producto> cargaInicial();
    
=======

public interface IProductosDAO {

    /**
     * Registra un nuevo producto en la base de datos.
     *
     * @param nuevoProductoDTO Objeto con la información del producto a registrar.
     * @return El producto registrado con su ID generado.
     */
    public abstract Producto registrar(NuevoProductoDTO nuevoProductoDTO);

    /**
     * Busca productos que coincidan con el nombre y/o tipo proporcionados.
     * Los filtros son opcionales, por lo que si alguno es null, no se considera.
     *
     * @param nombre       Parte del nombre del producto a buscar (puede ser null).
     * @param tipoProducto Tipo de producto a buscar (puede ser null).
     * @return Lista de productos que cumplen con los filtros.
     */
    public abstract List<ProductoRegistradoDTO> buscarProductos(String nombre, String tipoProducto);

    /**
     * Verifica si ya existe un producto con un nombre y tipo determinados.
     *
     * @param nombre Nombre del producto a buscar.
     * @param tipo   Tipo del producto a buscar.
     * @return true si existe un producto con ese nombre y tipo, false en caso contrario.
     */
    public abstract boolean existenteProducto(String nombre, TipoProducto tipo);

    /**
     * Actualiza los datos de un producto existente. Solo se modifican los campos no nulos.
     *
     * @param productoActualizadoDTO DTO con la información a actualizar.
     * @return El producto actualizado.
     */
    public abstract Producto actualizarProducto(ProductoActualizadoDTO productoActualizadoDTO);

    /**
     * Recupera un producto por su ID.
     *
     * @param id ID del producto a buscar.
     * @return El producto correspondiente, o null si no se encuentra.
     */
    public abstract Producto obtenerProducto(Long id);

    //
>>>>>>> master
}
