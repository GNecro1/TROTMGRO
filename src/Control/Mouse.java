package Control;

import java.awt.Point;

import Main.Game;

public class Mouse extends Point {
	private static final long serialVersionUID = 1L;

	public boolean clicked = false, rightClicked = false, midleClicked = false;
	Game Game;
	
	public Mouse(int x, int y, Game g) {
		Game = g;
		setLocation(x, y);
	}

	public Mouse() {
	}

	public int getXPlusOff() {
		return (int) (x + Game.c.getXOffset());
	}

	public int getYPlusOff() {
		return (int) (y + Game.c.getYOffset());
	}
	
	public double getX() {
		return (int) (x);
	}

	public double getY() {
		return (int) (y);
	}

	public void setMouse(Mouse mouse) {
		clicked = mouse.clicked;
		rightClicked = mouse.rightClicked;
		midleClicked = mouse.midleClicked;
		x = mouse.x;
		y = mouse.y;
		setLocation(x, y);
	}
}
