package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import clueGame.CardType;
import clueGame.Card;

/**
 * Base class for representing a player.
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
	
	/**
	 * Method that disproves a suggestion given by an ArrayList<Card>
	 * Checks players deck to see if they have a card in the array list that corresponds to the suggestion
	 * If multiple, randomly chooses one
	 * Else, picks only one
	 * @param suggestion
	 * @return Null if matching cards in player deck, otherwise random* card in both playerdeck and suggestion
	 */
	public Card disproveSuggestion(ArrayList<Card> suggestion) {
		// TODO Auto-generated method stub
		int cardCounter = 0;
		ArrayList<Card> correctCards = new ArrayList<Card>();
		
		for(Card suggestionCard : suggestion){
			
			for(Card playerCard : playerDeck){
				
				if(suggestionCard == playerCard){
					
					cardCounter++;
					correctCards.add(suggestionCard);
					
				}
				
			}
			
		}
		
		if(cardCounter != 0){
			
			Random rand = new Random();
			return correctCards.get(rand.nextInt(correctCards.size()));
			
		}
		
		return null;
		
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

	public ArrayList<Card> createSuggestion(Character initial) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setLocation(int row, int column){
		this.row = row;
		this.column = column;
		
	}
	
	public void returnSuggestion(Card card){
		if(unknownWeaponDeck.contains(card)){
			
			unknownWeaponDeck.remove(card);
			knownCardDeck.add(card);
			
		}else if(unknownPersonDeck.contains(card)){
			
			unknownPersonDeck.remove(card);
			knownCardDeck.add(card);
			
		}else{
			
			unknownRoomDeck.remove(card);
			knownCardDeck.add(card);
			
		}
		
		
	}

	public ArrayList<Card> getUnknownRoomDeck() {
		return unknownRoomDeck;
	}

	public ArrayList<Card> getUnknownWeaponDeck() {
		return unknownWeaponDeck;
	}

	public ArrayList<Card> getUnknownPersonDeck() {
		return unknownPersonDeck;
	}

	public ArrayList<Card> getKnownCardDeck() {
		// TODO Auto-generated method stub
		return knownCardDeck;
	}
	
	
}
