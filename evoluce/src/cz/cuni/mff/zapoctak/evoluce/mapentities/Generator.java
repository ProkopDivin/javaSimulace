package cz.cuni.mff.zapoctak.evoluce.mapentities;
import java.util.Random;

/**
 * 
 * To have some randomnes in program 
 *
 */
public class Generator {

    private Random rng;
    public Generator() {
        rng = new Random(42);
    }
    
    public int roll(int max) {       
        return rng.nextInt(max);
    }
    
}
