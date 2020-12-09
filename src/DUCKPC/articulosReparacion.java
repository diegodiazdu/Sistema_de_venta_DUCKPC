package DUCKPC;

import Controlador.Conexion;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import static javax.swing.JOptionPane.YES_OPTION;
import javax.swing.table.DefaultTableModel;

public class articulosReparacion extends javax.swing.JFrame {

    Conexion con = new Conexion();
    Connection cn;
    DefaultTableModel model = new DefaultTableModel();

    public articulosReparacion() {
        initComponents();
        setFecha();
        setVendedor();
        tablaDetalle.getTableHeader().enable(false);
        getIdReparacion();
        jsCantidad.setEditor(new JSpinner.DefaultEditor(jsCantidad));
        this.setResizable(false);
    }

    /*########## METODOS ##########*/
    public void setVendedor() {
        Login lg = new Login();
        txtVendedor.setText(lg.usuario);
    }

    public void getIdReparacion(){
        ListaRepa lr = new ListaRepa();
        txtIDReparacion.setText(lr.idReparacion);
        txtIDReparacion.setEditable(false);
    }
    
    public void setFecha() {
        Calendar fecha = Calendar.getInstance();
        Calendar fechaG = new GregorianCalendar();

        int annio = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);

        txtFecha.setText(annio + "-" + (mes + (1)) + "-" + dia);
    }

    public int getVendedor(String nombre) {

        int id = 0;

        String opSelect = "SELECT USU_ID FROM USUARIO WHERE USU_USER = ?";
        PreparedStatement ps = null;

        try {
            cn = con.conectar();
            ps = cn.prepareStatement(opSelect);
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                id = rs.getInt("usu_id");

            }
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Error: idVendedor() " + e.getMessage());

        }
        return id;

    }

    public boolean existeCliente(String cli_rut) {

        PreparedStatement ps = null;
        ResultSet rs = null;

        String opSelect = "SELECT * FROM CLIENTE WHERE cli_rut = ?";

        try {

            cn = con.conectar();
            ps = cn.prepareStatement(opSelect);
            ps.setString(1, cli_rut);
            rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error en el bloque de validacion de cliente " + e.getMessage());

        }
        return true;
    }

    public boolean existeProducto(String pro_codigo) {

        PreparedStatement ps = null;
        ResultSet rs = null;

        String opSelect = "SELECT * FROM PRODUCTO WHERE PRO_CODIGO = ?";

        try {

            cn = con.conectar();
            ps = cn.prepareStatement(opSelect);
            ps.setString(1, pro_codigo);
            rs = ps.executeQuery();

            //Si no encunetra registros return false
            return rs.next();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error en el bloque de validacion de cliente " + e.getMessage());

        }
        return true;
    }

    public void listarProducto(String idProducto) {

        try {

            cn = con.conectar();

            PreparedStatement consulta = cn.prepareStatement("SELECT * FROM PRODUCTO WHERE PRO_CODIGO = ? ");

            consulta.setString(1, idProducto);

            ResultSet rs = consulta.executeQuery();

            if (rs.next()) {

                txtPrecio.setText(rs.getString("pro_precio_venta"));
                txtProducto.setText(rs.getString("pro_descripcion"));
                txtStock.setText(rs.getString("pro_stock"));
                txtPrecio.setEditable(false);
                txtProducto.setEditable(false);
                txtStock.setEditable(false);

            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e.toString());

        }
    }

    public void agregarProducto() {

        model = (DefaultTableModel) tablaDetalle.getModel();

        int item = 0;
        item = item + 1;
        String codigo = txtCodproducto.getText();
        String nombre = txtProducto.getText();
        int precio = Integer.parseInt(txtPrecio.getText());
        int cantidad = Integer.parseInt(jsCantidad.getValue().toString());
        int stock = Integer.parseInt(txtStock.getText());
        int total = cantidad * precio;

        ArrayList lista = new ArrayList();

        if (stock > 0) {

            lista.add(item);
            lista.add(codigo);
            lista.add(nombre);
            lista.add(cantidad);
            lista.add(precio);
            lista.add(total);

            Object[] obj = new Object[6];

            obj[0] = lista.get(0);
            obj[1] = lista.get(1);
            obj[2] = lista.get(2);
            obj[3] = lista.get(3);
            obj[4] = lista.get(4);
            obj[5] = lista.get(5);

            model.addRow(obj);
            tablaDetalle.setModel(model);

            calcularTotal();

        } else {

            JOptionPane.showMessageDialog(null, "No hay stock suficiente del siguiente producto: " + nombre);
        }

    }
    
    public void registrarDetalle() {

        
        for (int i = 0; i < tablaDetalle.getRowCount(); i++) {
            
            int codigoRep = Integer.parseInt(txtIDReparacion.getText());
            int pro_codigo = Integer.parseInt(tablaDetalle.getValueAt(i, 1).toString());
            int cantidad = Integer.parseInt(tablaDetalle.getValueAt(i, 3).toString());
            int precio = Integer.parseInt(tablaDetalle.getValueAt(i, 4).toString());

            String opInsert = ("INSERT INTO DETALLE_REPARACION(detrep_rep_id, detrep_usu_id , detrep_pro_codigo, detrep_cantidad,detrep_total)"
                    + "VALUES(?,?,?,?,?)");

            try {

                cn = con.conectar();
                PreparedStatement ps = cn.prepareStatement(opInsert);

                ps.setInt(1, codigoRep);
                ps.setInt(2, getVendedor(txtVendedor.getText()));
                ps.setInt(3, pro_codigo);
                ps.setInt(4, cantidad);
                ps.setInt(5, precio);

                ps.executeUpdate();

                //JOptionPane.showMessageDialog(null, "Detalle de venta registrado correctamente!", "Correcto", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {

                JOptionPane.showMessageDialog(null, "Error al registrar detalle de venta " + e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    public void calcularTotal() {

        int totalPagar = 0;
        int cantidad;
        int precio;

        for (int i = 0; i < tablaDetalle.getRowCount(); i++) {

            cantidad = Integer.parseInt(tablaDetalle.getValueAt(i, 3).toString());
            precio = Integer.parseInt(tablaDetalle.getValueAt(i, 4).toString());
            totalPagar = totalPagar + (cantidad * precio);
        }

        txtTotal.setText("" + totalPagar);

    }

    public String getIdVenta() {

        String idVenta = "";

        String selectMax = "SELECT MAX(ven_id) FROM VENTA";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            cn = con.conectar();

            ps = cn.prepareStatement(selectMax);

            rs = ps.executeQuery();

            while (rs.next()) {

                idVenta = rs.getString(1);

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al capturar el id de venta", "Ups!", JOptionPane.ERROR_MESSAGE);
        }

        return idVenta;

    }

    public void actualizarStock() {

        String opUpdate = "UPDATE PRODUCTO SET PRO_STOCK = ? WHERE PRO_CODIGO = ?";
        PreparedStatement ps = null;

        for (int i = 0; i < tablaDetalle.getRowCount(); i++) {

            int idProducto = Integer.parseInt(tablaDetalle.getValueAt(i, 1).toString());
            int cantidad = Integer.parseInt(tablaDetalle.getValueAt(i, 3).toString());
            int stockActual = Integer.parseInt(txtStock.getText().toString()) - cantidad;

            try {

                cn = con.conectar();
                ps = cn.prepareStatement(opUpdate);
                ps.setInt(1, stockActual);
                ps.setInt(2, idProducto);
                ps.executeUpdate();

            } catch (Exception e) {

                JOptionPane.showMessageDialog(null, "Error: actualizarStock() " + e.getMessage(), "Ups!", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    public void limpiarTabla() {

        for (int i = 0; i < model.getRowCount(); i++) {

            model.removeRow(i);

            i = i - 1;

        }
    }

    public void limpiarTxt() {
        txtCodproducto.setText("");
        txtPrecio.setText("");
        jsCantidad.setValue(1);
        txtProducto.setText("");
        txtStock.setText("");
        txtCodproducto.requestFocus();
    }


    /*########## FIN METODOS ##########*/
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtCodproducto = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jsCantidad = new javax.swing.JSpinner();
        btnBuscarPro = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        txtFecha = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtVendedor = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaDetalle = new javax.swing.JTable();
        btnCancelar = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        btnGenerarVenta = new javax.swing.JButton();
        txtStock = new javax.swing.JTextField();
        txtProducto = new javax.swing.JTextField();
        txtIDReparacion = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        jLabel5.setText("jLabel5");

        jLabel6.setText("jLabel6");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel1.setText("ARTICULOS PARA REPARACIÓN");

        jLabel2.setText("Venta y reparaciones de articulos tecnologicos");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel8.setText("Cod Producto:");

        jLabel9.setText("Precio:");

        jLabel10.setText("Cantidad:");

        jsCantidad.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        btnBuscarPro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/search_icon_129533.png"))); // NOI18N
        btnBuscarPro.setText("Buscar");
        btnBuscarPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarProActionPerformed(evt);
            }
        });

        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/sign-add-icon_34367.png"))); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        txtFecha.setEditable(false);
        txtFecha.setText("2090-10-23");

        jLabel12.setText("Producto:");

        jLabel13.setText("Stock:");

        jLabel14.setText("Vendedor:");

        txtVendedor.setEditable(false);

        tablaDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nro", "Codigo", "Producto", "Cantidad", "Precio", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaDetalle);

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/sign-error-icon_34362.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel15.setText("Total a pagar:");

        txtTotal.setEditable(false);

        btnGenerarVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/calculator-icon_34473.png"))); // NOI18N
        btnGenerarVenta.setText("Registrar articulo usado");
        btnGenerarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarVentaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jsCantidad, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                                .addComponent(txtPrecio))
                            .addComponent(txtCodproducto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnBuscarPro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAgregar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtFecha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel13)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtVendedor)
                            .addComponent(txtStock)
                            .addComponent(txtProducto)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnGenerarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnBuscarPro, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addComponent(txtVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCodproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jsCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGenerarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4))
        );

        jLabel3.setText("Codigo de reparación:");

        jLabel4.setBackground(new java.awt.Color(255, 153, 102));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/logo_duckpc_96x96.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(195, 195, 195)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtIDReparacion, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(380, 380, 380)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)))
                .addGap(9, 9, 9)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtIDReparacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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

    private void btnBuscarProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProActionPerformed

        String codigo = txtCodproducto.getText();

        if (!codigo.equals("")) {

            if (existeProducto(codigo)) {

                listarProducto(codigo);

            } else {

                int respuesta = JOptionPane.showConfirmDialog(rootPane, "Desea ver el listado de productos?", "Codigo de producto no valido", JOptionPane.YES_NO_OPTION);

                if (respuesta == YES_OPTION) {

                    Stock st = new Stock();
                    st.setVisible(true);

                }
            }

        } else {

            JOptionPane.showMessageDialog(null, "Debe ingresar el codigo del producto", "Error", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_btnBuscarProActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed

        agregarProducto();

    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed


    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGenerarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarVentaActionPerformed

        if (!txtTotal.getText().isEmpty() || txtIDReparacion.getText().isEmpty()) {

            registrarDetalle();
            actualizarStock();
            limpiarTxt();

            JOptionPane.showMessageDialog(null, "Tarea realizada con exito", "Correcto!", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Debes ingresar datos en los formularios de compra", "Ups!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGenerarVentaActionPerformed

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
            java.util.logging.Logger.getLogger(articulosReparacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(articulosReparacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(articulosReparacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(articulosReparacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new articulosReparacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscarPro;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGenerarVenta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jsCantidad;
    private javax.swing.JTable tablaDetalle;
    private javax.swing.JTextField txtCodproducto;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtIDReparacion;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtProducto;
    private javax.swing.JTextField txtStock;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtVendedor;
    // End of variables declaration//GEN-END:variables
}
