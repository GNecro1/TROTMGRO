package World;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import Control.Camera;
import Control.Controler;
import World.Tile.Grass;
import World.Tile.Stone;
import World.Tile.Tile;

public class World {

	int worldSize = 128;
	public Tile[][] tiles = new Tile[worldSize][worldSize];

	private Random r = new Random(10);
	private Camera c;

	public World(Camera camera) {
		c = camera;
		gen();
	}

	public void gen() {
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[0].length; j++) {
				if (r.nextInt(10) > 1) {
					tiles[i][j] = new Grass(i * 64, j * 64);
				} else {
					tiles[i][j] = new Stone(i * 64, j * 64);
				}
			}
		}

	}

	public void tick() {
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[0].length; j++) {
				tiles[i][j].tick();
			}
		}
		if (Controler.space) {
			gen();
		}
	}

	public void render(Graphics2D g) {
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[0].length; j++) {
				if (tiles[i][j].intersects(new Rectangle((int) (-c.getXOffset() - 64), (int) (-c.getYOffset() - 64), 448 * 2 + 128, 360 * 2 + 64))) {
					tiles[i][j].render(g);
				}
			}
		}
	}

}
