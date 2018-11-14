import java.util.*;
import java.awt.*;

import javax.swing.*;

public class CharacterTile extends Tile
{
	//Tile symbol (1-9) or (N, E, S, W, C, F)
	protected char	symbol;
	protected static HashMap<Character, Character> hmCharMap;
	
	//Creating a character mapping via hash table to match the English characters to Chinese characters 
	static
	{
		hmCharMap = new HashMap<Character, Character>();
		hmCharMap.put('1','\u4E00');
		hmCharMap.put('2','\u4E8C');
		hmCharMap.put('3','\u4E09');
		hmCharMap.put('4','\u56DB');
		hmCharMap.put('5','\u4E94');
		hmCharMap.put('6','\u516D');
		hmCharMap.put('7','\u4E03');
		hmCharMap.put('8','\u516B');
		hmCharMap.put('9','\u4E5D');
		hmCharMap.put('N','\u5317');
		hmCharMap.put('E','\u6771');
		hmCharMap.put('W','\u897F');
		hmCharMap.put('S','\u5357');
		hmCharMap.put('C','\u4E2D');
		hmCharMap.put('F','\u767C');
		
	}
	//Basic constructor
	public CharacterTile(char symbol)
	{
		this.symbol = symbol;
		setToolTipText(toString());
	}
	
	//Check to see if other's symbol is a match
	public boolean matches(Tile other)
	{
		if(!super.matches(other))
			return false;
		
		try
		{
			CharacterTile temp = (CharacterTile)other;
			return this.symbol == temp.symbol;
		}
		catch(ClassCastException e)
		{
			//If unable to cast other to CharacterTile
			return false;
		}
	}
	
	//Override toString()
	public String toString()
	{
		//If number
		if(Character.isDigit(symbol))
			return "Character " + symbol;
		
		//If Letter
		switch(symbol)
		{
		 	case 'N':
		 		return "North Wind";
				 
		 	case 'E':
		 		return "East Wind";
				 
		 	case 'W':
		 		return "West Wind";
				 
		 	case 'S':
		 		return "South Wind";
				 
		 	case 'C':
		 		return "Red Dragon";
				 
		 	case 'F':
		 		return "Green Dragon";
				 
		 	default:		//In case of bad input/error
		 		return "Error";
		 }
	}
	
	//Converts the CharacterTiles symbol to Chinese
	public String toChinese()
	{
		return hmCharMap.get(symbol).toString();
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D 	g2 = (Graphics2D)g;
		
		Font 		f = new Font("Serif", Font.BOLD, (int)(FACE * .4));
		g2.setFont(f);
		
		g2.setPaint(Color.RED);
		
		if(symbol >= '1' && symbol <= '9')
		{
			//	If number
			char c = '\u842C';
			g.drawString(c + "", (int)(SIZE.width *.6 - (g.getFontMetrics().stringWidth(c +"")/2) ), (int)(SIZE.height * .8 - SIZE.height * .1));
			
			//	Set Chinese character
			g2.setPaint(Color.BLACK);
			g.drawString(toChinese(), (int)(SIZE.width *.6 - (g.getFontMetrics().stringWidth(toChinese())/2) ), (int)(SIZE.height * .35));
		}
		else
		{
			//	Set Chinese character
			f = new Font("Serif", Font.BOLD, (int)(FACE * .7));
			g2.setFont(f);
			if(symbol == 'C')
			{
				g2.setPaint(Color.RED);
			}
			else if(symbol == 'F')
			{
				g2.setPaint(Color.GREEN);
			}
			else
			{
				g2.setPaint(Color.BLACK);
			}
			g.drawString(toChinese(), (int)(SIZE.width *.6 - (g.getFontMetrics().stringWidth(toChinese())/2) ), (int)(SIZE.height * .6));
		}

		//	Set character
 		f = new Font("Serif", Font.BOLD, (int)(FACE * .2));
		g2.setFont(f);
		g2.setPaint(Color.RED);
		g.drawString(symbol + "",(int)(SIZE.width * .9 - (g.getFontMetrics().stringWidth(symbol +"")/2) ), (int)(SIZE.height * .2));
		

	}

	public static void main(String[] args)
	{
		JFrame		frame = new JFrame();
		JPanel		tiles = new JPanel();
		JScrollPane	scroller = new JScrollPane(tiles);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Character Tiles");
		frame.add(scroller);

		// Try something like this if your tiles don't fit on the screen.
		// Replace "tile width" and "tile height" with your values.
		//scroller.setPreferredSize(new Dimension(8 * tile width, 40 + tile height));

		tiles.add(new CharacterTile('1'));
		tiles.add(new CharacterTile('2'));
		tiles.add(new CharacterTile('3'));
		tiles.add(new CharacterTile('4'));
		tiles.add(new CharacterTile('5'));
		tiles.add(new CharacterTile('6'));
		tiles.add(new CharacterTile('7'));
		tiles.add(new CharacterTile('8'));
		tiles.add(new CharacterTile('9'));
		tiles.add(new CharacterTile('N'));
		tiles.add(new CharacterTile('E'));
		tiles.add(new CharacterTile('W'));
		tiles.add(new CharacterTile('S'));
		tiles.add(new CharacterTile('C'));
		tiles.add(new CharacterTile('F'));

		frame.pack();
		frame.setVisible(true);
	}
}
