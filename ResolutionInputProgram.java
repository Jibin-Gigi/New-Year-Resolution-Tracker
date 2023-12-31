import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ResolutionInputProgram {
    private static final String RESOLUTIONS_FILE = "resolutions.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            ArrayList<String> resolutions = new ArrayList<>();

            // Input resolutions
            System.out.println("New Year Resolution - Input Program");
            while (true) {
                System.out.print("Enter a resolution ( or type 'exit' to finish): ");
                String resolution = scanner.nextLine() + " - Incomplete";

                if (resolution.equalsIgnoreCase("exit - Incomplete")) {
                    break;
                }

                resolutions.add(resolution);
            }
        
            // Save resolutions to file
            saveResolutions(resolutions);

            System.out.println("Resolutions saved successfully. You can now use the Resolution Editor Program.");
            System.out.println();

        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            scanner.close();
        }
        
    }

    private static void saveResolutions(ArrayList<String> resolutions) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RESOLUTIONS_FILE))) {
            for (String resolution : resolutions) {
                bw.write(resolution);
                bw.newLine();
            }
        }
    }
}
