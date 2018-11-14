import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

import javax.swing.*;

public class CircleTile extends RankTile
{
	
	//Basic constructor
	public CircleTile(int rank)
	{
		super(rank);
		setToolTipText(toString());
	}
	
	//Override toString()
	public String toString()
	{
		return "Circle " + rank;
	}
	
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D		g2 = (Graphics2D)g;
		double 			scale = 1;
		int				xL;
		int				yL;
		AffineTransform 	g2r;
		
		switch(rank)
		{
			case 1:
				//	Big circle
				g2.setPaint(Color.GREEN);
				g.fillOval((int)(SIZE.width * .2 + FACE * .1), (int)(FACE * .1), (int)(FACE * .8), (int)(FACE * .8));
				
				//	Middle circle
				xL = (int)(SIZE.width * .2 + FACE * (1 - .16)/2);
				yL = (int)(FACE * (1 - .16)/2);
				g2.setColor(Color.RED);
				
				g2.fillOval(xL, yL, (int)(scale * FACE * .16), (int)(scale * FACE * .16));
				
				//	Draw X in middle of circle
				g2.setColor(Color.WHITE);
				g2r = g2.getTransform();
				g2.rotate(Math.toRadians(45),(int)(xL + (scale * FACE * .16) /2),(int)(scale * FACE * .16 / 2) + yL );
				g2.drawLine((int)(xL + (scale * FACE * .16) /2), yL, (int)(xL + (scale * FACE * .16) /2), (int)(scale * FACE * .16) + yL);
				g2.rotate(Math.toRadians(90),(int)(xL + (scale * FACE * .16) /2),(int)(scale * FACE * .16 / 2) + yL);
				g2.drawLine((int)(xL + (scale * FACE * .16) /2), yL, (int)(xL + (scale * FACE * .16) /2), (int)(scale * FACE * .16) + yL);
				g2.setTransform(g2r);

				//	Draw white circle outline
				g2.setPaint(Color.WHITE);
				g.drawOval((int)(SIZE.width * .2 + FACE * (1 - .16)/2), (int)(FACE * (1 - .16)/2), (int)(FACE * .16), (int)(FACE * .16));

				//	Small white circles
				g2r = g2.getTransform();
				
				g2.setPaint(Color.WHITE);
				int x = (int)(SIZE.width * .2 + FACE * (1 - .08)/2);
				int y = (int)(FACE * .15 );
				int dia = (int)(FACE * .08);
				double rotation = 360 / 17;
				
				g2.fillOval(x, y, dia, dia);
				for(double i = 0; i < 16; ++i)
				{
					g2.rotate(Math.toRadians(rotation),(int)(SIZE.width * .6), (int)(SIZE.height *.4) );
					g2.fillOval(x, y, dia, dia);
				}
				//g2.fillOval(x, y, dia, dia);
				g2.setTransform(g2r);
				break;
				
			case 2:
				//	Top Circle
				scale = 2.5;
				xL = (int)(SIZE.width * .2 + FACE / 2 - scale * FACE * .16 / 2 );
				yL = (int)(FACE * .1);
				g2.setColor(Color.GREEN);
				
				g2.fillOval(xL, yL, (int)(scale * FACE * .16), (int)(scale * FACE * .16));
				
				//	Draw X in middle of circle
				g2.setColor(Color.WHITE);
				g2r = g2.getTransform();
				g2.rotate(Math.toRadians(45),(int)(xL + (scale * FACE * .16) /2),(int)(scale * FACE * .16 / 2) + yL );
				g2.drawLine((int)(xL + (scale * FACE * .16) /2), yL, (int)(xL + (scale * FACE * .16) /2), (int)(scale * FACE * .16) + yL);
				g2.rotate(Math.toRadians(90),(int)(xL + (scale * FACE * .16) /2),(int)(scale * FACE * .16 / 2) + yL);
				g2.drawLine((int)(xL + (scale * FACE * .16) /2), yL, (int)(xL + (scale * FACE * .16) /2), (int)(scale * FACE * .16) + yL);
				g2.setTransform(g2r);
				
				//	Bottom circle
				yL = (int)(FACE * .9 - scale * FACE * .16);
				
				g2.setColor(Color.RED);
				g2.fillOval(xL, yL, (int)(scale * FACE * .16), (int)(scale * FACE * .16));
				
				//	Draw X in middle of circle
				g2.setColor(Color.WHITE);
				g2r = g2.getTransform();
				g2.rotate(Math.toRadians(45),(int)(xL + (scale * FACE * .16) /2),(int)(scale * FACE * .16 / 2) + yL );
				g2.drawLine((int)(xL + (scale * FACE * .16) /2), yL, (int)(xL + (scale * FACE * .16) /2), (int)(scale * FACE * .16) + yL);
				g2.rotate(Math.toRadians(90),(int)(xL + (scale * FACE * .16) /2),(int)(scale * FACE * .16 / 2) + yL);
				g2.drawLine((int)(xL + (scale * FACE * .16) /2), yL, (int)(xL + (scale * FACE * .16) /2), (int)(scale * FACE * .16) + yL);
				g2.setTransform(g2r);
				break;
				
			case 3:
				//	Left circle
				scale = 2.0;
				xL = (int)(SIZE.width * .2 + FACE * .02);
				yL = (int)(FACE * .02);
				
				g2.setColor(Color.BLUE);
				g2.fillOval(xL, yL, (int)(scale * FACE * .16), (int)(scale * FACE * .16));
				
				//	Draw X in middle of circle
				g2.setColor(Color.WHITE);
				g2r = g2.getTransform();
				g2.rotate(Math.toRadians(45),(int)(xL + (scale * FACE * .16) /2),(int)(scale * FACE * .16 / 2) + yL );
				g2.drawLine((int)(xL + (scale * FACE * .16) /2), yL, (int)(xL + (scale * FACE * .16) /2), (int)(scale * FACE * .16) + yL);
				g2.rotate(Math.toRadians(90),(int)(xL + (scale * FACE * .16) /2),(int)(scale * FACE * .16 / 2) + yL);
				g2.drawLine((int)(xL + (scale * FACE * .16) /2), yL, (int)(xL + (scale * FACE * .16) /2), (int)(scale * FACE * .16) + yL);
				g2.setTransform(g2r);
				
				//	Middle circle
				xL = (int)(SIZE.width * .2 + FACE * .02 + (scale * FACE * .16));
				yL = (int)(FACE * .02 + (scale * FACE * .16));
				
				g2.setColor(Color.RED);
				g2.fillOval(xL, yL, (int)(scale * FACE * .16), (int)(scale * FACE * .16));
				
				//	Draw X in middle of circle
				g2.setColor(Color.WHITE);
				g2r = g2.getTransform();
				g2.rotate(Math.toRadians(45),(int)(xL + (scale * FACE * .16) /2),(int)(scale * FACE * .16 / 2) + yL );
				g2.drawLine((int)(xL + (scale * FACE * .16) /2), yL, (int)(xL + (scale * FACE * .16) /2), (int)(scale * FACE * .16) + yL);
				g2.rotate(Math.toRadians(90),(int)(xL + (scale * FACE * .16) /2),(int)(scale * FACE * .16 / 2) + yL);
				g2.drawLine((int)(xL + (scale * FACE * .16) /2), yL, (int)(xL + (scale * FACE * .16) /2), (int)(scale * FACE * .16) + yL);
				g2.setTransform(g2r);
				
				//	Right circle
				xL = (int)(SIZE.width * .2 + FACE * .02 + 2 * (scale * FACE * .16));
				yL = (int)(FACE * .02 + 2 * (scale * FACE * .16));
				
				g2.setColor(Color.GREEN);
				g2.fillOval(xL, yL, (int)(scale * FACE * .16), (int)(scale * FACE * .16));
				
				//	Draw X in middle of circle
				g2.setColor(Color.WHITE);
				g2r = g2.getTransform();
				g2.rotate(Math.toRadians(45),(int)(xL + (scale * FACE * .16) /2),(int)(scale * FACE * .16 / 2) + yL );
				g2.drawLine((int)(xL + (scale * FACE * .16) /2), yL, (int)(xL + (scale * FACE * .16) /2), (int)(scale * FACE * .16) + yL);
				g2.rotate(Math.toRadians(90),(int)(xL + (scale * FACE * .16) /2),(int)(scale * FACE * .16 / 2) + yL);
				g2.drawLine((int)(xL + (scale * FACE * .16) /2), yL, (int)(xL + (scale * FACE * .16) /2), (int)(scale * FACE * .16) + yL);
				g2.setTransform(g2r);
				break;
				
			case 4:
				
				//	Top left circle
				scale = 2.5;
				xL = (int)(SIZE.width * .2 + FACE * .08);
				yL = (int)(FACE * .08);
				
				g2.setColor(Color.BLUE);
				g2.fillOval(xL, yL, (int)(scale * FACE * .16), (int)(scale * FACE * .16));
				
				//	Draw X in middle of circle
				g2.setColor(Color.WHITE);
				g2r = g2.getTransform();
				g2.rotate(Math.toRadians(45),(int)(xL + (scale * FACE * .16) /2),(int)(scale * FACE * .16 / 2) + yL );
				g2.drawLine((int)(xL + (scale * FACE * .16) /2), yL, (int)(xL + (scale * FACE * .16) /2), (int)(scale * FACE * .16) + yL);
				g2.rotate(Math.toRadians(90),(int)(xL + (scale * FACE * .16) /2),(int)(scale * FACE * .16 / 2) + yL);
				g2.drawLine((int)(xL + (scale * FACE * .16) /2), yL, (int)(xL + (scale * FACE * .16) /2), (int)(scale * FACE * .16) + yL);
				g2.setTransform(g2r);
				
				//	Top right circle
				xL = (int)(SIZE.width * .2 + FACE - FACE * .08 - scale * FACE * .16);
				
				g2.setColor(Color.GREEN);
				g2.fillOval(xL, yL, (int)(scale * FACE * .16), (int)(scale * FACE * .16));
				
				//	Draw X in middle of circle
				g2.setColor(Color.WHITE);
				g2r = g2.getTransform();
				g2.rotate(Math.toRadians(45),(int)(xL + (scale * FACE * .16) /2),(int)(scale * FACE * .16 / 2) + yL );
				g2.drawLine((int)(xL + (scale * FACE * .16) /2), yL, (int)(xL + (scale * FACE * .16) /2), (int)(scale * FACE * .16) + yL);
				g2.rotate(Math.toRadians(90),(int)(xL + (scale * FACE * .16) /2),(int)(scale * FACE * .16 / 2) + yL);
				g2.drawLine((int)(xL + (scale * FACE * .16) /2), yL, (int)(xL + (scale * FACE * .16) /2), (int)(scale * FACE * .16) + yL);
				g2.setTransform(g2r);
				
				//	Bottom right circle
				xL = (int)(SIZE.width * .2 + FACE - FACE * .08 - scale * FACE * .16);
				yL = (int)(FACE - FACE * .08 - scale * FACE * .16);
				
				g2.setColor(Color.BLUE);
				g2.fillOval(xL, yL, (int)(scale * FACE * .16), (int)(scale * FACE * .16));
				
				//	Draw X in middle of circle
				g2.setColor(Color.WHITE);
				g2r = g2.getTransform();
				g2.rotate(Math.toRadians(45),(int)(xL + (scale * FACE * .16) /2),(int)(scale * FACE * .16 / 2) + yL );
				g2.drawLine((int)(xL + (scale * FACE * .16) /2), yL, (int)(xL + (scale * FACE * .16) /2), (int)(scale * FACE * .16) + yL);
				g2.rotate(Math.toRadians(90),(int)(xL + (scale * FACE * .16) /2),(int)(scale * FACE * .16 / 2) + yL);
				g2.drawLine((int)(xL + (scale * FACE * .16) /2), yL, (int)(xL + (scale * FACE * .16) /2), (int)(scale * FACE * .16) + yL);
				g2.setTransform(g2r);
				
				//	Bottom left circle
				xL = (int)(SIZE.width * .2 + FACE * .08);
				
				g2.setColor(Color.GREEN);
				g2.fillOval(xL, yL, (int)(scale * FACE * .16), (int)(scale * FACE * .16));
				
				//	Draw X in middle of circle
				g2.setColor(Color.WHITE);
				g2r = g2.getTransform();
				g2.rotate(Math.toRadians(45),(int)(xL + (scale * FACE * .16) /2),(int)(scale * FACE * .16 / 2) + yL );
				g2.drawLine((int)(xL + (scale * FACE * .16) /2), yL, (int)(xL + (scale * FACE * .16) /2), (int)(scale * FACE * .16) + yL);
				g2.rotate(Math.toRadians(90),(int)(xL + (scale * FACE * .16) /2),(int)(scale * FACE * .16 / 2) + yL);
				g2.drawLine((int)(xL + (scale * FACE * .16) /2), yL, (int)(xL + (scale * FACE * .16) /2), (int)(scale * FACE * .16) + yL);
				g2.setTransform(g2r);
				break;
				
			case 5:
				
				//	Top left circle
				scale = 2;
				xL = (int)(SIZE.width * .2 + FACE * .02);
				yL = (int)(FACE * .02);
				
				g2.setColor(Color.BLUE);
				g2.fillOval(xL, yL, (int)(scale * FACE * .16), (int)(scale * FACE * .16));
				
				//	Draw X in middle of circle
				g2.setColor(Color.WHITE);
				g2r = g2.getTransform();
				g2.rotate(Math.toRadians(45),(int)(xL + (scale * FACE * .16) /2),(int)(scale * FACE * .16 / 2) + yL );
				g2.drawLine((int)(xL + (scale * FACE * .16) /2), yL, (int)(xL + (scale * FACE * .16) /2), (int)(scale * FACE * .16) + yL);
				g2.rotate(Math.toRadians(90),(int)(xL + (scale * FACE * .16) /2),(int)(scale * FACE * .16 / 2) + yL);
				g2.drawLine((int)(xL + (scale * FACE * .16) /2), yL, (int)(xL + (scale * FACE * .16) /2), (int)(scale * FACE * .16) + yL);
				g2.setTransform(g2r);
				
				//	Top right circle
				xL = (int)(SIZE.width * .2 + FACE - FACE * .02 - scale * FACE * .16);
				
				g2.setColor(Color.GREEN);
				g2.fillOval(xL, yL, (int)(scale * FACE * .16), (int)(scale * FACE * .16));
				
				//	Draw X in middle of circle
				g2.setColor(Color.WHITE);
				g2r = g2.getTransform();
				g2.rotate(Math.toRadians(45),(int)(xL + (scale * FACE * .16) /2),(int)(scale * FACE * .16 / 2) + yL );
				g2.drawLine((int)(xL + (scale * FACE * .16) /2), yL, (int)(xL + (scale * FACE * .16) /2), (int)(scale * FACE * .16) + yL);
				g2.rotate(Math.toRadians(90),(int)(xL + (scale * FACE * .16) /2),(int)(scale * FACE * .16 / 2) + yL);
				g2.drawLine((int)(xL + (scale * FACE * .16) /2), yL, (int)(xL + (scale * FACE * .16) /2), (int)(scale * FACE * .16) + yL);
				g2.setTransform(g2r);
				
				//	Bottom right circle
				yL = (int)(FACE - FACE * .02 - scale * FACE * .16);
				
				g2.setColor(Color.BLUE);
				g2.fillOval(xL, yL, (int)(scale * FACE * .16), (int)(scale * FACE * .16));
				
				//	Draw X in middle of circle
				g2.setColor(Color.WHITE);
				g2r = g2.getTransform();
				g2.rotate(Math.toRadians(45),(int)(xL + (scale * FACE * .16) /2),(int)(scale * FACE * .16 / 2) + yL );
				g2.drawLine((int)(xL + (scale * FACE * .16) /2), yL, (int)(xL + (scale * FACE * .16) /2), (int)(scale * FACE * .16) + yL);
				g2.rotate(Math.toRadians(90),(int)(xL + (scale * FACE * .16) /2),(int)(scale * FACE * .16 / 2) + yL);
				g2.drawLine((int)(xL + (scale * FACE * .16) /2), yL, (int)(xL + (scale * FACE * .16) /2), (int)(scale * FACE * .16) + yL);
				g2.setTransform(g2r);
				
				//	Bottom left circle
				xL = (int)(SIZE.width * .2 + FACE * .02);

				g2.setColor(Color.GREEN);
				g2.fillOval(xL, yL, (int)(scale * FACE * .16), (int)(scale * FACE * .16));
				
				//	Draw X in middle of circle
				g2.setColor(Color.WHITE);
				g2r = g2.getTransform();
				g2.rotate(Math.toRadians(45),(int)(xL + (scale * FACE * .16) /2),(int)(scale * FACE * .16 / 2) + yL );
				g2.drawLine((int)(xL + (scale * FACE * .16) /2), yL, (int)(xL + (scale * FACE * .16) /2), (int)(scale * FACE * .16) + yL);
				g2.rotate(Math.toRadians(90),(int)(xL + (scale * FACE * .16) /2),(int)(scale * FACE * .16 / 2) + yL);
				g2.drawLine((int)(xL + (scale * FACE * .16) /2), yL, (int)(xL + (scale * FACE * .16) /2), (int)(scale * FACE * .16) + yL);
				g2.setTransform(g2r);
				
				//	Middle circle
				xL = (int)(SIZE.width * .2 + (FACE - scale * FACE * .16) / 2);
				yL = (int)((FACE - scale * FACE * .16) / 2);
				
				g2.setColor(Color.RED);
				g2.fillOval(xL, yL, (int)(scale * FACE * .16), (int)(scale * FACE * .16));
				
				//	Draw X in middle of circle
				g2.setColor(Color.WHITE);
				g2r = g2.getTransform();
				g2.rotate(Math.toRadians(45),(int)(xL + (scale * FACE * .16) /2),(int)(scale * FACE * .16 / 2) + yL );
				g2.drawLine((int)(xL + (scale * FACE * .16) /2), yL, (int)(xL + (scale * FACE * .16) /2), (int)(scale * FACE * .16) + yL);
				g2.rotate(Math.toRadians(90),(int)(xL + (scale * FACE * .16) /2),(int)(scale * FACE * .16 / 2) + yL);
				g2.drawLine((int)(xL + (scale * FACE * .16) /2), yL, (int)(xL + (scale * FACE * .16) /2), (int)(scale * FACE * .16) + yL);
				g2.setTransform(g2r);
				break;
				
			case 6:
				
				scale = 2;
				xL = (int)(SIZE.width * .2 + (FACE - scale * FACE * .16 * 2) / 3 );
				yL = (int)((FACE - scale * FACE * .16 * 3) / 4);
				
				for(int i = 0; i < 2; ++i)
				{
					yL = (int)((FACE - scale * FACE * .16 * 3) / 4);
					for(int j =0; j < 3; ++j)
					{
						if(j == 0)
							g2.setColor(Color.GREEN);
						else
							g2.setColor(Color.RED);
						
						g2.fillOval(xL, yL, (int)(scale * FACE * .16), (int)(scale * FACE * .16));
						
						//	Draw X in middle of circle
						g2.setColor(Color.WHITE);
						g2r = g2.getTransform();
						g2.rotate(Math.toRadians(45),(int)(xL + (scale * FACE * .16) /2),(int)(scale * FACE * .16 / 2) + yL );
						g2.drawLine((int)(xL + (scale * FACE * .16) /2), yL, (int)(xL + (scale * FACE * .16) /2), (int)(scale * FACE * .16) + yL);
						g2.rotate(Math.toRadians(90),(int)(xL + (scale * FACE * .16) /2),(int)(scale * FACE * .16 / 2) + yL);
						g2.drawLine((int)(xL + (scale * FACE * .16) /2), yL, (int)(xL + (scale * FACE * .16) /2), (int)(scale * FACE * .16) + yL);
						g2.setTransform(g2r);
						yL += (int)((FACE - scale * FACE * .16 * 3) / 4 + scale * FACE * .16);
					}
					xL = (int)((SIZE.width * .2 + 2 * (FACE - scale * FACE * .16 * 2) / 3) + scale * FACE * .16);
				}
				break;
				
			case 7:
				//	Green circles
				scale = 1.5;
				xL = (int)(SIZE.width * .2 + (FACE - 3 * scale * FACE * .16) / 4);
				yL = (int)(scale * FACE * .16 * .2);
				
				for(int i = 0; i < 3; ++i)
				{	
					g2.setColor(Color.GREEN);
					g2.fillOval(xL, yL, (int)(scale * FACE * .16), (int)(scale * FACE * .16));
					
					//	Draw X in middle of circle
					g2.setColor(Color.WHITE);
					g2r = g2.getTransform();
					g2.rotate(Math.toRadians(45),(int)(xL + (scale * FACE * .16) /2),(int)(scale * FACE * .16 / 2) + yL );
					g2.drawLine((int)(xL + (scale * FACE * .16) /2), yL, (int)(xL + (scale * FACE * .16) /2), (int)(scale * FACE * .16) + yL);
					g2.rotate(Math.toRadians(90),(int)(xL + (scale * FACE * .16) /2),(int)(scale * FACE * .16 / 2) + yL);
					g2.drawLine((int)(xL + (scale * FACE * .16) /2), yL, (int)(xL + (scale * FACE * .16) /2), (int)(scale * FACE * .16) + yL);
					g2.setTransform(g2r);
					
					xL += (int)((FACE - 3 * scale * FACE * .16) / 4 + scale * FACE * .16);
					yL += (int)(scale * FACE * .16 * .5);
				}
				
				//	Red circles
				xL = (int)(SIZE.width * .2 + (FACE - scale * FACE * .16 * 2) / 3 );
				yL = (int)(FACE / 2 + ((FACE / 2 - scale * FACE * .16 * 2) / 3));
				
				for(int i =0; i < 2; ++i)
				{
					yL = (int)(FACE / 2 + ((FACE / 2 - scale * FACE * .16 * 2) / 3));
					for(int j = 0; j < 2; ++j)
					{
						g2.setColor(Color.RED);
						g2.fillOval(xL, yL, (int)(scale * FACE * .16), (int)(scale * FACE * .16));
						
						//	Draw X in middle of circle
						g2.setColor(Color.WHITE);
						g2r = g2.getTransform();
						g2.rotate(Math.toRadians(45),(int)(xL + (scale * FACE * .16) /2),(int)(scale * FACE * .16 / 2) + yL );
						g2.drawLine((int)(xL + (scale * FACE * .16) /2), yL, (int)(xL + (scale * FACE * .16) /2), (int)(scale * FACE * .16) + yL);
						g2.rotate(Math.toRadians(90),(int)(xL + (scale * FACE * .16) /2),(int)(scale * FACE * .16 / 2) + yL);
						g2.drawLine((int)(xL + (scale * FACE * .16) /2), yL, (int)(xL + (scale * FACE * .16) /2), (int)(scale * FACE * .16) + yL);
						g2.setTransform(g2r);
						
						yL += (int) (((FACE / 2 - scale * FACE * .16 * 2) / 3) + scale * FACE * .16);
					}
					xL += (int)((FACE - scale * FACE * .16 * 2) / 3 + scale * FACE * .16);
				}
				break;
				
			case 8:
				
				scale = 1.5;
				xL = (int)(SIZE.width * .2 + (FACE  - scale * FACE * .16 * 2) / 3);
				
				
				for(int i = 0; i < 2; ++i)
				{
					yL = (int)((FACE  - scale * FACE * .16 * 4) / 5);
					for(int j = 0; j < 4; ++j)
					{
						g2.setColor(Color.BLUE);
						g2.fillOval(xL, yL, (int)(scale * FACE * .16), (int)(scale * FACE * .16));
						
						//	Draw X in middle of circle
						g2.setColor(Color.WHITE);
						g2r = g2.getTransform();
						g2.rotate(Math.toRadians(45),(int)(xL + (scale * FACE * .16) /2),(int)(scale * FACE * .16 / 2) + yL );
						g2.drawLine((int)(xL + (scale * FACE * .16) /2), yL, (int)(xL + (scale * FACE * .16) /2), (int)(scale * FACE * .16) + yL);
						g2.rotate(Math.toRadians(90),(int)(xL + (scale * FACE * .16) /2),(int)(scale * FACE * .16 / 2) + yL);
						g2.drawLine((int)(xL + (scale * FACE * .16) /2), yL, (int)(xL + (scale * FACE * .16) /2), (int)(scale * FACE * .16) + yL);
						g2.setTransform(g2r);
						yL += (int)((FACE  - scale * FACE * .16 * 4) / 5 + scale * FACE * .16);
					}
					xL += (int)(scale * FACE * .16 + (FACE  - scale * FACE * .16 * 2) / 3);
				}
				break;
				
			case 9:
				
				//	Top left circle
				scale = 2;
				yL = (int)((FACE - scale * FACE * .16 * 3) / 4 + FACE * .005);
				
				for(int i = 0; i < 3; ++i)
				{
					xL = (int)(SIZE.width * .2 + (FACE - scale * FACE * .16 * 3) / 4 + FACE * .005);

					for(int j = 0; j < 3; ++j)
					{
						//	Set color
						if(i == 0)
							g2.setColor(Color.GREEN);
						else if(i == 1)
							g2.setColor(Color.RED);
						else if(i == 2)
							g2.setColor(Color.BLUE);
						
						g2.fillOval(xL, yL, (int)(scale * FACE * .16), (int)(scale * FACE * .16));
						
						//	Draw X in middle of circle
						g2.setColor(Color.WHITE);
						g2r = g2.getTransform();
						g2.rotate(Math.toRadians(45),(int)(xL + (scale * FACE * .16) /2),(int)(scale * FACE * .16 / 2) + yL );
						g2.drawLine((int)(xL + (scale * FACE * .16) /2), yL, (int)(xL + (scale * FACE * .16) /2), (int)(scale * FACE * .16) + yL);
						g2.rotate(Math.toRadians(90),(int)(xL + (scale * FACE * .16) /2),(int)(scale * FACE * .16 / 2) + yL);
						g2.drawLine((int)(xL + (scale * FACE * .16) /2), yL, (int)(xL + (scale * FACE * .16) /2), (int)(scale * FACE * .16) + yL);
						g2.setTransform(g2r);
						
						xL += (int)((FACE - scale * FACE * .16 * 3) / 4 + scale * FACE * .16);
					}
					yL += (int)((FACE - scale * FACE * .16 * 3) / 4 + scale * FACE * .16);
				}
				break;
		}
	}
	

	public static void main(String[] args)
	{
		JFrame	frame = new JFrame();

		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Circle Tiles");

		frame.add(new CircleTile(1));
		frame.add(new CircleTile(2));
		frame.add(new CircleTile(3));
		frame.add(new CircleTile(4));
		frame.add(new CircleTile(5));
		frame.add(new CircleTile(6));
		frame.add(new CircleTile(7));
		frame.add(new CircleTile(8));
		frame.add(new CircleTile(9));

		frame.pack();
		frame.setVisible(true);
	}
}
