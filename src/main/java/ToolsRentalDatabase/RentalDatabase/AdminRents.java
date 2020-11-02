package ToolsRentalDatabase.RentalDatabase;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

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

public class AdminRents extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTable table;
	public static Connection con;
	private JTable table_1;
	private JTextField fNameTextField;
	private JTextField lNameTextField;
	
	public JTable rentTableGetter()
	{
		return table;
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminRents frame = new AdminRents();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AdminRents() {
		setTitle("View Rents");
		setBounds(100, 100, 1200, 568);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 1164, 188);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{new Integer(1), null, null, "Karl Anthony", "Arnejo", "Haron", "Lua", "Deadpool", "Uncharted 4"},
				{new Integer(2), null, null, "Justine Shane", "Macarat", "Jan Isiah", "Gelacio", "Captain America Civil War", "Uncharted 4"},
				{new Integer(3), null, null, "Justine Shane", "Macarat", "Jan Benedict", "David", "Deadpool", "Uncharted 4"},
				{new Integer(4), null, null, "Justine Shane", "Macarat", "Jan Benedict", "David", "Deadpool", "GTA Vice City"},
				{new Integer(5), null, null, "Justine Shane", "Macarat", "Miah", "Abduljabbar", "Deadpool", "Battlefield 4"},
				{new Integer(6), null, null, "Karl Anthony", "Arnejo", "Jan Isiah", "Gelacio", "Dark Shadows", "GTA San Andreas"},
				{new Integer(7), null, null, "Karl Anthony", "Arnejo", "Miah", "Abduljabbar", "Crimson Peak", "GTA Vice City"},
				{new Integer(8), null, null, "Justine Shane", "Macarat", "Grace", "Cedeno", "Batman VS Superman", "Minecraft"},
				{new Integer(9), null, null, "Karl Anthony", "Arnejo", "Miah", "Abduljabbar", "The Purge 1", "GTA Vice City"},
				{new Integer(10), null, null, "Justine Shane", "Macarat", "Jan Isiah", "Gelacio", "XMen Days Of The Future Past", "Supermario"},
			},
			new String[] {
				"rent_id", "dateborrowed", "datedue", "fname", "lname", "userfname", "userlname", "moviename", "gamename"
			}
		));
		table.getColumnModel().getColumn(0).setMinWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(84);
		table.getColumnModel().getColumn(3).setPreferredWidth(111);
		table.getColumnModel().getColumn(4).setPreferredWidth(96);
		table.getColumnModel().getColumn(5).setPreferredWidth(103);
		table.getColumnModel().getColumn(6).setPreferredWidth(106);
		table.getColumnModel().getColumn(7).setPreferredWidth(164);
		table.getColumnModel().getColumn(8).setPreferredWidth(131);
		scrollPane.setViewportView(table);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 291, 1164, 188);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Rent ID", "First Name", "Last Name", "Date borrowed", "Date Due", "Movie Name", "Game Name"
			}
		));
		scrollPane_1.setViewportView(table_1);
		
		fNameTextField = new JTextField();
		fNameTextField.setBounds(10, 235, 126, 20);
		contentPane.add(fNameTextField);
		fNameTextField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String userFname = "";
				String userLname = "";
				
				userFname = fNameTextField.getText();
				userLname = lNameTextField.getText();
				
				if(userLname.equals(""))
				{
					viewAllRentsWithFname(userFname);	
				}
				else if(userFname.equals(""))
				{
					viewAllRentsWithLname(userLname);	
				}
				else
					viewAllRentsWithFnameLname(userFname, userLname);
			}
		});
		btnSearch.setBounds(284, 234, 89, 23);
		contentPane.add(btnSearch);
		
		JButton btnDelete = new JButton("Delete Rent");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
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
					deleteRent(selectedRentIDTable);
					
					JOptionPane.showMessageDialog(btnDelete, "Rent deleted.");
				}
				else if(selectedRowIndexTable1 != -1)
				{
					selectedRentIDTable1 = (int)table_1.getModel().getValueAt(selectedRowIndexTable1, selectedColumnIndexTable1);	
					deleteRent(selectedRentIDTable1);
					
					JOptionPane.showMessageDialog(btnDelete, "Rent deleted.");
				}
				else if(selectedRowIndexTable1 == -1 || selectedRowIndexTable == -1)
				{
					JOptionPane.showMessageDialog(btnDelete, "No Selected RentID.");
				}
				
				//refresh
				viewAllRents();
				//assigning it with a string to make sure it doesnt exist in the database.
				viewAllRentsWithFname("!@#$%^&*()");
			}
		});
		btnDelete.setBounds(10, 490, 143, 23);
		contentPane.add(btnDelete);
		
		lNameTextField = new JTextField();
		lNameTextField.setColumns(10);
		lNameTextField.setBounds(146, 235, 126, 20);
		contentPane.add(lNameTextField);
		
		JLabel lblDeletionOfRents = new JLabel("SEARCH BY FIRST NAME OR LAST NAME");
		lblDeletionOfRents.setBounds(10, 210, 363, 14);
		contentPane.add(lblDeletionOfRents);
		
		JLabel label = new JLabel("First Name");
		label.setBounds(10, 266, 86, 14);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("Last Name");
		label_1.setBounds(143, 266, 77, 14);
		contentPane.add(label_1);
		
		//Calling of methods
		viewAllRents();
	}
	
	public void viewAllRents()
	{
		con = DatabaseConnection.connectDB();
		try
		{
			String query = "SELECT p.rentid, p.dateborrowed, p.datedue, c.userfname AS customer_first_name, c.userlname AS customer_last_name, s.fname AS staff_first_name, s.lname AS staff_last_name, m.moviename, g.gamename FROM customer c LEFT OUTER JOIN productrent p on c.customerid = p.customerid LEFT OUTER JOIN movie m ON p.movieid = m.movieid LEFT OUTER JOIN game g ON p.gameid = g.gameid LEFT OUTER JOIN staff s ON p.staffid = s.staffid WHERE p.staffid IS NOT NULL AND p.dateborrowed IS NOT NULL AND p.datedue IS NOT NULL ORDER BY p.datedue";
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(query);
			table.setModel(DbUtils.resultSetToTableModel(rs));
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void viewAllRentsWithFname(String custFName)
	{
		con = DatabaseConnection.connectDB();
		try
		{
			String query = "SELECT p.rentid, p.dateborrowed, p.datedue, c.userfname AS customer_first_name, c.userlname AS customer_last_name, s.fname AS staff_first_name, s.lname AS staff_last_name, m.moviename, g.gamename FROM customer c LEFT OUTER JOIN productrent p on c.customerid = p.customerid LEFT OUTER JOIN movie m ON p.movieid = m.movieid LEFT OUTER JOIN game g ON p.gameid = g.gameid LEFT OUTER JOIN staff s ON p.staffid = s.staffid WHERE p.staffid IS NOT NULL AND p.dateborrowed IS NOT NULL AND p.datedue IS NOT NULL AND c.userfname ILIKE '%"+custFName+"%' ORDER BY p.datedue";
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(query);
			table_1.setModel(DbUtils.resultSetToTableModel(rs));
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void viewAllRentsWithLname(String custLName)
	{
		con = DatabaseConnection.connectDB();
		try
		{
			String query = "SELECT p.rentid, p.dateborrowed, p.datedue, c.userfname AS customer_first_name, c.userlname AS customer_last_name, s.fname AS staff_first_name, s.lname AS staff_last_name, m.moviename, g.gamename FROM customer c LEFT OUTER JOIN productrent p on c.customerid = p.customerid LEFT OUTER JOIN movie m ON p.movieid = m.movieid LEFT OUTER JOIN game g ON p.gameid = g.gameid LEFT OUTER JOIN staff s ON p.staffid = s.staffid WHERE p.staffid IS NOT NULL AND p.dateborrowed IS NOT NULL AND p.datedue IS NOT NULL AND userlname ILIKE '%"+custLName+"%' ORDER BY p.datedue";
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(query);
			table_1.setModel(DbUtils.resultSetToTableModel(rs));
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void viewAllRentsWithFnameLname(String custFName, String custLName)
	{
		con = DatabaseConnection.connectDB();
		try
		{
			String query = "SELECT p.rentid, p.dateborrowed, p.datedue, c.userfname AS customer_first_name, c.userlname AS customer_last_name, s.fname AS staff_first_name, s.lname AS staff_last_name, m.moviename, g.gamename FROM customer c LEFT OUTER JOIN productrent p on c.customerid = p.customerid LEFT OUTER JOIN movie m ON p.movieid = m.movieid LEFT OUTER JOIN game g ON p.gameid = g.gameid LEFT OUTER JOIN staff s ON p.staffid = s.staffid WHERE p.staffid IS NOT NULL AND p.dateborrowed IS NOT NULL AND p.datedue IS NOT NULL AND c.userfname ILIKE '%"+custFName+"%' AND userlname ILIKE '%"+custLName+"%' ORDER BY p.datedue";
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(query);
			table_1.setModel(DbUtils.resultSetToTableModel(rs));
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void deleteRent(int rentID)
	{	
		con = DatabaseConnection.connectDB();
		try 
		{
			String query = String.format("DELETE FROM productrent WHERE rentid = '%s'", rentID);
			Statement s = con.createStatement();
			s.executeUpdate(query);
		
			
			
			}catch(Exception e)
			{
				e.printStackTrace();
			}
	}
}
