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
 * Test de la clase ClientesFrecuentesDAO.
 * Este conjunto de pruebas verifica los métodos de la clase de persistencia,
 * que gestionan la creación, búsqueda y validación de los clientes frecuentes.
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
     * Test para el registro de un cliente frecuente.
     * Verifica que un cliente se registre correctamente.
     */
    @Test
    public void testRegistrarClienteFrecuente() {
        NuevoClienteFrecuenteDTO nuevoClienteFrecuenteDTO = new NuevoClienteFrecuenteDTO(
                "Juan", "Pérez", "Lopez", "juan.perez@example.com", "1234567890"
        );

        ClienteFrecuente clienteGuardado = clientesFrecuentesDAO.registrar(nuevoClienteFrecuenteDTO);

        // Verifica que el cliente se haya guardado correctamente
        assertNotNull(clienteGuardado.getId());
        assertEquals(nuevoClienteFrecuenteDTO.getNombre(), clienteGuardado.getNombre());
        assertEquals(nuevoClienteFrecuenteDTO.getApellidoPaterno(), clienteGuardado.getApellidoPaterno());
        assertEquals(nuevoClienteFrecuenteDTO.getApellidoMaterno(), clienteGuardado.getApellidoMaterno());
        assertEquals(nuevoClienteFrecuenteDTO.getCorreo(), clienteGuardado.getCorreo());
        assertEquals(nuevoClienteFrecuenteDTO.getNumeroTelefono(), clienteGuardado.getTelefono());
    }

    /**
     * Test para verificar que no se registren correos duplicados.
     * Verifica que se lance una excepción si se intenta registrar un correo ya existente.
     */
    @Test
    public void testRegistrarClienteConCorreoDuplicado() {
        NuevoClienteFrecuenteDTO cliente1DTO = new NuevoClienteFrecuenteDTO(
                "Juan", "Pérez", "Lopez", "juan.perez@example.com", "1234567890"
        );
        clientesFrecuentesDAO.registrar(cliente1DTO);

        NuevoClienteFrecuenteDTO cliente2DTO = new NuevoClienteFrecuenteDTO(
                "Ana", "Martínez", "Gonzalez", "juan.perez@example.com", "0987654321"
        );

        // Verifica que se lance una excepción cuando se intente registrar un correo duplicado
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            clientesFrecuentesDAO.registrar(cliente2DTO);
        });
        assertEquals("El correo ingresado ya está registrado.", thrown.getMessage());
    }

    /**
     * Test para verificar que no se registren teléfonos duplicados.
     * Verifica que se lance una excepción si se intenta registrar un número de teléfono ya existente.
     */
    @Test
    public void testRegistrarClienteConTelefonoDuplicado() {
        NuevoClienteFrecuenteDTO cliente1DTO = new NuevoClienteFrecuenteDTO(
                "Juan", "Pérez", "Lopez", "juan.perez@example.com", "1234567890"
        );
        clientesFrecuentesDAO.registrar(cliente1DTO);

        NuevoClienteFrecuenteDTO cliente2DTO = new NuevoClienteFrecuenteDTO(
                "Ana", "Martínez", "Gonzalez", "ana.martinez@example.com", "1234567890"
        );

        // Verifica que se lance una excepción cuando se intente registrar un teléfono duplicado
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            clientesFrecuentesDAO.registrar(cliente2DTO);
        });
        assertEquals("El número de teléfono ingresado ya está registrado.", thrown.getMessage());
    }

    /**
     * Test para verificar que la búsqueda de clientes funcione correctamente.
     * Verifica que los clientes puedan ser encontrados con un filtro.
     */
    @Test
    public void testBuscarClientes() {
        NuevoClienteFrecuenteDTO cliente1DTO = new NuevoClienteFrecuenteDTO(
                "Juan", "Pérez", "Lopez", "juan.perez@example.com", "1234567890"
        );
        clientesFrecuentesDAO.registrar(cliente1DTO);

        NuevoClienteFrecuenteDTO cliente2DTO = new NuevoClienteFrecuenteDTO(
                "Ana", "Martínez", "Gonzalez", "ana.martinez@example.com", "0987654321"
        );
        clientesFrecuentesDAO.registrar(cliente2DTO);

        // Busca clientes cuyo nombre contiene "Juan"
        List<ClienteFrecuente> clientesEncontrados = clientesFrecuentesDAO.buscarClientes("Juan");

        // Verifica que se encuentren los clientes con el filtro aplicado
        assertTrue(clientesEncontrados.size() > 0);
        assertTrue(clientesEncontrados.get(0).getNombre().contains("Juan"));
    }
}
