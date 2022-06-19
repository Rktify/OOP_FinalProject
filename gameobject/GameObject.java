package gameobject;

import java.util.Random;

import Main.Main;
import game.Game;
import graphics.Renderer;
import graphics.Sprite;

public class GameObject {

	//Declarations
	public double x, y;
	public int width, height;
	public Sprite sprite;
	public int value, speed = 20;
	public boolean moving, remove, Moved;
	Random rand = new Random();
	
	public GameObject(double x, double y) {
		this.x = x;
		this.y = y;
		this.value = (rand.nextBoolean() ? 2 : 4); // Basically the value is gonna 50% be 2 or 4
		createSprite();
		this.width = sprite.width;
		this.height = sprite.height;
	}

	public void createSprite() { //Creating every object
		if(this.value == 2) {
			this.sprite = new Sprite(100, 100, 0xeeee4da);
		}else if(this.value == 4) {
			this.sprite = new Sprite(100, 100, 0xede0c8);
		}else if(this.value == 8) {
			this.sprite = new Sprite(100, 100, 0xf2b179);
		}else if(this.value == 16) {
			this.sprite = new Sprite(100, 100, 0xf59563);
		}else if(this.value == 32) {
			this.sprite = new Sprite(100, 100, 0xf67c5f);
		}else if(this.value == 64) {
			this.sprite = new Sprite(100, 100, 0xf65e3b);
		}else if(this.value == 128) {
			this.sprite = new Sprite(100, 100, 0xedcf72);
		}else if(this.value == 256) {
			this.sprite = new Sprite(100, 100, 0xedcc61);
		}else if(this.value == 512) {
			this.sprite = new Sprite(100, 100, 0xedc850);
		}else if(this.value == 1024) {
			this.sprite = new Sprite(100, 100, 0xedc53f);
		}else if(this.value == 2048) {
			this.sprite = new Sprite(100, 100, 0xedc22e);
		}else if(this.value == 4096) {
			this.sprite = new Sprite(100, 100, 0x5DDB92);
		}else if(this.value == 8192) {
			this.sprite = new Sprite(100, 100, 0xEC4D58);
		}
	}

	public boolean canMove() { //Checking if the object can move or combine
		if(x < 0 || x + width > Main.WIDTH || y < 0 || y + height > Main.HEIGHT) {
			return false;
		}
		for(int i = 0; i < Game.objects.size(); i++) {
			if(this == Game.objects.get(i)) {
				continue;
			}
			if(x + width > Game.objects.get(i).x && x < Game.objects.get(i).x + Game.objects.get(i).width && y + height > Game.objects.get(i).y && y < Game.objects.get(i).y + Game.objects.get(i).height && value != Game.objects.get(i).value) {
				return false;
			}
		}
		return true;
	}
	
	public void move() { //The moves the blocks make
		if(Game.moving) {
			if(Moved == false) {
				Moved = true;
			}
			if(canMove()) {
				moving = true;
			}
			
			if(moving) {
				if(Game.direction == 0) {
					x -= speed; // Left
				}
				if(Game.direction == 1) {
					x += speed; // Right
				}
				if(Game.direction == 2) {
					y -= speed; // Up
				}
				if(Game.direction == 3) {
					y += speed; // Down
				}
			}
			if(canMove() == false) {
				moving = false;
				x = Math.round(x / 100) * 100;
				y = Math.round(y / 100) * 100;
			}
		}
	
	}
	public void render() { //Renders sprite
		Renderer.renderSprite(sprite, (int) x, (int) y);
	}
}
