package itson.sistemarestaurantepersistencia.implementaciones;

import itson.sistemarestaurantedominio.Ingrediente;
import itson.sistemarestaurantedominio.UnidadMedidaIngrediente;
import itson.sistemarestaurantedominio.dtos.IngredienteRegistradoDTO;
import itson.sistemarestaurantedominio.dtos.NuevoIngredienteDTO;
import itson.sistemarestaurantedominio.dtos.StockIngredienteActualizadoDTO;
import itson.sistemarestaurantepersistencia.IIngredientesDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 * Implementación de la interfaz IIngredientesDAO que maneja el acceso
 * a datos para la entidad Ingrediente utilizando JPA.
 */
public class IngredientesDAO implements IIngredientesDAO {

    /**
     * Registra un nuevo ingrediente en la base de datos.
     * @param nuevoIngredienteDTO DTO que contiene la información del nuevo ingrediente a registrar
     * @return ingrediente el ingrediente persistido
     */
    @Override
    public Ingrediente registrar(NuevoIngredienteDTO nuevoIngredienteDTO) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        entityManager.getTransaction().begin();
        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setNombre(nuevoIngredienteDTO.getNombre());
        ingrediente.setUnidadMedida(nuevoIngredienteDTO.getUnidadMedidaIngrediente());
        ingrediente.setStock(nuevoIngredienteDTO.getStock());
        entityManager.persist(ingrediente);
        entityManager.getTransaction().commit();
        return ingrediente;
    }

    /**
     * Verifica si ya existe un ingrediente con el mismo nombre y unidad de medida en la base de datos.
     * @param nombre nombre del ingrediente
     * @param unidadMedida unidad de medida del ingrediente
     * @return true si el ingrediente ya está registrado, false en caso contrario
     */
    @Override
    public boolean existeIngrediente(String nombre, UnidadMedidaIngrediente unidadMedida) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        String jpql = "SELECT COUNT(i) FROM Ingrediente i WHERE i.nombre = :nombre AND i.unidadMedida = :unidad";
        Long count = entityManager.createQuery(jpql, Long.class)
                .setParameter("nombre", nombre)
                .setParameter("unidad", unidadMedida)
                .getSingleResult();
        return count > 0;
    }
    
     /**
     * Busca ingredientes filtrando por nombre y unidad de medida.
     *
     * @param nombre texto parcial del nombre del ingrediente a buscar; si es null, no se filtra por nombre
     * @param unidadMedida unidad de medida a filtrar; si es null, no se filtra por unidad
     * @return lista de ingredientes que coinciden con los filtros, en forma de IngredienteRegistradoDTO
     */
    @Override
    public List<IngredienteRegistradoDTO> buscarIngredientes(String nombre, String unidadMedida) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        String jpql = """
                      SELECT 
                      new itson.sistemarestaurantedominio.dtos.IngredienteRegistradoDTO(
                      i.id, i.nombre, i.unidadMedida, i.stock) 
                      FROM Ingrediente i
                      WHERE (:nombre IS NULL OR i.nombre LIKE :nombre)
                      AND (:unidadMedida IS NULL OR i.unidadMedida LIKE :unidadMedida)
                      """;
        TypedQuery<IngredienteRegistradoDTO> query = 
                entityManager.createQuery(jpql, IngredienteRegistradoDTO.class);
        query.setParameter("nombre", nombre != null ? "%" + nombre + "%" : null);
        query.setParameter("unidadMedida", unidadMedida != null ? "%" + unidadMedida + "%" : null);
        return query.getResultList();
    }
    
    /**
     * Actualiza el stock de un ingrediente existente en la base de datos.
     *
     * @param ingrediente DTO con el ID del ingrediente y el nuevo valor de stock
     * @return el objeto Ingrediente actualizado
     */
    @Override
    public Ingrediente actualizarStock(StockIngredienteActualizadoDTO ingrediente){
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        entityManager.getTransaction().begin();
        Ingrediente ingredienteExistente = 
                entityManager.find(Ingrediente.class, 
                        ingrediente.getId());
        ingredienteExistente.setStock(ingrediente.getStock());
        entityManager.getTransaction().commit();
        return ingredienteExistente;
    }
    
}
