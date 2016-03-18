/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MonAD;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author VS3XXBD
 */
//public class Conexion 
//{
//    Connection conexion;
//    public  Connection Abrir(String cadenaBD)
//    {
//        try
//        {
//            // Se registra el Driver de MySQL
//            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());            
//            String[] parametros = cadenaBD.split("\\|");
//            
//            // Se obtiene una conexión con la base de datos. 
//            conexion = DriverManager.getConnection ("jdbc:mysql://localhost/"+parametros[0]+"",parametros[1],parametros[2]);
//            
//            
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//            conexion = null;
//        }
//        
//        finally
//        {
//            return conexion;
//        }
//    
//    }
//    
//    public  void Cerrar(Connection conex)
//    {
//        try
//        {
//            
//            conexion = conex;
//            conex.close();
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//    
//    }
//    
//}
