package Main;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Control.Button;
import Control.Controler;
import Graphics.RL;

public class MainMenu extends Menu {

	public ArrayList<Button> buttons = new ArrayList<>();

	public MainMenu(Main main, Controler control) {
		super(main, control);
		
		/**
		 * Creating the Buttons for the game
		 */
		//BufferedImage[] img = {RL.Play1,RL.Play2};
		Button play = new Button(750, Main.height/2, 128, 48, "Play");
		play.setMouseListener(c);
		play.setChangeState(2);
		play.setOffSets(35, 5);
		play.setFont(new Font("Courier", 1, 24));
		buttons.add(play);
		Button Online = new Button(750, Main.height/2 + 50 + 10, 128, 48, "Online");
		Online.setMouseListener(c);
		Online.setChangeState(3);
		Online.setOffSets(25, 5);
		Online.setFont(new Font("Courier", 1, 24));
		buttons.add(Online);
		Button Options = new Button(750, Main.height/2 + 100 + 20, 128, 48, "Options");
		Options.setMouseListener(c);
		Options.setChangeState(3);
		Options.setOffSets(15, 5);
		Options.setFont(new Font("Courier", 1, 24));
		buttons.add(Options);
		//BufferedImage[] quit = {RL.Quit1,RL.Quit2};
		Button Quit = new Button(750, Main.height/2 + 150 + 30, 128, 48, "Quit");
		Quit.setMouseListener(c);
		Quit.setChangeState(4);
		Quit.setOffSets(35, 5);
		Quit.setFont(new Font("Courier", 1, 24));
		buttons.add(Quit);
	}

	public void tick() {
		/**
		 * Ticking the buttons
		 */
		
		for (int i = 0; i < buttons.size(); i++) {
			buttons.get(i).tick();
			if(buttons.get(i).getTitle().equalsIgnoreCase("online")&& buttons.get(i).getClicked()){
				main.menus.get(3).init();
			}
		}
	}

	public void render(Graphics2D g) {
		/**
		 * Rendering the buttons
		 */
		for (int i = 0; i < buttons.size(); i++) {
			buttons.get(i).render(g);
		}
	}
}
