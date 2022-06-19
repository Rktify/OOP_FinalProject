package graphics;

import Main.Main;

public class Renderer {
	
	public static int width = Main.WIDTH, height = Main.HEIGHT;
	public static int[] pixels = new int[width * height];
	
	public static void colorBackground() {
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				pixels[x + y * width] = 0xffffffff;

				//The color of the gaps between of each block
				//Basically, if it isnt the gap the colors gonna be the same but if it isnt its going to be ccccff.
				if(x % 100 < 3 || x % 100 > 97 || y % 100 < 3 || y % 100 > 97) {
					pixels[x + y * width] = 0xffccccff;
				}
			}
		}
	}
	
	public static void renderSprite(Sprite sprite, int xpos, int ypos) {
		if(xpos < -sprite.width || xpos > width || ypos < -sprite.height || ypos > height) {
			return; //Basically, if the sprite is outside of the screen then, we return
		}
		
		for(int y = 0; y < sprite.height; y++) {
			int yy = y + ypos;
			if(yy < 0 || yy > height) continue;
			//finding every Y pos and keeps checking if its out of the screen or not, if it is it continues to the X
			for(int x = 0; x < sprite.width; x++) {
				int xx = x + xpos;
				if(xx < 0 || xx > width) {
					continue;
				}
				//same thing as Y
				int color = sprite.pixels[x + y * sprite.height];
				if(color == 0xffff00ff) {
					continue;
				}
				else {pixels[xx + yy * width] = color;
				}
			}
		}
		
	}
	
}
