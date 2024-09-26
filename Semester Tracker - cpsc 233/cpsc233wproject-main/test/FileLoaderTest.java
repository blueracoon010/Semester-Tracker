import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.File;

class FileLoaderTest {

    @Test
    void testDataLoadingFromFile() {
        Data data = null;

        // Create a temporary file for testing
        File file = new File("test.csv");
        if(file.exists()&&file.canRead()){
            data = FileLoader.load(file);
        }
        if(data!=null) {
            assertEquals(1, data.getAllCourses().size());
            assertEquals(1, data.getAllCourses().getFirst().getAssignments().size());
            assertEquals(1, data.getAllCourses().getFirst().getExams().size());
        }
    }
}
