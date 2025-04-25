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
        //ask for the file name to be added
        //String fileName = args[0];
        //String fileName = "src/Week2.csv";
        Scanner kb = new Scanner(System.in);
        System.out.print("Type the name of the file you'd like to add: ");
        String fileName = kb.next();
        System.out.println();

        //initialize the file being read
        FileInputStream myFile = null;

        /// Storing data

        //store pace into its own file
        try {
            myFile = new FileInputStream(fileName); //take command terminal input
        } catch (FileNotFoundException e) {
            System.out.println("Could not open input file");
            System.exit(1);
        }
        Pace.updatePaceFile(myFile);

        //store heart rate into its own file
        try{
            myFile = new FileInputStream(fileName);
        }catch(FileNotFoundException e){
            System.out.println("Could not open input file");
            System.exit(1);
        }
        HeartRate.updateHRFile(myFile);

        //store mileage into its own file
        try{
            myFile = new FileInputStream(fileName);
        }catch(FileNotFoundException e){
            System.out.println("Could not open input file");
            System.exit(1);
        }
        Mileage.updateMileageFile(myFile);

        //print the number of runs
        System.out.println("The total number of runs from this file is: " + howManyRuns(fileName) + ".");

        //show the highest mileage run with merge sorting
        Mileage.highToLow();

        /// Pace

        //run the feature for calculating average pace in min:sec for all (present and past) runs
        String paceOverall = Pace.averagePace();
        System.out.println("The average pace of these runs is " + paceOverall + ".");

        //find the average pace of just the added file
        try {
            myFile = new FileInputStream(fileName); //take command terminal input
        } catch (FileNotFoundException e) {
            System.out.println("Could not open input file");
            System.exit(1);
        }
        Scanner fileReader = new Scanner(myFile);
        String paceNew = Pace.averagePaceFromNewFile(fileReader);
        System.out.println("The average pace of the added runs is: " + paceNew + ".");

        /// Mileage

        //run the feature for calculating total mileage in miles.decimals
        String mileageOverall = Mileage.totalMileage();
        System.out.println("The total mileage of these runs is " + mileageOverall + ".");

        //find the average mileage for the added file
        try {
            myFile = new FileInputStream(fileName); //take command terminal input
        } catch (FileNotFoundException e) {
            System.out.println("Could not open input file");
            System.exit(1);
        }
        fileReader = new Scanner(myFile);
        Double averageMileageNew = Mileage.averageMileage(fileReader);
        System.out.println("The average mileage for the added file is: " + String.format("%.2f",averageMileageNew) + ".");

        //reverse the order of the stacks so that they can be emptied appropriately
        Mileage.zeroToThree.reverse();
        Mileage.threeToSix.reverse();
        Mileage.sixAndUp.reverse();

        /// Heart Rate

        //find max average HR with recursive search of the added file
        try{
            myFile = new FileInputStream(fileName);
        }catch(FileNotFoundException e){
            System.out.println("Could not open input file");
            System.exit(1);
        }
        fileReader = new Scanner(myFile);
        int maxHRnew = HeartRate.findMaxHR(fileReader,0);
        System.out.println("The max average HR for the added runs is: " + maxHRnew);

        //print the average of the average heart rates from the added file
        try{
            myFile = new FileInputStream(fileName);
        }catch(FileNotFoundException e){
            System.out.println("Could not open input file");
            System.exit(1);
        }
        fileReader = new Scanner(myFile);
        int averageHRnew = HeartRate.averageHR(fileReader);
        System.out.println("The average heart rate of the added runs is: " + averageHRnew + ".");

        //calculate the average heart rate of 0-3 mile runs
        try{
            myFile = new FileInputStream(fileName);
        }catch(FileNotFoundException e){
            System.out.println("Could not open input file");
            System.exit(1);
        }
        fileReader = new Scanner(myFile);
        int averageHRnew03 = HeartRate.averageHRrange(fileReader,Mileage.zeroToThree);
        System.out.println("The average heart rate of less-than-three mile efforts is: " + averageHRnew03 + ".");

        //calculate the average heart rate of 3-6 mile runs
        try{
            myFile = new FileInputStream(fileName);
        }catch(FileNotFoundException e){
            System.out.println("Could not open input file");
            System.exit(1);
        }
        fileReader = new Scanner(myFile);
        int averageHRnew36 = HeartRate.averageHRrange(fileReader,Mileage.threeToSix);
        System.out.println("The average heart rate of 3-6 mile efforts is: " + averageHRnew36 + ".");

        //calculate the average heart rate of 6+ mile runs
        try{
            myFile = new FileInputStream(fileName);
        }catch(FileNotFoundException e){
            System.out.println("Could not open input file");
            System.exit(1);
        }
        fileReader = new Scanner(myFile);
        int averageHRnew60 = HeartRate.averageHRrange(fileReader,Mileage.sixAndUp);
        System.out.println("The average heart rate of 6-or-more mile efforts is: " + averageHRnew60 + ".");
        fileReader.close();

        //Report what the next seven runs should be based on this recent week's data
        System.out.println();
        System.out.println("Here are your goals for the future based on your recent runs:");
        System.out.println("*If a target says \"0\" or \"0.0,\" then there were no runs that fit that category.*");
        System.out.println("EASY RUNS (0-3 miles): Target Average Heart Rate: " + averageHRnew03*0.95 + "BPM");
        System.out.println("MEDIUM RUNS (3-6 miles): Target Average Heart Rate: " + averageHRnew36*0.95 + "BPM");
        System.out.println("EASY RUNS (6+ miles): Target Average Heart Rate: " + averageHRnew60*0.95 + "BPM");
        System.out.println();
        System.out.println("Target Weekly Mileage: " + String.format("%.2f",averageMileageNew*6*1.1) + " miles");
        String newpace = paceNew.replace("\"","");
        String[] paceIncreased = newpace.split(":");
        double minutes = Integer.parseInt(paceIncreased[0]);
        double seconds = Integer.parseInt(paceIncreased[1]);
        double totalTime = (minutes * 60) + seconds;
        totalTime = totalTime * 0.99;
        int minute = (int) Math.floor(totalTime/60);
        int second = (int) Math.floor(totalTime%60);
        System.out.println("By the end of this future set of runs, you target average pace is: " + minute + ":" + second);
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