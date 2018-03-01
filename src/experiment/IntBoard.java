package experiment;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class IntBoard {
	
	private HashMap<BoardCell, Set<BoardCell>> adjMtx;
	private BoardCell[][] grid;
	private Set<BoardCell> targets;
	private Set<BoardCell> visited;

	public IntBoard() {
		super();
		
		grid = new BoardCell[4][4];
		for(int i = 0; i < 4; i++){
			
			for(int j = 0; j < 4; j++){
				
				grid[i][j] = new BoardCell(i,j);
				
			}
			
		}
		
		adjMtx = new HashMap<BoardCell,Set<BoardCell>>();
		adjMtx = calcAdjacencies();
		
		
		
	}
	
	public HashMap<BoardCell, Set<BoardCell>>calcAdjacencies(){
		
		HashMap<BoardCell, Set<BoardCell>> result = new HashMap<BoardCell, Set<BoardCell>>();
		
		for(int i = 0; i < 4; i++){
			
			for(int j = 0; j < 4; j++){
				
				BoardCell current = grid[i][j];
				System.out.println(current);
				Set<BoardCell> currentSet = new HashSet<BoardCell>();
	
				for(int r = -1; r < 2; r+=2){
					
					if(!(current.getRow() + r == -1 || current.getRow() + r == 4)){
						
						currentSet.add(new BoardCell(current.getRow() + r,current.getColumn()));
						
					}
					
				}
				
				for(int c = -1; c < 2; c+=2){
					
					if(!(current.getColumn() + c == -1 || current.getColumn() + c == 4)){
						
						currentSet.add(new BoardCell(current.getRow(),current.getColumn()+c));
						
					}
					
				}
				
				result.put(current,currentSet);
				
			}
			
		}
		
		return result;
		
	}
	
	public Set<BoardCell> getAdjList(BoardCell cell){
		
		Set<BoardCell> result = new HashSet<BoardCell>();
		
		result = adjMtx.get(cell);
		
		return result;
		
		
		
	}
	
	public void calctargets(int startCell, int pathLength){
		
	}
	
	public void getTargets(){
		
	}
	
	public BoardCell getCell(int row, int column){
		
		return grid[row][column];
		
	}
	
}
