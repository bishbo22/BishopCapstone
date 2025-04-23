import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Pace {

    //add the new list of paces to the Pace csv
    public static void updatePaceFile(FileInputStream myFile) {
        Scanner fileReader = new Scanner(myFile);
        FileOutputStream paces = null;
        //set the destination of PACE information, try-catch set up for future errors that may occur when I open up the code for user interface
        try {
            paces = new FileOutputStream("Pace.csv");
        }
        catch (FileNotFoundException e) {
            System.out.println("Could not open output file");
            System.exit(1);
        }
        PrintWriter fileWriterPaces = new PrintWriter(paces);
        //read through the main input file and parse out the paces from the "Running" activities, avoiding empty fields as well
        while (fileReader.hasNextLine()) {
            String data = fileReader.nextLine();
            String[] arrOfData = data.split(",");
            if (arrOfData[0].equals("Running")) {
                try {
                    fileWriterPaces.print(arrOfData[12]);
                    fileWriterPaces.print(",");
                } catch (NumberFormatException e) {} //System.out.println("There is an error with the running data in this line.");
            }
        }
        fileReader.close();
        fileWriterPaces.flush();
        fileWriterPaces.close();
    }

    //use the new Pace file to read the new paces and calculate the average pace of the entered runs from the typed-in file
    public static String averagePace() {

        String averagePace = null;
        FileInputStream paceFile = null;
        int minutes = 0;
        int seconds = 0;
        int counter = 0;

        try {
            paceFile = new FileInputStream("Pace.csv");
        } catch (FileNotFoundException e) {
            System.out.println("Could not open input file");
            System.exit(1);
        }

        Scanner paceReader = new Scanner(paceFile);

        //sort through the array list by splitting up the commas, removing quotation marks,and separating the minutes and seconds
        String data = paceReader.nextLine();
        paceReader.close();
        String[] arrOfData = data.split(",");
        boolean status = true;
        for (int i = 0; i < arrOfData.length; i++) {
            char[] dats = new char[arrOfData[i].length()];
            for (int j = 0; j < dats.length; j++) {
                dats[j] = arrOfData[i].charAt(j);
                if (dats[j] != ':' || dats[j] == '-') {
                    status = false;
                }
            }
            if (!status) {
                try {
                    String timeArray = arrOfData[i].replace("\"", "");
                    String[] newTimeArray = timeArray.split(":");
                    int minute = Integer.parseInt(newTimeArray[0]);
                    int second = Integer.parseInt(newTimeArray[1]);
                    minutes += minute;
                    seconds += second;
                    counter++;
                } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {} //System.out.println("There is an error with the running data in this line.");
            }
        }

        //calculate the average
        seconds += minutes * 60;
        seconds = seconds / counter;
        minutes = seconds / 60;
        seconds = seconds % 60;

        //print the average and check to see if it is a single digit second value so that the appropriate leading zero can be added
        if (seconds < 10) {
            averagePace = minutes + ":0" + seconds;
        } else {
            averagePace = minutes + ":" + seconds;
        }
        return averagePace;
    }

    //average pace from the new file
    public static String averagePaceFromNewFile(Scanner fileReader) {
        String averagePace = null;
        int minutes = 0;
        int seconds = 0;
        int counter = 0;
        //sort through the array list by splitting up the commas, removing quotation marks,and separating the minutes and seconds
        while (fileReader.hasNextLine()) {
            String data = fileReader.nextLine();
            String[] arrOfData = data.split(",");
            boolean status = true;

            if (arrOfData[0].equals("Running")){
                char[] dats = new char[arrOfData[12].length()];
                for (int j = 0; j < dats.length; j++) {
                    dats[j] = arrOfData[12].charAt(j);
                    if (dats[j] != ':' || dats[j] == '-') {
                        status = false;
                    }
                }
                if (!status) {
                    try {
                        String timeArray = arrOfData[12].replace("\"", "");
                        String[] newTimeArray = timeArray.split(":");
                        int minute = Integer.parseInt(newTimeArray[0]);
                        int second = Integer.parseInt(newTimeArray[1]);
                        minutes += minute;
                        seconds += second;
                        counter++;
                    } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {} //System.out.println("There is an error with the running data in this line.");
                }
            }
        }

        //calculate the average
        seconds += minutes * 60;
        seconds = seconds / counter;
        minutes = seconds / 60;
        seconds = seconds % 60;

        //print the average and check to see if it is a single digit second value so that the appropriate leading zero can be added
        if (seconds < 10) {
            averagePace = minutes + ":0" + seconds;
        } else {
            averagePace = minutes + ":" + seconds;
        }
        fileReader.close();
        return averagePace;
    }
}