package Main;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.TextField;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Control.Button;
import Control.Controler;

public class Multiplayer extends Game {
	
	public ArrayList<Button> buttons = new ArrayList<>();
	
	public Multiplayer(Main main, Controler control) {
		super(main, control);
		Button host = new Button(150, 150, 128, 48, "Host");
		host.setMouseListener(controler);
		host.setChangeState(2);
		host.setOffSets(35, 5);
		host.setFont(new Font("Courier", 1, 24));
		buttons.add(host);
	}
	
	public void tick() {
		for (int i = 0; i < buttons.size(); i++) {
			buttons.get(i).tick();
		}
	}
	
	public void render(Graphics2D g) {
		for (int i = 0; i < buttons.size(); i++) {
			buttons.get(i).render(g);
		}
	}
	
}
