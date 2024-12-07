package system;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class instructor_frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private CardLayout cardlayout;
	private static String username ="Default";
	private static String assignedModule ="Default";
	
	private DefaultTableModel teacherTableModel;
    private JTable teacherTable;
    
    private DefaultTableModel modulesTableModel;
    private JTable modulesTable;
    private DefaultTableModel studentmodulesTableModel;
    private JTable studentmodulesTable;
    private DefaultTableModel resultmodulesTableModel;
    private JTable resultmodulesTable;

    private JTable resultTable;

    private JTable table;
    private JTable table_1;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					instructor_frame frame = new instructor_frame(username,assignedModule);
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
	public instructor_frame(String username,String assignedModule) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 849, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(123, 104, 238));
		panel.setBounds(0, 0, 204, 523);
		contentPane.add(panel);
		panel.setLayout(null);
		JPanel mainpanel = new JPanel();
		mainpanel.setBounds(201, 0, 634, 523);
		contentPane.add(mainpanel);
		cardlayout = new CardLayout();  // Initialize cardLayout
	    mainpanel.setLayout(cardlayout);
		
		JButton home_btn = new JButton("Home");
		home_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(mainpanel, "home_panel");
			}
		});
		home_btn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		home_btn.setBounds(33, 91, 144, 61);
		panel.add(home_btn);
		
		JButton module_btn = new JButton("Module");
		module_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(mainpanel, "module_panel");
			}
		});
		module_btn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		module_btn.setBounds(33, 174, 144, 61);
		panel.add(module_btn);
		
		JButton student_btn = new JButton("Student");
		student_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(mainpanel, "student_panel");
			}
		});
		student_btn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		student_btn.setBounds(33, 255, 144, 61);
		panel.add(student_btn);
		
		JButton result_btn = new JButton("Result");
		result_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(mainpanel, "result_panel");
			}
		});
		result_btn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		result_btn.setBounds(33, 330, 144, 61);
		panel.add(result_btn);
		
		JButton result_btn_1 = new JButton("Log Out");
		result_btn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				sytem1 frame = new sytem1();
				frame.setVisible(true);
			}
		});
		result_btn_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		result_btn_1.setBounds(33, 420, 144, 61);
		panel.add(result_btn_1);
		
	    JPanel home_panel = new JPanel();
	    home_panel.setBackground(new Color(240, 255, 255));
	    home_panel.setBounds(174, 0, 661, 523);
	    mainpanel.add(home_panel, "home_panel");
	    home_panel.setLayout(null);
	    
	    JLabel welcome_lbl = new JLabel("Welcome MR."+username);
		welcome_lbl.setFont(new Font("Tahoma", Font.PLAIN, 40));
		welcome_lbl.setBounds(58, 30, 500, 50);
		home_panel.add(welcome_lbl);
		JLabel welcome_lbl1;
		if (assignedModule != null) {
		    welcome_lbl1 = new JLabel("Your assigned Module: " + assignedModule);
		} else {
		    welcome_lbl1 = new JLabel("Contact the admin for module assignment.");
		}
		welcome_lbl1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		welcome_lbl1.setBounds(10, 70, 500, 50);
		home_panel.add(welcome_lbl1);
		
		
		
		teacherTableModel = new DefaultTableModel(
                new Object[][] {},
                new String[] { "instructor_id", "Instructor_Name", "Module_name" }
        );
        teacherTable = new JTable(teacherTableModel);

        JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(52, 206, 547, 232);
		home_panel.add(scrollPane);
		
        // Set the model to the JTable
        scrollPane.setViewportView(teacherTable);
        teacherTable.setFont(new Font("Tahoma", Font.PLAIN, 16));
        teacherTable.setForeground(Color.RED);
        teacherTable.setRowHeight(30);
        DefaultTableCellRenderer centerRenderer1 = new DefaultTableCellRenderer();
        centerRenderer1.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < teacherTable.getColumnCount(); i++) {
            teacherTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer1);
        }

		
		// Call the instructor_list method to populate the table
		Instructor inst = new Instructor();
		inst.instruct_module(assignedModule,teacherTable);

	    JPanel module_panel = new JPanel();
	    module_panel.setBackground(new Color(240, 255, 255));
	    module_panel.setBounds(174, 0, 661, 523);
	    mainpanel.add(module_panel, "module_panel");
	    module_panel.setLayout(null);
	    
	    JLabel module_lbl = new JLabel("Module : "+assignedModule);
	    module_lbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
	    module_lbl.setBounds(24, 86, 217, 70);
	    module_panel.add(module_lbl);
	    
	    
	    
	    modulesTableModel = new DefaultTableModel(
                new Object[][] {},
                new String[] { "module_name", "Subjects", "Level" }
        );
        modulesTable = new JTable(modulesTableModel);

        JScrollPane scrollPane_1 = new JScrollPane();
	    scrollPane_1.setBounds(24, 166, 568, 254);
	    module_panel.add(scrollPane_1);
	    
        // Set the model to the JTable
        scrollPane_1.setViewportView(modulesTable);
        modulesTable.setFont(new Font("Tahoma", Font.PLAIN, 16));
        modulesTable.setForeground(Color.RED);
        modulesTable.setRowHeight(30);
        DefaultTableCellRenderer centerRenderer11 = new DefaultTableCellRenderer();
        centerRenderer11.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < modulesTable.getColumnCount(); i++) {
            modulesTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer11);
        }
        Module module = new Module();
        module.course_list(modulesTable,assignedModule);
	    
	    JPanel student_panel = new JPanel();
	    student_panel.setBackground(new Color(240, 255, 255));
	    student_panel.setBounds(174, 0, 661, 523);
	    mainpanel.add(student_panel, "student_panel");
	    student_panel.setLayout(null);
	    
	    JLabel lblNewLabel = new JLabel("Students present in your Module");
	    lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
	    lblNewLabel.setBounds(27, 62, 420, 59);
	    student_panel.add(lblNewLabel);
	    
	    
	    studentmodulesTableModel = new DefaultTableModel(
                new Object[][] {},
                new String[] { "Students_id", "Students_name", "Students_email","Students_module","Students_level" }
        );
        studentmodulesTable = new JTable(studentmodulesTableModel);
        JScrollPane scrollPane_2 = new JScrollPane();
	    scrollPane_2.setBounds(26, 160, 577, 268);
	    student_panel.add(scrollPane_2);
	    
        
	    
        // Set the model to the JTable
        scrollPane_2.setViewportView(studentmodulesTable);
        studentmodulesTable.setFont(new Font("Tahoma", Font.PLAIN, 16));
        studentmodulesTable.setForeground(Color.RED);
        studentmodulesTable.setRowHeight(30);
        DefaultTableCellRenderer centerRenderer111 = new DefaultTableCellRenderer();
        centerRenderer111.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < studentmodulesTable.getColumnCount(); i++) {
        	studentmodulesTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer111);
        }
        Student students = new Student();
		students.student_module(assignedModule,studentmodulesTable);


	    JPanel result_panel = new JPanel();
	    result_panel.setBackground(new Color(245, 222, 179));
	    result_panel.setBounds(174, 0, 661, 523);
	    mainpanel.add(result_panel, "result_panel");
	    result_panel.setLayout(null);
	    
	    JLabel studentidlbl = new JLabel("Student id:");
	    studentidlbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
	    studentidlbl.setBounds(10, 10, 190, 55);
	    result_panel.add(studentidlbl);
	    
	    JLabel studentnamelbl = new JLabel("Student name: ");
	    studentnamelbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
	    studentnamelbl.setBounds(180, 10, 190, 55);
	    result_panel.add(studentnamelbl);
	    
	    JTextArea id_textarea = new JTextArea();
	    id_textarea.setFont(new Font("Monospaced", Font.PLAIN, 20));
	    id_textarea.setBounds(10, 59, 146, 47);
	    result_panel.add(id_textarea);
	    
	    JTextArea name_textarea = new JTextArea();
	    name_textarea.setFont(new Font("Monospaced", Font.PLAIN, 20));
	    name_textarea.setBounds(179, 59, 210, 47);
	    result_panel.add(name_textarea);
	   
	    JLabel lblSubject = new JLabel("Subject:");
        lblSubject.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblSubject.setBounds(412, 21, 190, 32);
        result_panel.add(lblSubject);
        
        JTextArea subjectarea = new JTextArea();
        subjectarea.setFont(new Font("Monospaced", Font.PLAIN, 20));
        subjectarea.setBounds(412, 59, 210, 47);
        result_panel.add(subjectarea);
        
	    JButton searchbtn = new JButton("Search");
	    searchbtn.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		 String name = name_textarea.getText().trim();
	    	        String idText = id_textarea.getText().trim();
	    	        String subject = subjectarea.getText().trim();

	    	        if (!name.isEmpty() || !idText.isEmpty() ||  !subject.isEmpty()) {
	    	            // At least one field is provided, perform the search
	    	            int id = 0;  // Default value for ID if not provided
	    	            int marks = 0;  // Default value for marks if not provided

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
	    	            highlightRow(resultmodulesTable, id, name, subject);
	    	        } else {
	    	            // All fields are empty, show a message to the user
	    	            JOptionPane.showMessageDialog(null, "Enter at least one field to search.");
	    	        }
	    	    }		
		});

	    searchbtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
	    searchbtn.setBounds(10, 128, 151, 55);
	    result_panel.add(searchbtn);
	    
	    JButton editbtn = new JButton("Edit");
	    editbtn.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		int selectedRow = resultmodulesTable.getSelectedRow();
		        if (selectedRow != -1) {
		            // Get data from selected row
		        	 int studentId = (int) resultmodulesTable.getValueAt(selectedRow, 0);
		             String studentName = (String) resultmodulesTable.getValueAt(selectedRow, 1);
		             int marks = (int) resultmodulesTable.getValueAt(selectedRow, 3);
		             String grade = (String) resultmodulesTable.getValueAt(selectedRow, 4);
		             String subjects = (String) resultmodulesTable.getValueAt(selectedRow, 2);
		             
		           

		            // Show a dialog for editing
		             showEditStudent(studentId,studentName,subjects,marks,grade,assignedModule);    
		             name_textarea.setText("");
		             id_textarea.setText("");
		             subjectarea.setText("");
		             

				

	    	}
	    	}
	    });
	    
	    editbtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
	    editbtn.setBounds(180, 128, 151, 55);
	    result_panel.add(editbtn);
	    
	    
	    
	    resultmodulesTableModel = new DefaultTableModel(
                new Object[][] {},
                new String[] { "Students_id", "Students_name", "Subjects","marks","grade" }
        );
        resultmodulesTable = new JTable(resultmodulesTableModel);
        
        JScrollPane scrollPane_3 = new JScrollPane();
	    scrollPane_3.setBounds(30, 221, 580, 261);
	    result_panel.add(scrollPane_3);
	    scrollPane_3.setViewportView(resultmodulesTable);
	    
	    
        resultmodulesTable.setFont(new Font("Tahoma", Font.PLAIN, 16));
        resultmodulesTable.setForeground(Color.RED);
        resultmodulesTable.setRowHeight(30);
        DefaultTableCellRenderer centerRenderer1111 = new DefaultTableCellRenderer();
        centerRenderer1111.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < resultmodulesTable.getColumnCount(); i++) {
        	resultmodulesTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer1111);
        }
        Result result = new Result();
        result.result_list(resultmodulesTable,  assignedModule); 
        
	}
	private void highlightRow(JTable resultmodulesTable, int id, String name, String subject) {
	    resultmodulesTable.clearSelection();
	    List<Integer> matchingRows = new ArrayList<>();

	    for (int row = 0; row < resultmodulesTable.getRowCount(); row++) {
	        int tableId = (int) resultmodulesTable.getValueAt(row, 0);
	        String tableName = ((String) resultmodulesTable.getValueAt(row, 1)).trim();
	        String tableSubject = ((String) resultmodulesTable.getValueAt(row, 2)).trim();

	        // Check if the row matches the search criteria based on name, ID, marks, and subject
	        boolean idMatch = id == 0 || tableId == id;
	        boolean nameMatch = name == null || name.isEmpty() || name.equalsIgnoreCase(tableName);
	        boolean subjectMatch = subject == null || subject.isEmpty() || subject.equalsIgnoreCase(tableSubject);

	        if (idMatch && nameMatch&& subjectMatch) {
	            resultmodulesTable.getSelectionModel().addSelectionInterval(row, row);
	            resultmodulesTable.setSelectionBackground(Color.YELLOW);
	            matchingRows.add(row);
	        }
	    }

	    // If no matching rows based on the criteria, show a message
	    if (matchingRows.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "No data matched for the provided criteria.");
	    }
	}
	private void showEditStudent(int studentId, String studentName,String subjects,int marks, String grade,String assignedModule) {
	    JDialog editDialog = new JDialog(this, "Edit Student", true);
	    editDialog.getContentPane().setLayout(new BorderLayout());

	    // Create JTextField for editing
	    JTextField editMarksField = new JTextField(String.valueOf(marks));
	    JTextField editgradeField = new JTextField(String.valueOf(grade));  // Corrected this line

	    // Create buttons for saving or canceling
	    JButton saveButton = new JButton("Save");
	    saveButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            // Get updated data from text field
	            int editedMarks = Integer.parseInt(editMarksField.getText());
	            String editedGrade = editgradeField.getText();  // Corrected this line

	            // Edit in database
	            Result result = new Result();
	            result.editResult(studentId, studentName, subjects,editedMarks, editedGrade);

	            // Refresh the JTable
	            System.out.println("Before update - assignedModule: " + assignedModule);
	            result.result_list(resultmodulesTable,  assignedModule);
	            System.out.println("After update - assignedModule: " + assignedModule);


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
	    editPanel.add(new JLabel("Marks:"));
	    editPanel.add(editMarksField);
	    editPanel.add(new JLabel("Grades:"));
	    editPanel.add(editgradeField);
	    editPanel.add(saveButton);
	    editPanel.add(cancelButton);

	    editDialog.getContentPane().add(editPanel, BorderLayout.CENTER);

	    // Set dialog properties
	    editDialog.setSize(300, 200);
	    editDialog.setLocationRelativeTo(this);
	    editDialog.setVisible(true);
	}
}
