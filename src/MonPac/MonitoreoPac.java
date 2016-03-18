/*
 * Clase principal para ejecutar el monitoreo de PAC
 */
package MonPac;
import Monitoreos.Conexion;
import MonAD.ExecQuery;
import com.toedter.calendar.IDateEditor;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.MenuElement;
import javax.swing.MenuSelectionManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import utiles.Util;
/**
 *
 * @author VS3XXBD
 */
public class MonitoreoPac extends javax.swing.JFrame {

    Util utiles = new Util();
    String lastArchivo = "";                                                    //Almacena la ruta del último archivo seleccionado para que cuando se vaya a seleccionar otro archivo el explorador mantenda la ubicación del anterior
    String cadenaBD;                                                            //Cadena con los siguientes datos: base de datos|usuariolocal|contraseñalocal|mesmonitoreo|archivosnomina|usuariopac|contraseñapac|urlbasepac|fechadescarga
    
    /**
     * Creates new form ActiveDirectory
     */
    public MonitoreoPac() {
        initComponents();
        
        jTextField28.setText(obtieneTabla());                                   //Establece en el campo mes monitoreo el mes y año de la fecha de descarga
        
        escuchaMes();
        escuchaAno();
               
    }
    
    private void escuchaMes() {
           jCalendar1.getMonthChooser().addPropertyChangeListener(
                   new java.beans.PropertyChangeListener() {

                       @Override
                       public void propertyChange(java.beans.PropertyChangeEvent evt) {
                           
                               SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                               String fecha = formato.format(jCalendar1.getDate());
                               String[] arreglo = fecha.split("-");
                               String mes = utiles.dateMonth(jCalendar1.getDate());
                               String ano = arreglo[0].substring(2);
                               
                               jTextField28.setText(mes + ano);
                                                      
                       }
                   });
       }
    
      private void escuchaAno() {
           jCalendar1.getYearChooser().addPropertyChangeListener(
                   new java.beans.PropertyChangeListener() {

                       @Override
                       public void propertyChange(java.beans.PropertyChangeEvent evt) {
                           
                               SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                               String fecha = formato.format(jCalendar1.getDate());
                               String[] arreglo = fecha.split("-");
                               String mes = utiles.dateMonth(jCalendar1.getDate());
                               String ano = arreglo[0].substring(2);
                               
                               jTextField28.setText(mes + ano);
                                                      
                       }
                   });
       }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jTextField16 = new javax.swing.JTextField();
        jTextField17 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jTextField18 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jCalendar1 = new com.toedter.calendar.JCalendar();
        jLabel6 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel43 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jTextField28 = new javax.swing.JTextField();
        jTextField29 = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jTextField30 = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jTextField31 = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jTextField38 = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        jPasswordField2 = new javax.swing.JPasswordField();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jCheckUsrInt = new javax.swing.JCheckBox();
        jCheckUsrExt = new javax.swing.JCheckBox();
        jCheckUsrs = new javax.swing.JCheckBox();
        ExcepDup = new javax.swing.JCheckBox();
        Matriz = new javax.swing.JCheckBox();
        jPanel14 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jTextField32 = new javax.swing.JTextField();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jTextField33 = new javax.swing.JTextField();
        Base2 = new javax.swing.JTextField();
        jTextField39 = new javax.swing.JTextField();
        jButton20 = new javax.swing.JButton();
        jTextField40 = new javax.swing.JTextField();
        jButton21 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextField1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField1.setText("0");
        jTextField1.setToolTipText("Número de usuarios registrados en el sistema.");

        jLabel3.setText("Total de usuarios");

        jLabel4.setText("Usuarios internos.");

        jTextField3.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField3.setText("0");
        jTextField3.setToolTipText("Número de usuarios internos registrados en el sistema.");

        jLabel5.setText("Usuarios externos.");

        jTextField4.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField4.setText("0");
        jTextField4.setToolTipText("Número de usuarios externos registrados en el sistema.");

        jLabel7.setText("Usuarios internos dados de baja.");

        jTextField5.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField5.setText("0");
        jTextField5.setToolTipText("Número de usuarios internos registrados en el sistema que se encuentran dados de baja o que no se encuentran en los archivos de nómina.");

        jLabel8.setText("Usuarios externos dados de baja.");

        jTextField6.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField6.setText("0");
        jTextField6.setToolTipText("Número de usuarios internos registrados en el sistema que se encuentran dados de baja o que no se encuentran en los archivos de nómina.");

        jLabel9.setText("Usuarios internos con inactividad.");

        jTextField7.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField7.setText("0");
        jTextField7.setToolTipText("Número de usuarios internos que no han ingresado al sistema en un periodo determinado.");

        jLabel10.setText("Usuarios externos con inactividad.");

        jTextField8.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField8.setText("0");
        jTextField8.setToolTipText("Número de usuarios externos que no han ingresado al sistema en un periodo determinado.");

        jLabel13.setText("Usuarios internos con USERID incorrecto.");

        jTextField11.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField11.setText("0");
        jTextField11.setToolTipText("Número de usuarios internos donde el UserID del sistema no corresponde al registrado en nómina. Aquí pueden aparecer usuarios homónimos que no están registrados en nómina.");

        jLabel14.setText("Usuarios externos con USERID incorrecto.");

        jTextField12.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField12.setText("0");
        jTextField12.setToolTipText("Número de usuarios externos donde el UserID del sistema no corresponde al registrado en nómina. Aquí pueden aparecer usuarios homónimos que no están registrados en nómina.");

        jLabel15.setText("Usuarios internos duplicados por nombre.");

        jTextField13.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField13.setText("0");

        jLabel16.setText("Usuarios internos con perfil incorrecto\n");

        jTextField14.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField14.setText("0");

        jLabel17.setText("Usuarios internos perfil no autorizado");

        jTextField15.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField15.setText("0");

        jTextField16.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField16.setText("0");

        jTextField17.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField17.setText("0");

        jLabel19.setText("Usuarios externos duplicados por nombre.");

        jTextField18.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField18.setText("0");

        jLabel20.setText("Usuarios externos con perfil incorrecto");

        jLabel21.setText("Usuarios externos perfil no autorizado");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14)
                            .addComponent(jLabel19)
                            .addComponent(jLabel16)
                            .addComponent(jLabel20)))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                        .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(22, 22, 22)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jTextField18, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                    .addComponent(jTextField17, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField4)
                    .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField5, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField6, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField7, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField8, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField11, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField12, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField13, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField14, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField15, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField16, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(34, 34, 34)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jCalendar1.setToolTipText("Seleccione aquí la fecha en que se descargó el registro de usuarios.\n");

        jLabel6.setText("Fecha de descarga.");

        jButton1.setText("Ejecutar monitoreo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Perfiles Semestrales");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                .addGap(39, 39, 39)
                .addComponent(jButton1)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jCalendar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 20, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCalendar1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/telcel.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel43))
                .addGap(0, 300, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(149, 149, 149))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Monitoreo", jPanel2);

        jLabel22.setText("Nombre de la base de datos:");

        jLabel23.setText("Usuario:");

        jLabel24.setText("Contraseña");

        jTextField28.setEditable(false);
        jTextField28.setToolTipText("Este campo indica el més del monitoreo, el dato se obtiene de la fecha de descarga registrada en el calendario.");
        jTextField28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField28ActionPerformed(evt);
            }
        });

        jTextField29.setText("root");
        jTextField29.setToolTipText("Ingrese aquí el usuario de la base de datos local");

        jPasswordField1.setText("almapodrida");
        jPasswordField1.setToolTipText("Ingrese aquí la contraseña de la base de datos local");

        jTextField30.setText("pacaplication");
        jTextField30.setToolTipText("Nombre de la base de datos operativa.");
        jTextField30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField30ActionPerformed(evt);
            }
        });

        jLabel34.setText("Mes del monitoreo");

        jLabel35.setText("Archivos identidad");

        jTextField31.setToolTipText("Ingrese aquí la fecha del monitoreo con formato ddmmaaaa. Una vez cargados los archivos con la fecha quedarán almacenados de manera definitiva en el sistema.");
        jTextField31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField31ActionPerformed(evt);
            }
        });

        jLabel44.setText("Usuario obtención de datos:");

        jTextField38.setText("svaudi01");
        jTextField38.setToolTipText("Ingrese aquí el usuario de la base de datos del aplicativo para obtener los usuarios en el sistema.");

        jLabel45.setText("Contraseña");

        jPasswordField2.setText("aud#seg1");
        jPasswordField2.setToolTipText("Ingrese aquí la contraseña de la base del aplicativo");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addComponent(jLabel35)
                            .addComponent(jLabel34)
                            .addComponent(jLabel23)
                            .addComponent(jLabel24))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField31)
                            .addComponent(jTextField30)
                            .addComponent(jTextField28)
                            .addComponent(jTextField29)
                            .addComponent(jPasswordField1, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel45)
                            .addComponent(jLabel44))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField38)
                            .addComponent(jPasswordField2, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jTextField30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(jTextField31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jTextField29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(jTextField38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(jPasswordField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(395, 395, 395))
        );

        jCheckUsrInt.setEnabled(false);

        jCheckUsrExt.setEnabled(false);

        ExcepDup.setEnabled(false);

        Matriz.setEnabled(false);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckUsrExt)
                    .addComponent(jCheckUsrInt)
                    .addComponent(jCheckUsrs)
                    .addComponent(ExcepDup)
                    .addComponent(Matriz))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCheckUsrInt)
                .addGap(21, 21, 21)
                .addComponent(jCheckUsrExt)
                .addGap(189, 189, 189)
                .addComponent(ExcepDup)
                .addGap(18, 18, 18)
                .addComponent(Matriz)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(jCheckUsrs)
                .addContainerGap())
        );

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel36.setText("Nómina internos");

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel37.setText("Nómina externos");

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel25.setText("Usuarios:");

        jLabel46.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel46.setText("Excepciones duplicados");

        jLabel47.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel47.setText("Matriz de perfiles");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(jLabel37)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(2, 2, 2))
                    .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel36)
                .addGap(24, 24, 24)
                .addComponent(jLabel37)
                .addGap(171, 171, 171)
                .addComponent(jLabel46)
                .addGap(24, 24, 24)
                .addComponent(jLabel47)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addComponent(jLabel25)
                .addContainerGap())
        );

        jButton13.setText("Examinar");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setText("Examinar");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jTextField33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField33ActionPerformed(evt);
            }
        });

        Base2.setText("oracle:thin:@uxcirca-scan.telcel.com:1530/PACBD");

        jButton20.setText("Examinar");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jButton21.setText("Examinar");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel15Layout.createSequentialGroup()
                            .addComponent(jTextField32, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton13))
                        .addGroup(jPanel15Layout.createSequentialGroup()
                            .addComponent(jTextField33, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton14))
                        .addComponent(Base2))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jTextField39, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton20))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jTextField40, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton21)))
                .addContainerGap(99, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton14))
                .addGap(147, 147, 147)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton20)
                    .addComponent(jTextField39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton21)
                    .addComponent(jTextField40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 104, Short.MAX_VALUE)
                .addComponent(Base2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jButton12.setText("Importar archivos");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/telcel.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(jLabel42)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(48, Short.MAX_VALUE)
                .addComponent(jLabel42)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Configuración", jPanel3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 743, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    String Corporativo, NomInt, NomExt, BajasInt, BajasExt, BFalta, BFbaja, ExcepDuplicado, MatrizPerfiles ;
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
//        limpiaCampos();
        
        if(jTextField31.getText().equals(""))                                   //VALIDACIÓN DE QUE SE HAYA INGRESADO UNA FECHA EN EL CAMPO DE ARCHIVOS DE IDENTIDAD
        {
            JOptionPane.showMessageDialog(null, "No se ha ingresado el campo Archivos de identidad, Por favor ingrese la fecha de descarga en formato ddmmaaaa", "Error", JOptionPane.WARNING_MESSAGE);
        }
        else
        {
            ArrayList<String> PreparaTablas = new ArrayList();                  //ARRAY LIST PARA ALMACENAR LAS OPERACIONES A BASE DE DATOS Y HACER TODO MEDIANTE UNA SOLA CONEXIÓN
            DateFormat formatofecha = new SimpleDateFormat("yyyy-MM-dd");       //SE OBTIENE LA FECHA EN EL FORMATO ESTABLECIDO
            String fechareporte = formatofecha.format(jCalendar1.getDate());
            System.out.println("Ejecutando monitoreo");
                                                                                //SE CREA LA CADENABD CONCATENANDO LOS DIFERENTES DATOS
            cadenaBD = obtieneBase()+"|"+obtieneUsuario()+"|"+obtieneContrasena()+"|"+obtieneTabla() + "|" + obtieneID() + "|" + obtieneUsuario2() + "|" + obtieneContrasena2() + "|" + obtieneBase2() + "|" + fechareporte;
            String[] parametros = cadenaBD.split("\\|");
            System.out.println("Estableciendo conexión con la BD");
            Conexion ConLocal = new Conexion();
            ConLocal.AbrirLocal(cadenaBD);

            System.out.println("Creando objeto de ejecución");
            ExecQuery EjecutaLo = new ExecQuery();


            PreparaTablas.add(Monitoreos.Querys.BorraInternosTrabajo());            //Esta sección borra todas las tablas temporales que se crearon durante la ejecución anterior del programa
            PreparaTablas.add(Monitoreos.Querys.BorraExternosTrabajo());
            PreparaTablas.add(Monitoreos.Querys.BorraBaajasInt());
            PreparaTablas.add(Monitoreos.Querys.BorraBaajasExt());
            PreparaTablas.add(Monitoreos.Querys.BorraCruceInternosTrabajo(cadenaBD));
            PreparaTablas.add(Monitoreos.Querys.BorraCruceExternosTrabajo(cadenaBD));
            PreparaTablas.add(Monitoreos.Querys.BorraBajasLInt(cadenaBD));
            PreparaTablas.add(Monitoreos.Querys.BorraBajasLExt(cadenaBD));
            PreparaTablas.add(Monitoreos.Querys.BorraDupXNombreInt(cadenaBD));
            PreparaTablas.add(Monitoreos.Querys.BorraDupXNombreExt(cadenaBD));
            PreparaTablas.add(Monitoreos.Querys.BorraInacInt(cadenaBD));
            PreparaTablas.add(Monitoreos.Querys.BorraInacExt(cadenaBD));
            PreparaTablas.add(Monitoreos.Querys.BorraNoNominaInt(cadenaBD));
            PreparaTablas.add(Monitoreos.Querys.BorraNoNominaExt(cadenaBD));
            PreparaTablas.add(Monitoreos.Querys.BorraUsrIncInt(cadenaBD));
            PreparaTablas.add(Monitoreos.Querys.BorraUsrIncExt(cadenaBD));
            PreparaTablas.add(Monitoreos.Querys.BorraPerfilesInt());
            PreparaTablas.add(Monitoreos.Querys.BorraPerfilesExt());
            PreparaTablas.add(Monitoreos.Querys.BorraCoincidenciasInt());
            PreparaTablas.add(Monitoreos.Querys.BorraCoincidenciasExt());
            PreparaTablas.add(Monitoreos.Querys.BorraPerfilesSinUsoInt(cadenaBD));
            PreparaTablas.add(Monitoreos.Querys.BorraPerfilesSinUsoExt(cadenaBD));
            PreparaTablas.add(Monitoreos.Querys.BorraPerfilesSinUso(cadenaBD));
            PreparaTablas.add(Monitoreos.Querys.BorraPerfilNoAutorizadosInt());
            PreparaTablas.add(Monitoreos.Querys.BorraPerfilNoAutorizadosExt());
//
            PreparaTablas.add(Monitoreos.Querys.CreaInternosTrabajo(cadenaBD));     //Crea la tabla de usuarios internos
            PreparaTablas.add(Monitoreos.Querys.CreaExternosTrabajo(cadenaBD));     //Crea la tabla de usuarios externos
            PreparaTablas.add(Monitoreos.Querys.CruceInternos(cadenaBD));           //Realiza el cruce de los archivos de nómina con los usuarios internos
            PreparaTablas.add(Monitoreos.Querys.CruceExternos(cadenaBD));           //Realiza el cruce de los archivos de nómina con los usuarios externos
            PreparaTablas.add(Monitoreos.Querys.NoNominaCreaInt(cadenaBD));         //Crea una tabla con los usuarios internos que no fueron encontrados en nómina
            PreparaTablas.add(Monitoreos.Querys.NoNominaBorraInt(cadenaBD));        //Boora los usuarios internos no encontrados en la nómina del listado de usuarios internos
            PreparaTablas.add(Monitoreos.Querys.NoNominaCreaExt(cadenaBD));         //Crea una tabla con los usuarios externos que no fueron encontrados en nómina
            PreparaTablas.add(Monitoreos.Querys.NoNominaBorraExt(cadenaBD));        //Boora los usuarios externos no encontrados en la nómina del listado de usuarios externos
            PreparaTablas.add(Monitoreos.Querys.CreaBajasInt(cadenaBD));            //Crea una tabla con los usuarios internos que se encuentran dados de baja en la nómina
            PreparaTablas.add(Monitoreos.Querys.BorraBajasInt(cadenaBD));           //Borra los usuarios internos que se encuientran dados de baja en la nómina del listado de usuarios internos
            PreparaTablas.add(Monitoreos.Querys.CreaBajasExt(cadenaBD));            //Crea una tabla con los usuarios externos que se encuentran dados de baja en la nómina
            PreparaTablas.add(Monitoreos.Querys.BorraBajasExt(cadenaBD));           //Borra los usuarios externos que se encuientran dados de baja en la nómina del listado de usuarios externos
            PreparaTablas.add(Monitoreos.Querys.CreaInactividadInt(cadenaBD));      //Crea una tabla con los usuarios internos que no han ingresado a la aplicación en determinado tiempo
            PreparaTablas.add(Monitoreos.Querys.BorraInactividadInt(cadenaBD));     //Borra los usuarios internos que no han ingresado a la aplicación en determinado tiempo de la lista de usuarios internos
            PreparaTablas.add(Monitoreos.Querys.CreaInactividadExt(cadenaBD));      //Crea una tabla con los usuarios externos que no han ingresado a la aplicación en determinado tiempo
            PreparaTablas.add(Monitoreos.Querys.BorraInactividadExt(cadenaBD));     //Borra los usuarios externos que no han ingresado a la aplicación en determinado tiempo de la lista de usuarios externos
            PreparaTablas.add(Monitoreos.Querys.UsrIDIncInt(cadenaBD));             //Crea una tabla con los usuarios internos que tienen un UserID incorrecto
            PreparaTablas.add(Monitoreos.Querys.BorraUsrIDIncInt(cadenaBD));        //Borra los usuaios internos con UserID incorrecto de la tabla de usuarios internos
            PreparaTablas.add(Monitoreos.Querys.UsrIDIncExt(cadenaBD));             //Crea una tabla con los usuarios externos que tienen un UserID incorrecto
            PreparaTablas.add(Monitoreos.Querys.BorraUsrIDIncExt(cadenaBD));        //Borra los usuaios externos con UserID incorrecto de la tabla de usuarios externos
            PreparaTablas.add(Monitoreos.Querys.CreaDuplicadosInt(cadenaBD));       //Crea una tabla con los usuarios internos que tienen duplicidad por nombre en el registro de usuarios del sistema
            PreparaTablas.add(Monitoreos.Querys.CreaDuplicadosExt(cadenaBD));       //Crea una tabla con los usuarios externos que tienen duplicidad por nombre en el registro de usuarios del sistema
            PreparaTablas.add(Monitoreos.Querys.BorraExcepDuplicadosInt(cadenaBD)); //Borra los usuarios internos registrados en las excepciones de la tabla de usuarios duplicados
            PreparaTablas.add(Monitoreos.Querys.BorraExcepDuplicadosExt(cadenaBD)); //Borra los usuarios internos registrados en las excepciones de la tabla de usuarios duplicados
            PreparaTablas.add(Monitoreos.Querys.BorraDuplicadosInt(cadenaBD));      //Borra los usuarios duplicados que no fueron excepciones de la tabla original de usuarios internos
            PreparaTablas.add(Monitoreos.Querys.BorraDuplicadosExt(cadenaBD));      //Borra los usuarios duplicados que no fueron excepciones de la tabla original de usuarios externos
            PreparaTablas.add(Monitoreos.Querys.CreaPerfilesInt(cadenaBD));         //Crea una tabla que cruza los usuarios internos con los perfiles que deben tener según la matriz de perfiles
            PreparaTablas.add(Monitoreos.Querys.CreaPerfilesExt(cadenaBD));         //Crea una tabla que cruza los usuarios externos con los perfiles que deben tener según la matriz de perfiles
            PreparaTablas.add(Monitoreos.Querys.CreaCoincidenciasInt());            //Obtiene los usuarios internos con coincidencias correctas en la matriz de perfiles
            PreparaTablas.add(Monitoreos.Querys.CreaCoincidenciasExt());            //Obtiene los usuarios externos con coincidencias correctas en la matriz de perfiles
            PreparaTablas.add(Monitoreos.Querys.BorraPerfilesCorrectoInt());        //Borra los usuarios internos con perfil correcto de las incidencias
            PreparaTablas.add(Monitoreos.Querys.BorraPerfilesCorrectoExt());        //Borra los usuarios externos con perfil correcto de las incidencias
//
//            PreparaTablas.add(MonPac.Querys.BorraPerfilesCorrectoInt());            //Borra los usuarios internos con perfil correcto de las incidencias
//            PreparaTablas.add(MonPac.Querys.BorraPerfilesCorrectoExt());            //Borra los usuarios externos con perfil correcto de las incidencias
//            PreparaTablas.add(MonPac.Querys.BorraPerfilesCorrectoInt());            //Borra los usuarios internos con perfil correcto de las incidencias
//            PreparaTablas.add(MonPac.Querys.BorraPerfilesCorrectoExt());            //Borra los usuarios externos con perfil correcto de las incidencias

            PreparaTablas.add(Monitoreos.Querys.CreaPerfilesSinUsoInt(cadenaBD));       //Crea una lista de perfiles no usados por los usuarios internos
            PreparaTablas.add(Monitoreos.Querys.CreaPerfilesSinUsoExt(cadenaBD));       //Crea una lista de perfiles no usados por los usuarios externos
            PreparaTablas.add(Monitoreos.Querys.CreaPerfilesSinUso(cadenaBD));          //Crea una lista de la interseccion de perfiles no usados
            PreparaTablas.add(Monitoreos.Querys.BorraPerfilesSinUsoInt());          //
            PreparaTablas.add(Monitoreos.Querys.BorraPerfilesSinUsoExt());
            PreparaTablas.add(Monitoreos.Querys.CreaUsrNoAutorizadosInt());
            PreparaTablas.add(Monitoreos.Querys.CreaUsrNoAutorizadosExt());
            PreparaTablas.add(Monitoreos.Querys.BorraNoAutorizadosInt());
            PreparaTablas.add(Monitoreos.Querys.BorraNoAutorizadosExt());  




            System.out.println("Creando nuevas tablas de trabajo");
            EjecutaLo.Exect(ConLocal.conexion, PreparaTablas);                  //Ejecuta todas las instrucciones almacenadas en el arreglo

            Conteos conteo = new Conteos();

            String total = conteo.conteo(ConLocal.conexion, "USUARIOS" + parametros[4]);//Cuenta el total de usuarios
            jTextField1.setText(total);
            
            String internos = conteo.conteo(ConLocal.conexion, "INTERNOS");     //Cuenta el total de usuarios internos
            jTextField3.setText(internos);
            String externos = conteo.conteo(ConLocal.conexion, "EXTERNOS");     //Cuenta el total de usuarios externos
            jTextField4.setText(externos);
    
    //        
            String bint = conteo.conteo(ConLocal.conexion, "BAAJASINT", "NONOMINAINT");//Cuenta el total de usuarios internos dados de baja y que no están registrados en nómina
            jTextField5.setText(bint);
            String bext = conteo.conteo(ConLocal.conexion, "BAAJASEXT", "NONOMINAEXT");//Cuenta el total de usuarios externos dados de baja y que no están registrados en nómina
            jTextField6.setText(bext);
            
            
            String inint = conteo.conteo(ConLocal.conexion, "INACTIVIDADINT");  //Cuenta el total de usuaios internos reportados con intactividad
            jTextField7.setText(inint);
            String inext = conteo.conteo(ConLocal.conexion, "INACTIVIDADEXT");  //Cuenta el total de usuaios externos reportados con intactividad
            jTextField8.setText(inext);
            
         
            String usrincint = conteo.conteo(ConLocal.conexion, "USRINCINT" );  //Cuenta el total de usuarios internos reportados con UserID incorrecto
            jTextField11.setText(usrincint);
            String usrincext = conteo.conteo(ConLocal.conexion, "USRINCEXT");   //Cuenta el total de usuarios externos reportados con UserID incorrecto
            jTextField12.setText(usrincext);
            
            String dupxnomint = conteo.conteo(ConLocal.conexion, "DUPXNOMBREINT");//Cuenta el total de usuarios internos reportados por duplicidad
            dupxnomint = Integer.toString(Integer.parseInt(dupxnomint)/2);
            jTextField13.setText(dupxnomint);
            String dupxnomext = conteo.conteo(ConLocal.conexion, "DUPXNOMBREEXT");//Cuenta el total de usuarios externos reportados por duplicidad
            dupxnomext = Integer.toString(Integer.parseInt(dupxnomext)/2);
            jTextField14.setText(dupxnomext);
            
            String perfilint = conteo.conteo(ConLocal.conexion, "PERFILESINT"); //Cuenta el total de usuarios internos con perfil incorrecto
            jTextField15.setText(perfilint);
            
            String perfilext = conteo.conteo(ConLocal.conexion, "PERFILESEXT"); //Cuenta el total de usuarios externos con perfil incorrecto
            jTextField16.setText(perfilext);
            
            String noAutoint = conteo.conteo(ConLocal.conexion, "USUARIOSNOAUTORIZADOSINT");//Cuenta el total de usuarios internos con perfil no autorizado
            jTextField17.setText(noAutoint);
            
            String noAutoext = conteo.conteo(ConLocal.conexion, "USUARIOSNOAUTORIZADOSEXT");//Cuenta el total de usuarios externos con perfil no autorizado
            jTextField18.setText(noAutoext);
            
            

            System.out.println(cadenaBD);
    


            ConLocal.Cerrar();                                                  //Cierra a conexión de MySQL

            Resultados PantallaDeResultados = new Resultados(cadenaBD);         //LLama la pantalla de resultados
            PantallaDeResultados.setVisible(true);
//            this.dispose();
            
            JOptionPane.showMessageDialog(null, "La ejecución del monitoreo ha finalizado", "Proceso terminado", JOptionPane.INFORMATION_MESSAGE);
        }
        
        
        
        
    }//GEN-LAST:event_jButton1ActionPerformed


    
    public String obtieneTabla(){                                               //Obtiene mes y año del monitoreo, realizada para almacenar los registros del monitoreo a futuro
    
        String nombreTabla;
        
        DateFormat formatofecha = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = formatofecha.format(jCalendar1.getDate());
        
        String[] arreglo = fecha.split("-");
        String mes = utiles.dateMonth(jCalendar1.getDate());
        String ano = arreglo[0].substring(2);
        
        nombreTabla = mes + ano;
       
        return nombreTabla;
    }
    
    public String obtieneUsuario(){                                             //Obtiene el usuario de la base de datos local
   
        String usuario;
        usuario = jTextField29.getText();
        return usuario;
   
    }
    
    public String obtieneBase(){                                                //Obtiene la base de datos local
   
        String base;
        base = jTextField30.getText();
        return base;
   
    }
    
    public String obtieneBase2(){                                               //Obtiene el nombre de la base de datos de la aplicación
   
        String base;
        base = Base2.getText();
        return base;
   
    }
    
    public String obtieneUsuario2(){                                            //Obtiene el usuario de la base de datos de la aplicación
   
        String base;
        base = jTextField38.getText();
        return base;
   
    }
    
    public String obtieneContrasena(){                                          //Obtiene la contraseña de la base de datos local
    
        String contrasena;
        contrasena = jPasswordField1.getText();
        return contrasena;
    }
    
    public String obtieneContrasena2(){                                         //Obtiene la contraseña de la base de datos de la aplicación
    
        String contrasena;
        contrasena = jPasswordField2.getText();
        return contrasena;
    }
    
    public String obtieneID(){                                                  //Obtiene la fecha de los archivos de identidad ingresada por el usuario
    
        String ID;
        ID = jTextField31.getText();
        return ID;
    }
    
    
                                                                                //Procedimiento para agregar todos los archivos a la base de datos local
    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        String cadenaBD = obtieneBase()+"|"+obtieneUsuario()+"|"+obtieneContrasena()+"|"+obtieneTabla() + "|" + obtieneID() + "|" + obtieneUsuario2() + "|" + obtieneContrasena2() + "|" + obtieneBase2();
        String[] parametros = cadenaBD.split("\\|");
         
        if(jTextField31.getText().equals(""))                                   //Verifica que se haya ingresado el campo de las nóminas de identidad
        {
            JOptionPane.showMessageDialog(null, "No se ha ingresado el campo Archivos de identidad, Por favor ingrese la fecha de descarga en formato ddmmaaaa", "Error", JOptionPane.WARNING_MESSAGE);
        }
        else
        {
            if(jCheckUsrs.isSelected())                                         //Valida que esté seleccionada la casilla de los usuarios de la aplicación
            {
                String Creacion, InsUsuarios, BorraUsuarios;


                Conexion ConUsuarios = new Conexion();                          //Se crea y abre la conexión con el servidor de base de datos de la aplicación
                Conexion ConLocal = new Conexion();                             //Se crea y abre la conexión con el servidor de base de datos local

                ConUsuarios.AbrirUsuarios(cadenaBD);
                ConLocal.AbrirLocal(cadenaBD);

                ExecQuery EjecutaUs = new ExecQuery();
                ExecQuery EjecutaLo = new ExecQuery();

                System.out.println("Importando usuarios de PAC");
                ResultSet usuarios = EjecutaUs.Cons(ConUsuarios.conexion, Monitoreos.Querys.Usuarios());    //Obtiene los usuarios del día de la BD del aplicativo


                System.out.println("Obteniendo query de inserción");
                InsUsuarios = Monitoreos.Querys.CreaInsertUsuarios(cadenaBD, usuarios);     //Crea la instrucción para insertar los usuarios a la BD local

                System.out.println("Obteniendo query para eliminar tabla de usuarios del día");
                BorraUsuarios = Monitoreos.Querys.BorraUsuarios(cadenaBD);          //Instrucción para borrar la tabla de usuarios anterior

                System.out.println("Eliminando tabla previa del día");
                EjecutaLo.Exect(ConLocal.conexion, BorraUsuarios);              //Borra la tala de usuarios anterior
                ConUsuarios.Cerrar();

                System.out.println("Creando tabla de usuarios");
                Creacion = Monitoreos.Querys.CreaUsuarios(cadenaBD);                //Instrucción para crear la tabla de usuarios de la aplicación en la BD local

                EjecutaLo.Exect(ConLocal.conexion, Creacion);                   //Crea tabla para usuarios

                System.out.println("Insertando usuarios");
                EjecutaLo.Exect( ConLocal.conexion, InsUsuarios);               //Inserta los usuarios.
                ConLocal.Cerrar();
            }




            if(jCheckUsrInt.isSelected())                                       //Valida que este habilitada la casilla de nómina internos                                      
            {
                System.out.println("Registrando archivo de usuarios internos de identidad ");
                Monitoreos.Archivos.lecturaUsuariosIdInt(NomInt, cadenaBD);     //Lee el archivo de la nómina de internos e inserta los usuarios en la BD local
            }
            if(jCheckUsrExt.isSelected())                                       //Valida que este habilitada la casilla de nómina externos
            {
                System.out.println("Registrando archivo de usuarios externos de identidad");
                Monitoreos.Archivos.lecturaUsuariosIdExt(NomExt, cadenaBD);     //Lee el archivo de la nómina de externos e inserta los usuarios en la BD local
            }

            if(ExcepDup.isSelected())                                           //Valida que este habilitada la casilla de excepciones
            {
                System.out.println("Registrando omisiones en duplicidad ");
                Conexion ConLocal = new Conexion();
                ConLocal.AbrirLocal(cadenaBD);
                ExecQuery EjecutaLo = new ExecQuery();
                EjecutaLo.Exect(ConLocal.conexion, Monitoreos.Querys.BorraExcepDuplicados(cadenaBD));   //Borra el archivo anterior de excepciones
                EjecutaLo.Exect(ConLocal.conexion, Monitoreos.Querys.CreaExcepDuplicados(cadenaBD));    //Crea la tabla de excepciones
                EjecutaLo.Exect(ConLocal.conexion, Monitoreos.Querys.CreaExcepDuplicados(cadenaBD, ExcepDuplicado));    //Lee el archivo de excepciones y las inserta en la tabla
            }

            if(Matriz.isSelected())                                             //Valida que esté habilitada la casilla de matriz de perfiles de perfiles
            {
                System.out.println("Registrando matriz de perfiles estandarizados");
                Conexion ConLocal = new Conexion();
                ConLocal.AbrirLocal(cadenaBD);
                ExecQuery EjecutaLo = new ExecQuery();
                EjecutaLo.Exect(ConLocal.conexion, Monitoreos.Querys.BorraMatrizPerfiles(cadenaBD));    //Borra la matriz de perfiles anterior
                EjecutaLo.Exect(ConLocal.conexion, Monitoreos.Querys.CreaMatrizPerfiles(cadenaBD));     //Crea la tabla para la matriz de perfiles
                EjecutaLo.Exect(ConLocal.conexion, Monitoreos.Querys.CreaMatrizPerfiles(cadenaBD, MatrizPerfiles)); //Lee el archivo de matriz de perfiles y la inserta en la BD local

            }
            
            JOptionPane.showMessageDialog(null, "El proceso de importación de los archivos ha finalizado", "Proceso terminado", JOptionPane.INFORMATION_MESSAGE);
        }
        
        
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jTextField28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField28ActionPerformed
        
    }//GEN-LAST:event_jTextField28ActionPerformed

    private void jTextField30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField30ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField30ActionPerformed

    private void jTextField31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField31ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField31ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        JFileChooser fileChooser = new JFileChooser(lastArchivo);               //Abre una ventana de exploración con la última ubicacion en que se seleccionó un archivo
        fileChooser.setDialogTitle("Nomina internos");                          //Establece el titulo de la ventana de exploración
        Dimension dim = new Dimension(800, 600);                                //Establece el tamaño de la ventana de exploración
        fileChooser.setPreferredSize(dim);                                      //Establece el tamaño de la ventana de exploración

        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");  //Restringe el tipo de archivos que se muestran en el explirador
        fileChooser.setFileFilter(filter);
        
        int seleccion = fileChooser.showOpenDialog(this);                       
        if(seleccion == JFileChooser.APPROVE_OPTION)                            //Valida si se seleccionó un archivo
        {
            NomInt = fileChooser.getSelectedFile().getAbsolutePath();           //Obtiene la ubicación del archivo seleccionado
            lastArchivo = NomInt;                                               //Lo guarda como última ubicación de selección
            jTextField32.setText(NomInt);                                       //Muestra la ruta en el cuadro de texto asociado
            jCheckUsrInt.setSelected(true);                                     //Selecciona la casilla de verificación
            jCheckUsrInt.setEnabled(true);                                      //Permite al usuario deshabilitarla posteriormente
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        JFileChooser fileChooser = new JFileChooser(lastArchivo);               //Abre una ventana de exploración con la última ubicacion en que se seleccionó un archivo
        fileChooser.setDialogTitle("Nomina externos");                          //Establece el titulo de la ventana de exploración
        Dimension dim = new Dimension(800, 600);                                //Establece el tamaño de la ventana de exploración
        fileChooser.setPreferredSize(dim);                                      //Establece el tamaño de la ventana de exploración
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");  //Restringe el tipo de archivos que se muestran en el explirador
        fileChooser.setFileFilter(filter);
        int seleccion = fileChooser.showOpenDialog(this);
        if(seleccion == JFileChooser.APPROVE_OPTION)                            //Valida si se seleccionó un archivo
        {
            NomExt = fileChooser.getSelectedFile().getAbsolutePath();           //Obtiene la ubicación del archivo seleccionado
            lastArchivo = NomExt;                                               //Lo guarda como última ubicación de selección
            jTextField33.setText(NomExt);                                       //Muestra la ruta en el cuadro de texto asociado
            jCheckUsrExt.setSelected(true);                                     //Selecciona la casilla de verificación                  
            jCheckUsrExt.setEnabled(true);                                      //Permite al usuario deshabilitarla posteriormente
        }
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jTextField33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField33ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField33ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        JFileChooser fileChooser = new JFileChooser(lastArchivo);               //Abre una ventana de exploración con la última ubicacion en que se seleccionó un archivo
        fileChooser.setDialogTitle("Excepciones de usuarios duplicados");       //Establece el titulo de la ventana de exploración
        Dimension dim = new Dimension(800, 600);                                //Establece el tamaño de la ventana de exploración
        fileChooser.setPreferredSize(dim);                                      //Establece el tamaño de la ventana de exploración
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");  //Restringe el tipo de archivos que se muestran en el explirador
        fileChooser.setFileFilter(filter);
        int seleccion = fileChooser.showOpenDialog(this);
        if(seleccion == JFileChooser.APPROVE_OPTION)                            //Valida si se seleccionó un archivo
        {
            ExcepDuplicado = fileChooser.getSelectedFile().getAbsolutePath();   //Obtiene la ubicación del archivo seleccionado
            lastArchivo = ExcepDuplicado;                                       //Lo guarda como última ubicación de selección
            jTextField39.setText(ExcepDuplicado);                               //Muestra la ruta en el cuadro de texto asociado
            ExcepDup.setSelected(true);                                         //Selecciona la casilla de verificación                  
            ExcepDup.setEnabled(true);                                          //Permite al usuario deshabilitarla posteriormente
        }
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        JFileChooser fileChooser = new JFileChooser(lastArchivo);               //Abre una ventana de exploración con la última ubicacion en que se seleccionó un archivo
        fileChooser.setDialogTitle("Matriz de perfiles estandarizados");        //Establece el titulo de la ventana de exploración
        Dimension dim = new Dimension(800, 600);                                //Establece el tamaño de la ventana de exploración
        fileChooser.setPreferredSize(dim);                                      //Establece el tamaño de la ventana de exploración
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");  //Restringe el tipo de archivos que se muestran en el explirador
        fileChooser.setFileFilter(filter);
        int seleccion = fileChooser.showOpenDialog(this);
        if(seleccion == JFileChooser.APPROVE_OPTION)                            //Valida si se seleccionó un archivo    
        {
            MatrizPerfiles = fileChooser.getSelectedFile().getAbsolutePath();   //Obtiene la ubicación del archivo seleccionado
            lastArchivo = MatrizPerfiles;                                       //Lo guarda como última ubicación de selección
            jTextField40.setText(MatrizPerfiles);                               //Muestra la ruta en el cuadro de texto asociado
            Matriz.setSelected(true);                                           //Selecciona la casilla de verificación                  
            Matriz.setEnabled(true);                                            //Permite al usuario deshabilitarla posteriormente
        }
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(MonitoreoPac.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MonitoreoPac.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MonitoreoPac.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MonitoreoPac.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MonitoreoPac().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Base2;
    private javax.swing.JCheckBox ExcepDup;
    private javax.swing.JCheckBox Matriz;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private com.toedter.calendar.JCalendar jCalendar1;
    private javax.swing.JCheckBox jCheckUsrExt;
    private javax.swing.JCheckBox jCheckUsrInt;
    private javax.swing.JCheckBox jCheckUsrs;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField28;
    private javax.swing.JTextField jTextField29;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField30;
    private javax.swing.JTextField jTextField31;
    private javax.swing.JTextField jTextField32;
    private javax.swing.JTextField jTextField33;
    private javax.swing.JTextField jTextField38;
    private javax.swing.JTextField jTextField39;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField40;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    // End of variables declaration//GEN-END:variables
}
