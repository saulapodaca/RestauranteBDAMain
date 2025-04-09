package itson.sistemarestaurantenegocio.implementaciones;

import itson.sistemarestaurantedominio.Ingrediente;
import itson.sistemarestaurantedominio.UnidadMedidaIngrediente;
import itson.sistemarestaurantedominio.dtos.IngredienteRegistradoDTO;
import itson.sistemarestaurantedominio.dtos.NuevoIngredienteDTO;
import itson.sistemarestaurantedominio.dtos.StockIngredienteActualizadoDTO;
import itson.sistemarestaurantenegocio.IIngredientesBO;
import itson.sistemarestaurantenegocio.excepciones.IngredienteRegistradoException;
import itson.sistemarestaurantenegocio.excepciones.NombreInvalidoException;
import itson.sistemarestaurantenegocio.excepciones.StockInvalidoException;
import itson.sistemarestaurantepersistencia.IIngredientesDAO;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Clase de lógica de negocio para la gestión de ingredientes.
 * Implementa las validaciones y reglas necesarias antes de delegar en la capa DAO.
 */
public class IngredientesBO implements IIngredientesBO{

     // Expresión regular para validar que el nombre solo contenga letras y espacios
    private static final String PATRON_TEXTO_VALIDO = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$";
    
    private IIngredientesDAO ingredientesDAO;
    // Texto por defecto en el campo de búsqueda
    private static final String DEFAULT_BUSCADOR = "INGRESE EL INGREDIENTE QUE DESEA BUSCAR";
    
    
    /**
     * Constructor que recibe la dependencia DAO para la gestión de ingredientes.
     * @param ingredientesDAO DAO para acceso a datos de ingredientes
     */
    public IngredientesBO (IIngredientesDAO ingredientesDAO){
        this.ingredientesDAO = ingredientesDAO;
    }

     /**
     * Registra un nuevo ingrediente en el sistema luego de pasar por varias validaciones.
     * 
     * @param nuevoIngredienteDTO DTO con los datos del nuevo ingrediente
     * @return el ingrediente registrado
     * @throws NombreInvalidoException si el nombre no cumple con el patrón permitido
     * @throws StockInvalidoException si el stock es negativo
     * @throws IngredienteRegistradoException si el ingrediente ya está registrado
     */
    @Override
    public Ingrediente registrar(NuevoIngredienteDTO nuevoIngredienteDTO) throws NombreInvalidoException, StockInvalidoException, IngredienteRegistradoException{
        validarNombre(nuevoIngredienteDTO.getNombre());
        validarStock(nuevoIngredienteDTO.getStock());
        validarExistenciaInventario(nuevoIngredienteDTO.getNombre(), nuevoIngredienteDTO.getUnidadMedidaIngrediente());
        return ingredientesDAO.registrar(nuevoIngredienteDTO);
    }

    /**
     * Valida que el nombre del ingrediente contenga solo letras y espacios.
     * 
     * @param nombre Nombre a validar
     * @throws NombreInvalidoException si el nombre contiene caracteres inválidos
     */
    public void validarNombre(String nombre) throws NombreInvalidoException {
        if(!Pattern.matches(PATRON_TEXTO_VALIDO, nombre))
            throw new NombreInvalidoException("El nombre del ingrediente no puede contener números ni carácteres especiales,");
    }
    
     /**
     * Valida que el stock no sea negativo.
     * 
     * @param stock Valor del stock
     * @throws StockInvalidoException si el stock es menor que cero
     */
    public void validarStock(Integer stock) throws StockInvalidoException{
        if(stock<0)
            throw new StockInvalidoException("El stock no puede ser negativo");
    }
    
     /**
     * Verifica si un ingrediente ya se encuentra registrado con el mismo nombre y unidad de medida.
     * 
     * @param nombre Nombre del ingrediente
     * @param unidadMedidaIngrediente Unidad de medida del ingrediente
     * @throws IngredienteRegistradoException si el ingrediente ya existe
     */
    public void validarExistenciaInventario(String nombre, UnidadMedidaIngrediente unidadMedidaIngrediente) throws IngredienteRegistradoException {
        if (ingredientesDAO.existeIngrediente(nombre, unidadMedidaIngrediente)) {
            throw new IngredienteRegistradoException("El ingrediente ya se encuentra registrado");
        }
    }

     /**
     * Valida los valores iniciales ingresados por el usuario antes del registro.
     * 
     * @param nombre Nombre ingresado
     * @param nombreDefault Texto por defecto del campo nombre
     * @param stock Stock ingresado
     * @param stockDefault Texto por defecto del campo stock
     * @throws NombreInvalidoException si el nombre está vacío o es el valor por defecto
     * @throws StockInvalidoException si el stock está vacío o no es un número entero
     */
    @Override
    public void validacionesIniciales(String nombre,String nombreDefault, String stock, String stockDefault) throws NombreInvalidoException, StockInvalidoException{
        if (nombre.equals(nombreDefault) || nombre.isBlank() || nombre.isEmpty())
            throw new NombreInvalidoException("El nombre no puede estar vacío");
        if (stock.equals(stockDefault) || stock.isBlank())
            throw new StockInvalidoException("El stock inicial no puede estar vacío");
        try{
            Integer s = Integer.valueOf(stock);
        }catch(NumberFormatException e){
            throw new StockInvalidoException("El stock solo admite números enteros");
        }
    }
    
     /**
     * Busca ingredientes según el filtro de texto y unidad de medida.
     * 
     * @param filtroBusqueda Texto a buscar (nombre del ingrediente)
     * @param unidadMedida Unidad de medida seleccionada
     * @return lista de ingredientes que cumplen con el filtro
     */
    @Override
    public List<IngredienteRegistradoDTO> buscarIngredientePorFiltro(String filtroBusqueda, String unidadMedida) {
        if (filtroBusqueda.equals(DEFAULT_BUSCADOR) 
                || filtroBusqueda.trim().isEmpty())
            filtroBusqueda = null;
        if (unidadMedida.trim().equals("TODOS"))
                unidadMedida = null;       
        return ingredientesDAO.buscarIngredientes(filtroBusqueda, unidadMedida);  
    }
    
      /**
     * Actualiza el stock de un ingrediente dado su ID y nuevo valor de stock en formato texto.
     * 
     * @param id ID del ingrediente
     * @param nuevoStockTexto nuevo valor de stock como String
     * @throws StockInvalidoException si el valor no es un número entero positivo
     */
    @Override
    public void actualizarStockIngrediente(Long id, String nuevoStockTexto) throws StockInvalidoException{
        try {
            Integer nuevoStock = Integer.valueOf(nuevoStockTexto);
            StockIngredienteActualizadoDTO ingredienteActualizado
                    = new StockIngredienteActualizadoDTO(id, nuevoStock);
            validarStock(nuevoStock);
            ingredientesDAO.actualizarStock(ingredienteActualizado);
        }catch(StockInvalidoException | NumberFormatException e){
            throw new StockInvalidoException("El stock solo admite números enteros positivos.");
        }
    }

}
