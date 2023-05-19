package cz.cuni.mff.zapoctak.evoluce.input;

import cz.cuni.mff.zapoctak.evoluce.entities.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Check the header section.
 *
 */
public class HeaderChacker extends Chacker {
	protected static final String[] paramsNames= { "days", "map_hight", "map_width", "calculator", "nutritions_divider" };
	
	protected String getSection() {
		return "*head";
	}
	/**
	 * Check if there is right attributes define in header section 
	 * @param param map with name of attribute as a key and string list of values
	 * @return list of attributes with their values converted to the final type.
	 * @throws RuntimeException
	 */
	public Header check(HashMap<String, ArrayList<String> > param) throws RuntimeException {
		 Header header = new Header();
		 if (param.size() > paramsNames.length) {
		      throw new RuntimeException("Too much parameters in header definition");
		 }
		 for (String parameterName : paramsNames) {
		     if (!param.containsKey(parameterName)) {
		         throw new RuntimeException(parameterName + " is not set in header declaration");
		     } else {
		         procesHeaderParams(header, param.get(parameterName), parameterName);
		     }
		  }
		 return header;
   }

	/**
	 * Check particular attribute and their values  and convert it into their final type
	 * @param header store the values
	 * @param parameters list of values
	 * @param parameterName name of attribute
	 */
	protected void procesHeaderParams(Header header,ArrayList<String> parameters, String parameterName) {
	    switch (parameterName) {
	    case "days":
	    	checkCount(1, parameters, parameterName);
	        header.setDays(Integer.parseInt(parameters.get(0)));
	        break;
	    case "map_hight": 
	        checkCount(1, parameters, parameterName);
	        header.setMapHeight(Integer.parseInt(parameters.get(0)));
	        break;
	    case "map_width": 
	        checkCount(1, parameters, parameterName);
	        header.setMapWidth(Integer.parseInt(parameters.get(0)));
	        break;
	    case "nutritions_divider":
	        checkCount(1, parameters, parameterName);
	        header.setDivider(Float.parseFloat(parameters.get(0)));
	        break;
	    case "calculator":
	        checkCount(4, parameters, parameterName);
	        header.setCalculator(Integer.parseInt(parameters.get(0)), Float.parseFloat(parameters.get(1)), 
	                            Float.parseFloat(parameters.get(2)), Float.parseFloat(parameters.get(3)));
	        break;
	    default:
	        throw new IllegalArgumentException(parameterName + " isn't head parameter");
	    
	    }
	}
	
	
}
