import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Mileage {

    //add the new list of mileages to the Mileage.csv csv
    public static void updateMileageFile(FileInputStream myFile, Scanner fileReader) {
        fileReader = new Scanner(myFile);
        FileOutputStream mileages = null;
        //set the destination of MILEAGE information, try-catch set up for future errors that may occur when I open up the code for user interface
        try {
            mileages = new FileOutputStream("Mileage.csv");
        }
        catch (FileNotFoundException e) {
            System.out.println("Could not open output file");
            System.exit(1);
        }
        PrintWriter fileWriterMileages = new PrintWriter(mileages);

        //read through the main input file and parse out the mileages from the "Running" activities, avoiding empty fields as well
        while (fileReader.hasNextLine()) {
            String data = fileReader.nextLine();
            String[] arrOfData = data.split(",");
            if (arrOfData[0].equals("Running")) {
                try {
                    fileWriterMileages.print(arrOfData[4]);
                    fileWriterMileages.print(",");
                } catch (NumberFormatException e) {
                    System.out.println("There is an error with the running data in this line.");
                }
            }
        }

        fileReader.close();
        fileWriterMileages.flush();
        fileWriterMileages.close();
    }

    //method for compiling all mileage in total for a quick print of how many miles have been run in a date range
    public static String averageMileage(){

        String averageMileage = null;
        FileInputStream mileageFile = null;
        int miles = 0;
        int decimals = 0;
        int counter = 0;

        try {
            mileageFile = new FileInputStream("Mileage.csv.csv");
        } catch (FileNotFoundException e) {
            System.out.println("Could not open input file");
            System.exit(1);
        }

        Scanner mileageReader = new Scanner(mileageFile);

        //sort through the array list by splitting up the commas, removing quotation marks,and separating the minutes and seconds
        String data = mileageReader.nextLine();
        String[] arrOfData = data.split(",");
        boolean status = true;
        for (int i = 0; i < arrOfData.length; i++){
            char[] dats = new char[arrOfData[i].length()];
            for (int j = 0; j < dats.length; j++) {
                dats[j] = arrOfData[i].charAt(j);
                if (dats[j] != ':' || dats[j] == '-'){
                    status = false;
                }
            }
            if (!status){
                try {
                    String timeArray= arrOfData[i].replace("\"","");
                    String[] newTimeArray = timeArray.split(":");
                    int mile = Integer.parseInt(newTimeArray[0]);
                    int decimal = Integer.parseInt(newTimeArray[1]);
                    miles += mile;
                    decimals += decimal;
                    counter++;
                }
                catch(ArrayIndexOutOfBoundsException | NumberFormatException e){
                    System.out.println("There is an error with the running data in this line.");
                }
            }
        }

        //calculate the average


        //print the average and check to see if it is a single digit second value so that the appropriate leading zero can be added
        if (seconds < 10){
            averagePace = minutes + ":0" + seconds;
        }
        else {
            averagePace = minutes + ":" + seconds;
        }
        return averagePace;
    }

    //method for reporting the mileage and the total number of runs that were done in the past week (if the user is using it on a weekly basis)
}
