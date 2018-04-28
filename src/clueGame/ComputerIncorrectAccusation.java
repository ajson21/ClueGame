package clueGame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.*;

/**
 * GUI to display information from computer incorrectly accusing. 
 * 
 * @author ajson, jasonyu
 *
 */
public class ComputerIncorrectAccusation {
	private JPanel topPanel, bottomPanel;
	private JPanel peoplePanel;
	private int hgap;
	private int vgap;
	private String currPlayer;
	private JButton ok = new JButton("Ok");
	public static JFrame mainFrame = new JFrame("Wrong Accusation!");

	public ComputerIncorrectAccusation() {
		hgap = 5;
		vgap = 5;
	}

	void displayGUI(String person, String weapon, String room) {
		
		Board board;
		board = Board.getInstance();
		
		currPlayer = board.getPlayerList()[board.getCurrentPlayer()].getName();
		
		JLabel p = new JLabel(currPlayer + " has incorrectly accused " + person + " of murder with a " + weapon
				+ " in the " + room + ".");
		
		bottomPanel = new JPanel();
		bottomPanel.setOpaque(true);
		topPanel = new JPanel();
		topPanel.setOpaque(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel contentPane = new JPanel();
		contentPane.setOpaque(true);
		contentPane.setBorder(BorderFactory.createEmptyBorder(hgap, hgap, hgap, hgap));
		contentPane.setLayout(new BorderLayout(4, 2));

		peoplePanel = new JPanel();
		peoplePanel.setOpaque(true);

		topPanel.add(p, BorderLayout.PAGE_END);
		topPanel.add(peoplePanel, BorderLayout.PAGE_END);
		bottomPanel.add(ok);

		contentPane.add(topPanel, BorderLayout.PAGE_START);
		contentPane.add(bottomPanel, BorderLayout.PAGE_END);

		mainFrame.setContentPane(contentPane);
		mainFrame.pack();
		mainFrame.setLocationByPlatform(true);
		mainFrame.setVisible(true);

		ok.addActionListener(new IncorrectAccusationSubmitButton());
	}

}
