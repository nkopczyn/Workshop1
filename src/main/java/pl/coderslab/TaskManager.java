package pl.coderslab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class TaskManager {

    public static void main(String[] args) {

        processFile("tasks.csv");
        //chooseOption();

    }

    public static void processFile(String fileName) {
        File taskFile = new File(fileName);

        String [][] fileTextArr = new String[0][];

        try {
            Scanner fileScanner = new Scanner(taskFile);
            int lineCount = 0; // keeping track of which line we are processing

            while (fileScanner.hasNextLine()) {

                String line = fileScanner.nextLine();
                //line = line.replace("-", ""); check later if we need to remove these chars
                //line = line.replace(",", "");
                String [] wordsOfLine = line.split(" ");

                fileTextArr = Arrays.copyOf(fileTextArr, lineCount+1);
                fileTextArr[lineCount] = wordsOfLine;
                lineCount++;

            }

            for (String [] lineArr : fileTextArr) {
                System.out.println(Arrays.toString(lineArr));
            }


        } catch (FileNotFoundException e ) {
            System.out.println("File not found");
        }

    }

    public static void chooseOption() {
        System.out.println(ConsoleColors.BLUE + "Please select an option:");
        System.out.println(ConsoleColors.WHITE + "add");
        System.out.println("remove");
        System.out.println("list");
        System.out.println("exit");

        Scanner optionScanner = new Scanner(System.in);
        String option = optionScanner.nextLine();

        switch (option) {
            case "add":
                addTask();
                //System.out.println("Add chosen");
                break;
            case "remove":
                System.out.println("Remove chosen");
                break;
            case "list":
                System.out.println("List chosen");
                break;
            case "exit":
                System.out.println("Exit chosen");
                break;
            default:
                System.out.println("Please select a correct option");
        }

    }

    public static void addTask() {
        System.out.println("Add chosen");
    }

}
