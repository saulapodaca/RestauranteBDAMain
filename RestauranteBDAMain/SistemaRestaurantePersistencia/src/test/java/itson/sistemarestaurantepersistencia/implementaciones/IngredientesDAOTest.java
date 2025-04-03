/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package itson.sistemarestaurantepersistencia.implementaciones;

import itson.sistemarestaurantedominio.Ingrediente;
import itson.sistemarestaurantedominio.UnidadMedidaIngrediente;
import itson.sistemarestaurantedominio.dtos.NuevoIngredienteDTO;
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
public class IngredientesDAOTest {
    
    public IngredientesDAOTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of registrar method, of class IngredientesDAO.
     */
    @Test
    public void testRegistrarOk() {
        IngredientesDAO ingredientesDAO = new IngredientesDAO();
        NuevoIngredienteDTO nuevoIngredienteDTO = new NuevoIngredienteDTO("Queso", UnidadMedidaIngrediente.PIEZAS, 2);
        Ingrediente ingredienteGuardado = ingredientesDAO.registrar(nuevoIngredienteDTO);
        assertNotNull(ingredienteGuardado.getId());
        assertEquals(nuevoIngredienteDTO.getNombre(), ingredienteGuardado.getNombre());
        assertEquals(nuevoIngredienteDTO.getUnidadMedidaIngrediente(), ingredienteGuardado.getUnidadMedida());
        assertEquals(nuevoIngredienteDTO.getStock(), ingredienteGuardado.getStock());
    }

    /**
     * Test of obtenerInventarioIngredientes method, of class IngredientesDAO.
     */
    @Test
    public void testObtenerInventarioIngredientes() {
        IngredientesDAO ingredientesDAO = new IngredientesDAO();
        System.out.println(ingredientesDAO.obtenerInventarioIngredientes());
    }
    
}
