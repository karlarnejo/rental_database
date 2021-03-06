package ToolsRentalDatabase.RentalDatabase;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class AddingProductGameFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField TypeComment;
	private JPanel PanelName;
	public static Connection con;
	public JLabel MainPicture;
	public JLabel SidePic1;
	public JLabel SidePic2;
	public JLabel SidePic3;
	public JLabel SidePic4;
	public JButton AddCommentbt;
	public JTextArea CommentTextArea;
	
	public JButton getAddCommentbt() {
		return AddCommentbt;
	}
	public void setAddCommentbt(JButton addCommentbt) {
		AddCommentbt = addCommentbt;
	}
	public JLabel getMainPicture() {
		return MainPicture;
	}
	public JLabel getSidePic1() {
		return SidePic1;
	}
	public void setSidePic1(JLabel sidePic1) {
		SidePic1 = sidePic1;
	}
	public JLabel getSidePic2() {
		return SidePic2;
	}
	public void setSidePic2(JLabel sidePic2) {
		SidePic2 = sidePic2;
	}
	public JLabel getSidePic3() {
		return SidePic3;
	}
	public void setSidePic3(JLabel sidePic3) {
		SidePic3 = sidePic3;
	}
	public JLabel getSidePic4() {
		return SidePic4;
	}
	public void setSidePic4(JLabel sidePic4) {
		SidePic4 = sidePic4;
	}
	public void setMainPicture(JLabel mainPicture) {
		MainPicture = mainPicture;
	}
	public JPanel getPanelName() {
		return PanelName;
	}
	public void setPanelName(JPanel panelName) {
		PanelName = panelName;
	}
	
	public JTextField getTypeComment() {
		return TypeComment;
	}
	public void setTypeComment(JTextField typeComment) {
		TypeComment = typeComment;
	}
	public JTextArea getCommentTextArea() {
		return CommentTextArea;
	}
	public void setCommentTextArea(JTextArea commentTextArea) {
		CommentTextArea = commentTextArea;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddingProductGameFrame frame = new AddingProductGameFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	} 

	/**
	 * Create the frame.
	 */
	public AddingProductGameFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 703, 878);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		PanelName = new JPanel();
		PanelName.setLayout(null);
		PanelName.setBounds(10, 11, 665, 770);
		contentPane.add(PanelName);
		
		SidePic1 = new JLabel("");
		SidePic1.setBounds(309, 0, 169, 199);
		PanelName.add(SidePic1);
		
		SidePic2 = new JLabel("");
		SidePic2.setBounds(309, 206, 169, 199);
		PanelName.add(SidePic2);
		
		SidePic3 = new JLabel("");
		SidePic3.setBounds(491, 0, 169, 199);
		PanelName.add(SidePic3);
		
		JLabel MainPicture = new JLabel("");
		MainPicture.setBounds(-1, 0, 300, 405);
		PanelName.add(MainPicture);
		
		SidePic4 = new JLabel("");
		SidePic4.setBounds(491, 206, 169, 199);
		PanelName.add(SidePic4);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBackground(Color.WHITE);
		textArea.setBounds(9, 420, 651, 133);
		PanelName.add(textArea);
		
		TypeComment = new JTextField();
		TypeComment.setColumns(10);
		TypeComment.setBounds(10, 598, 505, 20);
		PanelName.add(TypeComment);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 631, 650, 124);
		PanelName.add(scrollPane);
		
		JTextArea CommentTextArea = new JTextArea();
		CommentTextArea.setEditable(false);
		scrollPane.setViewportView(CommentTextArea);
		
		JButton button = new JButton("Add comment");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			/*	comment = TypeComment.getText();
				CommentTextArea.append(comment + "\n\n");
				TypeComment.setText("");*/
			}
		});
		button.setBounds(525, 597, 135, 23);
		PanelName.add(button);
		
		JButton button_1 = new JButton("Add Cart");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		button_1.setBounds(571, 564, 89, 23);
		PanelName.add(button_1);
	}
	
	public AddingProductGameFrame(String Desc, String Comments, String gameName, String customerUsernameForCustomerID) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 710, 1000);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		PanelName = new JPanel();
		PanelName.setLayout(null);
		PanelName.setBounds(0, 0, 665, 770);
		contentPane.add(PanelName);
		
		SidePic1 = new JLabel("");
		SidePic1.setBounds(309, 0, 169, 199);
		PanelName.add(SidePic1);
		
		SidePic2 = new JLabel("");
		SidePic2.setBounds(309, 206, 169, 199);
		PanelName.add(SidePic2);
		
		SidePic3 = new JLabel("");
		SidePic3.setBounds(491, 0, 169, 199);
		PanelName.add(SidePic3);
		
		SidePic4 = new JLabel("");
		SidePic4.setBounds(491, 206, 169, 199);
		PanelName.add(SidePic4);
		
		MainPicture = new JLabel("");
		MainPicture.setIcon(new ImageIcon());
		MainPicture.setBounds(-1, 0, 300, 405);
		PanelName.add(MainPicture);
		
		JTextArea Description = new JTextArea();
		Description.setText(Desc);
		Description.setEditable(false);
		Description.setBackground(Color.WHITE);
		Description.setBounds(9, 420, 651, 133);
		PanelName.add(Description);
		
		TypeComment = new JTextField();
		TypeComment.setColumns(10);
		TypeComment.setBounds(10, 598, 505, 20);
		PanelName.add(TypeComment);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 631, 650, 124);
		PanelName.add(scrollPane);
		
		CommentTextArea = new JTextArea();
		CommentTextArea.setText(Comments);
		CommentTextArea.setEditable(false);
		scrollPane.setViewportView(CommentTextArea);
		
		AddCommentbt = new JButton("Add comment");
		AddCommentbt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String comment = "";
				comment = TypeComment.getText();
				
				if(comment.equals(""))
				{
					JOptionPane.showMessageDialog(AddCommentbt, "Please type a comment.");
				}
				insertComment(comment, gameName);
				TypeComment.setText("");
				//isettext nasad nako sa CommentTextArea gamit ang method nako na sql na mokuha sa comments gamit moviename.
				//Dapat i mention ni na line, kaning ubos ani after sa insertComment para ang i settext ky updated na.
				CommentTextArea.setText(comment(gameName));
			}
		});
		
		AddCommentbt.setBounds(525, 597, 135, 23);
		PanelName.add(AddCommentbt);
		
		JButton AddtoCartbt = new JButton("Add Cart");
		AddtoCartbt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				if(availableOrNo(gameName).equals("t"))
				{
					insertionOfRent(incrementingRentID(), customerID(customerUsernameForCustomerID), gameID(gameName));
					JOptionPane.showMessageDialog(AddtoCartbt, "Game added.");
				}
				else
					JOptionPane.showMessageDialog(AddtoCartbt, "Game currently unavailable.");
			}
		});
		AddtoCartbt.setBounds(571, 564, 89, 23);
		PanelName.add(AddtoCartbt);
	}
	
	public void insertComment(String commentType, String gamename)
	{	
		con = DatabaseConnection.connectDB();
		try
		{
			Statement s = con.createStatement();
			String query = String.format("UPDATE game SET comments = concat(comments, '%s') WHERE gamename = '%s'", commentType + "\n\n", gamename);
			s.executeUpdate(query);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	public int gameID(String gamename)
	{	
		int gameName = 0;
		con = DatabaseConnection.connectDB();
		try
		{
			String query = String.format("SELECT gameID FROM game WHERE gamename = '%s'", gamename);
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(query);
			
			while(rs.next())
			{
				gameName = rs.getInt(1);
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return gameName;
	}
	
	public int incrementingRentID()
	{
		int increment = 0;
		
		con = DatabaseConnection.connectDB();
		
		try {
			Statement s = con.createStatement();
			String incrementing = "SELECT rentid AS rentID FROM productrent ORDER BY rentid DESC LIMIT 1";
			ResultSet rs = s.executeQuery(incrementing);
			rs.next();
			
			increment = rs.getInt(1);
			}
			catch (SQLException e1)
			{
				e1.printStackTrace();
			}
		return increment;
	} 
	
	public int customerID(String username)
	{	
		int customer = 0;
		con = DatabaseConnection.connectDB();
		try
		{
			String query = String.format("SELECT customerid FROM customer WHERE customerusername = '%s'", username);
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(query);
			
			while(rs.next())
			{
				customer = rs.getInt(1);
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return customer;
	}
	
	public void insertionOfRent(int rentID, int custID, int gameID)
	{
		con = DatabaseConnection.connectDB();
		try
		{
			Statement s = con.createStatement();
			//Creating string for insertion. Notice the ID+1 as the customerid.
			String query = String.format("INSERT INTO productrent(rentid, customerid, gameid) VALUES('%s', '%s', '%s')", rentID+1, custID, gameID);
			//Executing the update
			s.executeUpdate(query);
		}catch(Exception e)
		{
			e.printStackTrace();
			
		}
		
	}
	
	public String availableOrNo(String gamename)
	{	
		String avialability = "";
		con = DatabaseConnection.connectDB();
		try
		{
			String query = String.format("SELECT availability FROM game WHERE gamename = '%s'", gamename);
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(query);
			
			while(rs.next())
			{
				avialability = rs.getString(1);
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return avialability;
	} 
	
	public String comment(String comm)
	{	
		String comments = "";
		con = DatabaseConnection.connectDB();
		try
		{
			String query = String.format("SELECT comments FROM game WHERE gamename = '%s'", comm);
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(query);
			
			while(rs.next())
			{
				comments = rs.getString(1);
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return comments;
	} 
}
