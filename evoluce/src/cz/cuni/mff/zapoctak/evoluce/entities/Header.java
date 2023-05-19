package cz.cuni.mff.zapoctak.evoluce.entities;

import cz.cuni.mff.zapoctak.evoluce.calculators.*;

/**
 * 
 * Class describing the header parameters in header file.
 *
 */
public class Header {
    private int days = -1;
    private int mapWidth = -1;
    private int mapHeight = -1;
    private Calculator calculator = null;
    private float divider = -1;

    public void print() {
        // Implementation goes here
    	System.out.printf("days: %d%n",days);
    	System.out.printf("mapWidth: %d%n",mapWidth);
    	System.out.printf("mapHeight: %d%n",mapHeight);
    	System.out.printf("calculator: %d%n",calculator.getId());
    	System.out.printf("divider: %f%n",divider);
    }

    // Getters
    public int getDays() {
        return days;
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public float getDivider() {
        return divider;
    }

    public Calculator getCalculator() {
        return calculator;
    }

    // Setters
    public void setDays(int x) {
        days = x;
    }

    public void setDivider(float x) {
        divider = x;
    }

    public void setMapWidth(int x) {
        mapWidth = x;
    }

    public void setMapHeight(int x) {
        mapHeight = x;
    }

    public void setCalculator(int x, float size, float sense, float dexterity) {
     
            switch (x) {
                case 0:
                    calculator = new Calculator(size, sense, dexterity);
                    break;
                case 1:
                    calculator = new PlusCalculator(size, sense, dexterity);
                    break;
                case 2:
                    calculator = new KineticCalculator(size, sense, dexterity);
                    break;
                default:
                    throw new IllegalArgumentException("Error: calculator must be non-negative integer smaller than 3.");
                    
               
        }
    }
}
