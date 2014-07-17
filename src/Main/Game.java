package Main;

import java.awt.Graphics2D;
import java.util.ArrayList;

import Control.Camera;
import Control.Controler;
import Entity.*;
import Entity.Missle.Missle;
import Entity.Particle.Particle;
import World.World;

public class Game extends Menu {

	public static int pSpeed = 3;
	public static int animSpeed = 250;
	public static Camera c;

	public static ArrayList<Missle> missles = new ArrayList<>();
	public static ArrayList<Particle> particles = new ArrayList<>();

	private World w;

	private Player p;

	public Game(Main main, Controler con) {
		super(main, con);
		c = new Camera();
		w = new World(c);
		p = new Player(Main.width / 2 - 24, Main.height / 2 - 24, w);
		p.setMouse(con.mouse);
	}

	public void tick() {
		w.tick();
		c.tick();
		p.tick();
		for(int i = 0; i< missles.size();i++){
			missles.get(i).tick();
		}
		for(int i = 0; i< particles.size();i++){
			particles.get(i).tick();
		}
	}

	public void render(Graphics2D g) {
		g.translate(c.getXOffset(), c.getYOffset());
		w.render(g);
		p.render(g);
		for(int i = 0; i< missles.size();i++){
			missles.get(i).render(g);
		}
		for(int i = 0; i< particles.size();i++){
			particles.get(i).render(g);
		}
		g.translate(-c.getXOffset(), -c.getYOffset());
		p.renderAfter(g);
	}
}
