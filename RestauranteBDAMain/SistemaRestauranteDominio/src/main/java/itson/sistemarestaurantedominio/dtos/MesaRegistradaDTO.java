package itson.sistemarestaurantedominio.dtos;

import itson.sistemarestaurantedominio.EstadoMesa;

public class MesaRegistradaDTO {

    private Long id;
    private int numeroMesa;
    private EstadoMesa estadoMesa;

    public MesaRegistradaDTO(Long id, int numeroMesa, EstadoMesa estadoMesa) {
        this.id = id;
        this.numeroMesa = numeroMesa;
        this.estadoMesa = estadoMesa;
    }

    public Long getId(){
        return id;
    }
    public int getNumeroMesa() {
        return numeroMesa;
    }

    public EstadoMesa getEstadoMesa() {
        return estadoMesa;
    }
}
