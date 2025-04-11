/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.sistemarestaurantenegocio;

import itson.sistemarestaurantedominio.dtos.MesaRegistradaDTO;
import java.util.List;

/**
 *
 * @author saula
 */
public interface IMesasBO {

    public abstract void insertarMesasIniciales();

    public abstract List<MesaRegistradaDTO> obtenerMesas();

}
