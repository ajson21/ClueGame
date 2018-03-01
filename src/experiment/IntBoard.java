package experiment;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
/**
 * 
 * @author ajson, jasonyu
 * IntBoard class, 
 */
public class IntBoard {
	
	private HashMap<BoardCell, Set<BoardCell>> adjMtx;
	private BoardCell[][] grid;
	private Set<BoardCell> targets = new HashSet<BoardCell>();
	private Set<BoardCell> visited = new HashSet<BoardCell>();
/**
 * IntBoard represents the game board, with different cells. Travel is only possible in compass directions (no diagonals)
 */
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

	/**
	 * Calculates adjacencies for each boardcell. Follows rules for edges and corners
	 * @return Hashmap with boardcell as key and adjacent boardcells as set
	 */
	public HashMap<BoardCell, Set<BoardCell>>calcAdjacencies(){
		
		HashMap<BoardCell, Set<BoardCell>> result = new HashMap<BoardCell, Set<BoardCell>>();
		
		for(int i = 0; i < 4; i++){
			
			for(int j = 0; j < 4; j++){
				
				BoardCell current = grid[i][j];
				Set<BoardCell> currentSet = new HashSet<BoardCell>();
	
				for(int r = -1; r < 2; r+=2){
					
					if(!(current.getRow() + r == -1 || current.getRow() + r == 4)){

						currentSet.add(grid[current.getRow()+r][current.getColumn()]);
						
					}
					
				}
				
				for(int c = -1; c < 2; c+=2){
					
					if(!(current.getColumn() + c == -1 || current.getColumn() + c == 4)){

						currentSet.add(grid[current.getRow()][current.getColumn()+c]);
						
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
	/**
	 * Calculates targets of specificed boardcell with specified length
	 * Follows pseudocode
	 * @param startCell
	 * @param pathLength
	 */
	public void calcTargets(BoardCell startCell, int pathLength){
		
		for(BoardCell adjCell: adjMtx.get(startCell)){
			
			if(!visited.contains(adjCell)){
				
				visited.add(adjCell);
				
				if(pathLength == 1){
					
					targets.add(adjCell);
				
				} else {
					
					calcTargets(adjCell,pathLength--);
				
				}
				
			}
			
			visited.remove(adjCell);
			
		}
		
	}
	
	public void emptyTargetSets(){
		targets.clear();
		visited.clear();
	}
	
	public Set<BoardCell> getTargets(){
		
		return targets;
		
	}
	
	public BoardCell getCell(int row, int column){
		
		return grid[row][column];
		
	}
	
}
