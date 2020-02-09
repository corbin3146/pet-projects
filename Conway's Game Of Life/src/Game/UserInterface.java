/**/
package Game;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class UserInterface {
	Grid grid;
	JFrame frame;
	JPanel panel;
	JPanel controls;
	int horizontalSize;
	int verticalSize;
	private volatile boolean running = false;

	

	public UserInterface(int horizontalSizeIn, int verticalSizeIn) {
		grid = new Grid();
		frame = new JFrame();
		frame.setExtendedState(frame.MAXIMIZED_BOTH);


		
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {

			@Override
			public boolean dispatchKeyEvent(KeyEvent e) {
				if (e.getID() == KeyEvent.KEY_TYPED) {
					System.out.println("keytyped");

					if (e.getKeyChar() == ' ') {
						if (running) {
							running = false;
							e.consume();
							System.out.println("not running");
							try {
								Thread.sleep(200);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							update(grid);
							return false;
						}
						System.out.println("running");
						running = true;
						Thread thread1 = new Thread(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								running = true;
								while (running) {
									// for(int i=0;i<10;i++) {
									itterate(grid);
									try {
										Thread.sleep(50);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							}
						});
						thread1.setPriority(4);
						
						thread1.start();

					}

				}
				e.consume();
				return false;
			}
		});

		
		panel = new JPanel();
		verticalSize = verticalSizeIn;
		horizontalSize = horizontalSizeIn;
		panel.setLayout(new GridLayout(verticalSize, horizontalSize));
		panel.setVisible(true);
		frame.add(panel);
		frame.setSize(8 * horizontalSize, 8 * verticalSize);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		update(grid);
	}

	void itterate(Grid grid) {
		grid.itterate();
		update(grid);
		// System.out.println("here");
	}

	void update(Grid grid) {
		panel.removeAll();
		int minSize = Math.min(frame.getWidth() - 100, frame.getHeight() - 100);
		panel.setSize(minSize,minSize);
		for (int i = 0; i < verticalSize; i++) {
			for (int c = 0; c < horizontalSize; c++) {
				JButton button = new JButton();
				if (grid.getValue(i, c)) {
					button.setBackground(new Color(0, 0, 0));
					button.setForeground(new Color(0, 0, 0));
				} else {
					button.setBackground(new Color(150, 150, 150));
					button.setForeground(new Color(150, 150, 150));
				}
				button.setText(i + "X" + c);
				if (running) {
					button.setBorderPainted(false);
				}
				button.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {

						String[] coord = button.getText().split("X");
						int i = Integer.parseInt(coord[0]);
						int c = Integer.parseInt(coord[1]);
						System.out.println(i + "x" + c);
						grid.setValue(i, c, !grid.getValue(i, c));

						
						
						update(grid);
					}
				});

				panel.add(button);
			}
		}
		// JPanel panel2 = new JPanel();
		// panel2.add(new JButton("play"));
		// frame.add(panel2);
		// frame.setLayout(new FlowLayout());

		frame.invalidate();
		frame.validate();
		// frame.add(new JButton("play"));//windows xp effect, dont use
		// System.out.println("1");
		frame.repaint();
		// System.out.println("2");
		// System.out.println("here2");
	}
}