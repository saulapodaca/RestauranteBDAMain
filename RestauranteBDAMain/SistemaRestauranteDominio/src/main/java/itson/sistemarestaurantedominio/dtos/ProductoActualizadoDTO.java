package itson.sistemarestaurantedominio.dtos;

//@author SAUL ISAAC APODACA BALDENEGRO 00000252020

public class ProductoActualizadoDTO {

    private Long id;
    private String nombre;
    private Float precio;

    public ProductoActualizadoDTO(Long id, String nombre, float precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    public ProductoActualizadoDTO(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public ProductoActualizadoDTO(Long id, Float precio) {
        this.id = id;
        this.precio = precio;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Float getPrecio() {
        return precio;
    }
    
}
