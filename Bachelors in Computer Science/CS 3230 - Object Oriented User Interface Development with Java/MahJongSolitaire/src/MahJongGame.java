import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;


public class MahJongGame extends JFrame
{
	private MahJongBoard game;
	public MahJongGame()
	{
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		game = new MahJongBoard();
		add(game);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLocation((int)((screenSize.width - this.getSize().width)/2), (int)((screenSize.height - this.getSize().height)/2));
		setVisible(true);
	}
	
	public static void main(String[] args)
	{
		new MahJongGame();
	}
}
