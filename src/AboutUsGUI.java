import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JLabel;

public class AboutUsGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AboutUsGUI frame = new AboutUsGUI();
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
	public AboutUsGUI() {
		setResizable(false);
		
	
		
	    setBounds(0,0,710, 700);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(System.getProperty("user.dir").concat("\\Pictures\\Rental Videos and Games\\Design\\Pandora's Box\\sdftyy.jpg")));
		label_2.setBounds(0, 0, 716, 104);
		contentPane.add(label_2);
		
		JLabel lblGameAndMovie = new JLabel("Game and Movie Services");
		lblGameAndMovie.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblGameAndMovie.setBounds(45, 158, 370, 54);
		contentPane.add(lblGameAndMovie);
		
		JTextArea txtrLaunchedIn = new JTextArea();
		txtrLaunchedIn.setEditable(false);
		txtrLaunchedIn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtrLaunchedIn.setText("Pandora's Box has since become Philippine's largest and most respected online video game and movie \r\nrental service. We provide our customers with the largest and latest selection of games and movies for a \r\nwide variety of console systems, handheld devices and etc. Once the customer logs in, Pandora's Box \r\ncustomers can immediately have instant access to the site\u2019s extensive library of new and classic games and \r\nmovies.  Pandora's Box is committed to providing its members with an easy-to-use service that supplies \r\nthe greatest selection of the highest quality games and movies at the most affordable prices.");
		txtrLaunchedIn.setBounds(45, 223, 618, 101);
		contentPane.add(txtrLaunchedIn);
		
		JLabel lblNewLabel = new JLabel("Headquarters");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(45, 335, 180, 39);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Iligan City, Lanao Del Norte");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(45, 385, 180, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Contact Us");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_2.setBounds(45, 410, 248, 39);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Contact Num: 09123456789");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(46, 460, 180, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Founded");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_4.setBounds(44, 535, 138, 39);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("June 2016 with official launch in October 2016.\r\n");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(44, 585, 330, 14);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Email: pandorasbox@yahoo.com");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_6.setBounds(45, 485, 180, 14);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblFacebookWwwfacebook = new JLabel("Facebook: www.facebook.com/pandorasbox");
		lblFacebookWwwfacebook.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFacebookWwwfacebook.setBounds(45, 510, 256, 14);
		contentPane.add(lblFacebookWwwfacebook);
	}
}