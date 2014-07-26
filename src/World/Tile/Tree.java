package World.Tile;

import java.awt.Graphics2D;

import Graphics.RL;
import World.World;

public class Tree extends Tile {
	
	private static final long serialVersionUID = 1L;
	private World world;
	private boolean first = true, right = false, left = false, up = false, down = false;
	
	public Tree(int x, int y, World w) {
		super(x, y);
		world = w;
		solid = true;
	}
	
	public void tick() {
		first();
	}
	
	public void first() {
		if (first) {
			if (y / 64 + 1 <= 127 && world.tiles[x / 64][y / 64 + 1] instanceof Tree)
				down = true;
			if (y / 64 - 1 >= 1 && world.tiles[x / 64][y / 64 - 1] instanceof Tree)
				up = true;
			if (x / 64 + 1 <= 127 && world.tiles[x / 64 + 1][y / 64] instanceof Tree)
				right = true;
			if (x / 64 - 1 >= 1 && world.tiles[x / 64 - 1][y / 64] instanceof Tree)
				left = true;
			first = false;
		}
		
	}
	
	public void preRender(Graphics2D g) {
		g.drawImage(RL.tree2, x, y - 64, 64, 64, null);
	}
	
	public void render(Graphics2D g) {
		
		g.drawImage(RL.tree, x, y, width, height, null);
		
	}
	
}
