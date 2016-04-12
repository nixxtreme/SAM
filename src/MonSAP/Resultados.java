
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
    ResultSet bajasIntSAP, bajasExtSAP, usrInt, usrExt, inactInt, inactExt, dupInt, dupExt, perfilInt, perfilExt, noAutoInt, noAutoExt, agreg, elim, Uint, Uext, Ugen;       //Almacenan los resultados de cada consulta
  
    
    public Resultados(String cadena) {                                          //Inicializa la ventana y ejecuta los métodos para que se visualicen las tablas de resultados
        cadenaBD = cadena;                                                      //Almacena la cadena con los datos de conexión a la base de datos local
        initComponents();                                                       //Inicializa los componentes proncipales de la ventana
        definirModelosBajasIntSAP();                                               //Define el modelo de la tabla de inconsistencias de bajas internos
        definirModelosBajasExtSAP();                                               //Define el modelo de la tabla de inconsistencias de bajas externos
        definirModelosInactividadInt();                                         //Define el modelo de la tabla de inconsistencias de inactividad internos
        definirModelosInactividadExt();                                         //Define el modelo de la tabla de inconsistencias de inactividad externos
//        definirModelosUserIncInt();                                             //Define el modelo de la tabla de inconsistencias de UserID incorrecto internos
//        definirModelosUserIncExt();                                             //Define el modelo de la tabla de inconsistencias de UserID incorrecto externos
//        definirModelosDupXNomInt();                                             //Define el modelo de la tabla de inconsistencias de usuarios duplicados por nombre internos
//        definirModelosDupXNomExt();                                             //Define el modelo de la tabla de inconsistencias de usuarios duplicados por nombre externos
//        definirModelosPerfilIncInt();                                           //Define el modelo de la tabla de inconsistencias de usuarios internos con perfil incorrecto
//        definirModelosPerfilIncExt();                                           //Define el modelo de la tabla de inconsistencias de usuarios externos con perfil incorrecto
//        definirModelosNoAutorizadoInt();                                        //Define el modelo de la tabla de inconsistencias de usuarios internos no autorizados
//        definirModelosNoAutorizadoExt();                                        //Define el modelo de la tabla de inconsistencias de usuarios externos no autorizados
        definirModelosAgregados();                                              //Define el modelo de la tabla de usuarios agregados
        definirModelosEliminados();                                             //Define el modelo de la tabla de usuarios eliminados
        definirModelosUsuariosInt();
        definirModelosUsuariosExt();
        definirModelosUsuariosGen();
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
                modeloBajasInt.addColumn("Número de empleado");
                modeloBajasInt.addColumn("Nombre completo");
                modeloBajasInt.addColumn("Grupo");
                modeloBajasInt.addColumn("Valido de");
                modeloBajasInt.addColumn("Validez a");
                modeloBajasInt.addColumn("ID numero de empleado");
                modeloBajasInt.addColumn("ID usuario");
                modeloBajasInt.addColumn("ID nombre");
                modeloBajasInt.addColumn("Región");
                modeloBajasInt.addColumn("Gerencia");
                modeloBajasInt.addColumn("Departamento");
                modeloBajasInt.addColumn("ID Puesto");
                modeloBajasInt.addColumn("Puesto");
                modeloBajasInt.addColumn("Estatus");
                modeloBajasInt.addColumn("Fecha de baja");
               
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
                TableColumn CUsuario = tablaBajasInt.getColumn("Número de empleado");    //Se llama a la columna
                CUsuario.setPreferredWidth(150);                                 //Se define su tamaño
                TableColumn CNombre = tablaBajasInt.getColumn("Nombre completo");       //Se llama a la columna
                CNombre.setPreferredWidth(350);                                  //Se define su tamaño
                TableColumn CGpo = tablaBajasInt.getColumn("Grupo");        //Se llama a la columna
                CGpo.setPreferredWidth(85);                                 //Se define su tamaño            
                TableColumn CValido = tablaBajasInt.getColumn("Valido de");        //Se llama a la columna
                CValido.setPreferredWidth(85);                                 //Se define su tamaño
                TableColumn CValidez = tablaBajasInt.getColumn("Validez a");        //Se llama a la columna
                CValidez.setPreferredWidth(85);                                 //Se define su tamaño
                TableColumn CIDUsuario = tablaBajasInt.getColumn("ID usuario"); //Se llama a la columna
                CIDUsuario.setPreferredWidth(80);                                  //Se define su tamaño
                TableColumn CBBFNumEMP = tablaBajasInt.getColumn("ID numero de empleado");  //Se llama a la columna
                CBBFNumEMP.setPreferredWidth(150);                              //Se define su tamaño
                TableColumn CBBFNombre = tablaBajasInt.getColumn("ID nombre");    //Se llama a la columna
                CBBFNombre.setPreferredWidth(350);                              //Se define su tamaño
                TableColumn CRegion = tablaBajasInt.getColumn("Región");        //Se llama a la columna
                CRegion.setPreferredWidth(200);                                  //Se define su tamaño
                TableColumn CGerencia = tablaBajasInt.getColumn("Gerencia");        //Se llama a la columna
                CGerencia.setPreferredWidth(350);                                 //Se define su tamaño
                TableColumn CIP = tablaBajasInt.getColumn("Departamento");                //Se llama a la columna
                CIP.setPreferredWidth(350);                                     //Se define su tamaño
                TableColumn CIDPerfil = tablaBajasInt.getColumn("Puesto");   //Se llama a la columna
                CIDPerfil.setPreferredWidth(395);                                //Se define su tamaño
                 TableColumn CPerfil = tablaBajasInt.getColumn("ID Puesto");   //Se llama a la columna
                CPerfil.setPreferredWidth(155);                                //Se define su tamaño                                                             
                TableColumn Estatus = tablaBajasInt.getColumn("Estatus");       //Se llama a la columna
                Estatus.setPreferredWidth(115);                                 //Se define su tamaño
                TableColumn CBBFFechaBaja = tablaBajasInt.getColumn("Fecha de baja");   //Se llama a la columna
                CBBFFechaBaja.setPreferredWidth(140);                           //Se define su tamaño
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
                modeloBajasExt.addColumn("Número de empleado");
                modeloBajasExt.addColumn("Nombre completo");
                modeloBajasExt.addColumn("Grupo");
                modeloBajasExt.addColumn("Valido de");
                modeloBajasExt.addColumn("Validez a");
                modeloBajasExt.addColumn("ID numero de empleado");
                modeloBajasExt.addColumn("ID usuario");
                modeloBajasExt.addColumn("ID nombre");
                modeloBajasExt.addColumn("Región");
                modeloBajasExt.addColumn("Gerencia");
                modeloBajasExt.addColumn("Departamento");
                modeloBajasExt.addColumn("ID Puesto");
                modeloBajasExt.addColumn("Puesto");
                modeloBajasExt.addColumn("Estatus");
                modeloBajasExt.addColumn("Fecha de baja");
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
                TableColumn CUsuario = tablaBajasExt.getColumn("Número de empleado");    //Se llama a la columna
                CUsuario.setPreferredWidth(150);                                 //Se define su tamaño
                TableColumn CNombre = tablaBajasExt.getColumn("Nombre completo");       //Se llama a la columna
                CNombre.setPreferredWidth(350);                                  //Se define su tamaño
                TableColumn CGpo = tablaBajasExt.getColumn("Grupo");            //Se llama a la columna
                CGpo.setPreferredWidth(85);                                     //Se define su tamaño            
                TableColumn CValido = tablaBajasExt.getColumn("Valido de");        //Se llama a la columna
                CValido.setPreferredWidth(85);                                  //Se define su tamaño
                TableColumn CValidez = tablaBajasExt.getColumn("Validez a");        //Se llama a la columna
                CValidez.setPreferredWidth(85);                                 //Se define su tamaño
                TableColumn CIDUsuario = tablaBajasExt.getColumn("ID usuario"); //Se llama a la columna
                CIDUsuario.setPreferredWidth(80);                                  //Se define su tamaño
                TableColumn CBBFNumEMP = tablaBajasExt.getColumn("ID numero de empleado");  //Se llama a la columna
                CBBFNumEMP.setPreferredWidth(150);                              //Se define su tamaño
                TableColumn CBBFNombre = tablaBajasExt.getColumn("ID nombre");  //Se llama a la columna
                CBBFNombre.setPreferredWidth(350);                              //Se define su tamaño
                TableColumn CRegion = tablaBajasExt.getColumn("Región");        //Se llama a la columna
                CRegion.setPreferredWidth(200);                                 //Se define su tamaño
                TableColumn CGerencia = tablaBajasExt.getColumn("Gerencia");    //Se llama a la columna
                CGerencia.setPreferredWidth(350);                               //Se define su tamaño
                TableColumn CIP = tablaBajasExt.getColumn("Departamento");      //Se llama a la columna
                CIP.setPreferredWidth(350);                                     //Se define su tamaño
                TableColumn CIDPerfil = tablaBajasExt.getColumn("Puesto");      //Se llama a la columna
                CIDPerfil.setPreferredWidth(395);                               //Se define su tamaño
                 TableColumn CPerfil = tablaBajasExt.getColumn("ID Puesto");    //Se llama a la columna
                CPerfil.setPreferredWidth(155);                                 //Se define su tamaño                                                             
                TableColumn Estatus = tablaBajasExt.getColumn("Estatus");       //Se llama a la columna
                Estatus.setPreferredWidth(115);                                 //Se define su tamaño
                TableColumn CBBFFechaBaja = tablaBajasExt.getColumn("Fecha de baja");   //Se llama a la columna
                CBBFFechaBaja.setPreferredWidth(140);                              //Se define su tamaño
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
        Object[] registro = new Object[13];                                     //Crea un arreglo para recibir los elementos de cada renglon
        
        try
        {
            Conexion conLocal = new Conexion();                                 //Crea la conexión para la consulta
            conLocal.AbrirLocal(cadenaBD);
            ExecQuery EjecutaLo = new ExecQuery();                              //Crea el objeto para ejecutar la consulta
            inactInt = EjecutaLo.Cons(conLocal.conexion, Monitoreos.Querys.ResultadosInactividadIntSAP()); //Ejecuta la consulta y almacena el resultado en bajasExt
            if(inactInt.next())                                                 //Valida que no se encuentre vacío el resutado de la consulta
            {
                inactInt.beforeFirst();                                         //Regresa a la posición inicial del resultado
                modeloInactividadInt.addColumn("Agregar");
                modeloInactividadInt.addColumn("Numero Empleado");
                modeloInactividadInt.addColumn("Usuario");
                modeloInactividadInt.addColumn("Nombre Completo");
                modeloInactividadInt.addColumn("Region");
                modeloInactividadInt.addColumn("Gerencia");
                modeloInactividadInt.addColumn("Departamento");
                modeloInactividadInt.addColumn("Puesto");
                modeloInactividadInt.addColumn("Creado Por");
                modeloInactividadInt.addColumn("Fecha Creacion");
                modeloInactividadInt.addColumn("Entrada Sistema");
                modeloInactividadInt.addColumn("Clave Acc");
                
                while(inactInt.next())                                          //Lee cada registro hasta que ya no haya más
                {
                    for(int k=1; k<13; k++)                                     //Recorre los elementos del registro para obtener cada dato de las columnas
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
                TableColumn CNumEmp = tablaInacInt.getColumn("Número Empleado"); //Se llama a la columna
                CNumEmp.setPreferredWidth(140);                                 //Se define su tamaño
                TableColumn CUserID = tablaInacInt.getColumn("Usuario");        //Se llama a la columna
                CUserID.setPreferredWidth(70);                                  //Se define su tamaño
                TableColumn CNombre = tablaInacInt.getColumn("Nombre Completo");         //Se llama a la columna
                CNombre.setPreferredWidth(360);                                 //Se define su tamaño
                TableColumn CRegion = tablaInacInt.getColumn("Region");  //Se llama a la columna
                CRegion.setPreferredWidth(160);                                  //Se define su tamaño
                TableColumn CGerencia = tablaInacInt.getColumn("Gerencia");         //Se llama a la columna
                CGerencia.setPreferredWidth(70);                                  //Se define su tamaño
                TableColumn CDep = tablaInacInt.getColumn("Departamento");                 //Se llama a la columna
                CDep.setPreferredWidth(120);                                     //Se define su tamaño
                TableColumn CPuesto = tablaInacInt.getColumn("Puesto");    //Se llama a la columna
                CPuesto.setPreferredWidth(85);                                //Se define su tamaño
                TableColumn CCreado = tablaInacInt.getColumn("Creado por");         //Se llama a la columna
                CCreado.setPreferredWidth(400);                                 //Se define su tamaño
                TableColumn CCreacion = tablaInacInt.getColumn("Fecha Creacion");         //Se llama a la columna
                CCreacion.setPreferredWidth(400);                                 //Se define su tamaño
                TableColumn CEntrada = tablaInacInt.getColumn("Entrada Sistema");         //Se llama a la columna
                CEntrada.setPreferredWidth(400);                                 //Se define su tamaño
                TableColumn CClave = tablaInacInt.getColumn("Clave Acc");         //Se llama a la columna
                CClave.setPreferredWidth(400);                                 //Se define su tamaño
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
        Object[] registro = new Object[13];                                     //Crea un arreglo para recibir los elementos de cada renglon
        
        
        try
        {
            Conexion conLocal = new Conexion();                                 //Crea la conexión para la consulta
            conLocal.AbrirLocal(cadenaBD);
            ExecQuery EjecutaLo = new ExecQuery();                              //Crea el objeto para ejecutar la consulta
            inactExt = EjecutaLo.Cons(conLocal.conexion, Monitoreos.Querys.ResultadosInactividadExtSAP()); //Ejecuta la consulta y almacena el resultado en bajasExt
            if(inactExt.next())                                                 //Valida que no se encuentre vacío el resutado de la consulta
            {
                inactExt.beforeFirst();                                         //Regresa a la posición inicial del resultado
                modeloInactividadExt.addColumn("Agregar");
                modeloInactividadExt.addColumn("Numero Empleado");
                modeloInactividadExt.addColumn("Usuario");
                modeloInactividadExt.addColumn("Nombre Completo");
                modeloInactividadExt.addColumn("Region");
                modeloInactividadExt.addColumn("Gerencia");
                modeloInactividadExt.addColumn("Departamento");
                modeloInactividadExt.addColumn("Puesto");
                modeloInactividadExt.addColumn("Creado Por");
                modeloInactividadExt.addColumn("Fecha Creacion");
                modeloInactividadExt.addColumn("Entrada Sistema");
                modeloInactividadExt.addColumn("Clave Acc");
                
                while(inactExt.next())                                          //Lee cada registro hasta que ya no haya más
                {
                    for(int k=1; k<13; k++)                                     //Recorre los elementos del registro para obtener cada dato de las columnas
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
                TableColumn CNumEmp = tablaInacExt.getColumn("Número Empleado"); //Se llama a la columna
                CNumEmp.setPreferredWidth(140);                                 //Se define su tamaño
                TableColumn CUserID = tablaInacExt.getColumn("Usuario");        //Se llama a la columna
                CUserID.setPreferredWidth(70);                                  //Se define su tamaño
                TableColumn CNombre = tablaInacExt.getColumn("Nombre Completo");         //Se llama a la columna
                CNombre.setPreferredWidth(360);                                 //Se define su tamaño
                TableColumn CRegion = tablaInacExt.getColumn("Region");  //Se llama a la columna
                CRegion.setPreferredWidth(160);                                  //Se define su tamaño
                TableColumn CGerencia = tablaInacExt.getColumn("Gerencia");         //Se llama a la columna
                CGerencia.setPreferredWidth(70);                                  //Se define su tamaño
                TableColumn CDep = tablaInacExt.getColumn("Departamento");                 //Se llama a la columna
                CDep.setPreferredWidth(120);                                     //Se define su tamaño
                TableColumn CPuesto = tablaInacExt.getColumn("Puesto");    //Se llama a la columna
                CPuesto.setPreferredWidth(85);                                //Se define su tamaño
                TableColumn CCreado = tablaInacExt.getColumn("Creado por");         //Se llama a la columna
                CCreado.setPreferredWidth(400);                                 //Se define su tamaño
                TableColumn CCreacion = tablaInacExt.getColumn("Fecha Creacion");         //Se llama a la columna
                CCreacion.setPreferredWidth(400);                                 //Se define su tamaño
                TableColumn CEntrada = tablaInacExt.getColumn("Entrada Sistema");         //Se llama a la columna
                CEntrada.setPreferredWidth(400);                                 //Se define su tamaño
                TableColumn CClave = tablaInacExt.getColumn("Clave Acc");         //Se llama a la columna
                CClave.setPreferredWidth(400);                                 //Se define su tamaño
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

    
    
    void definirModelosUserIncInt()                                             //Crea el modelo para la tabla de inconsistencias de usuarios internos con UserID incorrecto
    {
        
        DefaultTableModel modeloUsuarioIncInt = new DefaultTableModel();        //Define el objeto modelo de tabla
        Object[] registro = new Object[16];                                     //Crea un arreglo para recibir los elementos de cada renglon
        
        
        try
        {
            Conexion conLocal = new Conexion();                                 //Crea la conexión para la consulta
            conLocal.AbrirLocal(cadenaBD);
            ExecQuery EjecutaLo = new ExecQuery();                              //Crea el objeto para ejecutar la consulta
            usrInt = EjecutaLo.Cons(conLocal.conexion, Monitoreos.Querys.ResultadosUserIncInt());   //Ejecuta la consulta y almacena el resultado en bajasExt
            if(usrInt.next())                                                   //Valida que no se encuentre vacío el resutado de la consulta
            {
                usrInt.beforeFirst();                                           //Regresa a la posición inicial del resultado
                modeloUsuarioIncInt.addColumn("Agregar");
                modeloUsuarioIncInt.addColumn("Número de empleado");
                modeloUsuarioIncInt.addColumn("User ID");
                modeloUsuarioIncInt.addColumn("Nombre");
                modeloUsuarioIncInt.addColumn("Fecha de último acceso");
                modeloUsuarioIncInt.addColumn("Región");
                modeloUsuarioIncInt.addColumn("IP");
                modeloUsuarioIncInt.addColumn("ID perfil");
                modeloUsuarioIncInt.addColumn("Perfil");
                modeloUsuarioIncInt.addColumn("ID Número de empleado");
                modeloUsuarioIncInt.addColumn("User ID correcto");
                modeloUsuarioIncInt.addColumn("Puesto");
                modeloUsuarioIncInt.addColumn("Gerencia");
                modeloUsuarioIncInt.addColumn("ID Nombre");
                while(usrInt.next())                                            //Lee cada registro hasta que ya no haya más
                {
                    for(int k=1; k<15; k++)                                     //Recorre los elementos del registro para obtener cada dato de las columnas
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
                    modeloUsuarioIncInt.addRow(registro);                       //Ya que todos los elementos del registro están en el arreglo se agrega el arreglo como un nuevo renglón de la tabla
                }
                tablaUsrInt.setModel(modeloUsuarioIncInt);                      //Una vez construida completamente la tabla se define el modelo a la tabla original
                tablaUsrInt.setAutoResizeMode(AUTO_RESIZE_OFF);                 //Se desabilita el ajuste automatico de ancho de columnas para establecerlo manualmente
                tablaUsrInt.getColumnModel().getColumn(0).setCellEditor(new Clase_CellEditor());    //Se hace uso de editor y renderizador de columnas
                tablaUsrInt.getColumnModel().getColumn(0).setCellRenderer(new Clase_CellRender());

                TableColumn CAgregar = tablaUsrInt.getColumn("Agregar");        //Se llama a la columna
                CAgregar.setPreferredWidth(55);                                 //Se define su tamaño
                TableColumn CNumEmp = tablaUsrInt.getColumn("Número de empleado");  //Se llama a la columna
                CNumEmp.setPreferredWidth(140);                                 //Se define su tamaño
                TableColumn CUserID = tablaUsrInt.getColumn("User ID");         //Se llama a la columna
                CUserID.setPreferredWidth(70);                                  //Se define su tamaño
                TableColumn CNombre = tablaUsrInt.getColumn("Nombre");          //Se llama a la columna
                CNombre.setPreferredWidth(360);                                 //Se define su tamaño
                TableColumn CFecha = tablaUsrInt.getColumn("Fecha de último acceso");   //Se llama a la columna
                CFecha.setPreferredWidth(160);                                  //Se define su tamaño
                TableColumn CRegion = tablaUsrInt.getColumn("Región");          //Se llama a la columna
                CRegion.setPreferredWidth(70);                                  //Se define su tamaño
                TableColumn CIP = tablaUsrInt.getColumn("IP");                  //Se llama a la columna
                CIP.setPreferredWidth(120);                                     //Se define su tamaño
                TableColumn CIDPerfil = tablaUsrInt.getColumn("ID perfil");     //Se llama a la columna
                CIDPerfil.setPreferredWidth(85);                                //Se define su tamaño
                TableColumn CPerfil = tablaUsrInt.getColumn("Perfil");          //Se llama a la columna
                CPerfil.setPreferredWidth(400);                                 //Se define su tamaño
                TableColumn CIDNUM = tablaUsrInt.getColumn("ID Número de empleado");    //Se llama a la columna
                CIDNUM.setPreferredWidth(140);                                  //Se define su tamaño
                TableColumn CUSEROK = tablaUsrInt.getColumn("User ID correcto");    //Se llama a la columna
                CUSEROK.setPreferredWidth(90);                                  //Se define su tamaño
                TableColumn CPUESTO = tablaUsrInt.getColumn("Puesto");          //Se llama a la columna
                CPUESTO.setPreferredWidth(400);                                 //Se define su tamaño
                TableColumn CGERENCIA = tablaUsrInt.getColumn("Gerencia");      //Se llama a la columna
                CGERENCIA.setPreferredWidth(400);                               //Se define su tamaño
                TableColumn CIDNOMBRE = tablaUsrInt.getColumn("ID Nombre");     //Se llama a la columna
                CIDNOMBRE.setPreferredWidth(360);                               //Se define su tamaño
                
                
              
            }
            else
            {
                modeloUsuarioIncInt.addColumn("No se encontraron inconsistencias");
                tablaUsrInt.setModel(modeloUsuarioIncInt);
            }
            conLocal.Cerrar();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }  
        
        
    }
    
    
    void definirModelosUserIncExt()                                             //crea el modelo para la tabla de inconsistencias de los usuarios externos con UserID incorrecto
    {
        
        DefaultTableModel modeloUsuarioIncExt = new DefaultTableModel();        //Define el objeto modelo de tabla
        Object[] registro = new Object[16];                                     //Crea un arreglo para recibir los elementos de cada renglón
        
        
        try
        {
            Conexion conLocal = new Conexion();                                 //Crea la conexión para la consulta
            conLocal.AbrirLocal(cadenaBD);
            ExecQuery EjecutaLo = new ExecQuery();                              //Crea el objeto para ejecutar la consulta
            usrExt = EjecutaLo.Cons(conLocal.conexion, Monitoreos.Querys.ResultadosUserIncExt());   //Ejecuta la consulta y almacena el resultado en bajasExt
            if(usrExt.next())                                                   //Valida que no se encuentre vacío el resutado de la consulta
            {
                usrExt.beforeFirst();                                           //Regresa a la posición inicial del resultado
                modeloUsuarioIncExt.addColumn("Agregar");
                modeloUsuarioIncExt.addColumn("Número de empleado");
                modeloUsuarioIncExt.addColumn("User ID");
                modeloUsuarioIncExt.addColumn("Nombre");
                modeloUsuarioIncExt.addColumn("Fecha de último acceso");
                modeloUsuarioIncExt.addColumn("Región");
                modeloUsuarioIncExt.addColumn("IP");
                modeloUsuarioIncExt.addColumn("ID perfil");
                modeloUsuarioIncExt.addColumn("Perfil");
                modeloUsuarioIncExt.addColumn("ID Número de empleado");
                modeloUsuarioIncExt.addColumn("User ID correcto");
                modeloUsuarioIncExt.addColumn("Puesto");
                modeloUsuarioIncExt.addColumn("Gerencia");
                modeloUsuarioIncExt.addColumn("ID Nombre");
                while(usrExt.next())                                            //Lee cada registro hasta que ya no haya más
                {
                    for(int k=1; k<15; k++)                                     //Recorre los elementos del registro para obtener cada dato de las columnas
                    {
                        if(k==1)
                        {
                            registro[k-1]=Boolean.TRUE;                         //Si está en la primer columna establece un valor TRUE para que el checkbox esté seleccionado
                        }
                        else
                        {
                            registro[k-1]=usrExt.getString(k-1);
                        }
                    }
                    modeloUsuarioIncExt.addRow(registro);                       //Ya que todos los elementos del registro están en el arreglo se agrega el arreglo como un nuevo renglón de la tabla
                }
                tablaUsrExt.setModel(modeloUsuarioIncExt);                      //Una vez construida completamente la tabla se define el modelo a la tabla original
                tablaUsrExt.setAutoResizeMode(AUTO_RESIZE_OFF);                 //Se desabilita el ajuste automatico de ancho de columnas para establecerlo manualmente
                tablaUsrExt.getColumnModel().getColumn(0).setCellEditor(new Clase_CellEditor());    //Se hace uso de editor y renderizador de columnas
                tablaUsrExt.getColumnModel().getColumn(0).setCellRenderer(new Clase_CellRender());

                TableColumn CAgregar = tablaUsrExt.getColumn("Agregar");        //Se llama a la columna
                CAgregar.setPreferredWidth(55);                                 //Se define su tamaño
                TableColumn CNumEmp = tablaUsrExt.getColumn("Número de empleado");  //Se llama a la columna
                CNumEmp.setPreferredWidth(140);                                 //Se define su tamaño
                TableColumn CUserID = tablaUsrExt.getColumn("User ID");         //Se llama a la columna
                CUserID.setPreferredWidth(70);                                  //Se define su tamaño
                TableColumn CNombre = tablaUsrExt.getColumn("Nombre");          //Se llama a la columna
                CNombre.setPreferredWidth(360);                                 //Se define su tamaño
                TableColumn CFecha = tablaUsrExt.getColumn("Fecha de último acceso");   //Se llama a la columna//Se llama a la columna
                CFecha.setPreferredWidth(160);                                  //Se define su tamaño
                TableColumn CRegion = tablaUsrExt.getColumn("Región");          //Se llama a la columna
                CRegion.setPreferredWidth(70);                                  //Se define su tamaño
                TableColumn CIP = tablaUsrExt.getColumn("IP");                  //Se llama a la columna
                CIP.setPreferredWidth(120);                                     //Se define su tamaño
                TableColumn CIDPerfil = tablaUsrExt.getColumn("ID perfil");     //Se llama a la columna
                CIDPerfil.setPreferredWidth(85);                                //Se define su tamaño
                TableColumn CPerfil = tablaUsrExt.getColumn("Perfil");          //Se llama a la columna
                CPerfil.setPreferredWidth(400);                                 //Se define su tamaño
                TableColumn CIDNUM = tablaUsrExt.getColumn("ID Número de empleado");    //Se llama a la columna
                CIDNUM.setPreferredWidth(140);                                  //Se define su tamaño
                TableColumn CUSEROK = tablaUsrExt.getColumn("User ID correcto");    //Se llama a la columna
                CUSEROK.setPreferredWidth(90);                                  //Se define su tamaño
                TableColumn CPUESTO = tablaUsrExt.getColumn("Puesto");          //Se llama a la columna
                CPUESTO.setPreferredWidth(400);                                 //Se define su tamaño
                TableColumn CGERENCIA = tablaUsrExt.getColumn("Gerencia");      //Se llama a la columna
                CGERENCIA.setPreferredWidth(400);                               //Se define su tamaño
                TableColumn CIDNOMBRE = tablaUsrExt.getColumn("ID Nombre");     //Se llama a la columna
                CIDNOMBRE.setPreferredWidth(360);                               //Se define su tamaño
                
                
              
            }
            else
            {
                modeloUsuarioIncExt.addColumn("No se encontraron inconsistencias");
                tablaUsrExt.setModel(modeloUsuarioIncExt);
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
        
        DefaultTableModel modeloUsuarioDupXNomInt = new DefaultTableModel();    //Define el objeto modelo de tabla
        Object[] registro = new Object[16];                                     //Crea un arreglo para recibir los elementos de cada renglon
        
        
        try
        {
            Conexion conLocal = new Conexion();                                 //Crea la conexión para la consulta
            conLocal.AbrirLocal(cadenaBD);  
            ExecQuery EjecutaLo = new ExecQuery();                              //Crea el objeto para ejecutar la consulta
            dupInt = EjecutaLo.Cons(conLocal.conexion, Monitoreos.Querys.ResultadosDuplicadosXNomInt());    //Ejecuta la consulta y almacena el resultado en bajasExt
            if(dupInt.next())                                                   //Valida que no se encuentre vacío el resutado de la consulta
            {
                dupInt.beforeFirst();                                           //Regresa a la posición inicial del resultado
                modeloUsuarioDupXNomInt.addColumn("Agregar");
                modeloUsuarioDupXNomInt.addColumn("Número de empleado");
                modeloUsuarioDupXNomInt.addColumn("User ID");
                modeloUsuarioDupXNomInt.addColumn("Nombre");
                modeloUsuarioDupXNomInt.addColumn("Fecha de último acceso");
                modeloUsuarioDupXNomInt.addColumn("Región");
                modeloUsuarioDupXNomInt.addColumn("IP");
                modeloUsuarioDupXNomInt.addColumn("ID perfil");
                modeloUsuarioDupXNomInt.addColumn("Perfil");
                modeloUsuarioDupXNomInt.addColumn("ID Número de empleado");
                modeloUsuarioDupXNomInt.addColumn("User ID correcto");
                modeloUsuarioDupXNomInt.addColumn("Puesto");
                modeloUsuarioDupXNomInt.addColumn("Gerencia");
                modeloUsuarioDupXNomInt.addColumn("ID Nombre");
                while(dupInt.next())                                            //Lee cada registro hasta que ya no haya más
                {
                    for(int k=1; k<15; k++)                                     //Recorre los elementos del registro para obtener cada dato de las columnas
                    {
                        if(k==1)
                        {
                            registro[k-1]=Boolean.TRUE;                         //Si está en la primer columna establece un valor TRUE para que el checkbox esté seleccionado
                        }
                        else
                        {
                            registro[k-1]=dupInt.getString(k-1);
                        }
                    }
                    modeloUsuarioDupXNomInt.addRow(registro);                   //Ya que todos los elementos del registro están en el arreglo se agrega el arreglo como un nuevo renglón de la tabla
                }
                tablaDupInt.setModel(modeloUsuarioDupXNomInt);                  //Una vez construida completamente la tabla se define el modelo a la tabla original
                tablaDupInt.setAutoResizeMode(AUTO_RESIZE_OFF);                 //Se desabilita el ajuste automatico de ancho de columnas para establecerlo manualmente
                tablaDupInt.getColumnModel().getColumn(0).setCellEditor(new Clase_CellEditor());    //Se hace uso de editor y renderizador de columnas
                tablaDupInt.getColumnModel().getColumn(0).setCellRenderer(new Clase_CellRender());

                TableColumn CAgregar = tablaDupInt.getColumn("Agregar");        //Se llama a la columna
                CAgregar.setPreferredWidth(55);                                 //Se define su tamaño
                TableColumn CNumEmp = tablaDupInt.getColumn("Número de empleado");  //Se llama a la columna
                CNumEmp.setPreferredWidth(140);                                 //Se define su tamaño
                TableColumn CUserID = tablaDupInt.getColumn("User ID");         //Se llama a la columna
                CUserID.setPreferredWidth(70);                                  //Se define su tamaño
                TableColumn CNombre = tablaDupInt.getColumn("Nombre");          //Se llama a la columna
                CNombre.setPreferredWidth(360);                                 //Se define su tamaño
                TableColumn CFecha = tablaDupInt.getColumn("Fecha de último acceso");   //Se llama a la columna
                CFecha.setPreferredWidth(160);                                  //Se define su tamaño
                TableColumn CRegion = tablaDupInt.getColumn("Región");          //Se llama a la columna
                CRegion.setPreferredWidth(70);                                  //Se define su tamaño
                TableColumn CIP = tablaDupInt.getColumn("IP");                  //Se llama a la columna
                CIP.setPreferredWidth(120);                                     //Se define su tamaño
                TableColumn CIDPerfil = tablaDupInt.getColumn("ID perfil");     //Se llama a la columna
                CIDPerfil.setPreferredWidth(85);                                //Se define su tamaño
                TableColumn CPerfil = tablaDupInt.getColumn("Perfil");          //Se llama a la columna
                CPerfil.setPreferredWidth(400);                                 //Se define su tamaño
                TableColumn CIDNUM = tablaDupInt.getColumn("ID Número de empleado");    //Se llama a la columna
                CIDNUM.setPreferredWidth(140);                                  //Se define su tamaño
                TableColumn CUSEROK = tablaDupInt.getColumn("User ID correcto");    //Se llama a la columna
                CUSEROK.setPreferredWidth(90);                                  //Se define su tamaño
                TableColumn CPUESTO = tablaDupInt.getColumn("Puesto");          //Se llama a la columna
                CPUESTO.setPreferredWidth(400);                                 //Se define su tamaño
                TableColumn CGERENCIA = tablaDupInt.getColumn("Gerencia");      //Se llama a la columna
                CGERENCIA.setPreferredWidth(400);                               //Se define su tamaño
                TableColumn CIDNOMBRE = tablaDupInt.getColumn("ID Nombre");     //Se llama a la columna
                CIDNOMBRE.setPreferredWidth(360);                               //Se define su tamaño
                
                
              
            }
            else
            {
                modeloUsuarioDupXNomInt.addColumn("No se encontraron inconsistencias");
                tablaDupInt.setModel(modeloUsuarioDupXNomInt);
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
        
        DefaultTableModel modeloUsuarioDupXNomExt = new DefaultTableModel();    //Define el objeto modelo de tabla
        Object[] registro = new Object[16];                                     //Crea un arreglo para recibir los elementos de cada renglon
        
        
        try
        {
            Conexion conLocal = new Conexion();                                 //Crea la conexión para la consulta
            conLocal.AbrirLocal(cadenaBD);
            ExecQuery EjecutaLo = new ExecQuery();                              //Crea el objeto para ejecutar la consulta
            dupExt = EjecutaLo.Cons(conLocal.conexion, Monitoreos.Querys.ResultadosDuplicadosXNomExt());    //Ejecuta la consulta y almacena el resultado en bajasExt
            if(dupExt.next())                                                   //Valida que no se encuentre vacío el resutado de la consulta
            {
                dupExt.beforeFirst();                                           //Regresa a la posición inicial del resultado
                modeloUsuarioDupXNomExt.addColumn("Agregar");
                modeloUsuarioDupXNomExt.addColumn("Número de empleado");
                modeloUsuarioDupXNomExt.addColumn("User ID");
                modeloUsuarioDupXNomExt.addColumn("Nombre");
                modeloUsuarioDupXNomExt.addColumn("Fecha de último acceso");
                modeloUsuarioDupXNomExt.addColumn("Región");
                modeloUsuarioDupXNomExt.addColumn("IP");
                modeloUsuarioDupXNomExt.addColumn("ID perfil");
                modeloUsuarioDupXNomExt.addColumn("Perfil");
                modeloUsuarioDupXNomExt.addColumn("ID Número de empleado");
                modeloUsuarioDupXNomExt.addColumn("User ID correcto");
                modeloUsuarioDupXNomExt.addColumn("Puesto");
                modeloUsuarioDupXNomExt.addColumn("Gerencia");
                modeloUsuarioDupXNomExt.addColumn("ID Nombre");
                while(dupExt.next())                                            //Lee cada registro hasta que ya no haya más
                {
                    for(int k=1; k<15; k++)                                     //Recorre los elementos del registro para obtener cada dato de las columnas
                    {
                        if(k==1)
                        {
                            registro[k-1]=Boolean.TRUE;                         //Si está en la primer columna establece un valor TRUE para que el checkbox esté seleccionado
                        }
                        else
                        {
                            registro[k-1]=dupExt.getString(k-1);
                        }
                    }
                    modeloUsuarioDupXNomExt.addRow(registro);                   //Ya que todos los elementos del registro están en el arreglo se agrega el arreglo como un nuevo renglón de la tabla
                }
                tablaDupExt.setModel(modeloUsuarioDupXNomExt);                  //Una vez construida completamente la tabla se define el modelo a la tabla original
                tablaDupExt.setAutoResizeMode(AUTO_RESIZE_OFF);                 //Se desabilita el ajuste automatico de ancho de columnas para establecerlo manualmente
                tablaDupExt.getColumnModel().getColumn(0).setCellEditor(new Clase_CellEditor());    //Se hace uso de editor y renderizador de columnas
                tablaDupExt.getColumnModel().getColumn(0).setCellRenderer(new Clase_CellRender());

                TableColumn CAgregar = tablaDupExt.getColumn("Agregar");        //Se llama a la columna
                CAgregar.setPreferredWidth(55);                                 //Se define su tamaño
                TableColumn CNumEmp = tablaDupExt.getColumn("Número de empleado");      //Se llama a la columna
                CNumEmp.setPreferredWidth(140);                                 //Se define su tamaño
                TableColumn CUserID = tablaDupExt.getColumn("User ID");         //Se llama a la columna
                CUserID.setPreferredWidth(70);                                  //Se define su tamaño
                TableColumn CNombre = tablaDupExt.getColumn("Nombre");          //Se llama a la columna
                CNombre.setPreferredWidth(360);                                 //Se define su tamaño
                TableColumn CFecha = tablaDupExt.getColumn("Fecha de último acceso");   //Se llama a la columna
                CFecha.setPreferredWidth(160);                                  //Se define su tamaño
                TableColumn CRegion = tablaDupExt.getColumn("Región");          //Se llama a la columna
                CRegion.setPreferredWidth(70);                                  //Se define su tamaño
                TableColumn CIP = tablaDupExt.getColumn("IP");                  //Se llama a la columna
                CIP.setPreferredWidth(120);                                     //Se define su tamaño
                TableColumn CIDPerfil = tablaDupExt.getColumn("ID perfil");     //Se llama a la columna
                CIDPerfil.setPreferredWidth(85);                                //Se define su tamaño
                TableColumn CPerfil = tablaDupExt.getColumn("Perfil");          //Se llama a la columna
                CPerfil.setPreferredWidth(400);                                 //Se define su tamaño
                TableColumn CIDNUM = tablaDupExt.getColumn("ID Número de empleado");    //Se llama a la columna
                CIDNUM.setPreferredWidth(140);                                  //Se define su tamaño
                TableColumn CUSEROK = tablaDupExt.getColumn("User ID correcto");    //Se llama a la columna
                CUSEROK.setPreferredWidth(90);                                  //Se define su tamaño
                TableColumn CPUESTO = tablaDupExt.getColumn("Puesto");          //Se llama a la columna
                CPUESTO.setPreferredWidth(400);                                 //Se define su tamaño
                TableColumn CGERENCIA = tablaDupExt.getColumn("Gerencia");      //Se llama a la columna
                CGERENCIA.setPreferredWidth(400);                               //Se define su tamaño
                TableColumn CIDNOMBRE = tablaDupExt.getColumn("ID Nombre");     //Se llama a la columna
                CIDNOMBRE.setPreferredWidth(360);                               //Se define su tamaño
                
                
              
            }
            else
            {
                modeloUsuarioDupXNomExt.addColumn("No se encontraron inconsistencias");
                tablaDupExt.setModel(modeloUsuarioDupXNomExt);
            }
            conLocal.Cerrar();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }  
        
        
    }
    
    
    void definirModelosPerfilIncInt()                                           //Crea el modelo para la tabla de inconsistencias de los usuarios internos con perfil incorrecto
    {
        
        DefaultTableModel modeloPerfilIncInt = new DefaultTableModel();         //Define el objeto modelo de tabla
        Object[] registro = new Object[20];                                     //Crea un arreglo para recibir los elementos de cada renglon
        
        
        try
        {
            Conexion conLocal = new Conexion();                                 //Crea la conexión para la consulta
            conLocal.AbrirLocal(cadenaBD);
            ExecQuery EjecutaLo = new ExecQuery();                              //Crea el objeto para ejecutar la consulta
            perfilInt = EjecutaLo.Cons(conLocal.conexion, Monitoreos.Querys.ResultadosPerfilIncorrectoInt());   //Ejecuta la consulta y almacena el resultado en bajasExt
            if(perfilInt.next())                                                //Valida que no se encuentre vacío el resutado de la consulta
            {   
                perfilInt.beforeFirst();                                        //Regresa a la posición inicial del resultado
                modeloPerfilIncInt.addColumn("Agregar");
                modeloPerfilIncInt.addColumn("Número de empleado");
                modeloPerfilIncInt.addColumn("User ID");
                modeloPerfilIncInt.addColumn("Nombre");
                modeloPerfilIncInt.addColumn("Fecha de último acceso");
                modeloPerfilIncInt.addColumn("Región");
                modeloPerfilIncInt.addColumn("IP");
                modeloPerfilIncInt.addColumn("ID puesto");
                modeloPerfilIncInt.addColumn("ID perfil");
                modeloPerfilIncInt.addColumn("Perfil");
                modeloPerfilIncInt.addColumn("Matriz ID puesto");
                modeloPerfilIncInt.addColumn("Matriz ID perfil");
                modeloPerfilIncInt.addColumn("Matriz Perfil");
                modeloPerfilIncInt.addColumn("ID Número de empleado");          
                modeloPerfilIncInt.addColumn("Puesto");
                modeloPerfilIncInt.addColumn("Departamento");
                modeloPerfilIncInt.addColumn("Gerencia");
                modeloPerfilIncInt.addColumn("ID Nombre");
                while(perfilInt.next())                                         //Lee cada registro hasta que ya no haya más
                {
                    for(int k=1; k<18; k++)                                     //Recorre los elementos del registro para obtener cada dato de las columnas
                    {
                        if(k==1)
                        {
                            registro[k-1]=Boolean.TRUE;                         //Si está en la primer columna establece un valor TRUE para que el checkbox esté seleccionado
                        }
                        else
                        {
                            registro[k-1]=perfilInt.getString(k-1);
                        }
                    }
                    modeloPerfilIncInt.addRow(registro);                        //Ya que todos los elementos del registro están en el arreglo se agrega el arreglo como un nuevo renglón de la tabla
                }
                tablaPerfilInt.setModel(modeloPerfilIncInt);                    //Una vez construida completamente la tabla se define el modelo a la tabla original
                tablaPerfilInt.setAutoResizeMode(AUTO_RESIZE_OFF);              //Se desabilita el ajuste automatico de ancho de columnas para establecerlo manualmente
                tablaPerfilInt.getColumnModel().getColumn(0).setCellEditor(new Clase_CellEditor()); //Se hace uso de editor y renderizador de columnas
                tablaPerfilInt.getColumnModel().getColumn(0).setCellRenderer(new Clase_CellRender());

                TableColumn CAgregar = tablaPerfilInt.getColumn("Agregar");     //Se llama a la columna
                CAgregar.setPreferredWidth(55);                                 //Se define su tamaño
                TableColumn CNumEmp = tablaPerfilInt.getColumn("Número de empleado");   //Se llama a la columna
                CNumEmp.setPreferredWidth(140);                                 //Se define su tamaño
                TableColumn CUserID = tablaPerfilInt.getColumn("User ID");      //Se llama a la columna
                CUserID.setPreferredWidth(70);                                  //Se define su tamaño
                TableColumn CNombre = tablaPerfilInt.getColumn("Nombre");       //Se llama a la columna
                CNombre.setPreferredWidth(360);                                 //Se define su tamaño
                TableColumn CFecha = tablaPerfilInt.getColumn("Fecha de último acceso");    //Se llama a la columna
                CFecha.setPreferredWidth(160);                                  //Se define su tamaño
                TableColumn CRegion = tablaPerfilInt.getColumn("Región");       //Se llama a la columna
                CRegion.setPreferredWidth(70);                                  //Se define su tamaño
                TableColumn CIP = tablaPerfilInt.getColumn("IP");               //Se llama a la columna
                CIP.setPreferredWidth(120);                                     //Se define su tamaño
                TableColumn CIDPerfil = tablaPerfilInt.getColumn("ID perfil");  //Se llama a la columna
                CIDPerfil.setPreferredWidth(85);                                //Se define su tamaño
                TableColumn CPerfil = tablaPerfilInt.getColumn("Perfil");       //Se llama a la columna
                CPerfil.setPreferredWidth(400);                                 //Se define su tamaño
                TableColumn CIDNUM = tablaPerfilInt.getColumn("ID Número de empleado"); //Se llama a la columna
                CIDNUM.setPreferredWidth(180);                                  //Se define su tamaño

                TableColumn CPUESTO = tablaPerfilInt.getColumn("Puesto");       //Se llama a la columna
                CPUESTO.setPreferredWidth(400);                                 //Se define su tamaño
                TableColumn CGERENCIA = tablaPerfilInt.getColumn("Gerencia");   //Se llama a la columna
                CGERENCIA.setPreferredWidth(400);                               //Se define su tamaño
                TableColumn CIDNOMBRE = tablaPerfilInt.getColumn("ID Nombre");  //Se llama a la columna
                CIDNOMBRE.setPreferredWidth(360);                               //Se define su tamaño
                TableColumn CMidpuesto = tablaPerfilInt.getColumn("Matriz ID puesto");  //Se llama a la columna
                CMidpuesto.setPreferredWidth(100);                              //Se define su tamaño
                TableColumn CMidperfil = tablaPerfilInt.getColumn("Matriz ID perfil");  //Se llama a la columna
                CMidperfil.setPreferredWidth(100);                              //Se define su tamaño
                TableColumn CMperfil = tablaPerfilInt.getColumn("Matriz Perfil");   //Se llama a la columna
                CMperfil.setPreferredWidth(400);                                //Se define su tamaño
                
              
            }
            else
            {
                modeloPerfilIncInt.addColumn("No se encontraron inconsistencias");
                tablaPerfilInt.setModel(modeloPerfilIncInt);
            }
            conLocal.Cerrar();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }  
        
        
    }
    
    
    
    void definirModelosPerfilIncExt()                                           //Crea el modelo para la tabla de inconsistencias de usuarios externos con perfil incorrecto
    {
        
        DefaultTableModel modeloPerfilIncExt = new DefaultTableModel();         //Define el objeto modelo de tabla
        Object[] registro = new Object[20];                                     //Crea un arreglo para recibir los elementos de cada renglon
        
        
        try
        {
            Conexion conLocal = new Conexion();                                 //Crea la conexión para la consulta
            conLocal.AbrirLocal(cadenaBD);
            ExecQuery EjecutaLo = new ExecQuery();                              //Crea el objeto para ejecutar la consulta
            perfilExt = EjecutaLo.Cons(conLocal.conexion, Monitoreos.Querys.ResultadosPerfilIncorrectoExt());   //Ejecuta la consulta y almacena el resultado en bajasExt
            if(perfilExt.next())                                                //Valida que no se encuentre vacío el resutado de la consulta
            {
                perfilExt.beforeFirst();                                        //Regresa a la posición inicial del resultado
                modeloPerfilIncExt.addColumn("Agregar");
                modeloPerfilIncExt.addColumn("Número de empleado");
                modeloPerfilIncExt.addColumn("User ID");
                modeloPerfilIncExt.addColumn("Nombre");
                modeloPerfilIncExt.addColumn("Fecha de último acceso");
                modeloPerfilIncExt.addColumn("Región");
                modeloPerfilIncExt.addColumn("IP");
                modeloPerfilIncExt.addColumn("ID puesto");
                modeloPerfilIncExt.addColumn("ID perfil");
                modeloPerfilIncExt.addColumn("Perfil");
                modeloPerfilIncExt.addColumn("Matriz ID puesto");
                modeloPerfilIncExt.addColumn("Matriz ID perfil");
                modeloPerfilIncExt.addColumn("Matriz Perfil");
                modeloPerfilIncExt.addColumn("ID Número de empleado");
                modeloPerfilIncExt.addColumn("Puesto");
                modeloPerfilIncExt.addColumn("Departamento");
                modeloPerfilIncExt.addColumn("Gerencia");
                modeloPerfilIncExt.addColumn("ID Nombre");
                while(perfilExt.next())                                         //Lee cada registro hasta que ya no haya más
                {
                    for(int k=1; k<18; k++)                                     //Recorre los elementos del registro para obtener cada dato de las columnas
                    {
                        if(k==1)
                        {
                            registro[k-1]=Boolean.TRUE;                         //Si está en la primer columna establece un valor TRUE para que el checkbox esté seleccionado
                        }
                        else
                        {
                            registro[k-1]=perfilExt.getString(k-1);
                        }
                    }
                    modeloPerfilIncExt.addRow(registro);                        //Ya que todos los elementos del registro están en el arreglo se agrega el arreglo como un nuevo renglón de la tabla
                }
                tablaPerfilExt.setModel(modeloPerfilIncExt);                    //Una vez construida completamente la tabla se define el modelo a la tabla original
                tablaPerfilExt.setAutoResizeMode(AUTO_RESIZE_OFF);              //Se desabilita el ajuste automatico de ancho de columnas para establecerlo manualmente
                tablaPerfilExt.getColumnModel().getColumn(0).setCellEditor(new Clase_CellEditor());     //Se hace uso de editor y renderizador de columnas
                tablaPerfilExt.getColumnModel().getColumn(0).setCellRenderer(new Clase_CellRender());

                TableColumn CAgregar = tablaPerfilExt.getColumn("Agregar");     //Se llama a la columna
                CAgregar.setPreferredWidth(55);                                 //Se define su tamaño
                TableColumn CNumEmp = tablaPerfilExt.getColumn("Número de empleado");   //Se llama a la columna
                CNumEmp.setPreferredWidth(140);                                 //Se define su tamaño
                TableColumn CUserID = tablaPerfilExt.getColumn("User ID");      //Se llama a la columna
                CUserID.setPreferredWidth(70);                                  //Se define su tamaño
                TableColumn CNombre = tablaPerfilExt.getColumn("Nombre");       //Se llama a la columna
                CNombre.setPreferredWidth(360);                                 //Se define su tamaño
                TableColumn CFecha = tablaPerfilExt.getColumn("Fecha de último acceso");    //Se llama a la columna
                CFecha.setPreferredWidth(160);                                  //Se define su tamaño
                TableColumn CRegion = tablaPerfilExt.getColumn("Región");       //Se llama a la columna
                CRegion.setPreferredWidth(70);                                  //Se define su tamaño
                TableColumn CIP = tablaPerfilExt.getColumn("IP");               //Se llama a la columna
                CIP.setPreferredWidth(120);                                     //Se define su tamaño
                TableColumn CIDPerfil = tablaPerfilExt.getColumn("ID perfil");  //Se llama a la columna
                CIDPerfil.setPreferredWidth(85);                                //Se define su tamaño
                TableColumn CPerfil = tablaPerfilExt.getColumn("Perfil");       //Se llama a la columna
                CPerfil.setPreferredWidth(400);                                 //Se define su tamaño
                TableColumn CIDNUM = tablaPerfilExt.getColumn("ID Número de empleado"); //Se llama a la columna
                CIDNUM.setPreferredWidth(180);                                  //Se define su tamaño

                TableColumn CPUESTO = tablaPerfilExt.getColumn("Puesto");       //Se llama a la columna
                CPUESTO.setPreferredWidth(400);                                 //Se define su tamaño
                TableColumn CGERENCIA = tablaPerfilExt.getColumn("Gerencia");   //Se llama a la columna
                CGERENCIA.setPreferredWidth(400);                               //Se define su tamaño
                TableColumn CIDNOMBRE = tablaPerfilExt.getColumn("ID Nombre");  //Se llama a la columna
                CIDNOMBRE.setPreferredWidth(360);                               //Se define su tamaño
                TableColumn CMidpuesto = tablaPerfilExt.getColumn("Matriz ID puesto");  //Se llama a la columna
                CMidpuesto.setPreferredWidth(100);                              //Se define su tamaño
                TableColumn CMidperfil = tablaPerfilExt.getColumn("Matriz ID perfil");  //Se llama a la columna
                CMidperfil.setPreferredWidth(100);                              //Se define su tamaño
                TableColumn CMperfil = tablaPerfilExt.getColumn("Matriz Perfil");   //Se llama a la columna
                CMperfil.setPreferredWidth(400);                                //Se define su tamaño
                
                
              
            }
            else
            {
                modeloPerfilIncExt.addColumn("No se encontraron inconsistencias");
                tablaPerfilExt.setModel(modeloPerfilIncExt);
            }
            conLocal.Cerrar();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }  
        
        
    }
    
    
    void definirModelosNoAutorizadoInt()                                        //Crea el modelo para la tabla de inconsistencias de usuarios internos con perfil no autorizado
    {
        
        DefaultTableModel modeloNoAutorizadoInt = new DefaultTableModel();      //Define el objeto modelo de tabla
        Object[] registro = new Object[20];                                     //Crea un arreglo para recibir los elementos de cada renglon
        
        
        try
        {
            Conexion conLocal = new Conexion();                                 //Crea la conexión para la consulta
            conLocal.AbrirLocal(cadenaBD);
            ExecQuery EjecutaLo = new ExecQuery();                              //Crea el objeto para ejecutar la consulta
            noAutoInt = EjecutaLo.Cons(conLocal.conexion, Monitoreos.Querys.ResultadosNoAutorizadoInt());   //Ejecuta la consulta y almacena el resultado en bajasExt
            if(noAutoInt.next())                                                //Valida que no se encuentre vacío el resutado de la consulta
            {
                noAutoInt.beforeFirst();                                        //Regresa a la posición inicial del resultado
                modeloNoAutorizadoInt.addColumn("Agregar");
                modeloNoAutorizadoInt.addColumn("Número de empleado");
                modeloNoAutorizadoInt.addColumn("User ID");
                modeloNoAutorizadoInt.addColumn("Nombre");
                modeloNoAutorizadoInt.addColumn("Fecha de último acceso");
                modeloNoAutorizadoInt.addColumn("Región");
                modeloNoAutorizadoInt.addColumn("IP");
                modeloNoAutorizadoInt.addColumn("ID puesto");
                modeloNoAutorizadoInt.addColumn("ID perfil");
                modeloNoAutorizadoInt.addColumn("Perfil");
                modeloNoAutorizadoInt.addColumn("Matriz ID puesto");
                modeloNoAutorizadoInt.addColumn("Matriz ID perfil");
                modeloNoAutorizadoInt.addColumn("Matriz Perfil");
                modeloNoAutorizadoInt.addColumn("ID Número de empleado");
                modeloNoAutorizadoInt.addColumn("Puesto");
                modeloNoAutorizadoInt.addColumn("Departamento");
                modeloNoAutorizadoInt.addColumn("Gerencia");
                modeloNoAutorizadoInt.addColumn("ID Nombre");
                while(noAutoInt.next())                                         //Lee cada registro hasta que ya no haya más
                {
                    for(int k=1; k<18; k++)                                     //Recorre los elementos del registro para obtener cada dato de las columnas
                    {
                        if(k==1)
                        {
                            registro[k-1]=Boolean.TRUE;                         //Si está en la primer columna establece un valor TRUE para que el checkbox esté seleccionado
                        }
                        else
                        {
                            registro[k-1]=noAutoInt.getString(k-1);
                        }
                    }
                    modeloNoAutorizadoInt.addRow(registro);                     //Ya que todos los elementos del registro están en el arreglo se agrega el arreglo como un nuevo renglón de la tabla
                }
                tablaNoAutoInt.setModel(modeloNoAutorizadoInt);                 //Una vez construida completamente la tabla se define el modelo a la tabla original
                tablaNoAutoInt.setAutoResizeMode(AUTO_RESIZE_OFF);              //Se desabilita el ajuste automatico de ancho de columnas para establecerlo manualmente
                tablaNoAutoInt.getColumnModel().getColumn(0).setCellEditor(new Clase_CellEditor());     //Se hace uso de editor y renderizador de columnas
                tablaNoAutoInt.getColumnModel().getColumn(0).setCellRenderer(new Clase_CellRender());

                TableColumn CAgregar = tablaNoAutoInt.getColumn("Agregar");     //Se llama a la columna
                CAgregar.setPreferredWidth(55);                                 //Se define su tamaño
                TableColumn CNumEmp = tablaNoAutoInt.getColumn("Número de empleado");   //Se llama a la columna
                CNumEmp.setPreferredWidth(140);                                 //Se define su tamaño
                TableColumn CUserID = tablaNoAutoInt.getColumn("User ID");      //Se llama a la columna
                CUserID.setPreferredWidth(70);                                  //Se define su tamaño
                TableColumn CNombre = tablaNoAutoInt.getColumn("Nombre");       //Se llama a la columna
                CNombre.setPreferredWidth(360);                                 //Se define su tamaño
                TableColumn CFecha = tablaNoAutoInt.getColumn("Fecha de último acceso");    //Se llama a la columna
                CFecha.setPreferredWidth(160);                                  //Se define su tamaño
                TableColumn CRegion = tablaNoAutoInt.getColumn("Región");       //Se llama a la columna
                CRegion.setPreferredWidth(70);                                  //Se define su tamaño
                TableColumn CIP = tablaNoAutoInt.getColumn("IP");               //Se llama a la columna
                CIP.setPreferredWidth(120);                                     //Se define su tamaño
                TableColumn CIDPerfil = tablaNoAutoInt.getColumn("ID perfil");  //Se llama a la columna
                CIDPerfil.setPreferredWidth(85);                                //Se define su tamaño
                TableColumn CPerfil = tablaNoAutoInt.getColumn("Perfil");       //Se llama a la columna
                CPerfil.setPreferredWidth(400);                                 //Se define su tamaño
                TableColumn CIDNUM = tablaNoAutoInt.getColumn("ID Número de empleado"); //Se llama a la columna
                CIDNUM.setPreferredWidth(180);                                  //Se define su tamaño

                TableColumn CPUESTO = tablaNoAutoInt.getColumn("Puesto");       //Se llama a la columna
                CPUESTO.setPreferredWidth(400);                                 //Se define su tamaño
                TableColumn CGERENCIA = tablaNoAutoInt.getColumn("Gerencia");   //Se llama a la columna
                CGERENCIA.setPreferredWidth(400);                               //Se define su tamaño
                TableColumn CIDNOMBRE = tablaNoAutoInt.getColumn("ID Nombre");  //Se llama a la columna
                CIDNOMBRE.setPreferredWidth(360);                               //Se define su tamaño
                TableColumn CMidpuesto = tablaNoAutoInt.getColumn("Matriz ID puesto");  //Se llama a la columna
                CMidpuesto.setPreferredWidth(100);                              //Se define su tamaño
                TableColumn CMidperfil = tablaNoAutoInt.getColumn("Matriz ID perfil");  //Se llama a la columna
                CMidperfil.setPreferredWidth(100);                              //Se define su tamaño
                TableColumn CMperfil = tablaNoAutoInt.getColumn("Matriz Perfil");   //Se llama a la columna
                CMperfil.setPreferredWidth(400);                                //Se define su tamaño
                
              
            }
            else
            {
                modeloNoAutorizadoInt.addColumn("No se encontraron inconsistencias");
                tablaNoAutoInt.setModel(modeloNoAutorizadoInt);
            }
            conLocal.Cerrar();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }  
        
        
    }
    
    
    void definirModelosNoAutorizadoExt()                                        //Crea el modelo para la tabla de inconsistencias de usuarios externos con perfil no autorizado
    {
        
        DefaultTableModel modeloNoAutorizadoExt = new DefaultTableModel();      //Define el objeto modelo de tabla
        Object[] registro = new Object[20];                                     //Crea un arreglo para recibir los elementos de cada renglon
        
        
        try
        {
            Conexion conLocal = new Conexion();                                 //Crea la conexión para la consulta
            conLocal.AbrirLocal(cadenaBD);
            ExecQuery EjecutaLo = new ExecQuery();                              //Crea el objeto para ejecutar la consulta
            noAutoExt = EjecutaLo.Cons(conLocal.conexion, Monitoreos.Querys.ResultadosNoAutorizadoExt());   //Ejecuta la consulta y almacena el resultado en bajasExt
            if(noAutoExt.next())                                                //Valida que no se encuentre vacío el resutado de la consulta
            {
                noAutoExt.beforeFirst();                                        //Regresa a la posición inicial del resultado
                modeloNoAutorizadoExt.addColumn("Agregar");
                modeloNoAutorizadoExt.addColumn("Número de empleado");
                modeloNoAutorizadoExt.addColumn("User ID");
                modeloNoAutorizadoExt.addColumn("Nombre");
                modeloNoAutorizadoExt.addColumn("Fecha de último acceso");
                modeloNoAutorizadoExt.addColumn("Región");
                modeloNoAutorizadoExt.addColumn("IP");
                modeloNoAutorizadoExt.addColumn("ID puesto");
                modeloNoAutorizadoExt.addColumn("ID perfil");
                modeloNoAutorizadoExt.addColumn("Perfil");
                modeloNoAutorizadoExt.addColumn("Matriz ID puesto");
                modeloNoAutorizadoExt.addColumn("Matriz ID perfil");
                modeloNoAutorizadoExt.addColumn("Matriz Perfil");
                modeloNoAutorizadoExt.addColumn("ID Número de empleado");
                modeloNoAutorizadoExt.addColumn("Puesto");
                modeloNoAutorizadoExt.addColumn("Departamento");
                modeloNoAutorizadoExt.addColumn("Gerencia");
                modeloNoAutorizadoExt.addColumn("ID Nombre");
                while(noAutoExt.next())                                         //Lee cada registro hasta que ya no haya más
                {
                    for(int k=1; k<18; k++)                                     //Recorre los elementos del registro para obtener cada dato de las columnas
                    {
                        if(k==1)
                        {
                            registro[k-1]=Boolean.TRUE;                         //Si está en la primer columna establece un valor TRUE para que el checkbox esté seleccionado
                        }
                        else
                        {
                            registro[k-1]=noAutoExt.getString(k-1);
                        }
                    }
                    modeloNoAutorizadoExt.addRow(registro);                     //Ya que todos los elementos del registro están en el arreglo se agrega el arreglo como un nuevo renglón de la tabla
                }
                tablaNoAutoExt.setModel(modeloNoAutorizadoExt);                 //Una vez construida completamente la tabla se define el modelo a la tabla original
                tablaNoAutoExt.setAutoResizeMode(AUTO_RESIZE_OFF);              //Se desabilita el ajuste automatico de ancho de columnas para establecerlo manualmente
                tablaNoAutoExt.getColumnModel().getColumn(0).setCellEditor(new Clase_CellEditor());     //Se hace uso de editor y renderizador de columnas
                tablaNoAutoExt.getColumnModel().getColumn(0).setCellRenderer(new Clase_CellRender());

                TableColumn CAgregar = tablaNoAutoExt.getColumn("Agregar");     //Se llama a la columna
                CAgregar.setPreferredWidth(55);                                 //Se define su tamaño
                TableColumn CNumEmp = tablaNoAutoExt.getColumn("Número de empleado");   //Se llama a la columna
                CNumEmp.setPreferredWidth(140);                                 //Se define su tamaño
                TableColumn CUserID = tablaNoAutoExt.getColumn("User ID");      //Se llama a la columna
                CUserID.setPreferredWidth(70);                                  //Se define su tamaño
                TableColumn CNombre = tablaNoAutoExt.getColumn("Nombre");       //Se llama a la columna
                CNombre.setPreferredWidth(360);                                 //Se define su tamaño
                TableColumn CFecha = tablaNoAutoExt.getColumn("Fecha de último acceso");    //Se llama a la columna
                CFecha.setPreferredWidth(160);                                  //Se define su tamaño
                TableColumn CRegion = tablaNoAutoExt.getColumn("Región");       //Se llama a la columna
                CRegion.setPreferredWidth(70);                                  //Se define su tamaño
                TableColumn CIP = tablaNoAutoExt.getColumn("IP");               //Se llama a la columna
                CIP.setPreferredWidth(120);                                     //Se define su tamaño
                TableColumn CIDPerfil = tablaNoAutoExt.getColumn("ID perfil");  //Se llama a la columna
                CIDPerfil.setPreferredWidth(85);                                //Se define su tamaño
                TableColumn CPerfil = tablaNoAutoExt.getColumn("Perfil");       //Se llama a la columna
                CPerfil.setPreferredWidth(400);                                 //Se define su tamaño
                TableColumn CIDNUM = tablaNoAutoExt.getColumn("ID Número de empleado"); //Se llama a la columna
                CIDNUM.setPreferredWidth(180);                                  //Se define su tamaño

                TableColumn CPUESTO = tablaNoAutoExt.getColumn("Puesto");       //Se llama a la columna
                CPUESTO.setPreferredWidth(400);                                 //Se define su tamaño
                TableColumn CGERENCIA = tablaNoAutoExt.getColumn("Gerencia");   //Se llama a la columna
                CGERENCIA.setPreferredWidth(400);                               //Se define su tamaño
                TableColumn CIDNOMBRE = tablaNoAutoExt.getColumn("ID Nombre");  //Se llama a la columna
                CIDNOMBRE.setPreferredWidth(360);                               //Se define su tamaño
                TableColumn CMidpuesto = tablaNoAutoExt.getColumn("Matriz ID puesto");  //Se llama a la columna
                CMidpuesto.setPreferredWidth(100);                              //Se define su tamaño
                TableColumn CMidperfil = tablaNoAutoExt.getColumn("Matriz ID perfil");  //Se llama a la columna
                CMidperfil.setPreferredWidth(100);                              //Se define su tamaño
                TableColumn CMperfil = tablaNoAutoExt.getColumn("Matriz Perfil");   //Se llama a la columna
                CMperfil.setPreferredWidth(400);                                //Se define su tamaño
                
              
            }
            else
            {
                modeloNoAutorizadoExt.addColumn("No se encontraron inconsistencias");
                tablaNoAutoExt.setModel(modeloNoAutorizadoExt);
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
    
    
    void definirModelosUsuariosInt()                                               //Define el modelo de la tabla de inconsistencias de usuarios internos reportados como baja
    {
        DefaultTableModel modeloInt = new DefaultTableModel();             //Crea el objeto modelo de tabla
       
        Object[] registro = new Object[8];                                     //Crea un arreglo para recibir los elementos de cada renglon
        int i = 0;                                                              //Inicializa la variable para el contador
        try
        {
            Conexion conLocal = new Conexion();                                 //Inicia la conexión local
            conLocal.AbrirLocal(cadenaBD);
            ExecQuery EjecutaLo = new ExecQuery();                              //Crea el objeto para ejecutar la consulta
            Uint = EjecutaLo.Cons(conLocal.conexion, Monitoreos.Querys.ResultadosUsrInt());   //Ejecuta la consulta y almacena el resultado en la variable
            
            if(Uint.next())                                                       //Verifica que el resultado no esté vacío
            {
                Uint.beforeFirst();                                            //Regresa a la posición inicial del resultado
                modeloInt.addColumn("Agregar");
                modeloInt.addColumn("Usuario");                                  //Crea las columnas necesarias para el reporte
                modeloInt.addColumn("Nombre_Completo");
                modeloInt.addColumn("Grupo");             
                modeloInt.addColumn("Valido_de");
                modeloInt.addColumn("Validez_a");
                
                
                                               
                while(Uint.next())                                          //Lee cada registro hasta que ya no haya más
                {
                    for(int k=1; k<7; k++)                                     
                    {
                        if(k==1)                                                
                        {
                            registro[k-1]=Boolean.TRUE;                         //Si está en la primer columna establece un valor TRUE para que el checkbox esté seleccionado
                        }
                        else
                        {
                            registro[k-1]=Uint.getString(k-1);              //Recorre los elementos del registro para obtener cada dato de las columnas
                        }
                    }
                    modeloInt.addRow(registro);                            //Ya que todos los elementos del registro están en el arreglo se agrega el arreglo como un nuevo renglón de la tabla
                }

                tablaUserInt.setModel(modeloInt);                                //Una vez construida completamente la tabla se define el modelo a la tabla original
                tablaUserInt.setAutoResizeMode(AUTO_RESIZE_OFF);                 //Se desabilita el ajuste automatico de ancho de columnas para establecerlo manualmente
                tablaUserInt.getColumnModel().getColumn(0).setCellEditor(new Clase_CellEditor());  //Se hace uso de editor y renderizador de columnas
                tablaUserInt.getColumnModel().getColumn(0).setCellRenderer(new Clase_CellRender());

                TableColumn CAgregar = tablaUserInt.getColumn("Agregar");     //Se llama a la columna
                CAgregar.setPreferredWidth(80); 
                TableColumn CUser = tablaUserInt.getColumn("Usuario");      //Se llama a la columna
                CUser.setPreferredWidth(150);                                 //Se define su tamaño
                TableColumn CNombre = tablaUserInt.getColumn("Nombre_Completo");    //Se llama a la columna
                CNombre.setPreferredWidth(300);                                 //Se define su tamaño
                TableColumn CGrupo = tablaUserInt.getColumn("Grupo");       //Se llama a la columna
                CGrupo.setPreferredWidth(300);                                  //Se define su tamaño
                TableColumn CValor = tablaUserInt.getColumn("Valido_de"); //Se llama a la columna
                CValor.setPreferredWidth(160);                                  //Se define su tamaño
                TableColumn CValidez = tablaUserInt.getColumn("Validez_a"); //Se llama a la columna
                CValidez.setPreferredWidth(160);                                  //Se define su tamaño
                                             
            }
            else                                                                //Si el resultado se encontraba vacío
            {
                modeloInt.addColumn("No se encontraron inconsistencias");  //Se crea una columna con la leyecnda
                tablaUserInt.setModel(modeloInt);                         //Se define el modelo 
            }
            
            conLocal.Cerrar();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }    
    }
    
    void definirModelosUsuariosExt()                                               //Define el modelo de la tabla de inconsistencias de usuarios internos reportados como baja
    {
        DefaultTableModel modeloExt = new DefaultTableModel();             //Crea el objeto modelo de tabla
       
        Object[] registro = new Object[8];                                     //Crea un arreglo para recibir los elementos de cada renglon
        int i = 0;                                                              //Inicializa la variable para el contador
        try
        {
            Conexion conLocal = new Conexion();                                 //Inicia la conexión local
            conLocal.AbrirLocal(cadenaBD);
            ExecQuery EjecutaLo = new ExecQuery();                              //Crea el objeto para ejecutar la consulta
            Uext = EjecutaLo.Cons(conLocal.conexion, Monitoreos.Querys.ResultadosUsrExt());   //Ejecuta la consulta y almacena el resultado en la variable
            
            if(Uext.next())                                                 //Verifica que el resultado no esté vacío
            {
                Uext.beforeFirst();                                            //Regresa a la posición inicial del resultado
                modeloExt.addColumn("Agregar");
                modeloExt.addColumn("Usuario");                                  //Crea las columnas necesarias para el reporte
                modeloExt.addColumn("Nombre_Completo");
                modeloExt.addColumn("Grupo");      
                modeloExt.addColumn("Valido_de");
                modeloExt.addColumn("Validez_a");
                
                                               
                while(Uext.next())                                          //Lee cada registro hasta que ya no haya más
                {
                    for(int k=1; k<7; k++)                                     
                    {
                        if(k==1)                                                
                        {
                            registro[k-1]=Boolean.TRUE;                         //Si está en la primer columna establece un valor TRUE para que el checkbox esté seleccionado
                        }
                        else
                        {
                            registro[k-1]=Uext.getString(k-1);              //Recorre los elementos del registro para obtener cada dato de las columnas
                        }
                    }
                    modeloExt.addRow(registro);                            //Ya que todos los elementos del registro están en el arreglo se agrega el arreglo como un nuevo renglón de la tabla
                }

                tablaUserExt.setModel(modeloExt);                         //Una vez construida completamente la tabla se define el modelo a la tabla original
                tablaUserExt.setAutoResizeMode(AUTO_RESIZE_OFF);               //Se desabilita el ajuste automatico de ancho de columnas para establecerlo manualmente
                tablaUserExt.getColumnModel().getColumn(0).setCellEditor(new Clase_CellEditor());  //Se hace uso de editor y renderizador de columnas
                tablaUserExt.getColumnModel().getColumn(0).setCellRenderer(new Clase_CellRender());

                TableColumn CAgregar = tablaUserExt.getColumn("Agregar");     //Se llama a la columna
                CAgregar.setPreferredWidth(80); 
                TableColumn CUser = tablaUserExt.getColumn("Usuario");      //Se llama a la columna
                CUser.setPreferredWidth(150);                                 //Se define su tamaño
                TableColumn CNombre = tablaUserExt.getColumn("Nombre_Completo");    //Se llama a la columna
                CNombre.setPreferredWidth(300);                                 //Se define su tamaño
                TableColumn CGrupo = tablaUserExt.getColumn("Grupo");       //Se llama a la columna
                CGrupo.setPreferredWidth(300);                                  //Se define su tamaño
                TableColumn CValor = tablaUserExt.getColumn("Valido_de"); //Se llama a la columna
                CValor.setPreferredWidth(160);                                  //Se define su tamaño
                TableColumn CValidez = tablaUserExt.getColumn("Validez_a"); //Se llama a la columna
                CValidez.setPreferredWidth(160);                                  //Se define su tamaño
                                             
            }
            else                                                                //Si el resultado se encontraba vacío
            {
                modeloExt.addColumn("No se encontraron inconsistencias");  //Se crea una columna con la leyecnda
                tablaUserExt.setModel(modeloExt);                         //Se define el modelo 
            }
            
            conLocal.Cerrar();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }    
    }
    
    
    void definirModelosUsuariosGen()                                               //Define el modelo de la tabla de inconsistencias de usuarios internos reportados como baja
    {
        DefaultTableModel modeloGen = new DefaultTableModel();             //Crea el objeto modelo de tabla
       
        Object[] registro = new Object[8];                                     //Crea un arreglo para recibir los elementos de cada renglon
        int i = 0;                                                              //Inicializa la variable para el contador
        try
        {
            Conexion conLocal = new Conexion();                                 //Inicia la conexión local
            conLocal.AbrirLocal(cadenaBD);
            ExecQuery EjecutaLo = new ExecQuery();                              //Crea el objeto para ejecutar la consulta
            Ugen = EjecutaLo.Cons(conLocal.conexion, Monitoreos.Querys.ResultadosUsrGen());   //Ejecuta la consulta y almacena el resultado en la variable
            
            if(Ugen.next())                                                 //Verifica que el resultado no esté vacío
            {
                Ugen.beforeFirst();                                            //Regresa a la posición inicial del resultado
                modeloGen.addColumn("Agregar");
                modeloGen.addColumn("Usuario");                                  //Crea las columnas necesarias para el reporte
                modeloGen.addColumn("Nombre_Completo");
                modeloGen.addColumn("Grupo");
                modeloGen.addColumn("Valido_de");
                modeloGen.addColumn("Validez_a");
                
                                               
                while(Ugen.next())                                          //Lee cada registro hasta que ya no haya más
                {
                    for(int k=1; k<7; k++)                                     
                    {
                        if(k==1)                                                
                        {
                            registro[k-1]=Boolean.TRUE;                         //Si está en la primer columna establece un valor TRUE para que el checkbox esté seleccionado
                        }
                        else
                        {
                            registro[k-1]=Ugen.getString(k-1);              //Recorre los elementos del registro para obtener cada dato de las columnas
                        }
                    }
                    modeloGen.addRow(registro);                            //Ya que todos los elementos del registro están en el arreglo se agrega el arreglo como un nuevo renglón de la tabla
                }

                tablaUsrGen.setModel(modeloGen);                         //Una vez construida completamente la tabla se define el modelo a la tabla original
                tablaUsrGen.setAutoResizeMode(AUTO_RESIZE_OFF);               //Se desabilita el ajuste automatico de ancho de columnas para establecerlo manualmente
                tablaUsrGen.getColumnModel().getColumn(0).setCellEditor(new Clase_CellEditor());  //Se hace uso de editor y renderizador de columnas
                tablaUsrGen.getColumnModel().getColumn(0).setCellRenderer(new Clase_CellRender());

                TableColumn CAgregar = tablaUsrGen.getColumn("Agregar");     //Se llama a la columna
                CAgregar.setPreferredWidth(80); 
                TableColumn CUser = tablaUsrGen.getColumn("Usuario");      //Se llama a la columna
                CUser.setPreferredWidth(150);                                 //Se define su tamaño
                TableColumn CNombre = tablaUsrGen.getColumn("Nombre_Completo");    //Se llama a la columna
                CNombre.setPreferredWidth(300);                                 //Se define su tamaño
                TableColumn CGrupo = tablaUsrGen.getColumn("Grupo");       //Se llama a la columna
                CGrupo.setPreferredWidth(300);                                  //Se define su tamaño                           
                TableColumn CValor = tablaUsrGen.getColumn("Valido_de"); //Se llama a la columna
                CValor.setPreferredWidth(160);                                  //Se define su tamaño
                TableColumn CValidez = tablaUsrGen.getColumn("Validez_a"); //Se llama a la columna
                CValidez.setPreferredWidth(160);                                  //Se define su tamaño
                                             
            }
            else                                                                //Si el resultado se encontraba vacío
            {
                modeloGen.addColumn("No se encontraron inconsistencias");  //Se crea una columna con la leyecnda
                tablaUsrGen.setModel(modeloGen);                         //Se define el modelo 
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
        tablaUsrInt = new javax.swing.JTable();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tablaUsrExt = new javax.swing.JTable();
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
        tablaPerfilExt = new javax.swing.JTable();
        jPanel32 = new javax.swing.JPanel();
        jPanel34 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel36 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        tablaNoAutoInt = new javax.swing.JTable();
        jPanel33 = new javax.swing.JPanel();
        jPanel35 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel37 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        tablaNoAutoExt = new javax.swing.JTable();
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
        jPanel41 = new javax.swing.JPanel();
        jPanel42 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jPanel43 = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        tablaEliminado1 = new javax.swing.JTable();
        jScrollPane16 = new javax.swing.JScrollPane();
        tablaAgregado1 = new javax.swing.JTable();
        jPanel44 = new javax.swing.JPanel();
        jPanel45 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jPanel46 = new javax.swing.JPanel();
        jScrollPane18 = new javax.swing.JScrollPane();
        tablaUserInt = new javax.swing.JTable();
        jPanel47 = new javax.swing.JPanel();
        jPanel48 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jPanel49 = new javax.swing.JPanel();
        jScrollPane20 = new javax.swing.JScrollPane();
        tablaUserExt = new javax.swing.JTable();
        jPanel53 = new javax.swing.JPanel();
        jPanel54 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jPanel55 = new javax.swing.JPanel();
        jScrollPane24 = new javax.swing.JScrollPane();
        tablaUsrGen = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1370, 730));
        setSize(new java.awt.Dimension(1370, 730));

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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 534, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 43, Short.MAX_VALUE))
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

        jTabbedPane3.addTab("Bajas internos", jPanel9);

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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 44, Short.MAX_VALUE))
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

        jTabbedPane3.addTab("Bajas externos", jPanel10);

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
                .addContainerGap(57, Short.MAX_VALUE))
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
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
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

        jTabbedPane3.addTab("Inactividad internos", jPanel3);

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
                .addContainerGap(57, Short.MAX_VALUE))
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
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
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

        jTabbedPane3.addTab("Inactividad externos", jPanel6);

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

        tablaUsrInt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane5.setViewportView(tablaUsrInt);

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5)
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
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

        jTabbedPane3.addTab("User ID incorrecto internos", jPanel13);

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

        tablaUsrExt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane6.setViewportView(tablaUsrExt);

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

        jTabbedPane3.addTab("User ID incorrecto externos", jPanel15);

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

        jTabbedPane3.addTab("Duplicados por nombre internos", jPanel17);

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

        jTabbedPane3.addTab("Duplicados por nombre externos", jPanel18);

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
            .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
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

        jTabbedPane3.addTab("Perfil incorrecto internos", jPanel19);

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

        tablaPerfilExt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane10.setViewportView(tablaPerfilExt);

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

        jTabbedPane3.addTab("Perfil incorrecto externos", jPanel23);

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

        jTabbedPane3.addTab("Usuarios internos con perfil no autorizado", jPanel32);

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/telcel.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel35Layout.createSequentialGroup()
                .addContainerGap(1009, Short.MAX_VALUE)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        tablaNoAutoExt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane12.setViewportView(tablaNoAutoExt);

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane12)
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel37, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Usuarios externos con perfil no autorizado", jPanel33);

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
                .addContainerGap(57, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 242, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
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

        jTabbedPane3.addTab("Usuarios Administradores", jPanel38);

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/telcel.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
        jPanel42.setLayout(jPanel42Layout);
        jPanel42Layout.setHorizontalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel42Layout.createSequentialGroup()
                .addContainerGap(1009, Short.MAX_VALUE)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel42Layout.setVerticalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel42Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        tablaEliminado1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane15.setViewportView(tablaEliminado1);

        tablaAgregado1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane16.setViewportView(tablaAgregado1);

        javax.swing.GroupLayout jPanel43Layout = new javax.swing.GroupLayout(jPanel43);
        jPanel43.setLayout(jPanel43Layout);
        jPanel43Layout.setHorizontalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane15)
                .addContainerGap())
            .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel43Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane16)
                    .addContainerGap()))
        );
        jPanel43Layout.setVerticalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel43Layout.createSequentialGroup()
                .addContainerGap(247, Short.MAX_VALUE)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69))
            .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel43Layout.createSequentialGroup()
                    .addGap(21, 21, 21)
                    .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(295, Short.MAX_VALUE)))
        );

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/telcel.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel45Layout = new javax.swing.GroupLayout(jPanel45);
        jPanel45.setLayout(jPanel45Layout);
        jPanel45Layout.setHorizontalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel45Layout.createSequentialGroup()
                .addContainerGap(1009, Short.MAX_VALUE)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel45Layout.setVerticalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        tablaUserInt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane18.setViewportView(tablaUserInt);

        javax.swing.GroupLayout jPanel46Layout = new javax.swing.GroupLayout(jPanel46);
        jPanel46.setLayout(jPanel46Layout);
        jPanel46Layout.setHorizontalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane18)
        );
        jPanel46Layout.setVerticalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel46Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane18, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel44Layout = new javax.swing.GroupLayout(jPanel44);
        jPanel44.setLayout(jPanel44Layout);
        jPanel44Layout.setHorizontalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel46, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel44Layout.setVerticalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addComponent(jPanel45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel43, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel41Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addComponent(jPanel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel41Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTabbedPane3.addTab("Usuarios Internos", jPanel41);

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/telcel.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel48Layout = new javax.swing.GroupLayout(jPanel48);
        jPanel48.setLayout(jPanel48Layout);
        jPanel48Layout.setHorizontalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel48Layout.createSequentialGroup()
                .addContainerGap(1009, Short.MAX_VALUE)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel48Layout.setVerticalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel48Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        tablaUserExt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane20.setViewportView(tablaUserExt);

        javax.swing.GroupLayout jPanel49Layout = new javax.swing.GroupLayout(jPanel49);
        jPanel49.setLayout(jPanel49Layout);
        jPanel49Layout.setHorizontalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane20)
        );
        jPanel49Layout.setVerticalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel49Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane20, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel47Layout = new javax.swing.GroupLayout(jPanel47);
        jPanel47.setLayout(jPanel47Layout);
        jPanel47Layout.setHorizontalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel49, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel47Layout.setVerticalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel47Layout.createSequentialGroup()
                .addComponent(jPanel48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Usuarios Externos", jPanel47);

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
                .addComponent(jScrollPane24, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE))
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

        jTabbedPane3.addTab("Usuarios Genericos", jPanel53);

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
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
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
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel53;
    private javax.swing.JPanel jPanel54;
    private javax.swing.JPanel jPanel55;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTable tablaAgregado;
    private javax.swing.JTable tablaAgregado1;
    private javax.swing.JTable tablaBajasExt;
    private javax.swing.JTable tablaBajasInt;
    private javax.swing.JTable tablaDupExt;
    private javax.swing.JTable tablaDupInt;
    private javax.swing.JTable tablaEliminado;
    private javax.swing.JTable tablaEliminado1;
    private javax.swing.JTable tablaInacExt;
    private javax.swing.JTable tablaInacInt;
    private javax.swing.JTable tablaNoAutoExt;
    private javax.swing.JTable tablaNoAutoInt;
    private javax.swing.JTable tablaPerfilExt;
    private javax.swing.JTable tablaPerfilInt;
    private javax.swing.JTable tablaUserExt;
    private javax.swing.JTable tablaUserInt;
    private javax.swing.JTable tablaUsrExt;
    private javax.swing.JTable tablaUsrGen;
    private javax.swing.JTable tablaUsrInt;
    // End of variables declaration//GEN-END:variables
}
