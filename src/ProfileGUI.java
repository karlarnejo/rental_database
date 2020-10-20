import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;
import java.awt.Color;
import javax.swing.JLabel;

public class ProfileGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private static Connection con;

	public JTable tableGetter()
	{
		return table;
	}
	private JTable table;
	private JTable table_1;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
		//			ProfileGUI frame = new ProfileGUI();
		//			frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public ProfileGUI(String user) {
		
		setTitle("Profile");
		setResizable(false);
		setBounds(100, 100, 880, 454);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 60, 854, 148);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setEnabled(false);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Movie Name", "Game Name", "Date Rented", "Date Due"
			}
		));
		scrollPane.setViewportView(table);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 244, 854, 170);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setEnabled(false);
		scrollPane_1.setViewportView(table_1);
		
		JLabel lblPendingRents = new JLabel("Pending Rents");
		lblPendingRents.setBounds(10, 219, 197, 14);
		contentPane.add(lblPendingRents);
		
		JLabel lblApprovedRents = new JLabel("Approved Rents");
		lblApprovedRents.setBounds(10, 11, 128, 14);
		contentPane.add(lblApprovedRents);
		
		//Calling viewAllRents with parameter (username) declared in the MainMenu
		viewAllApprovedRents(user);
		viewAllPendingRents(user);
	}
	
	public void viewAllApprovedRents(String username)
	{
		con = DatabaseConnection.connectDB();
		try
		{
			String query = String.format("SELECT DISTINCT rentID, (c.userfname), c.userlname, p.dateborrowed, p.datedue, m.moviename, g.gamename FROM staff s LEFT OUTER JOIN productrent p ON (p.staffid IS NOT NULL) LEFT OUTER JOIN customer c ON p.customerid = c.customerid LEFT OUTER JOIN movie m ON p.movieid = m.movieid LEFT OUTER JOIN game g ON p.gameid = g.gameid WHERE c.customerusername = '%s'", username);
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(query);
			table.setModel(DbUtils.resultSetToTableModel(rs));
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void viewAllPendingRents(String username)
	{
		con = DatabaseConnection.connectDB();
		try
		{
			String query = String.format("SELECT DISTINCT rentID, (c.userfname), c.userlname, m.moviename, g.gamename FROM staff s LEFT OUTER JOIN productrent p ON (p.staffid IS NULL) LEFT OUTER JOIN customer c ON p.customerid = c.customerid LEFT OUTER JOIN movie m ON p.movieid = m.movieid LEFT OUTER JOIN game g ON p.gameid = g.gameid WHERE c.customerusername = '%s'", username);
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(query);
			table_1.setModel(DbUtils.resultSetToTableModel(rs));
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
