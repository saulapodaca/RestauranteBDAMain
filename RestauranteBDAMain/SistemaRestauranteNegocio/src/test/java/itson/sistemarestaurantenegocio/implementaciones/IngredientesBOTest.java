/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package itson.sistemarestaurantenegocio.implementaciones;

import itson.sistemarestaurantedominio.Ingrediente;
import itson.sistemarestaurantedominio.UnidadMedidaIngrediente;
import itson.sistemarestaurantedominio.dtos.IngredienteRegistradoDTO;
import itson.sistemarestaurantedominio.dtos.NuevoIngredienteDTO;
import itson.sistemarestaurantepersistencia.implementaciones.IngredientesDAO;
import itson.sistemarestaurantepersistencia.implementaciones.ManejadorConexiones;
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
public class IngredientesBOTest {
//    
//    public IngredientesBOTest() {
//    }
//    
//    @BeforeAll
//    public static void setUpClass() {
//        //TODO: cambio de base de datos a la de prueba
//    }
//    
//    @AfterAll
//    public static void tearDownClass() {
//    }
//    
//    @BeforeEach
//    public void setUp() {
//    }
//    
//    @AfterEach
//    public void tearDown() {
//    }
//
//    /**
//     * Test of registrar method, of class IngredientesBO.
//     */
//    @Test
//    public void testRegistrarOk() throws Exception {
//        IngredientesDAO ingredientesDAO = new IngredientesDAO();
//        NuevoIngredienteDTO nuevoIngredienteDTO = new NuevoIngredienteDTO("Queso", UnidadMedidaIngrediente.GRAMOS, 250.3f);
//        Ingrediente ingredienteGuardado = ingredientesDAO.registrar(nuevoIngredienteDTO);
//        assertNotNull(ingredienteGuardado.getId());
//        assertEquals(nuevoIngredienteDTO.getNombre(), ingredienteGuardado.getNombre());
//        assertEquals(nuevoIngredienteDTO.getUnidadMedidaIngrediente(), ingredienteGuardado.getUnidadMedida());
//        assertEquals(nuevoIngredienteDTO.getStock(), ingredienteGuardado.getStock());
//    }
//
//        /**
//     * Test of registrar method, of class IngredientesBO.
//     */
//    @Test
//    public void testRegistrarIngredienteRepetido() throws Exception {
//        IngredientesDAO ingredientesDAO = new IngredientesDAO();
//        NuevoIngredienteDTO nuevoIngredienteDTO = new NuevoIngredienteDTO("Queso", UnidadMedidaIngrediente.GRAMOS, 250.3f);
//        Ingrediente ingredienteGuardado = ingredientesDAO.registrar(nuevoIngredienteDTO);
//        
//        for (IngredienteRegistradoDTO ing: ingredientesDAO.obtenerInventarioIngredientes())
//            System.out.println(ing.getNombre() + "  " + "  " +ing.getUnidadMedidaIngrediente());
//        
//    }
//    
//    /**
//     * Test of validarNombre method, of class IngredientesBO.
//     */
//    @Test
//    public void testValidarNombre() throws Exception {
//    }
//
//    /**
//     * Test of validarStock method, of class IngredientesBO.
//     */
//    @Test
//    public void testValidarStock() throws Exception {
//    }
//
//    /**
//     * Test of validarExistenciaInventario method, of class IngredientesBO.
//     */
//    @Test
//    public void testValidarExistenciaInventario() throws Exception {
//    }
//    
}
