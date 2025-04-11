package itson.sistemarestaurantepersistencia.implementaciones;

//@author SAUL ISAAC APODACA BALDENEGRO 00000252020

import itson.sistemarestaurantedominio.Ingrediente;
import itson.sistemarestaurantedominio.Producto;
import itson.sistemarestaurantedominio.ProductoIngrediente;
import itson.sistemarestaurantedominio.dtos.NuevaRelacionProductoIngredienteDTO;
import itson.sistemarestaurantedominio.dtos.RelacionProductoIngredienteRegistradaDTO;
import itson.sistemarestaurantepersistencia.IProductosIngredientesDAO;
import itson.sistemarestaurantepersistencia.excepciones.PersistenciaException;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


public class ProductoIngredientesDAO implements IProductosIngredientesDAO {

    public ProductoIngrediente registrar(NuevaRelacionProductoIngredienteDTO relacionProductoIngrediente) 
            throws PersistenciaException{
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        entityManager.getTransaction().begin();
        ProductoIngrediente relacion = new ProductoIngrediente();
        
        Ingrediente ingrediente = entityManager
                .find(Ingrediente.class, 
                        relacionProductoIngrediente.getIdIngrediente());
        if (ingrediente == null)
            throw new PersistenciaException("El ingrediente no existe.");
        
        Producto producto = entityManager
                .find(Producto.class,
                        relacionProductoIngrediente.getIdProducto());
        if (producto == null)
            throw new PersistenciaException("El producto no existe.");

        relacion.setIngrediente(ingrediente);
        relacion.setProducto(producto);
        
        entityManager.persist(relacion);
        entityManager.getTransaction().commit();
        
        return relacion;
    }
    
    public void eliminarRelacion(RelacionProductoIngredienteRegistradaDTO relacionProductoIngrediente) 
            throws PersistenciaException{
        EntityManager entityManager = ManejadorConexiones.getEntityManager();       
        entityManager.getTransaction().begin();
        ProductoIngrediente relacion = entityManager
                .find(ProductoIngrediente.class, 
                        relacionProductoIngrediente.getIdRelacion());
        entityManager.remove(relacion);
        entityManager.getTransaction().commit();
    }
    
    public boolean existeRelacion(Long idProducto, Long idIngrediente){
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        
        Root<ProductoIngrediente> relacion = cq.from(ProductoIngrediente.class);
        
        cq.select(cb.count(relacion));
        
        Predicate ingredientePredicate = cb.equal(
                relacion.get("idIngrediente"), 
                idIngrediente);
        Predicate ProductoPrecPredicate = cb.equal(
                relacion.get("idProducto"), 
                idProducto);
        
        cq.where(cb.and(ingredientePredicate, ProductoPrecPredicate));
        
        Long count = entityManager.createQuery(cq).getSingleResult();
        return count > 0;
    }
    
    
}
