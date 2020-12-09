package DUCKPC;


import Controlador.Conexion;
import Controlador.encriptacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Login extends javax.swing.JFrame {
    
    Conexion con = new Conexion();
    Connection cn = con.conectar();

    public Login() {
        initComponents();
        this.setResizable(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField2 = new javax.swing.JTextField();
        txtUsuario = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        btnIngreso = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnRecuperacion = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnRecuperacion1 = new javax.swing.JButton();

        jTextField2.setText("jTextField2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnIngreso.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnIngreso.setText("Ingresar");
        btnIngreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresoActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("Usuario:");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("Contrasena:");

        btnRecuperacion.setBackground(new java.awt.Color(153, 204, 255));
        btnRecuperacion.setText("¿Olvidaste tu contraseña?");
        btnRecuperacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecuperacionActionPerformed(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/loginIcon.png"))); // NOI18N

        btnRecuperacion1.setBackground(new java.awt.Color(153, 204, 255));
        btnRecuperacion1.setText("Cancelar");
        btnRecuperacion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecuperacion1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 132, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(92, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
                                .addComponent(txtUsuario))
                            .addComponent(jLabel2))
                        .addGap(67, 67, 67))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRecuperacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRecuperacion1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(btnRecuperacion, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRecuperacion1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static String usuario = "";
    
    public int getNivel(String nombre) {

        int id = 0;

        String opSelect = "SELECT NIV_ID FROM USUARIO WHERE USU_USER = ?";
        PreparedStatement ps = null;

        try {
            cn = con.conectar();
            ps = cn.prepareStatement(opSelect);
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                id = rs.getInt("niv_id");

            }
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Error: idVendedor() " + e.getMessage());

        }
        return id;
    }
    
    public void login(String user) {

        String pass = new String(txtPassword.getPassword());
        String newPass = encriptacion.Encriptar(pass);

        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String opSelect = ("SELECT * FROM USUARIO WHERE USU_USER = ? AND USU_PASS = ?");

        try {

            ps = cn.prepareStatement(opSelect);

            ps.setString(1, user);
            ps.setString(2, newPass);

            rs = ps.executeQuery();

            if (rs.absolute(1) || rs.absolute(2)) {
                    
                
                if(getNivel(txtUsuario.getText())==1){
                    
                MENU menu = new MENU();
                menu.setVisible(true);
                this.dispose();
                //JOptionPane.showMessageDialog(null, "Bienvenido " + user, "", JOptionPane.INFORMATION_MESSAGE);
                    
                }else if(getNivel(txtUsuario.getText())==2){
                    
                MENU_EMPLEADO menuE = new MENU_EMPLEADO();
                menuE.setVisible(true);
                this.dispose();
                
                //JOptionPane.showMessageDialog(null, "Bienvenido " + user);
                    
                }

            } else {

                JOptionPane.showMessageDialog(null, "Credenciales incorrectas", "Error!", JOptionPane.ERROR_MESSAGE);
                limpiaCampos();
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "error "+e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);

        }
    }
    
    public void limpiaCampos() {

        txtUsuario.setText("");
        txtPassword.setText("");
        txtUsuario.requestFocus();

    }
    
    private void btnIngresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresoActionPerformed
        
        if((txtUsuario.getText().isEmpty() && txtPassword.getPassword().length==0)){
            
            JOptionPane.showMessageDialog(null, "Debes rellenar los campos","Oops!",JOptionPane.WARNING_MESSAGE);
            
        }else{
            
            login(txtUsuario.getText());
            usuario = txtUsuario.getText();
            
        }

    }//GEN-LAST:event_btnIngresoActionPerformed

    private void btnRecuperacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecuperacionActionPerformed
        recupera_contrasena rc = new recupera_contrasena();
        rc.setVisible(true);
    }//GEN-LAST:event_btnRecuperacionActionPerformed

    private void btnRecuperacion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecuperacion1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnRecuperacion1ActionPerformed

    public static void main(String args[]) {
     
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
 
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIngreso;
    private javax.swing.JButton btnRecuperacion;
    private javax.swing.JButton btnRecuperacion1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
