def String splitName(String fullname, String code){
    
    String[] name;
    name = fullname.split(' ');
    def name_size = name.size();
    
    def firstname = name[0];
    def lastname = '';
        
    for (i = 1; i < name_size; i++)
    {
        if(i==1)
        {
            lastname = name[1];    
        }
        else
        {
            lastname = lastname + ' ' + name[i];
        }
    }
    
    if(code=='first'){
        return firstname;
    }
    if(code=='last'){
        return lastname;
    }
    
    return;
    
}