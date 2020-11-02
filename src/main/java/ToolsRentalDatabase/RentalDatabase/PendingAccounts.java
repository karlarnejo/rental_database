package ToolsRentalDatabase.RentalDatabase;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PendingAccounts extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTable table;
	private static Connection con;
	private JTable table_1;
	private JTextField fNameTextField;
	private JTextField lNameTextField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PendingAccounts frame = new PendingAccounts();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PendingAccounts() {
		setBounds(100, 100, 1200, 568);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 1164, 188);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton ActivateButton = new JButton("Activate");
		ActivateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				
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
					selectedRowIndexTable1 = -1;
					
					selectedRentIDTable = (int)table.getModel().getValueAt(selectedRowIndexTable, selectedColumnIndexTable);
					Approve(selectedRentIDTable);
					JOptionPane.showMessageDialog(ActivateButton, "Account activated.");
				}
				else if(selectedRowIndexTable1 != -1)
				{
					selectedRowIndexTable  = -1;
					
					selectedRentIDTable1 = (int)table_1.getModel().getValueAt(selectedRowIndexTable1, selectedColumnIndexTable1);	
					Approve(selectedRentIDTable1);
					JOptionPane.showMessageDialog(ActivateButton, "Account activated.");
				}
				else if(selectedRowIndexTable1 == -1 || selectedRowIndexTable == -1)
					JOptionPane.showMessageDialog(ActivateButton, "Error. No such account ID.");
				
				//refresh
				viewAllAccounts();
				viewAllAccountsWithFname("!@#$%&()");
			}
		});
		ActivateButton.setBounds(10, 497, 89, 23);
		contentPane.add(ActivateButton);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 298, 1164, 188);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		JLabel lblSearch = new JLabel("SEARCH BY FIRST NAME OR LAST NAME");
		lblSearch.setBounds(10, 210, 363, 14);
		contentPane.add(lblSearch);
		
		fNameTextField = new JTextField();
		fNameTextField.setColumns(10);
		fNameTextField.setBounds(10, 235, 126, 20);
		contentPane.add(fNameTextField);
		
		JLabel label_1 = new JLabel("First Name");
		label_1.setBounds(13, 266, 86, 14);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("Last Name");
		label_2.setBounds(146, 266, 77, 14);
		contentPane.add(label_2);
		
		lNameTextField = new JTextField();
		lNameTextField.setColumns(10);
		lNameTextField.setBounds(146, 235, 126, 20);
		contentPane.add(lNameTextField);
		
		JButton button_1 = new JButton("Search");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String userFname = "";
				String userLname = "";
				
				userFname = fNameTextField.getText();
				userLname = lNameTextField.getText();
				
				if(userLname.equals(""))
				{
					viewAllAccountsWithFname(userFname);	
				}
				else if(userFname.equals(""))
				{
					viewAllAccountsWithLname(userLname);	
				}
				else
					viewAllAccountsWithFnameLname(userFname, userLname);
			}
		});
		button_1.setBounds(284, 234, 89, 23);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("Delete");
		button_2.addActionListener(new ActionListener() {
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
					selectedRowIndexTable1 = -1;
					
					selectedRentIDTable = (int)table.getModel().getValueAt(selectedRowIndexTable, selectedColumnIndexTable);
					deleteAccount(selectedRentIDTable);
					JOptionPane.showMessageDialog(button_2, "Account deleted.");
				}
				else if(selectedRowIndexTable1 != -1)
				{
					selectedRowIndexTable  = -1;
					
					selectedRentIDTable1 = (int)table_1.getModel().getValueAt(selectedRowIndexTable1, selectedColumnIndexTable1);	
					deleteAccount(selectedRentIDTable1);
					JOptionPane.showMessageDialog(button_2, "Account deleted.");
				}
				else if(selectedRowIndexTable1 == -1 || selectedRowIndexTable == -1)
					JOptionPane.showMessageDialog(button_2, "Error. No such Customer ID.");
				
				//refresh
				viewAllAccounts();
				viewAllAccountsWithFname("!@#$%&()");
				
			}
		});
		button_2.setBounds(262, 497, 143, 23);
		contentPane.add(button_2);
		
		JButton btnDeactivate = new JButton("Deactivate");
		btnDeactivate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
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
					selectedRowIndexTable1 = -1;
					
					selectedRentIDTable = (int)table.getModel().getValueAt(selectedRowIndexTable, selectedColumnIndexTable);
					deactivate(selectedRentIDTable);
					JOptionPane.showMessageDialog(btnDeactivate, "Account deactivated.");
				}
				else if(selectedRowIndexTable1 != -1)
				{
					selectedRowIndexTable  = -1;
					
					selectedRentIDTable1 = (int)table_1.getModel().getValueAt(selectedRowIndexTable1, selectedColumnIndexTable1);	
					deactivate(selectedRentIDTable1);
					JOptionPane.showMessageDialog(btnDeactivate, "Account deactivated.");
				}
				else if(selectedRowIndexTable1 == -1 || selectedRowIndexTable == -1)
					JOptionPane.showMessageDialog(btnDeactivate, "Error. No such Customer ID.");
				
				//refresh
				viewAllAccounts();
				viewAllAccountsWithFname("!@#$%&()");
			}
		});
		btnDeactivate.setBounds(109, 497, 143, 23);
		contentPane.add(btnDeactivate);
		
		//Calling of methods
		viewAllAccounts();
	}
	
	public void viewAllAccounts()
	{
		con = DatabaseConnection.connectDB();
		try 
		{
			String query = String.format("SELECT customerid as customer_ID, userfname as user_first_name, userlname as user_last_name, customerusername as customer_username, age, address, birthday, activated FROM customer ORDER BY activated");
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(query);
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void viewAllAccountsWithFname(String custFName)
	{
		con = DatabaseConnection.connectDB();
		try
		{
			String query = "SELECT customerid as customer_ID, userfname as user_first_name, userlname as user_last_name, customerusername as customer_username, age, address, birthday, activated FROM customer WHERE userfname ILIKE '%"+custFName+"%' ORDER BY activated";
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(query);
			table_1.setModel(DbUtils.resultSetToTableModel(rs));
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void viewAllAccountsWithLname(String custLName)
	{
		con = DatabaseConnection.connectDB();
		try
		{
			String query = "SELECT customerid as customer_ID, userfname as user_first_name, userlname as user_last_name, customerusername as customer_username, age, address, birthday, activated FROM customer WHERE userlname ILIKE '%"+custLName+"%' ORDER BY activated";
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(query);
			table_1.setModel(DbUtils.resultSetToTableModel(rs));
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void viewAllAccountsWithFnameLname(String custFName, String custLName)
	{
		con = DatabaseConnection.connectDB();
		try
		{
			String query = "SELECT customerid as customer_ID, userfname as user_first_name, userlname as user_last_name, customerusername as customer_username, age, address, birthday, activated FROM customer WHERE userfname ILIKE '%"+custFName+"%' AND userlname ILIKE '%"+custLName+"%' ORDER BY activated";
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(query);
			table_1.setModel(DbUtils.resultSetToTableModel(rs));
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void Approve(int custID)
	{
		boolean bol = true;
		
		con = DatabaseConnection.connectDB();
		try
		{
			String query = String.format("UPDATE customer SET activated = '%s' WHERE customerid = '%s'", bol, custID);
			Statement s = con.createStatement();
			s.executeUpdate(query);
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void deactivate(int custID)
	{
		boolean bol = false;
		
		con = DatabaseConnection.connectDB();
		try
		{
			String query = String.format("UPDATE customer SET activated = '%s' WHERE customerid = '%s'", bol, custID);
			Statement s = con.createStatement();
			s.executeUpdate(query);
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void deleteAccount(int custID)
	{	
		con = DatabaseConnection.connectDB();
		try 
		{
			String query = String.format("DELETE FROM customer WHERE customerid = '%s'", custID);
			Statement s = con.createStatement();
			s.executeUpdate(query);
		
			}catch(Exception e)
			{
				e.printStackTrace();
			}
	}
}
