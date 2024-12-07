package system;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;

public  class registerform extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JPasswordField passwordField;
	public static JPanel regisform() {

	 JPanel registerform = new JPanel();
     registerform.setOpaque(false);
     registerform.setBounds(10, 84, 422, 566);
     registerform.setLayout(null);
     
     JTextArea nameArea = new JTextArea();
     nameArea.setBounds(52, 42, 342, 43);
     nameArea.setMargin(new Insets(10, 2, 3, 2));
     nameArea.setForeground(new Color(230, 230, 250));
     nameArea.setFont(new Font("Arial", Font.PLAIN, 15));
     nameArea.setDisabledTextColor(new Color(240, 255, 255));
     nameArea.setCaretColor(new Color(240, 248, 255));
     nameArea.setBorder(null);
     nameArea.setBackground(new Color(123, 104, 238));
     registerform.add(nameArea);
     
     JTextArea phnnumber = new JTextArea();
     phnnumber.setMargin(new Insets(10, 2, 3, 2));
     phnnumber.setForeground(new Color(230, 230, 250));
     phnnumber.setFont(new Font("Arial", Font.PLAIN, 15));
     phnnumber.setDisabledTextColor(new Color(240, 255, 255));
     phnnumber.setCaretColor(new Color(240, 248, 255));
     phnnumber.setBorder(null);
     phnnumber.setBackground(new Color(123, 104, 238));
     phnnumber.setBounds(52, 144, 342, 43);
     registerform.add(phnnumber);
     
     JTextArea email = new JTextArea();
     email.setMargin(new Insets(10, 2, 3, 2));
     email.setForeground(new Color(230, 230, 250));
     email.setFont(new Font("Arial", Font.PLAIN, 15));
     email.setDisabledTextColor(new Color(240, 255, 255));
     email.setCaretColor(new Color(240, 248, 255));
     email.setBorder(null);
     email.setBackground(new Color(123, 104, 238));
     email.setBounds(52, 251, 342, 43);
     registerform.add(email);
     
     JLabel name = new JLabel("Name");
     name.setBounds(52, 10, 90, 22);
     name.setForeground(new Color(240, 248, 255));
     name.setFont(new Font("Arial", Font.PLAIN, 18));
     name.setBackground(new Color(230, 230, 250));
     registerform.add(name);
     
     JLabel phonenum = new JLabel("Phone no.");
     phonenum.setForeground(new Color(240, 248, 255));
     phonenum.setFont(new Font("Arial", Font.PLAIN, 18));
     phonenum.setBackground(new Color(230, 230, 250));
     phonenum.setBounds(52, 114, 90, 22);
     registerform.add(phonenum);
     
     JSeparator separator_2 = new JSeparator();
     separator_2.setBounds(52, 429, 326, 11);
     registerform.add(separator_2);
     
    
	passwordField = new JPasswordField();
     passwordField.setBounds(52, 362, 276, 43);
     passwordField.setFont(new Font("Tahoma", Font.PLAIN, 15));
     passwordField.setBorder(null);
     passwordField.setBackground(new Color(123, 104, 238));
     registerform.add(passwordField);
     updatePasswordTooltip();
     passwordField.addKeyListener(new KeyListener(){

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			updatePasswordTooltip();
			
		}
    	 
     });
     JToggleButton tglbtnNewToggleButton = new JToggleButton(new ImageIcon(registerform.class.getResource("/icons8-lock-40.png")));
     tglbtnNewToggleButton.setVisible(true);
     tglbtnNewToggleButton.setSelected(true);
     tglbtnNewToggleButton.setOpaque(true);
     tglbtnNewToggleButton.setBorder(null);
     tglbtnNewToggleButton.setBorderPainted(false);
     tglbtnNewToggleButton.setFocusPainted(false);
     tglbtnNewToggleButton.setContentAreaFilled(false);

     tglbtnNewToggleButton.setBackground(Color.LIGHT_GRAY);
     tglbtnNewToggleButton.setBounds(338, 362, 49, 43);
     registerform.add(tglbtnNewToggleButton);

     tglbtnNewToggleButton.addItemListener(new ItemListener() {
         @Override
         public void itemStateChanged(ItemEvent e) {
             if (e.getStateChange() == ItemEvent.SELECTED) {
                 // Button is pressed, show the visible password icon
             	tglbtnNewToggleButton.setIcon((new ImageIcon(getClass().getResource("/icons8-unlock-40 (1).png"))));
                 passwordField.setEchoChar((char) 0); // Display the password
             } else {
                 // Button is released, show the hidden password icon
             	tglbtnNewToggleButton.setIcon((new ImageIcon(getClass().getResource("/icons8-lock-40.png"))));
                 passwordField.setEchoChar('\u2022'); // Mask the password
             }
         }
     });

     
     JSeparator separator_3 = new JSeparator();
     separator_3.setBounds(52, 95, 334, 10);
     registerform.add(separator_3);
     
     JSeparator separator_3_1 = new JSeparator();
     separator_3_1.setBounds(60, 309, 334, 10);
     registerform.add(separator_3_1);
     
     JSeparator separator_3_2 = new JSeparator();
     separator_3_2.setBounds(60, 200, 334, 10);
     registerform.add(separator_3_2);
     
     JLabel lblNewLabel_2 = new JLabel("Password");
     lblNewLabel_2.setBounds(52, 330, 76, 22);
     lblNewLabel_2.setForeground(new Color(240, 255, 240));
     lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
     registerform.add(lblNewLabel_2);
     
     JLabel mail = new JLabel("E-mail\r\n");
     mail.setForeground(new Color(240, 248, 255));
     mail.setFont(new Font("Arial", Font.PLAIN, 18));
     mail.setBackground(new Color(230, 230, 250));
     mail.setBounds(52, 219, 90, 22);
     registerform.add(mail);
     
     JButton btnNewButton = new JButton("Register");
     btnNewButton.setBounds(137, 439, 85, 21);
     registerform.add(btnNewButton);
     
     JComboBox<String> levelComboBox = new JComboBox<>(new String[]{"4", "5", "6"});
     levelComboBox.setBounds(298, 439, 80, 21);
     registerform.add(levelComboBox);
     registerform.add(levelComboBox);
     JRadioButton rdbtnNewRadioButton = new JRadioButton("students");
     rdbtnNewRadioButton.setBounds(39, 477, 103, 21);
     rdbtnNewRadioButton.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		levelComboBox.setEnabled(true);
      	}
      });
      
     registerform.add(rdbtnNewRadioButton);
     
     JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("instructors");
     rdbtnNewRadioButton_1.setBounds(187, 477, 103, 21);
     registerform.add(rdbtnNewRadioButton_1);
     rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
     	public void actionPerformed(ActionEvent e) {
     		levelComboBox.setEnabled(false);
     	}
     });
     
    
     
     ButtonGroup buttonGroup = new ButtonGroup();
     buttonGroup.add(rdbtnNewRadioButton);
     buttonGroup.add(rdbtnNewRadioButton_1);
     
     btnNewButton.addActionListener(new ActionListener() {
    	    @Override
    	    public void actionPerformed(ActionEvent e) {
    	        // Get the selected role
    	        String selectedRole = null;
    	        if (rdbtnNewRadioButton.isSelected()) {
                    selectedRole = "students";
                } else if (rdbtnNewRadioButton_1.isSelected()) {
                    selectedRole = "instructors";
                }

                // Check if at least one radio button is selected
                if (selectedRole == null) {
                	
                    JOptionPane.showMessageDialog(null, "Please select a role (students or instructors) to register.");
                    return;
                }

    	        String name = nameArea.getText();
    	        
    	        String userEmail = email.getText();
    	        String userPassword = String.valueOf(passwordField.getPassword());
    	        
    	        if(name.isEmpty()|| userEmail.isEmpty()|| userPassword.isEmpty()) {
    	        	JOptionPane.showMessageDialog(null, "All fields must be filled.");
    	        	return;
    	        }
    	        if (!isValidPassword(userPassword)) {
    	        	JOptionPane.showMessageDialog(null, "Password must contain at least one digit and special character");
    	        	return;
    	        }
    	        

    	        // Check if it's a teacher and disable the levelComboBox
    	        if ("instructors".equals(selectedRole)) {
    	            levelComboBox.setEnabled(false);
    	        } else {
    	            levelComboBox.setEnabled(true);
    	        }
    	        
    	        // Get the level (assuming it's enabled and a student)
    	        int level = Integer.parseInt(levelComboBox.isEnabled() ? levelComboBox.getSelectedItem().toString() : "0");
    	        login_function.setFrameReference(registerform);
    	        // Register the user based on the selected role
    	        if ("students".equals(selectedRole)) {
    	            login_function.registerStudent(name, userEmail, userPassword,level);
    	            
    	        } else if ("instructors".equals(selectedRole)) {
    	            login_function.registerTeacher(name, userEmail, userPassword);
    	        }


    	    }
    	});
     
     

     
	return registerform;

	}
	  private static boolean isValidPassword(String password) {
	        // Check if the password meets the required pattern and minimum length
	        return password.length() >= 8 && password.matches(".*\\d.*") && password.matches(".*[!@#$%^&*()-+=<>?].*");
	    }
	  private static void updatePasswordTooltip() {
	        // Update the tooltip text dynamically based on the length of the password
	        if (passwordField.getPassword().length < 8) {
	            passwordField.setToolTipText("Password must be at least 8 characters long.");
	        } else {
	            passwordField.setToolTipText(null); // Remove tooltip text
	        }
	    }
}



