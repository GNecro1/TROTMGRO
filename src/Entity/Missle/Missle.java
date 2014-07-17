package Entity.Missle;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import Entity.Particle.Particle;
import Main.Game;

public class Missle extends Rectangle {
	private static final long serialVersionUID = 1L;

	protected double xVel, yVel;
	protected int xTarget, yTarget;
	public ArrayList<Particle> particles = new ArrayList<>();

	public Missle(int x, int y, int width, int height, int targetX, int targetY) {
		super(x, y, width, height);
		setBounds(x, y, width, height);
		Game.missles.add(this);
	}

	public Missle(int x, int y, int width, int height, double velX, double velY) {
		super(x, y, width, height);
		setBounds(x, y, width, height);
		xVel = velX;
		yVel = velY;
		Game.missles.add(this);
	}

	public void tick() {
		ticking();
		for (Particle p : particles) {
			p.tick();
		}
	}

	public void render(Graphics2D g) {
		for (Particle p : particles) {
			p.render(g);
		}
		rendering(g);
		
	}
	
	public void ticking() {
		
	}

	public void rendering(Graphics2D g) {
		
	}

}
