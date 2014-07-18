package Entity.Missle;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import Control.Timer;
import Entity.Particle.WaterBoltParticle;
import Main.Game;
import World.World;

public class Energy extends Missle{
	private static final long serialVersionUID = 1L;

	private Timer t;
	private Random r;
	private World w;

	public Energy(int x, int y, double targetX, double targetY, World world) {
		super(x, y, 8, 8, targetX, targetY);
		w = world;
		Game.missles.add(this);
		xVel = targetX;
		yVel = targetY;
		t = new Timer(0.5);
		t.start();
		r = new Random();

	}

	public void ticking() {
		if (t.Ring()) {
			destroy();
		} else {
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
		Game.missles.remove(this);
	}

	public void rendering(Graphics2D g) {
		g.setColor(new Color(105,20,195));
		g.fill3DRect((int) x, (int) y, width, height,true);
	}

}