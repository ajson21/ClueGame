package tests;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;

public class gameSetupTests {

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
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
