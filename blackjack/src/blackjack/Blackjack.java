package blackjack;
import java.util.*;




public class Blackjack implements BlackjackEngine 
{
	//declaring instance variables, a random object, and array lists
	
	private int account, betAmount, decks, gameStatus;
	private Random randomNum;
	ArrayList<Card> deck, player, dealer;
	
/**
	 * Constructor you must provide.  Initializes the player's account 
	 * to 200 and the initial bet to 5.  Feel free to initialize any other
	 * fields. Keep in mind that the constructor does not define the 
	 * deck(s) of cards.
	 * @param randomGenerator
	 * @param numberOfDecks
	 */
	public Blackjack(Random randomGenerator, int numberOfDecks)//Constructor initializes instance variables, random number, and array lists.
	{
		 this.account = 200;
		 this.betAmount = 5;
		 decks = numberOfDecks;
		 randomNum = randomGenerator;
		 deck = new ArrayList<Card>();
		 player = new ArrayList<Card>();
		 dealer = new ArrayList<Card>();
	}
	
	//returns the number of decks.
	public int getNumberOfDecks() 
	{
		return decks;
	}
	
	//creates a deck in the required order and shuffles it using a random number.
	public void createAndShuffleGameDeck()
	{
		for (int i = 0; i < decks; i++)// accounts for multiple decks
		{
		  for (CardSuit suit: CardSuit.values())//accounts for all suits
		  {
			  for (CardValue val : CardValue.values())//accounts for all values.
			  {
				  deck.add(new Card(val, suit));//adds cards to ArrayList deck
			  }
				
		  }
		}
		
		Collections.shuffle(deck, randomNum);//shuffles the deck using a random number.
	}
	
	//calls the toArray() private method to convert the deck array list to an array and returns the array.
	public Card[] getGameDeck()
	{
		return toArray(deck);
	}
	
	//deals cards to the player and dealer
	public void deal()
	{
		//sets the status of the game to "game in progress".
		gameStatus = GAME_IN_PROGRESS;
		
		deck.clear();//clears any existing deck to start with an empty deck
		player.clear();//clears the player's hand to start with a new hand
		dealer.clear();//clears the dealer's hand to start with a new hand
		createAndShuffleGameDeck();// creates and shuffles the deck/decks of cards.
		
		//gives first and third cards from the deck to the player
		player.add(deck.get(0));
		player.add(deck.get(2));
		
		//gives second and fourth cards from the deck to the dealer and sets the dealer's first card as faced down. 
	    dealer.add(deck.get(1));
	    dealer.get(0).setFaceDown();
	    dealer.add(deck.get(3));
	    
	  //subtracts the betAmount from account as the player places his/her bet.
		account -= betAmount;
		
		for(int i = 0; i < 4; i++)
		{
			deck.remove(0);//removes the dealt cards from the deck
		}
	}
		
	//calls the toArray() private method to convert the dealer array list to an array and returns the array.
	public Card[] getDealerCards() 
	{
		return toArray(dealer);
	}

	//calls the getCardsTotal() private method to get an array of the possible values of the dealer's cards and returns it.
	public int[] getDealerCardsTotal()
	{
		return getCardsTotal(dealer);
	}

	//calls the getCardsEvaluation() private method to evaluate the dealer's cards and returns an integer according to the result.
	public int getDealerCardsEvaluation()
	{
		return getCardsEvaluation(dealer, getDealerCardsTotal());
    }
	
	//calls the toArray() private method to convert the player array list to an array and returns the array.
	public Card[] getPlayerCards()
	{
		return toArray(player);
	}
	
	//calls the getCardsTotal() private method to get an array of the possible values of the player's cards and returns it.
	public int[] getPlayerCardsTotal()
	{
		return getCardsTotal(player);
	}
		
	//calls the getCardsEvaluation() private method to evaluate the player's cards and returns an integer according to the result.
	public int getPlayerCardsEvaluation()
	{
		return getCardsEvaluation(player, getPlayerCardsTotal());
	}
	
	//gives the player a card and updates the status of the game according to the value of the card given.
	public void playerHit()
	{
		 player.add(deck.get(0));
		 deck.remove(0);//removes the dealt card from the deck.
		 
		 if (getPlayerCardsEvaluation() == BUST)
		 {
			 gameStatus = DEALER_WON;
			dealer.get(0).setFaceUp();
		 }
		 else
		 {
			gameStatus = GAME_IN_PROGRESS;
		 }
	} 
	
	//reveals dealer's hand and evaluates the winner.
	public void playerStand()
	{
		dealer.get(0).setFaceUp();//reveals the dealer's card that was faced down
		
		while (getDealerCardsEvaluation() != BUST && getDealerCardsTotal()[getDealerCardsTotal().length-1] < 16)
		{
			dealer.add(deck.get(0));
			deck.remove(0);
		}
		
		//checks whose value of cards is greater.
		if (getDealerCardsEvaluation() == BUST || (getDealerCardsTotal()[getDealerCardsTotal().length-1] < getPlayerCardsTotal()[getPlayerCardsTotal().length-1]))
		{
			gameStatus = PLAYER_WON;
			account += (2 * betAmount);
	    }
		
		else if (getDealerCardsTotal()[getDealerCardsTotal().length-1] > getPlayerCardsTotal()[getPlayerCardsTotal().length-1])
		{
			gameStatus = DEALER_WON;
		}
		
		else
		{
			gameStatus = DRAW;
			account += betAmount;
		}
		
	}
	
	//returns the status of the game.
	public int getGameStatus() 
	{
		return gameStatus;
	}
		
	//sets the betAmount to the amount inputed.
	public void setBetAmount(int amount)
	{
		betAmount = amount;
	}
	
	//returns the amount of the bet.
	public int getBetAmount()
	{
		return betAmount;
	}
	
	//sets the player's account to the amount inputed.
	public void setAccountAmount(int amount)
	{	
		account = amount;
	}
	
	//returns the amount in the player's account.
	public int getAccountAmount()
	{
		return account;
	}
	
	//converts a Card type array list to a Card[] array and returns it.
	private Card[] toArray(ArrayList<Card> arrayList)
	{
		Card[] cardArray = new Card[arrayList.size()];
		
		for (int i=0; i<arrayList.size(); i++)
		{
			cardArray[i] = arrayList.get(i); //copies elements of the array list to elements of the array.
		}
		
		return cardArray;
	}
	
	//Converts an Integer type array list to an int[] array and returns it.
	private int[] toIntArray(ArrayList<Integer> arrayList)
	{
		int[] array = new int[arrayList.size()];
		
		for (int i=0; i<arrayList.size(); i++)
		{
			array[i] = arrayList.get(i); //copies elements of the array list to elements of the array.
		}
		
		return array;
	}
	
	//returns an array of the possible values of the dealer or player's cards.
	private int[] getCardsTotal(ArrayList<Card> cards)
	{
		//initializing variables and an array list.
		ArrayList<Integer> cardsTotal = new ArrayList<Integer>();
		int total = 0;
	
		//calculates the sum of the values of the dealer or player's cards
		for (int i = 0; i < cards.size(); i++)
		{
			total += cards.get(i).getValue().getIntValue();
		}
		
		cardsTotal.add(total);//adds the sum of the values to the array list.
		
		//checks if there are any Aces in the player or dealer's hand and if it is less than or equal to 21.
		for (int i=0; i<cards.size(); i++)
		{
			if (cards.get(i).getValue().getIntValue() == 1 && (total+10) <= 21)
			{
				cardsTotal.add(total+10);
				total += 10;
			}
		}
		
		int[] cardsTotalArray = toIntArray(cardsTotal);//converts cardsTotal array list to cardsTotalArray array.
		
		if (cardsTotalArray[0] > 21)
		{
			return null;// returns null if the smallest possible value of the hand are greater than 21.
		}
		else
		{
			return cardsTotalArray;
		}
		
	}
	
	//evaluates the dealer or player's cards and returns an integer according to the result.
	private int getCardsEvaluation(ArrayList<Card> cards, int[] cardsTotal)
	{
		int rv, checkBlackjack = 0;
		
		//checks to see if the dealer's hand is only made up of ACE, 10, Jack, Queen, or King for blackjack.
		if (cardsTotal != null && cardsTotal[cardsTotal.length-1] == 21)
		{
			for (int i = 0; i < cards.size(); i++)
			{
				if (cards.get(i).getValue().getIntValue() ==1 || cards.get(i).getValue().getIntValue() == 10)
	            {
					checkBlackjack++;
	            }
			}
		}
		
		if (cardsTotal == null)//returns BUST if the dealer's hand is above 21.
		{
			rv = BUST;
		}
		else if (checkBlackjack == cards.size())//returns BLACKJACK if the dealer's hand has a blackjack.
		{
		    rv = BLACKJACK;
		}
		else if (cardsTotal[0] == 21)//returns HAS_21 if the dealer has 21 but not blackjack.
		{
		    rv = HAS_21;
		}
		else   //otherwise, it returns LESS_THAN_21 for the dealer's hand being below 21.
		{
			rv = LESS_THAN_21;
		}
		
		return rv;
	}
	
	
	/* Feel Free to add any private methods you might need */
}