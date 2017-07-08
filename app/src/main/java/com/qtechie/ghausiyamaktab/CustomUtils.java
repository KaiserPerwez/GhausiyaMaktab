package com.qtechie.ghausiyamaktab;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Kaiser on 01-07-2017.
 */

public class CustomUtils {

    public static String getCurrentTimeHHMMSS(){
        String time= new SimpleDateFormat("HH:mm:ss").format(new Date());
        int hour=Integer.parseInt(time.substring(0,2));
        if(hour>11){
            hour-=12;
            if(hour==0)
                return  "00"+time.substring(2).concat(" PM");
            else
                return  hour+time.substring(2).concat(" PM");
        }
        else{
            return  time.concat(" AM");
        }
    }

}
