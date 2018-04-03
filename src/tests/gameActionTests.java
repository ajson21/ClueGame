package tests;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.ComputerPlayer;
import clueGame.Player;

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

		ComputerPlayer tester = new ComputerPlayer("Tester", 8, 17, Color.RED);
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
		
		ComputerPlayer tester = new ComputerPlayer("Tester", 8, 17, Color.RED);
		board.calcTargets(6, 17, 1);
		Set<BoardCell> targets= board.getTargets();
		targets = tester.selectTargets(targets);
		assertEquals(targets.size(), 1);
		

		board.calcTargets(9, 4, 2);
		targets= board.getTargets();
		targets = tester.selectTargets(targets);
		assertEquals(targets.size(), 1);
		

		board.calcTargets(16, 18, 1);
		targets= board.getTargets();
		targets = tester.selectTargets(targets);
		assertEquals(targets.size(), 2);
		
	}
	
	@Test 
	public void selectVisitedTargetComputer(){
		
		
		
	}
	
	@Test
	public void checkAccusation() {
		fail("Not yet implemented");
	}
	
	@Test
	public void disproveAccusation() {
		fail("Not yet implemented");
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
