package World.Tile;

import java.awt.Graphics2D;

import Graphics.RL;
import World.World;

public class Stone extends Tile {
	
	private static final long serialVersionUID = 1L;
	private World world;
	private boolean first = true, right = false, left = false, up = false, down = false;
	
	public Stone(int x, int y, World w) {
		super(x, y);
		world = w;
		solid = true;
	}
	
	public void tick() {
		first();
	}
	
	public void first() {
		if (first) {
			if (y / 64 + 1 <= 127 && world.tiles[x / 64][y / 64 + 1] instanceof Stone)
				down = true;
			if (y / 64 - 1 >= 1 && world.tiles[x / 64][y / 64 - 1] instanceof Stone)
				up = true;
			if (x / 64 + 1 <= 127 && world.tiles[x / 64 + 1][y / 64] instanceof Stone)
				right = true;
			if (x / 64 - 1 >= 1 && world.tiles[x / 64 - 1][y / 64] instanceof Stone)
				left = true;
			first = false;
		}
		
	}
	
	public void render(Graphics2D g) {
		
		g.drawImage(RL.stone, x, y, width, height, null);
		if (up) {
			g.drawImage(RL.stoneVC, x, y - height / 2, 64, 64, null);
		}
		if (down) {
			g.drawImage(RL.stoneVC, x, y + height / 2, 64, 64, null);
		}
		if (left) {
			g.drawImage(RL.stoneHC, x - width / 2, y, 64, 64, null);
		}
		if (right) {
			g.drawImage(RL.stoneHC, x + width / 2, y, 64, 64, null);
		}
		
	}
	
}
