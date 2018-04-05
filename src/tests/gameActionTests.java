package tests;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;
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
	
	
	@Test
	public void handleSuggestion() {
		fail("Not yet implemented");
	}
	
	@Test
	public void createSuggestion() {
		fail("Not yet implemented");
	}

}
