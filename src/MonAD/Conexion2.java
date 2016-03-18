package MonAD;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author VS3XXBD
 */
public class Conexion2
{
    Connection conexion;
    public Conexion2()
    {
        conexion = null;
    }
    
    public  void AbrirUsuarios(String cadenaBD)
    {
        try
        {
            // Se registra el Driver de MySQL
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());            
            String[] parametros = cadenaBD.split("\\|");
            
            // Se obtiene una conexión con la base de datos. 
            conexion = DriverManager.getConnection ("jdbc:" + parametros[7],parametros[5],parametros[6]);
            
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
            conexion = null;
        }
        
        
    
    }
    
    
    public  void AbrirLocal(String cadenaBD)
    {
        try
        {
            // Se registra el Driver de MySQL
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());            
            String[] parametros = cadenaBD.split("\\|");
            System.out.println(cadenaBD);
            // Se obtiene una conexión con la base de datos. 
            conexion = DriverManager.getConnection ("jdbc:mysql://localhost/"+parametros[0]+"?zeroDateTimeBehavior=convertToNull"+"",parametros[1],parametros[2]);
            
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
            conexion = null;
        }
        
        
    
    }
    
    public  void Cerrar()
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