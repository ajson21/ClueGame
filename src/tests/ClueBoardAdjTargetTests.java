package tests;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;

public class ClueBoardAdjTargetTests {
	
	private static Board board;
	@BeforeClass
	public static void setUp() {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("CTest_ClueLayout.csv", "CTest_ClueLegend.txt");		
		// Initialize will load BOTH config files 
		board.initialize();
		System.out.println(board);
		
	}
	
	
	@Test
	public void adjWalkway(){

		Set<BoardCell> testList = board.getAdjList(8, 7);
		assertEquals(4, testList.size());
		assertTrue(testList.contains(board.getCellAt(8, 8)));
		assertTrue(testList.contains(board.getCellAt(8, 6)));
		assertTrue(testList.contains(board.getCellAt(9, 7)));
		assertTrue(testList.contains(board.getCellAt(7, 7)));
		
	}
	
	@Test
	public void insideRoom(){
		
		Set<BoardCell> testList = board.getAdjList(1, 1);
		assertEquals(0, testList.size());
		
	}
	
	@Test
	public void insideDoorway1(){
		
		Set<BoardCell> testList = board.getAdjList(7, 3);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(8, 3)));
		
	}
	
	@Test
	public void insideDoorway2(){
		
		Set<BoardCell> testList = board.getAdjList(15, 16);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(14, 16)));
		
	}
	
	@Test
	public void rightEdge(){

		Set<BoardCell> testList = board.getAdjList(12, 22);
		assertEquals(3, testList.size());
		assertTrue(testList.contains(board.getCellAt(12, 21)));
		assertTrue(testList.contains(board.getCellAt(11, 22)));
		assertTrue(testList.contains(board.getCellAt(13, 22)));
		
	}
	
	@Test
	public void leftEdge(){

		Set<BoardCell> testList = board.getAdjList(9, 0);
		assertEquals(3, testList.size());
		assertTrue(testList.contains(board.getCellAt(9, 1)));
		assertTrue(testList.contains(board.getCellAt(8, 0)));
		assertTrue(testList.contains(board.getCellAt(10, 0)));
		
	}
	
	@Test
	public void topEdge(){

		Set<BoardCell> testList = board.getAdjList(0, 6);
		assertEquals(2, testList.size());
		assertTrue(testList.contains(board.getCellAt(0, 7)));
		assertTrue(testList.contains(board.getCellAt(1, 6)));
		
	}
	
	@Test 
	public void bottomEdge(){

		Set<BoardCell> testList = board.getAdjList(23, 12);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(22, 12)));
		
	}
	
	@Test
	public void outsideRoom1(){

		Set<BoardCell> testList = board.getAdjList(5, 11);
		assertEquals(3, testList.size());
		assertTrue(testList.contains(board.getCellAt(5, 10)));
		assertTrue(testList.contains(board.getCellAt(5, 12)));
		assertTrue(testList.contains(board.getCellAt(6, 11)));
		
	}
	
	public void outsideRoom2(){

		Set<BoardCell> testList = board.getAdjList(16, 22);
		assertEquals(3, testList.size());
		assertTrue(testList.contains(board.getCellAt(15, 22)));
		assertTrue(testList.contains(board.getCellAt(16, 23)));
		assertTrue(testList.contains(board.getCellAt(16, 21)));
		
	}
	
	@Test
	public void doorwayAdjR(){

		Set<BoardCell> testList = board.getAdjList(14, 6);
		assertEquals(4, testList.size());
		assertTrue(testList.contains(board.getCellAt(14, 7)));
		assertTrue(testList.contains(board.getCellAt(14, 5)));
		assertTrue(testList.contains(board.getCellAt(13, 6)));
		assertTrue(testList.contains(board.getCellAt(15, 6)));
	}
	
	@Test
	public void doorwayAdjL(){
	
		Set<BoardCell> testList = board.getAdjList(1, 7);
		assertEquals(4, testList.size());
		assertTrue(testList.contains(board.getCellAt(1, 8)));
		assertTrue(testList.contains(board.getCellAt(1, 6)));
		assertTrue(testList.contains(board.getCellAt(0, 7)));
		assertTrue(testList.contains(board.getCellAt(2, 7)));
		
	}
	
	@Test
	public void doorwayAdjU(){

		Set<BoardCell> testList = board.getAdjList(17, 4);
		assertEquals(4, testList.size());
		assertTrue(testList.contains(board.getCellAt(18, 4)));
		assertTrue(testList.contains(board.getCellAt(16, 4)));
		assertTrue(testList.contains(board.getCellAt(17, 5)));
		assertTrue(testList.contains(board.getCellAt(17, 3)));
		
	}
	
	@Test
	public void doorwayAdjD(){
		
		Set<BoardCell> testList = board.getAdjList(6, 17);
		assertEquals(4, testList.size());
		assertTrue(testList.contains(board.getCellAt(6, 18)));
		assertTrue(testList.contains(board.getCellAt(6, 16)));
		assertTrue(testList.contains(board.getCellAt(7, 17)));
		assertTrue(testList.contains(board.getCellAt(5, 17)));
		
	}
	
	@Test
	public void walkwayTargets(){

		board.calcTargets(9, 5, 1);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(4, targets.size());
		assertTrue(targets.contains(board.getCellAt(9, 6)));
		assertTrue(targets.contains(board.getCellAt(9, 4)));
		assertTrue(targets.contains(board.getCellAt(8, 5)));
		assertTrue(targets.contains(board.getCellAt(10, 5)));
		

		board.calcTargets(10, 6, 2);
		targets=board.getTargets();
		assertEquals(5, targets.size());
		assertTrue(targets.contains(board.getCellAt(12, 6)));
		assertTrue(targets.contains(board.getCellAt(8, 6)));
		assertTrue(targets.contains(board.getCellAt(10, 4)));
		assertTrue(targets.contains(board.getCellAt(10, 8)));
		assertTrue(targets.contains(board.getCellAt(9, 5)));
		assertTrue(targets.contains(board.getCellAt(9, 7)));
		assertTrue(targets.contains(board.getCellAt(11, 5)));
		assertTrue(targets.contains(board.getCellAt(11, 7)));
		

		board.calcTargets(13, 7, 3);
		targets=board.getTargets();
		assertEquals(11, targets.size());
		assertTrue(targets.contains(board.getCellAt(10, 7)));
		assertTrue(targets.contains(board.getCellAt(11, 6)));
		assertTrue(targets.contains(board.getCellAt(12, 5)));
		assertTrue(targets.contains(board.getCellAt(13, 4)));
		assertTrue(targets.contains(board.getCellAt(14, 5)));
		assertTrue(targets.contains(board.getCellAt(15, 6)));
		assertTrue(targets.contains(board.getCellAt(16, 7)));
		assertTrue(targets.contains(board.getCellAt(15, 8)));
		assertTrue(targets.contains(board.getCellAt(14, 9)));
		assertTrue(targets.contains(board.getCellAt(13, 10)));
		assertTrue(targets.contains(board.getCellAt(11, 8)));

		board.calcTargets(0, 18, 4);
		targets=board.getTargets();
		assertEquals(1, targets.size());
		assertTrue(targets.contains(board.getCellAt(4, 18)));
	}
	
	@Test
	public void roomTargets(){
		
		board.calcTargets(7, 3, 1);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(1, targets.size());
		assertTrue(targets.contains(board.getCellAt(8, 3)));
		
		board.calcTargets(15, 14, 2);
		targets= board.getTargets();
		assertEquals(1, targets.size());
		assertTrue(targets.contains(board.getCellAt(14, 14)));
		
	}
	
	@Test
	public void leaveRoomTargets(){
		
		board.calcTargets(5, 17, 1);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(1, targets.size());
		assertTrue(targets.contains(board.getCellAt(6, 17)));
		

		board.calcTargets(20, 7, 1);
		targets = board.getTargets();
		assertEquals(1, targets.size());
		assertTrue(targets.contains(board.getCellAt(20,8)));
		
		
		
	}
	
}
