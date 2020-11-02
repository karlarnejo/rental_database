package ToolsRentalDatabase.RentalDatabase;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

public class ApproveRent extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTable table;
	public static Connection con;
	private JTable table_1;
	private JTextField firstNameTextField;
	private JTextField lastNameTextField;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					ApproveRent frame = new ApproveRent();
//					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ApproveRent(String adminUsername) {
		setBounds(100, 100, 1200, 568);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 1164, 188);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Rent ID", "Customer FName", "Customer LName", "Movie Name", "Game Name"
			}
		));
		scrollPane.setViewportView(table);
		
		JButton approveButton = new JButton("Approve");
		approveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				String currentDate = "";
				String dateDue = "";

				SimpleDateFormat curDate = new SimpleDateFormat("yyyy/MM/dd");
				SimpleDateFormat plus3Days = new SimpleDateFormat("yyyy/MM/dd");
				
				Calendar cal2 = Calendar.getInstance();
				cal2.setTime(new Date());
				currentDate = curDate.format(cal2.getTime());
				System.out.println(currentDate);
				
				Calendar cal = Calendar.getInstance();
				cal.setTime(new Date()); // Now use today date.
				cal.add(Calendar.DATE, 3); // Adding 5 days
				dateDue = plus3Days.format(cal.getTime());
				System.out.println(dateDue);
				
				
				int selectedRentIDTable = 0;
				int selectedRowIndexTable = -1;
				int selectedColumnIndexTable = 0;
				
				int selectedRentIDTable1 = 0;
				int selectedRowIndexTable1 = -1;
				int selectedColumnIndexTable1 = 0;
				
				selectedRowIndexTable = table.getSelectedRow();
				selectedRowIndexTable1 = table_1.getSelectedRow();
				
				if(selectedRowIndexTable != -1)
				{

					selectedRentIDTable = (int)table.getModel().getValueAt(selectedRowIndexTable, selectedColumnIndexTable);
					approveRent(currentDate, dateDue, currentStaff(adminUsername), selectedRentIDTable);
					JOptionPane.showMessageDialog(approveButton, "Rent approved.");
				}
				else if(selectedRowIndexTable1 != -1)
				{

					selectedRentIDTable1 = (int)table_1.getModel().getValueAt(selectedRowIndexTable1, selectedColumnIndexTable1);	
					approveRent(currentDate, dateDue, currentStaff(adminUsername), selectedRentIDTable1);
					JOptionPane.showMessageDialog(approveButton, "Rent approved.");
				}
				else if(selectedRowIndexTable1 == -1 || selectedRowIndexTable == -1)
					JOptionPane.showMessageDialog(approveButton, "Error. No such Rent ID.");

				
				//refresh
				viewNeedApprove();
				//assigning it with a string to make sure it doesnt exist in the database.
				viewAllNeedApproveWithFname("!@#$%&*()");
			}
		});
		approveButton.setBounds(10, 495, 89, 23);
		contentPane.add(approveButton);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 296, 1164, 188);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		firstNameTextField = new JTextField();
		firstNameTextField.setColumns(10);
		firstNameTextField.setBounds(10, 235, 126, 20);
		contentPane.add(firstNameTextField);
		
		JLabel label = new JLabel("SEARCH BY FIRST NAME OR LAST NAME");
		label.setBounds(10, 210, 363, 14);
		contentPane.add(label);
		
		lastNameTextField = new JTextField();
		lastNameTextField.setColumns(10);
		lastNameTextField.setBounds(146, 235, 126, 20);
		contentPane.add(lastNameTextField);
		
		JButton button_1 = new JButton("Search");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String userFname = "";
				String userLname = "";
				
				userFname = firstNameTextField.getText();
				userLname = lastNameTextField.getText();
				
				if(userLname.equals(""))
				{
					viewAllNeedApproveWithFname(userFname);	
				}
				else if(userFname.equals(""))
				{
					viewAllNeedApproveWithLname(userLname);	
				}
				else
					viewAllNeedApproveWithFnameLname(userFname, userLname);
			}
		});
		button_1.setBounds(284, 234, 89, 23);
		contentPane.add(button_1);
		
		JLabel label_1 = new JLabel("First Name");
		label_1.setBounds(13, 266, 86, 14);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("Last Name");
		label_2.setBounds(146, 266, 77, 14);
		contentPane.add(label_2);
		
		//Calling of methods
		viewNeedApprove();
	}
	
	public int currentStaff(String username)
	{
		int ID = 0;
		
		con = DatabaseConnection.connectDB();
		try 
		{
			String query = String.format("SELECT staffid FROM staff WHERE staffusername = '%s'", username);
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(query);
		
			while(rs.next())
			{
				ID = rs.getInt(1);
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return ID;
	}
	
	public void viewNeedApprove()
	{
		con = DatabaseConnection.connectDB();
		try
		{
			String query = "SELECT DISTINCT rentID, (c.userfname), c.userlname, m.moviename, g.gamename FROM staff s LEFT OUTER JOIN productrent p ON (p.staffid IS NULL) LEFT OUTER JOIN customer c ON p.customerid = c.customerid LEFT OUTER JOIN movie m ON p.movieid = m.movieid LEFT OUTER JOIN game g ON p.gameid = g.gameid";
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(query);
			table.setModel(DbUtils.resultSetToTableModel(rs));
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void viewAllNeedApproveWithFname(String custFName)
	{
		con = DatabaseConnection.connectDB();
		try
		{
			String query = "SELECT DISTINCT rentID, (c.userfname), c.userlname, m.moviename, g.gamename FROM staff s LEFT OUTER JOIN productrent p ON (p.staffid IS NULL) LEFT OUTER JOIN customer c ON p.customerid = c.customerid LEFT OUTER JOIN movie m ON p.movieid = m.movieid LEFT OUTER JOIN game g ON p.gameid = g.gameid WHERE c.userfname ILIKE '%"+custFName+"%'";
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(query);
			table_1.setModel(DbUtils.resultSetToTableModel(rs));
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void viewAllNeedApproveWithLname(String custLName)
	{
		con = DatabaseConnection.connectDB();
		try
		{
			String query = "SELECT DISTINCT rentID, (c.userfname), c.userlname, m.moviename, g.gamename FROM staff s LEFT OUTER JOIN productrent p ON (p.staffid IS NULL) LEFT OUTER JOIN customer c ON p.customerid = c.customerid LEFT OUTER JOIN movie m ON p.movieid = m.movieid LEFT OUTER JOIN game g ON p.gameid = g.gameid WHERE c.userlname ILIKE '%"+custLName+"%'";
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(query);
			table_1.setModel(DbUtils.resultSetToTableModel(rs));
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void viewAllNeedApproveWithFnameLname(String custFName, String custLName)
	{
		con = DatabaseConnection.connectDB();
		try
		{
			String query = "SELECT DISTINCT rentID, (c.userfname), c.userlname, m.moviename, g.gamename FROM staff s LEFT OUTER JOIN productrent p ON (p.staffid IS NULL) LEFT OUTER JOIN customer c ON p.customerid = c.customerid LEFT OUTER JOIN movie m ON p.movieid = m.movieid LEFT OUTER JOIN game g ON p.gameid = g.gameid WHERE c.userfname ILIKE '%"+custFName+"%' AND c.userlname ILIKE '%"+custLName+"%'";
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(query);
			table_1.setModel(DbUtils.resultSetToTableModel(rs));
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void approveRent(String dateborrowed, String datedue, int staffID, int rentID)
	{
		con = DatabaseConnection.connectDB();
		try
		{
			Statement s = con.createStatement();
			String query = String.format("UPDATE productrent SET dateborrowed = DATE '%s', datedue = DATE '%s', staffid = %s WHERE rentid = %s", dateborrowed, datedue, staffID, rentID);
			//Executing the update
			s.executeUpdate(query);
		}catch(Exception e)
		{
			e.printStackTrace();
			
		}
		
	}
	
	
}
