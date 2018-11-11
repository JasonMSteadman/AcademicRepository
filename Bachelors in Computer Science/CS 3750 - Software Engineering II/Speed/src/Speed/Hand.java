package Speed;

import java.util.*;

public class Hand {
	
	protected Stack<Card> pile;
	protected Card inHand;
		
	public Hand()
	{
		pile = new Stack<Card>();
		inHand =  new Card(0);
	}
	
	//	Adds a single card
	public boolean addCard(Card input)
	{
		try
		{
			pile.push(input);
		}
		catch(Exception e)
		{
			return false;
		}
		return true;
	}
	
	public void drawCard()
	{
		if(!pile.empty())
			inHand = pile.pop();
	}
	
	//	Take the whole deck
	public Stack<Card> getDeck()
	{
		Stack<Card> temp = pile;
		pile.clear();
		return temp;
	}
	
	//	Adds a group of cards
	public boolean addCard(Stack<Card> input)
	{
		try
		{
			while(!input.empty())
			{
				pile.push(input.pop());
			}
		}
		catch(Exception e)
		{
			return false;
		}
		
		return true;
	}
	
	//	Removes a single card
	public Card playCard()
	{
		Card temp = inHand;
		inHand = new Card(0);
		return temp;
	}
	
	public void replaceCardInHand()
	{
		if(inHand.iValue != 0)
			addCard(playCard());
	}
	
	//	Removes a single card
	public Card removeCard()
	{
		if(!pile.empty())
			return pile.pop();
		
		return null;
	}
	
	//	View the value of the top card
	public int viewTop()
	{
		//	Used to find winner
		if(pile.empty())
			return 0;
		
		return -1;
	}
	
	//	Shuffles cards
	public boolean shuffle()
	{
		try
		{
			Collections.shuffle(pile);
		}
		catch (Exception e)
		{
			return false;
		}
		return true;
	}
	
	//	Empties the hand
	public void emptyHand()
	{
		pile.clear();
	}
}
