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
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import clueGame.BoardCell;

public class Board {

	private BoardCell[][] grid;
	// variable used for singleton pattern
	private static Board theInstance = new Board();

	private String csv_file;
	private String legend_file;

	private HashMap<BoardCell, Set<BoardCell>> adjMtx;
	private Set<BoardCell> targets = new HashSet<BoardCell>();
	private Set<BoardCell> visited = new HashSet<BoardCell>();
	private Map<Character, String> legend = new HashMap<Character, String>();

	// constructor is private to ensure only one can be created
	private Board() {
	}

	// this method returns the only Board
	public static Board getInstance() {
		return theInstance;
	}

	/**
	 * Initialize calls loadRoomConfig and loadBoardConfig, which both have the
	 * exceptions needed.
	 */
	public void initialize() {

		try {
			loadRoomConfig();
			loadBoardConfig();
		} catch (BadConfigFormatException e) {
			System.out.println(e.getMessage());
		}

	}

	public HashMap<BoardCell, Set<BoardCell>> calcAdjacencies() {

		HashMap<BoardCell, Set<BoardCell>> result = new HashMap<BoardCell, Set<BoardCell>>();

		for (int i = 0; i < grid.length; i++) {

			for (int j = 0; j < grid[i].length; j++) {

				BoardCell current = grid[i][j];
				Set<BoardCell> currentSet = new HashSet<BoardCell>();

				for (int r = -1; r < 2; r += 2) {

					if (!(current.getRow() + r == -1 || current.getRow() + r == grid.length)) {

						currentSet.add(grid[current.getRow() + r][current.getColumn()]);

					}

				}

				for (int c = -1; c < 2; c += 2) {

					if (!(current.getColumn() + c == -1 || current.getColumn() + c == grid[0].length)) {

						currentSet.add(grid[current.getRow()][current.getColumn() + c]);

					}

				}

				Set<BoardCell> toBeRemoved = new HashSet<BoardCell>();

				for (BoardCell added : currentSet) {

					boolean ableToAdd = false;

					// Walkway case
					if (current.getInitial() == 'W') {

						if (added.getInitial() == 'W') {

							ableToAdd = true;

						} else if (added.getInitial() != 'W') {

							if (added.isDoorway()) {

								DoorDirection temp = added.getDoorDirection();

								switch (temp) {

								case RIGHT:

									if (added.getColumn() + 1 == current.getColumn()) {

										ableToAdd = true;

									}

									break;

								case LEFT:
									
									if (added.getColumn() - 1 == current.getColumn()) {

										ableToAdd = true;

									}

									break;

								case UP:
									
									if (added.getRow() - 1 == current.getRow()) {

										ableToAdd = true;

									}

									break;

								case DOWN:
									
									if (added.getRow() + 1 == current.getRow()) {

										ableToAdd = true;

									}

									break;

								}

							}

						}

					} else if (current.getInitial() != 'W') {

						if (current.isDoorway()) {

							DoorDirection temp = current.getDoorDirection();

							switch (temp) {

							case RIGHT:
								
								if (added.getColumn() - 1 == current.getColumn()) {
									
									ableToAdd = true;
									
								}
								
								break;
								
							case LEFT:
								
								if (added.getColumn() + 1 == current.getColumn()) {
									
									ableToAdd = true;
									
								}
								
								break;
								
							case UP:
								
								if (added.getRow() + 1 == current.getRow()) {
									
									ableToAdd = true;
									
								}
								
								break;
								
							case DOWN:
								
								if (added.getRow() - 1 == current.getRow()) {
									
									ableToAdd = true;
									
								}
								
								break;

							}

						}

					}

					if (!ableToAdd) {

						toBeRemoved.add(added);

					}

				}

				for (BoardCell remove : toBeRemoved) {

					currentSet.remove(remove);

				}

				// System.out.println(current.getRow() + " " +
				// current.getColumn() + " " + currentSet.size());

				result.put(current, currentSet);

			}

		}

		return result;

	}

	public Set<BoardCell> getAdjList(int x, int y) {

		Set<BoardCell> result = new HashSet<BoardCell>();

		result = adjMtx.get(getCellAt(x, y));

		return result;

	}

	public void calcTargets(int x, int y, int pathLength) {

		emptyTargetSets();
		visited.add(getCellAt(x, y));
		recursiveCalcTargets(x, y, pathLength);

	}

	public void recursiveCalcTargets(int x, int y, int pathLength) {

		for (BoardCell adjCell : adjMtx.get(getCellAt(x, y))) {

			if (!visited.contains(adjCell)) {

				visited.add(adjCell);

				if (pathLength == 1) {

					targets.add(adjCell);

				} else if (adjCell.isDoorway()) {

					targets.add(adjCell);

				} else {

					recursiveCalcTargets(adjCell.getRow(), adjCell.getColumn(), pathLength - 1);

				}

				visited.remove(adjCell);

			}

		}

	}

	public void emptyTargetSets() {

		targets.clear();
		visited.clear();

	}

	public Set<BoardCell> getTargets() {

		return targets;

	}

	public void setConfigFiles(String x, String y) {

		csv_file = x;
		legend_file = y;

	}

	public Map<Character, String> getLegend() {

		return legend;

	}

	public int getNumRows() {

		return grid.length;

	}

	public int getNumColumns() {

		return grid[0].length;

	}

	public BoardCell getCellAt(int i, int j) {

		return grid[i][j];

	}

	public boolean isRoom(int i, int j) {
		
		char roomInitial = grid[i][j].getInitial();
		
		if (roomInitial == 'X' || roomInitial == 'W') {
			
			return false;
			
		}
		
		return true;

	}

	/**
	 * loadRoomConfig tests to see that all symbols in legend are consistent and
	 * the symbols in the board match up.
	 * 
	 * @throws BadConfigFormatException
	 */
	public void loadRoomConfig() throws BadConfigFormatException {

		try {
			FileReader reader = new FileReader(legend_file);
			Scanner in = new Scanner(reader);
			in.useDelimiter(",");

			while (in.hasNext()) {
				String input = in.nextLine();
				String[] temp = input.split(", ");

				if (temp[2].equalsIgnoreCase("Card") || temp[2].equalsIgnoreCase("Other")) {

				} else {
					throw new BadConfigFormatException("Incorrect legend configuration");
				}

				legend.put(temp[0].charAt(0), temp[1]);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Ensures that the columns and rows are of consistent size, if a column is
	 * missing an element, something is wrong.
	 * 
	 * @throws BadConfigFormatException
	 */
	public void loadBoardConfig() throws BadConfigFormatException {

		try {
			FileReader reader = new FileReader(csv_file);
			Scanner in = new Scanner(reader);
			ArrayList<String> input = new ArrayList<String>();

			int previous_col_elements = 0;

			in.useDelimiter(",");

			int col_counter = 0;
			int row_counter = 0;

			while (in.hasNext()) {

				String temp = in.nextLine();
				String sep[] = temp.split(",");

				col_counter = sep.length;

				if (row_counter == 0) {
					previous_col_elements = col_counter;
				} else {
					if (previous_col_elements != col_counter) {
						throw new BadConfigFormatException("Inconsisent column elements.");
					}
				}

				row_counter++;

				for (int i = 0; i < sep.length; i++) {

					boolean existsInLegend = false;

					for (Character key : legend.keySet()) {

						if (sep[i].charAt(0) == key) {
							existsInLegend = true;
						}

					}

					if (!existsInLegend) {
						throw new BadConfigFormatException("Detected room not in legend.");
					}

					input.add(sep[i]);
				}

			}

			grid = new BoardCell[row_counter][col_counter];
			int counter = 0;
			for (int i = 0; i < row_counter; i++) {
				for (int j = 0; j < col_counter; j++) {
					grid[i][j] = new BoardCell(i, j, input.get(counter));
					counter++;
				}
			}

			adjMtx = new HashMap<BoardCell, Set<BoardCell>>();
			adjMtx = calcAdjacencies();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}