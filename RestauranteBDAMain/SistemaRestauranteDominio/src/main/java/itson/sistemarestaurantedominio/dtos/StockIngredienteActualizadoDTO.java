package itson.sistemarestaurantedominio.dtos;

//@author SAUL ISAAC APODACA BALDENEGRO 00000252020

public class StockIngredienteActualizadoDTO {
    private Long id;
    private Integer stock;
    
    public StockIngredienteActualizadoDTO(Long id, Integer stock){
        this.id = id;
        this.stock = stock;
    }
    
    public Long getId(){
        return id;
    }
    
    public Integer getStock(){
        return stock;
    }
}
