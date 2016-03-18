/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MonAD;

import Monitoreos.Clase_CellEditor;
import Monitoreos.Clase_CellRender;
import MonPac.*;
import java.sql.ResultSet;
import static javax.swing.JTable.AUTO_RESIZE_OFF;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author YOSH
 */
public class ResultadosIndividual extends javax.swing.JFrame {
    
    ResultSet bajasInt, bajasExt, usrInt, usrExt, inactInt, inactExt, bajaInt, dupInt, dupExt, eprfilInt, perfilExt;        //Se establecen los resultset para las diferentes consultas
    /**
     * Creates new form ResultadosIndividual
     */
    public ResultadosIndividual(String cadenaBD)                                //Método original para inicializar componentes, nunca se utiliza
    {
        
        initComponents();
    }
    
    public ResultadosIndividual(int reg, String cadenaBD)                       //Inicializa la ventana y ejecuta los métodos para que se visualicen las tablas de resultados
    {
        initComponents();                                                       //Inicializa los componentes proncipales de la ventana
        definirModelosBajasInt(reg, cadenaBD);                                  //Define el modelo de la tabla de inconsistencias de bajas internos
        definirModelosBajasExt(reg, cadenaBD);                                  //Define el modelo de la tabla de inconsistencias de bajas externos
        definirModelosUsrInt(reg, cadenaBD);                                    //Define el modelo de la tabla de inconsistencias de inactividad internos
        definirModelosUsrExt(reg, cadenaBD);                                    //Define el modelo de la tabla de inconsistencias de inactividad externos
        definirModelosInactividadInt(reg, cadenaBD);                            //Define el modelo de la tabla de inconsistencias de UserID incorrecto internos
        definirModelosInactividadExt(reg, cadenaBD);                            //Define el modelo de la tabla de inconsistencias de UserID incorrecto externos
        definirModelosDuplicadosNombreInt(reg, cadenaBD);                       //Define el modelo de la tabla de inconsistencias de usuarios duplicados por nombre internos
        definirModelosDuplicadosNombreExt(reg, cadenaBD);                       //Define el modelo de la tabla de inconsistencias de usuarios duplicados por nombre externos
        definirModelosDuplicadosNumeroInt(reg, cadenaBD);                       //Define el modelo de la tabla de inconsistencias de usuarios duplicados por número de empleado internos
        definirModelosDuplicadosNumeroExt(reg, cadenaBD);                       //Define el modelo de la tabla de inconsistencias de usuarios duplicados por número de empleado externos
        definirModelosDuplicadosUserInt(reg, cadenaBD);                         //Define el modelo de la tabla de inconsistencias de usuarios duplicados por UserID internos
        definirModelosDuplicadosUserExt(reg, cadenaBD);                         //Define el modelo de la tabla de inconsistencias de usuarios duplicados por UserID externos
                 
    }
    
    
    void definirModelosBajasInt(int reg, String cadenaBD)
    {
        DefaultTableModel modeloInactividadExt = new DefaultTableModel();       //Define el modelo de la tabla de inconsistencias de usuarios internos reportados como baja
        Object[] registro = new Object[14];                                     //Crea un arreglo para recibir los elementos de cada renglon
        
        
        try
        {
            MonAD.Conexion2 conLocal = new MonAD.Conexion2();         //Crea la conexión
            conLocal.AbrirLocal(cadenaBD);                                      //Abre la conexión con la base de datos local
            ExecQuery EjecutaLo = new ExecQuery();                              //Crea el objeto de ejecución
            bajaInt = EjecutaLo.Cons(conLocal.conexion, MonAD.Consultas.ResultadosBajasInt(reg));  //Ejecuta la consulta de usuarios internos dados de baja o no registrados en nómina
//            System.out.println(monitoreos.Consultas.ResultadosBajasInt(reg));   
            if(bajaInt.next())                                                  //Valida que el resultado no esté vacío
            {
                
                modeloInactividadExt.addColumn("Agregar");                      //Agrega las columnas necesarias
                modeloInactividadExt.addColumn("Número de empleado");
                modeloInactividadExt.addColumn("User ID");
                modeloInactividadExt.addColumn("Nombre");
                modeloInactividadExt.addColumn("Fecha de último acceso");
                modeloInactividadExt.addColumn("Puesto");
                modeloInactividadExt.addColumn("Gerencia");
                modeloInactividadExt.addColumn("ID nombre");
                modeloInactividadExt.addColumn("Fecha de baja");
                modeloInactividadExt.addColumn("Estatus");
                bajaInt.beforeFirst();                                          //Dado que se avanzó una posición al validar si estaba vacío se regresa a la posición inicial
                while(bajaInt.next())                                           //Recorre los registros del resultado
                {
                    for(int k=1; k<11; k++)                                     //Recorre todos los elementos del registro
                    {
                        if(k==1)                                                //Valida si es a primer posición del recorrido para almacenar el valor TRUE predeterminado para el checkBox
                        {
                            registro[k-1]=Boolean.TRUE;
                        }
                        else                                                    //Si no es el primer registro va guardando cada uno de los datos del registro en la posición correspondiente del arreglo
                        {
                            registro[k-1]=bajaInt.getString(k-1);
                            if(k==5 && registro[k-1]==null)                     //Elimina problemas con campos de fecha nulos
                            {
                                registro[k-1] = "";
                            }
                            
                        }
                    }
//                    for(int i=0; i<10; i++)
//                    {
//                        System.out.println(registro[i]);
//                    }
                    modeloInactividadExt.addRow(registro);                      //Agrega el arreglo como un nuevo renglon al modelo de la tabla
                }
                tablaBajasInt.setModel(modeloInactividadExt);                   //Establece el modelo creado en la tabla de incidencais
                tablaBajasInt.setAutoResizeMode(AUTO_RESIZE_OFF);               //Se desabilita el ajuste automatico de ancho de columnas para establecerlo manualmente
                tablaBajasInt.getColumnModel().getColumn(0).setCellEditor(new Clase_CellEditor());      //Se hace uso de editor y renderizador de columnas
                tablaBajasInt.getColumnModel().getColumn(0).setCellRenderer(new Clase_CellRender());

                TableColumn CAgregar = tablaBajasInt.getColumn("Agregar");      //Se manda a llamar a la columna paramodificarla
                CAgregar.setPreferredWidth(55);                                 //Se define el ancho de la columna
                TableColumn CNumEmp = tablaBajasInt.getColumn("Número de empleado");    //Se manda a llamar a la columna paramodificarla
                CNumEmp.setPreferredWidth(140);                                 //Se define el ancho de la columna
                TableColumn CUserID = tablaBajasInt.getColumn("User ID");       //Se manda a llamar a la columna paramodificarla
                CUserID.setPreferredWidth(70);                                  //Se define el ancho de la columna
                TableColumn CNombre = tablaBajasInt.getColumn("Nombre");        //Se manda a llamar a la columna paramodificarla
                CNombre.setPreferredWidth(300);                                 //Se define el ancho de la columna
                TableColumn CFecha = tablaBajasInt.getColumn("Fecha de último acceso");     //Se manda a llamar a la columna paramodificarla
                CFecha.setPreferredWidth(160);                                  //Se define el ancho de la columna
                TableColumn CPuesto = tablaBajasInt.getColumn("Puesto");        //Se manda a llamar a la columna paramodificarla
                CPuesto.setPreferredWidth(300);                                 //Se define el ancho de la columna
                TableColumn CGerencia = tablaBajasInt.getColumn("Gerencia");    //Se manda a llamar a la columna paramodificarla
                CGerencia.setPreferredWidth(300);                               //Se define el ancho de la columna
                TableColumn CIDNombre = tablaBajasInt.getColumn("ID nombre");   //Se manda a llamar a la columna paramodificarla
                CIDNombre.setPreferredWidth(300);                               //Se define el ancho de la columna
                TableColumn CFechaBaja = tablaBajasInt.getColumn("Fecha de baja");      //Se manda a llamar a la columna paramodificarla
                CFechaBaja.setPreferredWidth(120);                              //Se define el ancho de la columna
                TableColumn CEstatus = tablaBajasInt.getColumn("Estatus");      //Se manda a llamar a la columna paramodificarla
                CEstatus.setPreferredWidth(160);                                //Se define el ancho de la columna
            }
            else                                                                //En caso de no tener resultados se crea una columna con la leyenda "No se encontraron inconsistencias"
            {
                modeloInactividadExt.addColumn("No se encontraron inconsistencias");
                tablaBajasInt.setModel(modeloInactividadExt);
            }
            conLocal.Cerrar();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    
    void definirModelosBajasExt(int reg, String cadenaBD)
    {
        DefaultTableModel modeloBajasExt = new DefaultTableModel();
        Object[] registro = new Object[14];                                     //Crea un arreglo para recibir los elementos de cada renglon
        
        
        try
        {
            MonAD.Conexion2 conLocal = new MonAD.Conexion2();         //Crea la conexión
            conLocal.AbrirLocal(cadenaBD);                                      //Abre la conexión con la base de datos local
            ExecQuery EjecutaLo = new ExecQuery();                              //Crea el objeto de ejecución
            bajasExt = EjecutaLo.Cons(conLocal.conexion, MonAD.Consultas.ResultadosBajasExt(reg)); //Ejecuta la consulta de usuarios externos dados de baja o no registrados en nómina
            
            if(bajasExt.next())                                                 //Valida que el resultado no esté vacío
            {
                bajasExt.beforeFirst();                                         //Dado que se avanzó una posición al validar si estaba vacío se regresa a la posición inicial
                modeloBajasExt.addColumn("Agregar");                            //Agrega las columnas necesarias
                modeloBajasExt.addColumn("Número de empleado");
                modeloBajasExt.addColumn("User ID");
                modeloBajasExt.addColumn("Nombre");
                modeloBajasExt.addColumn("Fecha de último acceso");
                modeloBajasExt.addColumn("Puesto");
                modeloBajasExt.addColumn("Gerencia");
                modeloBajasExt.addColumn("ID nombre");
                modeloBajasExt.addColumn("Fecha de baja");
                modeloBajasExt.addColumn("Estatus");
                modeloBajasExt.addColumn("ID User ID");
                
               
                
                while(bajasExt.next())                                          //Recorre los registros del resultado
                {
                    for(int k=1; k<12; k++)                                     //Recorre todos los elementos del registro
                    {
                        if(k==1)                                                //Valida si es a primer posición del recorrido para almacenar el valor TRUE predeterminado para el checkBox
                        {
                            registro[k-1]=Boolean.TRUE;
                        }
                        else                                                    //Si no es el primer registro va guardando cada uno de los datos del registro en la posición correspondiente del arreglo
                        {
                            registro[k-1]=bajasExt.getString(k-1);
                        }
                    }
                    modeloBajasExt.addRow(registro);                            //Agrega el arreglo como un nuevo renglon al modelo de la tabla
                }
                tablaBajasExt.setModel(modeloBajasExt);                         //Establece el modelo creado en la tabla de incidencais
                tablaBajasExt.setAutoResizeMode(AUTO_RESIZE_OFF);               //Se desabilita el ajuste automatico de ancho de columnas para establecerlo manualmente
                tablaBajasExt.getColumnModel().getColumn(0).setCellEditor(new Clase_CellEditor());      //Se hace uso de editor y renderizador de columnas
                tablaBajasExt.getColumnModel().getColumn(0).setCellRenderer(new Clase_CellRender());

                TableColumn CAgregar = tablaBajasExt.getColumn("Agregar");      //Se manda a llamar a la columna para modificarla
                CAgregar.setPreferredWidth(55);                                 //Se define el ancho de la columna
                TableColumn CNumEmp = tablaBajasExt.getColumn("Número de empleado");    //Se manda a llamar a la columna para modificarla
                CNumEmp.setPreferredWidth(140);                                 //Se define el ancho de la columna
                TableColumn CUserID = tablaBajasExt.getColumn("User ID");       //Se manda a llamar a la columna para modificarla
                CUserID.setPreferredWidth(70);                                  //Se define el ancho de la columna
                TableColumn CNombre = tablaBajasExt.getColumn("Nombre");        //Se manda a llamar a la columna para modificarla
                CNombre.setPreferredWidth(360);                                 //Se define el ancho de la columna
                TableColumn CFecha = tablaBajasExt.getColumn("Fecha de último acceso");     //Se manda a llamar a la columna para modificarla
                CFecha.setPreferredWidth(160);                                  //Se define el ancho de la columna
                TableColumn CRegion = tablaBajasExt.getColumn("Puesto");        //Se manda a llamar a la columna para modificarla
                CRegion.setPreferredWidth(170);                                 //Se define el ancho de la columna
                TableColumn CIP = tablaBajasExt.getColumn("Gerencia");          //Se manda a llamar a la columna para modificarla
                CIP.setPreferredWidth(220);                                     //Se define el ancho de la columna
                TableColumn CIDPerfil = tablaBajasExt.getColumn("ID nombre");   //Se manda a llamar a la columna para modificarla
                CIDPerfil.setPreferredWidth(360);                               //Se define el ancho de la columna
                TableColumn CPerfil = tablaBajasExt.getColumn("Fecha de baja"); //Se manda a llamar a la columna para modificarla
                CPerfil.setPreferredWidth(160);                                 //Se define el ancho de la columna
                TableColumn CUsuario = tablaBajasExt.getColumn("ID User ID");   //Se manda a llamar a la columna para modificarla
                CUsuario.setPreferredWidth(70);                                 //Se define el ancho de la columna
                TableColumn CEstatus = tablaBajasExt.getColumn("Estatus");      //Se manda a llamar a la columna para modificarla
                CEstatus.setPreferredWidth(100);                                //Se define el ancho de la columna
            }
            else                                                                //En caso de no tener resultados se crea una columna con la leyenda "No se encontraron inconsistencias"
            {
                modeloBajasExt.addColumn("No se encontraron inconsistencias");
                tablaBajasExt.setModel(modeloBajasExt);
            }
            conLocal.Cerrar();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    
    void definirModelosUsrInt(int reg, String cadenaBD)
    {
        DefaultTableModel modeloUsrInt = new DefaultTableModel();
        Object[] registro = new Object[14];                                     //Crea un arreglo para recibir los elementos de cada renglon
        
        
        try
        {
            MonAD.Conexion2 conLocal = new MonAD.Conexion2();         //Crea la conexión
            conLocal.AbrirLocal(cadenaBD);                                      //Abre la conexión con la base de datos local
            ExecQuery EjecutaLo = new ExecQuery();                              //Crea el objeto de ejecución
            usrInt = EjecutaLo.Cons(conLocal.conexion, MonAD.Consultas.ResultadosUsrInt(reg));     //Ejecuta la consulta de usuarios internos con UserID incorrecto
//            System.out.println(monitoreos.Consultas.ResultadosUsrInt(reg));
            if(usrInt.next())                                                   //Valida que el resultado no esté vacío
            {
                
                modeloUsrInt.addColumn("Agregar");                              //Agrega las columnas necesarias
                modeloUsrInt.addColumn("Número de empleado");
                modeloUsrInt.addColumn("Nombre");
                modeloUsrInt.addColumn("User ID");
                modeloUsrInt.addColumn("User ID correcto");
                modeloUsrInt.addColumn("Fecha de último acceso");
                modeloUsrInt.addColumn("ID nombre");
                modeloUsrInt.addColumn("Puesto");
                modeloUsrInt.addColumn("Gerencia");
                modeloUsrInt.addColumn("Estatus");
                usrInt.beforeFirst();                                           //Dado que se avanzó una posición al validar si estaba vacío se regresa a la posición inicial
                while(usrInt.next())                                            //Recorre los registros del resultado
                {
                    for(int k=1; k<11; k++)                                     //Recorre todos los elementos del registro
                    {
                        if(k==1)                                                //Valida si es a primer posición del recorrido para almacenar el valor TRUE predeterminado para el checkBox
                        {
                            registro[k-1]=Boolean.TRUE;
                        }
                        else                                                    //Si no es el primer registro va guardando cada uno de los datos del registro en la posición correspondiente del arreglo
                        {
                            registro[k-1]=usrInt.getString(k-1);
                            if(k==5 && registro[k-1]==null)                     //Elimina problemas con campos de fecha nulos
                            {
                                registro[k-1] = "";
                            }
                            
                        }
                    }
//                    for(int i=0; i<10; i++)
//                    {
//                        System.out.println(registro[i]);
//                    }
                    modeloUsrInt.addRow(registro);                              //Agrega el arreglo como un nuevo renglon al modelo de la tabla
                }
                tablausrInt.setModel(modeloUsrInt);                             //Establece el modelo creado en la tabla de incidencais
                tablausrInt.setAutoResizeMode(AUTO_RESIZE_OFF);                 //Se desabilita el ajuste automatico de ancho de columnas para establecerlo manualmente
                tablausrInt.getColumnModel().getColumn(0).setCellEditor(new Clase_CellEditor());        //Se hace uso de editor y renderizador de columnas
                tablausrInt.getColumnModel().getColumn(0).setCellRenderer(new Clase_CellRender());

                TableColumn CAgregar = tablausrInt.getColumn("Agregar");        //Se manda a llamar a la columna para modificarla
                CAgregar.setPreferredWidth(55);                                 //Se define el ancho de la columna
                TableColumn CNumEmp = tablausrInt.getColumn("Número de empleado");      //Se manda a llamar a la columna para modificarla
                CNumEmp.setPreferredWidth(140);                                 //Se define el ancho de la columna
                TableColumn CUserID = tablausrInt.getColumn("User ID");         //Se manda a llamar a la columna para modificarla
                CUserID.setPreferredWidth(70);                                  //Se define el ancho de la columna
                TableColumn CNombre = tablausrInt.getColumn("Nombre");          //Se manda a llamar a la columna para modificarla
                CNombre.setPreferredWidth(300);                                 //Se define el ancho de la columna
                TableColumn CFecha = tablausrInt.getColumn("Fecha de último acceso");   //Se manda a llamar a la columna para modificarla
                CFecha.setPreferredWidth(160);                                  //Se define el ancho de la columna
                TableColumn CPuesto = tablausrInt.getColumn("Puesto");          //Se manda a llamar a la columna para modificarla
                CPuesto.setPreferredWidth(300);                                 //Se define el ancho de la columna
                TableColumn CGerencia = tablausrInt.getColumn("Gerencia");      //Se manda a llamar a la columna para modificarla
                CGerencia.setPreferredWidth(300);                               //Se define el ancho de la columna
                TableColumn CIDNombre = tablausrInt.getColumn("ID nombre");     //Se manda a llamar a la columna para modificarla
                CIDNombre.setPreferredWidth(300);                               //Se define el ancho de la columna
                TableColumn CFechaBaja = tablausrInt.getColumn("User ID correcto");     //Se manda a llamar a la columna para modificarla
                CFechaBaja.setPreferredWidth(90);                               //Se define el ancho de la columna
                TableColumn CEstatus = tablausrInt.getColumn("Estatus");        //Se manda a llamar a la columna para modificarla
                CEstatus.setPreferredWidth(180);                                //Se define el ancho de la columna
            }
            else                                                                //En caso de no tener resultados se crea una columna con la leyenda "No se encontraron inconsistencias"
            {
                modeloUsrInt.addColumn("No se encontraron inconsistencias");
                tablausrInt.setModel(modeloUsrInt);
            }
            conLocal.Cerrar();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    void definirModelosUsrExt(int reg, String cadenaBD)
    {
        DefaultTableModel modeloUsrExt = new DefaultTableModel();
        Object[] registro = new Object[14];                                     //Crea un arreglo para recibir los elementos de cada renglon
        
        
        try
        {
            MonAD.Conexion2 conLocal = new MonAD.Conexion2();         //Crea la conexión
            conLocal.AbrirLocal(cadenaBD);                                      //Abre la conexión con la base de datos local
            ExecQuery EjecutaLo = new ExecQuery();                              //Crea el objeto de ejecución
            usrExt = EjecutaLo.Cons(conLocal.conexion, MonAD.Consultas.ResultadosUsrExt(reg));     //Ejecuta la consulta de usuarios externos con userID incorrecto
//            System.out.println(monitoreos.Consultas.ResultadosUsrExt(reg));
            if(usrExt.next())                                                   //Valida que el resultado no esté vacío
            {
                
                modeloUsrExt.addColumn("Agregar");                              //Agrega las columnas necesarias
                modeloUsrExt.addColumn("Número de empleado");
                modeloUsrExt.addColumn("Nombre");
                modeloUsrExt.addColumn("User ID");
                modeloUsrExt.addColumn("User ID correcto");
                modeloUsrExt.addColumn("Fecha de último acceso");
                modeloUsrExt.addColumn("ID nombre");
                modeloUsrExt.addColumn("Puesto");
                modeloUsrExt.addColumn("Gerencia");
                modeloUsrExt.addColumn("Estatus");
                usrExt.beforeFirst();                                           //Dado que se avanzó una posición al validar si estaba vacío se regresa a la posición inicial
                while(usrExt.next())                                            //Recorre los registros del resultado
                {
                    for(int k=1; k<11; k++)                                     //Recorre todos los elementos del registro
                    {
                        if(k==1)                                                //Valida si es a primer posición del recorrido para almacenar el valor TRUE predeterminado para el checkBox
                        {
                            registro[k-1]=Boolean.TRUE;
                        }
                        else                                                    //Si no es el primer registro va guardando cada uno de los datos del registro en la posición correspondiente del arreglo
                        {
                            registro[k-1]=usrExt.getString(k-1);
                            if(k==5 && registro[k-1]==null)                     //Elimina problemas con campos de fecha nulos
                            {
                                registro[k-1] = "";
                            }
                            
                        }
                    }
//                    for(int i=0; i<10; i++)
//                    {
//                        System.out.println(registro[i]);
//                    }
                    modeloUsrExt.addRow(registro);                              //Agrega el arreglo como un nuevo renglon al modelo de la tabla
                }
                tablausrExt.setModel(modeloUsrExt);                             //Establece el modelo creado en la tabla de incidencais
                tablausrExt.setAutoResizeMode(AUTO_RESIZE_OFF);                 //Se desabilita el ajuste automatico de ancho de columnas para establecerlo manualmente
                tablausrExt.getColumnModel().getColumn(0).setCellEditor(new Clase_CellEditor());        //Se hace uso de editor y renderizador de columnas
                tablausrExt.getColumnModel().getColumn(0).setCellRenderer(new Clase_CellRender());

                TableColumn CAgregar = tablausrExt.getColumn("Agregar");        //Se manda a llamar a la columna para modificarla
                CAgregar.setPreferredWidth(55);                                 //Se define el ancho de la columna
                TableColumn CNumEmp = tablausrExt.getColumn("Número de empleado");  //Se manda a llamar a la columna para modificarla
                CNumEmp.setPreferredWidth(140);                                 //Se define el ancho de la columna
                TableColumn CUserID = tablausrExt.getColumn("User ID");         //Se manda a llamar a la columna para modificarla
                CUserID.setPreferredWidth(70);                                  //Se define el ancho de la columna
                TableColumn CNombre = tablausrExt.getColumn("Nombre");          //Se manda a llamar a la columna para modificarla
                CNombre.setPreferredWidth(300);                                 //Se define el ancho de la columna
                TableColumn CFecha = tablausrExt.getColumn("Fecha de último acceso");   //Se manda a llamar a la columna para modificarla
                CFecha.setPreferredWidth(160);                                  //Se define el ancho de la columna
                TableColumn CPuesto = tablausrExt.getColumn("Puesto");          //Se manda a llamar a la columna para modificarla
                CPuesto.setPreferredWidth(300);                                 //Se define el ancho de la columna
                TableColumn CGerencia = tablausrExt.getColumn("Gerencia");      //Se manda a llamar a la columna para modificarla
                CGerencia.setPreferredWidth(300);                               //Se define el ancho de la columna
                TableColumn CIDNombre = tablausrExt.getColumn("ID nombre");     //Se manda a llamar a la columna para modificarla
                CIDNombre.setPreferredWidth(300);                               //Se define el ancho de la columna
                TableColumn CFechaBaja = tablausrExt.getColumn("User ID correcto");     //Se manda a llamar a la columna para modificarla
                CFechaBaja.setPreferredWidth(90);                               //Se define el ancho de la columna
                TableColumn CEstatus = tablausrExt.getColumn("Estatus");        //Se manda a llamar a la columna para modificarla
                CEstatus.setPreferredWidth(180);                                //Se define el ancho de la columna
            }
            else                                                                //En caso de no tener resultados se crea una columna con la leyenda "No se encontraron inconsistencias"
            {
                modeloUsrExt.addColumn("No se encontraron inconsistencias");
                tablausrExt.setModel(modeloUsrExt);
            }
            conLocal.Cerrar();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    
    void definirModelosInactividadInt(int reg, String cadenaBD)
    {
        DefaultTableModel modeloinacInt = new DefaultTableModel();
        Object[] registro = new Object[14];                                     //Crea un arreglo para recibir los elementos de cada renglon
        
        
        try
        {
            MonAD.Conexion2 conLocal = new MonAD.Conexion2();         //Crea la conexión
            conLocal.AbrirLocal(cadenaBD);                                      //Abre la conexión con la base de datos local
            ExecQuery EjecutaLo = new ExecQuery();                              //Crea el objeto de ejecución
            usrExt = EjecutaLo.Cons(conLocal.conexion, MonAD.Consultas.ResultadosInacInt(reg));        //Ejecuta la consulta de usuarios internos con inactividad
//            System.out.println(monitoreos.Consultas.ResultadosInacInt(reg));
            if(usrExt.next())                                                   //Valida que el resultado no esté vacío
            {
                
                modeloinacInt.addColumn("Agregar");                             //Agrega las columnas necesarias
                modeloinacInt.addColumn("Número de empleado");
                modeloinacInt.addColumn("User ID");
                modeloinacInt.addColumn("Nombre");
                modeloinacInt.addColumn("Fecha de último acceso");
                modeloinacInt.addColumn("ID nombre");
                modeloinacInt.addColumn("Puesto");
                modeloinacInt.addColumn("Gerencia");
                modeloinacInt.addColumn("Estatus");
                usrExt.beforeFirst();                                           //Dado que se avanzó una posición al validar si estaba vacío se regresa a la posición inicial
                while(usrExt.next())                                            //Recorre los registros del resultado
                {
                    for(int k=1; k<10; k++)                                     //Recorre todos los elementos del registro
                    {
                        if(k==1)                                                //Valida si es a primer posición del recorrido para almacenar el valor TRUE predeterminado para el checkBox
                        {
                            registro[k-1]=Boolean.TRUE;
                        }
                        else                                                    //Si no es el primer registro va guardando cada uno de los datos del registro en la posición correspondiente del arreglo
                        {
                            registro[k-1]=usrExt.getString(k-1);
                            if(k==5 && registro[k-1]==null)                     //Elimina problemas con campos de fecha nulos
                            {
                                registro[k-1] = "";
                            }
                            
                        }
                    }
//                    for(int i=0; i<10; i++)
//                    {
//                        System.out.println(registro[i]);
//                    }
                    modeloinacInt.addRow(registro);                             //Agrega el arreglo como un nuevo renglon al modelo de la tabla
                }
                tablainactividadInt.setModel(modeloinacInt);                    //Establece el modelo creado en la tabla de incidencais
                tablainactividadInt.setAutoResizeMode(AUTO_RESIZE_OFF);         //Se desabilita el ajuste automatico de ancho de columnas para establecerlo manualmente
                tablainactividadInt.getColumnModel().getColumn(0).setCellEditor(new Clase_CellEditor());        //Se hace uso de editor y renderizador de columnas
                tablainactividadInt.getColumnModel().getColumn(0).setCellRenderer(new Clase_CellRender());

                TableColumn CAgregar = tablainactividadInt.getColumn("Agregar");    //Se manda a llamar a la columna para modificarla
                CAgregar.setPreferredWidth(55);                                 //Se define el ancho de la columna
                TableColumn CNumEmp = tablainactividadInt.getColumn("Número de empleado");  //Se manda a llamar a la columna para modificarla
                CNumEmp.setPreferredWidth(140);                                 //Se define el ancho de la columna
                TableColumn CUserID = tablainactividadInt.getColumn("User ID");     //Se manda a llamar a la columna para modificarla
                CUserID.setPreferredWidth(70);                                  //Se define el ancho de la columna
                TableColumn CNombre = tablainactividadInt.getColumn("Nombre");  //Se manda a llamar a la columna para modificarla
                CNombre.setPreferredWidth(300);                                 //Se define el ancho de la columna
                TableColumn CFecha = tablainactividadInt.getColumn("Fecha de último acceso");   //Se manda a llamar a la columna para modificarla
                CFecha.setPreferredWidth(160);                                  //Se define el ancho de la columna
                TableColumn CPuesto = tablainactividadInt.getColumn("Puesto");  //Se manda a llamar a la columna para modificarla
                CPuesto.setPreferredWidth(300);                                 //Se define el ancho de la columna
                TableColumn CGerencia = tablainactividadInt.getColumn("Gerencia");  //Se manda a llamar a la columna para modificarla
                CGerencia.setPreferredWidth(300);                               //Se define el ancho de la columna
                TableColumn CIDNombre = tablainactividadInt.getColumn("ID nombre"); //Se manda a llamar a la columna para modificarla
                CIDNombre.setPreferredWidth(300);                               //Se define el ancho de la columna
                TableColumn CEstatus = tablainactividadInt.getColumn("Estatus");    //Se manda a llamar a la columna para modificarla
                CEstatus.setPreferredWidth(180);                                //Se define el ancho de la columna
            }
            else                                                                //En caso de no tener resultados se crea una columna con la leyenda "No se encontraron inconsistencias"
            {
                modeloinacInt.addColumn("No se encontraron inconsistencias");
                tablainactividadInt.setModel(modeloinacInt);
            }
            conLocal.Cerrar();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    
    void definirModelosInactividadExt(int reg, String cadenaBD)
    {
        DefaultTableModel modeloinacExt = new DefaultTableModel();
        Object[] registro = new Object[14];                                     //Crea un arreglo para recibir los elementos de cada renglon
        
        
        try
        {
            MonAD.Conexion2 conLocal = new MonAD.Conexion2();         //Crea la conexión
            conLocal.AbrirLocal(cadenaBD);                                      //Abre la conexión con la base de datos local
            ExecQuery EjecutaLo = new ExecQuery();                              //Crea el objeto de ejecución
            inactExt = EjecutaLo.Cons(conLocal.conexion, MonAD.Consultas.ResultadosInacExt(reg));      //Ejecuta la consulta de usuarios externos con inactividad
//            System.out.println(monitoreos.Consultas.ResultadosInacExt(reg));
            if(inactExt.next())                                                 //Valida que el resultado no esté vacío
            {
                
                modeloinacExt.addColumn("Agregar");                             //Agrega las columnas necesarias
                modeloinacExt.addColumn("Número de empleado");
                modeloinacExt.addColumn("User ID");
                modeloinacExt.addColumn("Nombre");
                modeloinacExt.addColumn("Fecha de último acceso");
                modeloinacExt.addColumn("ID nombre");
                modeloinacExt.addColumn("Puesto");
                modeloinacExt.addColumn("Gerencia");
                modeloinacExt.addColumn("Estatus");
                inactExt.beforeFirst();                                         //Dado que se avanzó una posición al validar si estaba vacío se regresa a la posición inicial
                while(inactExt.next())                                          //Recorre los registros del resultado
                {
                    for(int k=1; k<10; k++)                                     //Recorre todos los elementos del registro
                    {
                        if(k==1)                                                //Valida si es a primer posición del recorrido para almacenar el valor TRUE predeterminado para el checkBox
                        {
                            registro[k-1]=Boolean.TRUE;
                        }
                        else                                                    //Si no es el primer registro va guardando cada uno de los datos del registro en la posición correspondiente del arreglo
                        {
                            registro[k-1]=inactExt.getString(k-1);
                            if(k==5 && registro[k-1]==null)                     //Elimina problemas con campos de fecha nulos
                            {
                                registro[k-1] = "";
                            }
                            
                        }
                    }
//                    for(int i=0; i<10; i++)
//                    {
//                        System.out.println(registro[i]);
//                    }
                    modeloinacExt.addRow(registro);                             //Agrega el arreglo como un nuevo renglon al modelo de la tabla
                }
                tablainactividadExt.setModel(modeloinacExt);                    //Establece el modelo creado en la tabla de incidencais
                tablainactividadExt.setAutoResizeMode(AUTO_RESIZE_OFF);         //Se desabilita el ajuste automatico de ancho de columnas para establecerlo manualmente
                tablainactividadExt.getColumnModel().getColumn(0).setCellEditor(new Clase_CellEditor());        //Se hace uso de editor y renderizador de columnas
                tablainactividadExt.getColumnModel().getColumn(0).setCellRenderer(new Clase_CellRender());

                TableColumn CAgregar = tablainactividadExt.getColumn("Agregar");    //Se manda a llamar a la columna paramodificarla
                CAgregar.setPreferredWidth(55);                                 //Se define el ancho de la columna
                TableColumn CNumEmp = tablainactividadExt.getColumn("Número de empleado");  //Se manda a llamar a la columna paramodificarla
                CNumEmp.setPreferredWidth(140);                                 //Se define el ancho de la columna
                TableColumn CUserID = tablainactividadExt.getColumn("User ID"); //Se manda a llamar a la columna paramodificarla
                CUserID.setPreferredWidth(70);                                  //Se define el ancho de la columna
                TableColumn CNombre = tablainactividadExt.getColumn("Nombre");  //Se manda a llamar a la columna paramodificarla
                CNombre.setPreferredWidth(300);                                 //Se define el ancho de la columna
                TableColumn CFecha = tablainactividadExt.getColumn("Fecha de último acceso");   //Se manda a llamar a la columna paramodificarla
                CFecha.setPreferredWidth(160);                                  //Se define el ancho de la columna
                TableColumn CPuesto = tablainactividadExt.getColumn("Puesto");  //Se manda a llamar a la columna paramodificarla
                CPuesto.setPreferredWidth(300);                                 //Se define el ancho de la columna
                TableColumn CGerencia = tablainactividadExt.getColumn("Gerencia");  //Se manda a llamar a la columna paramodificarla
                CGerencia.setPreferredWidth(300);                               //Se define el ancho de la columna
                TableColumn CIDNombre = tablainactividadExt.getColumn("ID nombre"); //Se manda a llamar a la columna paramodificarla
                CIDNombre.setPreferredWidth(300);                               //Se define el ancho de la columna
                TableColumn CEstatus = tablainactividadExt.getColumn("Estatus");    //Se manda a llamar a la columna paramodificarla
                CEstatus.setPreferredWidth(180);                                //Se define el ancho de la columna
            }
            else                                                                //En caso de no tener resultados se crea una columna con la leyenda "No se encontraron inconsistencias"
            {
                modeloinacExt.addColumn("No se encontraron inconsistencias");
                tablainactividadExt.setModel(modeloinacExt);
            }
            conLocal.Cerrar();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    
    void definirModelosDuplicadosNombreInt(int reg, String cadenaBD)
    {
        DefaultTableModel modelodupxnomInt = new DefaultTableModel();
        Object[] registro = new Object[14];                                     //Crea un arreglo para recibir los elementos de cada renglon
        
        
        try
        {
            MonAD.Conexion2 conLocal = new MonAD.Conexion2();         //Crea la conexión
            conLocal.AbrirLocal(cadenaBD);                                      //Abre la conexión con la base de datos local
            ExecQuery EjecutaLo = new ExecQuery();                              //Crea el objeto de ejecución
            dupInt = EjecutaLo.Cons(conLocal.conexion, MonAD.Consultas.ResultadosDupxnomInt(reg));     //Ejecuta la consulta de usuarios internos duplicados por nombre
//            System.out.println(monitoreos.Consultas.ResultadosInacExt(reg));
            if(dupInt.next())                                                   //Valida que el resultado no esté vacío
            {
                
                modelodupxnomInt.addColumn("Agregar");                          //Agrega las columnas necesarias
                modelodupxnomInt.addColumn("Número de empleado");
                modelodupxnomInt.addColumn("User ID");
                modelodupxnomInt.addColumn("Nombre");
                modelodupxnomInt.addColumn("Fecha de último acceso");
                modelodupxnomInt.addColumn("ID nombre");
                modelodupxnomInt.addColumn("Puesto");
                modelodupxnomInt.addColumn("Gerencia");
                modelodupxnomInt.addColumn("Estatus");
                dupInt.beforeFirst();                                           //Dado que se avanzó una posición al validar si estaba vacío se regresa a la posición inicial
                while(dupInt.next())                                            //Recorre los registros del resultado
                {
                    for(int k=1; k<10; k++)                                     //Recorre todos los elementos del registro
                    {
                        if(k==1)                                                //Valida si es a primer posición del recorrido para almacenar el valor TRUE predeterminado para el checkBox
                        {
                            registro[k-1]=Boolean.TRUE;
                        }
                        else                                                    //Si no es el primer registro va guardando cada uno de los datos del registro en la posición correspondiente del arreglo
                        {
                            registro[k-1]=dupInt.getString(k-1);
                            if(k==5 && registro[k-1]==null)                     //Elimina problemas con campos de fecha nulos
                            {
                                registro[k-1] = "";
                            }
                            
                        }
                    }
//                    for(int i=0; i<10; i++)
//                    {
//                        System.out.println(registro[i]);
//                    }
                    modelodupxnomInt.addRow(registro);                          //Agrega el arreglo como un nuevo renglon al modelo de la tabla
                }
                tabladuplicadosxnombreInt.setModel(modelodupxnomInt);           //Establece el modelo creado en la tabla de incidencais
                tabladuplicadosxnombreInt.setAutoResizeMode(AUTO_RESIZE_OFF);   //Se desabilita el ajuste automatico de ancho de columnas para establecerlo manualmente
                tabladuplicadosxnombreInt.getColumnModel().getColumn(0).setCellEditor(new Clase_CellEditor());      //Se hace uso de editor y renderizador de columnas
                tabladuplicadosxnombreInt.getColumnModel().getColumn(0).setCellRenderer(new Clase_CellRender());

                TableColumn CAgregar = tabladuplicadosxnombreInt.getColumn("Agregar");  //Se manda a llamar a la columna para modificarla
                CAgregar.setPreferredWidth(55);                                 //Se define el ancho de la columna
                TableColumn CNumEmp = tabladuplicadosxnombreInt.getColumn("Número de empleado");    //Se manda a llamar a la columna para modificarla
                CNumEmp.setPreferredWidth(140);                                 //Se define el ancho de la columna
                TableColumn CUserID = tabladuplicadosxnombreInt.getColumn("User ID");   //Se manda a llamar a la columna para modificarla
                CUserID.setPreferredWidth(70);                                  //Se define el ancho de la columna
                TableColumn CNombre = tabladuplicadosxnombreInt.getColumn("Nombre");    //Se manda a llamar a la columna para modificarla
                CNombre.setPreferredWidth(300);                                 //Se define el ancho de la columna
                TableColumn CFecha = tabladuplicadosxnombreInt.getColumn("Fecha de último acceso"); //Se manda a llamar a la columna para modificarla
                CFecha.setPreferredWidth(160);                                  //Se define el ancho de la columna
                TableColumn CPuesto = tabladuplicadosxnombreInt.getColumn("Puesto");    //Se manda a llamar a la columna para modificarla
                CPuesto.setPreferredWidth(300);                                 //Se define el ancho de la columna
                TableColumn CGerencia = tabladuplicadosxnombreInt.getColumn("Gerencia");    //Se manda a llamar a la columna para modificarla
                CGerencia.setPreferredWidth(300);                               //Se define el ancho de la columna
                TableColumn CIDNombre = tabladuplicadosxnombreInt.getColumn("ID nombre");   //Se manda a llamar a la columna para modificarla
                CIDNombre.setPreferredWidth(300);                               //Se define el ancho de la columna
                TableColumn CEstatus = tabladuplicadosxnombreInt.getColumn("Estatus");  //Se manda a llamar a la columna para modificarla
                CEstatus.setPreferredWidth(180);                                //Se define el ancho de la columna
            }
            else                                                                //En caso de no tener resultados se crea una columna con la leyenda "No se encontraron inconsistencias"
            {
                modelodupxnomInt.addColumn("No se encontraron inconsistencias");
                tabladuplicadosxnombreInt.setModel(modelodupxnomInt);
            }
            conLocal.Cerrar();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    
    void definirModelosDuplicadosNombreExt(int reg, String cadenaBD)
    {
        DefaultTableModel modelodupxnomExt = new DefaultTableModel();
        Object[] registro = new Object[14];                                     //Crea un arreglo para recibir los elementos de cada renglon
        
        
        try
        {
            MonAD.Conexion2 conLocal = new MonAD.Conexion2();         //Crea la conexión
            conLocal.AbrirLocal(cadenaBD);                                      //Abre la conexión con la base de datos local
            ExecQuery EjecutaLo = new ExecQuery();                              //Crea el objeto de ejecución
            dupExt = EjecutaLo.Cons(conLocal.conexion, MonAD.Consultas.ResultadosDupxnomExt(reg));     //Ejecuta la consulta de usuarios externos duplicados por nombre
//            System.out.println(monitoreos.Consultas.ResultadosInacExt(reg));
            if(dupExt.next())                                                   //Valida que el resultado no esté vacío
            {
                
                modelodupxnomExt.addColumn("Agregar");                          //Agrega las columnas necesarias
                modelodupxnomExt.addColumn("Número de empleado");
                modelodupxnomExt.addColumn("User ID");
                modelodupxnomExt.addColumn("Nombre");
                modelodupxnomExt.addColumn("Fecha de último acceso");
                modelodupxnomExt.addColumn("ID nombre");
                modelodupxnomExt.addColumn("Puesto");
                modelodupxnomExt.addColumn("Gerencia");
                modelodupxnomExt.addColumn("Estatus");
                dupExt.beforeFirst();                                           //Dado que se avanzó una posición al validar si estaba vacío se regresa a la posición inicial
                while(dupExt.next())                                            //Recorre los registros del resultado
                {
                    for(int k=1; k<10; k++)                                     //Recorre todos los elementos del registro
                    {
                        if(k==1)                                                //Valida si es a primer posición del recorrido para almacenar el valor TRUE predeterminado para el checkBox
                        {
                            registro[k-1]=Boolean.TRUE;
                        }
                        else                                                    //Si no es el primer registro va guardando cada uno de los datos del registro en la posición correspondiente del arreglo
                        {
                            registro[k-1]=dupExt.getString(k-1);
                            if(k==5 && registro[k-1]==null)                     //Elimina problemas con campos de fecha nulos
                            {
                                registro[k-1] = "";
                            }
                            
                        }
                    }
//                    for(int i=0; i<10; i++)
//                    {
//                        System.out.println(registro[i]);
//                    }
                    modelodupxnomExt.addRow(registro);                          //Agrega el arreglo como un nuevo renglon al modelo de la tabla
                }
                tabladuplicadosxnombreExt.setModel(modelodupxnomExt);           //Establece el modelo creado en la tabla de incidencais
                tabladuplicadosxnombreExt.setAutoResizeMode(AUTO_RESIZE_OFF);   //Se desabilita el ajuste automatico de ancho de columnas para establecerlo manualmente
                tabladuplicadosxnombreExt.getColumnModel().getColumn(0).setCellEditor(new Clase_CellEditor());      //Se hace uso de editor y renderizador de columnas
                tabladuplicadosxnombreExt.getColumnModel().getColumn(0).setCellRenderer(new Clase_CellRender());

                TableColumn CAgregar = tabladuplicadosxnombreExt.getColumn("Agregar");  //Se manda a llamar a la columna para modificarla
                CAgregar.setPreferredWidth(55);                                 //Se define el ancho de la columna                               
                TableColumn CNumEmp = tabladuplicadosxnombreExt.getColumn("Número de empleado");    //Se manda a llamar a la columna para modificarla
                CNumEmp.setPreferredWidth(140);                                 //Se define el ancho de la columna
                TableColumn CUserID = tabladuplicadosxnombreExt.getColumn("User ID");   //Se manda a llamar a la columna para modificarla
                CUserID.setPreferredWidth(70);                                  //Se define el ancho de la columna
                TableColumn CNombre = tabladuplicadosxnombreExt.getColumn("Nombre");    //Se manda a llamar a la columna para modificarla
                CNombre.setPreferredWidth(300);                                 //Se define el ancho de la columna
                TableColumn CFecha = tabladuplicadosxnombreExt.getColumn("Fecha de último acceso"); //Se manda a llamar a la columna para modificarla
                CFecha.setPreferredWidth(160);                                  //Se define el ancho de la columna
                TableColumn CPuesto = tabladuplicadosxnombreExt.getColumn("Puesto");    //Se manda a llamar a la columna para modificarla
                CPuesto.setPreferredWidth(300);                                 //Se define el ancho de la columna
                TableColumn CGerencia = tabladuplicadosxnombreExt.getColumn("Gerencia");    //Se manda a llamar a la columna para modificarla
                CGerencia.setPreferredWidth(300);                               //Se define el ancho de la columna
                TableColumn CIDNombre = tabladuplicadosxnombreExt.getColumn("ID nombre");   //Se manda a llamar a la columna para modificarla
                CIDNombre.setPreferredWidth(300);                               //Se define el ancho de la columna
                TableColumn CEstatus = tabladuplicadosxnombreExt.getColumn("Estatus");  //Se manda a llamar a la columna para modificarla
                CEstatus.setPreferredWidth(180);                                //Se define el ancho de la columna
            }
            else                                                                //En caso de no tener resultados se crea una columna con la leyenda "No se encontraron inconsistencias"
            {
                modelodupxnomExt.addColumn("No se encontraron inconsistencias");
                tabladuplicadosxnombreExt.setModel(modelodupxnomExt);
            }
            conLocal.Cerrar();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    void definirModelosDuplicadosNumeroInt(int reg, String cadenaBD)
    {
        DefaultTableModel modelodupxnumInt = new DefaultTableModel();
        Object[] registro = new Object[14];                                     //Crea un arreglo para recibir los elementos de cada renglon
        
        
        try
        {
            MonAD.Conexion2 conLocal = new MonAD.Conexion2();         //Crea la conexión
            conLocal.AbrirLocal(cadenaBD);                                      //Abre la conexión con la base de datos local
            ExecQuery EjecutaLo = new ExecQuery();                              //Crea el objeto de ejecución
            dupInt = EjecutaLo.Cons(conLocal.conexion, MonAD.Consultas.ResultadosDupxnumInt(reg));     //Ejecuta la consulta de usuarios internos duplicados por número de empleado
//            System.out.println(monitoreos.Consultas.ResultadosInacExt(reg));
            if(dupInt.next())                                                   //Valida que el resultado no esté vacío
            {
                
                modelodupxnumInt.addColumn("Agregar");                          //Agrega las columnas necesarias
                modelodupxnumInt.addColumn("Número de empleado");
                modelodupxnumInt.addColumn("User ID");
                modelodupxnumInt.addColumn("Nombre");
                modelodupxnumInt.addColumn("Fecha de último acceso");
                modelodupxnumInt.addColumn("ID nombre");
                modelodupxnumInt.addColumn("Puesto");
                modelodupxnumInt.addColumn("Gerencia");
                modelodupxnumInt.addColumn("Estatus");
                dupInt.beforeFirst();                                           //Dado que se avanzó una posición al validar si estaba vacío se regresa a la posición inicial
                while(dupInt.next())                                            //Recorre los registros del resultado
                {
                    for(int k=1; k<10; k++)                                     //Recorre todos los elementos del registro
                    {
                        if(k==1)                                                //Valida si es a primer posición del recorrido para almacenar el valor TRUE predeterminado para el checkBox
                        {
                            registro[k-1]=Boolean.TRUE;
                        }
                        else                                                    //Si no es el primer registro va guardando cada uno de los datos del registro en la posición correspondiente del arreglo
                        {
                            registro[k-1]=dupInt.getString(k-1);
                            if(k==5 && registro[k-1]==null)                     //Elimina problemas con campos de fecha nulos
                            {
                                registro[k-1] = "";
                            }
                            
                        }
                    }
//                    for(int i=0; i<10; i++)
//                    {
//                        System.out.println(registro[i]);
//                    }
                    modelodupxnumInt.addRow(registro);                          //Agrega el arreglo como un nuevo renglon al modelo de la tabla
                }
                tabladuplicadosxnumeroInt.setModel(modelodupxnumInt);           //Establece el modelo creado en la tabla de incidencais
                tabladuplicadosxnumeroInt.setAutoResizeMode(AUTO_RESIZE_OFF);   //Se desabilita el ajuste automatico de ancho de columnas para establecerlo manualmente
                tabladuplicadosxnumeroInt.getColumnModel().getColumn(0).setCellEditor(new Clase_CellEditor());      //Se hace uso de editor y renderizador de columnas
                tabladuplicadosxnumeroInt.getColumnModel().getColumn(0).setCellRenderer(new Clase_CellRender());

                TableColumn CAgregar = tabladuplicadosxnumeroInt.getColumn("Agregar");  //Se manda a llamar a la columna paramodificarla
                CAgregar.setPreferredWidth(55);                                 //Se define el ancho de la columna
                TableColumn CNumEmp = tabladuplicadosxnumeroInt.getColumn("Número de empleado");    //Se manda a llamar a la columna paramodificarla
                CNumEmp.setPreferredWidth(140);                                 //Se define el ancho de la columna
                TableColumn CUserID = tabladuplicadosxnumeroInt.getColumn("User ID");   //Se manda a llamar a la columna paramodificarla
                CUserID.setPreferredWidth(70);                                  //Se define el ancho de la columna
                TableColumn CNombre = tabladuplicadosxnumeroInt.getColumn("Nombre");    //Se manda a llamar a la columna paramodificarla
                CNombre.setPreferredWidth(300);                                 //Se define el ancho de la columna
                TableColumn CFecha = tabladuplicadosxnumeroInt.getColumn("Fecha de último acceso"); //Se manda a llamar a la columna paramodificarla
                CFecha.setPreferredWidth(160);                                  //Se define el ancho de la columna
                TableColumn CPuesto = tabladuplicadosxnumeroInt.getColumn("Puesto");    //Se manda a llamar a la columna paramodificarla
                CPuesto.setPreferredWidth(300);                                 //Se define el ancho de la columna
                TableColumn CGerencia = tabladuplicadosxnumeroInt.getColumn("Gerencia");    //Se manda a llamar a la columna paramodificarla
                CGerencia.setPreferredWidth(300);                               //Se define el ancho de la columna
                TableColumn CIDNombre = tabladuplicadosxnumeroInt.getColumn("ID nombre");   //Se manda a llamar a la columna paramodificarla
                CIDNombre.setPreferredWidth(300);                               //Se define el ancho de la columna
                TableColumn CEstatus = tabladuplicadosxnumeroInt.getColumn("Estatus");  //Se manda a llamar a la columna paramodificarla
                CEstatus.setPreferredWidth(180);                                //Se define el ancho de la columna
            }
            else                                                                //En caso de no tener resultados se crea una columna con la leyenda "No se encontraron inconsistencias"
            {
                modelodupxnumInt.addColumn("No se encontraron inconsistencias");
                tabladuplicadosxnumeroInt.setModel(modelodupxnumInt);
            }
            conLocal.Cerrar();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    
    void definirModelosDuplicadosNumeroExt(int reg, String cadenaBD)
    {
        DefaultTableModel modelodupxnumExt = new DefaultTableModel();
        Object[] registro = new Object[14];                                     //Crea un arreglo para recibir los elementos de cada renglon
        
        
        try
        {
            MonAD.Conexion2 conLocal = new MonAD.Conexion2();         //Crea la conexión
            conLocal.AbrirLocal(cadenaBD);                                      //Abre la conexión con la base de datos local
            ExecQuery EjecutaLo = new ExecQuery();                              //Crea el objeto de ejecución
            dupExt = EjecutaLo.Cons(conLocal.conexion, MonAD.Consultas.ResultadosDupxnumExt(reg));     //Ejecuta la consulta de usuarios externos duplicados por número de empleado
//            System.out.println(monitoreos.Consultas.ResultadosInacExt(reg));
            if(dupExt.next())                                                   //Valida que el resultado no esté vacío
            {
                
                modelodupxnumExt.addColumn("Agregar");                          //Agrega las columnas necesarias
                modelodupxnumExt.addColumn("Número de empleado");
                modelodupxnumExt.addColumn("User ID");
                modelodupxnumExt.addColumn("Nombre");
                modelodupxnumExt.addColumn("Fecha de último acceso");
                modelodupxnumExt.addColumn("ID nombre");
                modelodupxnumExt.addColumn("Puesto");
                modelodupxnumExt.addColumn("Gerencia");
                modelodupxnumExt.addColumn("Estatus");
                dupExt.beforeFirst();                                           //Dado que se avanzó una posición al validar si estaba vacío se regresa a la posición inicial
                while(dupExt.next())                                            //Recorre los registros del resultado
                {
                    for(int k=1; k<10; k++)                                     //Recorre todos los elementos del registro
                    {
                        if(k==1)                                                //Valida si es a primer posición del recorrido para almacenar el valor TRUE predeterminado para el checkBox
                        {
                            registro[k-1]=Boolean.TRUE;
                        }
                        else                                                    //Si no es el primer registro va guardando cada uno de los datos del registro en la posición correspondiente del arreglo
                        {
                            registro[k-1]=dupExt.getString(k-1);
                            if(k==5 && registro[k-1]==null)                     //Elimina problemas con campos de fecha nulos
                            {
                                registro[k-1] = "";
                            }
                            
                        }
                    }
//                    for(int i=0; i<10; i++)
//                    {
//                        System.out.println(registro[i]);
//                    }
                    modelodupxnumExt.addRow(registro);                          //Agrega el arreglo como un nuevo renglon al modelo de la tabla
                }
                tabladuplicadosxnumeroExt.setModel(modelodupxnumExt);           //Establece el modelo creado en la tabla de incidencais
                tabladuplicadosxnumeroExt.setAutoResizeMode(AUTO_RESIZE_OFF);   //Se desabilita el ajuste automatico de ancho de columnas para establecerlo manualmente
                tabladuplicadosxnumeroExt.getColumnModel().getColumn(0).setCellEditor(new Clase_CellEditor());      //Se hace uso de editor y renderizador de columnas
                tabladuplicadosxnumeroExt.getColumnModel().getColumn(0).setCellRenderer(new Clase_CellRender());

                TableColumn CAgregar = tabladuplicadosxnumeroExt.getColumn("Agregar");  //Se manda a llamar a la columna para modificarla
                CAgregar.setPreferredWidth(55);                                 //Se define el ancho de la columna
                TableColumn CNumEmp = tabladuplicadosxnumeroExt.getColumn("Número de empleado");    //Se manda a llamar a la columna para modificarla
                CNumEmp.setPreferredWidth(140);                                 //Se define el ancho de la columna
                TableColumn CUserID = tabladuplicadosxnumeroExt.getColumn("User ID");   //Se manda a llamar a la columna para modificarla
                CUserID.setPreferredWidth(70);                                  //Se define el ancho de la columna
                TableColumn CNombre = tabladuplicadosxnumeroExt.getColumn("Nombre");    //Se manda a llamar a la columna para modificarla
                CNombre.setPreferredWidth(300);                                 //Se define el ancho de la columna
                TableColumn CFecha = tabladuplicadosxnumeroExt.getColumn("Fecha de último acceso");     //Se manda a llamar a la columna para modificarla
                CFecha.setPreferredWidth(160);                                  //Se define el ancho de la columna
                TableColumn CPuesto = tabladuplicadosxnumeroExt.getColumn("Puesto");    //Se manda a llamar a la columna para modificarla
                CPuesto.setPreferredWidth(300);                                 //Se define el ancho de la columna
                TableColumn CGerencia = tabladuplicadosxnumeroExt.getColumn("Gerencia");    //Se manda a llamar a la columna para modificarla
                CGerencia.setPreferredWidth(300);                               //Se define el ancho de la columna
                TableColumn CIDNombre = tabladuplicadosxnumeroExt.getColumn("ID nombre");   //Se manda a llamar a la columna para modificarla
                CIDNombre.setPreferredWidth(300);                               //Se define el ancho de la columna
                TableColumn CEstatus = tabladuplicadosxnumeroExt.getColumn("Estatus");  //Se manda a llamar a la columna para modificarla
                CEstatus.setPreferredWidth(180);                                //Se define el ancho de la columna
            }
            else                                                                //En caso de no tener resultados se crea una columna con la leyenda "No se encontraron inconsistencias"
            {
                modelodupxnumExt.addColumn("No se encontraron inconsistencias");
                tabladuplicadosxnumeroExt.setModel(modelodupxnumExt);
            }
            conLocal.Cerrar();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    void definirModelosDuplicadosUserInt(int reg, String cadenaBD)
    {
        DefaultTableModel modelodupxusrInt = new DefaultTableModel();
        Object[] registro = new Object[14];                                     //Crea un arreglo para recibir los elementos de cada renglon
        
        
        try
        {
            MonAD.Conexion2 conLocal = new MonAD.Conexion2();         //Crea la conexión
            conLocal.AbrirLocal(cadenaBD);                                      //Abre la conexión con la base de datos local
            ExecQuery EjecutaLo = new ExecQuery();                              //Crea el objeto de ejecución
            dupInt = EjecutaLo.Cons(conLocal.conexion, MonAD.Consultas.ResultadosDupxusrInt(reg));     //Ejecuta la consulta de usuarios internos duplicados por UserID
//            System.out.println(monitoreos.Consultas.ResultadosInacExt(reg));
            if(dupInt.next())                                                   //Valida que el resultado no esté vacío
            {
                
                modelodupxusrInt.addColumn("Agregar");                          //Agrega las columnas necesarias
                modelodupxusrInt.addColumn("Número de empleado");
                modelodupxusrInt.addColumn("User ID");
                modelodupxusrInt.addColumn("Nombre");
                modelodupxusrInt.addColumn("Fecha de último acceso");
                modelodupxusrInt.addColumn("ID nombre");
                modelodupxusrInt.addColumn("Puesto");
                modelodupxusrInt.addColumn("Gerencia");
                modelodupxusrInt.addColumn("Estatus");
                dupInt.beforeFirst();                                           //Dado que se avanzó una posición al validar si estaba vacío se regresa a la posición inicial
                while(dupInt.next())                                            //Recorre los registros del resultado
                {
                    for(int k=1; k<10; k++)                                     //Recorre todos los elementos del registro
                    {
                        if(k==1)                                                //Valida si es a primer posición del recorrido para almacenar el valor TRUE predeterminado para el checkBox
                        {
                            registro[k-1]=Boolean.TRUE;
                        }
                        else                                                    //Si no es el primer registro va guardando cada uno de los datos del registro en la posición correspondiente del arreglo
                        {
                            registro[k-1]=dupInt.getString(k-1);
                            if(k==5 && registro[k-1]==null)                     //Elimina problemas con campos de fecha nulos
                            {
                                registro[k-1] = "";
                            }
                            
                        }
                    }
//                    for(int i=0; i<10; i++)
//                    {
//                        System.out.println(registro[i]);
//                    }
                    modelodupxusrInt.addRow(registro);                          //Agrega el arreglo como un nuevo renglon al modelo de la tabla
                }
                tabladuplicadosuserInt.setModel(modelodupxusrInt);              //Establece el modelo creado en la tabla de incidencais
                tabladuplicadosuserInt.setAutoResizeMode(AUTO_RESIZE_OFF);      //Se desabilita el ajuste automatico de ancho de columnas para establecerlo manualmente
                tabladuplicadosuserInt.getColumnModel().getColumn(0).setCellEditor(new Clase_CellEditor());     //Se hace uso de editor y renderizador de columnas
                tabladuplicadosuserInt.getColumnModel().getColumn(0).setCellRenderer(new Clase_CellRender());

                TableColumn CAgregar = tabladuplicadosuserInt.getColumn("Agregar");     //Se manda a llamar a la columna para modificarla
                CAgregar.setPreferredWidth(55);                                 //Se define el ancho de la columna
                TableColumn CNumEmp = tabladuplicadosuserInt.getColumn("Número de empleado");   //Se manda a llamar a la columna para modificarla
                CNumEmp.setPreferredWidth(140);                                 //Se define el ancho de la columna
                TableColumn CUserID = tabladuplicadosuserInt.getColumn("User ID");  //Se manda a llamar a la columna para modificarla
                CUserID.setPreferredWidth(70);                                  //Se define el ancho de la columna
                TableColumn CNombre = tabladuplicadosuserInt.getColumn("Nombre");   //Se manda a llamar a la columna para modificarla
                CNombre.setPreferredWidth(300);                                 //Se define el ancho de la columna
                TableColumn CFecha = tabladuplicadosuserInt.getColumn("Fecha de último acceso");    //Se manda a llamar a la columna para modificarla
                CFecha.setPreferredWidth(160);                                  //Se define el ancho de la columna
                TableColumn CPuesto = tabladuplicadosuserInt.getColumn("Puesto");   //Se manda a llamar a la columna para modificarla
                CPuesto.setPreferredWidth(300);                                 //Se define el ancho de la columna
                TableColumn CGerencia = tabladuplicadosuserInt.getColumn("Gerencia");   //Se manda a llamar a la columna para modificarla
                CGerencia.setPreferredWidth(300);                               //Se define el ancho de la columna
                TableColumn CIDNombre = tabladuplicadosuserInt.getColumn("ID nombre");  //Se manda a llamar a la columna para modificarla
                CIDNombre.setPreferredWidth(300);                               //Se define el ancho de la columna
                TableColumn CEstatus = tabladuplicadosuserInt.getColumn("Estatus"); //Se manda a llamar a la columna para modificarla
                CEstatus.setPreferredWidth(180);                                //Se define el ancho de la columna
            }
            else                                                                //En caso de no tener resultados se crea una columna con la leyenda "No se encontraron inconsistencias"
            {
                modelodupxusrInt.addColumn("No se encontraron inconsistencias");
                tabladuplicadosuserInt.setModel(modelodupxusrInt);
            }
            conLocal.Cerrar();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    
    
    void definirModelosDuplicadosUserExt(int reg, String cadenaBD)
    {
        DefaultTableModel modelodupxusrExt = new DefaultTableModel();
        Object[] registro = new Object[14];                                     //Crea un arreglo para recibir los elementos de cada renglon
        
        
        try
        {
            MonAD.Conexion2 conLocal = new MonAD.Conexion2();         //Crea la conexión
            conLocal.AbrirLocal(cadenaBD);                                      //Abre la conexión con la base de datos local
            ExecQuery EjecutaLo = new ExecQuery();                              //Crea el objeto de ejecución
            dupExt = EjecutaLo.Cons(conLocal.conexion, MonAD.Consultas.ResultadosDupxusrExt(reg));     //Ejecuta la consulta de usuarios externos duplicados por UserID
//            System.out.println(monitoreos.Consultas.ResultadosInacExt(reg));
            if(dupExt.next())                                                   //Valida que el resultado no esté vacío
            {
                
                modelodupxusrExt.addColumn("Agregar");                          //Agrega las columnas necesarias
                modelodupxusrExt.addColumn("Número de empleado");
                modelodupxusrExt.addColumn("User ID");
                modelodupxusrExt.addColumn("Nombre");
                modelodupxusrExt.addColumn("Fecha de último acceso");
                modelodupxusrExt.addColumn("ID nombre");
                modelodupxusrExt.addColumn("Puesto");
                modelodupxusrExt.addColumn("Gerencia");
                modelodupxusrExt.addColumn("Estatus");
                dupExt.beforeFirst();                                           //Dado que se avanzó una posición al validar si estaba vacío se regresa a la posición inicial
                while(dupExt.next())                                            //Recorre los registros del resultado
                {
                    for(int k=1; k<10; k++)                                     //Recorre todos los elementos del registro
                    {
                        if(k==1)                                                //Valida si es a primer posición del recorrido para almacenar el valor TRUE predeterminado para el checkBox
                        {
                            registro[k-1]=Boolean.TRUE;
                        }
                        else                                                    //Si no es el primer registro va guardando cada uno de los datos del registro en la posición correspondiente del arreglo
                        {
                            registro[k-1]=dupExt.getString(k-1);
                            if(k==5 && registro[k-1]==null)                     //Elimina problemas con campos de fecha nulos
                            {
                                registro[k-1] = "";
                            }
                            
                        }
                    }
//                    for(int i=0; i<10; i++)
//                    {
//                        System.out.println(registro[i]);
//                    }
                    modelodupxusrExt.addRow(registro);                          //Agrega el arreglo como un nuevo renglon al modelo de la tabla
                }
                tabladuplicadosuserExt.setModel(modelodupxusrExt);              //Establece el modelo creado en la tabla de incidencais
                tabladuplicadosuserExt.setAutoResizeMode(AUTO_RESIZE_OFF);      //Se desabilita el ajuste automatico de ancho de columnas para establecerlo manualmente
                tabladuplicadosuserExt.getColumnModel().getColumn(0).setCellEditor(new Clase_CellEditor());     //Se hace uso de editor y renderizador de columnas
                tabladuplicadosuserExt.getColumnModel().getColumn(0).setCellRenderer(new Clase_CellRender());

                TableColumn CAgregar = tabladuplicadosuserExt.getColumn("Agregar");     //Se manda a llamar a la columna para modificarla
                CAgregar.setPreferredWidth(55);                                 //Se define el ancho de la columna
                TableColumn CNumEmp = tabladuplicadosuserExt.getColumn("Número de empleado");   //Se manda a llamar a la columna para modificarla
                CNumEmp.setPreferredWidth(140);                                 //Se define el ancho de la columna
                TableColumn CUserID = tabladuplicadosuserExt.getColumn("User ID");  //Se manda a llamar a la columna para modificarla
                CUserID.setPreferredWidth(70);                                  //Se define el ancho de la columna
                TableColumn CNombre = tabladuplicadosuserExt.getColumn("Nombre");   //Se manda a llamar a la columna para modificarla
                CNombre.setPreferredWidth(300);                                 //Se define el ancho de la columna
                TableColumn CFecha = tabladuplicadosuserExt.getColumn("Fecha de último acceso");    //Se manda a llamar a la columna para modificarla
                CFecha.setPreferredWidth(160);                                  //Se define el ancho de la columna
                TableColumn CPuesto = tabladuplicadosuserExt.getColumn("Puesto");   //Se manda a llamar a la columna para modificarla
                CPuesto.setPreferredWidth(300);                                 //Se define el ancho de la columna
                TableColumn CGerencia = tabladuplicadosuserExt.getColumn("Gerencia");   //Se manda a llamar a la columna para modificarla
                CGerencia.setPreferredWidth(300);                               //Se define el ancho de la columna
                TableColumn CIDNombre = tabladuplicadosuserExt.getColumn("ID nombre");  //Se manda a llamar a la columna para modificarla
                CIDNombre.setPreferredWidth(300);                               //Se define el ancho de la columna
                TableColumn CEstatus = tabladuplicadosuserExt.getColumn("Estatus");     //Se manda a llamar a la columna para modificarla
                CEstatus.setPreferredWidth(180);                                //Se define el ancho de la columna
            }
            else                                                                //En caso de no tener resultados se crea una columna con la leyenda "No se encontraron inconsistencias"
            {
                modelodupxusrExt.addColumn("No se encontraron inconsistencias");
                tabladuplicadosuserExt.setModel(modelodupxusrExt);
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

        jTabbedPane18 = new javax.swing.JTabbedPane();
        jPanel778 = new javax.swing.JPanel();
        jPanel779 = new javax.swing.JPanel();
        jLabel196 = new javax.swing.JLabel();
        jPanel780 = new javax.swing.JPanel();
        jScrollPane193 = new javax.swing.JScrollPane();
        tablaBajasInt = new javax.swing.JTable();
        jPanel782 = new javax.swing.JPanel();
        jPanel783 = new javax.swing.JPanel();
        jLabel197 = new javax.swing.JLabel();
        jPanel784 = new javax.swing.JPanel();
        jScrollPane194 = new javax.swing.JScrollPane();
        tablaBajasExt = new javax.swing.JTable();
        jPanel786 = new javax.swing.JPanel();
        jPanel787 = new javax.swing.JPanel();
        jLabel198 = new javax.swing.JLabel();
        jPanel788 = new javax.swing.JPanel();
        jScrollPane195 = new javax.swing.JScrollPane();
        tablainactividadInt = new javax.swing.JTable();
        jPanel790 = new javax.swing.JPanel();
        jPanel791 = new javax.swing.JPanel();
        jLabel199 = new javax.swing.JLabel();
        jPanel792 = new javax.swing.JPanel();
        jScrollPane196 = new javax.swing.JScrollPane();
        tablainactividadExt = new javax.swing.JTable();
        jPanel794 = new javax.swing.JPanel();
        jPanel795 = new javax.swing.JPanel();
        jLabel200 = new javax.swing.JLabel();
        jPanel796 = new javax.swing.JPanel();
        jScrollPane197 = new javax.swing.JScrollPane();
        tablausrInt = new javax.swing.JTable();
        jPanel798 = new javax.swing.JPanel();
        jPanel799 = new javax.swing.JPanel();
        jLabel201 = new javax.swing.JLabel();
        jPanel800 = new javax.swing.JPanel();
        jScrollPane198 = new javax.swing.JScrollPane();
        tablausrExt = new javax.swing.JTable();
        jPanel802 = new javax.swing.JPanel();
        jPanel803 = new javax.swing.JPanel();
        jLabel202 = new javax.swing.JLabel();
        jPanel804 = new javax.swing.JPanel();
        jScrollPane199 = new javax.swing.JScrollPane();
        tabladuplicadosxnombreInt = new javax.swing.JTable();
        jPanel806 = new javax.swing.JPanel();
        jPanel807 = new javax.swing.JPanel();
        jLabel203 = new javax.swing.JLabel();
        jPanel808 = new javax.swing.JPanel();
        jScrollPane200 = new javax.swing.JScrollPane();
        tabladuplicadosxnombreExt = new javax.swing.JTable();
        jPanel810 = new javax.swing.JPanel();
        jPanel811 = new javax.swing.JPanel();
        jLabel204 = new javax.swing.JLabel();
        jPanel812 = new javax.swing.JPanel();
        jScrollPane201 = new javax.swing.JScrollPane();
        tabladuplicadosxnumeroInt = new javax.swing.JTable();
        jPanel814 = new javax.swing.JPanel();
        jPanel815 = new javax.swing.JPanel();
        jLabel205 = new javax.swing.JLabel();
        jPanel816 = new javax.swing.JPanel();
        jScrollPane202 = new javax.swing.JScrollPane();
        tabladuplicadosxnumeroExt = new javax.swing.JTable();
        jPanel818 = new javax.swing.JPanel();
        jPanel819 = new javax.swing.JPanel();
        jLabel206 = new javax.swing.JLabel();
        jPanel820 = new javax.swing.JPanel();
        jScrollPane203 = new javax.swing.JScrollPane();
        tabladuplicadosuserInt = new javax.swing.JTable();
        jPanel822 = new javax.swing.JPanel();
        jPanel823 = new javax.swing.JPanel();
        jLabel207 = new javax.swing.JLabel();
        jPanel824 = new javax.swing.JPanel();
        jScrollPane204 = new javax.swing.JScrollPane();
        tabladuplicadosuserExt = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTabbedPane18.setPreferredSize(new java.awt.Dimension(1380, 850));

        jLabel196.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/telcel.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel779Layout = new javax.swing.GroupLayout(jPanel779);
        jPanel779.setLayout(jPanel779Layout);
        jPanel779Layout.setHorizontalGroup(
            jPanel779Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel779Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel196, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel779Layout.setVerticalGroup(
            jPanel779Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel779Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel196, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane193.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane193.setMinimumSize(new java.awt.Dimension(1000, 419));
        jScrollPane193.setPreferredSize(new java.awt.Dimension(1800, 419));

        tablaBajasInt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane193.setViewportView(tablaBajasInt);

        javax.swing.GroupLayout jPanel780Layout = new javax.swing.GroupLayout(jPanel780);
        jPanel780.setLayout(jPanel780Layout);
        jPanel780Layout.setHorizontalGroup(
            jPanel780Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel780Layout.createSequentialGroup()
                .addComponent(jScrollPane193, javax.swing.GroupLayout.PREFERRED_SIZE, 1365, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel780Layout.setVerticalGroup(
            jPanel780Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel780Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane193, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel778Layout = new javax.swing.GroupLayout(jPanel778);
        jPanel778.setLayout(jPanel778Layout);
        jPanel778Layout.setHorizontalGroup(
            jPanel778Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel779, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel780, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel778Layout.setVerticalGroup(
            jPanel778Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel778Layout.createSequentialGroup()
                .addComponent(jPanel779, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel780, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane18.addTab("Bajas Internos", jPanel778);

        jLabel197.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/telcel.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel783Layout = new javax.swing.GroupLayout(jPanel783);
        jPanel783.setLayout(jPanel783Layout);
        jPanel783Layout.setHorizontalGroup(
            jPanel783Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel783Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel197, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel783Layout.setVerticalGroup(
            jPanel783Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel783Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel197, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane194.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        tablaBajasExt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane194.setViewportView(tablaBajasExt);

        javax.swing.GroupLayout jPanel784Layout = new javax.swing.GroupLayout(jPanel784);
        jPanel784.setLayout(jPanel784Layout);
        jPanel784Layout.setHorizontalGroup(
            jPanel784Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane194, javax.swing.GroupLayout.DEFAULT_SIZE, 1375, Short.MAX_VALUE)
        );
        jPanel784Layout.setVerticalGroup(
            jPanel784Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel784Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane194, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel782Layout = new javax.swing.GroupLayout(jPanel782);
        jPanel782.setLayout(jPanel782Layout);
        jPanel782Layout.setHorizontalGroup(
            jPanel782Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel783, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel784, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel782Layout.setVerticalGroup(
            jPanel782Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel782Layout.createSequentialGroup()
                .addComponent(jPanel783, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel784, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane18.addTab("Bajas Externos", jPanel782);

        jLabel198.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/telcel.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel787Layout = new javax.swing.GroupLayout(jPanel787);
        jPanel787.setLayout(jPanel787Layout);
        jPanel787Layout.setHorizontalGroup(
            jPanel787Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel787Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel198, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel787Layout.setVerticalGroup(
            jPanel787Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel787Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel198, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane195.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        tablainactividadInt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane195.setViewportView(tablainactividadInt);

        javax.swing.GroupLayout jPanel788Layout = new javax.swing.GroupLayout(jPanel788);
        jPanel788.setLayout(jPanel788Layout);
        jPanel788Layout.setHorizontalGroup(
            jPanel788Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane195, javax.swing.GroupLayout.DEFAULT_SIZE, 1375, Short.MAX_VALUE)
        );
        jPanel788Layout.setVerticalGroup(
            jPanel788Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel788Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane195, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel786Layout = new javax.swing.GroupLayout(jPanel786);
        jPanel786.setLayout(jPanel786Layout);
        jPanel786Layout.setHorizontalGroup(
            jPanel786Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel787, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel788, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel786Layout.setVerticalGroup(
            jPanel786Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel786Layout.createSequentialGroup()
                .addComponent(jPanel787, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel788, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane18.addTab("Inactividad Internos", jPanel786);

        jLabel199.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/telcel.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel791Layout = new javax.swing.GroupLayout(jPanel791);
        jPanel791.setLayout(jPanel791Layout);
        jPanel791Layout.setHorizontalGroup(
            jPanel791Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel791Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel199, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel791Layout.setVerticalGroup(
            jPanel791Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel791Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel199, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane196.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        tablainactividadExt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane196.setViewportView(tablainactividadExt);

        javax.swing.GroupLayout jPanel792Layout = new javax.swing.GroupLayout(jPanel792);
        jPanel792.setLayout(jPanel792Layout);
        jPanel792Layout.setHorizontalGroup(
            jPanel792Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane196, javax.swing.GroupLayout.DEFAULT_SIZE, 1375, Short.MAX_VALUE)
        );
        jPanel792Layout.setVerticalGroup(
            jPanel792Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel792Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane196, javax.swing.GroupLayout.PREFERRED_SIZE, 546, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel790Layout = new javax.swing.GroupLayout(jPanel790);
        jPanel790.setLayout(jPanel790Layout);
        jPanel790Layout.setHorizontalGroup(
            jPanel790Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel791, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel792, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel790Layout.setVerticalGroup(
            jPanel790Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel790Layout.createSequentialGroup()
                .addComponent(jPanel791, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel792, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane18.addTab("Inactividad Externos", jPanel790);

        jLabel200.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/telcel.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel795Layout = new javax.swing.GroupLayout(jPanel795);
        jPanel795.setLayout(jPanel795Layout);
        jPanel795Layout.setHorizontalGroup(
            jPanel795Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel795Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel200, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel795Layout.setVerticalGroup(
            jPanel795Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel795Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel200, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane197.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        tablausrInt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane197.setViewportView(tablausrInt);

        javax.swing.GroupLayout jPanel796Layout = new javax.swing.GroupLayout(jPanel796);
        jPanel796.setLayout(jPanel796Layout);
        jPanel796Layout.setHorizontalGroup(
            jPanel796Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane197, javax.swing.GroupLayout.DEFAULT_SIZE, 1375, Short.MAX_VALUE)
        );
        jPanel796Layout.setVerticalGroup(
            jPanel796Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel796Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane197, javax.swing.GroupLayout.PREFERRED_SIZE, 547, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel794Layout = new javax.swing.GroupLayout(jPanel794);
        jPanel794.setLayout(jPanel794Layout);
        jPanel794Layout.setHorizontalGroup(
            jPanel794Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel795, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel796, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel794Layout.setVerticalGroup(
            jPanel794Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel794Layout.createSequentialGroup()
                .addComponent(jPanel795, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel796, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane18.addTab("User ID Incorrecto Internos", jPanel794);

        jLabel201.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/telcel.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel799Layout = new javax.swing.GroupLayout(jPanel799);
        jPanel799.setLayout(jPanel799Layout);
        jPanel799Layout.setHorizontalGroup(
            jPanel799Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel799Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel201, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel799Layout.setVerticalGroup(
            jPanel799Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel799Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel201, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane198.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        tablausrExt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane198.setViewportView(tablausrExt);

        javax.swing.GroupLayout jPanel800Layout = new javax.swing.GroupLayout(jPanel800);
        jPanel800.setLayout(jPanel800Layout);
        jPanel800Layout.setHorizontalGroup(
            jPanel800Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane198, javax.swing.GroupLayout.DEFAULT_SIZE, 1375, Short.MAX_VALUE)
        );
        jPanel800Layout.setVerticalGroup(
            jPanel800Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel800Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane198, javax.swing.GroupLayout.PREFERRED_SIZE, 547, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel798Layout = new javax.swing.GroupLayout(jPanel798);
        jPanel798.setLayout(jPanel798Layout);
        jPanel798Layout.setHorizontalGroup(
            jPanel798Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel799, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel800, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel798Layout.setVerticalGroup(
            jPanel798Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel798Layout.createSequentialGroup()
                .addComponent(jPanel799, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel800, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane18.addTab("User ID Incorrecto Externos", jPanel798);

        jLabel202.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/telcel.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel803Layout = new javax.swing.GroupLayout(jPanel803);
        jPanel803.setLayout(jPanel803Layout);
        jPanel803Layout.setHorizontalGroup(
            jPanel803Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel803Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel202, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel803Layout.setVerticalGroup(
            jPanel803Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel803Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel202, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane199.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        tabladuplicadosxnombreInt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane199.setViewportView(tabladuplicadosxnombreInt);

        javax.swing.GroupLayout jPanel804Layout = new javax.swing.GroupLayout(jPanel804);
        jPanel804.setLayout(jPanel804Layout);
        jPanel804Layout.setHorizontalGroup(
            jPanel804Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane199, javax.swing.GroupLayout.DEFAULT_SIZE, 1375, Short.MAX_VALUE)
        );
        jPanel804Layout.setVerticalGroup(
            jPanel804Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel804Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane199, javax.swing.GroupLayout.PREFERRED_SIZE, 547, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel802Layout = new javax.swing.GroupLayout(jPanel802);
        jPanel802.setLayout(jPanel802Layout);
        jPanel802Layout.setHorizontalGroup(
            jPanel802Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel803, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel804, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel802Layout.setVerticalGroup(
            jPanel802Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel802Layout.createSequentialGroup()
                .addComponent(jPanel803, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel804, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane18.addTab("Duplicados por nombre Internos", jPanel802);

        jLabel203.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/telcel.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel807Layout = new javax.swing.GroupLayout(jPanel807);
        jPanel807.setLayout(jPanel807Layout);
        jPanel807Layout.setHorizontalGroup(
            jPanel807Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel807Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel203, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel807Layout.setVerticalGroup(
            jPanel807Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel807Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel203, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane200.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        tabladuplicadosxnombreExt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane200.setViewportView(tabladuplicadosxnombreExt);

        javax.swing.GroupLayout jPanel808Layout = new javax.swing.GroupLayout(jPanel808);
        jPanel808.setLayout(jPanel808Layout);
        jPanel808Layout.setHorizontalGroup(
            jPanel808Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane200, javax.swing.GroupLayout.DEFAULT_SIZE, 1375, Short.MAX_VALUE)
        );
        jPanel808Layout.setVerticalGroup(
            jPanel808Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel808Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane200, javax.swing.GroupLayout.PREFERRED_SIZE, 544, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel806Layout = new javax.swing.GroupLayout(jPanel806);
        jPanel806.setLayout(jPanel806Layout);
        jPanel806Layout.setHorizontalGroup(
            jPanel806Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel807, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel808, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel806Layout.setVerticalGroup(
            jPanel806Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel806Layout.createSequentialGroup()
                .addComponent(jPanel807, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel808, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane18.addTab("Duplicados por nombre Externos", jPanel806);

        jLabel204.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/telcel.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel811Layout = new javax.swing.GroupLayout(jPanel811);
        jPanel811.setLayout(jPanel811Layout);
        jPanel811Layout.setHorizontalGroup(
            jPanel811Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel811Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel204, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel811Layout.setVerticalGroup(
            jPanel811Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel811Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel204, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane201.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        tabladuplicadosxnumeroInt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane201.setViewportView(tabladuplicadosxnumeroInt);

        javax.swing.GroupLayout jPanel812Layout = new javax.swing.GroupLayout(jPanel812);
        jPanel812.setLayout(jPanel812Layout);
        jPanel812Layout.setHorizontalGroup(
            jPanel812Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane201, javax.swing.GroupLayout.DEFAULT_SIZE, 1375, Short.MAX_VALUE)
        );
        jPanel812Layout.setVerticalGroup(
            jPanel812Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel812Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane201, javax.swing.GroupLayout.PREFERRED_SIZE, 544, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel810Layout = new javax.swing.GroupLayout(jPanel810);
        jPanel810.setLayout(jPanel810Layout);
        jPanel810Layout.setHorizontalGroup(
            jPanel810Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel811, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel812, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel810Layout.setVerticalGroup(
            jPanel810Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel810Layout.createSequentialGroup()
                .addComponent(jPanel811, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel812, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane18.addTab("Duplicados por número de empleado Internos", jPanel810);

        jLabel205.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/telcel.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel815Layout = new javax.swing.GroupLayout(jPanel815);
        jPanel815.setLayout(jPanel815Layout);
        jPanel815Layout.setHorizontalGroup(
            jPanel815Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel815Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel205, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel815Layout.setVerticalGroup(
            jPanel815Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel815Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel205, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane202.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        tabladuplicadosxnumeroExt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane202.setViewportView(tabladuplicadosxnumeroExt);

        javax.swing.GroupLayout jPanel816Layout = new javax.swing.GroupLayout(jPanel816);
        jPanel816.setLayout(jPanel816Layout);
        jPanel816Layout.setHorizontalGroup(
            jPanel816Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane202, javax.swing.GroupLayout.DEFAULT_SIZE, 1375, Short.MAX_VALUE)
        );
        jPanel816Layout.setVerticalGroup(
            jPanel816Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel816Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane202, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel814Layout = new javax.swing.GroupLayout(jPanel814);
        jPanel814.setLayout(jPanel814Layout);
        jPanel814Layout.setHorizontalGroup(
            jPanel814Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel815, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel816, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel814Layout.setVerticalGroup(
            jPanel814Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel814Layout.createSequentialGroup()
                .addComponent(jPanel815, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel816, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane18.addTab("Duplicados por número de empleado Externos", jPanel814);

        jLabel206.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/telcel.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel819Layout = new javax.swing.GroupLayout(jPanel819);
        jPanel819.setLayout(jPanel819Layout);
        jPanel819Layout.setHorizontalGroup(
            jPanel819Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel819Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel206, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel819Layout.setVerticalGroup(
            jPanel819Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel819Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel206, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane203.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        tabladuplicadosuserInt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane203.setViewportView(tabladuplicadosuserInt);

        javax.swing.GroupLayout jPanel820Layout = new javax.swing.GroupLayout(jPanel820);
        jPanel820.setLayout(jPanel820Layout);
        jPanel820Layout.setHorizontalGroup(
            jPanel820Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane203, javax.swing.GroupLayout.DEFAULT_SIZE, 1375, Short.MAX_VALUE)
        );
        jPanel820Layout.setVerticalGroup(
            jPanel820Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel820Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane203, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel818Layout = new javax.swing.GroupLayout(jPanel818);
        jPanel818.setLayout(jPanel818Layout);
        jPanel818Layout.setHorizontalGroup(
            jPanel818Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel819, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel820, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel818Layout.setVerticalGroup(
            jPanel818Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel818Layout.createSequentialGroup()
                .addComponent(jPanel819, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel820, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane18.addTab("Duplicados por UserID Internos", jPanel818);

        jLabel207.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/telcel.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel823Layout = new javax.swing.GroupLayout(jPanel823);
        jPanel823.setLayout(jPanel823Layout);
        jPanel823Layout.setHorizontalGroup(
            jPanel823Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel823Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel207, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel823Layout.setVerticalGroup(
            jPanel823Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel823Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel207, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane204.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        tabladuplicadosuserExt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane204.setViewportView(tabladuplicadosuserExt);

        javax.swing.GroupLayout jPanel824Layout = new javax.swing.GroupLayout(jPanel824);
        jPanel824.setLayout(jPanel824Layout);
        jPanel824Layout.setHorizontalGroup(
            jPanel824Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane204, javax.swing.GroupLayout.DEFAULT_SIZE, 1375, Short.MAX_VALUE)
        );
        jPanel824Layout.setVerticalGroup(
            jPanel824Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel824Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane204, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel822Layout = new javax.swing.GroupLayout(jPanel822);
        jPanel822.setLayout(jPanel822Layout);
        jPanel822Layout.setHorizontalGroup(
            jPanel822Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel823, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel824, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel822Layout.setVerticalGroup(
            jPanel822Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel822Layout.createSequentialGroup()
                .addComponent(jPanel823, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel824, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane18.addTab("Duplicados por UserID Externos", jPanel822);

        jLabel1.setText(".");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(170, 170, 170)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 704, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jLabel1))
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
            java.util.logging.Logger.getLogger(ResultadosIndividual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ResultadosIndividual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ResultadosIndividual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ResultadosIndividual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ResultadosIndividual(args[1]).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel196;
    private javax.swing.JLabel jLabel197;
    private javax.swing.JLabel jLabel198;
    private javax.swing.JLabel jLabel199;
    private javax.swing.JLabel jLabel200;
    private javax.swing.JLabel jLabel201;
    private javax.swing.JLabel jLabel202;
    private javax.swing.JLabel jLabel203;
    private javax.swing.JLabel jLabel204;
    private javax.swing.JLabel jLabel205;
    private javax.swing.JLabel jLabel206;
    private javax.swing.JLabel jLabel207;
    private javax.swing.JPanel jPanel778;
    private javax.swing.JPanel jPanel779;
    private javax.swing.JPanel jPanel780;
    private javax.swing.JPanel jPanel782;
    private javax.swing.JPanel jPanel783;
    private javax.swing.JPanel jPanel784;
    private javax.swing.JPanel jPanel786;
    private javax.swing.JPanel jPanel787;
    private javax.swing.JPanel jPanel788;
    private javax.swing.JPanel jPanel790;
    private javax.swing.JPanel jPanel791;
    private javax.swing.JPanel jPanel792;
    private javax.swing.JPanel jPanel794;
    private javax.swing.JPanel jPanel795;
    private javax.swing.JPanel jPanel796;
    private javax.swing.JPanel jPanel798;
    private javax.swing.JPanel jPanel799;
    private javax.swing.JPanel jPanel800;
    private javax.swing.JPanel jPanel802;
    private javax.swing.JPanel jPanel803;
    private javax.swing.JPanel jPanel804;
    private javax.swing.JPanel jPanel806;
    private javax.swing.JPanel jPanel807;
    private javax.swing.JPanel jPanel808;
    private javax.swing.JPanel jPanel810;
    private javax.swing.JPanel jPanel811;
    private javax.swing.JPanel jPanel812;
    private javax.swing.JPanel jPanel814;
    private javax.swing.JPanel jPanel815;
    private javax.swing.JPanel jPanel816;
    private javax.swing.JPanel jPanel818;
    private javax.swing.JPanel jPanel819;
    private javax.swing.JPanel jPanel820;
    private javax.swing.JPanel jPanel822;
    private javax.swing.JPanel jPanel823;
    private javax.swing.JPanel jPanel824;
    private javax.swing.JScrollPane jScrollPane193;
    private javax.swing.JScrollPane jScrollPane194;
    private javax.swing.JScrollPane jScrollPane195;
    private javax.swing.JScrollPane jScrollPane196;
    private javax.swing.JScrollPane jScrollPane197;
    private javax.swing.JScrollPane jScrollPane198;
    private javax.swing.JScrollPane jScrollPane199;
    private javax.swing.JScrollPane jScrollPane200;
    private javax.swing.JScrollPane jScrollPane201;
    private javax.swing.JScrollPane jScrollPane202;
    private javax.swing.JScrollPane jScrollPane203;
    private javax.swing.JScrollPane jScrollPane204;
    private javax.swing.JTabbedPane jTabbedPane18;
    private javax.swing.JTable tablaBajasExt;
    private javax.swing.JTable tablaBajasInt;
    private javax.swing.JTable tabladuplicadosuserExt;
    private javax.swing.JTable tabladuplicadosuserInt;
    private javax.swing.JTable tabladuplicadosxnombreExt;
    private javax.swing.JTable tabladuplicadosxnombreInt;
    private javax.swing.JTable tabladuplicadosxnumeroExt;
    private javax.swing.JTable tabladuplicadosxnumeroInt;
    private javax.swing.JTable tablainactividadExt;
    private javax.swing.JTable tablainactividadInt;
    private javax.swing.JTable tablausrExt;
    private javax.swing.JTable tablausrInt;
    // End of variables declaration//GEN-END:variables
}
