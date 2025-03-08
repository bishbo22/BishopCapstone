///Please use this code to see the average pace of over 500 runs from my personal running watch over the past 5 years.
///The pace class is almost complete, but check the comments for future methods in each of the classes.
///Use the terminal and write "GarminActivities.csv" as args[0] to have the code succesfully run.

///If you have your own Garmin watch, go to the Garmin website and export the csv file with all the activity data!

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        FileInputStream myFile = null;
        //String fileName = args[0];
        String fileName = "src/GarminActivities.csv";
        //open file when the program is called in the terminal, catches incorrect file names (for testing code enter "GarminActivities.csv"
        try {
            myFile = new FileInputStream(fileName); //take command terminal input
        } catch (FileNotFoundException e) {
            System.out.println("Could not open input file");
            System.exit(1);
        }

        //store pace into its own file
        Pace.updatePaceFile(myFile);

        //run the feature for calculating average pace in min:sec
        System.out.println("The average pace of these runs is " + Pace.averagePace() + ".");

        //create an object for the HeartRate class and the use of its methods
        try{
            myFile = new FileInputStream(fileName);
        }catch(FileNotFoundException e){
            System.out.println("Could not open input file");
            System.exit(1);
        }
        HeartRate.updateHRFile(myFile);

        //find max HR with recursive search of the original csv
        try{
            myFile = new FileInputStream(fileName);
        }catch(FileNotFoundException e){
            System.out.println("Could not open input file");
            System.exit(1);
        }
        Scanner fileReader = new Scanner(myFile);
        System.out.println("The max Average HR for one run out of them all is: " + HeartRate.findMaxHR(fileReader,0));

        //create an object for the Consistency class and the use of its methods
        //Consistency consistency = new Consistency();

        //create an object for the Goals class and the use of its methods
        //Goals goals = new Goals();

        //store mileage in its own file
        try{
            myFile = new FileInputStream(fileName);
        }catch(FileNotFoundException e){
            System.out.println("Could not open input file");
            System.exit(1);
        }
        Mileage.updateMileageFile(myFile);

        //run the feature for calculating average mileage in miles.decimals
        System.out.println("The total mileage of these runs is " + Mileage.totalMileage() + ".");

        //organize the mileage document from low to high
        Mileage.lowToHigh();
    }
}