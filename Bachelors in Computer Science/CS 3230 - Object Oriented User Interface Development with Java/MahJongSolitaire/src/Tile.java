import java.awt.*;

import javax.swing.*;

public class Tile extends JPanel
{
	private static final double 			SCALE = .08;	//	Default is 0.08
	private static Dimension 				screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	protected static final Dimension		SIZE = new Dimension((int)(screenSize.height * SCALE),(int)(screenSize.height * SCALE));
	protected static final int 				FACE = (int)(SIZE.height * .8);
	private int iX;
	private int iY;
	private int iZ;
	private int iZOrder;
	
	public Tile()
	{
		setPreferredSize(SIZE);
		setSize(SIZE);
		setOpaque(false);
	}
	
	//	Used to set the coordinate of the tile
	public void setXYZCoor(int x, int y, int z)
	{
		this.iX = x;
		this.iY = y;
		this.iZ = z;
	}
	
	//	Returns the x location
	public int getXCoor()
	{
		return iX;
	}
	
	//	Returns the y location
	public int getYCoor()
	{
		return iY;
	}
	
	//	Returns the z location
	public int getZCoor()
	{
		return iZ;
	} 
	
	//	Set z order
	public void setZOrder(int zOrder)
	{
		iZOrder = zOrder;
	}
	
	//	Get z order
	public int getZOrder()
	{
		return iZOrder;
	}
	
	//Check to see if other is a child of the Tile class
	public boolean matches(Tile other)
	{
		//if other is null
		if(other == null)
			return false;
			
		//if other is not a Fraction object
		if(!(this.getClass() == other.getClass()))
			return false;
			
		return true;
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		setBackground(Color.WHITE);
		g.setColor(Color.WHITE);
		g.fillRect((int)(SIZE.width * .2), 0, (int)(SIZE.width * .8) - 1, (int)(SIZE.height * .8) - 1);
		g.setColor(Color.BLACK);
		g.drawRect((int)(SIZE.width * .2), 0, (int)(SIZE.width * .8) - 1, (int)(SIZE.height * .8) - 1);
		
		
		//	Side right
		int[] x = new int[] {(int)(SIZE.width * .2), (int)(SIZE.width * .1), (int)(SIZE.width * .1), (int)(SIZE.width * .2)};
		int[] y = new int[] {0, (int)(SIZE.height * .1), (int)(SIZE.height * .90), (int)(SIZE.height * .8) - 1};
		g.setColor(Color.WHITE);
		g.fillPolygon(x, y, 4);
		g.setColor(Color.BLACK);
		g.drawPolygon(x, y, 4);
		
		//	Bottom top
		x = new int[] {SIZE.width - 2, (int)(SIZE.width - SIZE.width * .1), (int)(SIZE.width * .1), (int)(SIZE.width * .2)};
		y = new int[] {(int)(SIZE.height * .8), (int)(SIZE.height * .9), (int)(SIZE.height * .9),(int)(SIZE.height * .8)};
		g.setColor(Color.WHITE);
		g.fillPolygon(x, y, 4);
		g.setColor(Color.BLACK);
		g.drawPolygon(x, y, 4);
		
		Graphics2D 		g2 = (Graphics2D)g;
		Color			lightEdge = Color.decode("#7cff7c");
		Color			darkEdge = Color.decode("#37a837");
		
		GradientPaint 	grad = new GradientPaint(0,(int)(SIZE.height * .90), lightEdge, 0,0, darkEdge);
		g2.setPaint(grad);
		
		//	Side left
		x = new int[] {(int)(SIZE.width * .1), 0, 0, (int)(SIZE.width * .1)};
		y = new int[] {(int)(SIZE.height * .1), (int)(SIZE.height * .2), SIZE.height, (int)(SIZE.height * .90)};
		g.fillPolygon(x, y, 4);
		
		//	Create outline
		g2.setPaint(Color.BLACK);
		g.drawPolygon(x, y, 4);
		
		grad = new GradientPaint(0,SIZE.height, lightEdge, SIZE.width, SIZE.height, darkEdge);
		g2.setPaint(grad);
		
		//	Bottom bottom
		x = new int[] {(int)(SIZE.width - SIZE.width * .1), (int)(SIZE.width - SIZE.width * .2), 0, (int)(SIZE.width * .1)};
		y = new int[] {(int)(SIZE.height * .9), SIZE.height - 1, SIZE.height - 1, (int)(SIZE.height * .9)};
		g.fillPolygon(x, y, 4);
		
		//	Create outline
		g2.setPaint(Color.BLACK);
		g.drawPolygon(x, y, 4);
		
	}
		
	public Dimension getSize()
	{
		return SIZE;
	}
	
	public static void main(String[] args)
	{
		JFrame	frame = new JFrame();

		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Tile");
		
		frame.add(new Tile());
		frame.add(new Tile());
		frame.add(new Tile());
		
		frame.pack();
		frame.setVisible(true);
	}
}
