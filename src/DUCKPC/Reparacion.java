package DUCKPC;

import Controlador.Conexion;
import Controlador.encriptacion;
import java.sql.*;
import java.util.Calendar;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.YES_OPTION;

public class Reparacion extends javax.swing.JFrame {

    Conexion con = new Conexion();
    Connection cn = null;

    public Reparacion() {
        initComponents();
        setFecha();
        setVendedor();
        this.setResizable(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField2 = new javax.swing.JTextField();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtRutcliente = new javax.swing.JTextField();
        btnBuscarCliente = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        btnIngreso = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtArtefacto = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtProblema = new javax.swing.JTextPane();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnListadoRep = new javax.swing.JButton();
        txtFecha = new javax.swing.JTextField();
        txtUsuario = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();

        jMenuItem1.setText("jMenuItem1");

        jMenuItem2.setText("jMenuItem2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("Rut Cliente:");

        txtRutcliente.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        btnBuscarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/user-id-icon_34334.png"))); // NOI18N
        btnBuscarCliente.setText("Buscar");
        btnBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarClienteActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setText("Problema:");

        btnIngreso.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnIngreso.setText("Ingresar reparación");
        btnIngreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresoActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setText("Nombre:");

        txtCliente.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setText("Artefacto:");

        jScrollPane1.setViewportView(txtProblema);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtRutcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtArtefacto, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscarCliente)
                .addGap(166, 166, 166))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRutcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(txtArtefacto, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setText("INGRESO A REPARACIÓN");

        jLabel5.setText("Venta y reparaciones de articulos tecnologicos");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/logo_duckpc_96x96.png"))); // NOI18N

        btnListadoRep.setText("Listado de reparaciones");
        btnListadoRep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListadoRepActionPerformed(evt);
            }
        });

        txtFecha.setEditable(false);
        txtFecha.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtFecha.setForeground(java.awt.Color.blue);

        txtUsuario.setEditable(false);
        txtUsuario.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel1.setText("Usuario:");

        jLabel7.setText("Fecha:");
        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(152, 152, 152)
                                        .addComponent(btnListadoRep, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(171, 171, 171)
                                        .addComponent(jLabel3)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtUsuario)
                            .addComponent(txtFecha, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(btnListadoRep))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public void setFecha() {

        Calendar fecha = Calendar.getInstance();

        int annio = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);

        txtFecha.setText(annio + "-" + (mes + (1)) + "-" + dia);

    }

    public void setVendedor() {
        Login lg = new Login();
        txtUsuario.setText(lg.usuario);
    }

    public void enviarCorreo() {

        try {

            try {

                String cliente = getCliente(txtRutcliente.getText());
                String artefacto = txtArtefacto.getText();
                String fecha = txtFecha.getText();
                String correoCliente = getCorreo(txtRutcliente.getText());
                String usuario = getNombreVendedor(txtUsuario.getText());

                Properties props = new Properties();
                //Definiendo las propiedades de mail
                props.setProperty("mail.smtp.host", "smtp.gmail.com");
                props.setProperty("mail.smtp.starttls.enable", "true");
                props.setProperty("mail.smtp.port", "587");
                props.setProperty("mail.smtp.auth", "true");

                Session session = Session.getDefaultInstance(props);

                String remitente = "duckpc.contacto@gmail.com";
                String password = "E6Yyn2ps6WSiH4z";
                String receptor = correoCliente;
                String asunto = "Ingreso a reparacion - DUCKPC";
                String mensaje = "Estimado : " + cliente + "\n"
                        + "Su " + artefacto + " está en proceso de reparación\n"
                        + "Ingresado con la fecha: " + fecha + "\n"
                        + "atendido(a) por: " + usuario + "\n"
                        + "Gracias por confiar en DUCKPC, estaremos en contacto con usted";

                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(remitente));

                message.addRecipient(Message.RecipientType.TO, new InternetAddress(receptor));
                message.setSubject(asunto);
                message.setText(mensaje);

                Transport t = session.getTransport("smtp");

                t.connect(remitente, password);
                t.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
                t.close();

                //JOptionPane.showMessageDialog(null, "Mensaje enviado!\nRevise su bandeja de entrada", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
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

    public String getNombreVendedor(String user) {

        String nombreCompleto = "";

        String opSelect = "SELECT USU_NAME FROM USUARIO WHERE USU_USER = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            cn = con.conectar();
            ps = cn.prepareStatement(opSelect);
            ps.setString(1, user);
            rs = ps.executeQuery();

            if (rs.next()) {

                nombreCompleto = rs.getString("usu_name");

            }
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Error: getNombreVendedor() " + e.getMessage());

        }
        return nombreCompleto;

    }

    public String getCliente(String rut) {

        String nombre = "";

        String opSelect = "SELECT cli_nombre FROM cliente WHERE cli_rut = ?";
        PreparedStatement ps = null;

        try {
            cn = con.conectar();
            ps = cn.prepareStatement(opSelect);
            ps.setString(1, rut);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                nombre = rs.getString("cli_nombre");

            }
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Error: idVendedor() " + e.getMessage());

        }
        return nombre;

    }

    public String getCorreo(String rut) {

        String correo = "";

        String opSelect = "SELECT cli_correo FROM cliente WHERE cli_rut = ?";
        PreparedStatement ps = null;

        try {
            cn = con.conectar();
            ps = cn.prepareStatement(opSelect);
            ps.setString(1, rut);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                correo = rs.getString("cli_correo");

            }
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Error: idVendedor() " + e.getMessage());

        }
        return correo;

    }

    public void ingresaReparacion() {

        String opInsert = "INSERT INTO REPARACION(rep_usu_id, rep_cli_rut,rep_fechaIngreso,rep_monto,rep_estado,rep_artefacto,rep_problema)VALUES(?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        String rut = txtRutcliente.getText();
        String fecha = txtFecha.getText();
        String artefacto = txtArtefacto.getText();
        String problema = txtProblema.getText();

        try {

            cn = con.conectar();
            ps = cn.prepareStatement(opInsert);
            ps.setInt(1, getVendedor(txtUsuario.getText()));
            ps.setString(2, rut);
            ps.setString(3, fecha);
            ps.setInt(4, 0);
            ps.setString(5, "Pendiente");
            ps.setString(6, artefacto);
            ps.setString(7, problema);

            ps.executeUpdate();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error: ingresarReparacion() " + e.getMessage(), "Ups!", JOptionPane.ERROR_MESSAGE);

        }

    }

    public boolean validaBlancos() {

        if (txtCliente.getText().isEmpty()
                || txtFecha.getText().isEmpty()
                || txtProblema.getText().isEmpty()
                || txtRutcliente.getText().isEmpty()
                || txtUsuario.getText().isEmpty()) {

            return true;

        } else {

            return false;
        }

    }

    public void limpia() {

        txtCliente.setText(null);
        txtFecha.setText(null);
        txtArtefacto.setText(null);
        txtProblema.setText(null);
        txtRutcliente.setText(null);
        txtUsuario.setText(null);

    }

    private void btnBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClienteActionPerformed

        String rut = txtRutcliente.getText();

        if (!rut.equals("")) {

            if (existeCliente(rut)) {

                listarCliente(rut);

            } else {

                int respuesta = JOptionPane.showConfirmDialog(rootPane, "Desea registrar?", "Cliente no registrado", JOptionPane.YES_NO_OPTION);

                if (respuesta == YES_OPTION) {

                    registroClientes rc = new registroClientes();
                    rc.setVisible(true);
                }
            }

        } else {

            JOptionPane.showMessageDialog(null, "Debe ingresar el rut de un cliente", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnBuscarClienteActionPerformed

    private void btnIngresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresoActionPerformed

        if (validaBlancos()) {

            JOptionPane.showMessageDialog(null, "Debes rellenar los campos", "ups!", JOptionPane.WARNING_MESSAGE);

        } else {

            ingresaReparacion();
            //enviarCorreo();
            JOptionPane.showMessageDialog(null, "Tarea realizada con exito", "Correcto!", JOptionPane.INFORMATION_MESSAGE);
            limpia();

        }

    }//GEN-LAST:event_btnIngresoActionPerformed

    private void btnListadoRepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListadoRepActionPerformed
        ListaRepa lr = new ListaRepa();
        lr.setVisible(true);
    }//GEN-LAST:event_btnListadoRepActionPerformed

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Reparacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Reparacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Reparacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Reparacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Reparacion().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarCliente;
    private javax.swing.JButton btnIngreso;
    private javax.swing.JButton btnListadoRep;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField txtArtefacto;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextPane txtProblema;
    private javax.swing.JTextField txtRutcliente;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
