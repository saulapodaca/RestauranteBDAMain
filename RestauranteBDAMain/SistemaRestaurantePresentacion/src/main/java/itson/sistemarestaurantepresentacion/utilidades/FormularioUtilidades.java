package itson.sistemarestaurantepresentacion.utilidades;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class FormularioUtilidades {
/**
     * Configura el comportamiento del placeholder en un JTextField.
     * @param textField El JTextField al que se le quiere configurar el placeholder
     * @param placeholderText El texto que aparecerá como placeholder
     */
    public static void configurarPlaceholder(JTextField textField, String placeholderText) {
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

    /**
     * Configura los placeholders en los JTextFields de un formulario.
     * @param textFields Los JTextFields que van a tener un placeholder.
     * @param placeholderText El texto que aparecerá como placeholder.
     */
    public static void configurarPlaceholders(JTextField[] textFields, String placeholderText) {
        for (JTextField textField : textFields)
            configurarPlaceholder(textField, placeholderText);        
    }
    
    /**
     * Configura la búsqueda en tiempo real para un JTextField y opcionalmente un JComboBox.
     * @param textField El JTextField donde se escribirá la búsqueda.
     * @param comboBox El JComboBox con las opciones de filtro (puede ser null si no se usa).
     * @param listener El listener que se invoca para actualizar la búsqueda.
     */
    public static void configurarBusquedaEnTiempoReal(JTextField textField, JComboBox<String> comboBox, Runnable listener) {
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                listener.run();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                listener.run();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                listener.run();
            }
        });
        // Si no hay JComboBox, solo escuchar los cambios en el JTextField
        
        if (comboBox != null) // Si hay JComboBox, también agregar el listener del combo
            comboBox.addActionListener(e -> {listener.run();
                // Volver a enfocar el campo de texto
                textField.requestFocusInWindow();
            });
        }
    }
