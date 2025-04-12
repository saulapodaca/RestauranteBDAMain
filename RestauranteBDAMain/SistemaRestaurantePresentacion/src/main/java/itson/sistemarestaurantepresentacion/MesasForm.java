/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package itson.sistemarestaurantepresentacion;

import itson.sistemarestaurantedominio.dtos.MesaRegistradaDTO;
import itson.sistemarestaurantenegocio.IMesasBO;
import itson.sistemarestaurantepresentacion.buscadores.MesasPanel;
import itson.sistemarestaurantepresentacion.utilidades.UIUtilidades;
import java.awt.BorderLayout;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author saula
 */
public class MesasForm extends javax.swing.JFrame {

    private IMesasBO mesasBO;


    public MesasForm(IMesasBO mesasBO) {
        initComponents();
        this.mesasBO = mesasBO;

        configurarVentana();
        cargarPanelMesas();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backround = new javax.swing.JPanel();
        botonInsertar = new javax.swing.JLabel();
        botonInsertarMesasBloqueado = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        backround.setBackground(new java.awt.Color(255, 255, 255));

        botonInsertar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Insertar Mesas.png"))); // NOI18N
        botonInsertar.setText("jLabel1");
        botonInsertar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonInsertarMouseClicked(evt);
            }
        });

        botonInsertarMesasBloqueado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/InsertarMesaBloqueado.png"))); // NOI18N
        botonInsertarMesasBloqueado.setText("jLabel1");

        javax.swing.GroupLayout backroundLayout = new javax.swing.GroupLayout(backround);
        backround.setLayout(backroundLayout);
        backroundLayout.setHorizontalGroup(
            backroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backroundLayout.createSequentialGroup()
                .addGap(237, 237, 237)
                .addComponent(botonInsertar, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(botonInsertarMesasBloqueado, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        backroundLayout.setVerticalGroup(
            backroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backroundLayout.createSequentialGroup()
                .addContainerGap(486, Short.MAX_VALUE)
                .addGroup(backroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonInsertar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonInsertarMesasBloqueado, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backround, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backround, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonInsertarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonInsertarMouseClicked
        insertarMesas();
    }//GEN-LAST:event_botonInsertarMouseClicked

    private void configurarVentana() {
        setTitle("Estado actual de las mesas");
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
    }

    private void cargarPanelMesas() {
         List<MesaRegistradaDTO> mesas = mesasBO.obtenerMesas();
        mesas.sort(Comparator.comparingInt(MesaRegistradaDTO::getNumeroMesa));

        MesasPanel panelMesas = new MesasPanel(mesas, mesasBO);
        UIUtilidades.configurarComponente(panelMesas, 0, 0, 720, 480);

        UIUtilidades.actualizarPanel(backround, panelMesas);

        configurarBotones();

        if (mesas.isEmpty()) {
            UIUtilidades.mostrarComponente(botonInsertar, botonInsertarMesasBloqueado);
        } else {
            UIUtilidades.ocultarComponente(botonInsertar, botonInsertarMesasBloqueado);
        }

        add(backround, BorderLayout.CENTER);
    }

    private void configurarBotones() {
        UIUtilidades.configurarComponente(botonInsertar, 237, 489, 215, 55);
        UIUtilidades.configurarComponente(botonInsertarMesasBloqueado, 237, 489, 215, 55);
        UIUtilidades.agregarComponentes(backround, botonInsertar, botonInsertarMesasBloqueado);
    }

    private void insertarMesas() {
        mesasBO.insertarMesasIniciales();
        cargarPanelMesas();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel backround;
    private javax.swing.JLabel botonInsertar;
    private javax.swing.JLabel botonInsertarMesasBloqueado;
    // End of variables declaration//GEN-END:variables
}
