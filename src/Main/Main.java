package Main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.JFrame;

import Control.Controler;
import Control.Timer;
import Graphics.Animation;

public class Main extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	public static String title = "Framework";
	public static int width = 448 * 2;
	public static int height = 320 * 2;
	public static int index = 0;

	public boolean run = false;
	public Thread thread;
	public int finalFrames = 0, finalTicks = 0;
	public static boolean debug = true, done = false;
	public Controler control;

	public static ArrayList<Timer> timers = new ArrayList<>();
	public static ArrayList<Animation> anims = new ArrayList<>();
	public ArrayList<Menu> menus = new ArrayList<>();

	public Main() {
		setPreferredSize(new Dimension(width, height));
		requestFocus();

		control = new Controler(this);

		addKeyListener(control);
		addMouseListener(control);
		addMouseMotionListener(control);

		menus.add(new MainMenu(this, control));
		menus.add(new Options(this, control));
		menus.add(new Game(this, control));
		menus.add(new Multiplayer(this, control));
		done = true;
	}

	public Game getGame() {
		if (done)
			return (Game) menus.get(2);

		return null;
	}

	public synchronized void start() {
		run = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {
		run = false;
		try {
			thread.join();
			System.exit(0);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		long lastTime = System.nanoTime();
		double ns = 16666666.666666666D;
		double delta = 0.0D;
		double delta2 = 0.0D;
		int ticks = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		while (run) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			delta2 += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1.0D) {
				tick();
				if (debug) {
					ticks++;
				}
				delta -= 1.0D;
			}
			if (delta2 >= 0.1D) {
				delta2 -= 0.1D;
				draw();
				if (debug) {
					frames++;
				}
			}
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				if (debug) {
					this.finalFrames = frames;
					this.finalTicks = ticks;
				}
				frames = 0;
				ticks = 0;
			}
		}
	}

	private void draw() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g1 = bs.getDrawGraphics();
		Graphics2D g = (Graphics2D) g1;
		g.clearRect(0, 0, getWidth(), getHeight());
		render(g);
		debug(g);
		bs.show();
		g.dispose();
	}

	private void debug(Graphics2D g) {
		if (debug) {
			g.setFont(new Font("Courier", 1, 12));
			g.setColor(Color.BLACK);
			g.drawString("FPS : " + finalFrames + " Ticks : " + finalTicks + " Player X : " + ((Game) menus.get(2)).p.x + " Player Y : " + ((Game) menus.get(2)).p.y, 0, 10);
		}
	}

	private void render(Graphics2D g) {
		if (index == 4) {
			System.exit(ERROR);
		}
		menus.get(index).render(g);
	}

	private void tick() {
		if (index == 4) {
			System.exit(ERROR);
		}
		menus.get(index).tick();
		for (int i = 0; i < timers.size(); i++) {
			timers.get(i).tick();
		}
		for (Animation a : anims) {
			a.tick();
		}
	}

	public static void main(String[] args) {
		JFrame jf = new JFrame();
		Main m = new Main();
		jf.add(m);
		jf.pack();
		jf.requestFocus();
		jf.setTitle(title);
		jf.setVisible(true);
		jf.setResizable(false);
		jf.setDefaultCloseOperation(3);
		jf.setLocationRelativeTo(null);
		m.start();
	}

}
