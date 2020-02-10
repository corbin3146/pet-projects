package asteroids;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class UserInterface {
	public UserInterface() {
		JFrame Frame = new JFrame();
		JLabel label = new JLabel();
		Graphics G = new Graphics();
		label.setIcon(new ImageIcon(G.drawBoard()));
		Frame.add(label);	
		Frame.setVisible(true);
	}
}
