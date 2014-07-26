package Entity.Item;

import java.awt.Graphics2D;

import Graphics.RL;
import Main.Game;

public class Mana extends Item {
	
	public Mana(int x, int y) {
		super(x, y, 24, 24);
	}
	
	public void tick(){
		if(this.intersects(Game.p)){
			Game.p.addMana(3);
			Game.ent.remove(this);
		}
	}
	
	public void render(Graphics2D g) {
		g.drawImage(RL.xp, (int)x, (int)y, 24, 24, null);
	}
}
