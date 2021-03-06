/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Monitoreos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author VS3XXBD
 */
public class Tablas 
{
    public static void usuariosTabla(int reg, String usuarios, String cadenaBD) //CREA LA TABLA DE USUARIOS REGIONALES
    {
        String[] parametros = cadenaBD.split("\\|");
        String mes = parametros[3];
        String tabla = "CREATE TABLE r"+ reg + "c" + mes + " (r" + reg + "_id int(11) NOT NULL AUTO_INCREMENT, Account_Disabled boolean, "
        + "Creation_Date datetime , Domain varchar(255) , Description varchar(255) , Full_Name varchar(255) , Last_Logon_Timestamp datetime NULL, "
        + "Title varchar(255) , User_Name varchar(45), PRIMARY KEY (r" + reg + "_id))";
        String valores = "INSERT INTO r" + reg + "c" + mes + " (Account_Disabled, Creation_Date, Description, Domain, Full_Name, Last_Logon_Timestamp, Title, User_Name) "
                + "values " + usuarios ;
        //System.out.println("Tabla \n" + tabla);
        //System.out.println("Valores \n" + valores);
        
        try
        {
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());
            
            
            
            // Se obtiene una conexión con la base de datos. 
            Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/"+parametros[0]+"",parametros[1],parametros[2]);
            
            Statement cstmt2 = conexion.createStatement();

            int rs2 = cstmt2.executeUpdate("DROP TABLE IF EXISTS r"+ reg + "c" + mes + "");
            //System.out.println("tabla " + tabla);
            //System.out.println("Valores " + valores);
                    ;
            rs2 = cstmt2.executeUpdate(tabla);
            
            rs2 = cstmt2.executeUpdate(valores);
            

            // Se cierra la conexión con la base de datos.
            conexion.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    
    
    
    
    
    
    public static void eliminaTablaExt(String cadenaBD)                         //ELIMINA LA TABLA DE NÓMINA EXTERNOS
    {
        String[] parametros = cadenaBD.split("\\|");
        

        
        try
        {
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());
            
            // Se obtiene una conexión con la base de datos. 
            Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/"+parametros[0]+"",parametros[1],parametros[2]);
            
            Statement cstmt2 = conexion.createStatement();

            int rs2 = cstmt2.executeUpdate("DROP TABLE IF EXISTS `idext"+ parametros[4] + "`");
            
            // Se cierra la conexión con la base de datos.
            conexion.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public static void eliminaTablaInt(String cadenaBD)                         //ELIMINA LA TABLA DE NÓMINA INTERNOS
    {
        String[] parametros = cadenaBD.split("\\|");
        

        
        try
        {
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());
            
            // Se obtiene una conexión con la base de datos. 
            Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/"+parametros[0]+"",parametros[1],parametros[2]);
            
            Statement cstmt2 = conexion.createStatement();

            int rs2 = cstmt2.executeUpdate("DROP TABLE IF EXISTS `idint"+ parametros[4] + "`");
            
            // Se cierra la conexión con la base de datos.
            conexion.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    
    
   
    
    public static void idExtTabla(String usuarios, String cadenaBD)             //INSERTA LOS REGISTROS A LA TABLA DE NÓMINA EXTERNOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String valores = "INSERT INTO idext" + parametros[4] + " (NUMEROEMPLEADO, USUARIO, NOMBRECOMPLETO, REGION, GERENCIA, DEPARTAMENTO, PUESTO, "
                + "IDPUESTO, JEFEINMEDIATO, ESTATUS, FECHA) values " + usuarios ;
        //System.out.println("Valores \n" + valores);
        
        try
        {
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());
            
            
            
            // Se obtiene una conexión con la base de datos. 
            Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/"+parametros[0]+"",parametros[1],parametros[2]);
            
            Statement cstmt2 = conexion.createStatement();
//            System.out.println(valores);
            int rs2 = cstmt2.executeUpdate(valores);
            conexion.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
  
    public static void idCreaExtTabla(String cadenaBD)                          //CREA LA TABLA DE NÓMINA EXTERNOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String tabla = "CREATE TABLE IF NOT EXISTS idext"+ parametros[4] + " (NUMEROEMPLEADO VARCHAR(25) NOT NULL, USUARIO VARCHAR(25), "
        + "NOMBRECOMPLETO VARCHAR(255) , REGION varchar(45) , GERENCIA varchar(255) , DEPARTAMENTO VARCHAR(255) , PUESTO varchar(255) , "
        + "IDPUESTO VARCHAR(45) , JEFEINMEDIATO VARCHAR(255), ESTATUS VARCHAR(45), FECHA DATE, PRIMARY KEY (NUMEROEMPLEADO))";
        
        //System.out.println("Tabla \n" + tabla);
        
        try
        {
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());
            
            
            
            // Se obtiene una conexión con la base de datos. 
            Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/"+parametros[0]+"",parametros[1],parametros[2]);
            
            Statement cstmt2 = conexion.createStatement();
            int rs2 = cstmt2.executeUpdate(tabla);
            
            // Se cierra la conexión con la base de datos.
            conexion.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public static void idCreaIntTabla(String cadenaBD)                          //CREA LA TABLA DE NOMINA INTERNOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String tabla = "CREATE TABLE  IF NOT EXISTS idint"+ parametros[4] + " (NUMEROEMPLEADO int(11) NOT NULL, USUARIO VARCHAR(25), "
        + "NOMBRECOMPLETO VARCHAR(255) , REGION varchar(45) , GERENCIA varchar(255) , DEPARTAMENTO VARCHAR(255), PUESTO varchar(255), IDPUESTO VARCHAR(45), "
        + "JEFEINMEDIATO VARCHAR(255), ESTATUS VARCHAR(45), FECHA DATE, PRIMARY KEY (NUMEROEMPLEADO))";
        
        //System.out.println("Tabla \n" + tabla);
        
        try
        {
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());
            
            
            
            // Se obtiene una conexión con la base de datos. 
            Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/"+parametros[0]+"",parametros[1],parametros[2]);
            
            Statement cstmt2 = conexion.createStatement();
            int rs2 = cstmt2.executeUpdate(tabla);
            
            // Se cierra la conexión con la base de datos.
            conexion.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    
    
   public static void idIntTabla(String usuarios, String cadenaBD)             //INSERTA LOS REGISTROS A LA TABLA DE NÓMINA INTERNOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String mes = parametros[3];
        
        String valores = "INSERT IGNORE INTO idint" + parametros[4] + " (NUMEROEMPLEADO, USUARIO, NOMBRECOMPLETO, REGION, GERENCIA, DEPARTAMENTO, PUESTO, IDPUESTO, JEFEINMEDIATO, ESTATUS, FECHA) "
                + "values " + usuarios ;
        
        
        
        try
        {
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());
            
            
            
            // Se obtiene una conexión con la base de datos. 
            Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/"+parametros[0]+"",parametros[1],parametros[2]);
            
            Statement cstmt2 = conexion.createStatement();

            
            int rs3 = cstmt2.executeUpdate(valores);
            
            // Se cierra la conexión con la base de datos.
            conexion.close();
        }
        catch (SQLException e)
        {
            if(e instanceof SQLIntegrityConstraintViolationException)
            {
                
            }
            
        }
    }
   
   public static String eliminaTablaUsrAdmin(String cadenaBD)                         //ELIMINA LA TABLA DE USUARIOS ADMINISTRADORES
    {
        String[] parametros = cadenaBD.split("\\|");
        String Statement = "DROP TABLE IF EXISTS usrAdmin"+ parametros[4];
            
        return Statement;
    }
   
   public static String eliminaTablaDemonsa2(String cadenaBD)                         //ELIMINA LA TABLA DE NÓMINA INTERNOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String Statement = "DROP TABLE IF EXISTS `Demonsa2"+ parametros[4] + "`";
        
        return Statement;

    }
   
   public static String idCreaUsrAdmin(String cadenaBD)                          //CREA LA USUARIOS ADMINISTRADORES
    {
        String[] parametros = cadenaBD.split("\\|");
        String tabla = "CREATE TABLE IF NOT EXISTS usradmin"+ parametros[4] + " (USUARIO VARCHAR(25) NOT NULL, NOMBRE VARCHAR(45), APELLIDO VARCHAR(43), "
        + "ROL VARCHAR(25) , VALOR_AUTORIZACION VARCHAR(8), PERMITIDO BOOLEAN NOT NULL DEFAULT FALSE, PRIMARY KEY (USUARIO))";
        
        //System.out.println("Tabla \n" + tabla);
        
        return tabla;
    }
   
   public static String idCreaDemonsa2(String cadenaBD)                          //CREA LA TABLA DE NÓMINA EXTERNOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String tabla = "CREATE TABLE IF NOT EXISTS demonsa2"+ parametros[4] + " (NUMEMP VARCHAR(25) NOT NULL, NOMBRE VARCHAR(45), IDPUESTO VARCHAR(25), "
        + "PUESTO VARCHAR(45) , GERENCIA VARCHAR(45), REGION VARCHAR(25), PRIMARY KEY (NUMEMP))";
        
        return tabla;
    }
    
    public static String UsrAdminTabla(String usuarios, String cadenaBD)             //INSERTA LOS REGISTROS A LA TABLA DE USUARIOS ADMINISTRADORES
    {
        String[] parametros = cadenaBD.split("\\|");
        String mes = parametros[3];
        String valores = "INSERT IGNORE INTO usradmin" + parametros[4] + " (USUARIO, NOMBRE, APELLIDO, ROL, VALOR_AUTORIZACION) "
                + "values ";
        valores = valores + Archivos.lecturaUsuariosAdmin (usuarios, cadenaBD);
        
        return valores;
    }
    
    public static void Demonsa2Tabla(String usuarios, String cadenaBD)             //INSERTA LOS REGISTROS A LA TABLA DE NÓMINA INTERNOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String mes = parametros[3];
        
        String valores = "INSERT IGNORE INTO usradmin" + parametros[4] + " (NUMEMP, NOMBRE, IDPUESTO, PUESTO, GERENCIA, REGION) "
                + "values " + usuarios ;
        
        
        
        try
        {
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());
            
            
            
            // Se obtiene una conexión con la base de datos. 
            Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/"+parametros[0]+"",parametros[1],parametros[2]);
            
            Statement cstmt2 = conexion.createStatement();

            
            int rs3 = cstmt2.executeUpdate(valores);
            
            // Se cierra la conexión con la base de datos.
            conexion.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            if(e instanceof SQLIntegrityConstraintViolationException)
            {
                
            }
            
        }
    }
    
    public static String TablaAgreg(String cadenaBD)                             //Crea la tabla con las insidencias de usuarios administradores agregados en el mes actual
    {
        String statement = null;
        String[] parametros = cadenaBD.split("\\|");
        statement = "create table if not exists agregados SELECT Usuario, Nombre, Apellido, Rol, Valor_Autorizacion FROM usradmin" + parametros[4]
                    + " WHERE usuario NOT IN (SELECT usuario FROM usradmin" + parametros[5] + ")";
        return statement;
    }
    
    public static String TablaElim(String cadenaBD)                             //Crea la tabla con las insidencias de usuarios administradores agregados en el mes actual
    {
        String statement = null;
        String[] parametros = cadenaBD.split("\\|");
        statement = "create table if not exists eliminados SELECT Usuario, Nombre, Apellido, Rol, Valor_Autorizacion FROM usradmin" + parametros[5]
                    + " WHERE usuario NOT IN (SELECT usuario FROM usradmin" + parametros[4] + ")";
        return statement;
    }
    
    public static String TablaCodLiberaAgreg(String cadenaBD)                             //Crea la tabla con las insidencias de usuarios administradores agregados en el mes actual
    {
        String statement = null;
        String[] parametros = cadenaBD.split("\\|");
        statement = "create table if not exists CodLiberaagregados SELECT Usuario, Rol, Denominacion, Valor FROM codlibera" + parametros[4]
                    + " WHERE Concatenado NOT IN (SELECT Concatenado FROM codlibera" + parametros[5] + ")";
        return statement;
    }
    
    public static String TablaAdminUsrAdminGen(String cadenaBD)                             //Crea la tabla con las insidencias de usuarios administradores agregados en el mes actual
    {
        String statement = null;
        String[] parametros = cadenaBD.split("\\|");
        statement = "INSERT IGNORE INTO AdminUsrAdminGenSAP (Usuario, nombre, apellido, rol, valor_autorizacion) "
                + "SELECT Usuario, nombre, apellido, rol, valor_autorizacion FROM usradmin" + parametros[4] +  " where usuario not in "
                + "(select numeroempleado as usuario from idint" + parametros[4] + ") and usuario not in (select NUMEROEMPLEADO as usuario2 from "
                + "idext" + parametros[4] + ")";
        return statement;
    }
    
    public static String TablaAdminUsrAdmin(String cadenaBD)                             //Crea la tabla con las insidencias de usuarios administradores agregados en el mes actual
    {
        String statement = null;
        String[] parametros = cadenaBD.split("\\|");
        statement = "INSERT IGNORE INTO AdminUsrAdminSAP SELECT * FROM usradmin" + parametros[4] + " where usuario in "
                + "(select numeroempleado as usuario from idint" + parametros[4] + ") or usuario in (select NUMEROEMPLEADO as usuario2 from "
                + "idext" + parametros[4] + ")";
        return statement;
    }
    
    public static String EliminaAdminUsrAdmin(String cadenaBD)
    {
        String statement = null;
        statement = "DELETE FROM AdminUsrAdminSAP WHERE Usuario NOT IN (SELECT Usuario FROM GenericosSAP)";
        return statement;
    }
    
    
    
    public static String TablaTransfer(String cadenaBD)                             //Crea la tabla con las insidencias de usuarios administradores agregados en el mes actual
    {
        String statement = null;
        String[] parametros = cadenaBD.split("\\|");
        statement = "CREATE TABLE IF NOT EXISTS TransferSAP SELECT Usuario, Nombre_Completo, Grupo, Valido_de, Validez_a FROM UsuariosSAP" + parametros[4]
                    + " WHERE usuario LIKE 'TR%' ";
        return statement;
    }
    
    public static String TablaExtSAP(String cadenaBD)                             //Crea la tabla con las insidencias de usuarios administradores agregados en el mes actual
    {
        String statement = null;
        String[] parametros = cadenaBD.split("\\|");
        statement = "CREATE TABLE IF NOT EXISTS ExternosSAP SELECT Usuario, Nombre_Completo, Grupo, Valido_de, Validez_a FROM UsuariosSAP" + parametros[4]
                + " WHERE usuario LIKE 'EX%'";
        return statement;
    }
    
    
    public static String TablaGenSAP()                             //Crea la tabla con las insidencias de usuarios administradores agregados en el mes actual
    {
        String statement = null;
        statement = "CREATE TABLE IF NOT EXISTS GenericosSAP (Usuario VARCHAR(25) NOT NULL, Nombre_Completo VARCHAR(255), Grupo VARCHAR(45), Valido_de DATE , "
                + "Validez_a DATE, PRIMARY KEY (USUARIO))";
        return statement;
        
    }
    
    public static String TablaAdmGenSAP()                             //Crea la tabla con las insidencias de usuarios administradores agregados en el mes actual
    {
        String statement = null;
        statement = "CREATE TABLE IF NOT EXISTS AdminGenericosSAP (Usuario VARCHAR(25) NOT NULL, Nombre_Completo VARCHAR(255), Grupo VARCHAR(45), Valido_de DATE , "
                + "Validez_a DATE, PERMITIDO BOOLEAN default 0, PRIMARY KEY (USUARIO))";
        return statement;
        
    }
   
    public static String TablaGenSAP(String cadenaBD)                             //Crea la tabla con las insidencias de usuarios administradores agregados en el mes actual
    {
        String statement = null;
        String[] parametros = cadenaBD.split("\\|");
        statement = "INSERT IGNORE INTO GenericosSAP (Usuario, Nombre_Completo, Grupo, Valido_de, Validez_a) "
                + "SELECT Usuario, Nombre_Completo, Grupo, Valido_de, Validez_a FROM UsuariosSAP" + parametros[4] 
                + " WHERE usuario LIKE '%A%' "
                + "OR usuario LIKE '%B%' "
                + "OR usuario LIKE '%C%' "
                + "OR usuario LIKE '%D%' "
                + "OR usuario LIKE '%E%' "
                + "OR usuario LIKE '%F%' "
                + "OR usuario LIKE '%G%' "
                + "OR usuario LIKE '%H%' "  
                + "OR usuario LIKE '%I%' "
                + "OR usuario LIKE '%J%' "
                + "OR usuario LIKE '%K%' "
                + "OR usuario LIKE '%L%' " 
                + "OR usuario LIKE '%M%' "  
                + "OR usuario LIKE '%N%' "
                + "OR usuario LIKE '%Ñ%' "
                + "OR usuario LIKE '%O%' "
                + "OR usuario LIKE '%P%' "
                + "OR usuario LIKE '%Q%' "
                + "OR usuario LIKE '%R%' "
                + "OR usuario LIKE '%S%' "
                + "OR usuario LIKE '%T%' "
                + "OR usuario LIKE '%U%' "
                + "OR usuario LIKE '%V%' "
                + "OR usuario LIKE '%W%' " 
                + "OR usuario LIKE '%X%' "
                + "OR usuario LIKE '%Y%' "
                + "OR usuario LIKE '%Z%' ";
        return statement;
    }
    
    public static String InsertaAdmGenSAP()                             //Crea la tabla con las insidencias de usuarios administradores agregados en el mes actual
    {
        String statement = null;        
        statement = "INSERT IGNORE INTO AdminGenericosSAP (Usuario, Nombre_Completo, Grupo, Valido_de, Validez_a) "
                + "SELECT Usuario, Nombre_Completo, Grupo, Valido_de, Validez_a FROM GenericosSAP";
        return statement;
    }
    
    public static String EliminaAdmGenSAP()                             //Crea la tabla con las insidencias de usuarios administradores agregados en el mes actual
    {
        String statement = null;
        statement = "DELETE FROM AdminGenericosSAP WHERE Usuario NOT IN (SELECT Usuario FROM GenericosSAP)";
        return statement;
    }
    
    public static String InsertaAdmCodigosSAP(String cadenaBD)                             //Crea la tabla con las insidencias de usuarios administradores agregados en el mes actual
    {
        String statement = null;        
        String[] parametros = cadenaBD.split("\\|");
        statement = "INSERT IGNORE INTO AdminCodigosSAP (Usuario, Rol, Denominacion, Valor) "
                + "SELECT Usuario, Rol, Denominacion, Valor FROM codliberaagregados";
        return statement;
    }
    
    public static String EliminaAdmCodigosSAP(String cadenaBD)                             //Crea la tabla con las insidencias de usuarios administradores agregados en el mes actual
    {
        String statement = null;
        String[] parametros = cadenaBD.split("\\|");
        statement = "DELETE FROM AdminCodigosSAP WHERE Usuario NOT IN (SELECT Usuario FROM codlibera" + parametros[4] + ")";
        return statement;
    }
    
    
    public static String TablaIntSAP(String cadenaBD)                             //Crea la tabla con las insidencias de usuarios administradores agregados en el mes actual
    {
        String statement = null;
        String[] parametros = cadenaBD.split("\\|");
        statement = "create table if not exists InternosSAP SELECT Usuario, Nombre_Completo, Grupo, Valido_de, Validez_a FROM UsuariosSAP" + parametros[4];
        return statement;
    }
    
    
    
        //eliminacion tablas
     public static String eliminaTablaUsrsSAP(String cadenaBD)                         //ELIMINA LA TABLA DE USUARIOS ADMINISTRADORES
    {
        String[] parametros = cadenaBD.split("\\|");
        String statment = "DROP TABLE IF EXISTS Usuarios2"+ parametros[4];  
        return statment;
        
    }
     
     public static String eliminaUsuariosSAP2(String cadenaBD)                         //ELIMINA LA TABLA DE USUARIOS ADMINISTRADORES
    {
        String[] parametros = cadenaBD.split("\\|");
        String statment="DROP TABLE IF EXISTS UsuariosSAP"+ parametros[4];    
        return statment;
    }
     
     
    
                //USUARIOS 2 SAP
    public static String CreaUsuariosSAP(String cadenaBD)                          //AGREGA OTRA TABLA USUARIOS PARA LA MANIPULACION DE LOS REGISTROS
    {
        String[] parametros = cadenaBD.split("\\|");
        String tabla = "CREATE TABLE IF NOT EXISTS UsuariosSAP" + parametros[4] + " SELECT Usuario, Nombre_Completo, Grupo, valido_de, "
                + "validez_a FROM Usuarios2" + parametros[4]
                + " WHERE  (validez_a IS NULL) "
                + " OR "
                + " (validez_a > '" + parametros[6] + "')";
        return tabla; 
    }
   
    public static String CreaUsuarios2(String cadenaBD)                          //AGREGA TABLA PARA LOS USUARIOS OBTENIDOS DE LA BASE DE DATOS DE SAP SAP
    {
       String[] parametros = cadenaBD.split("\\|");
        String tabla = "CREATE TABLE IF NOT EXISTS Usuarios2" + parametros[4] + " (Usuario VARCHAR(20) NOT NULL, Nombre_Completo VARCHAR(45), Grupo VARCHAR(20), "
                + "Bloq varchar(45), motivo varchar(5) default null, valido_de DATE default null, Validez_a DATE default null, PRIMARY KEY(usuario))";
        return tabla;
    }
    
    
    public static String InsertarUsuariosSAP (String usuarios, String cadenaBD)             //INSERTA LOS REGISTROS A LA TABLA DE NÓMINA INTERNOS
    {
        String[] parametros = cadenaBD.split("\\|");
       
        String valores = "INSERT IGNORE INTO Usuarios2" + parametros[4] + " (USUARIO, NOMBRE_COMPLETO, GRUPO, BLOQ, MOTIVO, VALIDO_DE, VALIDEZ_A) "
                + "values ";
        
        valores = valores + Archivos.lecturaUsuariosSAP(usuarios, cadenaBD);
        return valores;
    }
    
    
    public static String InsertarDemonsa (String usuarios, String cadenaBD)             //INSERTA LOS REGISTROS A LA TABLA DE NÓMINA INTERNOS
    {
        String[] parametros = cadenaBD.split("\\|");
       
        String valores = "INSERT IGNORE INTO demonsa2" + parametros[4] + " (NUMEMP, NOMBRE, IDPUESTO, PUESTO, GERENCIA, REGION) "
                + "values ";
        
        valores = valores + Archivos.lecturaUsuariosDemonsa2(usuarios, cadenaBD);
//        System.out.println("SQL " + valores);
        return valores;
    }
    
    public static String InsertTablaGenSAP(String cadenaBD)                             //Crea la tabla con las insidencias de usuarios administradores agregados en el mes actual
    {
        String statement = null;
        String[] parametros = cadenaBD.split("\\|");
        statement = "INSERT IGNORE INTO GenericosSAP SELECT Usuario, Nombre_Completo, Grupo, Valido_de, Validez_a FROM UsuariosSAP" + parametros[4] 
                + " WHERE usuario LIKE '%A%' "
                + "OR usuario LIKE '%B%' "
                + "OR usuario LIKE '%C%' "
                + "OR usuario LIKE '%D%' "
                + "OR usuario LIKE '%E%' "
                + "OR usuario LIKE '%F%' "
                + "OR usuario LIKE '%G%' "
                + "OR usuario LIKE '%H%' "  
                + "OR usuario LIKE '%I%' "
                + "OR usuario LIKE '%J%' "
                + "OR usuario LIKE '%K%' "
                + "OR usuario LIKE '%L%' " 
                + "OR usuario LIKE '%M%' "  
                + "OR usuario LIKE '%N%' "
                + "OR usuario LIKE '%Ñ%' "
                + "OR usuario LIKE '%O%' "
                + "OR usuario LIKE '%P%' "
                + "OR usuario LIKE '%Q%' "
                + "OR usuario LIKE '%R%' "
                + "OR usuario LIKE '%S%' "
                + "OR usuario LIKE '%T%' "
                + "OR usuario LIKE '%U%' "
                + "OR usuario LIKE '%V%' "
                + "OR usuario LIKE '%W%' " 
                + "OR usuario LIKE '%X%' "
                + "OR usuario LIKE '%Y%' "
                + "OR usuario LIKE '%Z%' ";
        return statement;
    }
    
    public static String GenExt(String cadenaBD)                          //AGREGA OTRA TABLA USUARIOS PARA LA MANIPULACION DE LOS REGISTROS
    {
        String[] parametros = cadenaBD.split("\\|");
        String tabla = "INSERT IGNORE INTO GenericosSAP (Usuario, Nombre_Completo, Grupo, Valido_de, Validez_a) "
                + "SELECT Usuario, Nombre_Completo, Grupo, Valido_de, Validez_a FROM ExternosSAP"
                + " WHERE usuario LIKE '%A%' OR usuario LIKE '%B%' "
                + "OR usuario LIKE '%C%' "
                + "OR usuario LIKE '%D%' "
                + "OR usuario LIKE '%F%' "
                + "OR usuario LIKE '%G%' "
                + "OR usuario LIKE '%H%' "  
                + "OR usuario LIKE '%I%' "
                + "OR usuario LIKE '%J%' "
                + "OR usuario LIKE '%K%' "
                + "OR usuario LIKE '%L%' " 
                + "OR usuario LIKE '%M%' "  
                + "OR usuario LIKE '%N%' "
                + "OR usuario LIKE '%Ñ%' "
                + "OR usuario LIKE '%O%' "
                + "OR usuario LIKE '%P%' "
                + "OR usuario LIKE '%Q%' "
                + "OR usuario LIKE '%R%' "
                + "OR usuario LIKE '%S%' "
                + "OR usuario LIKE '%T%' "
                + "OR usuario LIKE '%U%' "
                + "OR usuario LIKE '%V%' "
                + "OR usuario LIKE '%W%' " 
                + "OR usuario LIKE '%Y%' "
                + "OR usuario LIKE '%Z%' ";
        return tabla; 
    }
    
    
    
    
                //**************Inactividad****************
   
    public static String eliminaFechasAcceso(String cadenaBD)                         //ELIMINA LA TABLA DE USUARIOS ADMINISTRADORES
    {
        String[] parametros = cadenaBD.split("\\|");
        String statment = "DROP TABLE IF EXISTS FechasA"+ parametros[4];  
        return statment;
        
    }   
    
    public static String CreaFechasAcceso(String cadenaBD)                          //AGREGA TABLA PARA LOS USUARIOS OBTENIDOS DE LA BASE DE DATOS DE SAP SAP
    {
       String[] parametros = cadenaBD.split("\\|");
        String tabla = "CREATE TABLE IF NOT EXISTS FechasA" + parametros[4] + " (Usuario VARCHAR(20) NOT NULL, Grupo VARCHAR(20), "
                + "Creado_por varchar(20), Fecha_Creacion DATE DEFAULT NULL, Valido_de DATE DEFAULT NULL, Fin_Validez DATE DEFAULT NULL, Entrada_Sist DATE DEFAULT NULL,"
                + " Clave_acc DATE DEFAULT NULL, Bloqueo Varchar(50), PRIMARY KEY(usuario))";
        return tabla;
        
    }
    
    public static String InsertarFechasAcceso (String fechas , String cadenaBD)             //INSERTA LOS REGISTROS A LA TABLA DE NÓMINA INTERNOS
    {
        String[] parametros = cadenaBD.split("\\|");
       
        String valores = "INSERT IGNORE INTO FechasA" + parametros[4] + " (USUARIO, GRUPO, CREADO_POR, FECHA_CREACION,"
                + " VALIDO_DE, FIN_VALIDEZ, ENTRADA_SIST, CLAVE_ACC, BLOQUEO) "
                + "values ";
        
        valores = valores + Archivos.lecturaFechasAcceso(fechas, cadenaBD);
        return valores;
        
    }
    
                //**************PERFILES****************
    
     public static String eliminaPerfiles(String cadenaBD)                         //ELIMINA LA TABLA DE Usuarios perfiles
    {
        String[] parametros = cadenaBD.split("\\|");
        String statment = "DROP TABLE IF EXISTS UPerfiles"+ parametros[4];  
        return statment;        
    }   
     
     public static String eliminaCodLibera(String cadenaBD)                         //ELIMINA LA TABLA DE Usuarios perfiles
    {
        String[] parametros = cadenaBD.split("\\|");
        String statment = "DROP TABLE IF EXISTS CodLibera"+ parametros[4];  
        return statment;        
    } 
    
    public static String CreaPerfiles(String cadenaBD)                          //AGREGA TABLA PARA LOS USUARIOS Perfiles OBTENIDOS DE LA BASE DE DATOS DE SAP 
    {
       String[] parametros = cadenaBD.split("\\|");
        String tabla = "CREATE TABLE IF NOT EXISTS UPerfiles" + parametros[4] + " (Usuario VARCHAR(20) NOT NULL, Nombre VARCHAR(30), "
                + "Apellido varchar(30), Grupo VARCHAR(15), Rol VARCHAR(20), Descripcion_Rol VARCHAR(55), Fecha_inicio DATE DEFAULT NULL,"
                + " Fecha_fin DATE DEFAULT NULL)";
        return tabla;
        
    }
    
    public static String CreaCodLibera(String cadenaBD)                          //AGREGA TABLA PARA LOS USUARIOS Perfiles OBTENIDOS DE LA BASE DE DATOS DE SAP 
    {
       String[] parametros = cadenaBD.split("\\|");
        String tabla = "CREATE TABLE IF NOT EXISTS CodLibera" + parametros[4] + " (Usuario VARCHAR(20) NOT NULL, Rol VARCHAR(30), "
                + "Denominacion varchar(100), Valor VARCHAR(4), Concatenado VARCHAR(20), PRIMARY KEY(Usuario))";
        return tabla;
        
    }
    
    public static String CreaCodigosLibera(String cadenaBD)                          //AGREGA TABLA PARA LOS USUARIOS Perfiles OBTENIDOS DE LA BASE DE DATOS DE SAP 
    {
       String[] parametros = cadenaBD.split("\\|");
        String tabla = "CREATE TABLE IF NOT EXISTS CodLibera" + parametros[4] + " (Usuario VARCHAR(20) NOT NULL, Rol VARCHAR(30), "
                + "Denominacion varchar(255), Valor VARCHAR(15))";
        return tabla;
        
    }
    
    public static String InsertarCodLibera (String fechas , String cadenaBD)             //INSERTA LOS REGISTROS A LA TABLA DE Usuarios Perfiles
    {
        String[] parametros = cadenaBD.split("\\|");
       
        String valores = "INSERT IGNORE INTO CodLibera" + parametros[4] + " (Usuario, Rol, Denominacion, Valor, Concatenado)"
                + "values ";
        
        valores = valores + Archivos.lecturaCodLibera(fechas, cadenaBD);
        return valores;
        
    }
    
    public static String InsertarPerfiles (String fechas , String cadenaBD)             //INSERTA LOS REGISTROS A LA TABLA DE Usuarios Perfiles
    {
        String[] parametros = cadenaBD.split("\\|");
       
        String valores = "INSERT IGNORE INTO Uperfiles" + parametros[4] + " (Usuario, Nombre, Apellido, Grupo,"
                + " Rol, Descripcion_Rol, Fecha_inicio, Fecha_fin)"
                + "values ";
        
        valores = valores + Archivos.lecturaUsuariosPerfil(fechas, cadenaBD);
        return valores;
        
    }
                //*****************************************

    
    public static String BorraInternosTrabajo()                                 //BORRA LA TABLA DE TRABAJO INTERNOS
    {
        String statement = "DROP TABLE IF EXISTS INTERNOS";
        return statement;
    }
    
    
    
    public static String BorraExternosTrabajo()                                 //BORRA LA TABLA DE TRABAJO EXTERNOS
    {
        String statement = "DROP TABLE IF EXISTS EXTERNOS";
        return statement;
    }
    
    
    public static String BorraBaajasInt()                                       //BORRA LA TABLA DE TRABAJO DE INCIDENCIAS DE BAJAS INTERNOS
    {
        String statement = "DROP TABLE IF EXISTS BAAJASINT";
        return statement;
    }
    
    public static String BorraBaajasExt()                                       //BORRA LA TABLA DE TRABAJO DE INCIDENCIAS DE BAJAS EXTERNOS
    {
        String statement = "DROP TABLE IF EXISTS BAAJASEXT";
        return statement;
    }
    
                            //****SAP*******
    public static String BorraBajasIntSAP()                                       //BORRA LA TABLA DE TRABAJO DE INCIDENCIAS DE BAJAS INTERNOS
    {
        String statement = "DROP TABLE IF EXISTS BAJASINTSAP";
        return statement;
    }
    
    public static String BorraBajasExtSAP()                                       //BORRA LA TABLA DE TRABAJO DE INCIDENCIAS DE BAJAS EXTERNOS
    {
        String statement = "DROP TABLE IF EXISTS BAJASEXTSAP";
        return statement;
    }
    
    public static String BorraCruceInternosSAP(String cadenaBD)             //BORRA TABLA DE TRABAJO DE CRUCES DE USUARIOS INTERNOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "DROP TABLE IF EXISTS CRUCEINTSAP" + parametros[4];
        return statement;
    }
    
    public static String BorraCruceExternosSAP(String cadenaBD)             //BORRA TABLA DE TRABAJO DE CRUCES DE USUARIOS EXTERNOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "DROP TABLE IF EXISTS CRUCEEXTSAP" + parametros[4];
        return statement;
    }    
    
    public static String BorraTablaDemIE(String cadenaBD)             //BORRA TABLA DE TRABAJO DE CRUCES DE USUARIOS EXTERNOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "DROP TABLE IF EXISTS demonsaIE" + parametros[4];
        return statement;
    }
    
    public static String BorraTablaTransfer()             //BORRA TABLA DE TRABAJO DE CRUCES DE USUARIOS EXTERNOS
    {
        String statement = "DROP TABLE IF EXISTS TransferSAP";
        return statement;
    }
    
    public static String BorraInacIntSAP()             //BORRA TABLA DE TRABAJO DE CRUCES DE USUARIOS EXTERNOS
    {
        String statement = "DROP TABLE IF EXISTS inactividadintsap";
        return statement;
    }
    
    public static String BorraInacExtSAP()             //BORRA TABLA DE TRABAJO DE CRUCES DE USUARIOS EXTERNOS
    {
        String statement = "DROP TABLE IF EXISTS inactividadExtsap";
        return statement;
    }
    
    public static String BorraExcepcionesSAP()             //BORRA TABLA DE TRABAJO DE CRUCES DE USUARIOS EXTERNOS
    {
        String statement = "DROP TABLE IF EXISTS ExcepcionesSAP";
        return statement;
    }
    
    public static String BorraSinExpiracionSAP()             //BORRA TABLA DE TRABAJO DE CRUCES DE USUARIOS EXTERNOS
    {
        String statement = "DROP TABLE IF EXISTS SinExpiracionSAP";
        return statement;
    }
    
    public static String BorraExpiracion180SAP()             //BORRA TABLA DE TRABAJO DE CRUCES DE USUARIOS EXTERNOS
    {
        String statement = "DROP TABLE IF EXISTS Expiracion180SAP";
        return statement;
    }
    
    public static String BorraDupXNombreIntSAP()             //BORRA TABLA DE TRABAJO DE CRUCES DE USUARIOS EXTERNOS
    {
        String statement = "DROP TABLE IF EXISTS dupxnombreintsap";
        return statement;
    }
    
    public static String BorraDupXNombreExtSAP()             //BORRA TABLA DE TRABAJO DE CRUCES DE USUARIOS EXTERNOS
    {
        String statement = "DROP TABLE IF EXISTS dupxnombreextsap";
        return statement;
    }
    
    public static String BorraPerfilesIntSAP()             //BORRA TABLA DE TRABAJO DE CRUCES DE USUARIOS EXTERNOS
    {
        String statement = "DROP TABLE IF EXISTS PerfilesIntSAP";
        return statement;
    }
    
    public static String BorraPerfilesNoAutorizadosIntSAP()             //BORRA TABLA DE TRABAJO DE CRUCES DE USUARIOS EXTERNOS
    {
        String statement = "DROP TABLE IF EXISTS PerfilesNoAutorizadosIntSAP";
        return statement;
    }
    
    public static String BorraCodLiberaAgregados()             //BORRA TABLA DE TRABAJO DE CRUCES DE USUARIOS EXTERNOS
    {
        String statement = "DROP TABLE IF EXISTS CodLiberaAgregados";
        return statement;
    }
                        //********************
    
    
    
    public static String BorraCruceInternosTrabajo(String cadenaBD)             //BORRA TABLA DE TRABAJO DE CRUCES DE USUARIOS INTERNOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "DROP TABLE IF EXISTS CRUCEINT" + parametros[4];
        return statement;
    }
    
    public static String BorraCruceExternosTrabajo(String cadenaBD)             //BORRA TABLA DE TRABAJO DE CRUCES DE USUARIOS EXTERNOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "DROP TABLE IF EXISTS CRUCEEXT" + parametros[4];
        return statement;
    }
    
    public static String BorraBajasLInt(String cadenaBD)                        //BORRA LA TABLA DE TRABAJO DE INCIDENCIAS DE USUARIOS INTERNOS REPORTADOS COMO BAJA
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "DROP TABLE IF EXISTS BAJALINT" + parametros[4];
        return statement;
    }
    
    public static String BorraBajasLExt(String cadenaBD)                        //BORRA LA TABLA DE TRABAJO DE INCIDENCIAS DE USUARIOS EXTERNOS REPORTADOS COMO BAJA
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "DROP TABLE IF EXISTS BAJALEXT" + parametros[4];
        return statement;
    }
    
    public static String BorraDupXNombreInt(String cadenaBD)                    //BORRA LA TABLA DE TRABAJO DE USAURIOS INTERNOS DUPLICADOS POR NOMBRE
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "DROP TABLE IF EXISTS DUPXNOMBREINT";
        return statement;
    }
    
    public static String BorraDupXNombreExt(String cadenaBD)                    //BORRA LA TABLA DE TRABAJO DE USAURIOS EXTERNOS DUPLICADOS POR NOMBRE
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "DROP TABLE IF EXISTS DUPXNOMBREEXT";
        return statement;
    }
    
    public static String BorraInacInt(String cadenaBD)                          //BORRA LA TABLA DE TRABAJO DE USUARIOS INTERNOS INACTIVOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "DROP TABLE IF EXISTS INACTIVIDADINT";
        return statement;
    }
    
    public static String BorraInacExt(String cadenaBD)                          //BORRA LA TABLA DE TRABAJO DE USUARIOS EXTERNOS INACTIVOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "DROP TABLE IF EXISTS INACTIVIDADEXT";
        return statement;
    }
    
    public static String BorraNoNominaInt(String cadenaBD)                      //BORRA LA TABLA DE TRABAJO DE LOS USAURIOS INTERNOS QUE NO SE ENCUENTRAN REGISTRADOS EN NOMINA
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "DROP TABLE IF EXISTS NONOMINAINT";
        return statement;
    }
    
    public static String BorraNoNominaExt(String cadenaBD)                      //BORRA LA TABLA DE TRABAJO DE LOS USAURIOS EXTERNOS QUE NO SE ENCUENTRAN REGISTRADOS EN NOMINA
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "DROP TABLE IF EXISTS NONOMINAEXT";
        return statement;
    }
    
    public static String BorraUsrIncInt(String cadenaBD)                        //BORRA LA TABLA DE TRABAJO DE LOS UUARIOS INTERNOS CON USER ID INCORRECTO
    {   
        String[] parametros = cadenaBD.split("\\|");
        String statement = "DROP TABLE IF EXISTS USRINCINT";
        return statement;
    }
    
    public static String BorraUsrIncExt(String cadenaBD)                        //BORRA LA TABLA DE TRABAJO DE LOS UsUARIOS EXTERNOS CON USER ID INCORRECTO
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "DROP TABLE IF EXISTS USRINCEXT";
        return statement;
    }
    
    public static String BorraPerfilesInt()                                     //BORRA LA TABLA DE TRABAJO DE LOS PERFILES DE UUARIOS INTERNOS
    {
        
        String statement = "DROP TABLE IF EXISTS PERFILESINT";
        return statement;
    }
    
    public static String BorraPerfilesExt()                                     //BORRA LA TABLA DE TRABAJO DE LOS PERFILES DE UUARIOS EXTERNOS
    {
        
        String statement = "DROP TABLE IF EXISTS PERFILESEXT";
        return statement;
    }
    
    public static String BorraCoincidenciasInt()                                //BORRA LA TABLA DE TRABAJO COINCIDENCIAS INTERNOS
    {
        
        String statement = "DROP TABLE IF EXISTS COINCIDENCIASINT";
        return statement;
    }
    
    public static String BorraCoincidenciasExt()                                //BORRA LA TABLA DE TRABAJO COINCIDENCIAS EXTERNOS
    {
        
        String statement = "DROP TABLE IF EXISTS COINCIDENCIASEXT";
        return statement;
    }    
    
    
    public static String BorraMatrizPerfiles(String cadenaBD)                   //BORRA LA TABLA DE MATRIZ DE PERFILES
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "DROP TABLE IF EXISTS MATRIZPERFILES" + parametros[4];
        return statement;
    }
    
    public static String BorraPerfilesSinUsoInt(String cadenaBD)                //BORRA LA TABLA DE TRABAJO PERFILES SIN USO DE USUARIOS INTERNOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "DROP TABLE IF EXISTS PERFILESSINUSOINT" + parametros[4];
        return statement;
    }
    
    public static String BorraPerfilesSinUsoExt(String cadenaBD)                //BORRA LA TABLA DE TRABAJO PERFILES SIN USO DE USUARIOS EXTERNOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "DROP TABLE IF EXISTS PERFILESSINUSOEXT" + parametros[4];
        return statement;
    }
    
    public static String BorraPerfilesSinUso(String cadenaBD)                   //BORRA LA TABLA DE PERFILES SIN USO
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "DROP TABLE IF EXISTS PERFILESSINUSO" + parametros[4];
        return statement;
    }
    
    public static String BorraPerfilNoAutorizadosInt()                          //BORRA LA TABLA DE TRABAJO DE USUARIOS INTERNOS CON PERFIL NO AUTORIZADO
    {
        String statement = "DROP TABLE IF EXISTS UsuariosNoAutorizadosInt";
        return statement;
    }
    
    public static String BorraPerfilNoAutorizadosExt()                          //BORRA LA TABLA DE TRABAJO DE USUARIOS EXTERNOS CON PERFIL NO AUTORIZADO
    {
        String statement = "DROP TABLE IF EXISTS UsuariosNoAutorizadosExt";
        return statement;
    }
        
    public static String CreaInternosTrabajo(String cadenaBD)                   //CREA LA TABLA DE TRABAJO DE USUARIOS INTERNOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "CREATE TABLE IF NOT EXISTS INTERNOS SELECT * FROM USUARIOS" + parametros[4] + " WHERE NUM_EMP NOT LIKE '%EX%'";
        return statement;
    }
    
    public static String CreaExternosTrabajo(String cadenaBD)                   //CREA LA TABLA DE TRABAJO DE USUARIOS EXTERNOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "CREATE TABLE IF NOT EXISTS EXTERNOS SELECT * FROM USUARIOS" + parametros[4] + " WHERE NUM_EMP LIKE '%EX%'";
        return statement;
    }
    
    public static String BorraVacia(String tabla)                               //BORRA LA TABLA DEL NOMBRE QUE RECIBE, SE UTILIZA PARA BORRAR LAS TABLAS VACÍAS
    {
        String statement = "DROP TABLE IF EXISTS " + tabla;
        return statement;
    }
    
    public static String NoNominaCreaInt(String cadenaBD)                       //AGREGA UNA TABLA PARA LAS INCIDENCIAS DE USUARIOS INTERNOS QUE NO SE ENCUENTRAN EN NÓMINA
    {
        String[] parametros = cadenaBD.split("\\|");
        String consulta = "create table if not exists nonominaint SELECT * FROM cruceint" + parametros[4] + " where IDNUMEMP is null";
                
        return consulta;
    }
    
    public static String NoNominaCreaExt(String cadenaBD)                       //AGREGA UNA TABLA PARA LAS INCIDENCIAS DE USUARIOS EXTERNOS QUE NO SE ENCUENTRAN EN NÓMINA
    {
        String[] parametros = cadenaBD.split("\\|");
        String consulta = "create table if not exists nonominaext SELECT * FROM cruceext" + parametros[4] + " where idusuario is null";
                
        return consulta;
    }
    
    
                            //***************SAP************
    public static String NoNominaCreaIntSAP(String cadenaBD)                    //AGREGA UNA TABLA PARA LAS INCIDENCIAS DE USUARIOS INTERNOS QUE NO SE ENCUENTRAN EN NÓMINA
    {
        String[] parametros = cadenaBD.split("\\|");
        String consulta = "create table if not exists nonominaintSAP SELECT * FROM cruceintSAP" + parametros[4] + " where NUMEROEMPLEADO is null  and nombre_completo is not null";
                
        return consulta;
    }
    
    public static String NoNominaCreaExtSAP(String cadenaBD)                    //AGREGA UNA TABLA PARA LAS INCIDENCIAS DE USUARIOS EXTERNOS QUE NO SE ENCUENTRAN EN NÓMINA
    {
        String[] parametros = cadenaBD.split("\\|");
        String consulta = "create table if not exists nonominaextSAP SELECT * FROM cruceextSAP" + parametros[4] + " where NUMEROEMPLEADO is null";
                
        return consulta;
    }
                            //*****************************
    
    
    public static String CreaBajasExt(String cadenaBD)                          //AGREGA UNA TABLA PARA LAS INCIDENCIAS DE USUARIOS EXTERNOS QUE ESTÁN DADOS DE BAJA
    {
        String[] parametros = cadenaBD.split("\\|");
        String consulta = "create table if not exists baajasext SELECT * FROM cruceext" + parametros[4] + " WHERE IDESTATUS = 'ELIMINADO'";
                
        return consulta;
    }
    
    public static String CreaBajasInt(String cadenaBD)                          //AGREGA UNA TABLA PARA LAS INCIDENCIAS DE USUARIOS INTERNOS QUE ESTÁN DADOS DE BAJA
    {
        String[] parametros = cadenaBD.split("\\|");
        String consulta = "create table if not exists baajasint SELECT * FROM cruceint" + parametros[4] + " WHERE IDESTATUS = 'ELIMINADO'";
                
        return consulta;
    }
    
    
                    //******SAP******
    
    public static String CreaBajasExtSAP(String cadenaBD)                          //AGREGA UNA TABLA PARA LAS INCIDENCIAS DE USUARIOS EXTERNOS QUE ESTÁN DADOS DE BAJA
    {
        String[] parametros = cadenaBD.split("\\|");
        String consulta = "create table if not exists bajasextSAP SELECT usuario, nombre_completo, rol, Descripcion_Rol,"
                + " fecha_creacion, Valido_de, Validez_a, Clave_acc, NUMEROEMPLEADO, IDUSUARIO, NOMBRECOMPLETO, PUESTO, GERENCIA,"
                + " ESTATUS, FECHA "
                + " FROM cruceextSAP" + parametros[4] + " WHERE ESTATUS = 'ELIMINADO'";
                
        return consulta;
    }
    
    public static String CreaBajasIntSAP(String cadenaBD)                          //AGREGA UNA TABLA PARA LAS INCIDENCIAS DE USUARIOS INTERNOS QUE ESTÁN DADOS DE BAJA
    {
        String[] parametros = cadenaBD.split("\\|");
        String consulta = "create table if not exists bajasintSAP SELECT usuario, nombre_completo, rol, Descripcion_Rol,"
                + " fecha_creacion, Valido_de, Validez_a, Clave_acc, NUMEROEMPLEADO, IDUSUARIO, NOMBRECOMPLETO, PUESTO, GERENCIA,"
                + " ESTATUS, FECHA "
                + " FROM cruceintSAP" + parametros[4] + " WHERE ESTATUS = 'ELIMINADO'";
                
        return consulta;
    }
    
    public static String CreaInactividadIntSAP(String cadenaBD)                    //AGREGA UNA TABLA PARA LAS INCIDENCIAS DE USUARIOS INTERNOS CON INACTIVIDAD
    {
        String[] parametros = cadenaBD.split("\\|");
        String consulta = "create table if not exists inactividadintSAP SELECT usuario, nombre_completo, rol, Descripcion_Rol,"
                + " fecha_creacion, Entrada_sist, NUMEROEMPLEADO, IDUSUARIO, NOMBRECOMPLETO, PUESTO, GERENCIA,"
                + " ESTATUS"
                + " FROM cruceintsap" + parametros[4]  
                +" WHERE DATEDIFF('" + parametros[6] + "', Entrada_sist) >= 60 OR (DATEDIFF('" + parametros[6] + "', Fecha_Creacion) >= 60 AND Entrada_Sist IS NULL)";
        return consulta;
    }
    
    public static String CreaSinExpiracionSAP(String cadenaBD)                    //AGREGA UNA TABLA PARA LAS INCIDENCIAS DE USUARIOS INTERNOS CON INACTIVIDAD
    {
        String[] parametros = cadenaBD.split("\\|");
        String consulta = "create table if not exists SinExpiracionSAP SELECT usuario, nombre_completo, rol, Descripcion_Rol,"
                + " fecha_creacion, Entrada_sist, NUMEROEMPLEADO, IDUSUARIO, NOMBRECOMPLETO, PUESTO, GERENCIA,"
                + " ESTATUS"
                + " FROM cruceextsap" + parametros[4]  
                +" WHERE fin_validez IS NULL";
        return consulta;
    }
    
    public static String CreaExpiracion180SAP(String cadenaBD)                    //AGREGA UNA TABLA PARA LAS INCIDENCIAS DE USUARIOS INTERNOS CON INACTIVIDAD
    {
        String[] parametros = cadenaBD.split("\\|");
        String consulta = "create table if not exists Expiracion180SAP SELECT usuario, nombre_completo, rol, Descripcion_Rol,"
                + " fecha_creacion, Entrada_sist, NUMEROEMPLEADO, IDUSUARIO, NOMBRECOMPLETO, PUESTO, GERENCIA, VALIDO_DE, VALIDEZ_A, DATEDIFF(VALIDEZ_A, VALIDO_DE) AS PERIODO, "
                + " ESTATUS"
                + " FROM cruceextsap" + parametros[4]  
                + " WHERE datediff(validez_a, valido_de) > 180 OR datediff(validez_a, valido_de) < 180";
        return consulta;
        
    }
    
    public static String CreaInactividadExtSAP(String cadenaBD)                    //AGREGA UNA TABLA PARA LAS INCIDENCIAS DE USUARIOS EXTERNOS CON INACTIVIDAD
    {
        String[] parametros = cadenaBD.split("\\|");
        String consulta = "create table if not exists inactividadextSAP SELECT usuario, nombre_completo, rol, Descripcion_Rol,"
                + " fecha_creacion, Entrada_sist, NUMEROEMPLEADO, IDUSUARIO, NOMBRECOMPLETO, PUESTO, GERENCIA,"
                + " ESTATUS"
                + " FROM cruceextsap" + parametros[4]  
                +" WHERE DATEDIFF('" + parametros[6] + "', Entrada_sist) >= 60 OR (DATEDIFF('" + parametros[6] + "', Fecha_Creacion) >= 60 AND Entrada_Sist IS NULL)";
        return consulta;
    }
    
                        //**************************
    
    
    
    public static String CreaInactividadExt(String cadenaBD)                    //AGREGA UNA TABLA PARA LAS INCIDENCIAS DE USUARIOS EXTERNOS CON INACTIVIDAD
    {
        String[] parametros = cadenaBD.split("\\|");
        String consulta = "create table if not exists inactividadext SELECT * FROM cruceext" + parametros[4] + " WHERE DATEDIFF('" + parametros[8] + "', FECHA_ACCESO) >= 61";
        return consulta;
    }
    
    public static String CreaInactividadInt(String cadenaBD)                    //AGREGA UNA TABLA PARA LAS INCIDENCIAS DE USUARIOS INTERNOS CON INACTIVIDAD
    {
        String[] parametros = cadenaBD.split("\\|");
        String consulta = "create table if not exists inactividadint SELECT * FROM cruceint" + parametros[4] + " WHERE DATEDIFF('" + parametros[8] + "', FECHA_ACCESO) >= 61";
        return consulta;
    }
    
    public static String CreaUsuarios(String cadenaBD)                          //AGREGA TABLA PARA LOS USUARIOS OBTENIDOS DE LA BASE DE DATOS DEL APLICATIVO PAC PAC PAC+++
    {
       String[] parametros = cadenaBD.split("\\|");
        String statement = "CREATE TABLE IF NOT EXISTS Usuarios" + parametros[4] + " (ID int NOT NULL AUTO_INCREMENT, NUM_EMP VARCHAR(45), USER_NAME VARCHAR(45), "    
                + "NOMBRE VARCHAR(255), REGION INT,"                                                                                                                        
                + "IP VARCHAR(45), CORREO VARCHAR(255), JEFE VARCHAR(45), PERFIL VARCHAR(45), NOMBRE_PERFIL VARCHAR(255), FECHA_ACCESO DATETIME NULL, PRIMARY KEY(ID))";        
        return statement;        
    }           
    
    
    
    public static String CreaPerfilesInt(String cadenaBD)                       //AGREGA UNA TABLA DE USUARIOS INTERNOS CON TODOS LOS PERFILES POSIBLES SEGÚN SU PUESTO
    {
        String[] parametros = cadenaBD.split("\\|");
        String consulta = "create table if not exists PerfilesInt SELECT CRUCEINT" + parametros[4] + ".NUM_EMP, CRUCEINT" + parametros[4] + ".USER_NAME, CRUCEINT" + parametros[4] + ".NOMBRE, "
                + "CRUCEINT" + parametros[4] + ".REGION, CRUCEINT" + parametros[4] + ".IP, CRUCEINT" + parametros[4] + ".CORREO, CRUCEINT" + parametros[4] + ".JEFE, "
                + "CRUCEINT" + parametros[4] + ".PERFIL, CRUCEINT" + parametros[4] + ".NOMBRE_PERFIL, CRUCEINT" + parametros[4] + ".FECHA_ACCESO, CRUCEINT" + parametros[4] + ".IDUSUARIO, "
                + "CRUCEINT" + parametros[4] + ".IDNUMEMP, "
                + "CRUCEINT" + parametros[4] + ".IDNOMBRE, CRUCEINT" + parametros[4] + ".IDPUESTO, CRUCEINT" + parametros[4] + ".IDGERENCIA, CRUCEINT" + parametros[4] + ".IDDEPARTAMENTO, "
                + "CRUCEINT" + parametros[4] + ".IDIDPUESTO, CRUCEINT" + parametros[4] + ".IDESTATUS, CRUCEINT" + parametros[4] + ".IDFECHA, "
                + "MATRIZPERFILES" + parametros[4] + ".CLAVE_PUESTO, MATRIZPERFILES" + parametros[4] + ".PUESTO, MATRIZPERFILES" + parametros[4] + ".PERFIL AS PERFIL_MATRIZ, MATRIZPERFILES" + parametros[4] + ".PUESTO_FUNCIONAL AS PUESTO_FUNCIONAL "
                + "FROM CRUCEINT" + parametros[4] + " RIGHT JOIN MATRIZPERFILES" + parametros[4] + " ON CRUCEINT" + parametros[4] + ".IDIDPUESTO = MATRIZPERFILES" + parametros[4] + ".CLAVE_PUESTO "
                + "UNION "
                + "SELECT CRUCEINT" + parametros[4] + ".NUM_EMP, CRUCEINT" + parametros[4] + ".USER_NAME, CRUCEINT" + parametros[4] + ".NOMBRE, "
                + "CRUCEINT" + parametros[4] + ".REGION, CRUCEINT" + parametros[4] + ".IP, CRUCEINT" + parametros[4] + ".CORREO, CRUCEINT" + parametros[4] + ".JEFE, "
                + "CRUCEINT" + parametros[4] + ".PERFIL, CRUCEINT" + parametros[4] + ".NOMBRE_PERFIL, CRUCEINT" + parametros[4] + ".FECHA_ACCESO, CRUCEINT" + parametros[4] + ".IDUSUARIO, "
                + "CRUCEINT" + parametros[4] + ".IDNUMEMP, "
                + "CRUCEINT" + parametros[4] + ".IDNOMBRE, CRUCEINT" + parametros[4] + ".IDPUESTO, CRUCEINT" + parametros[4] + ".IDGERENCIA, CRUCEINT" + parametros[4] + ".IDDEPARTAMENTO, "
                + "CRUCEINT" + parametros[4] + ".IDIDPUESTO, CRUCEINT" + parametros[4] + ".IDESTATUS, CRUCEINT" + parametros[4] + ".IDFECHA, "
                + "MATRIZPERFILES" + parametros[4] + ".CLAVE_PUESTO, MATRIZPERFILES" + parametros[4] + ".PUESTO, MATRIZPERFILES" + parametros[4] + ".PERFIL AS PERFIL_MATRIZ, MATRIZPERFILES" + parametros[4] + ".PUESTO_FUNCIONAL AS PUESTO_FUNCIONAL "
                + "FROM CRUCEINT" + parametros[4] + " LEFT JOIN MATRIZPERFILES" + parametros[4] + " ON CRUCEINT" + parametros[4] + ".IDIDPUESTO = MATRIZPERFILES" + parametros[4] + ".CLAVE_PUESTO";
        return consulta;
    }
    
    public static String CreaPerfilesExt(String cadenaBD)                       //AGREGA UNA TABLA DE USUARIOS EXTERNOS CON TODOS LOS PERFILES POSIBLES SEGÚN SU PUESTO
    {
        String[] parametros = cadenaBD.split("\\|");
        String consulta = "create table if not exists PerfilesExt SELECT CRUCEEXT" + parametros[4] + ".NUM_EMP, CRUCEEXT" + parametros[4] + ".USER_NAME, CRUCEEXT" + parametros[4] + ".NOMBRE, "
                + "CRUCEEXT" + parametros[4] + ".REGION, CRUCEEXT" + parametros[4] + ".IP, CRUCEEXT" + parametros[4] + ".CORREO, CRUCEEXT" + parametros[4] + ".JEFE, "
                + "CRUCEEXT" + parametros[4] + ".PERFIL, CRUCEEXT" + parametros[4] + ".NOMBRE_PERFIL, CRUCEEXT" + parametros[4] + ".FECHA_ACCESO, CRUCEEXT" + parametros[4] + ".IDUSUARIO, "
                + "CRUCEEXT" + parametros[4] + ".IDNUMEMP, "
                + "CRUCEEXT" + parametros[4] + ".IDNOMBRE, CRUCEEXT" + parametros[4] + ".IDPUESTO, CRUCEEXT" + parametros[4] + ".IDGERENCIA, CRUCEEXT" + parametros[4] + ".IDDEPARTAMENTO, "
                + "CRUCEEXT" + parametros[4] + ".IDIDPUESTO, CRUCEEXT" + parametros[4] + ".IDESTATUS, CRUCEEXT" + parametros[4] + ".IDFECHA, "
                + "MATRIZPERFILES" + parametros[4] + ".CLAVE_PUESTO, MATRIZPERFILES" + parametros[4] + ".PUESTO, MATRIZPERFILES" + parametros[4] + ".PERFIL AS PERFIL_MATRIZ , MATRIZPERFILES" + parametros[4] + ".PUESTO_FUNCIONAL AS PUESTO_FUNCIONAL "
                + "FROM CRUCEEXT" + parametros[4] + " RIGHT JOIN MATRIZPERFILES" + parametros[4] + " ON CRUCEEXT" + parametros[4] + ".IDIDPUESTO = MATRIZPERFILES" + parametros[4] + ".CLAVE_PUESTO "
                + "UNION "
                + "SELECT CRUCEEXT" + parametros[4] + ".NUM_EMP, CRUCEEXT" + parametros[4] + ".USER_NAME, CRUCEEXT" + parametros[4] + ".NOMBRE, "
                + "CRUCEEXT" + parametros[4] + ".REGION, CRUCEEXT" + parametros[4] + ".IP, CRUCEEXT" + parametros[4] + ".CORREO, CRUCEEXT" + parametros[4] + ".JEFE, "
                + "CRUCEEXT" + parametros[4] + ".PERFIL, CRUCEEXT" + parametros[4] + ".NOMBRE_PERFIL, CRUCEEXT" + parametros[4] + ".FECHA_ACCESO, CRUCEEXT" + parametros[4] + ".IDUSUARIO, "
                + "CRUCEEXT" + parametros[4] + ".IDNUMEMP, "
                + "CRUCEEXT" + parametros[4] + ".IDNOMBRE, CRUCEEXT" + parametros[4] + ".IDPUESTO, CRUCEEXT" + parametros[4] + ".IDGERENCIA, CRUCEEXT" + parametros[4] + ".IDDEPARTAMENTO, "
                + "CRUCEEXT" + parametros[4] + ".IDIDPUESTO, CRUCEEXT" + parametros[4] + ".IDESTATUS, CRUCEEXT" + parametros[4] + ".IDFECHA, "
                + "MATRIZPERFILES" + parametros[4] + ".CLAVE_PUESTO, MATRIZPERFILES" + parametros[4] + ".PUESTO, MATRIZPERFILES" + parametros[4] + ".PERFIL AS PERFIL_MATRIZ , MATRIZPERFILES" + parametros[4] + ".PUESTO_FUNCIONAL AS PUESTO_FUNCIONAL "
                + "FROM CRUCEEXT" + parametros[4] + " LEFT JOIN MATRIZPERFILES" + parametros[4] + " ON CRUCEEXT" + parametros[4] + ".IDIDPUESTO = MATRIZPERFILES" + parametros[4] + ".CLAVE_PUESTO ";
        return consulta;
    }
    
    public static String CreaCoincidenciasInt()                                 //AGREGA UNA TABLA CON LOS USUARIOS INTERNOS QUE TIENEN UN PERFIL CORRECTO
    {
        
        String consulta = "create table if not exists CoincidenciasInt SELECT * FROM PERFILESINT WHERE PERFIL=PERFIL_MATRIZ";
        return consulta;
    }
    
    public static String CreaCoincidenciasExt()                                 //AGREGA UNA TABLA CON LOS USUARIOS EXTERNOS QUE TIENEN UN PERFIL CORRECTO
    {
        
        String consulta = "create table if not exists CoincidenciasExt SELECT * FROM PERFILESEXT WHERE PERFIL=PERFIL_MATRIZ";
        return consulta;
    }
    
    public static String CreaPerfilesSinUsoInt(String cadenaBD)                 //AGREGA UNA TABLA CON LOS PERFILES QUE NO FUERON USADOS CON LOS USUARIOS INTERNOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String consulta = "create table if not exists PerfilesSinUsoInt" + parametros[4] + " SELECT * FROM PERFILESINT WHERE NUM_EMP IS NULL";
        return consulta;
    }
    
    public static String CreaPerfilesSinUsoExt(String cadenaBD)                 //AGREGA UNA TABLA CON LOS PERFILES QUE NO FUERON USADOS CON LOS USUARIOS EXTERNOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String consulta = "create table if not exists PerfilesSinUsoExt" + parametros[4] + " SELECT * FROM PERFILESEXT WHERE NUM_EMP IS NULL";
        return consulta;
    }
    
    public static String CreaUsrNoAutorizadosInt()                              //AGREGA UNA TABLA CON LOS USUARIOS INTERNOS QUE NO DEBEN TENER ACCESO A LA APLICACIÓN
    {
        String consulta = "create table if not exists UsuariosNoAutorizadosInt SELECT * FROM PERFILESINT WHERE CLAVE_PUESTO IS NULL";
        return consulta;
    }
    
    public static String CreaUsrNoAutorizadosExt()                              //AGREGA UNA TABLA CON LOS USUARIOS EXTERNOS QUE NO DEBEN TENER ACCESO A LA APLICACIÓN
    {
        String consulta = "create table if not exists UsuariosNoAutorizadosExt SELECT * FROM PERFILESEXT WHERE CLAVE_PUESTO IS NULL";
        return consulta;
    }

    public static String CreaPerfilesSinUso(String cadenaBD)                    //AGREGA UNA TABLA CON LOS PERFILES QUE NO FUERON USADOS POR USUARIOS INTERNOS Y EXTERNOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String consulta = "create table perfilessinuso" + parametros[4] + "  (select * from perfilessinusoint" + parametros[4] + "  where CLAVE_PUESTO in (select CLAVE_PUESTO FROM perfilessinusoext" + parametros[4] + " ))";
        return consulta;
    }
        
    public static String CreaExcepDuplicados(String cadenaBD)                   //CREA TABLA CON LOS REGISTROS DE USUARIOS QUE SON EXCEPCIONES AL MONITOREO DE USUARIOS DUPLICADOS             
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "CREATE TABLE IF NOT EXISTS EXCEPDUPLICADOS" + parametros[4] + " (ID INT NOT NULL AUTO_INCREMENT, NUM_EMP VARCHAR(45), USER_NAME VARCHAR(45), "
                + "NOMBRE VARCHAR(255), REGION INT, PRIMARY KEY(ID))";
        return statement;
    }
    
    public static String CreaMatrizPerfiles(String cadenaBD)                    //CREA TABLA PARA LA MATRIZ DE PERFILES ESTANDARIZADOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "CREATE TABLE IF NOT EXISTS MATRIZPERFILES" + parametros[4] + " (ID INT NOT NULL AUTO_INCREMENT, GERENCIA VARCHAR(255), CLAVE_PUESTO VARCHAR(45), "
                + "PUESTO VARCHAR(255), PERFIL VARCHAR(45), PUESTO_FUNCIONAL VARCHAR(255), "
                + "PRIMARY KEY(ID))";
        return statement;
    }
    
    public static String BorraUsuarios(String cadenaBD)                         //BORRA LA TABLA PREEXISTENTE DE USUARIOS PARA INGRESAR LOS USUARIOS NUEVOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "DROP TABLE IF EXISTS Usuarios" + parametros[4];
        return statement;
    }
    
    public static String CreaExcepDuplicados(String cadenaBD, String ruta)      //INSERTA EN LA TABLA DE EXCEPCIONES LOS REGISTROS DE USUARIOS DUPLICADOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String retorno = "INSERT INTO EXCEPDUPLICADOS" + parametros[4] + " (NUM_EMP, USER_NAME, NOMBRE, REGION) VALUES";        
        retorno = retorno + Archivos.lecturaExcepDuplicados(ruta, cadenaBD);
        
        return retorno;
    }
    
    public static String CreaMatrizPerfiles(String cadenaBD, String ruta)       //INSERTA EN LA TABLA LA MATRIZ DE PERFILES ESTANDARIZADOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String retorno = "INSERT INTO MATRIZPERFILES" + parametros[4] + " (GERENCIA, CLAVE_PUESTO, PUESTO, PERFIL, PUESTO_FUNCIONAL) VALUES";        
        retorno = retorno + Archivos.lecturaMatrizPerfiles(ruta, cadenaBD);
        System.out.println(retorno);
        return retorno;
    }
    
    public static String UsrIDIncInt(String cadenaBD)                           //AGREGA UNA TABLA PARA LAS INCIDENCIAS DE USUARIOS INTERNOS CON USER ID INCORRECTO
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "CREATE TABLE IF NOT EXISTS USRINCINT SELECT * FROM CRUCEINT" + parametros[4] + " WHERE USER_NAME != IDUSUARIO";
        return statement;
    }
    
    public static String UsrIDIncExt(String cadenaBD)                           //AGREGA UNA TABLA PARA LAS INCIDENCIAS DE USUARIOS EXTERNOS CON USER ID INCORRECTO
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "CREATE TABLE IF NOT EXISTS USRINCEXT SELECT * FROM CRUCEEXT" + parametros[4] + " WHERE USER_NAME != IDUSUARIO";
        return statement;
    }
    
    public static String CreaDuplicadosIntSAP(String cadenaBD)                     //AGREGA UNA TABLA CON LAS INCIDENCIAS DE USUARIOS INTERNOS DUPLICADOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "CREATE TABLE IF NOT EXISTS DUPXNOMBREINTSAP SELECT DISTINCT * FROM CRUCEINTSAP" + parametros[4] + " WHERE (NOMBRE_COMPLETO IN "
                + "(SELECT NOMBRE_COMPLETO FROM CRUCEINTSAP" + parametros[4] + " AS TMP GROUP BY NOMBRE_COMPLETO HAVING COUNT(*) > 1)) "
                + "AND (USUARIO IN (SELECT Usuario FROM cruceintsap" + parametros[4] + " AS TMP2 GROUP BY Usuario HAVING COUNT(*) < 2))  ORDER BY Nombre_Completo";
        return statement;
    }
    
    public static String CreaDuplicadosInt(String cadenaBD)                     //AGREGA UNA TABLA CON LAS INCIDENCIAS DE USUARIOS INTERNOS DUPLICADOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "CREATE TABLE IF NOT EXISTS DUPXNOMBREINT SELECT DISTINCT * FROM CRUCEINT" + parametros[4] + " WHERE NOMBRE IN "
                + "(SELECT NOMBRE FROM CRUCEINT" + parametros[4] + " AS TMP GROUP BY NOMBRE HAVING COUNT(*) > 1) ORDER BY NOMBRE";
        return statement;
    }
    
    public static String CreaDuplicadosExtSAP(String cadenaBD)                     //AGREGA UNA TABLA CON LAS INCIDENCIAS DE USUARIOS EXTERNOS DUPLICADOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "CREATE TABLE IF NOT EXISTS DUPXNOMBREEXTSAP SELECT DISTINCT * FROM CRUCEEXTSAP" + parametros[4] + " WHERE (NOMBRE_COMPLETO IN "
                + "(SELECT NOMBRE_COMPLETO FROM CRUCEEXTSAP" + parametros[4] + " AS TMP GROUP BY NOMBRE_COMPLETO HAVING COUNT(*) > 1)) "
                + "AND (USUARIO IN (SELECT Usuario FROM cruceextsap" + parametros[4] + " AS TMP2 GROUP BY Usuario HAVING COUNT(*) < 2))  ORDER BY Nombre_Completo";
        return statement;
    }
    
    public static String CreaDuplicadosExt(String cadenaBD)                     //AGREGA UNA TABLA CON LAS INCIDENCIAS DE USUARIOS EXTERNOS DUPLICADOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "CREATE TABLE IF NOT EXISTS DUPXNOMBREEXT SELECT DISTINCT * FROM CRUCEEXT" + parametros[4] + " WHERE NOMBRE IN "
                + "(SELECT NOMBRE FROM CRUCEEXT" + parametros[4] + " AS TMP GROUP BY NOMBRE HAVING COUNT(*) > 1) ORDER BY NOMBRE";
        return statement;
    }
    
    public static String BorraExcepDuplicados(String cadenaBD)                  //BORRA LA TABLA DE EXCEPCIONES PARA LOS REGISTROS QUE SON DUPLICADOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "DROP TABLE IF EXISTS EXCEPDUPLICADOS" + parametros[4];
        return statement;
    }
    
    public static String CreaInsertUsuarios(String cadenaBD, ResultSet Res)     //CREA LA INSTRUCCIÓN SQL PARA INSERTAR LOS USUARIOS QUE FUERON DESCARGADOS DE LA BD DEL APLICATIVO
    {
        String[] parametros = cadenaBD.split("\\|");
        String retorno = "INSERT INTO USUARIOS" + parametros[4] + " (NUM_EMP, USER_NAME, NOMBRE, REGION, IP, CORREO, JEFE, PERFIL, NOMBRE_PERFIL, FECHA_ACCESO) VALUES ";        
        String fecha;
        String nombre;
        String correo;
        try {
            
            while(Res.next())
            {
                fecha = Res.getString(13);
                if(!fecha.isEmpty())
                {
                    String [] fechac;
                    fechac = fecha.split(" ");
                    fecha = fechac[0];
                    String [] fechap;
                    fecha = "'" + fecha + "'";
                }
                else
                {
                    fecha = "''";
                }
                correo = Res.getString(8);
                if(correo==null)
                {
                    correo = "";
                }
                nombre = Res.getString(4);
//                System.out.println(nombre);
                nombre = Monitoreos.Archivos.escapaChar(nombre, "'");
//                System.out.println(nombre);
                retorno = retorno + "\n ('" + Res.getString(1) + "', '"  + Res.getString(2) + "', '" + Res.getString(3) + " " + nombre + " " + Res.getString(5) + "', "
                         + Res.getString(6) + ", '" + Res.getString(7) + "', '" + correo + "', '" + Res.getString(9) + "', '"
                         + Res.getString(11) + "', '" + Res.getString(12) + "', " + fecha +  "),";
                
            }
            retorno = retorno.substring(0, retorno.length()-1);
//            System.out.println(retorno);
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Querys.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return retorno;
        
    }
    
    public static String CruceInternos(String cadenaBD)                         //REALIZA EL CRUCE DE LOS USUARIOS INTERNOS
    {
        String statement = null;
        String[] parametros = cadenaBD.split("\\|");
        statement = "create table if not exists cruceint" + parametros[4] + " SELECT INTERNOS.ID, INTERNOS.NUM_EMP, INTERNOS.USER_NAME, INTERNOS.NOMBRE, INTERNOS.REGION, "
                + "INTERNOS.IP, INTERNOS.CORREO, INTERNOS.JEFE, INTERNOS.PERFIL, INTERNOS.NOMBRE_PERFIL, INTERNOS.FECHA_ACCESO,  "
                + "idint" + parametros[4] + ".NUMEROEMPLEADO AS IDNUMEMP, "
                + "idint" + parametros[4] + ".USUARIO AS IDUSUARIO, idint" + parametros[4] + ".NOMBRECOMPLETO AS IDNOMBRE, "
                + "idint" + parametros[4] + ".REGION AS IDREGION, idint" + parametros[4] + ".GERENCIA AS IDGERENCIA, "
                + "idint" + parametros[4] + ".DEPARTAMENTO AS IDDEPARTAMENTO, "
                + "idint" + parametros[4] + ".PUESTO AS IDPUESTO, idint" + parametros[4] + ".IDPUESTO AS IDIDPUESTO, "       
                + "idint" + parametros[4] + ".ESTATUS AS IDESTATUS, idint" + parametros[4] + ".FECHA AS IDFECHA "
                + ""
                + "FROM INTERNOS left join idint" + parametros[4] 
                + " on INTERNOS.NUM_EMP=idint" + parametros[4] + ".NUMEROEMPLEADO";
        
        return statement;
    }
    
    
    public static String CruceExternos(String cadenaBD)                         //REALIZA EL CRUCE DE LOS USUARIOS EXTERNOS
    {
        String statement = null;
        String[] parametros = cadenaBD.split("\\|");
        statement = "create table if not exists cruceext" + parametros[4] + " SELECT EXTERNOS.ID, EXTERNOS.NUM_EMP, " 
                    + "EXTERNOS.User_Name,  EXTERNOS.Nombre, EXTERNOS.Region, EXTERNOS.IP, EXTERNOS.CORREO, EXTERNOS.JEFE, EXTERNOS.PERFIL, "
                    + "EXTERNOS.NOMBRE_PERFIL, EXTERNOS.FECHA_ACCESO, "
                    + "idext" + parametros[4] + ".NUMEROEMPLEADO AS IDNUMEMP, " 
                    + "idext" + parametros[4] + ".USUARIO AS IDUSUARIO, idext" + parametros[4] + ".NOMBRECOMPLETO AS IDNOMBRE, "
                    + "idext" + parametros[4] + ".REGION AS IDREGION, idext" + parametros[4] + ".GERENCIA AS IDGERENCIA, "
                    + "idext" + parametros[4] + ".DEPARTAMENTO AS IDDEPARTAMENTO, "
                    + "idext" + parametros[4] + ".PUESTO AS IDPUESTO, idext" + parametros[4] + ".IDPUESTO AS IDIDPUESTO, "                    
                    + "idext" + parametros[4] + ".ESTATUS AS IDESTATUS, idext" + parametros[4] + ".FECHA AS IDFECHA  "
                    + "FROM EXTERNOS left join idext" + parametros[4]
                    + " on EXTERNOS.Num_Emp=idext" + parametros[4] + ".NUMEROEMPLEADO ";
            
        return statement;
    }
    
    
                    //************************SAP**********************
    
    public static String CruceInternosSAP(String cadenaBD)                         //REALIZA EL CRUCE DE LOS USUARIOS INTERNOS
    {
        String statement = null;
        String[] parametros = cadenaBD.split("\\|");
        statement = "CREATE TABLE IF NOT EXISTS cruceintSAP"+ parametros[4] + " SELECT internossap.Usuario, internossap.Nombre_completo, "
                + "internossap.Grupo, internossap.Valido_De, internossap.Validez_a,"
                + " idint" + parametros[4] + ".NUMEROEMPLEADO,"
                + " idint" + parametros[4] + ".USUARIO AS IDUSUARIO,"
                + " idint" + parametros[4] + ".NOMBRECOMPLETO,"
                + " idint" + parametros[4] + ".REGION," 
                + " idint" + parametros[4] + ".GERENCIA,"
                + " idint" + parametros[4] + ".DEPARTAMENTO,"
                + " idint" + parametros[4] + ".PUESTO,"
                + " idint" + parametros[4] + ".IDPUESTO," 
                + " idint" + parametros[4] + ".ESTATUS," 
                + " idint" + parametros[4] + ".FECHA,"                
                + " fechasa" + parametros[4] + ".Usuario AS usuario_fecha,"
                + " fechasa" + parametros[4] + ".Creado_por,"
                + " fechasa" + parametros[4] + ".Fecha_creacion,"
                + " fechasa" + parametros[4] + ".valido_de AS Inicio_validez,"
                + " fechasa" + parametros[4] + ".fin_validez,"
                + " fechasa" + parametros[4] + ".Entrada_sist,"
                + " fechasa" + parametros[4] + ".Clave_acc,"
                + " fechasa" + parametros[4] + ".Bloqueo," 
                + " Uperfiles" + parametros[4] + ".Nombre AS Nombre_F," 
                + " Uperfiles" + parametros[4] + ".Apellido AS Apellido_F," 
                + " Uperfiles" + parametros[4] + ".Grupo AS Grupo_Pais," 
                + " Uperfiles" + parametros[4] + ".Rol,"
                + " Uperfiles" + parametros[4] + ".Descripcion_Rol," 
                + " Uperfiles" + parametros[4] + ".Fecha_inicio,"
                + " Uperfiles" + parametros[4] + ".Fecha_fin" 
                + " FROM ((internossap left join idint" + parametros[4] + " on internossap.usuario = idint" + parametros[4] + ".NUMEROEMPLEADO) left join"
                + " fechasa" + parametros[4]+ " on fechasa" + parametros[4] +".usuario = internossap.usuario)"
                + " right join Uperfiles" + parametros[4] + " on Uperfiles" + parametros[4] + ".usuario = internossap.usuario "
                + " union "
                + " SELECT internossap.Usuario, internossap.Nombre_completo, "
                + "internossap.Grupo, internossap.Valido_De, internossap.Validez_a,"
                + " idint" + parametros[4] + ".NUMEROEMPLEADO,"
                + " idint" + parametros[4] + ".USUARIO AS IDUSUARIO,"
                + " idint" + parametros[4] + ".NOMBRECOMPLETO,"
                + " idint" + parametros[4] + ".REGION," 
                + " idint" + parametros[4] + ".GERENCIA,"
                + " idint" + parametros[4] + ".DEPARTAMENTO,"
                + " idint" + parametros[4] + ".PUESTO,"
                + " idint" + parametros[4] + ".IDPUESTO," 
                + " idint" + parametros[4] + ".ESTATUS," 
                + " idint" + parametros[4] + ".FECHA,"
                + " fechasa" + parametros[4] + ".Usuario AS usuario_fecha,"
                + " fechasa" + parametros[4] + ".Creado_por,"
                + " fechasa" + parametros[4] + ".Fecha_creacion,"
                + " fechasa" + parametros[4] + ".valido_de AS Inicio_validez,"
                + " fechasa" + parametros[4] + ".fin_validez,"
                + " fechasa" + parametros[4] + ".Entrada_sist,"
                + " fechasa" + parametros[4] + ".Clave_acc,"
                + " fechasa" + parametros[4] + ".Bloqueo," 
                + " Uperfiles" + parametros[4] + ".Nombre AS Nombre_F," 
                + " Uperfiles" + parametros[4] + ".Apellido AS Apellido_F," 
                + " Uperfiles" + parametros[4] + ".Grupo AS Grupo_Pais," 
                + " Uperfiles" + parametros[4] + ".Rol,"
                + " Uperfiles" + parametros[4] + ".Descripcion_Rol," 
                + " Uperfiles" + parametros[4] + ".Fecha_inicio,"
                + " Uperfiles" + parametros[4] + ".Fecha_fin" 
                + " FROM ((internossap left join idint" + parametros[4] + " on internossap.usuario = idint" + parametros[4] + ".NUMEROEMPLEADO) left join"
                + " fechasa" + parametros[4]+ " on fechasa" + parametros[4] +".usuario = internossap.usuario)"
                + " left join Uperfiles" + parametros[4] + " on Uperfiles" + parametros[4] + ".usuario = internossap.usuario ";
        
                             
        
        return statement;
    }
    
    
    public static String CruceExternosSAP(String cadenaBD)                         //REALIZA EL CRUCE DE LOS USUARIOS EXTERNOS
    {
        String statement = null;
        String[] parametros = cadenaBD.split("\\|");
        statement = "CREATE TABLE IF NOT EXISTS cruceextSAP"+ parametros[4] + " SELECT externossap.Usuario, externossap.Nombre_completo, "
                + "externossap.Grupo, externossap.Valido_De, externossap.Validez_a,"
                + " idext" + parametros[4] + ".NUMEROEMPLEADO,"
                + " idext" + parametros[4] + ".USUARIO AS IDUSUARIO,"
                + " idext" + parametros[4] + ".NOMBRECOMPLETO,"
                + " idext" + parametros[4] + ".REGION," 
                + " idext" + parametros[4] + ".GERENCIA,"
                + " idext" + parametros[4] + ".DEPARTAMENTO,"
                + " idext" + parametros[4] + ".PUESTO,"
                + " idext" + parametros[4] + ".IDPUESTO," 
                + " idext" + parametros[4] + ".ESTATUS," 
                + " idext" + parametros[4] + ".FECHA,"              
                + " fechasa" + parametros[4] + ".Usuario AS usuario_fecha,"
                + " fechasa" + parametros[4] + ".Creado_por,"
                + " fechasa" + parametros[4] + ".Fecha_creacion,"
                + " fechasa" + parametros[4] + ".valido_de AS Inicio_validez,"
                + " fechasa" + parametros[4] + ".fin_validez,"
                + " fechasa" + parametros[4] + ".Entrada_sist,"
                + " fechasa" + parametros[4] + ".Clave_acc,"
                + " fechasa" + parametros[4] + ".Bloqueo,"
                + " Uperfiles" + parametros[4] + ".Nombre AS Nombre_F," 
                + " Uperfiles" + parametros[4] + ".Apellido AS Apellido_F," 
                + " Uperfiles" + parametros[4] + ".Grupo AS Grupo_Pais," 
                + " Uperfiles" + parametros[4] + ".Rol,"
                + " Uperfiles" + parametros[4] + ".Descripcion_Rol," 
                + " Uperfiles" + parametros[4] + ".Fecha_inicio,"
                + " Uperfiles" + parametros[4] + ".Fecha_fin" 
                + " FROM ((externossap left join idext" + parametros[4] + " on externossap.usuario = idext" + parametros[4] + ".NUMEROEMPLEADO) left join"
                + " fechasa" + parametros[4]+ " on fechasa" + parametros[4] +".usuario = externossap.usuario)"
                + " left join Uperfiles" + parametros[4] + " on Uperfiles" + parametros[4] + ".usuario = externossap.usuario";
                
            
        return statement;
    }
    
    public static String CreaDemIE(String cadenaBD)                             //AGREGA UNA TABLA CON LAS EXCEPCIONES DE USUARIOS DEMOSA INTERNOS Y EXTERNOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "create table if not exists demonsaIE select * "
                + "FROM cruceintsap" + parametros[4] + " WHERE numemp IS NOT null "
                + "UNION "
                + "SELECT * FROM cruceextsap" + parametros[4] + " WHERE numemp IS NOT null";
        return statement;
    }
    
    public static String DMEXGen(String cadenaBD)                               //AGREGA A LA TABLA DE EXCEPCIONES DE USUARIOS DEMOSA LOS QUE SE ENCUENTRAN COMO USUARIOS GENÉRICOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String tabla = "create table if not exists DemonsaIE  SELECT Usuario, Nombre_Completo, Grupo, "
                + "Valido_de, Validez_a FROM GenericosSAP"
                + " WHERE Usuario LIKE '%DMEX%' or Usuario LIKE 'DM%' or Usuario in (select numemp from demonsa2" + parametros[4] + ")";
                
        return tabla; 
    }
    
    public static String DMEXInt(String cadenaBD)                               //AGREGA A LA TABLA DE EXCEPCIONES DE USUARIOS DEMOSA LOS QUE SE ENCUENTRAN COMO USUARIOS GENÉRICOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String tabla = "insert into DemonsaIE (Usuario, Nombre_Completo, Grupo, Valido_de, Validez_a) SELECT Usuario, Nombre_Completo, Grupo, "
                + "Valido_de, Validez_a FROM internosSAP"
                + " WHERE  Usuario in (select numemp from demonsa2" + parametros[4] + ")";
                
        return tabla; 
    }
    
    public static String DMEXExt(String cadenaBD)                               //AGREGA A LA TABLA DE EXCEPCIONES DE USUARIOS DEMOSA LOS QUE SE ENCUENTRAN COMO USUARIOS GENÉRICOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String tabla = "insert into DemonsaIE (Usuario, Nombre_Completo, Grupo, Valido_de, Validez_a) SELECT Usuario, Nombre_Completo, Grupo, "
                + "Valido_de, Validez_a FROM externosSAP"
                + " WHERE  Usuario in (select numemp from demonsa2" + parametros[4] + ")";
                
        return tabla; 
    }
    
    public static String ExcepcionesExtSAP(String cadenaBD)                        //CREA UNA TABLA CON LAS EXCEPCIONES DE USUARIOS ACTIVOS QUE PERTENENCEN A LA GERENCIA DE SAP
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "create table if not exists ExcepcionesSAP select * "
                + "FROM cruceextsap" + parametros[4] + " WHERE Gerencia LIKE '%SAP%' AND ESTATUS = 'ACTIVO' ";
        return statement;
    }
    
    public static String ExcepcionesIntSAP(String cadenaBD)                        //CREA UNA TABLA CON LAS EXCEPCIONES DE USUARIOS ACTIVOS QUE PERTENENCEN A LA GERENCIA DE SAP
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "INSERT INTO ExcepcionesSAP select * "
                + "FROM cruceintsap" + parametros[4] + " WHERE Gerencia LIKE '%SAP%' AND ESTATUS = 'ACTIVO' ";
        return statement;
    }
    
    public static String CreaPerfilesIntSAP(String cadenaBD)                     //AGREGA UNA TABLA CON LAS INCIDENCIAS DE USUARIOS EXTERNOS DUPLICADOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "create table if not exists PerfilesIntSAP select * "
                + "FROM cruceintsap" + parametros[4] + " WHERE idpuesto != rol ";
        return statement;
    } 
    
    public static String BorraPerfilesIntSAP(String cadenaBD)                     //AGREGA UNA TABLA CON LAS INCIDENCIAS DE USUARIOS EXTERNOS DUPLICADOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "DELETE FROM cruceintsap" + parametros[4] + " WHERE idpuesto != rol ";
        return statement;
    }
//    MÉTODO ORIGINAL
//    public static String CreaPerfilesNoAutorizadosIntSAP(String cadenaBD)                     //AGREGA UNA TABLA CON LAS INCIDENCIAS DE USUARIOS EXTERNOS DUPLICADOS
//    {
//        String[] parametros = cadenaBD.split("\\|");
//        String statement = "create table if not exists PerfilesNoAutorizadosIntSAP select * "
//                + "FROM cruceintsap" + parametros[4] + " WHERE idpuesto != Rol and Usuario in (select usuario from cruceintsap" + parametros[4] + " where idpuesto = Rol) "
//                + "union "
//                + "select * FROM cruceintsap" + parametros[4] + " WHERE idpuesto = Rol and Usuario in (select usuario from cruceintsap" + parametros[4] + " where idpuesto != Rol) order by usuario";
//        return statement;
//        
//    }
    
    public static String CreaPerfilesNoAutorizadosIntSAP(String cadenaBD)                     //AGREGA UNA TABLA CON LAS INCIDENCIAS DE USUARIOS EXTERNOS DUPLICADOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "create table if not exists PerfilesNoAutorizadosIntSAP select * FROM cruceintsap" + parametros[4] + " WHERE USUARIO IN "
                + " (SELECT USUARIO FROM CRUCEINTSAP" + parametros[4] + " GROUP BY usuario HAVING COUNT(*) > 1) order by Nombre_completo ";
                
        return statement;    
        
        
    }
    
    
//    public static String BorraPerfilesNoAutorizadosIntSAP(String cadenaBD)                     //AGREGA UNA TABLA CON LAS INCIDENCIAS DE USUARIOS EXTERNOS DUPLICADOS
//    {
//        String[] parametros = cadenaBD.split("\\|");
//        String statement = "DELETE FROM cruceintsap" + parametros[4] + " WHERE USUARIO IN (SELECT USUARIO FROM PerfilesNoAutorizadosIntSAP)";                
//        return statement;
//        
//    } 
    
    public static String BorraPerfilesNoAutorizadosIntSAP(String cadenaBD)                     //AGREGA UNA TABLA CON LAS INCIDENCIAS DE USUARIOS EXTERNOS DUPLICADOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "DELETE FROM cruceintsap" + parametros[4] + " WHERE USUARIO IN (SELECT USUARIO FROM PerfilesNoAutorizadosIntSAP)";                
        return statement;
        
    } 
    
    public static String CreaPerfilesExtSAP(String cadenaBD)                     //AGREGA UNA TABLA CON LAS INCIDENCIAS DE USUARIOS EXTERNOS DUPLICADOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "create table if not exists PerfilesIntSAP select  "
                + "FROM " + parametros[4] + " WHERE Gerencia LIKE '%SAP%' ";
        return statement;
    } 
       
    public static String CreaAdminUsrAdminGenSAP(String cadenaBD)                     //AGREGA UNA TABLA CON USUARIOS ADMINISTRADORES GENERICOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "create table if not exists AdminUsrAdminGenSAP (Usuario varchar(45), nombre varchar(255), apellido varchar(255),\n" +
        "rol varchar(45), valor_autorizacion varchar(45), permitido boolean default 0, ennomina boolean, fueranomina boolean,\n" +
        "primary key(Usuario))";
        return statement;
    } 
    
    public static String CreaAdminCodigosSAP(String cadenaBD)                     //AGREGA UNA TABLA CON USUARIOS ADMINISTRADORES GENERICOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "create table if not exists AdminCodigosSAP (Usuario varchar(45), Rol varchar(255), Denominacion varchar(255), Valor varchar(45), "
                + "permitido boolean default 0, primary key(Usuario))";
        return statement;
    } 
    
    public static String CreaAdminUsrAdminSAP(String cadenaBD)                     //AGREGA UNA TABLA CON USUARIOS ADMINISTRADORES GENERICOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "create table if not exists AdminUsrAdminSAP (Usuario varchar(45), nombre varchar(255), apellido varchar(255),\n" +
        "rol varchar(45), valor_autorizacion varchar(45), permitido boolean default 0, \n" +
        "primary key(Usuario));";
        return statement;
    } 
                    //*******************************
    
    public static String BorraUsrAdminAgregados()                          //BORRA LA TABLA DE TRABAJO DE USUARIOS ADMINISTRADORES AGREGADOS
    {
        String statement = "DROP TABLE IF EXISTS agregados";
        return statement;
    }
    public static String BorraUsrAdminEliminados()                          //BORRA LA TABLA DE TRABAJO DE USUARIOS ADMINISTRADORES ELIMINADOS
    {
        String statement = "DROP TABLE IF EXISTS eliminados";
        return statement;
    }
    
    public static String BorraExternosTrabajoSAP()                                 //BORRA LA TABLA DE TRABAJO EXTERNOS
    {
        String statement = "DROP TABLE IF EXISTS ExternosSAP";
        return statement;
    }
    
    public static String BorraGenericosTrabajoSAP()                                 //BORRA LA TABLA DE TRABAJO INTERNOS
    {
        String statement = "DROP TABLE IF EXISTS GenericosSAP";
        return statement;
    }
    
    public static String BorraInternosTrabajoSAP()                                 //BORRA LA TABLA DE TRABAJO INTERNOS
    {
        
        String statement = "DROP TABLE IF EXISTS InternosSAP";
        return statement;
    }
    
    public static String eliminanonominaextSAP()                                 //BORRA LA TABLA DE TRABAJO INTERNOS
    {
        
        String statement = "DROP TABLE IF EXISTS nonominaextSAP";
        return statement;
    }
    public static String eliminanonominaintSAP()                                 //BORRA LA TABLA DE TRABAJO INTERNOS
    {
        
        String statement = "DROP TABLE IF EXISTS nonominaintSAP";
        return statement;
    }
    
}
