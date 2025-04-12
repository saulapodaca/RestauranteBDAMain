/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package itson.sistemarestaurantepresentacion.buscadores;

import itson.sistemarestaurantedominio.ClienteFrecuente;
import itson.sistemarestaurantenegocio.IClientesFrecuentesBO;
import itson.sistemarestaurantepresentacion.listeners.ClientesFiltradosListener;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author rauln
 */
public class BuscadorClientesFrecuentesPanel extends javax.swing.JPanel {

    private IClientesFrecuentesBO clientesFrecuentesBO;
    private ClientesFiltradosListener listener;
    private static final String DEFAULT_BUSCADOR = "INGRESE EL CLIENTE QUE DESEA BUSCAR . . .";

    /**
     * Crea una nueva instancia del panel {@code BuscadorClientesFrecuentesPanel}.
     * Este constructor inicializa los componentes gráficos del panel, configura los placeholders
     * para los campos de texto y habilita la búsqueda en tiempo real.
     * 
     * @see #initComponents()
     * @see #configurarPlaceholders()
     * @see #configurarBusquedaEnTiempoReal()
     */
    public BuscadorClientesFrecuentesPanel() {
        initComponents();
        configurarPlaceholders();
        configurarBusquedaEnTiempoReal();
    }



    /**
     * Establece el objeto {@code IClientesFrecuentesBO} que se utilizará para acceder a la lógica de negocio 
     * relacionada con los clientes frecuentes.
     * Este método permite inyectar el objeto que implementa la interfaz {@code IClientesFrecuentesBO}, 
     * lo cual es útil para configurar la capa de negocio en el componente.
     * 
     * @param clientesFrecuentesBO El objeto que implementa la interfaz {@link IClientesFrecuentesBO}, 
     *                             que se encargará de la lógica de negocio para los clientes frecuentes.
     */
    public void setClientesFrecuentesBO(IClientesFrecuentesBO clientesFrecuentesBO) {
        this.clientesFrecuentesBO = clientesFrecuentesBO;
    }


    /**
     * Configura un placeholder para un campo de texto {@link JTextField}.
     * Este método establece el texto y el color inicial del placeholder en el campo de texto. 
     * Además, agrega un {@link FocusListener} para gestionar el comportamiento del placeholder cuando el campo obtiene o pierde el foco:
     * - Cuando el campo obtiene el foco y el texto es el placeholder, se borra el texto y se cambia el color a negro.
     * - Cuando el campo pierde el foco y el texto está vacío, se restaura el placeholder con el color gris.
     * 
     * @param textField El campo de texto en el que se aplicará el placeholder.
     * @param placeholderText El texto que se usará como placeholder.
     */
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


    /**
     * Configura los placeholders para todos los campos de entrada.
     * En este caso, configura el placeholder para el campo de búsqueda {@code textFieldBuscador} 
     * utilizando el valor predeterminado {@code DEFAULT_BUSCADOR}.
     * 
     * Este método es útil para asegurar que los campos de texto tengan un valor de placeholder 
     * visible cuando no contienen texto, mejorando la experiencia del usuario al indicar el tipo de entrada esperada.
     * 
     * @see #configurarPlaceholder(javax.swing.JTextField, String)
     */
    private void configurarPlaceholders() {
        configurarPlaceholder(textFieldBuscador, DEFAULT_BUSCADOR);
    }


    /**
     * Configura la búsqueda en tiempo real para el campo de texto {@code textFieldBuscador}.
     * Este método agrega un {@link DocumentListener} al campo de texto que escuchará los cambios en el contenido
     * y ejecutará el método {@link #actualizarBusqueda()} cada vez que el texto cambie, ya sea por inserción, 
     * eliminación o modificación del texto. Esto permite realizar la búsqueda de clientes de forma automática 
     * a medida que el usuario escribe en el campo de búsqueda.
     * 
     * @see #actualizarBusqueda()
     */
    private void configurarBusquedaEnTiempoReal() {
        textFieldBuscador.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                actualizarBusqueda();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                actualizarBusqueda();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                actualizarBusqueda();
            }
        });
    }


    /**
     * Actualiza la búsqueda de clientes frecuentes según el texto ingresado en el campo de búsqueda.
     * Si el texto ingresado es igual al valor por defecto del campo o si el objeto {@code clientesFrecuentesBO} 
     * no está configurado, no realiza la búsqueda.
     * Si hay texto válido en el campo de búsqueda y el BO está configurado, se realiza la búsqueda de los 
     * clientes frecuentes y se notifica a través del {@link ClientesFiltradosListener} los resultados obtenidos.
     * 
     * @see ClientesFrecuentesBO#buscarClientes(String)
     * @see ClientesFiltradosListener#onClientesFiltrados(List)
     */
    private void actualizarBusqueda() {
        // Verificar si el texto es igual al placeholder o si el BO no está configurado
        if (textFieldBuscador.getText().equals(DEFAULT_BUSCADOR)
                || clientesFrecuentesBO == null) {
            return; // No buscar si es texto placeholder o no hay BO configurado
        }

        String filtro = textFieldBuscador.getText().trim();

        try {
            List<ClienteFrecuente> resultados = clientesFrecuentesBO.buscarClientes(filtro);

            // Notificar al frame o panel donde se usa este buscador
            if (listener != null) {
                listener.onClientesFiltrados(resultados);
            }
        } catch (Exception ex) {
            System.err.println("Error al buscar clientes: " + ex.getMessage());
        }
    }


    /**
     * Establece el listener que se encargará de manejar los eventos relacionados con los clientes filtrados.
     * Este método permite asignar un objeto que implemente la interfaz {@link ClientesFiltradosListener} 
     * para que pueda recibir y procesar los eventos cuando los clientes filtrados cambien.
     * 
     * @param listener El objeto que implementa la interfaz {@link ClientesFiltradosListener}, 
     *                 el cual se encargará de manejar los eventos de clientes filtrados.
     * @see ClientesFiltradosListener
     */
    public void setClientesFiltradosListener(ClientesFiltradosListener listener) {
        this.listener = listener;
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textFieldBuscador = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        textFieldBuscador.setFont(new java.awt.Font("Montserrat Medium", 1, 12)); // NOI18N
        textFieldBuscador.setForeground(new java.awt.Color(204, 204, 204));
        textFieldBuscador.setText("INGRESE EL CLIENTE QUE DESEA BUSCAR . . .");
        textFieldBuscador.setBorder(null);
        textFieldBuscador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldBuscadorActionPerformed(evt);
            }
        });
        add(textFieldBuscador, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 10, 400, 20));
        add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 30, 400, 5));
    }// </editor-fold>//GEN-END:initComponents

    private void textFieldBuscadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldBuscadorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldBuscadorActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField textFieldBuscador;
    // End of variables declaration//GEN-END:variables


}
