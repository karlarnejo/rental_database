import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class StaffRegistration extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField staffFnameTextField;
	private JTextField staffLnameTextField;
	private JTextField staffUsernameTextField;
	private JPasswordField staffPasswordFieldConfirm;
	private JPasswordField staffPasswordField;
	private static Connection con;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StaffRegistration frame = new StaffRegistration();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public StaffRegistration() {
		setBounds(100, 100, 503, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("First Name:");
		lblName.setBounds(10, 70, 98, 14);
		contentPane.add(lblName);
		
		JLabel lblStaffRegistration = new JLabel("Staff Registration");
		lblStaffRegistration.setBounds(10, 11, 414, 30);
		contentPane.add(lblStaffRegistration);
		
		staffFnameTextField = new JTextField();
		staffFnameTextField.setBounds(91, 67, 130, 20);
		contentPane.add(staffFnameTextField);
		staffFnameTextField.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(239, 70, 98, 14);
		contentPane.add(lblLastName);
		
		staffLnameTextField = new JTextField();
		staffLnameTextField.setBounds(322, 67, 130, 20);
		contentPane.add(staffLnameTextField);
		staffLnameTextField.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(10, 124, 86, 14);
		contentPane.add(lblUsername);
		
		staffUsernameTextField = new JTextField();
		staffUsernameTextField.setBounds(131, 121, 130, 20);
		contentPane.add(staffUsernameTextField);
		staffUsernameTextField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(10, 152, 75, 14);
		contentPane.add(lblPassword);
		
		staffPasswordFieldConfirm = new JPasswordField();
		staffPasswordFieldConfirm.setBounds(131, 174, 130, 20);
		contentPane.add(staffPasswordFieldConfirm);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password:");
		lblConfirmPassword.setBounds(10, 177, 137, 14);
		contentPane.add(lblConfirmPassword);
		
		staffPasswordField = new JPasswordField();
		staffPasswordField.setBounds(131, 149, 130, 20);
		contentPane.add(staffPasswordField);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String staffFname = "";
				String staffLname = "";
				String staffUsername = "";
				String staffPassword = "";
				String staffPasswordConfirm = "";
				
				staffFname = staffFnameTextField.getText();
				staffLname = staffLnameTextField.getText();
				staffUsername = staffUsernameTextField.getText();
				staffPassword = staffPasswordField.getText();
				staffPasswordConfirm = staffPasswordFieldConfirm.getText();
				
				if(staffFname.equals("") || staffLname.equals("")
					  	|| staffUsername.equals("") || staffPassword.equals("")
					  	|| staffPasswordConfirm.equals(""))
				{
					JOptionPane.showMessageDialog(btnRegister, "Please fill up all the required details.");
				}
				else if(checkIfDuplicate(staffUsername) == 1)
				{
					JOptionPane.showMessageDialog(btnRegister, "Username already exists. Please try another one.");
				}
				else if(!staffPasswordConfirm.equals(staffPassword))
				{
					JOptionPane.showMessageDialog(btnRegister, "Password mismatch.");
				}
				else
				{
					insertionOfStaff(incrementingID(), staffFname, staffLname, staffUsername, staffPassword);
					
					staffFnameTextField.setText("");
					staffLnameTextField.setText("");
					staffUsernameTextField.setText("");
					staffPasswordField.setText("");
					staffPasswordFieldConfirm.setText("");
			
					JOptionPane.showMessageDialog(btnRegister, "Congrats! You are now registered.");
				}
				
			}
		});
		btnRegister.setBounds(10, 227, 89, 23);
		contentPane.add(btnRegister);
	}
	
	public int checkIfDuplicate(String username)
	{
		String user = "";
		
		con = DatabaseConnection.connectDB();
		try 
		{
			String query = String.format("SELECT staffusername FROM staff WHERE staffusername = '%s'", username);
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
			String incrementing = "SELECT staffid AS custID FROM staff ORDER BY staffid DESC LIMIT 1";
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
	
	public void insertionOfStaff(int ID, String FirstName, String lastName, String username, String password)
	{
		
		con = DatabaseConnection.connectDB();
		try
		{
			Statement s = con.createStatement();
			//Creating string for insertion. Notice the ID+1 as the customerid.
			String query = String.format("INSERT INTO staff VALUES('%s', '%s', '%s', '%s', '%s')", ID+1, FirstName, lastName, username, password);
			//Executing the update
			s.executeUpdate(query);
		}catch(Exception e)
		{
			e.printStackTrace();
			
		}
		
	}
}
