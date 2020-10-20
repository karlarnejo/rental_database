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

import net.proteanit.sql.DbUtils;

public class GameMovieAvailability extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField movieTextField;
	private JTextField gameTextField;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JTable table_3;
	private static Connection con;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameMovieAvailability frame = new GameMovieAvailability();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GameMovieAvailability() {
		setBounds(100, 100, 1200, 601);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable();
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 44, 500, 188);
		contentPane.add(scrollPane);
	
		JLabel lblSearchByMovie = new JLabel("SEARCH BY MOVIE NAME");
		lblSearchByMovie.setBounds(10, 243, 363, 14);
		contentPane.add(lblSearchByMovie);
		
		movieTextField = new JTextField();
		movieTextField.setColumns(10);
		movieTextField.setBounds(10, 268, 126, 20);
		contentPane.add(movieTextField);
		
		JLabel lblMovieName = new JLabel("Movie Name");
		lblMovieName.setBounds(10, 299, 86, 14);
		contentPane.add(lblMovieName);
		
		JButton button = new JButton("Search");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String movName = "";
				movName = movieTextField.getText();
				
				viewAllMoviesWithName(movName);
				
			}
		});
		button.setBounds(146, 267, 89, 23);
		contentPane.add(button);
		
		table_1 = new JTable();
		
		JScrollPane scrollPane_1 = new JScrollPane(table_1);
		scrollPane_1.setBounds(10, 324, 500, 188);
		contentPane.add(scrollPane_1);
				
		JButton btnAvailable = new JButton("Set Movie Available");
		btnAvailable.addActionListener(new ActionListener() {
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
					setMovieAvailable(selectedRentIDTable);
					JOptionPane.showMessageDialog(btnAvailable, "Movie made available.");
				}
				else if(selectedRowIndexTable1 != -1)
				{
					selectedRowIndexTable  = -1;
					
					selectedRentIDTable1 = (int)table_1.getModel().getValueAt(selectedRowIndexTable1, selectedColumnIndexTable1);	
					setMovieAvailable(selectedRentIDTable1);
					JOptionPane.showMessageDialog(btnAvailable, "Movie made available.");
				}
				else if(selectedRowIndexTable1 == -1 || selectedRowIndexTable == -1)
					JOptionPane.showMessageDialog(btnAvailable, "Error. No such movie ID.");
				
				
				//refresh
				viewAllMovies();
				//refresh the search movie after set available or unavailable
				viewAllMoviesWithName("!@#$%^&");
			}
		});
		btnAvailable.setBounds(10, 528, 143, 23);
		contentPane.add(btnAvailable);
		
		JButton btnUnavailable = new JButton("Set Movie Unavailable");
		btnUnavailable.addActionListener(new ActionListener() {
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
					setMovieUnavailable(selectedRentIDTable);
					JOptionPane.showMessageDialog(btnUnavailable, "Movie made Unavailable.");
				}
				else if(selectedRowIndexTable1 != -1)
				{
					selectedRowIndexTable  = -1;
					
					selectedRentIDTable1 = (int)table_1.getModel().getValueAt(selectedRowIndexTable1, selectedColumnIndexTable1);	
					setMovieUnavailable(selectedRentIDTable1);
					JOptionPane.showMessageDialog(btnUnavailable, "Movie made Unavailable.");
				}
				else if(selectedRowIndexTable1 == -1 || selectedRowIndexTable == -1)
					JOptionPane.showMessageDialog(btnUnavailable, "Error. No such movie ID.");
				
				
				//refresh
				viewAllMovies();
				//refresh the search movie after set available or unavailable
				viewAllMoviesWithName("!@#$%^&(");
			}
		});
		btnUnavailable.setBounds(163, 528, 180, 23);
		contentPane.add(btnUnavailable);
		
		JLabel lblSearchByGame = new JLabel("SEARCH BY GAME NAME");
		lblSearchByGame.setBounds(674, 243, 363, 14);
		contentPane.add(lblSearchByGame);
		
		table_2 = new JTable();

		JScrollPane scrollPane_2 = new JScrollPane(table_2);
		scrollPane_2.setBounds(674, 44, 500, 188);
		contentPane.add(scrollPane_2);
				
		gameTextField = new JTextField();
		gameTextField.setColumns(10);
		gameTextField.setBounds(674, 268, 126, 20);
		contentPane.add(gameTextField);
		
		JButton button_1 = new JButton("Search");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String gameName = "";
				gameName = gameTextField.getText();
				
				viewAllGamesWithName(gameName);
			}
		});
		button_1.setBounds(810, 267, 89, 23);
		contentPane.add(button_1);
		
		JLabel lblGameName = new JLabel("Game Name");
		lblGameName.setBounds(674, 299, 86, 14);
		contentPane.add(lblGameName);
		
		table_3 = new JTable();

		JScrollPane scrollPane_3 = new JScrollPane(table_3);
		scrollPane_3.setBounds(674, 324, 500, 188);
		contentPane.add(scrollPane_3);
				
		JButton btnSetAvailable = new JButton("Set Game Available");
		btnSetAvailable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int selectedRentIDTable2 = 0;
				int selectedRowIndexTable2 = -1;
				int selectedColumnIndexTable2 = 0;
				
				int selectedRentIDTable3 = 0;
				int selectedRowIndexTable3 = -1;
				int selectedColumnIndexTable3 = 0;
				
				selectedRowIndexTable2 = table_2.getSelectedRow();
				selectedRowIndexTable3 = table_3.getSelectedRow();
				
				if(selectedRowIndexTable2 != -1)
				{
					selectedRowIndexTable3 = -1;
					
					selectedRentIDTable2 = (int)table_2.getModel().getValueAt(selectedRowIndexTable2, selectedColumnIndexTable2);
					setGameAvailable(selectedRentIDTable2);
					JOptionPane.showMessageDialog(btnSetAvailable, "Game made available.");
				}
				else if(selectedRowIndexTable3 != -1)
				{
					selectedRowIndexTable2  = -1;
					
					selectedRentIDTable3 = (int)table_3.getModel().getValueAt(selectedRowIndexTable3, selectedColumnIndexTable3);	
					setGameAvailable(selectedRentIDTable3);
					JOptionPane.showMessageDialog(btnSetAvailable, "Game made available.");
				}
				else if(selectedRowIndexTable3 == -1 || selectedRowIndexTable2 == -1)
					JOptionPane.showMessageDialog(btnAvailable, "Error. No such Game ID.");
				
				
				//refresh
				viewAllGames();
				//refresh the search game after set available or unavailable
				viewAllGamesWithName("!@#$%^&()");
			}
		});
		btnSetAvailable.setBounds(674, 528, 143, 23);
		contentPane.add(btnSetAvailable);
		
		JButton btnSetUnavailable = new JButton("Set Game Unavailable");
		btnSetUnavailable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int selectedRentIDTable2 = 0;
				int selectedRowIndexTable2 = -1;
				int selectedColumnIndexTable2 = 0;
				
				int selectedRentIDTable3 = 0;
				int selectedRowIndexTable3 = -1;
				int selectedColumnIndexTable3 = 0;
				
				selectedRowIndexTable2 = table_2.getSelectedRow();
				selectedRowIndexTable3 = table_3.getSelectedRow();
				
				if(selectedRowIndexTable2 != -1)
				{
					selectedRowIndexTable3 = -1;
					
					selectedRentIDTable2 = (int)table_2.getModel().getValueAt(selectedRowIndexTable2, selectedColumnIndexTable2);
					setGameUnavailable(selectedRentIDTable2);
					JOptionPane.showMessageDialog(btnSetUnavailable, "Game made Unavailable.");
				}
				else if(selectedRowIndexTable3 != -1)
				{
					selectedRowIndexTable2  = -1;
					
					selectedRentIDTable3 = (int)table_3.getModel().getValueAt(selectedRowIndexTable3, selectedColumnIndexTable3);	
					setGameUnavailable(selectedRentIDTable3);
					JOptionPane.showMessageDialog(btnSetUnavailable, "Game made Unavailable.");
				}
				else if(selectedRowIndexTable3 == -1 || selectedRowIndexTable2 == -1)
					JOptionPane.showMessageDialog(btnSetUnavailable, "Error. No such Game ID.");
				
				
				//refresh
				viewAllGames();
				//refresh the search game after set available or unavailable
				viewAllGamesWithName("!@#$%&()");
			}
		});
		btnSetUnavailable.setBounds(827, 528, 180, 23);
		contentPane.add(btnSetUnavailable);
		
		JLabel lblMovies = new JLabel("Movies");
		lblMovies.setBounds(10, 11, 500, 22);
		contentPane.add(lblMovies);
		
		JLabel lblGames = new JLabel("Games");
		lblGames.setBounds(674, 11, 500, 22);
		contentPane.add(lblGames);
		
		JButton btnDelete = new JButton("Delete Movie");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int selectedRentIDTable2 = 0;
				int selectedRowIndexTable2 = -1;
				int selectedColumnIndexTable2 = 0;
				
				int selectedRentIDTable3 = 0;
				int selectedRowIndexTable3 = -1;
				int selectedColumnIndexTable3 = 0;
				
				selectedRowIndexTable2 = table.getSelectedRow();
				selectedRowIndexTable3 = table_1.getSelectedRow();
				
				if(selectedRowIndexTable2 != -1)
				{
					selectedRowIndexTable3 = -1;
					
					selectedRentIDTable2 = (int)table.getModel().getValueAt(selectedRowIndexTable2, selectedColumnIndexTable2);
					deleteMovie(selectedRentIDTable2);
					JOptionPane.showMessageDialog(btnDelete, "Movie deleted.");
				}
				else if(selectedRowIndexTable3 != -1)
				{
					selectedRowIndexTable2  = -1;
					
					selectedRentIDTable3 = (int)table_1.getModel().getValueAt(selectedRowIndexTable3, selectedColumnIndexTable3);	
					deleteMovie(selectedRentIDTable3);
					JOptionPane.showMessageDialog(btnDelete, "Movie deleted.");
				}
				else if(selectedRowIndexTable3 == -1 || selectedRowIndexTable2 == -1)
					JOptionPane.showMessageDialog(btnDelete, "Error. No such movie ID.");
				
				
				//refresh
				viewAllMovies();
				//refresh the search game after set available or unavailable
				viewAllMoviesWithName("!@#$%&()");
				
			}
		});
		btnDelete.setBounds(353, 528, 126, 23);
		contentPane.add(btnDelete);
		
		JButton btnDeleteMovie = new JButton("Delete Movie");
		btnDeleteMovie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int selectedRentIDTable2 = 0;
				int selectedRowIndexTable2 = -1;
				int selectedColumnIndexTable2 = 0;
				
				int selectedRentIDTable3 = 0;
				int selectedRowIndexTable3 = -1;
				int selectedColumnIndexTable3 = 0;
				
				selectedRowIndexTable2 = table_2.getSelectedRow();
				selectedRowIndexTable3 = table_3.getSelectedRow();
				
				if(selectedRowIndexTable2 != -1)
				{
					selectedRowIndexTable3 = -1;
					
					selectedRentIDTable2 = (int)table_2.getModel().getValueAt(selectedRowIndexTable2, selectedColumnIndexTable2);
					deleteGame(selectedRentIDTable2);
					JOptionPane.showMessageDialog(btnDeleteMovie, "Game deleted.");
				}
				else if(selectedRowIndexTable3 != -1)
				{
					selectedRowIndexTable2  = -1;
					
					selectedRentIDTable3 = (int)table_3.getModel().getValueAt(selectedRowIndexTable3, selectedColumnIndexTable3);	
					deleteGame(selectedRentIDTable3);
					JOptionPane.showMessageDialog(btnDeleteMovie, "Game deleted.");
				}
				else if(selectedRowIndexTable3 == -1 || selectedRowIndexTable2 == -1)
					JOptionPane.showMessageDialog(btnDeleteMovie, "Error. No such Game ID.");
				
				
				//refresh
				viewAllGames();
				//refresh the search game after set available or unavailable
				viewAllGamesWithName("!@#$%&()");
				
			}
		});
		btnDeleteMovie.setBounds(1017, 528, 126, 23);
		contentPane.add(btnDeleteMovie);
		
		//CALLING OF METHODS
		viewAllMovies();
		viewAllGames();
	}
	
	public void viewAllMovies()
	{
		con = DatabaseConnection.connectDB();
		try 
		{
			String query = String.format("SELECT movieid, moviename, availability FROM movie ORDER BY availability");
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(query);
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void viewAllMoviesWithName(String moviename)
	{
		con = DatabaseConnection.connectDB();
		try
		{
			String query = "SELECT movieid, moviename, availability FROM movie WHERE moviename ILIKE '%"+moviename+"%'";
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(query);
			table_1.setModel(DbUtils.resultSetToTableModel(rs));
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void viewAllGames()
	{
		con = DatabaseConnection.connectDB();
		try 
		{
			String query = String.format("SELECT gameid, gamename, availability FROM game ORDER BY availability");
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(query);
			table_2.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void viewAllGamesWithName(String gamename)
	{
		con = DatabaseConnection.connectDB();
		try
		{
			String query = "SELECT gameid, gamename, availability FROM game WHERE gamename ILIKE '%"+gamename+"%'";
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(query);
			table_3.setModel(DbUtils.resultSetToTableModel(rs));
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void setMovieAvailable(int movieID)
	{
		boolean bol = true;
		
		con = DatabaseConnection.connectDB();
		try
		{
			String query = String.format("UPDATE movie SET availability = '%s' WHERE movieid = '%s'", bol, movieID);
			Statement s = con.createStatement();
			s.executeUpdate(query);
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void setGameAvailable(int gameID)
	{
		boolean bol = true;
		
		con = DatabaseConnection.connectDB();
		try
		{
			String query = String.format("UPDATE game SET availability = '%s' WHERE gameid = '%s'", bol, gameID);
			Statement s = con.createStatement();
			s.executeUpdate(query);
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void setMovieUnavailable(int movieID)
	{
		boolean bol = false;
		
		con = DatabaseConnection.connectDB();
		try
		{
			String query = String.format("UPDATE movie SET availability = '%s' WHERE movieid = '%s'", bol, movieID);
			Statement s = con.createStatement();
			s.executeUpdate(query);
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void setGameUnavailable(int gameID)
	{
		boolean bol = false;
		
		con = DatabaseConnection.connectDB();
		try
		{
			String query = String.format("UPDATE game SET availability = '%s' WHERE gameid = '%s'", bol, gameID);
			Statement s = con.createStatement();
			s.executeUpdate(query);
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void deleteMovie(int movieID)
	{		
		con = DatabaseConnection.connectDB();
		try
		{
			String query = String.format("DELETE FROM movie WHERE movieid = '%s'", movieID);
			Statement s = con.createStatement();
			s.executeUpdate(query);
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void deleteGame(int gameID)
	{
		con = DatabaseConnection.connectDB();
		try
		{
			String query = String.format("DELETE FROM game WHERE gameid = '%s'", gameID);
			Statement s = con.createStatement();
			s.executeUpdate(query);
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
