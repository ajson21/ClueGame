package tests;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

import experiment.BoardCell;
import experiment.IntBoard;

public class IntBoardTests{

	@Before public void beforeAll(){
		IntBoard board = new IntBoard();
	}
	
	@Test
	public void testAdjacencyLTC()
	{
		BoardCell cell = board.getCell(0,0);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(1, 0)));
		assertTrue(testList.contains(board.getCell(0, 1)));
		assert(2, testList.size());
	}
	@Test
	public void testAdjacencyRTC()
	{
		BoardCell cell = board.getCell(0,3);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(0, 2)));
		assertTrue(testList.contains(board.getCell(1, 3)));
		assertEquals(2, testList.size());
	}
	@Test
	public void testAdjacencyLBC()
	{
		BoardCell cell = board.getCell(3,0);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(2, 0)));
		assertTrue(testList.contains(board.getCell(3, 1)));
		assertEquals(2, testList.size());
	}
	@Test
	public void testAdjacencyRBC()
	{
		BoardCell cell = board.getCell(3,3);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(3, 2)));
		assertTrue(testList.contains(board.getCell(2, 3)));
		assertEquals(2, testList.size());
	}
}
