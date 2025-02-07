import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FileInputStream myFile = null;
        FileOutputStream file = null;

        //open file when the program is called in the terminal, catches incorrect file names
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

        Scanner fileReader = new Scanner(myFile);
        PrintWriter fileWriter = new PrintWriter(file);

        Pace.updatePaceFile(myFile,file,fileReader,fileWriter);

        System.out.println("The average pace of these runs is " + Pace.averagePace() + ".");
    }
}