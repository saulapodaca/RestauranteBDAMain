package itson.sistemarestaurantepresentacion.control;

import itson.sistemarestaurantenegocio.IClientesFrecuentesBO;
import itson.sistemarestaurantenegocio.IIngredientesBO;
import itson.sistemarestaurantenegocio.fabrica.FabricaObjNegocio;
import itson.sistemarestaurantepresentacion.ClientesFrecuentes;
import itson.sistemarestaurantepresentacion.InventarioIngredientesForm;
import itson.sistemarestaurantepresentacion.pantallaregistros.RegistrarClienteFrecuenteForm;
import itson.sistemarestaurantepresentacion.pantallaregistros.RegistrarIngredienteForm;

public class Control {

    private static Control instance;
    IIngredientesBO ingredientesBO = FabricaObjNegocio.crearIngredienteBO();
    IClientesFrecuentesBO clientesBO = FabricaObjNegocio.crearClientesFrecuentesBO();

    /**
     * Constructor privado para evitar la creación externa de instancias.
     */
    private Control() {
    }

    /**
     * Método para obtener la instancia del singleton.
     *
     * @return una instancia de tipo Control.
     */
    public static Control getInstancia() {
        if (instance == null) {
            instance = new Control();
        }
        return instance;
    }

    /**
     * Método que abre la pantalla del inventario de ingredientes.
     */
    public void abrirInventarioIngredientes() {
        InventarioIngredientesForm inventarioIngredientesForm = new InventarioIngredientesForm(ingredientesBO);
        inventarioIngredientesForm.setVisible(true);
    }

    /**
     * Método que abre la pantalla de registro de ingredientes.
     */
    public void abrirRegistrarIngredientes() {
        RegistrarIngredienteForm registrarIngredienteForm = new RegistrarIngredienteForm(ingredientesBO);
        registrarIngredienteForm.setVisible(true);
    }

    /**
     * Método que abre la pantalla de clientes frecuentes registrados.
     */
    public void abrirClientesFrecuentes() {
        ClientesFrecuentes formClientesFrecuentes = new ClientesFrecuentes(clientesBO);
        formClientesFrecuentes.setVisible(true);
    }

    /**
     * Método que abre la pantalla del registro de clientes
     */
    public void abrirRegistrarClientesFrecuentes() {
        RegistrarClienteFrecuenteForm registrarClienteFrecuenteForm = new RegistrarClienteFrecuenteForm(clientesBO);
        registrarClienteFrecuenteForm.setVisible(true);
    }

}
