package cz.cuni.mff.zapoctak.evoluce.data;

/**
 * 
 * This class keep information of one kind of plant on the map  to make record.
 * 
 *
 */
public class PlantStat {
	private int count;

    public PlantStat(int count) {
        this.count = count;
    }
    /**
     * 
     * @param co add plant
     */
    public void notePlant(int co) {
        count = co;
    }
    /**
     * 
     * @param delimiter which will be used
     * @return string for one value in csv
     */
    public String toString(String delimiter) {
        return String.valueOf(count)+delimiter;
    }
}
