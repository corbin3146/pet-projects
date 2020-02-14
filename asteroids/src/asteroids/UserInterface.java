package asteroids;

import java.awt.Graphics;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class UserInterface {
	AsteroidGraphics g;
	JFrame frame;

	public UserInterface(Player p, AsteroidGraphics AG) {

		frame = new JFrame();
		// this is a JPanel
		g = AG;

		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {

			@Override
			public boolean dispatchKeyEvent(KeyEvent e) {
				// TODO Auto-generated method stub
				System.out.println(e.getKeyChar());
				if (e.getKeyChar() == 'w') {
					p.speed += 0.3;
				}
				if (e.getKeyChar() == 's') {
					p.speed -= 0.1;
				}
				if (e.getKeyChar() == 'a') {
					p.direction -=2;
				}
				if (e.getKeyChar() == 'd') {
					p.direction +=2;
				}
				return false;
			}

		});

		frame.setSize(1000, 1000);
		frame.add(g);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
