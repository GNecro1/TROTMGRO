package World.Tile;

import java.awt.Graphics2D;

import Graphics.RL;

public class Stone extends Tile {
	
	private static final long serialVersionUID = 1L;

	public Stone(int x, int y) {
		super(x, y);
		solid = true;
	}

	public void render(Graphics2D g) {
		g.drawImage(RL.stone, x, y, width, height, null);
	}

}
