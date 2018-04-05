package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
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
		return unknownCardDeck;
		
	}

	/**
	 * Helper method for testing functionality of accusations
	 * @param weapon
	 * @param room
	 * @param person
	 */
	public void giveAccusation(Card weapon, Card room, Card person) {
		// TODO Auto-generated method stub
		unknownCardDeck.clear();
		unknownCardDeck.add(weapon);
		unknownCardDeck.add(room);
		unknownCardDeck.add(person);
		
	}
	
}
