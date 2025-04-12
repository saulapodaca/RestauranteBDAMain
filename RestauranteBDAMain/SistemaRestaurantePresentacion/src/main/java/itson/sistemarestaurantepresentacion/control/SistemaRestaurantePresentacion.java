package itson.sistemarestaurantepresentacion.control;

import itson.sistemarestaurantenegocio.IClientesFrecuentesBO;
import itson.sistemarestaurantenegocio.fabrica.FabricaObjNegocio;
import itson.sistemarestaurantepresentacion.Comandas;
import itson.sistemarestaurantepresentacion.pantallaregistros.RegistrarClienteFrecuenteForm;

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
////        prueba 2.setVisible(true);
//
//        IMesasBO mesasBO = FabricaObjNegocio.crearMesasBO();
//        MesasForm pruebaMesas = new MesasForm(mesasBO);
//        pruebaMesas.setVisible(true);

          Comandas comandasForm = new Comandas();
          comandasForm.setVisible(true);
    }
}
