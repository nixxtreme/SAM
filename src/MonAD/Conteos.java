/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MonAD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author VS3XXBD
 */
public class Conteos 
{
    
    public static String contbint(int reg, String cadenaBD)                     //Devuelve el número de usuarios internos reportados como baja
    {
        String retorno=null;
        int total=0;
        try
        {            
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());        // Se registra el Driver de MySQL         
            String[] parametros = cadenaBD.split("\\|");
            
                                                                                // Se obtiene una conexión con la base de datos. 
            Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/"+parametros[0]+"",parametros[1],parametros[2]);            
            Statement s = conexion.createStatement();                           // Se crea un Statement
            
            
            ResultSet rs = s.executeQuery (MonAD.Consultas.consultabint(reg)); // Se realiza la consulta de usuarios reportados como baja. Los resultados se guardan en el ResultSet rs
            
            while (rs.next())                                                   // Se valida que el resultado no esté vacío
            {
                retorno = rs.getString(1);
//                System.out.println("retorno " + retorno);
            }
            
            total = Integer.parseInt(retorno);                                  //Se obtiene la cifra y se almacena
            System.out.println("Total " + total);
            
            rs = s.executeQuery (MonAD.Consultas.consultanonominaint(reg));    // Se realiza la consulta de usuarios no registrados en nómina. Los resultados se guardan en el ResultSet rs
            
            while (rs.next())                                                   // Se valida que el resultado no esté vacío
            {
                retorno = rs.getString(1);                                      //Se obtiene la cifra y se almacena
            }
            
            total = total + Integer.parseInt(retorno);                          //Se suma el resultado de usuarios reportados como baja y usuarios no registrados en nómina
            
            
            conexion.close();                                                   // Se cierra la conexión con la base de datos.
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    return Integer.toString(total);                                             //Regresa el número total de incidencias

    }
    
    
    public static String contbext(int reg, String cadenaBD)                     //Devuelve el número de usuarios externos reportados como baja
    {
        String retorno=null;
        int total = 0;
        try
        {
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());        // Se registra el Driver de MySQL
            
            String[] parametros = cadenaBD.split("\\|");
            
                                                                                // Se obtiene una conexión con la base de datos. 
            Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/"+parametros[0]+"",parametros[1],parametros[2]);
            
            // Se crea un Statement, para realizar la consulta
            Statement s = conexion.createStatement();
            
            
            ResultSet rs = s.executeQuery (MonAD.Consultas.consultabext(reg)); // Se realiza la consulta de usuarios reportados como baja. Los resultados se guardan en el ResultSet rs
            
            while (rs.next())                                                   // Se valida que el resultado no esté vacío
            {
                retorno = rs.getString(1);                                      //Se obtiene la cifra y se almacena
            }
            
            total = Integer.parseInt(retorno);            
            rs = s.executeQuery (MonAD.Consultas.consultanonominaext(reg));    // Se realiza la consulta de usuarios no registrados en nómina. Los resultados se guardan en el ResultSet rs
            
            while (rs.next())                                                   // Se valida que el resultado no esté vacío
            {
                retorno = rs.getString(1);                                      //Se obtiene la cifra y se almacena
            }
            
            total = total + Integer.parseInt(retorno);                          //Se suma el resultado de usuarios reportados como baja y usuarios no registrados en nómina
            
            // Se cierra la conexión con la base de datos.
            conexion.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    return Integer.toString(total);                                             //Regresa el número total de incidencias

    }
    
    
    public static String contnologext(int reg, String cadenaBD)                 //Devuelve el número de usuarios externos sin fecha de login y con fecha de creación mayor a 60 días
    {
        String retorno=null;
        try
        {
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());        // Se registra el Driver de MySQL
            
            String[] parametros = cadenaBD.split("\\|");
            
                                                                                // Se obtiene una conexión con la base de datos. 
            Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/"+parametros[0]+"",parametros[1],parametros[2]);
            
            Statement s = conexion.createStatement();                           // Se crea un Statement
            
            
            ResultSet rs = s.executeQuery (MonAD.Consultas.consultanologext(reg));     //Se ejecuta el método para obtener el conteo
            
            while (rs.next())                                                   // Se valida que el resultado no esté vacío
            {
                retorno = rs.getString(1);                                      //Obtiene el resultado y lo almacena
            }
            
            
            conexion.close();                                                   // Se cierra la conexión con la base de datos.
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    return retorno;                                                             //Regresa el número total de incidencias

    }
    
    
    public static String contnologint(int reg, String cadenaBD)                 //Devuelve el número de usuarios internos sin fecha de login y con fecha de creación mayor a 60 días
    {
        String retorno=null;
        try
        {
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());        // Se registra el Driver de MySQL
            
            String[] parametros = cadenaBD.split("\\|");
            
                                                                                // Se obtiene una conexión con la base de datos. 
            Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/"+parametros[0]+"",parametros[1],parametros[2]);
            
            Statement s = conexion.createStatement();                           // Se crea un Statement, para realizar la consulta
            
            ResultSet rs = s.executeQuery (MonAD.Consultas.consultanologint(reg)); //Se ejecuta el método para obtener el conteo
            
            
            while (rs.next())                                                   // Se valida que el resultado no esté vacío
            {
                retorno = rs.getString(1);                                      //Obtiene el resultado y lo almacena
            }
            
            // Se cierra la conexión con la base de datos.
            conexion.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    return retorno;                                                             //Regresa el número total de incidencias

    }
    
    
    public static String continacint(int reg, String cadenaBD)                  //Devuelve el número de usuarios internos inactivos
    {
        String retorno=null;
        try
        {
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());        // Se registra el Driver de MySQL
            
            String[] parametros = cadenaBD.split("\\|");
            
                                                                                // Se obtiene una conexión con la base de datos. 
            Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/"+parametros[0]+"",parametros[1],parametros[2]);
            Statement s = conexion.createStatement();                           // Se crea un Statement, para realizar la consulta
            
            ResultSet rs = s.executeQuery (MonAD.Consultas.consultainacint(reg));      //Se ejecuta el método para obtener el conteo
            
            while (rs.next())                                                   // Se valida que el resultado no esté vacío
            {
                retorno = rs.getString(1);                                      //Obtiene el resultado y lo almacena
            }
            
            conexion.close();                                                   // Se cierra la conexión con la base de datos.
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    return retorno;                                                             //Regresa el número total de incidencias

    }
    
    
    public static String continacext(int reg, String cadenaBD)                  //Devuelve el número de usuarios externos inactivos
    {
        String retorno=null;
        try
        {
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());        // Se registra el Driver de MySQL
            
            String[] parametros = cadenaBD.split("\\|");
            
                                                                                // Se obtiene una conexión con la base de datos. 
            Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/"+parametros[0]+"",parametros[1],parametros[2]);
            
            Statement s = conexion.createStatement();                           // Se crea un Statement
            
            ResultSet rs = s.executeQuery (MonAD.Consultas.consultainacext(reg));      //Se ejecuta el método para obtener el conteo
            
            while (rs.next())                                                   // Se valida que el resultado no esté vacío
            {
                retorno = rs.getString(1);                                      //Obtiene el resultado y lo almacena
            }
            
            conexion.close();                                                   // Se cierra la conexión con la base de datos.
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    return retorno;                                                             //Regresa el número total de incidencias

    }
    
    
    public static String contdupxnomint(int reg, String cadenaBD)               //Devuelve el número de usuarios internos duplicados por nombre
    {
        String retorno=null;
        try
        {
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());        // Se registra el Driver de MySQL
            
            String[] parametros = cadenaBD.split("\\|");
            
                                                                                // Se obtiene una conexión con la base de datos. 
            Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/"+parametros[0]+"",parametros[1],parametros[2]);
            
            Statement s = conexion.createStatement();                           // Se crea un Statement
            
            ResultSet rs = s.executeQuery (MonAD.Consultas.consultadupxnomint(reg));   //Se ejecuta el método para obtener el conteo
            
            while (rs.next())                                                   // Se valida que el resultado no esté vacío
            {
                retorno = rs.getString(1);                                      //Obtiene el resultado y lo almacena
            }
            
            conexion.close();                                                   // Se cierra la conexión con la base de datos.
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    return retorno;                                                             //Regresa el número total de incidencias

    }
    
    
    public static String contdupxnomext(int reg, String cadenaBD)               //Devuelve el número de usuarios externos duplicados por nombre
    {
        String retorno=null;
        try
        {
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());        // Se registra el Driver de MySQL
            
            String[] parametros = cadenaBD.split("\\|");
            
                                                                                // Se obtiene una conexión con la base de datos. 
            Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/"+parametros[0]+"",parametros[1],parametros[2]);
            
            Statement s = conexion.createStatement();                           // Se crea un Statement
            
            ResultSet rs = s.executeQuery (MonAD.Consultas.consultadupxnomext(reg));   //Se ejecuta el método para obtener el conteo
            
            while (rs.next())                                                   // Se valida que el resultado no esté vacío
            {
                retorno = rs.getString(1);                                      //Obtiene el resultado y lo almacena
            }
            
            conexion.close();                                                   // Se cierra la conexión con la base de datos.
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    return retorno;                                                             //Regresa el número total de incidencias

    }
    
    
    
    
    public static String contusrincint(int reg, String cadenaBD)                //Devuelve el número de usuarios internos con UserID incorrecto
    {
        String retorno=null;
        try
        {
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());        // Se registra el Driver de MySQL
            
            String[] parametros = cadenaBD.split("\\|");
            
                                                                                // Se obtiene una conexión con la base de datos. 
            Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/"+parametros[0]+"",parametros[1],parametros[2]);
            
            Statement s = conexion.createStatement();                           // Se crea un Statement
            

            ResultSet rs = s.executeQuery (MonAD.Consultas.consultausrincint(reg));    //Se ejecuta el método para obtener el conteo
            
            while (rs.next())                                                   // Se valida que el resultado no esté vacío
            {
                retorno = rs.getString(1);                                      //Obtiene el resultado y lo almacena
            }
            
            conexion.close();                                                   // Se cierra la conexión con la base de datos.
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    return retorno;                                                             //Regresa el número total de incidencias

    }
    
    
    public static String contusrincext(int reg, String cadenaBD)                //Devuelve el número de usuarios externos con UserID incorrecto
    {
        String retorno=null;
        try
        {
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());        // Se registra el Driver de MySQL
                
            String[] parametros = cadenaBD.split("\\|");
            
                                                                                // Se obtiene una conexión con la base de datos. 
            Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/"+parametros[0]+"",parametros[1],parametros[2]);
            
            Statement s = conexion.createStatement();                           // Se crea un Statement
            
            ResultSet rs = s.executeQuery (MonAD.Consultas.consultausrincext(reg));    //Se ejecuta el método para obtener el conteo
            
            while (rs.next())                                                   // Se valida que el resultado no esté vacío
            {
                retorno = rs.getString(1);                                      //Obtiene el resultado y lo almacena
            }
            
            conexion.close();                                                   // Se cierra la conexión con la base de datos.
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    return retorno;                                                             //Regresa el número total de incidencias

    }
    
    
    public static String contdupxnumint(int reg, String cadenaBD)               //Devuelve el número de usuarios internos duplicados por número de empleado
    {
        String retorno=null;
        try
        {
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());        // Se registra el Driver de MySQL
            
            String[] parametros = cadenaBD.split("\\|");
            
                                                                                // Se obtiene una conexión con la base de datos. 
            Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/"+parametros[0]+"",parametros[1],parametros[2]);
            
            Statement s = conexion.createStatement();                           // Se crea un Statement
            
            ResultSet rs = s.executeQuery (MonAD.Consultas.consultadupxnumint(reg));       //Se ejecuta el método para obtener el conteo
            
            while (rs.next())                                                   // Se valida que el resultado no esté vacío
            {
                retorno = rs.getString(1);                                      //Obtiene el resultado y lo almacena
            }
            
            conexion.close();                                                   // Se cierra la conexión con la base de datos.
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    return retorno;                                                             //Regresa el número total de incidencias

    }
    
    
    public static String contdupxnumext(int reg, String cadenaBD)               //Devuelve el número de usuarios externos duplicados por número de empleado
    {
        String retorno=null;
        try
        {
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());        // Se registra el Driver de MySQL
            
            String[] parametros = cadenaBD.split("\\|");
            
                                                                                // Se obtiene una conexión con la base de datos. 
            Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/"+parametros[0]+"",parametros[1],parametros[2]);
            
            Statement s = conexion.createStatement();                           // Se crea un Statement
            
            ResultSet rs = s.executeQuery (MonAD.Consultas.consultadupxnumext(reg));   //Se ejecuta el método para obtener el conteo
            
            while (rs.next())                                                   // Se valida que el resultado no esté vacío
            {
                retorno = rs.getString(1);                                      //Obtiene el resultado y lo almacena
            }
            
            conexion.close();                                                   // Se cierra la conexión con la base de datos.
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    return retorno;                                                             //Regresa el número total de incidencias

    }
    
    
    public static String contdupxusrint(int reg, String cadenaBD)               //Devuelve el número de usuarios internos duplicados por UserID
    {
        String retorno=null;
        try
        {
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());        // Se registra el Driver de MySQL
            
            String[] parametros = cadenaBD.split("\\|");
            
                                                                                // Se obtiene una conexión con la base de datos. 
            Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/"+parametros[0]+"",parametros[1],parametros[2]);
            
            Statement s = conexion.createStatement();                           // Se crea un Statement
            
            ResultSet rs = s.executeQuery (MonAD.Consultas.consultadupxusrint(reg));   //Se ejecuta el método para obtener el conteo
            
            while (rs.next())                                                   // Se valida que el resultado no esté vacío
            {
                retorno = rs.getString(1);                                      //Obtiene el resultado y lo almacena
            }
            
            conexion.close();                                                   // Se cierra la conexión con la base de datos.
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    return retorno;                                                             //Regresa el número total de incidencias

    }
    
    
    public static String contdupxusrext(int reg, String cadenaBD)               //Devuelve el número de usuarios externos duplicados por UserID
    {
        String retorno=null;
        try
        {
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());        // Se registra el Driver de MySQL
            
            String[] parametros = cadenaBD.split("\\|");
            
                                                                                // Se obtiene una conexión con la base de datos. 
            Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/"+parametros[0]+"",parametros[1],parametros[2]);
            
            Statement s = conexion.createStatement();                           // Se crea un Statement
            
            ResultSet rs = s.executeQuery (MonAD.Consultas.consultadupxusrext(reg));   //Se ejecuta el método para obtener el conteo
            
            while (rs.next())                                                   // Se valida que el resultado no esté vacío
            {
                retorno = rs.getString(1);                                      //Obtiene el resultado y lo almacena
            }
            
            conexion.close();                                                   // Se cierra la conexión con la base de datos.
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    return retorno;                                                             //Regresa el número total de incidencias

    }
    
    
    public static String total(int reg, String cadenaBD)                        //Devuelve el número de usuarios totales
    {
        String retorno=null;
        try
        {
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());        // Se registra el Driver de MySQL
            
            String[] parametros = cadenaBD.split("\\|");
            String base = "jdbc:mysql://localhost/"+parametros[0];
            
                                                                                // Se obtiene una conexión con la base de datos. 
            Connection conexion = DriverManager.getConnection (base, parametros[1], parametros[2]);
            
            Statement s = conexion.createStatement();                           // Se crea un Statement
            
            ResultSet rs = s.executeQuery ("select count(*) from r" + Integer.toString(reg) + " where Account_disabled = false");   //Se ejecuta el método para obtener el conteo
            
            while (rs.next())                                                   // Se valida que el resultado no esté vacío
            {
                retorno = rs.getString(1);                                      //Obtiene el resultado y lo almacena
            }
            
            conexion.close();                                                   // Se cierra la conexión con la base de datos.
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    return retorno;                                                             //Regresa el número total de incidencias

    }
    
    
    public static String externos(int reg, String cadenaBD)                     //Devuelve el número de usuarios externos
    {
        String retorno=null;
        try
        {
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());        // Se registra el Driver de MySQL
            
            String[] parametros = cadenaBD.split("\\|");
            
                                                                                // Se obtiene una conexión con la base de datos. 
            Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/"+parametros[0]+"",parametros[1],parametros[2]);     
            
            Statement s = conexion.createStatement();                           // Se crea un Statement
            
            // Se realiza la consulta. Los resultados se guardan en el ResultSet rs
            String consulta;
            consulta=MonAD.Consultas.consultaext(reg);
            //System.out.println(consulta);
            ResultSet rs = s.executeQuery (consulta);                           //Se ejecuta el método para obtener el conteo           
            
            while (rs.next())                                                   // Se valida que el resultado no esté vacío
            {
                retorno = rs.getString(1);                                      //Obtiene el resultado y lo almacena
            }
            
            conexion.close();                                                   // Se cierra la conexión con la base de datos.
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    return retorno;                                                             //Regresa el número total de incidencias

    }
    
    
    public static String genericos(int reg, String cadenaBD)                    //Devuelve el número de usuarios genéricos
    {
        String retorno=null;
        try
        {
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());        // Se registra el Driver de MySQL
            
            String[] parametros = cadenaBD.split("\\|");
            
                                                                                // Se obtiene una conexión con la base de datos. 
            Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/"+parametros[0]+"",parametros[1],parametros[2]);
            
            Statement s = conexion.createStatement();                           // Se crea un Statement
            
            // Se realiza la consulta. Los resultados se guardan en el ResultSet rs
            //ResultSet rs = s.executeQuery ("select count(*) from r" + Integer.toString(reg) + "WHERE  Description like '%gen%'");
            
            ResultSet rs = s.executeQuery (MonAD.Consultas.consultagen(reg));      //Se ejecuta el método para obtener el conteo
            while (rs.next())                                                   // Se valida que el resultado no esté vacío
            {
                retorno = rs.getString(1);                                      //Obtiene el resultado y lo almacena
            }
            
            conexion.close();                                                   // Se cierra la conexión con la base de datos.
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    return retorno;                                                             //Regresa el número total de incidencias
    
    }
    
    
    
    
    public static String internos(int reg, String cadenaBD)                     //Devuelve el número de usuarios internos
    {
        String retorno=null;
        try
        {
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());        // Se registra el Driver de MySQL
            
            String[] parametros = cadenaBD.split("\\|");
            
                                                                                // Se obtiene una conexión con la base de datos. 
            Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/"+parametros[0]+"",parametros[1],parametros[2]);
            
            Statement s = conexion.createStatement();                           // Se crea un Statement
            
            ResultSet rs = s.executeQuery (MonAD.Consultas.consultaint(reg));      //Se ejecuta el método para obtener el conteo
            
            while (rs.next())                                                   // Se valida que el resultado no esté vacío
            {
                retorno = rs.getString(1);                                      //Obtiene el resultado y lo almacena
            }
            
            
            conexion.close();                                                   // Se cierra la conexión con la base de datos.
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    return retorno;                                                             //Regresa el número total de incidencias

    }
    
}
