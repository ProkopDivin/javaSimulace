package cz.cuni.mff.zapoctak.evoluce.mapentities;
import cz.cuni.mff.zapoctak.evoluce.entities.*;

/**
 * 
 * Representing food,plant on the map.
 *
 */
public class Food extends Item {
	public  PlantSpecies plant;
	public Food( PlantSpecies p) { plant = p; }
}
