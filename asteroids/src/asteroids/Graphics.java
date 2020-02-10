package asteroids;

import java.awt.image.BufferedImage;

public class Graphics {
	BufferedImage drawBoard(){
		BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_3BYTE_BGR);
		return image;
	}
}
