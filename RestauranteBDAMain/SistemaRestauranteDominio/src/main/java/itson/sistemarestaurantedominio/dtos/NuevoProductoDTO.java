package itson.sistemarestaurantedominio.dtos;

import itson.sistemarestaurantedominio.Ingrediente;
import itson.sistemarestaurantedominio.TipoProducto;
import java.util.List;

public class NuevoProductoDTO {
    private String nombre;
    private float precio;
    private TipoProducto tipoProducto;
    private List<Ingrediente> ingredientes;
    
    public NuevoProductoDTO(String nombre, float precio, TipoProducto tipoProducto) {
        this.nombre = nombre;
        this.precio = precio;
        this.tipoProducto = tipoProducto;
    }

    public NuevoProductoDTO(String nombre, float precio, TipoProducto tipoProducto, List<Ingrediente> ingredientes) {
        this.nombre = nombre;
        this.precio = precio;
        this.tipoProducto = tipoProducto;
        this.ingredientes = ingredientes;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Ingrediente> getIngredientes(){
        return ingredientes;
    }
    
    public float getPrecio() {
        return precio;
    }

    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }
    
   
    
}
