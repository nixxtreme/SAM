
package MonPac;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author VS3XXBD
 */
public class Conteos 
{
    CallableStatement CStm;                                                     //Elementos de la clase
    ResultSet Rs, Rs2;
    
    public Conteos()                                                            //Constructor de la clase
    {
        CStm = null;
        Rs = null;
        Rs2 = null;
    }
    
    public String conteo(Connection con, String tabla)                          //Realiza el conteo de los registros de una tabla
    {
        String conteo = "0";                                                    //Establece la variable en cero
        try
        {
            CStm = con.prepareCall("CALL conteos('" + tabla + "')");            //Define la sentencia de llamada al stored procedure
            Rs = CStm.executeQuery();                                           //Ejecuta la sentencia
            while(Rs.next())                                                    //Valida se haya obtenido un resultado
            {
                conteo = Rs.getString(1);                                       //Obtiene el dato del resultado
            }
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            return conteo;                                                      //Devuelve el resultado
        }
        
    }
    
    public String conteo(Connection con, String tabla1, String tabla2)          //Realiza el conteo de los registros de dos tablas
    {
        String conteo = "";
        int cont = 0;                                                           //Establece la variable en cero
        try
        {
            CStm = con.prepareCall("CALL conteos('" + tabla1 + "')");           //Define la sentencia de llamada al stored procedure
            Rs = CStm.executeQuery();                                           //Ejecuta la sentencia
            while(Rs.next())                                                    //Valida se haya obtenido un resultado
            {
                cont = Rs.getInt(1);                                            //Obtiene el dato del resultado
            }
            CStm = con.prepareCall("CALL conteos('" + tabla2 + "')");           //Define la sentencia de llamada al stored procedure
            Rs = CStm.executeQuery();                                           //Ejecuta la sentencia
            while(Rs.next())                                                    //Valida se haya obtenido un resultado
            {
                cont = cont + Rs.getInt(1);                                     //Obtiene el dato del resultado y lo suma al resultado anterior
            }
            conteo = Integer.toString(cont);                                    //Convierte el int en String para retornarlo
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            return conteo;                                                      //Retorna el valor
        }
    }
    
}
