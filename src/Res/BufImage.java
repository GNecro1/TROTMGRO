package Res;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BufImage {

	public static BufferedImage grabImage(String path, int x, int y, int width, int height) {
		BufferedImage tile = null;
		BufferedImage bildTileset = null;
		try {
			bildTileset = ImageIO.read(new File(path));
		} catch (IOException error) {
			System.err.println("Tileset not found");
			error.printStackTrace();
		}
		tile = bildTileset.getSubimage(x * width, y * height, width, height);
		return tile;
	}
	
	/*public static BufferedImage grabImage(String path, int x, int y, int width, int height) {
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
	}*/

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
