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
	
	public BoardCell(int row, int column) {
		super();
		this.row = row;
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public boolean isDoorway() {
		// TODO Auto-generated method stub
		return false;
	}

	public Object getDoorDirection() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getInitial() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
