package asteroids;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.LinkedList;

import javax.swing.JPanel;


public class AsteroidPanel extends JPanel{
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int frameWidth = screenSize.width;
	int frameHeight = screenSize.height;
	
	
	Player p;
	LinkedList<Asteroid> asteroidBelt;
	LinkedList<Missile> missiles;
	int asteroidScale = 20;
	
	public AsteroidPanel(Player p,LinkedList<Asteroid> asteroidBelt, LinkedList<Missile> missiles) {
		this.p = p;
		this.asteroidBelt = asteroidBelt;
		this.missiles = missiles;
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, frameWidth, frameHeight);
		g.setColor(Color.WHITE);
		drawPlayer(g);
		drawAsteroids(g);
		drawMissiles(g);
		return;
	}
	
	void drawMissiles(Graphics g) {
		for(int i=0; i< missiles.size();i++) {
			Missile boomie = missiles.get(i);
			g.fillOval((int)boomie.xPos-3, (int)boomie.yPos-3, 6, 6);
		}
	}
	
	void drawAsteroids(Graphics g) {
		for(int i=0; i< asteroidBelt.size();i++) {
			Asteroid roid = asteroidBelt.get(i);
			int radius = roid.size*asteroidScale/2;
			g.drawOval((int)roid.xPos-radius, (int)roid.yPos-radius, roid.size*asteroidScale, roid.size*asteroidScale);
		}
	}
	
	void drawPlayer(Graphics g) {
		/*
		 * about drawing the player, I dont want to calculate the rotation of the player by myself
		 * to use g2d, the rotation happens on a point.
		 * I want to have the player made up of multiple shapes each with a point on the player's center
		 * this will alow the player rotate on it's center as the shapes rotate individually
		 * screw it, ill do the rotation myself
		 * 
		 * If you rotate point (px, py) around point (ox, oy) by angle theta you'll get: 
		 * p'x = cos(theta) * (px-ox) - sin(theta) * (py-oy) + ox. 
		 * p'y = sin(theta) * (px-ox) + cos(theta) * (py-oy) + oy. 
		 * this is an easy way to rotate a point in 2D.
		 */
		
		int[] xPoints = {(int) p.x1,(int) p.x2,(int) p.x3,(int) p.x4};
		int[] yPoints = {(int) p.y1,(int) p.y2,(int) p.y3,(int) p.y4};

		g.drawPolygon(xPoints, yPoints, 4);
		
		return;
	}
}
