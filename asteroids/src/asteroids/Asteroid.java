package asteroids;

import java.util.Random;

public class Asteroid {
	int xPos;
	int yPos;
	int size;
	int speed;//might be global later
	int direction;
	public Asteroid() {
		Random r = new Random();
		xPos = 0;
		yPos = 0;
		size = 0;
		speed = 0;
		direction = 0;
	}
	public Asteroid(int x, int y, int sp, int dir) {
		Random r = new Random();
		xPos = x;
		yPos = y;
		speed = sp;
		direction = dir;
	}
}
