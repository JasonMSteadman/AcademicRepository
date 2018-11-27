import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class MahJongGame extends JFrame
{
	private MahJongBoard 	game;
	private JPanel			sideMat;
	public MahJongGame()
	{
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		sideMat = new JPanel();
		game = new MahJongBoard(sideMat);
		JScrollPane sideScroll = new JScrollPane(sideMat);
		add(sideScroll, BorderLayout.EAST);
		add(game);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		addWindowListener(new WindowAdapter()
			{ public void windowClosing(WindowEvent e)
				{ 
					if(JOptionPane.showConfirmDialog(null, "Are you sure you want to exit the game?", "Exit Game", JOptionPane.YES_NO_OPTION) 
							!= JOptionPane.YES_OPTION)
						return;
					System.exit(0); 
				}
			});
			
		makeMenu();
		sideMat.setBackground(Color.GREEN);
		
		sideScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		sideScroll.setHorizontalScrollBar(null);
		sideScroll.setMaximumSize( new Dimension(sideMat.getPreferredSize().width, 0));
		
		//	Add auto scroll
		sideScroll.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener ()
				{
					public void adjustmentValueChanged(AdjustmentEvent e)
					{	e.getAdjustable().setValue(e.getAdjustable().getMaximum());	}
				});
		
		pack();
		setLocation((int)((screenSize.width - this.getSize().width)/2), (int)((screenSize.height - this.getSize().height)/2));
		setVisible(true);
	}
	
	//	Makes the menu for the game
	private void makeMenu()
	{
		JMenuBar menuBar 		= new JMenuBar();
		JMenu 					menu;
		JMenuItem 				menuItem;
		
		
		setJMenuBar(menuBar);
		
		//	Game tab
		menu = new JMenu("Game");
		menu.setMnemonic('G');
		menuBar.add(menu);

		//	New Game
		menuItem = new JMenuItem("New Game",'G');
		menuItem.setToolTipText("Start's a new game.");
		menuItem.setAccelerator(KeyStroke.getKeyStroke("ctrl G"));
		menu.add(menuItem);
		menuItem.addActionListener(new ActionListener () 
				{
					public void actionPerformed(ActionEvent e)
					{ 	
						if(game.getSeed() != -100)
							if(JOptionPane.showConfirmDialog(null, "Are you sure you would like to start a new game?", "Start New Game", JOptionPane.YES_NO_OPTION) 
									!= JOptionPane.YES_OPTION)
								return;
						game.newGame();
						setTitle("Game: " + game.getSeed());
					}
				});
		
		//	Restart
		menuItem = new JMenuItem("Restart",'R');
		menuItem.setToolTipText("Play current game again.");
		menuItem.setAccelerator(KeyStroke.getKeyStroke("ctrl R"));
		menu.add(menuItem);
		menuItem.addActionListener(new ActionListener () 
				{
					public void actionPerformed(ActionEvent e)
					{	
						int seed = game.getSeed();
						if(seed == -100)
							return;
						if(JOptionPane.showConfirmDialog(null, "Are you sure you want to restart your current game?", "Restart Game", JOptionPane.YES_NO_OPTION) 
								!= JOptionPane.YES_OPTION)
							return;
						game.newGame(game.getSeed());
						setTitle("Game: " + game.getSeed());
					}
				});
		
		//	Numbered
		menuItem = new JMenuItem("Numbered Game",'N');
		menuItem.setToolTipText("Lets you specify a game you wish to play");
		menuItem.setAccelerator(KeyStroke.getKeyStroke("ctrl N"));
		menu.add(menuItem);
		menuItem.addActionListener(new ActionListener () 
				{
					public void actionPerformed(ActionEvent e)
					{	getNumber();	}
				});
		

		//	Sound tab
		menu = new JMenu("Sound");
		menu.setMnemonic('S');
		menuBar.add(menu);

		//	Sound on or Off
		JRadioButtonMenuItem radMenuItem = new JRadioButtonMenuItem("On or Off", true);
		radMenuItem.setToolTipText("Turns game sounds on and off");
		radMenuItem.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
		menu.add(radMenuItem);
		radMenuItem.addActionListener(new ActionListener () 
				{
					public void actionPerformed(ActionEvent e)
					{	game.toggleSound();	}	
				});
		menu.add(radMenuItem);
		
		//	Move tab
		menu = new JMenu("Move");
		menu.setMnemonic('M');
		menuBar.add(menu);
		
		//	Undo	
		menuItem = new JMenuItem("Undo",'U');
		menuItem.setToolTipText("Undo the last move.");
		menu.add(menuItem);
		menuItem.setAccelerator(KeyStroke.getKeyStroke("ctrl Z"));
		menuItem.addActionListener(new ActionListener () 
				{
					public void actionPerformed(ActionEvent e)
					{	game.undo();	}
				});
		
		//	Redo
		menuItem = new JMenuItem("Redo",'Y');
		menuItem.setToolTipText("Lets you redo undos until a move is made.");
		menu.add(menuItem);
		menuItem.setAccelerator(KeyStroke.getKeyStroke("ctrl Y"));
		menuItem.addActionListener(new ActionListener () 
				{
					public void actionPerformed(ActionEvent e)
					{	game.redo();	}
				});

		//	Help tab		
		menu = new JMenu("Help");
		menu.setMnemonic('H');
		menuBar.add(menu);
		
		//	Game rules
		menuItem = new JMenuItem("Game rules",'R');
		menuItem.setToolTipText("Explains the rules of the game.");
		menuItem.setAccelerator(KeyStroke.getKeyStroke("ctrl H"));
		menu.add(menuItem);
		menuItem.addActionListener(new ActionListener () 
		{
			public void actionPerformed(ActionEvent e)
			{	
				JFrame help = new JFrame();
				help.add(new Help("HelpMainPage.html", "Help Menu"));
				help.setVisible(true);
				Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
				help.setSize((int)(size.width * .5), (int)(size.height * .5));
			}
		});
	}
	
	//	Used to get a number for a numbered game
	private void getNumber()
	{
		Integer userInput = null;
		
		while(userInput == null)
		{
			try
			{
				if(game.getSeed() != -100)
					if(JOptionPane.showConfirmDialog(null, "Are you sure you want to quit your current game?", "Start Numbered Game", JOptionPane.YES_NO_OPTION) 
							!= JOptionPane.YES_OPTION)
						return;
				
				String input = JOptionPane.showInputDialog("Please enter a game number from 100000 - 999999.");
				if(input == null)
					return;
				if(input.equals("cheat"))
				{
					game.cheat();
					return;
				}
				userInput = Integer.parseInt(input);
				
				if(userInput < 100000 || userInput > 999999)
					userInput = null;
			} 
			catch(NumberFormatException nfe)
			{
				userInput = null;
			}
		}
		
		game.newGame(userInput);
		setTitle("Game: " + game.getSeed());
	}
	
	public static void main(String[] args)
	{
		new MahJongGame();
	}
}
