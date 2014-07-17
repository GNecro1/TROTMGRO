package Entity.Particle;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import Control.Timer;
import Entity.Missle.WaterBolt;
import Main.Game;

public class WaterBoltParticle extends Particle {

	private double rotSpeed = 0.2, rot = 0, distance;

	private WaterBolt wb;

	private Color[] colors = new Color[6];

	private int index;
	private boolean orbit = false;

	public WaterBoltParticle(int x, int y, WaterBolt wabo, int degree, int dis, Color[] c, Random r) {
		super(x, y);
		distance = dis;
		rot = degree;
		wb = wabo;
		orbit = true;
		colors = c;

		index = r.nextInt(5);

		wb.particles.add(this);
	}

	private Timer t;

	private double xVel, yVel;

	private int targetX, targetY;

	public WaterBoltParticle(int x, int y, WaterBolt wabo, Color[] c, Random r) {
		super(x, y);
		wb = wabo;
		colors = c;

		t = new Timer(0.3);
		t.start();
		targetX = r.nextInt(1000000) - 500000;
		targetY = r.nextInt(1000000) - 500000;

		double pathX = (targetX - x);
		double pathY = (targetY - y);

		double distance = Math.sqrt(pathX * pathX + pathY * pathY);
		double directionX = pathX / distance;
		double directionY = pathY / distance;

		xVel = directionX * 7;
		yVel = directionY * 7;

		index = r.nextInt(5);

		Game.particles.add(this);
	}

	public void tick() {
		if (orbit) {
			rot = rot + rotSpeed;
			x = (int) (Math.cos(rot) * distance + wb.x + 8);
			y = (int) (Math.sin(rot) * distance + wb.y + 8);
		} else {
			if (!t.Ring()) {
				x += xVel;
				y += yVel;
			}else{
				destroy();
			}
		}
	}

	private void destroy() {
		Game.particles.remove(this);
	}

	public void render(Graphics2D g) {
		g.setColor(colors[index]);
		g.fill3DRect(x, y, 6, 6, true);
	}

}
