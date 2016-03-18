/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Monitoreos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

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
   
   public static void eliminaTablaUsrAdmin(String cadenaBD)                         //ELIMINA LA TABLA DE NÓMINA INTERNOS
    {
        String[] parametros = cadenaBD.split("\\|");
        

        
        try
        {
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());
            
            // Se obtiene una conexión con la base de datos. 
            Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/"+parametros[0]+"",parametros[1],parametros[2]);
            
            Statement cstmt2 = conexion.createStatement();  

            int rs2 = cstmt2.executeUpdate("DROP TABLE IF EXISTS `UsrAdmin"+ parametros[4] + "`");
            
            // Se cierra la conexión con la base de datos.
            conexion.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
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
   
   public static void idCreaUsrAdmin(String cadenaBD)                          //CREA LA TABLA DE NÓMINA EXTERNOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String tabla = "CREATE TABLE IF NOT EXISTS usradmin"+ parametros[4] + " (USUARIO VARCHAR(25) NOT NULL, NOMBRE VARCHAR(45), APELLIDO VARCHAR(43), "
        + "ROL VARCHAR(25) , VALOR_AUTORIZACION VARCHAR(8), PRIMARY KEY (USUARIO))";
        
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
    
    public static void UsrAdminTabla(String usuarios, String cadenaBD)             //INSERTA LOS REGISTROS A LA TABLA DE NÓMINA INTERNOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String mes = parametros[3];
        
        String valores = "INSERT IGNORE INTO usradmin" + parametros[4] + " (USUARIO, NOMBRE, APELLIDO, ROL, VALOR_AUTORIZACION) "
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
    public static String TablaInt(String cadenaBD)                             //Crea la tabla con las insidencias de usuarios administradores agregados en el mes actual
    {
        String statement = null;
        String[] parametros = cadenaBD.split("\\|");
        statement = "create table if not exists  SELECT Usuario, Nombre, Apellido, Rol, Valor_Autorizacion FROM usradmin" + parametros[5]
                    + " WHERE usuario NOT IN (SELECT usuario FROM usradmin" + parametros[4] + ")";
        return statement;
    }
    
    public static String TablaExt(String cadenaBD)                             //Crea la tabla con las insidencias de usuarios administradores agregados en el mes actual
    {
        String statement = null;
        String[] parametros = cadenaBD.split("\\|");
        statement = "create table if not exists eliminados SELECT Usuario, Nombre, Apellido, Rol, Valor_Autorizacion FROM usradmin" + parametros[5]
                    + " WHERE usuario NOT IN (SELECT usuario FROM usradmin" + parametros[4] + ")";
        return statement;
    }
    
    public static String TablaGen(String cadenaBD)                             //Crea la tabla con las insidencias de usuarios administradores agregados en el mes actual
    {
        String statement = null;
        String[] parametros = cadenaBD.split("\\|");
        statement = "create table if not exists eliminados SELECT Usuario, Nombre, Apellido, Rol, Valor_Autorizacion FROM usradmin" + parametros[5]
                    + " WHERE usuario NOT IN (SELECT usuario FROM usradmin" + parametros[4] + ")";
        return statement;
    }
    
    
}
