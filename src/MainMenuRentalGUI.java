import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainMenuRentalGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JButton AddProductButton;
	private JButton RentsButton;
	private JButton btnPendingAccounts;
	public String username = "";
	public Connection con;
	public JButton profileBtn;
	public JButton approveRentBtn;
	public JButton AllGames;
	public JButton AllMoviesButton;
	private JButton btnSetGameAnd;
	private JButton btnStaffRegistration;
	
	public JButton getBtnStaffRegistration() {
		return btnStaffRegistration;
	}
	public void setBtnStaffRegistration(JButton btnStaffRegistration) {
		this.btnStaffRegistration = btnStaffRegistration;
	}
	public JButton getBtnSetGameAnd() {
		return btnSetGameAnd;
	}
	public void setBtnSetGameAnd(JButton btnSetGameAnd) {
		this.btnSetGameAnd = btnSetGameAnd;
	}
	public JButton getBtnPendingAccounts() {
		return btnPendingAccounts;
	}
	public void setBtnPendingAccounts(JButton btnPendingAccounts) {
		this.btnPendingAccounts = btnPendingAccounts;
	}
	public JButton getAllGames() {
		return AllGames;
	}
	public void setAllGames(JButton allGames) {
		AllGames = allGames;
	}
	public JButton getAllMoviesButton() {
		return AllMoviesButton;
	}
	public void setAllMoviesButton(JButton allMoviesButton) {
		AllMoviesButton = allMoviesButton;
	}
	public JButton getApproveRentBtn() {
		return approveRentBtn;
	}
	public void setApproveRentBtn(JButton btnDeleteProduct) {
		this.approveRentBtn = btnDeleteProduct;
	}
	public JButton getProfileBtn() {
		return profileBtn;
	}
	public void setProfileBtn(JButton profileBtn) {
		this.profileBtn = profileBtn;
	}
	public String getUser() {
		return username;
	}
	public void setUser(String sample) {
		this.username = sample;
	}
	public JButton getAddProductButton() {
		return AddProductButton;
	}
	public void setAddProductButton(JButton addProductButton) {
		AddProductButton = addProductButton;
	}
	public JButton rentsButtonGetter()
	{
		return RentsButton;
	}
	
	AdminAddProduct addProd = new AdminAddProduct();
	AdminRents rents = new AdminRents();
	PendingAccounts pend = new PendingAccounts();
	GameMovieAvailability avail = new GameMovieAvailability();
	StaffRegistration staffReg = new StaffRegistration();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenuRentalGUI frame = new MainMenuRentalGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainMenuRentalGUI() {

		setTitle("Portal");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 710, 700);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//header design
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(System.getProperty("user.dir").concat("\\Pictures\\Rental Videos and Games\\Design\\Pandora's Box\\sdftyy.jpg")));
		label_2.setBounds(0, 0, 716, 104);
		contentPane.add(label_2);
						
		
		
		JButton AboutUs = new JButton("");
		AboutUs.setToolTipText("About Us");
		AboutUs.setIcon(new ImageIcon(System.getProperty("user.dir").concat("\\Pictures\\Rental Videos and Games\\Design\\Pandora's Box\\about us.jpg")));
		AboutUs.setBackground(Color.WHITE);
		AboutUs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				AboutUsGUI GUI1 = new AboutUsGUI();
				GUI1.setVisible(true);
			}
		});
		AboutUs.setBounds(290, 454,139,139);
		contentPane.add(AboutUs);
		
		JButton FAQ = new JButton("");
		FAQ.setToolTipText("FAQ");
		FAQ.setIcon(new ImageIcon(System.getProperty("user.dir").concat("\\Pictures\\Rental Videos and Games\\Design\\Pandora's Box\\faq.jpg")));
		FAQ.setBackground(Color.WHITE);
		FAQ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				FAQGUI GUI1 = new FAQGUI();
				GUI1.setVisible(true);
			}
		});
		FAQ.setBounds(113, 454, 138, 139);
		contentPane.add(FAQ);
		
		JButton LogoutButton = new JButton("");
		LogoutButton.setToolTipText("Logout");
		LogoutButton.setIcon(new ImageIcon(System.getProperty("user.dir").concat("\\Pictures\\Rental Videos and Games\\Design\\Pandora's Box\\log out - Copy.jpg")));
		LogoutButton.setBackground(Color.WHITE);
		LogoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				LoginGUI rental = new LoginGUI();
				
				
				addProd.dispose();
				rents.dispose();
				pend.dispose();
				avail.dispose();
				staffReg.dispose();
				rental.setVisible(true);
				dispose();
			}
		});
		LogoutButton.setBounds(468, 454, 139, 139);
		contentPane.add(LogoutButton);
		
		AllGames = new JButton("");
		AllGames.setToolTipText("Games");
		AllGames.setIcon(new ImageIcon(System.getProperty("user.dir").concat("\\Pictures\\Rental Videos and Games\\Design\\Pandora's Box\\game.jpg")));
		AllGames.setBackground(Color.WHITE);
		AllGames.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				//Sudlan ang games gamit ang getuser() para mkabalo ko kinsa na customer ang mo add rent
				Games games = new Games(getUser());
				games.setVisible(true);
			}
		});
		AllGames.setBounds(290,273,139,139);
		contentPane.add(AllGames);
		
		AllMoviesButton = new JButton("");
		AllMoviesButton.setToolTipText("Movies");
		AllMoviesButton.setIcon(new ImageIcon(System.getProperty("user.dir").concat("\\Pictures\\Rental Videos and Games\\Design\\Pandora's Box\\movie.jpg")));
		AllMoviesButton.setBackground(Color.WHITE);
		AllMoviesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{	
				//Sudlan ang movies gamit ang getuser() para mkabalo ko kinsa na customer ang mo add rent
				Movies movies = new Movies(getUser());
				movies.setVisible(true);
			}
		});
		AllMoviesButton.setBounds(113,273, 139, 139);
		contentPane.add(AllMoviesButton);
		
		profileBtn = new JButton("");
		profileBtn.setToolTipText("Profile");
		profileBtn.setIcon(new ImageIcon(System.getProperty("user.dir").concat("\\Pictures\\Rental Videos and Games\\Design\\Pandora's Box\\viewah.jpg")));
		profileBtn.setBackground(Color.WHITE);
		profileBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Dapat diri nimo i instantiate ang ProfileGUI kay automatic mo instance ang ProfileGUI nga WALAY sulod pag open pa lang sa MainMenu.
				//So para ma solutionan, Diri nato i instantiate ang ProfileGUI para masudlan siyag value.
				ProfileGUI tmp = new ProfileGUI(getUser());
				
				tmp.setVisible(true);
			}
		});
		profileBtn.setBounds(465,273,139,139);
		contentPane.add(profileBtn);
		
		AddProductButton = new JButton("Add product");
		AddProductButton.setForeground (new Color (0,0,0));
		AddProductButton.setBackground(new Color (245,255,250));
		AddProductButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addProd.setVisible(true);
			}
		});
		AddProductButton.setBounds(34,140,106,22);
		contentPane.add(AddProductButton);
		
		RentsButton = new JButton("View And Deletion Of Rents");
		RentsButton.setForeground (new Color (0,0,0));
		RentsButton.setBackground(new Color (245,255,250));
		RentsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				rents.viewAllRents();
				rents.setVisible(true);
			}
		});
		RentsButton.setBounds(163, 142, 188, 23);
		contentPane.add(RentsButton);
		
		approveRentBtn = new JButton("Pending Rents");
		approveRentBtn.setForeground (new Color (0,0,0));
		approveRentBtn.setBackground(new Color (245,255,250));
		approveRentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ApproveRent approve = new ApproveRent(getUser());
				
				approve.setVisible(true);
			}
		});
		approveRentBtn.setBounds(377,142,133,23);
		contentPane.add(approveRentBtn);
		
		btnPendingAccounts = new JButton("Pending Accounts");
		btnPendingAccounts.setForeground (new Color (0,0,0));
		btnPendingAccounts.setBackground(new Color (245,255,250));
		btnPendingAccounts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				pend.viewAllAccounts();
				pend.setVisible(true);
			}
		});
		btnPendingAccounts.setBounds(533,142,139,23);
		contentPane.add(btnPendingAccounts);
		
		btnSetGameAnd = new JButton("Set Game and Movie Availability");
		btnSetGameAnd.setForeground (new Color (0,0,0));
		btnSetGameAnd.setBackground(new Color (245,255,250));
		btnSetGameAnd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				avail.viewAllMovies();
				avail.viewAllGames();
				avail.setVisible(true);
			}
		});
		btnSetGameAnd.setBounds(133, 176, 218, 23);
		contentPane.add(btnSetGameAnd);
		
		btnStaffRegistration = new JButton("Staff Registration");
		btnStaffRegistration.setForeground (new Color (0,0,0));
		btnStaffRegistration.setBackground(new Color (245,255,250));
		btnStaffRegistration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				staffReg.setVisible(true);
			}
		});
		btnStaffRegistration.setBounds(377, 176, 160, 23);
		contentPane.add(btnStaffRegistration);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(System.getProperty("user.dir").concat("\\Pictures\\Rental Videos and Games\\Design\\Pandora's Box\\dfsduy.jpg")));
		label.setBounds(-15, 129, 737, 80);
		contentPane.add(label);
		
		
	}
}
