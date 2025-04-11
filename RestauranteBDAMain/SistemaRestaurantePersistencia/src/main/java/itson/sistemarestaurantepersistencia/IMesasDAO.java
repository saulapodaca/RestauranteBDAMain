package itson.sistemarestaurantepersistencia;

import itson.sistemarestaurantedominio.Mesa;
import itson.sistemarestaurantedominio.dtos.MesaRegistradaDTO;
import itson.sistemarestaurantedominio.dtos.NuevaMesaDTO;

public interface IMesasDAO {

    public abstract Mesa registrar(NuevaMesaDTO nuevaMesa);

    public Mesa cambiarEstado(MesaRegistradaDTO mesaRegistrada, String estadoNuevo);

    public MesaRegistradaDTO obtenerMesa(Integer numMesa);

    public boolean existeMesa(Integer numMesa);

    public void insertarMesasIniciales();

}
