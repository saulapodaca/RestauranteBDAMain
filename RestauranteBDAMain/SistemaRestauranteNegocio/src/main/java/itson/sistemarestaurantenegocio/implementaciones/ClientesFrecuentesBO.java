package itson.sistemarestaurantenegocio.implementaciones;

import itson.sistemarestaurantedominio.ClienteFrecuente;
import itson.sistemarestaurantedominio.dtos.NuevoClienteFrecuenteDTO;
import itson.sistemarestaurantenegocio.IClientesFrecuentesBO;
import itson.sistemarestaurantenegocio.excepciones.CampoObligatorioException;
import itson.sistemarestaurantenegocio.excepciones.FormatoInvalidoException;
import itson.sistemarestaurantenegocio.excepciones.TelefonoInvalidoException;
import itson.sistemarestaurantenegocio.excepciones.EncriptadoException;
import itson.sistemarestaurantepersistencia.IClientesFrecuentesDAO;
import itson.encriptacion.Encriptador;
import itson.encriptacion.Desencriptador;
import itson.sistemarestaurantenegocio.excepciones.DatoDuplicadoException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementación de la lógica de negocio para la gestión de clientes
 * frecuentes. Esta clase permite registrar, buscar y validar clientes
 * frecuentes, realizando la encriptación de los datos sensibles como el correo
 * y teléfono antes de almacenarlos en la base de datos, y desencriptándolos
 * cuando es necesario retornarlos.
 */
/**
 * Implementación de la lógica de negocio para la gestión de clientes
 * frecuentes. Esta clase permite registrar, buscar y validar clientes
 * frecuentes, realizando la encriptación de los datos sensibles como el correo
 * y teléfono antes de almacenarlos en la base de datos, y desencriptándolos
 * cuando es necesario retornarlos.
 */
public class ClientesFrecuentesBO implements IClientesFrecuentesBO {

    // Placeholders por defecto para los campos del formulario
    private static final String DEFAULT_NOMBRE = "INGRESE EL O LOS NOMBRES";
    private static final String DEFAULT_APELLIDO_PATERNO = "INGRESE EL APELLIDO PATERNO";
    private static final String DEFAULT_APELLIDO_MATERNO = "INGRESE EL APELLIDO MATERNO";
    private static final String DEFAULT_TELEFONO = "INGRESE EL NUMERO DE TELEFONO";

    private final IClientesFrecuentesDAO clienteFrecuenteDAO;

    /**
     * Constructor para inicializar el servicio de clientes frecuentes.
     *
     * @param clienteFrecuenteDAO El objeto que maneja las operaciones de
     * persistencia de clientes frecuentes.
     */
    public ClientesFrecuentesBO(IClientesFrecuentesDAO clienteFrecuenteDAO) {
        this.clienteFrecuenteDAO = clienteFrecuenteDAO;
    }

    /**
     * Registra un nuevo cliente frecuente en la base de datos, encriptando sus
     * datos sensibles antes de persistirlos.
     *
     * @param nuevoClienteFrecuenteDTO El DTO con la información del nuevo
     * cliente frecuente.
     * @return El cliente registrado, con los datos encriptados y retornados
     * desencriptados si es necesario.
     * @throws CampoObligatorioException Si algún campo obligatorio no ha sido
     * proporcionado.
     * @throws FormatoInvalidoException Si algún campo tiene un formato
     * inválido.
     * @throws TelefonoInvalidoException Si el número de teléfono no tiene el
     * formato correcto.
     * @throws EncriptadoException Si ocurre un error al encriptar o
     * desencriptar los datos.
     * @throws DatoDuplicadoException Si el correo o teléfono ya están
     * registrados.
     */
    @Override
    public ClienteFrecuente registrarClienteFrecuente(NuevoClienteFrecuenteDTO nuevoClienteFrecuenteDTO) {
        validarCamposObligatorios(nuevoClienteFrecuenteDTO);
        validarFormatoCampos(nuevoClienteFrecuenteDTO);
        validarTelefonoExacto(nuevoClienteFrecuenteDTO.getNumeroTelefono());

        String correo = nuevoClienteFrecuenteDTO.getCorreo();
        String telefono = nuevoClienteFrecuenteDTO.getNumeroTelefono();

// Verificar si el correo no es null ni vacío antes de intentar encriptarlo
        String correoEncriptado = null;
        if (correo != null && !correo.trim().isEmpty()) {
            try {
                correoEncriptado = Encriptador.encriptar(correo);
            } catch (Exception ex) {
                Logger.getLogger(ClientesFrecuentesBO.class.getName()).log(Level.SEVERE, "Error en encriptación de correo", ex);
                throw new EncriptadoException("Error en encriptación de correo.");
            }
        }

// Encriptar el teléfono (sin comprobación adicional porque el teléfono nunca debería ser null o vacío)
        String telefonoEncriptado;
        try {
            telefonoEncriptado = Encriptador.encriptar(telefono);
        } catch (Exception ex) {
            Logger.getLogger(ClientesFrecuentesBO.class.getName()).log(Level.SEVERE, "Error en encriptación de teléfono", ex);
            throw new EncriptadoException("Error en encriptación de teléfono.");
        }

        // Validación de existencia con datos desencriptados
        List<ClienteFrecuente> todosClientes = clienteFrecuenteDAO.obtenerTodosLosClientes();
        for (ClienteFrecuente cliente : todosClientes) {
            try {
                // Verificar correo solo si no es null ni vacío
                if (cliente.getCorreo() != null && !cliente.getCorreo().trim().isEmpty() && correo != null && !correo.trim().isEmpty()) {
                    String correoDesencriptado = Desencriptador.desencriptar(cliente.getCorreo());
                    if (correoDesencriptado.equalsIgnoreCase(correo.trim())) {
                        throw new DatoDuplicadoException("El correo electrónico ya está registrado.");
                    }
                }

                // Verificar teléfono solo si no es null ni vacío
                if (cliente.getTelefono() != null && !cliente.getTelefono().trim().isEmpty()) {
                    String telefonoDesencriptado = Desencriptador.desencriptar(cliente.getTelefono()).trim();
                    if (telefonoDesencriptado.equals(telefono.trim())) {
                        throw new DatoDuplicadoException("El número de teléfono ya está registrado.");
                    }
                }
            } catch (DatoDuplicadoException ex) {
                // Propagamos directamente esta excepción para que sea capturada en la UI
                throw ex;
            } catch (Exception ex) {
                // Si ocurre un error inesperado al desencriptar, logueamos el error y lanzamos una excepción controlada
                Logger.getLogger(ClientesFrecuentesBO.class.getName()).log(Level.SEVERE,
                        "Error al desencriptar datos del cliente: " + cliente.getCorreo() + " / " + cliente.getTelefono(), ex);
                throw new EncriptadoException("Error al desencriptar datos para validación del cliente. Por favor, intente nuevamente.");
            }
        }

        // Preparar DTO con datos encriptados
        nuevoClienteFrecuenteDTO.setCorreo(correoEncriptado); // Podría ser null
        nuevoClienteFrecuenteDTO.setNumeroTelefono(telefonoEncriptado);

        ClienteFrecuente clienteRegistrado = clienteFrecuenteDAO.registrar(nuevoClienteFrecuenteDTO);

        // Desencriptar si es necesario
        if (clienteRegistrado != null) {
            desencriptarCliente(clienteRegistrado);
        }

        return clienteRegistrado;
    }

    /**
     * Desencripta los datos sensibles de un cliente.
     *
     * @param cliente El cliente cuyo correo y teléfono deben ser
     * desencriptados.
     * @throws EncriptadoException Si ocurre un error al intentar desencriptar
     * los datos del cliente.
     */
    private void desencriptarCliente(ClienteFrecuente cliente) {
        try {
            // Solo desencriptar correo si no es null
            if (cliente.getCorreo() != null) {
                cliente.setCorreo(Desencriptador.desencriptar(cliente.getCorreo()));
            }
            cliente.setTelefono(Desencriptador.desencriptar(cliente.getTelefono()));
        } catch (Exception ex) {
            Logger.getLogger(ClientesFrecuentesBO.class.getName()).log(Level.SEVERE, "Error en desencriptación de datos", ex);
            throw new EncriptadoException("Error al desencriptar datos del cliente registrado.");
        }
    }

    /**
     * Valida que todos los campos obligatorios del DTO estén completos y no
     * contengan valores por defecto.
     *
     * @param dto El DTO que contiene los datos del cliente.
     * @throws CampoObligatorioException Si algún campo obligatorio no ha sido
     * completado correctamente.
     */
    private void validarCamposObligatorios(NuevoClienteFrecuenteDTO dto) {
        if (esTextoPorDefecto(dto.getNombre(), DEFAULT_NOMBRE)
                || esTextoPorDefecto(dto.getApellidoPaterno(), DEFAULT_APELLIDO_PATERNO)
                || esTextoPorDefecto(dto.getApellidoMaterno(), DEFAULT_APELLIDO_MATERNO)
                || esTextoPorDefecto(dto.getNumeroTelefono(), DEFAULT_TELEFONO)) {
            throw new CampoObligatorioException("Todos los campos deben completarse correctamente.");
        }
    }

    /**
     * Valida que los campos de nombre y apellido solo contengan letras y
     * espacios.
     *
     * @param dto El DTO con los datos a validar.
     * @throws FormatoInvalidoException Si algún campo contiene caracteres
     * inválidos.
     */
    private void validarFormatoCampos(NuevoClienteFrecuenteDTO dto) {
        if (!validarNombreApellido(dto.getNombre())
                || !validarNombreApellido(dto.getApellidoPaterno())
                || !validarNombreApellido(dto.getApellidoMaterno())) {
            throw new FormatoInvalidoException("Nombres y apellidos solo pueden contener letras y espacios.");
        }
    }

    /**
     * Valida que el número de teléfono tenga exactamente 10 dígitos.
     *
     * @param telefono El número de teléfono a validar.
     * @throws TelefonoInvalidoException Si el número de teléfono no tiene 10
     * dígitos.
     */
    private void validarTelefonoExacto(String telefono) {
        if (!validarTelefono(telefono)) {
            throw new TelefonoInvalidoException("El número de teléfono debe tener exactamente 10 dígitos.");
        }
    }

    /**
     * Valida si el texto proporcionado es igual al valor por defecto.
     *
     * @param texto El texto a validar.
     * @param textoPorDefecto El valor por defecto a comparar.
     * @return true si el texto es igual al valor por defecto, false si no.
     */
    private boolean esTextoPorDefecto(String texto, String textoPorDefecto) {
        return texto == null || texto.trim().isEmpty() || texto.equalsIgnoreCase(textoPorDefecto);
    }

    /**
     * Valida que el texto solo contenga letras y espacios.
     *
     * @param texto El texto a validar.
     * @return true si el texto es válido, false si no.
     */
    private boolean validarNombreApellido(String texto) {
        return texto != null && texto.matches("[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ\\s]+");
    }

    /**
     * Valida que el número de teléfono tenga el formato adecuado (10 dígitos).
     *
     * @param telefono El número de teléfono a validar.
     * @return true si el teléfono es válido, false si no.
     */
    private boolean validarTelefono(String telefono) {
        return telefono != null && telefono.matches("\\d{10}");
    }

    /**
     * Busca clientes frecuentes que coincidan con el filtro de búsqueda en sus
     * datos.
     *
     * @param filtro El filtro de búsqueda que se aplicará sobre el nombre,
     * apellido, correo o teléfono del cliente.
     * @return Una lista de clientes que coinciden con el filtro.
     */
    @Override
    public List<ClienteFrecuente> buscarClientes(String filtro) {
        if (filtro == null || filtro.trim().isEmpty()) {
            return new ArrayList<>();
        }
        List<ClienteFrecuente> todos = clienteFrecuenteDAO.obtenerTodosLosClientes();
        List<ClienteFrecuente> filtrados = new ArrayList<>();

        String filtroLower = filtro.toLowerCase();

        for (ClienteFrecuente cliente : todos) {
            try {
                String nombre = cliente.getNombre() != null ? cliente.getNombre().toLowerCase() : "";
                String apellidoPaterno = cliente.getApellidoPaterno() != null ? cliente.getApellidoPaterno().toLowerCase() : "";
                String apellidoMaterno = cliente.getApellidoMaterno() != null ? cliente.getApellidoMaterno().toLowerCase() : "";

                String correo = cliente.getCorreo() != null ? Desencriptador.desencriptar(cliente.getCorreo()).toLowerCase() : "";
                String telefono = cliente.getTelefono() != null ? Desencriptador.desencriptar(cliente.getTelefono()) : "";

                if (nombre.contains(filtroLower)
                        || apellidoPaterno.contains(filtroLower)
                        || apellidoMaterno.contains(filtroLower)
                        || correo.contains(filtroLower)
                        || telefono.contains(filtroLower)) {

                    ClienteFrecuente clienteDesencriptado = new ClienteFrecuente();
                    clienteDesencriptado.setNombre(nombre);
                    clienteDesencriptado.setApellidoPaterno(apellidoPaterno);
                    clienteDesencriptado.setApellidoMaterno(apellidoMaterno);
                    clienteDesencriptado.setTelefono(telefono);
                    clienteDesencriptado.setCorreo(correo);

                    filtrados.add(clienteDesencriptado);
                }
            } catch (Exception e) {
                throw new EncriptadoException("Error en desencriptación de datos.");
            }
        }

        return filtrados;
    }
}
