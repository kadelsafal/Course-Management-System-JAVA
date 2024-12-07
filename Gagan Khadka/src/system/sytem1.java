package system;

import java.awt.Color;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Point;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

public class sytem1 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Point compCoords;
	private JPasswordField passwordField;
      // Assuming this is the frame where user information is input

    // Constructor or other methods to initialize currentFrame as needed

   
	
    
    public  void close() {
    	this.dispose();
    }
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					sytem1 frame = new sytem1();
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
	@SuppressWarnings("unchecked")
	public sytem1() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 833, 635);
		contentPane = new JPanel();
		contentPane.setFont(new Font("Tahoma", Font.BOLD, 10));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setUndecorated(true);
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		 
        setVisible(true);
        contentPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                compCoords = e.getPoint();
            }
        });

        contentPane.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point currCoords = e.getLocationOnScreen();
                setLocation(currCoords.x - compCoords.x, currCoords.y - compCoords.y);
            }
        });
        contentPane.setLayout(null);
		
		
	       
	        JPanel box1 = new JPanel();
	        box1.setFocusable(false);
	        box1.setLocation(new Point(10, 0));
	        box1.setAlignmentY(2.0f);
	        box1.setAlignmentX(2.0f);
	        box1.setBorder(null);
	        box1.setBackground(new Color(255, 255, 255));
	        box1.setBounds(0, 0, 412, 660);
	        contentPane.add(box1);
	        box1.setLayout(null);
	        
	        JLabel lblNewLabel = new JLabel("<html>Course<br>Management<br>System</html>");
	        lblNewLabel.setForeground(new Color(138, 43, 226));
	        lblNewLabel.setSize(new Dimension(20, 20));
	        lblNewLabel.setDisplayedMnemonicIndex(0);
	        lblNewLabel.setToolTipText("");
	        lblNewLabel.setIconTextGap(5);
	        lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
	        lblNewLabel.setLabelFor(box1);
	        lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
	        lblNewLabel.setFont(new Font("Sylfaen", Font.BOLD, 60));
	        lblNewLabel.setBorder(null);
	        lblNewLabel.setBounds(46, 171, 356, 270);
	        box1.add(lblNewLabel);
	        
	        JSeparator separator = new JSeparator();
	        separator.setBackground(new Color(106, 90, 205));
	        separator.setBounds(10, 85, 392, 22);
	        box1.add(separator);
	        
	        JSeparator separator_1 = new JSeparator();
	        separator_1.setOrientation(SwingConstants.VERTICAL);
	        separator_1.setBackground(new Color(106, 90, 205));
	        separator_1.setBounds(35, 25, 11, 597);
	        box1.add(separator_1);
	        
	        JPanel box2 = new JPanel();
	        box2.setForeground(new Color(240, 255, 255));
	        box2.setBorder(null);
	        box2.setBackground(new Color(106, 90, 205));
	        box2.setBounds(409, 0, 442, 660);
	        contentPane.add(box2);
        box2.setLayout(null);
	        
	        
        
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setBounds(326, 0, 116, 45);
        box2.add(panel);
        panel.setMinimumSize(new Dimension(0, 0));
        FlowLayout fl_panel = new FlowLayout(FlowLayout.TRAILING, 5, 0);
        fl_panel.setAlignOnBaseline(true);
        panel.setLayout(fl_panel);
        
        JButton minimize_btn = new JButton("-");
        minimize_btn.setPreferredSize(new Dimension(35, 20));
        minimize_btn.setFont(new Font("Times New Roman", Font.BOLD, 45));
        panel.add(minimize_btn);
        
        
        
        JButton close_btn = new JButton("X");
        close_btn.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 30));
        panel.add(close_btn);
        ButtonClickListener clickListener = new hover_btn();
        clickListener.customizeButton(close_btn);
        clickListener.customizeButton(minimize_btn);
        
        JPanel loginform = new JPanel();
        loginform.setOpaque(false);
        loginform.setBounds(10, 84, 422, 566);
        box2.add(loginform);
        loginform.setLayout(null);
        
        JTextArea usernameArea = new JTextArea();
        usernameArea.setMargin(new Insets(3, 3, 3, 3));
        usernameArea.setTabSize(0);
        usernameArea.setBounds(52, 71, 342, 51);
        usernameArea.setForeground(new Color(230, 230, 250));
        usernameArea.setFont(new Font("Arial", Font.PLAIN, 23));
        usernameArea.setDisabledTextColor(new Color(240, 255, 255));
        usernameArea.setCaretColor(new Color(240, 248, 255));
        usernameArea.setBorder(new MatteBorder(1, 1, 3, 5, (Color) new Color(255, 255, 255)));
        usernameArea.setBackground(new Color(123, 104, 238));
        loginform.add(usernameArea);
        
        JLabel lblNewLabel_1 = new JLabel("Email");
        lblNewLabel_1.setBounds(58, 39, 90, 22);
        lblNewLabel_1.setForeground(new Color(240, 248, 255));
        lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 18));
        lblNewLabel_1.setBackground(new Color(230, 230, 250));
        loginform.add(lblNewLabel_1);
        
        JSeparator separator_2 = new JSeparator();
        separator_2.setBounds(52, 277, 326, 11);
        loginform.add(separator_2);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(55, 224, 273, 43);
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        passwordField.setBorder(new MatteBorder(1, 1, 3, 5, (Color) new Color(255, 255, 255)));
        passwordField.setBackground(new Color(123, 104, 238));
        loginform.add(passwordField);
        
        JToggleButton tglbtnNewToggleButton = new JToggleButton(new ImageIcon(getClass().getResource("/icons8-lock-40.png")));
        tglbtnNewToggleButton.setVisible(true);
        tglbtnNewToggleButton.setSelected(true);
        tglbtnNewToggleButton.setOpaque(true);
        tglbtnNewToggleButton.setBorder(null);
        tglbtnNewToggleButton.setBorderPainted(false);
        tglbtnNewToggleButton.setFocusPainted(false);
        tglbtnNewToggleButton.setContentAreaFilled(false);

        tglbtnNewToggleButton.setBackground(Color.LIGHT_GRAY);
        tglbtnNewToggleButton.setBounds(345, 224, 49, 43);
        loginform.add(tglbtnNewToggleButton);

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
        separator_3.setBounds(60, 132, 334, 10);
        loginform.add(separator_3);
        
        JLabel lblNewLabel_2 = new JLabel("Password");
        lblNewLabel_2.setBounds(58, 185, 76, 22);
        lblNewLabel_2.setForeground(new Color(240, 255, 240));
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
        loginform.add(lblNewLabel_2);
        JComboBox<?> roleBox = new JComboBox();
        roleBox.setFont(new Font("Verdana", Font.PLAIN, 18));
        roleBox.setModel(new DefaultComboBoxModel(new String[] {"students", "instructors", "admin"}));
        roleBox.setBounds(145, 298, 163, 36);
        loginform.add(roleBox);
        
        JButton Login_button = new JButton("Log IN");
        Login_button.setContentAreaFilled(false);
        Login_button.setBounds(177, 365, 90, 43);
        Login_button.setForeground(new Color(240, 255, 255));
        Login_button.setFont(new Font("Tahoma", Font.PLAIN, 25));
        Login_button.setBorder(new MatteBorder(1, 1, 1, 3, (Color) new Color(255, 255, 255)));
        Login_button.setBackground(new Color(123, 104, 238));
        	Login_button.addActionListener(new ActionListener() {
        	 
        		 public void actionPerformed(ActionEvent e) {
        			 performLogin(usernameArea, passwordField, roleBox);
        			 
                 }
             });
        	
          
        	loginform.add(Login_button);
        JButton register_btn = new JButton("<html>Dont have account ? <br>Signup</html");
        register_btn.setForeground(new Color(255, 255, 255));
        register_btn.setBorder(new MatteBorder(3, 1, 2, 6, (Color) new Color(255, 255, 255)));
        register_btn.setContentAreaFilled(false);
        register_btn.setBounds(104, 418, 260, 84);
        register_btn.setFont(new Font("Tahoma", Font.PLAIN, 20));
        register_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open a new panel or perform any action here
                try {
                    // Assuming registerform has a parameterless constructor
                    Container contentPane = loginform.getParent();

                    // Remove the loginform from the content pane
                    contentPane.remove(loginform);

                    // Create an instance of the registerform class
                    registerform registerForm = new registerform();

                    // Call the regisform method to get the JPanel for the register form
                    JPanel registerPanel = registerForm.regisform();

                    // Add the registerPanel to the content pane
                    contentPane.add(registerPanel);

                    // Add a "Back" button to the registerPanel
                    JButton back_btn = new JButton("Back");
                    back_btn.setBounds(309, 511, 85, 21);
                    back_btn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // Handle the action when the back button is pressed
                            try {
                                

                                // Assuming registerform has a parameterless constructor
                                Container contentPane = registerPanel.getParent();

                                // Remove the registerPanel from the content pane
                                contentPane.remove(registerPanel);



                                // Add the loginPanel to the content pane
                                contentPane.add(loginform);

                                // Repaint the content pane
                                contentPane.revalidate();
                                contentPane.repaint();
                            } catch (Exception ex) {
                                // Handle any exceptions that might occur
                                ex.printStackTrace();
                                JOptionPane.showMessageDialog(null, "Error going back to the login form: " + ex.getMessage());
                            }
                        }
                    });
                    registerPanel.add(back_btn);

                    // Repaint the content pane
                    contentPane.revalidate();
                    contentPane.repaint();

                } catch (Exception ex) {
                    // Handle any exceptions that might occur
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error opening the new panel: " + ex.getMessage());
                }
            }
        });

        loginform.add(register_btn);
        
        
        
        
       
        
        

}

	
	

	private void performLogin(JTextArea usernamearea, JPasswordField passwordField, JComboBox<?> roleBox) {
	    String email = usernamearea.getText();
	    char[] password = passwordField.getPassword();
	    String selectedRole = roleBox.getSelectedItem().toString();
	    
	        

	    login_function.loginApp(email, password, selectedRole, this);
	    
	}
}
	
	


