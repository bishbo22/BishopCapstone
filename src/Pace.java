import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Pace {
    public static void updatePaceFile(FileInputStream myFile, FileOutputStream file, Scanner fileReader, PrintWriter fileWriter) {
        fileReader = new Scanner(myFile);
        fileWriter = new PrintWriter(file);

        while (fileReader.hasNextLine()) {
            String data = fileReader.nextLine();
            String[] arrOfData = data.split(",");
            if (arrOfData[0].equals("Running")) {
                try {
                    fileWriter.print(arrOfData[12]);
                    fileWriter.print(",");
                } catch (NumberFormatException e) {
                    System.out.println("There is an error with the running data in this line.");
                }
            }
        }

        fileReader.close();
        fileWriter.flush();
        fileWriter.close();
    }

    public static String averagePace(){

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

        String data = paceReader.nextLine();
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
                    int minute = Integer.parseInt(newTimeArray[0]);
                    int second = Integer.parseInt(newTimeArray[1]);
                    minutes += minute;
                    seconds += second;
                    counter++;
                }
                catch(ArrayIndexOutOfBoundsException | NumberFormatException e){
                    System.out.println("There is an error with the running data in this line.");
                }
            }
        }
        seconds += minutes%counter*60;
        seconds = seconds/counter;
        minutes = minutes/counter;
        minutes += seconds/60;
        seconds = seconds%60;
        averagePace = minutes + ":" + seconds;
        return averagePace;
    }
}
