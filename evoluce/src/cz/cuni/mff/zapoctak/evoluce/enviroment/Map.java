

package cz.cuni.mff.zapoctak.evoluce.enviroment;

import cz.cuni.mff.zapoctak.evoluce.calculators.*;
import cz.cuni.mff.zapoctak.evoluce.entities.*;
import cz.cuni.mff.zapoctak.evoluce.mapentities.*;
import java.util.ArrayList;
import java.util.HashSet;



/**
 * 
 * Class with enviroment(animals plants and map).Takes care of managing things on the map, such as removing dead or eaten animals from the map, simulating one day on the map and
 * giving order to move to animals.
 *
 */
public class Map extends MapLoading {
	
    /**
     * coordinates where are animals which already moved 
     */
    private HashSet<Coordinates> takenCoordinates=new HashSet<Coordinates>();
    
	
	
	/**
	 * Give each animal order to move. 
	 */
	private void moveAnimals() {
		for (int i = 0; i < Animal.rounds; ++i) { 
		    HashSet<Coordinates> actualCoordinates = new HashSet<Coordinates>();

 		    for (Coordinates from : animalsCoordinates) {
		        if (!takenCoordinates.contains(from) && map[from.x][from.y] != null) { //wasnt eaten by enother animal and there is animal on the field.
		            Animal a = (Animal) map[from.x][from.y];
		            map[from.x][from.y]=null;
		            Coordinates to = a.makeStep(this, from, i, random);

		            if (!from.equals(to)) { //animal cant eat itself
		                float maxFood = a.species.getStopEat() * a.species.getReproduction() * calculator.hunger(a.getSize(), a.getSense(), a.getDexterity());
		                a.eat(map[to.x][to.y], maxFood);
		            }

		            takenCoordinates.add(to);
		            map[to.x][to.y] = a;
		            actualCoordinates.add(to);
		        }
		    }
		    animalsCoordinates = actualCoordinates;
		    takenCoordinates.clear();
		}
	}
	
	/**
	 * change maximum number of food on the map 
	 * 
	 * @param date which day of simulation it is.
	 */
	private void changeFoodCount(int date) {
		for (PlantSpecies f : plants) {
		    FoodChanger changer = f.getFoodChanger();
		    if (date % changer.getInterval() == 0) {
		        int x = (int) (changer.getTimes() * f.getCount());
		        x = Math.min(changer.getMax(), x);
		        x = Math.max(changer.getMin(), x);
		        f.setCount(x);
		    }
		}
	}
	 /**
	  * 
	  * @param c cordinates of field.
	  * @return true if  field is empty 
	  */
	public boolean isEmpty(Coordinates c) {
		if (width > c.x && c.x > -1 && height > c.y && c.y > -1) {
		    if (map[c.x][c.y] == null) {
		        return true;
		    }
		}
		return false;
		
	}
	
	/**
	 * Simulate one day on the map.
	 * 
	 * @param date number of the current day 
	 */
	public void Day(int date) {
	
		moveAnimals();
	    HashSet<Coordinates> newGeneration = new HashSet<Coordinates>();
	    for (Coordinates c : animalsCoordinates) {
	        Animal a = (Animal) map[c.x][c.y];
	        float hunger = calculator.hunger(a.getSize(), a.getSense(), a.getDexterity());
	        if (a.getEaten() < hunger) { //die of hunger
	            map[c.x][c.y] = null;
	        } else {	            
	            if (a.getEaten() > a.species.getReproduction() * hunger) { //can reproduce
	                Animal newAnimal = a.makeAnimal(random);
	                Coordinates newCoordinates = placeItem(newAnimal);
	                
	                if (newCoordinates.x > -1) { //can make new animal 
	                    newGeneration.add(newCoordinates);
	                    a.setEaten(a.getEaten() - hunger * a.species.getReproduction());
	                } else {
	                    a.setEaten(a.getEaten() - hunger);
	                }
	            }
	             else {
	            	 a.setEaten(a.getEaten() - hunger);
	            }
	            
	            newGeneration.add(c);
	            a.setEaten(a.getEaten() / divider);
	        }
	    }
	    
	    animalsCoordinates = newGeneration;
	    changeFoodCount(date);
	    putFood();
	}

	
	public Item[][] getMap(){
		return map;
	}
	
	
	public ArrayList<AnimalSpecies> getSpecies() {
	    return species;
	}

	public ArrayList<PlantSpecies> getPlants() {
	    return plants;
	}

	public HashSet<Coordinates> getAnimalCoor() {
	    return animalsCoordinates;
	}
	
	public int getWidth(){ return width; }
	public int getHeight(){ return height; }
	
	public Calculator getCalculator() {
		return calculator;
	}
}
