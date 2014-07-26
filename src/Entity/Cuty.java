package Entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import Control.Timer;
import Control.Vec2;
import Entity.Item.Mana;
import Graphics.Animation;
import Graphics.RL;
import Main.Game;
import World.World;

public class Cuty extends Entity {
	
	private static final long serialVersionUID = 1L;
	private Animation down;
	private BufferedImage[] downImg = { RL.CutyDown1, RL.CutyDown2, RL.CutyDown3 };
	private Timer dirChose;
	private Random r = new Random();
	private Vec2 vec = new Vec2(0, 0);
	private World world;
	private float x, y;
	
	public Cuty(int x, int y, int width, int height, World w) {
		super(x, y, width, height);
		this.x = x;
		this.y = y;
		Game.ent.add(this);
		health = 10;
		world = w;
		dirChose = new Timer(4);
		dirChose.start();
		dirChose.done = true;
		down = new Animation(Game.animSpeed, downImg);
		down.startAnimation();
		hitBox = new Rectangle(0, 0, width, height);
		colBox = new Rectangle(0, 0, width, height);
	}
	
	public void tick() {
		if (getColison()) {
			
		}
		else {
			x += vec.x;
			y += vec.y;
		}
		if (dirChose.Ring()) {
			vec.x = r.nextFloat() - r.nextFloat();
			vec.y = r.nextFloat() - r.nextFloat();
			dirChose.reset();
		}
		hit();
		setBounds((int) x, (int) y, width, height);
		hitBox.setBounds((int) x, (int) y, width, height);
		colBox.setBounds((int) x, (int) y, colBox.width, colBox.height);
		
	}
	
	public void dropMana(){
		new Mana((int)x+12,(int)y+12);
	}
	
	public void death(){
		Game.ent.remove(this);
	}
	public void hit(){
		for(int i = 0;i<Game.missiles.size(); i++){
			if(hitBox.intersects(Game.missiles.get(i))){
				dropMana();
				death();
				Game.missiles.remove(Game.missiles.get(i));
			}
		}
	}
	public boolean getColison() {
		colBox.x += vec.x;
		colBox.y += vec.y;
		colBox.setBounds((int) (colBox.x), (int) (colBox.y), colBox.width, colBox.height);
		for (int i = 0; i < world.tiles.length; i++) {
			for (int j = 0; j < world.tiles[0].length; j++) {
				if (colBox.intersects(world.tiles[i][j]) && world.tiles[i][j].solid) {
					vec.x = r.nextFloat() - r.nextFloat();
					vec.y = r.nextFloat() - r.nextFloat();
					dirChose.reset();
					return true;
				}
			}
		}
		return false;
	}
	
	public void render(Graphics2D g) {
		g.drawImage(down.getCurrentImage(), (int) x, (int) y, width, height, null);
	}
	
}
