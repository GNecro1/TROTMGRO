package Main;

import java.awt.Graphics2D;
import java.util.ArrayList;

import Control.Camera;
import Control.Controler;
import Entity.*;
import Entity.Missle.Missile;
import Entity.Particle.Particle;
import World.World;

public class Game extends Menu {

	public static int pSpeed = 3;
	public static int animSpeed = 250;
	public Camera c;

	public static ArrayList<Missile> missiles = new ArrayList<>();
	public static ArrayList<Particle> particles = new ArrayList<>();

	private World w;

	public Player p;

	public Game(Main main, Controler con) {
		super(main, con);
		c = new Camera();
		w = new World(c);
		p = new Player(2000, 2200, w);
		p.setMouse(con.mouse);
	}

	public void tick() {
		w.tick();
		c.tick(p);
		p.tick();
		for(int i = 0; i< missiles.size();i++){
			missiles.get(i).tick();
		}
		for(int i = 0; i< particles.size();i++){
			particles.get(i).tick();
		}
	}

	public void render(Graphics2D g) {
		g.translate(c.getXOffset(), c.getYOffset());
		w.render(g);
		p.render(g);
		for(int i = 0; i< missiles.size();i++){
			missiles.get(i).render(g);
		}
		for(int i = 0; i< particles.size();i++){
			particles.get(i).render(g);
		}
		g.translate(-c.getXOffset(), -c.getYOffset());
		p.renderAfter(g);
	}
}
