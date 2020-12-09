package DUCKPC;

import Controlador.Conexion;
import static DUCKPC.ListaRepa.idReparacion;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class ListaRepa extends javax.swing.JFrame {

    private TableRowSorter trsfiltro;
    Conexion con = new Conexion();
    Connection cn = con.conectar();

    public ListaRepa() {
        initComponents();
        listarTabla();
        setFecha();
        this.setResizable(false);
        /*##### LLenar combobox #####*/
        ArrayList<String> lista = new ArrayList<String>();
        lista = llenarComboInicio();
        for (int x = 0; x < lista.size(); x++) {
            jbFecha.addItem(lista.get(x));

        }
        /*##### Fin llenar combobox #####*/
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtFechaIngreso = new javax.swing.JTextField();
        txtId = new javax.swing.JTextField();
        txtDuegno = new javax.swing.JTextField();
        txtArtefacto = new javax.swing.JTextField();
        txtMonto = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableReparaciones = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        btnEntregar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btnPendientes = new javax.swing.JButton();
        btnTodo = new javax.swing.JButton();
        btnEntregados = new javax.swing.JButton();
        txtEstado = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtProblema = new javax.swing.JTextPane();
        txtBuscar = new javax.swing.JTextField();
        jbFecha = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        txtFechaSalida = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/logo_duckpc_96x96.png"))); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("Dueño:");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setText("Estado:");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setText("Fecha de Ingreso:");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setText("Problema:");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setText("Artefacto:");

        txtFechaIngreso.setEditable(false);

        txtId.setEditable(false);

        txtDuegno.setEditable(false);

        txtArtefacto.setEditable(false);

        tableReparaciones.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        tableReparaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nro", "Dueño", "Fecha Ingreso", "Fecha Salida", "Monto a pagar", "Estado", "Artefacto", "Problema"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableReparaciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableReparacionesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableReparaciones);
        if (tableReparaciones.getColumnModel().getColumnCount() > 0) {
            tableReparaciones.getColumnModel().getColumn(0).setPreferredWidth(100);
        }

        jButton1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton1.setText("Volver");

        btnEntregar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnEntregar.setText("Entregar Artefacto");
        btnEntregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntregarActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setText("Nro:");

        btnPendientes.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnPendientes.setText("Mostrar pendientes");
        btnPendientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPendientesActionPerformed(evt);
            }
        });

        btnTodo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnTodo.setText("Mostrar todo");
        btnTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTodoActionPerformed(evt);
            }
        });

        btnEntregados.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnEntregados.setText("Mostrar entregados");
        btnEntregados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntregadosActionPerformed(evt);
            }
        });

        txtEstado.setEditable(false);

        jLabel11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel11.setText("Monto a pagar:");

        jScrollPane3.setViewportView(txtProblema);

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });

        jbFecha.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "..." }));

        jButton3.setText("Filtrar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel12.setText("Desde:");

        jLabel13.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel13.setText("Buscar:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(406, 406, 406)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(406, 406, 406)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtDuegno, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(406, 406, 406)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(txtFechaIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(406, 406, 406)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(406, 406, 406)
                .addComponent(jLabel11)
                .addGap(40, 40, 40)
                .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(406, 406, 406)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(txtArtefacto, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(406, 406, 406)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(486, 486, 486)
                .addComponent(btnEntregar, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(693, 693, 693)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1353, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(763, 763, 763)
                .addComponent(btnTodo)
                .addGap(12, 12, 12)
                .addComponent(btnEntregados)
                .addGap(12, 12, 12)
                .addComponent(btnPendientes))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtDuegno, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtFechaIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtArtefacto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnEntregar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEntregados, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPendientes, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jLabel9.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel9.setText("SALIDA DE REPARACIÓN");

        jLabel10.setText("Venta y reparaciones de articulos tecnologicos");

        jButton2.setText("Productos usados en reparación");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        txtFechaSalida.setEditable(false);
        txtFechaSalida.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtFechaSalida.setForeground(java.awt.Color.blue);

        jLabel14.setText("Fecha:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jLabel1)
                        .addGap(400, 400, 400)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(16, 16, 16))
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtFechaSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(txtFechaSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static String idReparacion = "";

    public void setFecha() {
        Calendar fecha = Calendar.getInstance();
        Calendar fechaG = new GregorianCalendar();

        int annio = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);

        txtFechaSalida.setText(annio + "-" + (mes + (1)) + "-" + dia);
    }

    public ArrayList<String> llenarComboInicio() {

        ArrayList<String> lista = new ArrayList<String>();

        String opSelect = "SELECT DISTINCT rep_fechaIngreso FROM REPARACION";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            ps = cn.prepareStatement(opSelect);
            rs = ps.executeQuery();

            while (rs.next()) {

                lista.add(rs.getString(1));

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al listar combobox " + e.getMessage(), "oops!", JOptionPane.ERROR_MESSAGE);

        }

        return lista;

    }

    public void Actualizar() {

        String opUpdate = ("UPDATE REPARACION SET rep_fechaSalida=?, rep_monto=?, rep_estado=? WHERE rep_id = ?");

        try {

            int filaSel = tableReparaciones.getSelectedRow();

            String getID = (String) tableReparaciones.getValueAt(filaSel, 0);

            String fechaSalida = txtFechaSalida.getText();
            int monto = Integer.parseInt(txtMonto.getText());

            PreparedStatement ps = cn.prepareStatement(opUpdate);

            ps.setString(1, fechaSalida);
            ps.setInt(2, monto);
            ps.setString(3, "Entregado");
            ps.setString(4, getID);

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "registros editados correctamente!", "Correcto", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al editar registro " + e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);

        }

    }

    public void listarTabla() {

        String[] titulos = {"Nro", "Dueño", "Fecha Ingreso", "Fecha Salida", "Monto a pagar", "Estado", "Artefacto", "Problema"};
        String[] registros = new String[8];

        DefaultTableModel modelo = new DefaultTableModel(null, titulos);

        String opSelect = ("SELECT rep_id, CONCAT( cliente.cli_nombre, ' ', cliente.cli_apellido ) AS cliente, rep_fechaIngreso, rep_fechaSalida, rep_monto, rep_estado, rep_artefacto, rep_problema "
                + "FROM reparacion, cliente WHERE cliente.cli_rut = reparacion.rep_cli_rut");

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(opSelect);

            while (rs.next()) {

                registros[0] = rs.getString("rep_id");
                registros[1] = rs.getString("cliente").toUpperCase();
                registros[2] = rs.getString("rep_fechaIngreso");
                registros[3] = rs.getString("rep_fechaSalida");
                registros[4] = rs.getString("rep_monto");
                registros[5] = rs.getString("rep_estado");
                registros[6] = rs.getString("rep_artefacto");
                registros[7] = rs.getString("rep_problema");

                modelo.addRow(registros);

            }

            tableReparaciones.setModel(modelo);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al listar los datos " + e.getMessage());

        }

    }

    public void listarPorFecha() {

        String fechaIngreso = jbFecha.getSelectedItem().toString();

        String[] titulos = {"Nro", "Dueño", "Fecha Ingreso", "Fecha Salida", "Monto a pagar", "Estado", "Artefacto", "Problema"};
        String[] registros = new String[8];

        DefaultTableModel modelo = new DefaultTableModel(null, titulos);

        String opSelect = ("SELECT rep_id, CONCAT( cliente.cli_nombre, ' ', cliente.cli_apellido ) AS cliente, rep_fechaIngreso, rep_fechaSalida, rep_monto, rep_estado, rep_artefacto, rep_problema "
                + "FROM reparacion, cliente WHERE cliente.cli_rut = reparacion.rep_cli_rut AND rep_fechaIngreso BETWEEN '" + fechaIngreso + "' AND NOW()");

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(opSelect);

            while (rs.next()) {

                registros[0] = rs.getString("rep_id");
                registros[1] = rs.getString("cliente").toUpperCase();
                registros[2] = rs.getString("rep_fechaIngreso");
                registros[3] = rs.getString("rep_fechaSalida");
                registros[4] = rs.getString("rep_monto");
                registros[5] = rs.getString("rep_estado");
                registros[6] = rs.getString("rep_artefacto");
                registros[7] = rs.getString("rep_problema");

                modelo.addRow(registros);

            }

            tableReparaciones.setModel(modelo);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al listar los datos " + e.getMessage());

        }

    }

    public void listarPendientes() {

        String[] titulos = {"Nro", "Dueño", "Fecha Ingreso", "Fecha Salida", "Monto a pagar", "Estado", "Artefacto", "Problema"};
        String[] registros = new String[8];

        DefaultTableModel modelo = new DefaultTableModel(null, titulos);

        String opSelect = ("SELECT rep_id, CONCAT( cliente.cli_nombre, ' ', cliente.cli_apellido ) AS cliente, rep_fechaIngreso, rep_fechaSalida, rep_monto, rep_estado, rep_artefacto, rep_problema "
                + "FROM reparacion, cliente WHERE cliente.cli_rut = reparacion.rep_cli_rut AND REP_ESTADO LIKE 'pen%'");

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(opSelect);

            while (rs.next()) {

                registros[0] = rs.getString("rep_id");
                registros[1] = rs.getString("cliente").toUpperCase();
                registros[2] = rs.getString("rep_fechaIngreso");
                registros[3] = rs.getString("rep_fechaSalida");
                registros[4] = rs.getString("rep_monto");
                registros[5] = rs.getString("rep_estado");
                registros[6] = rs.getString("rep_artefacto");
                registros[7] = rs.getString("rep_problema");

                modelo.addRow(registros);

            }

            tableReparaciones.setModel(modelo);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al listar los datos " + e.getMessage());

        }

    }

    public void listarEntregadas() {

        String[] titulos = {"Nro", "Dueño", "Fecha Ingreso", "Fecha Salida", "Monto a pagar", "Estado", "Artefacto", "Problema"};
        String[] registros = new String[8];

        DefaultTableModel modelo = new DefaultTableModel(null, titulos);

        String opSelect = ("SELECT rep_id, CONCAT( cliente.cli_nombre, ' ', cliente.cli_apellido ) AS cliente, rep_fechaIngreso, rep_fechaSalida, rep_monto, rep_estado, rep_artefacto, rep_problema "
                + "FROM reparacion, cliente WHERE cliente.cli_rut = reparacion.rep_cli_rut AND REP_ESTADO LIKE 'entr%'");

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(opSelect);

            while (rs.next()) {

                registros[0] = rs.getString("rep_id");
                registros[1] = rs.getString("cliente").toUpperCase();
                registros[2] = rs.getString("rep_fechaIngreso");
                registros[3] = rs.getString("rep_fechaSalida");
                registros[4] = rs.getString("rep_monto");
                registros[5] = rs.getString("rep_estado");
                registros[6] = rs.getString("rep_artefacto");
                registros[7] = rs.getString("rep_problema");

                modelo.addRow(registros);

            }

            tableReparaciones.setModel(modelo);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al listar los datos " + e.getMessage());

        }

    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        String estado = txtEstado.getText().toString();

        if (txtId.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Debes seleccionar una reparación", "Oops!", JOptionPane.WARNING_MESSAGE);

        } else {

            if (estado.equalsIgnoreCase("pendiente")) {

                JOptionPane.showMessageDialog(null, "Antes debes entregar la reparación", "Oops!", JOptionPane.WARNING_MESSAGE);

            } else {

                articulosReparacion ar = new articulosReparacion();
                ar.setVisible(true);

            }

        }


    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnEntregadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntregadosActionPerformed
        listarEntregadas();
    }//GEN-LAST:event_btnEntregadosActionPerformed

    private void btnTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTodoActionPerformed
        listarTabla();
    }//GEN-LAST:event_btnTodoActionPerformed

    private void btnPendientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPendientesActionPerformed
        listarPendientes();
    }//GEN-LAST:event_btnPendientesActionPerformed

    private void btnEntregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntregarActionPerformed

        int monto = Integer.parseInt(txtMonto.getText().toString());

        if (monto <= 0) {

            JOptionPane.showMessageDialog(null, "Debe establecer un monto a pagar", "Oops!", JOptionPane.WARNING_MESSAGE);

        } else {

            Actualizar();
            listarTabla();
        }

    }//GEN-LAST:event_btnEntregarActionPerformed

    private void tableReparacionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableReparacionesMouseClicked

        int fila = tableReparaciones.rowAtPoint(evt.getPoint());

        txtId.setText(tableReparaciones.getValueAt(fila, 0).toString());

        //Para instancia al form articulosReparacion.java
        idReparacion = txtId.getText();

        txtDuegno.setText(tableReparaciones.getValueAt(fila, 1).toString());
        txtFechaIngreso.setText(tableReparaciones.getValueAt(fila, 2).toString());
        txtMonto.setText(tableReparaciones.getValueAt(fila, 4).toString());
        txtEstado.setText(tableReparaciones.getValueAt(fila, 5).toString());
        txtArtefacto.setText(tableReparaciones.getValueAt(fila, 6).toString());
        txtProblema.setText(tableReparaciones.getValueAt(fila, 7).toString());

        String estado = txtEstado.getText();

        if (estado.equalsIgnoreCase("Entregado")) {

            btnEntregar.setEnabled(false);

        } else if (estado.equalsIgnoreCase("Pendiente")) {

            btnEntregar.setEnabled(true);

        }
    }//GEN-LAST:event_tableReparacionesMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        listarPorFecha();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
        txtBuscar.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                String cadena = (txtBuscar.getText().toUpperCase());
                txtBuscar.setText(cadena);
                repaint();
                filtro();
            }
        });

        trsfiltro = new TableRowSorter(tableReparaciones.getModel());
        tableReparaciones.setRowSorter(trsfiltro);
    }//GEN-LAST:event_txtBuscarKeyTyped

    public void filtro() {

        trsfiltro.setRowFilter(RowFilter.regexFilter(txtBuscar.getText(), 1));
    }

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ListaRepa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListaRepa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListaRepa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListaRepa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListaRepa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEntregados;
    private javax.swing.JButton btnEntregar;
    private javax.swing.JButton btnPendientes;
    private javax.swing.JButton btnTodo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JComboBox<String> jbFecha;
    private javax.swing.JTable tableReparaciones;
    private javax.swing.JTextField txtArtefacto;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtDuegno;
    private javax.swing.JTextField txtEstado;
    private javax.swing.JTextField txtFechaIngreso;
    private javax.swing.JTextField txtFechaSalida;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtMonto;
    private javax.swing.JTextPane txtProblema;
    // End of variables declaration//GEN-END:variables
}
