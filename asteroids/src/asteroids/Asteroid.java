package asteroids;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Random;

public class Asteroid {
	double xPos;
	double yPos;
	int size;
	double speed;//might be global later
	double direction;
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int frameWidth = screenSize.width;
	int frameHeight = screenSize.height;
	
	public Asteroid() {
		Random r = new Random();
		xPos = r.nextDouble() * frameWidth;
		yPos = r.nextDouble() * frameHeight;
		size = (int) (1 + r.nextDouble() * 4);
		speed = 1 + r.nextDouble() * 6;
		direction = r.nextDouble() * 360;
	}
	public Asteroid(double x, double y, double sp, double dir, int siz) {
		xPos = x;
		yPos = y;
		speed = sp;
		direction = dir;
		size = siz;
	}
	void move() {
		
		xPos = (xPos + Math.cos(Math.toRadians(direction)) * speed)%frameWidth;
		yPos = (yPos + Math.sin(Math.toRadians(direction)) * speed)%frameHeight;
		if (xPos<0) {
			xPos = frameWidth - xPos;
		}
		if (yPos<0) {
			yPos = frameHeight - yPos;
		}
	}
}
