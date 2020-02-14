package asteroids;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

import javax.swing.JFrame;

public class UserInterface {
	boolean w;
	boolean a;
	boolean s;
	boolean d;
	AsteroidGraphics g;
	JFrame frame;

	public UserInterface(Player p, AsteroidGraphics AG, LinkedList<Missile> missiles) {

		frame = new JFrame();
		// this is a JPanel
		g = AG;

		KeyListener kl = new KeyListener() {
			
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyChar() == ' ') {
					System.out.println("space");
					missiles.add(new Missile(p));
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyChar() == 'w') {
					w = false;
				}
				if(e.getKeyChar() == 'a') {
					a = false;
				}
				if(e.getKeyChar() == 's') {
					s = false;
				}
				if(e.getKeyChar() == 'd') {
					d = false;
				}
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyChar() == 'w') {
					w = true;
				}
				if(e.getKeyChar() == 'a') {
					a = true;
				}
				if(e.getKeyChar() == 's') {
					s = true;
				}
				if(e.getKeyChar() == 'd') {
					d = true;
				}
			}
		};
		

		frame.setSize(1000, 1000);
		frame.add(g);
		frame.addKeyListener(kl);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
