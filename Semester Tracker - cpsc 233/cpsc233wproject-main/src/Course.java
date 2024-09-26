import java.util.ArrayList;
import java.util.HashMap;
/**
 * The Course class represents a course in an educational institution.
 * It contains information about assignments, exams, and their associated IDs.
 */

public class Course {
    // ArrayList to store assignments associated with this course
    private ArrayList<Assignment> assignments = new ArrayList<>();

    // ArrayList to store exams associated with this course
    private ArrayList<Exam> exams = new ArrayList<>();

    // HashMap to map assignment IDs to their respective assignments
    private HashMap<String, Assignment> assignmentIDs = new HashMap<>();

    // HashMap to map exam IDs to their respective exams
    private HashMap<String, Exam> examIDs = new HashMap<>();

    // Name of the course
    private String courseName;

    // Number of the course
    private int courseNumber;

    // Constructor to initialize course name and number
    public Course(String courseName, int courseNumber){
        this.courseName = courseName;
        this.courseNumber = courseNumber;
    }

    // Getter method for retrieving the course name
    public String getCourseName() {
        return courseName;
    }

    // Getter method for retrieving the course number
    public int getCourseNumber() {
        return courseNumber;
    }

    // Getter method for retrieving assignments associated with this course
    public ArrayList<Assignment> getAssignments(){
        return assignments;
    }

    // Getter method for retrieving exams associated with this course
    public ArrayList<Exam> getExams(){
        return exams;
    }

    // Getter method for retrieving assignment IDs and their respective assignments
    public HashMap<String, Assignment> getAssignmentIDs(){
        return assignmentIDs;
    }

    // Getter method for retrieving exam IDs and their respective exams
    public HashMap<String, Exam> getExamIDs(){
        return examIDs;
    }

    // Method to remove an assignment from this course
    public void removeAssignment(String id, Assignment assignment){
        assignmentIDs.remove(id);
        assignments.remove(assignment);
    }

    // Method to remove an exam from this course
    public void removeExam(String id, Exam exam){
        examIDs.remove(id);
        exams.remove(exam);
    }

    // Method to add an assignment to this course
    public void addAssignment(String id, Assignment assignment){
        assignments.add(assignment);
        assignmentIDs.put(id, assignment);
    }

    // Method to add an exam to this course
    public void addExam(String id, Exam exam){
        exams.add(exam);
        examIDs.put(id, exam);
    }
}