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
import groovy.xml.*;

def Message processData(Message message) {
    def header = message.getHeaders() as String;
    def body = message.getBody(java.lang.String) as String;
    def messageLog = messageLogFactory.getMessageLog(message);
    
    def properties = message.getProperties() as String;
    
    def bodyPretty = XmlUtil.serialize(body);
    
    if(messageLog != null) {
        
        messageLog.setStringProperty("Logging#1", "Printing Payload As Attachment")
        messageLog.addAttachmentAsString("1 - Payload Inbound", bodyPretty, "text/plain");
        
    }
     
    return message;
}