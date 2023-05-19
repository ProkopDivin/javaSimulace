package cz.cuni.mff.zapoctak.evoluce.input;
import java.util.ArrayList;

import cz.cuni.mff.zapoctak.evoluce.entities.*;
import java.io.*;
import java.util.HashMap;

/**
 * 
 * Read the input and prepare parameter for the map  
 *
 */
public class InputReader {
	
	/**r
	 * 
	 * @param path to configuration file 
	 * @return loaded parameters
	 * @throws FileNotFoundException
	 */
	public Parameters getParameters(String path) throws FileNotFoundException{
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
		return readInput(reader);
	   
	}
	
	
	/**
	 * Read one section
	 * @param reader file with define attributes and parameters
	 * @return readed section 
	 */
	private HashMap<String, ArrayList<String> >readSection(BufferedReader reader) {
	    HashMap<String, ArrayList<String>> parameters = new HashMap<>();

	    try {
	        while (true) {
	            Param line = readLine(reader);

	            if (line.getName().isEmpty() || line.getName().charAt(0) == '-') {
	                // "-" means end of the section
	                return parameters;
	            } else {
	                // first element of line is parameter name, second is parameters array
	                parameters.put(line.getName(), line.getVal());
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    return parameters;
	}
	
	/**
	 * 
	 * @param input file with input
	 * @return line = one attribute with his values.
	 * @throws IOException
	 */
	private Param readLine(BufferedReader input) throws IOException  {
		ArrayList<String>parameters = new ArrayList<String>();
	    String rawLine;
	    String firstWordInLine = "";
	    while (firstWordInLine.isEmpty()) {
	        rawLine =input.readLine();
	        if (rawLine==null)
	            return new Param(firstWordInLine, parameters);
	        
	        String[] words=rawLine.split("\\s+");
	        if (words.length>0 &&words[0].length()>0) { //not empty line 
	        	for (String w:words) {
	        		if (w.charAt(0) == '#') // don't read comments
	        			break;
	        			if (firstWordInLine.isEmpty()) {
	        				firstWordInLine = w;
	        			} else {
	        				parameters.add(w);
	            }
	        }
	        }
	      
	    }
	    return new Param(firstWordInLine, parameters);
	}
	/**
	 * Read whole file
	 * @param reader
	 * @return parameters
	 */
	private Parameters readInput(BufferedReader reader) {
	    
		ArrayList<AnimalSpecies>  species =new ArrayList<AnimalSpecies>();
		ArrayList<PlantSpecies>  plant= new ArrayList<PlantSpecies>() ;

	    Header header=null;
	    
	    HeaderChacker headerChecker = new HeaderChacker();
	    SpeciesChacker speciesChecker = new SpeciesChacker();
	    PlantChacker plantChecker = new PlantChacker();


	    try {
	    	Param  line=readLine(reader);
	        while(line.getName() != ""){
	        	
	            if (line.getName().equals("*head")) {
	                HashMap<String, ArrayList<String>> section = readSection(reader);
	                header = headerChecker.check(section);
	            }

	            if (line.getName().equals("*plant")) {
	                HashMap<String, ArrayList<String>> section = readSection(reader);
	                PlantSpecies p =plantChecker.check(section);
	                p.setId(plant.size());
	                plant.add(p);
	            }

	            if (line.getName().equals("*species")) {
	                 HashMap<String, ArrayList<String>> section = readSection(reader);
	                 AnimalSpecies s = speciesChecker.check(section);
	                 s.setId(species.size());
	                 species.add(s);	                    	                 
	            }
	            line = readLine(reader);
	        }

	        int items = 0;
	        for (PlantSpecies p: plant) {
	        	items += p.getCount();
	        }
	        for (AnimalSpecies s:species) {
	        	items += s.getCount();
	        }  
            if (header == null) {
            	throw new RuntimeException("Map is not define.");
            }
            else {
	           if (header.getMapHeight() * header.getMapWidth() < items) {
	               throw new RuntimeException("Too many items on the map");
	           }
            }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    return new Parameters(header, species, plant);
	}
		
}
