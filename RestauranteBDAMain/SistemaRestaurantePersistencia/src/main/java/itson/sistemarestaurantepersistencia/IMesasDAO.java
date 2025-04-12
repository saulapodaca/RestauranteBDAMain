package itson.sistemarestaurantepersistencia;

import itson.sistemarestaurantedominio.Mesa;
import itson.sistemarestaurantedominio.dtos.MesaRegistradaDTO;
import java.util.List;

public interface IMesasDAO {

    public abstract Mesa cambiarEstado(MesaRegistradaDTO mesaRegistrada, String estadoNuevo);

    public abstract void insertarMesasIniciales();
    
    public abstract List<MesaRegistradaDTO> obtenerMesas();

}
