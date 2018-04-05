package clueGame;

import java.awt.Color;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ComputerPlayer extends Player{
	
	Set<Character> visitedRooms = new HashSet<Character>();
	Set<Character> unvisitedRooms = new HashSet<Character>();

	public ComputerPlayer(String playerName, int row, int column, Color color, Map<Character, String> legend) {
		super(playerName, row, column, color);
		// TODO Auto-generated constructor stub
		
		for(Character room : legend.keySet()){
			
			if(room=='X' || room=='W'){
				
			}else{

				unvisitedRooms.add(room);
				
			}
			
			
		}
		
	}

	public Set<BoardCell> selectTargets(Set<BoardCell> targets){
		
		boolean filter = false;
		
		for(BoardCell target : targets){
			
			if(unvisitedRooms.contains(target.getInitial())){
				
				filter = true;
				
			}
			
		}
		
		if(filter){
			
			Set<BoardCell> toBeRemoved = new HashSet<BoardCell>();
			
			for(BoardCell target: targets){
				
				if(!unvisitedRooms.contains(target.getInitial())){
					
					toBeRemoved.add(target);
					
				}
				
			}
			
			for(BoardCell removed: toBeRemoved){
				
				targets.remove(removed);
				
			}
			
		}
		
		return targets;
		
	}
	
}
