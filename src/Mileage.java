import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.*;

public class Mileage {
    public static Stack<Integer> zeroToThree = new Stack<>();
    public static Stack<Integer> threeToSix = new Stack<>();
    public static Stack<Integer> sixAndUp = new Stack<>();
    //add the new list of mileages to the Mileage.csv
    public static void updateMileageFile(FileInputStream myFile) {
        Scanner fileReader = new Scanner(myFile);
        FileOutputStream mileages = null;
        //set the destination of MILEAGE information, try-catch set up for future errors that may occur when I open up the code for user interface
        try {
            mileages = new FileOutputStream("Mileage.csv",true);
        }
        catch (FileNotFoundException e) {
            System.out.println("Could not open output file");
            System.exit(1);
        }
        PrintWriter fileWriterMileages = new PrintWriter(mileages);
        //read through the main input file and parse out the mileages from the "Running" activities, avoiding empty fields as well
        while (fileReader.hasNextLine()) {
            String data = fileReader.nextLine();
            String[] arrOfData = data.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
            if (arrOfData[0].equals("Running")) {
                try {
                    fileWriterMileages.print(arrOfData[4]);
                    fileWriterMileages.print(",");
                } catch (NumberFormatException e) {} //System.out.println("There is an error with the running data in this line.");
            }
        }
        fileReader.close();
        fileWriterMileages.flush();
        fileWriterMileages.close();
    }

    //method for compiling all mileage in total for a quick print of how many miles have been run in a date range
    public static String totalMileage() {

        FileInputStream mileageFile = null;
        double miles = 0;
        try {
            mileageFile = new FileInputStream("Mileage.csv");
        } catch (FileNotFoundException e) {
            System.out.println("Could not open input file");
            System.exit(1);
        }

        Scanner mileageReader = new Scanner(mileageFile);

        //sort through the array list by splitting up the commas, removing quotation marks,and separating the miles and decimals
        String data = mileageReader.nextLine();
        String[] arrOfData = data.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
        boolean status = true;
        for (int i = 0; i < arrOfData.length; i++) {
            char[] dats = new char[arrOfData[i].length()];
            for (int j = 0; j < dats.length; j++) {
                dats[j] = arrOfData[i].charAt(j);
                if (dats[j] != '.') {
                    status = false;
                }
            }
            if (!status) {
                try {
                    String timeArray = arrOfData[i].replace("\"", "");
                    double mile = Double.parseDouble(timeArray.replace(",", ""));
                    miles += mile;
                } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {} //System.out.println("There is an error with the running data in this line.");
            }
        }
        String totalMileage = String.valueOf(Math.ceil(miles * 100.0) / 100.0);
        return totalMileage;
    }

    //find the highest mileage of a run from Mileage.csv by sorting the csv file from lowest to highest
    public static void highToLow() {
        FileInputStream mileages = null;
        //open this file
        try {
            mileages = new FileInputStream("Mileage.csv");
        } catch (FileNotFoundException e) {
            System.out.println("Could not open input file");
            System.exit(1);
        }
        Scanner fileReader = new Scanner(mileages);
        String data = fileReader.nextLine();
        String[] arrOfData = data.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
        LinkedList<Double> doubles = new LinkedList<>();
        int i = 0;
        while (i != arrOfData.length) {
            doubles.append(Double.parseDouble(arrOfData[i].replace("\"","")));
            i += 1;
        }
        MileageComparator<Double> comparingMileage = new MileageComparator<>();
        comparingMileage.sort(doubles);
        double first = doubles.head.data;
        System.out.println("The highest mileage run was " + first + " miles.");
        fileReader.close();
    }

    //calculate the average mileage of the added runs, needs to be started!!!
    public static Double averageMileage(Scanner fileReader) {
        double miles = 0;
        int counter = 0;
        int counter2 = 0;
        //sort through the array list by splitting up the commas, removing quotation marks,and separating the miles and decimals
        while (fileReader.hasNextLine()) {
            counter2++;
            String data = fileReader.nextLine();
            String[] arrOfData = data.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
            boolean status = true;
            char[] dats = new char[arrOfData[4].length()];
            if (arrOfData[0].equals("Running")) {
                for (int j = 0; j < dats.length; j++) {
                    dats[j] = arrOfData[4].charAt(j);
                    if (dats[j] != '.') {
                        status = false;
                    }
                }
                if (!status) {
                    try {
                        String timeArray = arrOfData[4].replace("\"", "");
                        double mile = Double.parseDouble(timeArray);
                        Node<Integer> curr = new Node<>(counter2,null);
                        if (mile < 3.00){
                            zeroToThree.push(curr.data);
                        }
                        else if (mile >= 3.00 && mile < 6.00){
                            threeToSix.push(curr.data);
                        }
                        else if (mile >= 6.00){
                            sixAndUp.push(curr.data);
                        }
                        miles += mile;
                        counter++;
                    } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {} //System.out.println("There is an error with the running data in this line.");
                }
            }
        }
        double totalMileage = Math.ceil(miles * 100.0) / 100.0;
        return totalMileage/counter;
    }
}


