package itson.sistemarestaurantepersistencia.implementaciones;

import itson.sistemarestaurantedominio.Producto;
import itson.sistemarestaurantedominio.dtos.NuevoProductoDTO;
import itson.sistemarestaurantepersistencia.IProductosDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
//import java.sql.Connection;

/**
 *
 * @author Felix isaac sanchez quintero
 */
public class ProductosDAO implements IProductosDAO{

    // private Connection connection;

   private EntityManager entityManager;

    public ProductosDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
   public Producto registrar(NuevoProductoDTO nuevoProductoDTO){
    entityManager.getTransaction().begin();
    Producto producto = new Producto();
    producto.setNombre(nuevoProductoDTO.getNombre());
    producto.setPrecio(nuevoProductoDTO.getPrecio());
    producto.setTipoProducto(nuevoProductoDTO.getTipoProducto());
    
    entityManager.persist(producto);
    entityManager.getTransaction().commit();
    return producto;
   }
  
   public List<Producto> buscarProductos(String filtro){
       if (filtro == null || filtro.trim().isEmpty()) {
           return new ArrayList<>();
       }
       String jpql = "SELECT p FROM Producto p WHERE p.nombre LIKE :filtro OR p.tipoProducto LIKE :filtro";
       TypedQuery<Producto> query = entityManager.createQuery(jpql,Producto.class);
       query.setParameter("filtro", "%" + filtro.trim() + "%");
       return query.getResultList();
   }
    
}
