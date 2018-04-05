package clueGame;

import java.awt.Color;
import java.io.FileNotFoundException;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import clueGame.BoardCell;
/**
 * Board Class, contains a grid of BoardCells to maintain board of the game
 * Calculates adjacent cells for each board cell and calculates targets for possible movement
 * @author Alan Son, Jason Yu
 *
 */
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
	
	//Deck used by game, read in by initialize
	private ArrayList<Card> solutionDeck = new ArrayList<Card>();
	private ArrayList<Card> roomDeck = new ArrayList<Card>();
	private ArrayList<Card> playerDeck = new ArrayList<Card>();
	private ArrayList<Card> weaponDeck = new ArrayList<Card>();
	private ArrayList<Card> gameDeck = new ArrayList<Card>();


	// Array of players, set to 6 total players
	private Player[] playerList = new Player[6];
	
	
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
		
		loadPlayers();
		loadDecks();
		dealDecks();
		
		
	}
	
	/**
	 * Loads player information, preset with default values
	 */
	private void loadPlayers(){
		
		// Creating new human player
		playerList[0] = new HumanPlayer("Mrs. Scarlet", 7, 9, Color.RED);
				
		// Creating computer players
		playerList[1] = new ComputerPlayer("Colonel Mustard", 7, 12, Color.YELLOW, legend);
		playerList[2] = new ComputerPlayer("Mrs. White", 7, 15, Color.WHITE, legend);
		playerList[3] = new ComputerPlayer("Mr. Green", 12, 9, Color.GREEN, legend);
		playerList[4] = new ComputerPlayer("Mrs. Peacock", 12, 12, Color.BLUE, legend);
		playerList[5] = new ComputerPlayer("Professor Plum", 12, 15, Color.MAGENTA, legend);
		
	}
	
	/**
	 * Loads different decks for usage in game
	 * Initializes weapon, character, and room decks, then one from each is selected to be used in solution deck
	 * Remaining are added to overall game deck
	 */
	private void loadDecks(){
		// Loading weapons
		weaponDeck.add(new Card("Candlestick", 0));
		weaponDeck.add(new Card("Lead Pipe", 0));
		weaponDeck.add(new Card("Knife", 0));
		weaponDeck.add(new Card("Rope", 0));
		weaponDeck.add(new Card("Revolver", 0));
		weaponDeck.add(new Card("Wrench", 0));

		// Loading rooms
		for(Character current : legend.keySet()){

			if(legend.get(current).equalsIgnoreCase("Walkway") || legend.get(current).equalsIgnoreCase("Closet")){

			} else {

				roomDeck.add(new Card(legend.get(current), 1)); 

			}

		}

		// Loading players
		for(Player current : playerList){

			playerDeck.add(new Card(current.getName(), 2));

		}

		Random rand = new Random();
		int weaponValue = rand.nextInt(weaponDeck.size());
		int playerValue = rand.nextInt(playerDeck.size());
		int roomValue = rand.nextInt(roomDeck.size());

		solutionDeck.add(weaponDeck.get(weaponValue));
		solutionDeck.add(playerDeck.get(playerValue));
		solutionDeck.add(roomDeck.get(roomValue));

		weaponDeck.remove(weaponValue);
		playerDeck.remove(playerValue);
		roomDeck.remove(roomValue);

		for(Card current: weaponDeck){
			
			gameDeck.add(current);
			
		}

		for(Card current: playerDeck){
			
			gameDeck.add(current);
			
		}

		for(Card current: roomDeck){
			
			gameDeck.add(current);
			
		}
		
	}

	/**
	 * Helper method to correctly deal all cards present in game deck to players equally
	 * Game deck will be empty at end, information still retained in subdecks still present
	 */
	private void dealDecks(){
		
		int currentPlayerToDeal = 0;
		
		while(gameDeck.size() > 0){

			if(currentPlayerToDeal == 6){
				
				currentPlayerToDeal = 0;
				
			}
			
			Random rand = new Random();
			int cardIndexToDeal = rand.nextInt(gameDeck.size());
			playerList[currentPlayerToDeal].addCard(gameDeck.get(cardIndexToDeal));
			gameDeck.remove(cardIndexToDeal);
			currentPlayerToDeal++;
			
		}
		
	}
	
	/**
	 * Method for calculating adjacencies for each board cell in grid
	 * Considers room/walkway/doorway adjacency cases
	 * @return HashMap of BoardCell to Set containing all adjacencies
	 */
	public HashMap<BoardCell, Set<BoardCell>> calcAdjacencies() {

		HashMap<BoardCell, Set<BoardCell>> result = new HashMap<BoardCell, Set<BoardCell>>();

		// Initial for loop for each grid, calculates adjacencies without walkway/room case. Handled later
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

				// Code block to filter adjacency cases
				for (BoardCell added : currentSet) {

					boolean ableToAdd = false;

					// Walkway case
					if (current.getInitial() == 'W') {

						if (added.getInitial() == 'W') {

							ableToAdd = true;

						} else if (added.getInitial() != 'W') {

							if (added.isDoorway()) {

								DoorDirection addedDoorDirection = added.getDoorDirection();

								switch (addedDoorDirection) {

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

					} else if (current.getInitial() == 'X') {
						
						if (added.getInitial() != 'X'){
							
							ableToAdd = true;
							
						}
						
					} else if (current.getInitial() != 'W') {

						if (current.isDoorway()) {

							DoorDirection currentDoorDirection = current.getDoorDirection();

							switch (currentDoorDirection) {

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

				result.put(current, currentSet);

			}

		}

		return result;

	}

	/**
	 * Getter for adjacency list given a cell
	 * @param x Row of cell
	 * @param y Column of cell
	 * @return Set of adjacent cells
	 */
	public Set<BoardCell> getAdjList(int x, int y) {

		Set<BoardCell> result = new HashSet<BoardCell>();

		result = adjMtx.get(getCellAt(x, y));

		return result;

	}

	/**
	 * Primary method for calculating targets. Makes subcall to a recursive function 
	 * @param row Row of cell
	 * @param column Column of cell
	 * @param pathLength Initial number of moves left
	 */
	public void calcTargets(int row, int column, int pathLength) {

		targets.clear();
		visited.clear();
		visited.add(getCellAt(row, column));
		recursiveCalcTargets(row, column, pathLength);

	}

	/**
	 * Recursive method for calcTargets. 
	 * @param row Row of cell
	 * @param column Column of cell
	 * @param pathLength Number of moves left to make
	 */
	public void recursiveCalcTargets(int row, int column, int pathLength) {

		for (BoardCell adjCell : adjMtx.get(getCellAt(row, column))) {

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

	/**
	 * Getter for possible targets, called after calculating targets
	 * @return Returns target sets
	 */
	public Set<BoardCell> getTargets() {

		return targets;

	}

	/**
	 * Setter for input files
	 * @param csv_file Name of csv_file to be used 
	 * @param legend_file Name of legend_file to be used
	 */
	public void setConfigFiles(String csv_file, String legend_file) {

		this.csv_file = csv_file;
		this.legend_file = legend_file;

	}
	
	/**
	 * Basic getter for the legend map of the game, room type and initial stored in a map
	 * @return Map containing initial to room name
	 */
	public Map<Character, String> getLegend() {

		return legend;

	}

	/**
	 * Basic getter for number of rows in grid
	 * @return Number of rows in grid
	 */
	public int getNumRows() {

		return grid.length;

	}

	/**
	 * Basic getter for number of columns in grid
	 * @return Number of columns based on first row
	 */
	public int getNumColumns() {

		return grid[0].length;

	}

	/**
	 * 
	 * @param row Row of cell
	 * @param column Column of cell
	 * @return Returns BoardCell object at desired row and column
	 */
	public BoardCell getCellAt(int row, int column) {

		return grid[row][column];

	}

	/**
	 * 
	 * @param row Row of cell
	 * @param column Column of cell
	 * @return Returns true if the selected board cell is a valid room, otherwise false if it is the closet or walkway
	 */
	public boolean isRoom(int row, int column) {
		
		char roomInitial = grid[row][column].getInitial();
		
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
			
			FileReader fileReader = new FileReader(legend_file);
			Scanner inputReader = new Scanner(fileReader);
			inputReader.useDelimiter(",");

			while (inputReader.hasNext()) {
				
				String input = inputReader.nextLine();
				String[] roomNames = input.split(", ");

				if (roomNames[2].equalsIgnoreCase("Card") || roomNames[2].equalsIgnoreCase("Other")) {

				} else {
					
					throw new BadConfigFormatException("Incorrect legend configuration");
					
				}

				legend.put(roomNames[0].charAt(0), roomNames[1]);
				
			}
			
			inputReader.close();

		} catch (FileNotFoundException e) {
			
			System.out.println("Detected legend file is not present in directory, please check that it is named correctly");
			
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
			
			FileReader fileReader = new FileReader(csv_file);
			Scanner inputReader = new Scanner(fileReader);
			ArrayList<String> input = new ArrayList<String>();

			int previous_col_elements = 0;

			inputReader.useDelimiter(",");

			int col_counter = 0;
			int row_counter = 0;

			while (inputReader.hasNext()) {

				String rowInput = inputReader.nextLine();
				String roomInitials[] = rowInput.split(",");

				col_counter = roomInitials.length;

				if (row_counter == 0) {
					
					previous_col_elements = col_counter;
					
				} else {
					
					if (previous_col_elements != col_counter) {
						
						throw new BadConfigFormatException("Inconsisent column elements.");
						
					}
					
				}

				row_counter++;

				for (int i = 0; i < roomInitials.length; i++) {

					boolean existsInLegend = false;

					for (Character key : legend.keySet()) {

						if (roomInitials[i].charAt(0) == key) {
							
							existsInLegend = true;
							
						}

					}

					if (!existsInLegend) {
						
						throw new BadConfigFormatException("Detected room not in legend.");
						
					}

					input.add(roomInitials[i]);
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
			System.out.println("Detected board file is not present in directory, please check that it is named correctly");
		}

	}

	public Player[] getPlayerList() {
		// TODO Auto-generated method stub
		return playerList;
	}

	public ArrayList<Card> getPlayerDeck() {
		// TODO Auto-generated method stub
		return playerDeck;
	}
	
	public ArrayList<Card> getWeaponDeck() {
		// TODO Auto-generated method stub
		return weaponDeck;
	}
	
	public ArrayList<Card> getRoomDeck() {
		// TODO Auto-generated method stub
		return roomDeck;
	}
	
	public ArrayList<Card> getSolutionDeck() {
		// TODO Auto-generated method stub
		return solutionDeck;
	}

	public ArrayList<Card> getGameDeck() {
		// TODO Auto-generated method stub
		return gameDeck;
	}

	public void clearSolution() {
		// TODO Auto-generated method stub
		solutionDeck.clear();
		
	}

	public void addSolution(Card weapon, Card room, Card person) {
		// TODO Auto-generated method stub
		solutionDeck.add(weapon);
		solutionDeck.add(room);
		solutionDeck.add(person);
		
	}

	public boolean checkAccusation(ArrayList<String> accusation) {
		// TODO Auto-generated method stub
		return false;
	}


}