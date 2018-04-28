package clueGame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.*;

/**
 * Class used to display GUI for human suggestions.
 * Allows player to choose options to make a suggestion
 * 
 * @author ajson, jasonyu
 *
 */
public class HumanSuggestionGUI
{
    private JPanel topPanel, centerPanel, bottomPanel;
    private JPanel peoplePanel, roomPanel, WeaponPanel;
    private int hgap;
    private int vgap;
    private static String weaponSelected;
    private static String personSelected;
    private JButton submit = new JButton("Submit");
    private JButton cancel = new JButton("Cancel");
    private JLabel p = new JLabel("People");
    private JLabel r = new JLabel("Your room");
    private JLabel w = new JLabel("Weapon");
    private JTextField rr = new JTextField();
    private static JComboBox cbox1, cbox3;
    private String[] people = {"Ms. Scarlett", "Colonel Mustard", "Mr. Green", "Mrs. White", "Mrs. Peacock", "Professor Plum"};
    private String[] weapons = {"Revolver", "Knife", "Pipe", "Wrench", "Rope", "Candlestick"};

    public static JFrame mainFrame = new JFrame("Make a Guess");
    public HumanSuggestionGUI()
    {
        hgap = 5;
        vgap = 5;
    }

    void displayGUI()
    {
    	Board board;
    	board = Board.getInstance();
    	
    	Character roomInitial = board.getGrid()[board.getPlayerList()[0].getRow()][board.getPlayerList()[0].getColumn()].getInitial();
    	String room = "";
    	Map<Character, String> legend = board.getLegend();
    	
    	
    	for(Character initial : legend.keySet()){
    		
    		if(roomInitial == initial){
    			
    			room = legend.get(initial);
    			break;
    			
    		}
    		
    	}
    	
        centerPanel = new JPanel(); 
        centerPanel.setOpaque(true);
    	bottomPanel = new JPanel();
    	bottomPanel.setOpaque(true);
        topPanel = new JPanel();
        topPanel.setOpaque(true);
    	rr.setText(room);
    	rr.setEditable(false);

        JPanel contentPane = new JPanel();
        contentPane.setOpaque(true);
        contentPane.setBorder(
            BorderFactory.createEmptyBorder(hgap, hgap, hgap, hgap));
        contentPane.setLayout(new BorderLayout(4, 2));
        
        roomPanel = new JPanel();
        roomPanel.setOpaque(true);
        roomPanel.add(r);
        roomPanel.add(rr);
        
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
        
    	submit.addActionListener(new SuggestionSubmitButton());
    	cancel.addActionListener(new CancelButton());
    	
    }
    
    public static ArrayList<String> getAnswers(){
        weaponSelected = cbox1.getSelectedItem().toString();
        personSelected = cbox3.getSelectedItem().toString();
    	ArrayList<String> result = new ArrayList<String>();
    	result.add(personSelected);
    	result.add(weaponSelected);
    	return result;
    }
    

/**
 * Submit button used for human suggestion.
 * Contains logic for correctly suggesting cards based on human input.
 * 
 * @author ajson, jasonyu
 *
 */
public class SuggestionSubmitButton implements ActionListener {
	SuggestionSubmitButton() {

	}

	public void actionPerformed(ActionEvent e) {

		Board board;
		board = Board.getInstance();

		ArrayList<String> suggestions = HumanSuggestionGUI.getAnswers();

		if (board.getCurrentPlayer() == 1) {

			Card roomSuggestion = null;
			Card weaponSuggestion = null;
			Card personSuggestion = null;
			
			String result1 = "";
			String result2 = "";
			
			result1 = suggestions.get(0);
			result2 = suggestions.get(1);
			
			Character roomInitial = board.getGrid()[board.getPlayerList()[0].getRow()][board.getPlayerList()[0].getColumn()].getInitial();

			for (Card card : board.getPlayerList()[0].getUnknownRoomDeck()) {
				if (roomInitial == card.getCardName().charAt(0)) {

					roomSuggestion = card;
					break;

				}

			}
			
			for (Card card : board.getPlayerList()[0].getUnknownWeaponDeck()){
				if (result1.equalsIgnoreCase(card.getCardName())) {

					weaponSuggestion = card;
					break;

				}else if(result2.equalsIgnoreCase(card.getCardName())){
					
					weaponSuggestion = card;
					break;
					
				}
				
			}
			
			for (Card card : board.getPlayerList()[0].getUnknownPersonDeck()){
				if (result1.equalsIgnoreCase(card.getCardName())) {

					personSuggestion = card;
					break;

				} else if(result2.equalsIgnoreCase(card.getCardName())){
					
					personSuggestion = card;
					break;
					
				}
				
			}

			for (Card card : board.getPlayerList()[0].getKnownCardDeck()) {
				if (roomInitial == card.getCardName().charAt(0)) {

					roomSuggestion = card;

				}
				
				if (result1.equalsIgnoreCase(card.getCardName()) || result2.equalsIgnoreCase(card.getCardName())) {

					personSuggestion = card;

				}
				
				if (result1.equalsIgnoreCase(card.getCardName()) || result2.equalsIgnoreCase(card.getCardName())) {

					weaponSuggestion = card;

				}

			}
			
			
			ArrayList<Card> suggestion = new ArrayList<Card>();
			
			suggestion.add(personSuggestion);
			suggestion.add(roomSuggestion);
			suggestion.add(weaponSuggestion);
			
			Card result = board.handleSuggestion(suggestion, 0);
			
			String guess = "";
			
			guess += roomSuggestion.getCardName();
			guess += " ";
			guess += personSuggestion.getCardName();
			guess += " ";
			guess += weaponSuggestion.getCardName();
			guess += " ";

			ControlPanelGUI.guessBox.setText(guess);
			
			if(result == null){
				
				ControlPanelGUI.guessResultBox.setText("No new clue");
				
			}else{
				
				ControlPanelGUI.guessResultBox.setText(result.getCardName());
				board.getPlayerList()[0].returnSuggestion(result);
				
			}
			
		}
		
		HumanSuggestionGUI.mainFrame.dispose();

	}

}

    
}
