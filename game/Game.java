package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Main.Main;
import gameobject.GameObject;
import graphics.Renderer;
import input.Keyboard;

public class Game {

	public static List<GameObject> objects;
	public static boolean moving, Moved, somethingIsMoving;
	public static int direction;

	private Random rand = new Random();
	
	public Game() {
		init();
	}

	public void init() {
		objects = new ArrayList<GameObject>();
		moving = false;
		Moved = true;
		somethingIsMoving = false;
		spawn();
	}

	public void update() {
		if(Keyboard.keyUp(KeyEvent.VK_R)) {
			init();
		} //Restarts the game
		
		for(int i = 0; i < objects.size(); i++) {
			objects.get(i).move();
		} //Updates every object
		checkForValueIncrease();
		movingLogic();
	}

	private void checkForValueIncrease() {
		for(int i = 0; i < objects.size(); i++) {
			for(int j = 0; j < objects.size(); j++) {
				if(i == j) { //Checking if its stacked
					continue;
				}
				//If the objects are the same and stacked together, it adds them
				if(objects.get(i).x == objects.get(j).x && objects.get(i).y == objects.get(j).y && !objects.get(i).remove && !objects.get(j).remove) {
					objects.get(j).remove = true; // Deletes 1
					objects.get(i).value *= 2; // Doubles 1
					objects.get(i).createSprite(); // Creates the new sprite created
				}
			}
		}
		for(int i = 0; i < objects.size(); i++) {
			if(objects.get(i).remove == true) {
				objects.remove(i);
			}
		}
		//Game over :(
		if(Game.objects.size() == ((Main.WIDTH/100)*(Main.WIDTH/100))) {
			System.out.println("You lost :(");
			System.exit(0);
		}
		//System.out.println(objects.size());
	}

	private void spawn() {
		//If there are already 16 items, game will not spawn anything else
		if(objects.size() == ((Main.WIDTH/100)*(Main.WIDTH/100))){
			return;
		}
		
		boolean available = false;
		int x = 0, y = 0;
		while(available == false) {
			x = rand.nextInt(4);
			y = rand.nextInt(4);
			boolean isAvailable = true;
			for(int i = 0 ; i < objects.size(); i++) {
				if(objects.get(i).x / 100 == x && objects.get(i).y / 100 == y) { //Checks if the slots are available or not and in this case, it is not.
					isAvailable = false;
				}
			}
			if(isAvailable == true) {
				available = true;
			}
		}
		objects.add(new GameObject(x * 100, y * 100)); //Spawns in the new objects at certain points
	}

	private void movingLogic() {
		somethingIsMoving = false;
		for(int i = 0; i < objects.size(); i++) {
			if(objects.get(i).moving) {
				somethingIsMoving = true; //Checking if the object is moving
			}
		}
		if(somethingIsMoving == false) { // Nothing is moving
			moving = false;
			for(int i = 0; i < objects.size(); i++) {
				objects.get(i).Moved = false;
			}
		}
		if(moving == false && Moved) { //If we have moved and not moving, it will spawn a new sprite
			spawn();
			Moved = false;
		}
		if(moving == false && Moved == false) { //If we havent moved and we are not moving, we move
			if(Keyboard.keyDown(KeyEvent.VK_LEFT) || Keyboard.keyDown(KeyEvent.VK_A)) {
				Moved = true;
				moving = true;
				direction = 0;
			}else if(Keyboard.keyDown(KeyEvent.VK_RIGHT) || Keyboard.keyDown(KeyEvent.VK_D)) {
				Moved = true;
				moving = true;
				direction = 1;
			}else if(Keyboard.keyDown(KeyEvent.VK_UP) || Keyboard.keyDown(KeyEvent.VK_W)) {
				Moved = true;
				moving = true;
				direction = 2;
			}else if(Keyboard.keyDown(KeyEvent.VK_DOWN) || Keyboard.keyDown(KeyEvent.VK_S)) {
				Moved = true;
				moving = true;
				direction = 3;
			}
		}
	}
	
	public void render() {
		Renderer.colorBackground();

		//Rendering the sprites
		for(int i = 0; i < objects.size(); i++) {
			objects.get(i).render();
		}

		//Rendering the background from Renderer.java
		for(int i = 0 ; i < Main.pixels.length; i++) {
			Main.pixels[i] = Renderer.pixels[i];
		}
	}
	
	public void renderText(Graphics2D g) { //displays the number text
		g.setFont(new Font("Verdana", 0, 100));
		g.setColor(Color.BLACK);
		
		for(int i = 0; i < objects.size(); i++) {
			String s = objects.get(i).value + "";
			int sw = (int) (g.getFontMetrics().stringWidth(s) / 2 / Main.scale);
			g.drawString(s, (int) (objects.get(i).x + objects.get(i).width / 2 - sw) * Main.scale, (int) (objects.get(i).y + objects.get(i).height / 2 + 18) * Main.scale); //Getting the number
		}
		
	}	
}
