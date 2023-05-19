package cz.cuni.mff.zapoctak.evoluce.data;
/**
 * 
 * Class which keep data of one animal kind for record.
 * It keeps sum of values of one aitribut of animal kind
 *
 */
public class SpeciesStat {
	private float size;
    private float sense;
    private float dexterity;
    private int count;
    
    public SpeciesStat(float size, float sense, float dexterity, int count) {
        this.size = size;
        this.sense = sense;
        this.dexterity = dexterity;
        this.count = count;
    }
    /**
     * 
     * @param si size
     * @param se sense
     * @param de dexterity
     * @param co count
     */
    public void noteAnimal(float si, float se, float de, int co) {
        size += si;
        sense += se;
        dexterity += de;
        count += co;
    }
    
    /**
     * 
     * @param delimiter 
     * @return String with average values of atributs
     */
    public String toString(String delimiter) {
    	if (count >0) {
           return String.valueOf(size/count)+ delimiter + String.valueOf(sense/count) + delimiter + String.valueOf(dexterity/count) + delimiter + String.valueOf(count)+ delimiter;
    	}else {
    		return  delimiter + delimiter + delimiter + String.valueOf(count)+ delimiter;
    	}
    }
}
