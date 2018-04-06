package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

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

	public ArrayList<Card> createSuggestion(Character initial) {
		// TODO Auto-generated method stub
		ArrayList<Card> suggestion = new ArrayList<Card>();
		boolean foundRoom = false;
		Card currentRoom = new Card("Temporary", 1);
		
		for(Card card : unknownRoomDeck){
			if(initial == card.getCardName().charAt(0)){
			
				foundRoom = true;
				currentRoom = card;
				break;
				
			}
			
		}
		
		for(Card card : knownCardDeck){
			if(initial == card.getCardName().charAt(0)){
			
				foundRoom = true;
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
	
	public Card disproveSuggestion(ArrayList<Card> suggestion) {
		// TODO Auto-generated method stub
		int cardCounter = 0;
		ArrayList<Card> correctCards = new ArrayList<Card>();
		
		for(Card suggestionCard : suggestion){
			
			for(Card playerCard : playerDeck){
				
				if(suggestionCard == playerCard){
					
					cardCounter++;
					correctCards.add(suggestionCard);
					
				}
				
			}
			
		}
		
		if(cardCounter != 0){
			
			Random rand = new Random();
			return correctCards.get(rand.nextInt(correctCards.size()));
			
		}
		
		return null;
		
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

	
	
}
