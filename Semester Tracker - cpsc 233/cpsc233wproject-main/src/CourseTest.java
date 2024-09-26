import org.junit.Test;

import static org.junit.Assert.*;

public class CourseTest {

    // Test case for getting the course name
    @Test
    public void testgetCourseName() {
        Course course = new Course("CPS", 231);
        assertEquals("CPS", course.getCourseName());
    }

    // Test case for getting the course number
    @Test
    public void testgetCourseNumber() {
        Course course = new Course("CPS", 231);
        assertEquals(231, course.getCourseNumber());
    }

    /**
     * Test case for adding and removing an assignment.
     * It adds an assignment, checks if it was added successfully,
     * then removes it and checks if it was removed successfully.
     */
    @Test
    public void testAddAndRemoveAssignment() {
        Course course = new Course("CPS", 231);
        Assignment assignment = new Assignment("A1", "2024/03/01", "incomplete", 30);
        course.addAssignment("A1", assignment);

        // Check if the assignment was added successfully
        assertTrue(course.getAssignmentIDs().containsKey("A1"));
        assertEquals(assignment, course.getAssignmentIDs().get("A1"));

        // Remove the assignment and check if it was removed successfully
        course.removeAssignment("A1", assignment);
        assertFalse(course.getAssignmentIDs().containsKey("A1"));
    }

    /**
     * Test case for adding and removing an exam.
     * It adds an exam, checks if it was added successfully,
     * then removes it and checks if it was removed successfully.
     */
    @Test
    public void testAddAndRemoveExam() {
        Course course = new Course("Phil", 213);
        Exam exam = new Exam("Test", "2024/04/01", "complete", 50);
        course.addExam("Test", exam);

        // Check if the exam was added successfully
        assertTrue(course.getExamIDs().containsKey("Test"));
        assertEquals(exam, course.getExamIDs().get("Test"));

        // Remove the exam and check if it was removed successfully
        course.removeExam("Test", exam);
        assertFalse(course.getExamIDs().containsKey("Test"));
    }
}