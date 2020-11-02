package ToolsRentalDatabase.RentalDatabase;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;

public class LoginGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private static Connection con;

	MainMenuRentalGUI mainMenu = new MainMenuRentalGUI();
	AdminAddProduct AddProd =  new AdminAddProduct();
	Registration register = new Registration();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LoginGUI() {


		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 710, 700);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Username");
		label.setFont(new Font ("Tahoma", Font.BOLD, 12));
		label.setForeground(Color.WHITE);
		label.setBounds(255, 301, 86, 14);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(324, 298, 128, 20);
		contentPane.add(textField);
		
		JLabel label_1 = new JLabel("Password");
		label_1.setFont(new Font ("Tahoma", Font.BOLD, 12));
		label_1.setForeground(Color.WHITE);
		label_1.setBounds(255, 355, 77, 10);
		contentPane.add(label_1);
		
		JButton button = new JButton("Login");
		button.setForeground(Color.BLACK);
		button.setBackground(Color.WHITE);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String TypeUsername = textField.getText();
				String TypePassword = passwordField.getText();
				
				if(AdminLoginChecker(TypeUsername, TypePassword) == 1)
				{
					//Setting the username of mainMenu before disposing so that i have access of who logged in.
					mainMenu.setUser(TypeUsername);
					
					mainMenu.getProfileBtn().setVisible(false);
					mainMenu.getAllGames().setVisible(false);
					mainMenu.getAllMoviesButton().setVisible(false);
					mainMenu.setVisible(true);
					
					dispose();
				}
				else
				{
					if(customerLoginChecker(TypeUsername, TypePassword)==1)
					{
						//Setting the username of mainMenu before disposing so that i have access of who logged in.
						mainMenu.setUser(TypeUsername);
						
						mainMenu.getApproveRentBtn().setVisible(false);
						mainMenu.getAddProductButton().setVisible(false);
						mainMenu.rentsButtonGetter().setVisible(false);
						mainMenu.getBtnPendingAccounts().setVisible(false);
						mainMenu.getBtnSetGameAnd().setVisible(false);
						mainMenu.getBtnStaffRegistration().setVisible(false);
						mainMenu.setVisible(true);
					
						dispose();
					}
					else
						JOptionPane.showMessageDialog(button, "Incorrect login details/account not activated.");
				}
			}
		});
		button.setBounds(366, 409, 86, 23);
		contentPane.add(button);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(324, 352, 128, 17);
		contentPane.add(passwordField);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setBackground(Color.WHITE);
		btnRegister.setForeground(Color.BLACK);
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				register.setVisible(true);
				dispose();
			}
		});
		btnRegister.setBounds(255, 409, 86, 23);
		contentPane.add(btnRegister);
		
		//header design
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(getClass().getResource("/Design/Pandora's Box/sdftyy.jpg")));
		label_2.setBounds(0, 0, 716, 104);
		contentPane.add(label_2);
		
		//box design
		JLabel label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon(getClass().getResource("/Design/Pandora's Box/pmnbh.jpg")));
		label_3.setBounds(183, 212, 343, 275);
		contentPane.add(label_3);
	}
	
	public int AdminLoginChecker(String username, String password)
	{
		String user = "";
		String pass = "";
		
		con = DatabaseConnection.connectDB();
		try 
		{
			String query = String.format("SELECT staffusername, staffpassword FROM staff WHERE staffusername = '%s' AND staffpassword = '%s'", username, password);
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(query);
		
			while(rs.next())
			{
				user = rs.getString(1);
				pass = rs.getString(2);
			}
			if(user.equals(username) && pass.equals(password))
			{
				return 1;
			}
			
			}catch(Exception e)
			{
				return 0;
			}
		
		return 0;
	}
	
	public int customerLoginChecker(String username, String password)
	{
		String user = "";
		String pass = "";
		String boll = "";
		
		con = DatabaseConnection.connectDB();
		try 
		{
			String query = String.format("SELECT customerusername, customerpassword, activated FROM customer WHERE customerusername = '%s' AND customerpassword = '%s'", username, password);
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(query);
		
			while(rs.next())
			{
				user = rs.getString(1);
				pass = rs.getString(2);
				boll = rs.getString(3);
			}
			//Ang return if boolean ky "t" or "f" mao na "t" kay true.
			if(user.equals(username) && pass.equals(password) && boll.equals("t"))
			{
				return 1;
			}
			
			}catch(Exception e)
			{
				return 0;
			}
		
		return 0;
	}
}
