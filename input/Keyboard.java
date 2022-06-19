package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener{

	public static boolean[] keys = new boolean[120];
	//Holds the state of 120 keys (true if they're down, false if they're not).

	public static boolean[] lastKeys = new boolean[120];
	
	public void update() { // To update the keys, last keys are one update after the keys
		for(int i = 0; i < keys.length; i++) {
			lastKeys[i] = keys[i];
		}
	}

	//Helper methods
	public static boolean keyDown(int key) {
		return keys[key] && lastKeys[key] == false;
	}
	public static boolean keyUp(int key) {
		return keys[key] == false && lastKeys[key];
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}
	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}	
}
