package clueGame;

/**
 * BoardCell class, represents one cell on a board.
 * Used in Board class
 * Contains information for each individual BoardCell that is accessed in Board
 * @author Alan Son, Jason Yu
 *
 */
public class BoardCell {
	
	private int row;
	private int column;
	private DoorDirection Direction;
	private String initial;
	
	/**
	 * Constructor of Boardcell
	 * Only gives value to DoorDirection of initial of room is of length 2
	 * @param row Row of cell
	 * @param column Column of cell
	 * @param initial String value of cell obtained from input file
	 */
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

	/**
	 * Getter for row of current cell
	 * @return Row
	 */
	public int getRow() {
		
		return row;
		
	}

	/**
	 * Getter for column of current cell
	 * @return Column
	 */
	public int getColumn() {
		
		return column;
		
	}

	/**
	 * Method to return if cell is a doorway.
	 * Only considers initials of length 2
	 * @return True if cell is a doorway, otherwise false
	 */
	public boolean isDoorway() {
		// TODO Auto-generated method stub
		if(initial.length() != 1){
			
			if(initial.substring(1, 2).equals("U") || initial.substring(1, 2).equals("R") || initial.substring(1, 2).equals("D") || initial.substring(1, 2).equals("L")){
				
				return true;
				
			}
			
		}
		
		return false;
		
	}

	/**
	 * Getter for DoorDirection of cell
	 * @return DoorDirection Direction
	 */
	public DoorDirection getDoorDirection() {
		// TODO Auto-generated method stub
		return Direction;
		
	}

	/**
	 * Getter for the initial of the room, only returns first character if room is a doorway
	 * @return Char for initial of room
	 */
	public char getInitial() {
		// TODO Auto-generated method stub
		return initial.charAt(0);
		
	}
	
}
