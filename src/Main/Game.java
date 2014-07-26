package Main;

import java.awt.Graphics2D;
import java.util.ArrayList;

import Control.Camera;
import Control.Controler;
import Entity.Cuty;
import Entity.Entity;
import Entity.Player;
import Entity.Missle.Missile;
import Entity.Particle.Particle;
import World.World;

public class Game extends Menu {
	
	public static int pSpeed = 3;
	public static int animSpeed = 250;
	public Camera c;
	
	public static ArrayList<Missile> missiles = new ArrayList<>();
	public static ArrayList<Particle> particles = new ArrayList<>();
	public static ArrayList<Entity> ent = new ArrayList<>();
	
	private World w;
	
	public static Player p;
	
	public Game(Main main, Controler con) {
		super(main, con);
		c = new Camera();
		w = new World(c);
		p = new Player(640, 1280, w, this);
		p.setMouse(con.mouse);
		new Cuty(640, 1280, 48, 48, w);
		new Cuty(640, 1280, 48, 48, w);
	}
	
	public void tick() {
		w.tick();
		c.tick(p);
		p.tick();
		for (int i = 0; i < missiles.size(); i++) {
			missiles.get(i).tick();
		}
		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).tick();
		}
		for (int i = 0; i < ent.size(); i++) {
			ent.get(i).tick();
		}
	}
	
	public void render(Graphics2D g) {
		g.translate(c.getXOffset(), c.getYOffset());
		w.render(g);
		p.render(g);
		for (int i = 0; i < missiles.size(); i++) {
			missiles.get(i).render(g);
		}
		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).render(g);
		}
		for (int i = 0; i < ent.size(); i++) {
			ent.get(i).render(g);
		}
		w.preRender(g);
		g.translate(-c.getXOffset(), -c.getYOffset());
		p.renderAfter(g);
	}
}
