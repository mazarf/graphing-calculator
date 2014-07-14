import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import java.util.*;

public class Drawing extends JPanel {
	
	public String s = "x^(1/2)";
	public int minX = -10;
	public int maxX = 10;
	public int minY = -10;
	public int maxY = 10;
	
	public Drawing()
	{
		this.setBackground(Color.white);
	}
	
	public void paintComponent(Graphics g)
	{
		
		super.paintComponent(g);
		
		double i = minX;
		Dimension d = this.getSize();
		double xSpacing = Math.abs( d.getWidth() / (double)(maxX-minX) );
		double ySpacing = Math.abs( d.getHeight() / (double)(maxY-minY) );
		
		//double leftEdge = d.getWidth()/2 - minX * xSpacing;
		//double rightEdge = d.getWidth()/2 + maxX * xSpacing;
		//double bottomEdge = d.getWidth()/2 - minY * ySpacing;
		//double topEdge = d.getHeight()/2 - maxY * ySpacing;
		
		g.setColor(Color.black);
		g.drawLine(0, d.height/2, d.width, d.height/2);
		g.drawLine(d.width/2, 0, d.width/2, d.height);
		
		g.setColor(Color.red);
		while(i <= maxX)
		{
			if(!Double.isNaN(function(i,s)))
			{
				g.fillOval(d.width/2 + (int)(i * xSpacing) - 3, d.height/2 - (int)(function(i, s) * ySpacing) - 3, 6, 6);
			}
			i = i + 0.001;
		}
		
	}
	
	double function(double x, String s)
	{
		
		if(s != null)
		{
			return Parser.evaluate(s, x);
		}
		
		/*double num = x + 1;
		double denom = x*x - 2;
		
		if(denom != 0)
		{
			return (num / denom);
		}
		else
		{
			return 999e99;
		}*/
		
		return Math.pow(x, 0.5);
		
	}
}

