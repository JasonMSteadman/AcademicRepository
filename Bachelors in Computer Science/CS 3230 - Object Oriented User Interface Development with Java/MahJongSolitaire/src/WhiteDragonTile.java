import java.awt.*;
import javax.swing.*;


public class WhiteDragonTile extends Tile
{
	
	public WhiteDragonTile()
	{
		setToolTipText(toString());
	}
	//Override toString()
	public String toString()
	{
		return "White Dragon";
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		int 		x;
		int			y;
		Color		color;
		int			h;		//	Height of boxes when horizontal
		int			w;		//	Width of boxes when horizontal
 
		w = (int)(FACE /7);
		h = (int)(2* w/3);
		x = (int)(SIZE.width * .2 + h);
		y = h;			//w = h*2   6 w + 2 h
		
		//	draw little boxes
		for(int i = 1; i <= 6; ++i)
		{
			color = (i % 2 > 0) ? Color.BLUE : Color.WHITE;
			g.setColor(color);
			g.fillRect(x, y, w, h);
			x += w;
		}
		
		x = (int)(SIZE.width * .2 + h);
		y += h;
		
		for(int i = 0; i < 5; ++i)
		{
			color = (i % 2 > 0) ? Color.BLUE : Color.WHITE;
			g.setColor(color);
			g.fillRect(x, y, h, w);
			y += w;
		}
		
		for(int i = 1; i <= 6; ++i)
		{
			color = (i % 2 > 0) ? Color.BLUE : Color.WHITE;
			g.setColor(color);
			g.fillRect(x, y, w, h);
			x += w;
		}
		
		x -= h;
		y -= w;
		
		for(int i = 1; i <= 5; ++i)
		{
			color = (i % 2 > 0) ? Color.BLUE : Color.WHITE;
			g.setColor(color);
			g.fillRect(x, y, h, w);
			y -= w;
		}
		
		//	Draw outline
		x = (int)(SIZE.width * .2 + h);
		y = h;
		
		g.setColor(Color.BLUE);
		g.drawRect(x, y, (int)(w * 6), (int)(w * 5 + 2 * h));
		
		g.drawRect((int)(x + h), (int)(y + h),(int)(4.75 * w),(int)(5 * w));
		
	}
	
	public static void main(String[] args)
	{
		JFrame	frame = new JFrame();

		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("White Dragon Tile");

		frame.add(new WhiteDragonTile());

		frame.pack();
		frame.setVisible(true);
	}
}
