package clueGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;
import java.util.Random;

import javax.swing.JPanel;

/**
 * BoardCell class, represents one cell on a board. Used in Board class Contains
 * information for each individual BoardCell that is accessed in Board
 * 
 * @author Alan Son, Jason Yu
 *
 */
public class BoardCell extends JPanel{

	private int row;
	private int column;
	private DoorDirection Direction;
	private String initial;

	/**
	 * Constructor of Boardcell Only gives value to DoorDirection of initial of
	 * room is of length 2
	 * 
	 * @param row
	 *            Row of cell
	 * @param column
	 *            Column of cell
	 * @param initial
	 *            String value of cell obtained from input file
	 */
	public BoardCell(int row, int column, String initial) {

		super();
		
		this.row = row;
		this.column = column;
		this.initial = initial;

		if (initial.length() == 2) {

			String temp = initial.substring(1);

			if (temp.equals("U")) {

				Direction = DoorDirection.UP;

			} else if (temp.equals("R")) {

				Direction = DoorDirection.RIGHT;

			} else if (temp.equals("D")) {

				Direction = DoorDirection.DOWN;

			} else if (temp.equals("L")) {

				Direction = DoorDirection.LEFT;

			} else {

				Direction = DoorDirection.NONE;
			}

		}

	}

	/**
	 * Getter for row of current cell
	 * 
	 * @return Row
	 */
	public int getRow() {

		return row;

	}

	/**
	 * Getter for column of current cell
	 * 
	 * @return Column
	 */
	public int getColumn() {

		return column;

	}

	/**
	 * Method to return if cell is a doorway. Only considers initials of length
	 * 2
	 * 
	 * @return True if cell is a doorway, otherwise false
	 */
	public boolean isDoorway() {
		// TODO Auto-generated method stub
		if (initial.length() != 1) {

			if (initial.substring(1, 2).equals("U") || initial.substring(1, 2).equals("R")
					|| initial.substring(1, 2).equals("D") || initial.substring(1, 2).equals("L")) {

				return true;

			}

		}

		return false;

	}

	/**
	 * Getter for DoorDirection of cell
	 * 
	 * @return DoorDirection Direction
	 */
	public DoorDirection getDoorDirection() {
		// TODO Auto-generated method stub
		return Direction;

	}

	/**
	 * Getter for the initial of the room, only returns first character if room
	 * is a doorway
	 * 
	 * @return Char for initial of room
	 */
	public char getInitial() {
		// TODO Auto-generated method stub
		return initial.charAt(0);

	}

	public void drawTarget(Graphics g, int row, int column){
		
		int multiplier = 25;
		g.setColor(Color.PINK);
		g.fillRect(row*multiplier, column*multiplier, multiplier, multiplier);
		
		
	}
	
	/**
	 * Method to draw each BoardCell with the current graphics object.
	 * Cases for both walkways and rooms.
	 * Also draws borders between rooms correctly.
	 * 
	 * @param g Graphics object shared by board
	 * @param row Row of BoardCell to be drawn
	 * @param column Column of BoardCell to be drawn
	 * @param walkway Switch if BoardCell is a walkway
	 * @param name Switch if the BoardCell to be drawn needs to display room name
	 * @param player Switch if the BoardCell to be drawn contains a player
	 * @param playerNumber Color codes players
	 * @param legend Names of rooms
	 */
	public void draw(Graphics g, int row, int column, boolean walkway, boolean name, boolean player, int playerNumber, Map<Character, String> legend) {
		
		int multiplier = 25;

		if(player){
			
			switch(playerNumber){
			
				case 0:
					
					g.setColor(Color.RED);
					break;
					
				case 1:
					
					g.setColor(Color.YELLOW);
					break;
					
				case 2:

					g.setColor(Color.LIGHT_GRAY);
					break;
					
				case 3:

					g.setColor(Color.GREEN);
					break;
					
				case 4:

					g.setColor(Color.BLUE);
					break;
					
				case 5:

					g.setColor(Color.MAGENTA);
					break;
		
			}
			
			g.fillOval(row*multiplier, column*multiplier, multiplier, multiplier);
			
			
		} else if (!walkway) {

			g.setColor(Color.GRAY);
			g.fillRect(row * multiplier, column * multiplier, multiplier, multiplier);

		} else {

			if (!name) {

				if (initial.equalsIgnoreCase("W")) {

					g.setColor(Color.WHITE);
					g.fillRect(row * multiplier, column * multiplier, multiplier, multiplier);
					g.setColor(Color.BLACK);
					g.drawRect(row * multiplier, column * multiplier, multiplier, multiplier);

				}

				if (isDoorway()) {

					switch (Direction) {

					// Code block for drawing doorways. 
					
					case UP:

						g.setColor(Color.BLUE);
						g.drawLine(row * multiplier, column * multiplier,
								row * multiplier + multiplier, column * multiplier);
						g.drawLine(row * multiplier, column * multiplier + 1,
								row * multiplier + multiplier, column * multiplier + 1);
						g.drawLine(row * multiplier, column * multiplier + 2,
								row * multiplier + multiplier, column * multiplier + 2);
						g.drawLine(row * multiplier, column * multiplier + 3,
								row * multiplier + multiplier, column * multiplier + 3);
						g.drawLine(row * multiplier, column * multiplier + 4,
								row * multiplier + multiplier, column * multiplier + 4);
						g.drawLine(row * multiplier, column * multiplier + 5,
								row * multiplier + multiplier, column * multiplier + 5);

						break;

					case DOWN:

						g.setColor(Color.BLUE);
						g.drawLine(row * multiplier, column * multiplier + multiplier,
								row * multiplier + multiplier,
								column * multiplier + multiplier);
						g.drawLine(row * multiplier, column * multiplier + multiplier - 1,
								row * multiplier + multiplier,
								column * multiplier + multiplier - 1);
						g.drawLine(row * multiplier, column * multiplier + multiplier - 2,
								row * multiplier + multiplier,
								column * multiplier + multiplier - 2);
						g.drawLine(row * multiplier, column * multiplier + multiplier - 3,
								row * multiplier + multiplier,
								column * multiplier + multiplier - 3);
						g.drawLine(row * multiplier, column * multiplier + multiplier - 4,
								row * multiplier + multiplier,
								column * multiplier + multiplier - 4);
						g.drawLine(row * multiplier, column * multiplier + multiplier - 5,
								row * multiplier + multiplier,
								column * multiplier + multiplier - 5);

						break;

					case RIGHT:

						g.setColor(Color.BLUE);
						g.drawLine(row * multiplier + multiplier, column * multiplier,
								row * multiplier + multiplier,
								column * multiplier + multiplier);
						g.drawLine(row * multiplier + multiplier - 1, column * multiplier,
								row * multiplier + multiplier - 1,
								column * multiplier + multiplier);
						g.drawLine(row * multiplier + multiplier - 2, column * multiplier,
								row * multiplier + multiplier - 2,
								column * multiplier + multiplier);
						g.drawLine(row * multiplier + multiplier - 3, column * multiplier,
								row * multiplier + multiplier - 3,
								column * multiplier + multiplier);
						g.drawLine(row * multiplier + multiplier - 4, column * multiplier,
								row * multiplier + multiplier - 4,
								column * multiplier + multiplier);
						g.drawLine(row * multiplier + multiplier - 5, column * multiplier,
								row * multiplier + multiplier - 5,
								column * multiplier + multiplier);

						break;

					case LEFT:

						g.setColor(Color.BLUE);
						g.drawLine(row * multiplier, column * multiplier, row * multiplier,
								column * multiplier + multiplier);
						g.drawLine(row * multiplier + 1, column * multiplier,
								row * multiplier + 1, column * multiplier + multiplier);
						g.drawLine(row * multiplier + 2, column * multiplier,
								row * multiplier + 2, column * multiplier + multiplier);
						g.drawLine(row * multiplier + 3, column * multiplier,
								row * multiplier + 3, column * multiplier + multiplier);
						g.drawLine(row * multiplier + 4, column * multiplier,
								row * multiplier + 4, column * multiplier + multiplier);
						g.drawLine(row * multiplier + 5, column * multiplier,
								row * multiplier + 5, column * multiplier + multiplier);

						break;

					}

				}

			} else {

				if (initial.length() == 2) {

					if (initial.charAt(1) == 'N') {

						String roomName = legend.get(getInitial());
						char[] nameArray = roomName.toCharArray();

						g.setColor(Color.BLUE);
						g.drawChars(nameArray, 0, nameArray.length, row*multiplier, column*multiplier);

					}

				}

			}

		}

	}
	
}
