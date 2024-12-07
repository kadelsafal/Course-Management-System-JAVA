package system;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;



public class Result {
	public void result_list(JTable resultmodulesTable, String assignedModule) {
	    DefaultTableModel model1 = (DefaultTableModel) resultmodulesTable.getModel();
	    

	    try {
	        Connection connection = database.getConnection();

	        // Query to select data from results table based on module_name
	        String resultsQuery = "SELECT * FROM results WHERE module_name = ?";
	        try (PreparedStatement resultsStatement = connection.prepareStatement(resultsQuery)) {
	            resultsStatement.setString(1, assignedModule);
	            ResultSet resultSet = resultsStatement.executeQuery();

	            model1.setRowCount(0);
	            

	            // Check if the resultSet is empty
	                // Results found, populate the tables
	                while (resultSet.next()) {
	                    int id = resultSet.getInt("student_id");
	                    String name = resultSet.getString("student_name");
	                    String subjects = resultSet.getString("subjects");
	                    int marks = resultSet.getInt("marks");
	                    String grade = resultSet.getString("grade");

	                    model1.addRow(new Object[]{id, name, subjects, marks, grade});
	                    
	                }

	                // Set the visibility of the resultTable
	                
	            }
	        }catch (Exception e) {
		        e.printStackTrace();
		        // Handle exceptions as needed
		    }
	}

	public void result_students(JTable resultTable, String username, String assignedModule, Integer id) {
	    DefaultTableModel model1 = (DefaultTableModel) resultTable.getModel();

	    try (Connection connection = database.getConnection()) {
	        // Query to select data from results table based on module_name and student_id
	        String moduleQuery;
	        PreparedStatement moduleStatement;
	        if (id != null) {
	            moduleQuery = "SELECT * FROM results WHERE student_name = ? AND student_id = ?";
	            moduleStatement = connection.prepareStatement(moduleQuery);
	            moduleStatement.setString(1, username);
	            moduleStatement.setInt(2, id); // Corrected the parameter index
	        } else {
	            moduleQuery = "SELECT * FROM results WHERE module_name = ? AND student_name = ?";
	            moduleStatement = connection.prepareStatement(moduleQuery);
	            moduleStatement.setString(1, assignedModule);
	            moduleStatement.setString(2, username);
	        }
	        
	        ResultSet moduleResultSet = moduleStatement.executeQuery();

	        // Clear existing rows in the table model
	        model1.setRowCount(0);

	        // Results found, populate model1
	        boolean resultsFound = false;
	        while (moduleResultSet.next()) {
	            // Assuming your_module_table has columns 'subjects', 'marks', and 'grade'
	            String subjects = moduleResultSet.getString("subjects");
	            int marks = moduleResultSet.getInt("marks");
	            String grade = moduleResultSet.getString("grade");

	            // Add row to the table model
	            model1.addRow(new Object[]{subjects, marks, grade});
	            resultsFound = true;
	        }

	        // If no results were found, display message dialog
	        if (!resultsFound) {
	            JOptionPane.showMessageDialog(null, "No results found.", "No Results", JOptionPane.INFORMATION_MESSAGE);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Handle SQLException appropriately
	    }
	}

public void insertResultsData(String email, String module) {
    try (Connection connection = database.getConnection()) {
        if (connection != null) {
            // Get subjects from the courses table based on the module name
            String selectSubjectsSql = "SELECT subjects FROM courses WHERE module_name = ?";
            try (PreparedStatement selectSubjectsStatement = connection.prepareStatement(selectSubjectsSql)) {
                selectSubjectsStatement.setString(1, module);
                try (ResultSet resultSet = selectSubjectsStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String subject = resultSet.getString("subjects");

                        // Insert data into the results table for each subject
                        String insertSql = "INSERT INTO results (student_id, student_name, module_name, subjects, marks, grade) " +
                                "VALUES (?, ?, ?, ?, 0, '')";
                        try (PreparedStatement insertStatement = connection.prepareStatement(insertSql)) {
                            int studentId = getStudentIdByEmail(email);

                            insertStatement.setInt(1, studentId);
                            insertStatement.setString(2, getStudentNameByEmail(email));
                            insertStatement.setString(3, module);
                            insertStatement.setString(4, subject);

                            insertStatement.executeUpdate();
                        }
                    }
                    System.out.println("Inserted into results table for module: " + module);
                }
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error during results data insertion.");
    }
}
public int getStudentIdByEmail(String email) {
    int studentId = -1; // Default value if not found

    try (Connection connection = database.getConnection()) {
        if (connection != null) {
            String sql = "SELECT student_id FROM students WHERE email = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, email);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        studentId = resultSet.getInt("student_id");
                    }
                }
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error retrieving student ID by email.");
    }

    return studentId;
}
private String getStudentNameByEmail(String email) {
    String studentName = "";

    try (Connection connection = database.getConnection()) {
        if (connection != null) {
            String selectNameSql = "SELECT name FROM students WHERE email = ?";
            try (PreparedStatement selectNameStatement = connection.prepareStatement(selectNameSql)) {
                selectNameStatement.setString(1, email);
                try (ResultSet resultSet = selectNameStatement.executeQuery()) {
                    if (resultSet.next()) {
                        studentName = resultSet.getString("name");
                    }
                }
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error retrieving student name by email.");
    }

    return studentName;
}



	// TODO Auto-generated method stub
	
public void editResult(int studentId, String studentName,String subjects, int marks, String grade) {
    try {
        Connection connection = database.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
        		"UPDATE results SET marks = ?, grade = ? WHERE student_id = ? AND student_name = ? AND subjects = ?");
        
        preparedStatement.setInt(1, marks);
        preparedStatement.setString(2, grade);
        preparedStatement.setInt(3, studentId);
        preparedStatement.setString(4, studentName);
        preparedStatement.setString(5, subjects);

        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}



}
