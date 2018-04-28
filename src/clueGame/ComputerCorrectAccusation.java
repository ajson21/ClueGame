package clueGame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.*;
import javax.swing.border.*;

/**
 * GUI to display information from computer player accusation. Exits game after dialogue.
 * 
 * @author ajson, jasonyu
 *
 */
public class ComputerCorrectAccusation {
	private JPanel topPanel, bottomPanel;
	private JPanel peoplePanel;
	private int hgap;
	private int vgap;
	private String person;
	private String weapon;
	private String room;
	private String currPlayer;
	private JButton ok = new JButton("Ok");
	
	public static JFrame mainFrame = new JFrame("Correct Accusation!");

	public ComputerCorrectAccusation() {
		hgap = 5;
		vgap = 5;
	}

	void displayGUI() {
		
		Board board;
		board = Board.getInstance();
		

		Map<Character, String> legend = board.getLegend();
		ArrayList<Card> solutionDeck = board.getSolutionDeck();
		
		Card roomCard = null;
		Card personCard = null;
		Card weaponCard = null;
		
		for(Card card : solutionDeck){
			
			switch(card.getType()){
			
				case WEAPON:
					weaponCard = card;
					break;
				case PERSON:
					personCard = card;
					break;
				case ROOM:
					roomCard = card;
					break;
			}
		}
		
		currPlayer = board.getPlayerList()[board.getCurrentPlayer()].getName();
		person = personCard.getCardName();
		weapon = weaponCard.getCardName();
		room = roomCard.getCardName();
		
		
		JLabel p = new JLabel(
				currPlayer + " has correctly accused " + person + " of murder with a " + weapon + " in the " + room + ".");
		
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

		ok.addActionListener(new ExitListener());
	}

}
