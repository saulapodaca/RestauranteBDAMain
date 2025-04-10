package itson.sistemarestaurantenegocio.implementaciones;

import itson.sistemarestaurantedominio.ClienteFrecuente;
import itson.sistemarestaurantedominio.dtos.NuevoClienteFrecuenteDTO;
import itson.sistemarestaurantenegocio.IClientesFrecuentesBO;
import itson.sistemarestaurantepersistencia.IClientesFrecuentesDAO;
import itson.sistemarestaurantenegocio.excepciones.CampoObligatorioException;
import itson.sistemarestaurantenegocio.excepciones.FormatoInvalidoException;
import itson.sistemarestaurantenegocio.excepciones.TelefonoInvalidoException;
import java.util.ArrayList;
import java.util.List;

public class ClientesFrecuentesBO implements IClientesFrecuentesBO {

    private static final String DEFAULT_NOMBRE = "INGRESE EL O LOS NOMBRES";
    private static final String DEFAULT_APELLIDO_PATERNO = "INGRESE EL APELLIDO PATERNO";
    private static final String DEFAULT_APELLIDO_MATERNO = "INGRESE EL APELLIDO MATERNO";
    private static final String DEFAULT_CORREO = "INGRESE EL CORREO ";
    private static final String DEFAULT_TELEFONO = "INGRESE EL NUMERO DE TELEFONO";

    private IClientesFrecuentesDAO clienteFrecuenteDAO;

    public ClientesFrecuentesBO(IClientesFrecuentesDAO clienteFrecuenteDAO) {
        this.clienteFrecuenteDAO = clienteFrecuenteDAO;
    }

    @Override
    public ClienteFrecuente registrarClienteFrecuente(NuevoClienteFrecuenteDTO nuevoClienteFrecuenteDTO) {
        if (esTextoPorDefecto(nuevoClienteFrecuenteDTO.getNombre(), DEFAULT_NOMBRE)
                || esTextoPorDefecto(nuevoClienteFrecuenteDTO.getApellidoPaterno(), DEFAULT_APELLIDO_PATERNO)
                || esTextoPorDefecto(nuevoClienteFrecuenteDTO.getApellidoMaterno(), DEFAULT_APELLIDO_MATERNO)
                || esTextoPorDefecto(nuevoClienteFrecuenteDTO.getNumeroTelefono(), DEFAULT_TELEFONO)) {
            throw new CampoObligatorioException("Todos los campos deben completarse correctamente.");
        }

        if (!validarNombreApellido(nuevoClienteFrecuenteDTO.getNombre())
                || !validarNombreApellido(nuevoClienteFrecuenteDTO.getApellidoPaterno())
                || !validarNombreApellido(nuevoClienteFrecuenteDTO.getApellidoMaterno())) {
            throw new FormatoInvalidoException("Nombres y apellidos solo pueden contener letras y espacios.");
        }

        if (!validarTelefono(nuevoClienteFrecuenteDTO.getNumeroTelefono())) {
            throw new TelefonoInvalidoException("El número de teléfono debe tener exactamente 10 dígitos.");
        }

        NuevoClienteFrecuenteDTO clienteValidado = new NuevoClienteFrecuenteDTO(
                nuevoClienteFrecuenteDTO.getNombre(),
                nuevoClienteFrecuenteDTO.getApellidoPaterno(),
                nuevoClienteFrecuenteDTO.getApellidoMaterno(),
                nuevoClienteFrecuenteDTO.getCorreo(),
                nuevoClienteFrecuenteDTO.getNumeroTelefono()
        );

        return clienteFrecuenteDAO.registrar(clienteValidado);
    }

    private boolean esTextoPorDefecto(String texto, String placeholder) {
        return texto.isEmpty() || texto.equals(placeholder);
    }

    private boolean validarNombreApellido(String texto) {
        return texto.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$");
    }

    private boolean validarTelefono(String telefono) {
        return telefono.matches("^\\d{10}$");
    }

    @Override
    public List<ClienteFrecuente> buscarClientes(String filtro) {
        if (filtro == null || filtro.trim().isEmpty()) {
            return new ArrayList<>();
        }
        return clienteFrecuenteDAO.buscarClientes(filtro.trim());
    }
}
