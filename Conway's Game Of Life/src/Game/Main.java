package Game;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.Raster;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		new BuffGUI(100, 100, 20);

		// Box box = new Box(10);
//		Grid g = new Grid();
//		g.setAlive(3, 3);
//		g.setAlive(4, 3);
//		g.setAlive(3, 4);
//		g.setAlive(3, 5);
//		
//		
//		while(true) {
//		g.itterate(); 
//		g.print(40, 40);
//		try {
//			Thread.sleep(100);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}}
		// int i = 'w';
		// System.out.println(i);

		/*
		 * 
		 * // char c = 0; // System.out.println((int)c); // c ^= 16; //
		 * System.out.println((int)c); // c+=1; // System.out.println((int)c);
		 * 
		 * // System.out.println(-10%3);
		 * 
		 * // CharGrid grid = new CharGrid(100,100); // // grid.toggle(99, 99);
		 * 
		 * // Grid grid = new Grid(); // grid.setAlive(5, 4); // grid.setAlive(5, 5); //
		 * grid.setAlive(6, 4); // grid.setAlive(6, 5); // grid.setAlive(7, 5); //
		 * UserInterface UI = new UserInterface(100,100);
		 * 
		 * // JFrame frame = new JFrame(); // frame.getContentPane().setLayout(new
		 * FlowLayout()); // frame.getContentPane().add(new JLabel(new
		 * ImageIcon(grid.toImage(9)))); // frame.pack(); // System.out.println("yp");
		 * // frame.setVisible(true); // frame.addMouseListener(new MouseListener() { //
		 * // @Override // public void mouseReleased(MouseEvent e) { // // TODO
		 * Auto-generated method stub // // } // // @Override // public void
		 * mousePressed(MouseEvent e) { // // TODO Auto-generated method stub // // } //
		 * // @Override // public void mouseExited(MouseEvent e) { // // TODO
		 * Auto-generated method stub // // } // // @Override // public void
		 * mouseEntered(MouseEvent e) { // // TODO Auto-generated method stub // // } //
		 * // @Override // public void mouseClicked(MouseEvent e) { // // TODO
		 * Auto-generated method stub //
		 * System.out.println((e.getX()/9-2)+","+(e.getY()/9-5)); // } // });
		 * //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); /* editMode(grid);
		 * 
		 * grid.print(0,0);
		 * 
		 * for (int i = 0; i < 10; i--) {
		 * System.out.println("#######################################");
		 * grid.Itterate(); grid.print(-1, -1); try { Thread.sleep(333); } catch
		 * (InterruptedException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } }
		 * 
		 * }
		 */
	}

	public static void editMode(Grid grid) {
		String input = "";
		int ptrX = 0;
		int ptrY = 0;
		grid.print(ptrX, ptrY);
		while (!input.equals("p")) {

			Scanner in = new Scanner(System.in);
			input = in.next();
			if (input.contains("w")) {
				ptrY--;
			}
			if (input.contains("a")) {
				ptrX--;
			}
			if (input.contains("s")) {
				ptrY++;
			}
			if (input.contains("d")) {
				ptrX++;
			}
			if (input.contains("t")) {
				grid.setValue(ptrX, ptrY, !grid.getValue(ptrX, ptrY));
			}
			grid.print(ptrX, ptrY);
		}
	}
}
/*
 * void keyTyped(KeyEvent e) { if (e.getKeyChar() == 'a') {
 * 
 * } }
 */
