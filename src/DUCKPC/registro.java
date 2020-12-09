package DUCKPC;

import Controlador.Conexion;
import Controlador.Encriptar;
import Controlador.encriptacion;
import java.sql.*;
import java.util.regex.*;
import javax.swing.*;

public class registro extends javax.swing.JFrame {

    Conexion con = new Conexion();
    Connection cn = con.conectar();

    public registro() {
        initComponents();
        this.setResizable(false);
    }

    public void errorPassword() {

        txtContrasena.setText("");
        txtConfirmaContrasena.setText("");
        txtContrasena.requestFocus();

    }

    public void errorUsuario() {
        txtUsuario.setText("");
        txtUsuario.requestFocus();
    }

    public void erroCorreo() {
        txtCorreo.setText("");
        txtCorreo.requestFocus();
    }

    public void limpiaCampos() {

        txtUsuario.setText("");
        txtContrasena.setText("");
        txtConfirmaContrasena.setText("");
        txtNombreCompleto.setText("");
        txtCorreo.setText("");
        txtUsuario.requestFocus();

    }

    public int existeUsuario(String usuario) {

        PreparedStatement ps = null;
        ResultSet rs = null;

        String opSelect = ("SELECT COUNT(usu_id) FROM usuario WHERE usu_user = ?");

        try {

            ps = cn.prepareStatement(opSelect);
            ps.setString(1, usuario);
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(rootPane, "Error " + e.getMessage());
        }

        return 1;
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

    public boolean contrasenaSegura() {

        String password = new String(txtContrasena.getPassword());

        boolean correcta = false;

        String pattern = "^(?=\\w*\\d)(?=\\w*[A-Z])(?=\\w*[a-z])\\S{8,16}$";

        if (password.matches(pattern)) {

            correcta = true;

        } else {

            correcta = false;
        }

        return correcta;
    }

    public boolean isBlancos() {

        boolean blancos = false;

        if (    txtUsuario.getText().isEmpty()
                || txtContrasena.getPassword().length == 0
                || txtConfirmaContrasena.getPassword().length == 0
                || txtNombreCompleto.getText().isEmpty()
                || txtCorreo.getText().isEmpty()) {

            blancos = true;
        }

        return blancos;
    }

    public void Insertar() {

        PreparedStatement ps = null;

        String opInsert = ("INSERT INTO USUARIO(niv_id, usu_user, usu_pass, usu_name, usu_correo)VALUES(?,?,?,?,?)");

        //Transformando campos Password[Arreglo] a String
        String pass = new String(txtContrasena.getPassword());
        String confirmaPass = new String(txtConfirmaContrasena.getPassword());

        if (isBlancos()) {

            JOptionPane.showMessageDialog(null, "No puede dejar campos en blanco", "Precaución!", JOptionPane.WARNING_MESSAGE);

        } else {

            if (contrasenaSegura()) {

                if (pass.equals(confirmaPass)) {

                    if (existeUsuario(txtUsuario.getText()) == 0) {

                        if (emailCorrecto(txtCorreo.getText())) {

                            try {

                                String passCrypted = encriptacion.Encriptar(pass);
                                ps = cn.prepareStatement(opInsert);

                                ps.setInt(1, 1);
                                ps.setString(2, txtUsuario.getText());
                                ps.setString(3, passCrypted);
                                ps.setString(4, txtNombreCompleto.getText().toUpperCase());
                                ps.setString(5, txtCorreo.getText());

                                ps.execute();

                                JOptionPane.showMessageDialog(null, "Regisrado Satisfactoriamente", "Correcto!", JOptionPane.INFORMATION_MESSAGE);

                                MENU menu = new MENU();
                                menu.setVisible(true);
                                this.dispose();
                                JOptionPane.showMessageDialog(null, "Bienvenido al sistema " + txtUsuario.getText(), "", JOptionPane.INFORMATION_MESSAGE);
                                cn.close();

                            } catch (Exception e) {

                                JOptionPane.showMessageDialog(null, "Error al registrar al usuario " + e);
                                limpiaCampos();

                            }

                        } else {

                            JOptionPane.showMessageDialog(null, "El email ingresado no es valido, porfavor asegurese de cumplir con el formato example@correo.com", "Oops!", JOptionPane.WARNING_MESSAGE);
                            erroCorreo();
                        }

                    } else {

                        JOptionPane.showMessageDialog(null, "El usuario ya existe en la base de datos", "Oops!", JOptionPane.WARNING_MESSAGE);
                        errorUsuario();

                    }

                } else {

                    JOptionPane.showMessageDialog(null, "Las contraseñas deben coincidir", "Oops!", JOptionPane.WARNING_MESSAGE);
                    errorPassword();

                }

            } else {

                JOptionPane.showMessageDialog(null, "La contraseña debe tener entre 8 y 16 caracteres, minisculas, mayusculas y numeros \n sin caracteres especiales", "Oops!", JOptionPane.WARNING_MESSAGE);

            }
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNombreCompleto = new javax.swing.JTextField();
        btnregistrar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtConfirmaContrasena = new javax.swing.JPasswordField();
        txtContrasena = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(580, 611));
        setName(""); // NOI18N

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("Usuario:");

        txtUsuario.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("Contraseña:");

        txtCorreo.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("Correo Electronico:");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setText("Nombre Completo:");

        txtNombreCompleto.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        btnregistrar.setText("Registrar");
        btnregistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnregistrarActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setText("Confirmar Contraseña:");

        txtConfirmaContrasena.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        txtContrasena.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/logo_duckpc_96x96.png"))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 153, 255));
        jLabel7.setText("          Crea una nueva cuenta");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(106, 106, 106)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnregistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 568, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2)
                                .addComponent(txtContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6)
                                .addComponent(txtConfirmaContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNombreCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 67, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtConfirmaContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNombreCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnregistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnregistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnregistrarActionPerformed

        Insertar();

    }//GEN-LAST:event_btnregistrarActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new registro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnregistrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPasswordField txtConfirmaContrasena;
    private javax.swing.JPasswordField txtContrasena;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtNombreCompleto;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
