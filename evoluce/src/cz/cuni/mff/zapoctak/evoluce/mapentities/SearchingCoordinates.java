package cz.cuni.mff.zapoctak.evoluce.mapentities;

public class SearchingCoordinates {
	private int min;
	private int max;
	private int i;
	private int j;
	private int direction=0;
	private Coordinates item;
	public boolean valid =true;
	public Coordinates get() {
	    return item;
	}
	
	/**
	 * Iterator throughout coordinates which animal see
	 * @param min distance
	 * @param max distance
	 */
	public SearchingCoordinates(int min, int max) {
	    this.max = max;
	    // indexes
	    this.i = min;
	    this.j = -1;
	}
	/**
	 * Iterate throughout coordinates in one from 4 directions 
	 */
	private void nextInOneDirection() {
	    if (j <= i && Math.abs(i - j) + j < max) {
	        // Coordinates in distance i
	        j += 1;
	    } else if (i < max) {
	        // Increase distance
	        i += 1;
	        j = 0; // Start new loop
	        // Generate first coordinates
	    } else {
	        valid = false;
	    }
    }
	/**
	 * 
	 * @return next coordination to search 
	 */
	public boolean next() {
	    direction = direction % 4; // Four directions
	    if (direction == 0) {
	        nextInOneDirection();
	    }
	    int x = i - j;
	    int y = j;
	    if (!valid) {
	        return false;
	    }
	    if (direction == 0) {
	        item = new Coordinates(x, y);
	        direction += 1;
	    } else if (direction == 1) {
	        item = new Coordinates(-x, -y);
	        direction += 1;
	    } else if (direction == 2) {
	        if (x == 0 || y == 0) { // Don't want to iterate repeated coordinates
	            direction = 0;
	            return next();
	        } else {
	            item = new Coordinates(x, -y);
	            direction += 1;
	        }
	    } else if (direction == 3) {
	        item = new Coordinates(-x, y);
	        direction += 1;
	    }
	    return true;
	}
}
