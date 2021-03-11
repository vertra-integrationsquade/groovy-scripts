import com.sap.it.api.mapping.*;
import java.util.HashMap;

def String getProp(String arg1, MappingContext context){  //arg1 é o nome da propriedade
    
    def arg2 = context.getProperty(arg1);

    return arg2;
}