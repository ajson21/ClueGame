package clueGame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.event.ActionListener;

/**
 * Submit button used for human suggestion.
 * Contains logic for correctly suggesting cards based on human input.
 * 
 * @author ajson, jasonyu
 *
 */
public class SubmitButton implements ActionListener {
	SubmitButton() {

	}

	public void actionPerformed(ActionEvent e) {

		Board board;
		board = Board.getInstance();

		ArrayList<String> suggestions = HumanSuggestionGUI.getAnswers();

		if (board.getCurrentPlayer() == 1) {

			Card roomSuggestion = null;
			Card weaponSuggestion = null;
			Card personSuggestion = null;
			String person = "";
			String weapon = "";

			if (suggestions.get(0).substring(0, 1).equals(1)) {

				weapon = suggestions.get(0).substring(1);
				person = suggestions.get(1).substring(1);

			} else {

				weapon = suggestions.get(1).substring(1);
				person = suggestions.get(0).substring(1);

			}

			Character roomInitial = board.getGrid()[board.getPlayerList()[0].getRow()][board.getPlayerList()[0].getColumn()].getInitial();

			for (Card card : board.getPlayerList()[0].getUnknownRoomDeck()) {
				if (roomInitial == card.getCardName().charAt(0)) {

					roomSuggestion = card;
					break;

				}

			}
			
			for (Card card : board.getPlayerList()[0].getUnknownWeaponDeck()){
				if (weapon.equalsIgnoreCase(card.getCardName())) {

					weaponSuggestion = card;
					break;

				}
				
			}
			
			for (Card card : board.getPlayerList()[0].getUnknownPersonDeck()){
				if (person.equalsIgnoreCase(card.getCardName())) {

					personSuggestion = card;
					break;

				}
				
			}

			for (Card card : board.getPlayerList()[0].getKnownCardDeck()) {
				if (roomInitial == card.getCardName().charAt(0)) {

					roomSuggestion = card;

				}
				
				if (person.equalsIgnoreCase(card.getCardName())) {

					personSuggestion = card;

				}
				
				if (weapon.equalsIgnoreCase(card.getCardName())) {

					weaponSuggestion = card;

				}

			}
			
			ArrayList<Card> suggestion = new ArrayList<Card>();
			suggestion.add(personSuggestion);
			suggestion.add(roomSuggestion);
			suggestion.add(weaponSuggestion);
			
			Card result = board.handleSuggestion(suggestion, 0);

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
