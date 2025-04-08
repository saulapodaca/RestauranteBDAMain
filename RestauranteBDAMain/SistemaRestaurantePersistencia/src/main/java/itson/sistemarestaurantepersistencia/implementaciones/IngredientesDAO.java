package itson.sistemarestaurantepersistencia.implementaciones;

//@author SAUL ISAAC APODACA BALDENEGRO 00000252020

import itson.sistemarestaurantedominio.Ingrediente;
import itson.sistemarestaurantedominio.UnidadMedidaIngrediente;
import itson.sistemarestaurantedominio.dtos.IngredienteRegistradoDTO;
import itson.sistemarestaurantedominio.dtos.NuevoIngredienteDTO;
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
     * @param filtroBusqueda por el nombre que se buscará, si es null mandará todos los ingredientes
     * @param unidadMedida por la unidad de medida por la que se filtrará, si es null mandará todos
     * @return regresa la lista de ingredientes filtrados DTO
     */
    @Override
    public List<IngredienteRegistradoDTO> buscarIngredientes(String filtroBusqueda, UnidadMedidaIngrediente unidadMedida) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        // El where es para que siempre se cumpla la condición y poder concatenar los otros filtros sin problemas
        StringBuilder jpql= new StringBuilder("""
                                              SELECT 
                                              new itson.sistemarestaurantedominio.dtos.IngredienteRegistradoDTO(i.id, i.nombre, i.unidadMedida, i.stock) 
                                              FROM Ingrediente i
                                              WHERE 1 = 1"""
        );
        
        //Si el filtro de busqueda contiene información concatenará la siguiente parte de la consulta
        if (filtroBusqueda != null && !filtroBusqueda.trim().isEmpty()) 
            jpql.append(" AND LOWER(i.nombre) LIKE :nombre");
        //si la unidad de medida contiene información concatenará la siguiente parte de la consulta
        if (unidadMedida != null) 
            jpql.append(" AND i.unidadMedida = :unidadMedida");
        
        TypedQuery<IngredienteRegistradoDTO> query = entityManager.
                createQuery(jpql.toString(), IngredienteRegistradoDTO.class);
        
        //Cuando se cumplen los filtros pasados y construir el query es necesario mandarle por lo que filtrará
        //Entonces si el filtro de busqueda contiene información, reemplazará el filtro de busqueda por lo que se mandó
        if (filtroBusqueda != null && !filtroBusqueda.trim().isEmpty()) 
            query.setParameter("nombre", "%" + filtroBusqueda + "%");
        //Si la undidad de medida contiene información, se reemplazará la unidad que se busca
        if (unidadMedida != null) 
            query.setParameter("unidadMedida", unidadMedida);
        
        return query.getResultList();
    }
}
