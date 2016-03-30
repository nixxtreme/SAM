package Monitoreos;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author VS3XXBD
 */
public class Conexion 
{
    public Connection conexion;
    public Conexion()                                                           //Constructor de la clase
    {
        conexion = null;
    }
    
    public  void AbrirUsuarios(String cadenaBD)                                 //Abre la conexión de base de datos del aplicativo
    {
        try
        {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());            
            String[] parametros = cadenaBD.split("\\|");                        //Se obtienen los datos de cadenaBD y se almacenan en un arreglo                     
//            System.out.println("Usuarios   jdbc:" + parametros[7] + parametros[5] + parametros[6]);
            conexion = DriverManager.getConnection ("jdbc:" + parametros[7],parametros[5],parametros[6]);
                        
        }
        catch (Exception e)
        {
            e.printStackTrace();
            conexion = null;
        }
        
        
    
    }
    
    
    public  void AbrirLocal(String cadenaBD)                                    //Abre la conexión de base de datos local
    {
        System.out.println("Cadena " + cadenaBD);
        try
        {
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());        //Registro de driver
            String[] parametros = cadenaBD.split("\\|");            
//            System.out.println("Local      jdbc:mysql://localhost/" +parametros[0]+ "?zeroDateTimeBehavior=convertToNull" + parametros[1] + parametros[2]);            
            conexion = DriverManager.getConnection ("jdbc:mysql://localhost/"+parametros[0]+"?zeroDateTimeBehavior=convertToNull"+"",parametros[1],parametros[2]);  //Establece la conexión
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
            conexion = null;
        }
    }
    
    public  void Cerrar()                                                       //Cierra la conexión a base de datos de la aplicación o local
    {
        try
        {
            conexion.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    
    }
    
}