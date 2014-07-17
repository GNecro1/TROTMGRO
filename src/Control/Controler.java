package Control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import Main.Game;
import Main.Main;

public class Controler implements KeyListener, MouseListener, MouseMotionListener {

	public Main main;
	public static boolean up = false, down = false, left = false, right = false, space = false;
	public Mouse mouse;

	public Controler(Main main) {
		this.main = main;
		mouse = new Mouse(0, 0,main.getGame());
	}
	
	public Mouse getMouse(){
		return mouse;
	}

	public void mouseDragged(MouseEvent e) {
		mouse.x = e.getX();
		mouse.y = e.getY();
		mouse.setLocation(e.getX(), e.getY());
	}

	public void mouseMoved(MouseEvent e) {
		mouse.x = e.getX();
		mouse.y = e.getY();
		mouse.setLocation(e.getX(), e.getY());
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		if (e.getButton() == 1) {
			mouse.clicked = true;
		}
		if (e.getButton() == 2) {
			mouse.midleClicked = true;
		}
		if (e.getButton() == 3) {
			mouse.rightClicked = true;
		}
	}

	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == 1) {
			mouse.clicked = false;
		}
		if (e.getButton() == 2) {
			mouse.midleClicked = false;
		}
		if (e.getButton() == 3) {
			mouse.rightClicked = false;
		}
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_F1) {
			if (Main.debug == false) {
				Main.debug = true;
			} else {
				Main.debug = false;
			}
		}
		if (key == KeyEvent.VK_W) {
			up = true;
		}
		if (key == KeyEvent.VK_A) {
			left = true;
		}
		if (key == KeyEvent.VK_S) {
			down = true;
		}
		if (key == KeyEvent.VK_D) {
			right = true;
		}
		if (key == KeyEvent.VK_SPACE) {
			space = true;
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_W) {
			up = false;
		}
		if (key == KeyEvent.VK_A) {
			left = false;
		}
		if (key == KeyEvent.VK_S) {
			down = false;
		}
		if (key == KeyEvent.VK_D) {
			right = false;
		}
		if (key == KeyEvent.VK_SPACE) {
			space = false;
		}
	}

	public void keyTyped(KeyEvent e) {
	}

}
