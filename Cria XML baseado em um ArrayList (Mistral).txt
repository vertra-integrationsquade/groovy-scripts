import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
import java.util.StringJoiner;

/*Aqui eu tive que tratar um campo do xml que vinha N mensagens de erros. Descartei parte do campo com informações inúteis, pegar essas mensagens de erros e armazená-las em um ArrayList com N elementos, cada elemento contendo uma mensagem de erro. Depois eu recriei um novo XML com N campos, cada um contendo uma dessas mensagens de erro. 
 
As principais funções utilizadas, contains(), indexof(), split() e StringJoiner(), esse último é uma classe java e necessita importação.
https://www.tutorialspoint.com/groovy/groovy_contains.htm
https://www.tutorialspoint.com/groovy/groovy_indexof.htm
https://www.tutorialspoint.com/groovy/groovy_split.htm
https://docs.oracle.com/javase/8/docs/api/java/util/StringJoiner.html
 */


def Message processData(Message message) {
    
    //Recupera a Property "body"
    def body = message.getProperty("body")
    
   
    //Verifica se há ocorrência de "object locked" ou "objeto bloqueado" na string "body" 
    def boolean1 = body.contains("object locked")
    def boolean2 = body.contains("objeto bloqueado")
    
    if (boolean1 == true || boolean2 == true){
        
        //Caso satisfaça a condição, cria um XML com o statusCode e body abaixo
        def statusCode = "2"
        body = "LOCKED"
        
        def XMLFinal = '<?xml version="1.0" encoding="utf-8"?><root><values><statusCode>'+statusCode+'</statusCode><Mensagem><body>'+body+'</body></Mensagem></values></root>'
        
        //Define o body da mensagem como sendo o novo XML criado
        message.setBody(XMLFinal)
        
    }else{
      
      //Caso contrário, chama método trataBody que devolve o body da msg tratado e em formato de Array
      def statusCode = "1"
      def body_tratado = trataBody(body)
      
      //Com o body tratado, chama o método criaXML
      def XMLFinal = criaXML(statusCode, body_tratado)
      
      //Define o body da mensagem como sendo o novo XML criado
      message.setBody(XMLFinal)
     
    }
return message
}

def trataBody (String body){
    
      def limiteInicial = '<message xml:lang="en">' 
      def limiteFinal = "</message>"
      
      def correcao = limiteInicial.length()
      
      def inicioMensagem = body.indexOf(limiteInicial)
      def fimMensagem = body.indexOf(limiteFinal)
      
      //Define a substring de acordo com os indexes obtidos 
      def a = body.substring(inicioMensagem + correcao, fimMensagem)
      
      //Define um array usando como separador '::'
      String[] str
      str = a.split('::')
      return str
}

def criaXML (String statusCode, String [] body_tratado){
    
      //Define a abertura e fechamento do XML
      def inicioXML = '<?xml version="1.0" encoding="utf-8"?><root><values><statusCode>'+statusCode+'</statusCode>'
      def fimXML = "</values></root>"
      
      //Define duas variáveis auxiliares para serem utilizadas no for
      def str2 = []
      String auxiliar = ""
      
      //O loop irá criar um novo arrayList no formato XML
      for( String values : body_tratado ){
        auxiliar = "<Mensagem><body>"+values+"</body></Mensagem>"
        
        str2.add(auxiliar)
        
      }
      
      //Transforma o arrayList str2 em uma única string str3
      StringJoiner joiner = new StringJoiner("");
      for( String values : str2 ) {
         joiner.add(values);
      }
      String str3 = joiner.toString()
      
      //Cria o XML final e o retorna em formato de String
      String XMLFinal = inicioXML + str3 + fimXML 
      return XMLFinal
}
    
