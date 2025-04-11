package itson.sistemarestaurantepersistencia.implementaciones;

import itson.sistemarestaurantedominio.Producto;
import itson.sistemarestaurantedominio.TipoProducto;
import itson.sistemarestaurantedominio.dtos.NuevoProductoDTO;
import itson.sistemarestaurantedominio.dtos.ProductoActualizadoDTO;
import itson.sistemarestaurantedominio.dtos.ProductoRegistradoDTO;
import itson.sistemarestaurantepersistencia.IProductosDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Predicate;

public class ProductosDAO implements IProductosDAO {

    @Override
    public Producto registrar(NuevoProductoDTO nuevoProductoDTO) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        entityManager.getTransaction().begin();
        Producto producto = new Producto();
        producto.setNombre(nuevoProductoDTO.getNombre());
        producto.setPrecio(nuevoProductoDTO.getPrecio());
        producto.setTipoProducto(nuevoProductoDTO.getTipoProducto());
        entityManager.persist(producto);
        entityManager.getTransaction().commit();
        return producto;
    }

    @Override
    public List<ProductoRegistradoDTO> buscarProductos(String nombre, String tipoProducto) {
 EntityManager entityManager = ManejadorConexiones.getEntityManager();
        String jpql = """
                      SELECT 
                      new itson.sistemarestaurantedominio.dtos.ProductoRegistradoDTO(
                      p.id, p.nombre, p.tipoProducto, p.precio) 
                      FROM Producto p
                      WHERE (:nombre IS NULL OR p.nombre LIKE :nombre)
                      AND (:tipoProducto IS NULL OR p.tipoProducto LIKE :tipoProducto)
                      """;
        TypedQuery<ProductoRegistradoDTO> query = 
                entityManager.createQuery(jpql, ProductoRegistradoDTO.class);
        query.setParameter("nombre", nombre != null ? "%" + nombre + "%" : null);
        query.setParameter("tipoProducto", tipoProducto != null ? "%" + tipoProducto + "%" : null);
        return query.getResultList();
    }

    @Override
    public boolean existenteProducto(String nombre, TipoProducto tipo) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);

        Root<Producto> producto = cq.from(Producto.class);

        cq.select(cb.count(producto));

        Predicate nombrePredicate = cb.equal(
                producto.get("nombre"), nombre
        );
        
        Predicate tipoPredicate = cb.equal(
                producto.get("tipoProducto"), tipo
        );
        
        cq.where(cb.and(nombrePredicate, tipoPredicate));

        Long count = entityManager.createQuery(cq).getSingleResult();
        return count > 0;
    }

    @Override
    public Producto actualizarProducto(ProductoActualizadoDTO productoActualizadoDTO){
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        entityManager.getTransaction().begin();
        Producto productoExistente = entityManager
                .find(Producto.class, productoActualizadoDTO.getId());
        if(productoActualizadoDTO.getNombre()!=null)
            productoExistente.setNombre(productoActualizadoDTO.getNombre());
        if(productoActualizadoDTO.getPrecio() != null)
            productoExistente.setPrecio(productoActualizadoDTO.getPrecio());
        entityManager.getTransaction().commit();
        return productoExistente;
    }
    
    @Override 
    public Producto obtenerProducto(Long id){
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        return entityManager.find(Producto.class, id);
    }
}
