import com.sap.gateway.ip.core.customdev.util.Message;
import javax.xml.namespace.QName;
import com.sap.gateway.ip.core.customdev.util.SoapHeader;

 

def Message processData(Message message) {
    //Body 
       def headers = new ArrayList();
       def Token = "<?xml version=\"1.0\" encoding=\"utf-8\"?><Token xmlns=\"Token\">5a3f3f6977dd47c1ae23559bc03ee7ed</Token>";
       def header = new SoapHeader(new QName("Token", "Token"), Token, false, "");
       headers.add(header);
       message.setSoapHeaders(headers);
       return message;
} 