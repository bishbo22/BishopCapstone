///Please use this code to see the average pace of over 500 runs from my personal running watch over the past 5 years.
///The pace class is almost complete, but check the comments for future methods in each of the classes.
///Use the terminal and write "GarminActivities.csv" as args[0] to have the code succesfully run.

///If you have your own Garmin watch, go to the Garmin website and export the csv file with all the activity data!

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FileInputStream myFile = null;

        //open file when the program is called in the terminal, catches incorrect file names (for testing code enter "GarminActivities.csv"
        try {
            myFile = new FileInputStream(args[0]);
        } catch (FileNotFoundException e) {
            System.out.println("Could not open input file");
            System.exit(1);
        }

        //set up the file writing variables so that the classes that need it can write to the Pace file, which stores all pertinent paces over time
        Scanner fileReader = new Scanner(myFile);

        //store pace into its own file
        Pace.updatePaceFile(myFile,fileReader);

        //run the feature for calculating average pace in min:sec
        System.out.println("The average pace of these runs is " + Pace.averagePace() + ".");

        //create an object for the HeartRate class and the use of its methods
        HeartRate hr = new HeartRate();

        //create an object for the Consistency class and the use of its methods
        Consistency consistency = new Consistency();

        //create an object for the Goals class and the use of its methods
        Goals goals = new Goals();

        //store mileage in its own file
        Mileage.updateMileageFile(myFile,fileReader);

    }
}