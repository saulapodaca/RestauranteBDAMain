package itson.sistemarestaurantedominio.dtos;

public class NuevaRelacionProductoIngredienteDTO {

    private Long idProducto;
    private Long idIngrediente;

    public NuevaRelacionProductoIngredienteDTO() {
    }

    public NuevaRelacionProductoIngredienteDTO(Long idProducto, Long idIngrediente) {
        this.idProducto = idProducto;
        this.idIngrediente = idIngrediente;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public Long getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public void setIdIngrediente(Long idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

}
