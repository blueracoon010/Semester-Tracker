/** Authors: 
* Saira Fatima
 * Warisha Zaman
 * Dominic Afuba
 *
 * We have created a semester tracker , which enables the user to add their classes, assignments, grades etc.
 * and allows them to track their progress and analyzes the data from what is being stored .
 *
 * The follwing is  the code for menu class which displays the menu options for the
 * user to choose from and has the functions created which takes the input from the user and stores
 * into the data class file.
 *
 */

import java.io.File;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Menu {
    //Data object to store user's information
    private static Data data = new Data();

    // Scanner object for user input
    private static final Scanner scanner = new Scanner(System.in);

    // List of menu options
    private static final ArrayList<String> options = new ArrayList<>();

    // Static initializer block to populate the list of menu options
    static{
        options.add("Exit");
        options.add("Add Course");
        options.add("Add Assignment");
        options.add("Add Exam ");
        options.add("Add Grades");
        options.add("Update Assignment Status");
        options.add("Delete Exam");
        options.add("Delete Assignment");
        options.add("View  all Assignments dates of a Course  ");
        options.add("View  all Courses ");
        options.add("View all  Exams dates of a Course");
        options.add("View all Deadlines");
        options.add("View Grades for a specific course");
        options.add("when are the  upcoming deadlines");
        options.add("What Course has the most Assignments and Exams");
        options.add("What Course has the least assignments and exams ");
        options.add("Recommend Study Schedule");// this would be based on the grades
        options.add("Load");
        options.add("Save");
    }


    // Message displaying the menu options
    private static String optMessage = """
            Track Your Semester !
            \tMenu options""";


    // Static initializer block to format the menu options message
    static{
        StringBuilder sb = new StringBuilder();
        sb.append(optMessage);
        for (int i = 0 ; i<options.size(); i++){
            sb.append(String.format("\t%d. %s\n", i , options.get(i)));
        }
        sb.append("\nSelect a menu option: ");
        optMessage = sb.toString();
    }

    /**
     * The Loop that the menu is meant to follow
     */

    public static void menuLoop(){
        System.out.println(optMessage);
        String choice = scanner.nextLine();
        int option = Integer.parseInt(choice);
        while(option !=0){
            if(option > 0 && option < options.size()){
                System.out.printf("Selected option %d. %s%n", option, options.get(option));
                System.out.println("Press any Enter Key to Continue");
                scanner.nextLine();
            }
            switch(option) {
                case 1 -> menuAddCourse();
                case 2 -> menuAddAssignment();
                case 3 -> menuAddExam();
                case 4 -> menuAddGrades();
                case 5 ->  menuUpdateAssignmentStatus();
                case 6-> menuDeleteExam();
                case 7 -> menuDeleteAssignment();
                case 8 -> menuViewAllAssignmentDatesForCourse();
                case 9 -> menuViewAllCourses();
                case 10 -> menuViewAllExamDatesForCourse();
                case 11 -> menuViewAllDeadlines();
                case 12 -> menuViewGradesForCourse();
                case 13 -> menuUpcomingDeadlines();
                case 14 -> menuCourseMostTasks();
                case 15 -> menuCourseLeastTasks();
                case 16 -> menuRecommendCourseToImprove();
                case 17 -> load();
                case 18 -> save();
                default -> System.out.printf("Option %d not recognized!%n", option);
            }
            System.out.println("Press Enter to see the menu again");
            scanner.nextLine();
            System.out.println(optMessage);
            choice = scanner.nextLine();
            option = Integer.parseInt(choice);
        }
        System.out.printf("Thanks for using the semester tracker.%nBye !%n");
    }


    /**
     * Saves a file to a csv
     */

    private static void save(){
        String filename;
        File file;
        do{
            do {
                System.out.println("Enter a filename");
                filename = scanner.nextLine().trim();
            }while (filename.isEmpty());
            file = new File(filename);
        }while(file.exists() && !file.canWrite());
        if(FileSaver.save(file, data)){
            System.out.printf("Saved to file %s%n", filename);
        }else{
            System.err.printf("Failed to save to file %s%n", filename);
        }
    }

    /**
     * Loads a file from a csv
     */
    private static void load(){
        String filename;
        File file;
        do{
            do {
                System.out.println("Enter a filename");
                filename = scanner.nextLine().trim();
            }while (filename.isEmpty());
            file = new File(filename);
        }while(!file.exists() && !file.canRead());
        Data datafile = FileLoader.load(file);
        if(datafile == null){
            System.err.printf("Failed to load data from file %s%n", filename );
        }else {
            Menu.data = datafile ;
        }
    }

    //Adding Functions for course

    /**
     * Adds a course
     */

    private static void menuAddCourse(){
        boolean success;
        do {
            String course_name = getCourseName();
            int course_number = getCourseNumber();
            success = data.storeCourseName(course_name, course_number );
            if(success){
                System.out.println("Successful!");
            }else{
                System.out.println("Failed! Try again.");
            }
        }while(!success);
    }

    /**
     * Prompts the user to enter a course name.
     * Continues prompting until a non-empty course name is provided.
     *
     * @return the course name entered by the user
     */
    private static String getCourseName(){
        String course_name;
        do{
            System.out.println("Enter Course Name ");
            course_name =scanner.nextLine().trim();
        }while(course_name.isEmpty());
        return course_name ;
    }

    /**
     * Prompts the user to enter a course number.
     * Validates that the course number is exactly 3 characters long.
     *
     * @return the course number entered by the user
     */
    private static int getCourseNumber(){
        System.out.println("Enter the course number");
        String course_number = scanner.nextLine();
        while(course_number.length()!= 3){
            System.out.println("Enter  course number of length 3");
            course_number = scanner.nextLine();
        }
        return Integer.parseInt(course_number);
    }

    /**
     * Adds an assignment
     */
    private static void menuAddAssignment(){
        boolean success;
        do {
            String coursename = getCourseName();
            String Assignmentname = getAssignmentName();
            int coursenumber = getCourseNumber();
            String DueDate = getDueDate();
            String Status = getStatus();
            double weight = getWeight();
            success = data.storeAssignments(coursename,  coursenumber,Assignmentname, DueDate, Status,  weight);
            if(success){
                System.out.println("Successful!");
            }else{
                System.out.println("Failed! Try again.");
            }
        } while(!success);
    }

    //Adding functions for the menuAddAssignment

    /**
     * Gets the due date of an Assignment
     */
    private static String getDueDate(){
        String DueDate;
        do{
            System.out.println("Enter the Due Date");
            DueDate = scanner.nextLine().trim();
        }while(DueDate.isEmpty());
        return DueDate;
    }

    /**
     * Gets the status of an assignment
     */
    private static String getStatus(){
        String Status;
        boolean success = false;
        do{
            // Prompts the user to enter the status
            System.out.println("Enter the Status :complete , not complete");
            Status = scanner.nextLine().trim();
            // Checks if the entered status is valid
            if (Status.equals("complete")){
                success = true;
            }
            if (Status.equals("not complete")){
                success = true;
            }
        }while(!success);// Loops until a valid status is provided
        return Status;
    }
    /**
     * Prompts the user to enter the numerical grade for a course.
     *
     * @return the numerical grade entered by the user
     */

    private static int getGrades() {
        int grade;
        do {
            // Prompts the user to enter the numerical grade
            System.out.println("Enter the numerical grade (4 for A, 3 for B, 2 for C, 1 for D, 0 for F):");
            String gradeString = scanner.nextLine().trim();

            try {
                grade = Integer.parseInt(gradeString);

                // Validate the grade range
                if (grade < 0 || grade > 4) {
                    System.out.println("Invalid grade. Please enter a valid numerical grade (0 to 4).");
                    grade = -1; // Set to -1 to continue the loop
                }
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid integer
                System.out.println("Invalid input. Please enter a numerical grade.");
                grade = -1; // Set to -1 to continue the loop
            }
        } while (grade == -1);// Loops until a valid grade is provided

        return grade;
    }
    /**
     * Prompts the user to enter the weight of a course.
     *
     * @return the weight entered by the user
     */
    private static double getWeight() {
        double weight;
        do {
            // Prompts the user to enter the weight of the course
            System.out.println("Enter the weight of the course:");
            try {
                weight = Double.parseDouble(scanner.nextLine().trim());
                if (weight < 0) {
                    System.out.println("Please enter a non-negative weight.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number for the weight.");
                weight = -1;
            }
        } while (weight < 0);// Loops until a non-negative weight is provided


        return weight;
    }
    /**
     * Prompts the user to enter the name of an assignment.
     *
     * @return the assignment name entered by the user
     */

    private static String getAssignmentName(){
        String Assignmentname;
        do{
            // Prompts the user to enter the assignment name
            System.out.println("Enter Assignment Name ");
            Assignmentname =scanner.nextLine().trim();
        }while(Assignmentname.isEmpty());// Loops until a non-empty assignment name is provided
        return Assignmentname ;
    }
    /**
     * Displays the menu for adding grades to an assignment.
     * Prompts the user to enter the course name, course number, assignment name, and grade.
     * Stores the grade information in the data object.
     */
    //Adding grades
    private static void menuAddGrades() {
        // Retrieves course and assignment details from the user
        String courseName = getCourseName();
        int courseNumber = getCourseNumber();
        String Assignmentname = getAssignmentName();

        boolean success;

        do {
            // Retrieves and validates the grade from the user
            int grade = getGrades();

            // Stores the grade information in the data object
            success = data.storeGrade(courseName, courseNumber,Assignmentname, grade);

            // Provides feedback to the user based on the success of the operation
            if (success) {
                System.out.println("Grades added successfully!");
            } else {
                System.out.println("Failed to add grades. Please check your input.");
            }
        } while (!success);
    }
    //Updating the assignment

    /**
     * sets the status of an assignment
     */
    private static void menuUpdateAssignmentStatus() {
        String courseName = getCourseName();
        String assignmentName = getAssignmentName();
        int courseNumber = getCourseNumber();

        // Retrieves the updated status from the user
        String updatedStatus = getStatus();

        // Updates the status of the assignment in the data object
        boolean success = data.updateAssignmentStatus(courseName, courseNumber, assignmentName, updatedStatus);

        // Provides feedback to the user based on the success of the operation
        if (success) {
            System.out.println("Status updated successfully!");
        } else {
            System.out.println("Failed to update status. Please check your input.");
        }
    }

    //Adding exam

    /**
     * Adds an Exam
     */
    private static void menuAddExam(){
        boolean success;
        do {
            String courseName = getCourseName();
            int courseNumber = getCourseNumber();
            String examname = getExamName();
            String date = getExamDate();
            double weight = getWeight();
            String Status = getStatus();
            success = data.storeExams(courseName, courseNumber, examname, date, Status, weight) ;
            if(success){
                System.out.println("Successful!");
            }else{
                System.out.println("Failed! Try again.");
            }
        } while(!success);
    }

    //adding functions for exam

    /**
     * Gets the duedate of an exam
     */
    private static String getExamDate(){
        String ExamDate;
        do{
            System.out.println("Enter the Exam Date");
            ExamDate = scanner.nextLine().trim();
        }while(ExamDate.isEmpty());
        return ExamDate;
    }
    /**
     * Prompts the user to enter the name of an exam.
     *
     * @return the exam name entered by the user
     */
    private static String getExamName(){
        String Examname;
        do{
            System.out.println("Enter Exam Name ");
            Examname =scanner.nextLine().trim();
        }while(Examname.isEmpty());
        return Examname ;
    }

    // function to view courses

    /**
     * Displays the courseIds of the courses added
     */
    private static void menuViewAllCourses(){
        ArrayList<String> allCourses = data.getAllCourseIDs();

        if (!allCourses.isEmpty()) {
            System.out.println("All Courses:");
            for (String course : allCourses) {
                System.out.println( course);
            }
        } else {
            System.out.println("No courses found.");
        }
    }

    // View all assignment Dates of a  course

    /**
     * Displays all assignments and their duedates for a course
     */
    private static void menuViewAllAssignmentDatesForCourse() {
        String courseName = getCourseName();
        int courseNumber = getCourseNumber();

        ArrayList<String> assignmentDates = data.getAllAssignmentDatesForCourse(courseName , courseNumber);
        if (!assignmentDates.isEmpty()) {
            System.out.println("Assignment dates for " + courseName + ":");
            for (String assignmentDate : assignmentDates) {
                System.out.println( assignmentDate);
            }
        } else {
            System.out.println("No assignments found for the specified course.");
        }
    }

    //View all exam dates of a course

    /**
     * Displays all exams and their duedates for a course
     */
    private static void menuViewAllExamDatesForCourse() {
        String courseName = getCourseName();
        int courseNumber = getCourseNumber();

        ArrayList<String> examDates = data.getAllExamDatesForCourse(courseName, courseNumber);

        if (!examDates.isEmpty()) {
            System.out.println("Exam dates for " + courseName + ":");
            for (String examDate : examDates) {
                System.out.println(examDate);
            }
        } else {
            System.out.println("No exams found for the specified course.");
        }
    }

    // creating function to view all deadlines

    /**
     * Displays all assignments and exams and their duedates for a course
     */
    private static void menuViewAllDeadlines(){
        String courseName = getCourseName();
        int courseNumber = getCourseNumber();
        ArrayList<String> deadlines = data.getAllDeadlinesForCourse(courseName, courseNumber);

        if (!deadlines.isEmpty()) {
            System.out.println("Deadlines for " + courseName + ":");
            for (String deadline : deadlines) {
                System.out.println( deadline);
            }
        } else {
            System.out.println("No deadlines found for the specified course.");
        }
    }

    // creating fucntion to view the grades for a course
    /**
     * Displays all grades for a course
     */
    private static void menuViewGradesForCourse(){
        String courseName = getCourseName();
        int courseNumber = getCourseNumber();
        ArrayList<Integer> grades = data.getAllGradesForCourse(courseName, courseNumber);

        if (!grades.isEmpty()) {
            System.out.println("Grades for " + courseName + ":");
            for (Integer grade : grades) {
                System.out.println("- " + grade);
            }
        } else {
            System.out.println("No grades found for the specified course.");
        }


    }

    //  creating function to Delete an Assignment

    /**
     * Deletes an assignment
     */
    private static void menuDeleteAssignment(){
        String courseName = getCourseName();
        int courseNumber = getCourseNumber();
        boolean success;
        do {
            System.out.println("Enter the name of the assignment you want to delete:");
            String assignmentName = scanner.nextLine().trim();


            success = data.deleteAssignmentFromCourse(courseName, courseNumber,assignmentName);

            if (success) {
                System.out.println("Assignment deleted successfully!");
            } else {
                System.out.println("Failed to delete Assignment. Please check your input.");
            }
        } while (!success);
    }

    /**
     * Deletes an exam
     */
    private static void menuDeleteExam(){
        String courseName = getCourseName();
        int courseNumber = getCourseNumber();
        boolean success;
        do {
            System.out.println("Enter the name of the exam you want to delete:");
            String examName = scanner.nextLine().trim();


            success = data.deleteExamFromCourse(courseName,  courseNumber,examName);

            if (success) {
                System.out.println("Exam deleted successfully!");
            } else {
                System.out.println("Failed to delete exam. Please check your input.");
            }
        } while (!success);
    }

    //Creating function to retrieve upcoming deadlines

    /**
     * Displays the next deadline for a course
     */
    public static void menuUpcomingDeadlines() {
        String courseName = getCourseName();
        int courseNumber = getCourseNumber();

        // You can specify the number of days ahead here
        int upcomingDeadline = data.getUpcomingDeadlinesForCourse(courseName, courseNumber);

        if (upcomingDeadline >= 0) {
            // Convert the integer date to a formatted string
            String formattedDate = formatDate(upcomingDeadline);

            System.out.println("Upcoming deadline for " + courseName + ":");
            System.out.println( formattedDate);
        } else {
            System.out.println("No upcoming deadlines found for the specified course.");
        }
    }

    /**
     * Formats duedate to standard form
     */
    private static String formatDate(int date) {
        // Assuming the date format is "20324" (YYYYMMDD)
        String dateString = String.valueOf(date);
        System.out.println(dateString);
        String day = dateString.substring(0, 1);
        String month = dateString.substring(1,3);
        String year = dateString.substring(3,5);
        return day + "/" + month + "/" + year;
    }

    // Creating function to retrieve the courses that has the most tasks
    /**
     * Displays course with the most assignments and exams
     */
    private static void menuCourseMostTasks() {
        // Retrieve all courses
        List<String> allCourses = data.getAllCourseIDs();

        if (!allCourses.isEmpty()) {
            String courseWithMostTasks = findCourseWithMostTasks(allCourses);

            if (courseWithMostTasks != null) {
                System.out.println("Course with the most tasks: " + courseWithMostTasks);
            } else {
                System.out.println("No tasks found for any course.");
            }
        } else {
            System.out.println("No courses found.");
        }
    }

    // Function to find the course with the most tasks
    /**
     * finds course with most assignments and exams
     *
     * @param courses The list of courses
     * returns The course with the most amount of assignments and exams
     */
    private static String findCourseWithMostTasks(List<String> courses) {
        int maxTasks = Integer.MIN_VALUE;
        String courseWithMostTasks = null;

        for (String course : courses) {
            int totalTasks = data.getTotalTasksForCourse(course);
            if (totalTasks > maxTasks) {
                maxTasks = totalTasks;
                courseWithMostTasks = course;
            }
        }

        return courseWithMostTasks;
    }

    // craeting the menu function to retrieve the course with the least tasks .
    /**
     * Displays course with the least assignments and exams
     */
    private static void menuCourseLeastTasks() {
        // Retrieve all courses
        List<String> allCourses = data.getAllCourseIDs();

        if (!allCourses.isEmpty()) {
            String courseWithLeastTasks = findCourseWithLeastTasks(allCourses);

            if (courseWithLeastTasks != null) {
                System.out.println("Course with the least tasks: " + courseWithLeastTasks);
            } else {
                System.out.println("No tasks found for any course.");
            }
        } else {
            System.out.println("No courses found.");
        }
    }

    // Function to find the course with the least tasks
    /**
     * finds course with most assignments and exams
     *
     * @param courses The list of courses
     * returns The course with the least amount of assignments and exams
     */
    private static String findCourseWithLeastTasks(List<String> courses) {
        int minTasks = Integer.MAX_VALUE;
        String courseWithLeastTasks = null;

        for (String course : courses) {
            int totalTasks = data.getTotalTasksForCourse(course);
            if (totalTasks < minTasks) {
                minTasks = totalTasks;
                courseWithLeastTasks = course;
            }
        }

        return courseWithLeastTasks;
    }
    // creating function to analyze which course to focus on
    /**
     * Displays the course with the worst grade
     */
    private static void menuRecommendCourseToImprove() {
        String courseWithLowestAverageGrade = null;
        double lowestAverageGrade = Double.MAX_VALUE;

        for (String course : data.getAllCourseIDs()) {
            double averageGrade;
            averageGrade = data.calculateCourseGrade( course);

            if (averageGrade < lowestAverageGrade) {
                lowestAverageGrade = averageGrade;
                courseWithLowestAverageGrade = course;
            }
        }

        if (courseWithLowestAverageGrade != null) {
            System.out.println("You should focus more on the course: " + courseWithLowestAverageGrade);
        } else {
            System.out.println("No courses found.");
        }
    }
}
