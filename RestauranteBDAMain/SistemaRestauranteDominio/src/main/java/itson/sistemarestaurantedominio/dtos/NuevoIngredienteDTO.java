package itson.sistemarestaurantedominio.dtos;

//@author SAUL ISAAC APODACA BALDENEGRO 00000252020

import itson.sistemarestaurantedominio.UnidadMedidaIngrediente;


public class NuevoIngredienteDTO {
    private String nombre;
    private UnidadMedidaIngrediente unidadMedidaIngrediente;
    private Integer stock;

    public NuevoIngredienteDTO(String nombre, UnidadMedidaIngrediente unidadMedidaIngrediente, Integer stock) {
        this.nombre = nombre;
        this.unidadMedidaIngrediente = unidadMedidaIngrediente;
        this.stock = stock;
    }

    public String getNombre() {
        return nombre;
    }

    public UnidadMedidaIngrediente getUnidadMedidaIngrediente() {
        return unidadMedidaIngrediente;
    }

    public Integer getStock() {
        return stock;
    }
       
}
