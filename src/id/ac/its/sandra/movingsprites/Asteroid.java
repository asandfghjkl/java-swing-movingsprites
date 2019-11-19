package id.ac.its.sandra.movingsprites;

public class Asteroid extends Sprite {
	private final int BOARD_WIDTH = 400;
	public Asteroid(int x, int y) {
		super(x, y);
		
		initAsteroid();
	}
	
	private void initAsteroid() {
		loadImage("src/resources/asteroid.png");
		getImageDimensions();
	}
	
	public void move() {
		if(x < 0) {
			x = BOARD_WIDTH;
		}
		
		x -= 1;
	}
}
