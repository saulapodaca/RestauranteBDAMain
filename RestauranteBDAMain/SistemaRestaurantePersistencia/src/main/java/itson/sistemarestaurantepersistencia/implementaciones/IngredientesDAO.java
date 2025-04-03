package itson.sistemarestaurantepersistencia.implementaciones;

//@author SAUL ISAAC APODACA BALDENEGRO 00000252020

import itson.sistemarestaurantedominio.Ingrediente;
import itson.sistemarestaurantedominio.UnidadMedidaIngrediente;
import itson.sistemarestaurantedominio.dtos.IngredienteRegistradoDTO;
import itson.sistemarestaurantedominio.dtos.NuevoIngredienteDTO;
import itson.sistemarestaurantepersistencia.IIngredientesDAO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;


public class IngredientesDAO implements IIngredientesDAO {

    @Override
    public Ingrediente registrar(NuevoIngredienteDTO nuevoIngredienteDTO) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager(false);
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
        EntityManager entityManager = ManejadorConexiones.getEntityManager(false);
        String jpqlQuery = "SELECT new itson.sistemarestaurantedominio.dtos.IngredienteRegistradoDTO(i.nombre, i.unidadMedida) FROM Ingrediente i";
        //CONSULTO MI BD PARA QUE ME DEVUELVA LOS INGREDIENTES QUE TENGO
        TypedQuery<IngredienteRegistradoDTO> query = entityManager.createQuery(jpqlQuery, IngredienteRegistradoDTO.class);
        List<IngredienteRegistradoDTO> ingredientesDTO = query.getResultList();
        
        return ingredientesDTO;
    }

}
