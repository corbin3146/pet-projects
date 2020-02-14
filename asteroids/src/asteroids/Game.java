package asteroids;

import java.util.LinkedList;

public class Game {
	Game() {
		int fpsCap = 60;
		Player p = new Player(0, 0, 500, 500);
		LinkedList<Asteroid> asteroidBelt = new LinkedList<Asteroid>();
		LinkedList<Missile> missiles = new LinkedList<Missile>();
		
		AsteroidGraphics AG = new AsteroidGraphics(p,asteroidBelt, missiles);
		UserInterface UI = new UserInterface(p, AG,missiles);
		
		asteroidBelt.add(new Asteroid(0, 0, 5, 90, 4));
		
		

		Thread thread1 = new Thread(new Runnable() {

			@Override
			public void run() {
				long oldTime = System.nanoTime();
				long newTime;
				while (true) {
					
					if(UI.w) {
						p.speed += 0.3;	
					}
					if(UI.a) {
						p.direction -=3;
					}
					if(UI.s) {
						p.speed -= 0.5;
					}
					if(UI.d) {
						p.direction +=3;
					}
					
					
					p.move();
					for(int i=0;i<asteroidBelt.size();i++) {
						asteroidBelt.get(i).move();
					}
					
					for(int i=0;i<missiles.size();i++) {
						if(!missiles.get(i).move()) {
							missiles.remove(i);
							i--;
						}
					}
					
					checkCollisions(asteroidBelt, p, missiles);
					
					AG.repaint();
					
					newTime = System.nanoTime();
					long delta = newTime - oldTime;
					long fps = 1000000000/delta;
					oldTime = newTime;
					UI.frame.setTitle(fps+"");
					
					try {
						Thread.sleep(Math.max(0, (1000 / fpsCap) - ((System.nanoTime() - oldTime) / 1000000)));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
			}
		});
		thread1.setPriority(1);

		thread1.start();
	}

	void checkCollisions(LinkedList<Asteroid> asteroidBelt, Player p, LinkedList<Missile> missiles ) {
		for(int i=0; i<asteroidBelt.size();i++) {
			Asteroid roid = asteroidBelt.get(i);
			double distance = Math.sqrt(Math.pow(roid.xPos-p.xPos, 2)+Math.pow(roid.yPos-p.yPos, 2));
			if (distance<(p.length+roid.size*20)) {
				System.out.println("collision");
				System.exit(0);
			}
			for(int j = 0; j< missiles.size();j++) {
				Missile boomie = missiles.get(j);
				double distance2 =  Math.sqrt(Math.pow(roid.xPos-boomie.xPos, 2)+Math.pow(roid.yPos-boomie.yPos, 2));
				if(distance2<(20 + roid.size*20)) {
					if(roid.size>1) {
					asteroidBelt.add(new Asteroid(roid.xPos,roid.yPos,roid.speed,roid.direction+45,roid.size-1));
					asteroidBelt.add(new Asteroid(roid.xPos,roid.yPos,roid.speed,roid.direction-45,roid.size-1));
					}
					asteroidBelt.remove(i);
					missiles.remove(j);
				}
			}
			
		}
	}
}