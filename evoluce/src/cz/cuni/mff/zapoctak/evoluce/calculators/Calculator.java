package cz.cuni.mff.zapoctak.evoluce.calculators;

/**
 * 
 * Hunger function is inspired by formula for kinetic energy. With sense to the power of 1
 *
 */
public class Calculator implements Calc{
	
    protected float rsense = -1;
    protected float rsize = -1;
    protected float rdexterity = -1;

    public Calculator() {}

    /**
     * 
     * @param size value to normalize size of animal
     * @param sense value to normalize sense of animal
     * @param dexterity value to normalize dexterity of animal
     */
    public Calculator(float size, float sense, float dexterity) {
        rsize = size;
        rsense = sense;
        rdexterity = dexterity;
    }

    public float hunger(float size, float sense, float dexterity) {
        float si = size / rsize;
        float se = sense / rsense;
        float de = dexterity / rdexterity;
        return (float)(Math.pow(si, 3) * Math.pow(de, 2) + se);
    }

    public String toString() {
        return "id:" + getId() + ",rsize:" + rsize + ",rsense:" + rsense + ",rdexterity:" + rdexterity;
    }

    public int getId() {
        return 0;
    }
}