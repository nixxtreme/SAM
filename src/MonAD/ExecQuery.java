/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MonAD;

import MonPac.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author VS3XXBD
 */
public class ExecQuery 
{
    Statement Stm;
    CallableStatement Cstm;
    ResultSet rs;
    
    public ExecQuery()
    {
        Stm = null;
        Cstm = null;
        rs = null;
    }
    
    public ArrayList<String> Exect(Connection con, ArrayList<String> querys)                 //Recibe una conexión y un arreglo con instrucciones y ejecuta cada una de ellas   
    {
        ArrayList<String> Conteos = new ArrayList(); 
        try
        {
            Stm = con.createStatement();                                        //Crea la sentencia
            int Rs2;
            String fecha;
            String total, internos, externos, genericos, transfer;
            
            
            Monitoreos.Conteos conteo = new Monitoreos.Conteos();
            for(int i = 0; i<querys.size(); i++)                                //Recorre los elementos del arreglo
            {
                System.out.println(querys.get(i));                              //Imprime en consola cada sentencia
                if(querys.get(i).contains("Total"))
                {
                    String[] parametros = querys.get(i).split("\\|");
                    fecha = parametros[1];
                    total = conteo.conteo(con, "USUARIOSSAP" + fecha);//Cuenta el total de usuarios
                    System.out.println("                TOTAL: " + total);
                    Conteos.add(total);
                }
                else
                {
                    if(querys.get(i).equals("Internos"))
                    {
                        internos = conteo.conteo(con, "INTERNOSSAP");     //Cuenta el total de usuarios internos
                        Conteos.add(internos);
                    }
                    else
                    {
                        if(querys.get(i).equals("Externos"))
                        {
                            externos = conteo.conteo(con, "EXTERNOSSAP");     //Cuenta el total de usuarios externos
                            transfer = conteo.conteo(con, "TRANSFERSAP");     //Cuenta el total de usuarios 
                            int tra, ext;
                            tra = Integer.parseInt(transfer);
                            ext = Integer.parseInt(externos);
                            ext = tra + ext;
                            externos = "" + ext + "";
                            Conteos.add(externos);
                        }
                        else
                        {
                            if(querys.get(i).equals("Genericos"))
                            {
                                genericos = conteo.conteo(con, "GENERICOSSAP");//Cuenta el total de usuarios
                                Conteos.add(genericos);
                            }
                            else
                            {
                                Rs2 = Stm.executeUpdate(querys.get(i));                         //Ejecuta la sentencia
                            }

                        }


                    }
                }
                
                
            }
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            return Conteos;
        }
        
        
    }
    
    public void Exect( Connection con, String query)                            //Recibe una conexión y una instrucción y la ejecuta
    {
        try
        {
            Stm = con.createStatement();                                        //Crea ima semtemcoa
            int Rs2;
            
            Rs2 = Stm.executeUpdate(query);                                     //Ejecuta la instrucción
            
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        
    }
    
    
    public void CExect(String cadenaBD, Connection con, ArrayList<String> querys)   //Recibe una conexión y un arreglo de instrucciones para ejecutarlas
    {
        try
        {
            Cstm = con.prepareCall(querys.get(0));                              //Se crea la sentencia
            int Rs2;
            for(int i = 0; i<querys.size(); i++)                                //Se recorre el arreglo
            {
                System.out.println(querys.get(i));                              //Se imprime en consola cada sentencia
                Rs2 = Cstm.executeUpdate(querys.get(i));                        //Se ejecutan las sentencias
            }
            System.out.println("Finalizando ejecución de procedimientos");
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        
    }
    
    public void CExect( Connection con, String query)                           //Recibe una sentencia y la ejecuta
    {
        System.out.println(query);
        try
        {
            System.out.println(query);                                          //Imprime la sentencia
            Cstm = con.prepareCall(query);                                      //Crea la sentencia
            int Rs2;
            
            Rs2 = Cstm.executeUpdate(query);                                    //Ejecuta la sentencia
            
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        
    }
    
    
    
    public ResultSet Cons(Connection con, String query)                         //Recibe una sentencia y la ejecuta     
    {
        ResultSet Rs = null;
        try
        {
            Stm = con.createStatement();                                        //Crea la sentencia
            Rs = Stm.executeQuery(query);                                       //Ejecuta la sentencia
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            return Rs;
        }
    }
    
}
