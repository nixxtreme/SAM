
package MonPac;


import Monitoreos.Conexion;
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
    ResultSet bajasInt, bajasExt, usrInt, usrExt, inactInt, inactExt, dupInt, dupExt, perfilInt, perfilExt, noAutoInt, noAutoExt;       //Almacenan los resultados de cada consulta
  
    
    public Resultados(String cadena) {                                          //Inicializa la ventana y ejecuta los métodos para que se visualicen las tablas de resultados
        cadenaBD = cadena;                                                      //Almacena la cadena con los datos de conexión a la base de datos local
        initComponents();                                                       //Inicializa los componentes proncipales de la ventana
        definirModelosBajasInt();                                               //Define el modelo de la tabla de inconsistencias de bajas internos
        definirModelosBajasExt();                                               //Define el modelo de la tabla de inconsistencias de bajas externos
        definirModelosInactividadInt();                                         //Define el modelo de la tabla de inconsistencias de inactividad internos
        definirModelosInactividadExt();                                         //Define el modelo de la tabla de inconsistencias de inactividad externos
        definirModelosUserIncInt();                                             //Define el modelo de la tabla de inconsistencias de UserID incorrecto internos
        definirModelosUserIncExt();                                             //Define el modelo de la tabla de inconsistencias de UserID incorrecto externos
        definirModelosDupXNomInt();                                             //Define el modelo de la tabla de inconsistencias de usuarios duplicados por nombre internos
        definirModelosDupXNomExt();                                             //Define el modelo de la tabla de inconsistencias de usuarios duplicados por nombre externos
        definirModelosPerfilIncInt();                                           //Define el modelo de la tabla de inconsistencias de usuarios internos con perfil incorrecto
        definirModelosPerfilIncExt();                                           //Define el modelo de la tabla de inconsistencias de usuarios externos con perfil incorrecto
        definirModelosNoAutorizadoInt();                                        //Define el modelo de la tabla de inconsistencias de usuarios internos no autorizados
        definirModelosNoAutorizadoExt();                                        //Define el modelo de la tabla de inconsistencias de usuarios externos no autorizados
    }
    
    void definirModelosBajasInt()                                               //Define el modelo de la tabla de inconsistencias de usuarios internos reportados como baja
    {
        DefaultTableModel modeloBajasInt = new DefaultTableModel();             //Crea el objeto modelo de tabla
        
        
        
        Object[] registro = new Object[15];                                     //Crea un arreglo para recibir los elementos de cada renglon
        int i = 0;                                                              //Inicializa la variable para el contador
        try
        {
            Conexion conLocal = new Conexion();                                 //Inicia la conexión local
            conLocal.AbrirLocal(cadenaBD);
            ExecQuery EjecutaLo = new ExecQuery();                              //Crea el objeto para ejecutar la consulta
            bajasInt = EjecutaLo.Cons(conLocal.conexion, Monitoreos.Querys.ResultadosBajasInt());   //Ejecuta la consulta y almacena el resultado en la variable
            
            if(bajasInt.next())                                                 //Verifica que el resultado no esté vacío
            {
                modeloBajasInt.addColumn("Agregar");                            //Crea las columnas necesarias para el reporte
                modeloBajasInt.addColumn("Número de empleado");
                modeloBajasInt.addColumn("User ID");
                modeloBajasInt.addColumn("Nombre");
                modeloBajasInt.addColumn("Fecha de último acceso");
                modeloBajasInt.addColumn("Región");
                modeloBajasInt.addColumn("IP");
                modeloBajasInt.addColumn("ID perfil");
                modeloBajasInt.addColumn("Perfil");
                modeloBajasInt.addColumn("ID Numero de empleado");
                modeloBajasInt.addColumn("Puesto");
                modeloBajasInt.addColumn("Gerencia");
                modeloBajasInt.addColumn("Nombre ");
                modeloBajasInt.addColumn("Fecha de baja");
                modeloBajasInt.addColumn("Estatus");
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
    
    void definirModelosBajasExt()                                               //Define el modelo de la tabla de inconsistencias de usuarios externos reportados como baja
    {
        DefaultTableModel modeloBajasExt = new DefaultTableModel();             //Define el objeto modelo de tabla
        
        
        
        Object[] registro = new Object[15];                                     //Crea el arreglo para almacenar los datos de cada registro
        int i = 0;
        try
        {
            Conexion conLocal = new Conexion();                                 //Crea conexión para la consulta
            conLocal.AbrirLocal(cadenaBD);
            ExecQuery EjecutaLo = new ExecQuery();                              //Crea el objeto de ejecución
            bajasExt = EjecutaLo.Cons(conLocal.conexion, Monitoreos.Querys.ResultadosBajasExt());   //Ejecuta la consulta y almacena el resultado en bajasExt
            
            if(bajasExt.next())                                                 //Valida que no se encuentre vacío el resutado de la consulta
            {
                modeloBajasExt.addColumn("Agregar");                            //agrega las columnas a la tabla
                modeloBajasExt.addColumn("Número de empleado");
                modeloBajasExt.addColumn("User ID");
                modeloBajasExt.addColumn("Nombre");
                modeloBajasExt.addColumn("Fecha de último acceso");
                modeloBajasExt.addColumn("Región");
                modeloBajasExt.addColumn("IP");
                modeloBajasExt.addColumn("ID perfil");
                modeloBajasExt.addColumn("Perfil");
                modeloBajasExt.addColumn("ID Numero de empleado");
                modeloBajasExt.addColumn("Puesto");
                modeloBajasExt.addColumn("Gerencia");
                modeloBajasExt.addColumn("Nombre ");
                modeloBajasExt.addColumn("Fecha de baja");
                modeloBajasExt.addColumn("Estatus");
                bajasExt.beforeFirst();                                         //Establece el puntero en la posición inicial
                while(bajasExt.next())                                          //Lee cada registro hasta que ya no haya más                            
                {
                    for(int k=1; k<16; k++)                                     //Recorre los elementos del registro para obtener cada dato de las columnas
                    {
                        if(k==1)
                        {
                            registro[k-1]=Boolean.TRUE;                         //Si está en la primer columna establece un valor TRUE para que el checkbox esté seleccionado
                        }
                        else
                        {
                            registro[k-1]=bajasExt.getString(k-1);
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
                TableColumn CNumEmp = tablaBajasExt.getColumn("Número de empleado");    //Se llama a la columna
                CNumEmp.setPreferredWidth(140);                                 //Se define su tamaño
                TableColumn CUserID = tablaBajasExt.getColumn("User ID");       //Se llama a la columna
                CUserID.setPreferredWidth(70);                                  //Se define su tamaño
                TableColumn CNombre = tablaBajasExt.getColumn("Nombre");        //Se llama a la columna
                CNombre.setPreferredWidth(360);                                 //Se define su tamaño
                TableColumn CFecha = tablaBajasExt.getColumn("Fecha de último acceso"); //Se llama a la columna
                CFecha.setPreferredWidth(160);                                  //Se define su tamaño
                TableColumn CRegion = tablaBajasExt.getColumn("Región");        //Se llama a la columna
                CRegion.setPreferredWidth(70);                                  //Se define su tamaño
                TableColumn CIP = tablaBajasExt.getColumn("IP");                //Se llama a la columna
                CIP.setPreferredWidth(120);                                     //Se define su tamaño
                TableColumn CIDPerfil = tablaBajasExt.getColumn("ID perfil");   //Se llama a la columna
                CIDPerfil.setPreferredWidth(85);                                //Se define su tamaño
                TableColumn CPerfil = tablaBajasExt.getColumn("Perfil");        //Se llama a la columna
                CPerfil.setPreferredWidth(400);                                 //Se define su tamaño
                TableColumn CBBFNumEMP = tablaBajasExt.getColumn("ID Numero de empleado");  //Se llama a la columna
                CBBFNumEMP.setPreferredWidth(160);                              //Se define su tamaño
                TableColumn CBBFPuesto = tablaBajasExt.getColumn("Puesto");     //Se llama a la columna
                CBBFPuesto.setPreferredWidth(400);                              //Se define su tamaño
                TableColumn CBBFGerencia = tablaBajasExt.getColumn("Gerencia"); //Se llama a la columna
                CBBFGerencia.setPreferredWidth(300);                            //Se define su tamaño
                TableColumn CBBFNombre = tablaBajasExt.getColumn("Nombre ");    //Se llama a la columna
                CBBFNombre.setPreferredWidth(300);                              //Se define su tamaño
                TableColumn CBBFFechaBaja = tablaBajasExt.getColumn("Fecha de baja");   //Se llama a la columna
                CBBFFechaBaja.setPreferredWidth(160);                           //Se define su tamaño
                TableColumn Estatus = tablaBajasExt.getColumn("Estatus");       //Se llama a la columna
                Estatus.setPreferredWidth(160);                                 //Se define su tamaño
            }
            else
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
    
    
    void definirModelosInactividadInt()                                         //Crea el modelo para la tabla de inconsistencias de usuarios internos inactivos
    {
        DefaultTableModel modeloInactividadInt = new DefaultTableModel();       //Define el objeto modelo de tabla
        Object[] registro = new Object[14];                                     //Crea un arreglo para recibir los elementos de cada renglon
        
        try
        {
            Conexion conLocal = new Conexion();                                 //Crea la conexión para la consulta
            conLocal.AbrirLocal(cadenaBD);
            ExecQuery EjecutaLo = new ExecQuery();                              //Crea el objeto para ejecutar la consulta
            inactInt = EjecutaLo.Cons(conLocal.conexion, Monitoreos.Querys.ResultadosInactividadInt()); //Ejecuta la consulta y almacena el resultado en bajasExt
            if(inactInt.next())                                                 //Valida que no se encuentre vacío el resutado de la consulta
            {
                inactInt.beforeFirst();                                         //Regresa a la posición inicial del resultado
                modeloInactividadInt.addColumn("Agregar");
                modeloInactividadInt.addColumn("Número de empleado");
                modeloInactividadInt.addColumn("User ID");
                modeloInactividadInt.addColumn("Nombre");
                modeloInactividadInt.addColumn("Fecha de último acceso");
                modeloInactividadInt.addColumn("Región");
                modeloInactividadInt.addColumn("IP");
                modeloInactividadInt.addColumn("ID perfil");
                modeloInactividadInt.addColumn("Perfil");
                while(inactInt.next())                                          //Lee cada registro hasta que ya no haya más
                {
                    for(int k=1; k<10; k++)                                     //Recorre los elementos del registro para obtener cada dato de las columnas
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
                TableColumn CNumEmp = tablaInacInt.getColumn("Número de empleado"); //Se llama a la columna
                CNumEmp.setPreferredWidth(140);                                 //Se define su tamaño
                TableColumn CUserID = tablaInacInt.getColumn("User ID");        //Se llama a la columna
                CUserID.setPreferredWidth(70);                                  //Se define su tamaño
                TableColumn CNombre = tablaInacInt.getColumn("Nombre");         //Se llama a la columna
                CNombre.setPreferredWidth(360);                                 //Se define su tamaño
                TableColumn CFecha = tablaInacInt.getColumn("Fecha de último acceso");  //Se llama a la columna
                CFecha.setPreferredWidth(160);                                  //Se define su tamaño
                TableColumn CRegion = tablaInacInt.getColumn("Región");         //Se llama a la columna
                CRegion.setPreferredWidth(70);                                  //Se define su tamaño
                TableColumn CIP = tablaInacInt.getColumn("IP");                 //Se llama a la columna
                CIP.setPreferredWidth(120);                                     //Se define su tamaño
                TableColumn CIDPerfil = tablaInacInt.getColumn("ID perfil");    //Se llama a la columna
                CIDPerfil.setPreferredWidth(85);                                //Se define su tamaño
                TableColumn CPerfil = tablaInacInt.getColumn("Perfil");         //Se llama a la columna
                CPerfil.setPreferredWidth(400);                                 //Se define su tamaño
                
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
        Object[] registro = new Object[14];                                     //Crea un arreglo para recibir los elementos de cada renglon
        
        
        try
        {
            Conexion conLocal = new Conexion();                                 //Crea la conexión para la consulta
            conLocal.AbrirLocal(cadenaBD);
            ExecQuery EjecutaLo = new ExecQuery();                              //Crea el objeto para ejecutar la consulta
            inactExt = EjecutaLo.Cons(conLocal.conexion, Monitoreos.Querys.ResultadosInactividadExt()); //Ejecuta la consulta y almacena el resultado en bajasExt
            if(inactExt.next())                                                 //Valida que no se encuentre vacío el resutado de la consulta
            {
                inactExt.beforeFirst();                                         //Regresa a la posición inicial del resultado
                modeloInactividadExt.addColumn("Agregar");
                modeloInactividadExt.addColumn("Número de empleado");
                modeloInactividadExt.addColumn("User ID");
                modeloInactividadExt.addColumn("Nombre");
                modeloInactividadExt.addColumn("Fecha de último acceso");
                modeloInactividadExt.addColumn("Región");
                modeloInactividadExt.addColumn("IP");
                modeloInactividadExt.addColumn("ID perfil");
                modeloInactividadExt.addColumn("Perfil");
                while(inactExt.next())                                          //Lee cada registro hasta que ya no haya más
                {
                    for(int k=1; k<10; k++)                                     //Recorre los elementos del registro para obtener cada dato de las columnas
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
                TableColumn CNumEmp = tablaInacExt.getColumn("Número de empleado");     //Se llama a la columna
                CNumEmp.setPreferredWidth(140);                                 //Se define su tamaño
                TableColumn CUserID = tablaInacExt.getColumn("User ID");        //Se llama a la columna
                CUserID.setPreferredWidth(70);                                  //Se define su tamaño
                TableColumn CNombre = tablaInacExt.getColumn("Nombre");         //Se llama a la columna
                CNombre.setPreferredWidth(360);                                 //Se define su tamaño
                TableColumn CFecha = tablaInacExt.getColumn("Fecha de último acceso");  //Se llama a la columna
                CFecha.setPreferredWidth(160);                                  //Se define su tamaño
                TableColumn CRegion = tablaInacExt.getColumn("Región");         //Se llama a la columna
                CRegion.setPreferredWidth(70);                                  //Se define su tamaño
                TableColumn CIP = tablaInacExt.getColumn("IP");                 //Se llama a la columna
                CIP.setPreferredWidth(120);                                     //Se define su tamaño
                TableColumn CIDPerfil = tablaInacExt.getColumn("ID perfil");    //Se llama a la columna
                CIDPerfil.setPreferredWidth(85);                                //Se define su tamaño
                TableColumn CPerfil = tablaInacExt.getColumn("Perfil");         //Se llama a la columna
                CPerfil.setPreferredWidth(400);                                 //Se define su tamaño
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
                modeloPerfilIncInt.addColumn("Matriz Puesto Funcional");
                modeloPerfilIncInt.addColumn("Matriz Perfil");
                modeloPerfilIncInt.addColumn("ID Número de empleado");          
                modeloPerfilIncInt.addColumn("Puesto");
                modeloPerfilIncInt.addColumn("Departamento");
                modeloPerfilIncInt.addColumn("Gerencia");
                modeloPerfilIncInt.addColumn("ID Nombre");
                while(perfilInt.next())                                         //Lee cada registro hasta que ya no haya más
                {
                    for(int k=1; k<20; k++)                                     //Recorre los elementos del registro para obtener cada dato de las columnas
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
                TableColumn CPF = tablaPerfilInt.getColumn("Matriz Puesto Funcional");       //Se llama a la columna
                CPF.setPreferredWidth(300);                                 //Se define su tamaño
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
                TableColumn CDepto = tablaPerfilInt.getColumn("Departamento");   //Se llama a la columna
                CDepto.setPreferredWidth(400);   
              
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
                modeloPerfilIncExt.addColumn("Matriz Puesto Funcional");
                modeloPerfilIncExt.addColumn("Matriz Perfil");
                modeloPerfilIncExt.addColumn("ID Número de empleado");
                modeloPerfilIncExt.addColumn("Puesto");
                modeloPerfilIncExt.addColumn("Departamento");
                modeloPerfilIncExt.addColumn("Gerencia");
                modeloPerfilIncExt.addColumn("ID Nombre");
                while(perfilExt.next())                                         //Lee cada registro hasta que ya no haya más
                {
                    for(int k=1; k<20; k++)                                     //Recorre los elementos del registro para obtener cada dato de las columnas
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
                TableColumn CPF = tablaPerfilExt.getColumn("Matriz Puesto Funcional");       //Se llama a la columna
                CPF.setPreferredWidth(300);   
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
                TableColumn CDepto = tablaPerfilExt.getColumn("Departamento");   //Se llama a la columna
                CDepto.setPreferredWidth(400);                                //Se define su tamaño
                
              
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

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
                .addContainerGap(57, Short.MAX_VALUE))
        );

        jPanel1.setPreferredSize(new java.awt.Dimension(1800, 551));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setMinimumSize(new java.awt.Dimension(1000, 419));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(1800, 419));

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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
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
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE))
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
                .addContainerGap(57, Short.MAX_VALUE))
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
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
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
                .addGap(597, 597, 597))
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
            .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
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
                .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTable tablaBajasExt;
    private javax.swing.JTable tablaBajasInt;
    private javax.swing.JTable tablaDupExt;
    private javax.swing.JTable tablaDupInt;
    private javax.swing.JTable tablaInacExt;
    private javax.swing.JTable tablaInacInt;
    private javax.swing.JTable tablaNoAutoExt;
    private javax.swing.JTable tablaNoAutoInt;
    private javax.swing.JTable tablaPerfilExt;
    private javax.swing.JTable tablaPerfilInt;
    private javax.swing.JTable tablaUsrExt;
    private javax.swing.JTable tablaUsrInt;
    // End of variables declaration//GEN-END:variables
}
