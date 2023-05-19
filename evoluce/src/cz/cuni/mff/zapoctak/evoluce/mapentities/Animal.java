package cz.cuni.mff.zapoctak.evoluce.mapentities;

import cz.cuni.mff.zapoctak.evoluce.entities.*;
import cz.cuni.mff.zapoctak.evoluce.enviroment.*;

/**
 * 
 * Describing one animal on the map.
 *
 */
public class Animal extends Item { 
	 private float size=-1;
	 private float dexterity =-1;
	 private float sense=-1;
	 public AnimalSpecies species;
	 public float eaten=0;
	 public static int rounds =3;
	 private static final int[][] relativeMoves={{0,-1},{0,1},{1,0},{-1,0} };
	 private static final int[][] mutations ={{1,1,1},
			 								{1,-1,1},	 
			 								{1,1,-1},
			 								{1,-1,-1},
			 								{-1,1,1},
			 								{-1,1,-1},							
			 								{-1,-1,1},
			 								{-1,-1,-1}};
	 
	 public Animal(float siz, float sense, float dexterity, AnimalSpecies s) {
		 this.size = siz;
		 this.sense = sense;
		 this.dexterity = dexterity;
		 this.species = s;
		 }
	  
	 
	 /**
	  * Make a descendant
	  * @param random random number generator 
	  * @return animal 
	  */
	  public Animal makeAnimal( Generator random) {
		  int roll = random.roll(8);
		  float new_size = size + mutations[roll][0] * size * species.getMutation();
		  float new_sense = sense + mutations[roll][1] * sense * species.getMutation();
		  float new_dexterity = dexterity + mutations[roll][2] * dexterity * species.getMutation();
		  return new Animal(new_size, new_sense, new_dexterity, species);
	 }
	 /**
	  * Eat food or another animal 
	  * @param item something to eat
	  * @param maxFood maximum food which can animal eat
	  */
	 public void eat(Item item,float maxFood) {
		 if (item == null) {
		        return;
		    } else {
		        if (item instanceof Animal && item != null) {
		            Animal a = (Animal) item;
		            eaten += a.getSize() * species.getFood().getMeat();
		        } else {
		            Food f = (Food) item;
		            eaten += f.plant.getNourishment() * species.getFood().getPlants();
		            f.plant.decreaseActualCount();
		        }
		        eaten = Math.min(maxFood, eaten);
		    }
	 }
	 
	 /**
	  * Say where animal will go.
	  * Animals move in turns to be more fair, it doesn`t depends on the order in which animals are moving.
	  * @param map map with animals and food
	  * @param coor current coordination of animal 
	  * @param turn number of turn 
	  * @param random random number generator 
	  * @return coordinates where animal will go 
	  */
	 public Coordinates makeStep(Map map, Coordinates coor, int turn, Generator random) {
		 int step;
		    int wholeDexterity = (int) dexterity;
		    if (turn == rounds - 1) {
		        step = (wholeDexterity / rounds) + (wholeDexterity % rounds);
		    } else {
		        step = (wholeDexterity / rounds);
		    }
		    Coordinates somethingToEat = searchAround(map, coor, step);
		    float hunger = map.getCalculator().hunger(size, sense, dexterity);

		    if (somethingToEat.x == -1 || eaten > species.getStopEat() * species.getReproduction() * hunger) {
		        return randomEmptyBox(map, coor, step, random);
		    } else {
		        if (somethingToEat.distance(coor) > step) {
		            return goCloser(map, coor, coor.directionTo(somethingToEat), step);
		        } else {
		            return somethingToEat;
		        }
		    }
	 }
	 
	 /**
	  * Search map and foud where animal want to go 
	  * @param m map with enviroment 
	  * @param coor current coordinates
	  * @param step how far can animal go in one turn 
	  * @return return coordinates where animal want to go or wan to go closer to.
	  */
	 private Coordinates searchAround(Map m, Coordinates coor, int step) {
		    Coordinates somethingToEat = new Coordinates();
		    int isense = (int) sense;
		    float maxMeal = 0;
		    boolean reachable = true;
		    SearchingCoordinates searcher = new SearchingCoordinates(1, isense);
		    while (searcher.next()) {
		        Coordinates relativeMove = searcher.get();
		        Coordinates c = new Coordinates(relativeMove.x + coor.x , relativeMove.y + coor.y);
		        if (m.getWidth() > c.x && c.x > -1 && m.getHeight() > c.y && c.y > -1) {
		            Item item = m.getMap()[c.x][c.y];
		            if (item != null) {
		                float meal = calcMeal(item);
		                if (meal > maxMeal) {
		                    if (reachable) {
		                        maxMeal = meal;
		                        somethingToEat.x=c.x;
		                        somethingToEat.y=c.y;
		                    } else if (maxMeal == 0) {
		                        maxMeal = meal;
		                        somethingToEat.x=c.x;
		                        somethingToEat.y=c.y;
		                    }
		                }
		                if (relativeMove.length() > step) {
		                    if (reachable && maxMeal > 0) {
		                        return somethingToEat;
		                    }
		                    reachable = false;
		                }
		            }
		        }
		    }
		    if (maxMeal > 0) {
		        return somethingToEat;
		    } else {
		        return new Coordinates(-1, -1);
		    }
		}
	 
	 /**
	  * Calculate how many nutrition will animal get from food 
	  * @param item something to eat 
	  * @return how mutch nutrition will animal get  by eating this item 
	  */
	 private float calcMeal(Item item) {
		    float meal;
		    // Calculating the meal value
		    if (item instanceof Food) {
		        Food food = (Food) item;
		        if (food.plant.getSize() > size) {
		            meal = -1; // Can't reach the food
		        } else {
		            meal = species.getFood().getPlants() * food.plant.getNourishment();
		        }
		    } else {
		        Animal animal = (Animal) item;
		        if (animal.species.getId() == species.getId() || animal.getSize() > size) {
		            meal = -1; // Don't allow cannibalism or can't be eaten due to size
		        } else {
		            meal = species.getFood().getMeat() * animal.getSize();
		        }
		    }
		    return meal;
		}
	 
	 /**
	  * If animal doesn`t know where to move, this function return random field 
	  * @param m enviromet with animals and food
	  * @param coor current coordinates
	  * @param step how fat animal can move 
	  * @param random random number generator 
	  * @return coordinates of random field 
	  */
	 private Coordinates randomEmptyBox(Map m, Coordinates coor, int step, Generator random) {
		    for (int i = step; i > 0; --i) { // length of jump
		        for (int j = 0; j <= i; ++j) {
		            int x = (i - j);       
		            int randomIndex = random.roll(4);
		            for (int k = randomIndex; k < 4 + randomIndex; ++k) {
		                int index = k % 4;
		                Coordinates c = new Coordinates(x * relativeMoves[index][0] + coor.x, x * relativeMoves[index][1] + coor.y);
		                if (m.isEmpty(c)) {
		                    return c;
		                }
		            }
		        }
		    }
		    return coor; // nowhere to move
		}
	 /**
	  * Animal can`t go to some field, because it is far, so it will find pozition closer to that field.
	  * @param m enviroment 
	  * @param coor current coordination
	  * @param direction direction of movement 
	  * @param step how far can animal go 
	  * @return coordinates where animal will go 
	  */
	 private Coordinates goCloser(Map m, Coordinates coor, Coordinates direction, int step) {
		    int mx = 1; // direction in x axis
		    int my = 1; // direction in y axis
		    if (direction.x < 0) {
		        mx = -1;
		    }
		    if (direction.y < 0) {
		        my = -1;
		    }
		    for (int i = step; i > 0; --i) {
		        for (int j = 0; j <= i; ++j) {
		            if (i - j <= Math.abs(direction.x) && j <= Math.abs(direction.y)) {
		                Coordinates c = new Coordinates((i - j) * mx + coor.x, j * my + coor.y);
		                if (m.isEmpty(c)) {
		                    return c;
		                }
		            }
		        }
		    }
		    return coor; // can't go closer, so it will stay on the same spot
		}
	 
	 public float getSize() {
		    return size;
		}

	  public float getSense() {
		    return sense;
	  }

	  public float getDexterity() {
		    return dexterity;
	  }
	  public float getEaten() {
		    return eaten;
	  }
	  public void setEaten(float x) {
		    eaten=x;
	  }
	 
}
