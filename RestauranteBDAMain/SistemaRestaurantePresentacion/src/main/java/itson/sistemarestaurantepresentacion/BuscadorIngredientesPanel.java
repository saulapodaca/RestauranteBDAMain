package itson.sistemarestaurantepresentacion;

import itson.sistemarestaurantedominio.dtos.IngredienteRegistradoDTO;
import itson.sistemarestaurantenegocio.IIngredientesBO;
import itson.sistemarestaurantepresentacion.utilidades.FormularioUtilidades;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * Panel Swing que permite buscar ingredientes en tiempo real mediante un campo de texto y una unidad de medida.
 * Implementa IngredientesRegistradosListener para recibir los resultados de búsqueda.
 */
public class BuscadorIngredientesPanel extends javax.swing.JPanel implements IngredientesRegistradosListener {

    private static final String DEFAULT_BUSCADOR = "INGRESE EL INGREDIENTE QUE DESEA BUSCAR";
    private IngredientesRegistradosListener listener;
    private IIngredientesBO ingredientesBO;

    /**
     * Constructor que inicializa el panel y configura los placeholders.
     */
    public BuscadorIngredientesPanel() {
        initComponents();
        FormularioUtilidades.configurarPlaceholders(new JTextField[]{textFieldBuscador}, DEFAULT_BUSCADOR);
    }

    /**
     * Inicializa el panel con la lógica de negocio y un listener para recibir los resultados.
     *
     * @param ingredientesBO lógica de negocio para gestionar los ingredientes
     * @param listener listener que recibirá los ingredientes encontrados
     */
    public void iniciarBusqueda(IIngredientesBO ingredientesBO, IngredientesRegistradosListener listener) {
        this.ingredientesBO = ingredientesBO;
        this.listener = listener;
        FormularioUtilidades.configurarBusquedaEnTiempoReal(
                textFieldBuscador, comboBoxUnidad, this::actualizarBusqueda);
        SwingUtilities.invokeLater(() -> textFieldBuscador.requestFocusInWindow());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        textFieldBuscador = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        comboBoxUnidad = new javax.swing.JComboBox<>();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        textFieldBuscador.setFont(new java.awt.Font("Montserrat Medium", 1, 12)); // NOI18N
        textFieldBuscador.setForeground(new java.awt.Color(204, 204, 204));
        textFieldBuscador.setText("INGRESE EL INGREDIENTE QUE DESEA BUSCAR");
        textFieldBuscador.setBorder(null);
        textFieldBuscador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldBuscadorActionPerformed(evt);
            }
        });
        jPanel1.add(textFieldBuscador, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 310, 20));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 310, 10));

        comboBoxUnidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TODOS", "PIEZAS", "GRAMOS", "MILILITROS" }));
        jPanel1.add(comboBoxUnidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, 110, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

     /**
     * Ejecuta la búsqueda de ingredientes utilizando la lógica de negocio y actualiza al listener con los resultados.
     */
    private void actualizarBusqueda() {
        String filtro = textFieldBuscador.getText().trim();
        String unidad = comboBoxUnidad.getSelectedItem().toString();
        try {
            List<IngredienteRegistradoDTO> resultados = ingredientesBO.buscarIngredientePorFiltro(filtro, unidad);

            if (listener != null) {
                listener.onIngredientesRegistrados(resultados);
            }
        } catch (Exception e) {
            System.err.println("Error al buscar ingredientes: " + e.getMessage());

        }
    }

    private void textFieldBuscadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldBuscadorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldBuscadorActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboBoxUnidad;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField textFieldBuscador;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onIngredientesRegistrados(List<IngredienteRegistradoDTO> ingredientes) {
    }
}
