import java.awt.*;
import java.awt.event.*;
import java.util.Collections;
import java.util.Stack;
import javax.swing.*;
import javax.swing.border.*;

import javax.swing.*;

public class MahJongBoard extends JPanel implements MouseListener
{
	private final int		XMAX = 15;
	private final int		YMAX = 8;
	private final int 		ZMAX = 4;
	private Dimension 		SIZE; 
	private Tile first, 	second;
	private MahJongModel	model;
	
	public MahJongBoard()
	{
		Tile t 					= new Tile();
		Dimension tileSize 		= t.getSize();
		SIZE 					= new Dimension((int)(tileSize.width * 13.8), (int)(tileSize.height * 8.2));
		
		setPreferredSize(SIZE);
		//setSize(SIZE);
		setLayout(null);
		
		setBackground(Color.YELLOW);
		
		Stack<Tile> tiles = new Stack<Tile>();
		
		//	Create deck
		for(int i = 0; i < 4; ++i)
		{
			tiles.push(new CharacterTile('1'));
			tiles.push(new CharacterTile('2'));
			tiles.push(new CharacterTile('3'));
			tiles.push(new CharacterTile('4'));
			tiles.push(new CharacterTile('5'));
			tiles.push(new CharacterTile('6'));
			tiles.push(new CharacterTile('7'));
			tiles.push(new CharacterTile('8'));
			tiles.push(new CharacterTile('9'));
			tiles.push(new CharacterTile('N'));
			tiles.push(new CharacterTile('E'));
			tiles.push(new CharacterTile('S'));
			tiles.push(new CharacterTile('W'));
			tiles.push(new CharacterTile('C'));
			tiles.push(new CharacterTile('F'));
			
			tiles.push(new CircleTile(1));
			tiles.push(new CircleTile(2));
			tiles.push(new CircleTile(3));
			tiles.push(new CircleTile(4));
			tiles.push(new CircleTile(5));
			tiles.push(new CircleTile(6));
			tiles.push(new CircleTile(7));
			tiles.push(new CircleTile(8));
			tiles.push(new CircleTile(9));
			
			tiles.push(new BambooTile(2));
			tiles.push(new BambooTile(3));
			tiles.push(new BambooTile(4));
			tiles.push(new BambooTile(5));
			tiles.push(new BambooTile(6));
			tiles.push(new BambooTile(7));
			tiles.push(new BambooTile(8));
			tiles.push(new BambooTile(9));
			
			
			tiles.push(new Bamboo1Tile());
			tiles.push(new WhiteDragonTile());
		}
		
		tiles.push(new FlowerTile("Chrysanthemum"));
		tiles.push(new FlowerTile("Orchid"));
		tiles.push(new FlowerTile("Plum"));
		tiles.push(new FlowerTile("Bamboo"));

		tiles.push(new SeasonTile("Spring"));
		tiles.push(new SeasonTile("Summer"));
		tiles.push(new SeasonTile("Fall"));
		tiles.push(new SeasonTile("Winter"));
		//	Shuffle tiles
		Collections.shuffle(tiles);
		
		model = new MahJongModel(tiles);
		
		
		//	Add layer 1
		for(int z = ZMAX - 1; z >= 0; --z)
		{
			//int z = 0;
			for(int x = 0; x < XMAX; ++x)	
			{
				for(int y = YMAX - 1; y >= 0; --y)
				{
					if(model.getTile(x,y,z) != null)
					{
						t = model.getTile(x, y, z);
						if(z == 3)
							t.setLocation((int)((tileSize.width * .5) +.8 * tileSize.width * 7 + tileSize.width * .5) ,(int)(.8 * tileSize.height * 4 - tileSize.height * .2));
						else if(x == 0 || x > 12)
							t.setLocation((int)(.8 * tileSize.width * (x + 1)) ,(int)(.8 * tileSize.height * (y+ 1) - tileSize.height * .5));
						else if(z == 0)
							t.setLocation((int)(.8 * tileSize.width * (x + 1)) ,(int)(.8 * tileSize.height * (y+ 1)));
						else if (z == 1)
							t.setLocation((int)((tileSize.width * .2) + .8 *tileSize.width * (x + 1)) ,(int)(.8 * tileSize.height * (y+ 1) - (tileSize.width * .2) ));
						else if(z == 2)
							t.setLocation((int)((tileSize.width * .4) + .8 *tileSize.width * (x + 1)) ,(int)( .8 * tileSize.height * (y+ 1) - (tileSize.width * .4) ));
						
						t.addMouseListener(this);
						add(t);
					}
				}
			}
		} 
		validate();
		setVisible(true);
		
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		ImageIcon		image;
		String			imgName = "images/dragon_bg.png";

		image = new ImageIcon(imgName);
			
		image = new ImageIcon(image.getImage().getScaledInstance( (int) (SIZE.width * .8), -1 , Image.SCALE_SMOOTH));
		
		g.drawImage(image.getImage(), (int)((SIZE.width - image.getIconWidth()) / 2) ,(int)((SIZE.height - image.getIconHeight()) / 2), this);
		
	}
	
	//	Helper class meant to organize the boards data structure
	public class MahJongModel 
	{
		private Tile[][][] model;
		
		public MahJongModel(Stack<Tile> tiles)
		{
			model = new Tile[YMAX][XMAX][ZMAX];		
			
			//	Load default value
			for(int z = 0; z < ZMAX; ++z)
			{
				for(int y = 0; y < YMAX; ++y)
				{
					for(int x = 0; x <XMAX; ++x)
						model[y][x][z] = null;
				}
			}
			
			//	Load layer one into model
			for(int y = 0; y < YMAX; ++y)
			{
				for(int x = 0; x < XMAX; ++x)
				{
					if((y == 0 || y == 3 || y == 7))
					{
						if(x == 0)
							x = 1;
						if(x == 13)
							break;
					}
					else if (y == 1 || y == 6)
					{
						if(x == 0)
							x = 3;
						if(x == 11)
							break;
					}
					else if(y == 2 || y == 5)
					{
						if(x == 0)
							x = 2;
						if(x == 12)
							break;
					}
					if(!tiles.empty())
					{
						model[y][x][0] = tiles.pop();
						model[y][x][0].setXYZCoor(x, y, 0);
					}
				}
			}
			
			//	Load layer two
			for(int y = 1; y < 7; ++y)
			{
				for(int x = 4; x < 10; ++x)
				{
					if(!tiles.empty())
					{
						model[y][x][1] = tiles.pop();
						model[y][x][1].setXYZCoor(x, y, 1);	
					}
				}
			}
			
			//	load layer three
			for(int y = 2; y < 6; ++y)
			{
				for(int x = 5; x < 9; ++x)
				{
					if(!tiles.empty())
					{
						model[y][x][2] = tiles.pop();
						model[y][x][2].setXYZCoor(x, y, 2);
					}
				}
			}
			
			//	Load layer 4
			if(!tiles.empty())
			{
				model[0][0][3] = tiles.pop();
				model[0][0][3].setXYZCoor(0, 0, 3);	
			}
		}
		
		//	Checks to see if a tile is free
		public boolean isFree(Tile tile)
		{
			int x = tile.getXCoor();
			int y = tile.getYCoor();
			int z = tile.getZCoor();
			
			//	Check for top most tile
			if(z == ZMAX -1)
				return true;
			
			//	Check x using short circuiting to remove array out of bounds exceptions
			if(!(x == 0 || x == XMAX -1) && !(model[y][x - 1][z] == null || model[y][x + 1][z] == null))
				return false;
			
			//	Check for a tile on top
			if(model[y][x][z + 1] != null)
				return false;
			
			//	Check special cases
			if(y == 3 && x == 1)
				if(model[y + 1][x - 1][z] != null)
					return false;
			
			if(y == 3 && x == 12)
				if(model[y + 1][x + 1][z] != null)
					return false;
			 
				if(z == ZMAX - 2 && (y == 3 || y == 4) && (x == 6 || x == 7) && model[0][0][3] != null)
						return false;
			
			return true;
		}
		
		//	Returns Tile at location
		public Tile getTile(int x, int y, int z)
		{
			return model[y][x][z];
		}
		
		//	Returns a reference of the model
		public void removeTile(int x, int y, int z)
		{
			model[y][x][z] = null;
		}
		
	}
	
	// For testing
	public static void main(String[] args)
	{
		Tile t 					= new Tile();
		Dimension tileSize 		= t.getSize();
		Dimension SIZE 			= new Dimension(tileSize.height * 17, tileSize.width * 12);
		
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(new MahJongBoard());
		
		//frame.setSize(SIZE);
		frame.pack();
		frame.setVisible(true);	
	}

	
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		//	TODO Make this better!
		if(!(e.getButton() == MouseEvent.BUTTON1))
			return;
		
		Tile t = (Tile)e.getSource();
		
		//	Check to see if the Tile can be selected
		if(!model.isFree(t))
			return;
		
		if(first == null)
		{
			first = t;
			first.setBorder(BorderFactory.createLineBorder(Color.RED));
			return;
		}
		else if(t == first)
		{
			first.setBorder(BorderFactory.createEmptyBorder());
			first = null;
			return;
		}
		
			second = t;
		

		
		if(first.matches(second))
		{
			remove(first);
			remove(second);
			model.removeTile(first.getXCoor(), first.getYCoor(), first.getZCoor());
			model.removeTile(second.getXCoor(), second.getYCoor(), second.getZCoor());
			first = second = null;
			revalidate();
			repaint();
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}
}
