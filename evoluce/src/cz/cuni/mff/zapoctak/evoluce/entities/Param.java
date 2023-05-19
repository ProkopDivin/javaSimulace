package cz.cuni.mff.zapoctak.evoluce.entities;
import java.util.ArrayList;

/**
 * 
 * class describing one parameter in configuration file.
 *
 */
public class Param {
	private ArrayList<String> val;
	private String name;
	
	public Param(String name, ArrayList<String> values) {
		val=values;
		this.name=name;
	}
	/**
	 * 
	 * @return values of the parameter
	 */
	public ArrayList<String> getVal() {
		return val;
	}
	/**
	 * 
	 * @return name of the parameter
	 */
	public String getName() {
		return name;
	}
}
