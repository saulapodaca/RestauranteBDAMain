package itson.sistemarestaurantedominio.dtos;

//@author SAUL ISAAC APODACA BALDENEGRO 00000252020

import itson.sistemarestaurantedominio.UnidadMedidaIngrediente;


public class NuevoIngredienteDTO {
    private String nombre;
    private UnidadMedidaIngrediente unidadMedidaIngrediente;
    private float stock;

    public NuevoIngredienteDTO(String nombre, UnidadMedidaIngrediente unidadMedidaIngrediente, float stock) {
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

    public float getStock() {
        return stock;
    }
       
}
