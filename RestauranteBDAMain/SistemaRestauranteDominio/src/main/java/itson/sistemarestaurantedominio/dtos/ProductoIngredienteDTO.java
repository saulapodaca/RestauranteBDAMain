package itson.sistemarestaurantedominio.dtos;

import itson.sistemarestaurantedominio.Ingrediente;

public class ProductoIngredienteDTO {
    private Ingrediente ingrediente;
    private String unidad;
    private float cantidad;

    public ProductoIngredienteDTO(Ingrediente ingrediente, String unidad, float cantidad) {
        this.ingrediente = ingrediente;
        this.unidad = unidad;
        this.cantidad = cantidad;
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    public String getUnidad() {
        return unidad;
    }

    public float getCantidad() {
        return cantidad;
    }
}
