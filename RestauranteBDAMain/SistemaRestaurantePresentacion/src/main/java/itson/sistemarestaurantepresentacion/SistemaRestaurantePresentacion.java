/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package itson.sistemarestaurantepresentacion;


import itson.sistemarestaurantenegocio.fabrica.FabricaObjNegocio;
import itson.sistemarestaurantenegocio.IClientesFrecuentesBO;

/**
 *
 * @author saula
 */
public class SistemaRestaurantePresentacion {

    public static void main(String[] args) {
        IClientesFrecuentesBO clientesFrecuentesBO = FabricaObjNegocio.crearClientesFrecuentesBO();
        RegistrarClienteFrecuenteForm prueba = new RegistrarClienteFrecuenteForm(clientesFrecuentesBO);
        prueba.setVisible(true);
        
    }
}
