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
	boolean space;
	AsteroidPanel g;
	JFrame frame;

	public UserInterface(AsteroidPanel AG) {

		frame = new JFrame();
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		
		// this is a JPanel
		g = AG;

		KeyListener kl = new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyChar() == 'w') {
					w = false;
				}
				if (e.getKeyChar() == 'a') {
					a = false;
				}
				if (e.getKeyChar() == 's') {
					s = false;
				}
				if (e.getKeyChar() == 'd') {
					d = false;
				}
				if (e.getKeyChar() == ' ') {
					space = false;
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyChar() == 'w') {
					w = true;
				}
				if (e.getKeyChar() == 'a') {
					a = true;
				}
				if (e.getKeyChar() == 's') {
					s = true;
				}
				if (e.getKeyChar() == 'd') {
					d = true;
				}
				if (e.getKeyChar() == ' ') {
					space = true;
				}
			}
		};

		frame.add(g);
		frame.addKeyListener(kl);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
