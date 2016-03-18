/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MonAD;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author VS3XXBD
 */
public class Conect {
    
    public static void cruces(int reg, String cadenaBD)                         //Prepara la información, el entorno de trabajo y realiza los cruces
    {
        //System.out.println("Region en cruces " + reg);
        System.out.println("Eliminando tablas temporales");
        eliminaTablas(reg, cadenaBD);                                           //Elimina las tablas internos, externos y genéricos.
        System.out.println("Creando tablas de trabajo");
        creaTablasTrabajo(reg, cadenaBD);                                       //Crea tablas duplicadas de las originales para altearrlas sin dañar la integridad de la información enviada
        System.out.println("Generando números de empleado");
        numEmp(reg, cadenaBD);                                                  //Llama al stored procedure para calcular el número de empleado del usuario
        System.out.println("Obteniendo usuarios externos");
        tablaExt(reg, cadenaBD);                                                //Crea la tabla externos con los elementos de la tabla genéricos que coinciden con nómina.
        System.out.println("Obteniendo usuarios genéricos");
        tablaGen(reg, cadenaBD);                                                //Crea tabla GEN con el contenido de RN donde NUMEMP=0 con descripción "gen", full_name "usuario", "desarrollo", "outsourcing" y los borra de RN
        System.out.println("Obteniendo usuarios internos");
        tablaInt(reg, cadenaBD);                                                //Crea la tabla internos y copia todo el contenido de RN                                            
        System.out.println("Realizando cruce de usuarios internos");
        cruceint(reg, cadenaBD);                                                //Crea la tabla cruceint con todos los usuarios incluyendo sus datos de nómina
        System.out.println("Realizando cruce de usuarios externos");
        cruceext(reg, cadenaBD);                                                //Crea la tabla cruceext con todos los usuarios incluyendo sus datos de nómina
        
        
        
    }
    
    public static void filtros (int reg, String date, String cadenaBD)          //Realiza el análisis de información
    {
        //System.out.println("Fecha en filtro " + date);
        System.out.println("Buscando elementos que no se encuentran registrados en nómina");
        noennomina(reg, cadenaBD);                                              //Crea la tabla de incidencias de usuarios internos y externos no registrados en nómina
        System.out.println("Buscando usuarios internos dados de baja");
        bajasint(reg, cadenaBD);                                                //Crea la tabla de incidencias de usuarios internos que se encuentran dados de baja
        System.out.println("Buscando usuarios externos dados de baja");
        bajasext(reg, cadenaBD);                                                //Crea la tabla de incidencias de usuarios externos que se encuentran dados de baja
        System.out.println("Buscando usuarios con inactividad");
        inactividad(reg, date, cadenaBD);                                       //Crea la tabla de incidencias de usuarios internos y externos con inactividad
        System.out.println("Buscado usuarios sin fecha de login y con fecha de creación mayor a 60 días");
        nologin(reg, date, cadenaBD);                                           //Crea la tabla de incidencias de usuarios internos y externos que no han hecho login y tienen fecha de creación mayor a 60 días
        System.out.println("Buscando usuarios con USERID incorrecto");
        usridinc(reg, cadenaBD);                                                //Crea la tabla de incidencias de usuarios internos y externos con UserID incorrecto
        System.out.println("Buscando usuarios duplicados por nombre");
        dupxnom(reg, cadenaBD);                                                 //Crea la tabla de incidencias de usuarios internos y externos duplicados por nombre
        System.out.println("Buscando usuarios duplicados por número de emplerado");
        dupxnum(reg, cadenaBD);                                                 //Crea la tabla de incidencias de usuarios internos y externos duplicados por número de empleado
        System.out.println("Buscando usuarios duplicados por USERID");
        dupxusr(reg, cadenaBD);                                                 //Crea la tabla de incidencias de usuarios internos y externos duplicados por UserID
    }
    
    public static void bajasint(int reg, String cadenaBD)                       //Crea la tabla de incidencias de usuarios internos que se encuentran dados de baja
    {
        try
        {            
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());        // Se registra el Driver de MySQL
            String[] parametros = cadenaBD.split("\\|");
                                                                                // Se obtiene una conexión con la base de datos. 
            Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/"+parametros[0]+"",parametros[1],parametros[2]);           
            CallableStatement cstmt2 = conexion.prepareCall(nulos(reg));        // Se crea un Statement

            int rs2 = cstmt2.executeUpdate("create table if not exists bajasint" + Integer.toString(reg) + " select * from cruceint" + Integer.toString(reg)
            + " where IDESTATUS = 'ELIMINADO' ");                               //Se ejecuta creación de tabla con usuaios dados de baja
           rs2 = cstmt2.executeUpdate("delete from cruceint" + Integer.toString(reg) + " where IDESTATUS = 'ELIMINADO' ");  //Se eliminan usuarios dados de baja de la tabla de trabajo
            conexion.close();                                                   // Se cierra la conexión con la base de datos.
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public static void bajasext(int reg, String cadenaBD)                       //Crea la tabla de incidencias de usuarios externos que se encuentran dados de baja
    {
        try
        {
            
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());        // Se registra el Driver de MySQL            
            String[] parametros = cadenaBD.split("\\|");
            
                                                                                // Se obtiene una conexión con la base de datos. 
            Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/"+parametros[0]+"",parametros[1],parametros[2]);
            
                                                                    
            CallableStatement cstmt2 = conexion.prepareCall(nulos(reg));        // Se crea un Statement
                                                                                //Se ejecuta la sentencia para crear tabla de usuarios dados de baja
            int rs2 = cstmt2.executeUpdate("create table if not exists bajasext" + Integer.toString(reg) 
                    + " select * from cruceext" + Integer.toString(reg) + " where IDESTATUS = 'ELIMINADO'");
            rs2 = cstmt2.executeUpdate("delete from cruceext" + Integer.toString(reg) + " where IDESTATUS = 'ELIMINADO'");//Se eliminan los usuarios dados de baja

            
            conexion.close();                                                   // Se cierra la conexión con la base de datos.
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public static void noennomina(int reg, String cadenaBD)                     //Crea la tabla de incidencias de usuarios internos y externos no registrados en nómina
    {
        try
        {
            
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());        // Se registra el Driver de MySQL            
            String[] parametros = cadenaBD.split("\\|");
            
                                                                                // Se obtiene una conexión con la base de datos. 
            Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/"+parametros[0]+"",parametros[1],parametros[2]);
            
            String sentenciaint = "create table if not exists nonominaint" + Integer.toString(reg)  //Se crea la instrucción para crear la tabla de usuarios internos no registrados en nómina
                    + " SELECT * FROM cruceint" + Integer.toString(reg) + " where IDUSUARIO is null";
            
            String sentenciaext = "create table if not exists nonominaext" + Integer.toString(reg) //Se crea la instrucción para crear la tabla de usuarios externos no registrados en nómina
                    + " SELECT * FROM cruceext" + Integer.toString(reg) + " where idusuario is null";
            
            
            Statement stmt2 = conexion.createStatement();                       //Se crea la sentencia

            stmt2.executeUpdate(sentenciaint);                                  //Se ejecuta la instrucción de usuarios internos
            stmt2.executeUpdate(sentenciaext);                                  //Se ejecuta la instrucción de usuarios externos
            
            String sentenciaint2 = "delete from cruceint" + Integer.toString(reg) + " where IDUSUARIO is null"; //Se crea la instrucción para borrar los usuarios internos no registrados en nóina de la tabla de cruce
            String sentenciaext2 = "delete from cruceext" + Integer.toString(reg) + " where idusuario is null"; //Se crea la instrucción para borrar los usuarios externos no registrados en nóina de la tabla de cruce
            stmt2.executeUpdate(sentenciaint2);                                 //Se ejecuta la instrucción de borrado de internos
            stmt2.executeUpdate(sentenciaext2);                                 //Se ejecuta la instrucción de borrado de externos
            
            conexion.close();                                                   // Se cierra la conexión con la base de datos.
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    
    }
    
    
    public static void inactividad(int reg, String date, String cadenaBD)       //Crea la tabla de incidencias de usuarios internos y externos con inactividad
    {
        //System.out.println("Fecha en inactividad " + date);
        try
        {
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());        // Se registra el Driver de MySQL            
            String[] parametros = cadenaBD.split("\\|");
            
                                                                                // Se obtiene una conexión con la base de datos. 
            Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/"+parametros[0]+"",parametros[1],parametros[2]);
                                                                                //Se crea la instrucción para crear la tabla de usuarios internos inactivos
            String sentenciaint = "create table if not exists inactividadint" + Integer.toString(reg) 
                    + " SELECT * FROM cruceint" + Integer.toString(reg) + " where datediff('" + date + "', Last_Logon_Timestamp) >= 61";
                                                                                //Se crea la instrucción para crear la tabla de usuarios internos inactivos
            String sentenciaext = "create table if not exists inactividadext" + Integer.toString(reg) 
                    + " SELECT * FROM cruceext" + Integer.toString(reg) + " where datediff('" + date + "', Last_Logon_Timestamp) >= 61";
            
            Statement stmt2 = conexion.createStatement();                       //Se crea el objeto de sentencia
            stmt2.executeUpdate(sentenciaint);                                  //Se ejecuta la sentencia de creación de inactividad internos
            stmt2.executeUpdate(sentenciaext);                                  //Se ejecuta la sentencia de creación de inactividad externos
            
            String sentenciaint2 = "delete from cruceint" + Integer.toString(reg) + " where datediff('" + date + "', Last_Logon_Timestamp) >= 61";  //Se crea la instrucción de borrado de usuarios internos inactivos de la tabla de trabajo de cruce
            String sentenciaext2 = "delete from cruceext" + Integer.toString(reg) + " where datediff('" + date + "', Last_Logon_Timestamp) >= 61";  //Se crea la instrucción de borrado de usuarios externos inactivos de la tabla de trabajo de cruce
            stmt2.executeUpdate(sentenciaint2);                                 //Se ejecuta la sentencia de borrado de inactivos internos            
            stmt2.executeUpdate(sentenciaext2);                                 //Se ejecuta la sentencia de borrado de inactivos externos
            
            
            conexion.close();                                                   // Se cierra la conexión con la base de datos.
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    
    
    }
        
    public static void usridinc(int reg, String cadenaBD)                       //Crea la tabla de incidencias de usuarios internos y externos con UserID incorrecto
    {
        try
        {
            
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());        // Se registra el Driver de MySQL            
            String[] parametros = cadenaBD.split("\\|");
            
                                                                                // Se obtiene una conexión con la base de datos. 
            Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/"+parametros[0]+"",parametros[1],parametros[2]);
            
            String sentenciaint = "create table if not exists usrincint" + Integer.toString(reg)    //Se crea instrucción de creación de  tabla de usuarios internos con UserID incorrecto
                    + " SELECT * FROM cruceint" + Integer.toString(reg) + " where User_name != idusuario";
            
            String sentenciaext = "create table if not exists usrincext" + Integer.toString(reg)    //Se crea instrucción de creación de  tabla de usuarios externos con UserID incorrecto
                    + " SELECT * FROM cruceext" + Integer.toString(reg) + " where User_name != idusuario";

            
            Statement stmt2 = conexion.createStatement();                       //Se crea el objeto de sentencia
            stmt2.executeUpdate(sentenciaint);                                  //Se ejecuta la instrucción de USerID incorrecto de usuarios internos
            stmt2.executeUpdate(sentenciaext);                                  //Se ejecuta la instrucción de USerID incorrecto de usuarios externos
            
            String sentenciaint2 = "delete from cruceint" + Integer.toString(reg) + " where User_name != idusuario";    //Se crea la instrucción de borrado de usuarios internos con UserID incorrecto de la tabla de cruce
            String sentenciaext2 = "delete from cruceext" + Integer.toString(reg) + " where User_name != idusuario";    //Se crea la instrucción de borrado de usuarios externos con UserID incorrecto de la tabla de cruce
            stmt2.executeUpdate(sentenciaint2);                                 //Se ejectura el borrado de usuarios internos con UserID incorrecto
                                    
            stmt2.executeUpdate(sentenciaext2);                                 //Se ejectura el borrado de usuarios externos con UserID incorrecto
            
            conexion.close();                                                   // Se cierra la conexión con la base de datos.
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    
    
    }
    
    public static void nologin(int reg, String date, String cadenaBD)           //Crea la tabla de incidencias de usuarios internos y externos que no han hecho login y tienen fecha de creación mayor a 60 días
    {
        try
        {
            
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());        // Se registra el Driver de MySQL
            String[] parametros = cadenaBD.split("\\|");
            
                                                                                // Se obtiene una conexión con la base de datos. 
            Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/"+parametros[0]+"",parametros[1],parametros[2]);
                                                                                //Se crea la instrucción de obtención de usuarios internos que no tienen fecha de login y tienen fecha de creación mayor a 60 días
            String sentenciaint = "create table if not exists nologinint" + Integer.toString(reg) 
                    + " SELECT * FROM cruceint" + Integer.toString(reg) + " where Last_Logon_Timestamp is null and datediff('" + date + "', Creation_Date) >= 61";
                                                                                //Se crea la instrucción de obtención de usuarios externos que no tienen fecha de login y tienen fecha de creación mayor a 60 días
            String sentenciaext = "create table if not exists nologinext" + Integer.toString(reg) 
                    + " SELECT * FROM cruceext" + Integer.toString(reg) + " where Last_Logon_Timestamp is null and datediff('" + date + "', Creation_Date) >= 61";
            
            Statement stmt2 = conexion.createStatement();                       //Se crea el objeto de senttencia
            stmt2.executeUpdate(sentenciaint);                                  //Se ejecuta la obtención de usuarios internos sin login
            stmt2.executeUpdate(sentenciaext);                                  //Se ejecuta la obtención de usuarios externos sin login
                                                                                //Se crea instrucción de borrado de usuarios internos sin login
            String sentenciaint2 = "delete from cruceint" + Integer.toString(reg) + " where Last_Logon_Timestamp is null and datediff('" + date + "', Creation_Date) >= 61";
                                                                                //Se crea instrucción de borrado de usuarios externos sin login
            String sentenciaext2 = "delete from cruceext" + Integer.toString(reg) + " where Last_Logon_Timestamp is null and datediff('" + date + "', Creation_Date) >= 61";
            stmt2.executeUpdate(sentenciaint2);                                 //Se ejecuta borrado de usuarios internos sin login            
            stmt2.executeUpdate(sentenciaext2);                                 //Se ejecuta borrado de usuarios internos sin login
            
            
            
            
            conexion.close();                                                   // Se cierra la conexión con la base de datos.
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    
    
    
    }
    
    
    public static void dupxnom(int reg, String cadenaBD)                        //Crea la tabla de incidencias de usuarios internos y externos duplicados por nombre
    {
        try
        {
            // Se registra el Driver de MySQL
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());
            String[] parametros = cadenaBD.split("\\|");
            
                                                                                // Se obtiene una conexión con la base de datos. 
            Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/"+parametros[0]+"",parametros[1],parametros[2]);
                                                                                //Se crea la instrucción para crea la tabla de usuarios internos duplicados por nombre
            String sentenciaint = "create table if not exists dupxnombreint" + Integer.toString(reg) 
                    + " SELECT DISTINCT * FROM cruceint" + Integer.toString(reg) + " WHERE Full_Name In (SELECT Full_Name FROM cruceint" + Integer.toString(reg)
                    + " As Tmp GROUP BY Full_Name HAVING Count(*) > 1) order by Full_Name";
                                                                                //Se crea la instrucción para crea la tabla de usuarios externos duplicados por nombre
            String sentenciaext = "create table if not exists dupxnombreext" + Integer.toString(reg) 
                    + " SELECT DISTINCT * FROM cruceext" + Integer.toString(reg) + " WHERE Full_Name In (SELECT Full_Name FROM cruceext" + Integer.toString(reg)
                    + " As Tmp GROUP BY Full_Name HAVING Count(*) > 1) order by Full_Name";
            
            Statement stmt2 = conexion.createStatement();                       //Se crea el objeto de sentencia
            stmt2.executeUpdate(sentenciaint);                                  //Se ejecuta la obtención de usuarios internos duplicados por nombre
            stmt2.executeUpdate(sentenciaext);                                  //Se ejecuta la obtención de usuarios externos duplicados por nombre
            
            conexion.close();                                                   // Se cierra la conexión con la base de datos.
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    
    }
    
    public static void dupxnum(int reg, String cadenaBD)                        //Crea la tabla de incidencias de usuarios internos y externos duplicados por número de empleado
    {
        try
        {
            
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());        // Se registra el Driver de MySQL
            String[] parametros = cadenaBD.split("\\|");
            
                                                                                // Se obtiene una conexión con la base de datos. 
            Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/"+parametros[0]+"",parametros[1],parametros[2]);
                                                                                //Se crea la instrucción para obtener los usuarios internos duplicados por numero de empleado
            String sentenciaint = "create table if not exists dupxnumint" + Integer.toString(reg) 
                    + " SELECT DISTINCT * FROM cruceint" + Integer.toString(reg) + " WHERE numemp In (SELECT numemp FROM cruceint" + Integer.toString(reg)
                    + " As Tmp GROUP BY numemp HAVING Count(*) > 1) order by numemp";
                                                                                //Se crea la instrucción para obtener los usuarios externos duplicados por numero de empleado
            String sentenciaext = "create table if not exists dupxnumext" + Integer.toString(reg) 
                    + " SELECT DISTINCT * FROM cruceext" + Integer.toString(reg) + " WHERE IDNUMEMP In (SELECT IDNUMEMP FROM cruceext" + Integer.toString(reg)
                    + " As Tmp GROUP BY IDNUMEMP HAVING Count(*) > 1) order by IDNUMEMP";
            
            
            Statement stmt2 = conexion.createStatement();                       //Se crea el objeto de sentencia
            stmt2.executeUpdate(sentenciaint);                                  //Se ejecuta la instrucción para obtener los usuarios internos duplicados por nombre
            stmt2.executeUpdate(sentenciaext);                                  //Se ejecuta la instrucción para obtener los usuarios externos duplicados por nombre
              
            
            conexion.close();                                                   // Se cierra la conexión con la base de datos.
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    
    
    }
    
    public static void dupxusr(int reg, String cadenaBD)                        //Crea la tabla de incidencias de usuarios internos y externos duplicados por UserID
    {
        try
        {
            
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());        // Se registra el Driver de MySQL
            String[] parametros = cadenaBD.split("\\|");
            
                                                                                // Se obtiene una conexión con la base de datos. 
            Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/"+parametros[0]+"",parametros[1],parametros[2]);
                                                                                //Se crea la instrucción para obtener los usuarios internos duplicados por UserID
            String sentenciaint = "create table if not exists dupxusrint" + Integer.toString(reg) 
                    + " SELECT DISTINCT * FROM cruceint" + Integer.toString(reg) + " WHERE USER_NAME In (SELECT USER_NAME FROM cruceint" + Integer.toString(reg)
                    + " As Tmp GROUP BY USER_NAME HAVING Count(*) > 1) order by USER_NAME";
                                                                                //Se crea la instrucción para obtener los usuarios externos duplicados por UserID
            String sentenciaext = "create table if not exists dupxusrext" + Integer.toString(reg) 
                    + " SELECT DISTINCT * FROM cruceext" + Integer.toString(reg) + " WHERE USER_NAME In (SELECT USER_NAME FROM cruceext" + Integer.toString(reg)
                    + " As Tmp GROUP BY USER_NAME HAVING Count(*) > 1) order by USER_NAME";
            
            
            Statement stmt2 = conexion.createStatement();                       //Se crea el objeto de sentencia
            stmt2.executeUpdate(sentenciaint);                                  //Se ejcuta la instrucción para obtener los usuarios internos duplicados por UserID
            stmt2.executeUpdate(sentenciaext);                                  //Se ejcuta la instrucción para obtener los usuarios externos duplicados por UserID
            
            conexion.close();                                                   // Se cierra la conexión con la base de datos.
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    
    
    
    }
    
    public static void limpiaTablas(String cadenaBD)                            //Borra todas las tablas de trabajo
    {
        int rs2;
        try
        {               
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());        // Se registra el Driver de MySQL
            String[] parametros = cadenaBD.split("\\|");
//            
                                                                //            // Se obtiene una conexión con la base de datos. 
            Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/"+parametros[0]+"",parametros[1],parametros[2]);
            
            
            
            Statement cstmt2 = conexion.createStatement();                      // Se crea un Statement, para realizar la consulta
            for(int reg=1; reg<10; reg++)                                       //Se eliminan las tablas de trabajo de todas las regiones
            {
                rs2 = cstmt2.executeUpdate("drop table if exists internos" + Integer.toString(reg));
                rs2 = cstmt2.executeUpdate("drop table if exists externos" + Integer.toString(reg));
                rs2 = cstmt2.executeUpdate("drop table if exists genericos" + Integer.toString(reg));
                rs2 = cstmt2.executeUpdate("drop table if exists cruceint" + Integer.toString(reg));
                rs2 = cstmt2.executeUpdate("drop table if exists cruceext" + Integer.toString(reg));
                rs2 = cstmt2.executeUpdate("drop table if exists nonominaext" + Integer.toString(reg));
                rs2 = cstmt2.executeUpdate("drop table if exists nonominaint" + Integer.toString(reg));
                rs2 = cstmt2.executeUpdate("drop table if exists bajasint" + Integer.toString(reg));
                rs2 = cstmt2.executeUpdate("drop table if exists bajasext" + Integer.toString(reg));
                rs2 = cstmt2.executeUpdate("drop table if exists inactividadint" + Integer.toString(reg));
                rs2 = cstmt2.executeUpdate("drop table if exists inactividadext" + Integer.toString(reg)); 
                rs2 = cstmt2.executeUpdate("drop table if exists nologinint" + Integer.toString(reg));
                rs2 = cstmt2.executeUpdate("drop table if exists nologinext" + Integer.toString(reg)); 
                rs2 = cstmt2.executeUpdate("drop table if exists usrincint" + Integer.toString(reg));
                rs2 = cstmt2.executeUpdate("drop table if exists usrincext" + Integer.toString(reg)); 
                rs2 = cstmt2.executeUpdate("drop table if exists dupxnombreint" + Integer.toString(reg));
                rs2 = cstmt2.executeUpdate("drop table if exists dupxnombreext" + Integer.toString(reg)); 
                rs2 = cstmt2.executeUpdate("drop table if exists dupxnumint" + Integer.toString(reg));
                rs2 = cstmt2.executeUpdate("drop table if exists dupxnumext" + Integer.toString(reg)); 
                rs2 = cstmt2.executeUpdate("drop table if exists dupxusrint" + Integer.toString(reg));
                rs2 = cstmt2.executeUpdate("drop table if exists dupxusrext" + Integer.toString(reg)); 
                rs2 = cstmt2.executeUpdate("drop table if exists externosr" + Integer.toString(reg)); 
                rs2 = cstmt2.executeUpdate("drop table if exists r" + Integer.toString(reg)); 
            }
            

            // Se cierra la conexión con la base de datos.
            conexion.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    
    public static void eliminaTablas(int reg, String cadenaBD)                  //Elimina las tablas internos, externos y genéricos.
    {

        try
        {
            
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());        // Se registra el Driver de MySQL
            String[] parametros = cadenaBD.split("\\|");
            
                                                                                // Se obtiene una conexión con la base de datos. 
            Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/"+parametros[0]+"",parametros[1],parametros[2]);
             
            
            
            CallableStatement cstmt2 = conexion.prepareCall(nulos(reg));        //Se eliminan todas las tablas de trabajo de una región
            int rs2 = cstmt2.executeUpdate("drop table if exists internos" + Integer.toString(reg));
            rs2 = cstmt2.executeUpdate("drop table if exists externos" + Integer.toString(reg));
            rs2 = cstmt2.executeUpdate("drop table if exists genericos" + Integer.toString(reg));
            rs2 = cstmt2.executeUpdate("drop table if exists cruceint" + Integer.toString(reg));
            rs2 = cstmt2.executeUpdate("drop table if exists cruceext" + Integer.toString(reg));
            rs2 = cstmt2.executeUpdate("drop table if exists nonominaext" + Integer.toString(reg));
            rs2 = cstmt2.executeUpdate("drop table if exists nonominaint" + Integer.toString(reg));
            rs2 = cstmt2.executeUpdate("drop table if exists bajasint" + Integer.toString(reg));
            rs2 = cstmt2.executeUpdate("drop table if exists bajasext" + Integer.toString(reg));
            rs2 = cstmt2.executeUpdate("drop table if exists inactividadint" + Integer.toString(reg));
            rs2 = cstmt2.executeUpdate("drop table if exists inactividadext" + Integer.toString(reg)); 
            rs2 = cstmt2.executeUpdate("drop table if exists nologinint" + Integer.toString(reg));
            rs2 = cstmt2.executeUpdate("drop table if exists nologinext" + Integer.toString(reg)); 
            rs2 = cstmt2.executeUpdate("drop table if exists usrincint" + Integer.toString(reg));
            rs2 = cstmt2.executeUpdate("drop table if exists usrincext" + Integer.toString(reg)); 
            rs2 = cstmt2.executeUpdate("drop table if exists dupxnombreint" + Integer.toString(reg));
            rs2 = cstmt2.executeUpdate("drop table if exists dupxnombreext" + Integer.toString(reg)); 
            rs2 = cstmt2.executeUpdate("drop table if exists dupxnumint" + Integer.toString(reg));
            rs2 = cstmt2.executeUpdate("drop table if exists dupxnumext" + Integer.toString(reg)); 
            rs2 = cstmt2.executeUpdate("drop table if exists dupxusrint" + Integer.toString(reg));
            rs2 = cstmt2.executeUpdate("drop table if exists dupxusrext" + Integer.toString(reg)); 
            rs2 = cstmt2.executeUpdate("drop table if exists externosr" + Integer.toString(reg)); 
            rs2 = cstmt2.executeUpdate("drop table if exists r" + Integer.toString(reg)); 
            
            conexion.close();                                                   // Se cierra la conexión con la base de datos.
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    
    public static void tablaExt(int reg, String cadenaBD)                       //Crea la tabla externos                                          
    {

        try
        {
            
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());        // Se registra el Driver de MySQL
            String[] parametros = cadenaBD.split("\\|");
            
                                                                                // Se obtiene una conexión con la base de datos. 
            Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/"+parametros[0]+"",parametros[1],parametros[2]);
            
            CallableStatement cstmt2 = conexion.prepareCall(nulos(reg));        // Se crea un Statement, para realizar la consulta
            String condicion;
            condicion= " where description like '%externo%' "                   //Se crea la condición para identificar los usuarios externos
                    + " or substring( User_Name from 4) like '%G%' "
                    + " or substring( User_Name from 4) like '%H%' "
                    + " or substring( User_Name from 4) like '%I%' "
                    + " or substring( User_Name from 4) like '%J%' "
                    + " or substring( User_Name from 4) like '%K%' "
                    + " or substring( User_Name from 4) like '%L%' "
                    + " or substring( User_Name from 4) like '%M%' "
                    + " or substring( User_Name from 4) like '%N%' "
                    + " or substring( User_Name from 4) like '%O%' "
                    + " or substring( User_Name from 4) like '%P%' "
                    + " or substring( User_Name from 4) like '%Q%' "
                    + " or substring( User_Name from 4) like '%R%' "
                    + " or substring( User_Name from 4) like '%S%' "
                    + " or substring( User_Name from 4) like '%T%' "
                    + " or substring( User_Name from 4) like '%U%' "
                    + " or substring( User_Name from 4) like '%V%' "
                    + " or substring( User_Name from 4) like '%W%' "
                    + " or substring( User_Name from 4) like '%X%' "
                    + " or substring( User_Name from 4) like '%Y%' "
                    + " or substring( User_Name from 4) like '%Z%' ";
            int rs2 = 0;
                                                                                //Se ejecuta la instrucción para crear la tabla de usuarios externos con numero de empeldo igual a cero
            rs2 = cstmt2.executeUpdate("create table if not exists externos" + Integer.toString(reg) + " select * from r" + Integer.toString(reg) + " where numemp=0 ");
                                                                                //Se ejecuta la instrucción para borrar de la tabla de usaurios de la aplicación los usuarios externos con numero de empeldo igual a cero
            rs2 = cstmt2.executeUpdate("delete from r" + Integer.toString(reg) + " where numemp=0 ");
                                                                                //Se agregan a los usuarios externos los usuarios de la aplicación que cumplen con la condición
            rs2 = cstmt2.executeUpdate("insert into externos" + Integer.toString(reg) + " select * from r" + Integer.toString(reg) + condicion);
                                                                                //Se borran los usuarios de la aplicación que conincidieron con la condición de usuarios externos
            rs2 = cstmt2.executeUpdate("delete from r" + Integer.toString(reg) + condicion);
            
            
            conexion.close();                                                   // Se cierra la conexión con la base de datos.
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    
    public static void tablaInt(int reg, String cadenaBD)                       //Crea la tabla internos y copia todo el contenido de RN                                            
    {

        try
        {
            
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());        // Se registra el Driver de MySQL
            String[] parametros = cadenaBD.split("\\|");
            
                                                                                // Se obtiene una conexión con la base de datos. 
            Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/"+parametros[0]+"",parametros[1],parametros[2]);
            
                                                                                // Se crea un Statement, para realizar la consulta
            CallableStatement cstmt2 = conexion.prepareCall(nulos(reg));
                                                                                //Se ejecuta instrucción para crear la tabla de usuarios internos de los usuarios del aplicativo
            int rs2 = cstmt2.executeUpdate("create table if not exists internos" + Integer.toString(reg) + " select * from r" + Integer.toString(reg));
           
            conexion.close();                                                   // Se cierra la conexión con la base de datos.
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    
    public static void creaTablasTrabajo(int reg, String cadenaBD)              //Crea la tabla internos y copia todo el contenido de RN                                            
    {

        try
        {
            
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());        // Se registra el Driver de MySQL
            String[] parametros = cadenaBD.split("\\|");
            
                                                                                // Se obtiene una conexión con la base de datos. 
            Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/"+parametros[0]+"",parametros[1],parametros[2]);
            Statement cstmt2 = conexion.createStatement();                      // Se crea un Statement, para realizar la consulta
                                                                                //Se ejecuta la instrucción para crear las tablas de trabajo con los usuarios de la aplicación
            int rs2 = cstmt2.executeUpdate("create table if not exists r" + Integer.toString(reg) + " select * from r" + Integer.toString(reg) + "c" + parametros[3]);
            rs2 = cstmt2.executeUpdate("delete from r" + Integer.toHexString(reg) + " where Account_disabled = 1 ");//Ejecuta la instrucción para eliminar los usuarios de la aplicación que tienen la cuenta deshabilitada
            

            conexion.close();                                                   // Se cierra la conexión con la base de datos.
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    
    public static void tablaGen(int reg, String cadenaBD)                       //Crea tabla GEN con el contenido de RN donde NUMEMP=0                                 
    {                                                                           
//        String retorno=null;                                                  
        try                                                                     
        {
            
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());        // Se registra el Driver de MySQL
            String[] parametros = cadenaBD.split("\\|");
            
                                                                                // Se obtiene una conexión con la base de datos. 
            Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/"+parametros[0]+"",parametros[1],parametros[2]);
            
            Statement cstmt2 = conexion.createStatement();                      // Se crea un Statement
                                                                                //Condiciones de identificación de usuarios genéricos
            String palabras = " where description like '%gene%' or full_name like '%generico%' or full_name like '%usuario temporal%' or full_name like '%ericsson%' "
                    + "or  full_name like '%computadoras%' or  full_name like '%scanner%' or  full_name like '%para entrar%' or  full_name like '%usuario para%' "
                    + "or  full_name like '%general%' or  full_name like '%gestoria region%' or  full_name like '%dessof%' "
                    + "or full_name like '%desarrollo%' or full_name like '%outsourcing%' or full_name like '%Acceso%' or full_name like '%Display%'"
                    + "or full_name like '%Guardias%' or full_name like '%kios%' or full_name like '%monitoreo%'"
                    + "or full_name like '%servidor%' or full_name like '%Wol r8%' or full_name like '%conectarse%' or full_name like '%sql%'"
                    + "or full_name like '%instalation%' or full_name like '%respaldo%' or full_name like '%capacitacion%' or full_name like '%finanzas%'"
                    + "or full_name like '%laptop%' or full_name like '%RBS%' or full_name like '%recepcion%' or full_name like '%reseteo%'"
                    + "or full_name like '%tux%' or full_name like '%simaec%' or full_name like '%stt%' or full_name like '%sistemas%' or full_name like '%tafingr%'"
                    + " or full_name like '%administra%' or full_name like '%asesor%'  or full_name like '%buscador%' or full_name like '%captura%'"
                    + " or full_name like '%cyber%' or full_name like '%eventual%' or full_name like '%jefatura%' or full_name like '%jefe%' or full_name like '%portabilidad%'"
                    + " or full_name like '%positivas%' or full_name like '%servicio%' or full_name like '%supervisor%' or full_name like '%backup%'"
                    + " or full_name like '%callcenter%' or full_name like '%calser%' or full_name like '%ccrr%' or full_name like '%cicout%' or full_name like '%cismonitor%'"
                    + " or full_name like '%cistrafico%' or full_name like '%copte%' or full_name like '%cr0%' or full_name like '%ftem%' or full_name like '%HP IRS%'"
                    + " or full_name like '%iphone%' or full_name like '%juridico%' or full_name like '%kaspersky%' or full_name like '%multi%' or full_name like '%nokia%'"
                    + " or full_name like '%pruebas%'  or full_name like '%RH Juarez%' or full_name like '%robot%' or full_name like '%samsung%' or full_name like '%scaner%'"
                    + " or full_name like '%venta%'  or full_name like '%vigilante%' or full_name like '%sony%' or full_name like '%trend%'"
                    + " or full_name like '%reagenda%' or full_name like '%copout%' or full_name like '%IVRR%' or full_name like '%comisiones%' or full_name like '%examenes%'"
                    + " or full_name like '%prueba%' or full_name like '%politica%' or full_name like '%celular%' or full_name like '%comunicacio%' or full_name like '%digital%'"
                    + " or full_name like '%enlaces%' or full_name like '%entorno%' or full_name like '%galax%' or full_name like '%ginx%' or full_name like '%digicel%'"
                    + " or full_name like '%sharepoint%' or full_name like '%ondacell%' or full_name like '%aplicaciones%' or full_name like '%proyecci%' "
                    + " or full_name like '%reportes%' or full_name like '%servitel%'  or full_name like '%renovaciones%'  or full_name like '%soporte%'"
                    + " or full_name like '%torreon%'  or full_name like '%caparh%'  or full_name like '%capasc%' or full_name like '%distribuidor%'"
                    + " or full_name like '%KAVcorpo%'  or full_name like '%blackberry%'  or full_name like '%telcel%' or full_name like '%procesos%'"
                    + " or full_name like 'LG' or full_name like '%RecHum%'  or full_name like 'romer3' or full_name like '%automatico%' or full_name like '%ccsm%'"
                    + " or full_name like '%leon1%' or full_name like '%irapuato%' or full_name like '%motorola%' or full_name like '%control%' or full_name like '%messenger%'"
                    + " or full_name like '%mtx%' or full_name like '%maquinas%' or full_name like '%virtuales%' or full_name like '%ntreg%' or full_name like '%series r6%'"
                    + " or full_name like '%wireles%' or full_name like '%sitecorp%' or full_name like '%server%' or full_name like '%arandascel%' or full_name like '%dhcp%'"
                    + " or full_name like '%telycom%' or full_name like '%queretaro%' or full_name like '%san luis poto%' or full_name like '%fresnillo%'"
                    + " or full_name like '%moroleon%' or full_name like '%personal%' or full_name like '%oracle%' or full_name like '%sistema%' or full_name like '%bajas%' "
                    + " or full_name like '%aguascalientes 1%' or full_name like '%aguascalientes i%' or full_name like '%aguascalientes v%' "
                    + " or full_name like '%aguascalientes 2%' or full_name like '%respaldo%' or full_name like '%camcc%'  or full_name like '%tecnico%'"
                    + " or full_name like '%celaya iii%' or full_name like '%celaya i%' or full_name like '%celaya ii%' or full_name like '%guanajuato%' "
                    + "or full_name like '%leon i%' or full_name like '%leon v' or full_name like '%leon vi' or full_name like '%centrales%' or full_name like '%trafico%'"
                    + " or full_name like '%lg qro%' or full_name like 'salamanca'  or full_name like '%san juan del r%' or full_name like '%san miguel de allende'"
                    + " or full_name like '%zacatecas%'  or full_name like 'comercial%'  or full_name like '%callidus%' "
                    + "or full_name like '%telefonico%' or full_name like '%comunicaciones%' or full_name like '%MulXerox%'";

                                                                                //Se ejecuta consulta para crear tabla de genéricos
            int rs2 = cstmt2.executeUpdate("create table if not exists genericos" + Integer.toString(reg) + " select * from r" + Integer.toString(reg) + palabras);
            rs2 = cstmt2.executeUpdate("delete from r" + Integer.toString(reg) + palabras); //Se ejecuta sentencia para elíminar los usuarios genéricos de la tabla de usuarios de la aplicación            
            rs2 = cstmt2.executeUpdate("insert into genericos" + Integer.toString(reg) + " select * from externos" + Integer.toString(reg) + palabras); //Busca usuarios genéricos en la tabla de usuarios externos y si los encuentra los agrega
            rs2 = cstmt2.executeUpdate("delete from externos" + Integer.toString(reg) + palabras);  //Elimina los usuarios genéricos de la tabla de usuarios externos
            
            conexion.close();                                                   // Se cierra la conexión con la base de datos.
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    
    private static String nulos(int reg)                                        //Devuelve String para llamar StoreProcedure para agregar NUMEMP
    {
        //System.out.println("Region en nulos " + reg);
        String retorno = null;
        retorno = "CALL agregaNUMEMP( 'r" + Integer.toString(reg) + "' )";      //Se define la llamada al stored procedure en la cadena
        return retorno;                                                         //Devuelve la cadena
    }
    
    
    
    private static String hexaDec(int reg)                                      //Devuelve la instrucción para la conversión del UserID al número de empleado
    {   
        
        String retorno = null;
        retorno = "UPDATE r" + Integer.toString(reg) + " SET numemp = conv(substring( User_Name from 4), 16, 10)";  //Se define la instrucción en la cadena
        
        return retorno;                                                         //Devuelve la cadena
    }
    
    
    public static void numEmp(int reg, String cadenaBD)                         //Agrega el número de empelado a las tablas de trabajo de los usuarios del aplicativo
    {
        try
        {
            
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());        // Se registra el Driver de MySQL
            String[] parametros = cadenaBD.split("\\|");
            
                                                                                // Se obtiene una conexión con la base de datos. 
            Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/"+parametros[0]+"",parametros[1],parametros[2]);
                        
            Statement s = conexion.createStatement();                           // Se crea un Statement
            
            CallableStatement cstmt = conexion.prepareCall(nulos(reg));         //Prepara la ejecución de la llamada al stored procedure para agregar el número de emmpleado
            ResultSet rs = cstmt.executeQuery();                                //Ejecuta la llamada al stored procedure
            CallableStatement cstmt2 = conexion.prepareCall(nulos(reg));
            int rs2 = cstmt2.executeUpdate(hexaDec(reg));
            
            
            conexion.close();                                                   // Se cierra la conexión con la base de datos.
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    //return retorno;
        

    }
    
    
    public static String borraDisable(int reg, String cadenaBD)                 //Realiza el cruce de información de los enteros
    {
        String retorno=null;
        try
        {
            
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());        // Se registra el Driver de MySQL
            String[] parametros = cadenaBD.split("\\|");
            
                                                                                // Se obtiene una conexión con la base de datos. 
            Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/"+parametros[0]+"",parametros[1],parametros[2]);
            
            CallableStatement cstmt2 = conexion.prepareCall(nulos(reg));        //PREPARA LA LLAMADA EL STOREPROCEDURE PARA AGREGAR NUMEMP EN TABALS R#
            int rs2 = cstmt2.executeUpdate("drop table if exists r" + Integer.toString(reg));   //Borra la tabla de trabajo de usuarios activos
            rs2 = cstmt2.executeUpdate("create table r" + Integer.toString(reg) + " select * from r" + Integer.toString(reg) + "c" + parametros[3]);    //Crea la tabla de trabajo con los usuarios activos
            rs2 = cstmt2.executeUpdate("delete from r" + Integer.toHexString(reg) + " where Account_disabled = 1 ");    //Borra los usuarios deshabilitados de la tabla de trabajo de usuarios activos
            
            retorno = MonAD.Conteos.total(reg, cadenaBD);                  //Cuenta el total de usuarios con cuenats habilitadas y lo guarda en retorno
            
            conexion.close();                                                   // Se cierra la conexión con la base de datos.
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return retorno;                                                         //Devuelve el total de usuarios habilitados
    }
    
    
   
    
    public static void cruceint(int reg, String cadenaBD)                                                  //Realiza el cruce de información de los enteros
    {
//        String retorno=null;
        try
        {
            
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());        // Se registra el Driver de MySQL
            String[] parametros = cadenaBD.split("\\|");
            
            // Se obtiene una conexión con la base de datos. 
            Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/"+parametros[0]+"",parametros[1],parametros[2]);
//            Consulta anterior            
//            String sentencia = "create table if not exists cruceint" + Integer.toString(reg) + " SELECT internos" + Integer.toString(reg) + ".r" + Integer.toString(reg) + "_id, "
//                    + "internos" + Integer.toString(reg) + ".Creation_Date, internos" + Integer.toString(reg) + ".Description, " + "internos" + Integer.toString(reg) + ".Full_Name,  "
//                    + "internos" + Integer.toString(reg) + ".Last_Logon_Timestamp, internos" + Integer.toString(reg) + ".User_Name, internos" + Integer.toString(reg) + ".numemp, " 
//                    + "basef" + parametros[4] + ".EMP AS BFNUMEMP, basef" + parametros[4] + ".NOMBRECOMPLETO AS BFNOMBRE, basef" + parametros[4] + ".PUESTO AS BFPUESTO, basef" + parametros[4] + ".GERENCIA AS BFGERENCIA, basef" + parametros[4] + ".REGION AS BFREGION, "
//                    + "bajasbf" + parametros[4] + ".EMP AS BBFEMP, bajasbf" + parametros[4] + ".NOMBRECOMPLETO AS BBFNOMBRE, bajasbf" + parametros[4] + ".PUESTO AS BBFPUESTO, bajasbf" + parametros[4] + ".GERENCIA AS BBFGERENCIA, "
//                    + "bajasbf" + parametros[4] + ".BAJA AS BBFBAJA, "
//                    + "idint" + parametros[4] + ".USUARIO AS IDUSUARIO, idint" + parametros[4] + ".NOMBRECOMPLETO AS IDNOMBRE, idint" + parametros[4] + ".REGION AS IDREGION, "
//                    + "idint" + parametros[4] + ".GERENCIA AS IDGERENCIA, "
//                    + "idint" + parametros[4] + ".PUESTO AS IDPUESTO FROM ((internos" + Integer.toString(reg) + " left join basef" + parametros[4] 
//                    + " on internos" + Integer.toString(reg) + ".numemp=basef" + parametros[4] + ".emp) left join bajasbf" + parametros[4] + " on internos" + Integer.toString(reg) + ".numemp=bajasbf" + parametros[4] + ".EMP) left join idint" + parametros[4] 
//                    + " on internos" + Integer.toString(reg) + ".numemp=idint" + parametros[4] + ".NUMEROEMPLEADO";
                                                                                //Se establece la sentencia para crear la tabla de trabajo cruce de usuarios internos que incluye la información de la aplicación y la información de la nómina
            String sentencia = "create table if not exists cruceint" + Integer.toString(reg) + " SELECT internos" + Integer.toString(reg) + ".r" + Integer.toString(reg) + "_id, "
                    + "internos" + Integer.toString(reg) + ".Creation_Date, internos" + Integer.toString(reg) + ".Description, " + "internos" + Integer.toString(reg) + ".Full_Name,  "
                    + "internos" + Integer.toString(reg) + ".Last_Logon_Timestamp, internos" + Integer.toString(reg) + ".User_Name, internos" + Integer.toString(reg) + ".numemp, " 
                    + "idint" + parametros[4] + ".USUARIO AS IDUSUARIO, idint" + parametros[4] + ".NOMBRECOMPLETO AS IDNOMBRE, idint" + parametros[4] + ".REGION AS IDREGION, "
                    + "idint" + parametros[4] + ".GERENCIA AS IDGERENCIA, idint" + parametros[4] + ".DEPARTAMENTO AS IDDEPARTAMENTO, idint" + parametros[4] + ".PUESTO AS IDPUESTO, "
                    + "idint" + parametros[4] + ".JEFEINMEDIATO AS IDJEFE, idint" + parametros[4] + ".ESTATUS AS IDESTATUS, idint" + parametros[4] + ".FECHA AS IDFECHA "
                    + "FROM internos" + Integer.toString(reg) + " left join idint" + parametros[4] 
                    + " on internos" + Integer.toString(reg) + ".numemp=idint" + parametros[4] + ".NUMEROEMPLEADO";
            
            Statement stmt2 = conexion.createStatement();                       //Crea la sentencia

            stmt2.executeUpdate(sentencia);                                     //Ejecuta la sentencia
           
           
            conexion.close();                                                    // Se cierra la conexión con la base de datos.
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    

    }
    
    
    public static void cruceext(int reg, String cadenaBD)
    {
        try
        {
            
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());        // Se registra el Driver de MySQL
            String[] parametros = cadenaBD.split("\\|");
            
                                                                                // Se obtiene una conexión con la base de datos. 
            Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/"+parametros[0]+"",parametros[1],parametros[2]);
            //Consulta para nóminas anteriores
//                    String sentencia = "create table if not exists cruceext" + Integer.toString(reg) 
//                    + " SELECT externos" + Integer.toString(reg) + ".r" + Integer.toString(reg) + "_id, "
//                    + "externos" + Integer.toString(reg) + ".Creation_Date, externos" + Integer.toString(reg) + ".Description, " 
//                    + "externos" + Integer.toString(reg) + ".Full_Name,  externos" + Integer.toString(reg) + ".Last_Logon_Timestamp, "
//                    + "externos" + Integer.toString(reg) + ".User_Name, externos" + Integer.toString(reg) + ".numemp, idext" + parametros[4] + ".NUMEROEMPLEADO AS IDNUMEMP," 
//                    + " idext" + parametros[4] + ".USUARIO AS IDUSUARIO, idext" + parametros[4] + ".NOMBRECOMPLETO AS IDNOMBRE, idext" + parametros[4] + ".REGION AS IDREGION, idext" + parametros[4] + ".GERENCIA AS IDGERENCIA, "
//                    + "idext" + parametros[4] + ".PUESTO AS IDPUESTO, bidext" + parametros[4] + ".BNUMEMP AS BNUMEMP, bidext" + parametros[4] + ".usuario as BIDUSRID, bidext" + parametros[4] + ".nombrecompleto as BIDNOMBRE "
//                    + "FROM (externos" + Integer.toString(reg) + " left join idext" + parametros[4]
//                    + " on externos" + Integer.toString(reg) + ".User_Name=idext" + parametros[4] + ".USUARIO or externos" + Integer.toString(reg) + ".Full_Name=idext" + parametros[4] + ".nombrecompleto) "
//                    + "LEFT JOIN bidext" + parametros[4] + " on externos" + Integer.toString(reg) + ".user_name = bidext" + parametros[4] + ".usuario or externos" + Integer.toString(reg) + ".Full_Name=idext" + parametros[4] + ".nombrecompleto group by Full_Name ";
                                                                                //Se establece la sentencia para crear la tabla de trabajo cruce de usuarios externos que incluye la información de la aplicación y la información de la nómina
                    String sentencia = "create table if not exists cruceext" + Integer.toString(reg) 
                    + " SELECT externos" + Integer.toString(reg) + ".r" + Integer.toString(reg) + "_id, "
                    + "externos" + Integer.toString(reg) + ".Creation_Date, externos" + Integer.toString(reg) + ".Description, " 
                    + "externos" + Integer.toString(reg) + ".Full_Name,  externos" + Integer.toString(reg) + ".Last_Logon_Timestamp, "
                    + "externos" + Integer.toString(reg) + ".User_Name, externos" + Integer.toString(reg) + ".numemp, idext" + parametros[4] + ".NUMEROEMPLEADO AS IDNUMEMP," 
                    + " idext" + parametros[4] + ".USUARIO AS IDUSUARIO, idext" + parametros[4] + ".NOMBRECOMPLETO AS IDNOMBRE, idext" + parametros[4] + ".REGION AS IDREGION, idext" + parametros[4] + ".GERENCIA AS IDGERENCIA, "
                    + "idext" + parametros[4] + ".DEPARTAMENTO AS IDDEPARTAMENTO, idext" + parametros[4] + ".PUESTO AS IDPUESTO, idext" + parametros[4] + ".JEFEINMEDIATO AS IDJEFE, "
                    + "idext" + parametros[4] + ".ESTATUS AS IDESTATUS, idext" + parametros[4] + ".FECHA AS IDFECHA "
                    + "FROM externos" + Integer.toString(reg) + " left join idext" + parametros[4]
                    + " on externos" + Integer.toString(reg) + ".User_Name=idext" + parametros[4] + ".USUARIO or externos" + Integer.toString(reg) + ".Full_Name=idext" + parametros[4] + ".nombrecompleto group by Full_Name ";
            
            
            
           
            Statement stmt2 = conexion.createStatement();                       //Se crea el objeto de sentencia
            stmt2.executeUpdate(sentencia);                                     //Se ejecuta la sentencia
           
            conexion.close();                                                   // Se cierra la conexión con la base de datos.
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
    
    
    
    
    
    
}


    