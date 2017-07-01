package com.qtechie.ghausiyamaktab;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Kaiser on 01-07-2017.
 */

public class CustomUtils {
    public static String getCurrentTimeHHMMSS(){
        String time= new SimpleDateFormat("HH:mm:ss").format(new Date());
        if(Integer.parseInt(time.substring(0,2))>11){
            return  time.concat(" PM");
        }
        else{
            return  time.concat(" AM");
        }
    }
}
