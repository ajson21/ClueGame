package tests;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;
import experiment.IntBoard;
/**
 * 
 * @author ajson, jasonyu
 *
 */
public class IntBoardTests {

	Board board;

	@Before
	public void beforeAll() {
		
		board = Board.getInstance();
		board.setConfigFiles("ClueLayout.csv", "ClueLegend.txt");	
		board.initialize();
		
	}
	/**
	 * Tests for different locations on the board. LTC = left top corner, RBC = right bottom corner.
	 * Sets up a cell and tests to see if the cells around exist. 
	 */

	@Test
	public void testAdjacencyLTC() {
		
		BoardCell cell = board.getCellAt(0, 0);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCellAt(1, 0)));
		assertTrue(testList.contains(board.getCellAt(0, 1)));
		assertEquals(2, testList.size());
		
	}

	@Test
	public void testAdjacencyRBC() {
		
		BoardCell cell = board.getCellAt(23, 22);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCellAt(23, 21)));
		assertTrue(testList.contains(board.getCellAt(22, 22)));
		assertEquals(2, testList.size());
		
	}
/**
 * Tests for Center point and left/right edges. 
 */
	@Test
	public void testAdjacencyCP() {

		BoardCell cell = board.getCellAt(1, 1);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCellAt(0, 1)));
		assertTrue(testList.contains(board.getCellAt(1, 0)));
		assertTrue(testList.contains(board.getCellAt(1, 2)));
		assertTrue(testList.contains(board.getCellAt(2, 1)));
		assertEquals(4, testList.size());
		
	}

	@Test
	public void testAdjacencyLE() {

		BoardCell cell = board.getCellAt(2, 0);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCellAt(1, 0)));
		assertTrue(testList.contains(board.getCellAt(2, 1)));
		assertTrue(testList.contains(board.getCellAt(3, 0)));
		assertEquals(3, testList.size());
		
	}

	@Test
	public void testAdjacencyRE() {
		
		BoardCell cell = board.getCellAt(2, 22);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCellAt(3, 22)));
		assertTrue(testList.contains(board.getCellAt(1, 22)));
		assertTrue(testList.contains(board.getCellAt(2, 21)));
		assertEquals(3, testList.size());
		
	}
	/**
	 * CalcTarget tests: tests to see if travel to target cell via path is possible. Locations tested:
	 * (0,0), (2,2)
	 */
	
	@Test
	public void calcTargetLTC(){
		
		BoardCell cell = board.getCellAt(0, 0);
		board.emptyTargetSets();
		//board.calcTargets(cell, 2);
		Set<BoardCell> testList = board.getTargets();
		/*assertTrue(testList.contains(board.getCellAt(1, 1)));
		assertTrue(testList.contains(board.getCellAt(0, 2)));
		assertTrue(testList.contains(board.getCellAt(2, 0)));
		board.emptyTargetSets();*/
		board.calcTargets(cell, 1);
		testList = board.getTargets();
		assertTrue(testList.contains(board.getCellAt(0, 1)));
		assertTrue(testList.contains(board.getCellAt(1, 0)));
		
	}
	
	@Test 
	public void calcTargetM(){
		
		BoardCell cell = board.getCellAt(2, 2);
		board.emptyTargetSets();
		//board.calcTargets(cell, 2);
		Set<BoardCell> testList = board.getTargets();
		/*assertTrue(testList.contains(board.getCellAt(0, 2)));
		assertTrue(testList.contains(board.getCellAt(1, 3)));
		assertTrue(testList.contains(board.getCellAt(1, 1)));
		assertTrue(testList.contains(board.getCellAt(2, 0)));
		assertTrue(testList.contains(board.getCellAt(3, 1)));
		assertTrue(testList.contains(board.getCellAt(3, 3)));
		board.emptyTargetSets();*/
		board.calcTargets(cell, 1);
		testList = board.getTargets();
		assertTrue(testList.contains(board.getCellAt(1, 2)));
		assertTrue(testList.contains(board.getCellAt(2, 1)));
		assertTrue(testList.contains(board.getCellAt(3, 2)));
		assertTrue(testList.contains(board.getCellAt(2, 3)));
	}

}
