package system;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.GraphicsConfiguration;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class login_function extends sytem1 {

    private static final long serialVersionUID = 497182976598376841L;
	private static final GraphicsConfiguration String = null;
    private static sytem1 frameReference;

    public static void setFrameReference(sytem1 frame) {
        frameReference = frame;
    }

    public static void disposeFrame() {
        if (frameReference != null) {
            frameReference.dispose();
            System.out.println("frameReference is null"+frameReference);
        } else {
            System.out.println("frameReference is null");
        }
    }
    public static void setFrameReference(JPanel panel) {
        frameReference = (sytem1) SwingUtilities.getWindowAncestor(panel);
    }
    
    public static void loginApp(String email, char[] password, String selectedRole, sytem1 framereference) {
        setFrameReference(framereference);  // Set the frame reference

        try (Connection connection = database.getConnection()) {
            if (connection != null) {
                String sql;
                String usernamecolumn;

                switch (selectedRole) {
                    case "students":
                        sql = "SELECT * FROM students WHERE email = ? AND password = ?";
                        
                        usernamecolumn = "name";
                        break;
                    case "instructors":
                        sql = "SELECT * FROM instructors WHERE email = ? AND password = ?";
                        usernamecolumn = "name";
                        break;
                    case "admin":
                        sql = "SELECT * FROM admin WHERE email = ? AND password = ?";
                        usernamecolumn = "name";
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Invalid role");
                        return;
                }

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, email);
                    statement.setString(2, new String(password));
                    
                    ResultSet resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                    	String username = resultSet.getString(usernamecolumn);
                        JOptionPane.showMessageDialog(null, "Login successful");
                        
                        if ("students".equalsIgnoreCase(selectedRole)) {
                        	 String enrolledModule = resultSet.getString("module_name");
                        	 String enrolledCourse = resultSet.getString("course_field");
                        	 int level = resultSet.getInt("level");
                        	 int id = resultSet.getInt("student_id");
                             if (enrolledModule == null || enrolledModule.isEmpty()) {
                                 // If not enrolled, create an instance of student_frame
                                 student_frame studentFrame = new student_frame(username,enrolledModule,enrolledCourse,level,id);
                                 studentFrame.setVisible(true);
                                 studentFrame.showEnrollmentInterface(email,enrolledModule);  // Call the method to show enrollment interface
                                 disposeFrame();
                             } else {
                                 // If already enrolled, proceed to the student frame
                                 student_frame studentFrame = new student_frame(username,enrolledModule,enrolledCourse,level,id);
                                 studentFrame.setVisible(true);
                                 disposeFrame();
                             }
                        } else if ("instructors".equalsIgnoreCase(selectedRole)) {
                        	String assignedModule = resultSet.getString("modules_name");
                        	assigned(email,assignedModule);
                            instructor_frame instructFrame = new instructor_frame(username,assignedModule);
                            instructFrame.setVisible(true);
                            disposeFrame();
                        } else if ("admin".equalsIgnoreCase(selectedRole)) {
                            admin_frame adminFrame = new admin_frame(username,email);
                            adminFrame.setVisible(true);
                            disposeFrame();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid username or password");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error during login");
        }
    }
private static void assigned(String email,String assignedModule) {
	if (assignedModule != null) {
		 JOptionPane.showMessageDialog(null,  "You have been assigned to the module: " + assignedModule);
	}else {
		JOptionPane.showMessageDialog(null,  "You have not been assigned to the module: ");
	}
}
   

	public static void registerStudent(String name, String email, String password, int level) {
        if(isValidEmail(email)&& !isEmailRegistered("students",email)) {
		registerUser("students", name, email, password, level);
        }else {
        	JOptionPane.showMessageDialog(null,"Invalid email or email is already registered");
        }
    }

    public static void registerTeacher(String name, String email, String password) {
    	if(isValidEmail(email)&& !isEmailRegistered("instructors",email)) {
    	registerUser("instructors", name, email, password, null);
    	}else {
    		JOptionPane.showMessageDialog(null,"Invalid email or email is already registered");
    	}
    }

    private static void registerUser(String tableName, String name, String email, String password,
            Object roleSpecificValue) {
         
        try (Connection connection = database.getConnection()) {
            if (connection != null) {
                String sql;
                
               
                if ("students".equals(tableName)) {
                    sql = "INSERT INTO students (name, email, password, level) VALUES (?, ?, ?, ?)";
                } else if ("instructors".equals(tableName)) {
                    sql = "INSERT INTO instructors (name, email, password) VALUES (?, ?, ?)";
                } else {
                    return; // Handle other roles if needed
                }

                try (PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                    statement.setString(1, name);
                    statement.setString(2, email);
                    statement.setString(3, password);
                    if ("students".equals(tableName)) {
                        statement.setInt(4, (int) roleSpecificValue);
                    }

                    int affectedRows = statement.executeUpdate();

                    if (affectedRows > 0) {
                        JOptionPane.showMessageDialog(null, "User registered successfully.");

                        if ("students".equals(tableName)) {
                            // Retrieve enrolledModule for the student
                            String enrolledModule = getEnrolledModuleForStudent(statement);
                            int level = getLevelForStudent(name);

                            student_frame studentFrame = new student_frame(name, enrolledModule,null,level,0);
                            studentFrame.setVisible(true);
                            studentFrame.showEnrollmentInterface(email, enrolledModule);

                            

                            disposeFrame();
                        } else if ("instructors".equals(tableName)) {
                            JOptionPane.showMessageDialog(null, "You have not been assigned any module yet");

                            // Open instructor_frame if needed
                            instructor_frame instructFrame = new instructor_frame(name, null);
                            instructFrame.setVisible(true);
                            disposeFrame();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "User registration failed.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error during user registration");
        }
    }

    private static String getEnrolledModuleForStudent(PreparedStatement statement) {
        String enrolledModule = null;
        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                int studentId = generatedKeys.getInt(1);
                String sql = "SELECT module_name FROM students WHERE student_id = ?";

                try (PreparedStatement enrollmentStatement = statement.getConnection().prepareStatement(sql)) {
                    enrollmentStatement.setInt(1, studentId);
                    ResultSet resultSet = enrollmentStatement.executeQuery();
                    if (resultSet.next()) {
                        enrolledModule = resultSet.getString("module_name");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error getting enrolled module for the student");
        }
        return enrolledModule;
    }

    private static boolean isValidEmail(String email) {
    	String emailPattern = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
    	return email.matches(emailPattern);
    }
    private static boolean isEmailRegistered(String tableName, String email) {
        try (Connection connection = database.getConnection()) {
            if (connection != null) {
                String sql = "SELECT * FROM " + tableName + " WHERE email = ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, email);
                    ResultSet resultSet = statement.executeQuery();
                    return resultSet.next(); // Returns true if email is already registered
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error checking email registration");
        }
        return false;
    }
    
    private static int getLevelForStudent(String studentName) {
        int level = 0; // Default value or appropriate default in your application

        String sql = "SELECT level FROM students WHERE name = ?";

        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, studentName);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                level = resultSet.getInt("level");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exception appropriately in your application
        }

        return level;
    }
}
    

