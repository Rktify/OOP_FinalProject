package graphics;

public class Sprite {

	public int width, height;
	public int[] pixels;
	
	public Sprite(int width, int height, int color) {
		this.width = width;
		this.height = height;
		this.pixels = new int[width * height];

		//Color of each block
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				pixels[x + y * width] = color;

				//The color of the "border" of each block
				//Basically, if it isnt a border the colors gonna be the same but if it isnt its going to be ff00ff.
				if(x % 100 < 3 || x % 100 > 97 || y % 100 < 3 || y % 100 > 97) {
					pixels[x + y * width] = 0xffff00ff;
				}
			}
		}
	}
	
}
