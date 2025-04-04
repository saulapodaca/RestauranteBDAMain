/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.sistemarestaurantenegocio.implementaciones;

import itson.sistemarestaurantedominio.ClienteFrecuente;
import itson.sistemarestaurantedominio.dtos.NuevoClienteFrecuenteDTO;
import itson.sistemarestaurantenegocio.IClientesFrecuentesBO;
import itson.sistemarestaurantepersistencia.IClientesFrecuentesDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rauln
 */
public class ClientesFrecuentesBO implements IClientesFrecuentesBO {

    // Constantes para los textos por defecto
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
        // Validaciones de texto por defecto (excepto correo)
        if (esTextoPorDefecto(nuevoClienteFrecuenteDTO.getNombre(), DEFAULT_NOMBRE)
                || esTextoPorDefecto(nuevoClienteFrecuenteDTO.getApellidoPaterno(), DEFAULT_APELLIDO_PATERNO)
                || esTextoPorDefecto(nuevoClienteFrecuenteDTO.getApellidoMaterno(), DEFAULT_APELLIDO_MATERNO)
                || esTextoPorDefecto(nuevoClienteFrecuenteDTO.getNumeroTelefono(), DEFAULT_TELEFONO)) {
            throw new IllegalArgumentException("Error: Todos los campos deben completarse correctamente.");
        }

        if (!validarNombreApellido(nuevoClienteFrecuenteDTO.getNombre())
                || !validarNombreApellido(nuevoClienteFrecuenteDTO.getApellidoPaterno())
                || !validarNombreApellido(nuevoClienteFrecuenteDTO.getApellidoMaterno())) {
            throw new IllegalArgumentException("Error: Nombres y apellidos solo pueden contener letras y espacios.");
        }

        if (!validarTelefono(nuevoClienteFrecuenteDTO.getNumeroTelefono())) {
            throw new IllegalArgumentException("Error: El número de teléfono debe tener exactamente 10 dígitos.");
        }

        NuevoClienteFrecuenteDTO clienteValidado = new NuevoClienteFrecuenteDTO(
                nuevoClienteFrecuenteDTO.getNombre(),
                nuevoClienteFrecuenteDTO.getApellidoPaterno(),
                nuevoClienteFrecuenteDTO.getApellidoMaterno(),
                nuevoClienteFrecuenteDTO.getCorreo(), // Aquí enviamos null si el correo no es válido
                nuevoClienteFrecuenteDTO.getNumeroTelefono()
        );

        // Registrar el cliente con los datos validados
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
            return new ArrayList<>(); // Retorna lista vacía si el filtro está vacío
        }
        return clienteFrecuenteDAO.buscarClientes(filtro.trim());
    }

}
