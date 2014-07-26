package Entity.Particle;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import Control.Timer;
import Main.Game;

public class BloodParticle extends Particle {
	
	private float x, y;
	private float targetX, targetY;
	private float xVel, yVel;
	private Timer t;
	
	public BloodParticle(float x, float y) {
		super((int) x, (int) y);
		Game.particles.add(this);
		t = new Timer(10 + (new Random().nextInt(3)));
		t.start();
		this.x = x;
		this.y = y;
		targetX = x + (new Random().nextInt(60) - 30);
		targetY = y + (new Random().nextInt(60) - 30);
	}
	
	public void tick() {
		if (x != targetX || y != targetY) {
			double pathX = (targetX - x);
			double pathY = (targetY - y);
			
			double distance = Math.sqrt(pathX * pathX + pathY * pathY);
			xVel = (float) (pathX / distance) * 2;
			yVel = (float) (pathY / distance) * 2;
			x += xVel;
			y += yVel;
		}
		if(t.Ring()){
			Game.particles.remove(this);
		}
	}
	
	public void render(Graphics2D g) {
		g.setColor(Color.red);
		g.fillRect((int) x, (int) y, 6, 6);
	}
}
