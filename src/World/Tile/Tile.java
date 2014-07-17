package World.Tile;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Tile extends Rectangle {
	private static final long serialVersionUID = 1L;

	protected final int width = 64;
	
	public boolean solid = false;
	
	public Tile(int x, int y) {
		setBounds(x, y, width, width);
	}

	public void tick() {

	}

	public void render(Graphics2D g) {
		
	}

}
