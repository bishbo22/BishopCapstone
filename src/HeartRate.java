import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
//average heart rates
public class HeartRate {

    //method for creating a mutable file of heart rate data for the subsequent methods' use
    public static void updateHRFile(FileInputStream myFile) {
        Scanner fileReader = new Scanner(myFile);
        FileOutputStream heartRates = null;
        //set the destination of MILEAGE information, try-catch set up for future errors that may occur when I open up the code for user interface
        try {
            heartRates = new FileOutputStream("HeartRate.csv");
        }
        catch (FileNotFoundException e) {
            System.out.println("Could not open output file");
            System.exit(1);
        }
        PrintWriter fileWriterHeartRate = new PrintWriter(heartRates);
        //read through the main input file and parse out the mileages from the "Running" activities, avoiding empty fields as well
        while (fileReader.hasNextLine()) {
            String data = fileReader.nextLine();
            String[] arrOfData = data.split(",");
            if (arrOfData[0].equals("Running")) {
                try {
                    fileWriterHeartRate.print(arrOfData[7]);
                    fileWriterHeartRate.print(",");
                } catch (NumberFormatException e) {
                    System.out.println("There is an error with the running data in this line.");
                }
            }
        }
        fileReader.close();
        fileWriterHeartRate.flush();
        fileWriterHeartRate.close();
    }

    //find max heart rate with recursion
    public static int findMaxHR(Scanner fileReader,int maxHR){
        if (!fileReader.hasNextLine()){ //base case, no lines left to check
            return maxHR;
        }
        String data = fileReader.nextLine();
        String[] arrOfData = data.split(",");
        int curr;
        if (arrOfData[0].equals("Running")){
                try{
                    arrOfData[7]=arrOfData[7].replace("\"", "");
                    curr = Integer.parseInt(arrOfData[7]);
                    if (curr > maxHR){
                        maxHR = curr;
                    }
                }catch(NumberFormatException e) {
                    System.out.println("Invalid Heart Rate Data.");
                }
        }
        return findMaxHR(fileReader,maxHR);
    }

    //method for calculating the average heart rate on all running efforts

    //method for seeing the average heart rate of certain distances (0-3 miles, 3.1-5 miles, and 5.1 and greater)
}
