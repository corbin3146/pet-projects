package asteroids;

import java.awt.Graphics;
import java.util.LinkedList;

import javax.swing.JPanel;

public class AsteroidGraphics extends JPanel{
	Player p;
	LinkedList<Asteroid> asteroidBelt;
	LinkedList<Missile> missiles;
	int asteroidScale = 20;
	
	public AsteroidGraphics(Player p,LinkedList<Asteroid> asteroidBelt, LinkedList<Missile> missiles) {
		this.p = p;
		this.asteroidBelt = asteroidBelt;
		this.missiles = missiles;
	}
	
	public void paint(Graphics g) {
		g.clearRect(0, 0, 1000, 1000);
		drawPlayer(g);
		drawAsteroids(g);
		drawMissiles(g);
		return;
	}
	
	void drawMissiles(Graphics g) {
		for(int i=0; i< missiles.size();i++) {
			Missile boomie = missiles.get(i);
			g.fillOval((int)boomie.xPos, (int)boomie.yPos, 5, 5);
		}
	}
	
	void drawAsteroids(Graphics g) {
		for(int i=0; i< asteroidBelt.size();i++) {
			Asteroid roid = asteroidBelt.get(i);
			g.fillOval((int)roid.xPos, (int)roid.yPos, roid.size*asteroidScale, roid.size*asteroidScale);
		}
	}
	
	void drawPlayer(Graphics g) {
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
		double cosTheta = Math.cos(theta);
		double sinTheta = Math.sin(theta);
		
		
		int x1 = (int) ((0 - sinTheta) * width + x0);
		int y1 = (int) (cosTheta * width + y0);
		
		int x2 = (int)(cosTheta * length + x0);
		int y2 = (int)(sinTheta * length + y0);
		
		int x3 = (int) (0 - sinTheta * (-1)* width + x0);
		int y3 = (int) (cosTheta * (-1) * width + y0);
		
		
		int x4 = (int)(cosTheta * (-1) * width + x0);
		int y4 = (int)(sinTheta * (-1) * width + y0);
		
		int[] xPoints = {x1,x2,x3,x4};
		int[] yPoints = {y1,y2,y3,y4};

		g.fillPolygon(xPoints, yPoints, 4);
		
		
		return;
	}
}
