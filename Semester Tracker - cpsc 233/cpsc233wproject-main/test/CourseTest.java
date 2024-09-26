import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class CourseTest {

    @Test
    public void testgetCourseName() {
        Course course = new Course("CPS", 231);
        assertEquals("CPS", course.getCourseName());
    }
    @Test
    public void testgetCourseNumber() {
        Course course = new Course("CPS", 231);
        assertEquals(231, course.getCourseNumber());
    }

    /**
     * testAddAndRemoveAssignment
     * This test.csv creates a course
     * adds an assignment to it
     * Checks if it was added
     * removes it, then checks if it was removed
     */
    @Test
    public void testAddAndRemoveAssignment() {
        Course course = new Course("CPS", 231);
        Assignment assignment = new Assignment("A1", "2024/03/01", "incomplete", 30);
        course.addAssignment("A1", assignment);


        assertTrue(course.getAssignmentIDs().containsKey("A1"));
        assertEquals(assignment, course.getAssignmentIDs().get("A1"));

        course.removeAssignment("A1", assignment);
        assertFalse(course.getAssignmentIDs().containsKey("A1"));
    }

    /**
     * this test.csv does the same thing as testAddAndRemoveAssignment except with exams
     */

    @Test
    public void testAddAndRemoveExam() {
        Course course = new Course("Phil", 213);
        Exam exam = new Exam("Test", "2024/04/01", "complete", 50);
        course.addExam("Test", exam);

        assertTrue(course.getExamIDs().containsKey("Test"));
        assertEquals(exam, course.getExamIDs().get("Test"));

        course.removeExam("Test", exam);
        assertFalse(course.getExamIDs().containsKey("Test"));

    }
}