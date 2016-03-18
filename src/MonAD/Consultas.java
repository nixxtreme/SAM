/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MonAD;

/**
 *
 * @author VS3XXBD
 */
public class Consultas 
{
    public static String consultagen(int reg)                                   //Devuelve la sentencia para obtener el número de usuarios genéricos
    {
        return "select count(*) from genericos" + Integer.toString(reg);
        
    }
    
    public static String consultadupxusrint(int reg)                            //Devuelve la sentencia para obtener el número de usuarios internos duplicados por UserID
    {
        return "select count(*) from dupxusrint" + Integer.toString(reg);
        
    }
    
    public static String consultadupxusrext(int reg)                            //Devuelve la sentencia para obtener el número de usuarios externos duplicados por UserID
    {
        return "select count(*) from dupxusrext" + Integer.toString(reg);
        
    }
    
    public static String consultadupxnomint(int reg)                            //Devuelve la sentencia para obtener el número de usuarios internos duplicados por nombre
    {
        return "select count(*) from dupxnombreint" + Integer.toString(reg);
        
    }
    
    public static String consultadupxnomext(int reg)                            //Devuelve la sentencia para obtener el número de usuarios externos duplicados por nombre
    {
        return "select count(*) from dupxnombreext" + Integer.toString(reg);
        
    }
    
    public static String consultadupxnumint(int reg)                            //Devuelve la sentencia para obtener el número de usuarios internos duplicados por numero de empleado
    {
        return "select count(*) from dupxnumint" + Integer.toString(reg);
        
    }
    
    public static String consultadupxnumext(int reg)                            //Devuelve la sentencia para obtener el número de usuarios externos duplicados por numero de empleado
    {
        return "select count(*) from dupxnumext" + Integer.toString(reg);
        
    }
    
    public static String consultausrincint(int reg)                             //Devuelve la sentencia para obtener el número de usuarios internos con UserId incorrecto
    {
        return "select count(*) from usrincint" + Integer.toString(reg);
        
    }
    
    public static String consultausrincext(int reg)                             //Devuelve la sentencia para obtener el número de usuarios externos con UserId incorrecto
    {
        return "select count(*) from usrincext" + Integer.toString(reg);
        
    }
    
    public static String consultaint(int reg)                                   //Devuelve la sentencia para obtener el número de usuarios internos
    {
        return "select count(*) from internos" + Integer.toString(reg);

    }
    
    public static String consultabint(int reg)                                  //Devuelve la sentencia para obtener el número de usuarios internos reportados como baja
    {
        return "select count(*) from bajasint" + Integer.toString(reg);

    }
    
    public static String consultaext(int reg)                                   //Devuelve la sentencia para obtener el número de usuarios extenros
    {
        return "select count(*) from externos" + Integer.toString(reg); 

    }
    
    public static String consultabext(int reg)                                  //Devuelve la sentencia para obtener el número de usuarios externos reportados como baja
    {
        return "select count(*) from bajasext" + Integer.toString(reg); 

    }
    
    public static String consultainacext(int reg)                               //Devuelve la sentencia para obtener el número de usuarios extenrnos inactivos
    {
        return "select count(*) from inactividadext" + Integer.toString(reg); 

    }
    
    public static String consultainacint(int reg)                               //Devuelve la sentencia para obtener el número de usuarios intenrnos inactivos
    {
        return "select count(*) from inactividadint" + Integer.toString(reg); 

    }
    
    public static String consultanologint(int reg)                              //Devuelve la sentencia para obtener el número de usuarios intenros sin fecha de login
    {
        return "select count(*) from nologinint" + Integer.toString(reg); 

    }
    
    public static String consultanologext(int reg)                              //Devuelve la sentencia para obtener el número de usuarios extenros sin fecha de login
    {
        return "select count(*) from nologinext" + Integer.toString(reg); 

    }
    
    public static String consultanonominaint(int reg)                           //Devuelve la sentencia para obtener el número de usuarios intenros que no se encuentran registrados en nómina
    {
        return "select count(*) from nonominaint" + Integer.toString(reg); 

    }
    
    public static String consultanonominaext(int reg)                           //Devuelve la sentencia para obtener el número de usuarios extenros que no se encuentran registrados en nómina
    {
        return "select count(*) from nonominaext" + Integer.toString(reg); 

    }
    
    public static String ResultadosBajasInt(int reg)                            //Devuelve la sentencia para obtener los usuarios internos reportados como baja
    {
        String statement = "select NUMEMP, USER_NAME, FULL_NAME, LAST_LOGON_TIMESTAMP, IDPUESTO, IDGERENCIA, IDNOMBRE, IDFECHA, IDESTATUS from bajasint" + reg
                + " union select NUMEMP, USER_NAME, FULL_NAME, LAST_LOGON_TIMESTAMP, IDPUESTO, IDGERENCIA, IDNOMBRE, IDFECHA, IDESTATUS from nonominaint" + reg;
        return statement;
    }
    
    public static String ResultadosBajasExt(int reg)                            //Devuelve la sentencia para obtener los usuarios externos reportados como baja
    {
        String statement = "select NUMEMP, USER_NAME, FULL_NAME, LAST_LOGON_TIMESTAMP, IDPUESTO, IDGERENCIA, IDNOMBRE, IDFECHA, IDESTATUS, IDUSUARIO from bajasext" + reg
                + " union select NUMEMP, USER_NAME, FULL_NAME, LAST_LOGON_TIMESTAMP, IDPUESTO, IDGERENCIA, IDNOMBRE, IDFECHA, IDESTATUS, IDUSUARIO from nonominaext" + reg;
        return statement;
    }
    
    public static String ResultadosUsrInt(int reg)                              //Devuelve la sentencia para obtener los usuarios internos con UserID incorrecto
    {
        String statement = "select NUMEMP, FULL_NAME, USER_NAME, IDUSUARIO, LAST_LOGON_TIMESTAMP, IDNOMBRE, IDPUESTO, IDGERENCIA, IDESTATUS from usrincint" + reg;
        return statement;
    }
    
    public static String ResultadosUsrExt(int reg)                              //Devuelve la sentencia para obtener los usuarios externos con UserID incorrecto
    {
        String statement = "select NUMEMP, FULL_NAME, USER_NAME, IDUSUARIO, LAST_LOGON_TIMESTAMP, IDNOMBRE, IDPUESTO, IDGERENCIA, IDESTATUS from usrincext" + reg;
        return statement;
    }
    
    public static String ResultadosInacInt(int reg)                             //Devuelve la sentencia para obtener los usuarios internos inactivos
    {
        String statement = "select NUMEMP, USER_NAME, FULL_NAME, LAST_LOGON_TIMESTAMP, IDNOMBRE, IDPUESTO, IDGERENCIA, IDESTATUS from inactividadint" + reg
                + " union select NUMEMP, USER_NAME, FULL_NAME, LAST_LOGON_TIMESTAMP, IDNOMBRE, IDPUESTO, IDGERENCIA, IDESTATUS from nologinint" + reg;
        return statement;
    }
    
    public static String ResultadosInacExt(int reg)                             //Devuelve la sentencia para obtener los usuarios internos inactivos
    {
        String statement = "select NUMEMP, USER_NAME, FULL_NAME, LAST_LOGON_TIMESTAMP, IDNOMBRE, IDPUESTO, IDGERENCIA, IDESTATUS from inactividadext" + reg
                + " union select NUMEMP, USER_NAME, FULL_NAME, LAST_LOGON_TIMESTAMP, IDNOMBRE, IDPUESTO, IDGERENCIA, IDESTATUS from inactividadext" + reg;
        return statement;
    }
    
    public static String ResultadosDupxnomInt(int reg)                          //Devuelve la sentencia para obtener los usuarios internos duplicados por nombre
    {
        String statement = "select NUMEMP, USER_NAME, FULL_NAME, LAST_LOGON_TIMESTAMP, IDNOMBRE, IDPUESTO, IDGERENCIA, IDESTATUS from dupxnombreint" + reg;
        return statement;
    }
    
    public static String ResultadosDupxnomExt(int reg)                          //Devuelve la sentencia para obtener los usuarios externos duplicados por nombre
    {
        String statement = "select NUMEMP, USER_NAME, FULL_NAME, LAST_LOGON_TIMESTAMP, IDNOMBRE, IDPUESTO, IDGERENCIA, IDESTATUS from dupxnombreext" + reg;
        return statement;
    }
    
    public static String ResultadosDupxnumInt(int reg)                          //Devuelve la sentencia para obtener los usuarios internos duplicados por número de empleado
    {
        String statement = "select NUMEMP, USER_NAME, FULL_NAME, LAST_LOGON_TIMESTAMP, IDNOMBRE, IDPUESTO, IDGERENCIA, IDESTATUS from dupxnumint" + reg;
        return statement;
    }
    
    public static String ResultadosDupxnumExt(int reg)                          //Devuelve la sentencia para obtener los usuarios externos duplicados por número de empleado
    {
        String statement = "select NUMEMP, USER_NAME, FULL_NAME, LAST_LOGON_TIMESTAMP, IDNOMBRE, IDPUESTO, IDGERENCIA, IDESTATUS from dupxnumext" + reg;
        return statement;
    }
    
    public static String ResultadosDupxusrInt(int reg)                          //Devuelve la sentencia para obtener los usuarios internos duplicados por UserID
    {
        String statement = "select NUMEMP, USER_NAME, FULL_NAME, LAST_LOGON_TIMESTAMP, IDNOMBRE, IDPUESTO, IDGERENCIA, IDESTATUS from dupxusrint" + reg;
        return statement;
    }
    
    public static String ResultadosDupxusrExt(int reg)                          //Devuelve la sentencia para obtener los usuarios externos duplicados por UserID
    {
        String statement = "select NUMEMP, USER_NAME, FULL_NAME, LAST_LOGON_TIMESTAMP, IDNOMBRE, IDPUESTO, IDGERENCIA, IDESTATUS from dupxusrext" + reg;
        return statement;
    }
}
