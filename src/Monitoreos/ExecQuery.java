package Monitoreos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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
    Statement Stm;                                                              //Elementos de la clase
    CallableStatement Cstm;
    ResultSet rs;
    
    public ExecQuery()                                                          //Constructor de la clase
    {
        Stm = null;
        Cstm = null;
        rs = null;
    }
    
    public void Exect(Connection con, ArrayList<String> querys)                 //Ejecuta un conjunto de instrucciones
    {
        try
        {
            Stm = con.createStatement();
            int Rs2;
            for(int i = 0; i<querys.size(); i++)                                //Recorre el arreglo con las instrucciones y ejecuta una a una
            {
                System.out.println(querys.get(i));                              
                Rs2 = Stm.executeUpdate(querys.get(i));
            }
//            System.out.println("Finalizando ejecución de procedimientos");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        
    }
    
    public void Exect( Connection con, String query)                            //Ejecuta una instruccion
    {
//        System.out.println(query);
        try
        {
            Stm = con.createStatement();                                        //Prepara la sentencia
            int Rs2;
            
            Rs2 = Stm.executeUpdate(query);                                     //Ejecuta la instrucción
            
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        
    }
    
    
    public void CExect(String cadenaBD, Connection con, ArrayList<String> querys)//Ejecuta un conjunto de instrucciones incluyendo llamadas a stored procedures
    {
        try
        {
            Cstm = con.prepareCall(querys.get(0));
            int Rs2;
            for(int i = 0; i<querys.size(); i++)                                //Recorre cada instrucción en el arreglo y la ejecuta
            {
                System.out.println(querys.get(i));
                Rs2 = Cstm.executeUpdate(querys.get(i));
            }
            System.out.println("Finalizando ejecución de procedimientos");      //Inica que ya se ejecutaron todas las instrucciones en el arreglo
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        
    }
    
    public void CExect( Connection con, String query)                           //Ejecuta una instruccion   
    {
        try
        {
            System.out.println(query);
            Cstm = con.prepareCall(query);                                      //Prepara la sentencia
            int Rs2;
            
            Rs2 = Cstm.executeUpdate(query);                                    //Ejecuta la instrucción
            
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        
    }
    
    
    
    public ResultSet Cons(Connection con, String query)                         //Ejecuta una consulta
    {
        ResultSet Rs = null;
        try
        {
            Stm = con.createStatement();                                        //Prepara la sentencia
            Rs = Stm.executeQuery(query);                                       //Ejecuta el query
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            return Rs;                                                          //Devuelve el ResultSet
        }
    }
    
}
