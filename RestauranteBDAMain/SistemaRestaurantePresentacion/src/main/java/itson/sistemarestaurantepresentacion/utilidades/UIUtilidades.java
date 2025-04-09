package itson.sistemarestaurantepresentacion.utilidades;

import java.awt.Color;
import javax.swing.JTable;
import javax.swing.Timer;


public class UIUtilidades {

    /**
     * Muestra un feedback visual en la tabla para indicar que el stock fue actualizado.
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
}
