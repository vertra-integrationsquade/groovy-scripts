/*

     ██╗███████╗██████╗ ██╗ ██████╗██████╗ ███╗   ███╗
     ██║██╔════╝██╔══██╗██║██╔════╝██╔══██╗████╗ ████║
     ██║█████╗  ██║  ██║██║██║     ██████╔╝██╔████╔██║
██   ██║██╔══╝  ██║  ██║██║██║     ██╔══██╗██║╚██╔╝██║
╚█████╔╝███████╗██████╔╝██║╚██████╗██║  ██║██║ ╚═╝ ██║
╚════╝ ╚══════╝╚═════╝ ╚═╝ ╚═════╝╚═╝  ╚═╝╚═╝     ╚═╝
                                                      
Consultant Willi Santana
Date: *********
JediCRM
*/
import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
import groovy.json.*;

def Message processData(Message message) {

    def body = message.getBody(java.lang.String) as String
    
    def messageLog = messageLogFactory.getMessageLog(message);

    if(messageLog != null) {
        
        messageLog.setStringProperty("Logging#1", "Printing Payload As Attachment")
        messageLog.addAttachmentAsString("1 - Entrada JSON", body, "text/plain");
        
    }    
    
    return message;
}