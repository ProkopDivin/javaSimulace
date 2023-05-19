package cz.cuni.mff.zapoctak.evoluce.input;
import java.util.List;

/**
 * 
 * This class and her descendants check sections of configuration file and convert the attribut values from string to there origin type   
 *
 */
public  class Chacker {
	
	/**
	 * check expected number of values to e given atributt
	 * @param expected_count how many values is given atribut describe with
	 * @param parameters values of attribut 
	 * @param name name of the atribut
	 */
	public void checkCount(int expected_count, List<String> parameters, String name) {
	    if (parameters.size() != expected_count)
	        throw new IllegalArgumentException("\""+getSection()+ "\"" + name + "\" wrong number of arguments");
	}
	protected String getSection() {
		return "None";
	}
    
}
