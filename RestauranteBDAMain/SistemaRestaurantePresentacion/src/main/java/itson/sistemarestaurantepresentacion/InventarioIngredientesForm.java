package itson.sistemarestaurantepresentacion;

import itson.sistemarestaurantepresentacion.listeners.IngredientesRegistradosListener;
import itson.sistemarestaurantedominio.dtos.IngredienteRegistradoDTO;
import itson.sistemarestaurantenegocio.IIngredientesBO;
import itson.sistemarestaurantenegocio.excepciones.StockInvalidoException;
import itson.sistemarestaurantepresentacion.utilidades.TablaUtilidades;
import itson.sistemarestaurantepresentacion.utilidades.UIUtilidades;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;

/**
 * Clase que representa el formulario principal para la gestión del inventario
 * de ingredientes. Permite buscar ingredientes, visualizar los resultados en 
 * una tabla y modificar el stock directamente desde la interfaz gráfica.
 * 
 * Implementa IngredientesRegistradosListener para recibir los resultados
 * del
 * panel de búsqueda.
 */
public class InventarioIngredientesForm extends javax.swing.JFrame implements IngredientesRegistradosListener {

    private DefaultTableModel modeloTabla;
    private JTable tablaResultados;
    private JScrollPane jScrollPane1;
    private IIngredientesBO ingredientesBO;

    /**
     * Constructor del formulario InventarioIngredientesForm.
     *
     * @param ingredientesBO capa de negocio que permite acceder y modificar la
     * información de los ingredientes.
     */
    public InventarioIngredientesForm(IIngredientesBO ingredientesBO) {
        this.ingredientesBO = ingredientesBO;
        initComponents();
        crearTabla();
        buscadorIngredientesPanel1.iniciarBusqueda(ingredientesBO, this);
        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 600, 350));
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        buscadorIngredientesPanel1 = new itson.sistemarestaurantepresentacion.buscadores.BuscadorIngredientesPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(buscadorIngredientesPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 812, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Método que se llama cuando se reciben los ingredientes filtrados desde el
     * panel de búsqueda. Actualiza la tabla con los nuevos resultados.
     *
     * @param ingredientes lista de ingredientes que cumplen con los filtros
     * aplicados.
     */
    @Override
    public void onIngredientesRegistrados(List<IngredienteRegistradoDTO> ingredientes) {
        actualizarTabla(ingredientes);
    }

    /**
     * Crea y configura la tabla donde se muestran los ingredientes encontrados.
     * Establece las columnas, formato visual y los listeners para edición de
     * stock.
     */
    private void crearTabla() {
        modeloTabla = new DefaultTableModel(new String[]{"ID", "Nombre", "Stock", "Unidad"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Solo se permite editar la columna de stock (índice 2)
                return column == 2;
            }
        };
        tablaResultados = new JTable(modeloTabla);
        jScrollPane1 = new JScrollPane(tablaResultados);

        // Usar las utilidades para configurar la tabla
        TablaUtilidades.configurarTabla(tablaResultados, modeloTabla, (TableModelEvent e) -> {
            if (e.getType() == TableModelEvent.UPDATE) {
                int fila = e.getFirstRow();
                int columna = e.getColumn();
                if (columna == 2) { // Solo si es la columna de stock
                    Long id = (Long) modeloTabla.getValueAt(fila, 0);
                    String nuevoStockTexto = modeloTabla.getValueAt(fila, columna).toString();
                    try {
                        ingredientesBO.actualizarStockIngrediente(id, nuevoStockTexto);
                        UIUtilidades.mostrarFeedbackDeEdicion(tablaResultados, fila);
                    } catch (StockInvalidoException ex) {
                        JOptionPane.showMessageDialog(jPanel1, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // Ocultar columna ID
        TablaUtilidades.ocultarColumnaId(tablaResultados);
    }

    /**
     * Método que actualiza el contenido de la tabla con una nueva lista de
     * ingredientes.
     * 
     * @param ingredientes lista de ingredientes a mostrar en la tabla.
     */
    public void actualizarTabla(List<IngredienteRegistradoDTO> ingredientes) {
        modeloTabla.setRowCount(0);
        for (IngredienteRegistradoDTO ing : ingredientes) 
            modeloTabla.addRow(new Object[]{
                ing.getId(),
                ing.getNombre(), 
                ing.getStock(), 
                ing.getUnidadMedidaIngrediente().toString()
            });    
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private itson.sistemarestaurantepresentacion.buscadores.BuscadorIngredientesPanel buscadorIngredientesPanel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}