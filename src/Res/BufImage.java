package Res;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BufImage {

	
	public static BufferedImage changeOpacitu(BufferedImage modMe, double modAmount) {
		for (int x = 0; x < modMe.getWidth(); x++) {
			for (int y = 0; y < modMe.getHeight(); y++) {
				//
				int argb = modMe.getRGB(x, y); // always returns TYPE_INT_ARGB
				int alpha = (argb >> 24) & 0xff; // isolate alpha
				
				alpha *= modAmount; // similar distortion to tape saturation
									// (has scrunching effect, eliminates
									// clipping)
				alpha &= 0xff; // keeps alpha in 0-255 range
				
				argb &= 0x00ffffff; // remove old alpha info
				argb |= (alpha << 24); // add new alpha info
				modMe.setRGB(x, y, argb);
			}
		}
		return modMe;
	}
	
	
	public static BufferedImage grabImage(String path, int x, int y, int width, int height) {
		BufferedImage tile = null;
		BufferedImage bildTileset = null;
		try {
			bildTileset = ImageIO.read(BufImage.class.getClassLoader().getResource(path));
		} catch (IOException error) {
			System.err.println("Tileset not found");
			error.printStackTrace();
		}
		tile = bildTileset.getSubimage(x * width, y * height, width, height);
		return tile;
	}

	public static BufferedImage rotateMyImage(BufferedImage img, double angle) {
		int w = img.getWidth();
		int h = img.getHeight();
		BufferedImage dimg = new BufferedImage(w, h, img.getType());
		Graphics2D g = dimg.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g.rotate(Math.toRadians(angle), w / 2, h / 2);

		g.drawImage(img, null, 0, 0);
		return dimg;
	}
}
