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
	public void testAdjacencyRBC()
	{
		BoardCell cell = board.getCell(3,3);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(3, 2)));
		assertTrue(testList.contains(board.getCell(2, 3)));
		assert(2, testList.size());
	}
	@Test
	public void testAdjacencyCP()
	{
		BoardCell cell = board.getCell(1,1);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(0, 1)));
		assertTrue(testList.contains(board.getCell(1, 0)));
		assertTrue(testList.contains(board.getCell(1, 2)));
		assertTrue(testList.contains(board.getCell(2, 1)));
		assert(2, testList.size());
	}
	@Test
	public void testAdjacencyLE()
	{
		BoardCell cell = board.getCell(2,0);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(1, 0)));
		assertTrue(testList.contains(board.getCell(2, 1)));
		assertTrue(testList.contains(board.getCell(3, 0)));
		assert(2, testList.size());
	}
	@Test
	public void testAdjacencyRE()
	{
		BoardCell cell = board.getCell(2,3);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(3, 3)));
		assertTrue(testList.contains(board.getCell(1, 3)));
		assertTrue(testList.contains(board.getCell(2, 2)));
		assert(2, testList.size());
	}
}
