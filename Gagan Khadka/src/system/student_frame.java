package system;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;
import javax.swing.JSeparator;

public class student_frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static String username = "Default";
	private static int level = 0;
	private static int id = 0;
	private static String enrolledModule = "Default";
	private static String enrolledCourse = "Default";
	 private JPanel home_panel; 
	 private JTable table;
	 private DefaultTableModel coursesTableModel;
	    private JTable courseTable;
	   private JLabel lblNewLabel_1;
	   private JLabel lblNewLabel_1_1;
	   private DefaultTableModel teacherTableModel;
	    private JTable teacherTable;
	    private JTable table_1;
	    private DefaultTableModel resultTableModel;
	    private DefaultTableModel resultmodulesTableModel;
	    private JTable resultmodulesTable;
	    private JTable resultTable;
	 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					student_frame frame = new student_frame(username,enrolledModule,enrolledCourse,level,id);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public student_frame(String username,String enrolledModule,String enrolledCourse,int level,int id) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 849, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(123, 104, 238));
		panel.setBounds(0, 0, 197, 523);
		contentPane.add(panel);
		panel.setLayout(null);
		JPanel mainpanel = new JPanel();
		mainpanel.setBounds(195, 0, 640, 523);
		contentPane.add(mainpanel);
		CardLayout cardlayout = new CardLayout();
		mainpanel.setLayout(cardlayout);
		
		
		JButton home_btn = new JButton("Home");
		home_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				cardlayout.show(mainpanel,"home_panel");
				
			}
		});
		home_btn.setFont(new Font("Verdana", Font.PLAIN, 20));
		home_btn.setBounds(10, 130, 177, 46);
		panel.add(home_btn);
		
		JButton instructor_btn = new JButton("Instructor");
		instructor_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(mainpanel,"instruct_panel");
			}
		});
		instructor_btn.setFont(new Font("Verdana", Font.PLAIN, 20));
		instructor_btn.setBounds(10, 196, 177, 46);
		panel.add(instructor_btn);
		
		JButton result_btn = new JButton("Result");
		result_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(mainpanel, "result_panel");
			}
		});
		result_btn.setFont(new Font("Verdana", Font.PLAIN, 20));
		result_btn.setBounds(10, 267, 177, 46);
		panel.add(result_btn);
		
		JButton Logout = new JButton("Logout");
		Logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				sytem1 frame = new sytem1();
				frame.setVisible(true);
			}
		});
		Logout.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 22));
		Logout.setBounds(30, 347, 137, 46);
		panel.add(Logout);
		
		JLabel lblNewLabel_3 = new JLabel("Dashboard");
		lblNewLabel_3.setForeground(new Color(224, 255, 255));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_3.setBounds(30, 45, 137, 75);
		panel.add(lblNewLabel_3);
		
		
		home_panel = new JPanel();
		home_panel.setBorder(new LineBorder(new Color(32, 178, 170), 18));
		home_panel.setBackground(new Color(135, 206, 250));
		home_panel.setBounds(10, 10, 620, 513);
		mainpanel.add(home_panel,"home_panel");
		home_panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome MR."+username);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel.setBounds(50, 23, 294, 58);
		home_panel.add(lblNewLabel);
		
		
		coursesTableModel = new DefaultTableModel(
                new Object[][] {},
                new String[] { "Course_id", "Subjects", "Module","Level" }
        );
        courseTable = new JTable(coursesTableModel);
    	JScrollPane scrollPane = new JScrollPane();
    	scrollPane.setBorder(new LineBorder(SystemColor.inactiveCaptionBorder, 7));
    	scrollPane.setBackground(SystemColor.inactiveCaptionBorder);
		scrollPane.setBounds(36, 258, 574, 242);
		home_panel.add(scrollPane);
	
    
		
        // Set the model to the JTable
        scrollPane.setViewportView(courseTable);
        courseTable.setFont(new Font("Tahoma", Font.PLAIN, 16));
        courseTable.setForeground(Color.RED);
        courseTable.setRowHeight(30);
        DefaultTableCellRenderer centerRenderer1 = new DefaultTableCellRenderer();
        centerRenderer1.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < courseTable.getColumnCount(); i++) {
            courseTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer1);
        }

        Module module = new Module();
        module.courses(courseTable, enrolledModule);
        System.out.println("Enrolled Module: " + enrolledModule);

     // Call the courses method
     module.courses(courseTable, enrolledModule);
     
     lblNewLabel_1 = new JLabel("Your Course : "+enrolledCourse);
     lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
     lblNewLabel_1.setBounds(50, 124, 284, 31);
     home_panel.add(lblNewLabel_1);
     
     lblNewLabel_1_1 = new JLabel("Your Module : "+enrolledModule);
     lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
     lblNewLabel_1_1.setBounds(60, 194, 284, 31);
     home_panel.add(lblNewLabel_1_1);
     System.out.println("Courses method executed");
   
     
		JPanel instruct_panel = new JPanel();
		instruct_panel.setBorder(new LineBorder(new Color(102, 205, 170), 12));
		instruct_panel.setBackground(new Color(135, 206, 250));
		instruct_panel.setBounds(10, 10, 620, 513);
		mainpanel.add(instruct_panel,"instruct_panel");
		instruct_panel.setLayout(null);
		
		teacherTableModel = new DefaultTableModel(
                new Object[][] {},
                new String[] { "instructor_id", "Instructor_Name", "Module_name" }
        );
        teacherTable = new JTable(teacherTableModel);

        JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setBorder(new LineBorder(new Color(230, 230, 250), 11));
		scrollPane1.setBounds(29, 149, 582, 296);
		instruct_panel.add(scrollPane1);
		
        // Set the model to the JTable
        scrollPane1.setViewportView(teacherTable);
        teacherTable.setFont(new Font("Tahoma", Font.PLAIN, 16));
        teacherTable.setForeground(Color.RED);
        teacherTable.setRowHeight(30);
        DefaultTableCellRenderer centerRenderer11 = new DefaultTableCellRenderer();
        centerRenderer11.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < teacherTable.getColumnCount(); i++) {
            teacherTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer1);
        }

		
		// Call the instructor_list method to populate the table
		Instructor inst = new Instructor();
		inst.instruct_module(enrolledModule,teacherTable);
		
		JLabel lblNewLabel_4 = new JLabel("Instruction Panel");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_4.setBounds(27, 38, 292, 39);
		instruct_panel.add(lblNewLabel_4);
		
		JPanel result_panel = new JPanel();
		result_panel.setBackground(new Color(240, 255, 240));
		result_panel.setBounds(10, 10, 620, 513);
		mainpanel.add(result_panel,"result_panel");
		result_panel.setLayout(null);
		
		JLabel stdnamelbl = new JLabel(" ");
		stdnamelbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		stdnamelbl.setBounds(60, 160, 435, 50);
		result_panel.add(stdnamelbl);


		 JLabel lblNewLabel_2_1_1_1_1 = new JLabel("Student Name : ");
	        lblNewLabel_2_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
	        lblNewLabel_2_1_1_1_1.setBounds(38, 41, 146, 50);
	        result_panel.add(lblNewLabel_2_1_1_1_1);
	       
		JLabel lblNewLabel_2_1 = new JLabel("Student ID : ");
        lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_2_1.setBounds(405, 41, 146, 50);
        result_panel.add(lblNewLabel_2_1);
        
        JLabel lblNewLabel_2_1_1 = new JLabel("Course : ");
        lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_2_1_1.setBounds(103, 103, 81, 50);
        result_panel.add(lblNewLabel_2_1_1);
        
        JLabel lblNewLabel_2_1_1_1 = new JLabel("Level : ");
        lblNewLabel_2_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_2_1_1_1.setBounds(456, 103, 65, 50);
        result_panel.add(lblNewLabel_2_1_1_1);
        
        JLabel namelbl = new JLabel(username);
        namelbl.setFont(new Font("Tahoma", Font.PLAIN, 17));
        namelbl.setBounds(183, 41, 191, 50);
        result_panel.add(namelbl);
        
        JLabel idlbl = new JLabel(String.valueOf(id));
        idlbl.setFont(new Font("Tahoma", Font.PLAIN, 17));
        idlbl.setBounds(526, 41, 104, 50);
        result_panel.add(idlbl);
        
        JLabel levellbl = new JLabel(String.valueOf(level));
        levellbl.setFont(new Font("Tahoma", Font.PLAIN, 17));
        levellbl.setBounds(526, 103, 104, 50);
        result_panel.add(levellbl);
        
        JLabel lblNewLabel_5 = new JLabel("Principal");
        lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 22));
        lblNewLabel_5.setBounds(60, 457, 132, 31);
        result_panel.add(lblNewLabel_5);
        
        JLabel courselbl = new JLabel(enrolledCourse);
        courselbl.setFont(new Font("Tahoma", Font.PLAIN, 17));
        courselbl.setBounds(179, 108, 146, 42);
        result_panel.add(courselbl);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(38, 443, 114, 4);
        result_panel.add(separator);
        
        
        
        resultTableModel = new DefaultTableModel(
                new Object[][] {},
                new String[] { "Subjects", "Marks", "Grade"
                    	 }
        );
        resultTable = new JTable(resultTableModel);
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(70, 206, 477, 180);
        result_panel.add(scrollPane_1);
        
        
        // Set the model to the JTable
        scrollPane_1.setViewportView(resultTable);
        
        resultTable.setFont(new Font("Tahoma", Font.PLAIN, 16));
        resultTable.setForeground(Color.RED);
        resultTable.setRowHeight(30);
        DefaultTableCellRenderer centerRenderer111 = new DefaultTableCellRenderer();
        centerRenderer111.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < resultTable.getColumnCount(); i++) {
            resultTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer1);
        }
        Result rs= new Result();
        rs.result_students(resultTable, username, enrolledModule,null);
		
		// Call the instructor_list method to populate the table
		
	}
	public void showEnrollmentInterface(String email, String enrolledModule) {
	   
		JDialog dialog = new JDialog((JFrame)null, "Registration Form Dialog", true);
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dialog.setSize(600, 400);
        setLocationRelativeTo(dialog);// Set appropriate size

        JPanel home_panel = new JPanel();
        home_panel.setLayout(null);
        dialog.getContentPane().add(home_panel);
		
		// Set up Course Field ComboBox
        JComboBox<String> courseFieldComboBox = new JComboBox<>();
	    courseFieldComboBox.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 30));
	    courseFieldComboBox.setModel(new DefaultComboBoxModel<>(new String[] {"Courses"}));
	    courseFieldComboBox.setBounds(68, 135, 135, 40);
	    home_panel.add(courseFieldComboBox);
	    lblNewLabel_1.setVisible(false);
	    lblNewLabel_1_1.setVisible(false);
	    // Populate Course Field ComboBox
	    Module mod = new Module();
	    mod.populateCoursesComboBox(courseFieldComboBox);

	    // Label for Course Field
	    JLabel courseFieldLabel = new JLabel("Select Course Field: ");
	    courseFieldLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
	    courseFieldLabel.setBounds(62, 103, 165, 22);
	    home_panel.add(courseFieldLabel);

	    // Button for Enrolling in Course Field
	    JButton enrollInCourseFieldButton = new JButton("Enroll in Course Field");
	    enrollInCourseFieldButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
	    enrollInCourseFieldButton.setBounds(247, 139, 207, 40);
	    home_panel.add(enrollInCourseFieldButton);

	    // Label for Module
	    JLabel moduleLabel = new JLabel("Select Module: ");
	    moduleLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
	    moduleLabel.setBounds(68, 185, 125, 22);
	    home_panel.add(moduleLabel);

	    // Module ComboBox
	    JComboBox<String> moduleComboBox = new JComboBox<>();
	    moduleComboBox.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 30));
	    moduleComboBox.setModel(new DefaultComboBoxModel<>(new String[] {"Modules"}));
	    moduleComboBox.setBounds(68, 217, 144, 40);
	    home_panel.add(moduleComboBox);

	    // Button for Enrolling in Module
	    JButton enrollInModuleButton = new JButton("Enroll in Module");
	    enrollInModuleButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
	    enrollInModuleButton.setBounds(247, 218, 207, 39);
	    home_panel.add(enrollInModuleButton);
	    // Get student level
	    Student std = new Student();
	    int studentLevel = std.getStudentLevel(email);

	    if (studentLevel != -1) {
	        // ActionListener for Enrolling in Module
	        enrollInModuleButton.addActionListener(e -> {
	            String selectedModule = moduleComboBox.getSelectedItem().toString();
	            if (!"Modules".equals(selectedModule)) {
	                Student stud = new Student();
	                boolean enrollmentSuccessful = stud.enrollStudent(email, selectedModule, courseTable);

	                if (enrollmentSuccessful) {
	                    // Change button text to "Enrolled"
	                    enrollInModuleButton.setText("Enrolled");
	                    enrollInModuleButton.setVisible(false);
	                    moduleComboBox.setEnabled(false);
	                    Instructor inst = new Instructor();
	            		inst.instruct_module(selectedModule,teacherTable);
	            		
	                } else {
	                    JOptionPane.showMessageDialog(null, "Enrollment failed. Please try again.");
	                }
	            } else {
	                JOptionPane.showMessageDialog(null, "Please select a valid module to enroll.");
	            }
	        });

	        // ActionListener for Enrolling in Course Field
	        enrollInCourseFieldButton.addActionListener(e -> {
	            String selectedCourseField = courseFieldComboBox.getSelectedItem().toString();
	            if (!"Courses".equals(selectedCourseField)) {
	                // Enroll the selected course field to students table
	                Student student = new Student();
	                boolean enrollmentSuccessful = student.enrollCourseField(email, selectedCourseField);

	                if (enrollmentSuccessful) {
	                    // Show a message or perform any action upon successful enrollment
	                    JOptionPane.showMessageDialog(null, "Course Field enrolled successfully.");
	                    
	                    mod.populateModulesComboBox(moduleComboBox, studentLevel, selectedCourseField);
	                    enrollInCourseFieldButton.setEnabled(false);
	                    enrollInCourseFieldButton.setText("Enrolled");
	                    courseFieldComboBox.setEnabled(false);
	                    
	            		
	                } else {
	                    JOptionPane.showMessageDialog(null, "Enrollment in Course Field failed. Please try again.");
	                }
	            } else {
	                JOptionPane.showMessageDialog(null, "Please select a valid course field to enroll.");
	            }
	        });
	    
	        // ActionListener for Module ComboBox
	        moduleComboBox.addActionListener(e -> {
	            String selectedModule = moduleComboBox.getSelectedItem().toString();
	            if (!"Modules".equals(selectedModule)) {
	                // Courses for the selected module
	                Module module = new Module();
	                module.courses(courseTable, selectedModule);
	            }
	        });
	    } else {
	        JOptionPane.showMessageDialog(null, "Error getting student level. Please try again.");
	    }
	    dialog.setLocationRelativeTo(null);

        // Make the dialog visible
        dialog.setVisible(true);
	}	}



	
