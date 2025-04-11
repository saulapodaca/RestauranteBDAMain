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

    /**
     * Registra un nuevo producto en la base de datos a partir del DTO
     * proporcionado.
     *
     * @param nuevoProductoDTO Objeto con la información del producto a
     * registrar.
     * @return La entidad Producto registrada con su ID generado.
     */
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

    /**
     * Busca productos en la base de datos filtrando opcionalmente por nombre y
     * tipo de producto. Si alguno de los parámetros es null, ese filtro se
     * omite.
     *
     * @param nombre El nombre (o parte del nombre) del producto a buscar. Puede
     * ser null.
     * @param tipoProducto El tipo de producto a buscar. Puede ser null.
     * @return Lista de productos que coinciden con los filtros proporcionados.
     */
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
        TypedQuery<ProductoRegistradoDTO> query
                = entityManager.createQuery(jpql, ProductoRegistradoDTO.class);
        query.setParameter("nombre", 
                nombre != null ? "%" + nombre + "%" : null);
        query.setParameter("tipoProducto", 
                tipoProducto != null ? "%" + tipoProducto + "%" : null);
        return query.getResultList();
    }

    /**
     * Verifica si ya existe un producto con el nombre y tipo especificados.
     *
     * @param nombre El nombre del producto a verificar.
     * @param tipo El tipo del producto a verificar.
     * @return true si existe un producto con ese nombre y tipo, false en caso
     * contrario.
     */
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

    /**
     * Actualiza un producto existente con los valores proporcionados en el DTO.
     * Solo se actualizan los campos que no son null en el DTO.
     *
     * @param productoActualizadoDTO DTO con los datos a actualizar.
     * @return El producto actualizado.
     */
    @Override
    public Producto actualizarProducto(ProductoActualizadoDTO productoActualizadoDTO) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        entityManager.getTransaction().begin();
        Producto productoExistente = entityManager
                .find(Producto.class, productoActualizadoDTO.getId());
        
        if (productoActualizadoDTO.getNombre() != null) 
            productoExistente.setNombre(productoActualizadoDTO.getNombre());        
        if (productoActualizadoDTO.getPrecio() != null)
            productoExistente.setPrecio(productoActualizadoDTO.getPrecio());
        
        entityManager.getTransaction().commit();
        return productoExistente;
    }

    /**
     * Obtiene un producto a partir de su ID.
     *
     * @param id El ID del producto a buscar.
     * @return El producto si existe, o null si no se encuentra.
     */
    @Override
    public Producto obtenerProducto(Long id) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        return entityManager.find(Producto.class, id);
    }
}
