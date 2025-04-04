/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package itson.sistemarestaurantepresentacion;

import itson.sistemarestaurantedominio.dtos.NuevoClienteFrecuenteDTO;
import itson.sistemarestaurantenegocio.IClientesFrecuentesBO;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JOptionPane;

/**
 *
 * @author rauln
 */
public class RegistrarClienteFrecuenteForm extends javax.swing.JFrame {

    // Constantes para los textos por defecto (agregar al inicio de la clase)
    private static final String DEFAULT_NOMBRE = "INGRESE EL O LOS NOMBRES";
    private static final String DEFAULT_APELLIDO_PATERNO = "INGRESE EL APELLIDO PATERNO";
    private static final String DEFAULT_APELLIDO_MATERNO = "INGRESE EL APELLIDO MATERNO";
    private static final String DEFAULT_CORREO = "INGRESE EL CORREO ";
    private static final String DEFAULT_TELEFONO = "INGRESE EL NUMERO DE TELEFONO";
    private IClientesFrecuentesBO clientesFrecuentesBO;

    public RegistrarClienteFrecuenteForm(IClientesFrecuentesBO clientesFrecuentesBO) {
        initComponents();
        cargarDominiosCorreo();
        ajustarComboBox();
        configurarPlaceholders();
        setLocationRelativeTo(null);
        this.clientesFrecuentesBO = clientesFrecuentesBO;
    }

    private void ajustarComboBox() {
        // Configurar tamaño explícito
        comboBoxDominios.setPreferredSize(new java.awt.Dimension(90, 20));
        comboBoxDominios.setMaximumSize(new java.awt.Dimension(90, 20));
        comboBoxDominios.setMinimumSize(new java.awt.Dimension(90, 20));

        // Asegurar que se aplican las propiedades de estilo
        comboBoxDominios.setFont(new java.awt.Font("Montserrat Medium", 0, 12));
        comboBoxDominios.setBackground(Color.WHITE);
        comboBoxDominios.setForeground(Color.BLACK);
        comboBoxDominios.setBorder(null);

        // Opcional: configurar renderer personalizado para controlar mejor la apariencia
        comboBoxDominios.setRenderer(new javax.swing.DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(
                    javax.swing.JList<?> list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                setFont(new java.awt.Font("Montserrat Medium", 0, 12));
                return this;
            }
        });
    }
// Método para configurar un campo de texto con su placeholder

    private void configurarPlaceholder(javax.swing.JTextField textField, String placeholderText) {
        // Establecer el texto y color inicial
        textField.setText(placeholderText);
        textField.setForeground(Color.GRAY);

        // Agregar FocusListener para manejar el comportamiento del placeholder
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // Cuando el campo obtiene el foco
                if (textField.getText().equals(placeholderText)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Cuando el campo pierde el foco
                if (textField.getText().isEmpty()) {
                    textField.setText(placeholderText);
                    textField.setForeground(Color.GRAY);
                }
            }
        });
    }

    // Método para configurar los placeholders en todos los campos
    private void configurarPlaceholders() {
        configurarPlaceholder(textFieldIngresarNombre, DEFAULT_NOMBRE);
        configurarPlaceholder(textFieldIngresarApellidoPaterno, DEFAULT_APELLIDO_PATERNO);
        configurarPlaceholder(textFieldIngresarApellidoMaterno, DEFAULT_APELLIDO_MATERNO);
        configurarPlaceholder(textFieldIngresarCorreoElectronico, DEFAULT_CORREO);
        configurarPlaceholder(textFieldIngresarTelefono, DEFAULT_TELEFONO);
    }

    // Método para llenar el JComboBox con los dominios
    private void cargarDominiosCorreo() {
        String[] dominios = {
            "@gmail.com", "@hotmail.com", "@yahoo.com", "@outlook.com", "@icloud.com"
        };

        comboBoxDominios.removeAllItems(); // Borra los valores anteriores
        for (String dominio : dominios) {
            comboBoxDominios.addItem(dominio);
        }
    }

    private void limpiarFormulario() {
        // Limpiar campos de texto
        textFieldIngresarNombre.setText(DEFAULT_NOMBRE);
        textFieldIngresarApellidoPaterno.setText(DEFAULT_APELLIDO_PATERNO);
        textFieldIngresarApellidoMaterno.setText(DEFAULT_APELLIDO_MATERNO);
        textFieldIngresarCorreoElectronico.setText(DEFAULT_CORREO);
        textFieldIngresarTelefono.setText(DEFAULT_TELEFONO);

        // Restablecer color de los textos a gris
        textFieldIngresarNombre.setForeground(Color.GRAY);
        textFieldIngresarApellidoPaterno.setForeground(Color.GRAY);
        textFieldIngresarApellidoMaterno.setForeground(Color.GRAY);
        textFieldIngresarCorreoElectronico.setForeground(Color.GRAY);
        textFieldIngresarTelefono.setForeground(Color.GRAY);

        // Restablecer el JComboBox a su valor por defecto
        comboBoxDominios.setSelectedIndex(0);
    }

    private void registrar() {
        try {
            String nombre = textFieldIngresarNombre.getText().trim();
            String apellidoP = textFieldIngresarApellidoPaterno.getText().trim();
            String apellidoM = textFieldIngresarApellidoMaterno.getText().trim();
            String correoSinArroba = textFieldIngresarCorreoElectronico.getText().trim();
            String dominio = (String) comboBoxDominios.getSelectedItem();
            String telefono = textFieldIngresarTelefono.getText().trim();
            String correoCompleto = correoSinArroba + dominio;

            // Crear el DTO con los datos ingresados
            NuevoClienteFrecuenteDTO nuevoClienteFrecuenteDTO = new NuevoClienteFrecuenteDTO(
                    nombre, apellidoP, apellidoM, correoCompleto, telefono
            );

            // Registrar el cliente
            this.clientesFrecuentesBO.registrarClienteFrecuente(nuevoClienteFrecuenteDTO);

            // Mensaje de éxito
            JOptionPane.showMessageDialog(this, "Registro exitoso!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiarFormulario();

        } catch (IllegalArgumentException e) {
            // Mostrar mensaje de error con JOptionPane
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error de Validación", JOptionPane.ERROR_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error inesperado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background = new javax.swing.JPanel();
        labelDominios = new javax.swing.JLabel();
        registrarClienteFrecuenteTxt2 = new javax.swing.JLabel();
        labelNombres = new javax.swing.JLabel();
        labelApellidoPaterno = new javax.swing.JLabel();
        labelCorreoElectronico = new javax.swing.JLabel();
        labelTelefono = new javax.swing.JLabel();
        textFieldIngresarApellidoPaterno = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        textFieldIngresarNombre = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        textFieldIngresarTelefono = new javax.swing.JTextField();
        labelApellidoMaterno = new javax.swing.JLabel();
        textFieldIngresarApellidoMaterno = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        comboBoxDominios = new javax.swing.JComboBox<>();
        jSeparator5 = new javax.swing.JSeparator();
        textFieldIngresarCorreoElectronico = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        botonRegistrar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Registrar Cliente Frecuente");
        setResizable(false);
        setSize(new java.awt.Dimension(300, 400));

        background.setBackground(new java.awt.Color(255, 255, 255));
        background.setMinimumSize(new java.awt.Dimension(300, 400));
        background.setPreferredSize(new java.awt.Dimension(300, 400));
        background.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelDominios.setFont(new java.awt.Font("Montserrat SemiBold", 1, 12)); // NOI18N
        labelDominios.setText("DOMINIO");
        background.add(labelDominios, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 210, -1, -1));

        registrarClienteFrecuenteTxt2.setFont(new java.awt.Font("Montserrat SemiBold", 1, 12)); // NOI18N
        registrarClienteFrecuenteTxt2.setText("REGISTRAR CLIENTE FRECUENTE");
        background.add(registrarClienteFrecuenteTxt2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        labelNombres.setFont(new java.awt.Font("Montserrat SemiBold", 1, 12)); // NOI18N
        labelNombres.setText("NOMBRES(S)");
        background.add(labelNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        labelApellidoPaterno.setFont(new java.awt.Font("Montserrat SemiBold", 1, 12)); // NOI18N
        labelApellidoPaterno.setText("APELLIDO PATERNO");
        background.add(labelApellidoPaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        labelCorreoElectronico.setFont(new java.awt.Font("Montserrat SemiBold", 1, 12)); // NOI18N
        labelCorreoElectronico.setText("CORREO ELECTRONICO");
        background.add(labelCorreoElectronico, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        labelTelefono.setFont(new java.awt.Font("Montserrat SemiBold", 1, 12)); // NOI18N
        labelTelefono.setText("TELEFONO");
        background.add(labelTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, -1, -1));

        textFieldIngresarApellidoPaterno.setFont(new java.awt.Font("Montserrat Medium", 0, 12)); // NOI18N
        textFieldIngresarApellidoPaterno.setForeground(new java.awt.Color(204, 204, 204));
        textFieldIngresarApellidoPaterno.setText("INGRESE EL APELLIDO PATERNO");
        textFieldIngresarApellidoPaterno.setBorder(null);
        background.add(textFieldIngresarApellidoPaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 240, -1));
        background.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 105, 240, 5));

        textFieldIngresarNombre.setFont(new java.awt.Font("Montserrat Medium", 0, 12)); // NOI18N
        textFieldIngresarNombre.setForeground(new java.awt.Color(204, 204, 204));
        textFieldIngresarNombre.setText("INGRESE EL O LOS NOMBRES");
        textFieldIngresarNombre.setBorder(null);
        background.add(textFieldIngresarNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 250, -1));
        background.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 155, 240, 5));
        background.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 205, 240, 5));

        textFieldIngresarTelefono.setFont(new java.awt.Font("Montserrat Medium", 0, 12)); // NOI18N
        textFieldIngresarTelefono.setForeground(new java.awt.Color(204, 204, 204));
        textFieldIngresarTelefono.setText("INGRESE EL NUMERO DE TELEFONO");
        textFieldIngresarTelefono.setBorder(null);
        background.add(textFieldIngresarTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 240, -1));

        labelApellidoMaterno.setFont(new java.awt.Font("Montserrat SemiBold", 1, 12)); // NOI18N
        labelApellidoMaterno.setText("APELLIDO MATERNO");
        background.add(labelApellidoMaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        textFieldIngresarApellidoMaterno.setFont(new java.awt.Font("Montserrat Medium", 0, 12)); // NOI18N
        textFieldIngresarApellidoMaterno.setForeground(new java.awt.Color(204, 204, 204));
        textFieldIngresarApellidoMaterno.setText("INGRESE EL APELLIDO MATERNO");
        textFieldIngresarApellidoMaterno.setBorder(null);
        background.add(textFieldIngresarApellidoMaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 240, -1));
        background.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 255, 150, 5));

        comboBoxDominios.setFont(new java.awt.Font("Montserrat Medium", 0, 12)); // NOI18N
        comboBoxDominios.setForeground(new java.awt.Color(204, 204, 204));
        comboBoxDominios.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboBoxDominios.setBorder(null);
        comboBoxDominios.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxDominiosItemStateChanged(evt);
            }
        });
        comboBoxDominios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxDominiosActionPerformed(evt);
            }
        });
        background.add(comboBoxDominios, new org.netbeans.lib.awtextra.AbsoluteConstraints(188, 230, -1, 20));
        background.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 255, 70, 5));

        textFieldIngresarCorreoElectronico.setFont(new java.awt.Font("Montserrat Medium", 0, 12)); // NOI18N
        textFieldIngresarCorreoElectronico.setForeground(new java.awt.Color(204, 204, 204));
        textFieldIngresarCorreoElectronico.setText("INGRESE EL CORREO");
        textFieldIngresarCorreoElectronico.setBorder(null);
        background.add(textFieldIngresarCorreoElectronico, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 150, -1));
        background.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 305, 240, 5));

        botonRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/REGISTRAR.png"))); // NOI18N
        botonRegistrar.setToolTipText("");
        botonRegistrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonRegistrarMouseClicked(evt);
            }
        });
        background.add(botonRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 340, 100, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboBoxDominiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxDominiosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxDominiosActionPerformed

    private void comboBoxDominiosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxDominiosItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxDominiosItemStateChanged

    private void botonRegistrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonRegistrarMouseClicked
        registrar();
    }//GEN-LAST:event_botonRegistrarMouseClicked

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel background;
    private javax.swing.JLabel botonRegistrar;
    private javax.swing.JComboBox<String> comboBoxDominios;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JLabel labelApellidoMaterno;
    private javax.swing.JLabel labelApellidoPaterno;
    private javax.swing.JLabel labelCorreoElectronico;
    private javax.swing.JLabel labelDominios;
    private javax.swing.JLabel labelNombres;
    private javax.swing.JLabel labelTelefono;
    private javax.swing.JLabel registrarClienteFrecuenteTxt2;
    private javax.swing.JTextField textFieldIngresarApellidoMaterno;
    private javax.swing.JTextField textFieldIngresarApellidoPaterno;
    private javax.swing.JTextField textFieldIngresarCorreoElectronico;
    private javax.swing.JTextField textFieldIngresarNombre;
    private javax.swing.JTextField textFieldIngresarTelefono;
    // End of variables declaration//GEN-END:variables
}
