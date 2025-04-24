///Please use this code to see the average pace of over 500 runs from my personal running watch over the past 5 years.
///The pace class is almost complete, but check the comments for future methods in each of the classes.
///Use the terminal and write "GarminActivities.csv" as args[0] to have the code successfully run.

///If you have your own Garmin watch, go to the Garmin website and export the csv file with all the activity data!

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        FileInputStream myFile = null;
        //String fileName = args[0];
        String fileName = "src/GarminActivities.csv"; //
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

        try {
            myFile = new FileInputStream(fileName); //take command terminal input
        } catch (FileNotFoundException e) {
            System.out.println("Could not open input file");
            System.exit(1);
        }
        Scanner fileReader = new Scanner(myFile);
        //find the average pace for the added file
        System.out.println("The average pace of the added runs is: " + Pace.averagePaceFromNewFile(fileReader) + ".");

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
        fileReader = new Scanner(myFile);
        System.out.println("The max Average HR for one run out of them all is: " + HeartRate.findMaxHR(fileReader,0));

        try{
            myFile = new FileInputStream(fileName);
        }catch(FileNotFoundException e){
            System.out.println("Could not open input file");
            System.exit(1);
        }
        fileReader = new Scanner(myFile);
        //print the average of the average heart rates across all the runs
        System.out.println("The average heart rate of these runs is: " + HeartRate.averageHR(fileReader) + ".");

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
        Mileage.highToLow();

        try {
            myFile = new FileInputStream(fileName); //take command terminal input
        } catch (FileNotFoundException e) {
            System.out.println("Could not open input file");
            System.exit(1);
        }
        fileReader = new Scanner(myFile);
        //find the average mileage for the added file
        System.out.println("The average mileage for the added file is: " + String.format("%.2f",Mileage.averageMileage(fileReader)) + ".");

        //print the number of runs
        System.out.println("The total number of runs is: " + howManyRuns(fileName) + ".");

        //testing stacks
        Mileage.zeroToThree.reverse();
        //Mileage.zeroToThree.print();
        Mileage.threeToSix.reverse();
        //Mileage.threeToSix.print();
        Mileage.sixAndUp.reverse();
        //Mileage.sixAndUp.print();

        //calculate the average heart rate of 0-3 mile runs
        try{
            myFile = new FileInputStream(fileName);
        }catch(FileNotFoundException e){
            System.out.println("Could not open input file");
            System.exit(1);
        }
        fileReader = new Scanner(myFile);
        System.out.println("The average heart rate of less-than-three mile efforts is: " + HeartRate.averageHRrange(fileReader,Mileage.zeroToThree) + ".");
        fileReader.close();

        //calculate the average heart rate of 3-6 mile runs
        try{
            myFile = new FileInputStream(fileName);
        }catch(FileNotFoundException e){
            System.out.println("Could not open input file");
            System.exit(1);
        }
        fileReader = new Scanner(myFile);
        System.out.println("The average heart rate of 3-6 mile efforts is: " + HeartRate.averageHRrange(fileReader,Mileage.threeToSix) + ".");
        fileReader.close();

        //calculate the average heart rate of 6+ mile runs
        try{
            myFile = new FileInputStream(fileName);
        }catch(FileNotFoundException e){
            System.out.println("Could not open input file");
            System.exit(1);
        }
        fileReader = new Scanner(myFile);
        System.out.println("The average heart rate of 6-or-more mile efforts is: " + HeartRate.averageHRrange(fileReader,Mileage.sixAndUp) + ".");
        fileReader.close();
    }

    public static int howManyRuns(String fileName){
        FileInputStream myFile = null;
        int counter = 0;
        try{
            myFile = new FileInputStream(fileName);
        }catch(FileNotFoundException e){
            System.out.println("Could not open input file");
            System.exit(1);
        }
        Scanner fileReader = new Scanner(myFile);
        while(fileReader.hasNextLine()){
            String data = fileReader.nextLine();
            String[] arrOfData = data.split(",");
            if (arrOfData[0].equals("Running")){
                counter++;
            }
        }
        return counter;
    }
}