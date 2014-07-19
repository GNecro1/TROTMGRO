package Entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Entity extends Rectangle {

	public double x, y;
	public int width, height;

	public Entity(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void tick() {

	}

	public void render(Graphics2D g) {

	}
}
