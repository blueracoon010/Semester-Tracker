/** Name : Saira Fatima
 * Ucid : 30232060
 * Name : Warisha Zaman
 * Ucid:
 * Name : Dominic Afuba
 * Ucid: 30202347
 * The follwing is the code to test.csv data class
 */

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class DataTest {
    Data Data = new Data();

    //Resets the course so that that everything can run together
    @AfterEach
    public void resetCourse(){
        Data.removeCourse("CS101101");
    }

    //storeCourse Tests

    @Test
    void TestStoreCourse(){
        assertTrue(Data.storeCourseName("CS101", 101));
    }

    @Test
    void TestStoreCourseSame(){
        Data.storeCourseName("CS101", 101);
        assertFalse(Data.storeCourseName("CS101", 101));
    }

    //storeAssignment Tests

    @Test
    void TestStoreAssignment(){
        Data.storeCourseName("CS101", 101);
        assertTrue(Data.storeAssignments("CS101", 101, "H1", "2024/03/01", "incomplete", 30));
    }

    @Test
    void TestStoreAssignmentSame(){
        Data.storeCourseName("CS101", 101);
        Data.storeAssignments("CS101", 101, "H1", "2024/03/01", "incomplete", 30);
        assertFalse(Data.storeAssignments("CS101", 101, "H1", "2024/03/01", "incomplete", 30));
    }

    @Test
    void TestStoreAssignmentWrongID(){
        Data.storeCourseName("CS101", 101);
        assertFalse(Data.storeAssignments("CS101", 102, "H1", "2024/03/01", "incomplete", 30));
    }

    //storeExam Tests

    @Test
    void TestStoreExam(){
        Data.storeCourseName("CS101", 101);
        assertTrue(Data.storeExams("CS101", 101, "H1", "2024/03/01", "incomplete", 30));
    }

    @Test
    void TestStoreExamSame(){
        Data.storeCourseName("CS101", 101);
        Data.storeExams("CS101", 101, "H1", "2024/03/01", "incomplete", 30);
        assertFalse(Data.storeExams("CS101", 101, "H1", "2024/03/01", "incomplete", 30));
    }

    @Test
    void TestStoreExamWrongID(){
        Data.storeCourseName("CS101", 101);
        assertFalse(Data.storeExams("CS101", 102, "H1", "2024/03/01", "incomplete", 30));
    }

    //deleteAssignment Tests

    @Test
    void TestDeleteAssignment(){
        Data.storeCourseName("CS101", 101);
        Data.storeAssignments("CS101", 101, "H1", "2024/03/01", "incomplete", 30);
        assertTrue(Data.deleteAssignmentFromCourse("CS101", 101, "H1"));
    }

    @Test
    void TestdeleteAssignmentNotSame(){
        Data.storeCourseName("CS101", 101);
        Data.storeAssignments("CS101", 101, "H1", "2024/03/01", "incomplete", 30);
        assertFalse(Data.deleteAssignmentFromCourse("CS101", 101, "H2"));
    }

    @Test
    void TestdeleteAssignmentWrongID(){
        Data.storeCourseName("CS101", 101);
        Data.storeAssignments("CS101", 101, "H1", "2024/03/01", "incomplete", 30);
        assertFalse(Data.deleteAssignmentFromCourse("CS101", 102, "H1"));
    }

    //deleteExam Tests

    @Test
    void TestdeleteExam(){
        Data.storeCourseName("CS101", 101);
        Data.storeExams("CS101", 101, "H1", "2024/03/01", "incomplete", 30);
        assertTrue(Data.deleteExamFromCourse("CS101", 101, "H1"));
    }

    @Test
    void TestdeleteExamNotSame(){
        Data.storeCourseName("CS101", 101);
        Data.storeExams("CS101", 101, "H1", "2024/03/01", "incomplete", 30);
        assertFalse(Data.deleteExamFromCourse("CS101", 101, "H2"));
    }

    @Test
    void TestdeleteExamWrongID(){
        Data.storeCourseName("CS101", 101);
        Data.storeExams("CS101", 101, "H1", "2024/03/01", "incomplete", 30);
        assertFalse(Data.deleteExamFromCourse("CS101", 102, "H1"));
    }

    //storeGrade Tests

    @Test
    void TestStoreGrade(){
        Data.storeCourseName("CS101", 101);
        Data.storeAssignments("CS101", 101, "H1", "2024/03/01", "incomplete", 30);
        assertTrue(Data.storeGrade("CS101", 101, "H1", 4));
    }

    @Test
    void TestStoreGradeNotSame(){
        Data.storeCourseName("CS101", 101);
        Data.storeAssignments("CS101", 101, "H1", "2024/03/01", "incomplete", 30);
        assertFalse(Data.storeGrade("CS101", 101, "H2", 4));
    }

    @Test
    void TestStoreGradeWrongID(){
        Data.storeCourseName("CS101", 101);
        Data.storeAssignments("CS101", 101, "H1", "2024/03/01", "incomplete", 30);
        assertFalse(Data.storeGrade("CS101", 102, "H1", 4));
    }

    //UpdateAssignmentStatus Tests

    @Test
    void TestUpdateAssignmentStatus(){
        Data.storeCourseName("CS101", 101);
        Data.storeAssignments("CS101", 101, "H1", "2024/03/01", "incomplete", 30);
        assertTrue(Data.updateAssignmentStatus("CS101", 101, "H1", "complete"));
    }

    @Test
    void TestUpdateAssignmentStatusNotSame(){
        Data.storeCourseName("CS101", 101);
        Data.storeAssignments("CS101", 101, "H1", "2024/03/01", "incomplete", 30);
        assertFalse(Data.updateAssignmentStatus("CS101", 101, "H2", "complete"));
    }

    @Test
    void TestUpdateAssignmentStatusWrongID(){
        Data.storeCourseName("CS101", 101);
        Data.storeAssignments("CS101", 101, "H1", "2024/03/01", "incomplete", 30);
        assertFalse(Data.updateAssignmentStatus("CS101", 102, "H1", "complete"));
    }

    //getAllAssignmentDatesForCourse Tests

    @Test
    void TestgetAllAssignmentDatesForCourse(){
        Data.storeCourseName("CS101", 101);
        Data.storeAssignments("CS101", 101, "H1", "2024/03/01", "not complete", 30);
        ArrayList<String> expected = new ArrayList<>();
        expected.add("H1: 2024/03/01");
        ArrayList<String> actual = Data.getAllAssignmentDatesForCourse("CS101", 101);
        assertEquals(expected, actual);
    }

    @Test
    void TestgetAllAssignmentDatesForCourseNotSame(){
        Data.storeCourseName("CS101", 101);
        Data.storeAssignments("CS101", 101, "H1", "2024/03/01", "not completed", 30);
        ArrayList<String> expected = new ArrayList<>();
        expected.add("H1: 2024/03/02");
        ArrayList<String> actual = Data.getAllAssignmentDatesForCourse("CS101", 101);
        assertNotEquals(expected, actual);
    }

    @Test
    void TestgetAllAssignmentDatesForCourseWrongID(){
        Data.storeCourseName("CS101", 101);
        Data.storeAssignments("CS101", 101, "H1", "2024/03/01", "not completed", 30);
        ArrayList<String> expected = new ArrayList<>();
        expected.add("H1: 2024/03/01");
        ArrayList<String> actual = Data.getAllAssignmentDatesForCourse("CS101", 102);
        assertNotEquals(expected, actual);
    }

    //getAllExamDatesForCourse Tests

    @Test
    void TestgetAllExamDatesForCourse(){
        Data.storeCourseName("CS101", 101);
        Data.storeExams("CS101", 101, "H1", "2024/03/01", "not complete", 30);
        ArrayList<String> expected = new ArrayList<>();
        expected.add("H1: 2024/03/01");
        ArrayList<String> actual = Data.getAllExamDatesForCourse("CS101", 101);
        assertEquals(expected, actual);
    }

    @Test
    void TestgetAllExamDatesForCourseNotSame(){
        Data.storeCourseName("CS101", 101);
        Data.storeExams("CS101", 101, "H1", "2024/03/01", "not completed", 30);
        ArrayList<String> expected = new ArrayList<>();
        expected.add("H1: 2024/03/02");
        ArrayList<String> actual = Data.getAllExamDatesForCourse("CS101", 101);
        assertNotEquals(expected, actual);
    }

    @Test
    void TestgetAllExamsDatesForCourseWrongID(){
        Data.storeCourseName("CS101", 101);
        Data.storeExams("CS101", 101, "H1", "2024/03/01", "not completed", 30);
        ArrayList<String> expected = new ArrayList<>();
        expected.add("H1: 2024/03/01");
        ArrayList<String> actual = Data.getAllExamDatesForCourse("CS101", 102);
        assertNotEquals(expected, actual);
    }

    //getAllDeadlinesForCourse Tests

    @Test
    void TestgetAllDeadlinesForCourse(){
        Data.storeCourseName("CS101", 101);
        Data.storeAssignments("CS101", 101, "A1", "2024/03/01", "not complete", 30);
        Data.storeExams("CS101", 101, "H1", "2024/03/01", "not complete", 30);
        ArrayList<String> expected = new ArrayList<>();
        expected.add("A1: 2024/03/01");
        expected.add("H1: 2024/03/01");
        ArrayList<String> actual = Data.getAllDeadlinesForCourse("CS101", 101);
        assertEquals(expected, actual);
    }

    @Test
    void TestgetAllDeadlinesForCourseNotSame(){
        Data.storeCourseName("CS101", 101);
        Data.storeExams("CS101", 101, "A1", "2024/03/01", "not completed", 30);
        Data.storeExams("CS101", 101, "H1", "2024/03/01", "not completed", 30);
        ArrayList<String> expected = new ArrayList<>();
        expected.add("A1: 2024/03/02");
        expected.add("H1: 2024/03/01");
        ArrayList<String> actual = Data.getAllExamDatesForCourse("CS101", 101);
        assertNotEquals(expected, actual);
    }

    @Test
    void TestgetAllDeadlinesForCourseWrongID(){
        Data.storeCourseName("CS101", 101);
        Data.storeAssignments("CS101", 101, "A1", "2024/03/01", "not completed", 30);
        Data.storeExams("CS101", 101, "H1", "2024/03/01", "not completed", 30);
        ArrayList<String> expected = new ArrayList<>();
        expected.add("A1: 2024/03/01");
        expected.add("H1: 2024/03/01");
        ArrayList<String> actual = Data.getAllExamDatesForCourse("CS101", 102);
        assertNotEquals(expected, actual);
    }

    //getAllCourses Tests

    @Test
    void TestgetAllCourses(){
        Data.storeCourseName("CS101", 101);
        ArrayList<String> expected = new ArrayList<>();
        expected.add("CS101101");
        ArrayList<String> actual = Data.getAllCourseIDs();
        assertEquals(expected, actual);
    }

    //getAllGradesForCourse Tests

    @Test
    void TestgetAllGradesForCourse(){
        Data.storeCourseName("CS101", 101);
        Data.storeAssignments("CS101", 101, "H1", "2024/03/01", "not completed", 30);
        Data.storeGrade("CS101", 101, "H1", 4);
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(4);
        ArrayList<Integer> actual = Data.getAllGradesForCourse("CS101", 101);
        assertEquals(expected, actual);
    }

    @Test
    void TestAllGradesForCourseNotSame(){
        Data.storeCourseName("CS101", 101);
        Data.storeAssignments("CS101", 101, "H1", "2024/03/01", "not completed", 30);
        Data.storeGrade("CS101", 101, "H1", 4);
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(3);
        ArrayList<Integer> actual = Data.getAllGradesForCourse("CS101", 101);
        assertNotEquals(expected, actual);
    }

    @Test
    void TestAllGradesForCourseWrongID(){
        Data.storeCourseName("CS101", 101);
        Data.storeAssignments("CS101", 101, "H1", "2024/03/01", "not completed", 30);
        Data.storeGrade("CS101", 101, "H1", 4);
        ArrayList<String> expected = new ArrayList<>();
        expected.add("4");
        ArrayList<String> actual = Data.getAllAssignmentDatesForCourse("CS101", 102);
        assertNotEquals(expected, actual);
    }

    //getUpcomingDeadlinesForCourse Tests

    @Test
    void TestgetUpcomingDeadlinesForCourse(){
        Data.storeCourseName("CS101", 101);
        Data.storeAssignments("CS101", 101, "A1", "2024/03/02", "not complete", 30);
        Data.storeExams("CS101", 101, "H1", "2024/03/01", "not complete", 30);
        int expected = 20240301;
        int actual = Data.getUpcomingDeadlinesForCourse("CS101", 101);
        assertEquals(expected, actual);
    }

    @Test
    void TestgetUpcomingDeadlinesForCourseNotSame(){
        Data.storeCourseName("CS101", 101);
        Data.storeAssignments("CS101", 101, "A1", "2024/03/02", "not completed", 30);
        Data.storeExams("CS101", 101, "H1", "2024/03/01", "not completed", 30);
        int expected = 20240302;
        int actual = Data.getUpcomingDeadlinesForCourse("CS101", 101);
        assertNotEquals(expected, actual);
    }

    @Test
    void TestgetUpcomingDeadlinesForCourseeWrongID(){
        Data.storeCourseName("CS101", 101);
        Data.storeAssignments("CS101", 101, "A1", "2024/03/02", "not completed", 30);
        Data.storeExams("CS101", 101, "H1", "2024/03/01", "not completed", 30);
        int expected = 20240301;
        int actual = Data.getUpcomingDeadlinesForCourse("CS101", 102);
        assertNotEquals(expected, actual);
    }

    //getTotalTasksForCourse Tests

    @Test
    void TestgetTotalTasksForCourse(){
        Data.storeCourseName("CS101", 101);
        Data.storeAssignments("CS101", 101, "A1", "2024/03/02", "not completed", 30);
        Data.storeExams("CS101", 101, "H1", "2024/03/01", "not completed", 30);
        int expected = 2;
        int actual = Data.getTotalTasksForCourse("CS101101");
        assertEquals(expected, actual);
    }

    @Test
    void TestgetTotalTasksForCourseNotSame(){
        Data.storeCourseName("CS101", 101);
        Data.storeAssignments("CS101", 101, "A1", "2024/03/02", "not completed", 30);
        Data.storeExams("CS101", 101, "H1", "2024/03/01", "not completed", 30);
        int expected = 3;
        int actual = Data.getTotalTasksForCourse("CS101101");
        assertNotEquals(expected, actual);
    }

    @Test
    void TestgetTotalTasksForCourseWrongID(){
        Data.storeCourseName("CS101", 101);
        Data.storeAssignments("CS101", 101, "A1", "2024/03/02", "not completed", 30);
        Data.storeExams("CS101", 101, "H1", "2024/03/01", "not completed", 30);
        int expected = 2;
        int actual = Data.getTotalTasksForCourse("CS101102");
        assertNotEquals(expected, actual);
    }

    //calculateCourseGrade Tests

    @Test
    void TestcalculateCourseGrade(){
        Data.storeCourseName("CS101", 101);
        Data.storeAssignments("CS101", 101, "A1", "2024/03/02", "not completed", 0.5);
        Data.storeExams("CS101", 101, "H1", "2024/03/01", "not completed", 0.5);
        Data.storeGrade("CS101", 101, "H1", 4);
        Data.storeGrade("CS101", 101, "A1", 4);
        double expected = 4;
        double actual = Data.calculateCourseGrade("CS101101");
        assertEquals(expected, actual);
    }

    @Test
    void TestcalculateCourseGradeNotSame(){
        Data.storeCourseName("CS101", 101);
        Data.storeAssignments("CS101", 101, "A1", "2024/03/02", "not completed", 0.5);
        Data.storeExams("CS101", 101, "H1", "2024/03/01", "not completed", 0.5);
        Data.storeGrade("CS101", 101, "H1", 4);
        Data.storeGrade("CS101", 101, "A1", 4);
        double expected = 3;
        double actual = Data.calculateCourseGrade("CS101101");
        assertNotEquals(expected, actual);
    }

    @Test
    void TestcalculateCourseGradeWrongID(){
        Data.storeCourseName("CS101", 101);
        Data.storeAssignments("CS101", 101, "A1", "2024/03/02", "not completed", 0.5);
        Data.storeExams("CS101", 101, "H1", "2024/03/01", "not completed", 0.5);
        Data.storeGrade("CS101", 101, "H1", 4);
        Data.storeGrade("CS101", 101, "A1", 4);
        double expected = 4;
        double actual = Data.calculateCourseGrade("CS101102");
        assertNotEquals(expected, actual);
    }
}
