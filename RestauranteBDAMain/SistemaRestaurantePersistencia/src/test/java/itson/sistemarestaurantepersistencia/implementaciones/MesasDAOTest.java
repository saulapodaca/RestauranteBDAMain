/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package itson.sistemarestaurantepersistencia.implementaciones;

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
public class MesasDAOTest {
    
    public MesasDAOTest() {
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
     * Test of registrar method, of class MesasDAO.
     */
    @Test
    public void testRegistrar() {
    }

    /**
     * Test of cambiarEstado method, of class MesasDAO.
     */
    @Test
    public void testCambiarEstado() {
    }

    /**
     * Test of obtenerMesa method, of class MesasDAO.
     */
    @Test
    public void testObtenerMesa() {
    }

    /**
     * Test of existeMesa method, of class MesasDAO.
     */
    @Test
    public void testExisteMesa() {
    }

    /**
     * Test of insertarMesasIniciales method, of class MesasDAO.
     */
    @Test
    public void testInsertarMesasIniciales() {
        MesasDAO mesasDAO = new MesasDAO();
        mesasDAO.insertarMesasIniciales();
    }
    
}
