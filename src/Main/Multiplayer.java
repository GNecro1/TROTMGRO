package Main;

import java.awt.Graphics2D;
import java.util.ArrayList;

import Control.Camera;
import Control.CameraOnline;
import Control.Controler;
import Entity.Player;
import Entity.PlayerOnline;
import Graphics.RL;
import Multiplayer.ClientHolder;

public class Multiplayer extends Menu {

	private ClientHolder client;
	private CameraOnline c;
	public Player p;
	public ArrayList<PlayerOnline> po = new ArrayList<>();

	public Multiplayer(Main main, Controler control) {
		super(main, control);
	}

	public void init() {
		client = new ClientHolder(this);
		c = new CameraOnline(this);
		p = new Player(Main.width / 2 - 32, Main.height / 2 - 32,null);
		Thread c = new Thread(client);
		c.start();
	}

	public void tick() {
		p.tick();
		c.tick();
	}

	public void render(Graphics2D g) {
		g.translate(c.getXOffset(), c.getYOffset());
		for (int i = 0; i < 64; i++) {
			for (int j = 0; j < 64; j++) {
				g.drawImage(RL.grass, i * 64, j * 64, 64, 64, null);
			}
		}
		for (PlayerOnline pon : po) {
			pon.render(g);
		}
		p.render(g);
	}

	public Player getPlayer() {
		return p;
	}
}
