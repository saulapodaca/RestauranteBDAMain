package itson.sistemarestaurantepresentacion.control;

import itson.sistemarestaurantenegocio.IClientesFrecuentesBO;
import itson.sistemarestaurantenegocio.fabrica.FabricaObjNegocio;
import itson.sistemarestaurantenegocio.IIngredientesBO;
import itson.sistemarestaurantenegocio.IMesasBO;
import itson.sistemarestaurantenegocio.IProductosBO;
import itson.sistemarestaurantepersistencia.implementaciones.MesasDAO;
import itson.sistemarestaurantepresentacion.ClientesFrecuentes;
import itson.sistemarestaurantepresentacion.Comandas;
import itson.sistemarestaurantepresentacion.InventarioIngredientesForm;
import itson.sistemarestaurantepresentacion.MesasForm;
import itson.sistemarestaurantepresentacion.pantallaregistros.RegistrarClienteFrecuenteForm;
import itson.sistemarestaurantepresentacion.pantallaregistros.RegistrarIngredienteForm;
import itson.sistemarestaurantepresentacion.pantallaregistros.RegistrarProductoForm;

public class SistemaRestaurantePresentacion {

    public static void main(String[] args) {
//        IClientesFrecuentesBO clientesFrecuentesBO = FabricaObjNegocio.crearClientesFrecuentesBO();
//        RegistrarClienteFrecuenteForm prueba = new RegistrarClienteFrecuenteForm(clientesFrecuentesBO);
//        prueba.setVisible(true);
//
//        IIngredientesBO ingredientesBO = FabricaObjNegocio.crearIngredienteBO();
//        RegistrarIngredienteForm pruebaRegistrarIngrediente = new RegistrarIngredienteForm(ingredientesBO);
//        pruebaRegistrarIngrediente.setVisible(true);
//        InventarioIngredientesForm pruebaInventario = new InventarioIngredientesForm(ingredientesBO);
//        pruebaInventario.setVisible(true);
//      
//        IClientesFrecuentesBO clientesFrecuentesBO = FabricaObjNegocio.crearClientesFrecuentesBO();
//        ClientesFrecuentes prueba2 = new ClientesFrecuentes(clientesFrecuentesBO);
//        prueba 2.setVisible(true);
     //     Comandas comanda = new Comandas();
     //     comanda.setVisible(true);
        
        
    IProductosBO productosbo = FabricaObjNegocio.crearProductosBO();
     RegistrarProductoForm pruebaProducto = new RegistrarProductoForm(productosbo);
     pruebaProducto.setVisible(true);
          
    }
}
