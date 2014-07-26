package Entity.Missle;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import Control.Timer;
import Control.Vec2;
import Main.Game;
import World.World;

public class Energy extends Missile {
	
	public float x, y;
	private Timer t;
	private Random r;
	private World w;
	
	public Energy(int x, int y, double targetX, double targetY, World world, Game g) {
		super(x, y, 8, 8, (int) targetX, (int) targetY, g);
		w = world;
		dmg = 4;
		/** float pathX = (float) (targetX - x); float pathY = (float) (targetY -
		 * y); float distance = (float) Math.atan2(Math.sqrt(pathX * pathX +
		 * pathY * pathY),Math.sqrt(pathX * pathX + pathY * pathY)); float
		 * directionX = pathX / distance; float directionY = pathY / distance;
		 * xVel = directionX; yVel = directionY; */
		this.x = x;
		this.y = y;
		float rot = angle(new Vec2(x, y), new Vec2((float) targetX, (float) targetY));
		
		Vec2 velocity = point(new Vec2(0, 0), new Vec2(0, -4), rot);
		
		xVel = velocity.getX();
		yVel = velocity.getY();
		
		t = new Timer(3);
		t.start();
		r = new Random();
		
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
		Game.missiles.remove(this);
	}
	
	public void rendering(Graphics2D g) {
		g.setColor(new Color(105, 20, 195));
		g.fill3DRect((int) x, (int) y, width, height, true);
	}
	
}
