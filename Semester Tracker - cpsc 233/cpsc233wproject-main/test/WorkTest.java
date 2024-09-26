import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WorkTest {
    Assignment assignment;

    @BeforeEach
    void createClass(){
        assignment = new Assignment("assignment1", "20/03/2024", "not complete", 0.5);
    }

    @Test
    void getName() {
        String actual = assignment.getName();
        String expected = "assignment1";
        assertEquals(expected, actual);
    }

    @Test
    void getDueDate() {
        String actual = assignment.getDueDate();
        String expected = "20/03/2024";
        assertEquals(expected, actual);
    }

    @Test
    void getDueDateInt() {
        int actual = assignment.getDueDateInt();
        int expected = 20032024;
        assertEquals(expected, actual);
    }

    @Test
    void getStatus() {
        String actual = assignment.getStatus();
        String expected = "not complete";
        assertEquals(expected, actual);
    }

    @Test
    void testToString() {
        String actual = assignment.toString();
        String expected = "assignment1: 20/03/2024";
        assertEquals(expected, actual);
    }

    @Test
    void getWorkWeight() {
        double actual = assignment.getWorkWeight();
        double expected = 0.5;
        assertEquals(expected, actual);
    }

    @Test
    void getGrade() {
        int actual = assignment.getGrade();
        int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    void setGrade() {
        assignment.setGrade(5);
        int actual = assignment.getGrade();
        int expected = 5;
        assertEquals(expected, actual);
    }

    @Test
    void setStatus() {
        assignment.setStatus("complete");
        String actual = assignment.getStatus();
        String expected = "complete";
        assertEquals(expected, actual);
    }
}