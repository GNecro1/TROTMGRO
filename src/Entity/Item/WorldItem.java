package Entity.Item;

import java.awt.Graphics2D;

import Entity.Entity;
import Main.Game;

public class WorldItem extends Entity {
	
	public WorldItem(int x, int y, int width, int height) {
		super(x, y, width, height);
		Game.ent.add(this);
		setBounds(x, y, width, height);
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics2D g) {
		
	}
	
}
