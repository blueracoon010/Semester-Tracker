import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * The FileLoader class  loads  data from a file.
 */
public class FileLoader {
    /**
     * Loads data from the specified file.
     *
     * @param file the file from which the data should be loaded
     * @return the loaded data, or null if loading fails
     * @throws FileNotFoundException if the specified file is not found
     */

    public static Data load(File file) {
        Data data = new Data();// Initializes a Data object to store the loaded data
        int counter = 0;// Counter variable for tracking iterations
        boolean isAssignment = true;// Flag to indicate whether the current entry is an assignment
        Course courseBeingUsed = null;// Variable to hold the current course being processed
        try (Scanner scanner = new Scanner(file)) {// Try-with-resources to automatically close the scanner
            String line = scanner.nextLine();// Reads the first line from the file
            // Checks  if the first line indicates the beginning of the "Courses" section
            if (!line.equals("Courses")) {
                return null;// Return null if the file format is invalid
            }

            // Iterates through each line in the file
            while (scanner.hasNextLine()) {
                line = scanner.nextLine(); // Reads the next line
                String[] newline = line.split(" ");// Splits the line into an array of strings based on spaces

                // Checks if the length of the split array is 2, indicating a course entry
                if (newline.length == 2) {
                    // Creates a new Course object and store its name and number in the Data object
                    Course course = new Course(newline[0], Integer.parseInt(newline[1]));
                    data.storeCourseName(newline[0], Integer.parseInt(newline[1]));
                    courseBeingUsed = course;// Sets the current course being used
                }

                // Checks if a course is currently being used
                if (courseBeingUsed != null) {
                    // Checks if the length of the split array is 5, indicating an assignment or exam entry
                    if (newline.length == 5) {
                        // Checks if the current entry is an assignment
                        if (isAssignment) {
                            // Stores the assignment information in the Data object
                            data.storeAssignments(courseBeingUsed.getCourseName(), courseBeingUsed.getCourseNumber(), newline[0], newline[1], newline[2], Double.parseDouble(newline[3]));
                            data.storeGrade(courseBeingUsed.getCourseName(), courseBeingUsed.getCourseNumber(), newline[0], Integer.parseInt(newline[4]));
                        } else {
                            // Stores the exam information in the Data object
                            data.storeExams(courseBeingUsed.getCourseName(), courseBeingUsed.getCourseNumber(), newline[0], newline[1], newline[2], Double.parseDouble(newline[3]));
                            data.storeGrade(courseBeingUsed.getCourseName(), courseBeingUsed.getCourseNumber(), newline[0], Integer.parseInt(newline[4]));
                        }
                    }
                }
                // Checks if the current line indicates the beginning of the "Exams" section
                if (line.equals("Exams")) {
                    isAssignment = false;// Sets the flag to indicate that the upcoming entries are exams
                }
                // Checks if the current line indicates the beginning of the "Assignments" section
                if (line.equals("Assignments")) {
                    isAssignment = true;// Sets the flag to indicate that the upcoming entries are assignments
                }


            }
        }catch(IOException ioe) {
            return null;// Returns null if an IOException occurs during file processing
        }
        return data;// Returns the loaded data

    }

}