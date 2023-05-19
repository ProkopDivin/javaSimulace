package cz.cuni.mff.zapoctak.evoluce.mapentities;

/**
 * 
 * Representing coordinates
 *
 */
public class Coordinates {
   public int x;
   public int y;
   
   public Coordinates() {
	   x=0;
	   y=0;
   }
	public Coordinates(int x, int y){
		this.x=x;
		this.y=y;
	}
	public int distance(Coordinates p) {
	    return Math.abs(x - p.x) + Math.abs(y - p.y);
	}

	public int length() {
	    return Math.abs(x) + Math.abs(y);
	}

	public Coordinates directionTo(Coordinates c) {
	    return new Coordinates(c.x - x, c.y - y);
	}
	@Override
	/**
	 * Have to be here because of hash set 
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinates c=(Coordinates) obj;
		return x==c.x && y==c.y ;
	}
	@Override
	public int hashCode() {
		return (x+y)*(x+y+1)/2+x;
	}
}
