import java.io.File;
import java.io.FileWriter;
import java.io.IOException;



public class FileSaver {
    public static boolean save(File file, Data data) {
        try (FileWriter fw = new FileWriter(file)) {
            fw.write("Courses\n");
            for (Course course : data.getAllCourses()) {
                fw.write(String.format("%s,%s%n", course.getCourseName(), course.getCourseNumber()));
                fw.flush();
                fw.write("Assignments\n");
                for (Assignment assignment : data.getAllAssignments(course)) {
                    fw.write(String.format("%s,%s,%s,%s,%s%n", assignment.getName(), assignment.getDueDate(), assignment.getStatus(), assignment.getWorkWeight(), assignment.getGrade()));
                }
                fw.flush();
                fw.write("Exams\n");
                for (Exam exam : data.getAllExams(course)) {
                    fw.write(String.format("%s,%s,%s,%s,%s%n", exam.getName(), exam.getDueDate(), exam.getStatus(), exam.getWorkWeight(), exam.getGrade()));
                }
                fw.flush();
            }
            fw.flush();
            return true; // Shows successful file saving
        } catch (IOException ioe) {
            //Return false if an IOException occurs during the file writing
            return false;
        }
    }
}
