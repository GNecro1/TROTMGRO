package Entity.Missle;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import Control.Vec2;
import Entity.Particle.Particle;
import Main.Game;

public class Missile extends Rectangle {
	private static final long serialVersionUID = 1L;
	
	protected float xVel, yVel;
	protected int xTarget, yTarget;
	public ArrayList<Particle> particles = new ArrayList<>();
	Game Game;
	
	public Missile(int x, int y, int width, int height, int targetX, int targetY, Game g) {
		super(x, y, width, height);
		setBounds(x, y, width, height);
		setGame(g);
		Game.missiles.add(this);
	}
	
	public Missile(int x, int y, int width, int height, float velX, float velY, Game g) {
		super(x, y, width, height);
		setBounds(x, y, width, height);
		xVel = velX;
		yVel = velY;
		setGame(g);
		Game.missiles.add(this);
	}
	
	public static Vec2 point(Vec2 pivot, Vec2 point, float rotation) {
		
		float rot = (float) (1f / 180 * rotation * Math.PI);
		
		float x = point.x - pivot.x;
		float y = point.y - pivot.y;
		
		float newx = (float) (x * Math.cos(rot) - y * Math.sin(rot));
		float newy = (float) (x * Math.sin(rot) + y * Math.cos(rot));
		
		newx += pivot.x;
		newy += pivot.y;
		
		return new Vec2(newx, newy);
	}
	
	public static int angle(Vec2 pivot, Vec2 point) {
		
		float xdiff = pivot.x - point.x;
		float ydiff = pivot.y - point.y;
		
		float angle = (float) ((Math.atan2(xdiff, ydiff)) * 180 / Math.PI);
		
		return -(int) angle;
	}
	
	public void setGame(Game g) {
		Game = g;
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
