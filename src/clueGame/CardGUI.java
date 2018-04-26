package clueGame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 * 
 * JPanel for displaying the Cards of the player
 * 
 * @author ajson, jasonyu
 *
 */
public class CardGUI extends JPanel{
	
public CardGUI(){
		
		JPanel panel = createCardGUI();
		add(panel);
		
	}
	
	private JPanel createCardGUI() {
		// TODO Auto-generated method stubJPanel turnPanel = new JPanel();
		Board board;
		board = Board.getInstance();
		
		JPanel cardPanel = new JPanel();
		cardPanel.setPreferredSize(new Dimension(200, 600));
		cardPanel.setLayout(new GridLayout(3,1));
		cardPanel.setBorder(new TitledBorder (new EtchedBorder(), "My Cards"));
		
		JPanel peoplePanel = new JPanel();
		peoplePanel.setPreferredSize(getSize());
		peoplePanel.setBorder(new TitledBorder (new EtchedBorder(), "People"));
		
		JTextField personCards = new JTextField("Person");
		personCards.setEditable(false);
		personCards.setPreferredSize(new Dimension(150,150));
		peoplePanel.add(personCards);
		
		JPanel roomPanel = new JPanel();
		roomPanel.setBorder(new TitledBorder (new EtchedBorder(), "Rooms"));
		
		JTextField roomCards = new JTextField("Room");
		roomCards.setEditable(false);
		roomCards.setPreferredSize(new Dimension(150,150));
		roomPanel.add(roomCards);
		
		JPanel weaponPanel = new JPanel();
		weaponPanel.setBorder(new TitledBorder (new EtchedBorder(), "Weapons"));
		
		JTextField weaponCards = new JTextField("Weapon");
		weaponCards.setEditable(false);
		weaponCards.setPreferredSize(new Dimension(150,150));
		weaponPanel.add(weaponCards);
		
		cardPanel.add(peoplePanel);
		cardPanel.add(roomPanel);
		cardPanel.add(weaponPanel);
		
		String roomText = "";
		String personText = "";
		String weaponText = "";
		
		for(Card card : board.getPlayerList()[0].getPlayerDeck()){
			
			switch(card.getType()){
			
				case WEAPON:
					weaponText += card.getCardName();
					weaponText += "\n";
					break;
				case PERSON:
					personText += card.getCardName();
					personText += "\n";
					break;
				case ROOM:
					roomText += card.getCardName();
					roomText += "\n";
					break;
			}
			
		}
		
		roomText.split("\\r?\\n");
		personText.split("\\r?\\n");
		weaponText.split("\\r?\\n");
		
		roomCards.setText(roomText);
		personCards.setText(personText);
		weaponCards.setText(weaponText);
		
		return cardPanel;
	}

}
