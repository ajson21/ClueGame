/**
 * 
 * @author ajson, jasonyu
 * Board class, currently a skeleton. 
 */
package clueGame;

import java.util.HashMap;
import java.util.Map;

public class Board {

	private BoardCell[][] grid;
	//variable used for singleton pattern
	private static Board theInstance = new Board();
	
	// constructor is private to ensure only one can be created	
	private Board() {}
	
	// this method returns the only Board
	public static Board getInstance() {
		return theInstance;
	}
	
	public void initialize(){
		
	}
	
	public void setConfigFiles(String x, String y){
		
	}
	
	public Map<Character, String> getLegend(){
		
		return new HashMap<Character,String>();
		
	}
	
	public int getNumRows(){
		return 0;
	}

	public int getNumColumns(){
		return 0;
	}

	public BoardCell getCellAt(int i, int j) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}