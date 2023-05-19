package cz.cuni.mff.zapoctak.evoluce.simulation;
import cz.cuni.mff.zapoctak.evoluce.input.*;
import cz.cuni.mff.zapoctak.evoluce.data.*;
import cz.cuni.mff.zapoctak.evoluce.enviroment.*;

import java.io.FileNotFoundException;

import cz.cuni.mff.zapoctak.evoluce.entities.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 
 * Class doing the simulation and control element of the simulation
 *
 */
public class Simulation {
	private int days=0;
	private String output;
	public boolean ready=false;
	private Map map= new Map();
	private Statistics stats=new Statistics();
	
	/**
	 * Start the simulation
	 */
	public void start() {
        stats.load(map, days, ",");
            
        try (BufferedWriter file = new BufferedWriter(new FileWriter(output + "_log.txt"))) {
            MapPrinter.printMap(map.getMap(),"0", file);
            
            for (int i = 1; i <= days; ++i) {
                map.Day(i);
                stats.record(map);
                MapPrinter.printMap(map.getMap(), String.valueOf(i), file);
                file.newLine();
            }
        } catch (IOException e) {
            System.out.println("Can't save log, the file: " + output + " cannot be opened.");
        }
    }
	
	/**
	 * Save the simulation
	 */
	public void save() {
		if (stats.recorded) {
	        stats.saveStats(output + ".csv");
	    } else {
	        System.out.println("no data for saving");
	    }
		
	}
	
	/**
	 * Load the simulation before it can be started 
	 * 
	 * @param inputfile path to configuration file 
	 * @param outputfile name of the output file 
	 */
	public void load(String inputfile, String outputfile) {
        InputReader reader=new InputReader();
	    output = outputfile;
	    try {
	        Parameters parameters = reader.getParameters(inputfile);
	        
	        printParams(parameters);
	        days = parameters.getHeader().getDays();
	        map.load(parameters);
	        ready = true;
	   
	    } catch (IllegalArgumentException e) {
	        System.out.println("Error:" + e.getMessage());
	    }catch ( FileNotFoundException e) {
	        System.out.println("Error:" + e.getMessage());
	    }catch (RuntimeException e) {
	    	System.out.println("Error:" + e.getMessage());
	    }
 	}
	
	
	

	private void printParams(Parameters params) {
	    Header h = params.getHeader();
	    h.print();
	    
	    System.out.println();
	    System.out.println("Plants:");
	    for (PlantSpecies p: params.getPlant()) {
	    	p.print();
	    }
	    
	    System.out.println();
	    System.out.println("Species:");
	    for(AnimalSpecies s: params.getSpecies()) {
	    	s.print();
	    }
	}
}
