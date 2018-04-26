package clueGame;

/**
 * Card class, represents a card used in Clue. Has a type and name
 * 
 * @author ajson, jasonyu
 *
 */
public class Card {

	private String cardName;
	private CardType type;
	
	public Card(String cardName, int cardType){
		this.cardName = cardName;
		switch (cardType){
			
			case 0:
				type = CardType.WEAPON;
				break;
			case 1:
				type = CardType.ROOM;
				break;
			case 2:
				type = CardType.PERSON;
				break;
		}
		
	}
	
	public CardType getType() {
		// TODO Auto-generated method stub
		return type;
	}

	public String getCardName() {
		// TODO Auto-generated method stub
		return cardName;
	}
	
}
