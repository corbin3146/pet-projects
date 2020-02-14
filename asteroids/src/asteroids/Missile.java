package asteroids;

public class Missile {
	double direction;
	double speed;
	double xPos;
	double yPos;

	public Missile(Player p) {
		direction = p.direction;
		speed = 20;
		xPos = p.xPos;
		yPos = p.yPos;
	}
	boolean move() {
		
		xPos = (xPos + Math.cos(Math.toRadians(direction)) * speed);
		yPos = (yPos + Math.sin(Math.toRadians(direction)) * speed);
		if(xPos>1000 || yPos>1000) {
			return false;
		}
		return true;
	}
}
