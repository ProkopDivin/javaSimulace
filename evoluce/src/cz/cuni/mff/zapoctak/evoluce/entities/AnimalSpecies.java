package cz.cuni.mff.zapoctak.evoluce.entities;


/**
 * 
 * Class describing one animal species.
 *
 */
public class AnimalSpecies {
    private int id;
    private String name;
    private float stop_eat;
    private float size;
    private float sense;
    private float dexterity;
    private float reproduction;
    private float mutation;
    private int count;
    private NutrionsMultiplicators food;

    public AnimalSpecies() {
        this.name = "";
        this.size = -1;
        this.sense = -1;
        this.dexterity = -1;
        this.reproduction = -1;
        this.mutation = -1;
        this.count = -1;
        this.stop_eat = -1;
        this.id = -1;
    }

    //getters 
    public String getName() {
        return name;
    }
    
    public int getId() {
        return id;
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

    public float getReproduction() {
        return reproduction;
    }

    public float getMutation() {
        return mutation;
    }

    public int getCount() {
        return count;
    }

    public float getStopEat() {
        return stop_eat;
    }

    public NutrionsMultiplicators getFood() {
        return food;
    }

    //setters
    public void setId(int x) {
        if (x >= 0) {
            id = x;
        } else {
            throw new IllegalArgumentException("id of animal must be positive or 0");
        }
    }

    public void setName(String x) {
        if (x.length() == 2) {
            name = x;
        } else {
            throw new IllegalArgumentException("name of animal must have length 2");
        }
    }

    public void setSize(float x) {
        if (x > 0) {
            size = x;
        } else {
            throw new IllegalArgumentException("size of animal must be positive");
        }
    }

    public void setSense(float x) {
        if (x >= 1) {
            sense = x;
        } else {
            throw new IllegalArgumentException("sense of animal must be greater or equal to 1");
        }
    }

    public void setDexterity(float x) {
        if (x >= 0) {
            dexterity = x;
        } else {
            throw new IllegalArgumentException("dexterity of animal must be greater or equal to 0");
        }
    }

    public void setReproduction(float x) {
        if (x >= 1) {
            reproduction = x;
        } else {
            throw new IllegalArgumentException("reproduction of animal must be greater or equal to 1");
        }
    }

    public void setMutation(float x) {
        if (x >= 0 && x < 1) {
            mutation = x;
        } else {
            throw new IllegalArgumentException("mutation of animal must be greater or equal to 0 and less than 1");
        }
    }

    public void setCount(int x) {
        if (x >= 0) {
            count = x;
        } else {
            throw new IllegalArgumentException("count of animal must be greater or equal to 0");
        }
    }

    public void setFood(float x, float y) {
        if (x >= 0 && y >= 0) {
            food = new NutrionsMultiplicators(x, y);
        } else {
            throw new IllegalArgumentException("nutrions multiplicators of animal must be greater or equal to 0");
        }
    }

    public void setStop_eat(float x) {
        if (x >= 1) {
            stop_eat = x;
        } else {
            throw new IllegalArgumentException("stop_eat of animal must be greater or equal to 1");
        }
    }
    

    public void decreaseCount() {
        count--;
    }

    public void print() {
    	 System.out.printf("Name: %s%n", name);
         System.out.printf("ID: %d%n", id);
         System.out.printf("Stop eat: %f%n" , stop_eat);
         System.out.printf("Count: %d%n", count);
         System.out.printf("Size: %f%n", size);

        System.out.printf("Sense: %f%n" , sense);
        System.out.printf("Dexterity: %f%n" , dexterity);
        System.out.printf("Reproduction %f%n: " , reproduction);
        System.out.printf("Mutation: %f%n" , mutation);

        System.out.printf("Food: %s%n" , food.toString());
    }
}
