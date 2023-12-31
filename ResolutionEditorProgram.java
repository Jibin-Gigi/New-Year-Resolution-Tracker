import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class ResolutionEditorProgram {
    private static final String RESOLUTIONS_FILE = "resolutions.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n----------------------------------");
        System.out.println("Let's Track your Progress Warrior");
        System.out.println("----------------------------------");
        try {
            ArrayList<String> resolutions = loadResolutions();

            while (true) {
                selectionMenu();

                int choice = scanner.nextInt();
                scanner.nextLine(); 

                switch (choice) {
                    case 1:
                        displayResolutions(resolutions);
                        break;
                    case 2:
                        System.out.println();
                        addResolution(resolutions, scanner);
                        break;
                    case 3:
                        System.out.println();
                        replaceResolution(resolutions, scanner);
                        break;
                    case 4:
                        deleteResolution(resolutions, scanner);
                        break;
                    case 5:
                        System.out.println();
                        markAsComplete(resolutions, scanner);
                        break;
                    case 6:
                        saveResolutions(resolutions);
                        System.out.print("  Exiting.");
                        return; // Exit the program
                    default:
                        System.out.println("Invalid choice. Enter a number between 1 and 6. ");
                }
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static void selectionMenu() {
        System.out.println("\nResolution Editor Program");
        System.out.println("1. View Resolutions");
        System.out.println("2. Add New Resolution");
        System.out.println("3. Replace/Edit Current Resolution");
        System.out.println("4. Delete a Resolution");
        System.out.println("5. Mark as Complete");
        System.out.println("6. Exit");
        System.out.print("Enter your choice (1-6): ");
        
    }

    private static ArrayList<String> loadResolutions() throws IOException {
        ArrayList<String> resolutions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(RESOLUTIONS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                resolutions.add(line);
            }
        }
        return resolutions;
    }

    private static void displayResolutions(ArrayList<String> resolutions) {
        System.out.println("\nYour Current Resolutions :");
        int index=1;
        for (String resolution : resolutions) {
            System.out.print(index+". ");
            System.out.println(resolution);
            index++;
        }
    }

    private static void addResolution(ArrayList<String> resolutions, Scanner scanner) {
        System.out.print("Enter a new resolution: ");
        String newResolution = scanner.nextLine();
        resolutions.add(newResolution + " - Incomplete");
        System.out.println("Resolution added successfully.");
    }

    private static void replaceResolution(ArrayList<String> resolutions, Scanner scanner) {
        displayResolutions(resolutions);
        System.out.print("Enter the index of the resolution to replace: ");
        try {
            int index = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            if (index >= 1 && index <= resolutions.size()) {
                System.out.print("Enter the new resolution: ");
                String newResolution = scanner.nextLine();
                resolutions.set(index - 1, newResolution + " - Incomplete");
                System.out.println("Resolution replaced successfully.");
            } else {
                System.out.println("Invalid index. Please enter a valid index.");
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            scanner.nextLine(); // Consume the invalid input
        }
    }

    private static void deleteResolution(ArrayList<String> resolutions, Scanner scanner) {
        displayResolutions(resolutions);
        System.out.print("Enter the index of the resolution to delete: ");
        try {
            int index = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            if (index >= 1 && index <= resolutions.size()) {
                resolutions.remove(index - 1);
                System.out.println("Resolution deleted successfully.");
            } else {
                System.out.println("Invalid index. Please enter a valid index.");
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            scanner.nextLine(); // Consume the invalid input
        }
    }

    private static void markAsComplete(ArrayList<String> resolutions, Scanner scanner) {
        displayResolutions(resolutions);
        System.out.print("Enter the index of the resolution to mark as complete: ");
        try {
            int index = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            if (index >= 1 && index <= resolutions.size()) {
                String resolution = resolutions.get(index - 1);
                String completedResolution = resolution.replace(" - Incomplete", " - Completed on " + getCurrentDateTime());
                resolutions.set(index - 1, completedResolution);
                System.out.println("Resolution marked as complete successfully.");
            } else {
                System.out.println("Invalid index. Please enter a valid index.");
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            scanner.nextLine(); // Consume the invalid input
        }
    }

    private static String getCurrentDateTime() {
        SimpleDateFormat time = new SimpleDateFormat("dd-MM-yyyy ; HH:mm:ss");
        Date currentDate = new Date();
        return time.format(currentDate);
    }

    private static void saveResolutions(ArrayList<String> resolutions) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RESOLUTIONS_FILE))) {
            for (String resolution : resolutions) {
                bw.write(resolution);
                bw.newLine();
            }
            System.out.println("Resolutions saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving resolutions: " + e.getMessage());
        }
    }
}
