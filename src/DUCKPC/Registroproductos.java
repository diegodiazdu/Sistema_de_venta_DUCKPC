package DUCKPC;

import Controlador.Conexion;

import java.sql.*;
import java.util.*;
import javax.swing.*;

import javax.swing.table.DefaultTableModel;

public class Registroproductos extends javax.swing.JFrame {

    Conexion con = new Conexion();
    Connection cn = con.conectar();
    DefaultTableModel model = new DefaultTableModel();

    public Registroproductos() {
        initComponents();
        this.setResizable(false);
        tablaCompra.getTableHeader().enable(false);
        /*##### LLenar combobox #####*/
        ArrayList<String> lista = new ArrayList<String>();
        lista = llenarCombo();
        for (int x = 0; x < lista.size(); x++) {
            cbFolio.addItem(lista.get(x));
        }
        /*##### Fin llenar combobox #####*/
    }

    /*########## METODOS ##########*/
    public ArrayList<String> llenarCombo() {

        ArrayList<String> lista = new ArrayList<String>();

        String opSelect = "SELECT COM_NOFOLIO FROM COMPRA";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            ps = cn.prepareStatement(opSelect);
            rs = ps.executeQuery();

            while (rs.next()) {

                lista.add(rs.getString(1));

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al listar combobox", "oops!", JOptionPane.ERROR_MESSAGE);

        }

        return lista;

    }

    public boolean validaBlancos() {

        if (txtNombre.getText().isEmpty()
                || cbFolio.getSelectedItem().toString().equals("...")
                || txtCodigo.getText().isEmpty()
                || txtCosto.getText().isEmpty()
                || txtVenta.getText().isEmpty()
                || txtStock.getText().isEmpty()) {

            return true;

        } else {

            return false;
        }

    }

    public boolean existeProveedor(String prov_id) {

        PreparedStatement ps = null;
        ResultSet rs = null;

        String opSelect = "SELECT * FROM PROVEEDOR WHERE prov_id = ?";

        try {

            cn = con.conectar();
            ps = cn.prepareStatement(opSelect);
            ps.setString(1, prov_id);
            rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error en el bloque de validacion de cliente " + e.getMessage());

        }
        return true;
    }

    public void listarProveedor(String IdProveedor) {

        try {

            cn = con.conectar();

            PreparedStatement consulta = cn.prepareStatement("SELECT * FROM PROVEEDOR WHERE PROV_ID = ? ");

            consulta.setString(1, IdProveedor);

            ResultSet rs = consulta.executeQuery();

            if (rs.next()) {

                txtVenta.setText(rs.getString("prov_nombre"));

            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e.toString());

        }
    }

    public void agregarProducto() {

        model = (DefaultTableModel) tablaCompra.getModel();

        int item = 0;
        item = item + 1;
        String noFolio = cbFolio.getSelectedItem().toString();
        String codigo = txtCodigo.getText();
        String descripcion = txtNombre.getText();
        int precioCosto = Integer.parseInt(txtCosto.getText());
        int precioVenta = Integer.parseInt(txtVenta.getText());
        int cantidad = Integer.parseInt(txtStock.getText());

        ArrayList lista = new ArrayList();

        lista.add(item);
        lista.add(noFolio);
        lista.add(codigo);
        lista.add(descripcion);
        lista.add(precioCosto);
        lista.add(precioVenta);
        lista.add(cantidad);

        Object[] obj = new Object[8];

        obj[0] = lista.get(0);
        obj[1] = lista.get(1);
        obj[2] = lista.get(2);
        obj[3] = lista.get(3);
        obj[4] = lista.get(4);
        obj[5] = lista.get(5);
        obj[6] = lista.get(6);

        model.addRow(obj);
        tablaCompra.setModel(model);
        calcularTotal();

    }

    public void registrarProducto() {

        for (int i = 0; i < tablaCompra.getRowCount(); i++) {

            String codigo = tablaCompra.getValueAt(i, 2).toString();
            String descripcion = tablaCompra.getValueAt(i, 3).toString();
            int pCosto = Integer.parseInt(tablaCompra.getValueAt(i, 4).toString());
            int pVenta = Integer.parseInt(tablaCompra.getValueAt(i, 5).toString());
            int stock = Integer.parseInt(tablaCompra.getValueAt(i, 6).toString());

            String opInsert = ("INSERT INTO PRODUCTO"
                    + "(PRO_CODIGO,PRO_DESCRIPCION,PRO_PRECIO_COSTO,PRO_PRECIO_VENTA,PRO_STOCK)"
                    + "VALUES(?,?,?,?,?)");

            try {

                cn = con.conectar();
                PreparedStatement ps = cn.prepareStatement(opInsert);

                ps.setString(1, codigo);
                ps.setString(2, descripcion);
                ps.setInt(3, pCosto);
                ps.setInt(4, pVenta);
                ps.setInt(5, stock);

                ps.executeUpdate();

            } catch (Exception e) {

                JOptionPane.showMessageDialog(null, "Error al registrar producto " + e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    public void registrarDetalleCompra() {

        for (int i = 0; i < tablaCompra.getRowCount(); i++) {

            String nofolio = tablaCompra.getValueAt(i, 1).toString();
            String codigo = tablaCompra.getValueAt(i, 2).toString();
            int cantidad = Integer.parseInt(tablaCompra.getValueAt(i, 6).toString());
            int totalNeto = (Integer.parseInt(txtCosto.getText()) * cantidad);

            String opInsert = ("INSERT INTO DETALLE_COMPRA"
                    + "(DETCOM_COM_NOFOLIO,DETCOM_PRO_CODIGO,DETCOM_CANTIDAD,DETCOM_TOTAL)"
                    + "VALUES(?,?,?,?)");

            try {

                cn = con.conectar();
                PreparedStatement ps = cn.prepareStatement(opInsert);

                ps.setString(1, nofolio);
                ps.setString(2, codigo);
                ps.setInt(3, cantidad);
                ps.setInt(4, totalNeto);

                ps.executeUpdate();

            } catch (Exception e) {

                JOptionPane.showMessageDialog(null, "Error al registrar detalle de venta " + e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    public void calcularTotal() {

        int totalPagar = 0;
        int precioNeto;

        for (int i = 0; i < tablaCompra.getRowCount(); i++) {

            precioNeto = Integer.parseInt(tablaCompra.getValueAt(i, 4).toString());
            totalPagar = totalPagar + precioNeto;
        }

        //txtInversion.setText("" + totalPagar);
    }

    public void limpiarTabla() {

        for (int i = 0; i < model.getRowCount(); i++) {

            model.removeRow(i);

            i = i - 1;

        }
    }

    public void limpiarTxt() {

        txtNombre.setText("");
        txtVenta.setText("");
        txtStock.setText("");
        txtCosto.setText("");
        txtNombre.requestFocus();
    }


    /*########## FIN METODOS ##########*/
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtCosto = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtStock = new javax.swing.JTextField();
        txtVenta = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        cbFolio = new javax.swing.JComboBox<>();
        txtCodigo = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCompra = new javax.swing.JTable();
        btnGenerarVenta = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        jLabel5.setText("jLabel5");

        jLabel6.setText("jLabel6");

        jLabel3.setText("jLabel3");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel1.setText("REGISTRO DE PRODUCTOS DUCK-PC");

        jLabel2.setText("Venta y reparaciones de articulos tecnologicos");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel7.setText("Descripcion:");

        jLabel8.setText("Precio Costo:");

        jLabel9.setText("Cantidad:");

        jLabel12.setText("Precio venta:");

        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/sign-add-icon_34367.png"))); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        jLabel10.setText("Folio de compra:");

        cbFolio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "..." }));

        jLabel11.setText("Codigo:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(244, 244, 244)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel11)
                            .addComponent(jLabel8)
                            .addComponent(jLabel12)
                            .addComponent(jLabel9)))
                    .addComponent(jLabel10))
                .addGap(53, 53, 53)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbFolio, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                        .addComponent(txtCosto)
                        .addComponent(txtCodigo))
                    .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 284, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cbFolio, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/logo_duckpc_96x96.png"))); // NOI18N

        tablaCompra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N°", "Folio", "Codigo", "Descripcion", "Costo", "Precio venta", "Cantidad"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaCompra);

        btnGenerarVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/calculator-icon_34473.png"))); // NOI18N
        btnGenerarVenta.setText("Registrar productos");
        btnGenerarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarVentaActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/sign-error-icon_34362.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnGenerarVenta))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(173, 173, 173)
                                        .addComponent(jLabel1))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(206, 206, 206)
                                        .addComponent(jLabel2)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2))
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGenerarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        if (!validaBlancos()) {
            agregarProducto();
        } else {
            JOptionPane.showMessageDialog(null, "No puede dejar campos en blanco", "Oops!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnGenerarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarVentaActionPerformed

        if (!validaBlancos()) {
            registrarProducto();
            registrarDetalleCompra();
            JOptionPane.showMessageDialog(null, "Tarea realizada con éxito", "Correcto!", JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Primero debes ingresar una compra", "Oops", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnGenerarVentaActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed

    }//GEN-LAST:event_btnCancelarActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Registroproductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registroproductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registroproductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registroproductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Registroproductos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGenerarVenta;
    private javax.swing.JComboBox<String> cbFolio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaCompra;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtCosto;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtStock;
    private javax.swing.JTextField txtVenta;
    // End of variables declaration//GEN-END:variables
}
