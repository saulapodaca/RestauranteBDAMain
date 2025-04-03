/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package itson.sistemarestaurantepresentacion;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 52644
 */
public class RegistrarProductos extends javax.swing.JFrame {
      DefaultTableModel mt = new DefaultTableModel();
    /**
     * Creates new form RegistrarProductos
     */
    public RegistrarProductos() {
        initComponents();
        String ids[] = {"Nombre ","Precio ","Tipo de Producto "};
    mt.setColumnIdentifiers(ids);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        background = new javax.swing.JPanel();
        labelUnidadMedida = new javax.swing.JLabel();
        registrarNuevoIngredienteTxt2 = new javax.swing.JLabel();
        labelNombre = new javax.swing.JLabel();
        labelStock = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        botonRegistrar = new javax.swing.JLabel();
        textFieldIngresarStock = new javax.swing.JTextField();
        comboBoxUnidadMedida = new javax.swing.JComboBox<>();
        textFieldIngresarNombre = new javax.swing.JTextField();
        labelUnidadMedida1 = new javax.swing.JLabel();
        textFieldIngresarNombre1 = new javax.swing.JTextField();

        jScrollPane2.setViewportView(jTextPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        background.setBackground(new java.awt.Color(255, 255, 255));
        background.setMinimumSize(new java.awt.Dimension(300, 400));
        background.setPreferredSize(new java.awt.Dimension(300, 400));
        background.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelUnidadMedida.setFont(new java.awt.Font("Montserrat SemiBold", 1, 12)); // NOI18N
        labelUnidadMedida.setText("PRECIO *Obligatorio*");
        background.add(labelUnidadMedida, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        registrarNuevoIngredienteTxt2.setFont(new java.awt.Font("Montserrat SemiBold", 1, 12)); // NOI18N
        registrarNuevoIngredienteTxt2.setText("REGISTRAR PRODUCTO");
        background.add(registrarNuevoIngredienteTxt2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, -1));

        labelNombre.setFont(new java.awt.Font("Montserrat SemiBold", 1, 12)); // NOI18N
        labelNombre.setText("NOMBRE *Obligatorio*");
        background.add(labelNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        labelStock.setFont(new java.awt.Font("Montserrat SemiBold", 1, 12)); // NOI18N
        labelStock.setText("BUSCAR INGREDIENTES *1 Por Lo Menos*");
        background.add(labelStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));
        background.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 105, 240, 5));
        background.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 155, 240, 5));
        background.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 305, 240, 5));

        botonRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/REGISTRAR.png"))); // NOI18N
        botonRegistrar.setToolTipText("");
        botonRegistrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonRegistrarMouseClicked(evt);
            }
        });
        background.add(botonRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, 100, 40));

        textFieldIngresarStock.setFont(new java.awt.Font("Montserrat Medium", 0, 12)); // NOI18N
        textFieldIngresarStock.setForeground(new java.awt.Color(204, 204, 204));
        textFieldIngresarStock.setText("INGRESE EL STOCK INICIAL");
        textFieldIngresarStock.setBorder(null);
        background.add(textFieldIngresarStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 250, -1));

        comboBoxUnidadMedida.setFont(new java.awt.Font("Montserrat Medium", 0, 12)); // NOI18N
        comboBoxUnidadMedida.setForeground(new java.awt.Color(204, 204, 204));
        comboBoxUnidadMedida.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PIEZAS", "GRAMOS", "MILILITROS" }));
        comboBoxUnidadMedida.setBorder(null);
        comboBoxUnidadMedida.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxUnidadMedidaItemStateChanged(evt);
            }
        });
        comboBoxUnidadMedida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxUnidadMedidaActionPerformed(evt);
            }
        });
        background.add(comboBoxUnidadMedida, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, 80, 20));
        comboBoxUnidadMedida.setSelectedIndex(1);

        textFieldIngresarNombre.setFont(new java.awt.Font("Montserrat Medium", 0, 12)); // NOI18N
        textFieldIngresarNombre.setForeground(new java.awt.Color(204, 204, 204));
        textFieldIngresarNombre.setText("INGRESE EL PRECIO");
        textFieldIngresarNombre.setBorder(null);
        textFieldIngresarNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldIngresarNombreActionPerformed(evt);
            }
        });
        background.add(textFieldIngresarNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 130, -1));

        labelUnidadMedida1.setFont(new java.awt.Font("Montserrat SemiBold", 1, 12)); // NOI18N
        labelUnidadMedida1.setText("Tipo *Obligatorio*");
        background.add(labelUnidadMedida1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, -1, -1));

        textFieldIngresarNombre1.setFont(new java.awt.Font("Montserrat Medium", 0, 12)); // NOI18N
        textFieldIngresarNombre1.setForeground(new java.awt.Color(204, 204, 204));
        textFieldIngresarNombre1.setText("INGRESE EL NOMBRE DEL PRODUCTO");
        textFieldIngresarNombre1.setBorder(null);
        textFieldIngresarNombre1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldIngresarNombre1ActionPerformed(evt);
            }
        });
        background.add(textFieldIngresarNombre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 250, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(126, Short.MAX_VALUE)
                .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(background, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(131, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonRegistrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonRegistrarMouseClicked
        try{
            String nombre = textFieldIngresarNombre.getText().trim();
            float stock = Float.parseFloat(textFieldIngresarStock.getText());
            String unidadSeleccionada = comboBoxUnidadMedida.getSelectedItem().toString();
            UnidadMedidaIngrediente unidadMedida = UnidadMedidaIngrediente.valueOf(unidadSeleccionada);

            NuevoIngredienteDTO ingredienteDTO = new NuevoIngredienteDTO(nombre, unidadMedida, stock);
            this.ingredientesBO.registrar(ingredienteDTO);

            JOptionPane.showMessageDialog(background, "Registro de ingrediente exitoso!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiarFormulario();
        }catch(IngredienteRegistradoException | NombreInvalidoException | StockInvalidoException e){
            JOptionPane.showMessageDialog(background, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_botonRegistrarMouseClicked

    private void comboBoxUnidadMedidaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxUnidadMedidaItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxUnidadMedidaItemStateChanged

    private void comboBoxUnidadMedidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxUnidadMedidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxUnidadMedidaActionPerformed

    private void textFieldIngresarNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldIngresarNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldIngresarNombreActionPerformed

    private void textFieldIngresarNombre1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldIngresarNombre1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldIngresarNombre1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RegistrarProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistrarProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistrarProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistrarProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistrarProductos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel background;
    private javax.swing.JLabel botonRegistrar;
    private javax.swing.JComboBox<String> comboBoxUnidadMedida;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JLabel labelNombre;
    private javax.swing.JLabel labelStock;
    private javax.swing.JLabel labelUnidadMedida;
    private javax.swing.JLabel labelUnidadMedida1;
    private javax.swing.JLabel registrarNuevoIngredienteTxt2;
    private javax.swing.JTextField textFieldIngresarNombre;
    private javax.swing.JTextField textFieldIngresarNombre1;
    private javax.swing.JTextField textFieldIngresarStock;
    // End of variables declaration//GEN-END:variables
}
