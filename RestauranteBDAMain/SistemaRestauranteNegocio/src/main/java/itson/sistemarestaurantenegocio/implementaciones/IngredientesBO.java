package itson.sistemarestaurantenegocio.implementaciones;

//@author SAUL ISAAC APODACA BALDENEGRO 00000252020

import itson.sistemarestaurantedominio.Ingrediente;
import itson.sistemarestaurantedominio.UnidadMedidaIngrediente;
import itson.sistemarestaurantedominio.dtos.IngredienteRegistradoDTO;
import itson.sistemarestaurantedominio.dtos.NuevoIngredienteDTO;
import itson.sistemarestaurantenegocio.IIngredientesBO;
import itson.sistemarestaurantenegocio.excepciones.IngredienteRegistradoException;
import itson.sistemarestaurantenegocio.excepciones.NombreInvalidoException;
import itson.sistemarestaurantenegocio.excepciones.StockInvalidoException;
import itson.sistemarestaurantepersistencia.IIngredientesDAO;
import java.util.List;
import java.util.regex.Pattern;


public class IngredientesBO implements IIngredientesBO{

    private IIngredientesDAO ingredientesDAO;
    
    public IngredientesBO (IIngredientesDAO ingredientesDAO){
        this.ingredientesDAO = ingredientesDAO;
    }
    
    @Override
    public Ingrediente registrar(NuevoIngredienteDTO nuevoIngredienteDTO) throws NombreInvalidoException, StockInvalidoException, IngredienteRegistradoException{
        validarNombre(nuevoIngredienteDTO.getNombre());
        validarStock(nuevoIngredienteDTO.getStock());
        validarExistenciaInventario(nuevoIngredienteDTO.getNombre(), nuevoIngredienteDTO.getUnidadMedidaIngrediente());
        return ingredientesDAO.registrar(nuevoIngredienteDTO);
    }

    public void validarNombre(String nombre) throws NombreInvalidoException {
        if(nombre == null || nombre.trim().isEmpty())
            throw new NombreInvalidoException("El nombre del ingrediente no puede estar vacío.");
        if(!Pattern.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", nombre))
            throw new NombreInvalidoException("El nombre del ingrediente no puede contener números ni carácteres especiales,");
    }
    
    public void validarStock(float stock) throws StockInvalidoException{
        if(stock<0)
            throw new StockInvalidoException("El stock no puede ser negativo");
    }
    
    public void validarExistenciaInventario(String nombre, UnidadMedidaIngrediente unidadMedidaIngrediente) throws IngredienteRegistradoException{
        List<IngredienteRegistradoDTO> ingredientesDTO = ingredientesDAO.obtenerInventarioIngredientes();
        //TODO NO DEBE DE RECORRER QUE SEA UNA CONSULTA
        for(IngredienteRegistradoDTO ing: ingredientesDTO)
            if(ing.getNombre().equalsIgnoreCase(nombre) && ing.getUnidadMedidaIngrediente().equals(unidadMedidaIngrediente))
                throw new IngredienteRegistradoException("El ingrediente ya se encuentra registrado");
    }
    
}
