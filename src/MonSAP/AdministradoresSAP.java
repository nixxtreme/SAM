/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MonSAP;


import Monitoreos.EachRowEditor;
import Monitoreos.CheckBoxRenderer;
import Monitoreos.EachRowRenderer;
import Monitoreos.Clase_CellEditor;
import Monitoreos.Clase_CellRender;
import Monitoreos.Conexion;
import Monitoreos.ExecQuery;
import java.awt.Color;
import java.sql.ResultSet;
import java.time.Clock;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import static javax.swing.JTable.AUTO_RESIZE_OFF;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
/**
 *
 * @author VS3XXBD
 */
public class AdministradoresSAP extends javax.swing.JFrame {
    String cadenaBD;
    ResultSet AdmUsrAdm;
    JCheckBox component = new JCheckBox();
    Clase_CellEditor editor  = new Clase_CellEditor();
    /**
     * Creates new form AdministradoresSAP
     */
    public AdministradoresSAP(String cadenaBD) {
        this.cadenaBD = cadenaBD;
        initComponents();
        definirModelosAdminUsrAdmin();
    }
    
    void definirModelosAdminUsrAdmin()                                               //Define el modelo de la tabla de inconsistencias de usuarios internos reportados como baja
    {
        DefaultTableModel modeloBajasInt = new DefaultTableModel();             //Crea el objeto modelo de tabla
                                                                 //Inicializa la variable para el contador
        try
        {
            Conexion conLocal = new Conexion();     
            conLocal.AbrirLocal(cadenaBD);
            
            ExecQuery EjecutaLo = new ExecQuery();                              //Crea el objeto para ejecutar la consulta
            AdmUsrAdm = EjecutaLo.Cons(conLocal.conexion, Monitoreos.Querys.AdministraUsuariosAdmin());   //Ejecuta la consulta y almacena el resultado en la variable
        
            // Inserta el encabezado
            modeloBajasInt.addColumn("Permitir");                            
            modeloBajasInt.addColumn("Usuario");
            modeloBajasInt.addColumn("Nombre");
            modeloBajasInt.addColumn("Apellido");
            modeloBajasInt.addColumn("Rol");
            modeloBajasInt.addColumn("Valor de autorización");

            java.sql.ResultSetMetaData rsmd = AdmUsrAdm.getMetaData();
            int colNo = rsmd.getColumnCount();
            Object[] objects = new Object[colNo];
            
            while (AdmUsrAdm.next()){
                for(int k=1; k < (colNo + 1); k++){
                    if (k == 1){
                        objects[k-1]= new Boolean(false);
                    }else{
                        objects[k-1]=AdmUsrAdm.getObject(k);
                    }
                }
                modeloBajasInt.addRow(objects);
            }
            
            TablaAdminUsrAdmin.setModel(modeloBajasInt);
            TablaAdminUsrAdmin.setAutoResizeMode(AUTO_RESIZE_OFF);  
            
            TableColumn CAgregar = TablaAdminUsrAdmin.getColumn("Permitir"); //Se llama a la columna
            CAgregar.setPreferredWidth(60);                                 //Se define su tamaño
            TableColumn Usuario = TablaAdminUsrAdmin.getColumn("Usuario");  //Se llama a la columna
            Usuario.setPreferredWidth(120);                                 //Se define su tamaño
            TableColumn Nombre = TablaAdminUsrAdmin.getColumn("Nombre");    //Se llama a la columna
            Nombre.setPreferredWidth(250);                                   //Se define su tamaño
            TableColumn Apellido = TablaAdminUsrAdmin.getColumn("Apellido");//Se llama a la columna
            Apellido.setPreferredWidth(250);                                 //Se define su tamaño
            TableColumn Rol = TablaAdminUsrAdmin.getColumn("Rol");          //Se llama a la columna
            Rol.setPreferredWidth(160);                                     //Se define su tamaño
            TableColumn Valorauto = TablaAdminUsrAdmin.getColumn("Valor de autorización");        //Se llama a la columna
            Valorauto.setPreferredWidth(110);                                  //Se define su tamaño
            
            CheckBoxRenderer checkBoxRenderer = new CheckBoxRenderer();
            EachRowRenderer rowRenderer = new EachRowRenderer();

            for(int s=0; s<TablaAdminUsrAdmin.getRowCount(); s++){                    
                rowRenderer.add(s, checkBoxRenderer);    
            }
                
            JCheckBox checkBox = new JCheckBox();
            checkBox.setHorizontalAlignment(JLabel.CENTER);
            DefaultCellEditor checkBoxEditor = new DefaultCellEditor(checkBox);
            EachRowEditor rowEditor = new EachRowEditor(TablaAdminUsrAdmin);
            
            for(int a=0; a<TablaAdminUsrAdmin.getRowCount();a++){
                rowEditor.setEditorAt(a, checkBoxEditor);
            }
            
            TablaAdminUsrAdmin.getColumn("Permitir").setCellRenderer(rowRenderer);
            TablaAdminUsrAdmin.getColumn("Permitir").setCellEditor(rowEditor);            
            
            conLocal.Cerrar();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        } 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaAdminUsrAdmin = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/telcel.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(638, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        TablaAdminUsrAdmin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(TablaAdminUsrAdmin);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE))
        );

        jButton1.setText("Reportar usuarios administradores");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ArrayList<String> preparaInstruccion = new ArrayList();
        Conexion conLocal = new Conexion();     
        conLocal.AbrirLocal(cadenaBD);
        MonAD.ExecQuery EjecutaSAP = new MonAD.ExecQuery();
        for(int s=0; s<TablaAdminUsrAdmin.getRowCount();s++){
            if(TablaAdminUsrAdmin.getModel().getValueAt(s, 0).equals(true))
            {
                preparaInstruccion.add("UPDATE `adminusradminsap` SET `PERMITIDO` = 1 WHERE `USUARIO` = '" + TablaAdminUsrAdmin.getModel().getValueAt(s, 1) + "'");
            }
            System.out.print("  Nombre: " + TablaAdminUsrAdmin.getModel().getValueAt(s, 1));
        }
        EjecutaSAP.Exect(conLocal.conexion, preparaInstruccion);
        
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(AdministradoresSAP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdministradoresSAP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdministradoresSAP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdministradoresSAP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdministradoresSAP(args[1]).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaAdminUsrAdmin;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
