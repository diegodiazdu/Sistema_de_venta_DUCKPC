
package DUCKPC;

import Controlador.Conexion;
import Controlador.encriptacion;
import com.placeholder.PlaceHolder;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class registroEmpleados extends javax.swing.JFrame {
    
    Conexion con = new Conexion();
    Connection cn = con.conectar();

    public registroEmpleados() {
        initComponents();
        listarTabla();
        PlaceHolder pc = new PlaceHolder(txtCorreo, new Color(189, 189, 189), Color.BLACK,"Example@correo.com", false, "Arial", 16);
        this.setResizable(false);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableEmpleados = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        txtConfirmaPassword = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        btnRegistrar = new javax.swing.JButton();
        btnAtras = new javax.swing.JButton();

        jLabel6.setText("jLabel6");

        jLabel7.setText("jLabel7");

        jLabel9.setText("jLabel9");

        jRadioButton1.setText("jRadioButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel8.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel8.setText("Registro de empleados DUCK-PC");

        tableEmpleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Usuario", "Nombre Completo", "Correo "
            }
        ));
        jScrollPane1.setViewportView(tableEmpleados);

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/logo_duckpc_96x96.png"))); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("Usuario:");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("Contraseña:");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("Confirmar contraseña:");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setText("Nombre completo:");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setText("Correo electronico:");

        btnRegistrar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/sign-add-icon_34367.png"))); // NOI18N
        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtConfirmaPassword)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtCorreo, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtUsuario, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnRegistrar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(305, 305, 305))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtConfirmaPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnAtras.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnAtras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/sign-error-icon_34362.png"))); // NOI18N
        btnAtras.setText("Cancelar");
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(216, 216, 216)
                                .addComponent(jLabel8))
                            .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 248, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel8)))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

     public void errorPassword() {

        txtPassword.setText("");
        txtConfirmaPassword.setText("");
        txtPassword.requestFocus();

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
        txtPassword.setText("");
        txtConfirmaPassword.setText("");
        txtNombre.setText("");
        txtCorreo.setText("");
        txtUsuario.requestFocus();

    }
    
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

        } catch (Exception e) {

            JOptionPane.showMessageDialog(rootPane, "Error " + e.getMessage());

        }
      
        return 1;
    }
    
    public int existeCorreo(String correo) {

        PreparedStatement ps = null;
        ResultSet rs = null;

        String opSelect = ("SELECT COUNT(usu_id) FROM usuario WHERE usu_correo = ?");

        try {

            ps = cn.prepareStatement(opSelect);
            ps.setString(1, correo);
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

        String[] titulos = {"Usuario", "Nombre Completo", "Correo"};
        String[] registros = new String[3];

        DefaultTableModel modelo = new DefaultTableModel(null, titulos);
           

        String opSelect = ("SELECT * FROM USUARIO");

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(opSelect);

            while (rs.next()) {

                registros[0] = rs.getString("usu_user");
                registros[1] = rs.getString("usu_name");
                registros[2] = rs.getString("usu_correo");
                

                modelo.addRow(registros);

            }

            tableEmpleados.setModel(modelo);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al listar los datos " + e);

        }

    }
    
     public boolean validaBlancos(){
        
        if(txtUsuario.equals("") || txtPassword.equals("") || txtConfirmaPassword.equals("") || txtNombre.equals("") || txtCorreo.equals("")){
            return true;
        }else{
            return false;
        }
    }
     
    public boolean contrasenaSegura() {

        String password = new String(txtPassword.getPassword());

        boolean correcta = false;

        String pattern = "^(?=\\w*\\d)(?=\\w*[A-Z])(?=\\w*[a-z])\\S{8,16}$";

        if (password.matches(pattern)) {

            correcta = true;

        } else {

            correcta = false;
        }

        return correcta;
    }
     
    public void InsertarEmpleado() {

        PreparedStatement ps = null;

        String opInsert = ("INSERT INTO USUARIO(niv_id, usu_user, usu_pass, usu_name, usu_correo)VALUES(?,?,?,?,?)");

        //Transformando campos Password[Arreglo] a String
        String pass = new String(txtPassword.getPassword());
        String confirmaPass = new String(txtConfirmaPassword.getPassword());

        if (validaBlancos()) {
            
            JOptionPane.showMessageDialog(null, "No puede dejar campos en blanco", "Precaución!", JOptionPane.WARNING_MESSAGE);

        } else {
            
            
            if(contrasenaSegura()){
                
                         
            if (pass.equals(confirmaPass)) {

                if (existeUsuario(txtUsuario.getText()) == 0) {
                    
                   if (existeCorreo(txtCorreo.getText()) == 0) {

                    if (emailCorrecto(txtCorreo.getText())) {
                    
                        try {

                            String passCrypted = encriptacion.Encriptar(pass);
                            ps = cn.prepareStatement(opInsert);

                            
                            ps.setInt(1, 2);
                            ps.setString(2, txtUsuario.getText());
                            ps.setString(3, passCrypted);
                            ps.setString(4, txtNombre.getText().toUpperCase());
                            ps.setString(5, txtCorreo.getText());
                            

                            ps.execute();

                            JOptionPane.showMessageDialog(null, "Regisrado Satisfactoriamente", "Correcto!", JOptionPane.INFORMATION_MESSAGE);

                        } catch (Exception e) {

                            JOptionPane.showMessageDialog(null, "Error al registrar al usuario " + e);
                            limpiaCampos();

                        }

                    } else {

                        JOptionPane.showMessageDialog(null, "El email ingresado no es valido, porfavor asegurese de cumplir con el formato example@correo.com", "Error!", JOptionPane.ERROR_MESSAGE);
                        erroCorreo();
                    }

                } else {

                    JOptionPane.showMessageDialog(null, "El usuario ya existe en la base de datos", "Error al registrarse", JOptionPane.ERROR_MESSAGE);
                    errorUsuario();

                }
                   
                }else {

                    JOptionPane.showMessageDialog(null, "El correo ya existe en la base de datos, intente con otro", "Error al registrarse", JOptionPane.ERROR_MESSAGE);
                    erroCorreo();

                }      

            } else {

                JOptionPane.showMessageDialog(null, "Las contraseñas deben coincidir", "Error!", JOptionPane.ERROR_MESSAGE);
                errorPassword();

            }
                
            }else{
                
                 JOptionPane.showMessageDialog(null, "La contraseña debe tener entre 8 y 16 caracteres, minisculas, mayusculas y numeros \n sin caracteres especiales", "Oops!", JOptionPane.WARNING_MESSAGE);
                
            }
   

        }

    }
   
    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        InsertarEmpleado();
        listarTabla();
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAtrasActionPerformed

    public static void main(String args[]) {
       
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(registroEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(registroEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(registroEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(registroEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new registroEmpleados().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCancelar1;
    private javax.swing.JButton btnCancelar2;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableEmpleados;
    private javax.swing.JPasswordField txtConfirmaPassword;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
