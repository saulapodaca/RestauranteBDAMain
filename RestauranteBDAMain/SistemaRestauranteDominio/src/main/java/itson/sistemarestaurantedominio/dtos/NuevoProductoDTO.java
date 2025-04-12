package itson.sistemarestaurantedominio.dtos;

import itson.sistemarestaurantedominio.TipoProducto;

public class NuevoProductoDTO {
    private String nombre;
    private float precio;
    private TipoProducto tipoProducto;
    
    public NuevoProductoDTO(String nombre, float precio, TipoProducto tipoProducto) {
        this.nombre = nombre;
        this.precio = precio;
        this.tipoProducto = tipoProducto;
    }

    public String getNombre() {
        return nombre;
    }
    
    public float getPrecio() {
        return precio;
    }

    public TipoProducto getTipoProducto() {
        return tipoProducto;
    } 
    
}
