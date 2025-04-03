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
        String jpqlQuery = "SELECT nombre, unidadMedida FROM Ingrediente";
        //CONSULTO MI BD PARA QUE ME DEVUELVA LOS INGREDIENTES QUE TENGO
        TypedQuery<Ingrediente> query = entityManager.createQuery(jpqlQuery, Ingrediente.class);
        List<Ingrediente> ingredientes = query.getResultList();
        
        //HAGO CONVERSIÓN A DTO
        List<IngredienteRegistradoDTO> ingredientesDTO = new ArrayList<>();
        for(Ingrediente ing: ingredientes)
            ingredientesDTO.add(new IngredienteRegistradoDTO(ing.getNombre(),ing.getUnidadMedida()));
        
        return ingredientesDTO;
    }

}
