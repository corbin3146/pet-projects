package asteroids;

public class Player {
	double direction;
	double speed;
	double xPos;
	double yPos;
	int width;
	int length;
	public Player(int dir, double sp,int x,int y) {
		direction = dir;
		speed = sp;
		xPos = x;
		yPos = y;
		width = 15;
		length = 30;
	}
	void move() {
//		x1 = x + cos(ang) * distance;
//		y1 = y + sin(ang) * distance;
		if(speed > 10) {
			speed = 10;
		}
		if(speed < 0) {
			speed =0;
		}
		xPos = (xPos + Math.cos(Math.toRadians(direction)) * speed)%1000;
		yPos = (yPos + Math.sin(Math.toRadians(direction)) * speed)%1000;
		if (xPos<0) {
			xPos = 1000 - xPos;
		}
		if (yPos<0) {
			yPos = 1000 - yPos;
		}
		speed -= 0.07;
		
	}
}
