import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
import groovy.util.XmlParser.*;
import groovy.util.XmlSlurper.*;
import groovy.xml.*;
import groovy.json.*; 

def Message processData(Message message) 
{
    def body = message.getBody(java.lang.String);
    //xml deserializado em campos pelo slurper
    def xml = new XmlSlurper().parseText(body);
    def root = xml;
    
    def endereco = root.BusinessPartnerSUITEReplicateRequestMessage.BusinessPartner.AddressInformation;
    def endereco_size = endereco.size();
    def usage_size = '';
    
    def email = '';
    def fax = '';
    def phone_size = '';
    def telefone = '';
    def celular = '';
    
    def pais_d = '';
    def estado_d = '';
    def cidade_d = ''; 
    def cep_d = '';
    def rua_d = '';
    def num_d = '';
   
    def pais_s = '';
    def estado_s = '';
    def cidade_s = ''; 
    def cep_s = '';
    def rua_s = '';
    def num_s = '';
    def tem_ship = 'nao';
    
    def pais_b = '';
    def estado_b = '';
    def cidade_b = ''; 
    def cep_b = '';
    def rua_b = '';
    def num_b = '';
    def tem_bill = 'nao';
   
   
    for (i = 0; i < endereco_size; i++)
    {
        
        usage_size = endereco[i].AddressUsage.size();
        
        for (x = 0; x < usage_size; x++)
        { 
            if( endereco[i].AddressUsage[x].AddressUsageCode == 'XXDEFAULT') 
            { //procura endereco padrao
                
               email = endereco[i].Address.Email.URI;
               fax = endereco[i].Address.Facsimile.FormattedNumberDescription;
               
               pais_d = endereco[i].Address.PostalAddress.CountryCode;
               estado_d = endereco[i].Address.PostalAddress.RegionCode;
               cidade_d = endereco[i].Address.PostalAddress.CityName;
               cep_d = endereco[i].Address.PostalAddress.StreetPostalCode;
               rua_d = endereco[i].Address.PostalAddress.StreetName;
               num_d = endereco[i].Address.PostalAddress.HouseID;
               
               phone_size = endereco[i].Address.Telephone.size();
               
               for (j = 0; j < phone_size; j++)
                { //preenche telefone e celular
                    if( endereco[i].Address.Telephone[j].MobilePhoneNumberIndicator == 'false')
                    {
                        telefone = endereco[i].Address.Telephone[j].FormattedNumberDescription;
                    }
                    if( endereco[i].Address.Telephone[j].MobilePhoneNumberIndicator == 'true')
                    {
                        celular = endereco[i].Address.Telephone[j].FormattedNumberDescription;
                    }
                  
                }
            } 
            
            if( endereco[i].AddressUsage[x].AddressUsageCode == 'SHIP_TO') 
            { //procura endereco ship_to
                
                if( endereco[i].AddressUsage[x].AddressUsageCode.DefaultIndicator == 'true')
                {
                    pais_s = endereco[i].Address.PostalAddress.CountryCode;
                    estado_s = endereco[i].Address.PostalAddress.RegionCode;
                    cidade_s = endereco[i].Address.PostalAddress.CityName;
                    cep_s = endereco[i].Address.PostalAddress.StreetPostalCode;
                    rua_s = endereco[i].Address.PostalAddress.StreetName;
                    num_s = endereco[i].Address.PostalAddress.HouseID;
                    tem_ship = 'sim';
                }
            }
            
            if( endereco[i].AddressUsage[x].AddressUsageCode == 'BILL_TO') 
            { //procura endereco bill_to
                
                if( endereco[i].AddressUsage[x].AddressUsageCode.DefaultIndicator == 'true')
                {
                    pais_b = endereco[i].Address.PostalAddress.CountryCode;
                    estado_b = endereco[i].Address.PostalAddress.RegionCode;
                    cidade_b = endereco[i].Address.PostalAddress.CityName;
                    cep_b = endereco[i].Address.PostalAddress.StreetPostalCode;
                    rua_b = endereco[i].Address.PostalAddress.StreetName;
                    num_b = endereco[i].Address.PostalAddress.HouseID;
                    tem_bill = 'sim';
                }
            }
        }
    } //end For

    //se nao achar ship_to e/ou bill_to preenche com o valor default
    if (tem_ship == 'nao')
    {
        pais_s = pais_d;
        estado_s = estado_d;
        cidade_s = cidade_d; 
        cep_s = cep_d;
        rua_s = rua_d;
        num_s = num_d;
    }
    if (tem_bill == 'nao')
    {
        pais_b = pais_d;
        estado_b = estado_d;
        cidade_b = cidade_d; 
        cep_b = cep_d;
        rua_b = rua_d;
        num_b = num_d;        
    }
    
    message.setProperty("email",email);
    message.setProperty("fax",fax);
    message.setProperty("telefone",telefone);
    message.setProperty("celular",celular);
    
    message.setProperty("pais_s",pais_s);
    message.setProperty("estado_s",estado_s);
    message.setProperty("cidade_s",cidade_s);
    message.setProperty("cep_s",cep_s);
    message.setProperty("rua_s",rua_s);
    message.setProperty("num_s",num_s);
    
    message.setProperty("pais_b",pais_b);
    message.setProperty("estado_b",estado_b);
    message.setProperty("cidade_b",cidade_b);
    message.setProperty("cep_b",cep_b);
    message.setProperty("rua_b",rua_b);
    message.setProperty("num_b",num_b);
    
    return message;
}