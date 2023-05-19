package cz.cuni.mff.zapoctak.evoluce.enviroment;
import cz.cuni.mff.zapoctak.evoluce.entities.*;
import cz.cuni.mff.zapoctak.evoluce.mapentities.*;
import cz.cuni.mff.zapoctak.evoluce.calculators.*;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * 
 * Takes care of loading and initialization of the Map- enviroment
 *
 */
public class MapLoading {
	/**
	 * plant species 
	 */
	protected ArrayList<PlantSpecies> plants =new ArrayList<PlantSpecies>();
	/**
	 * animal species
	 */
	protected ArrayList<AnimalSpecies> species=new ArrayList<AnimalSpecies>();
	/**
	 * coordinates of living animal 
	 */
	protected HashSet<Coordinates> animalsCoordinates= new HashSet<Coordinates>();
	protected int width;
	protected int height;
	protected Calculator calculator;
	protected float divider;
	/**
	 * Enviroment, there are all animal and plants stored 
	 */
	protected Item[][] map;
	protected Generator random=new Generator();
	
	/**
	 * Find all animals and node there coordinates.
	 */
	protected void findAnimals() {
		for (int i = 0; i < map.length; ++i) {
		    for (int j = 0; j < map[0].length; ++j) {
		        Item item = map[i][j];
		        if (item != null && item instanceof Animal) {
		            animalsCoordinates.add(new Coordinates(i, j));
		        }
		    }
		}
	}
	
	/**
	 * Load processed parameters from configuration file.
	 * @param params the parameters
	 */
	public void load(Parameters params ) {
		plants = params.getPlant();
		species = params.getSpecies();
		Header head=params.getHeader();
		width=head.getMapWidth();
		height=head.getMapHeight();
		calculator=head.getCalculator();
		divider=head.getDivider();
		map=new Item[width][height];
		fillMap();
		findAnimals();
	}
	/**
	 * Put animals and food on the map 
	 */
    private void fillMap() {
    	putAnimals();
    	putFood();
    }
    
    /**
     * Put one item on the map. item is animal or food.
     * @param item animal of food which is supposed to be on the map 
     * @return
     */
    protected Coordinates placeItem(Item item) {
    	Coordinates c=RandomCoordinates();
    	if (c.x > -1 && c.y > -1) {
    		map[c.x][c.y]=item;
    	}
    	return c;
    }
    
    /**
     * put given number of animals from each  animal species on the map.
     */
    private void putAnimals() {
    	   for(AnimalSpecies s : species) {
        
               for (int i = 0; i < s.getCount(); ++i) {
                   Animal a = new Animal(s.getSize(), s.getSense(), s.getDexterity(), s);
                   placeItem(a);
               }
    	   }
         
    }
    /**
     * put given number of food from each  plant species on the map.
     */
    protected void putFood() {
          for(PlantSpecies p: plants) {
            for (int i = p.getActualCount(); i < p.getCount(); ++i) {
                Food food = new Food(p);
                placeItem(food);
                food.plant.increaseActualCount();
            }
          }
    }
    /**
     * Get random coordinates of empty field on the map 
     * @return coordinates of empty field or (-1,-1)
     */
    private Coordinates RandomCoordinates() {
        int x = random.roll(width); 
        int y = random.roll(height);
        for (int i = 0; i < 5; ++i) { //try random position 5 times
            if (map[x][y] == null) return new Coordinates(x, y);
            x = random.roll(width);
            y = random.roll(height);
        }
        long startIndex = x * width + y;
        long places = width * height;
        for (long i = startIndex; i < places + startIndex; ++i) {  //random positions didn`t work find first possible pozition
            if (map[x][y] == null) return new Coordinates(x, y);
            long index = i % places;
            x = (int) (index / height);
            y = (int) (index % height);
        }
        if (map[x][y] == null) return new Coordinates(x, y);
        else {
            return new Coordinates(-1, -1);
        }
    }
}
