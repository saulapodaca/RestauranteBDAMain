package itson.sistemarestaurantenegocio.implementaciones;

//@author SAUL ISAAC APODACA BALDENEGRO 00000252020

import itson.sistemarestaurantedominio.dtos.MesaRegistradaDTO;
import itson.sistemarestaurantenegocio.IMesasBO;
import itson.sistemarestaurantepersistencia.IMesasDAO;
import java.util.List;


public class MesasBO implements IMesasBO {
    
    private IMesasDAO mesasDAO;
    
    public MesasBO(IMesasDAO mesasDAO){
        this.mesasDAO = mesasDAO;
    }
    public void insertarMesasIniciales(){
        mesasDAO.insertarMesasIniciales();
    }
    
    public List<MesaRegistradaDTO> obtenerMesas(){
        return mesasDAO.obtenerMesas();
    }
}
