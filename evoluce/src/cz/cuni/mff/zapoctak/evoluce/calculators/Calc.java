package cz.cuni.mff.zapoctak.evoluce.calculators;


/**
 * 
 * @author Prokop Divín
 * 
 * This interface specify class with function calculating hunger.
 *
 */
public interface Calc {
	
	
	/**
	 * 
	 * @param size of the animal.
	 * @param sense of the animal.
	 * @param dexterity of the animal.
	 * @return How much nutritions animal needs to survive
	 */
	public float hunger(float size, float sense, float dexterity);
	/**
	 * 
	 * @return String Id the calculator 
	 */
    public String toString();
    /**
     * 
     * @return Id of the calculator
     */
	public  int getId();   
}
