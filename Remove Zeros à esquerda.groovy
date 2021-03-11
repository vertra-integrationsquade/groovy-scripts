import com.sap.it.api.mapping.*;
import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
import groovy.*;

 

def String customFunc(String arg1){
   def arg2='';
   
   arg2=arg1.replaceFirst('^0+(?!$)','');

 

   arg2=arg1.replaceFirst("[^a-zA-Z0-9]+","");//remove caractere especial

 

    
    return arg2 
}