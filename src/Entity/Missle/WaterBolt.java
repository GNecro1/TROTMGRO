package Entity.Missle;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import Control.Timer;
import Entity.Particle.WaterBoltParticle;
import Main.Game;
import World.World;

public class WaterBolt extends Missile {
	private static final long serialVersionUID = 1L;
	
	public float x, y;
	private Timer t;
	private Random r;
	private Color[] colors = new Color[6];
	private int index;
	private World w;
	
	public WaterBolt(int x, int y, double targetX, double targetY, World world, Game g, int str) {
		super(x, y, 16, 16, (int) targetX, (int) targetY, g);
		w = world;
		dmg = 30;
		this.x = x;
		this.y = y;
		
		double pathX = (targetX - x);
		double pathY = (targetY - y);
		
		double distance = Math.sqrt(pathX * pathX + pathY * pathY);
		double directionX = pathX / distance;
		double directionY = pathY / distance;
		
		xVel = (float) (directionX * 7);
		yVel = (float) (directionY * 7);
		t = new Timer(2);
		t.start();
		r = new Random();
		index = r.nextInt(1);
		
		colors[0] = new Color(0x007BFF);
		colors[1] = new Color(0x3094FF);
		colors[2] = new Color(0x5CABFF);
		colors[3] = new Color(0x0068D6);
		colors[4] = new Color(0x0055B0);
		colors[5] = new Color(0x26CBE0);
		
		for (int i = 0; i < 10*str; i++) {
			new WaterBoltParticle(x, y, this, r.nextInt(360), r.nextInt(20), colors, r);
		}
	}
	
	public void ticking() {
		if (t.Ring()) {
			destroy();
		}
		else {
			x += xVel;
			y += yVel;
			
			setBounds((int) x, (int) y, width, height);
		}
		
		for (int i = 0; i < w.tiles.length; i++) {
			for (int j = 0; j < w.tiles[0].length; j++) {
				if (this.intersects(w.tiles[i][j]) && w.tiles[i][j].solid) {
					destroy();
				}
			}
		}
		// TODO : Collision with entityes
	}
	
	private void destroy() {
		for (int i = 0; i < 80; i++) {
			new WaterBoltParticle((int) x, (int) y, this, colors, r);
		}
		Game.missiles.remove(this);
	}
	
	public void rendering(Graphics2D g) {
		g.setColor(colors[1]);
		g.fill3DRect((int) x, (int) y, width, height, false);
	}
	
}
