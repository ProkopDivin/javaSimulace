package cz.cuni.mff.zapoctak.evoluce.data;



import cz.cuni.mff.zapoctak.evoluce.enviroment.*;
import cz.cuni.mff.zapoctak.evoluce.entities.*;
import cz.cuni.mff.zapoctak.evoluce.mapentities.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 
 * This is class to process statistics.
 *
 */
public class Statistics {
	private String d;
	/**
	 * How many records will be there
	 */
	private int records;
	public boolean recorded=false;
	private HashMap<Integer,ArrayList<SpeciesStat>> speciesStats= new HashMap<>();
	private HashMap<Integer,ArrayList<PlantStat>> plantStats = new HashMap<>();
	
	/**
	 * Prepare class for data recording
	 * @param map class with data for the record
	 * @param days how many day will simulation takes
	 * @param delimiter for csv file
	 */
	public void load(Map map, int days, String delimiter) {
        d = delimiter;
        records = days + 1;


        for (PlantSpecies p : map.getPlants()) {
            ArrayList<PlantStat> stats = new ArrayList<>(records);
            plantStats.put(p.getId(), stats);
        }

        for (AnimalSpecies s : map.getSpecies()) {
            ArrayList<SpeciesStat> stats = new ArrayList<>(records);
            speciesStats.put(s.getId(), stats);
        }

        record(map);
    }
	
	/**
	 * make record of one day 
	 * 
	 * @param map class with data for the record
	 */
	public void record(Map map) {
		beginRecord(map);
		processMap(map);
	}
	
	/**
	 * Save data recorded during simulation.
	 * @param fileName name of file to save the data
	 */
	public void saveStats(String fileName) {
        ArrayList<Integer> keys_plants = new ArrayList<>(plantStats.keySet());
        ArrayList<Integer> keys_species = new ArrayList<>(speciesStats.keySet());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(makeHeadline(keys_plants, keys_species));
            for (int i = 0; i < records; ++i) {
                writer.write(i + d);

                for (int key : keys_plants) {
                    writer.write(plantStats.get(key).get(i).toString(d));
                }

                for (int key : keys_species) {
                    writer.write(speciesStats.get(key).get(i).toString(d));
                }

                writer.newLine();
            }
            System.out.println("Results saved to: " + fileName);
        } catch (IOException e) {
            System.out.println("Unable to open file: " + fileName);
        }
    }
	/**
	 * Make headline for the csv file.
	 * @param plants kind of plants in the imulation
	 * @param species kind of animals in the simulation
	 * @return
	 */
	private String makeHeadline(ArrayList<Integer> plants, ArrayList<Integer> species) {
        StringBuilder sb = new StringBuilder();
        sb.append("day,");
        
        for (int key : plants) {
            sb.append(key).append("_plant_count,");
        }
        
        for (int key : species) {
            sb.append(key).append("_species_size,");
            sb.append(key).append("_species_sense,");
            sb.append(key).append("_species_dexterity,");
            sb.append(key).append("_species_count,");
        }
        
        sb.append(System.lineSeparator());
        return sb.toString();
    }
     /**
      * Make new empty record.
      * 
      * @param map data describing the simulation
      */
	private void beginRecord(Map map) {
		recorded=true;
		for (PlantSpecies p : map.getPlants()) {       
            plantStats.get(p.getId()).add(new PlantStat(0));
        }

        for (AnimalSpecies s : map.getSpecies()) {
            speciesStats.get(s.getId()).add(new SpeciesStat(0, 0, 0, 0));
        }
	}
	/**
	 * Fill record for one day.
	 * @param map data describing the simulation
	 */
	private void processMap(Map map) {
        for (Coordinates coor : map.getAnimalCoor()) {
            Item item = map.getMap()[coor.x][coor.y];
            if (item != null && item instanceof Animal) {
                Animal a = (Animal) item;
                speciesStats.get(a.species.getId()).get(speciesStats.get(a.species.getId()).size() - 1)
                        .noteAnimal(a.getSize(), a.getSense(), a.getDexterity(), 1);
            }
        }

        for (PlantSpecies plant : map.getPlants()) {
            plantStats.get(plant.getId()).get(plantStats.get(plant.getId()).size() - 1).notePlant(plant.getCount());
        }
    }
	
	
}
