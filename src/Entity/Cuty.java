package Entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import Control.Timer;
import Control.Vec2;
import Entity.Item.Mana;
import Entity.Missle.Energy;
import Entity.Particle.BloodParticle;
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
	private Timer lifeTime;
	private boolean atPl = false;
	
	public Cuty(int x, int y, World w) {
		super(x, y, 48, 48);
		this.x = x;
		this.y = y;
		Game.ent.add(this);
		health = 5;
		world = w;
		dirChose = new Timer(4);
		dirChose.start();
		dirChose.done = true;
		down = new Animation(Game.animSpeed, downImg);
		down.startAnimation();
		hitBox = new Rectangle(0, 0, width, height);
		colBox = new Rectangle(0, 0, width, height);
		lifeTime = new Timer(90);
		lifeTime.start();
	}
	
	public void tick() {
		if (atPl && (x - Game.p.x > -256 && x - Game.p.x < 256) && (y - Game.p.y > -256 && y - Game.p.y < 256)) {
			
		}
		if (!getColison()) {
			y += vec.y;
			x += vec.x;
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
		if (lifeTime.Ring() || health < 1) {
			death();
		}
	}
	
	public void dropMana(double i) {
		new Mana((int) x + 12, (int) y + 12, (int) i);
	}
	
	public void death() {
		for (int i = 0; i < 12; i++) {
			new BloodParticle(x + 24, y + 24);
		}
		Game.ent.remove(this);
	}
	
	public void hit() {
		for (int i = 0; i < Game.missiles.size(); i++) {
			if (hitBox.intersects(Game.missiles.get(i)) && Game.missiles.get(i).hit) {
				health -= Game.missiles.get(i).getDamage();
				atPl = true;
				if (health < 1) {
					dropMana(Game.missiles.get(i).getDamage() * 5);
					Game.p.addXP(0.3);
				}
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
		g.setColor(new Color(0, 0, 0, 125));
		g.fillOval(colBox.x - 5, colBox.y + 25, colBox.width + 5, colBox.height - 30);
		g.drawImage(down.getCurrentImage(), (int) x, (int) y, width, height, null);
	}
	
}
