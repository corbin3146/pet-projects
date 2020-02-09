package fractals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// ComplexNumber comp = new ComplexNumber(0, 0);
		// for (int i = 0; i < 10; i++) {
		// comp.square();
		// comp.print();
		// }

		// test1();
		// acidTrip();
		//ItsAlive();
		//ZigZag();
//		test2();
		//test3();
		new UserInterface();
	}

	public static void test1() throws IOException {
//		 int size = 18827;
		//int size = 26756;
		int size = 1000;
		double scope = 1.5;

		int shift = 10;

		// ComplexNumber c = new ComplexNumber(xc, ic);
		// ComplexNumber c = new ComplexNumber(-0.1011, 0.9563);
		ComplexNumber c = new ComplexNumber(-1,0);

		ImageGeneration generator = new ImageGeneration();

		BufferedImage pic = generator.generateImage(c, size, scope, shift);
		// Picture pic = generator.generateImage(c, size, xScope, yScope, shift);

		File file = new File("C:\\Users\\corbi\\Desktop\\fractalOut\\scope" + scope + "size=" + size + "X" + size
				+ "shift=" + shift + ".png");
		file.createNewFile();
		ImageIO.write(pic, "png", file);

		System.out.println("done");
	}

	public static void test2() throws IOException {
		int size = 1000;
		double scope = 1.5;

		int shift = 10;

		ComplexNumber c = new ComplexNumber(-.9,0);

		ImageGeneration generator = new ImageGeneration();

		BufferedImage pic = generator.generateImageZoom(c,2,200, size, scope, shift,.5,.5);

		File file = new File("C:\\Users\\corbi\\Desktop\\fractalOut\\scope" + scope + "size=" + size + "X" + size
				+ "shift=" + shift + ".png");
		file.createNewFile();
		ImageIO.write(pic, "png", file);

		System.out.println("done");
	}
	public static void test3() throws IOException {
		int size = 10;
		double scope = 1.5;

		int shift = 10;

		ComplexNumber c = new ComplexNumber(-1,0);

		ImageGeneration generator = new ImageGeneration();

		BufferedImage pic = generator.mendelbroth(2,200,1000, 1.5, 10, 0, 0);

		File file = new File("C:\\Users\\corbi\\Desktop\\fractalOut\\scope" + scope + "size=" + size + "X" + size
				+ "shift=" + shift + "zzz.png");
		file.createNewFile();
		ImageIO.write(pic, "png", file);

		System.out.println("done");
	}
	
	public static void acidTrip() throws FileNotFoundException, IOException {
		// int size = 18826;
		int size = 1000;
		double xc = -1.2;
		double scope = 1.5;
		// double xScope = 2.5;
		// double yScope = 1.75;

		ComplexNumber c = new ComplexNumber(0, -0.6563);

		ImageGeneration generator = new ImageGeneration();
		File file = new File("C:\\Users\\corbi\\Desktop\\fractalOut\\GIF_Sscope" + scope + "size=" + size + "X" + size
				+ ",c=" + xc + "shift=1-255.gif");
		// file.mkdirs();
		file.createNewFile();
		File foo = new File("C:\\Users\\corbi\\Desktop\\fractalOut\\temp.jpg");
		// foo.mkdirs();
		foo.createNewFile();
		BufferedImage first = ImageIO.read(foo);
		ImageOutputStream output = new FileImageOutputStream(file);
		GifSequenceWriter writer = new GifSequenceWriter(output, first.getType(), 3, true);

		for (int i = 0; i <= 75; i++) {
			System.out.println("shift=" + i);
			BufferedImage pic = generator.generateImage(c, size, scope, i);

			writer.writeToSequence(pic);

		}
		writer.close();
		output.close();
		System.out.println("done");
	}

	public static void ItsAlive() throws IOException {
		// int size = 18826;
		int size = 1000;
		double xc = -1.2;
		double scope = 1.5;

		ImageGeneration generator = new ImageGeneration();
		File file = new File("C:\\Users\\corbi\\Desktop\\fractalOut\\GIF_Sscope" + scope + "size=" + size + "X" + size
				+ ",c=" + xc + "shift=1-255.gif");
		file.createNewFile();
		File foo = new File("C:\\Users\\corbi\\Desktop\\fractalOut\\temp.jpg");
		foo.createNewFile();
		BufferedImage first = ImageIO.read(foo);
		ImageOutputStream output = new FileImageOutputStream(file);
		GifSequenceWriter writer = new GifSequenceWriter(output, first.getType(), 3, true);

		ComplexNumber c = new ComplexNumber(0, 0);

		double frames = 10;
		double radius = .25;
		while (radius < 1) {
			for (int i = 0; i <= frames; i++) {

				System.out.println("frame:" + i);

				BufferedImage pic = generator.generateImage(c, size, scope, 10);
				c.add(0, (radius / frames));

				writer.writeToSequence(pic);

			}

			for (int i = 0; i <= frames; i++) {

				System.out.println("frame:" + i);

				BufferedImage pic = generator.generateImage(c, size, scope, 10);
				c.add((radius / frames), 0);

				writer.writeToSequence(pic);

			}

			for (int i = 0; i <= frames; i++) {

				System.out.println("frame:" + i);

				BufferedImage pic = generator.generateImage(c, size, scope, 10);
				c.add(0, -(radius / frames));

				writer.writeToSequence(pic);

			}

			for (int i = 0; i <= frames; i++) {

				System.out.println("frame:" + i);

				BufferedImage pic = generator.generateImage(c, size, scope, 10);
				c.add(0, -(radius / frames));

				writer.writeToSequence(pic);

			}

			for (int i = 0; i <= frames; i++) {

				System.out.println("frame:" + i);

				BufferedImage pic = generator.generateImage(c, size, scope, 10);
				c.add(-(radius / frames), 0);

				writer.writeToSequence(pic);

			}

			for (int i = 0; i <= frames; i++) {

				System.out.println("frame:" + i);

				BufferedImage pic = generator.generateImage(c, size, scope, 10);
				c.add(-(radius / frames), 0);

				writer.writeToSequence(pic);

			}

			for (int i = 0; i <= frames; i++) {

				System.out.println("frame:" + i);

				BufferedImage pic = generator.generateImage(c, size, scope, 10);
				c.add(0, (radius / frames));

				writer.writeToSequence(pic);

			}

			for (int i = 0; i <= frames; i++) {

				System.out.println("frame:" + i);

				BufferedImage pic = generator.generateImage(c, size, scope, 10);
				c.add(0, (radius / frames));

				writer.writeToSequence(pic);

			}

			for (int i = 0; i <= frames; i++) {

				System.out.println("frame:" + i);

				BufferedImage pic = generator.generateImage(c, size, scope, 10);
				c.add((radius / frames), 0);

				writer.writeToSequence(pic);

			}

			for (int i = 0; i <= frames; i++) {

				System.out.println("frame:" + i);

				BufferedImage pic = generator.generateImage(c, size, scope, 10);
				c.add(0, -(radius / frames));

				writer.writeToSequence(pic);

			}
			System.out.println(radius);
			radius += 0.25;
		}
		writer.close();
		output.close();
		System.out.println("done");
	}

	public static void ZigZag() throws IOException {
		// int size = 18826;
		int size = 1000;
		double xc = -1.2;
		double scope = 1.5;

		ImageGeneration generator = new ImageGeneration();
		File file = new File("C:\\Users\\corbi\\Desktop\\fractalOut\\GIF_Sscope" + scope + "size=" + size + "X" + size
				+ ",c=" + xc + "shift=1-255.gif");
		file.createNewFile();
		File foo = new File("C:\\Users\\corbi\\Desktop\\fractalOut\\temp.jpg");
		foo.createNewFile();
		BufferedImage first = ImageIO.read(foo);
		ImageOutputStream output = new FileImageOutputStream(file);
		GifSequenceWriter writer = new GifSequenceWriter(output, first.getType(), 3, true);

		ComplexNumber c = new ComplexNumber(0, 0);

		double frames = 10;
		//get to -1,1
		for(int i =0; i<frames; i++) {
			System.out.println("frame:" + i);

			BufferedImage pic = generator.generateImage(c, size, scope, 10);
			c.add(-(1 / frames), (1 / frames));

			writer.writeToSequence(pic);
			
		}
		
		int direction = 1;
		while (c.getI() >= -1) {
			System.out.println(c.getI());
			for (int i = 0; i <= frames; i++) {

				System.out.println("frame:" + i);

				BufferedImage pic = generator.generateImage(c, size, scope, 10);
				c.add(direction *(1 / frames),0);

				writer.writeToSequence(pic);

			}
			direction *= -1;
			c.add(0,-(1/frames));
		}
		
		writer.close();
		output.close();
		System.out.println("done");
	}
}