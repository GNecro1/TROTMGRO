package Control;

import Main.Multiplayer;

public class CameraOnline {

	private int xOff, yOff;
	
	private Multiplayer mp;
	
	public CameraOnline(Multiplayer multiplayer) {
		mp = multiplayer;
	}

	public void tick() {
		xOff = mp.p.getOffset()[0];
		yOff = mp.p.getOffset()[1];
	}

	public double getXOffset() {
		return -xOff;
	}

	public double getYOffset() {
		return -yOff;
	}
}
