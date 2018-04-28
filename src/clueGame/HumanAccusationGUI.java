package clueGame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;

/**
 * GUI to show information for human accusation.
 * 
 * @author ajson, jasonyu
 *
 */
public class HumanAccusationGUI
{
    private JPanel topPanel, centerPanel, bottomPanel;
    private JPanel peoplePanel, roomPanel, WeaponPanel;
    private int hgap;
    private int vgap;
    private static String weaponSelected;
    private static String personSelected;
    private static String roomSelected;
    private JButton submit = new JButton("Submit");
    private JButton cancel = new JButton("Cancel");
    private JLabel p = new JLabel("Person");
    private JLabel r = new JLabel("Room");
    private JLabel w = new JLabel("Weapon");
    private JTextField rr = new JTextField();
    private static JComboBox cbox1, cbox2, cbox3;
    private static ArrayList<String> answers = new ArrayList<String>();
    private String[] rooms = {"Hotbox", "Dungeon", "Bar", "Study", "Computer Room", "Gameroom", "Kitchen", "Living Room", "Art Room", "Theater"};
    private String[] people = {"Ms. Scarlett", "Colonel Mustard", "Mr. Green", "Mrs. White", "Mrs. Peacock", "Professor Plum"};
    private String[] weapons = {"Revolver", "Knife", "Pipe", "Wrench", "Rope", "Candlestick"};

    public static JFrame mainFrame = new JFrame("Make an Accusation");
    public HumanAccusationGUI()
    {
        hgap = 5;
        vgap = 5;
    }

    void displayGUI()
    {

        centerPanel = new JPanel(); 
        centerPanel.setOpaque(true);
    	bottomPanel = new JPanel();
    	bottomPanel.setOpaque(true);
        topPanel = new JPanel();
        topPanel.setOpaque(true);
    	rr.setText("Current Room");
    	rr.setEditable(false);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contentPane = new JPanel();
        contentPane.setOpaque(true);
        contentPane.setBorder(
            BorderFactory.createEmptyBorder(hgap, hgap, hgap, hgap));
        contentPane.setLayout(new BorderLayout(4, 2));
        
        cbox2 = new JComboBox(rooms);
        roomPanel = new JPanel();
        roomPanel.setOpaque(true);
        roomPanel.add(r);
        roomPanel.add(cbox2);
        
        peoplePanel = new JPanel();
        peoplePanel.setOpaque(true);
        
        cbox1 = new JComboBox(people);
        topPanel.add(roomPanel, BorderLayout.PAGE_START);
        topPanel.add(p, BorderLayout.PAGE_END);
        topPanel.add(peoplePanel, BorderLayout.PAGE_END);
        topPanel.add(cbox1,BorderLayout.PAGE_END);

        WeaponPanel = new JPanel(); 
        WeaponPanel.setOpaque(true);

        cbox3 = new JComboBox(weapons);
        centerPanel.add(w);
        centerPanel.add(WeaponPanel);
        centerPanel.add(cbox3);
        
        bottomPanel.add(submit);
        bottomPanel.add(cancel);
        
        contentPane.add(topPanel, BorderLayout.PAGE_START);
        contentPane.add(centerPanel, BorderLayout.CENTER);
        contentPane.add(bottomPanel,BorderLayout.PAGE_END);
        mainFrame.setContentPane(contentPane);
        mainFrame.pack();
        mainFrame.setLocationByPlatform(true);
        mainFrame.setVisible(true);
        
    	submit.addActionListener(new HumanAccusationSubmitButton());
    	cancel.addActionListener(new CancelButton());
    }
    
    
    public static ArrayList<String> getAnswers(){
        weaponSelected = cbox1.getSelectedItem().toString();
        personSelected = cbox3.getSelectedItem().toString();
        roomSelected = cbox2.getSelectedItem().toString();
    	answers.add(roomSelected);
    	answers.add(personSelected);
    	answers.add(weaponSelected);
    	return answers;
    }

    /**
     * Imbedded class for logic of checking if accusation is correct
     * 
     * @author ajson
     *
     */
    public class HumanAccusationSubmitButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			Board board;
			board = Board.getInstance();
			

			ArrayList<String> accusations = HumanAccusationGUI.getAnswers();
			
			String result1 = accusations.get(0);
			String result2 = accusations.get(1);
			String result3 = accusations.get(2);
			
			Card roomAccusation = null;
			Card personAccusation = null;
			Card weaponAccusation = null;
			
			for(Card card : board.getPlayerList()[0].getUnknownPersonDeck()){
				
				if(result1.equalsIgnoreCase(card.getCardName()) || result2.equalsIgnoreCase(card.getCardName()) || result3.equalsIgnoreCase(card.getCardName())){
					
					personAccusation = card;
					
				}
				
			}
			
			for(Card card : board.getPlayerList()[0].getUnknownWeaponDeck()){
				
				if(result1.equalsIgnoreCase(card.getCardName()) || result2.equalsIgnoreCase(card.getCardName()) || result3.equalsIgnoreCase(card.getCardName())){
					
					weaponAccusation = card;
					
				}
				
			}
			
			for(Card card : board.getPlayerList()[0].getUnknownRoomDeck()){
				
				if(result1.equalsIgnoreCase(card.getCardName()) || result2.equalsIgnoreCase(card.getCardName()) || result3.equalsIgnoreCase(card.getCardName())){
					
					roomAccusation = card;
					
				}
				
			}
			
			for(Card card : board.getPlayerList()[0].getKnownCardDeck()){
				
				if(result1.equalsIgnoreCase(card.getCardName()) || result2.equalsIgnoreCase(card.getCardName()) || result3.equalsIgnoreCase(card.getCardName())){
					
					switch(card.getType()){
					
						case WEAPON:
							weaponAccusation = card;
							break;
							
						case PERSON:
							personAccusation = card;
							break;
							
						case ROOM:
							roomAccusation = card;
							break;
							
					}
					
				}
				
			}
			
			ArrayList<Card> accusation = new ArrayList<Card>();
			
			accusation.add(weaponAccusation);
			accusation.add(personAccusation);
			accusation.add(roomAccusation);
			
			boolean correct = board.checkAccusation(accusation);
			
			if(correct){
				
				new HumanCorrectAccusation().displayGUI();
				
			}else{
				
				new HumanIncorrectAccusation().displayGUI(personAccusation.getCardName(), weaponAccusation.getCardName(), roomAccusation.getCardName());
				
			}

			HumanAccusationGUI.mainFrame.dispose();
			
		}

	}
    
}
