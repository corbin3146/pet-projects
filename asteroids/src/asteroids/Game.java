package asteroids;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.LinkedList;

public class Game {
	int fpsCap = 60;
	int missilesPerSecond = 4;
	Thread thread1;

	public int getfoo() {
		return fpsCap;

	}

	Game() {
		
		int score =0;
		
		Player p = new Player(0, 0, 500, 500);

		LinkedList<Asteroid> asteroidBelt = new LinkedList<Asteroid>();
		LinkedList<Missile> missiles = new LinkedList<Missile>();

		//asteroidBelt.add(new Asteroid(0, 0, 5, 70, 4));
		//asteroidBelt.add(new Asteroid(900, 900, 3, 45, 2));
		
		for(int i=0;i<10;i++) {
			asteroidBelt.add(new Asteroid());
		}
		

		AsteroidPanel APanel = new AsteroidPanel(p, asteroidBelt, missiles);
		UserInterface UI = new UserInterface(APanel);

		thread1 = new Thread(new Runnable() {

			@Override
			public void run() {
				long oldTime = System.nanoTime();
				long newTime;
				int m = 0;
				
				boolean running = true;
				while (running) {

					if (UI.w) {
						p.speed += 0.3;
					}
					if (UI.a) {
						p.direction -= 7;
					}
					if (UI.s) {
						p.speed -= 0.5;
					}
					if (UI.d) {
						p.direction += 7;
					}
					if (UI.space) {
						if (m <= 0) {
							m = fpsCap / missilesPerSecond;
							missiles.add(new Missile(p));
						}
					}
					m--;
					
					p.move();
					for (int i = 0; i < asteroidBelt.size(); i++) {
						asteroidBelt.get(i).move();
					}

					for (int i = 0; i < missiles.size(); i++) {
						if (!missiles.get(i).move()) {
							missiles.remove(i);
							i--;
						}
					}

					running = checkCollisions(asteroidBelt, p, missiles);

					APanel.repaint();

					newTime = System.nanoTime();
					long delta = newTime - oldTime;
					long fps = 1000000000 / delta;
					oldTime = newTime;
					UI.frame.setTitle(fps + "");

					try {
						Thread.sleep(Math.max(0, (1000 / fpsCap) - ((System.nanoTime() - oldTime) / 1000000)));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					
					if(asteroidBelt.size() == 0 && !APanel.winScreen) {
						APanel.winScreen = true;
					}

				}
			}
		});
		thread1.setPriority(1);

		thread1.start();
	}

	boolean checkCollisions(LinkedList<Asteroid> asteroidBelt, Player p, LinkedList<Missile> missiles) {
		for (int i = 0; i < asteroidBelt.size(); i++) {
			Asteroid roid = asteroidBelt.get(i);
			double distance = Math.sqrt(Math.pow(roid.xPos - p.xPos, 2) + Math.pow(roid.yPos - p.yPos, 2));
			if (distance < (p.length + roid.size * 20)) {
				if (playerPointsCollision(p, roid)) {
					return false;
				}
			}
			for (int j = 0; j < missiles.size(); j++) {
				Missile boomie = missiles.get(j);
				double distance2 = Math
						.sqrt(Math.pow(roid.xPos - boomie.xPos, 2) + Math.pow(roid.yPos - boomie.yPos, 2));
				if (distance2 < (6 + roid.size * 20)) {
					if (roid.size > 1) {
						asteroidBelt.add(
								new Asteroid(roid.xPos, roid.yPos, roid.speed, roid.direction + 45, roid.size - 1));
						asteroidBelt.add(
								new Asteroid(roid.xPos, roid.yPos, roid.speed, roid.direction - 45, roid.size - 1));
					}
					asteroidBelt.remove(i);
					missiles.remove(j);
				}
			}

		}
		return true;
	}

	boolean playerPointsCollision(Player p, Asteroid roid) {

		if (Math.sqrt(Math.pow(p.x1 - roid.xPos, 2) + Math.pow(p.y1 - roid.yPos, 2)) < roid.size * 10) {
			return true;
		}
		if (Math.sqrt(Math.pow(p.x2 - roid.xPos, 2) + Math.pow(p.y2 - roid.yPos, 2)) < roid.size * 10) {
			return true;
		}
		if (Math.sqrt(Math.pow(p.x3 - roid.xPos, 2) + Math.pow(p.y3 - roid.yPos, 2)) < roid.size * 10) {
			return true;
		}
		if (Math.sqrt(Math.pow(p.x4 - roid.xPos, 2) + Math.pow(p.y4 - roid.yPos, 2)) < roid.size * 10) {
			return true;
		}
		return false;
	}
}