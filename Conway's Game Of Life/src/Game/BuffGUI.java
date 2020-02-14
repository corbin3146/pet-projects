package Game;

import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class BuffGUI {
	private volatile boolean running = false;
	CharGrid grid;
	int scale;
	int xOfset = 0;
	int yOfset = 0;
	int widthDisplayed = 0;
	int heightDisplayed = 0;
	int xMovement;
	int yMovement;
	int movementRatio = 20;
	double growthRatio = 1.1;
	boolean unlockKeyboard = false;
	long oldTime = System.nanoTime();

	JFrame frame;
	JLabel label;

	BufferedImage image;
	int fpsCap = 10;

	public BuffGUI(int width, int height, int scaleIn) {

		this.scale = scaleIn;

		grid = new CharGrid(width, height);
		frame = new JFrame();
		frame.setVisible(true);
		frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		// System.out.println(frame.getHeight());
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		widthDisplayed = frame.getWidth() / scale;
		heightDisplayed = frame.getHeight() / scale;
		xMovement = widthDisplayed / movementRatio;
		yMovement = heightDisplayed / movementRatio;

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new FlowLayout());
		label = new JLabel();
		image = grid.toImage(scale, xOfset, yOfset, widthDisplayed, heightDisplayed, running);
		label.setIcon(new ImageIcon(image));
		frame.getContentPane().add(label);
		// frame.getContentPane().add(new JLabel(
		// new ImageIcon(grid.toImage(scale, xOfset, yOfset, widthDisplayed,
		// heightDisplayed, running))));
		frame.pack();

		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {

			@Override
			public boolean dispatchKeyEvent(KeyEvent e) {
				if (unlockKeyboard) {
					return false;
				}
				if (e.getID() == KeyEvent.KEY_TYPED) {

					if (e.getKeyChar() == 'w') {
						yOfset += yMovement;
						update();
					}

					if (e.getKeyChar() == 'a') {
						xOfset -= xMovement;
						update();
					}

					if (e.getKeyChar() == 's') {
						yOfset -= yMovement;
						update();
					}

					if (e.getKeyChar() == 'd') {
						xOfset += xMovement;
						update();
					}

					if (e.getKeyChar() == 'i') {
						JOptionPane.showMessageDialog(null, "Welcome to Conway's game of life\r\n" + "Rules:\r\n"
								+ "Any live cell with fewer than two live neighbours dies, as if by underpopulation.\r\n"
								+ "Any live cell with two or three live neighbours lives on to the next generation.\r\n"
								+ "Any live cell with more than three live neighbours dies, as if by overpopulation.\r\n"
								+ "Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.\r\n"
								+ "W,A,S,D pans around\r\n" + "q,e grows and shrinks the game board\r\n"
								+ "+saves game     -loads scrool to zoom\r\n" + "space pauses/unpauses\r\n"
								+ "c claers the board f agjust frame rate");
					}
					if (e.getKeyChar() == 'c') {
						running = false;
						// clear the board
						grid = new CharGrid(width, height);
						update();

					}

					if (e.getKeyChar() == 'f') {
						unlockKeyboard = true;
						try {
						fpsCap = Integer.parseInt(JOptionPane.showInputDialog("Set a new framerate max\r\n"+
								"0 unlocks frame rate\r\n"+
								"negative numbers disable graphical updates for maximum speed"));}catch(NumberFormatException e1) {
						System.out.println("invalid frame rate");
					}
						unlockKeyboard = false;
						update();
					}
					if (e.getKeyChar() == '+') {
						// save

						System.out.println("saving");

						unlockKeyboard = true;
						String fileName = JOptionPane.showInputDialog("What would you like to save your game as?");
						if (fileName == null) {
							e.consume();
							return false;
						}
						unlockKeyboard = false;
						File file = new File(System.getProperty("user.dir") + "//saves//" + fileName + ".txt");
						System.out.println(file.getAbsolutePath());
						try {

							new File(System.getProperty("user.dir") + "//saves//").mkdirs();

							file.createNewFile();

							FileWriter out = new FileWriter(file);

							for (int i = 0; i < grid.globalGrid.length; i++) {
								for (int c = 0; c < grid.globalGrid[0].length; c++) {
									out.append((char) (grid.globalGrid[i][c] + 65));
								}
								out.append((char) 0);
								out.append('\n');
							}
							out.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}

					}

					if (e.getKeyChar() == '-') {
						// load
						System.out.println("loading");
						File dir = new File(System.getProperty("user.dir") + "//saves//");
						FileFilter fileFilter = new FileFilter() {

							@Override
							public boolean accept(File pathname) {

								return pathname.getName().endsWith(".txt");
							}
						};

						File[] fileList = dir.listFiles(fileFilter);
						String saveNames = "Saves:\n";
						for (int i = 0; i < fileList.length; i++) {
							saveNames += fileList[i].getName().replaceAll(".txt", "") + '\n';
						}
						unlockKeyboard = true;
						String fileName = JOptionPane.showInputDialog(saveNames + "What save would you like to load??");
						unlockKeyboard = false;
						if (fileName == null) {
							e.consume();
							return false;
						}
						File file = new File(System.getProperty("user.dir") + "//saves//" + fileName + ".txt");
						System.out.println(file.getAbsolutePath());
						try {
							// file.createNewFile();

							FileReader fileReader = new FileReader(file);
							BufferedReader in = new BufferedReader(fileReader);
							ArrayList<String> list = new ArrayList<String>();

							String line;
							while ((line = in.readLine()) != null) {
								list.add(line);

							}

							System.out.println(list.size() + "," + (list.get(0).length() - 1));
							CharGrid newGrid = new CharGrid(list.size(), list.get(0).length() - 1);
							for (int i = 0; i < list.size(); i++) {
								for (int c = 0; c < list.get(i).length() - 11; c++) {
									newGrid.globalGrid[i][c] = (char) (list.get(i).charAt(c) - 65);
								}
							}

							grid = newGrid;
							update();
							in.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}

					if (e.getKeyChar() == 'q') {

						grid.shrink(1 / growthRatio);
						update();
					}

					if (e.getKeyChar() == 'e') {
						grid.grow(growthRatio);
						update();
					}

					if (e.getKeyChar() == ' ') {
						if (running) {
							running = false;
							// e.consume();
							System.out.println("paused");

							update();

							return false;
						}

						System.out.println("started");
						start();

					}

				}
				e.consume();
				return false;
			}
		});

		frame.addMouseWheelListener(new MouseWheelListener() {

			public void mouseWheelMoved(MouseWheelEvent e) {
				adjustScale(-1 * e.getWheelRotation());
				update();
			}
		});

		frame.addMouseListener(new MouseListener() {
			int x1;
			int x2;
			int y1;
			int y2;

			public void mouseReleased(MouseEvent e) {
				x2 = ((e.getX() - 10) / scale + xOfset);
				y2 = ((e.getY() - 40) / scale + yOfset);
				// System.out.println("x2,y2 = " + x2 + "," + y2);

				int dx = x2 - x1;
				int dy = y2 - y1;

				System.out.println("dx=" + dx + " dy=" + dy);

				if (Math.abs(dx) > Math.abs(dy)) {// if line is more horizontal
					if (x1 > x2) {// swap points if drag left, yields equivalent right swipe
						int temp = x1;
						x1 = x2;
						x2 = temp;
						temp = y1;
						y1 = y2;
						y2 = temp;
					}

					dx = x2 - x1;
					dy = y2 - y1;

					System.out.println("horizontoal");
					for (int c = x1; c <= x2; c++) {

						int k = y1 + dy * (c - x1) / dx;
						grid.globalGrid = grid.toggle(grid.globalGrid, c, k);

					}
				} else {
					System.out.println("vertical");

					if (y1 > y2) {// swap points if drag left, yields equivalent right swipe
						int temp = x1;
						x1 = x2;
						x2 = temp;
						temp = y1;
						y1 = y2;
						y2 = temp;
					}

					dx = x2 - x1;
					dy = y2 - y1;

					for (int k = y1; k < y2; k++) {
						int c = x1 + dx * (k - y1) / dy;
						grid.globalGrid = grid.toggle(grid.globalGrid, c, k);

					}
				}
				update();
			}

			public void mousePressed(MouseEvent e) {
				x1 = (int) ((e.getX() - 10) / scale + xOfset);
				y1 = ((e.getY() - 40) / scale + yOfset);
				// System.out.println("x1,y1 = " + x1 + "," + y1);
			}

			public void mouseExited(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
				// e.getComponent().setCursor(Toolkit.getDefaultToolkit().createCustomCursor(cursor,
				// hotSpot, name));
				// Cursor cursor = new Cursor(Cursor.CROSSHAIR_CURSOR);
				// System.out.println("yo");
			}

			public void mouseClicked(MouseEvent e) {
				System.out.println(("(" + e.getX() + "," + e.getY() + ")"));

				int mouseX = (int) ((e.getX() - 10) / scale + xOfset);
				int mouseY = ((e.getY() - 40) / scale + yOfset);
				System.out.println(mouseX + "," + mouseY);

				grid.toggle(grid.globalGrid, mouseX, mouseY);
				update();

			}
		});
	}

	void itterate() {
		grid.itterate();
		update();
	}

	void update() {

		boolean wasRunning = running;
		running = false;

		image = grid.toImage(scale, xOfset, yOfset, widthDisplayed, heightDisplayed, wasRunning);
		label.setIcon(new ImageIcon(image));

		if (wasRunning) {
			start();
		}
	}

	void adjustScale(int i) {
		//System.out.println(i);
		if (scale + i < 1) {
			return;
		}
		scale += i;
		widthDisplayed = frame.getWidth() / scale;
		heightDisplayed = frame.getHeight() / scale;
		xMovement = widthDisplayed / movementRatio;
		yMovement = heightDisplayed / movementRatio;
	}

	double getFps() {
		long new_time = System.nanoTime();
		long delta = new_time - oldTime;
		double fps = 1000000000 / delta;
		oldTime = new_time;
		return fps;
	}

	void start() {
		
		running = true;
		
		//clear the grid
		image = grid.toImage(scale, xOfset, yOfset, widthDisplayed, heightDisplayed, running);
		label.setIcon(new ImageIcon(image));
		
		
		Thread thread1 = new Thread(new Runnable() {

			@Override
			public void run() {
				running = true;
				while (running) {
					if(fpsCap > 0)
					 try {
//						 System.out.println(()+"-"+(oldTime/1000000));
					// Thread.sleep(Math.max(0,(1000/fpsCap)-((System.nanoTime()-oldTime)/1000000)));
					 } catch (Exception e) {
					 // TODO Auto-generated catch block
					 e.printStackTrace();
					 }
					frame.setTitle("Fps:" + getFps());

					// grid.itterate();
					// update();
					if(fpsCap>=0) {
					image = grid.nextImage(image, scale, xOfset, yOfset, widthDisplayed, heightDisplayed);
					label.setIcon(new ImageIcon(image));
					}else {
						grid.itterate();
					}
					
				}
				// update on loop exit
				update();
			}
		});
		thread1.setPriority(1);

		thread1.start();
	}
}
