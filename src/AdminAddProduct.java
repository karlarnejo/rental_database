import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;

public class AdminAddProduct extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JPanel MoviePanel;
	private JPanel GamePanel;
	private JTextField MovieName;
	private JTextField MovieDirectory;
	private JTextField GameDirectory;
	private JTextField GameName;
	private JTextArea GameDescription;
	private JTextArea MovieDescription;
	public Connection con;
	
	private String globalMovieDirectory;
	private String globalGameDirectory;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminAddProduct frame = new AdminAddProduct();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AdminAddProduct() {
		setResizable(false);
		setTitle("Add Product");
		
		setBounds(100, 100, 829, 629);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		MoviePanel = new JPanel();
		MoviePanel.setBackground(Color.WHITE);
		MoviePanel.setBounds(10, 11, 803, 280);
		contentPane.add(MoviePanel);
		MoviePanel.setLayout(null);
		
		JButton AddMovie = new JButton("Add movie to database");
		AddMovie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String MovName = "";
				String MovDirectory = "";
				String MovDescription = "";
				
				MovName = MovieName.getText();
				MovDirectory = globalMovieDirectory;
				MovDescription = MovieDescription.getText();
				
				if(MovName.equals("") || MovDirectory.equals("") || MovDescription.equals(""))
				{
					JOptionPane.showMessageDialog(AddMovie, "Please fill all details needed. Cant add movie to database.");
				}
				else
				{
					insertionOfMovies(incrementingIDMovies(), MovName, MovDirectory, MovDescription);
					
					MovieName.setText("");
					MovieDirectory.setText("");
					MovieDescription.setText("");
					
					JOptionPane.showMessageDialog(AddMovie, "Movie added.");
				}
			}
		});
		
		MovieDescription = new JTextArea();
		MovieDescription.setBackground(UIManager.getColor("Button.background"));
		MovieDescription.setBounds(132, 102, 651, 133);
		MoviePanel.add(MovieDescription);
		AddMovie.setBounds(605, 246, 178, 23);
		MoviePanel.add(AddMovie);
		
		MovieName = new JTextField();
		MovieName.setBounds(98, 22, 226, 20);
		MoviePanel.add(MovieName);
		MovieName.setColumns(10);
		
		JLabel lblMovieName = new JLabel("Movie Name");
		lblMovieName.setBounds(10, 24, 122, 17);
		MoviePanel.add(lblMovieName);
		
		JLabel lblPicturesDirectory = new JLabel("Pictures Directory");
		lblPicturesDirectory.setBounds(10, 52, 122, 14);
		MoviePanel.add(lblPicturesDirectory);
		
		MovieDirectory = new JTextField();
		MovieDirectory.setEditable(false);
		MovieDirectory.setBounds(121, 49, 554, 20);
		MoviePanel.add(MovieDirectory);
		MovieDirectory.setColumns(10);
		
		JLabel lblMovieDesciption = new JLabel("Movie Desciption");
		lblMovieDesciption.setBounds(10, 95, 122, 14);
		MoviePanel.add(lblMovieDesciption);
		
		JLabel lblFitDescriptionIn = new JLabel("Fit description in the field. Press enter if description overlaps the width of field.");
		lblFitDescriptionIn.setBounds(132, 246, 471, 17);
		MoviePanel.add(lblFitDescriptionIn);
		
		JButton MovieDir = new JButton("Get directory");
		MovieDir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String directory = "";

				
				JFileChooser chooser = new JFileChooser();
				
				chooser.setDialogTitle("Browse the folder to process");
	            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	            chooser.setAcceptAllFileFilterUsed(false);
	            
	            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
	            {
	            	
	            	directory = chooser.getSelectedFile().getAbsolutePath();
	            	
	            	if (directory.equals(System.getProperty("user.dir")
	            			.concat("\\Pictures\\Rental Videos and Games\\Movies\\")
	            			.concat(MovieName.getText()))) {
	            		
	            		globalMovieDirectory = "\\\\Pictures\\\\Rental Videos and Games\\\\Movies\\\\".concat(MovieName.getText());
		            	MovieDirectory.setText(directory.replace("\\", "\\\\"));
	            	}
	            	
	            }		
			}
		});
		MovieDir.setBounds(685, 48, 108, 22);
		MoviePanel.add(MovieDir);
		
		GamePanel = new JPanel();
		GamePanel.setBackground(Color.WHITE);
		GamePanel.setBounds(10, 300, 803, 280);
		contentPane.add(GamePanel);
		GamePanel.setLayout(null);
		
		JButton AddGame = new JButton("Add game to database");
		AddGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String GName = "";
				String GDirectory = "";
				String GDescription = "";
				
				GName = GameName.getText();
				GDirectory = globalGameDirectory;
				GDescription = GameDescription.getText();
				
				if(GName.equals("") || GDirectory.equals("") || GDescription.equals(""))
				{
					JOptionPane.showMessageDialog(AddGame, "Please fill all details needed. Cant add game to database.");
				}
				else
				{
					insertionOfGames(incrementingIDGames(), GName, GDirectory, GDescription);
					
					GameName.setText("");
					GameDirectory.setText("");
					GameDescription.setText("");
					
					JOptionPane.showMessageDialog(AddGame, "Game added.");
				}
			}
		});
		
		GameDescription = new JTextArea();
		GameDescription.setBackground(UIManager.getColor("Button.background"));
		GameDescription.setBounds(132, 102, 651, 133);
		GamePanel.add(GameDescription);
		AddGame.setBounds(607, 246, 176, 23);
		GamePanel.add(AddGame);
		
		JLabel lblGameName = new JLabel("Game Name");
		lblGameName.setBounds(10, 25, 122, 17);
		GamePanel.add(lblGameName);
		
		JLabel label_1 = new JLabel("Pictures Directory");
		label_1.setBounds(10, 53, 122, 14);
		GamePanel.add(label_1);
		
		GameDirectory = new JTextField();
		GameDirectory.setEditable(false);
		GameDirectory.setColumns(10);
		GameDirectory.setBounds(121, 53, 554, 23);
		GamePanel.add(GameDirectory);
		
		JLabel lblGameDesciption = new JLabel("Game Desciption");
		lblGameDesciption.setBounds(10, 96, 122, 14);
		GamePanel.add(lblGameDesciption);
		
		GameName = new JTextField();
		GameName.setColumns(10);
		GameName.setBounds(100, 23, 226, 20);
		GamePanel.add(GameName);
		
		JLabel label = new JLabel("Fit description in the field. Press enter if description overlaps the width of field.");
		label.setBounds(132, 246, 471, 17);
		GamePanel.add(label);
		
		JButton GameDir = new JButton("Get directory");
		GameDir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String directory = "";

				
				JFileChooser chooser = new JFileChooser();
				
				chooser.setDialogTitle("Browse the folder to process");
	            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	            chooser.setAcceptAllFileFilterUsed(false);
	            
	            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
	            {
	            	
	            	directory = chooser.getSelectedFile().getAbsolutePath();
	            	
	            	if (directory.equals(System.getProperty("user.dir")
	            			.concat("\\Pictures\\Rental Videos and Games\\Games\\")
	            			.concat(GameName.getText()))) {
	            		
	            		globalGameDirectory = "\\\\Pictures\\\\Rental Videos and Games\\\\Games\\\\".concat(GameName.getText());
		            	GameDirectory.setText(directory.replace("\\", "\\\\"));
	            	}
	            	
	            }
			}
		});
		GameDir.setBounds(685, 49, 108, 22);
		GamePanel.add(GameDir);
	}
	
	public int incrementingIDMovies()
	{
		int incrementMovies = 0;
		
		con = DatabaseConnection.connectDB();
		try {
			
			Statement s = con.createStatement();
			String incrementing = "SELECT movieid AS movieID FROM movie ORDER BY movieid DESC LIMIT 1";
			ResultSet rs = s.executeQuery(incrementing);
			rs.next();
			incrementMovies = rs.getInt(1);
			} catch (SQLException e1)
			{
				e1.printStackTrace();
			}
		return incrementMovies;
	}
	
	public int incrementingIDGames()
	{
		int incrementGames = 0;
		
		con = DatabaseConnection.connectDB();
		try {
			
			Statement s = con.createStatement();
			String incrementing = "SELECT gameid AS gameID FROM game ORDER BY gameid DESC LIMIT 1";
			ResultSet rs = s.executeQuery(incrementing);
			rs.next();
			incrementGames = rs.getInt(1);
			} catch (SQLException e1)
			{
				e1.printStackTrace();
			}
		return incrementGames;
	}
	
	public void insertionOfMovies(int ID, String MovieName, String MovieDirectory, String MovieDescription)
	{
		boolean bol = true;
		
		con = DatabaseConnection.connectDB();
		try
		{
			Statement s = con.createStatement();
			String query = String.format("INSERT INTO movie(movieid, moviename, dirpictures, availability, description) VALUES('%s', '%s', '%s', '%s', '%s')", ID+1, MovieName, MovieDirectory, bol, MovieDescription);
			s.executeUpdate(query);
		}catch(Exception e)
		{
			e.printStackTrace();
			
		}
	}
	
	public void insertionOfGames(int ID, String GameName, String GameDirectory, String GameDescription)
	{
		boolean bol = true;
		
		con = DatabaseConnection.connectDB();
		try
		{
			Statement s = con.createStatement();
			String query = String.format("INSERT INTO game(gameid, gamename, dirpictures, availability, description) VALUES('%s', '%s', '%s', '%s', '%s')", ID+1, GameName, GameDirectory, bol, GameDescription);
			s.executeUpdate(query);
		}catch(Exception e)
		{
			e.printStackTrace();
			
		}
	}
}
