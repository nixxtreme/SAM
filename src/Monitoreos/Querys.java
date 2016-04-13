
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
    
    
    public static String NoNominaBorraExt(String cadenaBD)                      //ELIMINA LOS USUARIOS EXTERNOS QUE NO SE ENCUENTRAN REGISTRADOS EN NÓMINA
    {
        String[] parametros = cadenaBD.split("\\|");
        String consulta = "DELETE FROM cruceext" + parametros[4] + " where idusuario is null";
                
        return consulta;
    }
    
    
    
                        //******************SAP****************
    public static String NoNominaBorraIntSAP(String cadenaBD)                      //ELIMINA LOS USUARIOS INTERNOS QUE NO SE ENCUENTRAN REGISTRADOS EN NÓMINA
    {
        String[] parametros = cadenaBD.split("\\|");
        String consulta = "DELETE FROM cruceintSAP" + parametros[4] + " where NUMEROEMPLEADO is null";
                
        return consulta;
    }    
    
    public static String NoNominaBorraExtSAP(String cadenaBD)                      //ELIMINA LOS USUARIOS EXTERNOS QUE NO SE ENCUENTRAN REGISTRADOS EN NÓMINA
    {
        String[] parametros = cadenaBD.split("\\|");
        String consulta = "DELETE FROM cruceextSAP" + parametros[4] + " where NUMEROEMPLEADO is null";
                
        return consulta;
    }
    
    public static String eliminarDemCruceInt(String cadenaBD)                      //ELIMINA LOS USUARIOS EXTERNOS QUE NO SE ENCUENTRAN REGISTRADOS EN NÓMINA
    {
        String[] parametros = cadenaBD.split("\\|");
        String consulta = "DELETE FROM cruceintSAP" + parametros[4] + " where numemp IS NOT null";
                
        return consulta;
    }
    
    public static String eliminarDemCruceExt(String cadenaBD)                      //ELIMINA LOS USUARIOS EXTERNOS QUE NO SE ENCUENTRAN REGISTRADOS EN NÓMINA
    {
        String[] parametros = cadenaBD.split("\\|");
        String consulta = "DELETE FROM cruceextSAP" + parametros[4] + " where numemp IS NOT null";
                
        return consulta;
    }
    
    public static String BorraBajasExtSAP(String cadenaBD)                         //ELIMINA LOS USUARIOS EXTERNOS QUE ESTÁN REGSTRADOS COMO BAJA
    {
        String[] parametros = cadenaBD.split("\\|");
        String consulta = "DELETE FROM cruceextSAP" + parametros[4] + " WHERE ESTATUS = 'ELIMINADO'";
                
        return consulta;
    }
    
    public static String BorraBajasIntSAP(String cadenaBD)                         //ELIMINA LOS USUARIOS INTERNOS QUE ESTÁN REGISTRADOS COMO BAJA
    {
        String[] parametros = cadenaBD.split("\\|");
        String consulta = "DELETE FROM cruceintSAP" + parametros[4] + " WHERE ESTATUS = 'ELIMINADO'";
                
        return consulta;
    }
    
                //*******************************
    
    
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
    
    
    public static String BorraUsrIDIncInt(String cadenaBD)                      //BORRA LOS USUARIOS INTERNOS CON USER ID INCORRECTO DE LA TABLA DE CRUCES
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "DELETE FROM CRUCEINT" + parametros[4] + " WHERE USER_NAME != IDUSUARIO";
        return statement;
    }
    
    
    public static String BorraUsrIDIncExt(String cadenaBD)                      //ELIMINA LOS USUARIOS EXTERNOS CON USER ID INCORRECTO DE LA TABLA DE CRUCES
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "DELETE FROM CRUCEEXT" + parametros[4] + " WHERE USER_NAME != IDUSUARIO";
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
    
    public static String BuscaTablas(String cadenaBD)                           //BUSCA LAS TABLAS EXISTENTES DENTRO DE LA BASE DE DATOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = '" + parametros[0] + "'";
        return statement;
    }
    
    
    public static String BuscaVacias(String tabla)                              //CUENTA LOS REGISTROS EN CADA TABLA PARA BUSCAR LAS TABALS VACÍAS
    {
        String consulta = "SELECT COUNT(*) FROM " + tabla;
        return consulta;
    }
    
    public static String ResultadosBajasInt()                                   //OBTIENE EL RESULTADO DE LAS INCIDENCIAS DE USUARIOS INTERNOS DADOS DE BAJA
    {
        String statement = "select NUM_EMP, USER_NAME, NOMBRE, FECHA_ACCESO, REGION, IP, PERFIL, NOMBRE_PERFIL, IDNUMEMP, IDPUESTO, IDGERENCIA, IDNOMBRE, IDFECHA, "
                + "IDESTATUS  from baajasint union select NUM_EMP, USER_NAME, NOMBRE, FECHA_ACCESO, REGION, IP, PERFIL, NOMBRE_PERFIL, IDNUMEMP, IDPUESTO, IDGERENCIA, "
                + "IDNOMBRE, IDFECHA, IDESTATUS  from nonominaint";
        return statement;
    }
    
    public static String AdministraUsuariosAdmin()               //OBTIENE EL RESULTADO DE LOS USUARIOS ADMINISTRADORES QUE FUERON PERMITIDOS A PESAR DE NO ESTAR PRESENTES EN NÓMINA DE USUARIOS INTERNOS O EXTERNOS
    {
        String statement = "select PERMITIDO, USUARIO, NOMBRE, APELLIDO, ROL, VALOR_AUTORIZACION  from AdminUsrAdminSAP";
        return statement;
    }
    
   
                     //****************SAP****************
    
    
    public static String ResultadosBajasIntSAP()                                   //OBTIENE EL RESULTADO DE LAS INCIDENCIAS DE USUARIOS INTERNOS DADOS DE BAJA
    {
        String statement = "SELECT Usuario, Nombre_Completo, Entrada_Sist, NUMEROEMPLEADO, IdUsuario,"
                + " PUESTO, Gerencia, Nombrecompleto, ESTATUS, FECHA FROM bajasintSAP";
        
        return statement;
    }
    
    public static String ResultadosBajasExtSAP()                                   //OBTIENE EL RESULTADO DE LAS INCIDENCIAS DE USUARIOS EXTERNOS DADOS DE BAJA
    {
        String statement = "SELECT Usuario, Nombre_Completo, Entrada_Sist, NUMEROEMPLEADO, IdUsuario,"
                + " PUESTO, Gerencia, Nombrecompleto, ESTATUS, FECHA FROM bajasextSAP";
        return statement;
    }
    
    public static String BorraInactividadIntSAP(String cadenaBD)                   //ELIMINA LOS USUARIOS INTERNOS QUE ESTÁN REGISTRADOS COMO INACTIVIDAD
    {
        String[] parametros = cadenaBD.split("\\|");
        String consulta = "DELETE FROM cruceintsap" + parametros[4] + " WHERE DATEDIFF('" + parametros[6] + "', Entrada_Sist) >= 61 "
                + "OR (DATEDIFF('" + parametros[6] +"', Fecha_Creacion) >= 61 AND Entrada_Sist IS NULL)";
        return consulta;
    }
    
    public static String BorraInactividadExtSAP(String cadenaBD)                   //ELIMINA LOS USUARIOS INTERNOS QUE ESTÁN REGISTRADOS COMO INACTIVIDAD
    {
        String[] parametros = cadenaBD.split("\\|");
        String consulta = "DELETE FROM cruceextsap" + parametros[4] +  " WHERE DATEDIFF('" + parametros[6] + "', Entrada_Sist) >= 61 "
                + "OR (DATEDIFF('" + parametros[6] +"', Fecha_Creacion) >= 61 AND Entrada_Sist IS NULL)";
        return consulta;
    }
    
    public static String ResultadosInactividadIntSAP()                             //OBTIENE EL RESULTADO DE LAS INCIDENCIAS DE USUARIOS INTERNOS INACTIVOS
    {
        String statement = "SELECT Usuario, Nombre_Completo, Fecha_creacion, Entrada_Sist, NUMEROEMPLEADO, IdUsuario,"
                + " PUESTO, Gerencia, Nombrecompleto from inactividadintsap";
        return statement;
    }
   
    public static String ResultadosInactividadExtSAP()                             //OBTIENE EL RESULTADO DE LAS INCIDENCIAS DE USUARIOS EXTERNOS INACTIVOS
    {
        String statement = "SELECT Usuario, Nombre_Completo, Fecha_creacion, Entrada_Sist, NUMEROEMPLEADO, IdUsuario,"
                + " PUESTO, Gerencia, Nombrecompleto FROM inactividadextSAP";
        return statement;
    } 
    
    
                            //*******************
    
    
    
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
            
            //externos, genericos y transfer
    
    public static String BorrarTransferTablaSAP(String cadenaBD)                                 //BORRA LA TABLA DE TRABAJO EXTERNOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "DELETE FROM USUARIOSSAP" + parametros[4] + " WHERE Usuario LIKE 'TR%'";
        return statement;
    }
    
    public static String BorrarExternosTablaSAP(String cadenaBD)                                 //BORRA LA TABLA DE TRABAJO EXTERNOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "DELETE FROM USUARIOSSAP" + parametros[4] + " WHERE Usuario LIKE 'EX%'";
        return statement;
    }
    
    public static String BorrarGenericosTablaSAP(String cadenaBD)                                 //BORRA LA TABLA DE TRABAJO INTERNOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "DELETE FROM USUARIOSSAP" + parametros[4] + " WHERE usuario LIKE '%A%' OR usuario LIKE '%B%'"
                + "OR usuario LIKE '%C%'"
                + "OR usuario LIKE '%D%'"
                + "OR usuario LIKE '%E%'"
                + "OR usuario LIKE '%F%'"
                + "OR usuario LIKE '%G%'"
                + "OR usuario LIKE '%H%'"
                + "OR usuario LIKE '%I%'"
                + "OR usuario LIKE '%J%'"
                + "OR usuario LIKE '%K%'"
                + "OR usuario LIKE '%L%'"
                + "OR usuario LIKE '%M%'"
                + "OR usuario LIKE '%N%'"
                + "OR usuario LIKE '%Ñ%'"
                + "OR usuario LIKE '%O%'"
                + "OR usuario LIKE '%P%'"
                + "OR usuario LIKE '%Q%'"
                + "OR usuario LIKE '%R%'"
                + "OR usuario LIKE '%S%'"
                + "OR usuario LIKE '%T%'"
                + "OR usuario LIKE '%U%'"
                + "OR usuario LIKE '%V%'"
                + "OR usuario LIKE '%W%'"
                + "OR usuario LIKE '%X%'"
                + "OR usuario LIKE '%Y%'"
                + "OR usuario LIKE '%Z%'";
        return statement;
    }
    
    public static String BorrarGenExt(String cadenaBD)                                 //BORRA LA TABLA DE TRABAJO EXTERNOS
    {
        String[] parametros = cadenaBD.split("\\|");
        String statement = "DELETE FROM ExternosSAP WHERE usuario LIKE '%A%' OR usuario LIKE '%B%'"
                + "OR usuario LIKE '%C%'"
                + "OR usuario LIKE '%D%'"             
                + "OR usuario LIKE '%F%'"
                + "OR usuario LIKE '%G%'"
                + "OR usuario LIKE '%H%'"
                + "OR usuario LIKE '%I%'"
                + "OR usuario LIKE '%J%'"
                + "OR usuario LIKE '%K%'"
                + "OR usuario LIKE '%L%'"
                + "OR usuario LIKE '%M%'"
                + "OR usuario LIKE '%N%'"
                + "OR usuario LIKE '%Ñ%'"
                + "OR usuario LIKE '%O%'"
                + "OR usuario LIKE '%P%'"
                + "OR usuario LIKE '%Q%'"
                + "OR usuario LIKE '%R%'"
                + "OR usuario LIKE '%S%'"
                + "OR usuario LIKE '%T%'"
                + "OR usuario LIKE '%U%'"
                + "OR usuario LIKE '%V%'"
                + "OR usuario LIKE '%W%'"
                + "OR usuario LIKE '%Y%'"
                + "OR usuario LIKE '%Z%'";
        return statement;
    }
    
    
    public static String ResultadosUsrExt()                             //OBTIENE EL RESULTADO DE LOS USUARIOS EXTERNOS
    {
        String statement = "select Usuario, Nombre_Completo, Grupo, Valido_de, Validez_a  FROM ExternosSAP";
        return statement;
    }
    
    public static String ResultadosUsrInt()                             //OBTIENE EL RESULTADO DE LOS USUARIOS INTERNOS 
    {
        String statement = "select Usuario, Nombre_Completo, Grupo, Valido_de, Validez_a  FROM InternosSAP";
        return statement;
    }
    
    public static String ResultadosUsrGen()                             //OBTIENE EL RESULTADO DE LOS USUARIOS GENERICOS 
    {
        String statement = "select Usuario, Nombre_Completo, Grupo, Valido_de, Validez_a FROM GenericosSAP";
        return statement;
    }
    
   
}

    