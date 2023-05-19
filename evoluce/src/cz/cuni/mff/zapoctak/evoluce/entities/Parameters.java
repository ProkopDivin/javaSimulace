package cz.cuni.mff.zapoctak.evoluce.entities;

import java.util.ArrayList;

/**
 * 
 * Class foe keeping parameters loaded from configuration file.
 *
 */
public class Parameters {
   private Header head;
   private ArrayList<AnimalSpecies> species;
   private ArrayList<PlantSpecies> plant;
   public Parameters(Header head, ArrayList<AnimalSpecies> species,ArrayList<PlantSpecies> plant){
	   this.head=head;
	   this.species=species;
	   this.plant=plant;	   
   }
   /**
    * 
    * @return parameters for the simulation
    */
   public Header getHeader() {return head;}
   /**
    * 
    * @return parameters for all animal species
    */
   public ArrayList<AnimalSpecies> getSpecies() {return species;}
   /**
    * 
    * @return parameters for all plant species
    */
   public ArrayList<PlantSpecies> getPlant() {return plant;}
}
