package cz.cuni.mff.zapoctak.evoluce.entities;

/**
 * 
 * Class describing how match nutritions is animal able to get from plants and meet.
 *
 */
public class NutrionsMultiplicators {
    private float plants = -1;
    private float meat = -1;

    public NutrionsMultiplicators() {}

    public NutrionsMultiplicators(float plants, float meat) {
        this.plants = plants;
        this.meat = meat;
    }
    @Override
    public String toString() {
    	return String.format("plants(%f), meat(%f)",plants,meat);
    }

    public float getPlants() {
        return plants;
    }

    public void setPlants(float plants) {
        this.plants = plants;
    }

    public float getMeat() {
        return meat;
    }

    public void setMeat(float meat) {
        this.meat = meat;
    }
}