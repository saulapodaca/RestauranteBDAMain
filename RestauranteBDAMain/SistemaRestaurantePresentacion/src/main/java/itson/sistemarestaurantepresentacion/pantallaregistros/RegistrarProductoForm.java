/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package itson.sistemarestaurantepresentacion.pantallaregistros;

import itson.sistemarestaurantedominio.Ingrediente;
import itson.sistemarestaurantedominio.Producto;
import itson.sistemarestaurantedominio.TipoProducto;
import itson.sistemarestaurantedominio.dtos.NuevoProductoDTO;
import itson.sistemarestaurantenegocio.excepciones.ProductoDuplicadoException;
import itson.sistemarestaurantenegocio.implementaciones.ProductosBO;
import itson.sistemarestaurantepersistencia.IIngredientesDAO;
import itson.sistemarestaurantepersistencia.implementaciones.IngredientesDAO;
import itson.sistemarestaurantepersistencia.implementaciones.ManejadorConexiones;
import itson.sistemarestaurantepersistencia.implementaciones.ProductosDAO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 52644
 */
public class RegistrarProductoForm extends javax.swing.JFrame {

    private ProductosBO productosBO;
    /**
     * Creates new form RegistrarProductoForm
     */
    public RegistrarProductoForm() {
        initComponents();
        cargarProductos(); // Cargar los productos al iniciar
        configurarBusqueda(); // Configurar la búsqueda dinámica
    }

    public void limpiarFormulario() {
        textFieldingresarTipoDeProducto.setText("");
    }
    
   private void cargarProductos() {
    try {
        List<Producto> productos = productosBO.buscarProductos("");
        DefaultTableModel modelo = (DefaultTableModel) TablaRegistrarProductos.getModel();
        modelo.setRowCount(0);
        for (Producto producto : productos) {
            modelo.addRow(new Object[]{
                producto.getNombre(),
                producto.getPrecio(),
                producto.getTipoProducto()
            });
        }
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Error al cargar los productos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    private void buscarProductos(String filtro) {
    try {
        List<Producto> productos = productosBO.buscarProductos(filtro);
        DefaultTableModel modelo = (DefaultTableModel) TablaRegistrarProductos.getModel();
        modelo.setRowCount(0);
        for (Producto producto : productos) {
            modelo.addRow(new Object[]{
                producto.getNombre(),
                producto.getPrecio(),
                producto.getTipoProducto()
            });
        }
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Error al buscar los productos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
    
    private void configurarBusqueda() {
        textFieldingresarTipoDeProducto.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                buscarProductos(textFieldingresarTipoDeProducto.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                buscarProductos(textFieldingresarTipoDeProducto.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                buscarProductos(textFieldingresarTipoDeProducto.getText());
            }
        });
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        TablaRegistrarProductos = new javax.swing.JTable();
        botonRegistrarProducto = new javax.swing.JButton();
        textFieldingresarTipoDeProducto = new javax.swing.JTextField();
        Lupa = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        TablaRegistrarProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nombre", "Precio", "Tipo de Producto"
            }
        ));
        jScrollPane1.setViewportView(TablaRegistrarProductos);

        botonRegistrarProducto.setText("Registrar Producto");
        botonRegistrarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRegistrarProductoActionPerformed(evt);
            }
        });

        Lupa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lupa2.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Lupa)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(textFieldingresarTipoDeProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(162, 162, 162)
                        .addComponent(botonRegistrarProducto)))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Lupa)
                    .addComponent(textFieldingresarTipoDeProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botonRegistrarProducto)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonRegistrarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegistrarProductoActionPerformed
    // Mostrar un formulario para capturar los datos del producto
        JTextField campoNombre = new JTextField();
        JTextField campoPrecio = new JTextField();
        JComboBox<TipoProducto> comboTipo = new JComboBox<>(TipoProducto.values());

        Object[] campos = {
            "Nombre:", campoNombre,
            "Precio:", campoPrecio,
            "Tipo de producto:", comboTipo
        };

        int opcion = JOptionPane.showConfirmDialog(this, campos, "Registrar Producto", JOptionPane.OK_CANCEL_OPTION);
        if (opcion != JOptionPane.OK_OPTION) {
            return;
        }

        try {
            String nombre = campoNombre.getText();
            float precio = Float.parseFloat(campoPrecio.getText());
            TipoProducto tipoProducto = (TipoProducto) comboTipo.getSelectedItem();

            NuevoProductoDTO nuevoProductoDTO = new NuevoProductoDTO(nombre, precio, tipoProducto);
            Producto producto = productosBO.registrarProducto(nuevoProductoDTO);

            JOptionPane.showMessageDialog(this, "Producto registrado: " + producto.getNombre());
            cargarProductos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error: El precio debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_botonRegistrarProductoActionPerformed
    /*
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               new RegistrarProductoForm().setVisible(true);
            }
        });
    }
  */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Lupa;
    private javax.swing.JTable TablaRegistrarProductos;
    private javax.swing.JButton botonRegistrarProducto;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField textFieldingresarTipoDeProducto;
    // End of variables declaration//GEN-END:variables
}
