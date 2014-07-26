package World;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import Control.Camera;
import Control.Timer;
import Entity.Cuty;
import Res.WorldLoader;
import World.Tile.Grass;
import World.Tile.Stone;
import World.Tile.Tile;

public class World {
	
	int worldSize = 128;
	public Tile[][] tiles = new Tile[worldSize][worldSize];
	
	private Random r = new Random(10);
	private Camera c;
	private Timer t = new Timer(10);
	
	public World(Camera camera) {
		c = camera;
		t.start();
		tiles = WorldLoader.getWorld(this, worldSize);
	}
	
	public void gen() {
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[0].length; j++) {
				if (r.nextInt(10) > 1) {
					tiles[i][j] = new Grass(i * 64, j * 64);
				}
				else {
					tiles[i][j] = new Stone(i * 64, j * 64, this);
				}
			}
		}
		
	}
	
	public void tick() {
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[0].length; j++) {
				if (tiles[i][j].intersects(new Rectangle((int) (-c.getXOffset() - 64), (int) (-c.getYOffset() - 64), 448 * 2 + 128, 360 * 2 + 64))) {
					
					tiles[i][j].tick();
				}
			}
		}
		if (t.Ring()) {
			spawnCuty();
			t.reset();
		}
	}
	private void spawnCuty() {
		int spawnRate = 20;
		int chance = r.nextInt(100);
		if (chance > spawnRate) {
			for (int i = 0; i < chance / spawnRate; i++) {
				spawnCutyRe();
			}
		}
	}
	
	private void spawnCutyRe(){
		int x = r.nextInt(worldSize);
		int y = r.nextInt(worldSize);
		if(!tiles[x][y].solid){
			new Cuty(x * 64, y * 64, this);
			System.out.println("Spawned!");
		}else{
			spawnCutyRe();
		}
	}
	
	public void preRender(Graphics2D g) {
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[0].length; j++) {
				if (tiles[i][j].intersects(new Rectangle((int) (-c.getXOffset() - 64), (int) (-c.getYOffset() - 64), 448 * 2 + 128, 360 * 2 + 64))) {
					tiles[i][j].preRender(g);
				}
			}
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
