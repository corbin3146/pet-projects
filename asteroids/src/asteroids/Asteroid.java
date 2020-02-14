package asteroids;

import java.util.Random;

public class Asteroid {
	double xPos;
	double yPos;
	int size;
	double speed;//might be global later
	double direction;
	public Asteroid() {
		xPos = 0;
		yPos = 0;
		size = 0;
		speed = 0;
		direction = 0;
	}
	public Asteroid(double x, double y, double sp, double dir, int siz) {
		xPos = x;
		yPos = y;
		speed = sp;
		direction = dir;
		size = siz;
	}
	void move() {
		
		xPos = (xPos + Math.cos(Math.toRadians(direction)) * speed)%1000;
		yPos = (yPos + Math.sin(Math.toRadians(direction)) * speed)%1000;
		if (xPos<0) {
			xPos = 1000 - xPos;
		}
		if (yPos<0) {
			yPos = 1000 - yPos;
		}
	}
}
