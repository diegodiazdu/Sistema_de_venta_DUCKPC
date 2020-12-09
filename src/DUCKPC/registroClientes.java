package DUCKPC;

import Controlador.Conexion;
import Controlador.valida_rut;
import com.placeholder.PlaceHolder;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class registroClientes extends javax.swing.JFrame {

    Conexion con = new Conexion();
    Connection cn = con.conectar();
    Vender vn = new Vender();

    public registroClientes() {
        initComponents();
        listarTabla();
        txtTelefono.setText("+569");
        this.setResizable(false);
        PlaceHolder correo = new PlaceHolder(txtCorreo, new Color(189, 189, 189), Color.BLACK,"Example@correo.com", false, "Arial", 16);
        

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableEmpleados = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        editar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtRut = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        btnRegistrar = new javax.swing.JButton();

        jLabel6.setText("jLabel6");

        jLabel7.setText("jLabel7");

        jLabel9.setText("jLabel9");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel8.setText("Registro de clientes DUCK-PC");

        tableEmpleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Rut", "Nombres ", "Apellidos ", "Telefono", "Correo", "Dirección", "Descripción"
            }
        ));
        tableEmpleados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableEmpleadosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableEmpleados);

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/logo_duckpc_96x96.png"))); // NOI18N

        editar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        editar.setText("Editar");
        editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarActionPerformed(evt);
            }
        });

        btnNuevo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("Rut:");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("Nombre:");

        jLabel11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel11.setText("Apellidos:");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("Correo:");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setText("Telefono:");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setText("Dirección");

        jLabel12.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel12.setText("Descripcion");

        btnRegistrar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/sign-add-icon_34367.png"))); // NOI18N
        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtRut, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(373, 373, 373))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRut, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(editar, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(295, 295, 295)
                                .addComponent(jLabel8)
                                .addGap(0, 335, Short.MAX_VALUE))
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel10))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel8)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public int existeUsuario(String usuario) {

        PreparedStatement ps = null;
        ResultSet rs = null;
        Conexion con = new Conexion();
        Connection cn = con.conectar();

        String opSelect = ("SELECT COUNT(usu_id) FROM usuario WHERE usu_user = ?");

        try {

            ps = cn.prepareStatement(opSelect);
            ps.setString(1, usuario);
            rs = ps.executeQuery();

            if (rs.next()) {

                return rs.getInt(1);

            }

            return 1;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(rootPane, "Error " + e.getMessage());

            return 1;
        }

    }

    public void limpiaCampos() {

        txtRut.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtCorreo.setText("");
        txtTelefono.setText("");
        txtDireccion.setText("");
        txtDescripcion.setText("");
        txtRut.requestFocus();

    }

    public int existeRut(String rut) {

        PreparedStatement ps = null;
        ResultSet rs = null;

        String opSelect = ("SELECT COUNT(cli_rut) FROM CLIENTE WHERE CLI_RUT = ?");

        try {

            ps = cn.prepareStatement(opSelect);
            ps.setString(1, rut);
            rs = ps.executeQuery();

            if (rs.next()) {

                return rs.getInt(1);

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(rootPane, "Error " + e.getMessage());

        }

        return 0;

    }

    public String getNombre(String rut) {

        String nombreApellido = "";

        String opSelect = "SELECT cli_nombre, cli_apellido FROM cliente WHERE cli_rut = ?";
        PreparedStatement ps = null;

        try {
            cn = con.conectar();
            ps = cn.prepareStatement(opSelect);
            ps.setString(1, rut);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                nombreApellido = rs.getString("cli_nombre") + " " + rs.getString("cli_apellido");

            }
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Error: getNombre() " + e.getMessage());

        }
        return nombreApellido;

    }

    public boolean emailCorrecto(String correo) {

        // Patrón para validar el email
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        Matcher matcher = pattern.matcher(correo);

        if (matcher.find()) {
            return true;
        } else {
            return false;
        }

    }

    public void listarTabla() {

        String[] titulos = {"Rut", "Nombre", "Apellido", "Telefono", "Correo", "Dirección", "Descripción"};
        String[] registros = new String[7];

        DefaultTableModel modelo = new DefaultTableModel(null, titulos);

        String opSelect = ("SELECT * FROM CLIENTE WHERE CLI_RUT != '99.999.999-9'");

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(opSelect);

            while (rs.next()) {

                registros[0] = rs.getString("cli_rut");
                registros[1] = rs.getString("cli_nombre");
                registros[2] = rs.getString("cli_apellido");
                registros[3] = rs.getString("cli_telefono");
                registros[4] = rs.getString("cli_correo");
                registros[5] = rs.getString("cli_direccion");
                registros[6] = rs.getString("cli_descripcion");

                modelo.addRow(registros);

            }

            tableEmpleados.setModel(modelo);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al listar los datos " + e);

        }

    }

    public boolean isVacios() {

        boolean vacios = false;

        if (txtRut.getText().isEmpty()
                || txtNombre.getText().isEmpty()
                || txtApellido.getText().isEmpty()
                || txtCorreo.getText().isEmpty()
                || txtTelefono.getText().isEmpty()
                || txtDireccion.getText().isEmpty()) {

            vacios = true;

        }

        return vacios;
    }

    public void Insertar() {

        PreparedStatement ps = null;

        String opInsert = ("INSERT INTO CLIENTE(cli_rut, cli_nombre, cli_apellido, cli_telefono, cli_correo, cli_direccion, cli_descripcion)"
                + "VALUES(?,?,?,?,?,?,?)");

        try {

            ps = cn.prepareStatement(opInsert);

            ps.setString(1, txtRut.getText());
            ps.setString(2, txtNombre.getText());
            ps.setString(3, txtApellido.getText());
            ps.setString(4, txtTelefono.getText());
            ps.setString(5, txtCorreo.getText());
            ps.setString(6, txtDireccion.getText());
            ps.setString(7, txtDescripcion.getText());

            ps.execute();

            JOptionPane.showMessageDialog(null, "Cliente regisrado Satisfactoriamente", "Correcto!", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al registrar al cliente " + e);

        }

    }

    public void Actualizar() {

        String opInsert = ("UPDATE cliente SET cli_nombre=? , cli_apellido=? , cli_correo=? , cli_telefono=? , cli_direccion=? , cli_descripcion=? WHERE cli_rut = ?");

        try {

            int filaSel = tableEmpleados.getSelectedRow();

            String nombre = txtNombre.getText().toString();
            String apellido = txtApellido.getText().toString();
            String correo = txtCorreo.getText().toString();
            String telefono = txtTelefono.getText().toString();
            String direccion = txtDireccion.getText().toString();
            String descripcion = txtDescripcion.getText().toString();

            String id = (String) tableEmpleados.getValueAt(filaSel, 0);

            PreparedStatement ps = cn.prepareStatement(opInsert);

            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setString(3, correo);
            ps.setString(4, telefono);
            ps.setString(5, direccion);
            ps.setString(6, descripcion);
            ps.setString(7, id);

            ps.execute();
            JOptionPane.showMessageDialog(null, "registros editados correctamente!", "Correcto", JOptionPane.INFORMATION_MESSAGE);
            listarTabla();
            limpiaCampos();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al editar registro " + e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);

        }

    }

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed

        valida_rut val = new valida_rut();

        if (!isVacios()) {

            if (val.validarRut(txtRut.getText())) {

                if (existeRut(txtRut.getText()) == 0) {

                    Insertar();

                } else {
                    JOptionPane.showMessageDialog(null, "El rut ingresado ya se encuentra registrado por " + getNombre(txtRut.getText()), "Oops!", JOptionPane.WARNING_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(null, "El rut ingresado no es valido\nDebe poseer la estructura 11.111.111-1", "Oops!", JOptionPane.WARNING_MESSAGE);
            }

        } else {

            JOptionPane.showMessageDialog(null, "No puedes dejar campos en blanco", "Oops!", JOptionPane.WARNING_MESSAGE);
        }

        listarTabla();
        limpiaCampos();
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarActionPerformed
        if (!isVacios()) {
            Actualizar();
        } else {
            JOptionPane.showMessageDialog(null, "No puedes dejar campos en blanco", "Oops!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_editarActionPerformed

    private void tableEmpleadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableEmpleadosMouseClicked

        int fila = tableEmpleados.rowAtPoint(evt.getPoint());

        txtRut.setText(tableEmpleados.getValueAt(fila, 0).toString());
        txtNombre.setText(tableEmpleados.getValueAt(fila, 1).toString());
        txtApellido.setText(tableEmpleados.getValueAt(fila, 2).toString());
         txtTelefono.setText(tableEmpleados.getValueAt(fila, 3).toString());
        txtCorreo.setText(tableEmpleados.getValueAt(fila, 4).toString());
        txtDireccion.setText(tableEmpleados.getValueAt(fila, 5).toString());
        txtDescripcion.setText(tableEmpleados.getValueAt(fila, 6).toString());
        btnRegistrar.setEnabled(false);
        txtRut.setEditable(false);

    }//GEN-LAST:event_tableEmpleadosMouseClicked

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        btnRegistrar.setEnabled(true);
        limpiaCampos();
        txtRut.requestFocus();
    }//GEN-LAST:event_btnNuevoActionPerformed

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(registroClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(registroClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(registroClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(registroClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new registroClientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton editar;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableEmpleados;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtRut;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
