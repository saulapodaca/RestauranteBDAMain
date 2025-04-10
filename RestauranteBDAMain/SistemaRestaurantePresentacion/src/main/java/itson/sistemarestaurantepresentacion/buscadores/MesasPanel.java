/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package itson.sistemarestaurantepresentacion.buscadores;

import itson.sistemarestaurantedominio.EstadoMesa;
import itson.sistemarestaurantedominio.dtos.MesaRegistradaDTO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JButton;

/**
 *
 * @author saula
 */
public class MesasPanel extends javax.swing.JPanel {

    public MesasPanel(List<MesaRegistradaDTO> mesas) {
        initComponents();
        setLayout(new GridLayout(4, 5, 15, 15)); // 4 filas, 5 columnas, espacio entre celdas

        for (MesaRegistradaDTO mesa : mesas) {
            JButton botonMesa = new JButton(String.valueOf(mesa.getNumeroMesa()));
            botonMesa.setPreferredSize(new Dimension(60, 60));
            botonMesa.setFont(new Font("Arial", Font.BOLD, 16));
            botonMesa.setOpaque(true);
            botonMesa.setBorderPainted(false);
            botonMesa.setForeground(Color.WHITE);

            if (mesa.getEstadoMesa() == EstadoMesa.DISPONIBLE) {
                botonMesa.setBackground(Color.GREEN.darker());
            } else {
                botonMesa.setBackground(Color.GRAY);
            }

            add(botonMesa);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
