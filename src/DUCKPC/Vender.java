package DUCKPC;

import Controlador.Conexion;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import static javax.swing.JOptionPane.YES_OPTION;
import javax.swing.table.DefaultTableModel;

public class Vender extends javax.swing.JFrame {

    Conexion con = new Conexion();
    Connection cn;
    
    DefaultTableModel model = new DefaultTableModel();
    public static String rut = "";

    public Vender() {
        initComponents();
        tablaDetalle.getTableHeader().enable(false);
        setFecha();
        setVendedor();
        mostrarAlerta();
        jsCantidad.setEditor(new JSpinner.DefaultEditor(jsCantidad));
        this.setResizable(false);
    }

    /*########## METODOS ##########*/
    public void setVendedor() {
        Login lg = new Login();
        txtVendedor.setText(lg.usuario);
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

    public void listarCliente(String cli_rut) {

        try {

            cn = con.conectar();

            PreparedStatement consulta = cn.prepareStatement("SELECT * FROM CLIENTE WHERE CLI_RUT = ? ");

            consulta.setString(1, cli_rut);

            ResultSet resultado = consulta.executeQuery();

            if (resultado.next()) {

                txtCliente.setText(resultado.getString("cli_nombre"));
                txtCliente.setEditable(false);

            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e.toString());

        }
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

    public void registrarVenta() {

        String opInsert = ("INSERT INTO VENTA(VEN_CLI_RUT, VEN_USU_ID, VEN_FECHA, VEN_MONTO)VALUES(?,?,?,?)");

        try {

            cn = con.conectar();
            PreparedStatement ps = cn.prepareStatement(opInsert);

            ps.setString(1, txtClirut.getText());
            ps.setInt(2, getVendedor(txtVendedor.getText()));
            ps.setString(3, txtFecha.getText());
            ps.setInt(4, Integer.parseInt(txtTotal.getText()));

            ps.executeUpdate();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al registrar venta " + e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void registrarDetalle() {

        int idVenta = Integer.parseInt(getIdVenta());

        for (int i = 0; i < tablaDetalle.getRowCount(); i++) {

            int pro_codigo = Integer.parseInt(tablaDetalle.getValueAt(i, 1).toString());
            int cantidad = Integer.parseInt(tablaDetalle.getValueAt(i, 3).toString());
            int precio = Integer.parseInt(tablaDetalle.getValueAt(i, 4).toString());

            String opInsert = ("INSERT INTO DETALLE_VENTA(DET_VEN_ID, DET_PRO_CODIGO, DET_CANTIDAD, DET_TOTAL)VALUES(?,?,?,?)");

            try {

                cn = con.conectar();
                PreparedStatement ps = cn.prepareStatement(opInsert);

                ps.setInt(1, idVenta);
                ps.setInt(2, pro_codigo);
                ps.setInt(3, cantidad);
                ps.setInt(4, precio);

                ps.executeUpdate();

            } catch (Exception e) {

                JOptionPane.showMessageDialog(null, "Error al registrar detalle de venta " + e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
            }
        }

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

    public void limpiarVenta() {

        txtClirut.setText("");
        txtCodproducto.setText("");
        txtPrecio.setText("");
        jsCantidad.setValue(1);
        txtCliente.setText("");
        txtProducto.setText("");
        txtStock.setText("");
        txtClirut.requestFocus();
    }

    public void limpiarAgregado() {

        txtCodproducto.setText("");
        txtPrecio.setText("");
        jsCantidad.setValue(1);
        txtProducto.setText("");
        txtCodproducto.requestFocus();
    }

    public int retornaMenor3() {

        int pro_codigo = 0;

        String opSelect = "SELECT COUNT(pro_codigo) FROM PRODUCTO WHERE PRO_STOCK <= 3";
        PreparedStatement ps = null;

        try {
            cn = con.conectar();
            ps = cn.prepareStatement(opSelect);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                pro_codigo = rs.getInt(1);

            }
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Error: idVendedor() " + e.getMessage());

        }
        return pro_codigo;

    }

    public void mostrarAlerta() {

        if (retornaMenor3() >= 1) {

            JOptionPane.showMessageDialog(null, "Usted tiene " + retornaMenor3() + " productos con stock critico \n \n Contactese con su proveedor lo antes posible", "oops!", JOptionPane.WARNING_MESSAGE);

        }

    }

    public boolean isVacios() {

        boolean vacios = false;

        if (txtClirut.getText().isEmpty()
                || txtCodproducto.getText().isEmpty()
                || txtPrecio.getText().isEmpty()
                || txtCliente.getText().isEmpty()
                || txtProducto.getText().isEmpty()
                || txtStock.getText().isEmpty()
                || txtVendedor.getText().isEmpty()) {
            vacios = true;
        }

        return vacios;
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
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtClirut = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtCodproducto = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jsCantidad = new javax.swing.JSpinner();
        btnBuscarPro = new javax.swing.JButton();
        btnBuscarCli = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaDetalle = new javax.swing.JTable();
        btnCancelar = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        btnGenerarVenta = new javax.swing.JButton();
        txtStock = new javax.swing.JTextField();
        txtProducto = new javax.swing.JTextField();
        txtVendedor = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();

        jLabel5.setText("jLabel5");

        jLabel6.setText("jLabel6");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel1.setText("VENTAS DUCK-PC");

        jLabel2.setText("Venta y reparaciones de articulos tecnologicos");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/logo_duckpc_96x96.png"))); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel7.setText("Rut Cliente:");

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

        btnBuscarCli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/user-id-icon_34334.png"))); // NOI18N
        btnBuscarCli.setText("Buscar");
        btnBuscarCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarCliActionPerformed(evt);
            }
        });

        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/sign-add-icon_34367.png"))); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        jLabel11.setText("Cliente:");

        txtCliente.setEditable(false);

        jLabel12.setText("Producto:");

        jLabel13.setText("Stock:");

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
        btnGenerarVenta.setText("Aceptar");
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
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jsCantidad, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                                .addComponent(txtPrecio))
                            .addComponent(txtCodproducto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtClirut, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnBuscarPro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnBuscarCli, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAgregar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)
                            .addComponent(jLabel11))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtStock)
                            .addComponent(txtProducto)
                            .addComponent(txtCliente)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnGenerarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotal)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtClirut, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarCli, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarPro, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(txtProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13))
                    .addComponent(txtStock))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jsCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGenerarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4))
        );

        txtVendedor.setEditable(false);

        jLabel16.setText("Vendedor:");

        jLabel14.setText("Vendedor:");

        txtFecha.setEditable(false);
        txtFecha.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtFecha.setForeground(java.awt.Color.blue);
        txtFecha.setText("2090-10-23");

        jLabel17.setText("Fecha:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(157, 157, 157)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(jLabel1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtVendedor)
                            .addComponent(txtFecha, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE))
                        .addContainerGap())))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(357, 357, 357)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(368, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2))
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(380, 380, 380)
                    .addComponent(jLabel16)
                    .addContainerGap(219, Short.MAX_VALUE)))
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
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

                } else {
                    txtCodproducto.setText(null);
                }
            }

        } else {

            Stock st = new Stock();
            st.setVisible(true);

        }


    }//GEN-LAST:event_btnBuscarProActionPerformed

    private void btnBuscarCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCliActionPerformed

        String rut = txtClirut.getText();

        if (!rut.equals("")) {

            if (existeCliente(rut)) {

                listarCliente(rut);

            } else {

                int respuesta = JOptionPane.showConfirmDialog(rootPane, "Desea registrar?", "Cliente no registrado", JOptionPane.YES_NO_OPTION);

                if (respuesta == YES_OPTION) {

                    registroClientes rc = new registroClientes();
                    rc.setVisible(rootPaneCheckingEnabled);
                   

                } else {
                    
                    txtClirut.setText(null);
                }
            }

        } else {

            JOptionPane.showMessageDialog(null, "Debe ingresar el rut de un cliente", "Error", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_btnBuscarCliActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed

        if (isVacios()) {

            JOptionPane.showMessageDialog(null, "No has seleccionado nada!", "Oops!", JOptionPane.WARNING_MESSAGE);

        } else {

            int stock = Integer.parseInt(txtStock.getText());
            String producto = txtProducto.getText();
            int cantidad = Integer.parseInt(jsCantidad.getValue().toString());

            if (cantidad > stock) {

                JOptionPane.showMessageDialog(null, "La cantidad agregada es mayor al stock actual del producto", "Oops!", JOptionPane.WARNING_MESSAGE);

            } else {

                if (stock <= 0) {

                    JOptionPane.showMessageDialog(null, "No hay stock suficiente de \n" + producto, "Oops!", JOptionPane.WARNING_MESSAGE);

                } else {
                    agregarProducto();
                    limpiarAgregado();

                }

            }

        }


    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed


    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGenerarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarVentaActionPerformed

        if (!txtTotal.getText().equals("")) {

            registrarVenta();
            registrarDetalle();
            actualizarStock();
            limpiarVenta();
            Stock st = new Stock();
            st.listarTabla();

            JOptionPane.showMessageDialog(null, "Tarea realizada con exito", "Correcto!", JOptionPane.INFORMATION_MESSAGE);
            mostrarAlerta();

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
            java.util.logging.Logger.getLogger(Vender.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Vender.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Vender.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Vender.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Vender().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscarCli;
    private javax.swing.JButton btnBuscarPro;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGenerarVenta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jsCantidad;
    private javax.swing.JTable tablaDetalle;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtClirut;
    private javax.swing.JTextField txtCodproducto;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtProducto;
    private javax.swing.JTextField txtStock;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtVendedor;
    // End of variables declaration//GEN-END:variables
}
