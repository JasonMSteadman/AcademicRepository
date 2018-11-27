import java.awt.*;

import javax.swing.*;

public abstract class PictureTile extends Tile
{
	
	//Name of image file
	private String name;
	
	//Basic constructor
	public PictureTile(String name)
	{
		this.name = name;
		setToolTipText(toString());
	}
	
	//Override toString()
	public String toString()
	{
		return name;
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		ImageIcon		image;
		String			imgName = name + ".png";

		image = new ImageIcon(Toolkit.getDefaultToolkit().getImage(PictureTile.class.getResource(imgName)));

		//image = new ImageIcon(imgName);
			
		image = new ImageIcon(image.getImage().getScaledInstance(FACE - 4, FACE - 4, Image.SCALE_SMOOTH));
		
		g.drawImage(image.getImage(), (int)(SIZE.width * .2) + 2 ,2, this);
		
	}
	
	public static void main(String[] args)
	{
		JFrame	frame = new JFrame();

		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Picture Tiles");

		frame.add(new Bamboo1Tile());

		frame.add(new FlowerTile("Chrysanthemum"));
		frame.add(new FlowerTile("Orchid"));
		frame.add(new FlowerTile("Plum"));
		frame.add(new FlowerTile("Bamboo"));

		frame.add(new SeasonTile("Spring"));
		frame.add(new SeasonTile("Summer"));
		frame.add(new SeasonTile("Fall"));
		frame.add(new SeasonTile("Winter"));

		frame.pack();
		frame.setVisible(true);
	}
}
