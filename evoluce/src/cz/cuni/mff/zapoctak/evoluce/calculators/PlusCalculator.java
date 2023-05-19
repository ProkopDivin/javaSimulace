package cz.cuni.mff.zapoctak.evoluce.calculators;


/**
 * 
 *  Hunger function does not benefit any parameter.
 */
public class PlusCalculator extends Calculator {
	/**
     * 
     * @param size value to normalize size of animal
     * @param sense value to normalize sense of animal
     * @param dexterity value to normalize dexterity of animal
     */
    public PlusCalculator(float size, float sense, float dexterity) {
        super(size, sense, dexterity);
    }

    @Override
    public float hunger(float size, float sense, float dexterity) {
        float si = size / rsize;
        float se = sense / rsense;
        float de = dexterity / rdexterity;
        return (float) (si + de + se);
    }

    @Override
    public int getId(){
        return 1;
    }
}
