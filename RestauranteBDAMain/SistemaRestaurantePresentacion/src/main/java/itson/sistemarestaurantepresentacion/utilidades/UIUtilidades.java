package itson.sistemarestaurantepresentacion.utilidades;

import java.awt.Color;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.Timer;

public class UIUtilidades {

    /**
     * Muestra un feedback visual en la tabla para indicar que el stock fue
     * actualizado.
     *
     * @param tabla El JTable donde se debe mostrar la retroalimentación.
     * @param fila La fila que fue modificada.
     */
    public static void mostrarFeedbackDeEdicion(JTable tabla, int fila) {
        tabla.setRowSelectionInterval(fila, fila);
        tabla.setSelectionBackground(Color.GREEN);
        new Timer(1000, evt -> {
            tabla.clearSelection();
            tabla.setSelectionBackground(null);
        }).start();
    }

    public static void configurarComponente(JComponent componente, int x, int y, int width, int height) {
        componente.setBounds(x, y, width, height);
    }
    
    public static void actualizarPanel(JPanel contenedor, JComponent nuevoPanel) {
        contenedor.removeAll();
        contenedor.add(nuevoPanel);
        contenedor.revalidate();
        contenedor.repaint();
    }

    public static void agregarComponentes(JPanel contenedor, JComponent... componentes) {
        for (JComponent c : componentes) 
            contenedor.add(c);        
    }

    public static void mostrarComponente(JComponent componenteVisible, JComponent componenteOculto) {
        componenteVisible.setVisible(true);
        componenteOculto.setVisible(false);
    }

    public static void ocultarComponente(JComponent componenteVisible, JComponent componenteOculto) {
        componenteVisible.setVisible(false);
        componenteOculto.setVisible(true);
    }

}
