package Game;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Grid {
	protected boolean grid[][];
	Color cell = new Color(0,0,0);
	Color border = new Color(255,255,255);
	Color backGround = new Color(125,125,125);
	Color outOfBounds = new Color(255,255,255);
	
	
	public Grid() {
		grid = new boolean[100][100];
	}
	public Grid(int x,int y) {
		grid = new boolean[x][y];
	}
	void clear() {
		grid = new boolean[grid.length][grid[0].length];
	}
	void clear100() {
		grid = new boolean[100][100];
	}

	void grow(int scale) {

	}

	void setAlive(int x, int y) {
		grid[x][y] = true;
	}

	void setDead(int x, int y) {
		grid[x][y] = false;
	}

	void setValue(int x, int y, boolean life) {
		grid[x][y] = life;
	}

	boolean getValue(int x, int y) {// primitive booleans default to false
		return grid[x][y];
	}

	void itterate() {
		boolean[][] newGrid = new boolean[grid.length][grid[0].length];
		for (int x = 1; x < 99; x++) {
			for (int y = 1; y < 99; y++) {
				int neighbors = 0;

				if (grid[x - 1][y - 1]) {// up left
					neighbors++;
				}
				if (grid[x][y - 1]) {// up
					neighbors++;
				}
				if (grid[x + 1][y - 1]) {// up right
					neighbors++;
				}
				if (grid[x + 1][y]) {// right
					neighbors++;
				}
				if (grid[x + 1][y + 1]) {// down right
					neighbors++;
				}
				if (grid[x][y + 1]) {// down
					neighbors++;
				}
				if (grid[x - 1][y + 1]) {// down left
					neighbors++;
				}
				if (grid[x - 1][y]) {// left
					neighbors++;
				}

				// do or die time
				if (neighbors < 2) {
					newGrid[x][y] = false;
				}
				if (neighbors == 2 && grid[x][y]) {
					newGrid[x][y] = true;
				}
				if (neighbors == 3) {
					newGrid[x][y] = true;
				}
				if (neighbors > 3) {
					newGrid[x][y] = false;
				}
			}
		}
		grid = newGrid;
	}

	BufferedImage toImage(int scale) {
		BufferedImage image = new BufferedImage(grid.length*scale, grid[0].length*scale, BufferedImage.TYPE_3BYTE_BGR);
		for (int i = 0; i < grid.length*scale; i+=scale) {
			for (int c = 0; c < grid[0].length*scale; c+=scale) {
				Color color = new Color(125,125,125);
				if (grid[i/scale][c/scale]) {
					color = new Color(0,0,0);
				}
				for(int q = 0;q<scale;q++) {
					for(int w=0;w<scale;w++) {
						//System.out.println((i+q)+","+(c+w));
						if((q)%scale<=1 || (w)%scale<=1  ) {
							image.setRGB(i+q, c+w, new Color(0,0,0).getRGB());
						}else {
							image.setRGB(i+q, c+w, color.getRGB());
						}
					}
				}
			}
		}
		return image;
	}

	void print(int ptrX, int ptrY) {
		for (int i = 0; i < 40; i++) {
			for (int c = 0; c < 100; c++) {
				if (grid[c][i]) {
					if (ptrX == c && ptrY == i) {
						System.out.print("O");
					} else {
						System.out.print("@");
					}
				} else {
					if (ptrX == c && ptrY == i) {
						System.out.print("X");
					} else {
						System.out.print("-");
					}
				}
			}
			System.out.println();
		}
	}
}
