/*
 * Test de la clase ClientesFrecuentesBO.
 * Se realizan pruebas sobre los métodos de la clase ClientesFrecuentesBO para garantizar que el comportamiento de la lógica de negocio
 * sea el esperado bajo diferentes condiciones.
 */
package itson.sistemarestaurantenegocio.implementaciones;

import itson.sistemarestaurantedominio.ClienteFrecuente;
import itson.sistemarestaurantedominio.dtos.NuevoClienteFrecuenteDTO;
import itson.sistemarestaurantenegocio.excepciones.FormatoInvalidoException;
import itson.sistemarestaurantenegocio.excepciones.TelefonoInvalidoException;
import itson.sistemarestaurantepersistencia.IClientesFrecuentesDAO;
import itson.sistemarestaurantenegocio.excepciones.CampoObligatorioException;
import itson.sistemarestaurantepersistencia.implementaciones.ClientesFrecuentesDAO;
import itson.sistemarestaurantepersistencia.implementaciones.ManejadorConexiones;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test de la clase ClientesFrecuentesBO.
 * Este conjunto de pruebas verifica los métodos relacionados con la creación y búsqueda de clientes frecuentes,
 * así como las validaciones aplicadas durante el proceso.
 */
public class ClientesFrecuentesBOTest {

    public ClientesFrecuentesBOTest() {
        // Constructor de la clase de test (por convención, se deja vacío si no se necesita inicializar nada específico).
    }

    // Configuración que se ejecuta antes de todos los tests.
    @BeforeAll
    public static void setUpClass() {
        // Activa el modo de prueba para gestionar conexiones a la base de datos de forma controlada.
        ManejadorConexiones.isTestMode = true;
    }

    // Configuración que se ejecuta después de todos los tests.
    @AfterAll
    public static void tearDownClass() {
        // Desactiva el modo de prueba para restaurar el comportamiento normal de las conexiones.
        ManejadorConexiones.isTestMode = false;
    }

    /**
     * Test para el registro correcto de un cliente frecuente.
     * Verifica que los datos proporcionados se guarden correctamente y se devuelvan con su id generado.
     */
    @Test
    public void testRegistrarClienteFrecuenteOk() {
        IClientesFrecuentesDAO clienteFrecuenteDAO = new ClientesFrecuentesDAO(); // Instancia el DAO que interactúa con la base de datos.
        ClientesFrecuentesBO clientesFrecuentesBO = new ClientesFrecuentesBO(clienteFrecuenteDAO); // Instancia el BO con el DAO.
        String nombre = "Juan";
        String apellidoP = "Perez";
        String apellidoM = "Lopez";
        String correo = "ariraulm@gmail.com";
        String telefono = "1234567899";
        // Crea un nuevo cliente frecuente con datos válidos.
        NuevoClienteFrecuenteDTO nuevoClienteFrecuenteDTO = new NuevoClienteFrecuenteDTO(nombre, apellidoP, apellidoM, correo, telefono);
        // Llama al método para registrar el cliente.
        ClienteFrecuente clienteGuardado = clientesFrecuentesBO.registrarClienteFrecuente(nuevoClienteFrecuenteDTO);

        // SI el cliente guardado tiene un ID es que persistio..
        assertNotNull(clienteGuardado.getId());

    }

    /**
     * Test para validar la excepción cuando se omiten campos obligatorios.
     * Verifica que se lance una excepción cuando se proporciona el valor por defecto en uno de los campos.
     */
    @Test
    public void testRegistrarClienteFrecuenteConCamposObligatorios() {
        IClientesFrecuentesDAO clienteFrecuenteDAO = new ClientesFrecuentesDAO();
        ClientesFrecuentesBO clientesFrecuentesBO = new ClientesFrecuentesBO(clienteFrecuenteDAO);

        // Crea un cliente con el campo "nombre" vacío, lo que se debe considerar como valor por defecto.
        NuevoClienteFrecuenteDTO nuevoClienteFrecuenteDTO = new NuevoClienteFrecuenteDTO(
                "INGRESE EL O LOS NOMBRES", "Pérez", "Lopez", "juan.perez@example.com", "1234567890"
        );

        // Verifica que se lance la excepción CampoObligatorioException debido a que el nombre no está completo.
        CampoObligatorioException thrown = assertThrows(CampoObligatorioException.class, () -> {
            clientesFrecuentesBO.registrarClienteFrecuente(nuevoClienteFrecuenteDTO);
        });
        assertEquals("Todos los campos deben completarse correctamente.", thrown.getMessage());
    }

    /**
     * Test para validar la excepción cuando el formato del nombre es inválido.
     * Verifica que se lance una excepción FormatoInvalidoException si el nombre contiene caracteres no permitidos.
     */
    @Test
    public void testRegistrarClienteFrecuenteConFormatoInvalido() {
        IClientesFrecuentesDAO clienteFrecuenteDAO = new ClientesFrecuentesDAO();
        ClientesFrecuentesBO clientesFrecuentesBO = new ClientesFrecuentesBO(clienteFrecuenteDAO);

        // Crea un cliente con un nombre que contiene números, lo cual no es permitido.
        NuevoClienteFrecuenteDTO nuevoClienteFrecuenteDTO = new NuevoClienteFrecuenteDTO(
                "Juan123", "Pérez", "Lopez", "juan.perez@example.com", "1234567890"
        );

        // Verifica que se lance la excepción FormatoInvalidoException debido a caracteres no válidos en el nombre.
        FormatoInvalidoException thrown = assertThrows(FormatoInvalidoException.class, () -> {
            clientesFrecuentesBO.registrarClienteFrecuente(nuevoClienteFrecuenteDTO);
        });
        assertEquals("Nombres y apellidos solo pueden contener letras y espacios.", thrown.getMessage());
    }

    /**
     * Test para validar la excepción cuando el número de teléfono es inválido.
     * Verifica que se lance una excepción TelefonoInvalidoException si el número de teléfono no tiene exactamente 10 dígitos.
     */
    @Test
    public void testRegistrarClienteFrecuenteConTelefonoInvalido() {
        IClientesFrecuentesDAO clienteFrecuenteDAO = new ClientesFrecuentesDAO();
        ClientesFrecuentesBO clientesFrecuentesBO = new ClientesFrecuentesBO(clienteFrecuenteDAO);

        // Crea un cliente con un número de teléfono inválido (menos de 10 dígitos).
        NuevoClienteFrecuenteDTO nuevoClienteFrecuenteDTO = new NuevoClienteFrecuenteDTO(
                "Juan", "Pérez", "Lopez", "juan.perez@example.com", "12345"
        );

        // Verifica que se lance la excepción TelefonoInvalidoException debido a que el número de teléfono no tiene 10 dígitos.
        TelefonoInvalidoException thrown = assertThrows(TelefonoInvalidoException.class, () -> {
            clientesFrecuentesBO.registrarClienteFrecuente(nuevoClienteFrecuenteDTO);
        });
        assertEquals("El número de teléfono debe tener exactamente 10 dígitos.", thrown.getMessage());
    }

    /**
     * Test para buscar clientes frecuentes con un filtro de nombre.
     * Verifica que se pueda realizar la búsqueda correctamente y obtener al menos un cliente.
     */
    @Test
    public void testBuscarClientes() {
        IClientesFrecuentesDAO clienteFrecuenteDAO = new ClientesFrecuentesDAO();
        ClientesFrecuentesBO clientesFrecuentesBO = new ClientesFrecuentesBO(clienteFrecuenteDAO);

        // Se asume que ya hay clientes guardados en la base de datos.
        List<ClienteFrecuente> clientes = clientesFrecuentesBO.buscarClientes("Juan");

        // Verifica que al menos un cliente se ha encontrado con el nombre "Juan".
        assertTrue(clientes.size() > 0); // Verifica que la lista no esté vacía.
        assertTrue(clientes.get(0).getNombre().contains("Juan")); // Verifica que el nombre del cliente contiene "Juan".
    }
}
