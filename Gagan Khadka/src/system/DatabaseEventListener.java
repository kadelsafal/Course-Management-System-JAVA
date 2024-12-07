package system;

import java.sql.Connection;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;





public class DatabaseEventListener extends Thread {
    private Connection connection;
    private ActivityPanel activityPanel;
    private Set<String> currentStudents;
    private Set<String> currentInstructors;

    public DatabaseEventListener(ActivityPanel activityPanel) throws SQLException {
        this.activityPanel = activityPanel;
        this.connection = database.getConnection();
        this.currentStudents = new HashSet<>();
        this.currentInstructors = new HashSet<>();
    }

    @Override
    public void run() {
        try {
            while (true) {
                Statement statement = connection.createStatement();

                // Process students table
                Set<String> updatedStudents = new HashSet<>();
                ResultSet studentResultSet = statement.executeQuery("SELECT * FROM students");
                while (studentResultSet.next()) {
                    String studentName = studentResultSet.getString("name");
                    updatedStudents.add(studentName);
                    if (!currentStudents.contains(studentName)) {
                        // New student
                        String notification = "New student registered: " + studentName;
                        activityPanel.addNotification(notification);
                    }
                }

                // Check for removed students
                for (String student : currentStudents) {
                    if (!updatedStudents.contains(student)) {
                        String notification = "Student removed: " + student;
                        activityPanel.addNotification(notification);
                    }
                }
                currentStudents = updatedStudents;
                studentResultSet.close();

                // Process instructors table
                Set<String> updatedInstructors = new HashSet<>();
                ResultSet instructorResultSet = statement.executeQuery("SELECT * FROM instructors");
                while (instructorResultSet.next()) {
                    String instructorName = instructorResultSet.getString("name");
                    updatedInstructors.add(instructorName);
                    if (!currentInstructors.contains(instructorName)) {
                        // New instructor
                        String notification = "New Teacher registered: " + instructorName;
                        activityPanel.addNotification(notification);
                    }
                }

                // Check for removed instructors
                for (String instructor : currentInstructors) {
                    if (!updatedInstructors.contains(instructor)) {
                        String notification = "Teacher removed: " + instructor;
                        activityPanel.addNotification(notification);
                    }
                }
                currentInstructors = updatedInstructors;
                instructorResultSet.close();

                // Close statement
                statement.close();

                // Sleep for a short time to avoid too frequent updates
                Thread.sleep(1000);
            }
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    
}