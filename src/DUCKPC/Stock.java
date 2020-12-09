package DUCKPC;

import Controlador.*;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class Stock extends javax.swing.JFrame {

    private TableRowSorter trsfiltro;
    Conexion con = new Conexion();
    Connection cn = con.conectar();

    public Stock() {
        initComponents();
        listarTabla();
        /*##### LLenar combobox #####*/
        ArrayList<String> lista = new ArrayList<String>();
        lista = llenarComboInicio();
        for (int x = 0; x < lista.size(); x++) {
            jbFolio.addItem(lista.get(x));

        }
        /*##### Fin llenar combobox #####*/
        this.tableProductos.setDefaultRenderer(Object.class, new alertaColor());

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableProductos = new javax.swing.JTable();
        txtBuscar = new javax.swing.JTextField();
        jbFolio = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tableProductos.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tableProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Descripcion", "Precio", "Stock"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableProductos.setMaximumSize(new java.awt.Dimension(50, 320));
        jScrollPane1.setViewportView(tableProductos);

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });

        jbFolio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "..." }));

        jButton1.setText("Filtrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("Buscar por nombre");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("N° de Folio");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel6.setText("LISTADO DE PRODUCTOS");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/logo_duckpc_96x96.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 605, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jbFolio, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(1009, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(65, 65, 65)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(460, 460, 460)
                    .addComponent(jLabel6)
                    .addContainerGap(449, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jbFolio)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 619, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(96, 96, 96)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(695, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(771, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
        txtBuscar.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                String cadena = (txtBuscar.getText().toUpperCase());
                txtBuscar.setText(cadena);
                repaint();
                filtro();
            }
        });

        trsfiltro = new TableRowSorter(tableProductos.getModel());
        tableProductos.setRowSorter(trsfiltro);
    }//GEN-LAST:event_txtBuscarKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if (jbFolio.getSelectedItem().toString().equals("...")) {

            JOptionPane.showMessageDialog(null, "Debes seleccioanar un N° de folio para filtrar", "Oops!", JOptionPane.WARNING_MESSAGE);

        } else {

            filtrarFolio();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    public void filtro() {

        trsfiltro.setRowFilter(RowFilter.regexFilter(txtBuscar.getText(), 1));
    }

    public void listarTabla() {

        String[] titulos = {"Código", "Descripción", "Precio", "Stock"};
        String[] registros = new String[5];

        DefaultTableModel modelo = new DefaultTableModel(null, titulos);
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modelo);
        tableProductos.setRowSorter(sorter);

        String opSelect = ("SELECT pro_codigo, pro_descripcion,pro_precio_venta,pro_stock FROM PRODUCTO");

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(opSelect);

            while (rs.next()) {

                registros[0] = rs.getString("pro_codigo");
                registros[1] = rs.getString("pro_descripcion").toUpperCase();
                registros[2] = rs.getString("pro_precio_venta");
                registros[3] = rs.getString("pro_stock");

                modelo.addRow(registros);

            }

            tableProductos.setModel(modelo);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al listar los datos " + e);

        }

    }

    public void filtrarFolio() {

        String[] titulos = {"Código", "Descripción", "Precio", "Stock"};
        String[] registros = new String[5];
        int folio = Integer.parseInt(jbFolio.getSelectedItem().toString());

        DefaultTableModel modelo = new DefaultTableModel(null, titulos);
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modelo);
        tableProductos.setRowSorter(sorter);

        String opSelect = ("SELECT producto.pro_codigo,producto.pro_descripcion,producto.pro_precio_venta,producto.pro_stock "
                + "FROM producto, detalle_compra "
                + "WHERE producto.pro_codigo = detalle_compra.detcom_pro_codigo AND detcom_com_nofolio = '" + folio + "'");

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(opSelect);

            while (rs.next()) {

                registros[0] = rs.getString("producto.pro_codigo");
                registros[1] = rs.getString("producto.pro_descripcion").toUpperCase();
                registros[2] = rs.getString("producto.pro_precio_venta");
                registros[3] = rs.getString("producto.pro_stock");

                modelo.addRow(registros);

            }

            tableProductos.setModel(modelo);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al listar los datos " + e);

        }

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

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Stock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Stock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Stock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Stock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Stock().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> jbFolio;
    private javax.swing.JTable tableProductos;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
