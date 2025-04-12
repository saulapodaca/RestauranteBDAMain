package itson.sistemarestaurantepersistencia.implementaciones;

import itson.sistemarestaurantedominio.ClienteFrecuente;
import itson.sistemarestaurantedominio.dtos.NuevoClienteFrecuenteDTO;
import itson.sistemarestaurantepersistencia.IClientesFrecuentesDAO;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

/**
 * Test de la clase ClientesFrecuentesDAO. Este conjunto de pruebas verifica los
 * métodos de la clase de persistencia, que gestionan la creación, búsqueda y
 * validación de los clientes frecuentes.
 */
public class ClientesFrecuentesDAOTest {

    private static IClientesFrecuentesDAO clientesFrecuentesDAO;

    @BeforeAll
    public static void setUpClass() {
        // Inicialización antes de ejecutar los tests
        ManejadorConexiones.isTestMode = true;
        clientesFrecuentesDAO = new ClientesFrecuentesDAO();
    }

    @AfterAll
    public static void tearDownClass() {
        // Limpieza después de ejecutar todos los tests
        ManejadorConexiones.isTestMode = false;
    }

    /**
     * Test para el registro de un cliente frecuente. Verifica que un cliente se
     * registre correctamente.
     */
    @Test
    public void testRegistrarClienteOk() {
        // Crear DTO con datos válidos
        String nombre = "Carlos";
        String apellidoP = "Moreno";
        String apellidoM = "Lopez";
        String correo = "ari@gmail.com";
        String telefono = "1451451451";
        NuevoClienteFrecuenteDTO nuevoCliente = new NuevoClienteFrecuenteDTO(nombre, apellidoP, apellidoM, correo, telefono);
        // Ejecutar método
        ClienteFrecuente clienteRegistrado = clientesFrecuentesDAO.registrar(nuevoCliente);

        // Verificaciones
        assertNotNull(clienteRegistrado.getId());
        assertEquals(nuevoCliente.getNombre(), clienteRegistrado.getNombre());
        assertEquals(nuevoCliente.getApellidoPaterno(), clienteRegistrado.getApellidoPaterno());
        assertEquals(nuevoCliente.getApellidoMaterno(), clienteRegistrado.getApellidoMaterno());
    }
    @Test
    public void testRegistrarClienteCorreoNull() {
        // Crear DTO con datos válidos
        String nombre = "Carlos";
        String apellidoP = "Moreno";
        String apellidoM = "Lopez";
        String correo = null;
        String telefono = "1111111111";
        NuevoClienteFrecuenteDTO nuevoCliente = new NuevoClienteFrecuenteDTO(nombre, apellidoP, apellidoM, correo, telefono);
        // Ejecutar método
        ClienteFrecuente clienteRegistrado = clientesFrecuentesDAO.registrar(nuevoCliente);

        // Verificaciones
        assertNotNull(clienteRegistrado.getId());
        assertEquals(nuevoCliente.getNombre(), clienteRegistrado.getNombre());
        assertEquals(nuevoCliente.getApellidoPaterno(), clienteRegistrado.getApellidoPaterno());
        assertEquals(nuevoCliente.getApellidoMaterno(), clienteRegistrado.getApellidoMaterno());
    }
    
    @Test
    public void testObtenerTodosLosClientes(){
        ClientesFrecuentesDAO dao = new ClientesFrecuentesDAO();
        List listaClientes = dao.obtenerTodosLosClientes();
        assertNotNull(listaClientes);
    }
    
    @Test
    public void testObtenerReporteClientesFrecuentes(){
        ClientesFrecuentesDAO dao = new ClientesFrecuentesDAO();
        List listaClientesReporte = dao.obtenerReporteClientesFrecuentes("Carlos", 3);
        System.out.println(listaClientesReporte);
        assertNotNull(listaClientesReporte);
    }



}
