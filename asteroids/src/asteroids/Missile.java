package asteroids;

import java.awt.Dimension;
import java.awt.Toolkit;

public class Missile {
	double direction;
	double speed;
	double xPos;
	double yPos;
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int frameWidth = screenSize.width;
	int frameHeight = screenSize.height;

	public Missile(Player p) {
		direction = p.direction;
		speed = 20;
		xPos = p.xPos;
		yPos = p.yPos;
	}
	boolean move() {
		
		xPos = (xPos + Math.cos(Math.toRadians(direction)) * speed);
		yPos = (yPos + Math.sin(Math.toRadians(direction)) * speed);
		if(xPos>frameWidth || yPos>frameHeight) {
			return false;
		}
		return true;
	}
}
