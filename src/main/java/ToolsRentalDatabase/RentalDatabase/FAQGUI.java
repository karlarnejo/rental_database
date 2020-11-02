package ToolsRentalDatabase.RentalDatabase;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;

public class FAQGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FAQGUI frame = new FAQGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FAQGUI() {
		setResizable(false);
		setBounds(100, 100, 710, 700);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(getClass().getResource("/Design/Pandora's Box/sdftyy.jpg")));
		label_2.setBounds(0, 0, 716, 104);
		contentPane.add(label_2);
		
		JTextArea txtrTheresACouple = new JTextArea();
		txtrTheresACouple.setEditable(false);
		txtrTheresACouple.setText("There's a couple of reasons why your disc might not \r\nplay:\r\n\r\n1. Is it possible that you're trying to play a Blu-ray Disc in \r\na standard DVD player?  Blu-ray Discs will only play in \r\nBlu-ray compatible players.  You can tell if it's a Blu-ray\r\nDisc because the downward-facing side of it will be blue \r\ninstead of silver, and it'll have a Blu-ray logo on top.\r\n\r\n2. It might be dirty or smudged.  If so, please take a soft, \r\nlint-free cloth, and wipe gently in a straight line from the \r\ncenter of the disc to the edge. Only clean discs with a\r\nsolution of water and mild detergent or rubbing alcohol.  \r\nNever use abrasives, solvents or highly acidic cleansers.\r\n\r\n3. If you've tried to clean it and it still won't play, it might \r\nbe scratched.  Please report your unplayable disc here \r\nand we'll help you out.\r\n");
		txtrTheresACouple.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtrTheresACouple.setBounds(21, 262, 327, 213);
		contentPane.add(txtrTheresACouple);
		
		JTextArea txtrForEachAdditional = new JTextArea();
		txtrForEachAdditional.setEditable(false);
		txtrForEachAdditional.setText("For each additional day you keep a rental after 3 days' \r\ntime, you will be charged half of the original rental \r\nprice.");
		txtrForEachAdditional.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtrForEachAdditional.setBounds(21, 549, 311, 49);
		contentPane.add(txtrForEachAdditional);
		
		JTextArea txtrDependingOnYour = new JTextArea();
		txtrDependingOnYour.setEditable(false);
		txtrDependingOnYour.setText("You have to return it back to our branch personally. As of \r\nthe moment, there is no other way.");
		txtrDependingOnYour.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtrDependingOnYour.setBounds(367, 564, 500, 34);
		contentPane.add(txtrDependingOnYour);
		
		JTextArea txtrOnceshipsA = new JTextArea();
		txtrOnceshipsA.setEditable(false);
		txtrOnceshipsA.setText("Once Pandora's Box delivers a game to you,it is your full \r\nresponsibility to return it to us. If the game cannot be \r\nreturned to us or is not received back for any reason, \r\nPandora's Box reserves the right to charge the \r\nreplacement cost of the game to you.");
		txtrOnceshipsA.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtrOnceshipsA.setBounds(367, 262, 311, 77);
		contentPane.add(txtrOnceshipsA);
		
		JTextArea txtrSorryButTheres = new JTextArea();
		txtrSorryButTheres.setText("Sorry, but there's no way to cancel an online reservation. \r\nYou will still be charged even if you don't pick up you \r\nreservation once it's been made.");
		txtrSorryButTheres.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtrSorryButTheres.setEditable(false);
		txtrSorryButTheres.setBounds(367, 410, 451, 55);
		contentPane.add(txtrSorryButTheres);
		
		JTextArea txtrNoOnly = new JTextArea();
		txtrNoOnly.setText("No, Pandora's Box only offers console (Xbox 360 and PS4) \r\ngames and DVD and Blu-Ray or movies.");
		txtrNoOnly.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtrNoOnly.setEditable(false);
		txtrNoOnly.setBounds(367, 499, 451, 34);
		contentPane.add(txtrNoOnly);
		
		JTextArea txtrMyDiskWont = new JTextArea();
		txtrMyDiskWont.setText("My disk won't play. What should\r\n                       I do?");
		txtrMyDiskWont.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtrMyDiskWont.setBounds(21, 206, 305, 49);
		contentPane.add(txtrMyDiskWont);
		
		JTextArea txtrWillIBe = new JTextArea();
		txtrWillIBe.setText("Will I be charged if I don't return \r\n                my items?");
		txtrWillIBe.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtrWillIBe.setBounds(22, 493, 305, 49);
		contentPane.add(txtrWillIBe);
		
		JTextArea txtrWhatHappensIf = new JTextArea();
		txtrWhatHappensIf.setText("What happens if I lost or I \r\n      misplace my rental?");
		txtrWhatHappensIf.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtrWhatHappensIf.setBounds(393, 206, 311, 49);
		contentPane.add(txtrWhatHappensIf);
		
		JTextArea txtrCanIChanged = new JTextArea();
		txtrCanIChanged.setText("Can I changed or cancel a \r\n     reservation online?");
		txtrCanIChanged.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtrCanIChanged.setBounds(393, 350, 305, 49);
		contentPane.add(txtrCanIChanged);
		
		JTextArea txtrDoYouOffer = new JTextArea();
		txtrDoYouOffer.setText("Do you offer PC games or movies?\r\n");
		txtrDoYouOffer.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtrDoYouOffer.setBounds(373, 465, 321, 49);
		contentPane.add(txtrDoYouOffer);
		
		JTextArea txtrHowDoI = new JTextArea();
		txtrHowDoI.setText("How do I send back my item?");
		txtrHowDoI.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtrHowDoI.setBounds(392, 537, 305, 49);
		contentPane.add(txtrHowDoI);
	}
}