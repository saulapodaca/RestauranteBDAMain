package itson.sistemarestaurantenegocio.implementaciones;

import itson.sistemarestaurantedominio.ClienteFrecuente;
import itson.sistemarestaurantedominio.dtos.NuevoClienteFrecuenteDTO;
import itson.sistemarestaurantenegocio.IClientesFrecuentesBO;
import itson.sistemarestaurantepersistencia.IClientesFrecuentesDAO;

// Excepciones personalizadas que definimos
import itson.sistemarestaurantenegocio.excepciones.CampoObligatorioException;
import itson.sistemarestaurantenegocio.excepciones.FormatoInvalidoException;
import itson.sistemarestaurantenegocio.excepciones.TelefonoInvalidoException;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la lógica de negocio para la gestión de clientes frecuentes.
 */
public class ClientesFrecuentesBO implements IClientesFrecuentesBO {

    // Placeholders por defecto para los campos del formulario
    private static final String DEFAULT_NOMBRE = "INGRESE EL O LOS NOMBRES";
    private static final String DEFAULT_APELLIDO_PATERNO = "INGRESE EL APELLIDO PATERNO";
    private static final String DEFAULT_APELLIDO_MATERNO = "INGRESE EL APELLIDO MATERNO";
    private static final String DEFAULT_CORREO = "INGRESE EL CORREO ";
    private static final String DEFAULT_TELEFONO = "INGRESE EL NUMERO DE TELEFONO";

    // Dependencia para el acceso a datos de clientes frecuentes
    private IClientesFrecuentesDAO clienteFrecuenteDAO;

    // Constructor para inyectar la dependencia del DAO
    public ClientesFrecuentesBO(IClientesFrecuentesDAO clienteFrecuenteDAO) {
        this.clienteFrecuenteDAO = clienteFrecuenteDAO;
    }

    /**
     * Registra un nuevo cliente frecuente después de validar los datos.
     *
     * @param nuevoClienteFrecuenteDTO DTO con los datos del nuevo cliente.
     * @return ClienteFrecuente registrado.
     */
    @Override
    public ClienteFrecuente registrarClienteFrecuente(NuevoClienteFrecuenteDTO nuevoClienteFrecuenteDTO) {

        // Validación de campos obligatorios: que no sean el texto por defecto ni estén vacíos
        if (esTextoPorDefecto(nuevoClienteFrecuenteDTO.getNombre(), DEFAULT_NOMBRE)
                || esTextoPorDefecto(nuevoClienteFrecuenteDTO.getApellidoPaterno(), DEFAULT_APELLIDO_PATERNO)
                || esTextoPorDefecto(nuevoClienteFrecuenteDTO.getApellidoMaterno(), DEFAULT_APELLIDO_MATERNO)
                || esTextoPorDefecto(nuevoClienteFrecuenteDTO.getNumeroTelefono(), DEFAULT_TELEFONO)) {
            throw new CampoObligatorioException("Todos los campos deben completarse correctamente.");
        }

        // Validación de formato para nombres y apellidos (solo letras y espacios)
        if (!validarNombreApellido(nuevoClienteFrecuenteDTO.getNombre())
                || !validarNombreApellido(nuevoClienteFrecuenteDTO.getApellidoPaterno())
                || !validarNombreApellido(nuevoClienteFrecuenteDTO.getApellidoMaterno())) {
            throw new FormatoInvalidoException("Nombres y apellidos solo pueden contener letras y espacios.");
        }

        // Validación específica para el número de teléfono (exactamente 10 dígitos)
        if (!validarTelefono(nuevoClienteFrecuenteDTO.getNumeroTelefono())) {
            throw new TelefonoInvalidoException("El número de teléfono debe tener exactamente 10 dígitos.");
        }

        // Si todas las validaciones pasan, se crea un nuevo DTO para el cliente validado
        NuevoClienteFrecuenteDTO clienteValidado = new NuevoClienteFrecuenteDTO(
                nuevoClienteFrecuenteDTO.getNombre(),
                nuevoClienteFrecuenteDTO.getApellidoPaterno(),
                nuevoClienteFrecuenteDTO.getApellidoMaterno(),
                nuevoClienteFrecuenteDTO.getCorreo(),
                nuevoClienteFrecuenteDTO.getNumeroTelefono()
        );

        // Se delega la operación de registro al DAO
        return clienteFrecuenteDAO.registrar(clienteValidado);
    }

    /**
     * Verifica si un texto es vacío o corresponde al placeholder por defecto.
     *
     * @param texto El texto a evaluar.
     * @param placeholder El texto por defecto esperado.
     * @return true si es vacío o es el placeholder, false en caso contrario.
     */
    private boolean esTextoPorDefecto(String texto, String placeholder) {
        return texto.isEmpty() || texto.equals(placeholder);
    }

    /**
     * Valida que un nombre o apellido contenga solo letras y espacios.
     *
     * @param texto El texto a validar.
     * @return true si es válido, false si contiene otros caracteres.
     */
    private boolean validarNombreApellido(String texto) {
        return texto.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$");
    }

    /**
     * Valida que el número de teléfono tenga exactamente 10 dígitos numéricos.
     *
     * @param telefono El número de teléfono a validar.
     * @return true si es válido, false en caso contrario.
     */
    private boolean validarTelefono(String telefono) {
        return telefono.matches("^\\d{10}$");
    }

    /**
     * Busca clientes frecuentes que coincidan con el filtro proporcionado.
     *
     * @param filtro Texto a buscar.
     * @return Lista de clientes frecuentes que coincidan con el filtro.
     */
    @Override
    public List<ClienteFrecuente> buscarClientes(String filtro) {
        // Si el filtro es nulo o vacío, se retorna una lista vacía
        if (filtro == null || filtro.trim().isEmpty()) {
            return new ArrayList<>();
        }
        // Se delega la búsqueda al DAO
        return clienteFrecuenteDAO.buscarClientes(filtro.trim());
    }
}
