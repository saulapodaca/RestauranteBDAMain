package itson.sistemarestaurantedominio.dtos;

//@author SAUL ISAAC APODACA BALDENEGRO 00000252020

import itson.sistemarestaurantedominio.UnidadMedidaIngrediente;


public class IngredienteRegistradoDTO {
    private String nombre;
    private UnidadMedidaIngrediente unidadMedidaIngrediente;

    public IngredienteRegistradoDTO(String nombre, UnidadMedidaIngrediente unidadMedidaIngrediente) {
        this.nombre = nombre;
        this.unidadMedidaIngrediente = unidadMedidaIngrediente;
    }

    public String getNombre() {
        return nombre;
    }

    public UnidadMedidaIngrediente getUnidadMedidaIngrediente() {
        return unidadMedidaIngrediente;
    }

}
