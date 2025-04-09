package itson.sistemarestaurantepersistencia.implementaciones;

//@author SAUL ISAAC APODACA BALDENEGRO 00000252020

import itson.sistemarestaurantedominio.Ingrediente;
import itson.sistemarestaurantedominio.UnidadMedidaIngrediente;
import itson.sistemarestaurantedominio.dtos.IngredienteRegistradoDTO;
import itson.sistemarestaurantedominio.dtos.NuevoIngredienteDTO;
import itson.sistemarestaurantedominio.dtos.StockIngredienteActualizadoDTO;
import itson.sistemarestaurantepersistencia.IIngredientesDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;


public class IngredientesDAO implements IIngredientesDAO {

    /**
     * Método que registra el ingrediente en la base de datos
     * @param nuevoIngredienteDTO
     * @return ingrediente registrado
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
     * Método que regresa un boleano si es que el ingrediente a registrar existe ya dentro de la base de datos
     * @param nombre del ingrediente
     * @param unidadMedida del ingrediente
     * @return true si es que ya éxiste dentro de la base de datos, false de lo contrario
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
     * Método que busca el inventario de ingredientes a la base de datos
     * @param nombre por el nombre que se buscará, si es null mandará todos los ingredientes
     * @param unidadMedida por la unidad de medida por la que se filtrará, si es null mandará todos
     * @return regresa la lista de ingredientes filtrados DTO
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
