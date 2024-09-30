import java.util.Scanner;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Temperature temperature = null;
        Days days = new Days();

        try {
            temperature = loadFromFile("temperature.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 7; i++) {
            String day = days.whichDay(i + 1);
            System.out.print("Enter the temperature for " + day + "\n");

            boolean validInput = false;
            while (!validInput) {
                try {
                    String input = in.nextLine().trim();
                    double degrees = Double.parseDouble(input);
                    temperature.setMeanDayTemp(i, degrees);
                    validInput = true;
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid value for the temperature.");
                }
            }
        }

        if(temperature.checkIfSpring()){
            System.out.print("It is now spring \n");
        } else {
            System.out.print("It is not spring \n");
        }


        try {
            saveToFile(temperature, "temperature.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        in.close();
    }

    private static void saveToFile(Temperature temperature, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Double temp : temperature.meanDayTemp) {
                writer.write(temp + "\n");
            }
        }
    }

    private static Temperature loadFromFile(String filename) throws IOException {
        Temperature temperature = new Temperature();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int index = 0;
            while ((line = reader.readLine()) != null && index < temperature.meanDayTemp.length) {
                temperature.meanDayTemp[index] = Double.parseDouble(line);
                index++;
            }
        }
        return temperature;
    }
}
