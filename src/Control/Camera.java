package Control;

import Entity.Player;
import Main.Game;

public class Camera {

	private int xOff, yOff;

	public void tick(Player Player) {
		xOff = Player.getOffset()[0];
		yOff = Player.getOffset()[1];
	}

	public double getXOffset() {
		return -xOff;
	}

	public double getYOffset() {
		return -yOff;
	}
}
