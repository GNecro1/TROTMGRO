package World;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import Control.Camera;
import Control.Controler;
import World.Tile.Grass;
import World.Tile.Stone;
import World.Tile.Tile;
import Main.Main;

public class World {
	
	int worldSize = 128;
	public Tile[][] tiles = new Tile[worldSize][worldSize];
	
	private Random r = new Random();
	private Camera c;
	
	public World(Camera camera) {
		c = camera;
		gen();
	}
	
	public void gen() {
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[0].length; j++) {
				if (r.nextInt(10) > 1) {
					tiles[i][j] = new Grass(i * Tile.TILE_SIZE, j * Tile.TILE_SIZE);
				} else {
					tiles[i][j] = new Stone(i * Tile.TILE_SIZE, j * Tile.TILE_SIZE);
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
		// Lower bound
		int startX = (int) (-c.getXOffset() / Tile.TILE_SIZE);
		int startY = (int) (-c.getYOffset() / Tile.TILE_SIZE);
		
		// Higher bound
		int endX = (int) Math.min((-c.getXOffset() + Main.width) / Tile.TILE_SIZE, this.worldSize);
		int endY = (int) Math.min((-c.getYOffset() + Main.height) / Tile.TILE_SIZE, this.worldSize);
		
		for (int i = startX; i <= endX; i++) {
			for (int j = startY; j <= endY; j++) {
				if (i < 0 || j < 0 || i >= this.worldSize || j >= this.worldSize) {
					continue;
				}
				
				tiles[i][j].render(g);
			}
		}
	}
	
}
