/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package itson.sistemarestaurantepresentacion;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
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
public class Comandas extends javax.swing.JFrame {

    /**
     * Crea una nueva instancia del formulario "Comandas".
     * Este constructor inicializa los componentes gráficos de la interfaz de usuario, 
     * configura la tabla que se mostrará con las comandas y ajusta la ubicación 
     * de la ventana para que se centre en la pantalla.
     * 
     * @see initComponents()
     * @see inicializarTabla()
     */
    public Comandas() {
        initComponents();
        inicializarTabla();
        setLocationRelativeTo(null);
    }


    /**
     * Inicializa y configura la tabla que se usará para mostrar las comandas.
     * La tabla contiene las columnas: "Comanda", "Mesa", "Estado" y "Revisar".
     * Además, se configura un renderizador para mostrar un icono de lupa en la
     * columna "Revisar". También se establece una fuente personalizada para la
     * tabla y su cabecera, y se configuran los estilos visuales.
     *
     * @see JTable
     * @see DefaultTableModel
     * @see JTableHeader
     * @see Font
     * @see InputStream
     */
    private void inicializarTabla() {
        // Cargar la imagen de lupa
        ImageIcon lupaIcon = new ImageIcon(getClass().getResource("/lupa2.png"));

        // Modelo de la tabla: Define las columnas y las filas (inicialmente vacías)
        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"Comanda", "Mesa", "Estado", "Revisar"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Todas las celdas no son editables
            }
        };

        // Datos de ejemplo: Agrega filas con información de las comandas
        model.addRow(new Object[]{"C001", "Mesa 1", "Pendiente", lupaIcon});
        model.addRow(new Object[]{"C002", "Mesa 3", "En proceso", lupaIcon});
        model.addRow(new Object[]{"C003", "Mesa 5", "Entregado", lupaIcon});

        // Crear la tabla con el modelo de datos
        JTable table = new JTable(model);

        try {
            // Cargar la fuente personalizada desde un archivo .ttf
            InputStream is = getClass().getResourceAsStream("/Montserrat-Medium.ttf");
            Font montserrat = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(12f);

            // Aplicar la fuente a la cabecera de la tabla
            JTableHeader header = table.getTableHeader();
            header.setFont(montserrat);
            header.setBackground(Color.WHITE); // Fondo blanco
            header.setForeground(Color.BLACK); // Letra negra
            header.setBorder(BorderFactory.createEmptyBorder()); // Opcional: Sin bordes

            // Aplicar la fuente a las filas de la tabla
            table.setFont(montserrat);
            table.setRowHeight(40); // Altura de las filas

        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            // Si falla la carga de la fuente, se usa la predeterminada
        }

        // Centrar el texto de las columnas
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

        // Renderizador para la columna de icono (columna 3)
        table.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus,
                    int row, int column) {
                JLabel label = new JLabel();
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setIcon(lupaIcon);
                return label;
            }
        });

        // Configuración adicional de la cabecera de la tabla
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Montserrat", Font.BOLD, 14));
        header.setBackground(new Color(60, 63, 65)); // Fondo gris oscuro
        header.setForeground(Color.WHITE); // Texto blanco
        header.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1)); // Borde negro delgado
        table.setRowHeight(40); // Alto de las filas

        // Crear un JScrollPane para agregar la tabla y configurarlo en el panel
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 70, 760, 300); // Ajustar tamaño y posición
        fondo.setLayout(null); // Layout absoluto para posicionar manualmente
        fondo.add(scrollPane);

        // Agregar un MouseListener para abrir una nueva ventana con la información de la comanda
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                int column = table.columnAtPoint(e.getPoint());

                // Verifica si la columna seleccionada es la de la lupa (columna 3)
                if (column == 3) {
                    // Obtiene los datos de la fila seleccionada
                    String comanda = table.getValueAt(row, 0).toString();
                    String mesa = table.getValueAt(row, 1).toString();
                    String estado = table.getValueAt(row, 2).toString();

                    // Abre una nueva ventana con la información detallada de la comanda
                    InformacionComanda ventana = new InformacionComanda(comanda, mesa, estado);
                    ventana.setVisible(true);
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fondo = new javax.swing.JPanel();
        labelTexto = new javax.swing.JLabel();
        botonAgregarComanda = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fondo.setBackground(new java.awt.Color(255, 255, 255));
        fondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelTexto.setFont(new java.awt.Font("Montserrat Medium", 1, 24)); // NOI18N
        labelTexto.setText("COMANDAS");
        fondo.add(labelTexto, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 20, 160, -1));

        botonAgregarComanda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botonAgregarComanda.png"))); // NOI18N
        fondo.add(botonAgregarComanda, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 430, 300, 40));

        getContentPane().add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 500));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel botonAgregarComanda;
    private javax.swing.JPanel fondo;
    private javax.swing.JLabel labelTexto;
    // End of variables declaration//GEN-END:variables
}
