package clueGame;

public class Card {

	private String cardName;
	private Enum Type;
	
	public Card(String cardName, int cardType){
		this.cardName = cardName;
		switch (cardType){
			
			case 0:
				Type = CardType.WEAPON;
				break;
			case 1:
				Type = CardType.ROOM;
				break;
			case 2:
				Type = CardType.PERSON;
				break;
		}
		
	}
	
	public boolean equals(){
		return false;
	}
	
}
