package cz.cuni.mff.zapoctak.evoluce.enviroment;
import cz.cuni.mff.zapoctak.evoluce.mapentities.*;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * 
 * Class for printing the map
 *
 */
public class MapPrinter {
	/**
	 * Print the map with animals and food
	 * @param map the map with animals and food
	 * @param day number of day
	 * @param file output file to write the log
	 * @throws IOException
	 */
   public static void printMap(Item[][] map, String day, BufferedWriter file)throws IOException {
       int cellWidth = 3;
       int rowWidth = cellWidth * map[0].length + 1;
       file.write("day: " + day);
       printRowSeparator(file, rowWidth);
       
       for (Item[] line : map) {
           printRowSeparator(file, rowWidth);
           
           for (Item item : line) {
               file.write("|");
               
               if (item == null) {
                   file.write("  ");
               } else if (item instanceof Animal) {
                   file.write(((Animal) item).species.getName());
               } else if (item instanceof Food) {
                   file.write(((Food) item).plant.getName());
               }
           }
           file.write('|');
           file.newLine();
       }
       
       printRowSeparator(file, rowWidth);
   }
   /**
    * Print horizontal line to separate cells.
    * @param file file with the output
    * @param rowWidth width of row
    * @throws IOException
    */
   private static void printRowSeparator(BufferedWriter file, int rowWidth) throws IOException {
       for (int i = 0; i < rowWidth; i++) {
           file.write("-");
       }
       file.newLine();
   }
}
