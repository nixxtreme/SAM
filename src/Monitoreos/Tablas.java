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
        String Statement = "DROP TABLE IF EXISTS UsrAdmin"+ parametros[4];
            
        return Statement;
    }
   
   public static void eliminaTablaDemonsa2(String cadenaBD)                         //ELIMINA LA TABLA DE NÓMINA INTERNOS
    {
        String[] parametros = cadenaBD.split("\\|");
        

        
        try
        {
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());
            
            // Se obtiene una conexión con la base de datos. 
            Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/"+parametros[0]+"",parametros[1],parametros[2]);
            
            Statement cstmt2 = conexion.createStatement();  

            int rs2 = cstmt2.executeUpdate("DROP TABLE IF EXISTS `Demonsa2"+ parametros[4] + "`");
            
            // Se cierra la conexión con la base de datos.
            conexion.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
   
   public static String idCreaUsrAdmin(String cadenaBD)                          //CREA LA TABLA DE NÓMINA EXTERNOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String tabla = "CREATE TABLE IF NOT EXISTS usradmin"+ parametros[4] + " (USUARIO VARCHAR(25) NOT NULL, NOMBRE VARCHAR(45), APELLIDO VARCHAR(43), "
        + "ROL VARCHAR(25) , VALOR_AUTORIZACION VARCHAR(8), PERMITIDO BOOLEAN NOT NULL DEFAULT FALSE, PRIMARY KEY (USUARIO))";
        
        //System.out.println("Tabla \n" + tabla);
        
        return tabla;
    }
   
   public static void idCreaDemonsa2(String cadenaBD)                          //CREA LA TABLA DE NÓMINA EXTERNOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String tabla = "CREATE TABLE IF NOT EXISTS demonsa2"+ parametros[4] + " (NUMEMP VARCHAR(25) NOT NULL, NOMBRE VARCHAR(45), IDPUESTO VARCHAR(25), "
        + "PUESTO VARCHAR(45) , GERENCIA VARCHAR(45), REGION VARCHAR(25) PRIMARY KEY (NUMEMP))";
        
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
    
    public static String UsrAdminTabla(String usuarios, String cadenaBD)             //INSERTA LOS REGISTROS A LA TABLA DE NÓMINA INTERNOS
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
    
                                    //Tablas de cruces internos, externos y genericos PAQUETE TABLAS
   
    public static String TablaExtSAP(String cadenaBD)                             //Crea la tabla con las insidencias de usuarios administradores agregados en el mes actual
    {
        String statement = null;
        String[] parametros = cadenaBD.split("\\|");
        statement = "CREATE TABLE IF NOT EXISTS ExternosSAP SELECT Usuario, Nombre_Completo, Grupo, Bloq, Valido_de, Validez_a FROM UsuariosSAP" + parametros[4]
                    + " WHERE usuario LIKE 'EX%' ";
        return statement;
    }
    
    
   public static String TablaGenSAP(String cadenaBD)                             //Crea la tabla con las insidencias de usuarios administradores agregados en el mes actual
    {
        String statement = null;
        String[] parametros = cadenaBD.split("\\|");
        statement = "create table if not exists GenericosSAP SELECT Usuario, Nombre_Completo, Grupo, Bloq, Valido_de, Validez_a FROM UsuariosSAP" + parametros[4] 
                    + " WHERE usuario LIKE '%A%' OR usuario LIKE '%B%' "
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
    public static String TablaIntSAP(String cadenaBD)                             //Crea la tabla con las insidencias de usuarios administradores agregados en el mes actual
    {
        String statement = null;
        String[] parametros = cadenaBD.split("\\|");
        statement = "create table if not exists InternosSAP SELECT Usuario, Nombre_Completo, Grupo, Bloq, Valido_de, Validez_a FROM UsuariosSAP" + parametros[4];
        return statement;
    }
    
        //eliminacion tablas
     public static String eliminaTablaUsrsSAP(String cadenaBD)                         //ELIMINA LA TABLA DE USUARIOS ADMINISTRADORES
    {
        String[] parametros = cadenaBD.split("\\|");
        String statment = "DROP TABLE IF EXISTS `Usuarios2"+ parametros[4] + "`";  
        return statment;
        
    }
     
     public static String eliminaUsuariosSAP2(String cadenaBD)                         //ELIMINA LA TABLA DE USUARIOS ADMINISTRADORES
    {
        String[] parametros = cadenaBD.split("\\|");
        String statment="DROP TABLE IF EXISTS `UsuariosSAP"+ parametros[4] + "`";    
        return statment;
    }
    
                //USUARIOS 2 SAP
    public static String CreaUsuariosSAP(String cadenaBD)                          //AGREGA OTRA TABLA USUARIOS PARA LA MANIPULACION DE LOS REGISTROS
    {
        String[] parametros = cadenaBD.split("\\|");
        String tabla = "CREATE TABLE IF NOT EXISTS UsuariosSAP" + parametros[4] + " SELECT * FROM Usuarios2" + parametros[4];
        return tabla; 
    }
   
    public static String CreaUsuarios2(String cadenaBD)                          //AGREGA TABLA PARA LOS USUARIOS OBTENIDOS DE LA BASE DE DATOS DE SAP SAP
    {
       String[] parametros = cadenaBD.split("\\|");
        String tabla = "CREATE TABLE IF NOT EXISTS Usuarios2" + parametros[4] + " (Usuario VARCHAR(20) NOT NULL, Nombre_Completo VARCHAR(45), Grupo VARCHAR(20), "
                + "Bloq varchar(45), valido_de DATE, Validez_a DATE, PRIMARY KEY(usuario))";
        return tabla;
    }
    
    
    public static String InsertarUsuariosSAP (String usuarios, String cadenaBD)             //INSERTA LOS REGISTROS A LA TABLA DE NÓMINA INTERNOS
    {
        String[] parametros = cadenaBD.split("\\|");
       
        String valores = "INSERT IGNORE INTO Usuarios2" + parametros[4] + " (USUARIO, NOMBRE_COMPLETO, GRUPO, BLOQ, VALIDO_DE, VALIDEZ_A) "
                + "values ";
        
        valores = valores + Archivos.lecturaUsuariosSAP(usuarios, cadenaBD);
        return valores;
    }
    
    public static String GenExt(String cadenaBD)                          //AGREGA OTRA TABLA USUARIOS PARA LA MANIPULACION DE LOS REGISTROS
    {
        String[] parametros = cadenaBD.split("\\|");
        String tabla = "INSERT IGNORE INTO GenericosSAP SELECT Usuario, Nombre_Completo, Grupo, Bloq, Valido_de, Validez_a FROM ExternosSAP"
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
    public static String NoNominaCreaIntSAP(String cadenaBD)                       //AGREGA UNA TABLA PARA LAS INCIDENCIAS DE USUARIOS INTERNOS QUE NO SE ENCUENTRAN EN NÓMINA
    {
        String[] parametros = cadenaBD.split("\\|");
        String consulta = "create table if not exists nonominaint SELECT * FROM cruceint" + parametros[4] + " where IDNUMEMP is null";
                
        return consulta;
    }
    
    public static String NoNominaCreaExtSAP(String cadenaBD)                       //AGREGA UNA TABLA PARA LAS INCIDENCIAS DE USUARIOS EXTERNOS QUE NO SE ENCUENTRAN EN NÓMINA
    {
        String[] parametros = cadenaBD.split("\\|");
        String consulta = "create table if not exists nonominaext SELECT * FROM cruceext" + parametros[4] + " where idusuario is null";
                
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
        String consulta = "create table if not exists bajasextSAP SELECT * FROM cruceextSAP" + parametros[4] + " WHERE IDESTATUS = 'ELIMINADO'";
                
        return consulta;
    }
    
    public static String CreaBajasIntSAP(String cadenaBD)                          //AGREGA UNA TABLA PARA LAS INCIDENCIAS DE USUARIOS INTERNOS QUE ESTÁN DADOS DE BAJA
    {
        String[] parametros = cadenaBD.split("\\|");
        String consulta = "create table if not exists bajasintSAP SELECT * FROM cruceintSAP" + parametros[4] + " WHERE IDESTATUS = 'ELIMINADO'";
                
        return consulta;
    }
    
    
    
    
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
                + "PUESTO VARCHAR(255), PERFIL VARCHAR(45), PUESTO_FUNCIONAL VARCHAR(45), "
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
    
    public static String CreaDuplicadosInt(String cadenaBD)                     //AGREGA UNA TABLA CON LAS INCIDENCIAS DE USUARIOS INTERNOS DUPLICADOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "CREATE TABLE IF NOT EXISTS DUPXNOMBREINT SELECT DISTINCT * FROM CRUCEINT" + parametros[4] + " WHERE NOMBRE IN "
                + "(SELECT NOMBRE FROM CRUCEINT" + parametros[4] + " AS TMP GROUP BY NOMBRE HAVING COUNT(*) > 1) ORDER BY NOMBRE";
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
    
    
                    //SAP
    
    public static String CruceInternosSAP(String cadenaBD)                         //REALIZA EL CRUCE DE LOS USUARIOS INTERNOS
    {
        String statement = null;
        String[] parametros = cadenaBD.split("\\|");
        statement = "create table if not exists cruceintSAP" + parametros[4] + " SELECT INTERNOSSAP.Usuario, INTERNOSSAP.Nombre_Completo, INTERNOSSAP.Grupo "
                + "INTERNOSSAP.Valido_de, INTERNOSSAP.Validez_a "
                + "idint" + parametros[4] + ".NUMEROEMPLEADO AS IDNUMEMP, "
                + "idint" + parametros[4] + ".USUARIO AS IDUSUARIO, idint" + parametros[4] + ".NOMBRECOMPLETO AS IDNOMBRE, "
                + "idint" + parametros[4] + ".REGION AS IDREGION, idint" + parametros[4] + ".GERENCIA AS IDGERENCIA, "
                + "idint" + parametros[4] + ".DEPARTAMENTO AS IDDEPARTAMENTO, "
                + "idint" + parametros[4] + ".PUESTO AS IDPUESTO, idint" + parametros[4] + ".IDPUESTO AS IDIDPUESTO, "       
                + "idint" + parametros[4] + ".ESTATUS AS IDESTATUS, idint" + parametros[4] + ".FECHA AS IDFECHA "
                + ""
                + "FROM INTERNOSSAP left join idint" + parametros[4] 
                + " on INTERNOSSAP.Usuario=idint" + parametros[4] + ".NUMEROEMPLEADO";
        
        return statement;
    }
    
    
    public static String CruceExternosSAP(String cadenaBD)                         //REALIZA EL CRUCE DE LOS USUARIOS EXTERNOS
    {
        String statement = null;
        String[] parametros = cadenaBD.split("\\|");
        statement = "create table if not exists cruceextSAP" + parametros[4] + " SELECT EXTERNOSSAP.Usuario, EXTERNOSSAP.Nombre_Completo, EXTERNOSSAP.Grupo "
                + "EXTERNOSSAP.Valido_de, EXTERNOSSAP.Validez_a "
                    + "idext" + parametros[4] + ".NUMEROEMPLEADO AS IDNUMEMP, " 
                    + "idext" + parametros[4] + ".USUARIO AS IDUSUARIO, idext" + parametros[4] + ".NOMBRECOMPLETO AS IDNOMBRE, "
                    + "idext" + parametros[4] + ".REGION AS IDREGION, idext" + parametros[4] + ".GERENCIA AS IDGERENCIA, "
                    + "idext" + parametros[4] + ".DEPARTAMENTO AS IDDEPARTAMENTO, "
                    + "idext" + parametros[4] + ".PUESTO AS IDPUESTO, idext" + parametros[4] + ".IDPUESTO AS IDIDPUESTO, "                    
                    + "idext" + parametros[4] + ".ESTATUS AS IDESTATUS, idext" + parametros[4] + ".FECHA AS IDFECHA  "
                    + "FROM EXTERNOSSAP left join idext" + parametros[4]
                    + " on EXTERNOSSAP.Usuario=idext" + parametros[4] + ".NUMEROEMPLEADO ";
            
        return statement;
    }
    
    
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
    
}
