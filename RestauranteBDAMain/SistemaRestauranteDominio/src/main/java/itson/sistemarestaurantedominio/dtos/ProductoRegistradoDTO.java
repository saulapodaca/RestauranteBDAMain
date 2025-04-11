package itson.sistemarestaurantedominio.dtos;

//@author SAUL ISAAC APODACA BALDENEGRO 00000252020

import itson.sistemarestaurantedominio.TipoProducto;


public class ProductoRegistradoDTO {
  
    private Long idProducto;
    private String nombre;
    private TipoProducto tipoProducto;
    private Float precio;

    public ProductoRegistradoDTO(Long idProducto, String nombre, TipoProducto tipoProducto, float precio) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.tipoProducto = tipoProducto;
        this.precio = precio;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }

    public float getPrecio() {
        return precio;
    }    
    
}
