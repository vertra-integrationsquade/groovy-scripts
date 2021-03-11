import com.sap.gateway.ip.core.customdev.util.Message;
import javax.xml.namespace.QName;
import com.sap.gateway.ip.core.customdev.util.SoapHeader;

def Message processData(Message message){
    
    
    
    def recuperaToken = message.getProperty("Token");
    def Token = "<?xml version=\"1.0\" encoding=\"utf-8\"?><Token xmlns=\"Token\">"+recuperaToken+"</Token>";
    
    def headers = new ArrayList();
    def header = new SoapHeader(new QName("Token", "Token"), Token, false, "");
    headers.add(header);
    message.setSoapHeaders(headers);
    
    return message;
    
}