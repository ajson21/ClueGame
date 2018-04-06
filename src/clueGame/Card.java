package clueGame;

public class Card {

	private String cardName;
	private Enum type;
	
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
	
	public boolean equals(){
		return false;
	}

	public Enum getType() {
		// TODO Auto-generated method stub
		return type;
	}

	public String getCardName() {
		// TODO Auto-generated method stub
		return cardName;
	}
	
}
