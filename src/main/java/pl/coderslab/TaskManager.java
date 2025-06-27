package pl.coderslab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class TaskManager {

    // static that contains all tasks present in file in [][] format
    static String[][] fileWithTasks;

    public static void main(String[] args) {

        fileWithTasks = processFile("tasks.csv");
        //listTasks();

        chooseOption();
        // tutaj musi być loop if option == exit to wtedy konczymy program i zapisujemy do pliku


        System.out.println(Arrays.deepToString(fileWithTasks));

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
                System.out.println("Remove chosen");
                break;
            case "list":
                listTasks();
                break;
            case "exit":
                System.out.println("Exit chosen");
                break;
            default:
                System.out.println("Please select a correct option");
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
        String [] newTask = new String[]{taskDescription, taskDueDate, taskImportance};

        fileWithTasks = Arrays.copyOf(fileWithTasks, fileWithTasks.length + 1);
        fileWithTasks[fileWithTasks.length-1] = newTask;

        System.out.println(Arrays.deepToString(fileWithTasks));

    }






    public static void removeTask() {
        // usuwanie zadań z tablicy i walidacja
        System.out.println("remove chosen");
    }


    public static void listTasks() {

        for (int i = 0; i < fileWithTasks.length; i++) {
            String combinedArr = String.join(" ", fileWithTasks[i]);
            System.out.println(i + " : " + combinedArr);
        }
    }



    public static void exitProgram() {
        // save new file
        System.out.println(ConsoleColors.RED + "Bye, Bye.");
    }

}


