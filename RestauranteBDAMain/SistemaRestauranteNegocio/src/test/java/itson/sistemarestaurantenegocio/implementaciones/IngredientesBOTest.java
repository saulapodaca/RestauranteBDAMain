/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package itson.sistemarestaurantenegocio.implementaciones;

import itson.sistemarestaurantedominio.Ingrediente;
import itson.sistemarestaurantedominio.UnidadMedidaIngrediente;
import itson.sistemarestaurantedominio.dtos.IngredienteRegistradoDTO;
import itson.sistemarestaurantedominio.dtos.NuevoIngredienteDTO;
import itson.sistemarestaurantenegocio.excepciones.IngredienteRegistradoException;
import itson.sistemarestaurantenegocio.excepciones.NombreInvalidoException;
import itson.sistemarestaurantenegocio.excepciones.StockInvalidoException;
import itson.sistemarestaurantepersistencia.IIngredientesDAO;
import itson.sistemarestaurantepersistencia.implementaciones.IngredientesDAO;
import itson.sistemarestaurantepersistencia.implementaciones.ManejadorConexiones;
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
public class IngredientesBOTest {
    
    public IngredientesBOTest() {
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
     * Test of registrar method, of class IngredientesBO.
     * @throws java.lang.Exception
     */
    @Test
    public void testRegistrarOk() throws Exception {
        //poner nombre de nuevo producto, lo demás lo dejo así para que sea más fácil y sin tanto cambio
        String nombre = "";
        IngredientesDAO ingredientesDAO = new IngredientesDAO();
        IngredientesBO ingredientesBO = new IngredientesBO(ingredientesDAO);
        NuevoIngredienteDTO nuevoIngredienteDTO = new NuevoIngredienteDTO(nombre, UnidadMedidaIngrediente.GRAMOS, 250);
        Ingrediente ingredienteGuardado = ingredientesBO.registrar(nuevoIngredienteDTO);
        assertNotNull(ingredienteGuardado.getId());
        assertEquals(nuevoIngredienteDTO.getNombre(), ingredienteGuardado.getNombre());
        assertEquals(nuevoIngredienteDTO.getUnidadMedidaIngrediente(), ingredienteGuardado.getUnidadMedida());
        assertEquals(nuevoIngredienteDTO.getStock(), ingredienteGuardado.getStock());
    }

        /**
     * Test of registrar method, of class IngredientesBO.
     * 
     * @throws itson.sistemarestaurantenegocio.excepciones.IngredienteRegistradoException
     * @throws itson.sistemarestaurantenegocio.excepciones.NombreInvalidoException
     * @throws itson.sistemarestaurantenegocio.excepciones.StockInvalidoException
     */
    @Test
    public void testRegistrarIngredienteRepetido() throws IngredienteRegistradoException, NombreInvalidoException, StockInvalidoException {
        IIngredientesDAO ingredientesDAO = new IngredientesDAO();
        IngredientesBO ingredientesBO = new IngredientesBO(ingredientesDAO);
        List<IngredienteRegistradoDTO> ingredientes = ingredientesDAO.obtenerInventarioIngredientes();
        NuevoIngredienteDTO nuevoIngredienteDTO = new NuevoIngredienteDTO(ingredientes.get(0).getNombre(), ingredientes.get(0).getUnidadMedidaIngrediente(), ingredientes.get(0).getStock());
        IngredienteRegistradoException thrown = assertThrows(IngredienteRegistradoException.class,() -> {ingredientesBO.registrar(nuevoIngredienteDTO);});
        assertEquals("El ingrediente ya se encuentra registrado", thrown.getMessage());
        
    }
    
    
    /**
     * Test of validarNombre method, of class IngredientesBO.
     */
    @Test
    public void testValidarNombre() throws Exception {
    }

    /**
     * Test of validarStock method, of class IngredientesBO.
     */
    @Test
    public void testValidarStock() throws Exception {
    }

    /**
     * Test of validarExistenciaInventario method, of class IngredientesBO.
     */
    @Test
    public void testValidarExistenciaInventario() throws Exception {
    }
    
}
