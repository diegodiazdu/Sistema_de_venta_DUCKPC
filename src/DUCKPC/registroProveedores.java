package DUCKPC;

import Controlador.Conexion;
import Controlador.encriptacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class registroProveedores extends javax.swing.JFrame {

    Conexion con = new Conexion();
    Connection cn = con.conectar();

    public registroProveedores() {
        initComponents();
        listarTabla();
        txtTelefono.setText("+569");
        this.setResizable(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnRegistrar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProveedores = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        txtDescripcion = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        btnUpdate = new javax.swing.JButton();

        jLabel6.setText("jLabel6");

        jLabel7.setText("jLabel7");

        jLabel9.setText("jLabel9");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("Razon social:");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("Direccion:");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("Descripcion (Opcional): ");

        btnRegistrar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel8.setText("Registro de proveedores DUCK-PC");

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/logo_duckpc.png"))); // NOI18N

        tablaProveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Rut", "Nombres ", "Apellidos ", "Telefono", "Correo", "Dirección", "Descripción"
            }
        ));
        tablaProveedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaProveedoresMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaProveedores);

        jLabel11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel11.setText("Telefono:");

        btnUpdate.setText("Actualizar Datos");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnRegistrar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDescripcion, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 861, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8)
                                .addGap(276, 276, 276))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(jLabel11)
                        .addGap(12, 12, 12)
                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addGap(12, 12, 12)
                        .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public int existeUsuario(String nombre) {

        PreparedStatement ps = null;
        ResultSet rs = null;
        Conexion con = new Conexion();
        Connection cn = con.conectar();

        String opSelect = ("SELECT COUNT(prov_nombre) FROM proveedor WHERE prov_nombre = ?");

        try {

            ps = cn.prepareStatement(opSelect);
            ps.setString(1, nombre);
            rs = ps.executeQuery();

            if (rs.next()) {

                return rs.getInt(1);

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(rootPane, "Error " + e.getMessage());

        }

        return 0;
    }

    public void listarTabla() {

        String[] titulos = {"ID", "Razon Social", "Direccion", "Telefono", "Descripción"};
        String[] registros = new String[5];

        DefaultTableModel modelo = new DefaultTableModel(null, titulos);

        String opSelect = ("SELECT * FROM PROVEEDOR");

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(opSelect);

            while (rs.next()) {

                registros[0] = rs.getString("prov_id");
                registros[1] = rs.getString("prov_nombre");
                registros[2] = rs.getString("prov_direccion");
                registros[3] = rs.getString("prov_telefono");
                registros[4] = rs.getString("prov_descripcion");

                modelo.addRow(registros);

            }

            tablaProveedores.setModel(modelo);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al listar los datos " + e);

        }

    }

    public void Insertar() {

        PreparedStatement ps = null;

        String opInsert = ("INSERT INTO PROVEEDOR(PROV_NOMBRE, PROV_DIRECCION,PROV_TELEFONO, PROV_DESCRIPCION)VALUES(?,?,?,?)");

        try {

            ps = cn.prepareStatement(opInsert);

            ps.setString(1, txtNombre.getText());
            ps.setString(2, txtDireccion.getText());
            ps.setString(3, txtTelefono.getText());
            ps.setString(4, txtDescripcion.getText());

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Proveedor regisrado Satisfactoriamente", "Correcto!", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al registrar al cliente " + e.getMessage());

        }

    }

    public void Actualizar() {

        String opUpdate = "UPDATE PROVEEDOR SET PROV_NOMBRE = ?, PROV_DIRECCION = ?, PROV_TELEFONO = ?,PROV_DESCRIPCION = ? "
                + "WHERE PROV_ID = ?";

        try {

            int filaSel = tablaProveedores.getSelectedRow();

            String getID = (String) tablaProveedores.getValueAt(filaSel, 0);

            String nombre = txtNombre.getText();
            String direccion = txtDireccion.getText();
            String telefono = txtTelefono.getText();
            String descripcion = txtDescripcion.getText();

            PreparedStatement ps = cn.prepareStatement(opUpdate);

            ps.setString(1, nombre);
            ps.setString(2, direccion);
            ps.setString(3, telefono);
            ps.setString(4, descripcion);
            ps.setString(5, getID);

            ps.executeUpdate();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Ocurrió un error al actualizar los datos " + e.getMessage(), "Oops!", JOptionPane.ERROR_MESSAGE);

        }
    }

    public boolean validaBlancos() {

        if (txtNombre.getText().isEmpty()
                || txtDireccion.getText().isEmpty()
                || txtTelefono.getText().isEmpty()) {

            return true;

        } else {

            return false;
        }

    }

    public void limpiar() {
        txtNombre.setText("");
        txtTelefono.setText("");
        txtDireccion.setText("");
        txtDescripcion.setText("");
    }

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        if (existeUsuario(txtNombre.getText()) == 1) {

            JOptionPane.showMessageDialog(null, "El proveedor ya se encuentra registrado\nIntente nuevamente", "Oops!", JOptionPane.WARNING_MESSAGE);
        } else {

            if (validaBlancos()) {
                JOptionPane.showMessageDialog(null, "No puedes dejar campos en blanco", "oops!", JOptionPane.WARNING_MESSAGE);
            } else {
                Insertar();
                listarTabla();
                limpiar();
            }

        }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void tablaProveedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProveedoresMouseClicked
        int fila = tablaProveedores.rowAtPoint(evt.getPoint());
        txtNombre.setText(tablaProveedores.getValueAt(fila, 1).toString());
        txtDireccion.setText(tablaProveedores.getValueAt(fila, 2).toString());
        txtTelefono.setText(tablaProveedores.getValueAt(fila, 3).toString());
        txtDescripcion.setText(tablaProveedores.getValueAt(fila, 4).toString());
        btnRegistrar.setEnabled(false);
    }//GEN-LAST:event_tablaProveedoresMouseClicked

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed

        if (validaBlancos()) {
             JOptionPane.showMessageDialog(null, "No puedes dejar campos en blanco", "oops!", JOptionPane.WARNING_MESSAGE);
        } else {
            Actualizar();
            listarTabla();
            limpiar();
            btnRegistrar.setEnabled(true);

        }


    }//GEN-LAST:event_btnUpdateActionPerformed

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(registroProveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(registroProveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(registroProveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(registroProveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new registroProveedores().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaProveedores;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
