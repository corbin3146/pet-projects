package Game;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class CharGrid {
	char[][] globalGrid;
	Color cellColor = new Color(0, 0, 0);
	Color border1 = new Color(0, 0, 0);
	Color border2 = new Color(0, 0, 175);
	Color border3 = new Color(175, 0, 0);
	Color backGround = new Color(125, 125, 125);
	Color outOfBounds = new Color(150, 125, 125);

	// 00 L NNNN
	public CharGrid(int width, int height) {
		globalGrid = new char[width][height];
	}

	boolean getLife(int x, int y) {
		return (globalGrid[x][y] & 16) == 16;
	}

	int getNeighbors(int x, int y) {
		return globalGrid[x][y] & ~(0 << 3);
	}

	char[][] toggle(char[][] grid, int x, int y) {
		grid[x][y] ^= 16;// toggle this cell

		int neighbor = -1;// assume made death
		if ((grid[x][y] & 16) == 16) {// if made alive
			neighbor = 1;
			// System.out.println("made me alive");
		}

		grid = adjustNeighbors(grid, x, y, neighbor);
		return grid;
	}

	char[][] adjustNeighbors(char[][] grid, int x, int y, int neighbor) {
		int left = x - 1;
		int right = x + 1;
		int up = y + 1;
		int down = y - 1;

		// safety first
		right %= (grid.length);
		up %= (grid[0].length);
		if (left < 0) {
			left = grid.length - 1;
		}
		if (down < 0) {
			down = grid[0].length - 1;
		}

		grid[x][up] += neighbor;// up
		grid[right][up] += neighbor;// up right
		grid[right][y] += neighbor;// right
		grid[right][down] += neighbor;// down right
		grid[x][down] += neighbor;// down
		grid[left][down] += neighbor;// left down
		grid[left][y] += neighbor;// left
		grid[left][up] += neighbor;// left up

		// System.out.println("for" + x + "," + y + " alter:");
		// System.out.println(x + "," + up + "to:" + (int) grid[x][up]);
		// System.out.println(right + "," + up + "to:" + (int) grid[right][up]);
		// System.out.println(left + "," + down + "to:" + (int) grid[left][down]);
		return grid;
	}

	BufferedImage toImage(int scale) {
		BufferedImage image = new BufferedImage(globalGrid.length * scale, globalGrid[0].length * scale,
				BufferedImage.TYPE_3BYTE_BGR);
		for (int i = 0; i < globalGrid.length * scale; i += scale) {
			for (int c = 0; c < globalGrid[0].length * scale; c += scale) {
				Color color = new Color(125, 125, 125);// Background
				if ((globalGrid[i / scale][c / scale] & 16) == 16) {
					color = new Color(0, 0, 0);
				}
				for (int q = 0; q < scale; q++) {
					for (int w = 0; w < scale; w++) {
						// System.out.println((i+q)+","+(c+w));
						if ((q) % scale <= 1 || (w) % scale <= 1) {// making grid
							image.setRGB(i + q, c + w, new Color(0, 0, 0).getRGB());
						} else {
							image.setRGB(i + q, c + w, color.getRGB());
						}
					}
				}
			}
		}
		return image;
	}

	BufferedImage toImage(int scale, int xOfset, int yOfset, int width, int height, boolean running) {
		BufferedImage image = new BufferedImage(width * scale, height * scale, BufferedImage.TYPE_3BYTE_BGR);
		for (int i = 0; i < width * scale; i += scale) {
			for (int c = 0; c < height * scale; c += scale) {
				Color color = new Color(125, 125, 125);// Background

				try {
					if ((globalGrid[(i / scale) + xOfset][(c / scale) + yOfset] & 16) == 16) {
						color = cellColor;
					}
				} catch (Exception e) {
					color = outOfBounds;
				}

				for (int q = 0; q < scale; q++) {
					for (int w = 0; w < scale; w++) {
						if (((q+1) % scale <= 0 || (w+1) % scale <= 0) && color != outOfBounds && !running) {// making grid
							if (((i/scale+q)+xOfset + 0) % (100) <= 0 || ((c/scale+w)+yOfset + 0) % (100) <= 0) {
								image.setRGB(i + q, c + w, border3.getRGB());
							} else if(((i/scale+q)+xOfset + 0) % (10) <= 0 || ((c/scale+w)+yOfset + 0) % (10) <= 0) {
								image.setRGB(i + q, c + w, border2.getRGB());
							}else {
								image.setRGB(i + q, c + w, border1.getRGB());
							}
						} else {
							image.setRGB(i + q, c + w, color.getRGB());
						}
					}
				}
			}
		}
		return image;
	}

	void itterate() {
		char[][] newGrid = new char[globalGrid.length][globalGrid[0].length];
		// System.out.println("length:"+globalGrid.length);

		for (int i = 0; i < globalGrid.length; i++) {
			for (int c = 0; c < globalGrid[0].length; c++) {
				int cell = globalGrid[i][c];

				if (cell == 3 || cell == 18 || cell == 19) {
					// System.out.println("found a live one for next round");
					toggle(newGrid, i, c);
				}
			}
		}
		globalGrid = newGrid;
	}

	BufferedImage nextImage(BufferedImage image, int scale, int xOfset, int yOfset, int width, int height) {
		char[][] newGrid = new char[globalGrid.length][globalGrid[0].length];

		for (int i = 0; i < globalGrid.length; i++) {
			for (int c = 0; c < globalGrid[0].length; c++) {
				int cell = globalGrid[i][c];

				if (cell == 3 || cell == 18 || cell == 19) {// lives to next round, no image change
					toggle(newGrid, i, c);
				}

			}
		}

		for (int i = 0; i < globalGrid.length; i++) {
			for (int c = 0; c < globalGrid[0].length; c++) {
				int oldG = globalGrid[i][c] & 16;
				int newG = newGrid[i][c] & 16;
				if (oldG != newG) {// if livingness changed
					if (newG == 16) {// born
						for (int k = 0; k < scale; k++) {
							for (int j = 0; j < scale; j++) {
								if ((xOfset < i && i < (xOfset + width)) && (yOfset < c && c < (yOfset + height))) {
									image.setRGB(((i - xOfset) * scale) + k, ((c - yOfset) * scale) + j,
											cellColor.getRGB());
								}
							}
						}
					} else if (oldG == 16) {// died
						for (int k = 0; k < scale; k++) {
							for (int j = 0; j < scale; j++) {
								if ((xOfset < i && i < (xOfset + width)) && (yOfset < c && c < (yOfset + height))) {
									image.setRGB(((i - xOfset) * scale) + k, ((c - yOfset) * scale) + j,
											backGround.getRGB());
								}
							}
						}
					}
				}
			}
		}

		globalGrid = newGrid;
		return image;
	}

	void grow(double ratio) {
		char[][] newGrid = new char[(int) (globalGrid.length * ratio)][(int) (globalGrid[0].length * ratio)];

		for (int i = 0; i < globalGrid.length; i++) {
			for (int c = 0; c < globalGrid[0].length; c++) {
				newGrid[i][c] = globalGrid[i][c];
			}
		}

		globalGrid = newGrid;
	}

	void shrink(double ratio) {
		char[][] newGrid = new char[(int) (globalGrid.length * ratio)][(int) (globalGrid[0].length * ratio)];

		if (newGrid.length == 0 || newGrid[0].length == 0) {

		}

		for (int i = 0; i < newGrid.length; i++) {
			for (int c = 0; c < newGrid[0].length; c++) {
				newGrid[i][c] = globalGrid[i][c];
			}
		}

		globalGrid = newGrid;
	}
}
