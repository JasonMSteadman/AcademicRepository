import java.awt.*;
import java.awt.geom.AffineTransform;

import javax.swing.*;

public class BambooTile extends RankTile
{
	
	//Basic constructor
	public BambooTile(int rank)
	{
		super(rank);
		setToolTipText(toString());
	}
	
	//Override toString()
	public String toString()
	{
		return "Bamboo " + rank;
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		Graphics2D	g2 = (Graphics2D)g;
		int					xL;
		int					yL;
		int					tempY;
		int					count;
		int[]				xAr;
		int[]				yAr;
		Color 				color;
		double				SCALE = .8;
		AffineTransform 	g2r = g2.getTransform();		
		
		switch(rank)
		{
			case 2:
				
				yL = tempY =(int) (((FACE - 5)/8) + FACE * .06);
				color = Color.BLUE;
				for(int j = 0; j < 2; ++j)
				{
					xL = (int)(SIZE.width * .2 + FACE * .5 - (FACE / 12)/2);
					g.setColor(color);
	
					//	Bamboo image 
					g.fillRect(xL, yL,(int)(FACE / 11), (int)(FACE * .333 - 5));
					for(int i = 0; i < 3; ++i)
					{	
						g.setColor(color);
						g.fillRect(xL - (int)(( 2 * FACE / 12) / 4 ), yL + (int)((FACE * .333 - 5)/8), (int)( 2 * FACE / 12), (int)((FACE * .333 - 5)/8));
						
						xAr = new int[] {xL - (int)(( 2 * FACE / 9) / 4 ), xL, xL};
						yAr = new int[] {yL + (int)(((FACE * .333 - 8)/8 + ((FACE * .333 - 5)/8)) - ((FACE * .333 - 5)/9)), yL, yL + 
								(int)((FACE * .333 - 5)/8 + ((FACE * .333 - 10)/8) - ((FACE * .333 - 5)/9))};
						g.fillPolygon(xAr, yAr, 3);
						
						xAr = new int[] {(int)((xL - (( 2 * FACE / 12) / 4 ) + ( 2 * FACE / 12))) ,(int)(xL + FACE / 11),(int)(xL + FACE / 11)};
						yAr = new int[] {yL + (int)(((FACE * .333 - 10)/8 + ((FACE * .333 - 5)/8)) - ((FACE * .333 - 5)/9)), yL, yL + 
								(int)((FACE * .333 - 5)/8 + ((FACE * .333 - 10)/8) - ((FACE * .333 - 5)/9))};
						g.fillPolygon(xAr, yAr, 3);
						yL += (int)((FACE * .333 - 5)/8 * 3.2);
					}	
					
					yL = tempY;
					g.setColor(Color.WHITE);
					g.fillRect(xL + (int)(FACE/11 / 3 * 1.7) , yL + (int)((FACE * .333 - 5) * .1), (int)(FACE / 11 / 4), (int)((FACE * .333 - 5) * .86));
					
					yL += (int)((FACE * .333 - 5)/8 * 3.2);
					g.setColor(color);
					g.fillRect(xL , yL,(int)(FACE / 11), (int)((FACE * .333 - 5)/8 * 3.2 / 1.7));
					
					yL = tempY += (int) (FACE * .333 + 5);
					color = Color.GREEN;
				}
				break;
			
			case 3:
			
				yL = tempY =(int) (((FACE - 5)/8) + FACE * .06);
				color = Color.BLUE;
		
				xL = (int)(SIZE.width * .2 + FACE * .5 - (FACE / 12)/2);
				g.setColor(color);

				//	Bamboo image 
				g.fillRect(xL, yL,(int)(FACE / 11), (int)(FACE * .333 - 5));
				for(int i = 0; i < 3; ++i)
				{	
					g.setColor(color);
					g.fillRect(xL - (int)(( 2 * FACE / 12) / 4 ), yL + (int)((FACE * .333 - 5)/8), (int)( 2 * FACE / 12), (int)((FACE * .333 - 5)/8));
					
					xAr = new int[] {xL - (int)(( 2 * FACE / 9) / 4 ), xL, xL};
					yAr = new int[] {yL + (int)(((FACE * .333 - 8)/8 + ((FACE * .333 - 5)/8)) - ((FACE * .333 - 5)/9)), yL, yL + 
							(int)((FACE * .333 - 5)/8 + ((FACE * .333 - 10)/8) - ((FACE * .333 - 5)/9))};
					g.fillPolygon(xAr, yAr, 3);
					
					xAr = new int[] {(int)((xL - (( 2 * FACE / 12) / 4 ) + ( 2 * FACE / 12))) ,(int)(xL + FACE / 11),(int)(xL + FACE / 11)};
					yAr = new int[] {yL + (int)(((FACE * .333 - 10)/8 + ((FACE * .333 - 5)/8)) - ((FACE * .333 - 5)/9)), yL, yL + 
							(int)((FACE * .333 - 5)/8 + ((FACE * .333 - 10)/8) - ((FACE * .333 - 5)/9))};
					g.fillPolygon(xAr, yAr, 3);
					yL += (int)((FACE * .333 - 5)/8 * 3.2);
				}	
				
				yL = tempY;
				g.setColor(Color.WHITE);
				g.fillRect(xL + (int)(FACE/11 / 3 * 1.7) , yL + (int)((FACE * .333 - 5) * .1), (int)(FACE / 11 / 4), (int)((FACE * .333 - 5) * .86));
				
				yL += (int)((FACE * .333 - 5)/8 * 3.2);
				g.setColor(color);
				g.fillRect(xL , yL,(int)(FACE / 11), (int)((FACE * .333 - 5)/8 * 3.2 / 1.7));
				
				yL = tempY += (int) (FACE * .333 + 5);
				
				xL = (int) (SIZE.width * .2 + (FACE * .37) * 2 / 3);
				color = Color.GREEN;
				g.setColor(color);
				
				for(int j = 0; j < 2; ++j)
				{
					//	Bamboo image 
					g.fillRect(xL, yL,(int)(FACE / 11), (int)(FACE * .333 - 5));
					for(int i = 0; i < 3; ++i)
					{	
						g.setColor(color);
						g.fillRect(xL - (int)(( 2 * FACE / 12) / 4 ), yL + (int)((FACE * .333 - 5)/8), (int)( 2 * FACE / 12), (int)((FACE * .333 - 5)/8));
						
						xAr = new int[] {xL - (int)(( 2 * FACE / 9) / 4 ), xL, xL};
						yAr = new int[] {yL + (int)(((FACE * .333 - 8)/8 + ((FACE * .333 - 5)/8)) - ((FACE * .333 - 5)/9)), yL, yL + 
								(int)((FACE * .333 - 5)/8 + ((FACE * .333 - 10)/8) - ((FACE * .333 - 5)/9))};
						g.fillPolygon(xAr, yAr, 3);
						
						xAr = new int[] {(int)((xL - (( 2 * FACE / 12) / 4 ) + ( 2 * FACE / 12))) ,(int)(xL + FACE / 11),(int)(xL + FACE / 11)};
						yAr = new int[] {yL + (int)(((FACE * .333 - 10)/8 + ((FACE * .333 - 5)/8)) - ((FACE * .333 - 5)/9)), yL, yL + 
								(int)((FACE * .333 - 5)/8 + ((FACE * .333 - 10)/8) - ((FACE * .333 - 5)/9))};
						g.fillPolygon(xAr, yAr, 3);
						yL += (int)((FACE * .333 - 5)/8 * 3.2);
					}	
					
					yL = tempY;
					g.setColor(Color.WHITE);
					g.fillRect(xL + (int)(FACE/11 / 3 * 1.7) , yL + (int)((FACE * .333 - 5) * .1), (int)(FACE / 11 / 4), (int)((FACE * .333 - 5) * .86));
					
					yL += (int)((FACE * .333 - 5)/8 * 3.2);
					g.setColor(color);
					g.fillRect(xL , yL,(int)(FACE / 11), (int)((FACE * .333 - 5)/8 * 3.2 / 1.7));
					yL = tempY;
					xL += xL - (int) (FACE / 11);
				}
				
				break;
				
			case 4:
				
				xL = (int) (SIZE.width * .2 + (FACE * .37) * 2 / 3);
				count = 1;
				
				for(int j = 0; j < 2; ++j)
				{
					yL = tempY =(int) (((FACE - 5)/8) + FACE * .06);
					for(int k = 0; k < 2; ++k)
					{
						if(count == 1 || count == 4)
							color = Color.GREEN;
						else
							color = Color.BLUE;
						++count;
						//	Bamboo image 
						g.setColor(color);
						g.fillRect(xL, yL,(int)(FACE / 11), (int)(FACE * .333 - 5));
						for(int i = 0; i < 3; ++i)
						{	
							g.fillRect(xL - (int)(( 2 * FACE / 12) / 4 ), yL + (int)((FACE * .333 - 5)/8), (int)( 2 * FACE / 12), (int)((FACE * .333 - 5)/8));
							
							xAr = new int[] {xL - (int)(( 2 * FACE / 9) / 4 ), xL, xL};
							yAr = new int[] {yL + (int)(((FACE * .333 - 8)/8 + ((FACE * .333 - 5)/8)) - ((FACE * .333 - 5)/9)), yL, yL + 
									(int)((FACE * .333 - 5)/8 + ((FACE * .333 - 10)/8) - ((FACE * .333 - 5)/9))};
							g.fillPolygon(xAr, yAr, 3);
							
							xAr = new int[] {(int)((xL - (( 2 * FACE / 12) / 4 ) + ( 2 * FACE / 12))) ,(int)(xL + FACE / 11),(int)(xL + FACE / 11)};
							yAr = new int[] {yL + (int)(((FACE * .333 - 10)/8 + ((FACE * .333 - 5)/8)) - ((FACE * .333 - 5)/9)), yL, yL + 
									(int)((FACE * .333 - 5)/8 + ((FACE * .333 - 10)/8) - ((FACE * .333 - 5)/9))};
							g.fillPolygon(xAr, yAr, 3);
							yL += (int)((FACE * .333 - 5)/8 * 3.2);
						}	
						
						yL = tempY;
						g.setColor(Color.WHITE);
						g.fillRect(xL + (int)(FACE/11 / 3 * 1.7) , yL + (int)((FACE * .333 - 5) * .1), (int)(FACE / 11 / 4), (int)((FACE * .333 - 5) * .86));
						
						yL += (int)((FACE * .333 - 5)/8 * 3.2);
						g.setColor(color);
						g.fillRect(xL , yL,(int)(FACE / 11), (int)((FACE * .333 - 5)/8 * 3.2 / 1.7));
						
						yL = tempY += (int) (FACE * .333 + 5);
					}
					xL += xL - (int) (FACE / 11);
					
				}
				break;
				
			case 5:
				
				xL = (int) (SIZE.width * .2 + (FACE - (FACE / 11) * 3 )/ 4);
				count = 1;
				
				for(int j = 0; j < 2; ++j)
				{
					yL = tempY =(int) (((FACE - 5)/8) + FACE * .06);
					for(int k = 0; k < 2; ++k)
					{
						if(count == 1 || count == 4)
							color = Color.GREEN;
						else
							color = Color.BLUE;
						++count;
						//	Bamboo image 
						g.setColor(color);
						g.fillRect(xL, yL,(int)(FACE / 11), (int)(FACE * .333 - 5));
						for(int i = 0; i < 3; ++i)
						{	
							g.fillRect(xL - (int)(( 2 * FACE / 12) / 4 ), yL + (int)((FACE * .333 - 5)/8), (int)( 2 * FACE / 12), (int)((FACE * .333 - 5)/8));
							
							xAr = new int[] {xL - (int)(( 2 * FACE / 9) / 4 ), xL, xL};
							yAr = new int[] {yL + (int)(((FACE * .333 - 8)/8 + ((FACE * .333 - 5)/8)) - ((FACE * .333 - 5)/9)), yL, yL + 
									(int)((FACE * .333 - 5)/8 + ((FACE * .333 - 10)/8) - ((FACE * .333 - 5)/9))};
							g.fillPolygon(xAr, yAr, 3);
							
							xAr = new int[] {(int)((xL - (( 2 * FACE / 12) / 4 ) + ( 2 * FACE / 12))) ,(int)(xL + FACE / 11),(int)(xL + FACE / 11)};
							yAr = new int[] {yL + (int)(((FACE * .333 - 10)/8 + ((FACE * .333 - 5)/8)) - ((FACE * .333 - 5)/9)), yL, yL + 
									(int)((FACE * .333 - 5)/8 + ((FACE * .333 - 10)/8) - ((FACE * .333 - 5)/9))};
							g.fillPolygon(xAr, yAr, 3);
							yL += (int)((FACE * .333 - 5)/8 * 3.2);
						}	
						
						yL = tempY;
						g.setColor(Color.WHITE);
						g.fillRect(xL + (int)(FACE/11 / 3 * 1.7) , yL + (int)((FACE * .333 - 5) * .1), (int)(FACE / 11 / 4), (int)((FACE * .333 - 5) * .86));
						
						yL += (int)((FACE * .333 - 5)/8 * 3.2);
						g.setColor(color);
						g.fillRect(xL , yL,(int)(FACE / 11), (int)((FACE * .333 - 5)/8 * 3.2 / 1.7));
						
						yL = tempY += (int) (FACE * .333 + 5);
					}
					xL =+ 2 * xL + (FACE / 11);
				}
				
				//	Red middle
				yL = tempY =(int) (((FACE - 5)/8) + 10 + (FACE * .333 - 5) / 2);
				color = Color.RED;
				
				xL = (int)(SIZE.width * .2 + FACE * .5 - (FACE / 11)/2);
				g.setColor(color);

				//	Bamboo image 
				g.fillRect(xL, yL,(int)(FACE / 11), (int)(FACE * .333 - 5));
				for(int i = 0; i < 3; ++i)
				{	
					g.setColor(color);
					g.fillRect(xL - (int)(( 2 * FACE / 12) / 4 ), yL + (int)((FACE * .333 - 5)/8), (int)( 2 * FACE / 12), (int)((FACE * .333 - 5)/8));
					
					xAr = new int[] {xL - (int)(( 2 * FACE / 9) / 4 ), xL, xL};
					yAr = new int[] {yL + (int)(((FACE * .333 - 8)/8 + ((FACE * .333 - 5)/8)) - ((FACE * .333 - 5)/9)), yL, yL + 
							(int)((FACE * .333 - 5)/8 + ((FACE * .333 - 10)/8) - ((FACE * .333 - 5)/9))};
					g.fillPolygon(xAr, yAr, 3);
					
					xAr = new int[] {(int)((xL - (( 2 * FACE / 12) / 4 ) + ( 2 * FACE / 12))) ,(int)(xL + FACE / 11),(int)(xL + FACE / 11)};
					yAr = new int[] {yL + (int)(((FACE * .333 - 10)/8 + ((FACE * .333 - 5)/8)) - ((FACE * .333 - 5)/9)), yL, yL + 
							(int)((FACE * .333 - 5)/8 + ((FACE * .333 - 10)/8) - ((FACE * .333 - 5)/9))};
					g.fillPolygon(xAr, yAr, 3);
					yL += (int)((FACE * .333 - 5)/8 * 3.2);
				}	
				
				yL = tempY;
				g.setColor(Color.WHITE);
				g.fillRect(xL + (int)(FACE/11 / 3 * 1.7) , yL + (int)((FACE * .333 - 5) * .1), (int)(FACE / 11 / 4), (int)((FACE * .333 - 5) * .86));
				
				yL += (int)((FACE * .333 - 5)/8 * 3.2);
				g.setColor(color);
				g.fillRect(xL , yL,(int)(FACE / 11), (int)((FACE * .333 - 5)/8 * 3.2 / 1.7));
				
				yL = tempY += (int) (FACE * .333 + 5);
				break;
				
			case 6:
				xL = (int) (SIZE.width * .2 + (FACE - (FACE / 11) * 3 )/ 4);
				yL = tempY =(int) (((FACE - 5)/8) + FACE * .06);
				count = 1;
				
				for(int j = 0; j < 2; ++j)
				{
					xL = (int) (SIZE.width * .2 + (FACE - (FACE / 11) * 3 )/ 4);
					color = (j == 0) ? Color.GREEN : Color.BLUE;
					
					for(int k = 0; k < 3; ++k)
					{
						//	Bamboo image 
						g.setColor(color);
						g.fillRect(xL, yL,(int)(FACE / 11), (int)(FACE * .333 - 5));
						for(int i = 0; i < 3; ++i)
						{	
							g.fillRect(xL - (int)(( 2 * FACE / 12) / 4 ), yL + (int)((FACE * .333 - 5)/8), (int)( 2 * FACE / 12), (int)((FACE * .333 - 5)/8));
							
							xAr = new int[] {xL - (int)(( 2 * FACE / 9) / 4 ), xL, xL};
							yAr = new int[] {yL + (int)(((FACE * .333 - 8)/8 + ((FACE * .333 - 5)/8)) - ((FACE * .333 - 5)/9)), yL, yL + 
									(int)((FACE * .333 - 5)/8 + ((FACE * .333 - 10)/8) - ((FACE * .333 - 5)/9))};
							g.fillPolygon(xAr, yAr, 3);
							
							xAr = new int[] {(int)((xL - (( 2 * FACE / 12) / 4 ) + ( 2 * FACE / 12))) ,(int)(xL + FACE / 11),(int)(xL + FACE / 11)};
							yAr = new int[] {yL + (int)(((FACE * .333 - 10)/8 + ((FACE * .333 - 5)/8)) - ((FACE * .333 - 5)/9)), yL, yL + 
									(int)((FACE * .333 - 5)/8 + ((FACE * .333 - 10)/8) - ((FACE * .333 - 5)/9))};
							g.fillPolygon(xAr, yAr, 3);
							yL += (int)((FACE * .333 - 5)/8 * 3.2);
						}	
						
						yL = tempY;
						g.setColor(Color.WHITE);
						g.fillRect(xL + (int)(FACE/11 / 3 * 1.7) , yL + (int)((FACE * .333 - 5) * .1), (int)(FACE / 11 / 4), (int)((FACE * .333 - 5) * .86));
						
						yL += (int)((FACE * .333 - 5)/8 * 3.2);
						g.setColor(color);
						g.fillRect(xL , yL,(int)(FACE / 11), (int)((FACE * .333 - 5)/8 * 3.2 / 1.7));
						yL = tempY;
						
						xL += (int)((FACE - (FACE / 11) * 3 )/ 4 + (FACE / 11));
					}
					yL = tempY += (int) (FACE * .333 + 5);
				}
				break;
				
			case 7:
				
				xL = (int) (SIZE.width * .2 + (FACE - (FACE / 11) * 3 )/ 4);
				yL = tempY = (int)(FACE * .03);
				count = 1;
				
				for(int j = 0; j < 3; ++j)
				{
					xL = (int) (SIZE.width * .2 + (FACE - (FACE / 11) * 3 )/ 4);					
					for(int k = 0; k < 3; ++k)
					{
						if(j == 0 && k != 1)
						{
							xL += (int)((FACE - (FACE / 11) * 3 )/ 4 + (FACE / 11));
							continue;
						}
						//	Bamboo image 
						if( j == 0)
							color = Color.RED;
						else if (k == 1)
							color = Color.BLUE;
						else
							color = Color.GREEN;
						
						g.setColor(color);
						g.fillRect(xL, yL,(int)(FACE / 11), (int)(FACE * .333 - 5));
						for(int i = 0; i < 3; ++i)
						{	
							g.fillRect(xL - (int)(( 2 * FACE / 12) / 4 ), yL + (int)((FACE * .333 - 5)/8), (int)( 2 * FACE / 12), (int)((FACE * .333 - 5)/8));
							
							xAr = new int[] {xL - (int)(( 2 * FACE / 9) / 4 ), xL, xL};
							yAr = new int[] {yL + (int)(((FACE * .333 - 8)/8 + ((FACE * .333 - 5)/8)) - ((FACE * .333 - 5)/9)), yL, yL + 
									(int)((FACE * .333 - 5)/8 + ((FACE * .333 - 10)/8) - ((FACE * .333 - 5)/9))};
							g.fillPolygon(xAr, yAr, 3);
							
							xAr = new int[] {(int)((xL - (( 2 * FACE / 12) / 4 ) + ( 2 * FACE / 12))) ,(int)(xL + FACE / 11),(int)(xL + FACE / 11)};
							yAr = new int[] {yL + (int)(((FACE * .333 - 10)/8 + ((FACE * .333 - 5)/8)) - ((FACE * .333 - 5)/9)), yL, yL + 
									(int)((FACE * .333 - 5)/8 + ((FACE * .333 - 10)/8) - ((FACE * .333 - 5)/9))};
							g.fillPolygon(xAr, yAr, 3);
							yL += (int)((FACE * .333 - 5)/8 * 3.2);
						}	
						
						yL = tempY;
						g.setColor(Color.WHITE);
						g.fillRect(xL + (int)(FACE/11 / 3 * 1.7) , yL + (int)((FACE * .333 - 5) * .1), (int)(FACE / 11 / 4), (int)((FACE * .333 - 5) * .86));
						
						yL += (int)((FACE * .333 - 5)/8 * 3.2);
						g.setColor(color);
						g.fillRect(xL , yL,(int)(FACE / 11), (int)((FACE * .333 - 5)/8 * 3.2 / 1.7));
						yL = tempY;
						
						xL += (int)((FACE - (FACE / 11) * 3 )/ 4 + (FACE / 11));
					}
					yL = tempY += (int) (FACE * .32);
				}
				break;
				
			case 8:
				
				xL = (int) (SIZE.width * .2 + (FACE * .08));				
				for(int j = 0; j < 2; ++j)
				{
					yL = tempY =(int) (((FACE - 5)/8) + FACE * .06);
					for(int k = 0; k < 2; ++k)
					{
						color = (k == 0) ? Color.GREEN : Color.BLUE;
						//	Bamboo image 
						g.setColor(color);
						g.fillRect(xL, yL,(int)(FACE / 11), (int)(FACE * .333 - 5));
						for(int i = 0; i < 3; ++i)
						{	
							g.fillRect(xL - (int)(( 2 * FACE / 12) / 4 ), yL + (int)((FACE * .333 - 5)/8), (int)( 2 * FACE / 12), (int)((FACE * .333 - 5)/8));
							
							xAr = new int[] {xL - (int)(( 2 * FACE / 9) / 4 ), xL, xL};
							yAr = new int[] {yL + (int)(((FACE * .333 - 8)/8 + ((FACE * .333 - 5)/8)) - ((FACE * .333 - 5)/9)), yL, yL + 
									(int)((FACE * .333 - 5)/8 + ((FACE * .333 - 10)/8) - ((FACE * .333 - 5)/9))};
							g.fillPolygon(xAr, yAr, 3);
							
							xAr = new int[] {(int)((xL - (( 2 * FACE / 12) / 4 ) + ( 2 * FACE / 12))) ,(int)(xL + FACE / 11),(int)(xL + FACE / 11)};
							yAr = new int[] {yL + (int)(((FACE * .333 - 10)/8 + ((FACE * .333 - 5)/8)) - ((FACE * .333 - 5)/9)), yL, yL + 
									(int)((FACE * .333 - 5)/8 + ((FACE * .333 - 10)/8) - ((FACE * .333 - 5)/9))};
							g.fillPolygon(xAr, yAr, 3);
							yL += (int)((FACE * .333 - 5)/8 * 3.2);
						}	
						
						yL = tempY;
						g.setColor(Color.WHITE);
						g.fillRect(xL + (int)(FACE/11 / 3 * 1.7) , yL + (int)((FACE * .333 - 5) * .1), (int)(FACE / 11 / 4), (int)((FACE * .333 - 5) * .86));
						
						yL += (int)((FACE * .333 - 5)/8 * 3.2);
						g.setColor(color);
						g.fillRect(xL , yL,(int)(FACE / 11), (int)((FACE * .333 - 5)/8 * 3.2 / 1.7));
						
						yL = tempY += (int) (FACE * .333 + 5);
					}
					xL = (int)(SIZE.width * .2 + FACE -(FACE * .08) - FACE / 11);				
				}
				
				//	Angled bamboo
				xL = (int) (SIZE.width * .2 + FACE / 13 * 4);
				
				for(int j = 0; j < 2; ++j)
				{
					yL = tempY =(int) (((FACE - 5)/8) + FACE * .05);
					for(int k = 0; k < 2; ++k)
					{
						if(j == 0 && k == 0)
							g2.rotate(Math.toRadians(45), xL + (int)(FACE / 11 / 2), yL + (int)((FACE * .333 - 5)/2)); 	
						else if(j == 0 && k == 1)
							g2.rotate(Math.toRadians(-45), xL + (int)(FACE / 11 / 2), yL + (int)((FACE * .333 - 5)/2)); 	
						else if(j == 1 && k == 0)
						{
							g2.rotate(Math.toRadians(-45), xL + (int)(FACE / 11 / 2), (int)((FACE * .333 - 5)/2)); 	
							yL += (int)(FACE * .05);
							xL -=(int)(FACE * .03);
						}
						else if(j == 1 && k == 1)
						{
							xL = (int)(SIZE.width * .2 + FACE  - FACE / 11 - FACE * .05);
							g2.rotate(Math.toRadians(45), xL + (int)(FACE / 11 / 2), (int)((FACE * .333 - 5)/2));
							xL +=(int)(FACE * .2);
							
						}


						color = (k == 0) ? Color.GREEN : Color.BLUE;
						//	Bamboo image 
						g.setColor(color);
						g.fillRect(xL, yL,(int)(FACE / 11), (int)(FACE * .333 - 5));
						for(int i = 0; i < 3; ++i)
						{	
							g.fillRect(xL - (int)(( 2 * FACE / 12) / 4 ), yL + (int)((FACE * .333 - 5)/8), (int)( 2 * FACE / 12), (int)((FACE * .333 - 5)/8));
							
							xAr = new int[] {xL - (int)(( 2 * FACE / 9) / 4 ), xL, xL};
							yAr = new int[] {yL + (int)(((FACE * .333 - 8)/8 + ((FACE * .333 - 5)/8)) - ((FACE * .333 - 5)/9)), yL, yL + 
									(int)((FACE * .333 - 5)/8 + ((FACE * .333 - 10)/8) - ((FACE * .333 - 5)/9))};
							g.fillPolygon(xAr, yAr, 3);
							
							xAr = new int[] {(int)((xL - (( 2 * FACE / 12) / 4 ) + ( 2 * FACE / 12))) ,(int)(xL + FACE / 11),(int)(xL + FACE / 11)};
							yAr = new int[] {yL + (int)(((FACE * .333 - 10)/8 + ((FACE * .333 - 5)/8)) - ((FACE * .333 - 5)/9)), yL, yL + 
									(int)((FACE * .333 - 5)/8 + ((FACE * .333 - 10)/8) - ((FACE * .333 - 5)/9))};
							g.fillPolygon(xAr, yAr, 3);
							yL += (int)((FACE * .333 - 5)/8 * 3.2);
							
						}	
						
						yL = tempY;
						g.setColor(Color.WHITE);
						g.fillRect(xL + (int)(FACE/11 / 3 * 1.7) , yL + (int)((FACE * .333 - 5) * .1), (int)(FACE / 11 / 4), (int)((FACE * .333 - 5) * .86));
						
						yL += (int)((FACE * .333 - 5)/8 * 3.2);
						g.setColor(color);
						g.fillRect(xL , yL,(int)(FACE / 11), (int)((FACE * .333 - 5)/8 * 3.2 / 1.7));
						
						yL = tempY += (int) (FACE * .333 + 5);
						g2.setTransform(g2r);
					}
					xL = (int)(SIZE.width * .2 + FACE - (FACE * .4) - FACE / 11);				
				}
				break;
				
			case 9:
				
				xL = (int) (SIZE.width * .2 + (FACE - (FACE / 11) * 3 )/ 4);
				yL = tempY = (int)(FACE * .03);
				count = 1;
				
				for(int j = 0; j < 3; ++j)
				{
					xL = (int) (SIZE.width * .2 + (FACE - (FACE / 11) * 3 )/ 4);					
					for(int k = 0; k < 3; ++k)
					{
						//	Bamboo image 
						if( k == 0)
							color = Color.RED;
						else if (k == 1)
							color = Color.BLUE;
						else
							color = Color.GREEN;
						
						g.setColor(color);
						g.fillRect(xL, yL,(int)(FACE / 11), (int)(FACE * .333 - 5));
						for(int i = 0; i < 3; ++i)
						{	
							g.fillRect(xL - (int)(( 2 * FACE / 12) / 4 ), yL + (int)((FACE * .333 - 5)/8), (int)( 2 * FACE / 12), (int)((FACE * .333 - 5)/8));
							
							xAr = new int[] {xL - (int)(( 2 * FACE / 9) / 4 ), xL, xL};
							yAr = new int[] {yL + (int)(((FACE * .333 - 8)/8 + ((FACE * .333 - 5)/8)) - ((FACE * .333 - 5)/9)), yL, yL + 
									(int)((FACE * .333 - 5)/8 + ((FACE * .333 - 10)/8) - ((FACE * .333 - 5)/9))};
							g.fillPolygon(xAr, yAr, 3);
							
							xAr = new int[] {(int)((xL - (( 2 * FACE / 12) / 4 ) + ( 2 * FACE / 12))) ,(int)(xL + FACE / 11),(int)(xL + FACE / 11)};
							yAr = new int[] {yL + (int)(((FACE * .333 - 10)/8 + ((FACE * .333 - 5)/8)) - ((FACE * .333 - 5)/9)), yL, yL + 
									(int)((FACE * .333 - 5)/8 + ((FACE * .333 - 10)/8) - ((FACE * .333 - 5)/9))};
							g.fillPolygon(xAr, yAr, 3);
							yL += (int)((FACE * .333 - 5)/8 * 3.2);
						}	
						
						yL = tempY;
						g.setColor(Color.WHITE);
						g.fillRect(xL + (int)(FACE/11 / 3 * 1.7) , yL + (int)((FACE * .333 - 5) * .1), (int)(FACE / 11 / 4), (int)((FACE * .333 - 5) * .86));
						
						yL += (int)((FACE * .333 - 5)/8 * 3.2);
						g.setColor(color);
						g.fillRect(xL , yL,(int)(FACE / 11), (int)((FACE * .333 - 5)/8 * 3.2 / 1.7));
						yL = tempY;
						
						xL += (int)((FACE - (FACE / 11) * 3 )/ 4 + (FACE / 11));
					}
					yL = tempY += (int) (FACE * .32);
				}

				break;
		}
		
	}
	
	public static void main(String[] args)
	{
		JFrame	frame = new JFrame();

		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Bamboo Tiles");

		frame.add(new BambooTile(2));
		frame.add(new BambooTile(3));
		frame.add(new BambooTile(4));
		frame.add(new BambooTile(5));
		frame.add(new BambooTile(6));
		frame.add(new BambooTile(7));
		frame.add(new BambooTile(8));
		frame.add(new BambooTile(9));

		frame.pack();
		frame.setVisible(true);
	}
}
