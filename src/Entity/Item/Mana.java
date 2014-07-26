package Entity.Item;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Control.Timer;
import Graphics.RL;
import Main.Game;
import Res.BufImage;

public class Mana extends Item {
	double degree = 0;
	int manaReg;
	private Timer t;
	public Mana(int x, int y, int mana) {
		super(x, y, 24, 24);
		manaReg = mana;
		t = new Timer(6);
		t.start();
	}
	
	public void tick() {
		if (this.intersects(Game.p)) {
			Game.p.addMana(manaReg);
			Game.ent.remove(this);
		}
		if(t.Ring()){
			Game.ent.remove(this);
		}
		degree += 0.8;
	}
	
	public void render(Graphics2D g) {
		BufferedImage img = BufImage.rotateMyImage((BufferedImage) RL.xp, degree);
		g.drawImage(img, (int) x, (int) y, 24, 24, null);
	}
}
