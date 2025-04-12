/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package itson.sistemarestaurantepresentacion.Reportes;

/**
 *
 * @author rauln
 */
public class SeleccionarReporte extends javax.swing.JFrame {

    /**
     * Creates new form SeleccionarReporte
     */
    public SeleccionarReporte() {
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fondo = new javax.swing.JPanel();
        labelTexto = new javax.swing.JLabel();
        botonReporteClientesFrecuentes = new javax.swing.JLabel();
        botonReporteComandas = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fondo.setBackground(new java.awt.Color(255, 255, 255));
        fondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelTexto.setFont(new java.awt.Font("Montserrat Medium", 1, 24)); // NOI18N
        labelTexto.setText("SELECCIONAR TIPO DE REPORTE");
        fondo.add(labelTexto, new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 20, 430, -1));

        botonReporteClientesFrecuentes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconReporteClientes.png"))); // NOI18N
        fondo.add(botonReporteClientesFrecuentes, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 150, 200, 200));

        botonReporteComandas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconReporteComandas.png"))); // NOI18N
        fondo.add(botonReporteComandas, new org.netbeans.lib.awtextra.AbsoluteConstraints(425, 150, 200, 200));

        getContentPane().add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 500));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel botonReporteClientesFrecuentes;
    private javax.swing.JLabel botonReporteComandas;
    private javax.swing.JPanel fondo;
    private javax.swing.JLabel labelTexto;
    // End of variables declaration//GEN-END:variables
}
