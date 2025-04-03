package itson.sistemarestaurantedominio.dtos;

//@author SAUL ISAAC APODACA BALDENEGRO 00000252020

import itson.sistemarestaurantedominio.UnidadMedidaIngrediente;


public class IngredienteRegistradoDTO {
    private Long id;
    private String nombre;
    private UnidadMedidaIngrediente unidadMedidaIngrediente;
    private float stock;

    public IngredienteRegistradoDTO(Long id, String nombre, UnidadMedidaIngrediente unidadMedidaIngrediente, float stock) {
        this.id = id;
        this.nombre = nombre;
        this.unidadMedidaIngrediente = unidadMedidaIngrediente;
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getStock() {
        return stock;
    }

    public void setStock(float stock) {
        this.stock = stock;
    }
    
    public String getNombre() {
        return nombre;
    }

    public UnidadMedidaIngrediente getUnidadMedidaIngrediente() {
        return unidadMedidaIngrediente;
    }

}
