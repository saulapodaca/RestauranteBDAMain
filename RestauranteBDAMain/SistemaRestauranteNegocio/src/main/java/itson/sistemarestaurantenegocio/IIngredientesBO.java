package itson.sistemarestaurantenegocio;

import itson.sistemarestaurantedominio.Ingrediente;
import itson.sistemarestaurantedominio.dtos.IngredienteRegistradoDTO;
import itson.sistemarestaurantedominio.dtos.NuevoIngredienteDTO;
import itson.sistemarestaurantenegocio.excepciones.IngredienteRegistradoException;
import itson.sistemarestaurantenegocio.excepciones.NombreInvalidoException;
import itson.sistemarestaurantenegocio.excepciones.StockInvalidoException;
import java.util.List;

/**
 * Interfaz que define las operaciones de negocio relacionadas con los ingredientes.
 * Esta interfaz establece el contrato que deben cumplir las clases encargadas de la lógica de negocio.
 */
public interface IIngredientesBO {
    
     /**
     * Registra un nuevo ingrediente luego de aplicar las validaciones correspondientes.
     * 
     * @param nuevoIngredienteDTO DTO con la información del ingrediente a registrar
     * @return Objeto Ingrediente registrado
     * @throws NombreInvalidoException si el nombre del ingrediente es inválido
     * @throws StockInvalidoException si el stock ingresado es inválido
     * @throws IngredienteRegistradoException si el ingrediente ya está registrado
     */
    public abstract Ingrediente registrar(NuevoIngredienteDTO nuevoIngredienteDTO) 
            throws NombreInvalidoException, StockInvalidoException, IngredienteRegistradoException;
    
    /**
     * Valida los campos iniciales ingresados por el usuario antes de procesar el registro.
     * 
     * @param nombre nombre ingresado por el usuario
     * @param nombreDefault texto por defecto del campo nombre
     * @param stock stock ingresado por el usuario como texto
     * @param stockDefault texto por defecto del campo stock
     * @throws NombreInvalidoException si el nombre es vacío o inválido
     * @throws StockInvalidoException si el stock es vacío o no es un número válido
     */
    public abstract void validacionesIniciales(String nombre,String nombreDefault, String stock, String stockDefault) 
            throws NombreInvalidoException, StockInvalidoException;
    
    /**
     * Busca ingredientes registrados filtrando por texto y unidad de medida.
     * 
     * @param filtroBusqueda texto de búsqueda para el nombre del ingrediente
     * @param unidadMedida unidad de medida seleccionada (puede ser "TODOS")
     * @return lista de ingredientes que cumplen con el filtro
     */
    public abstract List<IngredienteRegistradoDTO> buscarIngredientePorFiltro(String filtroBusqueda, String unidadMedida);
    
     /**
     * Actualiza el stock de un ingrediente dado su ID y el nuevo stock como texto.
     * 
     * @param id identificador del ingrediente
     * @param nuevoStockTexto nuevo stock como texto (debe ser un número entero positivo)
     * @throws StockInvalidoException si el valor no es válido o menor que cero
     */
    public abstract void actualizarStockIngrediente(Long id, String nuevoStockTexto) 
            throws StockInvalidoException;
}
