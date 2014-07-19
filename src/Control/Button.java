package Control;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Main.Main;

public class Button extends Rectangle {
	private static final long serialVersionUID = 1L;

	private String title;
	private boolean on = false,img,clicked = false;
	private Controler c;
	private int index = 0, yOff, xOff;
	private Font f;
	private BufferedImage[] imgs = null;

	public Button(int x, int y, int width, int heingt, String Title) {
		setBounds(x, y, width, heingt);
		title = Title;
		img = false;
	}
	public Button(int x, int y, int width, int heingt,BufferedImage[] imga) {
		setBounds(x, y, width, heingt);
		imgs = imga;
		img = true;
	}

	public void setMouseListener(Controler control) {
		c = control;
	}
	
	public String getTitle(){
		return title;
	}
	
	public boolean getClicked(){
		return clicked;
	}

	public void tick() {
		if (this.contains(c.mouse)) {
			on = true;
			if (c.mouse.clicked) {
				 clicked = true;
				setState();
			}
		} else {
			on = false;
		}

	}

	public void setOffSets(int x, int y) {
		xOff = x;
		yOff = y;
	}

	public void setFont(Font font) {
		f = font;
	}

	private void setState() {
		Main.index = index;

	}

	public void setChangeState(int state) {
		index = state;
	}

	public void render(Graphics2D g) {
		if (!img) {
			if (on == false) {
				g.setColor(Color.GRAY);
			} else {
				g.setColor(Color.LIGHT_GRAY);
			}
			g.fill3DRect(x, y, width, height,true);
			g.setColor(Color.BLACK);
			g.setFont(f);
			g.drawString(title, x + xOff, y + yOff + g.getFont().getSize());

		}else{
			if (on == false) {
				g.drawImage(imgs[0], x, y, width, height, null);
			} else {
				g.drawImage(imgs[1], x, y, width, height, null);
			}
		}
	}
}
