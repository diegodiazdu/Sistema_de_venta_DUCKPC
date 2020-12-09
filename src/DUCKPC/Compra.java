package DUCKPC;

import Controlador.Conexion;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import static javax.swing.JOptionPane.YES_OPTION;
import javax.swing.table.DefaultTableModel;

public class Compra extends javax.swing.JFrame {

    Conexion con = new Conexion();
    Connection cn;
    DefaultTableModel model = new DefaultTableModel();

    public Compra() {
        initComponents();
        setFecha();
        tablaCompra.getTableHeader().enable(false);
        this.setResizable(false);
    }

    /*########## METODOS ##########*/
    public void calcularIVA(int precio) {

        double iva = 0.19;

        if (precio > 180) {

            precio = (int) (precio + (precio * iva));
        }

        txtTotalNeto.setText("" + precio);
    }

    public boolean validaBlancos() {

        if (txtnoFolio.getText().isEmpty()
                || txtProveedor.getText().isEmpty()
                || txtTotalBruto.getText().isEmpty()
                || txtTotalNeto.getText().isEmpty()
                || txtFecha.getText().isEmpty()) {

            return true;

        } else {

            return false;
        }

    }

    public void setFecha() {
        Calendar fecha = Calendar.getInstance();
        Calendar fechaG = new GregorianCalendar();

        int annio = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);

        txtFecha.setText(annio + "-" + (mes + (1)) + "-" + dia);
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

                txtProvNombre.setText(rs.getString("prov_nombre"));

            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e.toString());

        }
    }

    public void agregarProducto() {

        model = (DefaultTableModel) tablaCompra.getModel();

        int item = 0;
        item = item + 1;
        String noFolio = txtnoFolio.getText();
        String proveedor = txtProveedor.getText();
        int totalBruto = Integer.parseInt(txtTotalBruto.getText());
        int totalNeto = Integer.parseInt(txtTotalNeto.getText());

        ArrayList lista = new ArrayList();

        lista.add(item);
        lista.add(noFolio);
        lista.add(proveedor);
        lista.add(totalBruto);
        lista.add(totalNeto);

        Object[] obj = new Object[5];

        obj[0] = lista.get(0);
        obj[1] = lista.get(1);
        obj[2] = lista.get(2);
        obj[3] = lista.get(3);
        obj[4] = lista.get(4);

        model.addRow(obj);
        tablaCompra.setModel(model);
        calcularTotal();

    }

    public void registrarCompra() {

        for (int i = 0; i < tablaCompra.getRowCount(); i++) {

            String nofolio = tablaCompra.getValueAt(i, 1).toString();
            String proveedor = tablaCompra.getValueAt(i, 2).toString();
            int totalBruto = Integer.parseInt(tablaCompra.getValueAt(i, 3).toString());
            int totalNeto = Integer.parseInt(tablaCompra.getValueAt(i, 4).toString());
            String fecha = txtFecha.getText();

            String opInsert = ("INSERT INTO COMPRA(COM_NOFOLIO,COM_PROV_ID,COM_FECHA,COM_TOTAL_BRUTO,COM_TOTAL_NETO)VALUES(?,?,?,?,?)");

            try {

                cn = con.conectar();
                PreparedStatement ps = cn.prepareStatement(opInsert);

                ps.setString(1, nofolio);
                ps.setString(2, proveedor);
                ps.setString(3, fecha);
                ps.setInt(4, totalBruto);
                ps.setInt(5, totalNeto);

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

        txtInversion.setText("" + totalPagar);

    }

    public void limpiarTabla() {

        for (int i = 0; i < model.getRowCount(); i++) {

            model.removeRow(i);

            i = i - 1;

        }
    }

    public void limpiarTxt() {

        txtnoFolio.setText("");
        txtProvNombre.setText("");
        txtTotalBruto.setText("");
        txtTotalNeto.setText("");
        txtProveedor.setText("");
        txtnoFolio.requestFocus();
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
        txtnoFolio = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtProveedor = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        btnBuscarProv = new javax.swing.JButton();
        txtFecha = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCompra = new javax.swing.JTable();
        btnCancelar = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        txtInversion = new javax.swing.JTextField();
        btnGenerarVenta = new javax.swing.JButton();
        txtTotalBruto = new javax.swing.JTextField();
        btnCalcularIva = new javax.swing.JButton();
        txtTotalNeto = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtProvNombre = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        jLabel5.setText("jLabel5");

        jLabel6.setText("jLabel6");

        jLabel3.setText("jLabel3");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel1.setText("REGISTRO DE COMPRAS DUCK-PC");

        jLabel2.setText("Venta y reparaciones de articulos tecnologicos");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel7.setText("NO. Folio");

        jLabel8.setText("ID Proveedor");

        jLabel9.setText("Total bruto:");

        btnBuscarProv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/search_icon_129533.png"))); // NOI18N
        btnBuscarProv.setText("Buscar");
        btnBuscarProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarProvActionPerformed(evt);
            }
        });

        txtFecha.setEditable(false);
        txtFecha.setText("2090-10-23");

        tablaCompra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nro", "N° de folio", "Proveedor", "Total Bruto", "Total Neto"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaCompra);

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/sign-error-icon_34362.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel15.setText("Invertido:");

        txtInversion.setEditable(false);

        btnGenerarVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/calculator-icon_34473.png"))); // NOI18N
        btnGenerarVenta.setText("Registrar compra");
        btnGenerarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarVentaActionPerformed(evt);
            }
        });

        txtTotalBruto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTotalBrutoKeyTyped(evt);
            }
        });

        btnCalcularIva.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/calculator-icon_34473.png"))); // NOI18N
        btnCalcularIva.setText("Calcular ");
        btnCalcularIva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularIvaActionPerformed(evt);
            }
        });

        txtTotalNeto.setEditable(false);

        jLabel11.setText("Total Neto:");

        jLabel12.setText("Proveedor");

        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/sign-add-icon_34367.png"))); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(txtProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(txtnoFolio, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(btnBuscarProv, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(18, 18, 18)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                    .addComponent(txtTotalBruto, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(btnCalcularIva, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(txtProvNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                    .addComponent(txtTotalNeto, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGap(6, 6, 6))
                                        .addComponent(btnAgregar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(177, 177, 177))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnGenerarVenta)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtInversion, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnoFolio, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarProv, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtProvNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTotalBruto, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCalcularIva)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotalNeto, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnGenerarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel15)
                        .addComponent(txtInversion, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/logo_duckpc_96x96.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(173, 173, 173)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(206, 206, 206)
                        .addComponent(jLabel2)))
                .addContainerGap(247, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void btnGenerarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarVentaActionPerformed

        if (!validaBlancos()) {
            registrarCompra();
            JOptionPane.showMessageDialog(null, "Tarea realizada con éxito", "Correcto!", JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Primero debes ingresar una compra", "Oops", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_btnGenerarVentaActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed

    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnBuscarProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProvActionPerformed

        String idProveedor = txtProveedor.getText();

        if (!idProveedor.equals("")) {

            if (existeProveedor(idProveedor)) {

                listarProveedor(idProveedor);

            } else {

                int respuesta = JOptionPane.showConfirmDialog(rootPane, "Desea ver el listado de proveedores?", "ID de proveedor inexistente", JOptionPane.YES_NO_OPTION);

                if (respuesta == YES_OPTION) {

                    registroProveedores rp = new registroProveedores();
                    rp.setVisible(true);

                }
            }

        } else {

            JOptionPane.showMessageDialog(null, "Debe ingresar el codigo del producto", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnBuscarProvActionPerformed

    private void btnCalcularIvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularIvaActionPerformed

        if (validaBlancos()) {

            JOptionPane.showMessageDialog(null, "Debes ingresar un precio", "Oops", JOptionPane.WARNING_MESSAGE);

        } else {

            calcularIVA(Integer.parseInt(txtTotalBruto.getText().toString().replace(" ", "")));

        }

    }//GEN-LAST:event_btnCalcularIvaActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        if (!validaBlancos()) {
            agregarProducto();
        } else {
            JOptionPane.showMessageDialog(null, "No puede dejar campos en blanco", "Oops!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void txtTotalBrutoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalBrutoKeyTyped

        char miChar = evt.getKeyChar();
        if ((miChar < '0' || miChar > '9')) {
            
            evt.consume();
            
        } else if (Character.isLetter(miChar)) {

            getToolkit().beep();

            evt.consume();
        }
    }//GEN-LAST:event_txtTotalBrutoKeyTyped

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
            java.util.logging.Logger.getLogger(Compra.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Compra.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Compra.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Compra.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Compra().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscarProv;
    private javax.swing.JButton btnCalcularIva;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGenerarVenta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtInversion;
    private javax.swing.JTextField txtProvNombre;
    private javax.swing.JTextField txtProveedor;
    private javax.swing.JTextField txtTotalBruto;
    private javax.swing.JTextField txtTotalNeto;
    private javax.swing.JTextField txtnoFolio;
    // End of variables declaration//GEN-END:variables
}
