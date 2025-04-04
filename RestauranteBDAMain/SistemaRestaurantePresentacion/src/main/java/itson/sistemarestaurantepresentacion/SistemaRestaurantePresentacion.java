/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package itson.sistemarestaurantepresentacion;


import itson.sistemarestaurantenegocio.fabrica.FabricaObjNegocio;
import itson.sistemarestaurantenegocio.IClientesFrecuentesBO;
import itson.sistemarestaurantenegocio.IIngredientesBO;

/**
 *
 * @author saula
 */
public class SistemaRestaurantePresentacion {

    public static void main(String[] args) {
//        IClientesFrecuentesBO clientesFrecuentesBO = FabricaObjNegocio.crearClientesFrecuentesBO();
//        RegistrarClienteFrecuenteForm prueba = new RegistrarClienteFrecuenteForm(clientesFrecuentesBO);
//        prueba.setVisible(true);

//        IIngredientesBO ingredientesBO = FabricaObjNegocio.crearIngredienteBO();
//        RegistrarIngredienteForm pruebaRegistrarIngrediente = new RegistrarIngredienteForm(ingredientesBO);
//        pruebaRegistrarIngrediente.setVisible(true);
//        
        IClientesFrecuentesBO clientesFrecuentesBO = FabricaObjNegocio.crearClientesFrecuentesBO();
        ClientesFrecuentes prueba2 = new ClientesFrecuentes(clientesFrecuentesBO);
        prueba2.setVisible(true);

        
        
    }
}
