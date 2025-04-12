package itson.sistemarestaurantepersistencia;

import itson.sistemarestaurantedominio.Ingrediente;
import itson.sistemarestaurantedominio.UnidadMedidaIngrediente;
import itson.sistemarestaurantedominio.dtos.NuevoIngredienteDTO;
import itson.sistemarestaurantedominio.dtos.IngredienteRegistradoDTO;
import itson.sistemarestaurantedominio.dtos.StockIngredienteActualizadoDTO;
import java.util.List;

/**
 * Interfaz que define las operaciones de acceso a datos para la entidad Ingrediente.
 * Encapsula las interacciones con la base de datos relacionadas con ingredientes.
 */
public interface IIngredientesDAO {
    
    /**
     * Registra un nuevo ingrediente en la base de datos.
     *
     * @param nuevoIngredienteDTO DTO con los datos del ingrediente a registrar
     * @return el objeto Ingrediente persistido con su información almacenada
     */
    public abstract Ingrediente registrar(NuevoIngredienteDTO nuevoIngredienteDTO);
    
    /**
     * Verifica si ya existe un ingrediente con el mismo nombre y unidad de medida.
     *
     * @param nombre nombre del ingrediente
     * @param unidadMedida unidad de medida del ingrediente
     * @return true si el ingrediente ya existe, false en caso contrario
     */
    public abstract boolean existeIngrediente(String nombre, UnidadMedidaIngrediente unidadMedida);
    
     /**
     * Busca ingredientes registrados filtrando por nombre y unidad de medida.
     *
     * @param nombre texto de búsqueda para el nombre; si es null, no se aplica filtro por nombre
     * @param unidadMedida unidad de medida a filtrar; si es null, no se aplica filtro por unidad
     * @return lista de ingredientes registrados que coinciden con los filtros, como IngredienteRegistradoDTO   
     */
    public abstract List<IngredienteRegistradoDTO> buscarIngredientes(String nombre, String unidadMedida);
    
     /**
     * Actualiza el stock de un ingrediente existente.
     *
     * @param ingrediente DTO que contiene el ID del ingrediente y el nuevo stock
     * @return el objeto Ingrediente con el stock actualizado
     */
    public abstract Ingrediente actualizarStock(StockIngredienteActualizadoDTO ingrediente);
    
    
    public Ingrediente obtenerIngredientePorID(Long id);

}
