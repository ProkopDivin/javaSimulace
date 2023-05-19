package cz.cuni.mff.zapoctak.evoluce;

import cz.cuni.mff.zapoctak.evoluce.simulation.Simulation;





/**
 * @author Prokop Divin
 * 
 *
 */

public class Main {
   
	public static void main(String[] args) {
		if(args.length==2) {
		   Simulation s=new Simulation();
		   s.load(args[0], args[1]);
		   if(s.ready) {
			   s.start();
			   s.save();
		}
		}else {
			System.out.println("Error 2 arguments required.");
		}
	}
}
