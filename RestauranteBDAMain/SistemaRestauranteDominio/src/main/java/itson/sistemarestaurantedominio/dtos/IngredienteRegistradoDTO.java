package itson.sistemarestaurantedominio.dtos;

//@author SAUL ISAAC APODACA BALDENEGRO 00000252020

import itson.sistemarestaurantedominio.UnidadMedidaIngrediente;


public class IngredienteRegistradoDTO {
    private Long id;
    private String nombre;
    private UnidadMedidaIngrediente unidadMedidaIngrediente;
    private Integer stock;

    public IngredienteRegistradoDTO(Long id, String nombre, UnidadMedidaIngrediente unidadMedidaIngrediente, Integer stock) {
        this.id = id;
        this.nombre = nombre;
        this.unidadMedidaIngrediente = unidadMedidaIngrediente;
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public Integer getStock() {
        return stock;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public UnidadMedidaIngrediente getUnidadMedidaIngrediente() {
        return unidadMedidaIngrediente;
    }
    
    public void setStock(Integer stock){
        this.stock = stock;
    }

}
