package system;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
class students{
	private int id;
	private String name;
	private int level;
	private String email;
	private String password;
	public students(int id,String name, int level, String email,String password) {
		this.id = id;
		this.name = name;
		this .level = level;
		this .email = email;
		this.password = password;
		
	}
	
	public int getid() {
		return id;
	}
	public String getname() {
		return name;
	}
	public int getlevel() {
		return level;
	}
	public String getemail() {
		return email;
	}
	public String getpassword() {
		return password;
	}
}

public class Student extends students {
	 public Student() {
	        super(0, "", 0,"","");
	    }

	    // Parameterized constructor
	    public Student(int id, String name,int level, String email,String password) {
	        super(id, name,level, email,password);
	    }
	    public void student_list(JTable studentTable) {
			  
	        DefaultTableModel model = (DefaultTableModel) studentTable.getModel();
	        try {
	        	Connection connection = database.getConnection();
	            // Use the existing connection instead of creating a new one
	            Statement statement = connection.createStatement();
	            String query = "SELECT * FROM students";
	            ResultSet resultSet = statement.executeQuery(query);
	            model.setRowCount(0);
	            while (resultSet.next()) {
	                int id = resultSet.getInt("student_id");
	                String name = resultSet.getString("name");
	                String email = resultSet.getString("email");
	                int level = resultSet.getInt("level");
	                // Create an instance of the Instructor class and add it to the list
	               
	                // Add the row to the JTable model
	                model.addRow(new Object[]{id, name,level,email});
	            }

	            // Close resources
	            resultSet.close();
	            statement.close();

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    public void addstudent(String name,String email,int level,String password) {
			 try {
				 Connection connection = database.getConnection();
				 PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO students (name,email,level,password ) VALUES (?, ?,?,?)");
				 preparedStatement.setString(1, name);
				 preparedStatement.setString(2, email);
				 preparedStatement.setInt(3, level);
				 preparedStatement.setString(4, password);
				 preparedStatement.executeUpdate();
				 preparedStatement.close();
				 connection.close();
			 } catch (SQLIntegrityConstraintViolationException e) {
		            // Catch the specific exception for duplicate key violation
		            // Display a message to the user indicating the duplicate entry
		            JOptionPane.showMessageDialog(null, "Duplicate entry for module name: " + name,
		                    "Duplicate Entry", JOptionPane.ERROR_MESSAGE);
			 }catch (Exception e) {
		            e.printStackTrace();
		        }
		 }
	    
	    public void updateStudent() {
	        try {
	            Connection connection = database.getConnection();
	            Statement statement = connection.createStatement();

	            // Execute the first statement to set the variable
	            statement.execute("SET @new_id := 0");

	            // Execute the second statement to update student_id
	            String updateQuery = "UPDATE students SET student_id = @new_id := @new_id + 1";
	            statement.executeUpdate(updateQuery);

	            // Reset the auto-increment value to 1
	            String resetAutoIncrementQuery = "ALTER TABLE students AUTO_INCREMENT = 1";
	            statement.executeUpdate(resetAutoIncrementQuery);

	            statement.close();
	            connection.close();
	            System.out.println("Update success");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    public void deleteStudent(int id, String name) {
	        try {
	            Connection connection = database.getConnection();
	            PreparedStatement preparedStatement1 = connection.prepareStatement("DELETE FROM students WHERE student_id = ? AND name = ?");
	            PreparedStatement preparedStatement2 = connection.prepareStatement("DELETE FROM results WHERE student_id = ? AND student_name = ?");
	            
	            preparedStatement1.setInt(1, id);
	            preparedStatement1.setString(2, name);
	            preparedStatement1.executeUpdate();
	            preparedStatement1.close();
	            
	            preparedStatement2.setInt(1, id);
	            preparedStatement2.setString(2, name);
	            preparedStatement2.executeUpdate();
	            preparedStatement2.close();
	            
	            connection.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    public void editStudent(int id, String name,int level) {
		    try {
		        Connection connection = database.getConnection();
		        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE students SET name = ?, level = ? WHERE student_id = ?");
		        preparedStatement.setString(1, name);
		        preparedStatement.setInt(2, level);  // Corrected order: set level before ID
		        preparedStatement.setInt(3, id);
		        preparedStatement.executeUpdate();
		        preparedStatement.close();
		        connection.close();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
	    
	    public void student_module(String assignedModule,JTable studentmodulesTable) {
	        DefaultTableModel model = (DefaultTableModel) studentmodulesTable.getModel();
	        try {
	            Connection connection = database.getConnection();
	            String query = "SELECT student_id, name,email, level,module_name FROM students WHERE module_name = ?";
	            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	            	
	            	preparedStatement.setString(1, assignedModule);
	                System.out.print("Assigned: "+assignedModule);
	                try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                    model.setRowCount(0);
	                    while (resultSet.next()) {
	                        int studentId = resultSet.getInt("student_id");
	                        String studentName = resultSet.getString("name");
	                        String module_name = resultSet.getString("module_name");
	                        int studentlevel = resultSet.getInt("level");
	                        String studentmail = resultSet.getString("email");
	                        

	                        model.addRow(new Object[]{studentId, studentName, studentmail,module_name,studentlevel});
	                    }	            
	                    }
	            }
	        
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	   }

	    public boolean enrollStudent(String email, String selectedModule, JTable courseTable) {
	        try (Connection connection = database.getConnection()) {
	            if (connection != null) {
	                // Update the student's module in the database
	                String updateSql = "UPDATE students SET module_name = ? WHERE email = ?";
	                try (PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {
	                    updateStatement.setString(1, selectedModule);
	                    updateStatement.setString(2, email);

	                    int rowsAffected = updateStatement.executeUpdate();

	                    if (rowsAffected > 0) {
	                    	
	                    	// Update the course table after successful enrollment
	                        Result result = new Result();
	                        result.insertResultsData(email, selectedModule);
	                        Module module = new Module();
	                        module.courses(courseTable, selectedModule);
	                        System.out.println("Courses method executed for module: " + selectedModule);

	                        return true; // Enrollment successful
	                    } else {
	                        JOptionPane.showMessageDialog(null, "Enrollment failed. Please try again.");
	                    }
	                }
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Error during enrollment.");
	        }

	        return false; // Enrollment failed
	    }

	    public int  getStudentLevel(String email) {
	        try (Connection connection = database.getConnection()) {
	            if (connection != null) {
	                String sql = "SELECT level FROM students WHERE email = ?";
	                try (PreparedStatement statement = connection.prepareStatement(sql)) {
	                    statement.setString(1, email);
	                    ResultSet resultSet = statement.executeQuery();
	                    if (resultSet.next()) {
	                        return resultSet.getInt("level");
	                    }
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Error getting student level");
	        }
	        return -1; // Return -1 if an error occurs
	    }
	    public boolean enrollCourseField(String email, String selectedCourseField) {
	        try (Connection connection = database.getConnection()) {
	            if (connection != null) {
	                // Update the student's course field in the database
	                String updateSql = "UPDATE students SET course_field = ? WHERE email = ?";
	                try (PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {
	                    updateStatement.setString(1, selectedCourseField);
	                    updateStatement.setString(2, email);

	                    int rowsAffected = updateStatement.executeUpdate();

	                    return rowsAffected > 0;
	                }
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Error during course field enrollment.");
	        }
	        return false;
	    }
	}


