/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package itson.sistemarestaurantepresentacion;

import itson.sistemarestaurantepresentacion.listeners.ClientesFiltradosListener;
import itson.encriptacion.Desencriptador;
import itson.sistemarestaurantedominio.ClienteFrecuente;
import itson.sistemarestaurantenegocio.IClientesFrecuentesBO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rauln
 */
public class ClientesFrecuentes extends javax.swing.JFrame implements ClientesFiltradosListener {

    private DefaultTableModel modeloTabla;
    private JTable tablaResultados;
    private JScrollPane jScrollPane1;
    private IClientesFrecuentesBO clientesFrecuentesBO;
    private ClientesFiltradosListener listener;

    /**
     * Crea una nueva instancia de la clase {@code ClientesFrecuentes}.
     * Este constructor inicializa el objeto de lógica de negocio {@code IClientesFrecuentesBO}, 
     * inicializa los componentes gráficos del formulario, crea la tabla de clientes frecuentes y configura
     * el panel de búsqueda de clientes frecuentes para que reciba los resultados filtrados.
     * 
     * @param clientesFrecuentesBO El objeto que implementa la interfaz {@link IClientesFrecuentesBO}, 
     *                             el cual se encargará de la lógica de negocio relacionada con los clientes frecuentes.
     * @see #initComponents()
     * @see #crearTabla()
     * @see BuscadorClientesFrecuentesPanel#setClientesFiltradosListener(ClientesFrecuentes)
     * @see BuscadorClientesFrecuentesPanel#setClientesFrecuentesBO(IClientesFrecuentesBO)
     */
    public ClientesFrecuentes(IClientesFrecuentesBO clientesFrecuentesBO) {
        this.clientesFrecuentesBO = clientesFrecuentesBO;
        initComponents();
        crearTabla();

        // Configura el listener para que la clase ClientesFrecuentes reciba los resultados filtrados
        buscadorClientesFrecuentesPanel1.setClientesFiltradosListener(this);
        buscadorClientesFrecuentesPanel1.setClientesFrecuentesBO(clientesFrecuentesBO);
        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 600, 350));
    }


    /**
     * Método que maneja los resultados filtrados de clientes frecuentes.
     * Este método es llamado cuando se reciben los clientes filtrados, y actualiza la tabla 
     * con los resultados obtenidos. Si ocurre algún error durante la actualización, se registra 
     * el error en el log.
     * 
     * @param clientes Lista de clientes frecuentes filtrados que se utilizará para actualizar la tabla.
     * @see #actualizarTabla(List)
     */
    @Override
    public void onClientesFiltrados(List<ClienteFrecuente> clientes) {
        try {
            actualizarTabla(clientes);
        } catch (Exception ex) {
            Logger.getLogger(ClientesFrecuentes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    /**
     * Crea y configura la tabla {@code JTable} que se utilizará para mostrar los resultados de los clientes frecuentes.
     * Este método inicializa el modelo de la tabla {@code DefaultTableModel} con las columnas "Nombre", "Teléfono" y "Correo", 
     * y luego crea la instancia de la tabla {@code JTable} y un {@code JScrollPane} para permitir el desplazamiento 
     * de los resultados en la interfaz gráfica.
     * 
     * @see DefaultTableModel
     * @see JTable
     * @see JScrollPane
     */
    private void crearTabla() {
        modeloTabla = new DefaultTableModel(new String[]{"Nombre", "Teléfono", "Correo"}, 0);
        tablaResultados = new JTable(modeloTabla);
        jScrollPane1 = new JScrollPane(tablaResultados);
    }


    /**
     * Actualiza la tabla con los datos de la lista de clientes frecuentes.
     * Este método limpia primero la tabla (eliminando las filas existentes) y luego agrega una nueva fila 
     * por cada cliente en la lista {@code clientes}, utilizando los datos del cliente para completar las columnas 
     * "Nombre", "Teléfono" y "Correo".
     * 
     * @param clientes Lista de objetos {@code ClienteFrecuente} que contiene los datos que se mostrarán en la tabla.
     * @throws Exception Si ocurre un error durante la actualización de la tabla.
     * @see ClienteFrecuente
     */
    public void actualizarTabla(List<ClienteFrecuente> clientes) throws Exception {
        modeloTabla.setRowCount(0);
        for (ClienteFrecuente cliente : clientes) {
            modeloTabla.addRow(new Object[]{cliente.getNombre(), cliente.getTelefono(), cliente.getCorreo()});
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        buscadorClientesFrecuentesPanel1 = new itson.sistemarestaurantepresentacion.buscadores.BuscadorClientesFrecuentesPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(buscadorClientesFrecuentesPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(175, 20, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 490));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private itson.sistemarestaurantepresentacion.buscadores.BuscadorClientesFrecuentesPanel buscadorClientesFrecuentesPanel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
