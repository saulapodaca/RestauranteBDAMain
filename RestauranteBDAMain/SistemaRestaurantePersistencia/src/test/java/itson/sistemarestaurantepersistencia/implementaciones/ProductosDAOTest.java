/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package itson.sistemarestaurantepersistencia.implementaciones;

import itson.sistemarestaurantedominio.Producto;
import itson.sistemarestaurantedominio.TipoProducto;
import itson.sistemarestaurantedominio.dtos.NuevoProductoDTO;
import itson.sistemarestaurantedominio.dtos.ProductoRegistradoDTO;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author saula
 */
public class ProductosDAOTest {
    
    public ProductosDAOTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
        ManejadorConexiones.isTestMode = true;
    }

    @AfterAll
    public static void tearDownClass() {
        ManejadorConexiones.isTestMode = false;

    }

    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of registrar method, of class ProductosDAO.
     */
    @Test
    public void testRegistrar() {
        String nombre = "Sandwich de jamon";
        float precio = 220f;
        
        ProductosDAO productosDAO = new ProductosDAO();
        NuevoProductoDTO nuevoProducto = new NuevoProductoDTO(nombre, precio, TipoProducto.PLATILLO);
        Producto productoGuardado = productosDAO.registrar(nuevoProducto);
        assertNotNull(productoGuardado.getId());
        assertEquals(nuevoProducto.getNombre(), productoGuardado.getNombre());
        assertEquals(nuevoProducto.getTipoProducto(), productoGuardado.getTipoProducto());
        assertEquals(nuevoProducto.getPrecio(), productoGuardado.getPrecio());
    }

    /**
     * Test of buscarProductos method, of class ProductosDAO.
     */
    @Test
    public void testBuscarProductos() {
        String filtro = "queso";
        String tipo = "";
        ProductosDAO productosDAO = new ProductosDAO();
        List<ProductoRegistradoDTO> productos = productosDAO.buscarProductos(filtro, tipo);
        assertEquals(productos.size(), 1);
    }

    /**
     * Test of existenteProducto method, of class ProductosDAO.
     */
    @Test
    public void testExistenteProducto() {
        String nombre = "SANDWICH de queso";
        ProductosDAO productosDAO = new ProductosDAO();
        boolean existencia = productosDAO.existenteProducto(nombre, TipoProducto.POSTRE);
        assertEquals(existencia, false);
    }
    
}
