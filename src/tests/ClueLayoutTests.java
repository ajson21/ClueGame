/**
 * 
 * @author ajson, jasonyu
 *
 */
package tests;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.DoorDirection;

public class ClueLayoutTests {

	public static final int LEGEND_SIZE = 11;
	public static final int NUM_ROWS = 24;
	public static final int NUM_COLUMNS = 23;
	public static final int NUM_DOORS = 26;

	private static Board board;
	
	/**
	 * Test for setting up board, read in files and initialize.
	 */
	@BeforeClass
	public static void setUp() {
		
		board = Board.getInstance();
		board.setConfigFiles("ClueLayout.csv", "ClueLegend.txt");		
		board.initialize();
		
	}
	
	/**
	 * Test the legend, make sure that the initials stand for what they should be standing for. 
	 * Test dimensions of board, make sure the board dimensions are what they should be. 
	 * Test doorway directions/number, make sure doors are working. 
	 */
	
	
	@Test
	public void testLegend(){

		Map<Character, String> legend = board.getLegend();
		
		assertEquals("Hotbox", legend.get('H'));
		assertEquals("Gameroom", legend.get('G'));
		assertEquals("Dungeon", legend.get('D'));
		assertEquals("Theater", legend.get('T'));
		assertEquals("Bar", legend.get('B'));
		
	}
	
	@Test 
	public void testDimensions(){
		assertEquals(board.getNumRows(), NUM_ROWS);
		assertEquals(board.getNumColumns(), NUM_COLUMNS);
	}
	
	@Test
	public void testDoorwayDirections(){
		
		BoardCell room = board.getCellAt(11, 5);
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.RIGHT, room.getDoorDirection());
		
		room = board.getCellAt(18, 3);
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.UP, room.getDoorDirection());

		room = board.getCellAt(7, 3);
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.DOWN, room.getDoorDirection());

		room = board.getCellAt(19, 9);
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.LEFT, room.getDoorDirection());

	}

	
	@Test
	public void testNumberDoors(){
		
		int numDoors = 0;
		
		for (int i=0; i<board.getNumRows(); i++)
			
			for (int j=0; j<board.getNumColumns(); j++) {
				
				BoardCell cell = board.getCellAt(i, j);
				
				if (cell.isDoorway())
					
					numDoors++;
				
			}
		
		assertEquals(NUM_DOORS, numDoors);
		
	}
	
	@Test
	public void testInitial(){

		assertEquals('W', board.getCellAt(9, 0).getInitial());
		assertEquals('T', board.getCellAt(23, 22).getInitial());
		assertEquals('G', board.getCellAt(0, 16).getInitial());
		assertEquals('A', board.getCellAt(1, 22).getInitial());
		assertEquals('C', board.getCellAt(23, 14).getInitial());
		
	}
	
}
