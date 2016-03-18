/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utiles;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author YOSH
 */
public class Util {
    
    public String dateMonth(Date date){
     
        String result="";
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        int month=0;

        try{
          month=calendar.get(Calendar.MONTH);
        }catch(Exception ex){}
        switch(month){
         case 0:
           {
             result="enero";
             break;
           }
         case 1:
           {
             result="febrero";
             break;
           }
         case 2:
           {
             result="marzo";
             break;
           }
         case 3:
           {
             result="abril";
             break;
           }
         case 4:
           {
             result="mayo";
             break;
           }
         case 5:
           {
             result="junio";
             break;
           }
         case 6:
           {
             result="julio";
             break;
           }
         case 7:
           {
             result="agosto";
             break;
           }
         case 8:
           {
             result="septiembre";
             break;
           }
         case 9:
           {
             result="octubre";
             break;
           }
         case 10:
           {
             result="noviembre";
             break;
           }
         case 11:
           {
             result="diciembre";
             break;
           }
         default:
           {
             result="Error";
             break;
           }
        }
        return result;
    }
     
    
}
