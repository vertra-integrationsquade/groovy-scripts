import com.sap.it.api.mapping.*;


def void genderTransf(String[] genderIN, Output genderOUT){
	def gender = genderIN[0];
	
	
	if(gender == 'gt_Female'){
	    genderOUT.addValue("2");
	} else{
	    genderOUT.addValue("1");
	}
}