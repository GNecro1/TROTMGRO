package Entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import Control.Controler;
import Control.Mouse;
import Control.Timer;
import Entity.Missle.Energy;
import Entity.Missle.WaterBolt;
import Graphics.Animation;
import Graphics.RL;
import Main.Game;
import Main.Main;
import World.World;

@SuppressWarnings("serial")
public class Player extends Entity {

	private int ID = new Random().nextInt();

	private double xPrev, yPrev;
	public BufferedImage[] imagUp = { RL.player2Up1, RL.player2Up2, RL.player2Up3 }, imagLeft = { RL.player2Left1, RL.player2Left2, RL.player2Left3 }, imagDown = { RL.player2Down1, RL.player2Down2, RL.player2Down3 }, imagRight = { RL.player2Right1, RL.player2Right2, RL.player2Right3 };
	public Animation animUp, animLeft, animRight, animDown;
	private Rectangle colBox;

	private int dir = 0;
	private World world;
	private Mouse m;
	private int health = 100, mana = 100, deaths = 0;
	private int bX, bY;
	private Timer t = new Timer(3);
	private Timer MRT = new Timer(0.5);

	public Player(int x, int y, World w) {
		super(x, y, 48, 48);
		t.start();
		bX = x;
		bY = y;
		colBox = new Rectangle(x - 5, y - 5, 74, 74);
		world = w;
		MRT.start();
		animUp = new Animation(Game.animSpeed, imagUp);
		animDown = new Animation(Game.animSpeed, imagDown);
		animLeft = new Animation(Game.animSpeed, imagLeft);
		animRight = new Animation(Game.animSpeed, imagRight);
		animDown.startAnimation();
	}

	public void tick() {
		animUp.stopAnimation();
		animDown.stopAnimation();
		animRight.stopAnimation();
		animLeft.stopAnimation();
		shoot();
		move();
		if (MRT.Ring() && mana < 100) {
			mana++;
			MRT.reset();
		}
		colBox.setBounds((int) x, (int) y, width, height);
		setBounds((int) x, (int) y, width, height);
	}

	public void move() {
		xPrev = x;
		yPrev = y;
		if (colDectUP()) {
			if (Controler.up) {
				y -= Game.pSpeed;
				dir = 1;
				animUp.startAnimation();
			}
		}else{
			y+=1;
		}
		if (colDectDOWN()) {
			if (Controler.down) {
				y += Game.pSpeed;
				dir = 0;
				animDown.startAnimation();
			}
		}
		if (colDectLEFT()) {
			if (Controler.left) {
				x -= Game.pSpeed;
				dir = 3;
				animLeft.startAnimation();
			}
		}
		if (colDectRIGHT()) {
			if (Controler.right) {
				x += Game.pSpeed;
				dir = 2;
				animRight.startAnimation();
			}
		} else {
			x = xPrev;
			y = yPrev;
		}
		if (health < 1) {
			respawn();
		}
	}

	private WaterBolt wb;

	private void shoot() {
		if (m.clicked) {
			new WaterBolt((int) x + 24, (int) y + 24, m.getX() + getOffset()[0], m.getY() + getOffset()[1], world);

			t.reset();
		}

	}

	public void setMouse(Mouse mouse) {
		m = mouse;
	}

	private boolean colDectUP() {
		if (Controler.up) {
			colBox.y -= Game.pSpeed;
			yPrev += Game.pSpeed / 4;
		}
		colBox.setBounds(colBox.x, colBox.y, colBox.width, colBox.height);
		for (int i = 0; i < world.tiles.length; i++) {
			for (int j = 0; j < world.tiles[0].length; j++) {
				if (colBox.intersects(world.tiles[i][j]) && world.tiles[i][j].solid) {
					return false;
				}
			}
		}
		colBox.y += Game.pSpeed;
		colBox.setBounds(colBox.x,colBox.y,colBox.width,colBox.height);
		return true;
	}

	private boolean colDectDOWN() {
		if (Controler.down) {
			colBox.y += Game.pSpeed;
			yPrev -= Game.pSpeed / 4;
		}
		colBox.setBounds(colBox.x, colBox.y, colBox.width, colBox.height);

		for (int i = 0; i < world.tiles.length; i++) {
			for (int j = 0; j < world.tiles[0].length; j++) {
				if (colBox.intersects(world.tiles[i][j]) && world.tiles[i][j].solid) {
					return false;
				}
			}
		}
		colBox.y -= Game.pSpeed;
		return true;
	}

	private boolean colDectLEFT() {
		if (Controler.left) {
			colBox.x -= Game.pSpeed;
			xPrev += Game.pSpeed / 4;
		}
		colBox.setBounds(colBox.x, colBox.y, colBox.width, colBox.height);

		for (int i = 0; i < world.tiles.length; i++) {
			for (int j = 0; j < world.tiles[0].length; j++) {
				if (colBox.intersects(world.tiles[i][j]) && world.tiles[i][j].solid) {
					return false;
				}
			}
		}
		colBox.x += Game.pSpeed;
		return true;
	}

	private boolean colDectRIGHT() {
		if (Controler.right) {
			colBox.x += Game.pSpeed;
			xPrev -= Game.pSpeed / 4;
		}
		colBox.setBounds(colBox.x, colBox.y, colBox.width, colBox.height);

		for (int i = 0; i < world.tiles.length; i++) {
			for (int j = 0; j < world.tiles[0].length; j++) {
				if (colBox.intersects(world.tiles[i][j]) && world.tiles[i][j].solid) {
					return false;
				}
			}
		}
		colBox.x -= Game.pSpeed;
		return true;
	}

	public int[] getOffset() {
		int[] i = new int[2];

		i[0] = (int) (x - (448 - 32));
		i[1] = (int) (y - (360 - 32));

		return i;
	}

	public void respawn() {
		health = 100;
		mana = 100;
		deaths++;
		x = bX;
		y = bY;
	}

	public void render(Graphics2D g) {
		if (dir == 2) {
			g.drawImage(animRight.getCurrentImage(), (int) x, (int) y, width, height, null);
		} else if (dir == 1) {
			g.drawImage(animUp.getCurrentImage(), (int) x, (int) y, width, height, null);
		} else if (dir == 3) {
			g.drawImage(animLeft.getCurrentImage(), (int) x, (int) y, width, height, null);
		} else if (dir == 0) {
			g.drawImage(animDown.getCurrentImage(), (int) x, (int) y, width, height, null);
		}
	}

	public void renderAfter(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(7, 13, 156, 26);
		g.setColor(Color.DARK_GRAY);
		g.fill3DRect(10, 15, 150, 20, true);
		g.setColor(new Color(255, 59, 59));
		g.fill3DRect(10, 15, (health / 2) * 3, 20, true);
		g.setColor(Color.white);
		g.setFont(new Font("Courier", 1, 17));
		g.drawString("HEALTH : " + health, 25, 29);

		g.setColor(Color.BLACK);
		g.fillRect(167, 13, 156, 26);
		g.setColor(Color.DARK_GRAY);
		g.fill3DRect(170, 15, 150, 20, true);
		g.setColor(new Color(38, 157, 255));
		g.fill3DRect(170, 15, (mana / 2) * 3, 20, true);
		g.setColor(Color.white);
		g.drawString("MANA : " + mana, 185, 29);

	}

	public int getID() {
		return ID;
	}

}
