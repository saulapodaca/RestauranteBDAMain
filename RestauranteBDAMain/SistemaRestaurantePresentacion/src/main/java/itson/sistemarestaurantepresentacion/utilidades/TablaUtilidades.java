package itson.sistemarestaurantepresentacion.utilidades;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;


public class TablaUtilidades {

    /**
     * Configura el JTable para mostrar los ingredientes, establecer las
     * cabeceras, el formato visual y los listeners.
     *
     * @param tabla El JTable que se desea configurar.
     * @param modelo El DefaultTableModel de la tabla.
     * @param listener El listener que debe ejecutarse cuando se modifique el
     * stock.
     */
    public static void configurarTabla(JTable tabla, DefaultTableModel modelo, TableModelListener listener) {
        tabla.setModel(modelo);
        tabla.setRowHeight(40);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(200);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(150);
        tabla.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
        tabla.setFont(new Font("Arial", Font.BOLD, 16));

        // Configurar cabeceras de la tabla
        JTableHeader header = tabla.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 16));
        header.setBackground(Color.LIGHT_GRAY);
        header.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));

        // Centrar el texto de las cabeceras y las celdas
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                ((JLabel) component).setHorizontalAlignment(SwingConstants.CENTER);
                return component;
            }
        });

        tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                ((JLabel) c).setHorizontalAlignment(SwingConstants.CENTER);
                return c;
            }
        });

        // Agregar el listener para la modificación de stock
        modelo.addTableModelListener(listener);
    }

    /**
     * Configura la columna ID para que no sea visible.
     *
     * @param tabla El JTable al que se le debe ocultar la columna ID.
     */
    public static void ocultarColumnaId(JTable tabla) {
        tabla.getColumnModel().getColumn(0).setMinWidth(0);
        tabla.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla.getColumnModel().getColumn(0).setWidth(0);
    }
}
