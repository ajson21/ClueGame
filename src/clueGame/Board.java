/**
 * 
 * @author ajson, jasonyu
 * Board class, currently a skeleton. 
 */
package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import clueGame.BoardCell;

public class Board {

	private BoardCell[][] grid;
	//variable used for singleton pattern
	private static Board theInstance = new Board();
	
	private String csv_file;
	private String legend_file; 
	private HashMap<BoardCell, Set<BoardCell>> adjMtx;
	private Set<BoardCell> targets = new HashSet<BoardCell>();
	private Set<BoardCell> visited = new HashSet<BoardCell>();
	
	private Map<Character,String> legend = new HashMap<Character,String>();
	
	// constructor is private to ensure only one can be created	
	private Board() {}
	
	// this method returns the only Board
	public static Board getInstance() {
		return theInstance;
	}
	
	public void initialize(){
		
		try{
			FileReader reader = new FileReader(legend_file);
			Scanner in = new Scanner(reader);
			in.useDelimiter(",");
			
			while(in.hasNext()){
				String input = in.nextLine();
				String[] temp = input.split(", ");
				
				legend.put(temp[0].charAt(0),temp[1]);
			}
			
			
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
		
		try {
			FileReader reader = new FileReader(csv_file);
			Scanner in = new Scanner(reader);
			ArrayList<String> input = new ArrayList<String>();
			
			in.useDelimiter(",");
			
			int col_counter = 0;
			int row_counter = 0;
			
			while(in.hasNext()){
				
				String temp = in.nextLine();
				row_counter++;
				String sep[] = temp.split(",");
				col_counter = sep.length;
				
				for(int i = 0; i < sep.length; i++){
					input.add(sep[i]);
				}
				
			}
		
			grid = new BoardCell[row_counter][col_counter];
			int counter = 0;
			for(int i = 0; i < row_counter; i++){
				for(int j = 0; j < col_counter; j++){
					grid[i][j] = new BoardCell(i,j,input.get(counter));
					counter++;
				}
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	public HashMap<BoardCell, Set<BoardCell>>calcAdjacencies(){
		
		HashMap<BoardCell, Set<BoardCell>> result = new HashMap<BoardCell, Set<BoardCell>>();
		
		for(int i = 0; i < grid.length; i++){
			
			for(int j = 0; j < grid[0].length; j++){
				
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
	public void setConfigFiles(String x, String y){
		csv_file = x;
		legend_file = y;
	}
	
	public Map<Character, String> getLegend(){
		
		return legend;
		
	}
	
	public int getNumRows(){
		return grid.length;
	}

	public int getNumColumns(){
		return grid[0].length;
	}

	public BoardCell getCellAt(int i, int j) {
		return grid[i][j];
	}

	public void loadRoomConfig() {
		
		
	}

	public void loadBoardConfig() {
		
		
	}
	
	
}