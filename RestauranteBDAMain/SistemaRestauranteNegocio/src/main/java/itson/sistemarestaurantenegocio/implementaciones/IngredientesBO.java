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

    private static final String PATRON_TEXTO_VALIDO = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$";
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
        if(!Pattern.matches(PATRON_TEXTO_VALIDO, nombre))
            throw new NombreInvalidoException("El nombre del ingrediente no puede contener números ni carácteres especiales,");
    }
    
    public void validarStock(Integer stock) throws StockInvalidoException{
        if(stock<0)
            throw new StockInvalidoException("El stock no puede ser negativo");
    }
    
    public void validarExistenciaInventario(String nombre, UnidadMedidaIngrediente unidadMedidaIngrediente) throws IngredienteRegistradoException {
        if (ingredientesDAO.existeIngrediente(nombre, unidadMedidaIngrediente)) {
            throw new IngredienteRegistradoException("El ingrediente ya se encuentra registrado");
        }
    }

    @Override
    public void validacionesIniciales(String nombre,String nombreDefault, String stock, String stockDefault) throws NombreInvalidoException, StockInvalidoException{
        if (nombre.equals(nombreDefault) || nombre.isBlank() || nombre.isEmpty())
            throw new NombreInvalidoException("El nombre no puede estar vacío");
        if (stock.equals(stockDefault) || stock.isBlank())
            throw new StockInvalidoException("El stock inicial no puede estar vacío");
        try{
            Integer s = Integer.valueOf(stock);
        }catch(NumberFormatException e){
            throw new StockInvalidoException("El stock solo admite números enteros");
        }
    }
}
