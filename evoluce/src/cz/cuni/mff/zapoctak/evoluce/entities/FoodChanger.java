package cz.cuni.mff.zapoctak.evoluce.entities;

/**
 * 
 * Class to describe change of the amount of food during simulation.
 *
 */
public class FoodChanger {
	
    private int interval = -1;
    private float times = 1;
    private int max = 0;
    private int min = 0;

    public FoodChanger() {}

    public FoodChanger(int interval, float times, int max, int min) {
        this.interval = interval;
        this.times = times;
        this.max = max;
        this.min = min;
    }

    /**
	 * @return Interval when the maximum amount of food change.
	 */
    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    /**
     * 
     * @return How many time the maximum amount of food change
     */
    public float getTimes() {
        return times;
    }

    public void setTimes(float times) {
        this.times = times;
    }

    /**
     * 
     * @return Maximum amount of food.
     */
    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    /**
     * 
     * @return Minimum amount of food.
     */
    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }
}
