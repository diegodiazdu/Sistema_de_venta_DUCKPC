
package DUCKPC;

import Controlador.Conexion;
import java.awt.Desktop;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class inf_GananciasEsperadas extends javax.swing.JFrame {
    
    Conexion con = new Conexion();
    Connection cn = con.conectar();

    public inf_GananciasEsperadas() {
        initComponents();
        //listarTabla();
         /*##### LLenar combobox #####*/
        ArrayList<String> lista = new ArrayList<String>();
        lista = llenarComboInicio();
        for (int x = 0; x < lista.size(); x++) {
            jbFolio.addItem(lista.get(x));
           
        }
        /*##### Fin llenar combobox #####*/
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaGanacias = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        btnExcel = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();
        jbFolio = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tablaGanacias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Stock", "Precio Compra", "Precio Venta", "Ganancia"
            }
        ));
        jScrollPane1.setViewportView(tablaGanacias);

        jButton2.setText("Volver");

        btnExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/file_type_excel_icon_130611.png"))); // NOI18N
        btnExcel.setText(" Exportar excel");
        btnExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcelActionPerformed(evt);
            }
        });

        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/3925426-print-printer-printing-icon_111556.png"))); // NOI18N
        btnImprimir.setText("      Imprimir");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        jbFolio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "..." }));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel6.setText("GANANCIAS ESPERADAS");

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("N° Folio");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/logo_duckpc_96x96.png"))); // NOI18N

        jButton1.setText("Buscar por N° de folio");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 992, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnImprimir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnExcel))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jbFolio, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addGap(252, 252, 252)
                        .addComponent(jLabel2)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbFolio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(34, 34, 34)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        if(tablaVacia(tablaGanacias)){
            JOptionPane.showMessageDialog(null, "No hay nada para exportar");
        }else{
        imprimir("Ganancias esperadas", rootPaneCheckingEnabled);
        }
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void btnExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcelActionPerformed
        if(tablaVacia(tablaGanacias)){
            JOptionPane.showMessageDialog(null, "No hay nada para exportar");
        }else{
             try {
            exportarExcel(tablaGanacias);
        } catch (IOException ex) {
            Logger.getLogger(inf_GananciasEsperadas.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
       
    }//GEN-LAST:event_btnExcelActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(jbFolio.getSelectedItem().toString().equals("...")){
            
            JOptionPane.showMessageDialog(null, "Debes seleccioanar un N° de folio para filtrar","Oops!", JOptionPane.WARNING_MESSAGE);
        }else{
            filtrarFolio();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[]) {
       
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(inf_GananciasEsperadas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(inf_GananciasEsperadas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(inf_GananciasEsperadas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(inf_GananciasEsperadas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new inf_GananciasEsperadas().setVisible(true);
            }
        });
    }
    
    public void listarTabla() {

        String[] titulos = {"Nombre", "Stock", "Precio Compra", "Precio Venta", "Ganancia"};
        String[] registros = new String[6];

        DefaultTableModel modelo = new DefaultTableModel(null, titulos);

        String opSelect = ("SELECT PRO_DESCRIPCION, PRO_PRECIO_VENTA, PRO_PRECIO_COSTO, PRO_STOCK,"
                         + "(PRO_PRECIO_VENTA - PRO_PRECIO_COSTO) * PRO_STOCK AS PRO_GANANCIA FROM PRODUCTO");

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(opSelect);

            while (rs.next()) {

                registros[0] = rs.getString("PRO_DESCRIPCION");
                registros[1] = rs.getString("PRO_STOCK");
                registros[2] = rs.getString("PRO_PRECIO_COSTO");
                registros[3] = rs.getString("PRO_PRECIO_VENTA");
                registros[4] = rs.getString("PRO_GANANCIA");
                

                modelo.addRow(registros);

            }

            tablaGanacias.setModel(modelo);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al listar los datos " + e.getMessage());

        }

    }
    
    public void filtrarFolio() {

        String[] titulos = {"Nombre", "Stock", "Precio Compra", "Precio Venta", "Ganancia"};
        String[] registros = new String[6];
        
        int folio = Integer.parseInt(jbFolio.getSelectedItem().toString());

        DefaultTableModel modelo = new DefaultTableModel(null, titulos);

        String opSelect = ("SELECT producto.pro_descripcion, producto.pro_precio_costo, producto.pro_precio_venta, detcom_cantidad, "
                       + "((producto.pro_precio_venta - producto.pro_precio_costo) * detcom_cantidad) AS GANANCIA FROM producto, detalle_compra "
                       + "WHERE producto.pro_codigo = detalle_compra.detcom_pro_codigo AND detcom_com_nofolio = '"+folio+"'");

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(opSelect);

            while (rs.next()) {

                registros[0] = rs.getString("producto.pro_descripcion");
                registros[1] = rs.getString("detcom_cantidad");
                registros[2] = rs.getString("producto.pro_precio_costo");
                registros[3] = rs.getString("producto.pro_precio_venta");
                registros[4] = rs.getString("GANANCIA");
                

                modelo.addRow(registros);

            }

            tablaGanacias.setModel(modelo);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al listar los datos " + e.getMessage());

        }

    }
    
    public void exportarExcel(JTable t) throws IOException {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de excel", "xls");
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Guardar archivo");
        chooser.setAcceptAllFileFilterUsed(false);
        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            String ruta = chooser.getSelectedFile().toString().concat(".xls");
            try {
                File archivoXLS = new File(ruta);
                if (archivoXLS.exists()) {
                    archivoXLS.delete();
                }
                archivoXLS.createNewFile();
                Workbook libro = new HSSFWorkbook();
                FileOutputStream archivo = new FileOutputStream(archivoXLS);
                Sheet hoja = libro.createSheet("Mi hoja de trabajo 1");
                hoja.setDisplayGridlines(false);
                for (int f = 0; f < t.getRowCount(); f++) {
                    Row fila = hoja.createRow(f);
                    for (int c = 0; c < t.getColumnCount(); c++) {
                        Cell celda = fila.createCell(c);
                        if (f == 0) {
                            celda.setCellValue(t.getColumnName(c));
                        }
                    }
                }
                int filaInicio = 1;
                for (int f = 0; f < t.getRowCount(); f++) {
                    Row fila = hoja.createRow(filaInicio);
                    filaInicio++;
                    for (int c = 0; c < t.getColumnCount(); c++) {
                        Cell celda = fila.createCell(c);
                        if (t.getValueAt(f, c) instanceof Double) {
                            celda.setCellValue(Double.parseDouble(t.getValueAt(f, c).toString()));
                        } else if (t.getValueAt(f, c) instanceof Float) {
                            celda.setCellValue(Float.parseFloat((String) t.getValueAt(f, c)));
                        } else {
                            celda.setCellValue(String.valueOf(t.getValueAt(f, c)));
                        }
                    }
                }
                libro.write(archivo);
                archivo.close();
                Desktop.getDesktop().open(archivoXLS);
            } catch (IOException | NumberFormatException e) {
                throw e;
            }
        }
    }

    public void imprimir(String header, boolean showPrintDialog) {
        boolean fitWidth = true;
        boolean interactive = true;
        // We define the print mode (Definimos el modo de impresión)
        JTable.PrintMode mode = fitWidth ? JTable.PrintMode.FIT_WIDTH : JTable.PrintMode.NORMAL;
        try {
            // Print the table (Imprimo la tabla)             
            boolean complete = tablaGanacias.print(mode,
                    new MessageFormat(header),
                    new MessageFormat("Page{0,number,integer}"),
                    showPrintDialog,
                    null,
                    interactive);
            if (complete) {
                // Mostramos el mensaje de impresión existosa
                JOptionPane.showMessageDialog(tablaGanacias,
                        "Print complete (Impresión completa)",
                        "Print result (Resultado de la impresión)",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Mostramos un mensaje indicando que la impresión fue cancelada                 
                JOptionPane.showMessageDialog(tablaGanacias,
                        "Print canceled (Impresión cancelada)",
                        "Print result (Resultado de la impresión)",
                        JOptionPane.WARNING_MESSAGE);
            }
        } catch (PrinterException pe) {
            JOptionPane.showMessageDialog(tablaGanacias,
                    "Print fail (Fallo de impresión): " + pe.getMessage(),
                    "Print result (Resultado de la impresión)",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static boolean tablaVacia(JTable jTable) {
        if (jTable != null && jTable.getModel() != null) {
            return jTable.getModel().getRowCount() <= 0 ? true : false;
        }
        return false;
    }
    
    public ArrayList<String> llenarComboInicio() {

        ArrayList<String> lista = new ArrayList<String>();

        String opSelect = "SELECT DISTINCT detcom_com_nofolio FROM detalle_compra";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            ps = cn.prepareStatement(opSelect);
            rs = ps.executeQuery();

            while (rs.next()) {

                lista.add(rs.getString(1));

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al listar combobox " + e.getMessage(), "oops!", JOptionPane.ERROR_MESSAGE);

        }

        return lista;

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExcel;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> jbFolio;
    private javax.swing.JTable tablaGanacias;
    // End of variables declaration//GEN-END:variables
}
