package system;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.PanelUI;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPasswordField;
import javax.swing.border.LineBorder;
import javax.swing.JSeparator;
import javax.swing.border.MatteBorder;

public class admin_frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final String username = "Default";
	private static final String email = "Default";
	private CardLayout cardlayout;
	private JTextField name_instructor;
	private JTextField instructor_id;
	 private JTable instructorTable; // Class-level variable
	    private DefaultTableModel instructorTableModel;
	    private JTable moduleTable;
	    private DefaultTableModel moduleTableModel;
	    private JTextField level_field;
	    private JTextField module_Field;
	    private JTextField id_field;
	    private JTextField name_field;
	    private DefaultTableModel studentTableModel;
	    private JTable studentTable;
	    private JPasswordField current_password;
	    private JPasswordField new_password;
	    private JPasswordField confirm_password;
	    private JTextField stdid_textField;
	    private JTextField stdname_textField;
	    private JTable table;
	    private JTable resultTable;
	    private DefaultTableModel resultTableModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					admin_frame frame = new admin_frame(username,email);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public admin_frame(String username,String email) throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setAutoRequestFocus(false);
		setBounds(100, 100, 922, 637);
		getContentPane();
		getContentPane().setLayout(null);
		
		JPanel dashboard = new JPanel();
		dashboard.setBackground(new Color(123, 104, 238));
		dashboard.setBounds(5, 0, 245, 600);
		getContentPane().add(dashboard);
		dashboard.setLayout(null);
		//cardlayout mainpanel
		JPanel mainpanel = new JPanel();
		mainpanel.setBounds(250, 0, 658, 600);
		getContentPane().add(mainpanel);
		cardlayout = new CardLayout();  // Initialize cardLayout
	    mainpanel.setLayout(cardlayout);
		
		JButton Home_btn = new JButton("Home");
		Home_btn.setForeground(new Color(0, 0, 0));
		Home_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(mainpanel, "home_panel");
			}
		});
		Home_btn.setFont(new Font("Tahoma", Font.PLAIN, 24));
		Home_btn.setBounds(10, 97, 225, 64);
		dashboard.add(Home_btn);
		
		JButton instruct_btn = new JButton("Instructors");
		instruct_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(mainpanel,"instruct_panel");
				
			}
		});
		instruct_btn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		instruct_btn.setBounds(10, 183, 225, 64);
		dashboard.add(instruct_btn);
		
		JButton modules_btn = new JButton("Modules");
		modules_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(mainpanel, "module_panel");
			}
		});
		modules_btn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		modules_btn.setBounds(10, 262, 225, 64);
		dashboard.add(modules_btn);
		
		JButton students_btn = new JButton("Students");
		students_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(mainpanel, "student_panel");
			}
		});
		students_btn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		students_btn.setBounds(10, 347, 225, 64);
		dashboard.add(students_btn);
		
		JButton settings_btn = new JButton("Settings");
		settings_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(mainpanel, "settings_panel");
			}
		});
		settings_btn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		settings_btn.setBounds(10, 504, 225, 64);
		dashboard.add(settings_btn);
		
		JButton result_btn = new JButton("Results");
		result_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(mainpanel,"result_panel");			}
		});
		result_btn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		result_btn.setBounds(10, 430, 225, 64);
		dashboard.add(result_btn);
		
		
		
		JPanel home_panel = new JPanel();
		home_panel.setBackground(new Color(224, 255, 255));
		home_panel.setBounds(255, 0, 650, 650);
		mainpanel.add(home_panel,"home_panel");
		home_panel.setLayout(null);
		
		JLabel welcome_lbl = new JLabel("       Welcome MR.<dynamic>");
		welcome_lbl.setBorder(new MatteBorder(4, 0, 0, 9, (Color) new Color(123, 104, 238)));
		welcome_lbl.setFont(new Font("Tahoma", Font.PLAIN, 26));
		welcome_lbl.setBounds(36, 80, 359, 83);
		home_panel.add(welcome_lbl);
		
		JButton logout_btn = new JButton (new ImageIcon(getClass().getResource("/icons8-logout-100 (2).png")));
		logout_btn.setToolTipText("Log Out");
		logout_btn.setContentAreaFilled(false);
		logout_btn.setBorderPainted(false);
		logout_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logout();
			}
		});
		logout_btn.setFont(new Font("Tahoma", Font.PLAIN, 25));
		logout_btn.setBounds(500, 10, 133, 124);
		home_panel.add(logout_btn);
		 
		JPanel activity_panel = new ActivityPanel();  // Create an instance of your ActivityPanel class
		activity_panel.setBorder(new LineBorder(new Color(100, 149, 237), 7));
		activity_panel.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        JScrollPane scrollPane = new JScrollPane(activity_panel);
        scrollPane.setBounds(36, 223, 580, 263);
        home_panel.add(scrollPane);

        DatabaseEventListener databaseEventListener = new DatabaseEventListener((ActivityPanel) activity_panel);
        databaseEventListener.start();
		
		JLabel activity_lbl = new JLabel("Current Activity");
		activity_lbl.setAlignmentX(0.5f);
		activity_lbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		activity_lbl.setBounds(49, 173, 160, 40);
		home_panel.add(activity_lbl);
		
		
		JPanel instruct_panel = new JPanel();
		instruct_panel.setBounds(255, 0, 650, 650);
		mainpanel.add(instruct_panel,"instruct_panel");
		instruct_panel.setLayout(null);
		instruct_panel.setBackground(new Color(224, 255, 255));
		
		name_instructor = new JTextField();
		name_instructor.setFont(new Font("Tahoma", Font.PLAIN, 20));
		name_instructor.setBounds(47, 58, 218, 41);
		instruct_panel.add(name_instructor);
		name_instructor.setColumns(10);
		
		instructor_id = new JTextField();
		instructor_id.setFont(new Font("Tahoma", Font.PLAIN, 20));
		instructor_id.setColumns(10);
		instructor_id.setBounds(47, 167, 182, 41);
		instruct_panel.add(instructor_id);
		
		JLabel instructorname = new JLabel("Teacher Name :\r\n");
		instructorname.setForeground(new Color(0, 0, 0));
		instructorname.setFont(new Font("Tahoma", Font.BOLD, 20));
		instructorname.setBounds(57, 10, 182, 38);
		instruct_panel.add(instructorname);
		
		JLabel lblInstructorId = new JLabel("Instructor id");
		lblInstructorId.setForeground(new Color(0, 0, 0));
		lblInstructorId.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblInstructorId.setBounds(57, 109, 182, 38);
		instruct_panel.add(lblInstructorId);
		
		JButton btnNewButton_add = new JButton("Add");
		btnNewButton_add.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Create dialog box components
		        JTextField idField = new JTextField(5);
		        JTextField nameField = new JTextField(20);
		        JTextField emailField = new JTextField(20);
		        JPasswordField passwordField = new JPasswordField(20);
		        JTextField moduleField = new JTextField(20);

		        // Create panel and add components
		        JPanel panel = new JPanel(new GridLayout(5, 2));
		        panel.add(new JLabel("ID:"));
		        panel.add(idField);
		        panel.add(new JLabel("Name:"));
		        panel.add(nameField);
		        panel.add(new JLabel("Email:"));
		        panel.add(emailField);
		        panel.add(new JLabel("Password:"));
		        panel.add(passwordField);
		        panel.add(new JLabel("Module:"));
		        panel.add(moduleField);

		        // Show dialog box and wait for user input
		        int result = JOptionPane.showConfirmDialog(null, panel, "Add Instructor", JOptionPane.OK_CANCEL_OPTION);
		        if (result == JOptionPane.OK_OPTION) {
		            try {
		                // Retrieve input values
		                int id = Integer.parseInt(idField.getText());
		                String name = nameField.getText();
		                String email = emailField.getText();
		                String password = new String(passwordField.getPassword());
		                String module = moduleField.getText();

		                // Check if the module exists
		                Module moduleObj = new Module();
		                if (!moduleObj.moduleExists(module)) {
		                    JOptionPane.showMessageDialog(null, "The module does not exist.");
		                    return;
		                }

		                // Add the instructor
		                Instructor instructor = new Instructor();
		                instructor.addInstructor(id, name, email, password, module);
		                instructor.instructor_list(instructorTable);

		                // Clear the text fields
		                name_instructor.setText("");
		                instructor_id.setText("");
		            } catch (NumberFormatException ex) {
		                JOptionPane.showMessageDialog(null, "Please enter a valid ID.");
		            } catch (Exception ex) {
		                ex.printStackTrace();
		                JOptionPane.showMessageDialog(null, "An error occurred while adding the instructor.");
		            }
		        }
		    }
		});
		btnNewButton_add.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_add.setBounds(512, 168, 115, 41);
		instruct_panel.add(btnNewButton_add);

		
		JButton btnNewButton_search = new JButton("Search");
		btnNewButton_search.setBorder(new MatteBorder(0, 0, 4, 7, (Color) new Color(0, 0, 128)));
		btnNewButton_search.setContentAreaFilled(false);
		btnNewButton_search.setToolTipText("search Here");
		btnNewButton_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String name = name_instructor.getText().trim();
			        String idText = instructor_id.getText().trim();

			        if (!name.isEmpty() || !idText.isEmpty()) {
			            // At least one field is provided, perform the search
			            int id = 0;  // Default value for ID if not provided

			            // Check if ID is provided and parse it
			            if (!idText.isEmpty()) {
			                try {
			                    id = Integer.parseInt(idText);
			                } catch (NumberFormatException ex) {
			                    // Handle invalid number format if needed
			                    ex.printStackTrace();
			                    // You might want to show a message to the user
			                    return;
			                }
			            }

			            // Call a method to perform the search and highlight the row
			            highlightRow(instructorTable, id, name);
			        } else {
			            // Both fields are empty, show a message to the user
			            JOptionPane.showMessageDialog(null, "Enter either ID or name to search.");
			        }
			       
			       
		           
			}
			
			});
		btnNewButton_search.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_search.setBounds(289, 108, 115, 47);
		instruct_panel.add(btnNewButton_search);
		
		JButton btnNewButton_remove = new JButton("Remove");
		btnNewButton_remove.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            // Get the ID
		            int id = -1;
		            String idText = instructor_id.getText().trim();
		            if (!idText.isEmpty()) {
		                id = Integer.parseInt(idText);
		            }

		            // Get the name
		            String name = name_instructor.getText().trim();

		            // Validate input
		            if (id == -1 && name.isEmpty()) {
		                JOptionPane.showMessageDialog(null, "Please enter either a valid ID or select a row.");
		                return;
		            }

		            Instructor instructor = new Instructor();

		            // Check if the row is selected before attempting to remove
		            int selectedRow = instructorTable.getSelectedRow();
		            if (selectedRow != -1) {
		                // Get the ID and name from the selected row
		                id = (int) instructorTable.getValueAt(selectedRow, 0);
		                name = (String) instructorTable.getValueAt(selectedRow, 1);
		            }

		            // Remove the instructor
		            instructor.deleteInstructor(id, name);

		            // Update table
		            instructor.instructor_list(instructorTable);

		            // Clear fields
		            name_instructor.setText("");
		            instructor_id.setText("");

		            // Clear table selection
		            instructorTable.clearSelection();

		            // Show confirmation message
		            JOptionPane.showMessageDialog(null, "Instructor removed successfully.");
		        } catch (NumberFormatException ex) {
		            // Handle invalid ID format
		            JOptionPane.showMessageDialog(null, "Please enter a valid ID.");
		        } catch (Exception ex) {
		            // Handle other exceptions
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, "An error occurred while removing the instructor.");
		        }
		    }
		});
		btnNewButton_remove.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_remove.setBounds(434, 224, 115, 41);
		instruct_panel.add(btnNewButton_remove);
		
		JButton btnNewButton_edit = new JButton("Edit");
		btnNewButton_edit.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = instructorTable.getSelectedRow();
		        if (selectedRow != -1) {
		            // Get data from selected row
		            int id = (int) instructorTable.getValueAt(selectedRow, 0);
		            String name = (String) instructorTable.getValueAt(selectedRow, 1);
		            String email = (String) instructorTable.getValueAt(selectedRow, 2);
		            String module = (String) instructorTable.getValueAt(selectedRow, 3);

		            // Show a dialog for editing
		            showEditDialog(id, name, email, module);
		        }
		    }
		});
		btnNewButton_edit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_edit.setBounds(365, 168, 115, 41);
		instruct_panel.add(btnNewButton_edit);
		
		// Create a DefaultTableModel with no initial data
		instructorTableModel = new DefaultTableModel(
                new Object[][] {},
                new String[] { "instructor_id", "name", "email","Module Assigned" }
        );
        instructorTable = new JTable(instructorTableModel);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(36, 275, 594, 263);
        instruct_panel.add(scrollPane_1);

        // Set the model to the JTable
        scrollPane_1.setViewportView(instructorTable);
        instructorTable.setFont(new Font("Tahoma", Font.PLAIN, 16));
        instructorTable.setForeground(Color.RED);
        instructorTable.setRowHeight(30);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < instructorTable.getColumnCount(); i++) {
            instructorTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

		
		// Call the instructor_list method to populate the table
		Instructor instructor = new Instructor();
		instructor.instructor_list(instructorTable);
		
		JButton update_button = new JButton(new ImageIcon(getClass().getResource("/icons8-update-90.png")));
		update_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		update_button.setToolTipText("Update");
		update_button.setBorderPainted(false);
		update_button.setContentAreaFilled(false);
		update_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Instructor instructor = new Instructor();
				instructor.updateInstructor();
				instructor.instructor_list(instructorTable);
			}
		});
		update_button.setFont(new Font("Tahoma", Font.PLAIN, 18));
		update_button.setBounds(512, 15, 130, 84);
		instruct_panel.add(update_button);
		instructorTable.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 16));
		instructorTable.getTableHeader().setPreferredSize(new Dimension(instructorTable.getTableHeader().getWidth(), 30));

		// Create a JScrollPane and set the JTable to it
		
		
		JPanel module_panel = new JPanel();
		module_panel.setBounds(255, 0, 650, 650);
		mainpanel.add(module_panel,"module_panel");
		module_panel.setLayout(null);
		module_panel.setBackground(new Color(224, 255, 255));
		
		JLabel moduleinstructor_level = new JLabel("Level");
		moduleinstructor_level.setFont(new Font("Tahoma", Font.PLAIN, 20));
		moduleinstructor_level.setBounds(39, 28, 160, 43);
		module_panel.add(moduleinstructor_level);
		
		JLabel moduleinstructor_name = new JLabel("Module name");
		moduleinstructor_name.setFont(new Font("Tahoma", Font.PLAIN, 20));
		moduleinstructor_name.setBounds(39, 124, 160, 43);
		module_panel.add(moduleinstructor_name);
		
		level_field = new JTextField();
		level_field.setBounds(39, 76, 160, 43);
		module_panel.add(level_field);
		level_field.setColumns(10);
		
		module_Field = new JTextField();
		module_Field.setColumns(10);
		module_Field.setBounds(39, 163, 235, 43);
		module_panel.add(module_Field);
		
		JButton search_btn = new JButton("Search");
		search_btn.setBorder(new MatteBorder(0, 5, 0, 3, (Color) new Color(147, 112, 219)));
		search_btn.setToolTipText("Update");
		search_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String leveltxt = level_field.getText().trim();
				String module_name = module_Field.getText().trim();

		        if (!module_name.isEmpty() || !leveltxt .isEmpty()) {
		            // At least one field is provided, perform the search
		            int level = 0;  // Default value for ID if not provided

		            // Check if ID is provided and parse it
		            if (!leveltxt.isEmpty()) {
		                try {
		                    level = Integer.parseInt(leveltxt);
		                } catch (NumberFormatException ex) {
		                    // Handle invalid number format if needed
		                    ex.printStackTrace();
		                    // You might want to show a message to the user
		                    return;
		                }
		            }

		            // Call a method to perform the search and highlight the row
		            highlightedRow(moduleTable, level, module_name);
		        } else {
		            // Both fields are empty, show a message to the user
		            JOptionPane.showMessageDialog(null, "Enter either ID or name to search.");
		        }
		       
		       
	           
			}
		});
		search_btn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		search_btn.setBounds(278, 89, 138, 50);
		module_panel.add(search_btn);
		
		JButton add_btn = new JButton("Add");
		add_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  showAddDialog();
			}
		});
		add_btn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add_btn.setBounds(437, 226, 147, 50);
		module_panel.add(add_btn);
		
		JButton remove_btn = new JButton("DELETE");
		remove_btn.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String module_name = module_Field.getText().trim();
		        String levelText = level_field.getText().trim();

		        // Check if either name or level is entered
		        if (module_name.isEmpty() && levelText.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Please enter either a module name or a level.");
		            return;
		        }

		        int level = -1;
		        if (!levelText.isEmpty()) {
		            level = Integer.parseInt(levelText);
		        }

		        Module module = new Module();

		        // Check if the row is selected before attempting to remove
		        int selectedRow = moduleTable.getSelectedRow();
		        if (selectedRow != -1) {
		            // Get the level and name from the selected row
		            int selectedLevel = (int) moduleTable.getValueAt(selectedRow, 2);
		            String selectedModuleName = (String) moduleTable.getValueAt(selectedRow, 1);

		            // Check if the selected row matches the criteria (level and name)
		            if ((selectedLevel == level || level == -1) && (selectedModuleName.equalsIgnoreCase(module_name) || module_name.isEmpty())) {
		                // Remove the selected row
		                module.deleteModule(module_name, selectedLevel);
		                module.module_list(moduleTable);

		                // Clear the text fields
		                level_field.setText("");
		                module_Field.setText("");

		                // Clear the selection in the table
		                moduleTable.clearSelection();
		            } else {
		                // Show a message indicating that the selected row doesn't match the criteria
		                JOptionPane.showMessageDialog(null, "Selected row does not match the criteria.");
		            }
		        } else {
		            // Show a message indicating that no row is selected
		            JOptionPane.showMessageDialog(null, "No row selected. Please search and select a row to remove.");
		        }
		    }
		});
		remove_btn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		remove_btn.setBounds(341, 156, 147, 50);
		module_panel.add(remove_btn);

		JButton edit_btn = new JButton("EDIT");
		edit_btn.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = moduleTable.getSelectedRow();
		        if (selectedRow != -1) {
		            // Get data from selected row
		            int id = (int) moduleTable.getValueAt(selectedRow, 0);
		            String module_name = (String) moduleTable.getValueAt(selectedRow, 1);
		            int level = (int) moduleTable.getValueAt(selectedRow, 2);

		            // Show a dialog for editing with the selected row data
		            showeditDialog(id, module_name, level);
		        } else {
		            // Inform the user that no row is selected
		            JOptionPane.showMessageDialog(null, "Please select a row to edit.", "No Row Selected", JOptionPane.WARNING_MESSAGE);
		        }
		    }
		});
		edit_btn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		edit_btn.setBounds(509, 156, 128, 50);
		module_panel.add(edit_btn);
		
		
		
		
		moduleTableModel = new DefaultTableModel(
                new Object[][] {},
                new String[] { "module id", "Module Name", "Level" }
        );
        moduleTable = new JTable(moduleTableModel);

        JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(67, 322, 540, 237);
		module_panel.add(scrollPane_2);

        // Set the model to the JTable
        scrollPane_2.setViewportView(moduleTable);
        moduleTable.setFont(new Font("Tahoma", Font.PLAIN, 16));
        moduleTable.setForeground(Color.RED);
        moduleTable.setRowHeight(30);
        DefaultTableCellRenderer centerRenderer1 = new DefaultTableCellRenderer();
        centerRenderer1.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < moduleTable.getColumnCount(); i++) {
            moduleTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer1);
        }

		
		// Call the instructor_list method to populate the table
		Module module = new Module();
		module.module_list(moduleTable);
		
		JButton update_btn = new JButton(new ImageIcon(getClass().getResource("/icons8-update-90.png")));
		update_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		update_btn.setToolTipText("update");
		update_btn.setContentAreaFilled(false);
		update_btn.setBorderPainted(false);
		update_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Module module =new Module();
				module.updateModule();
				module.module_list(moduleTable);
				
			}
		});
		update_btn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		update_btn.setBounds(494, 30, 154, 77);
		module_panel.add(update_btn);
		moduleTable.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 16));
		moduleTable.getTableHeader().setPreferredSize(new Dimension(moduleTable.getTableHeader().getWidth(), 30));


		
		JPanel student_panel = new JPanel();
		student_panel.setBounds(255, 0, 650, 650);
		mainpanel.add(student_panel,"student_panel");
		student_panel.setLayout(null);
		student_panel.setBackground(new Color(224, 255, 255));
		
		JLabel studentlbl = new JLabel("Student ID");
		studentlbl.setFont(new Font("Tahoma", Font.BOLD, 20));
		studentlbl.setBounds(10, 20, 146, 36);
		student_panel.add(studentlbl);
		
		JLabel lblStudentName = new JLabel("Student Name");
		lblStudentName.setForeground(Color.BLACK);
		lblStudentName.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblStudentName.setBounds(10, 120, 146, 36);
		student_panel.add(lblStudentName);
		
		id_field = new JTextField();
		id_field.setFont(new Font("Tahoma", Font.PLAIN, 18));
		id_field.setBounds(10, 66, 146, 47);
		student_panel.add(id_field);
		id_field.setColumns(10);
		
		name_field = new JTextField();
		name_field.setFont(new Font("Tahoma", Font.PLAIN, 18));
		name_field.setColumns(10);
		name_field.setBounds(10, 166, 262, 47);
		student_panel.add(name_field);
		
		JButton update_btn3 = new JButton(new ImageIcon(getClass().getResource("/icons8-update-90.png")));
		update_btn3.setContentAreaFilled(false);
		update_btn3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		update_btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Student student = new Student();
				student.updateStudent();
				student.student_list(studentTable);
			}
		});
		update_btn3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		update_btn3.setBounds(502, 20, 157, 83);
		student_panel.add(update_btn3);
		
		JButton search_btn3 = new JButton("Search");
		search_btn3.setBorder(new MatteBorder(0, 8, 0, 5, (Color) new Color(123, 104, 238)));
		search_btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String idtxt = id_field.getText().trim();
				String name = name_field.getText().trim();

		        if (!name.isEmpty() || !idtxt .isEmpty()) {
		            // At least one field is provided, perform the search
		            int id = 0;  // Default value for ID if not provided

		            // Check if ID is provided and parse it
		            if (!idtxt.isEmpty()) {
		                try {
		                    id = Integer.parseInt(idtxt);
		                } catch (NumberFormatException ex) {
		                    // Handle invalid number format if needed
		                    ex.printStackTrace();
		                    // You might want to show a message to the user
		                    return;
		                }
		            }

		            // Call a method to perform the search and highlight the row
		            highlightstudent(studentTable, id, name);
		        } else {
		            // Both fields are empty, show a message to the user
		            JOptionPane.showMessageDialog(null, "Enter either ID or name to search.");
		        }
		       
		       
	           
			}

				
			
		});
		search_btn3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		search_btn3.setBounds(244, 80, 146, 49);
		student_panel.add(search_btn3);
		
		JButton add_btn3_1 = new JButton("Add");
		add_btn3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showAddStudentDialog();

				
			}
		});
		add_btn3_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add_btn3_1.setBounds(418, 248, 146, 49);
		student_panel.add(add_btn3_1);
		
		JButton remove_btn3_1_1 = new JButton("Remove");
		remove_btn3_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(id_field.getText());
		        String name = name_field.getText();
		        Student student = new Student();

		        // Highlight the row based on the provided ID and name
		        highlightstudent(studentTable, id, name);

		        // Check if the row is selected before attempting to remove
		        int selectedRow = studentTable.getSelectedRow();
		        if (selectedRow != -1) {
		            // Get the ID and name from the selected row
		            int selectedid = (int) studentTable.getValueAt(selectedRow, 0);
		            String selectedName = (String) studentTable.getValueAt(selectedRow, 1);
		            System.out.println("Selected level: "+selectedid);
		            System.out.println("Selected level: "+selectedName);
		            // Check if the selected row matches the criteria (ID and name)
		            if (selectedid == id) {
		                // Remove the selected row
		                student.deleteStudent(id,selectedName);
		                student.student_list(studentTable);
		                // Clear the text fields
		                id_field.setText("");
		                name_field.setText("");
		                System.out.print("deleted");
		            }
		            else if (selectedid == id && selectedName.equalsIgnoreCase(name)) {
		                // Remove the selected row
		                student.deleteStudent(id, name);
		                student.student_list(studentTable);
		                // Clear the text fields
		                id_field.setText("");
		                name_field.setText("");
		            } else {
		                // Show a message indicating that the selected row doesn't match the criteria
		                JOptionPane.showMessageDialog(null, "Selected row does not match the criteria.");
		            }
		        } else {
		            // Show a message indicating that no row is selected
		            JOptionPane.showMessageDialog(null, "No row selected. Please search and select a row to remove.");
		        }
		}

			

			
		});
		remove_btn3_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		remove_btn3_1_1.setBounds(322, 189, 146, 49);
		student_panel.add(remove_btn3_1_1);
		
		JButton edit_btn3 = new JButton("Edit");
		edit_btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = studentTable.getSelectedRow();
		        if (selectedRow != -1) {
		            // Get data from selected row
		        	 int level = (int) studentTable.getValueAt(selectedRow, 2);
		        	 int id = (int) studentTable.getValueAt(selectedRow, 0);// Use the correct column index
		             String name = (String) studentTable.getValueAt(selectedRow, 1);

		             System.out.println(id);		            
		           

		            // Show a dialog for editing
		             showeditStudent(level,name, id);    
		            id_field.setText("");
			        name_field.setText("");

				
		        }
			}
		});
		edit_btn3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		edit_btn3.setBounds(502, 189, 146, 49);
		student_panel.add(edit_btn3);
		
		
		
		
		
		studentTableModel = new DefaultTableModel(
                new Object[][] {},
                new String[] {"Student_id", "Name", "Level", "e-mail"}
        );
        studentTable = new JTable(studentTableModel);

        JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(30, 320, 585, 234);
		student_panel.add(scrollPane_3);

        // Set the model to the JTable
		scrollPane_3.setViewportView(studentTable);
        studentTable.setFont(new Font("Tahoma", Font.PLAIN, 16));
        studentTable.setForeground(Color.RED);
        studentTable.setRowHeight(30);
        DefaultTableCellRenderer centerRenderer11 = new DefaultTableCellRenderer();
        centerRenderer11.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < studentTable.getColumnCount(); i++) {
            studentTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer11);
        }

		
		// Call the instructor_list method to populate the table
		Student student = new Student();
		student.student_list(studentTable);
		
		
		JPanel result_panel = new JPanel();
		result_panel.setBounds(255, 0, 650, 650);
		mainpanel.add(result_panel,"result_panel");
		result_panel.setLayout(null);
		result_panel.setBackground(new Color(224, 255, 255));
		
		stdid_textField = new JTextField();
		stdid_textField.setBounds(57, 70, 192, 44);
		result_panel.add(stdid_textField);
		stdid_textField.setColumns(10);
		
		stdname_textField = new JTextField();
		stdname_textField.setColumns(10);
		stdname_textField.setBounds(401, 70, 228, 44);
		result_panel.add(stdname_textField);
		
		JLabel lblNewLabel_2 = new JLabel("Studuent ID :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(67, 33, 146, 27);
		result_panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Studuent Name :");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2_1.setBounds(399, 33, 177, 27);
		result_panel.add(lblNewLabel_2_1);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.setBorder(new MatteBorder(0, 7, 0, 7, (Color) new Color(123, 104, 238)));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(269, 138, 104, 44);
		btnNewButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Get the ID and name from the text fields
		        String idText = stdid_textField.getText();
		        String name = stdname_textField.getText();

		        // Check if the ID field is empty
		        if (idText.isEmpty()) {
		            // Handle empty ID field
		            JOptionPane.showMessageDialog(null, "Please enter a student ID.", "Error", JOptionPane.ERROR_MESSAGE);
		            return; // Exit the method
		        }

		        // Parse the ID
		        int id = Integer.parseInt(idText);

		        // Call the method to fetch results based on ID and name
		        Result rs = new Result();
		        rs.result_students(resultTable, name, null,id);
		    }
		});
		result_panel.add(btnNewButton);
		
		
		

        resultTableModel = new DefaultTableModel(
                new Object[][] {},
                new String[] { "Subjects", "Marks", "Grade"
                    	 }
        );
        
        resultTable = new JTable(resultTableModel);
        JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(75, 260, 524, 209);
		result_panel.add(scrollPane_4);
		
        // Set the model to the JTable
        scrollPane_4.setViewportView(resultTable);
        
        resultTable.setFont(new Font("Tahoma", Font.PLAIN, 16));
        resultTable.setForeground(Color.RED);
        resultTable.setRowHeight(30);
        DefaultTableCellRenderer centerRenderer111 = new DefaultTableCellRenderer();
        centerRenderer111.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < resultTable.getColumnCount(); i++) {
            resultTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer1);
        }
        
		
		
		JPanel settings_panel = new JPanel();
		settings_panel.setBounds(255, 0, 650, 650);
		mainpanel.add(settings_panel,"settings_panel");
		settings_panel.setLayout(null);
		settings_panel.setBackground(new Color(224, 255, 255));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 230, 140));
		panel.setBounds(36, 10, 494, 490);
		settings_panel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hello, "+username);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setBounds(10, 23, 345, 60);
		panel.add(lblNewLabel);
		
		JLabel lblChangePassword = new JLabel("Change Password");
		lblChangePassword.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblChangePassword.setBounds(10, 72, 345, 60);
		panel.add(lblChangePassword);
		
		current_password = new JPasswordField();
		current_password.setFont(new Font("Tahoma", Font.PLAIN, 18));
		current_password.setBounds(10, 176, 297, 53);
		panel.add(current_password);
		
		JLabel lblNewLabel_1 = new JLabel("Current Password ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(10, 136, 261, 44);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("New Password");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(10, 239, 261, 44);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Confirm Password");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_1_1.setBounds(10, 344, 261, 44);
		panel.add(lblNewLabel_1_1_1);
		
		new_password = new JPasswordField();
		new_password.setFont(new Font("Tahoma", Font.PLAIN, 18));
		new_password.setBounds(10, 281, 297, 53);
		panel.add(new_password);
		
		confirm_password = new JPasswordField();
		confirm_password.setFont(new Font("Tahoma", Font.PLAIN, 18));
		confirm_password.setBounds(10, 388, 297, 53);
		panel.add(confirm_password);
		
		JButton password_btn = new JButton("Password");
		password_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				char[] currentPasswordChars = current_password.getPassword();
				String currentPassword = new String(currentPasswordChars);
				System.out.println("currentpassword"+currentPassword);
				char[] newPasswordChars = new_password.getPassword();
				String newPassword = new String(newPasswordChars);

				char[] confirmNewPasswordChars = confirm_password.getPassword();
				String confirmNewPassword = new String(confirmNewPasswordChars);
				
            if (validateCurrentPassword(email,currentPassword)&&validateChangePassword( newPassword, confirmNewPassword)) {
                // Update the password in the database
                changePassword(email, newPassword);
                JOptionPane.showMessageDialog(null, "Password changed successfully");
                
            } else {
                JOptionPane.showMessageDialog(null, "Invalid password or passwords do not match");
            }
            System.out.println("currentpassword"+currentPassword);
			System.out.println("password"+newPassword);
			System.out.println("password"+confirmNewPassword);
        }
				
			
		});
		password_btn.setFont(new Font("Tahoma", Font.PLAIN, 19));
		password_btn.setBounds(333, 401, 140, 40);
		panel.add(password_btn);
		
		
	
		

	}
	private void logout() {
		this.dispose();
		sytem1 frame = new sytem1();
		frame.show();
		 ActivityPanel activityPanel = new ActivityPanel();
		activityPanel.clearnotifications();
	}
	private void showEditDialog(int id, String name, String email, String module) {
	    JDialog editDialog = new JDialog(this, "Edit Instructor", true);
	    editDialog.getContentPane().setLayout(new BorderLayout());

	    // Create JTextFields for editing
	    JTextField editNameField = new JTextField(name);
	    JLabel stdlabel = new JLabel(email);
	    JTextField editModuleField = new JTextField(module);

	 // Create buttons for saving or canceling
	    JButton saveButton = new JButton("Save");
	    saveButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            // Get updated data from text fields
	            String editedName = editNameField.getText();
	            String editedModule = editModuleField.getText();

	            // Print out the edited name and module (for debugging)
	            System.out.println("Edited Name: " + editedName);
	            System.out.println("Edited Module: " + editedModule);

	            // Edit in database
	            Instructor instructor = new Instructor();
	            instructor.editInstructor(id, editedName, editedModule);

	            // Refresh the JTable
	            instructor.instructor_list(instructorTable);

	            // Close the dialog
	            editDialog.dispose();
	        }
	    });
	    JButton cancelButton = new JButton("Cancel");
	    cancelButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            // Close the dialog without saving changes
	            editDialog.dispose();
	        }
	    });

	    // Add components to the dialog
	    JPanel panel = new JPanel(new GridLayout(4, 2));
	    panel.add(new JLabel("Name:"));
	    panel.add(editNameField);
	    panel.add(new JLabel("Email:"));
	    panel.add(stdlabel);
	    panel.add(new JLabel("Module:"));
	    panel.add(editModuleField);
	    panel.add(saveButton);
	    panel.add(cancelButton);

	    editDialog.getContentPane().add(panel);
	    editDialog.pack();
	    editDialog.setLocationRelativeTo(null); // Center the dialog on the screen
	    editDialog.setVisible(true);
	}private void showeditDialog(int id, String module_name, int level) {
	    JDialog editDialog = new JDialog(this, "Edit Module", true);
	    editDialog.getContentPane().setLayout(new BorderLayout());

	    // Create JTextFields for editing
	    JTextField editNameField = new JTextField(module_name);
	    JTextField editLevelField = new JTextField(String.valueOf(level));
	    JTextField editIdField = new JTextField(String.valueOf(id));

	    // Create buttons for saving or canceling
	    JButton saveButton = new JButton("Save");
	    saveButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            // Get updated data from text fields
	            String editedName = editNameField.getText();
	            int editedLevel = Integer.parseInt(editLevelField.getText());
	            int editedId = Integer.parseInt(editIdField.getText());

	            // Edit in database
	            Module module = new Module();
	            module.editModule(editedId, editedName, editedLevel);

	            // Refresh the JTable
	            module.module_list(moduleTable);

	            // Close the dialog
	            editDialog.dispose();
	        }
	    });
	    JButton cancelButton = new JButton("Cancel");
	    cancelButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            // Close the dialog without saving changes
	            editDialog.dispose();
	        }
	    });

	    // Create panel for layout
	    JPanel editPanel = new JPanel(new GridLayout(0, 2));
	    editPanel.add(new JLabel("Module Name:"));
	    editPanel.add(editNameField);
	    editPanel.add(new JLabel("Level:"));
	    editPanel.add(editLevelField);
	    editPanel.add(new JLabel("Id:"));
	    editPanel.add(editIdField);
	    editPanel.add(saveButton);
	    editPanel.add(cancelButton);

	    // Add panel to dialog
	    editDialog.getContentPane().add(editPanel, BorderLayout.CENTER);

	    // Set dialog properties
	    editDialog.setSize(300, 200);
	    editDialog.setLocationRelativeTo(this);
	    editDialog.setVisible(true);
	}
	private void highlightRow(JTable instructorTable, int id, String name) {
	    instructorTable.clearSelection();
	    List<Integer> matchingRows = new ArrayList<>();

	    for (int row = 0; row < instructorTable.getRowCount(); row++) {
	        int tableId = (int) instructorTable.getValueAt(row, 0);
	        String tableName = ((String) instructorTable.getValueAt(row, 1)).trim();

	        // Check if the row matches the search criteria based on name and ID
	        if (name != null && !name.isEmpty() && name.equalsIgnoreCase(tableName) && id != 0 && tableId == id) {
	            instructorTable.getSelectionModel().addSelectionInterval(row, row);
	            instructorTable.setSelectionBackground(Color.YELLOW);
	            matchingRows.add(row);
	        } else if (name != null && !name.isEmpty() && name.equalsIgnoreCase(tableName) && id == 0) {
	            // If only name is provided, highlight all rows with matched name
	            instructorTable.getSelectionModel().addSelectionInterval(row, row);
	            instructorTable.setSelectionBackground(Color.YELLOW);
	            matchingRows.add(row);
	        } else if (name == null || name.isEmpty() && id != 0 && tableId == id) {
	            // If only ID is provided, highlight all rows with matched ID
	            instructorTable.getSelectionModel().addSelectionInterval(row, row);
	            instructorTable.setSelectionBackground(Color.YELLOW);
	            matchingRows.add(row);
	        }
	    }

	    // If no matching rows based on name and ID, show a message
	    if (matchingRows.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "No data matched for the provided criteria.");
	    }
	}

	private void highlightedRow(JTable moduleTable, int level, String module_name) {
	    moduleTable.clearSelection();
	    List<Integer> matchingRows = new ArrayList<>();

	    for (int row = 0; row < moduleTable.getRowCount(); row++) {
	        int tableLevel = (int) moduleTable.getValueAt(row, 2);
	        String tableName = ((String) moduleTable.getValueAt(row, 1)).trim();

	        // Check if the row matches the search criteria based on level
	        if (level != 0 && tableLevel == level) {
	            moduleTable.getSelectionModel().addSelectionInterval(row, row);
	            moduleTable.setSelectionBackground(Color.YELLOW);
	            matchingRows.add(row);
	        }else if (module_name != null && !module_name.isEmpty() && module_name.equalsIgnoreCase(tableName)) {
	            // If only name is provided, select rows based on name
	            moduleTable.getSelectionModel().addSelectionInterval(row, row);
	            moduleTable.setSelectionBackground(Color.YELLOW);
	            matchingRows.add(row);
	        }
	    }

	    // Check if there are matching rows based on level
	    if (!matchingRows.isEmpty()) {
	        // If there are matching rows based on level, check if a name is also provided
	        if (module_name != null && !module_name.isEmpty()) {
	            // If name is provided, narrow down the selection to the specific rows with the matching name
	            List<Integer> finalMatchingRows = new ArrayList<>();
	            for (int matchingRow : matchingRows) {
	                String tableName = ((String) moduleTable.getValueAt(matchingRow, 1)).trim();
	                if (module_name.equalsIgnoreCase(tableName)) {
	                    finalMatchingRows.add(matchingRow);
	                }
	            }

	            // If there are final matching rows based on both level and name, select specific rows
	            if (!finalMatchingRows.isEmpty()) {
	                moduleTable.getSelectionModel().clearSelection();
	                for (int finalMatchingRow : finalMatchingRows) {
	                    moduleTable.getSelectionModel().addSelectionInterval(finalMatchingRow, finalMatchingRow);
	                    moduleTable.setSelectionBackground(Color.YELLOW);
	                }
	                return; // Exit the loop after selecting the rows
	            } else {
	                // If no matching row with both level and name, show a message
	                JOptionPane.showMessageDialog(null, "No data matched for the provided Level and Name.");
	            }
	        }
	    } else {
	        // If no matching rows based on level, show a message
	        JOptionPane.showMessageDialog(null, "No data matched for the provided Level.");
	    }
	}
	private void highlightstudent(JTable studentTable, int id, String name) {
	    studentTable.clearSelection();
	    List<Integer> matchingRows = new ArrayList<>();

	    for (int row = 0; row < studentTable.getRowCount(); row++) {
	        int tableId = (int) studentTable.getValueAt(row, 0);
	        String tableName = ((String) studentTable.getValueAt(row, 1)).trim();

	        // Check if the row matches the search criteria based on name and ID
	        if (name != null && !name.isEmpty() && name.equalsIgnoreCase(tableName) && id != 0 && tableId == id) {
	            studentTable.getSelectionModel().addSelectionInterval(row, row);
	            studentTable.setSelectionBackground(Color.YELLOW);
	            matchingRows.add(row);
	        } else if (name != null && !name.isEmpty() && name.equalsIgnoreCase(tableName) && id == 0) {
	            // If only name is provided, highlight all rows with matched name
	            studentTable.getSelectionModel().addSelectionInterval(row, row);
	            studentTable.setSelectionBackground(Color.YELLOW);
	            matchingRows.add(row);
	        } else if (name == null || name.isEmpty() && id != 0 && tableId == id) {
	            // If only ID is provided, highlight all rows with matched ID
	            studentTable.getSelectionModel().addSelectionInterval(row, row);
	            studentTable.setSelectionBackground(Color.YELLOW);
	            matchingRows.add(row);
	        }
	    }

	    // If no matching rows based on name and ID, show a message
	    if (matchingRows.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "No data matched for the provided criteria.");
	    }
	}
	
	private void showAddStudentDialog() {
	    // Create a panel to hold the text fields
	    JPanel panel = new JPanel();
	    JTextField nameField = new JTextField(10);
	    JTextField emailField = new JTextField(10);
	    JPasswordField passwordField = new JPasswordField(10);
	    JTextField levelField = new JTextField(10);

	    // Add components to the panel
	    panel.add(new JLabel("Name:"));
	    panel.add(nameField);
	    panel.add(new JLabel("Email:"));
	    panel.add(emailField);
	    panel.add(new JLabel("Password:"));
	    panel.add(passwordField);
	    panel.add(new JLabel("Level:"));
	    panel.add(levelField);

	    // Show the dialog box
	    int result = JOptionPane.showConfirmDialog(null, panel, "Enter Student Details",
	            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

	    // Check if the user clicked "OK"
	    if (result == JOptionPane.OK_OPTION) {
	        // Retrieve values from text fields
	        String name = nameField.getText();
	        String email = emailField.getText();
	        char[] passwordChars = passwordField.getPassword();
	        String password = new String(passwordChars); // Convert char array to String
	        int level = Integer.parseInt(levelField.getText()); // Assuming level_field is declared somewhere
	        // You may want to add validation for the level and handle potential NumberFormatException

	        // Your existing code to add a student
	        Student student = new Student();
	        student.addstudent(name, email, level, password);
	        student.student_list(studentTable);

	        // Clear text fields
	        nameField.setText("");
	        emailField.setText("");
	        passwordField.setText("");
	        levelField.setText("");
	    }
	}
	 
	 private void showeditStudent(int level, String name,int id) {
		 JDialog editDialog = new JDialog(this, "Edit Student", true);
		    editDialog.getContentPane().setLayout(new BorderLayout());

		    // Create JTextFields for editing
		    JTextField editNameField = new JTextField(name);
		    JTextField editLevelField = new JTextField(String.valueOf(level));
		   

		    

		    // Create buttons for saving or canceling
		    JButton saveButton = new JButton("Save");
		    saveButton.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		            // Get updated data from text fields
		            String editedName = editNameField.getText();
		            int editedlevel =  Integer.parseInt(editLevelField.getText());
		            

		            // Edit in database
		            Student student = new Student();
		            student.editStudent(id,editedName,  editedlevel);

		            // Refresh the JTable
		            student.student_list(studentTable);

		            // Close the dialog
		            editDialog.dispose();
		        }
	
		    });
		    JButton cancelButton = new JButton("Cancel");
		    cancelButton.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		            // Close the dialog without saving changes
		            editDialog.dispose();
		        }
		    });
		    JPanel editPanel = new JPanel(new GridLayout(0, 2));
		    editPanel.add(new JLabel("Student Name:"));
		    editPanel.add(editNameField);
		    
		    editPanel.add(new JLabel("Level:"));
		    editPanel.add(editLevelField);
		   
		    
		    editPanel.add(saveButton);
		    editPanel.add(cancelButton);

		    editDialog.getContentPane().add(editPanel, BorderLayout.CENTER);

		    // Set dialog properties
		    editDialog.setSize(300, 200);
		    editDialog.setLocationRelativeTo(this);
		    editDialog.setVisible(true);
		    
		 // Method to perform the search and highlight the row
		   
	
	}
	 private boolean validateCurrentPassword(String email,String enteredPassword) {
	        try (Connection connection = database.getConnection()) {
	            String sql = "SELECT password FROM admin WHERE email = ?";
	            try (PreparedStatement statement = connection.prepareStatement(sql)) {
	                statement.setString(1, email);
	                ResultSet resultSet = statement.executeQuery();

	                if (resultSet.next()) {
	                    String correctCurrentPassword = resultSet.getString("password");
	                    System.out.println("correct"+correctCurrentPassword);
	                    return enteredPassword.equals(correctCurrentPassword);
	                }
	                
	                System.out.println("enter"+enteredPassword);
	                
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return false;
	    
	 }
	 private boolean validateChangePassword( String newPassword, String confirmNewPassword) {
		    // Example validation logic:
		    // You can add more sophisticated validation rules based on your requirements

		    System.out.println("Entered validateChangePassword");

		    // Check if the new password is at least 8 characters long
		    if (newPassword.length() < 8) {
		        JOptionPane.showMessageDialog(null, "New password must be at least 8 characters long");
		        System.out.println("Validation failed: New password must be at least 8 characters long");
		        return false;
		    }

		    // Check if the new password contains at least 1 special character
		    if (!newPassword.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")) {
		        JOptionPane.showMessageDialog(null, "New password must contain at least 1 special character");
		        System.out.println("Validation failed: New password must contain at least 1 special character");
		        return false;
		    }

		    // Check if the new password and confirmation match
		    if (!newPassword.equals(confirmNewPassword)) {
		        JOptionPane.showMessageDialog(null, "New password and confirmation do not match");
		        System.out.println("Validation failed: New password and confirmation do not match");
		        return false;
		    }

		    System.out.println("Validation passed");
		    return true;
		}
	 private void changePassword(String email, String newPassword) {
	        // Example method to update the password in the database
	        try (Connection connection = database.getConnection()) {
	            String sql = "UPDATE admin SET password = ? WHERE email = ?";
	            try (PreparedStatement statement = connection.prepareStatement(sql)) {
	                statement.setString(1, newPassword);
	                statement.setString(2, email);
	                statement.executeUpdate();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Error changing password");
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
	 private void showAddDialog() {
		    JDialog addDialog = new JDialog(this, "Add Module", true);
		    addDialog.getContentPane().setLayout(new BorderLayout());

		    // Create JTextFields for input
		    JTextField moduleNameField = new JTextField();
		    JTextField levelField = new JTextField();

		    // Create buttons for adding or canceling
		    JButton addButton = new JButton("Add");
		    addButton.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		            // Get the entered data
		            String moduleName = moduleNameField.getText();
		            int level = Integer.parseInt(levelField.getText());

		            // Add module to database
		            Module module = new Module();
		            module.addModule(moduleName, level);

		            // Refresh the module table
		            module.module_list(moduleTable);

		            // Close the dialog
		            addDialog.dispose();
		        }
		    });
		    JButton cancelButton = new JButton("Cancel");
		    cancelButton.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		            // Close the dialog without adding module
		            addDialog.dispose();
		        }
		    });

		    // Create panel for layout
		    JPanel addPanel = new JPanel(new GridLayout(0, 2));
		    addPanel.add(new JLabel("Module Name:"));
		    addPanel.add(moduleNameField);
		    addPanel.add(new JLabel("Level:"));
		    addPanel.add(levelField);
		    addPanel.add(addButton);
		    addPanel.add(cancelButton);

		    // Add panel to dialog
		    addDialog.getContentPane().add(addPanel, BorderLayout.CENTER);

		    // Set dialog properties
		    addDialog.setSize(300, 150);
		    addDialog.setLocationRelativeTo(this);
		    addDialog.setVisible(true);
		}
}

