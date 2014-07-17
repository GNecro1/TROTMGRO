package World.Tile;

import java.awt.Graphics2D;

import Graphics.RL;

public class Grass extends Tile{
	
	private static final long serialVersionUID = 1L;

	public Grass(int x, int y) {
		super(x, y);
	}

	public void render(Graphics2D g) {
		g.drawImage(RL.grass, x, y, width, height, null);
	}

}
