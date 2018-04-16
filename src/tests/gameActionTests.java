package tests;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.ComputerPlayer;
import clueGame.Player;
import clueGame.Card;

public class gameActionTests {
	
	private static Board board;
	@BeforeClass
	public static void setUp() {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("ClueLayout.csv", "ClueLegend.txt");		
		// Initialize will load BOTH config files 
		board.initialize();
		
	}
	
	/**
	 * Test to check targets size for only non-room locations.
	 * Should pass already as targets is still the same and no need to filter.
	 */
	@Test
	public void selectTargetComputer() {

		ComputerPlayer tester = new ComputerPlayer("Tester", 8, 17, Color.RED, board.getLegend());
		board.calcTargets(8, 17, 1);
		Set<BoardCell> targets= board.getTargets();
		targets = tester.selectTargets(targets);
		assertEquals(targets.size(), 4);

		board.calcTargets(9, 7, 2);
		targets= board.getTargets();
		targets = tester.selectTargets(targets);
		assertEquals(targets.size(), 7);
		
	}
	
	/**
	 * Test to check that unvisited rooms are required targets.
	 * Checks cases for 1 roll, 2 roll, and 2 possible rooms for targets.
	 */
	@Test
	public void selectUnvisitedTargetComputer(){
		
		ComputerPlayer tester = new ComputerPlayer("Tester", 6, 17, Color.RED, board.getLegend());
		board.calcTargets(6, 17, 1);
		Set<BoardCell> targets= board.getTargets();
		targets = tester.selectTargets(targets);
		assertEquals(targets.size(), 1);
		

		board.calcTargets(9, 4, 2);
		targets= board.getTargets();
		targets = tester.selectTargets(targets);
		assertEquals(targets.size(), 1);
		

		board.calcTargets(15, 18, 2);
		targets= board.getTargets();
		targets = tester.selectTargets(targets);
		assertEquals(targets.size(), 2);
		
	}
	
	/**
	 * Test to check that a previously visited room will be included in movement possibilities.
	 * Runs an initial target selection to add a room to visitedRooms list.
	 * Checks that visitedRooms has size of 1, then room is considered in target selection along with walkways.
	 */
	@Test 
	public void selectVisitedTargetComputer(){
		
		ComputerPlayer tester = new ComputerPlayer("Tester", 6, 17, Color.RED, board.getLegend());
		board.calcTargets(6, 17, 1);
		Set<BoardCell> targets= board.getTargets();
		targets = tester.selectTargets(targets);
		assertEquals(targets.size(), 1);
		
		assertEquals(tester.getVisitedRooms().size(), 1);
		board.calcTargets(6, 17, 1);
		targets=tester.selectTargets(targets);
		assertEquals(targets.size(), 4);
		
	}
	
	/**
	 * Test that checks if accusations are made correctly, cards given by player are handled and compared in board
	 * Helper methods only used for this test were created
	 * Design is to compare memory of cards instead of simply names
	 */
	@Test
	public void checkAccusation() {

		ComputerPlayer tester = new ComputerPlayer("Tester", 6, 17, Color.RED, board.getLegend());
		board.clearSolution();
		Card weapon = new Card("Wrench", 0);
		Card room = new Card("Hotbox", 1);
		Card person = new Card("Tester", 2);
		board.addSolution(weapon, room, person);
		
		tester.giveAccusation(weapon,room,person);
		ArrayList<Card> accusation = tester.makeAccusation();
		assertEquals(board.checkAccusation(accusation),true);
		
		Card wrongWeapon = new Card("Knife", 0);
		tester.giveAccusation(wrongWeapon,room,person);
		accusation = tester.makeAccusation();
		assertEquals(board.checkAccusation(accusation),false);
		
		Card wrongRoom = new Card("Dungeon", 1);
		tester.giveAccusation(weapon, wrongRoom, person);
		accusation = tester.makeAccusation();
		assertEquals(board.checkAccusation(accusation),false);

		Card wrongPerson = new Card("tester", 2);
		tester.giveAccusation(weapon, room, wrongPerson);
		accusation = tester.makeAccusation();
		assertEquals(board.checkAccusation(accusation),false);
		
	}
	
	/**
	 * Test method that checks if suggestions have been made correctly
	 * Tests for if the player suggests the room, which is handled in implementation
	 * Checks if suggestion correctly selects cards for suggestions, with unknown cards being either size 1 or >1 for each card type
	 * Handled in implementation
	 */
	@Test
	public void createSuggestion() {
		
		ComputerPlayer tester = new ComputerPlayer("Tester", 7, 4, Color.RED, board.getLegend());
		Card Weapon = new Card("Wrench", 0);
		Card Room = new Card("Hotbox", 1);
		Card Person = new Card("Tester", 2);

		tester.giveSuggestion(Weapon,Room,Person);
		ArrayList<Card> suggestion = tester.createSuggestion(board.getCellAt(tester.getRow(),tester.getColumn()).getInitial());
		assertEquals(suggestion.size(), 3);
		assertEquals(suggestion.contains(Weapon), true);
		assertEquals(suggestion.contains(Room), true);
		assertEquals(suggestion.contains(Person), true);
		
		Card ExtraWeapon = new Card("Knife", 0);
		Card ExtraPerson = new Card("Tester1", 2);
		tester.giveSuggestion(Weapon, Room, ExtraPerson);
		tester.giveSuggestion(ExtraWeapon, Room, Person);
	
		suggestion = tester.createSuggestion(board.getCellAt(tester.getRow(),tester.getColumn()).getInitial());
		assertEquals(suggestion.size(), 3);
		
	}
	
	/**
	 * Test method for checking if disproveSuggestion is correctly implemented
	 * Checks if only one card is present in hand then player will return that card
	 * If multiple cards are present, randomly returns one
	 * Otherwise, returns null object if card is not present
	 */
	@Test
	public void disproveSuggestion() {
		
		ComputerPlayer tester = new ComputerPlayer("Tester", 7, 4, Color.RED, board.getLegend());
		Card Weapon = new Card("Wrench", 0);
		Card Room = new Card("Hotbox", 1);
		Card Person = new Card("Tester", 2);
		Card falseWeapon = new Card("Knife", 0);
		Card falseRoom = new Card("Dungeon", 1);
		Card falsePerson = new Card("Fake Tester", 2);
		
		
		tester.createPlayerDeck(Weapon, Room, Person);
		ArrayList<Card> suggestion = new ArrayList<Card>();
		suggestion.add(falseWeapon);
		suggestion.add(falseRoom);
		suggestion.add(Person);

		Card suggestionResult;
		suggestionResult = tester.disproveSuggestion(suggestion);
		assertEquals(suggestionResult, Person);
		
		//
		suggestion.clear();
		suggestion.add(falseWeapon);
		suggestion.add(Room);
		suggestion.add(Person);
		
		int roomCounter = 0;
		int personCounter = 0;
		
		for(int i = 0; i < 100; i++){
			
			suggestionResult = tester.disproveSuggestion(suggestion);
			
			if(suggestionResult == Room){
				
				roomCounter++;
				
			}
			
			if(suggestionResult == Person){
				
				personCounter++;
				
			}
			
		}
		
		assert(personCounter > 10);
		assert(roomCounter > 10);
		
		suggestion.clear();
		suggestion.add(falseWeapon);
		suggestion.add(falseRoom);
		suggestion.add(falsePerson);
		
		suggestionResult = tester.disproveSuggestion(suggestion);
		assertEquals(suggestionResult, null);
		
		
	}

	/**
	 * Test to check that handling suggestions are done correctly
	 * Randomly creates a suggestion from a computer player, with a hard coded room
	 * Holds counters for if the answer for the handleSuggestion is null or is a card
	 * Implementation details of handleSuggestion in comment block above handleSuggestion method
	 */
	@Test
	public void handleSuggestion() {
		
		
		int nullCounter = 0;
		int cardCounter = 0;
		
		for(int i = 0; i < 100; i++){
			
			board.initialize();
			
			Random rand = new Random();
			
			int playerCounter = rand.nextInt(board.getPlayerList().length - 1) + 1;
			
			ArrayList<Card> suggestion = new ArrayList<Card>();
			suggestion = board.getPlayerList()[1].createSuggestion('H');
			
			Card answer = board.handleSuggestion(suggestion, playerCounter);
			
			if(answer == null){
				
				nullCounter++;
				
			}else{
				
				cardCounter++;
				
			}
			
		}
		
		assert(nullCounter > 1);
		assert(cardCounter > 20);
		
	}

}
