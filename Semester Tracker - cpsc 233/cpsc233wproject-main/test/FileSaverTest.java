import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.*;

class FileSaverTest {

    @Test
    void saveDataToFile() throws IOException {
        // Create a temporary file for testing
        File tempFile = File.createTempFile("temp", ".txt");

        try {
            // Create some dummy data for testing
            Data data = new Data();
            data.storeCourseName("Math", 101);
            data.storeAssignments("Math", 101, "Assignment1", "2024-04-01", "not complete", 0.2);
            data.storeExams("Math", 101, "Midterm", "2024-04-15", "not complete", 0.8);

            // Invoke the save method with the temporary file and dummy data
            boolean result = FileSaver.save(tempFile, data);

            // Verify that the save method returns true (indicating successful saving)
            assertTrue(result);

            // Read the content of the temporary file
            StringBuilder content = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader(tempFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
            }

            // Verify if the content of the file matches the expected content
            String expectedContent = "Courses\nMath,101\nAssignments\nAssignment1,2024-04-01,not complete,0.2,0\nExams\nMidterm,2024-04-15,not complete,0.8,0\n";
            assertEquals(expectedContent, content.toString());
        } finally {
            // Delete the temporary file after the test
            tempFile.delete();
        }
    }
}
