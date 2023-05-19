package cz.cuni.mff.zapoctak.evoluce.entities;

/**
 * 
 * Class describing one kind of plants.
 *
 */
public class PlantSpecies {
    private int id;
    private int actualCount;
    private String name;
    private int count;
    private float size;
    private float nourishment;
    private FoodChanger changer;

    public PlantSpecies() {
        this.id = -1;
        this.actualCount = -1;
        this.name = "";
        this.count = -1;
        this.size = -1;
        this.nourishment = -1;
        this.changer = new FoodChanger();
    }

    // getters
    public int getId() {
        return id;
    }

    public int getActualCount() {
        return actualCount;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public float getSize() {
        return size;
    }

    public float getNourishment() {
        return nourishment;
    }

    public FoodChanger getFoodChanger() {
        return changer;
    }

    // setters
    public void setId(int x) {
        if (x >= 0) {
            id = x;
        } else {
            throw new IllegalArgumentException("id of plants must be greater or equal to 0");
        }
    }

   
    public void setName(String x) {
        if (x.length() == 2) {
            name = x;
        } else {
            throw new IllegalArgumentException("length of the name must be 2 characters");
        }
    }

    public void setCount(int x) {
        if (x >= 0) {
            count = x;
        } else {
            throw new IllegalArgumentException("count of plants must be greater or equal to 0");
        }
    }

    public void setSize(float x) {
        if (x >= 0) {
            size = x;
        } else {
            throw new IllegalArgumentException("size of plants must be non negative");
        }
    }

    public void setNourishment(float x) {
        if (x > 0) {
            nourishment = x;
        } else {
            throw new IllegalArgumentException("nourishment of plants must be greater than 0");
        }
    }

    public void setChanger(int interval, float times, int max, int min) {
        if (interval > 0 && times > 0 && max >= min && min >= 0) {
            changer = new FoodChanger(interval, times, max, min);
        } else {
            throw new IllegalArgumentException("plans changer doesn't support these parameters");
        }
    }

    public void decreaseActualCount() {
        actualCount--;
    }

    public void increaseActualCount() {
        actualCount++;
    }

    public void print() {
        System.out.printf("Name: %s%n", name);
        System.out.printf("ID: %d%n", id);
        System.out.printf("Actual Count: %d%n", actualCount);
        System.out.printf("Count: %d%n", count);
        System.out.printf("Size: %f%n", size);
        System.out.printf("Nourishment: %f%n", nourishment);
        System.out.printf("Food Changer: interval=%d, times=%f, max=%d, min=%d%n",
            changer.getInterval(), changer.getTimes(), changer.getMax(), changer.getMin());
    }
}
