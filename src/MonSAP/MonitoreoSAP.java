/*
 * Clase principal para ejecutar el monitoreo de PAC
 */
package MonSAP;
import Monitoreos.Conteos;
import MonPac.*;
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
public class MonitoreoSAP extends javax.swing.JFrame {

    Util utiles = new Util();
    String lastArchivo = "";                                                    //Almacena la ruta del último archivo seleccionado para que cuando se vaya a seleccionar otro archivo el explorador mantenda la ubicación del anterior
    String cadenaBD;                                                            //Cadena con los siguientes datos: base de datos|usuariolocal|contraseñalocal|mesmonitoreo|archivosnomina|usuariopac|contraseñapac|urlbasepac|fechadescarga
    
    /**
     * Creates new form ActiveDirectory
     */
    public MonitoreoSAP() {                                                     //CONSTRUCTOR
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
        jLabel25 = new javax.swing.JLabel();
        jTextField19 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jTextField20 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jCalendar1 = new com.toedter.calendar.JCalendar();
        jLabel6 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
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
        jLabel36 = new javax.swing.JLabel();
        jTextField32 = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jButton12 = new javax.swing.JButton();
        jPanelInternos = new javax.swing.JPanel();
        jLabelInternos = new javax.swing.JLabel();
        jTextFieldInternos = new javax.swing.JTextField();
        jCheckUsrInt = new javax.swing.JCheckBox();
        jButtonInternos = new javax.swing.JButton();
        jPanelInternos8 = new javax.swing.JPanel();
        jLabelInternos8 = new javax.swing.JLabel();
        jTextFieldExternos = new javax.swing.JTextField();
        jCheckUsrExt = new javax.swing.JCheckBox();
        jButtonExternos = new javax.swing.JButton();
        jPanelAdminUsr = new javax.swing.JPanel();
        jLabelAdminUsr = new javax.swing.JLabel();
        jTextFieldUsrAdmin = new javax.swing.JTextField();
        jCheckUsrAdmin = new javax.swing.JCheckBox();
        jButtonUsrAdmin = new javax.swing.JButton();
        jPanelUsuarios = new javax.swing.JPanel();
        jLabelUsuarios = new javax.swing.JLabel();
        jTextFieldUsuarios = new javax.swing.JTextField();
        jCheckUsrs = new javax.swing.JCheckBox();
        jButtonUsuarios = new javax.swing.JButton();
        jPanelDemonsa1 = new javax.swing.JPanel();
        jLabelDemonsa2 = new javax.swing.JLabel();
        jTextFieldDemonsa2 = new javax.swing.JTextField();
        jCheckDemonsa2 = new javax.swing.JCheckBox();
        jButtonDemonsa2 = new javax.swing.JButton();
        jPanelDemonsa2 = new javax.swing.JPanel();
        jLabelFechasAcceso3 = new javax.swing.JLabel();
        jTextFieldFechasAcceso = new javax.swing.JTextField();
        jCheckFechasAcceso = new javax.swing.JCheckBox();
        jButtonFechasAcceso = new javax.swing.JButton();
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

        jLabel25.setText("Usuarios Administradores dados de alta");

        jTextField19.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField19.setText("0");

        jLabel26.setText("Usuarios Administradores dados de Baja");

        jTextField20.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField20.setText("0");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(22, 22, 22))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField20, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
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
                    .addComponent(jTextField19, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE))
                .addGap(23, 23, 23))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26))
                .addContainerGap(60, Short.MAX_VALUE))
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

        jButton3.setText("Administrar Usuarios Administradores");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(300, 300, 300)
                .addComponent(jLabel6)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jCalendar1, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
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
                .addContainerGap(145, Short.MAX_VALUE))
        );

        jLabel43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/telcel.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel43))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 134, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        jTextField30.setText("monitoreos");
        jTextField30.setToolTipText("Nombre de la base de datos operativa.");
        jTextField30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField30ActionPerformed(evt);
            }
        });

        jLabel34.setText("Mes del monitoreo");

        jLabel35.setText("Archivos identidad");

        jTextField31.setText("00000000");
        jTextField31.setToolTipText("Ingrese aquí la fecha del monitoreo con formato ddmmaaaa. Una vez cargados los archivos con la fecha quedarán almacenados de manera definitiva en el sistema.");
        jTextField31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField31ActionPerformed(evt);
            }
        });

        jLabel36.setText("Archivos identidad anterior");

        jTextField32.setText("00000001");
        jTextField32.setToolTipText("Ingrese aquí la fecha del monitoreo con formato ddmmaaaa. Una vez cargados los archivos con la fecha quedarán almacenados de manera definitiva en el sistema.");
        jTextField32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField32ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel35, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel34, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField31)
                    .addComponent(jTextField30)
                    .addComponent(jTextField28)
                    .addComponent(jTextField29)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                    .addComponent(jTextField32))
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
                    .addComponent(jLabel36)
                    .addComponent(jTextField32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addContainerGap(79, Short.MAX_VALUE))
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
                .addContainerGap(80, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(395, 395, 395))
        );

        jButton12.setText("Importar archivos");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jLabelInternos.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelInternos.setText("Nómina internos");

        jCheckUsrInt.setEnabled(false);

        jButtonInternos.setText("Examinar");
        jButtonInternos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInternosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelInternosLayout = new javax.swing.GroupLayout(jPanelInternos);
        jPanelInternos.setLayout(jPanelInternosLayout);
        jPanelInternosLayout.setHorizontalGroup(
            jPanelInternosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInternosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCheckUsrInt)
                .addGap(18, 18, 18)
                .addComponent(jLabelInternos, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                .addGap(28, 28, 28)
                .addComponent(jTextFieldInternos, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonInternos)
                .addContainerGap())
        );
        jPanelInternosLayout.setVerticalGroup(
            jPanelInternosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInternosLayout.createSequentialGroup()
                .addGroup(jPanelInternosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelInternosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelInternos)
                        .addComponent(jTextFieldInternos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonInternos))
                    .addComponent(jCheckUsrInt))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabelInternos8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelInternos8.setText("Nómina externos");

        jCheckUsrExt.setEnabled(false);

        jButtonExternos.setText("Examinar");
        jButtonExternos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExternosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelInternos8Layout = new javax.swing.GroupLayout(jPanelInternos8);
        jPanelInternos8.setLayout(jPanelInternos8Layout);
        jPanelInternos8Layout.setHorizontalGroup(
            jPanelInternos8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInternos8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCheckUsrExt)
                .addGap(18, 18, 18)
                .addComponent(jLabelInternos8, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                .addGap(28, 28, 28)
                .addComponent(jTextFieldExternos, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonExternos)
                .addContainerGap())
        );
        jPanelInternos8Layout.setVerticalGroup(
            jPanelInternos8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInternos8Layout.createSequentialGroup()
                .addGroup(jPanelInternos8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelInternos8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelInternos8)
                        .addComponent(jTextFieldExternos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonExternos))
                    .addComponent(jCheckUsrExt))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabelAdminUsr.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelAdminUsr.setText("Usuarios administradores");

        jCheckUsrAdmin.setEnabled(false);

        jButtonUsrAdmin.setText("Examinar");
        jButtonUsrAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUsrAdminActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelAdminUsrLayout = new javax.swing.GroupLayout(jPanelAdminUsr);
        jPanelAdminUsr.setLayout(jPanelAdminUsrLayout);
        jPanelAdminUsrLayout.setHorizontalGroup(
            jPanelAdminUsrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAdminUsrLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCheckUsrAdmin)
                .addGap(18, 18, 18)
                .addComponent(jLabelAdminUsr, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                .addGap(28, 28, 28)
                .addComponent(jTextFieldUsrAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonUsrAdmin)
                .addContainerGap())
        );
        jPanelAdminUsrLayout.setVerticalGroup(
            jPanelAdminUsrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAdminUsrLayout.createSequentialGroup()
                .addGroup(jPanelAdminUsrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelAdminUsrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelAdminUsr)
                        .addComponent(jTextFieldUsrAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonUsrAdmin))
                    .addComponent(jCheckUsrAdmin))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabelUsuarios.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelUsuarios.setText("Usuarios");

        jTextFieldUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldUsuariosActionPerformed(evt);
            }
        });

        jCheckUsrs.setEnabled(false);

        jButtonUsuarios.setText("Examinar");
        jButtonUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUsuariosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelUsuariosLayout = new javax.swing.GroupLayout(jPanelUsuarios);
        jPanelUsuarios.setLayout(jPanelUsuariosLayout);
        jPanelUsuariosLayout.setHorizontalGroup(
            jPanelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelUsuariosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCheckUsrs)
                .addGap(18, 18, 18)
                .addComponent(jLabelUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                .addGap(28, 28, 28)
                .addComponent(jTextFieldUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonUsuarios)
                .addContainerGap())
        );
        jPanelUsuariosLayout.setVerticalGroup(
            jPanelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelUsuariosLayout.createSequentialGroup()
                .addGroup(jPanelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelUsuarios)
                        .addComponent(jTextFieldUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonUsuarios))
                    .addComponent(jCheckUsrs))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabelDemonsa2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelDemonsa2.setText("Nómina Demonsa 2");

        jCheckDemonsa2.setEnabled(false);

        jButtonDemonsa2.setText("Examinar");
        jButtonDemonsa2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDemonsa2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelDemonsa1Layout = new javax.swing.GroupLayout(jPanelDemonsa1);
        jPanelDemonsa1.setLayout(jPanelDemonsa1Layout);
        jPanelDemonsa1Layout.setHorizontalGroup(
            jPanelDemonsa1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDemonsa1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCheckDemonsa2)
                .addGap(18, 18, 18)
                .addComponent(jLabelDemonsa2, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                .addGap(28, 28, 28)
                .addComponent(jTextFieldDemonsa2, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonDemonsa2)
                .addContainerGap())
        );
        jPanelDemonsa1Layout.setVerticalGroup(
            jPanelDemonsa1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDemonsa1Layout.createSequentialGroup()
                .addGroup(jPanelDemonsa1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDemonsa1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelDemonsa2)
                        .addComponent(jTextFieldDemonsa2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonDemonsa2))
                    .addComponent(jCheckDemonsa2))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabelFechasAcceso3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelFechasAcceso3.setText("Fechas de acceso");

        jCheckFechasAcceso.setEnabled(false);

        jButtonFechasAcceso.setText("Examinar");
        jButtonFechasAcceso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFechasAccesoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelDemonsa2Layout = new javax.swing.GroupLayout(jPanelDemonsa2);
        jPanelDemonsa2.setLayout(jPanelDemonsa2Layout);
        jPanelDemonsa2Layout.setHorizontalGroup(
            jPanelDemonsa2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDemonsa2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCheckFechasAcceso)
                .addGap(18, 18, 18)
                .addComponent(jLabelFechasAcceso3, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                .addGap(28, 28, 28)
                .addComponent(jTextFieldFechasAcceso, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonFechasAcceso)
                .addContainerGap())
        );
        jPanelDemonsa2Layout.setVerticalGroup(
            jPanelDemonsa2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDemonsa2Layout.createSequentialGroup()
                .addGroup(jPanelDemonsa2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDemonsa2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelFechasAcceso3)
                        .addComponent(jTextFieldFechasAcceso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonFechasAcceso))
                    .addComponent(jCheckFechasAcceso))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jPanelInternos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(20, Short.MAX_VALUE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanelInternos8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanelUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanelAdminUsr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanelDemonsa1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanelDemonsa2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelInternos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelInternos8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelAdminUsr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelDemonsa1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelDemonsa2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 162, Short.MAX_VALUE)
                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/telcel.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel42)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel42)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(16, Short.MAX_VALUE))
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 743, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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

    
    String Corporativo, NomInt, NomExt, BajasInt, BajasExt, BFalta, BFbaja, ExcepDuplicado, MatrizPerfiles, UsrAdmin, UsrAdminAgregados, UsrAdminEliminados, Demonsa2, UsuariosSAP, FechasAcceso;
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
            cadenaBD = obtieneBase()+"|"+obtieneUsuario()+"|"+obtieneContrasena()+"|"+obtieneTabla() + "|" + obtieneID() + "|" + obtieneIDAnt() + "|" + fechareporte;
            String[] parametros = cadenaBD.split("\\|");
            System.out.println("Estableciendo conexión con la BD");
            Conexion ConLocal = new Conexion();
            ConLocal.AbrirLocal(cadenaBD);

            System.out.println("Creando objeto de ejecución");
            ExecQuery EjecutaSAP = new ExecQuery();


            
            
            
//            PreparaTablas.add(Monitoreos.Querys.BorraBajasLInt(cadenaBD));
//            PreparaTablas.add(Monitoreos.Querys.BorraBajasLExt(cadenaBD));
//            PreparaTablas.add(Monitoreos.Querys.BorraDupXNombreInt(cadenaBD));
//            PreparaTablas.add(Monitoreos.Querys.BorraDupXNombreExt(cadenaBD));
//            PreparaTablas.add(Monitoreos.Querys.BorraInacInt(cadenaBD));
//            PreparaTablas.add(Monitoreos.Querys.BorraInacExt(cadenaBD));
//            PreparaTablas.add(Monitoreos.Querys.BorraNoNominaInt(cadenaBD));
//            PreparaTablas.add(Monitoreos.Querys.BorraNoNominaExt(cadenaBD));
//            PreparaTablas.add(Monitoreos.Querys.BorraUsrIncInt(cadenaBD));
//            PreparaTablas.add(Monitoreos.Querys.BorraUsrIncExt(cadenaBD));
//            PreparaTablas.add(Monitoreos.Querys.BorraPerfilesInt());
//            PreparaTablas.add(Monitoreos.Querys.BorraPerfilesExt());
//            PreparaTablas.add(Monitoreos.Querys.BorraCoincidenciasInt());
//            PreparaTablas.add(Monitoreos.Querys.BorraCoincidenciasExt());
//            PreparaTablas.add(Monitoreos.Querys.BorraPerfilesSinUsoInt(cadenaBD));
//            PreparaTablas.add(Monitoreos.Querys.BorraPerfilesSinUsoExt(cadenaBD));
//            PreparaTablas.add(Monitoreos.Querys.BorraPerfilesSinUso(cadenaBD));
//            PreparaTablas.add(Monitoreos.Querys.BorraPerfilNoAutorizadosInt());
//            PreparaTablas.add(Monitoreos.Querys.BorraPerfilNoAutorizadosExt());
              
              PreparaTablas.add(Monitoreos.Tablas.BorraExternosTrabajoSAP());
              PreparaTablas.add(Monitoreos.Tablas.BorraGenericosTrabajoSAP());
              PreparaTablas.add(Monitoreos.Tablas.BorraInternosTrabajoSAP());            //Esta sección borra todas las tablas temporales que se crearon durante la ejecución anterior del programa
              PreparaTablas.add(Monitoreos.Tablas.BorraUsrAdminAgregados());
              PreparaTablas.add(Monitoreos.Tablas.BorraUsrAdminEliminados());
              PreparaTablas.add(Monitoreos.Tablas.BorraCruceInternosSAP(cadenaBD));
              PreparaTablas.add(Monitoreos.Tablas.BorraCruceExternosSAP(cadenaBD));             
              PreparaTablas.add(Monitoreos.Tablas.BorraBajasIntSAP());
              PreparaTablas.add(Monitoreos.Tablas.BorraBajasExtSAP());
              PreparaTablas.add(Monitoreos.Tablas.eliminanonominaextSAP());
              PreparaTablas.add(Monitoreos.Tablas.eliminanonominaintSAP());
              PreparaTablas.add(Monitoreos.Tablas.eliminaUsuariosSAP2(cadenaBD));     


//            PreparaTablas.add(Monitoreos.Querys.CreaInactividadExt(cadenaBD));      //Crea una tabla con los usuarios externos que no han ingresado a la aplicación en determinado tiempo
//            PreparaTablas.add(Monitoreos.Querys.BorraInactividadExt(cadenaBD));     //Borra los usuarios externos que no han ingresado a la aplicación en determinado tiempo de la lista de usuarios externos
//            PreparaTablas.add(Monitoreos.Querys.UsrIDIncInt(cadenaBD));             //Crea una tabla con los usuarios internos que tienen un UserID incorrecto
//            PreparaTablas.add(Monitoreos.Querys.BorraUsrIDIncInt(cadenaBD));        //Borra los usuaios internos con UserID incorrecto de la tabla de usuarios internos
//            PreparaTablas.add(Monitoreos.Querys.UsrIDIncExt(cadenaBD));             //Crea una tabla con los usuarios externos que tienen un UserID incorrecto
//            PreparaTablas.add(Monitoreos.Querys.BorraUsrIDIncExt(cadenaBD));        //Borra los usuaios externos con UserID incorrecto de la tabla de usuarios externos
//            PreparaTablas.add(Monitoreos.Querys.CreaDuplicadosInt(cadenaBD));       //Crea una tabla con los usuarios internos que tienen duplicidad por nombre en el registro de usuarios del sistema
//            PreparaTablas.add(Monitoreos.Querys.CreaDuplicadosExt(cadenaBD));       //Crea una tabla con los usuarios externos que tienen duplicidad por nombre en el registro de usuarios del sistema
//            PreparaTablas.add(Monitoreos.Querys.BorraExcepDuplicadosInt(cadenaBD)); //Borra los usuarios internos registrados en las excepciones de la tabla de usuarios duplicados
//            PreparaTablas.add(Monitoreos.Querys.BorraExcepDuplicadosExt(cadenaBD)); //Borra los usuarios internos registrados en las excepciones de la tabla de usuarios duplicados
//            PreparaTablas.add(Monitoreos.Querys.BorraDuplicadosInt(cadenaBD));      //Borra los usuarios duplicados que no fueron excepciones de la tabla original de usuarios internos
//            PreparaTablas.add(Monitoreos.Querys.BorraDuplicadosExt(cadenaBD));      //Borra los usuarios duplicados que no fueron excepciones de la tabla original de usuarios externos
//            PreparaTablas.add(Monitoreos.Querys.CreaPerfilesInt(cadenaBD));         //Crea una tabla que cruza los usuarios internos con los perfiles que deben tener según la matriz de perfiles
//            PreparaTablas.add(Monitoreos.Querys.CreaPerfilesExt(cadenaBD));         //Crea una tabla que cruza los usuarios externos con los perfiles que deben tener según la matriz de perfiles
//            PreparaTablas.add(Monitoreos.Querys.CreaCoincidenciasInt());            //Obtiene los usuarios internos con coincidencias correctas en la matriz de perfiles
//            PreparaTablas.add(Monitoreos.Querys.CreaCoincidenciasExt());            //Obtiene los usuarios externos con coincidencias correctas en la matriz de perfiles
//            PreparaTablas.add(Monitoreos.Querys.BorraPerfilesCorrectoInt());        //Borra los usuarios internos con perfil correcto de las incidencias
//            PreparaTablas.add(Monitoreos.Querys.BorraPerfilesCorrectoExt());        //Borra los usuarios externos con perfil correcto de las incidencias//
//            PreparaTablas.add(MonPac.Querys.BorraPerfilesCorrectoInt());            //Borra los usuarios internos con perfil correcto de las incidencias
//            PreparaTablas.add(MonPac.Querys.BorraPerfilesCorrectoExt());            //Borra los usuarios externos con perfil correcto de las incidencias
//            PreparaTablas.add(MonPac.Querys.BorraPerfilesCorrectoInt());            //Borra los usuarios internos con perfil correcto de las incidencias
//            PreparaTablas.add(MonPac.Querys.BorraPerfilesCorrectoExt());            //Borra los usuarios externos con perfil correcto de las incidencias
//            PreparaTablas.add(Monitoreos.Querys.CreaPerfilesSinUsoInt(cadenaBD));       //Crea una lista de perfiles no usados por los usuarios internos
//            PreparaTablas.add(Monitoreos.Querys.CreaPerfilesSinUsoExt(cadenaBD));       //Crea una lista de perfiles no usados por los usuarios externos
//            PreparaTablas.add(Monitoreos.Querys.CreaPerfilesSinUso(cadenaBD));          //Crea una lista de la interseccion de perfiles no usados
//            PreparaTablas.add(Monitoreos.Querys.BorraPerfilesSinUsoInt());          //
//            PreparaTablas.add(Monitoreos.Querys.BorraPerfilesSinUsoExt());
//            PreparaTablas.add(Monitoreos.Querys.CreaUsrNoAutorizadosInt());
//            PreparaTablas.add(Monitoreos.Querys.CreaUsrNoAutorizadosExt());
//            PreparaTablas.add(Monitoreos.Querys.BorraNoAutorizadosInt());
//            PreparaTablas.add(Monitoreos.Querys.BorraNoAutorizadosExt());  
            
            
            PreparaTablas.add(Monitoreos.Tablas.TablaAgreg(cadenaBD));          //crea la tabla de usuarios administradores agregados
            PreparaTablas.add(Monitoreos.Tablas.TablaElim(cadenaBD));              //crea la tabla de usuarios administradores eliminados           
            PreparaTablas.add(Monitoreos.Tablas.TablaAdminUsrAdmin(cadenaBD));              
            
            PreparaTablas.add(Monitoreos.Tablas.CreaUsuariosSAP(cadenaBD));  //crea la tabla de usuariosSAP de la tabla Usuarios2 para su manipulacion
            PreparaTablas.add(Monitoreos.Tablas.TablaExtSAP(cadenaBD));         //Crea la tabla de usuarios externos                         
            PreparaTablas.add(Monitoreos.Querys.BorrarExternosTablaSAP(cadenaBD));//borra los registros de esternos de la tabla
            PreparaTablas.add(Monitoreos.Tablas.TablaGenSAP(cadenaBD));           //crear tabla gen sap
        
            PreparaTablas.add(Monitoreos.Tablas.GenExt(cadenaBD));              //insertar genericos de TablaEXT a TablaGen
            PreparaTablas.add(Monitoreos.Querys.BorrarGenExt(cadenaBD));        //eliminar genericos en tabla EXTERNOS
            
            PreparaTablas.add(Monitoreos.Querys.BorrarGenericosTablaSAP(cadenaBD));//borrar los registros genericos sap de la tabla
            PreparaTablas.add(Monitoreos.Tablas.TablaIntSAP(cadenaBD));          //Crea la tabla de usuarios internos
            

            PreparaTablas.add(Monitoreos.Tablas.CruceInternosSAP(cadenaBD)); 
            PreparaTablas.add(Monitoreos.Tablas.CruceExternosSAP(cadenaBD));           //Realiza el cruce de los archivos de nómina con los usuarios internos//            PreparaTablas.add(Monitoreos.Querys.CruceExternos(cadenaBD));           //Realiza el cruce de los archivos de nómina con los usuarios externos
//           
            PreparaTablas.add(Monitoreos.Tablas.NoNominaCreaIntSAP(cadenaBD));         //Crea una tabla con los usuarios internos que no fueron encontrados en nómina
            PreparaTablas.add(Monitoreos.Querys.NoNominaBorraIntSAP(cadenaBD));        //Boora los usuarios internos no encontrados en la nómina del listado de usuarios internos
            PreparaTablas.add(Monitoreos.Tablas.NoNominaCreaExtSAP(cadenaBD));         //Crea una tabla con los usuarios externos que no fueron encontrados en nómina
            PreparaTablas.add(Monitoreos.Querys.NoNominaBorraExtSAP(cadenaBD));        //Boora los usuarios externos no encontrados en la nómina del listado de usuarios externos
//           
            PreparaTablas.add(Monitoreos.Tablas.CreaBajasIntSAP(cadenaBD));            //Crea una tabla con los usuarios internos que se encuentran dados de baja en la nómina
            PreparaTablas.add(Monitoreos.Querys.BorraBajasIntSAP(cadenaBD));           //Borra los usuarios internos que se encuientran dados de baja en la nómina del listado de usuarios internos
            PreparaTablas.add(Monitoreos.Tablas.CreaBajasExtSAP(cadenaBD));            //Crea una tabla con los usuarios externos que se encuentran dados de baja en la nómina
            PreparaTablas.add(Monitoreos.Querys.BorraBajasExtSAP(cadenaBD));           //Borra los usuarios externos que se encuientran dados de baja en la nómina del listado de usuarios externos
            
            //PreparaTablas.add(Monitoreos.Tablas.CreaInactividadIntSAP(cadenaBD));      //Crea una tabla con los usuarios internos que no han ingresado a la aplicación en determinado tiempo
            //PreparaTablas.add(Monitoreos.Querys.BorraInactividadIntSAP(cadenaBD));     //Borra los usuarios internos que no han ingresado a la aplicación en determinado tiempo de la lista de usuarios internos
            
            System.out.println("Creando nuevas tablas de trabajo");
            EjecutaSAP.Exect(ConLocal.conexion, PreparaTablas);                  //Ejecuta todas las instrucciones almacenadas en el arreglo
//
//            Conteos conteo = new Conteos();
//
//            String total = conteo.conteo(ConLocal.conexion, "USUARIOS" + parametros[4]);//Cuenta el total de usuarios
//            jTextField1.setText(total);
//            
//            String internos = conteo.conteo(ConLocal.conexion, "INTERNOS");     //Cuenta el total de usuarios internos
//            jTextField3.setText(internos);
//            String externos = conteo.conteo(ConLocal.conexion, "EXTERNOS");     //Cuenta el total de usuarios externos
//            jTextField4.setText(externos);
//    
//    //        
//            String bint = conteo.conteo(ConLocal.conexion, "BAAJASINT", "NONOMINAINT");//Cuenta el total de usuarios internos dados de baja y que no están registrados en nómina
//            jTextField5.setText(bint);
//            String bext = conteo.conteo(ConLocal.conexion, "BAAJASEXT", "NONOMINAEXT");//Cuenta el total de usuarios externos dados de baja y que no están registrados en nómina
//            jTextField6.setText(bext);
//            
//            
//            String inint = conteo.conteo(ConLocal.conexion, "INACTIVIDADINT");  //Cuenta el total de usuaios internos reportados con intactividad
//            jTextField7.setText(inint);
//            String inext = conteo.conteo(ConLocal.conexion, "INACTIVIDADEXT");  //Cuenta el total de usuaios externos reportados con intactividad
//            jTextField8.setText(inext);
//            
//         
//            String usrincint = conteo.conteo(ConLocal.conexion, "USRINCINT" );  //Cuenta el total de usuarios internos reportados con UserID incorrecto
//            jTextField11.setText(usrincint);
//            String usrincext = conteo.conteo(ConLocal.conexion, "USRINCEXT");   //Cuenta el total de usuarios externos reportados con UserID incorrecto
//            jTextField12.setText(usrincext);
//            
//            String dupxnomint = conteo.conteo(ConLocal.conexion, "DUPXNOMBREINT");//Cuenta el total de usuarios internos reportados por duplicidad
//            dupxnomint = Integer.toString(Integer.parseInt(dupxnomint)/2);
//            jTextField13.setText(dupxnomint);
//            String dupxnomext = conteo.conteo(ConLocal.conexion, "DUPXNOMBREEXT");//Cuenta el total de usuarios externos reportados por duplicidad
//            dupxnomext = Integer.toString(Integer.parseInt(dupxnomext)/2);
//            jTextField14.setText(dupxnomext);
//            
//            String perfilint = conteo.conteo(ConLocal.conexion, "PERFILESINT"); //Cuenta el total de usuarios internos con perfil incorrecto
//            jTextField15.setText(perfilint);
//            
//            String perfilext = conteo.conteo(ConLocal.conexion, "PERFILESEXT"); //Cuenta el total de usuarios externos con perfil incorrecto
//            jTextField16.setText(perfilext);
//            
//            String noAutoint = conteo.conteo(ConLocal.conexion, "USUARIOSNOAUTORIZADOSINT");//Cuenta el total de usuarios internos con perfil no autorizado
//            jTextField17.setText(noAutoint);
//            
//            String noAutoext = conteo.conteo(ConLocal.conexion, "USUARIOSNOAUTORIZADOSEXT");//Cuenta el total de usuarios externos con perfil no autorizado
//            jTextField18.setText(noAutoext);
//            
//            String altaUsrAdmin = conteo.conteo(ConLocal.conexion, "AGREGADOS");//Cuenta el total de usuarios administradores dados de alta
//            jTextField19.setText(altaUsrAdmin);
//            
//            String bajaUsrAdmin = conteo.conteo(ConLocal.conexion, "ELIMINADOS");//Cuenta el total de usuarios administradores dados de baja
//            jTextField20.setText(bajaUsrAdmin);
//
//            System.out.println(cadenaBD);
//    
//            ConLocal.Cerrar();                                                  //Cierra a conexión de MySQL

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
    
    public String obtieneContrasena(){                                          //Obtiene la contraseña de la base de datos local
    
        String contrasena;
        contrasena = jPasswordField1.getText();
        return contrasena;
    }
   
    
    public String obtieneID(){                                                  //Obtiene la fecha de los archivos de identidad ingresada por el usuario
    
        String ID;
        ID = jTextField31.getText();
        return ID;
    }
    
     public String obtieneIDAnt(){                                                  //Obtiene la fecha de los archivos de identidad ingresada por el usuario
    
        String ID;
        ID = jTextField32.getText();
        return ID;
    }
    
    
                                                                                //Procedimiento para agregar todos los archivos a la base de datos local
    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
                
        if(jTextField31.getText().equals(""))                                   //Verifica que se haya ingresado el campo de las nóminas de identidad
        {
            JOptionPane.showMessageDialog(null, "No se ha ingresado el campo Archivos de identidad, Por favor ingrese la fecha de descarga en formato ddmmaaaa", "Error", JOptionPane.WARNING_MESSAGE);
        }
        else
        {
            ArrayList<String> PreparaTablas = new ArrayList();                  //ARRAY LIST PARA ALMACENAR LAS OPERACIONES A BASE DE DATOS Y HACER TODO MEDIANTE UNA SOLA CONEXIÓN
            String cadenaBD = obtieneBase() + "|" + obtieneUsuario() + "|" + obtieneContrasena() + "|" + obtieneTabla() + "|" + obtieneID() + "|" + obtieneIDAnt();
            String[] parametros = cadenaBD.split("\\|");
            System.out.println("Estableciendo conexión con la BD");
            Conexion ConLocal = new Conexion();
            ConLocal.AbrirLocal(cadenaBD);

            System.out.println("Creando objeto de ejecución");
            ExecQuery EjecutaSAP = new ExecQuery();
            
            
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
            if(jCheckUsrAdmin.isSelected())                                       //Valida que este habilitada la casilla de Usuarios Administradores                                     
            {
                System.out.println("Registrando archivo de Usuarios Administradores");
                PreparaTablas.add(Monitoreos.Tablas.eliminaTablaUsrAdmin(cadenaBD)); 
                PreparaTablas.add(Monitoreos.Tablas.idCreaUsrAdmin(cadenaBD));  //Crea la tabla de usuariosAdministradores
                PreparaTablas.add(Monitoreos.Tablas.UsrAdminTabla(UsrAdmin,cadenaBD)); //lee el archivo e inserta los datos                                                                                                             
            }
            
            if(jCheckDemonsa2.isSelected())                                       //Valida que este habilitada la casilla de Usuarios Administradores                                     
            {
                System.out.println("Registrando archivo Demonsa 2");
                PreparaTablas.add(Monitoreos.Tablas.eliminaTablaDemonsa2(cadenaBD));
                PreparaTablas.add(Monitoreos.Tablas.idCreaDemonsa2(cadenaBD));
                
                
                
                Monitoreos.Archivos.lecturaUsuariosDemonsa2(UsrAdmin, cadenaBD);     //Lee el archivo de la nómina de internos e inserta los usuarios en la BD local
            }
            
            if(jCheckUsrs.isSelected())                                       //Valida que este habilitada la casilla de Usuarios Administradores                                     
            {
                System.out.println("Registrando archivo UsuariosSAP");
                
                
               PreparaTablas.add(Monitoreos.Tablas.eliminaTablaUsrsSAP(cadenaBD)); //elimina la tabla Usuarios SAP en caso de existir
               PreparaTablas.add(Monitoreos.Tablas.CreaUsuarios2(cadenaBD));    //crea la tabla de usuarios
               PreparaTablas.add(Monitoreos.Tablas.InsertarUsuariosSAP(UsuariosSAP, cadenaBD));     //Lee el archivo de  usuarios SAP e inserta usuarios en la BD local              
            }
            
            if(jCheckFechasAcceso.isSelected())                                       //Valida que este habilitada la casilla de Usuarios Administradores                                     
            {
                System.out.println("Registrando archivo UsuariosSAP");
                
                
//               PreparaTablas.add(Monitoreos.Tablas.eliminaTablaUsrsSAP(cadenaBD)); //elimina la tabla Usuarios SAP en caso de existir
//               PreparaTablas.add(Monitoreos.Tablas.CreaUsuarios2(cadenaBD));    //crea la tabla de usuarios
//               PreparaTablas.add(Monitoreos.Tablas.InsertarUsuariosSAP(UsuariosSAP, cadenaBD));     //Lee el archivo de  usuarios SAP e inserta usuarios en la BD local              
            }

//            if(Matriz.isSelected())                                             //Valida que esté habilitada la casilla de matriz de perfiles de perfiles
//            {
//                System.out.println("Registrando matriz de perfiles estandarizados");
//                Conexion ConLocal = new Conexion();
//                ConLocal.AbrirLocal(cadenaBD);
//                ExecQuery EjecutaLo = new ExecQuery();
//                EjecutaLo.Exect(ConLocal.conexion, MonPac.Querys.BorraMatrizPerfiles(cadenaBD));    //Borra la matriz de perfiles anterior
//                EjecutaLo.Exect(ConLocal.conexion, MonPac.Querys.CreaMatrizPerfiles(cadenaBD));     //Crea la tabla para la matriz de perfiles
//                EjecutaLo.Exect(ConLocal.conexion, MonPac.Querys.CreaMatrizPerfiles(cadenaBD, MatrizPerfiles)); //Lee el archivo de matriz de perfiles y la inserta en la BD local
//
//            }
            System.out.println("Creando nuevas tablas de trabajo");
            EjecutaSAP.Exect(ConLocal.conexion, PreparaTablas);    
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

    private void jButtonInternosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInternosActionPerformed
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
            jTextFieldInternos.setText(NomInt);                                       //Muestra la ruta en el cuadro de texto asociado
            jCheckUsrInt.setSelected(true);                                     //Selecciona la casilla de verificación
            jCheckUsrInt.setEnabled(true);                                      //Permite al usuario deshabilitarla posteriormente
        }
    }//GEN-LAST:event_jButtonInternosActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButtonExternosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExternosActionPerformed
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
            jTextFieldExternos.setText(NomExt);                                       //Muestra la ruta en el cuadro de texto asociado
            jCheckUsrExt.setSelected(true);                                     //Selecciona la casilla de verificación
            jCheckUsrExt.setEnabled(true);                                      //Permite al usuario deshabilitarla posteriormente
        }
    }//GEN-LAST:event_jButtonExternosActionPerformed

    private void jButtonUsrAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUsrAdminActionPerformed
        String pruebas = "D:\\Yoshian Loyo 2016\\DESARROLLO\\MONITOREO SAP\\Atziry\\ARCHIVOS TXT\\Usuarios Administradores 090316.txt";
        JFileChooser fileChooser = new JFileChooser(pruebas);               //Abre una ventana de exploración con la última ubicacion en que se seleccionó un archivo
        fileChooser.setDialogTitle("Usuarios Administradores");                          //Establece el titulo de la ventana de exploración
        Dimension dim = new Dimension(800, 600);                                //Establece el tamaño de la ventana de exploración
        fileChooser.setPreferredSize(dim);                                      //Establece el tamaño de la ventana de exploración

        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");  //Restringe el tipo de archivos que se muestran en el explirador
        fileChooser.setFileFilter(filter);
        
        int seleccion = fileChooser.showOpenDialog(this);                       
        if(seleccion == JFileChooser.APPROVE_OPTION)                            //Valida si se seleccionó un archivo
        {
            UsrAdmin = fileChooser.getSelectedFile().getAbsolutePath();           //Obtiene la ubicación del archivo seleccionado
            lastArchivo = UsrAdmin;                                               //Lo guarda como última ubicación de selección
            jTextFieldUsrAdmin.setText(UsrAdmin);                                       //Muestra la ruta en el cuadro de texto asociado
            System.out.println(lastArchivo);
            jCheckUsrAdmin.setSelected(true);                                     //Selecciona la casilla de verificación
            jCheckUsrAdmin.setEnabled(true);                                      //Permite al usuario deshabilitarla posteriormente
        }
    }//GEN-LAST:event_jButtonUsrAdminActionPerformed

    private void jButtonUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUsuariosActionPerformed
        JFileChooser fileChooser = new JFileChooser(lastArchivo);               //Abre una ventana de exploración con la última ubicacion en que se seleccionó un archivo
        fileChooser.setDialogTitle("Usuarios");                          //Establece el titulo de la ventana de exploración
        Dimension dim = new Dimension(800, 600);                                //Establece el tamaño de la ventana de exploración
        fileChooser.setPreferredSize(dim);                                      //Establece el tamaño de la ventana de exploración

        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");  //Restringe el tipo de archivos que se muestran en el explirador
        fileChooser.setFileFilter(filter);
        
        int seleccion = fileChooser.showOpenDialog(this);                       
        if(seleccion == JFileChooser.APPROVE_OPTION)                            //Valida si se seleccionó un archivo
        {
            UsuariosSAP = fileChooser.getSelectedFile().getAbsolutePath();           //Obtiene la ubicación del archivo seleccionado
            lastArchivo = UsuariosSAP;                                               //Lo guarda como última ubicación de selección
            jTextFieldUsuarios.setText(UsuariosSAP);                                       //Muestra la ruta en el cuadro de texto asociado
            jCheckUsrs.setSelected(true);                                     //Selecciona la casilla de verificación
            jCheckUsrs.setEnabled(true);                                      //Permite al usuario deshabilitarla posteriormente
        }
    }//GEN-LAST:event_jButtonUsuariosActionPerformed

    private void jTextField32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField32ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField32ActionPerformed

    private void jButtonDemonsa2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDemonsa2ActionPerformed
        JFileChooser fileChooser = new JFileChooser(lastArchivo);               //Abre una ventana de exploración con la última ubicacion en que se seleccionó un archivo
        fileChooser.setDialogTitle("Archivo Demonsa 2");                          //Establece el titulo de la ventana de exploración
        Dimension dim = new Dimension(800, 600);                                //Establece el tamaño de la ventana de exploración
        fileChooser.setPreferredSize(dim);                                      //Establece el tamaño de la ventana de exploración

        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");  //Restringe el tipo de archivos que se muestran en el explirador
        fileChooser.setFileFilter(filter);
        
        int seleccion = fileChooser.showOpenDialog(this);                       
        if(seleccion == JFileChooser.APPROVE_OPTION)                            //Valida si se seleccionó un archivo
        {
            Demonsa2 = fileChooser.getSelectedFile().getAbsolutePath();           //Obtiene la ubicación del archivo seleccionado
            lastArchivo = Demonsa2;                                               //Lo guarda como última ubicación de selección
            jTextFieldDemonsa2.setText(Demonsa2);                                       //Muestra la ruta en el cuadro de texto asociado
            jCheckDemonsa2.setSelected(true);                                     //Selecciona la casilla de verificación
            jCheckDemonsa2.setEnabled(true);                                      //Permite al usuario deshabilitarla posteriormente
        }
    }//GEN-LAST:event_jButtonDemonsa2ActionPerformed

    private void jTextFieldUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldUsuariosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldUsuariosActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
//        cadenaBD = obtieneBase()+"|"+obtieneUsuario()+"|"+obtieneContrasena()+"|"+obtieneTabla() + "|" + obtieneID() + "|" + obtieneIDAnt();
//        AdministradoresSAP asa = new AdministradoresSAP(cadenaBD);
//        asa.setVisible(true);

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButtonFechasAccesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFechasAccesoActionPerformed
        JFileChooser fileChooser = new JFileChooser(lastArchivo);               //Abre una ventana de exploración con la última ubicacion en que se seleccionó un archivo
        fileChooser.setDialogTitle("Archivo Fechas de acceso");                          //Establece el titulo de la ventana de exploración
        Dimension dim = new Dimension(800, 600);                                //Establece el tamaño de la ventana de exploración
        fileChooser.setPreferredSize(dim);                                      //Establece el tamaño de la ventana de exploración

        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");  //Restringe el tipo de archivos que se muestran en el explirador
        fileChooser.setFileFilter(filter);
        
        int seleccion = fileChooser.showOpenDialog(this);                       
        if(seleccion == JFileChooser.APPROVE_OPTION)                            //Valida si se seleccionó un archivo
        {
            FechasAcceso = fileChooser.getSelectedFile().getAbsolutePath();           //Obtiene la ubicación del archivo seleccionado
            lastArchivo = FechasAcceso;                                               //Lo guarda como última ubicación de selección
            jTextFieldFechasAcceso.setText(FechasAcceso);                                       //Muestra la ruta en el cuadro de texto asociado
            jCheckFechasAcceso.setSelected(true);                                     //Selecciona la casilla de verificación
            jCheckFechasAcceso.setEnabled(true);                                      //Permite al usuario deshabilitarla posteriormente
        }
              
    }//GEN-LAST:event_jButtonFechasAccesoActionPerformed

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
            java.util.logging.Logger.getLogger(MonitoreoSAP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MonitoreoSAP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MonitoreoSAP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MonitoreoSAP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MonitoreoSAP().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButtonDemonsa2;
    private javax.swing.JButton jButtonExternos;
    private javax.swing.JButton jButtonFechasAcceso;
    private javax.swing.JButton jButtonInternos;
    private javax.swing.JButton jButtonUsrAdmin;
    private javax.swing.JButton jButtonUsuarios;
    private com.toedter.calendar.JCalendar jCalendar1;
    private javax.swing.JCheckBox jCheckDemonsa2;
    private javax.swing.JCheckBox jCheckFechasAcceso;
    private javax.swing.JCheckBox jCheckUsrAdmin;
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
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelAdminUsr;
    private javax.swing.JLabel jLabelDemonsa2;
    private javax.swing.JLabel jLabelFechasAcceso3;
    private javax.swing.JLabel jLabelInternos;
    private javax.swing.JLabel jLabelInternos8;
    private javax.swing.JLabel jLabelUsuarios;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanelAdminUsr;
    private javax.swing.JPanel jPanelDemonsa1;
    private javax.swing.JPanel jPanelDemonsa2;
    private javax.swing.JPanel jPanelInternos;
    private javax.swing.JPanel jPanelInternos8;
    private javax.swing.JPanel jPanelUsuarios;
    private javax.swing.JPasswordField jPasswordField1;
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
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField28;
    private javax.swing.JTextField jTextField29;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField30;
    private javax.swing.JTextField jTextField31;
    private javax.swing.JTextField jTextField32;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextFieldDemonsa2;
    private javax.swing.JTextField jTextFieldExternos;
    private javax.swing.JTextField jTextFieldFechasAcceso;
    private javax.swing.JTextField jTextFieldInternos;
    private javax.swing.JTextField jTextFieldUsrAdmin;
    private javax.swing.JTextField jTextFieldUsuarios;
    // End of variables declaration//GEN-END:variables
}
