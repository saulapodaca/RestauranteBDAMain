package itson.sistemarestaurantedominio.dtos;

public class RelacionProductoIngredienteRegistradaDTO {

    private Long idRelacion;
    private Long idIngrediente;
    private Long idProducto;
    private Integer cantidad;

    public RelacionProductoIngredienteRegistradaDTO(Long idRelacion, Long idIngrediente, Long idProducto, Integer cantidad) {
        this.idRelacion = idRelacion;
        this.idIngrediente = idIngrediente;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }

    public Long getIdRelacion() {
        return idRelacion;
    }

    public Long getIdIngrediente() {
        return idIngrediente;
    }

    public Long getIdProducto() {
        return idProducto;
    }
    
    public Integer getCantidad(){
        return cantidad;
    }
       
}
