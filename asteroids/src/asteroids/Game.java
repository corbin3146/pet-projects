package asteroids;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Game {
	Game() {
		int fpsCap = 60;
		Player p = new Player(0, 0, 100, 100);
		AsteroidGraphics AG = new AsteroidGraphics(p);
		UserInterface UI = new UserInterface(p, AG);
		

		Thread thread1 = new Thread(new Runnable() {

			@Override
			public void run() {
				long oldTime = System.nanoTime();
				long newTime;
				while (true) {
					AG.repaint();
					p.move();
					checkCollisions();
					newTime = System.nanoTime();
					long delta = newTime - oldTime;
					long fps = delta / 1000000;
					UI.frame.setTitle(fps+"");
					try {
						Thread.sleep(Math.max(0, (1000 / fpsCap) - ((System.nanoTime() - oldTime) / 1000000)));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//System.out.println("FPS: " + fps);
					oldTime = newTime;
				}
			}
		});
		thread1.setPriority(1);

		thread1.start();

//		while (true) {
//			AG.repaint();
//			p.move();
//			checkCollisions();
//			newTime = System.nanoTime();
//			long delta = newTime - oldTime;
//			long fps = delta / 1000000;
//			try {
//				Thread.sleep(Math.max(0, (1000 / fpsCap) - ((System.nanoTime() - oldTime) / 1000000)));
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			//System.out.println("FPS: " + fps);
//			oldTime = newTime;
//		}
	}

	void checkCollisions() {

	}
}