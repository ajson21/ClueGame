package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import clueGame.CardType;
import clueGame.Card;

/**
 * 
 * @author ajson, jasonyu
 *
 */
public class Player {

	private String playerName;
	private int row;
	private int column;
	private Color color;
	protected ArrayList<Card> playerDeck = new ArrayList<Card>();
	protected ArrayList<Card> unknownWeaponDeck = new ArrayList<Card>();
	protected ArrayList<Card> unknownPersonDeck = new ArrayList<Card>();
	protected ArrayList<Card> unknownRoomDeck = new ArrayList<Card>();
	protected ArrayList<Card> knownCardDeck = new ArrayList<Card>();
	
	/**
	 * 
	 * @param playerName
	 * @param row
	 * @param column
	 * @param color
	 */
	public Player(String playerName, int row, int column, Color color) {
		super();
		this.playerName = playerName;
		this.row = row;
		this.column = column;
		this.color = color;
		
	}

	/**
	 * 
	 * @param card
	 */
	public void addCard(Card card){
		playerDeck.add(card);
	}
	
	public int getRow() {
		// TODO Auto-generated method stub
		return row;
	}

	public int getColumn() {
		// TODO Auto-generated method stub
		return column;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return playerName;
	}

	public Color getColor() {
		// TODO Auto-generated method stub
		return color;
	}

	public ArrayList<Card> getPlayerDeck() {
		// TODO Auto-generated method stub
		return playerDeck;
	}

	public void addToUnknownWeapon(Card card) {
		// TODO Auto-generated method stub
		unknownWeaponDeck.add(card);
		
	}
	
	public void addToUnknownPerson(Card card) {
		// TODO Auto-generated method stub
		unknownPersonDeck.add(card);
		
	}
	
	public void addToUnknownRoom(Card card) {
		// TODO Auto-generated method stub
		unknownRoomDeck.add(card);
		
	}

}
