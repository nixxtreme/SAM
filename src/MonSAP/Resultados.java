
package MonSAP;


import Monitoreos.Conexion;
import MonPac.*;
import Monitoreos.ExecQuery;
import Monitoreos.Clase_CellEditor;
import Monitoreos.Clase_CellRender;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.JLabel;
import static javax.swing.JTable.AUTO_RESIZE_OFF;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.TableView.TableRow;

/**
 *
 * @author VS3XXBD
 */
public class Resultados extends javax.swing.JFrame {
    String cadenaBD;
    ResultSet bajasIntSAP, bajasExtSAP, usrInt, usrExt, inactInt, inactExt, dupInt, duplicadosNombreInt, duplicadosNombreExt, dupExt, perfilInt, perfilInc, perfilExt, noAutoInt, noAutoExt, agreg, elim, Uint, Uext, Ugen, usrgen, sinValidez, usradm, validez180;       //Almacenan los resultados de cada consulta
  
    
    public Resultados(String cadena) {                                          //Inicializa la ventana y ejecuta los métodos para que se visualicen las tablas de resultados
        cadenaBD = cadena;                                                      //Almacena la cadena con los datos de conexión a la base de datos local
        initComponents();                                                       //Inicializa los componentes proncipales de la ventana
        definirModelosBajasIntSAP();                                               //Define el modelo de la tabla de inconsistencias de bajas internos
        definirModelosBajasExtSAP();                                               //Define el modelo de la tabla de inconsistencias de bajas externos
        definirModelosInactividadInt();                                         //Define el modelo de la tabla de inconsistencias de inactividad internos
        definirModelosInactividadExt();                                         //Define el modelo de la tabla de inconsistencias de inactividad externos
        definirModelosCodLibera();                                             //Define el modelo de la tabla de inconsistencias de UserID incorrecto internos
        
        definirModelosDupXNomInt();                                             //Define el modelo de la tabla de inconsistencias de usuarios duplicados por nombre internos
        definirModelosDupXNomExt();                                             //Define el modelo de la tabla de inconsistencias de usuarios duplicados por nombre externos
        
        definirModelosAgregados();                                              //Define el modelo de la tabla de usuarios agregados
        definirModelosEliminados();                                             //Define el modelo de la tabla de usuarios eliminados
        definirModelosSinValidez();
        definirModelosValidez180();
        definirModelosAdministradores();
        definirModelosGenericos();
        definirModelosPerfilIncorrectoSAP(); 
        definirModelosPerfilNoAutorizadoSAP();
    }
    
    void definirModelosBajasIntSAP()                                               //Define el modelo de la tabla de inconsistencias de usuarios internos reportados como baja
    {
        DefaultTableModel modeloBajasInt = new DefaultTableModel();             //Crea el objeto modelo de tabla
        
        
        
        Object[] registro = new Object[18];                                     //Crea un arreglo para recibir los elementos de cada renglon
        int i = 0;                                                              //Inicializa la variable para el contador
        try
        {
            Conexion conLocal = new Conexion();                                 //Inicia la conexión local
            conLocal.AbrirLocal(cadenaBD);
            ExecQuery EjecutaLo = new ExecQuery();                              //Crea el objeto para ejecutar la consulta
            bajasIntSAP = EjecutaLo.Cons(conLocal.conexion, Monitoreos.Querys.ResultadosBajasIntSAP());   //Ejecuta la consulta y almacena el resultado en la variable
            
            if(bajasIntSAP.next())                                                 //Verifica que el resultado no esté vacío
            {
                modeloBajasInt.addColumn("Agregar");                            //Crea las columnas necesarias para el reporte
                modeloBajasInt.addColumn("Num Emp");
                modeloBajasInt.addColumn("Nombre");
                modeloBajasInt.addColumn("Perfil");
                modeloBajasInt.addColumn("Puesto");
                modeloBajasInt.addColumn("Creado el");
                modeloBajasInt.addColumn("Válido de");
                modeloBajasInt.addColumn("Fin valid");
                modeloBajasInt.addColumn("Último acceso");
                
                modeloBajasInt.addColumn("NUMEROEMPLEADO");
                modeloBajasInt.addColumn("USERID");  
                modeloBajasInt.addColumn("NOMBRE");
                modeloBajasInt.addColumn("PUESTO");
                modeloBajasInt.addColumn("GERENCIA");
                modeloBajasInt.addColumn("ESTATUS");
                modeloBajasInt.addColumn("FECHA BAJA NOMINA");
               
               
                bajasIntSAP.beforeFirst();                                         //Regresa a la posición inicial del resultado
                while(bajasIntSAP.next())                                          //Lee cada registro hasta que ya no haya más
                {
                    for(int k=1; k<17; k++)                                     
                    {
                        if(k==1)                                                
                        {
                            registro[k-1]=Boolean.TRUE;                         //Si está en la primer columna establece un valor TRUE para que el checkbox esté seleccionado
                        }
                        else
                        {
                            registro[k-1]=bajasIntSAP.getString(k-1);              //Recorre los elementos del registro para obtener cada dato de las columnas                         
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
                TableColumn CUsuario = tablaBajasInt.getColumn("Num Emp");    //Se llama a la columna
                CUsuario.setPreferredWidth(110);                                 //Se define su tamaño
                TableColumn CNombre = tablaBajasInt.getColumn("Nombre");       //Se llama a la columna
                CNombre.setPreferredWidth(330);                                  //Se define su tamaño
                TableColumn CPerfil = tablaBajasInt.getColumn("Perfil");       //Se llama a la columna
                CPerfil.setPreferredWidth(150);                                  //Se define su tamaño
                TableColumn CPuesto = tablaBajasInt.getColumn("Puesto");        //Se llama a la columna
                CPuesto.setPreferredWidth(150);                                 //Se define su tamaño            
                TableColumn CCreado = tablaBajasInt.getColumn("Creado el");        //Se llama a la columna
                CCreado.setPreferredWidth(90);                                 //Se define su tamaño
                TableColumn CValido = tablaBajasInt.getColumn("Válido de");        //Se llama a la columna
                CValido.setPreferredWidth(90);                                 //Se define su tamaño
                TableColumn CFinV = tablaBajasInt.getColumn("Fin valid"); //Se llama a la columna
                CFinV.setPreferredWidth(90);                                  //Se define su tamaño
                TableColumn CUltimoa = tablaBajasInt.getColumn("Último acceso");  //Se llama a la columna
                CUltimoa.setPreferredWidth(90);                              //Se define su tamaño
                
                TableColumn CNumemp = tablaBajasInt.getColumn("NUMEROEMPLEADO");    //Se llama a la columna
                CNumemp.setPreferredWidth(110);                              //Se define su tamaño                                                                         
                TableColumn CUserid = tablaBajasInt.getColumn("USERID");       //Se llama a la columna
                CUserid.setPreferredWidth(90);                                 //Se define su tamaño
                TableColumn CNombren = tablaBajasInt.getColumn("NOMBRE");   //Se llama a la columna
                CNombren.setPreferredWidth(300);                           //Se define su tamaño
                TableColumn CPueston = tablaBajasInt.getColumn("PUESTO");   //Se llama a la columna
                CPueston.setPreferredWidth(300);                           //Se define su tamaño
                TableColumn CGerencia = tablaBajasInt.getColumn("GERENCIA");   //Se llama a la columna
                CGerencia.setPreferredWidth(250);                           //Se define su tamaño
                TableColumn CEstatus = tablaBajasInt.getColumn("ESTATUS");   //Se llama a la columna
                CEstatus.setPreferredWidth(150);                           //Se define su tamaño
                TableColumn CBBFFechaBaja = tablaBajasInt.getColumn("FECHA BAJA NOMINA");   //Se llama a la columna
                CBBFFechaBaja.setPreferredWidth(115);                           //Se define su tamaño
                
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
    
    void definirModelosBajasExtSAP()                                               //Define el modelo de la tabla de inconsistencias de usuarios externos reportados como baja
    {
        DefaultTableModel modeloBajasExt = new DefaultTableModel();             //Define el objeto modelo de tabla
          
        Object[] registro = new Object[18];                                     //Crea el arreglo para almacenar los datos de cada registro
        int i = 0;
        try
        {
            Conexion conLocal = new Conexion();                                 //Crea conexión para la consulta
            conLocal.AbrirLocal(cadenaBD);
            ExecQuery EjecutaLo = new ExecQuery();                              //Crea el objeto de ejecución
            bajasExtSAP = EjecutaLo.Cons(conLocal.conexion, Monitoreos.Querys.ResultadosBajasExtSAP());   //Ejecuta la consulta y almacena el resultado en bajasExt
            
            if(bajasExtSAP.next())                                                 //Verifica que el resultado no esté vacío
            {
                modeloBajasExt.addColumn("Agregar");                            //Crea las columnas necesarias para el reporte
                modeloBajasExt.addColumn("Num Emp");
                modeloBajasExt.addColumn("Nombre");
                modeloBajasExt.addColumn("Perfil");
                modeloBajasExt.addColumn("Puesto");
                modeloBajasExt.addColumn("Creado el");
                modeloBajasExt.addColumn("Válido de");
                modeloBajasExt.addColumn("Fin valid");
                modeloBajasExt.addColumn("Último acceso");
                
                modeloBajasExt.addColumn("NUMEROEMPLEADO");
                modeloBajasExt.addColumn("USERID");  
                modeloBajasExt.addColumn("NOMBRE");
                modeloBajasExt.addColumn("PUESTO");
                modeloBajasExt.addColumn("GERENCIA");
                modeloBajasExt.addColumn("ESTATUS");
                modeloBajasExt.addColumn("FECHA BAJA NOMINA");
                
                bajasExtSAP.beforeFirst();                                         //Regresa a la posición inicial del resultado
                while(bajasExtSAP.next())                                          //Lee cada registro hasta que ya no haya más
                {
                    for(int k=1; k<17; k++)                                     
                    {
                        if(k==1)                                                
                        {
                            registro[k-1]=Boolean.TRUE;                         //Si está en la primer columna establece un valor TRUE para que el checkbox esté seleccionado
                        }
                        else
                        {
                            registro[k-1]=bajasExtSAP.getString(k-1);              //Recorre los elementos del registro para obtener cada dato de las columnas
                        }
                    }
                    modeloBajasExt.addRow(registro);                            //Ya que todos los elementos del registro están en el arreglo se agrega el arreglo como un nuevo renglón de la tabla
                }

                tablaBajasExt.setModel(modeloBajasExt);                         //Una vez construida completamente la tabla se define el modelo a la tabla original
                tablaBajasExt.setAutoResizeMode(AUTO_RESIZE_OFF);               //Se desabilita el ajuste automatico de ancho de columnas para establecerlo manualmente
                tablaBajasExt.getColumnModel().getColumn(0).setCellEditor(new Clase_CellEditor());  //Se hace uso de editor y renderizador de columnas
                tablaBajasExt.getColumnModel().getColumn(0).setCellRenderer(new Clase_CellRender());

               TableColumn CAgregar = tablaBajasExt.getColumn("Agregar");      //Se llama a la columna
                CAgregar.setPreferredWidth(55);                                 //Se define su tamaño
                TableColumn CUsuario = tablaBajasExt.getColumn("Num Emp");    //Se llama a la columna
                CUsuario.setPreferredWidth(110);                                 //Se define su tamaño
                TableColumn CNombre = tablaBajasExt.getColumn("Nombre");       //Se llama a la columna
                CNombre.setPreferredWidth(330);                                  //Se define su tamaño
                TableColumn CPerfil = tablaBajasExt.getColumn("Perfil");       //Se llama a la columna
                CPerfil.setPreferredWidth(150);                                  //Se define su tamaño
                TableColumn CPuesto = tablaBajasExt.getColumn("Puesto");        //Se llama a la columna
                CPuesto.setPreferredWidth(300);                                 //Se define su tamaño            
                TableColumn CCreado = tablaBajasExt.getColumn("Creado el");        //Se llama a la columna
                CCreado.setPreferredWidth(90);                                 //Se define su tamaño
                TableColumn CValido = tablaBajasExt.getColumn("Válido de");        //Se llama a la columna
                CValido.setPreferredWidth(90);                                 //Se define su tamaño
                TableColumn CFinV = tablaBajasExt.getColumn("Fin valid"); //Se llama a la columna
                CFinV.setPreferredWidth(90);                                  //Se define su tamaño
                TableColumn CUltimoa = tablaBajasExt.getColumn("Último acceso");  //Se llama a la columna
                CUltimoa.setPreferredWidth(90);                              //Se define su tamaño
                
                TableColumn CNumemp = tablaBajasExt.getColumn("NUMEROEMPLEADO");    //Se llama a la columna
                CNumemp.setPreferredWidth(110);                              //Se define su tamaño                                                                         
                TableColumn CUserid = tablaBajasExt.getColumn("USERID");       //Se llama a la columna
                CUserid.setPreferredWidth(110);                                 //Se define su tamaño
                TableColumn CNombren = tablaBajasExt.getColumn("NOMBRE");   //Se llama a la columna
                CNombren.setPreferredWidth(330);                           //Se define su tamaño
                TableColumn CPueston = tablaBajasExt.getColumn("PUESTO");   //Se llama a la columna
                CPueston.setPreferredWidth(300);                           //Se define su tamaño
                TableColumn CGerencia = tablaBajasExt.getColumn("GERENCIA");   //Se llama a la columna
                CGerencia.setPreferredWidth(250);                           //Se define su tamaño
                TableColumn CEstatus = tablaBajasExt.getColumn("ESTATUS");   //Se llama a la columna
                CEstatus.setPreferredWidth(150);                           //Se define su tamaño
                TableColumn CBBFFechaBaja = tablaBajasExt.getColumn("FECHA BAJA NOMINA");   //Se llama a la columna
                CBBFFechaBaja.setPreferredWidth(115);                           //Se define su tamaño
            }
            else                                                                //Si el resultado se encontraba vacío
            {
                modeloBajasExt.addColumn("No se encontraron inconsistencias");  //Se crea una columna con la leyecnda
                tablaBajasExt.setModel(modeloBajasExt);                         //Se define el modelo 
            }
            
            conLocal.Cerrar();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }        
    }
    
    
    void definirModelosInactividadInt()                                         //Crea el modelo para la tabla de inconsistencias de usuarios internos inactivos
    {
        DefaultTableModel modeloInactividadInt = new DefaultTableModel();       //Define el objeto modelo de tabla
        Object[] registro = new Object[15];                                     //Crea un arreglo para recibir los elementos de cada renglon
        
        try
        {
            Conexion conLocal = new Conexion();                                 //Crea la conexión para la consulta
            conLocal.AbrirLocal(cadenaBD);
            ExecQuery EjecutaLo = new ExecQuery();                              //Crea el objeto para ejecutar la consulta
            inactInt = EjecutaLo.Cons(conLocal.conexion, Monitoreos.Querys.ResultadosInactividadIntSAP()); //Ejecuta la consulta y almacena el resultado en bajasExt
            if(inactInt.next())                                                 //Valida que no se encuentre vacío el resutado de la consulta
            {
                
                inactInt.beforeFirst(); 
                modeloInactividadInt.addColumn("Agregar");                            //Crea las columnas necesarias para el reporte
                modeloInactividadInt.addColumn("Num Emp");
                modeloInactividadInt.addColumn("Nombre");
                modeloInactividadInt.addColumn("Perfil");
                modeloInactividadInt.addColumn("Puesto");
                modeloInactividadInt.addColumn("Creado el");
                modeloInactividadInt.addColumn("Último acceso");
                
                modeloInactividadInt.addColumn("NUMEROEMPLEADO");
                modeloInactividadInt.addColumn("USERID");  
                modeloInactividadInt.addColumn("NOMBRE");
                modeloInactividadInt.addColumn("PUESTO");
                modeloInactividadInt.addColumn("GERENCIA");
                modeloInactividadInt.addColumn("ESTATUS");
                
                                                        //Regresa a la posición inicial del resultado
                
                while(inactInt.next())                                          //Lee cada registro hasta que ya no haya más
                {
                    for(int k=1; k<14; k++)                                     //Recorre los elementos del registro para obtener cada dato de las columnas
                    {
                        if(k==1)                                                //Si está en la primer columna establece un valor TRUE para que el checkbox esté seleccionado
                        {
                            registro[k-1]=Boolean.TRUE;
                        }
                        else
                        {
                            registro[k-1]=inactInt.getString(k-1);
                        }
                    }
                    modeloInactividadInt.addRow(registro);                      //Ya que todos los elementos del registro están en el arreglo se agrega el arreglo como un nuevo renglón de la tabla
                }
                tablaInacInt.setModel(modeloInactividadInt);                    //Una vez construida completamente la tabla se define el modelo a la tabla original
                tablaInacInt.setAutoResizeMode(AUTO_RESIZE_OFF);                //Se desabilita el ajuste automatico de ancho de columnas para establecerlo manualmente
                tablaInacInt.getColumnModel().getColumn(0).setCellEditor(new Clase_CellEditor());   //Se hace uso de editor y renderizador de columnas
                tablaInacInt.getColumnModel().getColumn(0).setCellRenderer(new Clase_CellRender());

                TableColumn CAgregar = tablaInacInt.getColumn("Agregar");       //Se llama a la columna
                CAgregar.setPreferredWidth(55);                                 //Se define su tamaño
                TableColumn CNumEmp = tablaInacInt.getColumn("Num Emp"); //Se llama a la columna
                CNumEmp.setPreferredWidth(140);                                 //Se define su tamaño
                TableColumn CNombre = tablaInacInt.getColumn("Nombre");        //Se llama a la columna
                CNombre.setPreferredWidth(310);                                  //Se define su tamaño
                TableColumn CPerfil = tablaInacInt.getColumn("Perfil");       //Se llama a la columna
                CPerfil.setPreferredWidth(150);                                  //Se define su tamaño
                TableColumn CPuesto = tablaInacInt.getColumn("Puesto");        //Se llama a la columna
                CPuesto.setPreferredWidth(300);                                 //Se define su tamaño            
                TableColumn CCreado = tablaInacInt.getColumn("Creado el");        //Se llama a la columna
                CCreado.setPreferredWidth(90);                                 //Se define su tamaño
                TableColumn CUltimoa = tablaInacInt.getColumn("Último acceso");  //Se llama a la columna
                CUltimoa.setPreferredWidth(90);                              //Se define su tamaño
                
                TableColumn CNumemp = tablaInacInt.getColumn("NUMEROEMPLEADO");    //Se llama a la columna
                CNumemp.setPreferredWidth(110);                              //Se define su tamaño                                                                         
                TableColumn CUserid = tablaInacInt.getColumn("USERID");       //Se llama a la columna
                CUserid.setPreferredWidth(110);                                 //Se define su tamaño
                TableColumn CNombren = tablaInacInt.getColumn("NOMBRE");   //Se llama a la columna
                CNombren.setPreferredWidth(330);                           //Se define su tamaño
                TableColumn CPueston = tablaInacInt.getColumn("PUESTO");   //Se llama a la columna
                CPueston.setPreferredWidth(300);                           //Se define su tamaño
                TableColumn CGerencia = tablaInacInt.getColumn("GERENCIA");   //Se llama a la columna
                CGerencia.setPreferredWidth(250);                           //Se define su tamaño
                TableColumn CEstatus = tablaInacInt.getColumn("ESTATUS");   //Se llama a la columna
                CEstatus.setPreferredWidth(150);                           //Se define su tamaño
                
                
            }
            else
            {
                modeloInactividadInt.addColumn("No se encontraron inconsistencias");
                tablaInacInt.setModel(modeloInactividadInt);
            }                                              
            conLocal.Cerrar();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }                        
    }
    
    
    void definirModelosInactividadExt()                                         //Crea el modelo para la tabla de inconsistencias para los usuarios externos inactivos
    {
        
        DefaultTableModel modeloInactividadExt = new DefaultTableModel();       //Define el objeto modelo de tabla
        Object[] registro = new Object[15];                                     //Crea un arreglo para recibir los elementos de cada renglon
        
        
        try
        {
            Conexion conLocal = new Conexion();                                 //Crea la conexión para la consulta
            conLocal.AbrirLocal(cadenaBD);
            ExecQuery EjecutaLo = new ExecQuery();                              //Crea el objeto para ejecutar la consulta
            inactExt = EjecutaLo.Cons(conLocal.conexion, Monitoreos.Querys.ResultadosInactividadExtSAP()); //Ejecuta la consulta y almacena el resultado en bajasExt
            if(inactExt.next())                                                 //Valida que no se encuentre vacío el resutado de la consulta
            {
               
                inactExt.beforeFirst();                                         //Regresa a la posición inicial del resultado
                modeloInactividadExt.addColumn("Agregar");                            //Crea las columnas necesarias para el reporte
                modeloInactividadExt.addColumn("Num Emp");
                modeloInactividadExt.addColumn("Nombre");
                modeloInactividadExt.addColumn("Perfil");
                modeloInactividadExt.addColumn("Puesto");
                modeloInactividadExt.addColumn("Creado el");
                modeloInactividadExt.addColumn("Último acceso");
                
                modeloInactividadExt.addColumn("NUMEROEMPLEADO");
                modeloInactividadExt.addColumn("USERID");  
                modeloInactividadExt.addColumn("NOMBRE");
                modeloInactividadExt.addColumn("PUESTO");
                modeloInactividadExt.addColumn("GERENCIA");
                modeloInactividadExt.addColumn("ESTATUS");
                
                                                         
                while(inactExt.next())                                          //Lee cada registro hasta que ya no haya más
                {
                    for(int k=1; k<14; k++)                                     //Recorre los elementos del registro para obtener cada dato de las columnas
                    {
                        if(k==1)
                        {
                            registro[k-1]=Boolean.TRUE;                         //Si está en la primer columna establece un valor TRUE para que el checkbox esté seleccionado
                        }
                        else
                        {
                            registro[k-1]=inactExt.getString(k-1);
                        }
                    }
                    modeloInactividadExt.addRow(registro);                      //Ya que todos los elementos del registro están en el arreglo se agrega el arreglo como un nuevo renglón de la tabla
                }
                tablaInacExt.setModel(modeloInactividadExt);                    //Una vez construida completamente la tabla se define el modelo a la tabla original
                tablaInacExt.setAutoResizeMode(AUTO_RESIZE_OFF);                //Se desabilita el ajuste automatico de ancho de columnas para establecerlo manualmente
                tablaInacExt.getColumnModel().getColumn(0).setCellEditor(new Clase_CellEditor());   //Se hace uso de editor y renderizador de columnas
                tablaInacExt.getColumnModel().getColumn(0).setCellRenderer(new Clase_CellRender());

                 TableColumn CAgregar = tablaInacExt.getColumn("Agregar");       //Se llama a la columna
                CAgregar.setPreferredWidth(55);                                 //Se define su tamaño
                TableColumn CNumEmp = tablaInacExt.getColumn("Num Emp"); //Se llama a la columna
                CNumEmp.setPreferredWidth(140);                                 //Se define su tamaño
                TableColumn CNombre = tablaInacExt.getColumn("Nombre");        //Se llama a la columna
                CNombre.setPreferredWidth(310);                                  //Se define su tamaño
                TableColumn CPerfil = tablaInacExt.getColumn("Perfil");       //Se llama a la columna
                CPerfil.setPreferredWidth(150);                                  //Se define su tamaño
                TableColumn CPuesto = tablaInacExt.getColumn("Puesto");        //Se llama a la columna
                CPuesto.setPreferredWidth(300);                                 //Se define su tamaño            
                TableColumn CCreado = tablaInacExt.getColumn("Creado el");        //Se llama a la columna
                CCreado.setPreferredWidth(90);                                 //Se define su tamaño
                TableColumn CUltimoa = tablaInacExt.getColumn("Último acceso");  //Se llama a la columna
                CUltimoa.setPreferredWidth(90);                              //Se define su tamaño
                
                TableColumn CNumemp = tablaInacExt.getColumn("NUMEROEMPLEADO");    //Se llama a la columna
                CNumemp.setPreferredWidth(110);                              //Se define su tamaño                                                                         
                TableColumn CUserid = tablaInacExt.getColumn("USERID");       //Se llama a la columna
                CUserid.setPreferredWidth(110);                                 //Se define su tamaño
                TableColumn CNombren = tablaInacExt.getColumn("NOMBRE");   //Se llama a la columna
                CNombren.setPreferredWidth(330);                           //Se define su tamaño
                TableColumn CPueston = tablaInacExt.getColumn("PUESTO");   //Se llama a la columna
                CPueston.setPreferredWidth(300);                           //Se define su tamaño
                TableColumn CGerencia = tablaInacExt.getColumn("GERENCIA");   //Se llama a la columna
                CGerencia.setPreferredWidth(250);                           //Se define su tamaño
                TableColumn CEstatus = tablaInacExt.getColumn("ESTATUS");   //Se llama a la columna
                CEstatus.setPreferredWidth(150);                                //Se define su tamaño
            }
            else
            {
                modeloInactividadExt.addColumn("No se encontraron inconsistencias");
                tablaInacExt.setModel(modeloInactividadExt);
            }
            conLocal.Cerrar();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }  
        
        
    }

    
    
    void definirModelosCodLibera()                                             //Crea el modelo para la tabla de inconsistencias de usuarios internos con UserID incorrecto
    {
        
        DefaultTableModel modeloCodLibera = new DefaultTableModel();        //Define el objeto modelo de tabla
        Object[] registro = new Object[6];                                     //Crea un arreglo para recibir los elementos de cada renglon
        
        
        try
        {
            Conexion conLocal = new Conexion();                                 //Crea la conexión para la consulta
            conLocal.AbrirLocal(cadenaBD);
            ExecQuery EjecutaLo = new ExecQuery();                              //Crea el objeto para ejecutar la consulta
            usrInt = EjecutaLo.Cons(conLocal.conexion, Monitoreos.Querys.ResultadosCodLibera());   //Ejecuta la consulta y almacena el resultado en bajasExt
            if(usrInt.next())                                                   //Valida que no se encuentre vacío el resutado de la consulta
            {
                usrInt.beforeFirst();                                           //Regresa a la posición inicial del resultado
                modeloCodLibera.addColumn("Agregar");
                modeloCodLibera.addColumn("Usuario");
                modeloCodLibera.addColumn("Rol");
                modeloCodLibera.addColumn("Denominacion");
                modeloCodLibera.addColumn("Valor");
                
                while(usrInt.next())                                            //Lee cada registro hasta que ya no haya más
                {
                    for(int k=1; k<6; k++)                                     //Recorre los elementos del registro para obtener cada dato de las columnas
                    {
                        if(k==1)
                        {
                            registro[k-1]=Boolean.TRUE;                         //Si está en la primer columna establece un valor TRUE para que el checkbox esté seleccionado
                        }
                        else
                        {
                            registro[k-1]=usrInt.getString(k-1);
                        }
                    }
                    modeloCodLibera.addRow(registro);                       //Ya que todos los elementos del registro están en el arreglo se agrega el arreglo como un nuevo renglón de la tabla
                }
                tablaCodLibera.setModel(modeloCodLibera);                      //Una vez construida completamente la tabla se define el modelo a la tabla original
                tablaCodLibera.setAutoResizeMode(AUTO_RESIZE_OFF);                 //Se desabilita el ajuste automatico de ancho de columnas para establecerlo manualmente
                tablaCodLibera.getColumnModel().getColumn(0).setCellEditor(new Clase_CellEditor());    //Se hace uso de editor y renderizador de columnas
                tablaCodLibera.getColumnModel().getColumn(0).setCellRenderer(new Clase_CellRender());

                TableColumn CAgregar = tablaCodLibera.getColumn("Agregar");        //Se llama a la columna
                CAgregar.setPreferredWidth(55);                                 //Se define su tamaño
                TableColumn CNumEmp = tablaCodLibera.getColumn("Usuario");  //Se llama a la columna
                CNumEmp.setPreferredWidth(110);                                 //Se define su tamaño
                TableColumn CUserID = tablaCodLibera.getColumn("Rol");         //Se llama a la columna
                CUserID.setPreferredWidth(120);                                  //Se define su tamaño
                TableColumn CNombre = tablaCodLibera.getColumn("Denominacion");          //Se llama a la columna
                CNombre.setPreferredWidth(400);                                 //Se define su tamaño
                TableColumn CFecha = tablaCodLibera.getColumn("Valor");   //Se llama a la columna
                CFecha.setPreferredWidth(160);                                  //Se define su tamaño
               
                
                
              
            }
            else
            {
                modeloCodLibera.addColumn("No se encontraron inconsistencias");
                tablaCodLibera.setModel(modeloCodLibera);
            }
            conLocal.Cerrar();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }  
        
        
    }
    
    
    
        
    void definirModelosDupXNomInt()                                             //crea el modelo para la tabla de inconsistencias de usuarios itnernos duplicados por nombre
    {
        DefaultTableModel modeloDuplicadosNombre = new DefaultTableModel();             //Crea el objeto modelo de tabla
        
        
        
        Object[] registro = new Object[18];                                     //Crea un arreglo para recibir los elementos de cada renglon
        int i = 0;                                                              //Inicializa la variable para el contador
        try
        {
            Conexion conLocal = new Conexion();                                 //Inicia la conexión local
            conLocal.AbrirLocal(cadenaBD);
            ExecQuery EjecutaLo = new ExecQuery();                              //Crea el objeto para ejecutar la consulta
            duplicadosNombreInt = EjecutaLo.Cons(conLocal.conexion, Monitoreos.Querys.ResultadosDuplicadosXNomIntSAP());   //Ejecuta la consulta y almacena el resultado en la variable
            
            if(duplicadosNombreInt.next())                                                 //Verifica que el resultado no esté vacío
            {
                modeloDuplicadosNombre.addColumn("Agregar");                            //Crea las columnas necesarias para el reporte
                modeloDuplicadosNombre.addColumn("Num Emp");
                modeloDuplicadosNombre.addColumn("Nombre");
                modeloDuplicadosNombre.addColumn("Perfil");
                modeloDuplicadosNombre.addColumn("Puesto");
                modeloDuplicadosNombre.addColumn("Creado el");
                modeloDuplicadosNombre.addColumn("Válido de");
                modeloDuplicadosNombre.addColumn("Fin valid");
                modeloDuplicadosNombre.addColumn("Último acceso");                
                modeloDuplicadosNombre.addColumn("NUMEROEMPLEADO");
                modeloDuplicadosNombre.addColumn("USERID");  
                modeloDuplicadosNombre.addColumn("NOMBRE");
                modeloDuplicadosNombre.addColumn("PUESTO");
                modeloDuplicadosNombre.addColumn("GERENCIA");
                
               
               
                duplicadosNombreInt.beforeFirst();                                         //Regresa a la posición inicial del resultado
                while(duplicadosNombreInt.next())                                          //Lee cada registro hasta que ya no haya más
                {
                    for(int k=1; k<15; k++)                                     
                    {
                        if(k==1)                                                
                        {
                            registro[k-1]=Boolean.TRUE;                         //Si está en la primer columna establece un valor TRUE para que el checkbox esté seleccionado
                        }
                        else
                        {
                            registro[k-1]=duplicadosNombreInt.getString(k-1);              //Recorre los elementos del registro para obtener cada dato de las columnas                                                         
                        }                        
                    }
                    
                    modeloDuplicadosNombre.addRow(registro);                            //Ya que todos los elementos del registro están en el arreglo se agrega el arreglo como un nuevo renglón de la tabla
                }

                tablaDupInt.setModel(modeloDuplicadosNombre);                         //Una vez construida completamente la tabla se define el modelo a la tabla original
                tablaDupInt.setAutoResizeMode(AUTO_RESIZE_OFF);               //Se desabilita el ajuste automatico de ancho de columnas para establecerlo manualmente
                tablaDupInt.getColumnModel().getColumn(0).setCellEditor(new Clase_CellEditor());  //Se hace uso de editor y renderizador de columnas
                tablaDupInt.getColumnModel().getColumn(0).setCellRenderer(new Clase_CellRender());

                TableColumn CAgregar = tablaDupInt.getColumn("Agregar");      //Se llama a la columna
                CAgregar.setPreferredWidth(55);                                 //Se define su tamaño
                TableColumn CUsuario = tablaDupInt.getColumn("Num Emp");    //Se llama a la columna
                CUsuario.setPreferredWidth(110);                                 //Se define su tamaño
                TableColumn CNombre = tablaDupInt.getColumn("Nombre");       //Se llama a la columna
                CNombre.setPreferredWidth(330);                                  //Se define su tamaño
                TableColumn CPerfil = tablaDupInt.getColumn("Perfil");       //Se llama a la columna
                CPerfil.setPreferredWidth(150);                                  //Se define su tamaño
                TableColumn CPuesto = tablaDupInt.getColumn("Puesto");        //Se llama a la columna
                CPuesto.setPreferredWidth(150);                                 //Se define su tamaño            
                TableColumn CCreado = tablaDupInt.getColumn("Creado el");        //Se llama a la columna
                CCreado.setPreferredWidth(90);                                 //Se define su tamaño
                TableColumn CValido = tablaDupInt.getColumn("Válido de");        //Se llama a la columna
                CValido.setPreferredWidth(90);                                 //Se define su tamaño
                TableColumn CFinV = tablaDupInt.getColumn("Fin valid"); //Se llama a la columna
                CFinV.setPreferredWidth(90);                                  //Se define su tamaño
                TableColumn CUltimoa = tablaDupInt.getColumn("Último acceso");  //Se llama a la columna
                CUltimoa.setPreferredWidth(90);                              //Se define su tamaño
                
                TableColumn CNumemp = tablaDupInt.getColumn("NUMEROEMPLEADO");    //Se llama a la columna
                CNumemp.setPreferredWidth(110);                              //Se define su tamaño                                                                         
                TableColumn CUserid = tablaDupInt.getColumn("USERID");       //Se llama a la columna
                CUserid.setPreferredWidth(90);                                 //Se define su tamaño
                TableColumn CNombren = tablaDupInt.getColumn("NOMBRE");   //Se llama a la columna
                CNombren.setPreferredWidth(300);                           //Se define su tamaño
                TableColumn CPueston = tablaDupInt.getColumn("PUESTO");   //Se llama a la columna
                CPueston.setPreferredWidth(300);                           //Se define su tamaño
                TableColumn CGerencia = tablaDupInt.getColumn("GERENCIA");   //Se llama a la columna
                CGerencia.setPreferredWidth(250);                           //Se define su tamaño
               
                
            }
            else                                                                //Si el resultado se encontraba vacío
            {
                modeloDuplicadosNombre.addColumn("No se encontraron inconsistencias");  //Se crea una columna con la leyecnda
                tablaDupInt.setModel(modeloDuplicadosNombre);                         //Se define el modelo 
            }
            
            conLocal.Cerrar();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }        
    }
    
    
    void definirModelosDupXNomExt()                                             //Crea el modelo para la tabla de inconsistencias de usuarios externos duplicados por nombre
    {
        DefaultTableModel modeloDuplicadosNombreExt = new DefaultTableModel();             //Crea el objeto modelo de tabla
          
        
        Object[] registro = new Object[18];                                     //Crea un arreglo para recibir los elementos de cada renglon
        int i = 0;                                                              //Inicializa la variable para el contador
        try
        {
            Conexion conLocal = new Conexion();                                 //Inicia la conexión local
            conLocal.AbrirLocal(cadenaBD);
            ExecQuery EjecutaLo = new ExecQuery();                              //Crea el objeto para ejecutar la consulta
            duplicadosNombreExt = EjecutaLo.Cons(conLocal.conexion, Monitoreos.Querys.ResultadosDuplicadosXNomExtSAP());   //Ejecuta la consulta y almacena el resultado en la variable
            
            if(duplicadosNombreExt.next())                                                 //Verifica que el resultado no esté vacío
            {
                modeloDuplicadosNombreExt.addColumn("Agregar");                            //Crea las columnas necesarias para el reporte
                modeloDuplicadosNombreExt.addColumn("Num Emp");
                modeloDuplicadosNombreExt.addColumn("Nombre");
                modeloDuplicadosNombreExt.addColumn("Perfil");
                modeloDuplicadosNombreExt.addColumn("Puesto");
                modeloDuplicadosNombreExt.addColumn("Creado el");
                modeloDuplicadosNombreExt.addColumn("Válido de");
                modeloDuplicadosNombreExt.addColumn("Fin valid");
                modeloDuplicadosNombreExt.addColumn("Último acceso");                
                modeloDuplicadosNombreExt.addColumn("NUMEROEMPLEADO");
                modeloDuplicadosNombreExt.addColumn("USERID");  
                modeloDuplicadosNombreExt.addColumn("NOMBRE");
                modeloDuplicadosNombreExt.addColumn("PUESTO");
                modeloDuplicadosNombreExt.addColumn("GERENCIA");
                
               
               
                duplicadosNombreExt.beforeFirst();                                         //Regresa a la posición inicial del resultado
                while(duplicadosNombreExt.next())                                          //Lee cada registro hasta que ya no haya más
                {
                    for(int k=1; k<15; k++)                                     
                    {
                        if(k==1)                                                
                        {
                            registro[k-1]=Boolean.TRUE;                         //Si está en la primer columna establece un valor TRUE para que el checkbox esté seleccionado
                        }
                        else
                        {
                            registro[k-1]=duplicadosNombreExt.getString(k-1);              //Recorre los elementos del registro para obtener cada dato de las columnas                         
                        }
                    }
                    
                    modeloDuplicadosNombreExt.addRow(registro);                            //Ya que todos los elementos del registro están en el arreglo se agrega el arreglo como un nuevo renglón de la tabla
                }

                tablaDupExt.setModel(modeloDuplicadosNombreExt);                         //Una vez construida completamente la tabla se define el modelo a la tabla original
                tablaDupExt.setAutoResizeMode(AUTO_RESIZE_OFF);               //Se desabilita el ajuste automatico de ancho de columnas para establecerlo manualmente
                tablaDupExt.getColumnModel().getColumn(0).setCellEditor(new Clase_CellEditor());  //Se hace uso de editor y renderizador de columnas
                tablaDupExt.getColumnModel().getColumn(0).setCellRenderer(new Clase_CellRender());

                TableColumn CAgregar = tablaDupExt.getColumn("Agregar");      //Se llama a la columna
                CAgregar.setPreferredWidth(55);                                 //Se define su tamaño
                TableColumn CUsuario = tablaDupExt.getColumn("Num Emp");    //Se llama a la columna
                CUsuario.setPreferredWidth(110);                                 //Se define su tamaño
                TableColumn CNombre = tablaDupExt.getColumn("Nombre");       //Se llama a la columna
                CNombre.setPreferredWidth(330);                                  //Se define su tamaño
                TableColumn CPerfil = tablaDupExt.getColumn("Perfil");       //Se llama a la columna
                CPerfil.setPreferredWidth(150);                                  //Se define su tamaño
                TableColumn CPuesto = tablaDupExt.getColumn("Puesto");        //Se llama a la columna
                CPuesto.setPreferredWidth(150);                                 //Se define su tamaño            
                TableColumn CCreado = tablaDupExt.getColumn("Creado el");        //Se llama a la columna
                CCreado.setPreferredWidth(90);                                 //Se define su tamaño
                TableColumn CValido = tablaDupExt.getColumn("Válido de");        //Se llama a la columna
                CValido.setPreferredWidth(90);                                 //Se define su tamaño
                TableColumn CFinV = tablaDupExt.getColumn("Fin valid"); //Se llama a la columna
                CFinV.setPreferredWidth(90);                                  //Se define su tamaño
                TableColumn CUltimoa = tablaDupExt.getColumn("Último acceso");  //Se llama a la columna
                CUltimoa.setPreferredWidth(90);                              //Se define su tamaño
                
                TableColumn CNumemp = tablaDupExt.getColumn("NUMEROEMPLEADO");    //Se llama a la columna
                CNumemp.setPreferredWidth(110);                              //Se define su tamaño                                                                         
                TableColumn CUserid = tablaDupExt.getColumn("USERID");       //Se llama a la columna
                CUserid.setPreferredWidth(90);                                 //Se define su tamaño
                TableColumn CNombren = tablaDupExt.getColumn("NOMBRE");   //Se llama a la columna
                CNombren.setPreferredWidth(300);                           //Se define su tamaño
                TableColumn CPueston = tablaDupExt.getColumn("PUESTO");   //Se llama a la columna
                CPueston.setPreferredWidth(300);                           //Se define su tamaño
                TableColumn CGerencia = tablaDupExt.getColumn("GERENCIA");   //Se llama a la columna
                CGerencia.setPreferredWidth(250);                           //Se define su tamaño
                
                
            }
            else                                                                //Si el resultado se encontraba vacío
            {
                modeloDuplicadosNombreExt.addColumn("No se encontraron inconsistencias");  //Se crea una columna con la leyecnda
                tablaDupExt.setModel(modeloDuplicadosNombreExt);                         //Se define el modelo 
            }
            
            conLocal.Cerrar();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }        
    }
    
    
    
    
    void definirModelosAgregados()                                               //Define el modelo de la tabla de inconsistencias de usuarios internos reportados como baja
    {
        DefaultTableModel modeloAgregados = new DefaultTableModel();             //Crea el objeto modelo de tabla
       
        Object[] registro = new Object[8];                                     //Crea un arreglo para recibir los elementos de cada renglon
        int i = 0;                                                              //Inicializa la variable para el contador
        try
        {
            Conexion conLocal = new Conexion();                                 //Inicia la conexión local
            conLocal.AbrirLocal(cadenaBD);
            ExecQuery EjecutaLo = new ExecQuery();                              //Crea el objeto para ejecutar la consulta
            agreg = EjecutaLo.Cons(conLocal.conexion, Monitoreos.Querys.ResultadosAgreg());   //Ejecuta la consulta y almacena el resultado en la variable
            
            if(agreg.next())                                                 //Verifica que el resultado no esté vacío
            {
                agreg.beforeFirst();                                            //Regresa a la posición inicial del resultado
                modeloAgregados.addColumn("Agregar");
                modeloAgregados.addColumn("Usuario");                            //Crea las columnas necesarias para el reporte
                modeloAgregados.addColumn("Nombre");
                modeloAgregados.addColumn("Apellido");
                modeloAgregados.addColumn("Rol");
                modeloAgregados.addColumn("Valor_Autorizacion");
                
                                               
                while(agreg.next())                                          //Lee cada registro hasta que ya no haya más
                {
                    for(int k=1; k<7; k++)                                     
                    {
                        if(k==1)                                                
                        {
                            registro[k-1]=Boolean.TRUE;                         //Si está en la primer columna establece un valor TRUE para que el checkbox esté seleccionado
                        }
                        else
                        {
                            registro[k-1]=agreg.getString(k-1);              //Recorre los elementos del registro para obtener cada dato de las columnas
                        }
                    }
                    modeloAgregados.addRow(registro);                            //Ya que todos los elementos del registro están en el arreglo se agrega el arreglo como un nuevo renglón de la tabla
                }

                tablaAgregado.setModel(modeloAgregados);                         //Una vez construida completamente la tabla se define el modelo a la tabla original
                tablaAgregado.setAutoResizeMode(AUTO_RESIZE_OFF);               //Se desabilita el ajuste automatico de ancho de columnas para establecerlo manualmente
                tablaAgregado.getColumnModel().getColumn(0).setCellEditor(new Clase_CellEditor());  //Se hace uso de editor y renderizador de columnas
                tablaAgregado.getColumnModel().getColumn(0).setCellRenderer(new Clase_CellRender());

                TableColumn CAgregar = tablaAgregado.getColumn("Agregar");     //Se llama a la columna
                CAgregar.setPreferredWidth(80); 
                TableColumn CUser = tablaAgregado.getColumn("Usuario");      //Se llama a la columna
                CUser.setPreferredWidth(150);                                 //Se define su tamaño
                TableColumn CNombre = tablaAgregado.getColumn("Nombre");    //Se llama a la columna
                CNombre.setPreferredWidth(300);                                 //Se define su tamaño
                TableColumn CApellido = tablaAgregado.getColumn("Apellido");       //Se llama a la columna
                CApellido.setPreferredWidth(300);                                  //Se define su tamaño
                TableColumn CRol = tablaAgregado.getColumn("Rol");        //Se llama a la columna
                CRol.setPreferredWidth(360);                                 //Se define su tamaño
                TableColumn CValor = tablaAgregado.getColumn("Valor_Autorizacion"); //Se llama a la columna
                CValor.setPreferredWidth(160);                                  //Se define su tamaño
                                             
            }
            else                                                                //Si el resultado se encontraba vacío
            {
                modeloAgregados.addColumn("No se encontraron inconsistencias");  //Se crea una columna con la leyecnda
                tablaAgregado.setModel(modeloAgregados);                         //Se define el modelo 
            }
            
            conLocal.Cerrar();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }    
    }
    
    void definirModelosAdministradores()                                               //Define el modelo de la tabla de inconsistencias de usuarios internos reportados como baja
    {
        DefaultTableModel modeloAdministradores = new DefaultTableModel();             //Crea el objeto modelo de tabla
       
        Object[] registro = new Object[8];                                     //Crea un arreglo para recibir los elementos de cada renglon
        int i = 0;                                                              //Inicializa la variable para el contador
        try
        {
            Conexion conLocal = new Conexion();                                 //Inicia la conexión local
            conLocal.AbrirLocal(cadenaBD);
            ExecQuery EjecutaLo = new ExecQuery();                              //Crea el objeto para ejecutar la consulta
            usradm = EjecutaLo.Cons(conLocal.conexion, Monitoreos.Querys.ResultadosAdmin());   //Ejecuta la consulta y almacena el resultado en la variable
            
            if(usradm.next())                                                 //Verifica que el resultado no esté vacío
            {
                usradm.beforeFirst();                                            //Regresa a la posición inicial del resultado
                modeloAdministradores.addColumn("Agregar");
                modeloAdministradores.addColumn("Usuario");                            //Crea las columnas necesarias para el reporte
                modeloAdministradores.addColumn("Nombre");
                modeloAdministradores.addColumn("Grupo");
                modeloAdministradores.addColumn("Valido de");
                modeloAdministradores.addColumn("Valido a");
                
                                               
                while(usradm.next())                                          //Lee cada registro hasta que ya no haya más
                {
                    for(int k=1; k<7; k++)                                     
                    {
                        if(k==1)                                                
                        {
                            registro[k-1]=Boolean.TRUE;                         //Si está en la primer columna establece un valor TRUE para que el checkbox esté seleccionado
                        }
                        else
                        {
                            registro[k-1]=usradm.getString(k-1);              //Recorre los elementos del registro para obtener cada dato de las columnas
                        }
                    }
                    modeloAdministradores.addRow(registro);                            //Ya que todos los elementos del registro están en el arreglo se usradma el arreglo como un nuevo renglón de la tabla
                }

                tablaUsrAdm.setModel(modeloAdministradores);                         //Una vez construida completamente la tabla se define el modelo a la tabla original
                tablaUsrAdm.setAutoResizeMode(AUTO_RESIZE_OFF);               //Se desabilita el ajuste automatico de ancho de columnas para establecerlo manualmente
                tablaUsrAdm.getColumnModel().getColumn(0).setCellEditor(new Clase_CellEditor());  //Se hace uso de editor y renderizador de columnas
                tablaUsrAdm.getColumnModel().getColumn(0).setCellRenderer(new Clase_CellRender());

                TableColumn CAgregar = tablaUsrAdm.getColumn("Agregar");     //Se llama a la columna
                CAgregar.setPreferredWidth(80); 
                TableColumn CUser = tablaUsrAdm.getColumn("Usuario");      //Se llama a la columna
                CUser.setPreferredWidth(150);                                 //Se define su tamaño
                TableColumn CNombre = tablaUsrAdm.getColumn("Nombre");    //Se llama a la columna
                CNombre.setPreferredWidth(300);                                 //Se define su tamaño
                TableColumn CApellido = tablaUsrAdm.getColumn("Grupo");       //Se llama a la columna
                CApellido.setPreferredWidth(300);                                  //Se define su tamaño
                TableColumn CRol = tablaUsrAdm.getColumn("Valido de");        //Se llama a la columna
                CRol.setPreferredWidth(360);                                 //Se define su tamaño
                TableColumn CValor = tablaUsrAdm.getColumn("Valido a"); //Se llama a la columna
                CValor.setPreferredWidth(160);                                  //Se define su tamaño
                                             
            }
            else                                                                //Si el resultado se encontraba vacío
            {
                modeloAdministradores.addColumn("No se encontraron inconsistencias");  //Se crea una columna con la leyecnda
                tablaUsrAdm.setModel(modeloAdministradores);                         //Se define el modelo 
            }
            
            conLocal.Cerrar();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }    
    }
    
    void definirModelosGenericos()                                               //Define el modelo de la tabla de inconsistencias de usuarios internos reportados como baja
    {
        DefaultTableModel modeloGenericos = new DefaultTableModel();             //Crea el objeto modelo de tabla
       
        Object[] registro = new Object[8];                                     //Crea un arreglo para recibir los elementos de cada renglon
        int i = 0;                                                              //Inicializa la variable para el contador
        try
        {
            Conexion conLocal = new Conexion();                                 //Inicia la conexión local
            conLocal.AbrirLocal(cadenaBD);
            ExecQuery EjecutaLo = new ExecQuery();                              //Crea el objeto para ejecutar la consulta
            usrgen = EjecutaLo.Cons(conLocal.conexion, Monitoreos.Querys.ResultadosUsrGen());   //Ejecuta la consulta y almacena el resultado en la variable
            
            if(usrgen.next())                                                 //Verifica que el resultado no esté vacío
            {
                usrgen.beforeFirst();                                            //Regresa a la posición inicial del resultado
                modeloGenericos.addColumn("Agregar");
                modeloGenericos.addColumn("Usuario");                            //Crea las columnas necesarias para el reporte
                modeloGenericos.addColumn("Nombre");
                modeloGenericos.addColumn("Grupo");
                modeloGenericos.addColumn("Valido de");
                modeloGenericos.addColumn("Valido a");
                
                                               
                while(usrgen.next())                                          //Lee cada registro hasta que ya no haya más
                {
                    for(int k=1; k<7; k++)                                     
                    {
                        if(k==1)                                                
                        {
                            registro[k-1]=Boolean.TRUE;                         //Si está en la primer columna establece un valor TRUE para que el checkbox esté seleccionado
                        }
                        else
                        {
                            registro[k-1]=usrgen.getString(k-1);              //Recorre los elementos del registro para obtener cada dato de las columnas
                        }
                    }
                    modeloGenericos.addRow(registro);                            //Ya que todos los elementos del registro están en el arreglo se usrgena el arreglo como un nuevo renglón de la tabla
                }

                tablaUsrGen.setModel(modeloGenericos);                         //Una vez construida completamente la tabla se define el modelo a la tabla original
                tablaUsrGen.setAutoResizeMode(AUTO_RESIZE_OFF);               //Se desabilita el ajuste automatico de ancho de columnas para establecerlo manualmente
                tablaUsrGen.getColumnModel().getColumn(0).setCellEditor(new Clase_CellEditor());  //Se hace uso de editor y renderizador de columnas
                tablaUsrGen.getColumnModel().getColumn(0).setCellRenderer(new Clase_CellRender());

                TableColumn CAgregar = tablaUsrGen.getColumn("Agregar");     //Se llama a la columna
                CAgregar.setPreferredWidth(80); 
                TableColumn CUser = tablaUsrGen.getColumn("Usuario");      //Se llama a la columna
                CUser.setPreferredWidth(150);                                 //Se define su tamaño
                TableColumn CNombre = tablaUsrGen.getColumn("Nombre");    //Se llama a la columna
                CNombre.setPreferredWidth(300);                                 //Se define su tamaño
                TableColumn CApellido = tablaUsrGen.getColumn("Grupo");       //Se llama a la columna
                CApellido.setPreferredWidth(300);                                  //Se define su tamaño
                TableColumn CRol = tablaUsrGen.getColumn("Valido de");        //Se llama a la columna
                CRol.setPreferredWidth(360);                                 //Se define su tamaño
                TableColumn CValor = tablaUsrGen.getColumn("Valido a"); //Se llama a la columna
                CValor.setPreferredWidth(160);                                  //Se define su tamaño
                                             
            }
            else                                                                //Si el resultado se encontraba vacío
            {
                modeloGenericos.addColumn("No se encontraron inconsistencias");  //Se crea una columna con la leyecnda
                tablaUsrGen.setModel(modeloGenericos);                         //Se define el modelo 
            }
            
            conLocal.Cerrar();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }    
    }
    

    void definirModelosEliminados()                                               //Define el modelo de la tabla de inconsistencias de usuarios internos reportados como baja
    {
        DefaultTableModel modeloEliminados = new DefaultTableModel();             //Crea el objeto modelo de tabla
       
        Object[] registro = new Object[8];                                     //Crea un arreglo para recibir los elementos de cada renglon
        int i = 0;                                                              //Inicializa la variable para el contador
        try
        {
            Conexion conLocal = new Conexion();                                 //Inicia la conexión local
            conLocal.AbrirLocal(cadenaBD);
            ExecQuery EjecutaLo = new ExecQuery();                              //Crea el objeto para ejecutar la consulta
            elim = EjecutaLo.Cons(conLocal.conexion, Monitoreos.Querys.ResultadosElim());   //Ejecuta la consulta y almacena el resultado en la variable
            
            if(elim.next())                                                 //Verifica que el resultado no esté vacío
            {
                elim.beforeFirst();                                         //Regresa a la posición inicial del resultado
                modeloEliminados.addColumn("Agregar");
                modeloEliminados.addColumn("Usuario");                            //Crea las columnas necesarias para el reporte
                modeloEliminados.addColumn("Nombre");
                modeloEliminados.addColumn("Apellido");
                modeloEliminados.addColumn("Rol");
                modeloEliminados.addColumn("Valor_Autorizacion");
                
                
                while(elim.next())                                          //Lee cada registro hasta que ya no haya más
                {
                    for(int k=1; k<7; k++)                                     
                    {
                        if(k==1)                                                
                        {
                            registro[k-1]=Boolean.TRUE;                         //Si está en la primer columna establece un valor TRUE para que el checkbox esté seleccionado
                        }
                        else
                        {
                            registro[k-1]=elim.getString(k-1);                 //Recorre los elementos del registro para obtener cada dato de las columnas
                        }
                    }
                    modeloEliminados.addRow(registro);                            //Ya que todos los elementos del registro están en el arreglo se agrega el arreglo como un nuevo renglón de la tabla
                }

                tablaEliminado.setModel(modeloEliminados);                         //Una vez construida completamente la tabla se define el modelo a la tabla original
                tablaEliminado.setAutoResizeMode(AUTO_RESIZE_OFF);               //Se desabilita el ajuste automatico de ancho de columnas para establecerlo manualmente
                tablaEliminado.getColumnModel().getColumn(0).setCellEditor(new Clase_CellEditor());  //Se hace uso de editor y renderizador de columnas
                tablaEliminado.getColumnModel().getColumn(0).setCellRenderer(new Clase_CellRender());

                TableColumn CAgregar = tablaEliminado.getColumn("Agregar");     //Se llama a la columna
                CAgregar.setPreferredWidth(80); 
                TableColumn CUser = tablaEliminado.getColumn("Usuario");        //Se llama a la columna
                CUser.setPreferredWidth(150);                                    //Se define su tamaño
                TableColumn CNombre = tablaEliminado.getColumn("Nombre");       //Se llama a la columna
                CNombre.setPreferredWidth(300);                                 //Se define su tamaño
                TableColumn CApellido = tablaEliminado.getColumn("Apellido");   //Se llama a la columna
                CApellido.setPreferredWidth(300);                                //Se define su tamaño
                TableColumn CRol = tablaEliminado.getColumn("Rol");             //Se llama a la columna
                CRol.setPreferredWidth(360);                                        //Se define su tamaño
                TableColumn CValor = tablaEliminado.getColumn("Valor_Autorizacion"); //Se llama a la columna
                CValor.setPreferredWidth(160);                                  //Se define su tamaño                                             
            }
            else                                                                //Si el resultado se encontraba vacío
            {
                modeloEliminados.addColumn("No se encontraron inconsistencias");  //Se crea una columna con la leyecnda
                tablaEliminado.setModel(modeloEliminados);                         //Se define el modelo 
            }
            
            conLocal.Cerrar();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }    
    }
    
    
    
    void definirModelosSinValidez()                                         //Crea el modelo para la tabla de inconsistencias para los usuarios externos inactivos
    {
        
        DefaultTableModel modeloSinValidez = new DefaultTableModel();       //Define el objeto modelo de tabla
        Object[] registro = new Object[15];                                     //Crea un arreglo para recibir los elementos de cada renglon
        
        
        try
        {
            Conexion conLocal = new Conexion();                                 //Crea la conexión para la consulta
            conLocal.AbrirLocal(cadenaBD);
            ExecQuery EjecutaLo = new ExecQuery();                              //Crea el objeto para ejecutar la consulta
            sinValidez = EjecutaLo.Cons(conLocal.conexion, Monitoreos.Querys.ResultadosSinValidezSAP()); //Ejecuta la consulta y almacena el resultado en bajasExt
            if(sinValidez.next())                                                 //Valida que no se encuentre vacío el resutado de la consulta
            {
               
                sinValidez.beforeFirst();                                         //Regresa a la posición inicial del resultado
                modeloSinValidez.addColumn("Agregar");                            //Crea las columnas necesarias para el reporte
                modeloSinValidez.addColumn("Num Emp");
                modeloSinValidez.addColumn("Nombre");
                modeloSinValidez.addColumn("Perfil");
                modeloSinValidez.addColumn("Puesto");
                modeloSinValidez.addColumn("Creado el");
                modeloSinValidez.addColumn("Último acceso");
                
                modeloSinValidez.addColumn("NUMEROEMPLEADO");
                modeloSinValidez.addColumn("USERID");  
                modeloSinValidez.addColumn("NOMBRE");
                modeloSinValidez.addColumn("PUESTO");
                modeloSinValidez.addColumn("GERENCIA");
                modeloSinValidez.addColumn("ESTATUS");
                
                                                         
                while(sinValidez.next())                                          //Lee cada registro hasta que ya no haya más
                {
                    for(int k=1; k<14; k++)                                     //Recorre los elementos del registro para obtener cada dato de las columnas
                    {
                        if(k==1)
                        {
                            registro[k-1]=Boolean.TRUE;                         //Si está en la primer columna establece un valor TRUE para que el checkbox esté seleccionado
                        }
                        else
                        {
                            registro[k-1]=sinValidez.getString(k-1);
                        }
                    }
                    modeloSinValidez.addRow(registro);                      //Ya que todos los elementos del registro están en el arreglo se agrega el arreglo como un nuevo renglón de la tabla
                }
                tablaUsrSinVal.setModel(modeloSinValidez);                    //Una vez construida completamente la tabla se define el modelo a la tabla original
                tablaUsrSinVal.setAutoResizeMode(AUTO_RESIZE_OFF);                //Se desabilita el ajuste automatico de ancho de columnas para establecerlo manualmente
                tablaUsrSinVal.getColumnModel().getColumn(0).setCellEditor(new Clase_CellEditor());   //Se hace uso de editor y renderizador de columnas
                tablaUsrSinVal.getColumnModel().getColumn(0).setCellRenderer(new Clase_CellRender());

                 TableColumn CAgregar = tablaUsrSinVal.getColumn("Agregar");       //Se llama a la columna
                CAgregar.setPreferredWidth(55);                                 //Se define su tamaño
                TableColumn CNumEmp = tablaUsrSinVal.getColumn("Num Emp"); //Se llama a la columna
                CNumEmp.setPreferredWidth(140);                                 //Se define su tamaño
                TableColumn CNombre = tablaUsrSinVal.getColumn("Nombre");        //Se llama a la columna
                CNombre.setPreferredWidth(310);                                  //Se define su tamaño
                TableColumn CPerfil = tablaUsrSinVal.getColumn("Perfil");       //Se llama a la columna
                CPerfil.setPreferredWidth(150);                                  //Se define su tamaño
                TableColumn CPuesto = tablaUsrSinVal.getColumn("Puesto");        //Se llama a la columna
                CPuesto.setPreferredWidth(300);                                 //Se define su tamaño            
                TableColumn CCreado = tablaUsrSinVal.getColumn("Creado el");        //Se llama a la columna
                CCreado.setPreferredWidth(90);                                 //Se define su tamaño
                TableColumn CUltimoa = tablaUsrSinVal.getColumn("Último acceso");  //Se llama a la columna
                CUltimoa.setPreferredWidth(90);                              //Se define su tamaño
                
                TableColumn CNumemp = tablaUsrSinVal.getColumn("NUMEROEMPLEADO");    //Se llama a la columna
                CNumemp.setPreferredWidth(110);                              //Se define su tamaño                                                                         
                TableColumn CUserid = tablaUsrSinVal.getColumn("USERID");       //Se llama a la columna
                CUserid.setPreferredWidth(110);                                 //Se define su tamaño
                TableColumn CNombren = tablaUsrSinVal.getColumn("NOMBRE");   //Se llama a la columna
                CNombren.setPreferredWidth(330);                           //Se define su tamaño
                TableColumn CPueston = tablaUsrSinVal.getColumn("PUESTO");   //Se llama a la columna
                CPueston.setPreferredWidth(300);                           //Se define su tamaño
                TableColumn CGerencia = tablaUsrSinVal.getColumn("GERENCIA");   //Se llama a la columna
                CGerencia.setPreferredWidth(250);                           //Se define su tamaño
                TableColumn CEstatus = tablaUsrSinVal.getColumn("ESTATUS");   //Se llama a la columna
                CEstatus.setPreferredWidth(150);                                //Se define su tamaño
            }
            else
            {
                modeloSinValidez.addColumn("No se encontraron inconsistencias");
                tablaUsrSinVal.setModel(modeloSinValidez);
            }
            conLocal.Cerrar();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }  
        
        
    }
    
    void definirModelosValidez180()                                         //Crea el modelo para la tabla de inconsistencias para los usuarios externos inactivos
    {
        
        DefaultTableModel modeloValidez180 = new DefaultTableModel();       //Define el objeto modelo de tabla
        Object[] registro = new Object[16];                                     //Crea un arreglo para recibir los elementos de cada renglon
        
        
        try
        {
            Conexion conLocal = new Conexion();                                 //Crea la conexión para la consulta
            conLocal.AbrirLocal(cadenaBD);
            ExecQuery EjecutaLo = new ExecQuery();                              //Crea el objeto para ejecutar la consulta
            validez180 = EjecutaLo.Cons(conLocal.conexion, Monitoreos.Querys.ResultadosValidez180SAP()); //Ejecuta la consulta y almacena el resultado en bajasExt
            if(validez180.next())                                                 //Valida que no se encuentre vacío el resutado de la consulta
            {
               
                validez180.beforeFirst();                                         //Regresa a la posición inicial del resultado
                modeloValidez180.addColumn("Agregar");                            //Crea las columnas necesarias para el reporte
                modeloValidez180.addColumn("Num Emp");
                modeloValidez180.addColumn("Nombre");
                modeloValidez180.addColumn("Perfil");
                modeloValidez180.addColumn("Puesto");
                modeloValidez180.addColumn("Creado el");
                modeloValidez180.addColumn("Último acceso");
                
                modeloValidez180.addColumn("NUMEROEMPLEADO");
                modeloValidez180.addColumn("USERID");  
                modeloValidez180.addColumn("NOMBRE");
                modeloValidez180.addColumn("PUESTO");
                modeloValidez180.addColumn("GERENCIA");
                modeloValidez180.addColumn("ESTATUS");
                modeloValidez180.addColumn("VALIDO DE");
                modeloValidez180.addColumn("VALIDEZ A");
                modeloValidez180.addColumn("PERIODO");
                
                
                java.sql.ResultSetMetaData rsmd = validez180.getMetaData();
                int colNo = rsmd.getColumnCount();

                                                         
                while(validez180.next())                                          //Lee cada registro hasta que ya no haya más
                {
                    for(int k=1; k<=(colNo+1); k++)                                     //Recorre los elementos del registro para obtener cada dato de las columnas
                    {
                        
                        if(k==1)
                        {
                            registro[k-1]=Boolean.TRUE;                         //Si está en la primer columna establece un valor TRUE para que el checkbox esté seleccionado
                        }
                        else
                        {
                            registro[k-1]=validez180.getString(k-1);
                        }
                    }
                    modeloValidez180.addRow(registro);                      //Ya que todos los elementos del registro están en el arreglo se agrega el arreglo como un nuevo renglón de la tabla
                }
                tablaValidez180.setModel(modeloValidez180);                    //Una vez construida completamente la tabla se define el modelo a la tabla original
                tablaValidez180.setAutoResizeMode(AUTO_RESIZE_OFF);                //Se desabilita el ajuste automatico de ancho de columnas para establecerlo manualmente
                tablaValidez180.getColumnModel().getColumn(0).setCellEditor(new Clase_CellEditor());   //Se hace uso de editor y renderizador de columnas
                tablaValidez180.getColumnModel().getColumn(0).setCellRenderer(new Clase_CellRender());

                TableColumn CAgregar = tablaValidez180.getColumn("Agregar");       //Se llama a la columna
                CAgregar.setPreferredWidth(55);                                 //Se define su tamaño
                TableColumn CNumEmp = tablaValidez180.getColumn("Num Emp"); //Se llama a la columna
                CNumEmp.setPreferredWidth(140);                                 //Se define su tamaño
                TableColumn CNombre = tablaValidez180.getColumn("Nombre");        //Se llama a la columna
                CNombre.setPreferredWidth(310);                                  //Se define su tamaño
                TableColumn CPerfil = tablaValidez180.getColumn("Perfil");       //Se llama a la columna
                CPerfil.setPreferredWidth(150);                                  //Se define su tamaño
                TableColumn CPuesto = tablaValidez180.getColumn("Puesto");        //Se llama a la columna
                CPuesto.setPreferredWidth(300);                                 //Se define su tamaño            
                TableColumn CCreado = tablaValidez180.getColumn("Creado el");        //Se llama a la columna
                CCreado.setPreferredWidth(90);                                 //Se define su tamaño
                TableColumn CUltimoa = tablaValidez180.getColumn("Último acceso");  //Se llama a la columna
                CUltimoa.setPreferredWidth(90);                              //Se define su tamaño
                
                TableColumn CNumemp = tablaValidez180.getColumn("NUMEROEMPLEADO");    //Se llama a la columna
                CNumemp.setPreferredWidth(110);                              //Se define su tamaño                                                                         
                TableColumn CUserid = tablaValidez180.getColumn("USERID");       //Se llama a la columna
                CUserid.setPreferredWidth(110);                                 //Se define su tamaño
                TableColumn CNombren = tablaValidez180.getColumn("NOMBRE");   //Se llama a la columna
                CNombren.setPreferredWidth(330);                           //Se define su tamaño
                TableColumn CPueston = tablaValidez180.getColumn("PUESTO");   //Se llama a la columna
                CPueston.setPreferredWidth(300);                           //Se define su tamaño
                TableColumn CGerencia = tablaValidez180.getColumn("GERENCIA");   //Se llama a la columna
                CGerencia.setPreferredWidth(250);                           //Se define su tamaño
                TableColumn CEstatus = tablaValidez180.getColumn("ESTATUS");   //Se llama a la columna
                CEstatus.setPreferredWidth(150);                                //Se define su tamaño
            }
            else
            {
                modeloValidez180.addColumn("No se encontraron inconsistencias");
                tablaValidez180.setModel(modeloValidez180);
            }
            conLocal.Cerrar();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }  
        
        
    }
    
    
    void definirModelosPerfilIncorrectoSAP()                                               //Define el modelo de la tabla de inconsistencias de usuarios internos reportados como baja
    {
        DefaultTableModel modeloPerfilIncorrecto = new DefaultTableModel();             //Crea el objeto modelo de tabla
        
        
        
        Object[] registro = new Object[18];                                     //Crea un arreglo para recibir los elementos de cada renglon
        int i = 0;                                                              //Inicializa la variable para el contador
        try
        {
            Conexion conLocal = new Conexion();                                 //Inicia la conexión local
            conLocal.AbrirLocal(cadenaBD);
            ExecQuery EjecutaLo = new ExecQuery();                              //Crea el objeto para ejecutar la consulta
            perfilInc = EjecutaLo.Cons(conLocal.conexion, Monitoreos.Querys.ResultadosPerfilIncorrectoIntSAP());   //Ejecuta la consulta y almacena el resultado en la variable
            
            if(perfilInc.next())                                                 //Verifica que el resultado no esté vacío
            {
                modeloPerfilIncorrecto.addColumn("Agregar");                            //Crea las columnas necesarias para el reporte
                modeloPerfilIncorrecto.addColumn("NUM EMP");
                modeloPerfilIncorrecto.addColumn("NOMBRE");                
                modeloPerfilIncorrecto.addColumn("VÁLIDO DE");
                modeloPerfilIncorrecto.addColumn("FIN VALIDEZ");
                modeloPerfilIncorrecto.addColumn("PUESTO");
                modeloPerfilIncorrecto.addColumn("CLAVE DE PUESTO");
                modeloPerfilIncorrecto.addColumn("ID CLAVE DE PUESTO");
                modeloPerfilIncorrecto.addColumn("ID PUESTO");                
                modeloPerfilIncorrecto.addColumn("NUMEROEMPLEADO");
                modeloPerfilIncorrecto.addColumn("USERID");  
                modeloPerfilIncorrecto.addColumn("ID NOMBRE");
                modeloPerfilIncorrecto.addColumn("GERENCIA");
                modeloPerfilIncorrecto.addColumn("ESTATUS");
               
                perfilInc.beforeFirst();                                         //Regresa a la posición inicial del resultado
                while(perfilInc.next())                                          //Lee cada registro hasta que ya no haya más
                {
                    for(int k=1; k<14; k++)                                     
                    {
                        if(k==1)                                                
                        {
                            registro[k-1]=Boolean.TRUE;                         //Si está en la primer columna establece un valor TRUE para que el checkbox esté seleccionado
                        }
                        else
                        {
                            registro[k-1]=perfilInc.getString(k-1);              //Recorre los elementos del registro para obtener cada dato de las columnas                         
                        }
                    }
                    
                    modeloPerfilIncorrecto.addRow(registro);                            //Ya que todos los elementos del registro están en el arreglo se agrega el arreglo como un nuevo renglón de la tabla
                }

                tablaPerfilInt.setModel(modeloPerfilIncorrecto);                         //Una vez construida completamente la tabla se define el modelo a la tabla original
                tablaPerfilInt.setAutoResizeMode(AUTO_RESIZE_OFF);               //Se desabilita el ajuste automatico de ancho de columnas para establecerlo manualmente
                tablaPerfilInt.getColumnModel().getColumn(0).setCellEditor(new Clase_CellEditor());  //Se hace uso de editor y renderizador de columnas
                tablaPerfilInt.getColumnModel().getColumn(0).setCellRenderer(new Clase_CellRender());

                TableColumn CAgregar = tablaPerfilInt.getColumn("Agregar");      //Se llama a la columna
                CAgregar.setPreferredWidth(55);                                 //Se define su tamaño
                TableColumn CUsuario = tablaPerfilInt.getColumn("NUM EMP");    //Se llama a la columna
                CUsuario.setPreferredWidth(110);                                 //Se define su tamaño
                TableColumn CNombre = tablaPerfilInt.getColumn("NOMBRE");       //Se llama a la columna
                CNombre.setPreferredWidth(320);                                  //Se define su tamaño
                TableColumn CPerfil = tablaPerfilInt.getColumn("VÁLIDO DE");       //Se llama a la columna
                CPerfil.setPreferredWidth(150);                                  //Se define su tamaño
                TableColumn CPuesto = tablaPerfilInt.getColumn("FIN VALIDEZ");        //Se llama a la columna
                CPuesto.setPreferredWidth(150);                                 //Se define su tamaño            
                TableColumn CCreado = tablaPerfilInt.getColumn("PUESTO");        //Se llama a la columna
                CCreado.setPreferredWidth(440);                                 //Se define su tamaño
                TableColumn CValido = tablaPerfilInt.getColumn("CLAVE DE PUESTO");        //Se llama a la columna
                CValido.setPreferredWidth(110);                                 //Se define su tamaño
                TableColumn CFinV = tablaPerfilInt.getColumn("ID CLAVE DE PUESTO"); //Se llama a la columna
                CFinV.setPreferredWidth(150);                                  //Se define su tamaño
                TableColumn CUltimoa = tablaPerfilInt.getColumn("ID PUESTO");  //Se llama a la columna
                CUltimoa.setPreferredWidth(440);                              //Se define su tamaño                
                TableColumn CNumemp = tablaPerfilInt.getColumn("NUMEROEMPLEADO");    //Se llama a la columna
                CNumemp.setPreferredWidth(150);                              //Se define su tamaño                                                                         
                TableColumn CUserid = tablaPerfilInt.getColumn("USERID");       //Se llama a la columna
                CUserid.setPreferredWidth(100);                                 //Se define su tamaño
                TableColumn CNombren = tablaPerfilInt.getColumn("ID NOMBRE");   //Se llama a la columna
                CNombren.setPreferredWidth(300);                           //Se define su tamaño
                TableColumn CGerencia = tablaPerfilInt.getColumn("GERENCIA");   //Se llama a la columna
                CGerencia.setPreferredWidth(350);                           //Se define su tamaño
                TableColumn CEstatus = tablaPerfilInt.getColumn("ESTATUS");   //Se llama a la columna
                CEstatus.setPreferredWidth(150);                           //Se define su tamaño
                
                
            }
            else                                                                //Si el resultado se encontraba vacío
            {
                modeloPerfilIncorrecto.addColumn("No se encontraron inconsistencias");  //Se crea una columna con la leyecnda
                tablaPerfilInt.setModel(modeloPerfilIncorrecto);                         //Se define el modelo 
            }
            
            conLocal.Cerrar();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }        
    }
    
    
    void definirModelosPerfilNoAutorizadoSAP()                                               //Define el modelo de la tabla de inconsistencias de usuarios internos reportados como baja
    {
        DefaultTableModel modeloPerfilNoAutorizado = new DefaultTableModel();             //Crea el objeto modelo de tabla
        
        
        
        Object[] registro = new Object[18];                                     //Crea un arreglo para recibir los elementos de cada renglon
        int i = 0;                                                              //Inicializa la variable para el contador
        try
        {
            Conexion conLocal = new Conexion();                                 //Inicia la conexión local
            conLocal.AbrirLocal(cadenaBD);
            ExecQuery EjecutaLo = new ExecQuery();                              //Crea el objeto para ejecutar la consulta
            perfilInc = EjecutaLo.Cons(conLocal.conexion, Monitoreos.Querys.ResultadosPerfilNoAutorizadoIntSAP());   //Ejecuta la consulta y almacena el resultado en la variable
            
            if(perfilInc.next())                                                 //Verifica que el resultado no esté vacío
            {
                modeloPerfilNoAutorizado.addColumn("Agregar");                            //Crea las columnas necesarias para el reporte
                modeloPerfilNoAutorizado.addColumn("NUM EMP");
                modeloPerfilNoAutorizado.addColumn("NOMBRE");                
                modeloPerfilNoAutorizado.addColumn("VÁLIDO DE");
                modeloPerfilNoAutorizado.addColumn("FIN VALIDEZ");
                modeloPerfilNoAutorizado.addColumn("PUESTO");
                modeloPerfilNoAutorizado.addColumn("CLAVE DE PUESTO");
                modeloPerfilNoAutorizado.addColumn("ID CLAVE DE PUESTO");
                modeloPerfilNoAutorizado.addColumn("ID PUESTO");                
                modeloPerfilNoAutorizado.addColumn("NUMEROEMPLEADO");
                modeloPerfilNoAutorizado.addColumn("USERID");  
                modeloPerfilNoAutorizado.addColumn("ID NOMBRE");
                modeloPerfilNoAutorizado.addColumn("GERENCIA");
                modeloPerfilNoAutorizado.addColumn("ESTATUS");
               
                perfilInc.beforeFirst();                                         //Regresa a la posición inicial del resultado
                while(perfilInc.next())                                          //Lee cada registro hasta que ya no haya más
                {
                    for(int k=1; k<14; k++)                                     
                    {
                        if(k==1)                                                
                        {
                            registro[k-1]=Boolean.TRUE;                         //Si está en la primer columna establece un valor TRUE para que el checkbox esté seleccionado
                        }
                        else
                        {
                            registro[k-1]=perfilInc.getString(k-1);              //Recorre los elementos del registro para obtener cada dato de las columnas                         
                        }
                    }
                    
                    modeloPerfilNoAutorizado.addRow(registro);                            //Ya que todos los elementos del registro están en el arreglo se agrega el arreglo como un nuevo renglón de la tabla
                }

                tablaNoAutoInt.setModel(modeloPerfilNoAutorizado);                         //Una vez construida completamente la tabla se define el modelo a la tabla original
                tablaNoAutoInt.setAutoResizeMode(AUTO_RESIZE_OFF);               //Se desabilita el ajuste automatico de ancho de columnas para establecerlo manualmente
                tablaNoAutoInt.getColumnModel().getColumn(0).setCellEditor(new Clase_CellEditor());  //Se hace uso de editor y renderizador de columnas
                tablaNoAutoInt.getColumnModel().getColumn(0).setCellRenderer(new Clase_CellRender());

                TableColumn CAgregar = tablaNoAutoInt.getColumn("Agregar");      //Se llama a la columna
                CAgregar.setPreferredWidth(55);                                 //Se define su tamaño
                TableColumn CUsuario = tablaNoAutoInt.getColumn("NUM EMP");    //Se llama a la columna
                CUsuario.setPreferredWidth(110);                                 //Se define su tamaño
                TableColumn CNombre = tablaNoAutoInt.getColumn("NOMBRE");       //Se llama a la columna
                CNombre.setPreferredWidth(320);                                  //Se define su tamaño
                TableColumn CPerfil = tablaNoAutoInt.getColumn("VÁLIDO DE");       //Se llama a la columna
                CPerfil.setPreferredWidth(150);                                  //Se define su tamaño
                TableColumn CPuesto = tablaNoAutoInt.getColumn("FIN VALIDEZ");        //Se llama a la columna
                CPuesto.setPreferredWidth(150);                                 //Se define su tamaño            
                TableColumn CCreado = tablaNoAutoInt.getColumn("PUESTO");        //Se llama a la columna
                CCreado.setPreferredWidth(440);                                 //Se define su tamaño
                TableColumn CValido = tablaNoAutoInt.getColumn("CLAVE DE PUESTO");        //Se llama a la columna
                CValido.setPreferredWidth(110);                                 //Se define su tamaño
                TableColumn CFinV = tablaNoAutoInt.getColumn("ID CLAVE DE PUESTO"); //Se llama a la columna
                CFinV.setPreferredWidth(150);                                  //Se define su tamaño
                TableColumn CUltimoa = tablaNoAutoInt.getColumn("ID PUESTO");  //Se llama a la columna
                CUltimoa.setPreferredWidth(440);                              //Se define su tamaño                
                TableColumn CNumemp = tablaNoAutoInt.getColumn("NUMEROEMPLEADO");    //Se llama a la columna
                CNumemp.setPreferredWidth(150);                              //Se define su tamaño                                                                         
                TableColumn CUserid = tablaNoAutoInt.getColumn("USERID");       //Se llama a la columna
                CUserid.setPreferredWidth(100);                                 //Se define su tamaño
                TableColumn CNombren = tablaNoAutoInt.getColumn("ID NOMBRE");   //Se llama a la columna
                CNombren.setPreferredWidth(300);                           //Se define su tamaño
                TableColumn CGerencia = tablaNoAutoInt.getColumn("GERENCIA");   //Se llama a la columna
                CGerencia.setPreferredWidth(350);                           //Se define su tamaño
                TableColumn CEstatus = tablaNoAutoInt.getColumn("ESTATUS");   //Se llama a la columna
                CEstatus.setPreferredWidth(150);                           //Se define su tamaño
                
                
            }
            else                                                                //Si el resultado se encontraba vacío
            {
                modeloPerfilNoAutorizado.addColumn("No se encontraron inconsistencias");  //Se crea una columna con la leyecnda
                tablaNoAutoInt.setModel(modeloPerfilNoAutorizado);                         //Se define el modelo 
            }
            
            conLocal.Cerrar();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }        
    }
    
    
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel8 = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel9 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaBajasInt = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaBajasExt = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaInacInt = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaInacExt = new javax.swing.JTable();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablaUsrSinVal = new javax.swing.JTable();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tablaValidez180 = new javax.swing.JTable();
        jPanel17 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel28 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tablaDupInt = new javax.swing.JTable();
        jPanel18 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tablaDupExt = new javax.swing.JTable();
        jPanel19 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tablaPerfilInt = new javax.swing.JTable();
        jPanel23 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel31 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tablaCodLibera = new javax.swing.JTable();
        jPanel32 = new javax.swing.JPanel();
        jPanel34 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel36 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        tablaNoAutoInt = new javax.swing.JTable();
        jPanel38 = new javax.swing.JPanel();
        jPanel39 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel40 = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        tablaEliminado = new javax.swing.JTable();
        jScrollPane14 = new javax.swing.JScrollPane();
        tablaAgregado = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel53 = new javax.swing.JPanel();
        jPanel54 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jPanel55 = new javax.swing.JPanel();
        jScrollPane24 = new javax.swing.JScrollPane();
        tablaUsrGen = new javax.swing.JTable();
        jPanel41 = new javax.swing.JPanel();
        jPanel56 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane25 = new javax.swing.JScrollPane();
        tablaUsrAdm = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTabbedPane3.setName(""); // NOI18N

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/telcel.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel1.setPreferredSize(new java.awt.Dimension(1800, 551));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setMinimumSize(new java.awt.Dimension(1000, 350));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(1800, 350));

        tablaBajasInt.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jScrollPane1.setViewportView(tablaBajasInt);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1365, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 70, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1365, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Bajas internos", null, jPanel9, "Muestra los usuarios internos que deben ser reportados como baja,  ya sea por que su estatus en nómina es \"Eliminado\" o porque no aparecen en nómina");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/telcel.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(1009, Short.MAX_VALUE)
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

        tablaBajasExt.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
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
        jScrollPane2.setViewportView(tablaBajasExt);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 70, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Bajas externos", null, jPanel10, "Muestra los usuarios externos que deben ser reportados como baja,  ya sea por que su estatus en nómina es \"Eliminado\" o porque no aparecen en nómina");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/telcel.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(1009, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        tablaInacInt.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tablaInacInt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tablaInacInt);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 66, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Inactividad internos", null, jPanel3, "Muestra los usuarios internos que no han ingresado al aplicativo en un periodo mayor o igual a 60 días");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/telcel.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(1009, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        tablaInacExt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane4.setViewportView(tablaInacExt);

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4)
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 68, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Inactividad externos", null, jPanel6, "Muestra los usuarios externos que no han ingresado al aplicativo en un periodo mayor o igual a 60 días");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/telcel.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(1009, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        tablaUsrSinVal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane5.setViewportView(tablaUsrSinVal);

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5)
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Usuarios externos sin fin de validez", null, jPanel13, "Indica los usuarios externos que no tienen definida una fecha de término de su periodo de validez");

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/telcel.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(1009, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        tablaValidez180.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane6.setViewportView(tablaValidez180);

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6)
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Usuarios externos con validez diferente de 180 días", null, jPanel15, "Indica los usuarios externos que  tienen definido un periodo de validez mayor a 180 días.");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/telcel.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap(1009, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        tablaDupInt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane7.setViewportView(tablaDupInt);

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7)
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Duplicados por nombre internos", null, jPanel17, "Usuarios internos duplicados por nombre.");

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/telcel.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap(1009, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        tablaDupExt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane8.setViewportView(tablaDupExt);

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8)
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Duplicados por nombre externos", null, jPanel18, "Usuarios externos duplicados por nombre.");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/telcel.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addContainerGap(1009, Short.MAX_VALUE)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        tablaPerfilInt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane9.setViewportView(tablaPerfilInt);

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane9)
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 65, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Perfil incorrecto internos", null, jPanel19, "Usuarios internos en los que no corresponde el cóigo de puesto registrado en el aplicativo con el cóigo de puesto registrado en la nómina.");

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/telcel.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addContainerGap(1009, Short.MAX_VALUE)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        tablaCodLibera.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane10.setViewportView(tablaCodLibera);

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane10)
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Códigos de liberación no autorizados", jPanel23);

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/telcel.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel34Layout.createSequentialGroup()
                .addContainerGap(1009, Short.MAX_VALUE)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(585, 585, 585))
        );

        tablaNoAutoInt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane11.setViewportView(tablaNoAutoInt);

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane11)
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel36, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Usuarios internos con perfil no autorizado", null, jPanel32, "Usuarios internos que tienen registrados dos o más perfiles");

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/telcel.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel39Layout.createSequentialGroup()
                .addContainerGap(1009, Short.MAX_VALUE)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        tablaEliminado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane13.setViewportView(tablaEliminado);

        tablaAgregado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane14.setViewportView(tablaAgregado);

        jLabel14.setText("Usuarios administrdores agregados con respecto al monitoreo anterior");

        jLabel15.setText("Usuarios administrdores eliminados con respecto al monitoreo anterior");

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane13)
                    .addGroup(jPanel40Layout.createSequentialGroup()
                        .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel40Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane14)
                    .addContainerGap()))
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel40Layout.createSequentialGroup()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 283, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel40Layout.createSequentialGroup()
                    .addGap(21, 21, 21)
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(295, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel40, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Usuarios Administradores agregados y eliminados", null, jPanel38, "Usuarios administradores que al hacer la comparación con el registro de usuarios administraores del mes anterior fueron agregados o eliminados.");

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/telcel.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel54Layout = new javax.swing.GroupLayout(jPanel54);
        jPanel54.setLayout(jPanel54Layout);
        jPanel54Layout.setHorizontalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel54Layout.createSequentialGroup()
                .addContainerGap(1009, Short.MAX_VALUE)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel54Layout.setVerticalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel54Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        tablaUsrGen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane24.setViewportView(tablaUsrGen);

        javax.swing.GroupLayout jPanel55Layout = new javax.swing.GroupLayout(jPanel55);
        jPanel55.setLayout(jPanel55Layout);
        jPanel55Layout.setHorizontalGroup(
            jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane24)
        );
        jPanel55Layout.setVerticalGroup(
            jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel55Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane24, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel53Layout = new javax.swing.GroupLayout(jPanel53);
        jPanel53.setLayout(jPanel53Layout);
        jPanel53Layout.setHorizontalGroup(
            jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel54, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel55, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel53Layout.setVerticalGroup(
            jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel53Layout.createSequentialGroup()
                .addComponent(jPanel54, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel55, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Usuarios Genericos", null, jPanel53, "Usuarios genéricos no autorizados");

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/telcel.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel56Layout = new javax.swing.GroupLayout(jPanel56);
        jPanel56.setLayout(jPanel56Layout);
        jPanel56Layout.setHorizontalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel56Layout.createSequentialGroup()
                .addContainerGap(1009, Short.MAX_VALUE)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel56Layout.setVerticalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel56Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        tablaUsrAdm.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane25.setViewportView(tablaUsrAdm);

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel56, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane25)
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addComponent(jPanel56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane25, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(62, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Usuarios Administradores", null, jPanel41, "Usuarios administradores no autorizados.");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 737, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            java.util.logging.Logger.getLogger(Resultados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Resultados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Resultados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Resultados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Resultados(args[1]).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel53;
    private javax.swing.JPanel jPanel54;
    private javax.swing.JPanel jPanel55;
    private javax.swing.JPanel jPanel56;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane25;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTable tablaAgregado;
    private javax.swing.JTable tablaBajasExt;
    private javax.swing.JTable tablaBajasInt;
    private javax.swing.JTable tablaCodLibera;
    private javax.swing.JTable tablaDupExt;
    private javax.swing.JTable tablaDupInt;
    private javax.swing.JTable tablaEliminado;
    private javax.swing.JTable tablaInacExt;
    private javax.swing.JTable tablaInacInt;
    private javax.swing.JTable tablaNoAutoInt;
    private javax.swing.JTable tablaPerfilInt;
    private javax.swing.JTable tablaUsrAdm;
    private javax.swing.JTable tablaUsrGen;
    private javax.swing.JTable tablaUsrSinVal;
    private javax.swing.JTable tablaValidez180;
    // End of variables declaration//GEN-END:variables
}
