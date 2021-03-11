import java.text.Normalizer

def String retiraAcento(String arg1){
    
    arg1 = Normalizer.normalize(arg1, Normalizer.Form.NFD);
    arg1 = arg1.replaceAll("\\p{M}", "");
    arg1 = arg1.replaceAll("[^\\p{ASCII}]", "");
    arg1 = arg1.replaceAll(" ", "-");
    
    return arg1;
}