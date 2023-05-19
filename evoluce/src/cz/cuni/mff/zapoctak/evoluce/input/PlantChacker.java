package cz.cuni.mff.zapoctak.evoluce.input;


import java.util.ArrayList;
import java.util.HashMap;
import cz.cuni.mff.zapoctak.evoluce.entities.*;
/**
 * 
 * Check section with plant-food definition
 *
 */
public class PlantChacker extends Chacker{
    protected static final String[] paramsNames= { "name","size","count","nourishment","changer"};
    protected String getSection() {
		return "*plant";
	}
    /**
	 * Check if there is right attributes define in plant section 
	 * @param param map with name of attribute as a key and string list of values
	 * @return list of attributes with their values converted to the final type.
	 * @throws RuntimeException
	 */
    public PlantSpecies check(HashMap<String, ArrayList<String>> param) throws RuntimeException {
    	 PlantSpecies plant = new PlantSpecies();
		 if (param.size() > paramsNames.length) {
		      throw new RuntimeException("Too much parameters in plant definition");
		 }
		 for (String parameterName : paramsNames) {
		     if (!param.containsKey(parameterName)) {
		         throw new RuntimeException(parameterName + " is not set in header declaration");
		     } else {
		    	 procesPlantsParams(plant, param.get(parameterName), parameterName);
		     }
		  }
		 return plant ;
    }
    
    /**
	 * Check particular attribute and their values  and convert it into their final type
	 * @param header store the values
	 * @param parameters list of values
	 * @param parameterName name of attribute
	 */
    protected void procesPlantsParams(PlantSpecies plant,ArrayList<String>  parameters, String parameterName) {
    	switch(parameterName) {
    	
    	case "size":
    		checkCount(1, parameters, parameterName);
    		plant.setSize(Float.parseFloat(parameters.get(0)));
    		break;
    	case "name":
    		checkCount(1, parameters, parameterName);
    		plant.setName(parameters.get(0));
    		break;
    	case "count":
    		checkCount(1, parameters, parameterName);
    		plant.setCount(Integer.parseInt(parameters.get(0)));
    		break;
    	case "nourishment":
    		checkCount(1, parameters, parameterName);
    		plant.setNourishment(Integer.parseInt(parameters.get(0)));
    		break;
    	case "changer":
    		checkCount(4, parameters, parameterName);
    		int interval= Integer.parseInt(parameters.get(0));
    		float times= Float.parseFloat(parameters.get(1));
    		int max= Integer.parseInt(parameters.get(2));
    		int min= Integer.parseInt(parameters.get(3));
    		plant.setChanger(interval, times, max, min);
    		break;
    	default:
    		throw new IllegalArgumentException(parameterName + " isn't plant parameter");
    	}
    	
    }
}
