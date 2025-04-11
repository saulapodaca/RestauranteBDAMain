package itson.sistemarestaurantepersistencia;

import itson.sistemarestaurantedominio.Mesa;
import itson.sistemarestaurantedominio.dtos.MesaRegistradaDTO;
import itson.sistemarestaurantedominio.dtos.NuevaMesaDTO;
import java.util.List;

public interface IMesasDAO {

    public abstract Mesa registrar(NuevaMesaDTO nuevaMesa);

    public abstract Mesa cambiarEstado(MesaRegistradaDTO mesaRegistrada, String estadoNuevo);

    public abstract MesaRegistradaDTO obtenerMesa(Integer numMesa);

    public abstract boolean existeMesa(Integer numMesa);

    public abstract void insertarMesasIniciales();
    
    public abstract List<MesaRegistradaDTO> obtenerMesas();

}
