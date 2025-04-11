package itson.sistemarestaurantepersistencia.implementaciones;

import itson.sistemarestaurantedominio.Ingrediente;
import itson.sistemarestaurantedominio.Producto;
import itson.sistemarestaurantedominio.ProductoIngrediente;
import itson.sistemarestaurantedominio.dtos.NuevaRelacionProductoIngredienteDTO;
import itson.sistemarestaurantedominio.dtos.ProductoRegistradoDTO;
import itson.sistemarestaurantedominio.dtos.RelacionProductoIngredienteRegistradaDTO;
import itson.sistemarestaurantepersistencia.IProductosIngredientesDAO;
import itson.sistemarestaurantepersistencia.excepciones.PersistenciaException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Implementación de la interfaz IProductosIngredientesDAO para gestionar las
 * relaciones entre productos e ingredientes en la base de datos.
 */
public class ProductoIngredientesDAO implements IProductosIngredientesDAO {

    /**
     * Registra una nueva relación entre un producto y un ingrediente en la base
     * de datos.
     *
     * @param relacionProductoIngrediente DTO que contiene la información de la
     * relación a registrar.
     * @return La entidad ProductoIngrediente registrada.
     * @throws PersistenciaException Si el producto o ingrediente especificado
     * no existe.
     */
    @Override
    public ProductoIngrediente registrar(NuevaRelacionProductoIngredienteDTO relacionProductoIngrediente)
            throws PersistenciaException {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        entityManager.getTransaction().begin();
        ProductoIngrediente relacion = new ProductoIngrediente();

        Ingrediente ingrediente = entityManager
                .find(Ingrediente.class,
                        relacionProductoIngrediente.getIdIngrediente());
        if (ingrediente == null) {
            throw new PersistenciaException("El ingrediente no existe.");
        }

        Producto producto = entityManager
                .find(Producto.class,
                        relacionProductoIngrediente.getIdProducto());
        if (producto == null) {
            throw new PersistenciaException("El producto no existe.");
        }

        relacion.setIngrediente(ingrediente);
        relacion.setProducto(producto);
        relacion.setCantidad(relacionProductoIngrediente.getCantidad());

        entityManager.persist(relacion);
        entityManager.getTransaction().commit();

        return relacion;
    }

    /**
     * Elimina una relación existente entre un producto y un ingrediente.
     *
     * @param relacionProductoIngrediente DTO que contiene el ID de la relación
     * a eliminar.
     */
    @Override
    public void eliminarRelacion(RelacionProductoIngredienteRegistradaDTO relacionProductoIngrediente) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        entityManager.getTransaction().begin();
        ProductoIngrediente relacion = entityManager
                .find(ProductoIngrediente.class,
                        relacionProductoIngrediente.getIdRelacion());
        entityManager.remove(relacion);
        entityManager.getTransaction().commit();
    }

    /**
     * Verifica si existe una relación entre un producto y un ingrediente.
     *
     * @param idProducto ID del producto.
     * @param idIngrediente ID del ingrediente.
     * @return true si existe la relación, false en caso contrario.
     */
    @Override
    public boolean existeRelacion(Long idProducto, Long idIngrediente) {
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

    /**
     * Obtiene la lista de ingredientes asociados a un producto específico.
     *
     * @param producto DTO del producto registrado, que contiene el ID del
     * producto.
     * @return Lista de objetos ProductoIngrediente asociados al producto.
     */
    @Override
    public List<ProductoIngrediente> obtenerListaIngredientesDeProducto(ProductoRegistradoDTO producto) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProductoIngrediente> cq = cb.createQuery(ProductoIngrediente.class);
        Root<ProductoIngrediente> relacion = cq.from(ProductoIngrediente.class);
        cq.select(relacion);
        Predicate idProducto = cb.equal(
                relacion.get("idProducto"),
                producto.getIdProducto());
        cq.where(idProducto);
        TypedQuery<ProductoIngrediente> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

}
