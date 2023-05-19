package cz.cuni.mff.zapoctak.evoluce.calculators;

/**
 * 
 * Hunger function is inspired by formula for kinetic energy. With sense to the power of 2
 *
 */
public class KineticCalculator extends Calculator{
	/**
     * 
     * @param size value to normalize size of animal
     * @param sense value to normalize sense of animal
     * @param dexterity value to normalize dexterity of animal
     */
	public KineticCalculator(float size, float sense, float dexterity) {
        super(size, sense, dexterity);
	}

	public float hunger(float size, float sense, float dexterity) {
		float si = size / rsize;
		float se = sense / rsense;
		float de = dexterity / rdexterity;
		float hunger = ((float)(Math.pow(si, 3) * Math.pow(de, 2) + Math.pow(se, 2)));
		return hunger;
	}
@Override
	public int getId() {
		return 2;
	}
}