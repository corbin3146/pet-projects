package asteroids;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class AsteroidGraphics extends JPanel{
	Player p;
	public AsteroidGraphics(Player p) {
		this.p = p;
	}
	public void paint(Graphics g) {
		g.clearRect(0, 0, 1000, 1000);
		drawPlayer(g, p);
		//loop
		//DRAW asteroids
		return;
	}
	
	void drawPlayer(Graphics g, Player p) {
		/*
		 * about drawing the player, I dont want to calculate the rotation of the player by myself
		 * to use g2d, the rotation happens on a point.
		 * I want to have the player made up of multiple shapes each with a point on the player's center
		 * this will alow the player rotate on it's center as the shapes rotate individually
		 * screw it, ill do the rotation myself
		 * If you rotate point (px, py) around point (ox, oy) by angle theta you'll get: 
		 * p'x = cos(theta) * (px-ox) - sin(theta) * (py-oy) + ox. 
		 * p'y = sin(theta) * (px-ox) + cos(theta) * (py-oy) + oy. 
		 * this is an easy way to rotate a point in 2D.
		 * */
		
		
		int x0 = (int) p.xPos;
		int y0 = (int) p.yPos;
		double theta = Math.toRadians(p.direction);
		int width = p.width;
		int length = p.length;
		
		
		int x1 = (int) (0 - Math.sin(theta) * width + x0);
		int y1 = (int) (Math.cos(theta) * width + y0);
		
		int x2 = (int)(Math.cos(theta) * length + x0);
		int y2 = (int)(Math.sin(theta) * length + y0);
		
		int x3 = (int) (0 - Math.sin(theta) * (-1)* width + x0);
		int y3 = (int) (Math.cos(theta) * (-1) * width + y0);
		
		
		int x4 = (int)(Math.cos(theta) * (-1) * width + x0);
		int y4 = (int)(Math.sin(theta) * (-1) * width + y0);
		
		int[] xPoints = {x1,x2,x3,x4};
		int[] yPoints = {y1,y2,y3,y4};

		g.fillPolygon(xPoints, yPoints, 4);
		
		
		return;
	}
}
