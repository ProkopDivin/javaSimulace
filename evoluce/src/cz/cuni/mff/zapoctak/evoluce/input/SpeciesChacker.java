package cz.cuni.mff.zapoctak.evoluce.input;
import java.util.ArrayList;
import java.util.HashMap;
import cz.cuni.mff.zapoctak.evoluce.entities.AnimalSpecies;

/**
 * 
 * Check animal section.
 *
 */
public class SpeciesChacker extends Chacker{
	protected static final String[] paramsNames= {"name","size","sense","dexterity","reproduction","mutation","count","food","stop_eat"};
	protected String getSection() {
		return "*species";	
	}
	/**
	 * Check if there is right attributes define in animal section 
	 * @param param map with name of attribute as a key and string list of values
	 * @return list of attributes with their values converted to the final type.
	 * @throws RuntimeException
	 */
	public  AnimalSpecies check(HashMap<String, ArrayList<String>> param) throws RuntimeException {
		 AnimalSpecies species = new  AnimalSpecies();
		 if (param.size() > paramsNames.length) {
		      throw new RuntimeException("Too much parameters in species definition");
		 }
		 for (String parameterName : paramsNames) {
		     if (!param.containsKey(parameterName)) {
		         throw new RuntimeException(parameterName + " is not set in species declaration");
		     } else {
		    	 procesSpeciesParams(species, param.get(parameterName), parameterName);
		     }
		  }
		 return species;
   }
	/**
	 * Check particular attribute and their values  and convert it into their final type
	 * @param header store the values
	 * @param parameters list of values
	 * @param parameterName name of attribute
	 */
	protected void procesSpeciesParams( AnimalSpecies animal,ArrayList<String> parameters, String parameterName) {
    	switch(parameterName) {
    	
    	case "name":
    	    checkCount(1, parameters, parameterName);
    	    animal.setName(parameters.get(0));
    	    break;
    	case "size":
    	    checkCount(1, parameters, parameterName);
    	    animal.setSize(Float.parseFloat(parameters.get(0)));
    	    break;
    	case "sense":
    	    checkCount(1, parameters, parameterName);
    	    animal.setSense(Float.parseFloat(parameters.get(0)));
    	    break;
    	case "dexterity":
    	    checkCount(1, parameters, parameterName);
    	    animal.setDexterity(Float.parseFloat(parameters.get(0)));
    	    break;
    	
    	case "reproduction":
    	    checkCount(1, parameters, parameterName);
    	    animal.setReproduction(Float.parseFloat(parameters.get(0)));
    	    break;
    	case "mutation":
    	    checkCount(1, parameters, parameterName);
    	    animal.setMutation(Float.parseFloat(parameters.get(0)));
    	    break;
    	case "count":
    	    checkCount(1, parameters, parameterName);
    	    animal.setCount(Integer.parseInt(parameters.get(0)));
    	    break;
    	case "food":
    	    checkCount(2, parameters, parameterName);
    	    float  meet= Float.parseFloat(parameters.get(0));
    	    float food = Float.parseFloat(parameters.get(1));
    	    animal.setFood(food,meet);
    	    break;
    	case "stop_eat":
    	    checkCount(1, parameters, parameterName);
    	    animal.setStop_eat(Float.parseFloat(parameters.get(0)));
    	    break;
    	default:
    		throw new IllegalArgumentException(parameterName + " isn't species parameter");
    	}
    	
    }

}
