/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package itson.sistemarestaurantepresentacion;

import itson.sistemarestaurantepresentacion.listeners.ClientesFiltradosListener;
import itson.sistemarestaurantedominio.ClienteFrecuente;
import itson.sistemarestaurantenegocio.IClientesFrecuentesBO;
import itson.sistemarestaurantepresentacion.buscadores.BuscadorClientesFrecuentesPanel;
import itson.sistemarestaurantepresentacion.control.Control;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;


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
    private Control control;

    /**
     * Crea una nueva instancia de la clase {@code ClientesFrecuentes}. Este
     * constructor inicializa el objeto de lógica de negocio
     * {@code IClientesFrecuentesBO}, inicializa los componentes gráficos del
     * formulario, crea la tabla de clientes frecuentes y configura el panel de
     * búsqueda de clientes frecuentes para que reciba los resultados filtrados.
     *
     * @param clientesFrecuentesBO El objeto que implementa la interfaz
     * {@link IClientesFrecuentesBO}, el cual se encargará de la lógica de
     * negocio relacionada con los clientes frecuentes.
     * @param control
     * @see #initComponents()
     * @see #crearTabla()
     * @see
     * BuscadorClientesFrecuentesPanel#setClientesFiltradosListener(ClientesFrecuentes)
     * @see
     * BuscadorClientesFrecuentesPanel#setClientesFrecuentesBO(IClientesFrecuentesBO)
     */
    public ClientesFrecuentes(IClientesFrecuentesBO clientesFrecuentesBO) {
        this.clientesFrecuentesBO = clientesFrecuentesBO;
       
        initComponents();
        crearTabla();

        // Configura el listener para que la clase ClientesFrecuentes reciba los resultados filtrados
        buscadorClientesFrecuentesPanel1.setClientesFiltradosListener(this);
        buscadorClientesFrecuentesPanel1.setClientesFrecuentesBO(clientesFrecuentesBO);
        fondo.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 600, 350));
    }

    /**
     * Método que maneja los resultados filtrados de clientes frecuentes. Este
     * método es llamado cuando se reciben los clientes filtrados, y actualiza
     * la tabla con los resultados obtenidos. Si ocurre algún error durante la
     * actualización, se registra el error en el log.
     *
     * @param clientes Lista de clientes frecuentes filtrados que se utilizará
     * para actualizar la tabla.
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
     * Crea y configura la tabla {@code JTable} que se utilizará para mostrar
     * los resultados de los clientes frecuentes. Este método inicializa el
     * modelo de la tabla {@code DefaultTableModel} con las columnas "Nombre",
     * "Teléfono" y "Correo", y luego crea la instancia de la tabla
     * {@code JTable} y un {@code JScrollPane} para permitir el desplazamiento
     * de los resultados en la interfaz gráfica.
     *
     * @see DefaultTableModel
     * @see JTable
     * @see JScrollPane
     */
    private void crearTabla() {
        modeloTabla = new DefaultTableModel(new String[]{"Nombre", "Teléfono", "Correo"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Todas las celdas no son editables
            }
        };
        tablaResultados = new JTable(modeloTabla);

        JTableHeader header = tablaResultados.getTableHeader();

        header.setBackground(Color.WHITE); // Fondo blanco
        header.setForeground(Color.BLACK); // Letra negra
        header.setBorder(BorderFactory.createEmptyBorder()); // Sin bordes opcional

        // Aplicar la fuente a las filas de la tabla
        tablaResultados.setRowHeight(40); // Altura de las filas

        // Centrar el texto de todas las columnas
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tablaResultados.getColumnCount(); i++) {
            tablaResultados.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Configuración adicional de la cabecera de la tabla
        
        header.setFont(new Font("Montserrat", Font.BOLD, 14));
        header.setBackground(new Color(60, 63, 65)); // Fondo gris oscuro
        header.setForeground(Color.WHITE); // Texto blanco
        header.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1)); // Borde negro delgado

        tablaResultados.setRowHeight(40); // Alto de las filas

        // Crear JScrollPane
        jScrollPane1 = new JScrollPane(tablaResultados);
        jScrollPane1.setBounds(20, 70, 760, 300); // Ajustar tamaño y posición si es necesario
        fondo.setLayout(null); // Layout absoluto para posicionar manualmente
        fondo.add(jScrollPane1);
    }

    /**
     * Actualiza la tabla con los datos de la lista de clientes frecuentes. Este
     * método limpia primero la tabla (eliminando las filas existentes) y luego
     * agrega una nueva fila por cada cliente en la lista {@code clientes},
     * utilizando los datos del cliente para completar las columnas "Nombre",
     * "Teléfono" y "Correo".
     *
     * @param clientes Lista de objetos {@code ClienteFrecuente} que contiene
     * los datos que se mostrarán en la tabla.
     * @throws Exception Si ocurre un error durante la actualización de la
     * tabla.
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

        fondo = new javax.swing.JPanel();
        buscadorClientesFrecuentesPanel1 = new itson.sistemarestaurantepresentacion.buscadores.BuscadorClientesFrecuentesPanel();
        botonRegistrarCliente = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fondo.setBackground(new java.awt.Color(255, 255, 255));
        fondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        fondo.add(buscadorClientesFrecuentesPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(175, 20, -1, -1));

        botonRegistrarCliente.setBackground(new java.awt.Color(255, 153, 153));
        botonRegistrarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconRegistrarCliente.png"))); // NOI18N
        botonRegistrarCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonRegistrarClienteMouseClicked(evt);
            }
        });
        fondo.add(botonRegistrarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 430, 300, 40));

        getContentPane().add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 490));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonRegistrarClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonRegistrarClienteMouseClicked
        // TODO add your handling code here:
        Control cont = new Control();
        cont.abrirRegistrarClientesFrecuentes();
    }//GEN-LAST:event_botonRegistrarClienteMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel botonRegistrarCliente;
    private itson.sistemarestaurantepresentacion.buscadores.BuscadorClientesFrecuentesPanel buscadorClientesFrecuentesPanel1;
    private javax.swing.JPanel fondo;
    // End of variables declaration//GEN-END:variables
}
