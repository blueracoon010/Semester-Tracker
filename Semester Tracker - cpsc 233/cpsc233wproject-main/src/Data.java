/** Authors: Saira Fatima, Warisha Zaman, Dominic Afuba
 * The follwing is the code for data class which stores the information given from the
 * menu class
 */


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
/**
 * The Data class stores information about courses, assignments, and exams.
 * It provides methods to manipulate and retrieve data.
 */

public class Data {

    //HashMap containing courses and their assignments and exams
    private static final HashMap<String, Course> courseIDs = new HashMap<>();

    //stores a course in course hashmap
    /**
     * Stores a course in the course hashmap.
     *
     * @param name   the name of the course
     * @param number the course number
     * @return true if the course is successfully stored, false if a course with the same ID already exists
     */
    public boolean storeCourseName(String name, int number) {
        String id = createID(name, Integer.toString(number));
        if (!courseIDs.containsKey(id)) {
            Course course = new Course(name, number);
            courseIDs.put(id, course);
            return true;
        } else {
            return false;
        }
    }

    //store assignments in assignment hashmap using the store work method
    /**
     * Stores an assignment for a specified course.
     *
     * @param courseName     the name of the course
     * @param courseNumber   the course number
     * @param assignmentName the name of the assignment
     * @param date           the due date of the assignment
     * @param status         the status of the assignment
     * @param weight         the weight of the assignment
     * @return true if the assignment is successfully stored, false if an assignment with the same name already exists for the course
     */
    public boolean storeAssignments(String courseName, int courseNumber, String assignmentName, String date, String status, double weight) {
        String courseID = createID(courseName, Integer.toString(courseNumber));
        String workID = createID(courseID, assignmentName);
        if (courseIDs.containsKey(courseID)) {
            Course course = courseIDs.get(courseID);
            Assignment assignment = new Assignment(assignmentName, date, status, weight);
            boolean theSame = false;
            for(Assignment other : course.getAssignments()){
                if (assignment.equals(other)) {
                    theSame = true;
                    break;
                }
            }
            if(!theSame) {
                course.addAssignment(workID, assignment);
                return true;
            }else{
                System.out.println("Failed! Inputted data already exists");
                return false;
            }
        } else {
            System.out.println("Failed! Course does not exist");
            return false;
        }
    }

    //store exams in exam hashmap using the store work method
    /**
     * Stores an exam for a specified course.
     *
     * @param courseName the name of the course
     * @param courseNumber the course number
     * @param examName the name of the exam
     * @param date the date of the exam
     * @param status the status of the exam
     * @param weight the weight of the exam
     * @return true if the exam is successfully stored, false if an exam with the same name already exists for the course
     */
    public boolean storeExams(String courseName, int courseNumber, String examName, String date, String status, double weight) {
        String courseID = createID(courseName, Integer.toString(courseNumber));
        String workID = createID(courseID, examName);
        if (courseIDs.containsKey(courseID)) {
            Course course = courseIDs.get(courseID);
            Exam exam = new Exam(examName, date, status, weight);
            boolean theSame = false;
            for(Exam other : course.getExams()){
                if (exam.equals(other)) {
                    theSame = true;
                    break;
                }
            }
            if(!theSame) {
                course.addExam(workID, exam);
                return true;
            }else{
                return false;
            }
        } else {
            return false;
        }
    }

    //updates the grade of an assignment or exam using the update value method
    /**
     * Stores the grade for a specified assignment or exam.
     *
     * @param courseName   the name of the course
     * @param courseNumber the course number
     * @param name         the name of the assignment or exam
     * @param grade        the grade to be stored
     * @return true if the grade is successfully stored, false if the course, assignment, or exam does not exist
     */
    public boolean storeGrade(String courseName, int courseNumber, String name, int grade) {
        String courseID = createID(courseName, Integer.toString(courseNumber));
        String workID = createID(courseID, name);
        if(courseIDs.containsKey(courseID)) {
            Course course = courseIDs.get(courseID);
            if (course.getAssignmentIDs().containsKey(workID)) {
                Assignment assignment = course.getAssignmentIDs().get(workID);
                assignment.setGrade(grade);
                for(Assignment other : course.getAssignments()){
                    if(other.equals(assignment)){
                        other.setGrade(grade);
                    }
                }
                return true;
            } else if (course.getExamIDs().containsKey(workID)) {
                Exam exam = course.getExamIDs().get(workID);
                exam.setGrade(grade);
                for(Exam other : course.getExams()){
                    if(other.equals(exam)){
                        other.setGrade(grade);
                    }
                }
                return true;
            } else {
                return false;
            }
        }else{
            return false;
        }
    }

    //updates the status of an assignment or exam using the update value method
    /**
     * Updates the status of a specified assignment or exam.
     *
     * @param courseName   the name of the course
     * @param courseNumber the course number
     * @param name         the name of the assignment or exam
     * @param status       the new status to be set
     * @return true if the status is successfully updated, false if the course, assignment, or exam does not exist
     */
    public boolean updateAssignmentStatus(String courseName, int courseNumber, String name, String status) {
        String courseID = createID(courseName, Integer.toString(courseNumber));
        String workID = createID(courseID, name);
        if(courseIDs.containsKey(courseID)) {
            Course course = courseIDs.get(courseID);
            if (course.getAssignmentIDs().containsKey(workID)) {
                Assignment assignment = course.getAssignmentIDs().get(workID);
                assignment.setStatus(status);
                for(Assignment other : course.getAssignments()){
                    if(other.equals(assignment)){
                        other.setStatus(status);
                    }
                }
                return true;
            } else if (course.getExamIDs().containsKey(workID)) {
                Exam exam = course.getExamIDs().get(workID);
                exam.setStatus(status);
                for(Exam other : course.getExams()){
                    if(other.equals(exam)){
                        other.setStatus(status);
                    }
                }
                return true;
            } else {
                return false;
            }
        }else{
            return false;
        }
    }

    //deletes assignment in assignment hashmap using the delete work method
    /**
     * Deletes an assignment from a course.
     *
     * @param courseName      the name of the course
     * @param courseNumber    the course number
     * @param assignmentName  the name of the assignment to be deleted
     * @return true if the assignment is successfully deleted, false if the course or assignment does not exist
     */
    public boolean deleteAssignmentFromCourse(String courseName, int courseNumber, String assignmentName) {
        String courseID = createID(courseName, Integer.toString(courseNumber));
        String workID = createID(courseID, assignmentName);
        if (courseIDs.containsKey(courseID)) {
            Course course = courseIDs.get(courseID);
            if (course.getAssignmentIDs().containsKey(workID)) {
                Assignment assignment = course.getAssignmentIDs().get(workID);
                course.removeAssignment(workID, assignment);
                return true;
            }
            else {
                return false;
            }
        } else {
            return false;
        }
    }

    //deletes exam in exam hashmap using the delete work method
    /**
     * Deletes an exam from a course.
     *
     * @param courseName the name of the course
     * @param courseNumber the course number
     * @param examName the name of the exam to be deleted
     * @return true if the exam is successfully deleted, false if the course or exam does not exist
     */
    public boolean deleteExamFromCourse(String courseName, int courseNumber, String examName) {
        String courseID = createID(courseName, Integer.toString(courseNumber));
        String workID = createID(courseID, examName);
        if (courseIDs.containsKey(courseID)) {
            Course course = courseIDs.get(courseID);
            if (course.getExamIDs().containsKey(workID)) {
                Exam exam = course.getExamIDs().get(workID);
                course.removeExam(workID, exam);
                return true;
            }
            else {
                return false;
            }
        } else {
            return false;
        }
    }

    //gets all the assignment dates for a course using the getAllWorkDatesForCourse method
    /**
     * Gets all the assignment dates for a course.
     *
     * @param courseName the name of the course
     * @param courseNumber the course number
     * @return an ArrayList containing the dates of all assignments in the course
     */
    public ArrayList<String> getAllAssignmentDatesForCourse(String courseName, int courseNumber) {
        ArrayList<String> works = new ArrayList<>();
        String courseID = createID(courseName, Integer.toString(courseNumber));
        if(courseIDs.containsKey(courseID)) {
            Course course = courseIDs.get(courseID);
            for (Assignment assignment : course.getAssignments()) {
                if(Objects.equals(assignment.getStatus(), "not complete") || Objects.equals(assignment.getStatus(), "not completed") || Objects.equals(assignment.getStatus(), "incomplete")) {
                    works.add(assignment.toString());
                }
            }
        }
        return works;
    }

    //gets all the exam dates for a course using the getAllWorkDatesForCourse method
    /**
     * Gets all the exam dates for a course.
     *
     * @param courseName the name of the course
     * @param courseNumber the course number
     * @return an ArrayList containing the dates of all exams in the course
     */
    public ArrayList<String> getAllExamDatesForCourse(String courseName, int courseNumber) {
        ArrayList<String> works = new ArrayList<>();
        String courseID = createID(courseName, Integer.toString(courseNumber));
        if(courseIDs.containsKey(courseID)) {
            Course course = courseIDs.get(courseID);
            for (Exam exam : course.getExams()) {
                if(Objects.equals(exam.getStatus(), "not complete") || Objects.equals(exam.getStatus(), "not completed") || Objects.equals(exam.getStatus(), "incomplete")) {
                    works.add(exam.toString());
                }
            }
        }
        return works;
    }

    //gets all the assignment dates and exam dates for a course using the getAllWorkDatesForCourse method
    /**
     * Retrieves all deadlines (assignment and exam dates) for a specified course as strings.
     * @param courseName The name of the course.
     * @param courseNumber The number of the course.
     * @return An ArrayList containing all deadlines (assignment and exam dates) for the specified course.
     */
    public ArrayList<String> getAllDeadlinesForCourse(String courseName, int courseNumber) {
        ArrayList<String> Deadlines = getAllAssignmentDatesForCourse(courseName, courseNumber);
        Deadlines.addAll(getAllExamDatesForCourse(courseName, courseNumber));
        return Deadlines;
    }

    //gets all the assignment dates and exam dates for a course using the getAllWorkDatesForCourse method but returns it
    //as a list of integers
    /**
     * Retrieves all deadlines (assignment and exam dates) for a specified course as integers.
     * @param courseName The name of the course.
     * @param courseNumber The number of the course.
     * @return An ArrayList containing all deadlines (assignment and exam dates) for the specified course as integers.
     */
    private ArrayList<Integer> getAllDeadlinesForCourseInt(String courseName, int courseNumber) {
        ArrayList<Integer> works = new ArrayList<>();
        String courseID = createID(courseName, Integer.toString(courseNumber));
        if(courseIDs.containsKey(courseID)) {
            Course course = courseIDs.get(courseID);
            for (Assignment assignment : course.getAssignments()) {
                if(Objects.equals(assignment.getStatus(), "not complete") || Objects.equals(assignment.getStatus(), "not completed") || Objects.equals(assignment.getStatus(), "incomplete")) {
                    works.add(assignment.getDueDateInt());
                }
            }
            for (Exam exam : course.getExams()) {
                if(Objects.equals(exam.getStatus(), "not complete") || Objects.equals(exam.getStatus(), "not completed") || Objects.equals(exam.getStatus(), "incomplete")) {
                    works.add(exam.getDueDateInt());
                }
            }
        }
        return works;
    }

    //gets all courseIDs
    /**
     * Retrieves all course IDs.
     * @return An ArrayList containing all course IDs.
     */
    public ArrayList<String> getAllCourseIDs() {
        return new ArrayList<>(courseIDs.keySet());
    }
    /**
     * Retrieves all courses.
     * @return An ArrayList containing all courses.
     */
    public ArrayList<Course> getAllCourses() {
        return new ArrayList<>(courseIDs.values());
    }
    /**
     * Retrieves all assignments for a specified course.
     * @param course The course for which assignments are retrieved.
     * @return An ArrayList containing all assignments for the specified course.
     */
    public ArrayList<Assignment> getAllAssignments(Course course) {
        return new ArrayList<>(course.getAssignments());
    }
    /**
     * Retrieves all exams for a specified course.
     * @param course The course for which exams are retrieved.
     * @return An ArrayList containing all exams for the specified course.
     */
    public ArrayList<Exam> getAllExams(Course course) {
        return new ArrayList<>(course.getExams());
    }

    //gets all the assignment grades and exam grade for a course using the getAllWorkDatesForCourse method but returns it
    //as a list of integers
    /**
     * Retrieves all grades (assignment and exam grades) for a specified course.
     * @param courseName The name of the course.
     * @param courseNumber The number of the course.
     * @return An ArrayList containing all grades (assignment and exam grades) for the specified course.
     */
    public ArrayList<Integer> getAllGradesForCourse(String courseName, int courseNumber) {
        ArrayList<Integer> works = new ArrayList<>();
        String courseID = createID(courseName, Integer.toString(courseNumber));
        if(courseIDs.containsKey(courseID)) {
            Course course = courseIDs.get(courseID);
            for (Assignment assignment : course.getAssignments()) {
                works.add(assignment.getGrade());
            }
            for (Exam exam : course.getExams()) {
                works.add(exam.getGrade());
            }
        }
        return works;
    }

    //gets the next assignment or exam deadline
    /**
     * Retrieves the upcoming deadline (earliest among assignment and exam deadlines) for a specified course.
     * @param courseName The name of the course.
     * @param courseNumber The number of the course.
     * @return The upcoming deadline for the specified course.
     */
    public int getUpcomingDeadlinesForCourse(String courseName, int courseNumber) {
        ArrayList<Integer> deadlines = getAllDeadlinesForCourseInt(courseName, courseNumber);
        if(deadlines.isEmpty()){
            return 0;
        }
        int min = deadlines.getFirst();
        for (Integer deadline : deadlines) {
            if (min > deadline) {
                min = deadline;
            }
        }
        return min;
    }

    //gets the total amount of assignments and exams in a course
    /**
     * Retrieves the total number of tasks (assignments and exams) in a specified course.
     * @param courseID The ID of the course.
     * @return The total number of tasks (assignments and exams) in the specified course.
     */
    public int getTotalTasksForCourse(String courseID) {
        if (courseIDs.containsKey(courseID)) {
            ArrayList<Assignment> assignments = courseIDs.get(courseID).getAssignments();
            ArrayList<Exam> exams = courseIDs.get(courseID).getExams();
            return assignments.size() + exams.size();
        }else{
            return 0;
        }
    }

    //calculates the grade of a course
    /**
     * Calculates the overall grade for the specified course.
     *
     * @param courseID The ID of the course for which the grade needs to be calculated.
     * @return The calculated overall grade for the course.
     */
    public double calculateCourseGrade(String courseID) {
        double grade = 0.0;
        if(courseIDs.containsKey(courseID)) {
            ArrayList<Assignment> assignments = courseIDs.get(courseID).getAssignments();
            ArrayList<Exam> exams = courseIDs.get(courseID).getExams();
            for (Assignment assignment : assignments) {
                grade += assignment.getGrade() * assignment.getWorkWeight();
            }
            for (Exam exam : exams) {
                grade += exam.getGrade() * exam.getWorkWeight();
            }
        }
        return grade;
    }

    //creates IDs for assignments, courses and exams
    /**
     * Creates a unique ID for a course, combining the name and number.
     *
     * @param name   The name of the course.
     * @param number The number of the course.
     * @return A unique ID for the course.
     */
    private String createID(String name, String number) {
        return name + number;
    }

    /**
     * Removes the course identified by the given course ID from the system.
     *
     * @param courseID The ID of the course to be removed.
     */
    public void removeCourse(String courseID){
        courseIDs.remove(courseID);
    }
}



