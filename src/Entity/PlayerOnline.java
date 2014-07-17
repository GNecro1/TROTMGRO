package Entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class PlayerOnline extends Entity {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private int ID;
	
	Color c;

	public PlayerOnline(int x, int y) {
		super(x, y,64,64);
		c = new Color(new Random().nextInt());
	}
	
	public void tick(){
		
	}

	public void setID(int iD) {
		ID = iD;
	}
	
	public int getID() {
		return ID;
	}
	
	public void render(Graphics2D g){
		g.setColor(c);
		g.fillRect((int)x, (int)y, width, height);
	}

}
