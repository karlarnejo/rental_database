import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Registration extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField NameField;
	private JTextField LastNameField;
	private JTextField AgeField;
	private JTextField AddressField;
	private JTextField BirthdayField;
	private JTextField UsernameField;
	private JPasswordField passwordField;
	private static Connection con;
	private JPasswordField confirmPassword;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registration frame = new Registration();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Registration() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 710, 700);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		NameField = new JTextField();
		NameField.setBounds(190, 273, 110, 20);
		contentPane.add(NameField);
		NameField.setColumns(10);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(107, 273, 46, 14);
		contentPane.add(lblName);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(107, 298, 73, 14);
		contentPane.add(lblLastName);
		
		LastNameField = new JTextField();
		LastNameField.setColumns(10);
		LastNameField.setBounds(190, 298, 110, 20);
		contentPane.add(LastNameField);
		
		JLabel lblAge = new JLabel("Age:");
		lblAge.setBounds(107, 352, 46, 14);
		contentPane.add(lblAge);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(107, 323, 73, 14);
		contentPane.add(lblAddress);
		
		AgeField = new JTextField();
		AgeField.setBounds(190, 352, 110, 20);
		contentPane.add(AgeField);
		AgeField.setColumns(10);
		
		AddressField = new JTextField();
		AddressField.setBounds(190, 323, 110, 20);
		contentPane.add(AddressField);
		AddressField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Birthday:");
		lblNewLabel.setBounds(107, 377, 65, 14);
		contentPane.add(lblNewLabel);
		
		BirthdayField = new JTextField();
		BirthdayField.setToolTipText("YY-MM-DD");
		BirthdayField.setBounds(190, 377, 110, 20);
		contentPane.add(BirthdayField);
		BirthdayField.setColumns(10);
		
		JLabel lblDesiredUsername = new JLabel("Desired Username:");
		lblDesiredUsername.setBounds(337, 273, 142, 14);
		contentPane.add(lblDesiredUsername);
		
		UsernameField = new JTextField();
		UsernameField.setBounds(459, 267, 117, 20);
		contentPane.add(UsernameField);
		UsernameField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(337, 298, 73, 14);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(459, 293, 117, 17);
		contentPane.add(passwordField);
		
		JButton RegisterButton = new JButton("Register");
		RegisterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			String FirstName =	NameField.getText();
			String lastName	= LastNameField.getText();
			String age = AgeField.getText();
			String address = AddressField.getText();
			String username = UsernameField.getText();
			String birthday = BirthdayField.getText();
			String password = passwordField.getText();
			String passwordconfirm = confirmPassword.getText();
			
				if(FirstName.equals("") || lastName.equals("")
					  	|| age.equals("") || address.equals("")
					  	|| username.equals("") || birthday.equals("")
					  	|| password.equals(""))
				{
					JOptionPane.showMessageDialog(RegisterButton, "Please fill up all the required details.");
				}
				else if(checkIfDuplicate(username) == 1)
				{
					JOptionPane.showMessageDialog(RegisterButton, "Username already exists. Please try another one.");
				}
				else if(!passwordconfirm.equals(password))
				{
					JOptionPane.showMessageDialog(RegisterButton, "Password mismatch.");
				}
				else
				{
						insertionOfCustomer(incrementingID(), FirstName, lastName, username, password , age, address, birthday);

						NameField.setText("");
						LastNameField.setText("");
						AgeField.setText("");
						AddressField.setText("");
						UsernameField.setText("");
						BirthdayField.setText("");
						passwordField.setText("");
				
						JOptionPane.showMessageDialog(RegisterButton, "Congrats! You are now registered.");
				}
			}
		});
		RegisterButton.setBounds(459, 373, 117, 23);
		contentPane.add(RegisterButton);
		
		JButton btnBackToLogin = new JButton("Back to login page");
		btnBackToLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login = new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btnBackToLogin.setBounds(440, 442, 154, 23);
		contentPane.add(btnBackToLogin);
		
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(System.getProperty("user.dir").concat("\\Pictures\\Rental Videos and Games\\Design\\Pandora's Box\\sdftyy.jpg")));
		label_2.setBounds(0, 0, 716, 104);
		contentPane.add(label_2);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password:");
		lblConfirmPassword.setBounds(337, 323, 125, 14);
		contentPane.add(lblConfirmPassword);
		
		confirmPassword = new JPasswordField();
		confirmPassword.setBounds(459, 317, 117, 20);
		contentPane.add(confirmPassword);
	}
	public int checkIfDuplicate(String username)
	{
		String user = "";
		
		con = DatabaseConnection.connectDB();
		try 
		{
			String query = String.format("SELECT customerusername FROM customer WHERE customerusername = '%s'", username);
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(query);
		
			while(rs.next())
			{
				user = rs.getString(1);
			}
			if(user.equals(username))
			{
				return 1;
			}
			
			}catch(Exception e)
			{
				return 0;
			}
		
		return 0;
	}
	public int incrementingID()
	{
		int increment = 0;
		
		con = DatabaseConnection.connectDB();
		try {
			//Creating connection
			Statement s = con.createStatement();
			//String incrementing to store the highest value of ID. Act as incrementing
			String incrementing = "SELECT customerid AS custID FROM customer ORDER BY customerid DESC LIMIT 1";
			//Assigning ResultSet rs to the executed incrementing s.executeQuery(incrementing)
			ResultSet rs = s.executeQuery(incrementing);
			//Reading/scanning the result
			rs.next();
			//Placing the result in Int increment. Mag depende kung int, getInt. If String, getString. Kanang (1) pasabot ana kay column 1, dira nimo
			//ibase kung unsa. Sa kani na case kay SELECT customerid ra man, edi ang customerid natural sa first column jd siya makabutang.
			//if "SELECT customerid, age", increment = rs.getInt(1) ug declare kag bago na int para sudlan assumeing age, so all in all
			// inrement = rs.getInt(1) ug age = rs.getInt(2).
			increment = rs.getInt(1);
			} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//Returns the largest value of customerid in the database para ma plus 1 sa insertionOfCustomer method nako sa ubos.
		return increment;
	} 
	public void insertionOfCustomer(int ID, String FirstName, String lastName, String username, String password, String age, String address, String birthday)
	{
		//set boolean para false ang equal niya pag mag insert sa activated.
		boolean bol = false;
		
		con = DatabaseConnection.connectDB();
		try
		{
			Statement s = con.createStatement();
			//Creating string for insertion. Notice the ID+1 as the customerid.
			String query = String.format("INSERT INTO customer VALUES('%s', '%s', '%s', '%s', '%s', '%s', '%s', DATE '%s', '%s')", ID+1, FirstName, lastName, username, password, age, address, birthday, bol);
			//Executing the update
			s.executeUpdate(query);
		}catch(Exception e)
		{
			e.printStackTrace();
			
		}
		
	}
}
