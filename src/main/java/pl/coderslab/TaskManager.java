package pl.coderslab;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class TaskManager {

    // static that contains all tasks present in given file in [][] format
    static String[][] fileWithTasks;

    public static void main(String[] args) {

        fileWithTasks = processFile("tasks.csv");

        chooseOption();

    }


    public static String[][] processFile(String fileName) {
        File taskFile = new File(fileName);

        String[][] fileTextArr = new String[0][];

        try {
            Scanner fileScanner = new Scanner(taskFile);
            int lineCount = 0; // keeping track of which line we are processing

            while (fileScanner.hasNextLine()) {

                String line = fileScanner.nextLine();
                //line = line.replace("-", ""); check later if we need to remove these chars
                //line = line.replace(",", "");
                String[] wordsOfLine = line.split(" ");

                fileTextArr = Arrays.copyOf(fileTextArr, lineCount + 1);
                fileTextArr[lineCount] = wordsOfLine;
                lineCount++;

            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        return fileTextArr;
    }


    public static void chooseOption() {

        // user can choose actions until they enter exit - than the exitProgram runs and the while loop breaks
        while (true) {
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
                    break;

                case "remove":
                    removeTask();
                    break;

                case "list":
                    listTasks();
                    break;

                case "exit":
                    exitProgram();
                    return;

                default:
                    System.out.println("Please select a correct option");
            }
        }

    }


    public static void addTask() {

        // collecting task information
        Scanner addingScanner = new Scanner(System.in);

        System.out.println("Please add task description:");
        String taskDescription = addingScanner.nextLine();

        System.out.println("Please add task dues date:");
        String taskDueDate = addingScanner.nextLine();

        System.out.println("Is your task important: true/false");
        String taskImportance = addingScanner.nextLine();

        // adding new task to task array
        String[] newTask = new String[]{taskDescription, taskDueDate, taskImportance};

        fileWithTasks = Arrays.copyOf(fileWithTasks, fileWithTasks.length + 1);
        fileWithTasks[fileWithTasks.length - 1] = newTask;

        System.out.println(Arrays.deepToString(fileWithTasks));

    }


    public static void removeTask() {

        Scanner removeScanner = new Scanner(System.in);
        System.out.println("Please select index number of item to remove:");

        // what the user enters, for now a string
        String removeInput = removeScanner.nextLine();

        try {
            int taskIndex = Integer.parseInt(removeInput);  // checking if the input can be parsed as an int
            if (taskIndex >= 0) {
                fileWithTasks = ArrayUtils.remove(fileWithTasks, taskIndex);

            } else {
                System.out.println("Please enter number greater than or equal to 0");
            }

        } catch (NumberFormatException e) {
            System.out.println("Incorrect argument passed, please enter a number.");
        }

        System.out.println("Value was successfully deleted.");

    }


    public static void listTasks() {

        for (int i = 0; i < fileWithTasks.length; i++) {
            String combinedArr = String.join(" ", fileWithTasks[i]);
            System.out.println(i + " : " + combinedArr);
        }
    }


    public static void exitProgram() {
        // save new file


        // print bye bye
        System.out.println(ConsoleColors.RED + "Bye, Bye.");
    }

}


