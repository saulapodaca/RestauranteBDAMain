package itson.sistemarestaurantedominio.dtos;

public class RelacionProductoIngredienteRegistradaDTO {

    private Long idRelacion;
    private Long idIngrediente;
    private Long idProducto;

    public RelacionProductoIngredienteRegistradaDTO(Long idRelacion, Long idIngrediente, Long idProducto) {
        this.idRelacion = idRelacion;
        this.idIngrediente = idIngrediente;
        this.idProducto = idProducto;
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
       
}
