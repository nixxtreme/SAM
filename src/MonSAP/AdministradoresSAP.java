/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MonSAP;

import Monitoreos.Clase_CellEditor;
import Monitoreos.Clase_CellRender;
import Monitoreos.Conexion;
import Monitoreos.ExecQuery;
import static javax.swing.JTable.AUTO_RESIZE_OFF;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author VS3XXBD
 */
public class AdministradoresSAP extends javax.swing.JFrame {

    /**
     * Creates new form AdministradoresSAP
     */
    public AdministradoresSAP(String cadenaBD) {
        initComponents();
        definirModelosAdminUsrAdmin(cadenaBD);
    }
    
    void definirModelosAdminUsrAdmin(cadenaBD)                                               //Define el modelo de la tabla de inconsistencias de usuarios internos reportados como baja
    {
        DefaultTableModel modeloBajasInt = new DefaultTableModel();             //Crea el objeto modelo de tabla
        
        
        
        Object[] registro = new Object[6];                                     //Crea un arreglo para recibir los elementos de cada renglon
        int i = 0;                                                              //Inicializa la variable para el contador
        try
        {
            Conexion conLocal = new Conexion();                                 //Inicia la conexión local
            conLocal.AbrirLocal(cadenaBD);
            ExecQuery EjecutaLo = new ExecQuery();                              //Crea el objeto para ejecutar la consulta
            bajasInt = EjecutaLo.Cons(conLocal.conexion, Monitoreos.Querys.ResultadosBajasInt());   //Ejecuta la consulta y almacena el resultado en la variable
            
            if(bajasInt.next())                                                 //Verifica que el resultado no esté vacío
            {
                modeloBajasInt.addColumn("Permitir");                            //Crea las columnas necesarias para el reporte
                modeloBajasInt.addColumn("Usuario");
                modeloBajasInt.addColumn("Nombre");
                modeloBajasInt.addColumn("Apellido");
                modeloBajasInt.addColumn("Rol");
                modeloBajasInt.addColumn("Valor de autorización");
                bajasInt.beforeFirst();                                         //Regresa a la posición inicial del resultado
                while(bajasInt.next())                                          //Lee cada registro hasta que ya no haya más
                {
                    for(int k=1; k<16; k++)                                     
                    {
                        if(k==1)                                                
                        {
                            registro[k-1]=Boolean.TRUE;                         //Si está en la primer columna establece un valor TRUE para que el checkbox esté seleccionado
                        }
                        else
                        {
                            registro[k-1]=bajasInt.getString(k-1);              //Recorre los elementos del registro para obtener cada dato de las columnas
                        }
                    }
                    modeloBajasInt.addRow(registro);                            //Ya que todos los elementos del registro están en el arreglo se agrega el arreglo como un nuevo renglón de la tabla
                }

                tablaBajasInt.setModel(modeloBajasInt);                         //Una vez construida completamente la tabla se define el modelo a la tabla original
                tablaBajasInt.setAutoResizeMode(AUTO_RESIZE_OFF);               //Se desabilita el ajuste automatico de ancho de columnas para establecerlo manualmente
                tablaBajasInt.getColumnModel().getColumn(0).setCellEditor(new Clase_CellEditor());  //Se hace uso de editor y renderizador de columnas
                tablaBajasInt.getColumnModel().getColumn(0).setCellRenderer(new Clase_CellRender());

                TableColumn CAgregar = tablaBajasInt.getColumn("Agregar");      //Se llama a la columna
                CAgregar.setPreferredWidth(55);                                 //Se define su tamaño
                TableColumn CNumEmp = tablaBajasInt.getColumn("Número de empleado");    //Se llama a la columna
                CNumEmp.setPreferredWidth(140);                                 //Se define su tamaño
                TableColumn CUserID = tablaBajasInt.getColumn("User ID");       //Se llama a la columna
                CUserID.setPreferredWidth(70);                                  //Se define su tamaño
                TableColumn CNombre = tablaBajasInt.getColumn("Nombre");        //Se llama a la columna
                CNombre.setPreferredWidth(360);                                 //Se define su tamaño
                TableColumn CFecha = tablaBajasInt.getColumn("Fecha de último acceso"); //Se llama a la columna
                CFecha.setPreferredWidth(160);                                  //Se define su tamaño
                TableColumn CRegion = tablaBajasInt.getColumn("Región");        //Se llama a la columna
                CRegion.setPreferredWidth(70);                                  //Se define su tamaño
                TableColumn CIP = tablaBajasInt.getColumn("IP");                //Se llama a la columna
                CIP.setPreferredWidth(120);                                     //Se define su tamaño
                TableColumn CIDPerfil = tablaBajasInt.getColumn("ID perfil");   //Se llama a la columna
                CIDPerfil.setPreferredWidth(85);                                //Se define su tamaño
                TableColumn CPerfil = tablaBajasInt.getColumn("Perfil");        //Se llama a la columna
                CPerfil.setPreferredWidth(400);                                 //Se define su tamaño
                TableColumn CBBFNumEMP = tablaBajasInt.getColumn("ID Numero de empleado");  //Se llama a la columna
                CBBFNumEMP.setPreferredWidth(160);                              //Se define su tamaño
                TableColumn CBBFPuesto = tablaBajasInt.getColumn("Puesto");     //Se llama a la columna
                CBBFPuesto.setPreferredWidth(400);                              //Se define su tamaño
                TableColumn CBBFGerencia = tablaBajasInt.getColumn("Gerencia"); //Se llama a la columna
                CBBFGerencia.setPreferredWidth(300);                            //Se define su tamaño
                TableColumn CBBFNombre = tablaBajasInt.getColumn("Nombre ");    //Se llama a la columna
                CBBFNombre.setPreferredWidth(300);                              //Se define su tamaño
                TableColumn CBBFFechaBaja = tablaBajasInt.getColumn("Fecha de baja");   //Se llama a la columna
                CBBFFechaBaja.setPreferredWidth(160);                           //Se define su tamaño
                TableColumn Estatus = tablaBajasInt.getColumn("Estatus");       //Se llama a la columna
                Estatus.setPreferredWidth(160);                                 //Se define su tamaño
            }
            else                                                                //Si el resultado se encontraba vacío
            {
                modeloBajasInt.addColumn("No se encontraron inconsistencias");  //Se crea una columna con la leyecnda
                tablaBajasInt.setModel(modeloBajasInt);                         //Se define el modelo 
            }
            
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

        jPanel11 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableAdminUsrAdmin = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/telcel.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(583, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTableAdminUsrAdmin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTableAdminUsrAdmin);

        jButton1.setText("jButton1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
                new AdministradoresSAP().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableAdminUsrAdmin;
    // End of variables declaration//GEN-END:variables
}
