package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * Class to represent an AI player.
 * Automated methods for gameplay decisions.
 * 
 * @author ajson, jasonyu
 *
 */
public class ComputerPlayer extends Player{
	
	Set<Character> visitedRooms = new HashSet<Character>();
	Set<Character> unvisitedRooms = new HashSet<Character>();

	public ComputerPlayer(String playerName, int row, int column, Color color, Map<Character, String> legend) {
		super(playerName, row, column, color);
		// TODO Auto-generated constructor stub
		
		for(Character room : legend.keySet()){
			
			if(room=='X' || room=='W'){
				
			}else{

				unvisitedRooms.add(room);
				
			}
			
		}
		
	}

	/**
	 * Target method for computer players that selects targets based on rooms visited
	 * Unvisited rooms are given priority, while unvisited rooms are considered same as walkways
	 * @param targets
	 * @return Set<BoardCell> valid targets
	 */
	public Set<BoardCell> selectTargets(Set<BoardCell> targets){
		
		boolean filter = false;
		
		for(BoardCell target : targets){
			
			if(unvisitedRooms.contains(target.getInitial())){
				
				filter = true;
				
			}
			
		}
		
		if(filter){
			
			Set<BoardCell> toBeRemoved = new HashSet<BoardCell>();
			
			for(BoardCell target: targets){
				
				if(!unvisitedRooms.contains(target.getInitial())){
					
					toBeRemoved.add(target);
					
				}
				
			}
			
			for(BoardCell removed: toBeRemoved){
				
				targets.remove(removed);
				
			}
			
			for(BoardCell target: targets){
				
				unvisitedRooms.remove(target.getInitial());
				visitedRooms.add(target.getInitial());
				
			}
			
		}
		
		return targets;
		
	}

	public Set<Character> getVisitedRooms() {
		// TODO Auto-generated method stub
		return visitedRooms;
	}

	/**
	 * Method called when computer player only has 3 cards left to guess
	 * Will change later to more meaningful decision making
	 * @return unknownCardDeck with cards 
	 */
	public ArrayList<Card> makeAccusation() {
		// TODO Auto-generated method stub
		ArrayList<Card> accusation = new ArrayList<Card>();
		accusation.add(unknownWeaponDeck.get(0));
		accusation.add(unknownPersonDeck.get(0));
		accusation.add(unknownRoomDeck.get(0));
		
		return accusation;
		
	}

	/**
	 * Helper method for testing functionality of accusations
	 * @param weapon
	 * @param room
	 * @param person
	 */
	public void giveAccusation(Card weapon, Card room, Card person) {
		// TODO Auto-generated method stub
		unknownWeaponDeck.clear();
		unknownPersonDeck.clear();
		unknownRoomDeck.clear();
		unknownWeaponDeck.add(weapon);
		unknownRoomDeck.add(room);
		unknownPersonDeck.add(person);
		
	}

	/**
	 * Helper method for testing functionality of suggestions
	 * @param weapon
	 * @param room
	 * @param person
	 */
	public void giveSuggestion(Card weapon, Card room, Card person) {
		// TODO Auto-generated method stub
		if(!unknownWeaponDeck.contains(weapon)){
			unknownWeaponDeck.add(weapon);
		}
		if(!unknownRoomDeck.contains(room)){
			unknownRoomDeck.add(room);
		}
		if(!unknownPersonDeck.contains(person)){
			unknownPersonDeck.add(person);
		}
		
	}

	/**
	 * Method that creates a suggestion for a computer player
	 * Considers unknown cards of each type and picks one from each
	 */
	@Override
	public ArrayList<Card> createSuggestion(Character initial) {
		// TODO Auto-generated method stub
		ArrayList<Card> suggestion = new ArrayList<Card>();
		Card currentRoom = new Card("Temporary", 1);
		
		for(Card card : unknownRoomDeck){
			if(initial == card.getCardName().charAt(0)){
			
				currentRoom = card;
				break;
				
			}
			
		}
		
		for(Card card : knownCardDeck){
			if(initial == card.getCardName().charAt(0)){
			
				currentRoom = card;
				break;
				
			}
			
		}
		
		suggestion.add(currentRoom);
		
		Random rand = new Random();
		
		if(unknownWeaponDeck.size() == 1){
			
			suggestion.add(unknownWeaponDeck.get(0));
			
		}else{
			
			int weaponValue = rand.nextInt(unknownWeaponDeck.size());
			suggestion.add(unknownWeaponDeck.get(weaponValue));
			
		}
		
		if(unknownPersonDeck.size() == 1){
			
			suggestion.add(unknownPersonDeck.get(0));
			
		}else{
			
			int personValue = rand.nextInt(unknownPersonDeck.size());
			suggestion.add(unknownPersonDeck.get(personValue));
			
		}
		
		return suggestion;
		
	}
	

	
	/**
	 * Helper method only used in testing
	 * Gives computer player exactly three cards that are known
	 * @param weapon
	 * @param room
	 * @param person
	 */
	public void createPlayerDeck(Card weapon, Card room, Card person) {
		// TODO Auto-generated method stub
		playerDeck.clear();
		playerDeck.add(weapon);
		playerDeck.add(room);
		playerDeck.add(person);
		
	}

	/**
	 * Checks if the computer player is able to make an accusation, check used in other part of Clue
	 */
	public boolean ableToMakeAccusation() {
		// TODO Auto-generated method stub
		if(unknownPersonDeck.size() == 1 && unknownWeaponDeck.size() == 1 && unknownRoomDeck.size() == 1){
			
			return true;
			
		}
		
		return false;
	}
	
}
