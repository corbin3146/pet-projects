package asteroids;

import java.awt.Dimension;
import java.awt.Toolkit;

public class Player {
	double direction;
	double oldDirection;
	double speed;
	double xPos;
	double yPos;
	double x1;
	double y1;
	double x2;
	double y2;
	double x3;
	double y3;
	double x4;
	double y4;
	int width;
	int length;
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int frameWidth = screenSize.width;
	int frameHeight = screenSize.height;
	
	
	public Player(int dir, double sp,int x,int y) {
		direction = dir;
		oldDirection = 1;
		speed = sp;
		xPos = x;
		yPos = y;
		width = 15;
		length = 30;
		x1 = xPos + length;
		y1 = yPos;
		x2 = xPos;
		y2 = yPos + width;
		x3 = xPos - width;
		y3 = yPos;
		x4 = xPos;
		y4 = yPos - width;
	}
	void move() {
		if(speed > 10) {
			speed = 10;
		}
		if(speed < 0) {
			speed =0;
		}
		
		//if the player rotated, recalculate points
		if(oldDirection != direction) {
			double theta = Math.toRadians(direction);
			double cosTheta = Math.cos(theta);
			double sinTheta = Math.sin(theta);
			x1 = (0 - sinTheta) * width + xPos;
			y1 = cosTheta * width + yPos;
			
			x2 = cosTheta * length + xPos;
			y2 = sinTheta * length + yPos;
			
			x3 = 0 - sinTheta * (-1)* width + xPos;
			y3 = cosTheta * (-1) * width + yPos;
			
			
			x4 = cosTheta * (-1) * width + xPos;
			y4 = sinTheta * (-1) * width + yPos;
		}
		oldDirection = direction;
		
		double deltaX = Math.cos(Math.toRadians(direction)) * speed;
		double deltaY = Math.sin(Math.toRadians(direction)) * speed;
		
		xPos += deltaX;
		yPos += deltaY;
		x1 += deltaX;
		x2 += deltaX;
		x3 += deltaX;
		x4 += deltaX;		
		y1 += deltaY;
		y2 += deltaY;
		y3 += deltaY;
		y4 += deltaY;		
		
		
		if(xPos > frameWidth){
			xPos -= frameWidth;
			x1 -= frameWidth;
			x2 -= frameWidth;
			x3 -= frameWidth;
			x4 -= frameWidth;	
		}
		if(yPos > frameHeight) {
			yPos -= frameHeight;
			y1 -= frameHeight;
			y2 -= frameHeight;
			y3 -= frameHeight;
			y4 -= frameHeight;
		}
		if (xPos<0) {
			xPos += frameWidth;
			x1 += frameWidth;
			x2 += frameWidth;
			x3 += frameWidth;
			x4 += frameWidth;
		}
		if (yPos<0) {
			yPos += frameHeight;
			y1 += frameHeight;
			y2 += frameHeight;
			y3 += frameHeight;
			y4 += frameHeight;
		}
		speed -= 0.07;
		
	}
}
