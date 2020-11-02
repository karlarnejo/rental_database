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

public class Movies extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JPanel panel;
	private static Connection con;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
	//				Movies frame = new Movies();
	//				frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Movies()
	{
		
	}
	
	public Movies(String usernameOfCusomter) {
		//Start of code panel with scrollpane
		setTitle("Movies");
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
		panel.setPreferredSize(new Dimension(300, 770 * movieCounter()));
		//End of code panel with scrollpane
		
		//<AddingProductMovieFrame> pasabot ana ky ang sulod sa akong LinkedList kay ang FRAME. TAKE NOTE FRAME ANG SULOD DILI JPANEL
		LinkedList<AddingProductMovieFrame> apmLL = new LinkedList<AddingProductMovieFrame>();
		
		LinkedList<String> movieName = new LinkedList<String>();
		LinkedList<String> moviePicturesDir = new LinkedList<String>();
		LinkedList<String> movieDescription = new LinkedList<String>();
		LinkedList<String> movieComments = new LinkedList<String>();
		//End of Declaration of LinkedList
		
		//Start of Running of SQL methods and storing in LinkedList
		MovName(movieName);
		dirPictures(moviePicturesDir);
		movDesc(movieDescription);
		comments(movieComments);

		//End of Running of SQL methods and storing in LinkedList
		
		//Start of Scanning of Database
		for (int i = 0; i < movieCounter(); i++)
		{
			//Gahimo kog instance sa AddingProductMovieFrame para ma set nako akong gusto ibutang kada loop gamit ang database
			AddingProductMovieFrame frame1 = new AddingProductMovieFrame(movieDescription.get(i), movieComments.get(i), movieName.get(i), usernameOfCusomter);
			
			//Giset nako ang picture ky dili siya pwede i loop didto sa akong AddingProductMovieFrame. Para sad limpyo akong AddingProductMovieFrame
			//as much as possible.
			frame1.getMainPicture().setIcon(new ImageIcon(System.getProperty("user.dir").concat(moviePicturesDir.get(i) + movieName.get(i)+ "1.jpg")));
			frame1.getSidePic1().setIcon(new ImageIcon(System.getProperty("user.dir").concat(moviePicturesDir.get(i) + movieName.get(i)+ "2.jpg")));
			frame1.getSidePic2().setIcon(new ImageIcon(System.getProperty("user.dir").concat(moviePicturesDir.get(i) + movieName.get(i)+ "3.jpg")));
			frame1.getSidePic3().setIcon(new ImageIcon(System.getProperty("user.dir").concat(moviePicturesDir.get(i) + movieName.get(i)+ "4.jpg")));
			frame1.getSidePic4().setIcon(new ImageIcon(System.getProperty("user.dir").concat(moviePicturesDir.get(i) + movieName.get(i)+ "5.jpg")));
			
		
			//Gamit atong instance na gihimo nako AddingProductMovieFrame nga ang ngalan kay "frame1", gi add nako sa LinkedList
			//nako ang frame1 with database kada loop tungod anang code sa taas ky mo loop man siya with data. So each 1 loop, naa
			//na siyay data pag add nimo sa LinkedList.
			apmLL.add(frame1);
		}
		//End of Scanning of Database
		
		//Start of Adding the blank panel to this panel with scrollpane.
		for (int o = 0; o < movieCounter(); o++)
		{
			//Ang "panel" mao ni siya ang ngalan sa panel nako na naay scrollpane. Gi add nako ang sulod sa akong LinkedList which is FRAMES.
			//Notice nga naay ".getPanelName()" ang "apmLL.get(o).getPanelName()" nako. Tungod kay FRAMES ang sulod sa akong LinkedList,
			//i point nako siya sa getPanelName() nako na method nga ang i return kay ang JPanel nako na katong hulmahan. I try gani diba
			//mo error siya if walaon nimo nang ".getPanelName()" kay dili pwede FRAME imong i add sa panel.
			panel.add(apmLL.get(o).getPanelName());
		}
		//End of Adding the blank panel to this panel with scrollpane.
		
	}
	//SQL methods.
	
	//movieCounter counts the number of movies in the database. Returns the number of movies by counting the IDs
	public int movieCounter()
	{
		int counter = 0;
		
		con = DatabaseConnection.connectDB();
		try 
		{
			String query = "SELECT COUNT (movieid) FROM movie";
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
	//MovName selects all the movienames in database and storing it in LinkedList
	public void MovName(LinkedList<String> movName)
	{	
		con = DatabaseConnection.connectDB();
		try 
		{
			String query = String.format("SELECT moviename FROM movie ORDER BY movieid");
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
			String query = String.format("SELECT dirpictures FROM movie ORDER BY movieid");
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
	//movDesc selects all the description in database and storing it in LinkedList
	public void movDesc(LinkedList<String> descMov)
	{
		
		con = DatabaseConnection.connectDB();
		try 
		{
			String query = String.format("SELECT description FROM movie ORDER BY movieid");
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(query);
		
			while(rs.next())
			{
				descMov.add(rs.getString(1));
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
			String query = String.format("SELECT comments FROM movie ORDER BY movieid");
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
