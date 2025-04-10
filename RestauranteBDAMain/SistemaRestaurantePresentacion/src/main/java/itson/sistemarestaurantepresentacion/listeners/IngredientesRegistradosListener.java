package itson.sistemarestaurantepresentacion.listeners;

import itson.sistemarestaurantedominio.dtos.IngredienteRegistradoDTO;
import java.util.List;

/**
 * Interfaz que define un listener para recibir una lista de ingredientes registrados.
 * 
 * Esta interfaz es útil para desacoplar la lógica de presentación de la lógica de negocio.
 * Permite que diferentes componentes (como paneles de búsqueda o formularios) reaccionen
 * cuando se obtienen los resultados de una consulta de ingredientes.
 */
public interface IngredientesRegistradosListener {
    /**
     * Método que se ejecuta cuando se obtiene la lista de ingredientes registrados.
     *
     * @param ingredientes lista de ingredientes que cumplen con los criterios de búsqueda
     */
    public abstract void onIngredientesRegistrados (List<IngredienteRegistradoDTO> ingredientes);
}
