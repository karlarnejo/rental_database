package ToolsRentalDatabase.RentalDatabase;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

public class Games extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JPanel panel;
	private static Connection con;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					Games frame = new Games();
//					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Games(String usernameOfCusomter) {
		//Start of code panel with scrollpane
		setTitle("Games");
		setResizable(false);
		setBounds(100, 100, 710, 700);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		scrollPane.setViewportView(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setPreferredSize(new Dimension(300, 770 * gameCounter()));
		//End of code panel with scrollpane
		
		//Start of Declaration of LinkedList
		LinkedList<AddingProductGameFrame> ampLL = new LinkedList<AddingProductGameFrame>();
		LinkedList<String> gameName = new LinkedList<String>();
		LinkedList<String> moviePicturesDir = new LinkedList<String>();
		LinkedList<String> gameDescription = new LinkedList<String>();
		LinkedList<String> gameComments = new LinkedList<String>();
		//End of Declaration of LinkedList
		
		//Start of Running of SQL methods and storing in LinkedList
		GamName(gameName);
		dirPictures(moviePicturesDir);
		GameDesc(gameDescription);
		comments(gameComments);
		//End of Running of SQL methods and storing in LinkedList
		
		//Start of Scanning database
		for (int i = 0; i < gameCounter(); i++)
		{
			AddingProductGameFrame frame1 = new AddingProductGameFrame(gameDescription.get(i), gameComments.get(i), gameName.get(i), usernameOfCusomter);
			frame1.getMainPicture().setIcon(new ImageIcon(System.getProperty("user.dir").concat(moviePicturesDir.get(i) + gameName.get(i)+ "1.jpg")));
			frame1.getSidePic1().setIcon(new ImageIcon(System.getProperty("user.dir").concat(moviePicturesDir.get(i) + gameName.get(i)+ "2.jpg")));
			frame1.getSidePic2().setIcon(new ImageIcon(System.getProperty("user.dir").concat(moviePicturesDir.get(i) + gameName.get(i)+ "3.jpg")));
			frame1.getSidePic3().setIcon(new ImageIcon(System.getProperty("user.dir").concat(moviePicturesDir.get(i) + gameName.get(i)+ "4.jpg")));
			frame1.getSidePic4().setIcon(new ImageIcon(System.getProperty("user.dir").concat(moviePicturesDir.get(i) + gameName.get(i)+ "5.jpg")));
			ampLL.add(frame1);
		}
		//End of Scanning database
		
		//Start of Adding the blank panel to this panel with scrollpane.
		for (int i = 0; i < gameCounter(); i++)
		{
			panel.add(ampLL.get(i).getPanelName());
		}
		//End of Adding the blank panel to this panel with scrollpane.
	}
	
	//SQL methods.
	
	//gameCounter counts the number of games in the database. Returns the number of games by counting the IDs.
	public int gameCounter()
	{
		int counter = 0;
		
		con = DatabaseConnection.connectDB();
		try 
		{
			String query = String.format("SELECT COUNT (gameid) FROM game");
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(query);
		
			while(rs.next())
			{
				counter = rs.getInt(1);
			}
			return counter;
			
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		
		return counter;
	}
	
	//GameName selects all the gamenames in database and storing it in LinkedList
	public void GamName(LinkedList<String> movName)
	{
		
		con = DatabaseConnection.connectDB();
		try 
		{
			String query = String.format("SELECT gamename FROM game");
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(query);
		
			while(rs.next())
			{
				movName.add(rs.getString(1));
			}
			
			}catch(Exception e)
			{
				e.printStackTrace();
			}
	}
	
	//dirPictures selects all the dir in database and storing it in LinkedList
	public void dirPictures(LinkedList<String> dirPic)
	{
		
		con = DatabaseConnection.connectDB();
		try 
		{
			String query = String.format("SELECT dirpictures FROM game");
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(query);
		
			while(rs.next())
			{
				dirPic.add(rs.getString(1));
			}
			
			}catch(Exception e)
			{
				e.printStackTrace();
			}
	}
	//GameDesc selects all the description in database and storing it in LinkedList
	public void GameDesc(LinkedList<String> descGame)
	{
		
		con = DatabaseConnection.connectDB();
		try 
		{
			String query = String.format("SELECT description FROM game");
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(query);
		
			while(rs.next())
			{
				descGame.add(rs.getString(1));
			}
			
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		
	}
	//comments selects all the comments in database and storing it in LinkedList
	public void comments(LinkedList<String> comment)
	{	
		con = DatabaseConnection.connectDB();
		try
		{
			String query = String.format("SELECT comments FROM game");
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(query);
			
			while(rs.next())
			{
				comment .add(rs.getString(1));
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
