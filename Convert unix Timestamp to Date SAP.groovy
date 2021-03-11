//Converte unix timestamp em datetime
//Codigo criado por Felipe Campos

import com.sap.it.api.mapping.*;
import java.time.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


def String customFunc(String arg1){

    long unix_seconds = arg1.toLong();
    Date date = new Date(unix_seconds*1000); 
    def formattedDate = date.format("yyyy-MM-dd HH:mm:ss")
   
    return formattedDate
    
}