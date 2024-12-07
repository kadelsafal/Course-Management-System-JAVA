package system;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


class Person {
	private int id;
	private String name;
	private String email;
	
	public Person(int id, String name,String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}
	public int getId() {
		return id;
	}
	 public String getName() {
	        return name;
	 }

	 public String getEmail() {
	        return email;
	 }
}
	 
	 public class Instructor extends Person{
		  // Default constructor
		    public Instructor() {
		        super(0, "", "");
		    }

		    // Parameterized constructor
		    public Instructor(int id, String name, String email) {
		        super(id, name, email);
		    }
		
		 
	 
	 public void instructor_list(JTable instructorTable) {
		  
	        DefaultTableModel model = (DefaultTableModel) instructorTable.getModel();
	        try {
	        	Connection connection = database.getConnection();
	            // Use the existing connection instead of creating a new one
	            Statement statement = connection.createStatement();
	            String query = "SELECT * FROM instructors";
	            ResultSet resultSet = statement.executeQuery(query);
	            model.setRowCount(0);
	            while (resultSet.next()) {
	                int id = resultSet.getInt("instructor_id");
	                String name = resultSet.getString("name");
	                String email = resultSet.getString("email");
	                String module = resultSet.getString("modules_name");

	                // Create an instance of the Instructor class and add it to the list
	               
	                // Add the row to the JTable model
	                model.addRow(new Object[]{id, name, email,module});
	            }

	            // Close resources
	            resultSet.close();
	            statement.close();

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	 public void addInstructor(int id, String name, String email,String password,String module) {
		 try {
			 Connection connection = database.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO instructors (instructor_id, name, email,password,modules_name) VALUES (?, ?, ?,?,?)");
			 preparedStatement.setInt(1, id);
			 preparedStatement.setString(2, name);
			 preparedStatement.setString(3, email);
			 preparedStatement.setString(4, password);
			 preparedStatement.setString(5, module);
			 preparedStatement.executeUpdate();
			 preparedStatement.close();
			 connection.close();
		 }catch (Exception e) {
	            e.printStackTrace();
	        }
	 }
	 
	
	 public void deleteInstructor(int id, String name) {
		 try {
			 Connection connection = database.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM instructors WHERE instructor_id = ? AND name = ?");
			 preparedStatement.setInt(1, id);
			 preparedStatement.setString(2, name);
			 
			 preparedStatement.executeUpdate();
			 preparedStatement.close();
			 connection.close();
		 }catch (Exception e) {
	            e.printStackTrace();
	        }
	 }
	 public void updateInstructor() {
	        try {
	            Connection connection = database.getConnection();
	            Statement statement = connection.createStatement();

	            // Execute the first statement to set the variable
	            statement.execute("SET @new_id := 0");

	            // Execute the second statement to update instructor_id
	            String updateQuery = "UPDATE instructors SET instructor_id = @new_id := @new_id + 1";
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
	 public void editInstructor(int id, String name, String module) {
		    try {
		        Connection connection = database.getConnection();
		        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE instructors SET name = ?, modules_name = ? WHERE instructor_id = ?");
		        preparedStatement.setString(1, name);
		        preparedStatement.setString(2, module);
		        preparedStatement.setInt(3, id);
		        preparedStatement.executeUpdate();
		        preparedStatement.close();
		        connection.close();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}

	 
	   public void instruct_module(String assignedModule,JTable teacherTable) {
	        DefaultTableModel model = (DefaultTableModel) teacherTable.getModel();
	        try {
	            Connection connection = database.getConnection();
	            String query = "SELECT instructor_id, name, modules_name FROM instructors WHERE modules_name = ?";
	            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	                preparedStatement.setString(1, assignedModule);
	                System.out.print("Assigned: "+assignedModule);
	                try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                    model.setRowCount(0);
	                    while (resultSet.next()) {
	                        int instructorId = resultSet.getInt("instructor_id");
	                        String instructorName = resultSet.getString("name");
	                        String module_name = resultSet.getString("modules_name");

	                        model.addRow(new Object[]{instructorId, instructorName, module_name});
	                    }	            
	                    }
	            }
	        
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	   }
	 }


	 

	
