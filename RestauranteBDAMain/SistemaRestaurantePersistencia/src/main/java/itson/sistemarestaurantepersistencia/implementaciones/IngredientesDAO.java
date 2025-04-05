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

    @Override
    public List<IngredienteRegistradoDTO> obtenerInventarioIngredientes() {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        String jpqlQuery = "SELECT new itson.sistemarestaurantedominio.dtos.IngredienteRegistradoDTO(i.id, i.nombre, i.unidadMedida, i.stock) FROM Ingrediente i";
        //CONSULTO MI BD PARA QUE ME DEVUELVA LOS INGREDIENTES QUE TENGO
        TypedQuery<IngredienteRegistradoDTO> query = entityManager.createQuery(jpqlQuery, IngredienteRegistradoDTO.class);
        List<IngredienteRegistradoDTO> ingredientesDTO = query.getResultList();
        
        return ingredientesDTO;
    }

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
    
    @Override
    public List<IngredienteRegistradoDTO> buscarIngredientes(String filtroBusqueda, String unidadMedida) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        StringBuilder jpql= new StringBuilder("""
                                              SELECT 
                                              new itson.sistemarestaurantedominio.dtos.IngredienteRegistradoDTO(i.id, i.nombre, i.unidadMedida, i.stock) 
                                              FROM Ingrediente i
                                              WHERE 1 = 1"""
        );
        if (filtroBusqueda != null && !filtroBusqueda.trim().isEmpty()) 
            jpql.append(" AND LOWER(i.nombre) LIKE :nombre");
        if (!unidadMedida.equals("TODOS")) 
            jpql.append(" AND LOWER(i.unidadMedida) = :unidadMedida");
        
        TypedQuery<IngredienteRegistradoDTO> query = entityManager.
                createQuery(jpql.toString(), IngredienteRegistradoDTO.class);
        
        if (filtroBusqueda != null && !filtroBusqueda.trim().isEmpty()) 
            query.setParameter("nombre", "%" + filtroBusqueda + "%");
        if (!unidadMedida.equals("TODOS")) 
            query.setParameter("unidadMedida", unidadMedida);
        
        return query.getResultList();
    }
}
