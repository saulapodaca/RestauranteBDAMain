package itson.sistemarestaurantedominio.dtos;

public class NuevaRelacionProductoIngredienteDTO {

    private Long idProducto;
    private Long idIngrediente;
    private Integer cantidad;

    public NuevaRelacionProductoIngredienteDTO() {
    }

    public NuevaRelacionProductoIngredienteDTO(Long idProducto, Long idIngrediente, Integer cantidad) {
        this.idProducto = idProducto;
        this.idIngrediente = idIngrediente;
        this.cantidad = cantidad;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public Long getIdIngrediente() {
        return idIngrediente;
    }
    
    public Integer getCantidad(){
        return cantidad;
    }

    public void setCantidad(Integer cantidad){
        this.cantidad = cantidad;
    }
    
    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public void setIdIngrediente(Long idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

}
