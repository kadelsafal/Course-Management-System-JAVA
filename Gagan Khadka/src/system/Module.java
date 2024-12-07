package system;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

class Course{
	private int id;
	private String module_name;
	private int level;
	
	public Course(int id, String module_name, int level) {
		this.id = id;
		this.module_name = module_name;
		this.level = level;
		
		
		
	}
	
	public int getId() {
		return id;
	}
	 public String getName() {
	        return module_name;
	 }

	 public int getlevel() {
	        return level;
	 }
}

public class Module extends Course {
	public Module() {
        super(0, "", 4);
    }

    // Parameterized constructor
    public Module(int id, String module_name, int level) {
        super(id, module_name, level);
    }

    
    
    public void module_list(JTable moduleTable) {
    	 DefaultTableModel model = (DefaultTableModel) moduleTable.getModel();
	        try {
	        	Connection connection = database.getConnection();
	            // Use the existing connection instead of creating a new one
	            Statement statement = connection.createStatement();
	            String query = "SELECT * FROM modules";
	            ResultSet resultSet = statement.executeQuery(query);
	            model.setRowCount(0);
	            while (resultSet.next()) {
	                int id = resultSet.getInt("module_id");
	                String name = resultSet.getString("module_name");
	                int level = resultSet.getInt("level");

	                // Create an instance of the Instructor class and add it to the list
	               
	                // Add the row to the JTable model
	                model.addRow(new Object[]{id, name, level});
	            }

	            // Close resources
	            resultSet.close();
	            statement.close();

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
    
    public void addModule(String module_name, int level) {
		 try {
			 Connection connection = database.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO modules (module_name, level) VALUES (?, ?)");
			 preparedStatement.setString(1, module_name);
			 preparedStatement.setInt(2, level);
			 preparedStatement.executeUpdate();
			 preparedStatement.close();
			 connection.close();
		 } catch (SQLIntegrityConstraintViolationException e) {
	            // Catch the specific exception for duplicate key violation
	            // Display a message to the user indicating the duplicate entry
	            JOptionPane.showMessageDialog(null, "Duplicate entry for module name: " + module_name,
	                    "Duplicate Entry", JOptionPane.ERROR_MESSAGE);
		 }catch (Exception e) {
	            e.printStackTrace();
	        }
	 }
    public void updateModule() {
        try {
            Connection connection = database.getConnection();
            Statement statement = connection.createStatement();

            // Execute the first statement to set the variable
            statement.execute("SET @new_id := 0");

            // Execute the second statement to update instructor_id
            String updateQuery = "UPDATE modules SET module_id = @new_id := @new_id + 1";
            statement.executeUpdate(updateQuery);
            String resetAutoIncrementQuery = "ALTER TABLE students AUTO_INCREMENT = 1";
            statement.executeUpdate(resetAutoIncrementQuery);

            statement.close();
            connection.close();
            System.out.println("Update success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


	 
	
 // Inside the Module class
    public void deleteModule(String module_name, int level) {
        try {
            Connection connection = database.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM modules WHERE module_name = ? AND level = ?");
            preparedStatement.setString(1, module_name);
            preparedStatement.setInt(2, level);

            int rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();

            if (rowsAffected > 0) {
                System.out.println("Module deleted successfully.");
            } else {
                System.out.println("No module found with the specified name and level.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	 
    public void editModule(int id, String module_name, int level) {
        try {
            Connection connection = database.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE modules SET module_name = ?,  level = ? WHERE module_id = ?");
            preparedStatement.setString(1, module_name);
            preparedStatement.setInt(2, level);
            preparedStatement.setInt(3, id);

            int rowsAffected = preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

            if (rowsAffected > 0) {
                System.out.println("Module updated successfully.");
            } else {
                System.out.println("No module found for the provided criteria.");
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            // Handle unique constraint violation
            JOptionPane.showMessageDialog(null, "Duplicate entry for module name. Please choose a different name.", "Duplicate Entry", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void course_list(JTable modulesTable, String assignedModule) {
        DefaultTableModel model = (DefaultTableModel) modulesTable.getModel();

        try {
            Connection connection = database.getConnection();
            String query = "SELECT * FROM courses WHERE module_name = ?";
            
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, assignedModule);

                ResultSet resultSet = preparedStatement.executeQuery();
                model.setRowCount(0);

                while (resultSet.next()) {
                    String name = resultSet.getString("module_name");
                    String course = resultSet.getString("subjects");
                    int level = resultSet.getInt("level");

                    // Add the row to the JTable model
                    model.addRow(new Object[]{ name,course, level});
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void courses(JTable courseTable, String enrolledModule) {
        DefaultTableModel model = (DefaultTableModel) courseTable.getModel();
        
        // Check if the enrolledModule is not "Default"
        if (!"Default".equals(enrolledModule)) {
            try (Connection connection = database.getConnection()) {
                if (connection != null) {
                    String query = "SELECT * FROM courses WHERE module_name = ?";
                    try (PreparedStatement statement = connection.prepareStatement(query)) {
                        statement.setString(1, enrolledModule);

                        ResultSet resultSet = statement.executeQuery();
                        model.setRowCount(0);

                        while (resultSet.next()) {
                            int id = resultSet.getInt("course_id");
                            String name = resultSet.getString("module_name");
                            String course = resultSet.getString("subjects");
                            int level = resultSet.getInt("level");

                            model.addRow(new Object[]{id, course, name, level});
                        }

                        System.out.println("Courses method executed for module: " + enrolledModule);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error during courses retrieval");
            }
        }
    }

  
        

    public void populateModulesComboBox(JComboBox<String> moduleComboBox, int studentLevel, String selectedCourseField) {
        try (Connection connection = database.getConnection()) {
            if (connection != null) {
                String sql = "SELECT DISTINCT module_name FROM modules WHERE level = ? AND course_field = ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setInt(1, studentLevel);
                    statement.setString(2, selectedCourseField);
                    ResultSet resultSet = statement.executeQuery();
                    while (resultSet.next()) {
                    	moduleComboBox.addItem(resultSet.getString("module_name"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error populating modules combo box");
        }
    }

	
public void populateCoursesComboBox(JComboBox<String> courseFieldComboBox) {
    try (Connection connection = database.getConnection()) {
        if (connection != null) {
            String sql = "SELECT DISTINCT course_field FROM modules";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {

                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                	courseFieldComboBox.addItem(resultSet.getString("course_field"));
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error populating modules combo box");
    }
}

public boolean moduleExists(String moduleName) {
    boolean exists = false;
    try {
        Connection connection = database.getConnection();

        // Query to check if the module exists in the modules table
        String query = "SELECT COUNT(*) FROM modules WHERE module_name = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, moduleName);
            ResultSet resultSet = statement.executeQuery();
            
            // If there is at least one row with the specified module name, set exists to true
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                exists = count > 0;
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        // Handle SQL exceptions as needed
    }
    return exists;
}



}



    	
    

