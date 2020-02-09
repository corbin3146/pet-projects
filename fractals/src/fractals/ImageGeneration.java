package fractals;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;

public class ImageGeneration {

	public BufferedImage generateImage(ComplexNumber c, int size, double scope, int shift) {

		BufferedImage pic = new BufferedImage(size, size, 1);
		for (int x = 0; x < size; x++) {
			// if(x%(size/100) == 0) {
			// System.out.println(x/(size/100) + "%");
			// }
			for (int y = 0; y < size; y++) {

				ComplexNumber comp = new ComplexNumber(((x / (double) size) * (2.0 * scope) - scope),
						((y / (double) size) * (2.0 * scope) - scope));
				int k = 0;
				boolean escape = false;
				while (comp.getX() < scope && comp.getI() < scope && k < 20) {
					comp.square();
					comp.add(c);
					k++;
				}

				Color color = new Color(0, 0, 0);

				if (comp.getX() < scope && comp.getI() < scope) {

				} else {
					int cshift = k * shift;
					color = new Color((Math.abs(255 - cshift)) % 255, 0, (0 + cshift) % 255);
				}

				pic.setRGB(x, y, color.getRGB());
			}
		}

		return pic;
	}

	public BufferedImage generateImageZoom(ComplexNumber c,int power,int maxK, int size, double scope, int shift, double xOffset,
			double iOffset) {

		BufferedImage pic = new BufferedImage(size, size, 1);
		for (int x = 0; x < size; x++) {
			// if(x%(size/100) == 0) {
			// System.out.println(x/(size/100) + "%");
			// }
			for (int y = 0; y < size; y++) {

				ComplexNumber comp = new ComplexNumber(((x / (double) size) * (2.0 * scope) - scope) + xOffset,
						((y / (double) size) * (2.0 * scope) - scope) + iOffset);
				int k = 0;
				while (comp.pithag()< 2 && k < maxK) {
					comp.pow(power);
					comp.add(c);
					k++;
				}

				Color color = new Color(0, 0, 0);

				if (comp.pithag() < 2) {

				} else {
//					int cshift = k * shift;
//					color = new Color((Math.abs(255 - cshift)) % 255, 0, (0 + cshift) % 255);
					int m = shift;

					int j = k % (6*m);
					
					if(0<=j && j<m) {
						color = new Color(255,0+(((k%m)*255)/m),0);
					}else if(m<=j && j<(2*m)) {
						color = new Color(255-(((k%m)*255)/m),255,0);
					}else if((2*m)<=j && j<(3*m)) {
						color = new Color(0,255,0+(((k%m)*255)/m));
					}else if((3*m)<=j && j<(4*m)) {
						color = new Color(0,255-(((k%m)*255)/m),255);
					}else if((4*m)<=j && j<(5*m)) {
						color = new Color(0+(((k%m)*255)/m),0,255);
					}else if((5*m)<=j && j<(6*m)) {
						color = new Color(255,0,255-(((k%m)*255)/m));
					}
				}

				pic.setRGB(x, y, color.getRGB());
			}
		}

		return pic;
	}

	public BufferedImage mendelbroth(int power,int maxK,int size, double scope, int shift, double xOffset, double iOffset) {
		int infinity = 2;
		BufferedImage pic = new BufferedImage(size, size, 1);
		for (int x = 0; x < size; x++) {
			// if(x%(size/100) == 0) {
			// System.out.println(x/(size/100) + "%");
			// }
			for (int y = 0; y < size; y++) {

				ComplexNumber comp = new ComplexNumber(((x / (double) size) * (2.0 * scope) - scope) + xOffset,
						((y / (double) size) * (2.0 * scope) - scope) + iOffset);
				ComplexNumber origin = new ComplexNumber(0, 0);
				int k = 0;
				while (origin.pithag() < infinity && k < maxK) {
					// origin.square();
//					origin.cube();
					origin.pow(power);
					origin.add(comp);
					k++;
				}

				Color color = new Color(0, 0, 0);

				if (origin.pithag() < infinity) {
				} else {
					
					int m = shift;
					
					int j = k % (6*m);
					
					if(0<=j && j<m) {
						color = new Color(255,0+(((k%m)*255)/m),0);
					}else if(m<=j && j<(2*m)) {
						color = new Color(255-(((k%m)*255)/m),255,0);
					}else if((2*m)<=j && j<(3*m)) {
						color = new Color(0,255,0+(((k%m)*255)/m));
					}else if((3*m)<=j && j<(4*m)) {
						color = new Color(0,255-(((k%m)*255)/m),255);
					}else if((4*m)<=j && j<(5*m)) {
						color = new Color(0+(((k%m)*255)/m),0,255);
					}else if((5*m)<=j && j<(6*m)) {
						color = new Color(255,0,255-(((k%m)*255)/m));
					}
				}

				pic.setRGB(x, y, color.getRGB());
			}
		}

		return pic;
	}
}