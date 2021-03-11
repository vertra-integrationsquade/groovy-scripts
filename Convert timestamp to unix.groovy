/*
Get current dateTime minus 24h, converts to UNIX 
 */
import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
import java.time.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

def Message processData(Message message) {
    //Body 
        
        Date latestdate = new Date();
        long newdate = latestdate.getTime();
        newdate = ((newdate/1000) - (3600*24));
   
       message.setProperty("TimeStampUnix", newdate);
       
       return message;
}