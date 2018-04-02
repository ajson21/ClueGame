package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.Card;
import clueGame.Player;

public class gameSetupTests {

	private static Board board;
	
	@BeforeClass
	public static void setUp() {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("ClueLayout.csv", "ClueLegend.txt");		
		// Initialize will load BOTH config files 
		board.initialize();
		
	}
	
	
	/*
	 * Test to check that player information has been loaded in correctly.
	 * Tests for location, color, and name
	 * Players are stored in a Player array, with playerList[0] being the human player, others being computer players.
	 */
	@Test
	public void loadPlayers() {
		
		Player[] playerList = board.getPlayerList();
		assertEquals(playerList[0].getRow(), 7);
		assertEquals(playerList[0].getColumn(), 9);
		assertEquals(playerList[0].getName(), "Mrs. Scarlet");
		assertEquals(playerList[0].getColor().getRed(), 255);
		assertEquals(playerList[0].getColor().getGreen(), 0);
		assertEquals(playerList[0].getColor().getBlue(), 0);
		
		
		assertEquals(playerList[1].getRow(), 7);
		assertEquals(playerList[1].getColumn(), 12);
		assertEquals(playerList[1].getName(), "Colonel Mustard");
		assertEquals(playerList[1].getColor().getRed(), 255);
		assertEquals(playerList[1].getColor().getGreen(), 255);
		assertEquals(playerList[1].getColor().getBlue(), 0);

		
		
		assertEquals(playerList[2].getRow(), 7);
		assertEquals(playerList[2].getColumn(), 15);
		assertEquals(playerList[2].getName(), "Mrs. White");
		assertEquals(playerList[2].getColor().getRed(), 255);
		assertEquals(playerList[2].getColor().getGreen(), 255);
		assertEquals(playerList[2].getColor().getBlue(), 255);

		assertEquals(playerList[3].getRow(), 12);
		assertEquals(playerList[3].getColumn(), 9);
		assertEquals(playerList[3].getName(), "Mr. Green");
		assertEquals(playerList[3].getColor().getRed(), 0);
		assertEquals(playerList[3].getColor().getGreen(), 255);
		assertEquals(playerList[3].getColor().getBlue(), 0);

		assertEquals(playerList[4].getRow(), 12);
		assertEquals(playerList[4].getColumn(), 12);
		assertEquals(playerList[4].getName(), "Mrs. Peacock");
		assertEquals(playerList[4].getColor().getRed(), 0);
		assertEquals(playerList[4].getColor().getGreen(), 0);
		assertEquals(playerList[4].getColor().getBlue(), 255);

		assertEquals(playerList[5].getRow(), 12);
		assertEquals(playerList[5].getColumn(), 15);
		assertEquals(playerList[5].getName(), "Professor Plum");
		assertEquals(playerList[5].getColor().getRed(), 255);
		assertEquals(playerList[5].getColor().getGreen(), 0);
		assertEquals(playerList[5].getColor().getBlue(), 255);
		
	}

	@Test
	public void createDeck() {
		
		ArrayList<Card> solutionDeck = board.getSolutionDeck();
		ArrayList<Card> roomDeck = board.getRoomDeck();
		ArrayList<Card> weaponDeck = board.getWeaponDeck();
		ArrayList<Card> playerDeck = board.getPlayerDeck();
		ArrayList<Card> gameDeck = board.getGameDeck();
		
		assertEquals(solutionDeck.size(), 3);
		assertEquals(roomDeck.size(), 9);
		assertEquals(weaponDeck.size(), 5);
		assertEquals(playerDeck.size(), 5);
		assertEquals(gameDeck.size(), 19);
		
	}

}
