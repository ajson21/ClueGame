package clueGame;

/**
 * 
 * @author ajson, jasonyu
 *
 */
public class BoardCell {
	
	private int row;
	private int column;
	private DoorDirection Direction;
	private String initial;
	
	public BoardCell(int row, int column, String initial) {
		
		super();
		this.row = row;
		this.column = column;
		this.initial = initial;
		
		if(initial.length()==2){
			
			String temp = initial.substring(1);
			
			if(temp.equals("U")){
				
				Direction = DoorDirection.UP;
				
			}else if(temp.equals("R")){
				
				Direction = DoorDirection.RIGHT;
				
			}else if(temp.equals("D")){
				
				Direction = DoorDirection.DOWN;
				
			}else{
				
				Direction = DoorDirection.LEFT;
				
			}
			
		}
		
	}

	public int getRow() {
		
		return row;
		
	}

	public int getColumn() {
		
		return column;
		
	}

	public boolean isDoorway() {
		// TODO Auto-generated method stub
		if(initial.length() != 1){
			
			if(initial.substring(1, 2).equals("U") || initial.substring(1, 2).equals("R") || initial.substring(1, 2).equals("D") || initial.substring(1, 2).equals("L")){
				
				return true;
				
			}
			
		}
		
		return false;
		
	}

	public DoorDirection getDoorDirection() {
		// TODO Auto-generated method stub
		return Direction;
		
	}

	public char getInitial() {
		// TODO Auto-generated method stub
		return initial.charAt(0);
		
	}
	
}
