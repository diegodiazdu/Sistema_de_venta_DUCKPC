package DUCKPC;

import Controlador.*;
import com.placeholder.PlaceHolder;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

public class recupera_contrasena extends javax.swing.JFrame {

    Conexion con = new Conexion();

    Connection cn = null;

    public recupera_contrasena() {
        initComponents();
        PlaceHolder pc = new PlaceHolder(txtCorreoRecuperacion, new Color(189, 189, 189), Color.BLACK,"Example@correo.com", false, "Arial", 16);
        txtVacio.requestFocus();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        txtCorreoRecuperacion = new javax.swing.JTextField();
        btnRecupera = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtVacio = new javax.swing.JTextField();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Recuperanción de contraseña");

        txtCorreoRecuperacion.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        btnRecupera.setText("RECUPERAR CONTRASEÑA");
        btnRecupera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecuperaActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setText("Escribe tu correo para recuperar tu contraseña: ");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/email_send_message_envelopme_electronic_icon-icons.com_59988(1).png"))); // NOI18N

        jLabel4.setBackground(new java.awt.Color(255, 153, 102));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/logo_duckpc_96x96.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnRecupera, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCorreoRecuperacion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 563, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(21, 21, 21)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtVacio, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtCorreoRecuperacion, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnRecupera, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addComponent(txtVacio, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public String getPassword(String correo) {

        String password = "";

        String opSelect = "SELECT USU_PASS FROM USUARIO WHERE USU_CORREO = ?";
        PreparedStatement ps = null;

        try {
            cn = con.conectar();
            ps = cn.prepareStatement(opSelect);
            ps.setString(1, correo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                password = rs.getString("usu_pass");

            }
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Error: getPassword() " + e.getMessage());

        }
        return password;

    }

    public String getUser(String correo) {

        String user = "";

        String opSelect = "SELECT USU_USER FROM USUARIO WHERE USU_CORREO = ?";
        PreparedStatement ps = null;

        try {
            cn = con.conectar();
            ps = cn.prepareStatement(opSelect);
            ps.setString(1, correo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                user = rs.getString("usu_user");

            }
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Error: getUser() " + e.getMessage());

        }
        return user;

    }

    public void enviarCorreo() {
        try {

            try {

                String contrasena = getPassword(txtCorreoRecuperacion.getText());
                String user = getUser(txtCorreoRecuperacion.getText());

                encriptacion.Desencriptar(contrasena);

                Properties props = new Properties();
                //Definiendo las propiedades de mail
                props.setProperty("mail.smtp.host", "smtp.gmail.com");
                props.setProperty("mail.smtp.starttls.enable", "true");
                props.setProperty("mail.smtp.port", "587");
                props.setProperty("mail.smtp.auth", "true");

                Session session = Session.getDefaultInstance(props);

                String remitente = "duckpc.contacto@gmail.com";
                String password = "E6Yyn2ps6WSiH4z";
                String receptor = txtCorreoRecuperacion.getText();
                String asunto = "Recuperación de contraseña";
                String mensaje = "Hola, tu usuario es " + user + " y tu contraseña es: " + encriptacion.Desencriptar(contrasena);

                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(remitente));

                message.addRecipient(Message.RecipientType.TO, new InternetAddress(receptor));
                message.setSubject(asunto);
                message.setText(mensaje);

                Transport t = session.getTransport("smtp");

                t.connect(remitente, password);
                t.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
                t.close();

                JOptionPane.showMessageDialog(null, "Mensaje enviado!\nRevise su bandeja de entrada", "Mensaje", JOptionPane.INFORMATION_MESSAGE);

            } catch (AddressException ex) {

                JOptionPane.showMessageDialog(null, "Error " + ex.getMessage(), "Mensaje", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(recupera_contrasena.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MessagingException ex) {
                JOptionPane.showMessageDialog(null, "Error " + ex.getMessage(), "Mensaje", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(recupera_contrasena.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (Exception ex) {
            Logger.getLogger(recupera_contrasena.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean existeCorreo(String correo) {

        PreparedStatement ps = null;
        ResultSet rs = null;

        String opSelect = "SELECT * FROM USUARIO WHERE usu_correo = ?";

        try {

            cn = con.conectar();
            ps = cn.prepareStatement(opSelect);
            ps.setString(1, correo);
            rs = ps.executeQuery();

            if (rs.next()) {

                return true;
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error en el bloque de validacion de correo " + e.getMessage());

        }

        return false;

    }

    public boolean emailCorrecto(String correo) {
        
        boolean correcto = false;

        // Patrón para validar el email
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        Matcher matcher = pattern.matcher(correo);

        if (matcher.find()) {

            correcto = true;
        }
        
        return correcto;

    }

    private void btnRecuperaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecuperaActionPerformed

        if (txtCorreoRecuperacion.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Debes proporcionar un correo", "Oops!", JOptionPane.WARNING_MESSAGE);

        } else {

            if (emailCorrecto(txtCorreoRecuperacion.getText())) {

                if (existeCorreo(txtCorreoRecuperacion.getText())) {

                    enviarCorreo();

                } else {

                    JOptionPane.showMessageDialog(null, txtCorreoRecuperacion.getText() + " no se encuentra registrado", "Oops!", JOptionPane.WARNING_MESSAGE);

                }

            } else {

                JOptionPane.showMessageDialog(null, txtCorreoRecuperacion.getText() + "no es un correo valido", "Oops!", JOptionPane.WARNING_MESSAGE);

            }
        }

    }//GEN-LAST:event_btnRecuperaActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new recupera_contrasena().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRecupera;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField txtCorreoRecuperacion;
    private javax.swing.JTextField txtVacio;
    // End of variables declaration//GEN-END:variables
}
