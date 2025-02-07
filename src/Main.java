import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FileInputStream myFile = null;
        FileOutputStream file = null;

        //open file when the program is called in the terminal, catches incorrect file names (for testing code enter "GarminActivities.csv"
        try {
            myFile = new FileInputStream(args[0]);
        } catch (FileNotFoundException e) {
            System.out.println("Could not open input file");
            System.exit(1);
        }

        //set the destination of desired information, try-catch set up for future errors that may occur when I open up the code for user interface
        try {
            file = new FileOutputStream("Pace.csv");
        }
        catch (FileNotFoundException e) {
            System.out.println("Could not open output file");
            System.exit(1);
        }

        //set up the file writing variables so that the classes that need it can write to the Pace file, which stores all pertinent paces over time
        Scanner fileReader = new Scanner(myFile);
        PrintWriter fileWriter = new PrintWriter(file);

        //create an object for the Pace class
        Pace pace = new Pace();
        pace.updatePaceFile(myFile,file,fileReader,fileWriter);

        //run the feature for calculating average pace in min:sec
        System.out.println("The average pace of these runs is " + Pace.averagePace() + ".");

        //create an object for the HeartRate class and the use of its methods
        HeartRate hr = new HeartRate();

        //create an object for the Consistency class and the use of its methods
        Consistency consistency = new Consistency();

        //create an object for the Goals class and the use of its methods
        Goals goals = new Goals();

        //create an object for the Mileage class and the use of its methods
        Mileage mileage = new Mileage();
    }
}