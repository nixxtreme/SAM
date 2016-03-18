/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import MonAD.ActiveDirectory;

/**
 *
 * @author YOSH
 */
public class Conexion {
    
    public Connection obtineConexion() throws SQLException{
    
        ActiveDirectory activeD = new ActiveDirectory();
            
        String base = "jdbc:mysql://localhost/adjunio";
        String usuario = activeD.obtieneUsuario();
        String contrasena = activeD.obtieneContrasena();

        // Se obtiene una conexión con la base de datos. 
        Connection conexion = DriverManager.getConnection (base, usuario, contrasena);
        
        return conexion;
        
    }
    
}
