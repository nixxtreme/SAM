
package Monitoreos;

import Monitoreos.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author VS3XXBD
 */
public class Querys 
{
    public static String Usuarios()                                             //QUERY PARA OBTENER LOS USUARIOS DE LA BASE DE DATOS DEL APLICATIVO
    {
        String consulta = "SELECT E.NUMEMP, E.ID_EMPLEADO AS CLAVEUNIVERSAL, E.NOMBRE, E.APPAT, E.APMAT, E.REGION, E.IP, E.CORREO, E.NUMEMPJEFE AS JEFEINMEDIATO, "
                + "E.ID_ST_EMPLEADO, E.ID_PERFIL, P.NOMBRE_PERFIL AS PERFIL, E.ULTIMAENTRADA, E.FZAVTAPREPAGO, E.FZAVTAPOSTPAGO "
                + "FROM PACAPP.T_PAC_EMPLEADOS E LEFT JOIN PACAPP.C_PAC_PERFILES P ON E.ID_PERFIL = P.ID_PERFIL";
        return consulta;
    }
    
    public static String NoNominaBorraInt(String cadenaBD)                      //ELIMINA LOS USUARIOS INTERNOS QUE NO SE ENCUENTRAN REGISTRADOS EN NÓMINA
    {
        String[] parametros = cadenaBD.split("\\|");
        String consulta = "DELETE FROM cruceint" + parametros[4] + " where IDNUMEMP is null";
                
        return consulta;
    }
    
    public static String NoNominaCreaInt(String cadenaBD)                       //AGREGA UNA TABLA PARA LAS INCIDENCIAS DE USUARIOS INTERNOS QUE NO SE ENCUENTRAN EN NÓMINA
    {
        String[] parametros = cadenaBD.split("\\|");
        String consulta = "create table if not exists nonominaint SELECT * FROM cruceint" + parametros[4] + " where IDNUMEMP is null";
                
        return consulta;
    }
    
    public static String NoNominaBorraExt(String cadenaBD)                      //ELIMINA LOS USUARIOS EXTERNOS QUE NO SE ENCUENTRAN REGISTRADOS EN NÓMINA
    {
        String[] parametros = cadenaBD.split("\\|");
        String consulta = "DELETE FROM cruceext" + parametros[4] + " where idusuario is null";
                
        return consulta;
    }
    
    public static String NoNominaCreaExt(String cadenaBD)                       //AGREGA UNA TABLA PARA LAS INCIDENCIAS DE USUARIOS EXTERNOS QUE NO SE ENCUENTRAN EN NÓMINA
    {
        String[] parametros = cadenaBD.split("\\|");
        String consulta = "create table if not exists nonominaext SELECT * FROM cruceext" + parametros[4] + " where idusuario is null";
                
        return consulta;
    }
    
    public static String CreaBajasExt(String cadenaBD)                          //AGREGA UNA TABLA PARA LAS INCIDENCIAS DE USUARIOS EXTERNOS QUE ESTÁN DADOS DE BAJA
    {
        String[] parametros = cadenaBD.split("\\|");
        String consulta = "create table if not exists baajasext SELECT * FROM cruceext" + parametros[4] + " WHERE IDESTATUS = 'ELIMINADO'";
                
        return consulta;
    }
    
    public static String CreaBajasInt(String cadenaBD)                          //AGREGA UNA TABLA PARA LAS INCIDENCIAS DE USUARIOS INTERNOS QUE ESTÁN DADOS DE BAJA
    {
        String[] parametros = cadenaBD.split("\\|");
        String consulta = "create table if not exists baajasint SELECT * FROM cruceint" + parametros[4] + " WHERE IDESTATUS = 'ELIMINADO'";
                
        return consulta;
    }
    
    public static String BorraBajasExt(String cadenaBD)                         //ELIMINA LOS USUARIOS EXTERNOS QUE ESTÁN REGSTRADOS COMO BAJA
    {
        String[] parametros = cadenaBD.split("\\|");
        String consulta = "DELETE FROM cruceext" + parametros[4] + " WHERE IDESTATUS = 'ELIMINADO'";
                
        return consulta;
    }
    
    public static String BorraBajasInt(String cadenaBD)                         //ELIMINA LOS USUARIOS INTERNOS QUE ESTÁN REGISTRADOS COMO BAJA
    {
        String[] parametros = cadenaBD.split("\\|");
        String consulta = "DELETE FROM cruceint" + parametros[4] + " WHERE IDESTATUS = 'ELIMINADO'";
                
        return consulta;
    }
        
    public static String CreaInactividadExt(String cadenaBD)                    //AGREGA UNA TABLA PARA LAS INCIDENCIAS DE USUARIOS EXTERNOS CON INACTIVIDAD
    {
        String[] parametros = cadenaBD.split("\\|");
        String consulta = "create table if not exists inactividadext SELECT * FROM cruceext" + parametros[4] + " WHERE DATEDIFF('" + parametros[8] + "', FECHA_ACCESO) >= 61";
        return consulta;
    }
    
    public static String CreaInactividadInt(String cadenaBD)                    //AGREGA UNA TABLA PARA LAS INCIDENCIAS DE USUARIOS INTERNOS CON INACTIVIDAD
    {
        String[] parametros = cadenaBD.split("\\|");
        String consulta = "create table if not exists inactividadint SELECT * FROM cruceint" + parametros[4] + " WHERE DATEDIFF('" + parametros[8] + "', FECHA_ACCESO) >= 61";
        return consulta;
    }
    
    public static String BorraInactividadExt(String cadenaBD)                   //ELIMINA LOS USUARIOS EXTERNOS QUE ESTÁN REGISTRADOS COMO INACTIVIDAD
    {
        String[] parametros = cadenaBD.split("\\|");
        String consulta = "DELETE FROM cruceext" + parametros[4] + " WHERE DATEDIFF('" + parametros[8] + "', FECHA_ACCESO) >= 61";
        return consulta;
    }
    
    public static String BorraInactividadInt(String cadenaBD)                   //ELIMINA LOS USUARIOS INTERNOS QUE ESTÁN REGISTRADOS COMO INACTIVIDAD
    {
        String[] parametros = cadenaBD.split("\\|");
        String consulta = "DELETE FROM cruceint" + parametros[4] + " WHERE DATEDIFF('" + parametros[8] + "', FECHA_ACCESO) >= 61";
        return consulta;
    }
    
    public static String CreaUsuarios(String cadenaBD)                          //AGREGA TABLA PARA LOS USUARIOS OBTENIDOS DE LA BASE DE DATOS DEL APLICATIVO
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "CREATE TABLE IF NOT EXISTS Usuarios" + parametros[4] + " (ID int NOT NULL AUTO_INCREMENT, NUM_EMP VARCHAR(45), USER_NAME VARCHAR(45), "
                + "NOMBRE VARCHAR(255), REGION INT,"
                + "IP VARCHAR(45), CORREO VARCHAR(255), JEFE VARCHAR(45), PERFIL VARCHAR(45), NOMBRE_PERFIL VARCHAR(255), FECHA_ACCESO DATETIME NULL, PRIMARY KEY(ID))";
        return statement;
    }
    
    public static String CreaPerfilesInt(String cadenaBD)                       //AGREGA UNA TABLA DE USUARIOS INTERNOS CON TODOS LOS PERFILES POSIBLES SEGÚN SU PUESTO
    {
        String[] parametros = cadenaBD.split("\\|");
        String consulta = "create table if not exists PerfilesInt SELECT CRUCEINT" + parametros[4] + ".NUM_EMP, CRUCEINT" + parametros[4] + ".USER_NAME, CRUCEINT" + parametros[4] + ".NOMBRE, "
                + "CRUCEINT" + parametros[4] + ".REGION, CRUCEINT" + parametros[4] + ".IP, CRUCEINT" + parametros[4] + ".CORREO, CRUCEINT" + parametros[4] + ".JEFE, "
                + "CRUCEINT" + parametros[4] + ".PERFIL, CRUCEINT" + parametros[4] + ".NOMBRE_PERFIL, CRUCEINT" + parametros[4] + ".FECHA_ACCESO, CRUCEINT" + parametros[4] + ".IDUSUARIO, "
                + "CRUCEINT" + parametros[4] + ".IDNUMEMP, "
                + "CRUCEINT" + parametros[4] + ".IDNOMBRE, CRUCEINT" + parametros[4] + ".IDPUESTO, CRUCEINT" + parametros[4] + ".IDGERENCIA, CRUCEINT" + parametros[4] + ".IDDEPARTAMENTO, "
                + "CRUCEINT" + parametros[4] + ".IDIDPUESTO, CRUCEINT" + parametros[4] + ".IDESTATUS, CRUCEINT" + parametros[4] + ".IDFECHA, "
                + "MATRIZPERFILES" + parametros[4] + ".CLAVE_PUESTO, MATRIZPERFILES" + parametros[4] + ".PUESTO, MATRIZPERFILES" + parametros[4] + ".PERFIL AS PERFIL_MATRIZ, MATRIZPERFILES" + parametros[4] + ".PUESTO_FUNCIONAL AS PUESTO_FUNCIONAL "
                + "FROM CRUCEINT" + parametros[4] + " RIGHT JOIN MATRIZPERFILES" + parametros[4] + " ON CRUCEINT" + parametros[4] + ".IDIDPUESTO = MATRIZPERFILES" + parametros[4] + ".CLAVE_PUESTO "
                + "UNION "
                + "SELECT CRUCEINT" + parametros[4] + ".NUM_EMP, CRUCEINT" + parametros[4] + ".USER_NAME, CRUCEINT" + parametros[4] + ".NOMBRE, "
                + "CRUCEINT" + parametros[4] + ".REGION, CRUCEINT" + parametros[4] + ".IP, CRUCEINT" + parametros[4] + ".CORREO, CRUCEINT" + parametros[4] + ".JEFE, "
                + "CRUCEINT" + parametros[4] + ".PERFIL, CRUCEINT" + parametros[4] + ".NOMBRE_PERFIL, CRUCEINT" + parametros[4] + ".FECHA_ACCESO, CRUCEINT" + parametros[4] + ".IDUSUARIO, "
                + "CRUCEINT" + parametros[4] + ".IDNUMEMP, "
                + "CRUCEINT" + parametros[4] + ".IDNOMBRE, CRUCEINT" + parametros[4] + ".IDPUESTO, CRUCEINT" + parametros[4] + ".IDGERENCIA, CRUCEINT" + parametros[4] + ".IDDEPARTAMENTO, "
                + "CRUCEINT" + parametros[4] + ".IDIDPUESTO, CRUCEINT" + parametros[4] + ".IDESTATUS, CRUCEINT" + parametros[4] + ".IDFECHA, "
                + "MATRIZPERFILES" + parametros[4] + ".CLAVE_PUESTO, MATRIZPERFILES" + parametros[4] + ".PUESTO, MATRIZPERFILES" + parametros[4] + ".PERFIL AS PERFIL_MATRIZ, MATRIZPERFILES" + parametros[4] + ".PUESTO_FUNCIONAL AS PUESTO_FUNCIONAL "
                + "FROM CRUCEINT" + parametros[4] + " LEFT JOIN MATRIZPERFILES" + parametros[4] + " ON CRUCEINT" + parametros[4] + ".IDIDPUESTO = MATRIZPERFILES" + parametros[4] + ".CLAVE_PUESTO";
        return consulta;
    }
    
    public static String CreaPerfilesExt(String cadenaBD)                       //AGREGA UNA TABLA DE USUARIOS EXTERNOS CON TODOS LOS PERFILES POSIBLES SEGÚN SU PUESTO
    {
        String[] parametros = cadenaBD.split("\\|");
        String consulta = "create table if not exists PerfilesExt SELECT CRUCEEXT" + parametros[4] + ".NUM_EMP, CRUCEEXT" + parametros[4] + ".USER_NAME, CRUCEEXT" + parametros[4] + ".NOMBRE, "
                + "CRUCEEXT" + parametros[4] + ".REGION, CRUCEEXT" + parametros[4] + ".IP, CRUCEEXT" + parametros[4] + ".CORREO, CRUCEEXT" + parametros[4] + ".JEFE, "
                + "CRUCEEXT" + parametros[4] + ".PERFIL, CRUCEEXT" + parametros[4] + ".NOMBRE_PERFIL, CRUCEEXT" + parametros[4] + ".FECHA_ACCESO, CRUCEEXT" + parametros[4] + ".IDUSUARIO, "
                + "CRUCEEXT" + parametros[4] + ".IDNUMEMP, "
                + "CRUCEEXT" + parametros[4] + ".IDNOMBRE, CRUCEEXT" + parametros[4] + ".IDPUESTO, CRUCEEXT" + parametros[4] + ".IDGERENCIA, CRUCEEXT" + parametros[4] + ".IDDEPARTAMENTO, "
                + "CRUCEEXT" + parametros[4] + ".IDIDPUESTO, CRUCEEXT" + parametros[4] + ".IDESTATUS, CRUCEEXT" + parametros[4] + ".IDFECHA, "
                + "MATRIZPERFILES" + parametros[4] + ".CLAVE_PUESTO, MATRIZPERFILES" + parametros[4] + ".PUESTO, MATRIZPERFILES" + parametros[4] + ".PERFIL AS PERFIL_MATRIZ , MATRIZPERFILES" + parametros[4] + ".PUESTO_FUNCIONAL AS PUESTO_FUNCIONAL "
                + "FROM CRUCEEXT" + parametros[4] + " RIGHT JOIN MATRIZPERFILES" + parametros[4] + " ON CRUCEEXT" + parametros[4] + ".IDIDPUESTO = MATRIZPERFILES" + parametros[4] + ".CLAVE_PUESTO "
                + "UNION "
                + "SELECT CRUCEEXT" + parametros[4] + ".NUM_EMP, CRUCEEXT" + parametros[4] + ".USER_NAME, CRUCEEXT" + parametros[4] + ".NOMBRE, "
                + "CRUCEEXT" + parametros[4] + ".REGION, CRUCEEXT" + parametros[4] + ".IP, CRUCEEXT" + parametros[4] + ".CORREO, CRUCEEXT" + parametros[4] + ".JEFE, "
                + "CRUCEEXT" + parametros[4] + ".PERFIL, CRUCEEXT" + parametros[4] + ".NOMBRE_PERFIL, CRUCEEXT" + parametros[4] + ".FECHA_ACCESO, CRUCEEXT" + parametros[4] + ".IDUSUARIO, "
                + "CRUCEEXT" + parametros[4] + ".IDNUMEMP, "
                + "CRUCEEXT" + parametros[4] + ".IDNOMBRE, CRUCEEXT" + parametros[4] + ".IDPUESTO, CRUCEEXT" + parametros[4] + ".IDGERENCIA, CRUCEEXT" + parametros[4] + ".IDDEPARTAMENTO, "
                + "CRUCEEXT" + parametros[4] + ".IDIDPUESTO, CRUCEEXT" + parametros[4] + ".IDESTATUS, CRUCEEXT" + parametros[4] + ".IDFECHA, "
                + "MATRIZPERFILES" + parametros[4] + ".CLAVE_PUESTO, MATRIZPERFILES" + parametros[4] + ".PUESTO, MATRIZPERFILES" + parametros[4] + ".PERFIL AS PERFIL_MATRIZ , MATRIZPERFILES" + parametros[4] + ".PUESTO_FUNCIONAL AS PUESTO_FUNCIONAL "
                + "FROM CRUCEEXT" + parametros[4] + " LEFT JOIN MATRIZPERFILES" + parametros[4] + " ON CRUCEEXT" + parametros[4] + ".IDIDPUESTO = MATRIZPERFILES" + parametros[4] + ".CLAVE_PUESTO ";
        return consulta;
    }
    
    public static String CreaCoincidenciasInt()                                 //AGREGA UNA TABLA CON LOS USUARIOS INTERNOS QUE TIENEN UN PERFIL CORRECTO
    {
        
        String consulta = "create table if not exists CoincidenciasInt SELECT * FROM PERFILESINT WHERE PERFIL=PERFIL_MATRIZ";
        return consulta;
    }
    
    public static String CreaCoincidenciasExt()                                 //AGREGA UNA TABLA CON LOS USUARIOS EXTERNOS QUE TIENEN UN PERFIL CORRECTO
    {
        
        String consulta = "create table if not exists CoincidenciasExt SELECT * FROM PERFILESEXT WHERE PERFIL=PERFIL_MATRIZ";
        return consulta;
    }
    
    public static String CreaPerfilesSinUsoInt(String cadenaBD)                 //AGREGA UNA TABLA CON LOS PERFILES QUE NO FUERON USADOS CON LOS USUARIOS INTERNOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String consulta = "create table if not exists PerfilesSinUsoInt" + parametros[4] + " SELECT * FROM PERFILESINT WHERE NUM_EMP IS NULL";
        return consulta;
    }
    
    public static String CreaPerfilesSinUsoExt(String cadenaBD)                 //AGREGA UNA TABLA CON LOS PERFILES QUE NO FUERON USADOS CON LOS USUARIOS EXTERNOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String consulta = "create table if not exists PerfilesSinUsoExt" + parametros[4] + " SELECT * FROM PERFILESEXT WHERE NUM_EMP IS NULL";
        return consulta;
    }
    
    public static String CreaUsrNoAutorizadosInt()                              //AGREGA UNA TABLA CON LOS USUARIOS INTERNOS QUE NO DEBEN TENER ACCESO A LA APLICACIÓN
    {
        String consulta = "create table if not exists UsuariosNoAutorizadosInt SELECT * FROM PERFILESINT WHERE CLAVE_PUESTO IS NULL";
        return consulta;
    }
    
    public static String CreaUsrNoAutorizadosExt()                              //AGREGA UNA TABLA CON LOS USUARIOS EXTERNOS QUE NO DEBEN TENER ACCESO A LA APLICACIÓN
    {
        String consulta = "create table if not exists UsuariosNoAutorizadosExt SELECT * FROM PERFILESEXT WHERE CLAVE_PUESTO IS NULL";
        return consulta;
    }

    public static String CreaPerfilesSinUso(String cadenaBD)                    //AGREGA UNA TABLA CON LOS PERFILES QUE NO FUERON USADOS POR USUARIOS INTERNOS Y EXTERNOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String consulta = "create table perfilessinuso" + parametros[4] + "  (select * from perfilessinusoint" + parametros[4] + "  where CLAVE_PUESTO in (select CLAVE_PUESTO FROM perfilessinusoext" + parametros[4] + " ))";
        return consulta;
    }
        
    public static String CreaExcepDuplicados(String cadenaBD)                   //CREA TABLA CON LOS REGISTROS DE USUARIOS QUE SON EXCEPCIONES AL MONITOREO DE USUARIOS DUPLICADOS             
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "CREATE TABLE IF NOT EXISTS EXCEPDUPLICADOS" + parametros[4] + " (ID INT NOT NULL AUTO_INCREMENT, NUM_EMP VARCHAR(45), USER_NAME VARCHAR(45), "
                + "NOMBRE VARCHAR(255), REGION INT, PRIMARY KEY(ID))";
        return statement;
    }
    
    public static String CreaMatrizPerfiles(String cadenaBD)                    //CREA TABLA PARA LA MATRIZ DE PERFILES ESTANDARIZADOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "CREATE TABLE IF NOT EXISTS MATRIZPERFILES" + parametros[4] + " (ID INT NOT NULL AUTO_INCREMENT, GERENCIA VARCHAR(255), CLAVE_PUESTO VARCHAR(45), "
                + "PUESTO VARCHAR(255), PERFIL VARCHAR(45), PUESTO_FUNCIONAL VARCHAR(45), "
                + "PRIMARY KEY(ID))";
        return statement;
    }
    
    public static String BorraUsuarios(String cadenaBD)                         //BORRA LA TABLA PREEXISTENTE DE USUARIOS PARA INGRESAR LOS USUARIOS NUEVOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "DROP TABLE IF EXISTS Usuarios" + parametros[4];
        return statement;
    }
    
    public static String CreaExcepDuplicados(String cadenaBD, String ruta)      //INSERTA EN LA TABLA DE EXCEPCIONES LOS REGISTROS DE USUARIOS DUPLICADOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String retorno = "INSERT INTO EXCEPDUPLICADOS" + parametros[4] + " (NUM_EMP, USER_NAME, NOMBRE, REGION) VALUES";        
        retorno = retorno + Archivos.lecturaExcepDuplicados(ruta, cadenaBD);
        
        return retorno;
    }
    
    public static String CreaMatrizPerfiles(String cadenaBD, String ruta)       //INSERTA EN LA TABLA LA MATRIZ DE PERFILES ESTANDARIZADOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String retorno = "INSERT INTO MATRIZPERFILES" + parametros[4] + " (GERENCIA, CLAVE_PUESTO, PUESTO, PERFIL, PUESTO_FUNCIONAL) VALUES";        
        retorno = retorno + Archivos.lecturaMatrizPerfiles(ruta, cadenaBD);
        
        return retorno;
    }
    
    public static String UsrIDIncInt(String cadenaBD)                           //AGREGA UNA TABLA PARA LAS INCIDENCIAS DE USUARIOS INTERNOS CON USER ID INCORRECTO
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "CREATE TABLE IF NOT EXISTS USRINCINT SELECT * FROM CRUCEINT" + parametros[4] + " WHERE USER_NAME != IDUSUARIO";
        return statement;
    }
    
    public static String BorraUsrIDIncInt(String cadenaBD)                      //BORRA LOS USUARIOS INTERNOS CON USER ID INCORRECTO DE LA TABLA DE CRUCES
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "DELETE FROM CRUCEINT" + parametros[4] + " WHERE USER_NAME != IDUSUARIO";
        return statement;
    }
    
    public static String UsrIDIncExt(String cadenaBD)                           //AGREGA UNA TABLA PARA LAS INCIDENCIAS DE USUARIOS EXTERNOS CON USER ID INCORRECTO
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "CREATE TABLE IF NOT EXISTS USRINCEXT SELECT * FROM CRUCEEXT" + parametros[4] + " WHERE USER_NAME != IDUSUARIO";
        return statement;
    }
    
    public static String BorraUsrIDIncExt(String cadenaBD)                      //ELIMINA LOS USUARIOS EXTERNOS CON USER ID INCORRECTO DE LA TABLA DE CRUCES
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "DELETE FROM CRUCEEXT" + parametros[4] + " WHERE USER_NAME != IDUSUARIO";
        return statement;
    }
    
    public static String CreaDuplicadosInt(String cadenaBD)                     //AGREGA UNA TABLA CON LAS INCIDENCIAS DE USUARIOS INTERNOS DUPLICADOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "CREATE TABLE IF NOT EXISTS DUPXNOMBREINT SELECT DISTINCT * FROM CRUCEINT" + parametros[4] + " WHERE NOMBRE IN "
                + "(SELECT NOMBRE FROM CRUCEINT" + parametros[4] + " AS TMP GROUP BY NOMBRE HAVING COUNT(*) > 1) ORDER BY NOMBRE";
        return statement;
    }
    
    public static String CreaDuplicadosExt(String cadenaBD)                     //AGREGA UNA TABLA CON LAS INCIDENCIAS DE USUARIOS EXTERNOS DUPLICADOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "CREATE TABLE IF NOT EXISTS DUPXNOMBREEXT SELECT DISTINCT * FROM CRUCEEXT" + parametros[4] + " WHERE NOMBRE IN "
                + "(SELECT NOMBRE FROM CRUCEEXT" + parametros[4] + " AS TMP GROUP BY NOMBRE HAVING COUNT(*) > 1) ORDER BY NOMBRE";
        return statement;
    }
    
    public static String BorraExcepDuplicadosInt(String cadenaBD)               //BORRA DE LA TABLA DE INCIDENCIAS DE USUARIOS INTEERNOS DUPLICACOS LOS USUARIOS QUE SE ENCUENTRAN EN EL REGISTRO DE EXCEPCIONES
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "DELETE FROM DUPXNOMBREINT WHERE NUM_EMP IN ( SELECT NUM_EMP FROM EXCEPDUPLICADOS" + parametros[4] + ") ";
        return statement;
    }
    
    public static String BorraExcepDuplicadosExt(String cadenaBD)               //BORRA DE LA TABLA DE INCIDENCIAS DE USUARIOS EXTEERNOS DUPLICACOS LOS USUARIOS QUE SE ENCUENTRAN EN EL REGISTRO DE EXCEPCIONES
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "DELETE FROM DUPXNOMBREEXT WHERE NUM_EMP IN ( SELECT NUM_EMP FROM EXCEPDUPLICADOS" + parametros[4] + ") ";
        return statement;
    }
    
    public static String BorraDuplicadosInt(String cadenaBD)                    //BORRA DE LA TABLA DE INCIDENCIAS DE USUARIOS INTEERNOS DUPLICACOS LOS USUARIOS QUE YA SE REGISTRARON EN LAS INCIDENCIAS
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "DELETE FROM CRUCEINT" + parametros[4] + " WHERE NUM_EMP IN ( SELECT NUM_EMP FROM DUPXNOMBREINT) ";
        return statement;
    }
    
    public static String BorraDuplicadosExt(String cadenaBD)                    //BORRA DE LA TABLA DE INCIDENCIAS DE USUARIOS EXTEERNOS DUPLICACOS LOS USUARIOS QUE YA SE REGISTRARON EN LAS INCIDENCIAS
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "DELETE FROM CRUCEEXT" + parametros[4] + " WHERE NUM_EMP IN ( SELECT NUM_EMP FROM DUPXNOMBREEXT) ";
        return statement;
    }
    
    public static String BorraExcepDuplicados(String cadenaBD)                  //BORRA LA TABLA DE EXCEPCIONES PARA LOS REGISTROS QUE SON DUPLICADOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "DROP TABLE IF EXISTS EXCEPDUPLICADOS" + parametros[4];
        return statement;
    }
    
    public static String CreaInsertUsuarios(String cadenaBD, ResultSet Res)     //CREA LA INSTRUCCIÓN SQL PARA INSERTAR LOS USUARIOS QUE FUERON DESCARGADOS DE LA BD DEL APLICATIVO
    {
        String[] parametros = cadenaBD.split("\\|");
        String retorno = "INSERT INTO USUARIOS" + parametros[4] + " (NUM_EMP, USER_NAME, NOMBRE, REGION, IP, CORREO, JEFE, PERFIL, NOMBRE_PERFIL, FECHA_ACCESO) VALUES ";        
        String fecha;
        String nombre;
        String correo;
        try {
            
            while(Res.next())
            {
                fecha = Res.getString(13);
                if(!fecha.isEmpty())
                {
                    String [] fechac;
                    fechac = fecha.split(" ");
                    fecha = fechac[0];
                    String [] fechap;
                    fecha = "'" + fecha + "'";
                }
                else
                {
                    fecha = "''";
                }
                correo = Res.getString(8);
                if(correo==null)
                {
                    correo = "";
                }
                nombre = Res.getString(4);
//                System.out.println(nombre);
                nombre = Monitoreos.Archivos.escapaChar(nombre, "'");
//                System.out.println(nombre);
                retorno = retorno + "\n ('" + Res.getString(1) + "', '"  + Res.getString(2) + "', '" + Res.getString(3) + " " + nombre + " " + Res.getString(5) + "', "
                         + Res.getString(6) + ", '" + Res.getString(7) + "', '" + correo + "', '" + Res.getString(9) + "', '"
                         + Res.getString(11) + "', '" + Res.getString(12) + "', " + fecha +  "),";
                
            }
            retorno = retorno.substring(0, retorno.length()-1);
//            System.out.println(retorno);
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Querys.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return retorno;
        
    }
        
    public static String BorraInternosTrabajo()                                 //BORRA LA TABLA DE TRABAJO INTERNOS
    {
        String statement = "DROP TABLE IF EXISTS INTERNOS";
        return statement;
    }
    
    public static String BorraExternosTrabajo()                                 //BORRA LA TABLA DE TRABAJO EXTERNOS
    {
        String statement = "DROP TABLE IF EXISTS EXTERNOS";
        return statement;
    }
    
    public static String BorraBaajasInt()                                       //BORRA LA TABLA DE TRABAJO DE INCIDENCIAS DE BAJAS INTERNOS
    {
        String statement = "DROP TABLE IF EXISTS BAAJASINT";
        return statement;
    }
    
    public static String BorraBaajasExt()                                       //BORRA LA TABLA DE TRABAJO DE INCIDENCIAS DE BAJAS EXTERNOS
    {
        String statement = "DROP TABLE IF EXISTS BAAJASEXT";
        return statement;
    }
    
    public static String BorraCruceInternosTrabajo(String cadenaBD)             //BORRA TABLA DE TRABAJO DE CRUCES DE USUARIOS INTERNOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "DROP TABLE IF EXISTS CRUCEINT" + parametros[4];
        return statement;
    }
    
    public static String BorraCruceExternosTrabajo(String cadenaBD)             //BORRA TABLA DE TRABAJO DE CRUCES DE USUARIOS EXTERNOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "DROP TABLE IF EXISTS CRUCEEXT" + parametros[4];
        return statement;
    }
    
    public static String BorraBajasLInt(String cadenaBD)                        //BORRA LA TABLA DE TRABAJO DE INCIDENCIAS DE USUARIOS INTERNOS REPORTADOS COMO BAJA
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "DROP TABLE IF EXISTS BAJALINT" + parametros[4];
        return statement;
    }
    
    public static String BorraBajasLExt(String cadenaBD)                        //BORRA LA TABLA DE TRABAJO DE INCIDENCIAS DE USUARIOS EXTERNOS REPORTADOS COMO BAJA
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "DROP TABLE IF EXISTS BAJALEXT" + parametros[4];
        return statement;
    }
    
    public static String BorraDupXNombreInt(String cadenaBD)                    //BORRA LA TABLA DE TRABAJO DE USAURIOS INTERNOS DUPLICADOS POR NOMBRE
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "DROP TABLE IF EXISTS DUPXNOMBREINT";
        return statement;
    }
    
    public static String BorraDupXNombreExt(String cadenaBD)                    //BORRA LA TABLA DE TRABAJO DE USAURIOS EXTERNOS DUPLICADOS POR NOMBRE
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "DROP TABLE IF EXISTS DUPXNOMBREEXT";
        return statement;
    }
    
    public static String BorraInacInt(String cadenaBD)                          //BORRA LA TABLA DE TRABAJO DE USUARIOS INTERNOS INACTIVOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "DROP TABLE IF EXISTS INACTIVIDADINT";
        return statement;
    }
    
    public static String BorraInacExt(String cadenaBD)                          //BORRA LA TABLA DE TRABAJO DE USUARIOS EXTERNOS INACTIVOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "DROP TABLE IF EXISTS INACTIVIDADEXT";
        return statement;
    }
    
    public static String BorraNoNominaInt(String cadenaBD)                      //BORRA LA TABLA DE TRABAJO DE LOS USAURIOS INTERNOS QUE NO SE ENCUENTRAN REGISTRADOS EN NOMINA
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "DROP TABLE IF EXISTS NONOMINAINT";
        return statement;
    }
    
    public static String BorraNoNominaExt(String cadenaBD)                      //BORRA LA TABLA DE TRABAJO DE LOS USAURIOS EXTERNOS QUE NO SE ENCUENTRAN REGISTRADOS EN NOMINA
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "DROP TABLE IF EXISTS NONOMINAEXT";
        return statement;
    }
    
    public static String BorraUsrIncInt(String cadenaBD)                        //BORRA LA TABLA DE TRABAJO DE LOS UUARIOS INTERNOS CON USER ID INCORRECTO
    {   
        String[] parametros = cadenaBD.split("\\|");
        String statement = "DROP TABLE IF EXISTS USRINCINT";
        return statement;
    }
    
    public static String BorraUsrIncExt(String cadenaBD)                        //BORRA LA TABLA DE TRABAJO DE LOS UsUARIOS EXTERNOS CON USER ID INCORRECTO
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "DROP TABLE IF EXISTS USRINCEXT";
        return statement;
    }
    
    public static String BorraPerfilesInt()                                     //BORRA LA TABLA DE TRABAJO DE LOS PERFILES DE UUARIOS INTERNOS
    {
        
        String statement = "DROP TABLE IF EXISTS PERFILESINT";
        return statement;
    }
    
    public static String BorraPerfilesExt()                                     //BORRA LA TABLA DE TRABAJO DE LOS PERFILES DE UUARIOS EXTERNOS
    {
        
        String statement = "DROP TABLE IF EXISTS PERFILESEXT";
        return statement;
    }
    
    public static String BorraPerfilesCorrectoInt()                             //BORRA LOS USUARIOS INTERNOS CON PERFIL CORRECTO DE LA TABLA PERFIL
    {
        
        String statement = "DELETE FROM PERFILESINT WHERE NUM_EMP IN (SELECT NUM_EMP FROM COINCIDENCIASINT)";
        return statement;
    }
    
    
    public static String BorraPerfilesCorrectoExt()                             //BORRA LOS USUARIOS EXTERNOS CON PERFIL CORRECTO DE LA TABLA PERFIL
    {
        
        String statement = "DELETE FROM PERFILESEXT WHERE NUM_EMP IN (SELECT NUM_EMP FROM COINCIDENCIASEXT)";
        return statement;
    }
    
    public static String BorraPerfilesSinUsoInt()                               //BORRA LOS REGISTROS DE PERFILES QUE NO FUERON USADOS POR USUARIOS INTERNOS
    {
        
        String statement = "DELETE FROM PERFILESINT WHERE NUM_EMP IS NULL";
        return statement;
    }
    
    public static String BorraPerfilesSinUsoExt()                               //BORRA LOS REGISTROS DE PERFILES QUE NO FUERON USADOS POR USUARIOS EXTERNOS
    {
        
        String statement = "DELETE FROM PERFILESEXT WHERE NUM_EMP IS NULL";
        return statement;
    }
    
    public static String BorraNoAutorizadosInt()                                //BORRA LOS USUARIOS INTERNOS QUE NO TIENEN AUTORIZADO HACER USO DEL SISTEMA
    {
        
        String statement = "DELETE FROM PERFILESINT WHERE CLAVE_PUESTO IS NULL";
        return statement;
    }
    
    public static String BorraNoAutorizadosExt()                                //BORRA LOS USUARIOS EXTERNOS QUE NO TIENEN AUTORIZADO HACER USO DEL SISTEMA
    {
        
        String statement = "DELETE FROM PERFILESEXT WHERE CLAVE_PUESTO IS NULL";
        return statement;
    }
    
    public static String BorraCoincidenciasInt()                                //BORRA LA TABLA DE TRABAJO COINCIDENCIAS INTERNOS
    {
        
        String statement = "DROP TABLE IF EXISTS COINCIDENCIASINT";
        return statement;
    }
    
    public static String BorraCoincidenciasExt()                                //BORRA LA TABLA DE TRABAJO COINCIDENCIAS EXTERNOS
    {
        
        String statement = "DROP TABLE IF EXISTS COINCIDENCIASEXT";
        return statement;
    }    
    
    
    public static String BorraMatrizPerfiles(String cadenaBD)                   //BORRA LA TABLA DE MATRIZ DE PERFILES
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "DROP TABLE IF EXISTS MATRIZPERFILES" + parametros[4];
        return statement;
    }
    
    public static String BorraPerfilesSinUsoInt(String cadenaBD)                //BORRA LA TABLA DE TRABAJO PERFILES SIN USO DE USUARIOS INTERNOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "DROP TABLE IF EXISTS PERFILESSINUSOINT" + parametros[4];
        return statement;
    }
    
    public static String BorraPerfilesSinUsoExt(String cadenaBD)                //BORRA LA TABLA DE TRABAJO PERFILES SIN USO DE USUARIOS EXTERNOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "DROP TABLE IF EXISTS PERFILESSINUSOEXT" + parametros[4];
        return statement;
    }
    
    public static String BorraPerfilesSinUso(String cadenaBD)                   //BORRA LA TABLA DE PERFILES SIN USO
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "DROP TABLE IF EXISTS PERFILESSINUSO" + parametros[4];
        return statement;
    }
    
    public static String BorraPerfilNoAutorizadosInt()                          //BORRA LA TABLA DE TRABAJO DE USUARIOS INTERNOS CON PERFIL NO AUTORIZADO
    {
        String statement = "DROP TABLE IF EXISTS UsuariosNoAutorizadosInt";
        return statement;
    }
    
    public static String BorraPerfilNoAutorizadosExt()                          //BORRA LA TABLA DE TRABAJO DE USUARIOS EXTERNOS CON PERFIL NO AUTORIZADO
    {
        String statement = "DROP TABLE IF EXISTS UsuariosNoAutorizadosExt";
        return statement;
    }
        
    public static String CreaInternosTrabajo(String cadenaBD)                   //CREA LA TABLA DE TRABAJO DE USUARIOS INTERNOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "CREATE TABLE IF NOT EXISTS INTERNOS SELECT * FROM USUARIOS" + parametros[4] + " WHERE NUM_EMP NOT LIKE '%EX%'";
        return statement;
    }
    
    public static String CreaExternosTrabajo(String cadenaBD)                   //CREA LA TABLA DE TRABAJO DE USUARIOS EXTERNOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "CREATE TABLE IF NOT EXISTS EXTERNOS SELECT * FROM USUARIOS" + parametros[4] + " WHERE NUM_EMP LIKE '%EX%'";
        return statement;
    }
    
    public static String BuscaTablas(String cadenaBD)                           //BUSCA LAS TABLAS EXISTENTES DENTRO DE LA BASE DE DATOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = '" + parametros[0] + "'";
        return statement;
    }
    
    public static String BorraVacia(String tabla)                               //BORRA LA TABLA DEL NOMBRE QUE RECIBE, SE UTILIZA PARA BORRAR LAS TABLAS VACÍAS
    {
        String statement = "DROP TABLE IF EXISTS " + tabla;
        return statement;
    }
        
    
    public static String BuscaVacias(String tabla)                              //CUENTA LOS REGISTROS EN CADA TABLA PARA BUSCAR LAS TABALS VACÍAS
    {
        String consulta = "SELECT COUNT(*) FROM " + tabla;
        return consulta;
    }
    
    public static String CruceInternos(String cadenaBD)                         //REALIZA EL CRUCE DE LOS USUARIOS INTERNOS
    {
        String statement = null;
        String[] parametros = cadenaBD.split("\\|");
        statement = "create table if not exists cruceint" + parametros[4] + " SELECT INTERNOS.ID, INTERNOS.NUM_EMP, INTERNOS.USER_NAME, INTERNOS.NOMBRE, INTERNOS.REGION, "
                + "INTERNOS.IP, INTERNOS.CORREO, INTERNOS.JEFE, INTERNOS.PERFIL, INTERNOS.NOMBRE_PERFIL, INTERNOS.FECHA_ACCESO,  "
                + "idint" + parametros[4] + ".NUMEROEMPLEADO AS IDNUMEMP, "
                + "idint" + parametros[4] + ".USUARIO AS IDUSUARIO, idint" + parametros[4] + ".NOMBRECOMPLETO AS IDNOMBRE, "
                + "idint" + parametros[4] + ".REGION AS IDREGION, idint" + parametros[4] + ".GERENCIA AS IDGERENCIA, "
                + "idint" + parametros[4] + ".DEPARTAMENTO AS IDDEPARTAMENTO, "
                + "idint" + parametros[4] + ".PUESTO AS IDPUESTO, idint" + parametros[4] + ".IDPUESTO AS IDIDPUESTO, "       
                + "idint" + parametros[4] + ".ESTATUS AS IDESTATUS, idint" + parametros[4] + ".FECHA AS IDFECHA "
                + ""
                + "FROM INTERNOS left join idint" + parametros[4] 
                + " on INTERNOS.NUM_EMP=idint" + parametros[4] + ".NUMEROEMPLEADO";
        
        return statement;
    }
    
    
    public static String CruceExternos(String cadenaBD)                         //REALIZA EL CRUCE DE LOS USUARIOS EXTERNOS
    {
        String statement = null;
        String[] parametros = cadenaBD.split("\\|");
        statement = "create table if not exists cruceext" + parametros[4] + " SELECT EXTERNOS.ID, EXTERNOS.NUM_EMP, " 
                    + "EXTERNOS.User_Name,  EXTERNOS.Nombre, EXTERNOS.Region, EXTERNOS.IP, EXTERNOS.CORREO, EXTERNOS.JEFE, EXTERNOS.PERFIL, "
                    + "EXTERNOS.NOMBRE_PERFIL, EXTERNOS.FECHA_ACCESO, "
                    + "idext" + parametros[4] + ".NUMEROEMPLEADO AS IDNUMEMP, " 
                    + "idext" + parametros[4] + ".USUARIO AS IDUSUARIO, idext" + parametros[4] + ".NOMBRECOMPLETO AS IDNOMBRE, "
                    + "idext" + parametros[4] + ".REGION AS IDREGION, idext" + parametros[4] + ".GERENCIA AS IDGERENCIA, "
                    + "idext" + parametros[4] + ".DEPARTAMENTO AS IDDEPARTAMENTO, "
                    + "idext" + parametros[4] + ".PUESTO AS IDPUESTO, idext" + parametros[4] + ".IDPUESTO AS IDIDPUESTO, "                    
                    + "idext" + parametros[4] + ".ESTATUS AS IDESTATUS, idext" + parametros[4] + ".FECHA AS IDFECHA  "
                    + "FROM EXTERNOS left join idext" + parametros[4]
                    + " on EXTERNOS.Num_Emp=idext" + parametros[4] + ".NUMEROEMPLEADO ";
            
        return statement;
    }
    
    public static String ResultadosBajasInt()                                   //OBTIENE EL RESULTADO DE LAS INCIDENCIAS DE USUARIOS INTERNOS DADOS DE BAJA
    {
        String statement = "select NUM_EMP, USER_NAME, NOMBRE, FECHA_ACCESO, REGION, IP, PERFIL, NOMBRE_PERFIL, IDNUMEMP, IDPUESTO, IDGERENCIA, IDNOMBRE, IDFECHA, "
                + "IDESTATUS  from baajasint union select NUM_EMP, USER_NAME, NOMBRE, FECHA_ACCESO, REGION, IP, PERFIL, NOMBRE_PERFIL, IDNUMEMP, IDPUESTO, IDGERENCIA, "
                + "IDNOMBRE, IDFECHA, IDESTATUS  from nonominaint";
        return statement;
    }
    
    public static String ResultadosBajasExt()                                   //OBTIENE EL RESULTADO DE LAS INCIDENCIAS DE USUARIOS EXTERNOS DADOS DE BAJA
    {
        String statement = "select NUM_EMP, USER_NAME, NOMBRE, FECHA_ACCESO, REGION, IP, PERFIL, NOMBRE_PERFIL, IDNUMEMP, IDPUESTO, IDGERENCIA, "
                + "IDNOMBRE, IDFECHA, IDESTATUS  from baajasext union select NUM_EMP, USER_NAME, NOMBRE, FECHA_ACCESO, REGION, IP, PERFIL, "
                + "NOMBRE_PERFIL, IDNUMEMP, IDPUESTO, IDGERENCIA, IDNOMBRE, IDFECHA, IDESTATUS  from nonominaext";
        return statement;
    }
    
    public static String ResultadosInactividadInt()                             //OBTIENE EL RESULTADO DE LAS INCIDENCIAS DE USUARIOS INTERNOS INACTIVOS
    {
        String statement = "select NUM_EMP, USER_NAME, NOMBRE, FECHA_ACCESO, REGION, IP, PERFIL, NOMBRE_PERFIL from inactividadint";
        return statement;
    }
    
    public static String ResultadosInactividadExt()                             //OBTIENE EL RESULTADO DE LAS INCIDENCIAS DE USUARIOS EXTERNOS INACTIVOS
    {
        String statement = "select NUM_EMP, USER_NAME, NOMBRE, FECHA_ACCESO, REGION, IP, PERFIL, NOMBRE_PERFIL from inactividadext";
        return statement;
    }    
    
    public static String ResultadosUserIncInt()                             //OBTIENE EL RESULTADO DE LAS INCIDENCIAS DE USUARIOS INTERNOS CON USER ID INCORRECTO
    {
        String statement = "select NUM_EMP, USER_NAME, NOMBRE, FECHA_ACCESO, REGION, IP, PERFIL, NOMBRE_PERFIL, IDNUMEMP, IDUSUARIO, IDPUESTO, IDGERENCIA, IDNOMBRE from usrincint";
        return statement;
    }
    
    public static String ResultadosUserIncExt()                             //OBTIENE EL RESULTADO DE LAS INCIDENCIAS DE USUARIOS EXTERNOS CON USER ID INCORRECTO
    {
        String statement = "select NUM_EMP, USER_NAME, NOMBRE, FECHA_ACCESO, REGION, IP, PERFIL, NOMBRE_PERFIL, IDNUMEMP, IDUSUARIO, IDPUESTO, IDGERENCIA, IDNOMBRE from usrincext";
        return statement;
    }    
    
    public static String ResultadosDuplicadosXNomInt()                             //OBTIENE EL RESULTADO DE LAS INCIDENCIAS DE USUARIOS INTERNOS DUPLICADOS POR NOMBRE
    {
        String statement = "select NUM_EMP, USER_NAME, NOMBRE, FECHA_ACCESO, REGION, IP, PERFIL, NOMBRE_PERFIL, IDNUMEMP, IDUSUARIO, IDPUESTO, IDGERENCIA, IDNOMBRE from dupxnombreint";
        return statement;
    }
    
    public static String ResultadosDuplicadosXNomExt()                             //OBTIENE EL RESULTADO DE LAS INCIDENCIAS DE USUARIOS EXTERNOS DUPLICADOS POR NOMBRE
    {
        String statement = "select NUM_EMP, USER_NAME, NOMBRE, FECHA_ACCESO, REGION, IP, PERFIL, NOMBRE_PERFIL, IDNUMEMP, IDUSUARIO, IDPUESTO, IDGERENCIA, IDNOMBRE from dupxnombreext";
        return statement;
    }
    
    public static String ResultadosPerfilIncorrectoInt()                             //OBTIENE EL RESULTADO DE LAS INCIDENCIAS DE USUARIOS INTERNOS CON PERFIL INCORRECTO
    {
        String statement = "select NUM_EMP, USER_NAME, NOMBRE, FECHA_ACCESO, REGION, IP, IDIDPUESTO, PERFIL, NOMBRE_PERFIL, CLAVE_PUESTO, PERFIL_MATRIZ, PUESTO_FUNCIONAL, PUESTO, IDNUMEMP, IDPUESTO, IDDEPARTAMENTO, IDGERENCIA, IDNOMBRE from perfilesint";
        return statement;
    }
    
    public static String ResultadosPerfilIncorrectoExt()                             //OBTIENE EL RESULTADO DE LAS INCIDENCIAS DE USUARIOS EXTERNOS CON PERFIL INCORRECTO
    {
        String statement = "select NUM_EMP, USER_NAME, NOMBRE, FECHA_ACCESO, REGION, IP, IDIDPUESTO, PERFIL, NOMBRE_PERFIL, CLAVE_PUESTO, PERFIL_MATRIZ, PUESTO_FUNCIONAL, PUESTO, IDNUMEMP, IDPUESTO, IDDEPARTAMENTO, IDGERENCIA, IDNOMBRE from perfilesext";
        return statement;
    }
    
    public static String ResultadosNoAutorizadoInt()                             //OBTIENE EL RESULTADO DE LAS INCIDENCIAS DE USUARIOS INTERNOS QUE NO TIENEN AUTORIZADO HACER USO DEL SISTEMA
    {
        String statement = "select NUM_EMP, USER_NAME, NOMBRE, FECHA_ACCESO, REGION, IP, IDIDPUESTO, PERFIL, NOMBRE_PERFIL, CLAVE_PUESTO, PERFIL_MATRIZ, PUESTO, IDNUMEMP, IDPUESTO, IDDEPARTAMENTO, IDGERENCIA, IDNOMBRE from UsuariosNoAutorizadosInt";
        return statement;
    }
    
    public static String ResultadosNoAutorizadoExt()                             //OBTIENE EL RESULTADO DE LAS INCIDENCIAS DE USUARIOS EXTERNOS QUE NO TIENEN AUTORIZADO HACER USO DEL SISTEMA
    {
        String statement = "select NUM_EMP, USER_NAME, NOMBRE, FECHA_ACCESO, REGION, IP, IDIDPUESTO, PERFIL, NOMBRE_PERFIL, CLAVE_PUESTO, PERFIL_MATRIZ, PUESTO, IDNUMEMP, IDPUESTO, IDDEPARTAMENTO, IDGERENCIA, IDNOMBRE from UsuariosNoAutorizadosExt";
        return statement;
    }
    
    public static String BorraUsrAdminAgregados()                          //BORRA LA TABLA DE TRABAJO DE USUARIOS ADMINISTRADORES AGREGADOS
    {
        String statement = "DROP TABLE IF EXISTS agregados";
        return statement;
    }
    public static String BorraUsrAdminEliminados()                          //BORRA LA TABLA DE TRABAJO DE USUARIOS ADMINISTRADORES ELIMINADOS
    {
        String statement = "DROP TABLE IF EXISTS eliminados";
        return statement;
    }
    
    public static String ResultadosAgreg()                             //OBTIENE EL RESULTADO DE LOS USUARIOS AGREGADOS 
    {
        String statement = "select USUARIO, NOMBRE, APELLIDO, ROL, VALOR_AUTORIZACION from agregados";
        return statement;
    }
    
    public static String ResultadosElim()                             //OBTIENE EL RESULTADO DE LOS USUARIOS AGREGADOS 
    {
        String statement = "select USUARIO, NOMBRE, APELLIDO, ROL, VALOR_AUTORIZACION from eliminados";
        return statement;
    }
    
    public static String BorraCruceSAPInt()                             //OBTIENE EL RESULTADO DE LOS USUARIOS AGREGADOS 
    {
        String statement = "select USUARIO, NOMBRE, APELLIDO, ROL, VALOR_AUTORIZACION from eliminados";***
        return statement;
    }
    
     public static String BorraCruceSAPExt()                             //OBTIENE EL RESULTADO DE LOS USUARIOS AGREGADOS 
    {
        String statement = "select USUARIO, NOMBRE, APELLIDO, ROL, VALOR_AUTORIZACION from eliminados";***
        return statement;
    }
     
     public static String BorraCruceSAPGen()                             //OBTIENE EL RESULTADO DE LOS USUARIOS AGREGADOS 
    {
        String statement = "select USUARIO, NOMBRE, APELLIDO, ROL, VALOR_AUTORIZACION from eliminados";***
        return statement;
    }
    
     public static String CreaInternosSAP(String cadenaBD)                   //CREA LA TABLA DE TRABAJO DE USUARIOS EXTERNOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "CREATE TABLE IF NOT EXISTS EXTERNOS SELECT * FROM USUARIOS" + parametros[4] + " WHERE NUM_EMP LIKE '%EX%'";******
        return statement;
    }
     
     public static String CreaExternosSAP(String cadenaBD)                   //CREA LA TABLA DE TRABAJO DE USUARIOS EXTERNOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "CREATE TABLE IF NOT EXISTS EXTERNOS SELECT * FROM USUARIOS" + parametros[4] + " WHERE NUM_EMP LIKE '%EX%'";*******
        return statement;
    }
     
     public static String CreaGenSAP(String cadenaBD)                   //CREA LA TABLA DE TRABAJO DE USUARIOS EXTERNOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "CREATE TABLE IF NOT EXISTS GENERICOS SELECT * FROM USUARIOS" + parametros[4] + " WHERE NUM_EMP LIKE '%EX%'";*******
        return statement;
    }
}