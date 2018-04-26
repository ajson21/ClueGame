package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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
	
		// NOTE
		// Loading information and dealing cards is all handled in sub methods called in initialize
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
		assertEquals(playerList[0].getName(), "Ms. Scarlett");
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

	/**
	 * Test for checking deck was created correctly
	 * Checks for size of solution deck, card type decks, and overall game deck
	 * NOTE: due to implementation for dealing decks, game deck that used to hold all subdecks will be empty
	 * Tested in deal decks
	 */
	@Test
	public void createDeck() {
		
		ArrayList<Card> solutionDeck = board.getSolutionDeck();
		ArrayList<Card> roomDeck = board.getRoomDeck();
		ArrayList<Card> weaponDeck = board.getWeaponDeck();
		ArrayList<Card> playerDeck = board.getPlayerDeck();
		
		assertEquals(solutionDeck.size(), 3);
		assertEquals(roomDeck.size(), 9);
		assertEquals(weaponDeck.size(), 5);
		assertEquals(playerDeck.size(), 5);
		
	}
	
	/**
	 * Test to check that cards have been dealt equally* to all players
	 * Human player starts with 1 extra card due to unequal split in deck
	 * Computer players start with 3 cards total
	 * Check for gameDeck to be size 0 to ensure all cards have been dealt
	 * Duplicate case has been handled in implementation, dealt cards are deleted from list
	 */
	@Test
	public void dealDeck(){
		
		Player[] playerList = board.getPlayerList();
		assertEquals(playerList[0].getPlayerDeck().size(), 4);
		assertEquals(playerList[1].getPlayerDeck().size(), 3);
		assertEquals(playerList[2].getPlayerDeck().size(), 3);
		assertEquals(playerList[3].getPlayerDeck().size(), 3);
		assertEquals(playerList[4].getPlayerDeck().size(), 3);
		assertEquals(playerList[5].getPlayerDeck().size(), 3);
		
		Set<Card> deckSet = new HashSet<Card>();
		for(int player = 0; player < 6; player++){
			for(int card = 0; card < playerList[player].getPlayerDeck().size(); card++){
				deckSet.add(playerList[player].getPlayerDeck().get(card));
			}
		}
		
		assertEquals(deckSet.size(), 19);
		
		
		// Test to ensure all cards have been dealt
		ArrayList<Card> gameDeck = board.getGameDeck();
		assertEquals(gameDeck.size(), 0);
		
	}

}
