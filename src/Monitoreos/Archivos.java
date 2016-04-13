/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Monitoreos;
import java.io.*;
import java.sql.*;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
/**
 *
 * @author VS3XXBD
 */
public class Archivos 
{
    
    
    private static String eliminaChar(String linea, String cara)                //BUSCA UN CARACTER DETERMINADO EN LA LÍNEA Y LO ELIMINA
    {
        String nueva_cadena = "";
        Character caracter = null;
        boolean valido = true;
        for (int i=0; i<linea.length(); i++)
            {
             valido = true;
             for (int j=0; j<cara.length(); j++)
                 {
                  caracter = cara.charAt(j);

                  if (linea.charAt(i) == caracter)
                     {
                      valido = false;
                      break;
                     }
                 }
             if (valido)
                 nueva_cadena += linea.charAt(i);
            }

        return nueva_cadena;
    }
    

    public static String creaLineaExcepDuplicados(String linea)                 //Procesa las lineas del archivo de exceciones 
    {
        String numero, usuario, nombre, region, idperfil, perfil, fechaacceso, idpuesto, puesto, retorno, accesodia, accesomes, accesoano;
        linea = eliminaChar(linea, "\"");                                       //Elimina el caracter "
        linea = escapaChar(linea, "'");                                         //Escapa el caracter '
//        System.out.println(linea);
        String[] temp = linea.split("\t");                                      //Separa los datos de la linea delimitados por tabulador y los almacena en un arreglo
        numero = temp[0];                                                       //Obtiene el número de empleado del elemento 0 del arreglo
        usuario = temp[1];                                                      //Obtiene el UserID del elemento 1 del arreglo
        nombre = temp[5];                                                       //Obtiene el nombre del elemento 5 del arreglo
        region = temp[6];                                                       //Obtiene la región del elemento 6 del arreglo
        
        
        retorno = "('" + numero + "', '" + usuario + "', '" + nombre + "', '" + region + "')";
        return retorno;                                                         //Devuelve la línea para ser concatenada con las anteriores
    }
    
    public static String creaLineaMatrizPerfiles(String linea)                  //Procesa las lineas del archivo de matriz de perfiles
    {
        String gerencia, clave, puesto, perfil, puestofuncional, retorno;
        linea = eliminaChar(linea, "\"");                                       //Elimina el caracter "
        linea = escapaChar(linea, "'");                                         //Escapa el caracter '
//        System.out.println("Linea " + linea);
        String[] temp = linea.split("\t");                                      //Separa los datos de la linea delimitados por tabulador y los almacena en un arreglo           
        gerencia = temp[0];                                                     //Obtiene la gerencia del elemento 0 del arreglo
        clave = temp[1];                                                        //Obtiene la clave de puesto del elemento 1 del arreglo
        puesto = temp[2];                                                       //Obtiene el puesto del elemento 2 del arreglo
        perfil = temp[3];                                                       //Obtiene el perfil del elemento 3 del arreglo
        puestofuncional = temp[4];
        retorno = "('" + gerencia + "', '" + clave + "', '" + puesto + "', '" + perfil + "', '" + puestofuncional + "')";
        return retorno;                                                         //Devuelve la línea para ser concatenada con las anteriores
    }
    
    public static String lecturaMatrizPerfiles(String ruta, String cadenaBD)    //Lee el archivo de matriz de perfiles
    {
        String[] parametros = cadenaBD.split("\\|");                            //Separa los datos de la cadenaBD
        File archivo = null;                                                    //Crea el objeto archivo
        FileReader fr = null;                                                   //Crea el objeto del lector de archivo
        BufferedReader br = null;                                               //Crea el objeto del buffer
        String usuarios, linea, temp, primera;
        
        usuarios="";
        linea = "";
        
        try
        {
            archivo = new File(ruta);                                           //Inicializa los objetos de lectura
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            for(int i = 0; i<8; i++)
            {
                primera = br.readLine();                                        //Elimina el encabezado del archivo
            }
            while((temp=br.readLine()) != null && temp.length()>10)             //Verifica que la siguiente línea no esté vacía y la longitud sea mayor a 10
            {
//                System.out.println("lenght " + temp.length());
                linea = linea + "\n " + creaLineaMatrizPerfiles(temp) + ",";    //Concatena la cadena con la nueva línea procesada por el método creaLineaMatrizPerfiles()
            }
            linea=linea.substring(0, linea.length()-1);                         //Quita la última coma a la cadena
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally                                                                 //Regresa la línea
        {
            return linea;   
        }
        
    }
    
    public static String lecturaExcepDuplicados(String ruta, String cadenaBD)   //Lee el archivo de excepciones 
    {
        String[] parametros = cadenaBD.split("\\|");                            //Separa los datos de la cadenaBD y los guarda en un arreglo de cadenas
        File archivo = null;                                                    //Crea el objeto de archivo
        FileReader fr = null;                                                   //Crea el objeto lector
        BufferedReader br = null;                                               //Crea el objeto del buffer
        String usuarios, linea, temp, primera;
        
        usuarios="";
        linea = "";
        
        try
        {
            archivo = new File(ruta);                                           //Crea el objeto de archivo 
            fr = new FileReader(archivo);                                       //Crea el objeto del lector de archivo 
            br = new BufferedReader(fr);                                        //Crea el objeto de buffer para leer el archivo
            primera = br.readLine();                                            //Elimina la primer línea
            while((temp=br.readLine()) != null)                                 //Valida que la siguiente línea no esté vacía
            {
                linea = linea + "\n " + creaLineaExcepDuplicados(temp) + ",";   //Concatena la cadena linea con la nueva línea del archivo procesada por el método creaLineaExcepDuplicados()
                //System.out.println("Linea  " + creaLinea(temp));
            }
            linea=linea.substring(0, linea.length()-1);                         //Quita la última coma de la cadena
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally                                                                 //Reresa la cadena con todas las líenas del archivo
        {
            return linea;   
        }
        
    }
    
    
    public static String escapaChar(String linea, String cara)                 //CUANDO EL REGISTRO TIENE UN CARACTER QUE CAUSA CONFLICTO CON MYSQL ESCAPA EL CARACTER
    {
        String nueva_cadena = "";
        Character caracter = null;
        boolean valido = true;
        for (int i=0; i<linea.length(); i++)
            {
             valido = true;
             for (int j=0; j<cara.length(); j++)
                 {
                  caracter = cara.charAt(j);

                  if (linea.charAt(i) == caracter)
                     {
                      valido = false;
                      nueva_cadena += "\\";
                      nueva_cadena += linea.charAt(i);
                      break;
                     }
                 }
             if (valido)
                 nueva_cadena += linea.charAt(i);
            }

        return nueva_cadena;
    }
    
    
    private static String creaLineaIdInt(String linea)                          //CREA LA LÍNEA DE REGISTRO DE NÓMINA INTERNOS
    {
        String numero, usuario, nombre, region, gerencia, puesto, jefe, retorno, departamento, idpuesto, estatus, fecha, fechadia, fechames, fechaano;  //Variables para almacenar los datos de cada registro
        
        linea = eliminaChar(linea, "\"");                                       //Elimina el caracter " de los registros
        linea = escapaChar(linea, "'");                                         //Escapa el caracter '
        //System.out.println("Linea " + linea);
        String[] temp = linea.split("\t");                                      //Crea un arreglo con los datos del registro separados por el tablulador
//        for(int i=0; i<temp.length;i++)
//        {
//            System.out.println(i + " " + temp[i]);
//        }
        
        numero = temp[0];                                                       //Obtiene el número de empleado de la posición 0 del arreglo
        usuario = temp[1];                                                      //Obtiene el UserID de la posición 1 del arreglo
        nombre = temp[2];                                                       //Obtiene el nombre de la posición 2 del arreglo
        region = temp[7];                                                       //Obtiene la región de la posición 7 del arreglo
        gerencia = temp[13];                                                    //Obtiene la gerencia de la posición 13 del arreglo
        departamento = temp[15];                                                //Obtiene el departamento de la posición 15 del arreglo
        puesto = temp[17];                                                      //Obtiene el puesto de la posición 17 del arreglo
        idpuesto = temp[18];                                                    //Obtiene el id del puesto de la posición 18 del arreglo
        jefe = temp[21];                                                        //Obtiene el jefe inmediato de la posición 21 del arreglo
        estatus = temp[29];                                                     //Obtiene el estatus de la posición 29 del arreglo
        fecha = temp[28];                                                       //Obtiene el fecha de la posición 28 del arreglo
//        System.out.println("numemp " + numero + " usuario " + usuario + " nombre " + nombre + " region " + region + " gerencia " + gerencia + " departamento " + departamento 
//        + " idpuesto " + idpuesto + " jefe " + jefe + " estatus " + estatus + " fecha " + fecha);
        if(fecha.contains("null") || fecha.contains("Null") || fecha.contains("NULL"))
        {
            fecha = "";
        }
        if(!fecha.isEmpty())                                                    //Verifica si el campo de fecha se encuentra vacío
        {
            String [] fechac;                                                   //Si el campo no está vacío verifica si está separado por /
            if(fecha.contains("/"))
            {
                fechac = fecha.split("/");                                      //Separa los elementos de la fecha separados por / almacenándolos en un arreglo
            }
            else
            {
                fechac = fecha.split("-");                                      //Si no contiene / separa los elementos de la fecha por - almacenándolos en un arreglo
            }
            
            
            fechames = fechac[1];                                               //Obtiene el mes de la posición 1 del arreglo
            fechadia = fechac[0];                                               //Obtiene el día de la posición 0 del arreglo
            fechaano = fechac[2];                                               //Obtiene el año de la posición 2 del arreglo
            switch(fechames)                                                    //Cuando las fechas contienen como dato las iniciales del mes y no el número las convierte en número
            {
                case "ene":
                    fechames="01";
                    break;
                case "feb":
                    fechames="02";
                    break;
                case "mar":
                    fechames="03";
                    break;
                case "abr":
                    fechames="04";
                    break;
                case "may":
                    fechames="05";
                    break;
                case "jun":
                    fechames="06";
                    break;
                case "jul":
                    fechames="07";
                    break;
                case "ago":
                    fechames="08";
                    break;
                case "sep":
                    fechames="09";
                    break;
                case "oct":
                    fechames="10";
                    break;
                case "nov":
                    fechames="11";
                    break;
                case "dic":
                    fechames="12";
                    break;
                case "11":                                                      //para caso especifico
                    fechames="11";
                    break;
                default:                                                        //En caso de contener un mensaje diferente manda un mensaje de error
                    JOptionPane.showMessageDialog(null, "Se ha encontrado un error en la fecha del empleado " + numero + "   fecha: " + fecha + " dia " + fechadia + " mes " + fechames + " año " + fechaano + " en los archivos de nóminas de usuarios internos", "Error", JOptionPane.WARNING_MESSAGE);
                    System.out.println("Error en la fecha del empleado " + numero + "   fecha: " + fecha + " dia " + fechadia + " mes " + fechames + " año " + fechaano);
                    break;
            }
            fecha = "'" + fechaano + "-" + fechames + "-" + fechadia + "'";     //Establece el campo fecha en formato aaaa-mm-dd
        }
        else
        {
            fecha = "''";                                                       //Si el campo de fecha se encontraba vacío se establece el campo vacío para mysql
        }
        
        retorno = "('" + numero + "', '" + usuario + "', '" + nombre + "', '" + region + "', '" + gerencia + "', '" + departamento + "', '" + 
                puesto + "', '" + idpuesto + "', '" + jefe + "', '" + estatus + "', " + fecha + ")";        //Regresa la línea para ser insertada en la BD local
        return retorno;
    }
       
    
    private static String creaLineaIdExt(String linea)                          //CREA LA LÍNEA DE REGISTRO DE NÓMINA EXTERNOS
    {
        String numero, usuario, nombre, region, gerencia, puesto, jefe, retorno, idpuesto, departamento, estatus, fecha, fechadia, fechames, fechaano;  //Variables para almacenar los datos de cada registro
        Integer count = 0;
        
        linea = eliminaChar(linea, "\"");                                       //Elimina el caracter " de los registros
        linea = escapaChar(linea, "'");                                         //Escapa el caracter '
        String[] temp;  
        temp = linea.split("\t");                                               //Crea un arreglo con los datos del registro separados por el tablulador
//        for(int i=0; i<temp.length;i++)
//        {
//            System.out.println(i + " " + temp[i]);
//        }
  
        numero = temp[0];                                                       //Obtiene el número de empleado de la posición 0 del arreglo
        usuario = temp[1];                                                      //Obtiene el UserID de la posición 1 del arreglo
        nombre = temp[2];                                                       //Obtiene el nombre de la posición 2 del arreglo
        region = temp[7];                                                       //Obtiene la región de la posición 7 del arreglo
        gerencia = temp[11];                                                    //Obtiene la gerencia de la posición 11 del arreglo                               
        departamento = temp[15];                                                //Obtiene el departamento de la posición 15 del arreglo
        puesto = temp[17];                                                      //Obtiene el puesto de la posición 17 del arreglo
        idpuesto = temp[18];                                                    //Obtiene el id del puesto de la posición 18 del arreglo
        jefe = temp[21];                                                        //Obtiene el jefe inmediato de la posición 21 del arreglo
        estatus = temp[29];                                                     //Obtiene el estatus de la posición 29 del arreglo
        fecha = temp[28];                                                       //Obtiene el fecha de la posición 28 del arreglo
        if(numero.contains("CIFRA"))                                            //Verifica si el primer elemento contiene la palabra CIFRA, en caso de tenerla es un renglón que incluye la cifra de control y es excluido poniendo todos los campos como cadenea vacía
        {
            numero = "";
            usuario = "";
            nombre = "";
            region = "";
            gerencia = "";
            departamento = "";
            puesto = "";
            idpuesto = "";
            jefe = "";
            estatus = "";
            fecha = "";
        }
        if(numero.contains("NUM"))                                              //Verifica si el primer elemento contiene la palabra NUM, en caso de tnerla en un renglón de encabezado repetido que es excluido pondiendo todos los campos como cadena vacía
        {
            numero = "";
            usuario = "";
            nombre = "";
            region = "";
            gerencia = "";
            departamento = "";
            puesto = "";
            idpuesto = "";
            jefe = "";
            estatus = "";
            fecha = "";
        }
        
        if(!fecha.isEmpty())                                                    //Verifica si el campo de fecha se encuentra vacío
        {
            String [] fechac;
            if(fecha.contains("/"))                                             //Si el campo no está vacío verifica si está separado por /
            {
                fechac = fecha.split("/");                                      //Separa los elementos de la fecha separados por / almacenándolos en un arreglo
            }
            else
            {
                fechac = fecha.split("-");                                      //Si no contiene / separa los elementos de la fecha por - almacenándolos en un arreglo
            }
            
            
            fechames = fechac[1];                                               //Obtiene el mes de la posición 1 del arreglo
            fechadia = fechac[0];                                               //Obtiene el dia de la posición 0 el arreglo
            fechaano = fechac[2];                                               //Obtiene el maño de la posición 2 del arreglo
            switch(fechames)                                                    //Cuando las fechas contienen como dato las iniciales del mes y no el número las convierte en número
            {
                case "ene":
                    fechames="01";
                    break;
                case "feb":
                    fechames="02";
                    break;
                case "mar":
                    fechames="03";
                    break;
                case "abr":
                    fechames="04";
                    break;
                case "may":
                    fechames="05";
                    break;
                case "jun":
                    fechames="06";
                    break;
                case "jul":
                    fechames="07";
                    break;
                case "ago":
                    fechames="08";
                    break;
                case "sep":
                    fechames="09";
                    break;
                case "oct":
                    fechames="10";
                    break;
                case "nov":
                    fechames="11";
                    break;
                case "dic":
                    fechames="12";
                    break;
                case "01":
                    fechames="01";
                    break;
                case "02":
                    fechames="02";
                    break;
                case "03":
                    fechames="03";
                    break;
                case "04":
                    fechames="04";
                    break;
                case "05":
                    fechames="05";
                    break;
                case "06":
                    fechames="06";
                    break;
                case "07":
                    fechames="07";
                    break;
                case "08":
                    fechames="08";
                    break;
                case "09":
                    fechames="09";
                    break;
                case "10":
                    fechames="10";
                    break;
                case "11":
                    fechames="11";
                    break;
                case "12":
                    fechames="12";
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Se ha encontrado un error en la fecha del empleado " + numero + "   fecha: " + fecha + " dia " + fechadia + " mes " + fechames + " año " + fechaano + " en los archivos de nóminas de usuarios internos", "Error", JOptionPane.WARNING_MESSAGE);
                    System.out.println("Error en la fecha del empleado " + numero + "   fecha: " + fecha + " dia " + fechadia + " mes " + fechames + " año " + fechaano);
                    break;
            }
            fecha = "'" + fechaano + "-" + fechames + "-" + fechadia + "'";     //Establece el campo fecha en formato aaaa-mm-dd
        }
        else
        {
            fecha = "'0000-00-00'";                                             //Establece el campo de la fecha como 0000-00-00
        }
        
        //System.out.println( numero + " " + idpuesto);
        
        retorno = "('" + numero + "', '" + usuario + "', '" + nombre + "', '" + region + "', '" + gerencia + "', '"  + departamento + "', '" 
                + puesto + "', '"  + idpuesto + "', '" + jefe + "', '" + estatus + "', " + fecha + ")";     //Regresa la línea para ser insertada en la BD local
        return retorno;
    }
       
    
    private static String creaLinea(String linea)                               //CREA LINEA DE REGISTRO DE USUARIOS DE ACTIVE DIRECTORY
    {
        String activo, crea, desc, dominio, nombre, log, puesto, user, retorno, creaano, creames, creadia, logano, logmes, logdia;
//        System.out.println(linea);
        linea = eliminaChar(linea, "\"");                                       //Elimina el caracter /
        linea = escapaChar(linea, "'");                                         //Escapa el caracter '
        String[] temp;
        temp = linea.split("\t");                                               //Separa los campos del registro marcados por tabuladores insertándolos en un arreglo
//        for(int i=0; i<temp.length;i++)
//        {
//            System.out.println(i + " " + temp[i]);
//        }
        
//        System.out.println("\n \n \n \n \n \n \n \n \n ");
        activo = temp[0];                                                       //Obtiene el campo activo de la posición 0 del arreglo
        if(activo.contains("FALSO")){                                           //Valida si el valor es verdadero o falso y crea false o true para insertarlo en mysql
            activo = "False";
        }
        if (activo.contains("VERDADERO"))
        {
            activo = "True";
        }
        crea = temp[1];                                                         //Obtiene el campo de fecha de creación de la posición 1 del arreglo
        desc = temp[2];                                                         //Obtiene el campo descripción de la posición 2 del arrelgo
        dominio = temp[3];                                                      //Obtiene el campo dominio de la posición 3 del arreglo
        nombre = temp[4];                                                       //Obtiene el campo nombre de la posición 4 del arreglo
        log = temp[5];                                                          //Obtiene el campo fecha de logeo de la posición 5 del arreglo
        puesto = temp[6];                                                       //Obtiene el campo puesto de la posición 6 del arreglo
        user = temp[7];                                                         //Obtiene el campo UserID de la posición 7 del arreglo
        if(!crea.isEmpty())                                                     //Valida si la fecha de creación está vacía
        {
            String [] fechac;                                                   //Crea un arreglo para almacenar los datos de la fecha
            fechac = crea.split(" ");                                           //Separa los datops de la fecha por el caracter espacio para separar la fecha de la hora según el formato de origen
            crea = fechac[0];                                                   //Obtiene el campo de la fecha de la posición 0 del arreglo
            String [] fechap;       
            fechap = crea.split("/");                                           //Separa los datos de la fecha por el caracter /
            creames = fechap[0];                                                //Obtiene el campo mes de la posición 0 del arreglo
            creadia = fechap[1];                                                //Obtiene el campo dia de la posición 1 del arreglo
            creaano = fechap[2];                                                //Obtiene el campo año de la posición 2 del arreglo
            crea = "'" + creaano + "-" + creames + "-" + creadia + "'";         //Devuelve el campo de la fecha en formato aaaa-mm-dd
        }
        else
        {
            crea = "''";                                                        //Si el campo estaba vacío devuelve una cadena vacía
        }
        
        if(!log.isEmpty())                                                      //Valida si la fecha de logeo se encuentra vacía
        {
            String [] fechal;                                                   //Crea un arreglo para almacenar los datos de la fecha
            fechal = log.split(" ");                                            //Separa los datops de la fecha por el caracter espacio para separar la fecha de la hora según el formato de origen                  
            log = fechal[0];                                                    //Obtiene el campo de la fecha de la posición 0 del arreglo
            String [] fechalp;                                                  //Crea un arreglo para almacenar los datos de la fecha
            fechalp = log.split("/");                                           //Separa los datos de la fecha por el caracter /    
            logmes = fechalp[0];                                                //Obtiene el campo mes de la posición 0 del arreglo
            logdia = fechalp[1];                                                //Obtiene el campo dia de la posición 1 del arreglo
            logano = fechalp[2];                                                //Obtiene el campo año de la posición 2 del arreglo
            log = "'" + logano + "-" + logmes + "-" + logdia + "'";             //Devuelve el campo de la fecha en formato aaaa-mm-dd
        }
        else
        {
            log = "NULL";                                                       //Si el campo de fecha está vacío se establece el campo de fecha como nulo
        }
        
        
        retorno = "(" + activo + " , " + crea + ", '" + desc + "', '" + dominio + "', '" + nombre + "', " + log + ", '" + puesto + "', '" + user + "')";
        System.out.println(retorno);
        return retorno;                                                         //Regresa la línea de usuario para ser insertada en mysql
    }
    
    
    public static void lecturaUsuariosIdInt(String ruta, String bd )            //LEE EL ARCHIVO DE NOMINA INTERNOS
    {
        String[] parametros = bd.split("\\|");                                  //SEPARA LOS ELEMENTOS DE LA CADENA HASTA SU REFERENCIA DE CORTE
        File archivo = null;                                                    //Crea el objeto del archivo vacío
        FileReader fr = null;                                                   //Crea el objeto del lector de archivos vacío
        BufferedReader br = null;                                               //Crea el bufer de lectura vacío
        String usuarios, linea, temp, primera;                                  
        
        usuarios="";
        linea = "";
        //System.out.println("linea " + linea);
        
        

        try
        {
            archivo = new File(ruta);                                           //Se establecen los parámetros para la lectura del archivo
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            Monitoreos.Tablas.eliminaTablaInt(bd);                              //Elimina la tabla de nómina internos en caso de existir
            Monitoreos.Tablas.idCreaIntTabla(bd);                               //Crea una nueva tabla de nómina internos
            primera = br.readLine();                                            //Retira el encabezado del archivo

            while((temp=br.readLine()) != null)                                 //Valida que la siguiente línea del archivo no esté vacía
            {
                if(temp.length()>80)                                            //Valida que la longitud de la línea sea mayor a 80 para descartar líneas con basura
                {
                    if(temp.contains("NUM EMP"))                                //Valida si contiene Num_EMP la línea para descartarla
                    {
                        
                    }
                    else
                    {
                        //System.out.println("temp length " + temp.length());
//                        System.out.println("\n temp = " + temp);
                        //System.out.println("Linea antes " + linea);
                        linea = linea + "\n " + creaLineaIdInt(temp) + ",";     //Almacena la concatenación de la cadena con la siguiente linea que se elabora en el método creaLineaIdInt()
                        //System.out.println("Linea  " + creaLinea(temp));
                    }
                    
                }
                
            }
            //System.out.println("Salida = " + linea);
            linea=linea.substring(0, linea.length()-1);                         //Elimina la última coma de la cadena
            Monitoreos.Tablas.idIntTabla(linea, bd);                            //Insera los registros en la tabla correspondiente
        }
//        catch(SQLException sqle)
//        {
//            sqle.printStackTrace();
//        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally                                                                 //Finaliza la lectura del archivo
        {
            try
            {
                if(fr != null)
                {
                    fr.close();
                }
            }
            catch(Exception e2)
            {
                e2.printStackTrace();
            }
        }
        
    }
    
    
    public static void lecturaUsuariosIdExt(String ruta, String bd )            //LEE EL ARCHIVO DE NOMINA EXTERNOS
    {
        
        String[] parametros = bd.split("\\|");                                  //SEPARA LOS ELEMENTOS DE LA CADENA HASTA SU REFERENCIA DE CORTE
        File archivo = null;                                                    //Crea el objeto del archivo vacío
        FileReader fr = null;                                                   //Crea el objeto del lector de archivo vacío
        BufferedReader br = null;                                               //Crea el bufer de lectura vacío
        String usuarios, linea, temp, primera;
        
        usuarios="";
        linea = "";
        //System.out.println("linea " + linea);
        Integer contador = 0;
        

        try                                                                     //Se establecen los parámetros para la lectura del archivo
        {
            archivo = new File(ruta);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            
            Monitoreos.Tablas.eliminaTablaExt(bd);                              //Elimina la tabla de nómina externos en caso de existir
            Monitoreos.Tablas.idCreaExtTabla(bd);                               //Crea una nueva tabla de nómina externos
            primera = br.readLine();                                            //Elimina el encabezado del archivo

            while((temp=br.readLine()) != null)                                 //Valida que la siguiente línea del archivo no esté vacía                   
            {
                
                if(temp.length()>80)                                            //Valida que la longitud de la línea sea mayor a 80 para descartar líneas con basura
                {
                    if(temp.contains("NUM EMP"))                                //Valida si contiene Num_EMP la línea para descartarla
                    {
                        
                    }
                    else
                    {
                    linea = linea + "\n " + creaLineaIdExt(temp) + ",";         //Almacena la concatenación de la cadena con la siguiente linea que se elabora en el método creaLineaIdInt()
                    //System.out.println("Linea: " + linea);
                    //System.out.println("Linea  " + creaLinea(temp));
                    contador++;
                    if(contador==2000)                                          //Para no ingresar instrucciones demasiado largas al servidor de mysql se envía
                    {                                                           //una instrucción al servidort cuando alcanza las 2000 lineas
                        linea=linea.substring(0, linea.length()-1);             //Para que no marque error se quita la última coma del bloque
                        Monitoreos.Tablas.idExtTabla(linea, bd);                //Se ejecuta la instrucción
                        contador =0;                                            //Se restablece el cnotador para crear otro bloque de 2000 líneas
                        linea="";                                               //La cadena linea vuelte a estar vacía
                    }
                    }
                }
                
            }
            //System.out.println("Salida = " + linea);
            linea=linea.substring(0, linea.length()-1);
            Monitoreos.Tablas.idExtTabla(linea, bd);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(fr != null)
                {
                    fr.close();
                }
            }
            catch(Exception e2)
            {
                e2.printStackTrace();
            }
        }
        
    }
    
    
    public static void lecturaUsuarios (String ruta, int reg, String bd )
    {
        String[] parametros = bd.split("\\|");                                  //Separa los elementos de la cadena de BD y los almacena en el arreglo
        
        File archivo = null;                                                    //Crea el objeto del archivo vacío
        FileReader fr = null;                                                   //Crea el objeto del lector de archivos vacío
        BufferedReader br = null;                                               //Crea el bufer de lectura vacío
        String usuarios, linea, temp, primera;
        
       usuarios="";
        linea = "";
        //System.out.println("linea " + linea);
        
        try                                                                     //Se establecen los parámetros para la lectura del archivo
        {
            archivo = new File(ruta);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            primera=br.readLine();                                              //Elimina la tabla de nómina internos en caso de existir
//            System.out.println(primera);
            while((temp=br.readLine()) != null)                                 //Valida que la siguiente línea del archivo no esté vacía
            {
//                System.out.println(temp);
                linea = linea + "\n " + creaLinea(temp) + ",";                  //Almacena la concatenación de la cadena con la siguiente linea que se elabora en el método creaLinea()
                //System.out.println("Linea  " + creaLinea(temp));
            }
            //System.out.println("Salida = " + linea);
            linea=linea.substring(0, linea.length()-1);                         //Quita la última coma
            //System.out.println("Linea " + linea);
            Monitoreos.Tablas.usuariosTabla(reg, linea, bd);                    //Crea la tabla e inserta los usuarios que son enviados mediante la cadena linea
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally                                                                 //Cierra lector de archivo
        {
            try
            {
                if(fr != null)
                {
                    fr.close();
                }
            }
            catch(Exception e2)
            {
                e2.printStackTrace();
            }
        }
        
    }
    
    public static String lecturaUsuariosAdmin(String ruta, String bd )            //LEE EL ARCHIVO DE NOMINA INTERNOS
    {
        String[] parametros = bd.split("\\|");                                  //SEPARA LOS ELEMENTOS DE LA CADENA HASTA SU REFERENCIA DE CORTE
        File archivo = null;                                                    //Crea el objeto del archivo vacío
        FileReader fr = null;                                                   //Crea el objeto del lector de archivos vacío
        BufferedReader br = null;                                               //Crea el bufer de lectura vacío
        String usuarios, linea, temp, primera;                                  
        
        usuarios="";
        linea = "";
        //System.out.println("linea " + linea);
        try
        {
            archivo = new File(ruta);                                           //Se establecen los parámetros para la lectura del archivo
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
                                       
                                                //Crea una nueva tabla de usuarios administradores
            
            for (int i=0;i<8;i++)                                               //Retira el encabezado del archivo 8 lineas
            {
                primera = br.readLine();                
            }                                           

            while((temp=br.readLine()) != null)                                 //Valida que la siguiente línea del archivo no esté vacía
            {
                
                if(temp.contains("----"))                                       //Valida si contiene guiones en la línea para descartarla
                {

                }
                else
                {
                    // System.out.println("temp = " + temp);
                    //System.out.println("Linea antes " + linea);
                    linea = linea + "\n " + creaLineaUsrAdmin(temp) + ",";     //Almacena la concatenación de la cadena con la siguiente linea que se elabora en el método creaLineaIdInt()
                    //System.out.println("Linea  " + creaLinea(temp));
                }                                      
            }
//            System.out.println("length " + linea.length());
            linea = linea.substring(0, linea.length()-4);                         //Elimina la última coma de la cadena
//            System.out.println("Salida = " + linea);   
            
        }
//        catch(SQLException sqle)
//        {
//            sqle.printStackTrace();
//        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally                                                                 //Finaliza la lectura del archivo
        {
            
            try
            {
                if(fr != null)
                {
                    fr.close();
                }
            }
            catch(Exception e2)
            {
                e2.printStackTrace();
            }
            return linea;
        }
        
    }
    
    public static String lecturaUsuariosDemonsa2(String ruta, String bd )            //LEE EL ARCHIVO DE NOMINA INTERNOS
    {
        String[] parametros = bd.split("\\|");                                   //SEPARA LOS ELEMENTOS DE LA CADENA HASTA SU REFERENCIA DE CORTE
        File archivo = null;                                                    //Crea el objeto del archivo vacío
        FileReader fr = null;                                                   //Crea el objeto del lector de archivos vacío
        BufferedReader br = null;                                               //Crea el bufer de lectura vacío
        String usuarios, linea, temp, primera;                                  
        
        usuarios="";
        linea = "";
//        System.out.println("linea " + linea);
        
        

        try
        {
            archivo = new File(ruta);                                           //Se establecen los parámetros para la lectura del archivo
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);            
            
            primera = br.readLine();                
                                     

            while((temp=br.readLine()) != null)                                 //Valida que la siguiente línea del archivo no esté vacía
            {
                    
//                System.out.println("temp = " + temp);
                //System.out.println("Linea antes " + linea);
                linea = linea + "\n " + creaLineaDemonsa2(temp) + ",";     //Almacena la concatenación de la cadena con la siguiente linea que se elabora en el método creaLineaIdInt()
                //System.out.println("Linea  " + creaLinea(temp));

            }
//            System.out.println("length " + linea.length());
            linea = linea.substring(0, linea.length()-1);                         //Elimina la última coma de la cadena
//            System.out.println("Salida = " + linea);
           
        }
//        catch(SQLException sqle)
//        {
//            sqle.printStackTrace();
//        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally                                                                 //Finaliza la lectura del archivo
        {
            try
            {
                if(fr != null)
                {
                    fr.close();
                }
            }
            catch(Exception e2)
            {
                e2.printStackTrace();
            }
            return linea;
        }
        
    }
    
    
    private static String creaLineaUsrAdmin(String linea)                          //CREA LA LÍNEA DE REGISTRO DE USUARIOS ADMINISTRADORES
    {
        String usuario, nombre, apellido, rol, val_auto, retorno;                         //Variables para almacenar los datos de cada registro
        
        //System.out.println("Linea " + linea);
        String[] temp = linea.split("\\|");                                      //Crea un arreglo con los datos del registro separados por el pipe
//        for(int i=0; i<temp.length;i++)
//        {
//            System.out.println(i + " " + temp[i]);
//        }
        
        if(temp[1].contains("*"))
        {
            retorno = "";
        }
        else
        {
            usuario = temp[1];                                                      //Obtiene el Usuario de la posición 1 del arreglo
            nombre = temp[2];                                                       //Obtiene el nombre de la posición 2 del arreglo
            rol = temp[4];                                                         //Obtiene la rol de la posición 4 del arreglo
            apellido = temp[3];                                                    //Obtiene la apellido de la posición 3 del arreglo
            val_auto = temp[7];                                                     //Obtiene el valor de autorizacion de la posición 7 del arreglo
            usuario = usuario.trim();
            nombre = nombre.trim();
            rol = rol.trim();                                                //Obtiene la rol de la posición 4 del arreglo
            apellido = apellido.trim();                                                    //Obtiene la apellido de la posición 3 del arreglo
            val_auto = val_auto.trim();    
            retorno = "('" + usuario + "', '" + nombre + "', '" + apellido + "', '" + rol + "', '" + val_auto + "')"; //Regresa la línea para ser insertada en la BD local
        }
 
        return retorno;
    }
    
    private static String creaLineaDemonsa2(String linea)                          //CREA LA LÍNEA DE REGISTRO DE USUARIOS ADMINISTRADORES
    {
        String numemp, nombre, idpuesto, puesto, gerencia, region, retorno;                         //Variables para almacenar los datos de cada registro
        
        //System.out.println("Linea " + linea);
        String[] temp = linea.split("\t");                                      //Crea un arreglo con los datos del registro separados por el pipe
//        for(int i=0; i<temp.length;i++)
//        {
//            System.out.println(i + " " + temp[i]);
//        }
        
        
        numemp = temp[0];                                                       //Obtiene el Usuario de la posición 1 del arreglo
        nombre = temp[1];                                                       //Obtiene el nombre de la posición 2 del arreglo
        idpuesto = temp[2];                                                     //Obtiene la rol de la posición 4 del arreglo
        puesto = temp[3];                                                       //Obtiene la apellido de la posición 3 del arreglo
        puesto = Monitoreos.Archivos.escapaChar(puesto, "\\'");
        gerencia = temp[4];                                                     //Obtiene el valor de autorizacion de la posición 7 del arreglo
        gerencia = Monitoreos.Archivos.escapaChar(gerencia, "\\'");
        region = temp[5];
        retorno = "('" + numemp + "', '" + nombre + "', '" + idpuesto + "', "
                + "'" + puesto + "', '" + gerencia +  "', '" + region + "')";   //Regresa la línea para ser insertada en la BD local

 
        return retorno;
    }
    
    private static String creaLineaUsrSAP(String linea)                          //CREA LA LÍNEA DE REGISTRO DE USUARIOS ADMINISTRADORES
    {
        String Usuario, Nombre_Completo, Grupo, Bloq, Valido_de, Validez_a, retorno, fechadia, fechames, fechaano;                         //Variables para almacenar los datos de cada registro
//        System.out.println("Linea " + linea);
        String[] temp = linea.split("\\|");                                      //Crea un arreglo con los datos del registro separados por el pipe
//        for(int i=0; i<temp.length;i++)                                             
//        {
//            System.out.println(i + " " + temp[i]);
//        }
//        System.out.println("\n\n\n");
        if(temp[1].contains("*"))
        {
            retorno = "";
        }
        else
        {
            Usuario = temp[1];                                                      //Obtiene el Usuario de la posición 1 del arreglo
//            System.out.println("Usr" + Usuario);
            Nombre_Completo = temp[2];                                                       //Obtiene el nombre de la posición 2 del arreglo
//            System.out.println("Nom " + Nombre_Completo);
            Grupo = temp[3];                                                         //Obtiene el grupo de la posición 3 del arreglo
//            System.out.println("Gpo " + Grupo);
            Bloq = temp[5];                                                    //Obtiene bloq de la posición 5 del arreglo
//            System.out.println("Bloq " + Bloq);
            Valido_de = temp[7];                                                     //Obtiene valido la posición 7 del arreglo
//            System.out.println("De " + Valido_de);
            Validez_a = temp[8];
//            System.out.println("A " + Validez_a);
            Usuario = Usuario.trim();                                 //ELIMINAR LOS ESPACIOS ENTRE CAMPOS
            Grupo = Grupo.trim();
            Bloq = Bloq.trim();
            Valido_de = Valido_de.trim();
            Validez_a = Validez_a.trim();
                
        if(!Valido_de.isEmpty())                                                    //Verifica si el campo de fecha se encuentra vacío
        {
//            System.out.println("entré");
            String [] fechac;                                                   //Si el campo no está vacío verifica si está separado por /
            
            fechac = Valido_de.split("\\.");                                      //Separa los elementos de la fecha separados por / almacenándolos en un arreglo
            //System.out.println("fechac " + fechac.length + "   " + fechac.toString());
//            for(int w = 0; w<3; w++)
//            {
//                System.out.println(w + "  fecha " + fechac[w]);
//            }
            fechames = fechac[1];                                               //Obtiene el mes de la posición 1 del arreglo
            fechadia = fechac[0];                                               //Obtiene el día de la posición 0 del arreglo
            fechaano = fechac[2];                                               //Obtiene el año de la posición 2 del arreglo
            
            Valido_de = "'" + fechaano + "-" + fechames + "-" + fechadia + "'";     //Establece el campo fecha en formato aaaa-mm-dd
        }
        else
        {
//            System.out.println("else");
            Valido_de = "''";                                                       //Si el campo de fecha se encontraba vacío se establece el campo vacío para mysql
        }
        
        if(!Validez_a.isEmpty())                                                    //Verifica si el campo de fecha se encuentra vacío
        {
            String [] fechac;                                                   //Si el campo no está vacío verifica si está separado por /
//            System.out.println("Valido a " + Validez_a);
            fechac = Validez_a.split("\\.");                                      //Separa los elementos de la fecha separados por / almacenándolos en un arreglo
//            for(int w = 0; w<3; w++)
//            {
//                System.out.println(w + "  fecha " + fechac[w]);
//            }
                   
            fechames = fechac[1];                                               //Obtiene el mes de la posición 1 del arreglo
            fechadia = fechac[0];                                               //Obtiene el día de la posición 0 del arreglo
            fechaano = fechac[2];                                               //Obtiene el año de la posición 2 del arreglo
            
            Validez_a = "'" + fechaano + "-" + fechames + "-" + fechadia + "'";     //Establece el campo fecha en formato aaaa-mm-dd
        }
        else
        {
            Validez_a = "''";                                                       //Si el campo de fecha se encontraba vacío se establece el campo vacío para mysql
        }
        
            
            retorno = "('" + Usuario + "', '" + Nombre_Completo + "', '" + Grupo + "', '" + Bloq + "', " + Valido_de + ", " + Validez_a + " )"; //Regresa la línea para ser insertada en la BD local
        }
//        System.out.println("Retorno " + retorno);
        return retorno;
    }
    
    
    private static String creaLineaFechasAcceso(String linea)                          //CREA LA LÍNEA DE REGISTRO DE USUARIOS ADMINISTRADORES
    {
        String Usuario, Grupo, Creado_por, Fecha_creacion, Valido_de, Fin_validez, Entrada_Sist, Clave_acc, Bloqueo, retorno, fechames, fechaano, fechadia;                         //Variables para almacenar los datos de cada registro
//        System.out.println("Linea " + linea);
        String[] temp = linea.split("\\|");                                      //Crea un arreglo con los datos del registro separados por el pipe
//        for(int i=0; i<temp.length;i++)                                             
//        {
//            System.out.println(i + " " + temp[i]);
//        }
//        System.out.println("\n\n\n");
        if(temp[1].contains("*"))
        {
            retorno = "";
        }
        else
        {
            Usuario = temp[1];                                                      //Obtiene el Usuario de la posición 1 del arreglo
            Grupo = temp[2];                                                        //Obtiene el grupo de la posición 2 del arreglo
            Creado_por = temp[4];                                                   //Obtiene el creado de la posición 4 del arreglo
            Fecha_creacion = temp[5];                                                    
            Valido_de = temp[6];                                                     
            Fin_validez = temp[7];
            Entrada_Sist = temp[8];
            Clave_acc = temp[11];
            Bloqueo = temp[13];
            
            Usuario = Usuario.trim();                                           //ELIMINAR LOS ESPACIOS ENTRE CAMPOS
            Grupo = Grupo.trim();
            Fecha_creacion = Fecha_creacion.trim();
            Valido_de = Valido_de.trim();
            Fin_validez = Fin_validez.trim();
            Entrada_Sist = Entrada_Sist.trim();
            Clave_acc = Clave_acc.trim();
            Bloqueo = Bloqueo.trim();
            
            
        //validacion de fechas
                
        if(!Fecha_creacion.isEmpty())                                           //Verifica si el campo de fecha se encuentra vacío
        {
            String [] fechac;                                                   //Si el campo no está vacío verifica si está separado por /            
            fechac = Fecha_creacion.split("\\.");                                      //Separa los elementos de la fecha separados por / almacenándolos en un arreglo 
            fechames = fechac[1];                                               //Obtiene el mes de la posición 1 del arreglo
            fechadia = fechac[0];                                               //Obtiene el día de la posición 0 del arreglo
            fechaano = fechac[2];                                               //Obtiene el año de la posición 2 del arreglo
            
            Fecha_creacion = "'" + fechaano + "-" + fechames + "-" + fechadia + "'";     //Establece el campo fecha en formato aaaa-mm-dd
        }
        else
        {    
            Fecha_creacion = "NULL";                                                       //Si el campo de fecha se encontraba vacío se establece el campo vacío para mysql
        }
        
        if(!Valido_de.isEmpty())                                                    //Verifica si el campo de fecha se encuentra vacío
        {
            String [] fechac;                                                   //Si el campo no está vacío verifica si está separado por /            
            fechac = Valido_de.split("\\.");                                      //Separa los elementos de la fecha separados por / almacenándolos en un arreglo 
            fechames = fechac[1];                                               //Obtiene el mes de la posición 1 del arreglo
            fechadia = fechac[0];                                               //Obtiene el día de la posición 0 del arreglo
            fechaano = fechac[2];                                               //Obtiene el año de la posición 2 del arreglo
            
            Valido_de = "'" + fechaano + "-" + fechames + "-" + fechadia + "'";     //Establece el campo fecha en formato aaaa-mm-dd
        }
        else
        {    
            Valido_de = "NULL";                                                       //Si el campo de fecha se encontraba vacío se establece el campo vacío para mysql
        }
        
        
        if(!Fin_validez.isEmpty())                                                    //Verifica si el campo de fecha se encuentra vacío
        {
            String [] fechac;                                                   //Si el campo no está vacío verifica si está separado por /  
            fechac = Fin_validez.split("\\.");                                      //Separa los elementos de la fecha separados por / almacenándolos en un arreglo
            fechames = fechac[1];                                               //Obtiene el mes de la posición 1 del arreglo
            fechadia = fechac[0];                                               //Obtiene el día de la posición 0 del arreglo
            fechaano = fechac[2];                                               //Obtiene el año de la posición 2 del arreglo
            
            Fin_validez = "'" + fechaano + "-" + fechames + "-" + fechadia + "'";     //Establece el campo fecha en formato aaaa-mm-dd
        }
        else
        {
            Fin_validez = "NULL";                                                       //Si el campo de fecha se encontraba vacío se establece el campo vacío para mysql
        }
        
       if (Entrada_Sist.contains("No utilizado"))
       {
           Entrada_Sist = "";    
       }
           
        if(!Entrada_Sist.isEmpty())                                                    //Verifica si el campo de fecha se encuentra vacío
        {
            String [] fechac;                                                   //Si el campo no está vacío verifica si está separado por /  
            fechac = Entrada_Sist.split("\\.");                                      //Separa los elementos de la fecha separados por / almacenándolos en un arreglo
            fechames = fechac[1];                                               //Obtiene el mes de la posición 1 del arreglo
            fechadia = fechac[0];                                               //Obtiene el día de la posición 0 del arreglo
            fechaano = fechac[2];                                               //Obtiene el año de la posición 2 del arreglo
            
            Entrada_Sist = "'" + fechaano + "-" + fechames + "-" + fechadia + "'";     //Establece el campo fecha en formato aaaa-mm-dd
        }
        else
        {
            Entrada_Sist = "NULL";                                                       //Si el campo de fecha se encontraba vacío se establece el campo vacío para mysql
        }
        
        if(!Clave_acc.isEmpty())                                                    //Verifica si el campo de fecha se encuentra vacío
        {
            String [] fechac;                                                   //Si el campo no está vacío verifica si está separado por /  
            fechac = Clave_acc.split("\\.");                                      //Separa los elementos de la fecha separados por / almacenándolos en un arreglo
            fechames = fechac[1];                                               //Obtiene el mes de la posición 1 del arreglo
            fechadia = fechac[0];                                               //Obtiene el día de la posición 0 del arreglo
            fechaano = fechac[2];                                               //Obtiene el año de la posición 2 del arreglo
            
            Clave_acc = "'" + fechaano + "-" + fechames + "-" + fechadia + "'";     //Establece el campo fecha en formato aaaa-mm-dd
        }
        else
        {
            Clave_acc = "NULL";                                                       //Si el campo de fecha se encontraba vacío se establece el campo vacío para mysql
        }
        
            
            retorno = "('" + Usuario + "', '" + Grupo + "', '" + Creado_por + "', " + Fecha_creacion + ", " + Valido_de + ", " 
                    + Fin_validez +  "," + Entrada_Sist + "," + Clave_acc + ",'" + Bloqueo + "' )"; //Regresa la línea para ser insertada en la BD local
        }
        return retorno;
    }
    
    
    private static String creaLineaUsrPerfil(String linea)                          //CREA LA LÍNEA DE REGISTRO DE USUARIO PERFIL
    {
        String Usuario, Nombre, Apellido, Grupo, Rol, Descripcion_Rol, Fecha_inicio, Fecha_fin, retorno, fechames, fechaano, fechadia;                         //Variables para almacenar los datos de cada registro
//        System.out.println("Linea " + linea);
        String[] temp = linea.split("\\|");                                      //Crea un arreglo con los datos del registro separados por el pipe
//        for(int i=0; i<temp.length;i++)                                             
//        {
//            System.out.println(i + " " + temp[i]);
//        }
//        System.out.println("\n\n\n");
        if(temp[1].contains("*"))
        {
            retorno = "";
        }
        else
        {
            Usuario = temp[1];                                                      //Obtiene el Usuario de la posición 1 del arreglo
            Nombre = temp[2];                                                        //Obtiene el nombre de la posición 2 del arreglo
            Apellido = temp[3]; 
            Grupo = temp[4]; 
            Rol = temp[5]; 
            Descripcion_Rol = temp[6]; 
            Fecha_inicio = temp[7]; 
            Fecha_fin = temp[8];                
            
            Usuario = Usuario.trim();                                           //ELIMINAR LOS ESPACIOS ENTRE CAMPOS
            Grupo = Grupo.trim();
            Nombre = Nombre.trim();
            Fecha_inicio = Fecha_inicio.trim();
            Fecha_fin = Fecha_fin.trim();
      
        //validacion de fechas
                
        if(!Fecha_inicio.isEmpty())                                           //Verifica si el campo de fecha se encuentra vacío
        {
            String [] fechac;                                                   //Si el campo no está vacío verifica si está separado por /            
            fechac = Fecha_inicio.split("\\.");                                      //Separa los elementos de la fecha separados por / almacenándolos en un arreglo 
            fechames = fechac[1];                                               //Obtiene el mes de la posición 1 del arreglo
            fechadia = fechac[0];                                               //Obtiene el día de la posición 0 del arreglo
            fechaano = fechac[2];                                               //Obtiene el año de la posición 2 del arreglo
            
            Fecha_inicio = "'" + fechaano + "-" + fechames + "-" + fechadia + "'";     //Establece el campo fecha en formato aaaa-mm-dd
        }
        else
        {    
            Fecha_inicio = "NULL";                                                       //Si el campo de fecha se encontraba vacío se establece el campo vacío para mysql
        }
        
        
        if(!Fecha_fin.isEmpty())                                                    //Verifica si el campo de fecha se encuentra vacío
        {
            String [] fechac;                                                   //Si el campo no está vacío verifica si está separado por /            
            fechac = Fecha_fin.split("\\.");                                      //Separa los elementos de la fecha separados por / almacenándolos en un arreglo 
            fechames = fechac[1];                                               //Obtiene el mes de la posición 1 del arreglo
            fechadia = fechac[0];                                               //Obtiene el día de la posición 0 del arreglo
            fechaano = fechac[2];                                               //Obtiene el año de la posición 2 del arreglo
            
            Fecha_fin = "'" + fechaano + "-" + fechames + "-" + fechadia + "'";     //Establece el campo fecha en formato aaaa-mm-dd
        }
        else
        {    
            Fecha_fin = "NULL";                                                       //Si el campo de fecha se encontraba vacío se establece el campo vacío para mysql
        }
                   
            retorno = "('" + Usuario + "', '" + Nombre + "', '" + Apellido + "', '" + Grupo + "', '" + Rol + "', '" 
                    + Descripcion_Rol +  "'," + Fecha_inicio + "," + Fecha_fin + ")"; //Regresa la línea para ser insertada en la BD local
        }
        return retorno;
    }
    
    
    public static String lecturaUsuariosSAP (String ruta, String bd )
    {
        String[] parametros = bd.split("\\|");                                  //SEPARA LOS ELEMENTOS DE LA CADENA HASTA SU REFERENCIA DE CORTE
        File archivo = null;                                                    //Crea el objeto del archivo vacío
        FileReader fr = null;                                                   //Crea el objeto del lector de archivos vacío
        BufferedReader br = null;                                               //Crea el bufer de lectura vacío
        String usuarios, linea, temp, primera;                                  
        
        usuarios="";
        linea = "";
        //System.out.println("linea " + linea);        
       
        try
        {
            archivo = new File(ruta);                                           //Se establecen los parámetros para la lectura del archivo
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
              
            for (int i=0;i<11;i++)                                               //Retira el encabezado del archivo 8 lineas
            {
                primera = br.readLine();                
            }                                           

            while((temp=br.readLine()) != null)                                 //Valida que la siguiente línea del archivo no esté vacía
            {
                
                if(temp.contains("----"))                                       //Valida si contiene guiones en la línea para descartarla
                {

                }
                else
                {
                    
//                    System.out.println("temp = " + temp);
                    //System.out.println("Linea antes " + linea);
                    linea = linea + "\n " + creaLineaUsrSAP(temp) + ",";     //Almacena la concatenación de la cadena con la siguiente linea que se elabora en el método creaLineaIdInt()
                    //System.out.println("Linea  " + creaLinea(temp));
                }            
                
            }
//            System.out.println("length " + linea.length());
            linea = linea.substring(0, linea.length()-1);                         //Elimina la última coma de la cadena
//            System.out.println("Salida = " + linea);                                     
        }
//        catch(SQLException sqle)
//        {
//            sqle.printStackTrace();
//        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally                                                                 //Finaliza la lectura del archivo
        {
            try
            {
                if(fr != null)
                {
                    fr.close();
                }
            }
            catch(Exception e2)
            {
                e2.printStackTrace();
            }
         return linea;
        }       
        
    }
    
    public static String lecturaFechasAcceso (String ruta, String bd )
    {
        String[] parametros = bd.split("\\|");                                  //SEPARA LOS ELEMENTOS DE LA CADENA HASTA SU REFERENCIA DE CORTE
        File archivo = null;                                                    //Crea el objeto del archivo vacío
        FileReader fr = null;                                                   //Crea el objeto del lector de archivos vacío
        BufferedReader br = null;                                               //Crea el bufer de lectura vacío
        String usuarios, linea, temp, primera;                                  
        
        usuarios="";
        linea = "";
        //System.out.println("linea " + linea);        
       
        try
        {
            archivo = new File(ruta);                                           //Se establecen los parámetros para la lectura del archivo
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
              
            for (int i=0;i<29;i++)                                               //Retira el encabezado del archivo 8 lineas
            {
                primera = br.readLine();                
            }                                           

            while((temp=br.readLine()) != null)                                 //Valida que la siguiente línea del archivo no esté vacía
            {
                
                if(temp.contains("----"))                                       //Valida si contiene guiones en la línea para descartarla
                {

                }
                else
                {
                    
//                    System.out.println("temp = " + temp);
                    //System.out.println("Linea antes " + linea);
                    linea = linea + "\n " + creaLineaFechasAcceso(temp) + ",";     //Almacena la concatenación de la cadena con la siguiente linea que se elabora en el método creaLineaIdInt()
                   // System.out.println("Linea  " + creaLineaFechasAcceso(temp));
                }            
                
            }
//            System.out.println("length " + linea.length());
            linea = linea.substring(0, linea.length()-1);                         //Elimina la última coma de la cadena
//            System.out.println("Salida = " + linea);                                     
        }
//        catch(SQLException sqle)
//        {
//            sqle.printStackTrace();
//        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally                                                                 //Finaliza la lectura del archivo
        {
            try
            {
                if(fr != null)
                {
                    fr.close();
                }
            }
            catch(Exception e2)
            {
                e2.printStackTrace();
            }
         return linea;
        }       
        
    }
        
    public static String lecturaUsuariosPerfil (String ruta, String bd )
    {
        String[] parametros = bd.split("\\|");                                  //SEPARA LOS ELEMENTOS DE LA CADENA HASTA SU REFERENCIA DE CORTE
        File archivo = null;                                                    //Crea el objeto del archivo vacío
        FileReader fr = null;                                                   //Crea el objeto del lector de archivos vacío
        BufferedReader br = null;                                               //Crea el bufer de lectura vacío
        String usuarios, linea, temp, primera;                                  
        
        usuarios="";
        linea = "";
        //System.out.println("linea " + linea);        
       
        try
        {
            archivo = new File(ruta);                                           //Se establecen los parámetros para la lectura del archivo
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
              
            for (int i=0;i<8;i++)                                               //Retira el encabezado del archivo 8 lineas
            {
                primera = br.readLine();                
            }                                           

            while((temp=br.readLine()) != null)                                 //Valida que la siguiente línea del archivo no esté vacía
            {
                
                if(temp.contains("----"))                                       //Valida si contiene guiones en la línea para descartarla
                {

                }
                else
                {
                    
//                    System.out.println("temp = " + temp);
                    //System.out.println("Linea antes " + linea);
                    linea = linea + "\n " + creaLineaUsrPerfil(temp) + ",";     //Almacena la concatenación de la cadena con la siguiente linea que se elabora en el método creaLineaIdInt()
                   // System.out.println("Linea  " + creaLineaFechasAcceso(temp));
                }            
                
            }
//            System.out.println("length " + linea.length());
            linea = linea.substring(0, linea.length()-1);                         //Elimina la última coma de la cadena
//            System.out.println("Salida = " + linea);                                     
        }
//        catch(SQLException sqle)
//        {
//            sqle.printStackTrace();
//        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally                                                                 //Finaliza la lectura del archivo
        {
            try
            {
                if(fr != null)
                {
                    fr.close();
                }
            }
            catch(Exception e2)
            {
                e2.printStackTrace();
            }
         return linea;
        }       
        
    }
    
    
}
       
 
 
      
