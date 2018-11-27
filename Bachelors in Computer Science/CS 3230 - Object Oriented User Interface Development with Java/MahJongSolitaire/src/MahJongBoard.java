import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;
import javax.swing.*;
import javax.swing.border.*;

import sun.applet.Main;

import javax.swing.*;

public class MahJongBoard extends JPanel implements MouseListener 
{
	private final int		XMAX = 15;
	private final int		YMAX = 8;
	private final int 		ZMAX = 5;
	private int				iSeed = -100;
	private	int 			iScore;
	private Dimension 		SIZE; 
	private Tile 			first, second, t;
	private MahJongModel	model;
	private Dimension 		tileSize;
	private boolean			bSound = true;
	private boolean			isCheating;
	private PlayClip		sound;
	private Stack<Tile>		undoStack;
	private Stack<Tile>		redoStack;
	private JPanel			sideMatRef;
	private Fireworks 		winner;
	
	public MahJongBoard(JPanel sideMatRef)
	{
		iScore = 0;
		t 		= new Tile();
		tileSize = t.getSize();
		SIZE 	= new Dimension((int)(tileSize.width * 13.8), (int)(tileSize.height * 8.2));
		sound = new PlayClip("stone-scraping.wav");
		undoStack = new Stack<Tile>();
		redoStack = new Stack<Tile>();
		this.sideMatRef = sideMatRef;
		sideMatRef.setPreferredSize(new Dimension((int)(tileSize.height * 2.5), 0));
		
		setPreferredSize(SIZE);
		setLayout(null);
		setBackground(Color.YELLOW);
		
		validate();
		setVisible(true);
	}
	
	//	Default new game
	public void newGame()
	{
		newGame(-1);
	}
	
	//	Starts a new game
	public void newGame(int seed)
	{
		iScore = 0;

		//	Stop fireworks if they are going
		if(winner != null)
			winner.stop();
		
		//	Clear side mat
		while(!undoStack.isEmpty())
			undo();
		//	Clear redo stack
		while(!redoStack.isEmpty())
			redoStack.pop();
		
		//	Clear the board
		removeAll();
		
		Random r = new Random();
		//	Looks for existing seed
		if(seed == -1)
			seed = r.nextInt(989999) + 10000;
		
		iSeed = seed;
		
		Stack<Tile> tiles = new Stack<Tile>();
		
			//		Create deck
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
				
				for(int j = 1; j < 10; ++j)
					tiles.push(new CircleTile(j));
				
				for(int j = 2; j < 10; ++j)
					tiles.push(new BambooTile(j));
				
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
		Collections.shuffle(tiles, new Random(seed));
		
		model = new MahJongModel(tiles);
		
		
		//	Add layer 1
		for(int z = ZMAX - 1; z >= 0; --z)
		{
			for(int x = 0; x < XMAX; ++x)	
			{
				for(int y = YMAX - 1; y >= 0; --y)
				{
					if(model.getTile(x,y,z) != null)
					{
						t = model.getTile(x, y, z);
						if(z == 4)
							t.setLocation((int)((tileSize.width * .7) +.8 * tileSize.width * 7 + tileSize.width * .5) ,(int)(.8 * tileSize.height * 4 - tileSize.height * .4));
						else if(x == 0 || x > 12)
							t.setLocation((int)(.8 * tileSize.width * (x + 1)) ,(int)(.8 * tileSize.height * (y+ 1) - tileSize.height * .5));
						else if(z == 0)
							t.setLocation((int)(.8 * tileSize.width * (x + 1)) ,(int)(.8 * tileSize.height * (y+ 1)));
						else if (z == 1)
							t.setLocation((int)((tileSize.width * .2) + .8 *tileSize.width * (x + 1)) ,(int)(.8 * tileSize.height * (y+ 1) - (tileSize.width * .2) ));
						else if(z == 2)
							t.setLocation((int)((tileSize.width * .4) + .8 *tileSize.width * (x + 1)) ,(int)( .8 * tileSize.height * (y+ 1) - (tileSize.width * .4) ));
						else if(z == 3)
							t.setLocation((int)((tileSize.width * .6) + .8 *tileSize.width * (x + 1)) ,(int)( .8 * tileSize.height * (y+ 1) - (tileSize.width * .6) ));
						
						t.addMouseListener(this);
						add(t);
					}
				}
			}
		} 
		repaint();		
	}
	
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		ImageIcon		image;
		String			imgName = "dragon_bg.png";

		image = new ImageIcon(MahJongBoard.class.getResource(imgName));
			
		image = new ImageIcon(image.getImage().getScaledInstance( (int) (SIZE.width * .8), -1 , Image.SCALE_SMOOTH));
		
		g.drawImage(image.getImage(), (int)((SIZE.width - image.getIconWidth()) / 2) ,(int)((SIZE.height - image.getIconHeight()) / 2), this);
		
	}
	
	//	Enter cheating mode!
	public void cheat()
	{
		isCheating = isCheating ? false : true;
	}
	//	Used to get the seed from current game
	public int getSeed()
	{
		return iSeed;
	}
	
	//	Toggles game sounds on and off
	public void toggleSound()
	{
		if(bSound)
			bSound = false;
		else
			bSound = true;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) 
	{
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
		else if(t == first || !first.matches(t))
		{
			if(!isCheating || t == first)
			{
				first.setBorder(BorderFactory.createEmptyBorder());
				first = null;
				return;
			}
		}
		
		second = t;
		
		if(bSound)
			sound.play();
		
		first.setBorder(BorderFactory.createEmptyBorder());
		first.setZOrder(getComponentZOrder(first));
		undoStack.push(first);
		remove(first);
		sideMatRef.add(first);

		second.setZOrder(getComponentZOrder(second));
		undoStack.push(second);
		remove(second);
		sideMatRef.add(second);
		
		iScore += 2;
		sideMatRef.setPreferredSize( new Dimension((int) (first.getPreferredSize().width * 2.5), 
				(int)(first.getPreferredSize().height * 1.07 * iScore / 2)));

		model.removeTile(first.getXCoor(), first.getYCoor(), first.getZCoor());
		model.removeTile(second.getXCoor(), second.getYCoor(), second.getZCoor());
		
		while(!redoStack.isEmpty())
			redoStack.pop();
		
		first = second = null;
		repaint();		
		
		//	Check for win
		if(iScore == 144)
		{
			winner = new Fireworks(this);
			winner.setSound(bSound);
			winner.fire();
		}
	}
	
	public void undo()
	{
		//	Game is over, no point in letting the user undo
		if(iScore == 144)
			return;
		//	No undo available
		if(undoStack.isEmpty())
			return;
		
		//	Replace Tiles
		for(int i = 0; i < 2; ++i)
		{
			t = undoStack.pop();
			if(t.getZCoor() == 4)
				t.setLocation((int)((tileSize.width * .7) +.8 * tileSize.width * 7 + tileSize.width * .5) ,(int)(.8 * tileSize.height * 4 - tileSize.height * .4));
			else if(t.getXCoor() == 0 || t.getXCoor() > 12)
				t.setLocation((int)(.8 * tileSize.width * (t.getXCoor() + 1)) ,(int)(.8 * tileSize.height * (t.getYCoor()+ 1) - tileSize.height * .5));
			else if(t.getZCoor() == 0)
				t.setLocation((int)(.8 * tileSize.width * (t.getXCoor() + 1)) ,(int)(.8 * tileSize.height * (t.getYCoor()+ 1)));
			else if (t.getZCoor() == 1)
				t.setLocation((int)((tileSize.width * .2) + .8 *tileSize.width * (t.getXCoor() + 1)) ,(int)(.8 * tileSize.height * (t.getYCoor()+ 1) - (tileSize.width * .2) ));
			else if(t.getZCoor() == 2)
				t.setLocation((int)((tileSize.width * .4) + .8 *tileSize.width * (t.getXCoor() + 1)) ,(int)( .8 * tileSize.height * (t.getYCoor()+ 1) - (tileSize.width * .4) ));
			else if(t.getZCoor() == 3)
				t.setLocation((int)((tileSize.width * .6) + .8 *tileSize.width * (t.getXCoor() + 1)) ,(int)( .8 * tileSize.height * (t.getYCoor()+ 1) - (tileSize.width * .6) ));
			
			add(t);
			setComponentZOrder(t, t.getZOrder());
			model.returnTile(t);
			sideMatRef.remove(t);
			redoStack.push(t);
			--iScore;
		}
		sideMatRef.setPreferredSize( new Dimension((int) (t.getPreferredSize().width * 2.5), 
				(int)(t.getPreferredSize().height * 1.07 *  iScore / 2)));
		
		repaint();
		

	}

	//	Redo last moves
	public void redo()
	{
		if(!redoStack.isEmpty())
			for(int i = 0; i < 2; ++i)
			{
				t = redoStack.pop();
				model.removeTile(t.getXCoor(), t.getYCoor(), t.getZCoor());
				undoStack.push(t);
				remove(t);
				sideMatRef.add(t);
				
				repaint();
				sideMatRef.revalidate();
				++iScore;
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
			
			//	load layer four
			model[3][7][3] = tiles.pop();
			model[3][7][3].setXYZCoor(7,3,3);
			
			model[4][7][3] = tiles.pop();
			model[4][7][3].setXYZCoor(7,4,3);

			model[3][6][3] = tiles.pop();
			model[3][6][3].setXYZCoor(6,3,3);

			model[4][6][3] = tiles.pop();
			model[4][6][3].setXYZCoor(6,4,3);

			//	Load layer 5
			if(!tiles.empty())
			{
				model[0][0][4] = tiles.pop();
				model[0][0][4].setXYZCoor(0, 0, 4);	
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
				if(model[y + 1][x - 1][z] != null && model[y][x + 1] != null)
					return false;
			
			if(y == 3 && x == 12)
				if(model[y + 1][x + 1][z] != null && model[y][x - 1][z] != null)
					return false;
			 
				if(z == ZMAX - 2 && model[0][0][4] != null)
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
		
		//	Returns a Tile to the board
		public void returnTile(Tile t)
		{
			model[t.getYCoor()][t.getXCoor()][t.getZCoor()] = t;
		}
		
	}
}
