/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.sistemarestaurantenegocio.fabrica;

import itson.sistemarestaurantenegocio.IClientesFrecuentesBO;
import itson.sistemarestaurantenegocio.IIngredientesBO;
import itson.sistemarestaurantenegocio.implementaciones.ClientesFrecuentesBO;
import itson.sistemarestaurantenegocio.implementaciones.IngredientesBO;
import itson.sistemarestaurantepersistencia.IClientesFrecuentesDAO;
import itson.sistemarestaurantepersistencia.IIngredientesDAO;
import itson.sistemarestaurantepersistencia.implementaciones.ClientesFrecuentesDAO;
import itson.sistemarestaurantepersistencia.implementaciones.IngredientesDAO;

/**
 *
 * @author rauln
 */
public class FabricaObjNegocio {
    
    public static IClientesFrecuentesBO crearClientesFrecuentesBO(){
        IClientesFrecuentesDAO clientesFrecuentesDAO = new ClientesFrecuentesDAO();
        IClientesFrecuentesBO clientesFrecuentesBO = new ClientesFrecuentesBO(clientesFrecuentesDAO);
        return clientesFrecuentesBO;
    }
    
    public static IIngredientesBO crearIngredienteBO(){
        IIngredientesDAO ingredientesDAO = new IngredientesDAO();
        IIngredientesBO ingredientesBO = new IngredientesBO(ingredientesDAO);
        return ingredientesBO;
    }
    
}


